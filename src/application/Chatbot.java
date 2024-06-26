package application;

import java.util.ArrayList;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.Span;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.langdetect.*;
import opennlp.tools.lemmatizer.LemmatizerModel;
import opennlp.tools.lemmatizer.LemmatizerME;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;


public class Chatbot {
	private String response;
	private ArrayList<ArrayList<String>> inputs = new ArrayList<ArrayList<String>>();

	public Chatbot() {
		response = "No entiendo lo que me dices";
		fillArray();
		
	}
	
	public String tokenizeBasedOnModel(String text){
        try {

            //Load the token models
            InputStream is = new FileInputStream("src\\es-token.bin");
            TokenizerModel model = new TokenizerModel(is);

            //Create the tokenizer ME model
            TokenizerME tokenizerME = new TokenizerME(model);

            //Print the tokens
            String [] tokenS = tokenizerME.tokenize(text);
            for(String tokens: tokenS){
                System.out.println(tokens);
            }

            //Get the position of the tokens
            Span[] spans = tokenizerME.tokenizePos(text);
            for(Span span: spans){
                System.out.println(span);
            }

            //Get the token probabilities
            double[] prob = tokenizerME.getTokenProbabilities();
            for(double s: prob){
                System.out.println(s);
            }

            //Consider the Alpha Numeric optimization
            boolean isAlphaNumeric = tokenizerME.useAlphaNumericOptimization();
            System.out.println(isAlphaNumeric);
            return lemmatize(tokenS);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "Hubo un error, intente de nuevo mas tarde";
        } catch (IOException e) {
            e.printStackTrace();
            return "Hubo un error, intente de nuevo mas tarde";
        }    
	}
	
	public String lemmatize(String[] tokens){
        try{
            // Parts-Of-Speech Tagging
            // reading parts-of-speech model to a stream
            // place the model file "en-pos-maxent.bin" in the folder "models", located in the project root directory
            InputStream posModelIn = new FileInputStream("src\\es-pos.bin");
            // loading the parts-of-speech model from stream
            POSModel posModel = new POSModel(posModelIn);
            // initializing the parts-of-speech tagger with model
            POSTaggerME posTagger = new POSTaggerME(posModel);
            // Tagger tagging the tokens
            String tags[] = posTagger.tag(tokens);
 
            // loading the dictionary to input stream
            // find en-lemmatizer.txt at https://raw.githubusercontent.com/richardwilly98/elasticsearch-opennlp-auto-tagging/master/src/main/resources/models/en-lemmatizer.dict
            InputStream lemmatizerM = new FileInputStream("src\\es-lemmatizer.bin");
            // loading the lemmatizer with dictionary
            LemmatizerModel lemmatizerModel = new LemmatizerModel(lemmatizerM);
            LemmatizerME lemmatizerME = new LemmatizerME(lemmatizerModel);
            
            // finding the lemmas
           
            String[] lemmas = lemmatizerME.lemmatize(tokens, tags);
            // printing the results
            System.out.println("\nPrinting lemmas for the given sentence...");
            System.out.println("WORD -POSTAG : LEMMA");
            String input = "";
            for(int i=0;i< tokens.length;i++){
                System.out.println(tokens[i]+" -"+tags[i]+" : "+lemmas[i]);
                input += lemmas[i] + " ";
            }
            return detectSentence(input);
 
        } catch (FileNotFoundException e){
            e.printStackTrace();
            return "Hubo un error, intente de nuevo más tarde";
        } catch (IOException e) {
            e.printStackTrace();
            return "Hubo un error, intente de nuevo más tarde";
        }
    }
	
	public String detectSentence(String rawText){
        try {
        	String response = "";

            //Load the sentence detection model
            InputStream is = new FileInputStream("src\\es-sent.bin");
            SentenceModel modelObj = new SentenceModel(is);

            //Break raw text into sentences
            SentenceDetectorME detectorME = new SentenceDetectorME(modelObj);
            String[] detectedSen = detectorME.sentDetect(rawText);
            System.out.println("All sentences detected are:");
            System.out.println(Arrays.toString(detectedSen));
            for(String sentence: detectedSen) {
            	String nuevaRes = getResponse(sentence);
            	if (!nuevaRes.equalsIgnoreCase("No entiendo lo que me dices")) {
            		response = response + nuevaRes;
            	}
            }
            if (response.equalsIgnoreCase("")) {
        		response = "No entiendo lo que me dices";
        	}
            else if (response.equalsIgnoreCase("Hola! ")) {
        		response = response + "¿Que dudas tienes?";
        	}

            //Find position of all sentences
            Span[] spans = detectorME.sentPosDetect(rawText);
            System.out.println("Positions of all detected sentences:");
            for(Span span: spans){
                System.out.println(span);
            }
            return response;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "Hubo un error, intente de nuevo más tarde";
        } catch (IOException e) {
            e.printStackTrace();
            return "Hubo un error, intente de nuevo más tarde";
        }
    }
	
	public String getResponse(String inputI) {
		String input = inputI.replace(" .", "");
		input = input.replace(".", "");
		System.out.println(input);
		if(findInArray(0,input,true)) {
			response = "Hola! ";
			return response;
		}
		else if(findInArray(1,input,false)) {
			response = "No puedo asegurarte si tienes esta condición, solicita esa información a tu especialista. Si no cuentas con un especialista que te proporcione atención, puedo recomendarte que visites la ventana de centros de atención y buscas a un especialista para ti. ";
			return response;
		}
		else if(findInArray(2,input,false)) {
			response = "Nos vemos! Si quieres cerrar esta ventana solo presiona la cruz en la esquina superior. ";
			return response;
		}
		else if (findInArray(3,input,false)){
			response = "Para ver tus resultados solo cierra esta ventana y presiona el botón correspondiente al ejercicio del quieres ver los resultados, esto se encuentra en la ventana de resultados. Aquí veras los resultados y puedes observar una gráfica de los mismos. ";
			return response;
		}
		else if (findInArray(4,input,false)) {
			response = "Estos ejercicios funcionan para estimular tu atención, concentración y conocimiento en casos de Alzheimer leve a moderado,";
			return response;
		}
		else if(findInArray(5,input,false)) {
			response = "La grafica es una representación visual de tu desempeño en los ejercicios, puedes acceder a ellos presionando el botón de Ver Grafica en la ventana de resultado de un ejercicio en específico. En esta ventana veras una gráfica de tu desempeño mediante el paso del tiempo al igual que un texto que explica si tu desempeño empeora, mejora o sería mejor hacer más ejercicios primero.";
			return response;
		}
		else if(findInArray(6,input,false)) {
			response = "En esta aplicación puedes intentar 4 ejercicios distintos. El primero es un laberinto, en donde deberás mover tu punto hacia la salida usando las flechas de direcciones, deberás hacerlo 5 veces y se medirá el tiempo tardado. El siguiente ejercicio es la sopa de letras, es una sopa de letras tradicional en donde deberás encontrar palabras escondidas entra varias letras aleatorias y se medirá el tiempo que tardes en hacerlo. El siguiente ejercicio es identificar siluetas en donde se te mostraran 10 siluetas y deberás identificar si se trata de un ave o no. Finalmente, el último ejercicio son los colores intermitentes, en donde deberás prestar atención al color y figura que se te muestra y rápidamente localizar en qué posición aparece después de un tiempo. ";
			return response;
		}
		else if(findInArray(7,input,false)) {
			response = "Soy un chatbot que fue creado por Edgar Jimenez Aceves durante su estancia en el Centro Universitario de Ciencias Exactas e Ingenierías. ";
			return response;
		}
		else if(findInArray(8,input,false)) {
			response = "¡No tengo ningún nombre, pero espero poder ayudarte con cualquier duda que tenga de igual manera! ";
			return response;
		}
		else {
			response = "No entiendo parte de lo que me dices. ";
			return response;
		}
	}

	
	private boolean findInArray(int index1, String input, boolean strict) {
		for (int i = 0; i < inputs.get(index1).size();i++) {
			if(strict) {
				if (input.equalsIgnoreCase(inputs.get(index1).get(i))) {
					return true;
				}
			}
			else {
				String inp = input.toLowerCase();
				if(inp.contains(inputs.get(index1).get(i))) {
					return true;
				}
			}
		}
		return false;
	}
	
	private void fillArray() {
		//Saludos
		inputs.add(new ArrayList<String>());
		inputs.get(0).add("hola");
		inputs.get(0).add("hola");
		inputs.get(0).add("saludo");
		inputs.get(0).add("buen dia");
		inputs.get(0).add("buen tarde");
		inputs.get(0).add("buen noche");
		//Pregunta 1
		inputs.add(new ArrayList<String>());
		inputs.get(1).add("tener alzheimer");
		inputs.get(1).add("ten alzheimer");
		inputs.get(1).add("sufro alzheimer");
		inputs.get(1).add("sufrir alzheimer");
		//Despedida
		inputs.add(new ArrayList<String>());
		inputs.get(2).add("adio");
		inputs.get(2).add("yo ver");
		inputs.get(2).add("bye");
		inputs.get(2).add("tú ver luego");
		inputs.get(2).add("tú ver despues");
		inputs.get(2).add("como cerrar el ventana");
		//Pregunta 2
		inputs.add(new ArrayList<String>());
		inputs.get(3).add("donde poder ver mi resultado");
		inputs.get(3).add("donde resultar");
		inputs.get(3).add("donde ejercicio");
		inputs.get(3).add("donde estar mi resultado");
		inputs.get(3).add("donde estan mi resultado");
		inputs.get(3).add("donde estan mi ejercicio");
		inputs.get(3).add("donde estar mi ejercicio");
		inputs.get(3).add("ya hacer mi ejercicio");
		inputs.get(3).add("donde poder ver mi resultado");
		//Pregunta 3
		inputs.add(new ArrayList<String>());
		inputs.get(4).add("de que servir este ejercicio");
		inputs.get(4).add("de que servir este tests");
		inputs.get(4).add("para que servir el tests");
		inputs.get(4).add("para que servir el ejercicio");
		inputs.get(4).add("de que servir el tests");
		inputs.get(4).add("de que servir el ejercicio");
		inputs.get(4).add("este ejercicio parecer inutil");
		//Pregunta 4
		inputs.add(new ArrayList<String>());
		inputs.get(5).add("no entender el grafica");
		inputs.get(5).add("no entender el tablita");
		inputs.get(5).add("como funcionar el tablita");
		inputs.get(5).add("como funcionar el grafica");
		inputs.get(5).add("explicayo el grafica");
		inputs.get(5).add("yo poder explicar el grafica");
		inputs.get(5).add("explicayo el tablita");
		inputs.get(5).add("yo poder explicar el tablita");
		//Pregunta 5
		inputs.add(new ArrayList<String>());
		inputs.get(6).add("como funcionar el ejercicio");
		inputs.get(6).add("como funcionar el tests");
		//Pregunta 6
		inputs.add(new ArrayList<String>());
		inputs.get(7).add("quien tú creer");
		inputs.get(7).add("cual ser tu creador");
		inputs.get(7).add("quien ser tu creador");
		inputs.get(7).add("quien ser");
		//Pregunta 7
		inputs.add(new ArrayList<String>());
		inputs.get(8).add("como tú llamasr");
		inputs.get(8).add("cual ser tu nombre");
	}
}
