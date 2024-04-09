package application;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Random;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SiluetasScreen {
	private Scene siluetasScene;
	Stage primStage;
	Scene retScene;
	private int numPruebas = 10;
	private String userID;
	private Button botonVolver, botonIniciar;
	private ArrayList<Silueta> siluList = new ArrayList<Silueta>();
	private boolean modoNC;
	public SiluetasScreen(String userId, Stage primaryStage, Scene returnScene, boolean modoNC) {
		this.modoNC = modoNC;
		llenarSiluetas();
		userID = userId;
		BorderPane layout = new BorderPane();
		primStage = primaryStage;
		retScene = returnScene;
		
		VBox center = new VBox();
		center.setSpacing(10);
		center.setPadding(new Insets(220,10,10,50));
		center.setAlignment(Pos.TOP_CENTER);
		//Add components
		Font fontTitulo = Font.font("Courier New",FontWeight.BOLD,22);
		Font fontTexto = Font.font("Courier New",FontWeight.NORMAL,16);
		Font fontCreditos = Font.font("Courier New",FontWeight.NORMAL,12);
		
		Label lblTitulo = new Label ("Siluetas");
		lblTitulo.setFont(fontTitulo);
		
		int minwidth = 300;
		
		Label lblInstrucciones = new Label();
		lblInstrucciones.setText("Se mostraran siluetas de animales en este ejercicio, debera responder a preguntas sobre el tipo de animal que se muestra.");
		lblInstrucciones.setFont(fontTexto);

		botonIniciar = new Button();
		botonIniciar.setText("Iniciar ejercicio");
		botonIniciar.setMinWidth(minwidth);
		botonIniciar.setFont(fontTexto);
		botonIniciar.setDefaultButton(true);
		botonIniciar.setOnAction(e-> empezarEvaluacion(0,0));
		
		botonVolver = new Button();
		botonVolver.setText("Volver a selección de ejercicios");
		botonVolver.setMinWidth(minwidth);
		botonVolver.setFont(fontTexto);
		botonVolver.setOnAction(e-> primaryStage.setScene(retScene));
		
		Label lblCreditos = new Label("Siluetas extraidas de https://www.freepik.com/free-vector/illustration-drawing-style-animal-collection_2945481.htm#");
		lblCreditos.setFont(fontCreditos);
		
		center.getChildren().addAll(lblTitulo,lblInstrucciones,botonIniciar,botonVolver,lblCreditos);
		
		
		layout.setCenter(center);
		BackgroundFill backgroundFill =
		        new BackgroundFill(
		                Color.valueOf("#b6d8f2"),
		                new CornerRadii(10),
		                new Insets(10)
		                );

		Background background =
		        new Background(backgroundFill);
		layout.setBackground(background);
		
		//Set Scene
		siluetasScene = new Scene(layout,Utilities.windowWidth,Utilities.windowHeight);
		siluetasScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
	}
	
	private void llenarSiluetas() {
		siluList.add(new Silueta("Silueta1.png","Jirafa",false));
		siluList.add(new Silueta("Silueta2.png","Loro",true));
		siluList.add(new Silueta("Silueta3.png","Pomeranian",false));
		siluList.add(new Silueta("Silueta4.png","Gato",false));
		siluList.add(new Silueta("Silueta5.png","Lobo",false));
		siluList.add(new Silueta("Silueta6.png","Tortuga",false));
		siluList.add(new Silueta("Silueta7.png","Tiburon",false));
		siluList.add(new Silueta("Silueta8.png","Gallina",true));
		siluList.add(new Silueta("Silueta9.png","Avestruz",true));
		siluList.add(new Silueta("Silueta10.png","Vaca",false));
		Collections.shuffle(siluList);
	}
	
	private void empezarEvaluacion(int puntaje, int contador) {
		int siluetaEscogida = contador;

		BorderPane layout = new BorderPane();
		VBox center = new VBox();
		center.setSpacing(10);
		center.setPadding(new Insets(10,10,10,10));
		center.setAlignment(Pos.CENTER);
		VBox top = new VBox();
		top.setSpacing(10);
		top.setPadding(new Insets(10,10,10,10));
		top.setAlignment(Pos.TOP_CENTER);
		HBox bottom = new HBox();
		bottom.setSpacing(10);
		bottom.setPadding(new Insets(10,10,30,10));
		bottom.setAlignment(Pos.TOP_CENTER);
		//Add components
		Font fontTitulo = Font.font("Courier New",FontWeight.BOLD,22);
		Font fontTexto = Font.font("Courier New",FontWeight.NORMAL,16);
		
		Label lblTitulo = new Label ("Siluetas");
		lblTitulo.setFont(fontTitulo);
		
		int minwidth = 300;
		
		Label lblInstrucciones = new Label();
		lblInstrucciones.setText("¿Es esto un ave?");
		lblInstrucciones.setFont(fontTexto);
		
		Button btnVolver = new Button();
		btnVolver.setText("Volver a selección de ejercicios");
		btnVolver.setMinWidth(minwidth);
		btnVolver.setFont(fontTexto);
		btnVolver.setOnAction(e-> salirEscena());
		
		Button btnSi = new Button();
		btnSi.setText("Sí");
		btnSi.setMinWidth(minwidth);
		btnSi.setFont(fontTexto);
		btnSi.setOnAction(e-> {
			if(siluList.get(siluetaEscogida).getAve()) {
				AlertBox.display("Resultado", "¡Correcto!");
				if(contador+1 >= numPruebas) {
					AlertBox.display("Resultado final", "Has acertado " + (puntaje +1)+ " de " + numPruebas);
					try {
						registrarResultadoBD(puntaje+1,0);
					} catch (IOException e1) {
						AlertBox.display("Error", "Error al conectar con el servidor.");
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						AlertBox.display("Error", "Error al conectar con el servidor.");
						e1.printStackTrace();
					}
					salirEscena();
				}
				else {
					empezarEvaluacion(puntaje+1,contador+1);
				}
			}
			else {
				AlertBox.display("Resultado", "Incorrecto");
				if(contador+1 >= numPruebas) {
					AlertBox.display("Resultado final", "Has acertado " + puntaje + " de " + numPruebas);
					try {
						registrarResultadoBD(puntaje,0);
					} catch (IOException e1) {
						AlertBox.display("Error", "Error al conectar con el servidor.");
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						AlertBox.display("Error", "Error al conectar con el servidor.");
						e1.printStackTrace();
					}
					salirEscena();
				}
				else {
					empezarEvaluacion(puntaje,contador+1);
				}
			}
			
			
		});
		
		Button btnNo = new Button();
		btnNo.setText("No");
		btnNo.setMinWidth(minwidth);
		btnNo.setFont(fontTexto);
		btnNo.setOnAction(e-> {
			if(!siluList.get(siluetaEscogida).getAve()) {
				AlertBox.display("Resultado", "¡Correcto!");
				if(contador + 1 >= numPruebas) {
					AlertBox.display("Resultado final", "Has acertado " + (puntaje +1)+ " de " + numPruebas);
					try {
						registrarResultadoBD(puntaje+1,0);
					} catch (IOException e1) {
						AlertBox.display("Error", "Error al conectar con el servidor.");
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						AlertBox.display("Error", "Error al conectar con el servidor.");
						e1.printStackTrace();
					}
					salirEscena();
				}
				else {
					empezarEvaluacion(puntaje+1,contador+1);
				}
			}
			else {
				AlertBox.display("Resultado", "Incorrecto");
				if(contador + 1 >= numPruebas) {
					AlertBox.display("Resultado final", "Has acertado " + puntaje + " de " + numPruebas);
					try {
						registrarResultadoBD(puntaje,0);
					} catch (IOException e1) {
						AlertBox.display("Error", "Error al conectar con el servidor.");
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						AlertBox.display("Error", "Error al conectar con el servidor.");
						e1.printStackTrace();
					}
					salirEscena();
				}
				else {
					empezarEvaluacion(puntaje,contador+1);
				}
			}
		});
		
		Label silueta = new Label();
		Image img = new Image(siluList.get(siluetaEscogida).getTextura());
		ImageView view = new ImageView(img);
		view.setFitHeight(500);
		view.setPreserveRatio(true);
		silueta.setGraphic(view);
		
		top.getChildren().addAll(lblTitulo,lblInstrucciones);
		center.getChildren().add(silueta);
		bottom.getChildren().addAll(btnSi,btnNo,btnVolver);
		
		layout.setTop(top);
		layout.setCenter(center);
		layout.setBottom(bottom);
		BackgroundFill backgroundFill =
		        new BackgroundFill(
		                Color.valueOf("#b6d8f2"),
		                new CornerRadii(10),
		                new Insets(10)
		                );

		Background background =
		        new Background(backgroundFill);
		layout.setBackground(background);
		
		Scene nextScene = new Scene(layout,Utilities.windowWidth,Utilities.windowHeight);
		nextScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primStage.setScene(nextScene);
	}
	
	public void registrarResultadoBD(int puntuacion, int errCount) throws IOException, InterruptedException {
		if (modoNC)
			return;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(URI.create
				(Utilities.getBaseURL() + "/Resultado/resregistrarsiluetas.php?iduser="
		+userID + "&puntos="+ puntuacion+ "&fecharesultado=" + formatter.format(date))).GET().build();
		
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		System.out.println(response.body());
		if (!response.body().contains("Exito#")) {
			if (errCount < 5 ) {
					AlertBox.display("Registro", "Resultado registrado con exito.");
					registrarResultadoBD(puntuacion,errCount+1);
			}
			else {
				AlertBoxNonWait.display("Error", "No se pudo registrar su resultado");
			}
		}
	}
	
	private void salirEscena() {
		primStage.setScene(retScene);
	}
	public Scene getScene() {
		return siluetasScene;
	}
	
	public Button getBotonVolver() {
		return botonVolver;
	}
	
	public Button getBotonIniciar() {
		return botonIniciar;
	}
}
