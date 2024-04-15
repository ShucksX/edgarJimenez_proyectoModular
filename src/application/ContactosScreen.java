package application;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ContactosScreen {
	private Scene contactosScene;
	private Button botonVolver;
	private TextField fieldId,fieldIdusuario, fieldNombre,fieldParentesco, fieldPais,fieldMunicipio,fieldLocalidad,fieldDireccion,fieldNumTel,fieldCorreo;
	private boolean mode;
	public ContactosScreen(Scene returnScene, Stage primaryStage, boolean mode, String userID) {
		this.mode = mode;
		BorderPane layout = new BorderPane();
		
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
		Font fontTitulo = Font.font("Courier New",FontWeight.BOLD,22);
		Font fontTexto = Font.font("Courier New",FontWeight.NORMAL,15);
		
		Label lblTitulo = new Label ("Contactos");
		lblTitulo.setFont(fontTitulo);
		
		VBox right = new VBox();
		right.setSpacing(5);
		right.setPadding(new Insets(10,15,10,10));
		right.setAlignment(Pos.BASELINE_CENTER);
		
		botonVolver = new Button();
		botonVolver.setText("Volver a menú de resultados");
		botonVolver.setMinWidth(300);
		botonVolver.setFont(fontTexto);
		botonVolver.setOnAction(e-> primaryStage.setScene(returnScene));
		
		TableView <Contacto> table = new TableView <>();
		table.setMaxWidth(600);
		TableColumn<Contacto, String> id = new TableColumn <> ("ID");
		id.setCellValueFactory(cellData -> cellData.getValue().idProperty());
		TableColumn<Contacto, String> idusuario = new TableColumn <> ("ID de usuario");
		idusuario.setCellValueFactory(cellData -> cellData.getValue().idusuarioProperty());
		TableColumn<Contacto, String> nombre = new TableColumn <> ("Nombre");
		nombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
		TableColumn<Contacto, String> parentesco = new TableColumn <> ("Parentesco");
		parentesco.setCellValueFactory(cellData -> cellData.getValue().parentescoProperty());
		TableColumn<Contacto, String> pais = new TableColumn <> ("Pais");
		pais.setCellValueFactory(cellData -> cellData.getValue().paisProperty());
		TableColumn<Contacto, String> municipio = new TableColumn <> ("Municipio");
		municipio.setCellValueFactory(cellData -> cellData.getValue().municipioProperty());
		TableColumn<Contacto, String> localidad = new TableColumn <> ("Localidad");
		localidad.setCellValueFactory(cellData -> cellData.getValue().localidadProperty());
		TableColumn<Contacto, String> direccion = new TableColumn <> ("Dirección");
		direccion.setCellValueFactory(cellData -> cellData.getValue().direccionProperty());
		TableColumn<Contacto, String> numtel = new TableColumn <> ("Numero de teléfono");
		numtel.setCellValueFactory(cellData -> cellData.getValue().numTelProperty());
		TableColumn<Contacto, String> correo = new TableColumn <> ("Correo");
		correo.setCellValueFactory(cellData -> cellData.getValue().correoProperty());
		if (mode) {
			table.getColumns().add(id);
			table.getColumns().add(idusuario);
		}
		table.getColumns().add(nombre);
		table.getColumns().add(parentesco);
		table.getColumns().add(pais);
		table.getColumns().add(municipio);
		table.getColumns().add(localidad);
		table.getColumns().add(direccion);
		table.getColumns().add(numtel);
		table.getColumns().add(correo);
		try {
			if(mode)
				fillTable(table);
			else
				fillTablebyID(table,userID);
		} catch (IOException e) {
			AlertBox.display("Error.", "La conexión al servidor se interrumpió.");
			e.printStackTrace();
		} catch (InterruptedException e) {
			AlertBox.display("Error.", "La conexión al servidor se interrumpió.");
			e.printStackTrace();
		}
		table.setMinWidth(600);
		table.setMinHeight(600);
		
		Insets inset = new Insets(0,0,0,10);
		Label txtId = new Label ("ID:");
		txtId.setFont(fontTexto);
		fieldId = new TextField();
		fieldId.setPromptText("ID");
		fieldId.setPadding(inset);
		fieldId.setFont(fontTexto);
		Label txtIdusuario = new Label ("ID de usuario:");
		txtIdusuario.setFont(fontTexto);
		fieldIdusuario = new TextField();
		fieldIdusuario.setPromptText("ID de usuario");
		fieldIdusuario.setPadding(inset);
		fieldIdusuario.setFont(fontTexto);
		Label txtNombre = new Label ("Nombre:");
		txtNombre.setFont(fontTexto);
		fieldNombre = new TextField();
		fieldNombre.setPromptText("Nombre");
		fieldNombre.setPadding(inset);
		fieldNombre.setFont(fontTexto);
		Label txtParentesco = new Label ("Parentesco:");
		txtParentesco.setFont(fontTexto);
		fieldParentesco = new TextField();
		fieldParentesco.setPromptText("Parentesco");
		fieldParentesco.setPadding(inset);
		fieldParentesco.setFont(fontTexto);
		Label txtPais= new Label ("Pais:");
		txtPais.setFont(fontTexto);
		fieldPais = new TextField();
		fieldPais.setPromptText("Pais");
		fieldPais.setPadding(inset);
		fieldPais.setFont(fontTexto);
		Label txtMunicipio= new Label ("Municipio:");
		txtMunicipio.setFont(fontTexto);
		fieldMunicipio = new TextField();
		fieldMunicipio.setPromptText("Municipio");
		fieldMunicipio.setPadding(inset);
		fieldMunicipio.setFont(fontTexto);
		Label txtLocalidad= new Label ("Localidad:");
		txtLocalidad.setFont(fontTexto);
		fieldLocalidad = new TextField();
		fieldLocalidad.setPromptText("Localidad");
		fieldLocalidad.setPadding(inset);
		fieldLocalidad.setFont(fontTexto);
		Label txtDireccion= new Label ("Dirección:");
		txtDireccion.setFont(fontTexto);
		fieldDireccion = new TextField();
		fieldDireccion.setPromptText("Dirección");
		fieldDireccion.setPadding(inset);
		fieldDireccion.setFont(fontTexto);
		Label txtNumTel= new Label ("Numero de teléfono:");
		txtNumTel.setFont(fontTexto);
		fieldNumTel = new TextField();
		fieldNumTel.setPromptText("Numero de teléfono");
		fieldNumTel.setPadding(inset);
		fieldNumTel.setFont(fontTexto);
		Label txtCorreo= new Label ("Correo:");
		txtCorreo.setFont(fontTexto);
		fieldCorreo = new TextField();
		fieldCorreo.setPromptText("Correo");
		fieldCorreo.setPadding(inset);
		fieldCorreo.setFont(fontTexto);
		
		
		Button btnBuscar = new Button();
		btnBuscar.setText("Buscar");
		btnBuscar.setMinWidth(300);
		btnBuscar.setFont(fontTexto);
		btnBuscar.setOnAction(e-> {
			try {
				buscar(table,userID);
			} catch (IOException e1) {
				AlertBox.display("Error.", "La conexión al servidor se interrumpió.");
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				AlertBox.display("Error.", "La conexión al servidor se interrumpió.");
				e1.printStackTrace();
			}
		});
		
		Button btnAddContacto = new Button();
		btnAddContacto.setText("Añadir nuevo contacto");
		btnAddContacto.setMinWidth(300);
		btnAddContacto.setFont(fontTexto);
		btnAddContacto.setOnAction(e-> addContacto(primaryStage,returnScene,userID));
		
		Button btnDelContacto = new Button();
		btnDelContacto.setText("Eliminar contacto");
		btnDelContacto.setMinWidth(300);
		btnDelContacto.setFont(fontTexto);
		btnDelContacto.setOnAction(e->{
			try {
				delContacto(table.getSelectionModel().getSelectedItem(),primaryStage,returnScene);
			} catch (IOException e1) {
				AlertBox.display("Error.", "La conexión al servidor se interrumpió.");
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				AlertBox.display("Error.", "La conexión al servidor se interrumpió.");
				e1.printStackTrace();
			}
		});
		
		top.getChildren().add(lblTitulo);
		center.getChildren().add(table);
		if (mode) {
			right.getChildren().addAll(txtId,fieldId,txtIdusuario,fieldIdusuario);
		}
		left.getChildren().addAll(btnAddContacto,btnDelContacto,botonVolver);
		right.getChildren().addAll(txtNombre,fieldNombre,txtParentesco,fieldParentesco,txtPais,fieldPais,txtMunicipio,fieldMunicipio,
				txtLocalidad,fieldLocalidad,txtDireccion,fieldDireccion);
		right.getChildren().addAll(txtNumTel,fieldNumTel,txtCorreo,fieldCorreo,btnBuscar);
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
		contactosScene = new Scene(layout,Utilities.windowWidth,Utilities.windowHeight);
		contactosScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	}
	
	public Button getBotonVolver() {
		return botonVolver;
	}
	
	public Scene getScene() {
		return contactosScene;
	}
	
	public void fillTablebyID(TableView<Contacto> table, String userid) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(URI.create(
				Utilities.getBaseURL() + "/Centros/obtenercontactos.php?idusuario=" + userid)).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		setupTable(table,response.body());
	}
	
	public void fillTable(TableView<Contacto> table) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(URI.create(
				Utilities.getBaseURL() + "/Centros/obtenercontactos.php")).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		setupTable(table,response.body());
	}
	
	public void setupTable(TableView<Contacto> table, String respuesta) {
		System.out.println(respuesta);
		int index1 = 0;
		int index2 = respuesta.indexOf("|");
		String id, idusuario,nombre,parentesco,pais,municipio,localidad,direccion,numtel,correo;
		ObservableList<Contacto> data = FXCollections.observableArrayList();
		while (index2 != -1) {
			id = respuesta.substring(index1,index2);
			index1 = index2+1;
			index2 = respuesta.indexOf("|",index1);
			idusuario = respuesta.substring(index1,index2);
			index1 = index2+1;
			index2 = respuesta.indexOf("|",index1);
			nombre = respuesta.substring(index1,index2);
			index1 = index2+1;
			index2 = respuesta.indexOf("|",index1);
			parentesco = respuesta.substring(index1,index2);
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
			index2 = respuesta.indexOf("|",index1);
			direccion = respuesta.substring(index1,index2);
			index1 = index2+1;
			index2 = respuesta.indexOf("|",index1);
			numtel = respuesta.substring(index1,index2);
			index1 = index2+1;
			index2 = respuesta.indexOf("%",index1);
			correo = respuesta.substring(index1,index2);
			index1 = index2+1;
			index2 = respuesta.indexOf("|",index1);
			data.add( new Contacto(id,idusuario,nombre,parentesco,pais,municipio,localidad,direccion,numtel,correo));
		}
		
		table.setItems(data);
	}
	
	public void buscar(TableView<Contacto> table,String userid) throws IOException, InterruptedException {
		String url = Utilities.getBaseURL() + "/Centros/obtenercontactos.php";
		boolean search = false;
		if (mode) {
			if (fieldId.getText() != "") {
				if (!search) {
						url = url + "?";
						search = true;
				}
				url = url + "id="+Utilities.stringToUTF(fieldId.getText());
			}
			
			if (fieldIdusuario.getText() != "") {
				if (!search) {
					url = url + "?";
					search = true;
				}
				else {
					url = url + "&";
				}
				url = url + "idusuario="+Utilities.stringToUTF(fieldIdusuario.getText());
			}
		}
		else {
			if (!search) {
				url = url + "?";
				search = true;
			}
			url = url + "idusuario="+Utilities.stringToUTF(userid);
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
		if (fieldParentesco.getText() != "") {
			if (!search) {
				url = url + "?";
				search = true;
			}
			else {
				url = url + "&";
			}
			url = url + "parentesco="+Utilities.stringToUTF(fieldParentesco.getText());
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
		
		if (fieldDireccion.getText() != "") {
			if (!search) {
				url = url + "?";
				search = true;
			}
			else {
				url = url + "&";
			}
				url = url + "direccion="+Utilities.stringToUTF(fieldDireccion.getText());
		}
		
		if (fieldNumTel.getText() != "") {
			if (!search) {
				url = url + "?";
				search = true;
			}
			else {
				url = url + "&";
			}
			url = url + "telefono="+Utilities.stringToUTF(fieldNumTel.getText());
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
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(URI.create(url)).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		setupTable(table,response.body());
	}
	
	private void delContacto(Contacto contacto, Stage primaryStage, Scene returnScene) throws IOException, InterruptedException {
		if(contacto == null) {
			AlertBox.display("Alerta.", "Seleccione un contacto primero.");
		}
		else {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder(URI.create(
					Utilities.getBaseURL() + "/Centros/eliminarcontacto.php?id="+ Utilities.stringToUTF(contacto.getID()))).GET().build();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			System.out.println(response.body());
			if(response.body().contains("Exito#")) {
				AlertBox.display("Éxito.", "Se eliminó el contacto con éxito.");
				primaryStage.setScene(returnScene);
			}
			else {
				AlertBox.display("Error.", "Hubo un error al eliminar el contacto.");
			}
		}
	}
	
	private void addContacto (Stage primaryStage, Scene returnScene, String userID) {
		if(AddContacto.display(userID)) {
			primaryStage.setScene(returnScene);
		}
	}
}

