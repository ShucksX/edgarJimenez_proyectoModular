package application;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
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
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class ResColores {
	private Scene resColoresScene;
	private Button botonVolver;
	private TextField fieldId, fieldIdUsuario, fieldPuntos;
	private DatePicker fieldFechaRes;
	private boolean mode;
	private String userID;
	private String style = "-fx-border-color: linear-gradient(#f77777, #e67777);-fx-border-radius: 30; -fx-border-width: 2px;-fx-background-color: linear-gradient(#bddbb6, #b2c7ad);-fx-background-radius: 30;";
	public ResColores(Scene returnScene, Stage primaryStage, boolean mode, String userID) {
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
		
		Label lblTitulo = new Label ("Resultados: Colores intermitentes");
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
		botonVolver.setText("Volver a menú de resultados");
		botonVolver.setMinWidth(minwidth);
		botonVolver.setFont(fontTexto);
		botonVolver.setOnAction(e-> primaryStage.setScene(returnScene));
		
		TableView <LSData> table = new TableView <>();
		TableColumn<LSData, String> id = new TableColumn <> ("ID");
		id.setCellValueFactory(cellData -> cellData.getValue().idProperty());
		TableColumn <LSData, String> idusuario = new TableColumn <> ("ID de usuario");
		idusuario.setCellValueFactory(cellData -> cellData.getValue().idUsuarioProperty());
		TableColumn<LSData, String> puntos = new TableColumn <> ("Aciertos sobre 5");
		puntos.setCellValueFactory(cellData -> cellData.getValue().tiempoProperty());
		TableColumn<LSData, String> fecharesultado = new TableColumn <> ("Fecha del resultado");
		fecharesultado.setCellValueFactory(cellData -> cellData.getValue().fechaResProperty());
		if (mode) {
			table.getColumns().add(id);
			table.getColumns().add(idusuario);
		}
		table.getColumns().add(puntos);
		table.getColumns().add(fecharesultado);
		try {
			if (mode)
				fillTable(table);
			else
				fillTableId(table);
		} catch (IOException e) {
			AlertBox.display("Error.", "La conexión al servidor se interrumpió.");
			e.printStackTrace();
		} catch (InterruptedException e) {
			AlertBox.display("Error.", "La conexión al servidor se interrumpió.");
			e.printStackTrace();
		}
		table.setMinWidth(600);
		table.setMinHeight(600);
		
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
		Label txtPuntos= new Label ("Aciertos sobre 5:");
		txtPuntos.setFont(fontTexto);
		fieldPuntos = new TextField();
		fieldPuntos.setPromptText("Aciertos sobre 5");
		fieldPuntos.setPadding(inset);
		fieldPuntos.setFont(fontTexto);
		Label txtfechaRes = new Label ("Fecha del resultado:");
		txtfechaRes.setFont(fontTexto);
		fieldFechaRes = new DatePicker();
		fieldFechaRes.setPromptText("Fecha del resultado");
		fieldFechaRes.setPadding(inset);
		
		Button btnDelResultado = new Button();
		btnDelResultado.setText("Eliminar resultado");
		btnDelResultado.setMinWidth(300);
		btnDelResultado.setFont(fontTexto);
		btnDelResultado.setOnAction(e->{
			try {
				delResultado(table.getSelectionModel().getSelectedItem(),primaryStage,returnScene);
			} catch (IOException e1) {
				AlertBox.display("Error.", "La conexión al servidor se interrumpió.");
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				AlertBox.display("Error.", "La conexión al servidor se interrumpió.");
				e1.printStackTrace();
			}
		});
		
		Button btnBuscar = new Button();
		btnBuscar.setText("Buscar");
		btnBuscar.setOnAction(e-> {
			try {
				buscar(table);
			} catch (IOException e1) {
				AlertBox.display("Error.", "La conexión al servidor se interrumpió.");
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				AlertBox.display("Error.", "La conexión al servidor se interrumpió.");
				e1.printStackTrace();
			}
		});
		
		top.getChildren().add(lblTitulo);
		if(mode) {
			left.getChildren().add(btnDelResultado);
		}
		else {
			left.getChildren().add(botonGrafica);
		}
		left.getChildren().add(botonVolver);
		center.getChildren().add(table);
		if (mode) {
			right.getChildren().addAll(txtId,fieldId,txtIdUsuario,fieldIdUsuario);
		}
		right.getChildren().addAll(txtPuntos,fieldPuntos,txtfechaRes,fieldFechaRes,btnBuscar);
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
		resColoresScene = new Scene(layout,Utilities.windowWidth,Utilities.windowHeight);
		resColoresScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	}
	
	public Button getBotonVolver() {
		return botonVolver;
	}
	
	public Scene getScene() {
		return resColoresScene;
	}
	
	public void fillTable(TableView<LSData> table) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(URI.create(
				Utilities.getBaseURL() + "/Resultado/obtenercolores.php")).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		setupTable(table,response.body());
	}
	
	public void fillTableId(TableView<LSData> table) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(URI.create(
				Utilities.getBaseURL() + "/Resultado/obtenercolores.php?idusuario=" + userID)).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		setupTable(table,response.body());
	}
	
	public void setupTable(TableView<LSData> table, String respuesta) {
		System.out.println(respuesta);
		int index1 = 0;
		int index2 = respuesta.indexOf("|");
		String id, idusuario, puntos, fecharesultado;
		ObservableList<LSData> data = FXCollections.observableArrayList();
		while (index2 != -1) {
			id = respuesta.substring(index1,index2);
			index1 = index2+1;
			index2 = respuesta.indexOf("|",index1);
			idusuario = respuesta.substring(index1,index2);
			index1 = index2+1;
			index2 = respuesta.indexOf("|",index1);
			puntos = respuesta.substring(index1,index2);
			index1 = index2+1;
			index2 = respuesta.indexOf("%",index1);
			fecharesultado = respuesta.substring(index1,index2);
			index1 = index2+1;
			index2 = respuesta.indexOf("|",index1);
			data.add( new LSData(id,idusuario,puntos,fecharesultado));
		}
		
		table.setItems(data);
	}
	
	public void buscar(TableView<LSData> table) throws IOException, InterruptedException {
		String url = Utilities.getBaseURL() + "/Resultado/obtenercolores.php";
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
			AlertBox.display("Error.", "Error conectando con el servidor, intente de nuevo mas tarde.");
			e.printStackTrace();
			return;
		} catch (InterruptedException e) {
			AlertBox.display("Error.", "Conexion interrumpida, intente de nuevo mas tarde.");
			e.printStackTrace();
			return;
		}
		
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Grafica de resultados: Colores intermitentes");
		window.setMinWidth(350);
		window.setResizable(false);
		
		Font fontTitulo = Font.font("Courier New",FontWeight.BOLD,22);
		Font fontTexto = Font.font("Courier New",FontWeight.NORMAL,16);
		Label label = new Label();
		label.setText("Grafica de resultados: Colores intermitentes");
		label.setFont(fontTitulo);
		
		final NumberAxis xAxis  = new NumberAxis();
		final NumberAxis yAxis  = new NumberAxis(0,5.5,1);
		xAxis.setLabel("Dias");
		yAxis.setLabel("Puntaje");
		
		final LineChart<Number,Number> lineChart = 
	                new LineChart<Number,Number>(xAxis,yAxis);
		
		lineChart.setTitle("Grafica de resultados: Colores intermitentes");
        //defining a series
        XYChart.Series<Number,Number> series = new XYChart.Series<Number,Number>();
        series.setName("Puntaje con el paso del tiempo");
        //populating the series with data
        for (int i = 0; i < table.getItems().size(); i++)
        series.getData().add(new XYChart.Data<Number,Number>(calcularTiempo(table,i),Integer.parseInt(table.getItems().get(i).getTiempo())));
        
        SLR slr = new SLR(series);
        slr.printRegEquation();
        
        Label slrLabel = new Label();
        slrLabel.setFont(fontTexto);
        String textSLR = "Su desempeño parece ";
        
        if (series.getData().size()<=2) {
        	textSLR = "Le recomendamos hacer más ejercicios antes de realizar conclusiones";
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
        sucursal.setText("Si requiere contactar a un centro de atención:");
        
        Label sucursal2 = new Label();
        sucursal2.setFont(fontTexto);
        sucursal2.setText("Le invitamos a visitar la ventana de sucursales en la selección de ejercicios");
        sucursal2.setWrapText(true);
        
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
		layout.getChildren().addAll(label,lineChart, slrLabel,sucursal,sucursal2,botonClose);
		lineChart.getData().add(series);
		
		Scene scene = new Scene(layout,850,650);
		window.setScene(scene);
		window.showAndWait();
		
		return;
	}
	
	private void delResultado(LSData resultado, Stage primaryStage, Scene returnScene) throws IOException, InterruptedException {
		if(resultado == null) {
			AlertBox.display("Alerta.", "Seleccione un resultado primero.");
		}
		else {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder(URI.create(
					Utilities.getBaseURL() + "/Resultado/reseliminarcolores.php?id="+ Utilities.stringToUTF(resultado.getID()))).GET().build();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			System.out.println(response.body());
			if(response.body().contains("Exito#")) {
				AlertBox.display("Éxito", "Se eliminó el resultado con éxito.");
				primaryStage.setScene(returnScene);
			}
			else {
				AlertBox.display("Error.", "Hubo un error al eliminar el resultado.");
			}
		}
	}
	
	private float calcularTiempo(TableView<LSData> table, int i) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate basedate = LocalDate.parse(table.getItems().get(0).getFechaRes(), formatter);
		LocalDate date = LocalDate.parse(table.getItems().get(i).getFechaRes(), formatter);
		return ChronoUnit.DAYS.between(basedate, date)+i*0.1f;
	}
	
}
