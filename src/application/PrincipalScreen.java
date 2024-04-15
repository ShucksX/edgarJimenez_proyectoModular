package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.BackgroundImage;

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
		
		Label lblTitulo = new Label ("Aplicación para la estimulación cognitiva para Alzheimer");
		lblTitulo.setFont(fontTitulo);
		Label lblCotacion = new Label ("(Este software se utiliza para Alzheimer leve a moderado)");
		lblCotacion.setFont(fontTexto);
		
		
		
		Label logo = new Label();
		Image img = new Image("LogoTemp.png");
		ImageView view = new ImageView(img);
		view.setFitHeight(400);
		view.setPreserveRatio(true);
		logo.setGraphic(view);
		
		botonLogin = new Button();
		botonLogin.setText("Ingresar");
		botonLogin.setMinWidth(200);
		botonLogin.setFont(fontTexto);
		botonLogin.setDefaultButton(true);
		
		botonRegistro = new Button();
		botonRegistro.setText("Regístrate aquí");
		botonRegistro.setMinWidth(200);
		botonRegistro.setFont(fontTexto);

		layout.getChildren().addAll(lblTitulo,lblCotacion,logo,botonLogin,botonRegistro);
		layout.setPadding(new Insets(80,0,0,0));
		BackgroundImage myBI= new BackgroundImage(new Image("background01.png",1800,800,false,true),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);

		Background background =
		        new Background(myBI);
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
