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
import javafx.stage.Stage;

public class VerResultadosScreen {
	private Scene verResultadoScene;
	private Button botonPalabra,botonLaberinto,botonSopa,botonColores,botonVolver;
	private Stage primaryStage;
	private String userId;
	public VerResultadosScreen(String userId,Stage primaryStage) {
		this.userId = userId;
		this.primaryStage = primaryStage;
		VBox layout = new VBox();
		layout.setSpacing(8);
		layout.setPadding(new Insets(10,10,10,10));
		layout.setAlignment(Pos.BASELINE_CENTER);
		//Add components
		Font fontTitulo = Font.font("Courier New",FontWeight.BOLD,22);
		Font fontTexto = Font.font("Courier New",FontWeight.NORMAL,16);
		
		Label lblTitulo = new Label ("Ver resultados de usuarios");
		lblTitulo.setFont(fontTitulo);
		
		botonPalabra = new Button();
		botonPalabra.setText("Memorizacion de palabras");
		botonPalabra.setMinWidth(320);
		botonPalabra.setFont(fontTexto);
		botonPalabra.setOnAction(e-> changeScenePalabra());
		
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
		
		botonVolver = new Button();
		botonVolver.setText("Volver a menu de administrador");
		botonVolver.setMinWidth(320);
		botonVolver.setFont(fontTexto);
		
		layout.getChildren().addAll(lblTitulo,botonPalabra,botonLaberinto,botonSopa,botonColores,botonVolver);
		BackgroundFill backgroundFill =
		        new BackgroundFill(
		                Color.valueOf("#deaff0"),
		                new CornerRadii(10),
		                new Insets(10)
		                );

		Background background =
		        new Background(backgroundFill);
		layout.setBackground(background);
		
		//Set Scene
		verResultadoScene = new Scene(layout,Utilities.windowWidth,Utilities.windowHeight);
		verResultadoScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	}
	
	public void changeScenePalabra() {
		ResPalabras sceneResPalabra = new ResPalabras(getScene(),primaryStage,true,userId);
		primaryStage.setScene(sceneResPalabra.getScene());
	}
	
	public void changeSceneLaberinto() {
		ResLaberinto sceneResLaberinto = new ResLaberinto(getScene(),primaryStage,true,userId);
		primaryStage.setScene(sceneResLaberinto.getScene());
	}
	
	public void changeSceneSopa() {
		ResSopa sceneResSopa = new ResSopa(getScene(),primaryStage,true,userId);
		primaryStage.setScene(sceneResSopa.getScene());
	}
	
	public void changeSceneColores() {
		ResColores sceneResColores = new ResColores(getScene(),primaryStage,true,userId);
		primaryStage.setScene(sceneResColores.getScene());
	}
	
	public Button getBotonPalabra() {
		return botonPalabra;
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
	
	public Button getBotonVolver() {
		return botonVolver;
	}
	
	public Scene getScene() {
		return verResultadoScene;
	}
}
