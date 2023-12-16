package application;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class PalabrasScreen {
	private Scene palabrasScene;
	private VBox layout;
	private Label lblTitulo, lblPalabra1,lblPalabra2,lblPalabra3, lblDisclaimer1,lblDisclaimer2,instruccion;
	private Button botonVolver,botonAceptar;
	private TextField fieldPalabra1, fieldPalabra2, fieldPalabra3;
	private RandomWord wordgen;
	private String palabra1,palabra2,palabra3;
	public PalabrasScreen() {
		wordgen = new RandomWord();
		layout = new VBox(8);
		layout.setPadding(new Insets(10,10,10,10));
		layout.setAlignment(Pos.BASELINE_CENTER);
		//Add components
		lblTitulo = new Label ("Memorizacion de palabras");
		Font fontTitulo = Font.font("Arial",FontWeight.BOLD,22);
		lblTitulo.setFont(fontTitulo);
	}
	//ESCENA PARA DAR PALABRAS A RECORDAR
	public void setupScene1(Stage primaryStage, String userid, ClienteScreen clienteScene) {
		Font fontTitulo = Font.font("Courier New",FontWeight.BOLD,18);
		palabra1 = wordgen.getRandomWord();
		lblPalabra1 = new Label(palabra1);
		lblPalabra1.setFont(fontTitulo);

		palabra2= "";
		do {
			palabra2 = wordgen.getRandomWord();
		}while (palabra2.compareTo(palabra1)==0);
		lblPalabra2 = new Label(palabra2);
		lblPalabra2.setFont(fontTitulo);
		
		palabra3= "";
		do {
			palabra3 = wordgen.getRandomWord();
		}while (palabra3.compareTo(palabra2)==0 || palabra3.compareTo(palabra1)==0);
		lblPalabra3 = new Label(palabra3);
		lblPalabra3.setFont(fontTitulo);
		
		Font fontTexto = Font.font("Courier New",FontWeight.NORMAL,16);
		
		lblDisclaimer1 = new Label("Memorice estas palabras y regrese dentro de un dia");
		lblDisclaimer1.setFont(fontTexto);
		lblDisclaimer2 = new Label("Por favor no anote estas palabras o similar.");
		lblDisclaimer2.setWrapText(true);
		lblDisclaimer2.setFont(fontTexto);
		
		int minwidth = 300;
		botonVolver = new Button();
		botonVolver.setText("Volver a seleccion de test");
		botonVolver.setMinWidth(minwidth);
		botonVolver.setFont(fontTexto);

		botonAceptar = new Button();
		botonAceptar.setText("Aceptar");
		botonAceptar.setMinWidth(minwidth);
		botonAceptar.setOnAction(e -> {
			try {
				registrarPalabraBD(primaryStage,userid,clienteScene);
			} catch (IOException | InterruptedException e1) {
				AlertBox.display("Error", "Hubo un error al registrar el test\nIntente de nuevo mas tarde");
				e1.printStackTrace();
			}
		});
		botonAceptar.setFont(fontTexto);
	}
	//ESCENA PARA EVALUAR RESPUESTA
	public void setupScene2(Stage primaryStage,String userid, ClienteScreen clienteScene,String respuesta) {
		int index1 = respuesta.indexOf("|");
		int index2 = respuesta.lastIndexOf("|");
		palabra1 = respuesta.substring(0,index1);
		palabra2 = respuesta.substring(index1+1,index2);
		palabra3 = respuesta.substring(index2+1);
		System.out.println(palabra1);
		System.out.println(palabra2);
		System.out.println(palabra3);
		
		Font fontTexto = Font.font("Arial",FontWeight.NORMAL,16);
		
		instruccion = new Label("Escriba las palabra que se le pidio recordar");
		instruccion.setFont(fontTexto);


		lblPalabra1 = new Label("Primer palabra");
		lblPalabra1.setFont(fontTexto);
		
		fieldPalabra1 = new TextField();
		fieldPalabra1.setPromptText("Primer palabra");
		fieldPalabra1.setFont(fontTexto);

		lblPalabra2 = new Label("Segunda palabra");
		lblPalabra2.setFont(fontTexto);

		fieldPalabra2 = new TextField();
		fieldPalabra2.setPromptText("Segunda palabra");
		fieldPalabra2.setFont(fontTexto);
		
		lblPalabra3 = new Label("Tercer palabra");
		lblPalabra3.setFont(fontTexto);

		fieldPalabra3 = new TextField();
		fieldPalabra3.setPromptText("Tercer palabra");
		fieldPalabra3.setFont(fontTexto);
		
		botonVolver = new Button();
		botonVolver.setText("Volver a seleccion de test");
		botonVolver.setMinWidth(250);
		botonVolver.setFont(fontTexto);

		botonAceptar = new Button();
		botonAceptar.setText("Enviar respuesta");
		botonAceptar.setMinWidth(250);
		botonAceptar.setOnAction(e -> {
			try {
				evaluarRespuesta(primaryStage,userid,clienteScene);
			} catch (IOException e1) {
				AlertBox.display("Error", "La conexion al servidor se interrumpio");
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				AlertBox.display("Error", "La conexion al servidor se interrumpio");
				e1.printStackTrace();
			}
		});
		botonAceptar.setFont(fontTexto);
	}
	
	private void evaluarRespuesta(Stage primaryStage,String userid, ClienteScreen clienteScene) throws IOException, InterruptedException {
		int puntuacion = 0;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		if (fieldPalabra1.getText() == "" || fieldPalabra2.getText() == "" || fieldPalabra3.getText() == "") {
			AlertBox.display("Atencion", "Por favor llene todos los campos con las palabras que se le pidio recordar");
			return;
		}
		if (fieldPalabra1.getText().equalsIgnoreCase(palabra1)) {
			puntuacion++;
		}
		if (fieldPalabra2.getText().equalsIgnoreCase(palabra2)) {
			puntuacion++;
		}
		if (fieldPalabra3.getText().equalsIgnoreCase(palabra3)) {
			puntuacion++;
		}
		AlertBox.display("Resultado", "Usted acerto " + puntuacion + " de 3 palabras");
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(URI.create
				(Utilities.getBaseURL() + "/modificarpalabra.php?iduser="+userid+
						"&fecharespuesta="+formatter.format(date)+"&puntuacion="+puntuacion)).GET().build();
		
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		//System.out.println(response.body());
		if (response.body().contains("Exito#")) {
			AlertBox.display("Exito", "Se registro su resultado en la base de datos");
			primaryStage.setScene(clienteScene.getScene());
		}
		else {
			AlertBox.display("Error", "Error al enviar su respuesta");
		}
	}
	
	private void registrarPalabraBD(Stage primaryStage, String userid, ClienteScreen clienteScene) throws IOException, InterruptedException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date date2 = calendar.getTime();
		System.out.println(formatter.format(date2));
		
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(URI.create
				(Utilities.getBaseURL() + "/registrarpalabra.php?iduser="+userid+"&palabra1="+
		palabra1+"&palabra2="+palabra2+"&palabra3="+palabra3+"&fechaacc="+formatter.format(date2))).GET().build();
		
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		System.out.println(response.body());
		if (response.body().contains("Exito")) {
			AlertBox.display("Registro exitoso", "Su test se registro con exito\nPodra responder en 1 dia");
			primaryStage.setScene(clienteScene.getScene());
		}
		else {
			AlertBox.display("Alerta", response.body());
		}
	}
	
	public String buscarPalabraBD(String userid) throws IOException, InterruptedException {	
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(URI.create
				(Utilities.getBaseURL() + "/buscarpalabra.php?iduser="+userid)).GET().build();
		
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		System.out.println(response.body());
		return response.body();
	}
	//ESCENA PARA MOSTRAR PALABRAS
	public void finishScene1() {
		layout.getChildren().addAll(lblTitulo,lblPalabra1,lblPalabra2,lblPalabra3,
				lblDisclaimer1,lblDisclaimer2,botonAceptar,botonVolver);
		BackgroundFill backgroundFill =
		        new BackgroundFill(
		                Color.valueOf("#b6d8f2"),
		                new CornerRadii(10),
		                new Insets(10)
		                );

		Background background =
		        new Background(backgroundFill);
		layout.setBackground(background);
		layout.setPadding(new Insets(10,15,10,15));
		//Set Scene
		palabrasScene = new Scene(layout,Utilities.windowWidth,Utilities.windowHeight);
		palabrasScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	}
	//ESCENA PARA RESPONDER
	public void finishScene2() {
		layout.getChildren().addAll(lblTitulo,instruccion,lblPalabra1,fieldPalabra1,
				lblPalabra2,fieldPalabra2,lblPalabra3,fieldPalabra3,botonAceptar,botonVolver);
		BackgroundFill backgroundFill =
		        new BackgroundFill(
		                Color.valueOf("#b6d8f2"),
		                new CornerRadii(10),
		                new Insets(10)
		                );

		Background background =
		        new Background(backgroundFill);
		layout.setBackground(background);
		layout.setPadding(new Insets(10,15,10,15));

		//Set Scene
		palabrasScene = new Scene(layout,Utilities.windowWidth,Utilities.windowHeight);
		palabrasScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	}
	
	public Button getBotonVolver() {
		return botonVolver;
	}
	
	public Scene getScene() {
		return palabrasScene;
	}
}
