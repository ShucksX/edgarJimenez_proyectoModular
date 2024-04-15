package application;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import javafx.animation.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ColoresScreen {
	private Scene coloresScene;
	private VBox layout;
	private Label lblTitulo, lblInstrucciones1,lblInstrucciones2,lblInstrucciones3;
	private Button botonVolver,botonIniciar;
	private int colorCorrecto,botonCorrecto,puntuacion,contador;
	private String userID;
	private boolean modoNC;
	public ColoresScreen(String userid, boolean modoNC) {
		this.modoNC = modoNC;
		userID = userid;
		puntuacion = 0;
		contador= 0;
		layout = new VBox();
		layout.setSpacing(8);
		layout.setPadding(new Insets(10,10,10,10));
		layout.setAlignment(Pos.BASELINE_CENTER);
		//Add components
		Font fontTitulo = Font.font("Courier New",FontWeight.BOLD,22);
		Font fontTexto = Font.font("Courier New",FontWeight.NORMAL,16);
		
		lblTitulo = new Label ("Colores intermitentes");
		lblTitulo.setFont(fontTitulo);
		
		lblInstrucciones1 = new Label("A continuación se le presentaran 6 colores por un segundo.");
		lblInstrucciones1.setFont(fontTexto);
		lblInstrucciones2 = new Label("Deberá escoger la ubicación del color que se le pide.");
		lblInstrucciones2.setFont(fontTexto);
		lblInstrucciones3 = new Label("(Los colores también tendrán una figura asociado)");
		lblInstrucciones3.setFont(fontTexto);
		
		int minwidth = 300;
		botonIniciar = new Button();
		botonIniciar.setText("Iniciar ejercicio");
		botonIniciar.setMinWidth(minwidth);
		botonIniciar.setFont(fontTexto);
		
		botonVolver = new Button();
		botonVolver.setText("Volver a selección de ejercicios");
		botonVolver.setMinWidth(minwidth);
		botonVolver.setFont(fontTexto);

		
	}
	
	private void setupScene(Stage primaryStage) {
		Font fontTexto = Font.font("Courier New",FontWeight.NORMAL,16);
		Label instruccion = new Label("Debe observar en que posición se encuentra este color:");
		instruccion.setFont(fontTexto);
		
		Random rand = new Random();
		colorCorrecto = rand.nextInt(6)+1;
		Label colorEscoger = new Label();
		Image imgEscoger = new Image("Color"+colorCorrecto+".png");
		ImageView viewEscoger = new ImageView(imgEscoger);
		viewEscoger.setFitHeight(160);
		viewEscoger.setPreserveRatio(true);
		colorEscoger.setGraphic(viewEscoger);
		
		VBox layout1 = new VBox();
		layout1.setSpacing(8);
		layout1.setPadding(new Insets(80,10,10,10));
		layout1.setAlignment(Pos.BASELINE_CENTER);
		layout1.getChildren().addAll(lblTitulo,instruccion,colorEscoger);
		BackgroundFill backgroundFill =
		        new BackgroundFill(
		                Color.valueOf("#b6d8f2"),
		                new CornerRadii(10),
		                new Insets(10)
		                );

		Background background =
		        new Background(backgroundFill);
		layout1.setBackground(background);
		
		Scene nextScene = new Scene(layout1,Utilities.windowWidth,Utilities.windowHeight);
		nextScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		primaryStage.setScene(nextScene);
		
	}
	
	private void setupScene2(Stage primaryStage, ClienteScreen clienteScene) {
		Label lblTitulo1 = new Label ("Colores intermitentes");
		Font fontTitulo = Font.font("Courier New",FontWeight.BOLD,22);
		lblTitulo1.setFont(fontTitulo);
		GridPane.setConstraints(lblTitulo1, 1, 0);
		GridPane.setHalignment(lblTitulo1,HPos.CENTER);
		
		Random rand = new Random();
		int num1 = rand.nextInt(6)+1;
		if (num1 == colorCorrecto) {
			botonCorrecto = 1;
		}
		Label color1 = new Label();
		Image img1 = new Image("Color"+num1+".png");
		ImageView view1 = new ImageView(img1);
		view1.setFitHeight(140);
		view1.setPreserveRatio(true);
		color1.setGraphic(view1);
		GridPane.setConstraints(color1, 0, 1);
		
		int num2= rand.nextInt(6)+1;
		while(num2==num1) {
			num2= rand.nextInt(6)+1;
		}
		if (num2 == colorCorrecto) {
			botonCorrecto = 2;
		}
		Label color2 = new Label();
		Image img2 = new Image("Color"+num2+".png");
		ImageView view2 = new ImageView(img2);
		view2.setFitHeight(140);
		view2.setPreserveRatio(true);
		color2.setGraphic(view2);
		GridPane.setConstraints(color2, 1, 1);
		
		int num3= rand.nextInt(6)+1;
		while(num3==num2 || num3==num1) {
			num3= rand.nextInt(6)+1;
		}
		if (num3 == colorCorrecto) {
			botonCorrecto = 3;
		}
		Label color3 = new Label();
		Image img3 = new Image("Color"+num3+".png");
		ImageView view3 = new ImageView(img3);
		view3.setFitHeight(140);
		view3.setPreserveRatio(true);
		color3.setGraphic(view3);
		GridPane.setConstraints(color3, 2, 1);
		
		int num4= rand.nextInt(6)+1;
		while(num4==num3 || num4==num2 || num4==num1) {
			num4= rand.nextInt(6)+1;
		}
		if (num4 == colorCorrecto) {
			botonCorrecto = 4;
		}
		Label color4 = new Label();
		Image img4 = new Image("Color"+num4+".png");
		ImageView view4 = new ImageView(img4);
		view4.setFitHeight(140);
		view4.setPreserveRatio(true);
		color4.setGraphic(view4);
		GridPane.setConstraints(color4, 0, 2);
		
		int num5= rand.nextInt(6)+1;
		while(num5==num4 || num5==num3 || num5==num2 || num5==num1) {
			num5= rand.nextInt(6)+1;
		}
		if (num5 == colorCorrecto) {
			botonCorrecto = 5;
		}
		Label color5 = new Label();
		Image img5 = new Image("Color"+num5+".png");
		ImageView view5 = new ImageView(img5);
		view5.setFitHeight(140);
		view5.setPreserveRatio(true);
		color5.setGraphic(view5);
		GridPane.setConstraints(color5, 1, 2);
		
		int num6= rand.nextInt(6)+1;
		while(num6== num5 || num6== num4 || num6==num3 || num6==num2 || num6==num1) {
			num6= rand.nextInt(6)+1;
		}
		if (num6 == colorCorrecto) {
			botonCorrecto = 6;
		}
		Label color6 = new Label();
		Image img6 = new Image("Color"+num6+".png");
		ImageView view6 = new ImageView(img6);
		view6.setFitHeight(140);
		view6.setPreserveRatio(true);
		color6.setGraphic(view6);
		GridPane.setConstraints(color6, 2, 2);
				
		GridPane layout2 = new GridPane();
		layout2.setVgap(50);
		layout2.setHgap(20);
		layout2.setPadding(new Insets(80,10,10,10));
		layout2.setAlignment(Pos.BASELINE_CENTER);
		layout2.getChildren().addAll(lblTitulo1,color1,color2,color3,color4,color5,color6);
		BackgroundFill backgroundFill =
		        new BackgroundFill(
		                Color.valueOf("#b6d8f2"),
		                new CornerRadii(10),
		                new Insets(10)
		                );

		Background background =
		        new Background(backgroundFill);
		layout2.setBackground(background);
		
		Scene coloresScene2 = new Scene(layout2,Utilities.windowWidth,Utilities.windowHeight);
		coloresScene2.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		primaryStage.setScene(coloresScene2);
		
		PauseTransition delay = new PauseTransition(Duration.seconds(1));
		delay.setOnFinished( event -> setupScene3(primaryStage,clienteScene));
		delay.play();	
	}
	
	private void setupScene3(Stage primaryStage, ClienteScreen clienteScene) {
		Label lblTitulo1 = new Label ("Colores intermitentes");
		Font fontTitulo = Font.font("Courier New",FontWeight.BOLD,22);
		lblTitulo1.setFont(fontTitulo);
		GridPane.setConstraints(lblTitulo1, 1, 0);
		GridPane.setHalignment(lblTitulo1,HPos.CENTER);
		
		int btnHeight = 160;
		int btnWidth = 240;
		
		Button boton1 = new Button();
		boton1.setMinHeight(btnHeight);
		boton1.setMinWidth(btnWidth);
		boton1.setOnAction(e-> evaluarRespuesta(1,primaryStage,clienteScene));
		GridPane.setConstraints(boton1,0,1);
		
		Button boton2 = new Button();
		boton2.setMinHeight(btnHeight);
		boton2.setMinWidth(btnWidth);
		boton2.setOnAction(e-> evaluarRespuesta(2,primaryStage,clienteScene));
		GridPane.setConstraints(boton2,1,1);

		Button boton3 = new Button();
		boton3.setMinHeight(btnHeight);
		boton3.setMinWidth(btnWidth);
		boton3.setOnAction(e-> evaluarRespuesta(3,primaryStage,clienteScene));
		GridPane.setConstraints(boton3,2,1);
		
		Button boton4 = new Button();
		boton4.setMinHeight(btnHeight);
		boton4.setMinWidth(btnWidth);
		boton4.setOnAction(e-> evaluarRespuesta(4,primaryStage,clienteScene));
		GridPane.setConstraints(boton4,0,2);
		
		Button boton5 = new Button();
		boton5.setMinHeight(btnHeight);
		boton5.setMinWidth(btnWidth);
		boton5.setOnAction(e-> evaluarRespuesta(5,primaryStage,clienteScene));
		GridPane.setConstraints(boton5,1,2);
		
		Button boton6 = new Button();
		boton6.setMinHeight(btnHeight);
		boton6.setMinWidth(btnWidth);
		boton6.setOnAction(e-> evaluarRespuesta(6,primaryStage,clienteScene));
		GridPane.setConstraints(boton6,2,2);
		
		GridPane.setConstraints(botonVolver, 1, 3);
		
		GridPane layout3 = new GridPane();
		layout3.setVgap(50);
		layout3.setHgap(20);
		layout3.setPadding(new Insets(80,10,10,10));
		layout3.setAlignment(Pos.BASELINE_CENTER);
		layout3.getChildren().addAll(lblTitulo1,boton1,boton2,boton3,boton4,boton5,boton6,botonVolver);
		BackgroundFill backgroundFill =
		        new BackgroundFill(
		                Color.valueOf("#b6d8f2"),
		                new CornerRadii(10),
		                new Insets(10)
		                );

		Background background =
		        new Background(backgroundFill);
		layout3.setBackground(background);
		
		Scene nextScene = new Scene(layout3,Utilities.windowWidth,Utilities.windowHeight);
		nextScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(nextScene);
		
	}
	
	public void finishScene() {
		layout.getChildren().addAll(lblTitulo,lblInstrucciones1,lblInstrucciones2,lblInstrucciones3,
				botonIniciar,botonVolver);
		BackgroundFill backgroundFill =
		        new BackgroundFill(
		                Color.valueOf("#b6d8f2"),
		                new CornerRadii(10),
		                new Insets(10)
		                );

		Background background =
		        new Background(backgroundFill);
		layout.setBackground(background);
		layout.setPadding(new Insets(150,0,0,0));
		
		//Set Scene
		coloresScene = new Scene(layout,Utilities.windowWidth,Utilities.windowHeight);
		coloresScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	}
	
	public void comenzarTest(Stage primaryStage, ClienteScreen clienteScene) {
		setupScene(primaryStage);
		PauseTransition delay = new PauseTransition(Duration.seconds(6));
		delay.setOnFinished( event -> setupScene2(primaryStage,clienteScene));
		delay.play();
	}
	
	private void evaluarRespuesta(int numBoton, Stage primaryStage, ClienteScreen clienteScene) {
		int testMax = 5;
		contador++;
		if (botonCorrecto==numBoton)
			puntuacion++;
		if (contador >= testMax) {
			AlertBox.display("Resultado", "Su puntaje es " + puntuacion + " de " + testMax);
			try {
				registrarResultadoBD(0);
			} catch (IOException e) {
				AlertBox.display("Error.", "La conexión al servidor se interrumpió.");
				e.printStackTrace();
			} catch (InterruptedException e) {
				AlertBox.display("Error.", "La conexión al servidor se interrumpió.");
				e.printStackTrace();
			}
			primaryStage.setScene(clienteScene.getScene());
		}
		else
			comenzarTest(primaryStage,clienteScene);
	}
	
	public void registrarResultadoBD(int errCount) throws IOException, InterruptedException {
		if(modoNC)
			return;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(URI.create
				(Utilities.getBaseURL() + "/Resultado/resregistrarcolores.php?iduser="
		+userID + "&puntos="+ puntuacion+ "&fecharesultado=" + formatter.format(date))).GET().build();
		
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		System.out.println(response.body());
		if (!response.body().contains("Exito#")) {
			if (errCount < 5 ) {
					registrarResultadoBD(errCount+1);
			}
			else {
				AlertBoxNonWait.display("Error.", "No se pudo registrar su resultado.");
			}
		}
	}
	
	public Button getBotonVolver() {
		return botonVolver;
	}
	
	public Button getBotonIniciar() {
		return botonIniciar;
	}
	
	public Scene getScene() {
		return coloresScene;
	}
}
