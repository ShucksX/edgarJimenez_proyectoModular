package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import javafx.collections.*;

public class AddAdminScreen {
	private Scene addAdminScene;
	private Button botonVolver,botonRemAdmin;
	private TextField fieldId, fieldNombre, fieldCorreo, fieldPais, fieldMunicipio, fieldLocalidad, fieldEstado;
	private DatePicker fieldFechaNac;
	@SuppressWarnings("unchecked")
	public AddAdminScreen() {
		
		BorderPane layout = new BorderPane();
		Font fontTexto = Font.font("Courier New",FontWeight.NORMAL,16);

		
		VBox top = new VBox();
		top.setSpacing(8);
		top.setPadding(new Insets(10,10,10,10));
		top.setAlignment(Pos.BASELINE_CENTER);
		
		VBox left = new VBox();
		left.setSpacing(20);
		left.setPadding(new Insets(10,10,10,15));
		left.setAlignment(Pos.BASELINE_CENTER);
		
		Group center = new Group();
		//Add components
		Label lblTitulo = new Label ("Administrar usuarios");
		Font fontTitulo = Font.font("Courier New",FontWeight.BOLD,18);
		lblTitulo.setFont(fontTitulo);
		
		VBox right = new VBox();
		right.setSpacing(5);
		right.setPadding(new Insets(10,15,10,10));
		right.setAlignment(Pos.BASELINE_CENTER);
		
		botonVolver = new Button();
		botonVolver.setText("Volver a menú de administrador");
		botonVolver.setMinWidth(220);
		botonVolver.setFont(fontTexto);
		
		Button botonAddAdmin = new Button();
		botonAddAdmin.setText("Convertir usuario en administrador");
		botonAddAdmin.setMinWidth(220);
		botonAddAdmin.setFont(fontTexto);
		
		
		botonRemAdmin = new Button();
		botonRemAdmin.setText("Remover mi rol de administrador");
		botonRemAdmin.setMinWidth(220);
		botonRemAdmin.setFont(fontTexto);
		
		TableView <Usuario> table = new TableView <>();
		table.setMaxWidth(600);
		TableColumn<Usuario, String> id = new TableColumn <> ("ID");
		id.setCellValueFactory(cellData -> cellData.getValue().idProperty());
		TableColumn <Usuario, String> nombre = new TableColumn <> ("Nombre");
		nombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
		TableColumn<Usuario, String> fechanac = new TableColumn <> ("Fecha de nacimiento");
		fechanac.setCellValueFactory(cellData -> cellData.getValue().fechaNacProperty());
		TableColumn<Usuario, String> correo = new TableColumn <> ("Correo");
		correo.setCellValueFactory(cellData -> cellData.getValue().correoProperty());
		TableColumn<Usuario, String> pais = new TableColumn <> ("País");
		pais.setCellValueFactory(cellData -> cellData.getValue().paisProperty());
		TableColumn<Usuario, String> municipio = new TableColumn <> ("Municipio");
		municipio.setCellValueFactory(cellData -> cellData.getValue().municipioProperty());
		TableColumn<Usuario, String> localidad = new TableColumn <> ("Localidad");
		localidad.setCellValueFactory(cellData -> cellData.getValue().localidadProperty());
		TableColumn <Usuario, String> estado = new TableColumn <> ("Estado");
		estado.setCellValueFactory(cellData -> cellData.getValue().estadoProperty());
		table.getColumns().addAll(id,nombre,fechanac,correo,pais,municipio,localidad,estado);
		try {
			fillTable(table);
		} catch (IOException e) {
			AlertBox.display("Error", "La conexión al servidor se interrumpió");
			e.printStackTrace();
		} catch (InterruptedException e) {
			AlertBox.display("Error", "La conexión al servidor se interrumpió");
			e.printStackTrace();
		}
		table.setMinWidth(600);
		table.setMinHeight(600);
		
		botonAddAdmin.setOnAction(e-> {
			try {
				addAdmin(table.getSelectionModel().getSelectedItem());
			} catch (IOException e1) {
				AlertBox.display("Error", "La conexión al servidor se interrumpió");
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				AlertBox.display("Error", "La conexión al servidor se interrumpió");
				e1.printStackTrace();
			}
		});
		
		Insets inset = new Insets(0,0,0,10);
		Label txtId = new Label ("ID :");
		fieldId = new TextField();
		fieldId.setPromptText("ID");
		fieldId.setPadding(inset);
		Label txtnombre = new Label ("Nombre :");
		fieldNombre = new TextField();
		fieldNombre.setPromptText("Nombre");
		fieldNombre.setPadding(inset);
		Label txtfechanac = new Label ("Fecha de nacimiento:");
		fieldFechaNac = new DatePicker();
		fieldFechaNac.setPromptText("Fecha de nacimiento");
		fieldFechaNac.setPadding(inset);
		Label txtCorreo = new Label ("Correo:");
		fieldCorreo = new TextField();
		fieldCorreo.setPromptText("Correo");
		fieldCorreo.setPadding(inset);
		Label txtPais = new Label ("Pais:");
		fieldPais = new TextField();
		fieldPais.setPromptText("Pais");
		fieldPais.setPadding(inset);
		Label txtMunicipio = new Label ("Municipio:");
		fieldMunicipio = new TextField();
		fieldMunicipio.setPromptText("Municipio");
		fieldMunicipio.setPadding(inset);
		Label txtLocalidad = new Label ("Localidad:");
		fieldLocalidad = new TextField();
		fieldLocalidad.setPromptText("Localidad");
		fieldLocalidad.setPadding(inset);
		Label txtEstado = new Label ("Estado:");
		fieldEstado = new TextField();
		fieldEstado.setPromptText("Estado");
		fieldEstado.setPadding(inset);
		
		Button btnBuscar = new Button();
		btnBuscar.setText("Buscar");
		btnBuscar.setFont(fontTexto);
		btnBuscar.setOnAction(e-> {
			try {
				buscar(table);
			} catch (IOException e1) {
				AlertBox.display("Error", "La conexión al servidor se interrumpió");
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				AlertBox.display("Error", "La conexión al servidor se interrumpió");
				e1.printStackTrace();
			}
		});
		
		top.getChildren().add(lblTitulo);
		left.getChildren().addAll(botonAddAdmin,botonRemAdmin, botonVolver);
		center.getChildren().add(table);
		right.getChildren().addAll(txtId,fieldId,txtnombre,fieldNombre,txtfechanac,fieldFechaNac,
				txtCorreo,fieldCorreo,txtPais,fieldPais,txtMunicipio,fieldMunicipio,txtLocalidad,
				fieldLocalidad,txtEstado,fieldEstado, btnBuscar);
		layout.setTop(top);
		layout.setLeft(left);
		layout.setCenter(center);
		layout.setRight(right);
		BackgroundFill backgroundFill =
		        new BackgroundFill(
		                Color.valueOf("#fabebe"),
		                new CornerRadii(10),
		                new Insets(10)
		                );

		Background background =
		        new Background(backgroundFill);
		layout.setBackground(background);
		//Set Scene
		addAdminScene = new Scene(layout,Utilities.windowWidth,Utilities.windowHeight);
		addAdminScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
	}
	
	public void addAdmin(Usuario user) throws IOException, InterruptedException {
		if (ConfirmBox.display("Alerta", "¿Esta seguro que desea promover al usuario seleccionado a administrador?")) {
			if (user  == null) {
				AlertBox.display("Error", "Seleccione un usuario primero");
			}
			else if (user.getEstado().contains("Sin verificar")) {
				AlertBox.display("Error", "El usuario debe de estar verificado antes de promoverlo a administrador");
			}
			else if (user.getEstado().contains("Administrador")) {
				AlertBox.display("Error", "Este usuario ya es administrador");
			}
			else {
				HttpClient client = HttpClient.newHttpClient();
				HttpRequest request = HttpRequest.newBuilder(URI.create
						(Utilities.getBaseURL() + "/cambiaradmin.php?iduser=" +
				user.getID() + "&status=2")).GET().build();
				HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
				String respuesta = response.body();
				if (respuesta.contains("Exito#"))
				{
					AlertBox.display("Exito", "Se promovio el usuario seleccionado a administrador");
					user.setEstado("Administrador");
				}
				else {
					AlertBox.display("Error", "Hubo un error al promover el usuario a administrador");
					System.out.println(respuesta);
				}
			}
		}
		
	}
	
	public void removeAdmin(Stage stage, Scene mainScreen, String userId) throws IOException, InterruptedException {
		if (ConfirmBox.display("Alerta", "¿Esta seguro que desea remover su rol de administrador?")) {
			if (ConfirmBox.display("Alerta", "¿Esta seguro? NO PODRA RECUPERAR SU ROL DE ADMINISTRADOR\nHASTA QUE ALGUIEN MAS LE CONCEDA EL ROL")) {
				HttpClient client = HttpClient.newHttpClient();
				HttpRequest request = HttpRequest.newBuilder(URI.create
						(Utilities.getBaseURL() + "/cambiaradmin.php?iduser=" +
				userId + "&status=1")).GET().build();
				HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
				String respuesta = response.body();
				if (respuesta.contains("Exito#"))
				{
					AlertBox.display("Exito", "Se removió el rol de administrador de su cuenta");
					stage.setScene(mainScreen);
				}
				else if (respuesta.contains("Nop#")) {
					AlertBox.display("Error", "Usted es el único administrador restante\nPor favor promueva un nuevo usuario antes de proceder");
				}
				else {
					AlertBox.display("Error", "Hubo un error al remover su rol de administrador");
					System.out.println(respuesta);
				}
			}
		}
	}
	
	public void fillTable(TableView<Usuario> table) throws IOException, InterruptedException {
		
		
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(URI.create(
				Utilities.getBaseURL() + "/obtenerusuarios.php")).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		setupTable(table,response.body());
	}
	
	public void setupTable(TableView<Usuario> table, String respuesta) {
		System.out.println(respuesta);
		int index1 = 0;
		int index2 = respuesta.indexOf("|");
		String id, nombre, edad, correo, pais, municipio, localidad, estado;
		ObservableList<Usuario> data = FXCollections.observableArrayList();
		while (index2 != -1) {
			id = respuesta.substring(index1,index2);
			index1 = index2+1;
			index2 = respuesta.indexOf("|",index1);
			nombre = respuesta.substring(index1,index2);
			index1 = index2+1;
			index2 = respuesta.indexOf("|",index1);
			edad = respuesta.substring(index1,index2);
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
			index2 = respuesta.indexOf("|",index1);
			localidad = respuesta.substring(index1,index2);
			index1 = index2+1;
			index2 = respuesta.indexOf("%",index1);
			if (index2 == -1) {
				estado = obtenerEstado(respuesta.substring(index1));
			}
			else {
				estado = obtenerEstado(respuesta.substring(index1,index2));
				index1 = index2+1;
				index2 = respuesta.indexOf("|",index1);
			}
			data.add( new Usuario(id,nombre,edad,correo,pais,municipio,localidad,estado));
		}
		
		table.setItems(data);
	}
	
	public void buscar(TableView<Usuario> table) throws IOException, InterruptedException {
		String url = Utilities.getBaseURL() + "/obtenerusuarios.php";
		boolean search = false;
		if (fieldId.getText() != "") {
			if (!search) {
				url = url + "?";
				search = true;
			}
			url = url + "id="+Utilities.stringToUTF(fieldId.getText());
		}
		
		if (fieldNombre.getText() != "") {
			if (!search) {
				url = url + "?";
				search = true;
			}
			else {
				url = url + "&";
			}
			url = url + "nombre="+Utilities.stringToUTF(fieldNombre.getText());
		}
		if (fieldCorreo.getText() != "") {
			if (!search) {
				url = url + "?";
				search = true;
			}
			else {
				url = url + "&";
			}
			url = url + "correo="+Utilities.stringToUTF(fieldCorreo.getText());
		}
		
		if (fieldFechaNac.getValue() != null) {
			if (!search) {
				url = url + "?";
				search = true;
			}
			else {
				url = url + "&";
			}
			url = url + "fechanac="+Utilities.stringToUTF(fieldFechaNac.getValue().toString());
		}
		
		if (fieldPais.getText() != "") {
			if (!search) {
				url = url + "?";
				search = true;
			}
			else {
				url = url + "&";
			}
			url = url + "pais="+Utilities.stringToUTF(fieldPais.getText());
		}
		
		if (fieldMunicipio.getText() != "") {
			if (!search) {
				url = url + "?";
				search = true;
			}
			else {
				url = url + "&";
			}
			url = url + "municipio="+Utilities.stringToUTF(fieldMunicipio.getText());
		}
		
		if (fieldLocalidad.getText() != "") {
			if (!search) {
				url = url + "?";
				search = true;
			}
			else {
				url = url + "&";
			}
			url = url + "localidad="+Utilities.stringToUTF(fieldLocalidad.getText());
		}
		
		if (fieldEstado.getText() != "") {
			if (!search) {
				url = url + "?";
				search = true;
			}
			else {
				url = url + "&";
			}
			if (fieldEstado.getText().equals("Sin verificar")) {
				url = url + "status=0";
			}
			else if (fieldEstado.getText().equals("Usuario")) {
				url = url + "status=1";
			}
			else if (fieldEstado.getText().equals("Administrador")) {
				url = url + "status=2";
			}
			else {
				url = url + "status="+Utilities.stringToUTF(fieldEstado.getText());
			}
		}
		
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(URI.create(url)).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		setupTable(table,response.body());
	}
	
	public String obtenerEstado(String estado) {
		if (estado.contains("0")) {
			return "Sin verificar";
		}
		else if (estado.contains("1")) {
			return "Usuario";
		}
		if (estado.contains("2")) {
			return "Administrador";
		}
		else {
			return "Error";
		}		
	}
	
	public Button getBotonVolver() {
		return botonVolver;
	}
	
	public Button getRemAdmin() {
		return botonRemAdmin;
	}
	
	public Scene getScene() {
		return addAdminScene;
	}
}
