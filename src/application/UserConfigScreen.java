package application;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.LocalDate;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UserConfigScreen {
	
	static boolean answer;
	static private String style = "-fx-border-color: linear-gradient(#f77777, #e67777);-fx-border-radius: 30; -fx-border-width: 2px;-fx-background-color: linear-gradient(#bddbb6, #b2c7ad);-fx-background-radius: 30;";
	
	public static boolean startConfig(String userId) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(URI.create
				(Utilities.getBaseURL() + "/obtenerusuarioporid.php?id=" + userId)).GET().build();
		
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		System.out.println(response.body());
		String respuesta = response.body();
		if (respuesta.contains("noe#")) {
			AlertBox.display("Error", "No se puedo encontrar su cuenta, intente de nuevo mas tarde");
			return false;
		}
		int index1 = 0;
		int index2 = respuesta.indexOf("|");
		String nombre,fechanac,correo,pais,municipio,localidad;
		Usuario user;
		nombre = respuesta.substring(index1,index2);
		index1 = index2+1;
		index2 = respuesta.indexOf("|",index1);
		fechanac = respuesta.substring(index1,index2);
		index1 = index2+1;
		index2 = respuesta.indexOf("|",index1);
		correo = respuesta.substring(index1,index2);
		index1 = index2+1;
		index2 = respuesta.indexOf("|",index1);
		pais = respuesta.substring(index1,index2);
		index1 = index2+1;
		index2 = respuesta.indexOf("|",index1);
		municipio = respuesta.substring(index1,index2);
		index1 = index2+1;
		index2 = respuesta.indexOf("%",index1);
		localidad = respuesta.substring(index1,index2);
		index1 = index2+1;
		index2 = respuesta.indexOf("|",index1);
		user = new Usuario(userId,nombre,fechanac,correo,pais,municipio,localidad,"1");
		display(user);
		return answer;
	}
	
	private static boolean display (Usuario user) {
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Configuración de usuario");
		window.setMinWidth(350);
		window.setResizable(false);
		
		Font fontTitulo = Font.font("Courier New",FontWeight.BOLD,22);
		Font fontSubtitulo = Font.font("Courier New",FontWeight.BOLD,17);
		Font fontTexto = Font.font("Arial",FontWeight.NORMAL,16);
		Label label = new Label();
		label.setText("Configuración de usuario");
		label.setFont(fontTitulo);
		
		Label nombreSub = new Label();
		nombreSub.setText("Nombre:");
		nombreSub.setFont(fontSubtitulo);
		Label nombre = new Label();
		nombre.setText(user.getNombre());
		nombre.setFont(fontTexto);
		
		Label correoSub = new Label();
		correoSub.setText("Correo:");
		correoSub.setFont(fontSubtitulo);
		Label correo = new Label();
		correo.setText(user.getCorreo());
		correo.setFont(fontTexto);
		
		Label fechaNacSub = new Label();
		fechaNacSub.setText("Fecha de nacimiento:");
		fechaNacSub.setFont(fontSubtitulo);
		Label fechaNac = new Label();
		fechaNac.setText(user.getFechaNac());
		fechaNac.setFont(fontTexto);
		
		Label ubicacion = new Label();
		ubicacion.setText("Información de ubicación:");
		ubicacion.setFont(fontSubtitulo);
		
		Label pais = new Label();
		pais.setText("Pais: " + user.getPais());
		pais.setFont(fontTexto);
		
		Label municipio = new Label();
		municipio.setText("Municipio: " + user.getMunicipio());
		municipio.setFont(fontTexto);
		
		Label localidad = new Label();
		localidad.setText("Localidad: " + user.getLocalidad());
		localidad.setFont(fontTexto);
		
		
		Button nombreButton = new Button("Cambiar nombre");
		nombreButton.setFont(fontTexto);
		nombreButton.setStyle(style);
		Button contrasenaButton = new Button("Cambiar contraseña");
		contrasenaButton.setFont(fontTexto);
		contrasenaButton.setStyle(style);
		Button correoButton = new Button("Cambiar correo");
		correoButton.setFont(fontTexto);
		correoButton.setStyle(style);
		Button fechaNacButton = new Button("Cambiar fecha de nacimiento");
		fechaNacButton.setFont(fontTexto);
		fechaNacButton.setStyle(style);
		Button ubicacionButton = new Button("Cambiar datos de ubicación");
		ubicacionButton.setFont(fontTexto);
		ubicacionButton.setStyle(style);
		Button cancelButton = new Button("Cerrar ventana");
		cancelButton.setFont(fontTexto);
		cancelButton.setStyle(style);
		
		nombreButton.setOnAction(e->{
			changeNombre(user);
			nombre.setText(user.getNombre());
			window.setWidth(window.getWidth()+0.01);
			});
		contrasenaButton.setOnAction(e->changeContrasena(user));
		correoButton.setOnAction(e->{
			changeCorreo(user);
			});
		fechaNacButton.setOnAction(e->{
			changeFechaNac(user);
			fechaNac.setText(user.getFechaNac());
			window.setWidth(window.getWidth()+0.01);
			});
		ubicacionButton.setOnAction(e->{
			changeUbicacion(user);
			});
		cancelButton.setOnAction(e->{
			answer = false;
			window.close();
		});
		
		
		VBox layout = new VBox();
		layout.setSpacing(10);
		layout.setPadding(new Insets(15,15,15,15));
		layout.setAlignment(Pos.BASELINE_LEFT);
		layout.getChildren().addAll(label,nombreSub,nombre,nombreButton,contrasenaButton,
				correoSub,correo,correoButton,fechaNacSub,fechaNac,fechaNacButton,
				ubicacion,pais,municipio,localidad,ubicacionButton,cancelButton);
		BackgroundFill backgroundFill =
		        new BackgroundFill(
		                Color.valueOf("#b6d8f2"),
		                new CornerRadii(10),
		                new Insets(10)
		                );

		Background background =
		        new Background(backgroundFill);
		layout.setBackground(background);
		
		Scene scene = new Scene(layout,400,650);

		window.setScene(scene);
		window.showAndWait();
		
		return answer;
	}
	
	
	private static void changeNombre(Usuario user) {
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Cambiar nombre");
		window.setMinWidth(350);
		window.setResizable(false);
		
		Font fontTitulo = Font.font("Courier New",FontWeight.BOLD,22);
		Font fontTexto = Font.font("Courier New",FontWeight.NORMAL,16);
		Label label = new Label();
		label.setText("Cambiar nombre");
		label.setFont(fontTitulo);
		
		Label nuevoNombre = new Label();
		nuevoNombre.setText("Escriba el nuevo nombre: ");
		nuevoNombre.setFont(fontTexto);
		TextField fieldNombre = new TextField();
		fieldNombre.setPromptText("Nombre");
		fieldNombre.setFont(fontTexto);
		
		Label contrasena = new Label();
		contrasena.setText("Escriba su contraseña: ");
		contrasena.setFont(fontTexto);
		PasswordField fieldContrasena = new PasswordField();
		fieldContrasena.setPromptText("Contraseña");
		fieldContrasena.setFont(fontTexto);
		
		Button aceptButton = new Button("Cambiar nombre");
		aceptButton.setFont(fontTexto);
		aceptButton.setStyle(style);
		Button cancelButton = new Button("Cerrar ventana");
		cancelButton.setFont(fontTexto);
		cancelButton.setStyle(style);

		
		aceptButton.setOnAction(e->{
			
			try {
				if (registrarNombreBD(user,fieldNombre.getText(),fieldContrasena.getText())) {
					window.close();
				}
			} catch (IOException e1) {
				AlertBox.display("Error", "Error al conectar con el servidor, intente de nuevo más tarde");
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				AlertBox.display("Error", "Se interrumpió la conexión, intente de nuevo más tarde");
				e1.printStackTrace();
			}
		});
		cancelButton.setOnAction(e->window.close());
		
		VBox layout = new VBox();
		layout.setSpacing(10);
		layout.setPadding(new Insets(10,15,10,15));
		layout.setAlignment(Pos.BASELINE_CENTER);
		layout.getChildren().addAll(label,nuevoNombre,fieldNombre,contrasena,fieldContrasena,aceptButton,cancelButton);
		BackgroundFill backgroundFill =
		        new BackgroundFill(
		                Color.valueOf("#b6d8f2"),
		                new CornerRadii(10),
		                new Insets(10)
		                );

		Background background =
		        new Background(backgroundFill);
		layout.setBackground(background);
		
		Scene scene = new Scene(layout,400,300);
		window.setScene(scene);
		window.showAndWait();
		
		return;
	}
	
	private static boolean registrarNombreBD(Usuario user, String newNombre, String contrasena) throws IOException, InterruptedException {
		if (newNombre.equals("") || contrasena.equals("")) {
			AlertBox.display("Error", "Por favor asegúrese de llenar todos los campos");
			return false;
		}
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(URI.create(
				Utilities.getBaseURL() + "/Config/modificarnombre.php?id="+user.getID()+
				"&nombre=" + Utilities.stringToUTF(newNombre) + "&contrasena=" + Utilities.stringToUTF(contrasena))).GET().build();
		HttpResponse<String> response;
		response = client.send(request, BodyHandlers.ofString());
		System.out.println(response.body());
		if (response.body().contains("Exito#")) {
			AlertBox.display("Exito", "Se cambió el nombre con éxito");
			return true;
		}
		else if (response.body().contains("noe#")) {
			AlertBox.display("Error", "Hubo un error, verifique que su contraseña esta correcta\nO intente de nuevo más tarde");
			return false;
		}
		else {
			AlertBox.display("Error", "Hubo un error al modificar sus datos, intente de nuevo más tarde");
			return false;
		}
	}
	
	private static void changeContrasena(Usuario user) {
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Cambiar contraseña");
		window.setMinWidth(350);
		window.setResizable(false);
		
		Font fontTitulo = Font.font("Courier New",FontWeight.BOLD,22);
		Font fontTexto = Font.font("Courier New",FontWeight.NORMAL,16);
		Label label = new Label();
		label.setText("Cambiar contraseña");
		label.setFont(fontTitulo);
		
		Label contrasena = new Label();
		contrasena.setText("Escriba su contraseña: ");
		contrasena.setFont(fontTexto);
		PasswordField fieldContrasena = new PasswordField();
		fieldContrasena.setPromptText("Contraseña");
		fieldContrasena.setFont(fontTexto);
		
		Label newContrasena = new Label();
		newContrasena.setText("Escriba su nueva contraseña: ");
		newContrasena.setFont(fontTexto);
		PasswordField fieldNewContrasena = new PasswordField();
		fieldNewContrasena.setPromptText("Nueva contraseña");
		fieldNewContrasena.setFont(fontTexto);
		
		Label newContrasena2 = new Label();
		newContrasena2.setText("Escriba su nueva contraseña de nuevo: ");
		newContrasena2.setFont(fontTexto);
		PasswordField fieldNewContrasena2 = new PasswordField();
		fieldNewContrasena2.setPromptText("Nueva contraseña");
		fieldNewContrasena2.setFont(fontTexto);
		
		Button aceptButton = new Button("Cambiar contraseña");
		aceptButton.setFont(fontTexto);
		aceptButton.setStyle(style);
		Button cancelButton = new Button("Cerrar ventana");
		cancelButton.setFont(fontTexto);
		cancelButton.setStyle(style);
		
		aceptButton.setOnAction(e->{
			
			try {
				if(fieldNewContrasena.getText().equals(fieldNewContrasena2.getText())) {
					if (registrarContrasenaBD(user,fieldContrasena.getText(),fieldNewContrasena.getText())) {
						window.close();
					}
				}
				else {
					AlertBox.display("Error", "Su nueva contraseña no concuerda en ambos campos, verifíquelas por favor");
				}
			} catch (IOException e1) {
				AlertBox.display("Error", "Error al conectar con el servidor, intente de nuevo más tarde");
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				AlertBox.display("Error", "Se interrumpió la conexión, intente de nuevo más tarde");
				e1.printStackTrace();
			}
		});
		cancelButton.setOnAction(e->window.close());
		
		VBox layout = new VBox();
		layout.setSpacing(10);
		layout.setPadding(new Insets(10,15,10,15));
		layout.setAlignment(Pos.BASELINE_CENTER);
		layout.getChildren().addAll(label,contrasena,fieldContrasena,newContrasena,fieldNewContrasena,
				newContrasena2,fieldNewContrasena2,aceptButton,cancelButton);
		BackgroundFill backgroundFill =
		        new BackgroundFill(
		                Color.valueOf("#b6d8f2"),
		                new CornerRadii(10),
		                new Insets(10)
		                );

		Background background =
		        new Background(backgroundFill);
		layout.setBackground(background);
		
		
		Scene scene = new Scene(layout,400,400);
		window.setScene(scene);
		window.showAndWait();
	}
	
	private static boolean registrarContrasenaBD(Usuario user, String contrasena, String newContrasena) throws IOException, InterruptedException {
		if (newContrasena.equals("") || contrasena.equals("")) {
			AlertBox.display("Error", "Por favor asegúrese de llenar todos los campos");
			return false;
		}
		if (newContrasena.contains(" ") || contrasena.contains(" ")) {
			AlertBox.display("Error", "Su contraseña no puede contener espacios");
			return false;
		}
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(URI.create(
				Utilities.getBaseURL() + "/Config/modificarcontrasena.php?id="+user.getID()+
				"&contrasena=" + contrasena + "&newcontrasena=" + newContrasena)).GET().build();
		HttpResponse<String> response;
		response = client.send(request, BodyHandlers.ofString());
		System.out.println(response.body());
		if (response.body().contains("Exito#")) {
			AlertBox.display("Exito", "Se cambió la contraseña con éxito");
			return true;
		}
		else if (response.body().contains("noe#")) {
			AlertBox.display("Error", "Hubo un error, verifique que su contraseña esta correcta\nO intente de nuevo más tarde");
			return false;
		}
		else {
			AlertBox.display("Error", "Hubo un error al modificar sus datos, intente de nuevo más tarde");
			return false;
		}
	}
	
	private static void changeCorreo(Usuario user) {
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Cambiar correo");
		window.setMinWidth(350);
		window.setResizable(false);
		
		Font fontTitulo = Font.font("Courier New",FontWeight.BOLD,22);
		Font fontTexto = Font.font("Courier New",FontWeight.NORMAL,16);
		Label label = new Label();
		label.setText("Cambiar correo");
		label.setFont(fontTitulo);
		
		Label nuevoCorreo = new Label();
		nuevoCorreo.setText("Escriba el nuevo correo: ");
		nuevoCorreo.setFont(fontTexto);
		TextField fieldCorreo = new TextField();
		fieldCorreo.setPromptText("Nuevo correo");
		fieldCorreo.setFont(fontTexto);
		
		Label contrasena = new Label();
		contrasena.setText("Escriba su contraseña: ");
		contrasena.setFont(fontTexto);
		PasswordField fieldContrasena = new PasswordField();
		fieldContrasena.setPromptText("Contraseña");
		fieldContrasena.setFont(fontTexto);
		
		Button aceptButton = new Button("Cambiar correo");
		aceptButton.setFont(fontTexto);
		aceptButton.setStyle(style);
		Button cancelButton = new Button("Cerrar ventana");
		cancelButton.setFont(fontTexto);
		cancelButton.setStyle(style);
		
		aceptButton.setOnAction(e->{
			
			try {
				if (registrarCorreoBD(user,fieldCorreo.getText(),fieldContrasena.getText())) {
					window.close();
				}
			} catch (IOException e1) {
				AlertBox.display("Error", "Error al conectar con el servidor, intente de nuevo más tarde");
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				AlertBox.display("Error", "Se interrumpió la conexión, intente de nuevo más tarde");
				e1.printStackTrace();
			}
		});
		cancelButton.setOnAction(e->window.close());
		
		VBox layout = new VBox();
		layout.setSpacing(10);
		layout.setPadding(new Insets(10,15,10,15));
		layout.setAlignment(Pos.BASELINE_CENTER);
		layout.getChildren().addAll(label,nuevoCorreo,fieldCorreo,contrasena,fieldContrasena,aceptButton,cancelButton);
		BackgroundFill backgroundFill =
		        new BackgroundFill(
		                Color.valueOf("#b6d8f2"),
		                new CornerRadii(10),
		                new Insets(10)
		                );

		Background background =
		        new Background(backgroundFill);
		layout.setBackground(background);
		
		Scene scene = new Scene(layout,400,300);
		window.setScene(scene);
		window.showAndWait();
		
		return;
	}
	
	private static boolean registrarCorreoBD(Usuario user, String newCorreo, String contrasena) throws IOException, InterruptedException {
		if (newCorreo.equals("") || contrasena.equals("")) {
			AlertBox.display("Error", "Por favor asegúrese de llenar todos los campos");
			return false;
		}
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(URI.create(
				Utilities.getBaseURL() + "/Config/modificarcorreo.php?id="+user.getID()+
				"&correo=" + Utilities.stringToUTF(newCorreo) + "&contrasena=" + Utilities.stringToUTF(contrasena))).GET().build();
		HttpResponse<String> response;
		response = client.send(request, BodyHandlers.ofString());
		System.out.println(response.body());
		if (response.body().contains("Exito#")) {
			AlertBox.display("Verifique el nuevo correo", "Se ha enviado un mensaje al correo que ingreso\nSiga las instrucciones para terminar el proceso.");
			return true;
		}
		else if (response.body().contains("noe#")) {
			AlertBox.display("Error", "Hubo un error, verifique que su contraseña esta correcta\nO intente de nuevo más tarde");
			return false;
		}
		else {
			AlertBox.display("Error", "Hubo un error al modificar sus datos, intente de nuevo más tarde");
			return false;
		}
	}
	
	private static void changeFechaNac(Usuario user) {
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Cambiar fecha de nacimiento");
		window.setMinWidth(350);
		window.setResizable(false);
		
		Font fontTitulo = Font.font("Courier New",FontWeight.BOLD,22);
		Font fontTexto = Font.font("Courier New",FontWeight.NORMAL,16);
		Label label = new Label();
		label.setText("Cambiar nombre");
		label.setFont(fontTitulo);
		
		Label nuevaFechaNac = new Label();
		nuevaFechaNac.setText("Escoja su nueva fecha de nacimiento: ");
		nuevaFechaNac.setFont(fontTexto);
		DatePicker fieldFechaNac = new DatePicker();
		fieldFechaNac.setPromptText("Nombre");
		
		Label contrasena = new Label();
		contrasena.setText("Escriba su contraseña: ");
		contrasena.setFont(fontTexto);
		PasswordField fieldContrasena = new PasswordField();
		fieldContrasena.setPromptText("Contraseña");
		fieldContrasena.setFont(fontTexto);
		
		Button aceptButton = new Button("Cambiar fecha de nacimiento");
		aceptButton.setFont(fontTexto);
		aceptButton.setStyle(style);
		Button cancelButton = new Button("Cerrar ventana");
		cancelButton.setFont(fontTexto);
		cancelButton.setStyle(style);

		
		aceptButton.setOnAction(e->{
			
			try {
				if (registrarFechaNacBD(user,fieldFechaNac.getValue(),fieldContrasena.getText())) {
					window.close();
				}
			} catch (IOException e1) {
				AlertBox.display("Error", "Error al conectar con el servidor, intente de nuevo más tarde");
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				AlertBox.display("Error", "Se interrumpió la conexión, intente de nuevo más tarde");
				e1.printStackTrace();
			}
		});
		cancelButton.setOnAction(e->window.close());
		
		VBox layout = new VBox();
		layout.setSpacing(10);
		layout.setPadding(new Insets(10,15,10,15));
		layout.setAlignment(Pos.BASELINE_CENTER);
		layout.getChildren().addAll(label,nuevaFechaNac,fieldFechaNac,contrasena,fieldContrasena,aceptButton,cancelButton);
		BackgroundFill backgroundFill =
		        new BackgroundFill(
		                Color.valueOf("#b6d8f2"),
		                new CornerRadii(10),
		                new Insets(10)
		                );

		Background background =
		        new Background(backgroundFill);
		layout.setBackground(background);
		
		Scene scene = new Scene(layout,400,300);
		window.setScene(scene);
		window.showAndWait();
		
		return;
	}
	
	private static boolean registrarFechaNacBD(Usuario user, LocalDate newFechaNac, String contrasena) throws IOException, InterruptedException {
		if (newFechaNac == null || contrasena.equals("")) {
			AlertBox.display("Error", "Por favor asegúrese de llenar todos los campos");
			return false;
		}
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(URI.create(
				Utilities.getBaseURL() + "/Config/modificarfechanac.php?id="+user.getID()+
				"&fechanac=" + newFechaNac.toString() + "&contrasena=" + Utilities.stringToUTF(contrasena))).GET().build();
		HttpResponse<String> response;
		response = client.send(request, BodyHandlers.ofString());
		System.out.println(response.body());
		if (response.body().contains("Exito#")) {
			AlertBox.display("Exito", "Se cambió la fecha de nacimiento con éxito");
			return true;
		}
		else if (response.body().contains("noe#")) {
			AlertBox.display("Error", "Hubo un error, verifique que su contraseña esta correcta\nO intente de nuevo más tarde");
			return false;
		}
		else {
			AlertBox.display("Error", "Hubo un error al modificar sus datos, intente de nuevo más tarde");
			return false;
		}
	}
	
	private static void changeUbicacion(Usuario user) {
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Cambiar datos de ubicación");
		window.setMinWidth(350);
		window.setResizable(false);
		
		Font fontTitulo = Font.font("Courier New",FontWeight.BOLD,22);
		Font fontTexto = Font.font("Courier New",FontWeight.NORMAL,16);
		Label label = new Label();
		label.setText("Cambiar datos de ubicación");
		label.setFont(fontTitulo);
		
		Label instrucciones = new Label();
		instrucciones.setText("Deje vacíos los datos que no cambian");
		instrucciones.setFont(fontTexto);
		
		Label nuevoPais = new Label();
		nuevoPais.setText("Escriba su nuevo país: ");
		nuevoPais.setFont(fontTexto);
		TextField fieldPais = new TextField();
		fieldPais.setPromptText("País");
		fieldPais.setFont(fontTexto);
		
		Label nuevoMunicipio = new Label();
		nuevoMunicipio.setText("Escriba su nuevo municipio: ");
		nuevoMunicipio.setFont(fontTexto);
		TextField fieldMunicipio = new TextField();
		fieldMunicipio.setPromptText("Municipio");
		fieldMunicipio.setFont(fontTexto);
		
		Label nuevaLocalidad = new Label();
		nuevaLocalidad.setText("Escriba su nueva localidad: ");
		nuevaLocalidad.setFont(fontTexto);
		TextField fieldLocalidad = new TextField();
		fieldLocalidad.setPromptText("Localidad");
		fieldLocalidad.setFont(fontTexto);
		
		Label contrasena = new Label();
		contrasena.setText("Escriba su contraseña: ");
		contrasena.setFont(fontTexto);
		PasswordField fieldContrasena = new PasswordField();
		fieldContrasena.setPromptText("Contraseña");
		fieldContrasena.setFont(fontTexto);
		
		Button aceptButton = new Button("Cambiar fecha de nacimiento");
		aceptButton.setFont(fontTexto);
		aceptButton.setStyle(style);
		Button cancelButton = new Button("Cerrar ventana");
		cancelButton.setFont(fontTexto);
		cancelButton.setStyle(style);
		
		aceptButton.setOnAction(e->{
			
			try {
				if (registrarUbicacionBD(user,fieldPais.getText(),fieldMunicipio.getText(),fieldLocalidad.getText(),fieldContrasena.getText())) {
					window.close();
				}
			} catch (IOException e1) {
				AlertBox.display("Error", "Error al conectar con el servidor, intente de nuevo más tarde");
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				AlertBox.display("Error", "Se interrumpió la conexión, intente de nuevo más tarde");
				e1.printStackTrace();
			}
		});
		cancelButton.setOnAction(e->window.close());
		
		VBox layout = new VBox();
		layout.setSpacing(10);
		layout.setPadding(new Insets(10,15,10,15));
		layout.setAlignment(Pos.BASELINE_CENTER);
		layout.getChildren().addAll(label,instrucciones,nuevoPais,fieldPais,nuevoMunicipio,fieldMunicipio,
				nuevaLocalidad,fieldLocalidad,contrasena,fieldContrasena,aceptButton,cancelButton);
		BackgroundFill backgroundFill =
		        new BackgroundFill(
		                Color.valueOf("#b6d8f2"),
		                new CornerRadii(10),
		                new Insets(10)
		                );

		Background background =
		        new Background(backgroundFill);
		layout.setBackground(background);
		
		Scene scene = new Scene(layout,400,500);
		window.setScene(scene);
		window.showAndWait();
		
		return;
	}
	
	private static boolean registrarUbicacionBD(Usuario user, String pais, String municipio, String localidad, String contrasena) throws IOException, InterruptedException {
		if ((pais.equals("") && municipio.equals("") && localidad.equals("")) || contrasena.equals("")) {
			AlertBox.display("Error", "Por favor asegúrese de llenar al menos un dato a cambiar e ingresar su contraseña");
			return false;
		}
		HttpClient client = HttpClient.newHttpClient();
		String url = Utilities.getBaseURL() + "/Config/modificarubicacion.php?id="+user.getID()+
				"&contrasena=" + Utilities.stringToUTF(contrasena);
		if (!pais.equals("")) {
			url = url + "&pais=" + Utilities.stringToUTF(pais);
		}
		if (!municipio.equals("")) {
			url = url + "&municipio=" + Utilities.stringToUTF(municipio);
		}
		if (!localidad.equals("")) {
			url = url + "&localidad=" + Utilities.stringToUTF(localidad);
		}
		System.out.println(url);
		HttpRequest request = HttpRequest.newBuilder(URI.create(url)).GET().build();
		HttpResponse<String> response;
		response = client.send(request, BodyHandlers.ofString());
		System.out.println(response.body());
		if (response.body().contains("Exito#")) {
			AlertBox.display("Exito", "Se cambió los datos de ubicación con éxito");
			return true;
		}
		else if (response.body().contains("noe#")) {
			AlertBox.display("Error", "Hubo un error, verifique que su contraseña esta correcta\nO intente de nuevo más tarde");
			return false;
		}
		else if (response.body().contains("noe#")) {
			AlertBox.display("Error", "Hubo un error, verifique que su contraseña esta correcta\nO intente de nuevo más tarde");
			return false;
		}
		else {
			AlertBox.display("Error", "Hubo un error al modificar sus datos, intente de nuevo más tarde");
			return false;
		}
	}
}
