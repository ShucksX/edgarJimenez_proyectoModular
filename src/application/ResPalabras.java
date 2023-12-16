package application;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ResPalabras {
	private Scene resPalabrasScene;
	private Button botonVolver;
	private TextField fieldId, fieldIdUsuario, fieldPalabra1,fieldPalabra2,fieldPalabra3,fieldStatus,fieldPuntos;
	private DatePicker fieldFechaAcc, fieldFechaRes;
	private boolean mode;
	private String userID;
	private String style = "-fx-border-color: linear-gradient(#f77777, #e67777);-fx-border-radius: 30; -fx-border-width: 2px;-fx-background-color: linear-gradient(#bddbb6, #b2c7ad);-fx-background-radius: 30;";
	public ResPalabras(Scene returnScene, Stage primaryStage, boolean mode, String userID) {
		this.userID = userID;
		this.mode = mode;
		BorderPane layout = new BorderPane();
		
		VBox top = new VBox();
		top.setSpacing(8);
		top.setPadding(new Insets(10,10,10,10));
		top.setAlignment(Pos.BASELINE_CENTER);
		
		VBox left = new VBox();
		left.setSpacing(20);
		left.setPadding(new Insets(0,10,10,15));
		left.setAlignment(Pos.BASELINE_CENTER);
		
		Group center = new Group();
		//Add components
		Font fontTitulo = Font.font("Courier New",FontWeight.BOLD,22);
		Font fontTexto = Font.font("Courier New",FontWeight.NORMAL,15);
		
		Label lblTitulo = new Label ("Resultados: Memorizacion de palabras");
		lblTitulo.setFont(fontTitulo);
		
		VBox right = new VBox();
		right.setSpacing(5);
		right.setPadding(new Insets(10,15,10,10));
		right.setAlignment(Pos.BASELINE_CENTER);
		
		int minwidth = 300;
		
		Button botonGrafica = new Button();
		botonGrafica.setText("Ver grafica");
		botonGrafica.setMinWidth(minwidth);
		botonGrafica.setFont(fontTexto);
		
		botonVolver = new Button();
		botonVolver.setText("Volver a menu de resultados");
		botonVolver.setMinWidth(minwidth);
		botonVolver.setFont(fontTexto);
		botonVolver.setOnAction(e-> primaryStage.setScene(returnScene));
		
		TableView <Palabra> table = new TableView <>();
		TableColumn<Palabra, String> id = new TableColumn <> ("ID");
		id.setCellValueFactory(cellData -> cellData.getValue().idProperty());
		TableColumn <Palabra, String> idusuario = new TableColumn <> ("ID de usuario");
		idusuario.setCellValueFactory(cellData -> cellData.getValue().idUsuarioProperty());
		TableColumn<Palabra, String> palabra1 = new TableColumn <> ("Primer palabra");
		palabra1.setCellValueFactory(cellData -> cellData.getValue().palabra1Property());
		TableColumn<Palabra, String> palabra2 = new TableColumn <> ("Segunda palabra");
		palabra2.setCellValueFactory(cellData -> cellData.getValue().palabra2Property());
		TableColumn<Palabra, String> palabra3 = new TableColumn <> ("Tercera palabra");
		palabra3.setCellValueFactory(cellData -> cellData.getValue().palabra3Property());
		TableColumn<Palabra, String> status = new TableColumn <> ("Estado");
		status.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
		TableColumn<Palabra, String> fechaaccesible = new TableColumn <> ("Fecha para permitir respuesta");
		fechaaccesible.setCellValueFactory(cellData -> cellData.getValue().fechaAccProperty());
		TableColumn<Palabra, String> fecharesultado = new TableColumn <> ("Fecha del resultado");
		fecharesultado.setCellValueFactory(cellData -> cellData.getValue().fechaResProperty());
		TableColumn<Palabra, String> puntos = new TableColumn <> ("Aciertos sobre 3");
		botonGrafica.setOnAction(e->ventanaGrafica(table));
		
		puntos.setCellValueFactory(cellData -> cellData.getValue().puntosProperty());
		if (mode) {
			table.getColumns().add(id);
			table.getColumns().add(idusuario);
		}
		table.getColumns().add(palabra1);
		table.getColumns().add(palabra2);
		table.getColumns().add(palabra3);
		table.getColumns().add(status);
		if(mode) {
			table.getColumns().add(fechaaccesible);
		}
		table.getColumns().add(fecharesultado);
		table.getColumns().add(puntos);
		try {
			if (mode)
				fillTable(table);
			else
				fillTableId(table);
		} catch (IOException e) {
			AlertBox.display("Error", "La conexion al servidor se interrumpio");
			e.printStackTrace();
		} catch (InterruptedException e) {
			AlertBox.display("Error", "La conexion al servidor se interrumpio");
			e.printStackTrace();
		}
		
		Insets inset = new Insets(0,0,0,10);
		Label txtId = new Label ("ID:");
		txtId.setFont(fontTexto);
		fieldId = new TextField();
		fieldId.setPromptText("ID");
		fieldId.setPadding(inset);
		fieldId.setFont(fontTexto);
		Label txtIdUsuario = new Label ("ID de usuario:");
		txtIdUsuario.setFont(fontTexto);
		fieldIdUsuario = new TextField();
		fieldIdUsuario.setPromptText("ID de usuario");
		fieldIdUsuario.setPadding(inset);
		fieldIdUsuario.setFont(fontTexto);
		Label txtPalabra1= new Label ("Primer palabra:");
		txtPalabra1.setFont(fontTexto);
		fieldPalabra1 = new TextField();
		fieldPalabra1.setPromptText("Primer palabra");
		fieldPalabra1.setPadding(inset);
		fieldPalabra1.setFont(fontTexto);
		Label txtPalabra2= new Label ("Segunda palabra:");
		txtPalabra2.setFont(fontTexto);
		fieldPalabra2 = new TextField();
		fieldPalabra2.setPromptText("Segunda palabra");
		fieldPalabra2.setPadding(inset);
		fieldPalabra2.setFont(fontTexto);
		Label txtPalabra3= new Label ("Tercera palabra:");
		txtPalabra3.setFont(fontTexto);
		fieldPalabra3 = new TextField();
		fieldPalabra3.setPromptText("Tercera palabra");
		fieldPalabra3.setPadding(inset);
		fieldPalabra3.setFont(fontTexto);
		Label txtStatus= new Label ("Estado:");
		txtStatus.setFont(fontTexto);
		fieldStatus = new TextField();
		fieldStatus.setPromptText("Estado");
		fieldStatus.setPadding(inset);
		fieldStatus.setFont(fontTexto);
		Label txtFechaAcc= new Label ("Fecha para permitir respuesta:");
		txtFechaAcc.setFont(fontTexto);
		fieldFechaAcc = new DatePicker();
		fieldFechaAcc.setMinWidth(minwidth);
		fieldFechaAcc.setPromptText("Fecha para permitir respuesta");
		//fieldFechaAcc.setPadding(inset);
		Label txtfechaRes = new Label ("Fecha del resultado:");
		txtfechaRes.setFont(fontTexto);
		fieldFechaRes = new DatePicker();
		fieldFechaRes.setMinWidth(minwidth);
		fieldFechaRes.setPromptText("Fecha del resultado");
		//fieldFechaRes.setPadding(inset);
		Label txtPuntos= new Label ("Aciertos sobre 3:");
		txtPuntos.setFont(fontTexto);
		fieldPuntos = new TextField();
		fieldPuntos.setPromptText("Aciertos sobre 3");
		fieldPuntos.setPadding(inset);
		fieldPuntos.setFont(fontTexto);
		
		
		Button btnBuscar = new Button();
		btnBuscar.setText("Buscar");
		btnBuscar.setOnAction(e-> {
			try {
				buscar(table);
			} catch (IOException e1) {
				AlertBox.display("Error", "La conexion al servidor se interrumpio");
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				AlertBox.display("Error", "La conexion al servidor se interrumpio");
				e1.printStackTrace();
			}
		});
		
		top.getChildren().add(lblTitulo);
		if (!mode) {
			left.getChildren().add(botonGrafica);
		}
		left.getChildren().addAll(botonVolver);
		center.getChildren().add(table);
		if (mode) {
			right.getChildren().addAll(txtId,fieldId,txtIdUsuario,fieldIdUsuario);
		}
		right.getChildren().addAll(txtPalabra1,fieldPalabra1,txtPalabra2,fieldPalabra2,
				txtPalabra3,fieldPalabra3,txtStatus,fieldStatus);
		if (mode) {
			right.getChildren().addAll(txtFechaAcc,fieldFechaAcc);
		}
		right.getChildren().addAll(txtfechaRes,fieldFechaRes,txtPuntos,fieldPuntos,btnBuscar);
		layout.setTop(top);
		layout.setLeft(left);
		layout.setCenter(center);
		layout.setRight(right);
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
		resPalabrasScene = new Scene(layout,Utilities.windowWidth,Utilities.windowHeight);
		
		resPalabrasScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	}
	
	public Button getBotonVolver() {
		return botonVolver;
	}
	
	public Scene getScene() {
		return resPalabrasScene;
	}
	
	public void fillTable(TableView<Palabra> table) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(URI.create(
				Utilities.getBaseURL() + "/Resultado/obtenerpalabras.php")).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		setupTable(table,response.body());
	}
	
	public void fillTableId(TableView<Palabra> table) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(URI.create(
				Utilities.getBaseURL() + "/Resultado/obtenerpalabras.php?idusuario=" + userID)).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		setupTable(table,response.body());
	}
	
	public void setupTable(TableView<Palabra> table, String respuesta) {
		System.out.println(respuesta);
		int index1 = 0;
		int index2 = respuesta.indexOf("|");
		String id, idusuario, palabra1,palabra2,palabra3,status,fechaaccesible, fecharesultado,puntos;
		ObservableList<Palabra> data = FXCollections.observableArrayList();
		while (index2 != -1) {
			id = respuesta.substring(index1,index2);
			index1 = index2+1;
			index2 = respuesta.indexOf("|",index1);
			idusuario = respuesta.substring(index1,index2);
			index1 = index2+1;
			index2 = respuesta.indexOf("|",index1);
			palabra1 = respuesta.substring(index1,index2);
			index1 = index2+1;
			index2 = respuesta.indexOf("|",index1);
			palabra2 = respuesta.substring(index1,index2);
			index1 = index2+1;
			index2 = respuesta.indexOf("|",index1);
			palabra3 = respuesta.substring(index1,index2);
			index1 = index2+1;
			index2 = respuesta.indexOf("|",index1);
			status = respuesta.substring(index1,index2);
			index1 = index2+1;
			index2 = respuesta.indexOf("|",index1);
			fechaaccesible = respuesta.substring(index1,index2);
			index1 = index2+1;
			index2 = respuesta.indexOf("|",index1);
			fecharesultado = respuesta.substring(index1,index2);
			index1 = index2+1;
			index2 = respuesta.indexOf("%",index1);
			puntos = respuesta.substring(index1,index2);
			index1 = index2+1;
			index2 = respuesta.indexOf("|",index1);
			data.add( new Palabra(id,idusuario,palabra1,palabra2,palabra3,status,fechaaccesible,fecharesultado,puntos));
		}
		
		table.setItems(data);
	}
	
	public void buscar(TableView<Palabra> table) throws IOException, InterruptedException {
		String url = Utilities.getBaseURL() + "/Resultado/obtenerpalabras.php";
		boolean search = false;
		if (mode) {
			if (fieldId.getText() != "") {
				if (!search) {
					url = url + "?";
					search = true;
				}
				url = url + "id="+Utilities.stringToUTF(fieldId.getText());
			}
			if (fieldIdUsuario.getText() != "") {
				if (!search) {
					url = url + "?";
					search = true;
				}
				else {
					url = url + "&";
				}
				url = url + "idusuario="+Utilities.stringToUTF(fieldIdUsuario.getText());
			}
		}
		else {
			search = true;
			url = url + "?idusuario="+Utilities.stringToUTF(userID);
		}
		if (fieldPalabra1.getText() != "") {
			if (!search) {
				url = url + "?";
				search = true;
			}
			else {
				url = url + "&";
			}
			url = url + "palabra1="+Utilities.stringToUTF(fieldPalabra1.getText());
		}
		
		if (fieldPalabra2.getText() != "") {
			if (!search) {
				url = url + "?";
				search = true;
			}
			else {
				url = url + "&";
			}
			url = url + "palabra2="+Utilities.stringToUTF(fieldPalabra2.getText());
		}
		
		if (fieldPalabra3.getText() != "") {
			if (!search) {
				url = url + "?";
				search = true;
			}
			else {
				url = url + "&";
			}
			url = url + "palabra3="+Utilities.stringToUTF(fieldPalabra3.getText());
		}
		
		if (fieldStatus.getText() != "") {
			if (!search) {
				url = url + "?";
				search = true;
			}
			else {
				url = url + "&";
			}
			if (fieldStatus.getText().equals("Sin responder")) {
				url = url + "status=0";
			}
			else if (fieldStatus.getText().equals("Completado")) {
				url = url + "status=1";
			}
			else {
				url = url + "status="+Utilities.stringToUTF(fieldStatus.getText());
			}
		}
		
		if (fieldFechaAcc.getValue() != null) {
			if (!search) {
				url = url + "?";
				search = true;
			}
			else {
				url = url + "&";
			}
			url = url + "fechaaccesible="+Utilities.stringToUTF(fieldFechaAcc.getValue().toString());
		}
		
		if (fieldFechaRes.getValue() != null) {
			if (!search) {
				url = url + "?";
				search = true;
			}
			else {
				url = url + "&";
			}
			url = url + "fecharespuesta="+Utilities.stringToUTF(fieldFechaRes.getValue().toString());
		}
		
		if (fieldPuntos.getText() != "") {
			if (!search) {
				url = url + "?";
				search = true;
			}
			else {
				url = url + "&";
			}
			url = url + "puntos="+Utilities.stringToUTF(fieldPuntos.getText());
		}
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(URI.create(url)).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		setupTable(table,response.body());
	}
	
	private void ventanaGrafica(TableView<Palabra> table) {
		try {
			fillTableId(table);
		} catch (IOException e) {
			AlertBox.display("Error", "Error conectando con el servidor, intente de nuevo mas tarde");
			e.printStackTrace();
			return;
		} catch (InterruptedException e) {
			AlertBox.display("Error", "Conexion interrumpida, intente de nuevo mas tarde");
			e.printStackTrace();
			return;
		}
		
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Grafica de resultados: Memorizar palabras");
		window.setMinWidth(350);
		window.setResizable(false);
		
		Font fontTitulo = Font.font("Courier New",FontWeight.BOLD,22);
		Font fontTexto = Font.font("Courier New",FontWeight.NORMAL,16);
		Label label = new Label();
		label.setText("Grafica de resultados: Memorizar palabras");
		label.setFont(fontTitulo);
		
		final NumberAxis xAxis  = new NumberAxis();
		final NumberAxis yAxis  = new NumberAxis(0,3.5,1);
		xAxis.setLabel("Dias");
		yAxis.setLabel("Puntaje");
		
		final LineChart<Number,Number> lineChart = 
	                new LineChart<Number,Number>(xAxis,yAxis);
		
		lineChart.setTitle("Grafica de resultados: Memorizar palabras");
        //defining a series
        XYChart.Series<Number,Number> series = new XYChart.Series<Number,Number>();
        series.setName("Puntaje con el paso del tiempo");
        //populating the series with data
        for (int i = 0; i < table.getItems().size(); i++) {
        	if (calcularTiempo(table,i) > -1)
        		series.getData().add(new XYChart.Data<Number,Number>(calcularTiempo(table,i),Integer.parseInt(table.getItems().get(i).getPuntos())));
        }
        
        SLR slr = new SLR(series);
        slr.printRegEquation();
        
        Label slrLabel = new Label();
        slrLabel.setFont(fontTexto);
        String textSLR = "Su desempeño parece ";
        
        if (series.getData().size()<=2) {
        	textSLR = "Le recomendamos hacer mas tests antes de realizar conclusiones";
        }
        else {
	        if (slr.getBeta1() < 0) {
	        	textSLR = textSLR + "disminuir negativamente con el paso del tiempo";
	        }
	        else {
	        	textSLR = textSLR + "aumentar positivamente con el paso del tiempo";
	        }
        }
        slrLabel.setText(textSLR);
        
        Label sucursal = new Label();
        sucursal.setFont(fontTexto);
        sucursal.setText("Si considera necesario asistir a un centro de atencion:");
        
        Label sucursal2 = new Label();
        sucursal2.setFont(fontTexto);
        sucursal2.setText("Le invitamos a visitar la ventana de sucursales en la seleccion de tests");
        sucursal2.setWrapText(true);
        
        Label edadLabel = new Label();
        edadLabel.setFont(fontTexto);
        try {
			int edad = Utilities.calcularEdad(userID);
			if (edad < 40) {
				edadLabel.setText("Considere que por su edad, es menos probable que tenga Alzheimer");
			}
		} catch (IOException e) {
			AlertBox.display("Error", "Error conectando con el servidor, intente de nuevo mas tarde");
			e.printStackTrace();
			return;
		} catch (InterruptedException e) {
			AlertBox.display("Error", "Conexion interrumpida, intente de nuevo mas tarde");
			e.printStackTrace();
			return;
		} catch (ParseException e) {
			AlertBox.display("Error", "Hubo un error, intente de nuevo mas tarde");
			e.printStackTrace();
			return;
		}
        
        
        Button botonClose = new Button();
        botonClose.setText("Cerrar ventana");
        botonClose.setFont(fontTexto);
        botonClose.setOnAction(e-> window.close());
        botonClose.setStyle(style);
		
		VBox layout = new VBox();
		layout.setSpacing(10);
		layout.setPadding(new Insets(10,15,10,15));
		BackgroundFill backgroundFill =
		        new BackgroundFill(
		                Color.valueOf("#deaff0"),
		                new CornerRadii(10),
		                new Insets(10)
		                );

		Background background =
		        new Background(backgroundFill);
		layout.setBackground(background);
		//layout.setAlignment(Pos.BASELINE_CENTER);
		layout.getChildren().addAll(label,lineChart, slrLabel,sucursal,sucursal2,edadLabel,botonClose);
		lineChart.getData().add(series);
		
		
		Scene scene = new Scene(layout,850,650);
		window.setScene(scene);
		window.showAndWait();
		
		return;
	}
	
	private float calcularTiempo(TableView<Palabra> table, int i) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		if (table.getItems().get(i).getStatus().equals("Sin responder")) {
			return -21;
		}
		LocalDate basedate = LocalDate.parse(table.getItems().get(0).getFechaRes(), formatter);
		LocalDate date = LocalDate.parse(table.getItems().get(i).getFechaRes(), formatter);
		return ChronoUnit.DAYS.between(basedate, date)+i*0.1f;
	}
}
