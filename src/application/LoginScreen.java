package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class LoginScreen  {
	private Scene loginScene;
	private TextField usuario;
	private PasswordField contrasena;
	private Button botonLogin;
	private Button botonRegistro;
	private Button botonRegresar;
	public LoginScreen() {
		VBox layout = new VBox();
		layout.setSpacing(8);
		layout.setPadding(new Insets(10,10,10,10));
		layout.setAlignment(Pos.BASELINE_CENTER);
		//Add components
		Font fontLogin = Font.font("Courier New",FontWeight.BOLD,22);
		Font fontTexto = Font.font("Courier New",FontWeight.NORMAL,16);
		
		Label lblLogin = new Label ("Login");
		lblLogin.setFont(fontLogin);

		
		usuario = new TextField();
		usuario.setPromptText("Correo");
		usuario.setFont(fontTexto);
		usuario.setMaxWidth(350);
		
		contrasena = new PasswordField();
		contrasena.setPromptText("Contraseña");
		contrasena.setFont(fontTexto);
		contrasena.setMaxWidth(350);
		
		int minwidth = 320;
		botonLogin = new Button();
		botonLogin.setText("Ingresar");
		botonLogin.setMinWidth(minwidth);
		botonLogin.setFont(fontTexto);
		botonLogin.setDefaultButton(true);
		
		botonRegistro = new Button();
		botonRegistro.setText("Registrate aqui");
		botonRegistro.setMinWidth(minwidth);
		botonRegistro.setFont(fontTexto);
		
		botonRegresar = new Button();
		botonRegresar.setText("Regresar a pantalla principal");
		botonRegresar.setMinWidth(minwidth);
		botonRegresar.setFont(fontTexto);
		
		layout.getChildren().addAll(lblLogin,usuario,contrasena,botonLogin,botonRegistro, botonRegresar);
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
		loginScene = new Scene(layout,Utilities.windowWidth,Utilities.windowHeight);
		loginScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	}
	
	public Button getBotonLogin() {
		return botonLogin;
	}
	
	public Button getBotonRegistro() {
		return botonRegistro;
	}
	
	public Button getBotonRegresar() {
		return botonRegresar;
	}
	public TextField getUsuario() {
		return usuario;
	}
	public PasswordField getContrasena(){
		return contrasena;
	}
	
	public Scene getScene() {
		return loginScene;
	}
	
	public void cleanFields() {
		usuario.clear();
		contrasena.clear();
	}
}
