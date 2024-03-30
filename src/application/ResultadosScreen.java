package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ResultadosScreen {
	private Scene resultadoScene;
	private Button botonSilueta,botonLaberinto,botonSopa,botonColores,botonChat,botonVolver;
	private String userID;
	private Stage primaryStage;
	public ResultadosScreen(String userid, Stage primaryStage) {
		userID = userid;
		this.primaryStage = primaryStage;
		VBox layout = new VBox();
		layout.setSpacing(8);
		layout.setPadding(new Insets(10,10,10,10));
		layout.setAlignment(Pos.BASELINE_CENTER);
		//Add components
		Font fontTitulo = Font.font("Courier New",FontWeight.BOLD,22);
		Font fontTexto = Font.font("Courier New",FontWeight.NORMAL,16);
		
		Label lblTitulo = new Label ("Ver resultados");
		lblTitulo.setFont(fontTitulo);
		
		botonSilueta = new Button();
		botonSilueta.setText("Siluetas");
		botonSilueta.setMinWidth(320);
		botonSilueta.setFont(fontTexto);
		botonSilueta.setOnAction(e-> changeSceneSilueta());
		
		botonLaberinto = new Button();
		botonLaberinto.setText("Laberinto");
		botonLaberinto.setMinWidth(320);
		botonLaberinto.setFont(fontTexto);
		botonLaberinto.setOnAction(e-> changeSceneLaberinto());
		
		botonSopa = new Button();
		botonSopa.setText("Sopa de letras");
		botonSopa.setMinWidth(320);
		botonSopa.setFont(fontTexto);
		botonSopa.setOnAction(e-> changeSceneSopa());
		
		botonColores = new Button();
		botonColores.setText("Colores intermitentes");
		botonColores.setMinWidth(320);
		botonColores.setFont(fontTexto);
		botonColores.setOnAction(e-> changeSceneColores());
		
		botonChat = new Button();
		botonChat.setText("Utiliza el chatbot si tienes dudas");
		botonChat.setMinWidth(320);
		botonChat.setFont(fontTexto);
		botonChat.setOnAction(e-> changeSceneChat());
		
		botonVolver = new Button();
		botonVolver.setText("Volver a selección de ejercicios");
		botonVolver.setMinWidth(320);
		botonVolver.setFont(fontTexto);
		
		layout.getChildren().addAll(lblTitulo,botonSilueta,botonLaberinto,botonSopa,botonColores,botonChat,botonVolver);
		BackgroundFill backgroundFill =
		        new BackgroundFill(
		                Color.valueOf("#deaff0"),
		                new CornerRadii(10),
		                new Insets(10)
		                );

		Background background =
		        new Background(backgroundFill);
		layout.setBackground(background);
		layout.setPadding(new Insets(20,10,10,10));
		
		//Set Scene
		resultadoScene = new Scene(layout,Utilities.windowWidth,Utilities.windowHeight);
		resultadoScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	}
	
	public void changeSceneSilueta() {
		ResSiluetas sceneResSilueta = new ResSiluetas(getScene(),primaryStage,false,userID);
		primaryStage.setScene(sceneResSilueta.getScene());
	}
	
	public void changeSceneLaberinto() {
		ResLaberinto sceneResLaberinto = new ResLaberinto(getScene(),primaryStage,false,userID);
		primaryStage.setScene(sceneResLaberinto.getScene());
	}
	
	public void changeSceneSopa() {
		ResSopa sceneResSopa = new ResSopa(getScene(),primaryStage,false,userID);
		primaryStage.setScene(sceneResSopa.getScene());
	}
	
	public void changeSceneColores() {
		ResColores sceneResColores = new ResColores(getScene(),primaryStage,false,userID);
		primaryStage.setScene(sceneResColores.getScene());
	}
	
	public void changeSceneChat() {
		Stage window = new Stage();
		
		window.setTitle("Chatbot para dudas");
		window.setMinWidth(350);
		window.setResizable(true);
		
		ChatBotScreen sceneChatBot = new ChatBotScreen();
		
		window.setScene(sceneChatBot.getScene());
		window.show();
	}
	
	public Button getBotonSilueta() {
		return botonSilueta;
	}
	
	public Button getBotonLaberinto() {
		return botonLaberinto;
	}
	
	public Button getBotonSopa() {
		return botonSopa;
	}
	
	public Button getBotonColores() {
		return botonColores;
	}
	
	public Button getBotonChat() {
		return botonChat;
	}
	
	public Button getBotonVolver() {
		return botonVolver;
	}
	
	public Scene getScene() {
		return resultadoScene;
	}
}

