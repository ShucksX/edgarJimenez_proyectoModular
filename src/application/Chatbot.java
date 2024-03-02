package application;

import java.util.ArrayList;

public class Chatbot {
	private String response;
	private ArrayList<ArrayList<String>> inputs = new ArrayList<ArrayList<String>>();

	public Chatbot() {
		response = "No entiendo lo que me dices";
		fillArray();
		
	}
	
	public String getResponse(String input) {
		if(findInArray(0,input,true)) {
			response = "Hola! ¿Que dudas tienes?";
			return response;
		}
		else if(findInArray(1,input,false)) {
			response = "No puedo asegurarte si tienes esta condicion, pero te recomendamos hacer los tests en esta aplicacion para observar como se comporta tu memoria y concentracion. Si considera que estos aspectos empeoran, puede comunicarse con un centro de atencion en la ventana de Centros de Atencion para recibir consulta y ayuda profesional";
			return response;
		}
		else if(findInArray(2,input,false)) {
			response = "Nos vemos! Si quieres cerrar esta ventana solo presiona la cruz en la esquina superior.";
			return response;
		}
		else if (findInArray(3,input,false)){
			response = "Pare ver tus resultados solo cierra esta ventana y presiona el boton correspondiente a la prueba de la quieres ver los resultados, en la ventana de resultados. Aqui veras los resultados y puedes observar una grafica de los mismos.";
			return response;
		}
		else if (findInArray(4,input,false)) {
			response = "Estas pruebas sirven para comprobar y practicar tu memoria y atencion, para que con los resultados puedas deducir si requieres apoyo o no,";
			return response;
		}
		else if(findInArray(5,input,false)) {
			response = "La grafica es una representacion visual de tu desempeño en las pruebas, puedes acceder a ellos presionando el boton de Ver Grafica en la ventana de resultado de una prueba en especifico. En esta ventana veras una grafica de tu desempeño mediante el paso del tiempo al igual que un texto que explica si tu desempeño empeora, mejora o seria mejor hacer mas pruebas primero. Esto no confirma si tienes Alzheimer o no asi, que se recomienda buscar un centro de atencion si buscas confirmacion en ello. ";
			return response;
		}
		else if(findInArray(6,input,false)) {
			response = "En esta aplicacion puedes intentar 4 pruebas distintas. La primera es un laberinto, en donde deberas mover tu punto hacia la salida usando las flechas de direcciones, deberas hacerlo 5 veces y se medira el tiempo tardado. La siguiente prueba es la sopa de letras, es una sopa de letras tradicional en donde deberas encontrar palabras escondidas entra varias letras aleatorias y se medira el tiempo que tardes en hacerlo. La siguiente pruebe es la memorizacion de palabras, en donde deberas recordar 3 palabras en un orden, esperar un dia y decir las palabras que se te pidio recordar. Finalmente, la ultima prueba son los colores intermitentes, en donde deberas prestar atencion al color y figura que se te muestra y rapidamente localizar en que posicion aparece despues de un tiempo.";
			return response;
		}
		else if(findInArray(7,input,false)) {
			response = "Soy un chatbot que fue creado por Edgar Jimenez Aceves durante su estancia en el Centro Universitario de Ciencias Exactas e Ingenierias.";
			return response;
		}
		else if(findInArray(8,input,false)) {
			response = "No tengo ningun nombre pero espero poder ayudarte con cualquier duda que tenga de igual manera!";
			return response;
		}
		else {
			response = "No entiendo lo que me dices";
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
		inputs.get(0).add("hola!");

		inputs.get(0).add("holaa");
		inputs.get(0).add("saludos");
		inputs.get(0).add("saludo");
		inputs.get(0).add("buen dia");
		inputs.get(0).add("buen dia!");
		inputs.get(0).add("buenos dias");
		inputs.get(0).add("buenos dias!");
		inputs.get(0).add("buenas tardes");
		inputs.get(0).add("buenas noches");
		inputs.get(0).add("buenas tardes!");
		inputs.get(0).add("buenas noches!");
		//Pregunta 1
		inputs.add(new ArrayList<String>());
		inputs.get(1).add("¿tengo alzheimer?");
		inputs.get(1).add("¿tengo alzheimer");
		inputs.get(1).add("tengo alzheimer?");
		inputs.get(1).add("tengo alzheimer");
		inputs.get(1).add("¿tendre alzheimer?");
		inputs.get(1).add("¿tendre alzheimer");
		inputs.get(1).add("tendre alzheimer?");
		inputs.get(1).add("tendre alzheimer");
		inputs.get(1).add("¿sufro alzheimer?");
		inputs.get(1).add("¿sufro alzheimer");
		inputs.get(1).add("sufro alzheimer?");
		inputs.get(1).add("sufro alzheimer");
		inputs.get(1).add("¿sufrir alzheimer?");
		inputs.get(1).add("¿sufrir alzheimer");
		inputs.get(1).add("sufrir alzheimer?");
		inputs.get(1).add("sufrir alzheimer");
		//Despedida
		inputs.add(new ArrayList<String>());
		inputs.get(2).add("adios");
		inputs.get(2).add("nos vemos");
		inputs.get(2).add("quiero irme");
		inputs.get(2).add("bye");
		inputs.get(2).add("adios");
		inputs.get(2).add("te veo luego");
		inputs.get(2).add("como cierro la ventana?");
		inputs.get(2).add("¿como cierro la ventana?");
		inputs.get(2).add("¿como cierro la ventana");
		inputs.get(2).add("como cerrar la ventana?");
		inputs.get(2).add("¿como cerrar la ventana?");
		inputs.get(2).add("¿como cerrar la ventana");
		//Pregunta 2
		inputs.add(new ArrayList<String>());
		inputs.get(3).add("¿donde puedo ver mis resultados?");
		inputs.get(3).add("donde puedo ver mis resultados?");
		inputs.get(3).add("¿donde puedo ver mis resultados");
		inputs.get(3).add("donde puedo ver mis resultados");
		inputs.get(3).add("¿donde resultados?");
		inputs.get(3).add("donde resultados?");
		inputs.get(3).add("donde resultados");
		inputs.get(3).add("¿donde estan mis resultados?");
		inputs.get(3).add("donde estan mis resultados?");
		inputs.get(3).add("donde estan mis resultados");
		inputs.get(3).add("ya hice mis pruebas");
		inputs.get(3).add("¿donde puedo ver mis resultados?");
		//Pregunta 3
		inputs.add(new ArrayList<String>());
		inputs.get(4).add("de que sirven estas pruebas");
		inputs.get(4).add("de que sirven estas pruebas?");
		inputs.get(4).add("¿de que sirven estas pruebas");
		inputs.get(4).add("¿de que sirven estas pruebas?");
		inputs.get(4).add("de que sirven estas tests");
		inputs.get(4).add("para que sirven los tests");
		inputs.get(4).add("para que sirven las pruebas");
		inputs.get(4).add("de que sirven estas tests?");
		inputs.get(4).add("¿de que sirven estas tests");
		inputs.get(4).add("¿de que sirven estas tests?");
		inputs.get(4).add("estas prueban parecen inutiles");
		//Pregunta 4
		inputs.add(new ArrayList<String>());
		inputs.get(5).add("no entiendo la grafica");
		inputs.get(5).add("no entiendo la tablita");
		inputs.get(5).add("como funciona la tablita");
		inputs.get(5).add("como funciona la tablita?");
		inputs.get(5).add("¿como funciona la tablita?");
		inputs.get(5).add("como funciona la grafica");
		inputs.get(5).add("como funciona la grafica?");
		inputs.get(5).add("¿como funciona la grafica?");
		inputs.get(5).add("explicame la grafica");
		inputs.get(5).add("explicame la tablita");
		//Pregunta 5
		inputs.add(new ArrayList<String>());
		inputs.get(6).add("como funcionan las pruebas");
		inputs.get(6).add("como funcionan las pruebas?");
		inputs.get(6).add("¿como funcionan las pruebas?");
		inputs.get(6).add("como funcionan los tests");
		inputs.get(6).add("como funcionan los tests?");
		inputs.get(6).add("¿como funcionan los tests?");
		//Pregunta 6
		inputs.add(new ArrayList<String>());
		inputs.get(7).add("quien te creo");
		inputs.get(7).add("¿quien te creo?");
		inputs.get(7).add("quien te creo?");
		inputs.get(7).add("cual es tu creador?");
		inputs.get(7).add("cual es tu creador");
		inputs.get(7).add("¿cual es tu creador?");
		inputs.get(7).add("quien eres");
		inputs.get(7).add("quien eres?");
		inputs.get(7).add("¿quien eres?");
		//Pregunta 7
		inputs.add(new ArrayList<String>());
		inputs.get(8).add("como te llamas");
		inputs.get(8).add("como te llamas?");
		inputs.get(8).add("¿como te llamas?");
		inputs.get(8).add("cual es tu nombre");
		inputs.get(8).add("cual es tu nombre?");
		inputs.get(8).add("¿cual es tu nombre?");

	}
}
