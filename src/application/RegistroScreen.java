package application;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class RegistroScreen {
	private Scene registroScene;
	private TextField nombre;
	private PasswordField contrasena, contrasena2;
	private TextField correo;
	private DatePicker fechanac;
	private TextField pais;
	private TextField municipio;
	private TextField localidad;
	private Button botonRegistro;
	private Button botonLogin;
	private Button botonRegresar;
	public RegistroScreen() {
		VBox layout = new VBox();
		layout.setSpacing(8);
		layout.setPadding(new Insets(10,10,10,10));
		layout.setAlignment(Pos.BASELINE_CENTER);
		//Add components
		Font fontTexto = Font.font("Courier New",FontWeight.NORMAL,16);
		Font fontRegistro = Font.font("Courier New",FontWeight.BOLD,22);
		
		Label lblRegistro = new Label ("Registro");
		lblRegistro.setFont(fontRegistro);
		
		nombre = new TextField();
		nombre.setPromptText("Nombre");
		nombre.setFont(fontTexto);
		nombre.setMaxWidth(350);
		
		contrasena = new PasswordField();
		contrasena.setPromptText("Contraseña");
		contrasena.setFont(fontTexto);
		contrasena.setMaxWidth(350);
		
		contrasena2 = new PasswordField();
		contrasena2.setPromptText("Repita su contraseña");
		contrasena2.setFont(fontTexto);
		contrasena2.setMaxWidth(350);
		
		correo = new TextField();
		correo.setPromptText("Correo");
		correo.setFont(fontTexto);
		correo.setMaxWidth(350);
		
		fechanac = new DatePicker();
		fechanac.setPromptText("Fecha de nacimiento");
		fechanac.setEditable(false);
		fechanac.setMaxWidth(350);
		fechanac.setMinWidth(320);

		pais = new TextField();
		pais.setPromptText("Pais");
		pais.setFont(fontTexto);
		pais.setMaxWidth(350);
		
		municipio = new TextField();
		municipio.setPromptText("Municipio");
		municipio.setFont(fontTexto);
		municipio.setMaxWidth(350);
		
		localidad = new TextField();
		localidad.setPromptText("Localidad");
		localidad.setFont(fontTexto);
		localidad.setMaxWidth(350);
		
		int minwidth = 320;
		botonRegistro = new Button();
		botonRegistro.setText("Registrarse");
		botonRegistro.setMinWidth(minwidth);
		botonRegistro.setOnAction(e-> {
			try {
				if (contrasena.getText().equals(contrasena2.getText()))
					registrar();
				else {
					AlertBox.display("Error.", "Las contraseñas en ambos campos no son las mismas\nAsegúrese de escribir la misma contraseña en ambos campos.");
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		});
		botonRegistro.setFont(fontTexto);
		botonRegistro.setDefaultButton(true);
		
		botonLogin = new Button();
		botonLogin.setText("¿Ya tienes una cuenta? Cliquea aquí");
		botonLogin.setMinWidth(minwidth);
		botonLogin.setFont(fontTexto);

		
		
		botonRegresar = new Button();
		botonRegresar.setText("Regresar a pantalla principal");
		botonRegresar.setMinWidth(minwidth);
		botonRegresar.setFont(fontTexto);
		
		layout.getChildren().addAll(lblRegistro,nombre,contrasena,contrasena2, correo, fechanac, pais, municipio,
				localidad,botonRegistro,botonLogin,botonRegresar);
		BackgroundImage myBI= new BackgroundImage(new Image("background01.png",1800,800,false,true),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);

		Background background =
		        new Background(myBI);
		layout.setBackground(background);
		
		//Set Scene
		registroScene = new Scene(layout,Utilities.windowWidth,Utilities.windowHeight);
		registroScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	}
	
	private void registrar() throws IOException, InterruptedException {
		String txtNombre = Utilities.stringToUTF(nombre.getText());
		String txtContrasena = contrasena.getText();
		if (txtContrasena.indexOf(" ")!=-1) {
			AlertBox.display("Error.", "La contraseña no puede contener espacios.");
			return;
		}
		String txtCorreo = correo.getText();
		if (txtCorreo.indexOf(" ")!=-1) {
			AlertBox.display("Error.", "El correo no puede contener espacios.");
			return;
		}
		String txtFechaNac;
		if (fechanac.getValue()!= null) {
			 txtFechaNac = fechanac.getValue().toString();
			 //System.out.println(txtFechaNac);
		}
		else {
			AlertBox.display("Error.", "Por favor asegúrese de llenar todos los campos.");
			return;
		}
		String txtPais = Utilities.stringToUTF(pais.getText());
		String txtMunicipio =  Utilities.stringToUTF(municipio.getText());
		String txtLocalidad =  Utilities.stringToUTF(localidad.getText());
		clearFields();
		
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(URI.create
				(Utilities.getBaseURL() + "/registrar.php?nombre="+txtNombre+"&correo="+
		txtCorreo+"&password="+txtContrasena+"&fechanac="+txtFechaNac+"&pais="+txtPais+
		"&municipio="+txtMunicipio+"&localidad="+txtLocalidad)).GET().build();
		
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		System.out.println(response.body());
		AlertBox.display("Alerta.", response.body());
	}
	

	
	public Button getBotonRegistro() {
		return botonRegistro;
	}
	
	public Button getBotonLogin() {
		return botonLogin;
	}
	
	public Button getBotonRegresar() {
		return botonRegresar;
	}
	
	public Scene getScene() {
		return registroScene;
	}
	
	private void clearFields() {
		nombre.clear();
		contrasena.clear();
		contrasena2.clear();
		correo.clear();
		fechanac.setValue(null);
		pais.clear();
		municipio.clear();
		localidad.clear();
	}
}
