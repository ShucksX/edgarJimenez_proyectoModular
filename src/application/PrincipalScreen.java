package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PrincipalScreen {
	private Scene principalScene;
	private Button botonLogin;
	private Button botonRegistro;
	public PrincipalScreen() {
		VBox layout = new VBox();
		layout.setSpacing(12);
		layout.setPadding(new Insets(10,10,10,10));
		layout.setAlignment(Pos.TOP_CENTER);
		//Add components
		Font fontTexto = Font.font("Courier New",FontWeight.NORMAL,16);
		Font fontTitulo = Font.font("Courier New",FontWeight.BOLD,22);
		
		Label lblTitulo = new Label ("Programa para pruebas del Alzheimer");
		
		lblTitulo.setFont(fontTitulo);
		
		Label logo = new Label();
		Image img = new Image("LogoTemp.png");
		ImageView view = new ImageView(img);
		view.setFitHeight(200);
		view.setPreserveRatio(true);
		logo.setGraphic(view);
		
		botonLogin = new Button();
		botonLogin.setText("Ingresar");
		botonLogin.setMinWidth(200);
		botonLogin.setFont(fontTexto);
		botonLogin.setDefaultButton(true);
		
		botonRegistro = new Button();
		botonRegistro.setText("Registrate aqui");
		botonRegistro.setMinWidth(200);
		botonRegistro.setFont(fontTexto);

		layout.getChildren().addAll(lblTitulo,logo,botonLogin,botonRegistro);
		BackgroundFill backgroundFill =
		        new BackgroundFill(
		                Color.valueOf("#a3de95"),
		                new CornerRadii(10),
		                new Insets(10)
		                );

		Background background =
		        new Background(backgroundFill);
		layout.setBackground(background);
		
		//Set Scene
		principalScene = new Scene(layout,Utilities.windowWidth,Utilities.windowHeight);
		principalScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	}
	
	public Button getBotonLogin() {
		return botonLogin;
	}
	
	public Button getBotonRegistro() {
		return botonRegistro;
	}
	
	public Scene getScene() {
		return principalScene;
	}
}
