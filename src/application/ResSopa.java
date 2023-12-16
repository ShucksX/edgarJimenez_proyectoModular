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

public class ResSopa {
	private Scene resSopaScene;
	private Button botonVolver;
	private TextField fieldId, fieldIdUsuario, fieldTiempo;
	private DatePicker fieldFechaRes;
	private boolean mode;
	private String userID;
	private String style = "-fx-border-color: linear-gradient(#f77777, #e67777);-fx-border-radius: 30; -fx-border-width: 2px;-fx-background-color: linear-gradient(#bddbb6, #b2c7ad);-fx-background-radius: 30;";
	public ResSopa(Scene returnScene, Stage primaryStage, boolean mode, String userID) {
		this.userID = userID;
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
		
		Label lblTitulo = new Label ("Resultados: Sopa de letras");
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
		
		TableView <LSData> table = new TableView <>();
		TableColumn<LSData, String> id = new TableColumn <> ("ID");
		id.setCellValueFactory(cellData -> cellData.getValue().idProperty());
		TableColumn <LSData, String> idusuario = new TableColumn <> ("ID de usuario");
		idusuario.setCellValueFactory(cellData -> cellData.getValue().idUsuarioProperty());
		TableColumn<LSData, String> tiempo = new TableColumn <> ("Tiempo tardado");
		tiempo.setCellValueFactory(cellData -> cellData.getValue().tiempoProperty());
		TableColumn<LSData, String> fecharesultado = new TableColumn <> ("Fecha del resultado");
		fecharesultado.setCellValueFactory(cellData -> cellData.getValue().fechaResProperty());
		if (mode) {
			table.getColumns().add(id);
			table.getColumns().add(idusuario);
		}
		table.getColumns().add(tiempo);
		table.getColumns().add(fecharesultado);
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
		
		botonGrafica.setOnAction(e->ventanaGrafica(table));
		
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
		Label txtTiempo= new Label ("Tiempo tardado:");
		txtTiempo.setFont(fontTexto);
		fieldTiempo = new TextField();
		fieldTiempo.setPromptText("Tiempo tardado");
		fieldTiempo.setPadding(inset);
		fieldTiempo.setFont(fontTexto);
		Label txtfechaRes = new Label ("Fecha del resultado:");
		txtfechaRes.setFont(fontTexto);
		fieldFechaRes = new DatePicker();
		fieldFechaRes.setPromptText("Fecha del resultado");
		fieldFechaRes.setPadding(inset);
		
		
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
		right.getChildren().addAll(txtTiempo,fieldTiempo,txtfechaRes,fieldFechaRes,btnBuscar);
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
		resSopaScene = new Scene(layout,Utilities.windowWidth,Utilities.windowHeight);
		resSopaScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	}
	
	public Button getBotonVolver() {
		return botonVolver;
	}
	
	public Scene getScene() {
		return resSopaScene;
	}
	
	public void fillTable(TableView<LSData> table) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(URI.create(
				Utilities.getBaseURL() + "/Resultado/obtenersopas.php")).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		setupTable(table,response.body());
	}
	
	public void fillTableId(TableView<LSData> table) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(URI.create(
				Utilities.getBaseURL() + "/Resultado/obtenersopas.php?idusuario=" + userID)).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		setupTable(table,response.body());
	}
	
	public void setupTable(TableView<LSData> table, String respuesta) {
		System.out.println(respuesta);
		int index1 = 0;
		int index2 = respuesta.indexOf("|");
		String id, idusuario, tiempo, fecharesultado;
		ObservableList<LSData> data = FXCollections.observableArrayList();
		while (index2 != -1) {
			id = respuesta.substring(index1,index2);
			index1 = index2+1;
			index2 = respuesta.indexOf("|",index1);
			idusuario = respuesta.substring(index1,index2);
			index1 = index2+1;
			index2 = respuesta.indexOf("|",index1);
			tiempo = respuesta.substring(index1,index2);
			index1 = index2+1;
			index2 = respuesta.indexOf("%",index1);
			fecharesultado = respuesta.substring(index1,index2);
			index1 = index2+1;
			index2 = respuesta.indexOf("|",index1);
			data.add( new LSData(id,idusuario,tiempo,fecharesultado));
		}
		
		table.setItems(data);
	}
	
	public void buscar(TableView<LSData> table) throws IOException, InterruptedException {
		String url = Utilities.getBaseURL() + "/Resultado/obtenersopas.php";
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
		if (fieldTiempo.getText() != "") {
			if (!search) {
				url = url + "?";
				search = true;
			}
			else {
				url = url + "&";
			}
			url = url + "tiempo="+Utilities.stringToUTF(fieldTiempo.getText());
		}
		
		if (fieldFechaRes.getValue() != null) {
			if (!search) {
				url = url + "?";
				search = true;
			}
			else {
				url = url + "&";
			}
			url = url + "fecharesultado="+Utilities.stringToUTF(fieldFechaRes.getValue().toString());
		}
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(URI.create(url)).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		setupTable(table,response.body());
	}
	
	private void ventanaGrafica(TableView<LSData> table) {
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
		window.setTitle("Grafica de resultados: Sopa de letras");
		window.setMinWidth(350);
		window.setResizable(false);
		
		Font fontTitulo = Font.font("Courier New",FontWeight.BOLD,22);
		Font fontTexto = Font.font("Courier New",FontWeight.NORMAL,16);
		Label label = new Label();
		label.setText("Grafica de resultados: Sopa de letras");
		label.setFont(fontTitulo);
		
		final NumberAxis xAxis  = new NumberAxis();
		final NumberAxis yAxis  = new NumberAxis();
		xAxis.setLabel("Dias");
		yAxis.setLabel("Tiempo para terminar el test");
		
		final LineChart<Number,Number> lineChart = 
	                new LineChart<Number,Number>(xAxis,yAxis);
		
		lineChart.setTitle("Grafica de resultados: Sopa de letras");
        //defining a series
        XYChart.Series<Number,Number> series = new XYChart.Series<Number,Number>();
        series.setName("Desempeño con el paso del tiempo");
        //populating the series with data
        for (int i = 0; i < table.getItems().size(); i++)
        series.getData().add(new XYChart.Data<Number,Number>(calcularTiempo(table,i),Integer.parseInt(table.getItems().get(i).getTiempo())));
        
        SLR slr = new SLR(series);
        slr.printRegEquation();
        
        Label slrLabel = new Label();
        slrLabel.setFont(fontTexto);
        String textSLR = "El tiempo que toma en cada test parece ";
        
        if (series.getData().size()<=2) {
        	textSLR = "Le recomendamos hacer mas tests antes de realizar conclusiones";
        }
        else {
	        if (slr.getBeta1() < 0) {
	        	textSLR = textSLR + "disminuir positivamente con el paso del tiempo";
	        }
	        else {
	        	textSLR = textSLR + "aumentar negativamente con el paso del tiempo";
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
	
	private float calcularTiempo(TableView<LSData> table, int i) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate basedate = LocalDate.parse(table.getItems().get(0).getFechaRes(), formatter);
		LocalDate date = LocalDate.parse(table.getItems().get(i).getFechaRes(), formatter);
		return ChronoUnit.DAYS.between(basedate, date)+i*0.1f;
	}
}
