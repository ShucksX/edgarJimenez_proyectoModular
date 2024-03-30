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

public class SucursalesScreen {
	private Scene sucursalesScene;
	private Button botonVolver;
	private TextField fieldId, fieldNombre, fieldPais,fieldMunicipio,fieldLocalidad,fieldDireccion,fieldNumTel,fieldCorreo;
	private boolean mode;
	public SucursalesScreen(Scene returnScene, Stage primaryStage, boolean mode, String userID) {
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
		
		Label lblTitulo = new Label ("Centros de atención");
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
		
		TableView <Sucursal> table = new TableView <>();
		table.setMaxWidth(600);
		TableColumn<Sucursal, String> id = new TableColumn <> ("ID");
		id.setCellValueFactory(cellData -> cellData.getValue().idProperty());
		TableColumn<Sucursal, String> nombre = new TableColumn <> ("Nombre del centro");
		nombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
		TableColumn<Sucursal, String> pais = new TableColumn <> ("Pais");
		pais.setCellValueFactory(cellData -> cellData.getValue().paisProperty());
		TableColumn<Sucursal, String> municipio = new TableColumn <> ("Municipio");
		municipio.setCellValueFactory(cellData -> cellData.getValue().municipioProperty());
		TableColumn<Sucursal, String> localidad = new TableColumn <> ("Localidad");
		localidad.setCellValueFactory(cellData -> cellData.getValue().localidadProperty());
		TableColumn<Sucursal, String> direccion = new TableColumn <> ("Dirección");
		direccion.setCellValueFactory(cellData -> cellData.getValue().direccionProperty());
		TableColumn<Sucursal, String> numtel = new TableColumn <> ("Numero de teléfono");
		numtel.setCellValueFactory(cellData -> cellData.getValue().numTelProperty());
		TableColumn<Sucursal, String> correo = new TableColumn <> ("Correo");
		correo.setCellValueFactory(cellData -> cellData.getValue().correoProperty());
		if (mode) {
			table.getColumns().add(id);
		}
		table.getColumns().add(nombre);
		table.getColumns().add(pais);
		table.getColumns().add(municipio);
		table.getColumns().add(localidad);
		table.getColumns().add(direccion);
		table.getColumns().add(numtel);
		table.getColumns().add(correo);
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
		
		Insets inset = new Insets(0,0,0,10);
		Label txtId = new Label ("ID:");
		txtId.setFont(fontTexto);
		fieldId = new TextField();
		fieldId.setPromptText("ID");
		fieldId.setPadding(inset);
		fieldId.setFont(fontTexto);
		Label txtNombre = new Label ("Nombre:");
		txtNombre.setFont(fontTexto);
		fieldNombre = new TextField();
		fieldNombre.setPromptText("Nombre");
		fieldNombre.setPadding(inset);
		fieldNombre.setFont(fontTexto);
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
				buscar(table);
			} catch (IOException e1) {
				AlertBox.display("Error", "La conexión al servidor se interrumpió");
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				AlertBox.display("Error", "La conexión al servidor se interrumpió");
				e1.printStackTrace();
			}
		});
		
		Button btnBuscarArea = new Button();
		btnBuscarArea.setText("Buscar centros cercanos");
		btnBuscarArea.setMinWidth(300);
		btnBuscarArea.setFont(fontTexto);
		btnBuscarArea.setOnAction(e-> {
			try {
				buscarArea(table,userID);
			} catch (IOException e1) {
				AlertBox.display("Error", "La conexión al servidor se interrumpió");
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				AlertBox.display("Error", "La conexión al servidor se interrumpió");
				e1.printStackTrace();
			}
		});
		
		Button btnAddCentro = new Button();
		btnAddCentro.setText("Añadir nuevo centro");
		btnAddCentro.setMinWidth(300);
		btnAddCentro.setFont(fontTexto);
		btnAddCentro.setOnAction(e-> addCenter(primaryStage,returnScene));
		
		top.getChildren().add(lblTitulo);
		center.getChildren().add(table);
		if (mode) {
			left.getChildren().add(btnAddCentro);
			right.getChildren().addAll(txtId,fieldId);
		}
		left.getChildren().addAll(botonVolver);
		right.getChildren().addAll(txtNombre,fieldNombre,txtPais,fieldPais,txtMunicipio,fieldMunicipio,
				txtLocalidad,fieldLocalidad,txtDireccion,fieldDireccion);
		right.getChildren().addAll(txtNumTel,fieldNumTel,txtCorreo,fieldCorreo,btnBuscar,btnBuscarArea);
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
		sucursalesScene = new Scene(layout,Utilities.windowWidth,Utilities.windowHeight);
		sucursalesScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	}
	
	public Button getBotonVolver() {
		return botonVolver;
	}
	
	public Scene getScene() {
		return sucursalesScene;
	}
	
	public void fillTable(TableView<Sucursal> table) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(URI.create(
				Utilities.getBaseURL() + "/Centros/obtenercentros.php")).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		setupTable(table,response.body());
	}
	
	public void setupTable(TableView<Sucursal> table, String respuesta) {
		System.out.println(respuesta);
		int index1 = 0;
		int index2 = respuesta.indexOf("|");
		String id, nombre,pais,municipio,localidad,direccion,numtel,correo;
		ObservableList<Sucursal> data = FXCollections.observableArrayList();
		while (index2 != -1) {
			id = respuesta.substring(index1,index2);
			index1 = index2+1;
			index2 = respuesta.indexOf("|",index1);
			nombre = respuesta.substring(index1,index2);
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
			data.add( new Sucursal(id,nombre,pais,municipio,localidad,direccion,numtel,correo));
		}
		
		table.setItems(data);
	}
	
	public void buscarArea(TableView<Sucursal> table, String UserID) throws IOException, InterruptedException {
		String url = Utilities.getBaseURL() + "/Centros/obtenerubicacion.php?userid=" + UserID;
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(URI.create(url)).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		if(response.body().contains("noe#")) {
			AlertBox.display("Hubo un error al obtener tu ubicación", "No se pudo obtener tu ubicación para la búsqueda, intenta de nuevo más tarde.");
		}
		else if(response.body().contains("noec#")) {
			AlertBox.display("Error", "No se logró encontrar centros cerca de tu ubicación, verifica tus datos de ubicación en tu configuración de usuario.");
		}
		else {
			AlertBox.display("Éxito", "Se encontraron centros de atención cercanos a tu ubicación.");
			setupTable(table,response.body());
		}
	}
	
	public void buscar(TableView<Sucursal> table) throws IOException, InterruptedException {
		String url = Utilities.getBaseURL() + "/Centros/obtenercentros.php";
		boolean search = false;
		if (mode) {
			if (fieldId.getText() != "") {
				if (!search) {
						url = url + "?";
						search = true;
				}
				url = url + "id="+Utilities.stringToUTF(fieldId.getText());
			}
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
			url = url + "numerotelefono="+Utilities.stringToUTF(fieldNumTel.getText());
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
	
	private void addCenter (Stage primaryStage, Scene returnScene) {
		if(AddCenter.display()) {
			primaryStage.setScene(returnScene);
		}
	}
}

