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
import javafx.scene.control.Label;
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

public class AddCenter {
	static boolean answer;
	static TextField fieldId, fieldNombre, fieldPais,fieldMunicipio,fieldLocalidad,fieldDireccion,fieldNumTel,fieldCorreo;
	private static String style = "-fx-border-color: linear-gradient(#f77777, #e67777);-fx-border-radius: 30; -fx-border-width: 2px;-fx-background-color: linear-gradient(#bddbb6, #b2c7ad);-fx-background-radius: 30;";
	public static boolean display () {
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Agregar nuevo centro");
		window.setMinWidth(350);
		window.setResizable(false);
		
		Font fontTitulo = Font.font("Courier New",FontWeight.BOLD,22);
		Font fontTexto = Font.font("Courier New",FontWeight.NORMAL,16);
		Label label = new Label();
		label.setText("Agregar nuevo centro");
		label.setFont(fontTitulo);
		
		fieldNombre = new TextField();
		fieldNombre.setPromptText("Nombre");
		fieldNombre.setFont(fontTexto);
		fieldPais = new TextField();
		fieldPais.setPromptText("País");
		fieldPais.setFont(fontTexto);
		fieldMunicipio = new TextField();
		fieldMunicipio.setPromptText("Municipio");
		fieldMunicipio.setFont(fontTexto);
		fieldLocalidad = new TextField();
		fieldLocalidad.setPromptText("Localidad");
		fieldLocalidad.setFont(fontTexto);
		fieldDireccion = new TextField();
		fieldDireccion.setPromptText("Dirección");
		fieldDireccion.setFont(fontTexto);
		fieldNumTel = new TextField();
		fieldNumTel.setPromptText("Numero de teléfono");
		fieldNumTel.setFont(fontTexto);
		fieldCorreo = new TextField();
		fieldCorreo.setPromptText("Correo");
		fieldCorreo.setFont(fontTexto);
		
		Button addButton = new Button("Agregar nuevo centro");
		addButton.setFont(fontTexto);
		addButton.setStyle(style);
		Button cancelButton = new Button("Cancelar");
		cancelButton.setFont(fontTexto);
		cancelButton.setStyle(style);
		
		addButton.setOnAction(e->{
			try {
				answer = addCenterDB();
				if (answer) {
					window.close();
				}
			} catch (IOException e1) {
				AlertBox.display("Error", "Error al conectar con el servidor");
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				AlertBox.display("Error", "Hubo una interrupción en la conexión");
				e1.printStackTrace();
			}
		});
		
		cancelButton.setOnAction(e->{
			answer = false;
			window.close();
		});
		
		
		VBox layout = new VBox();
		layout.setSpacing(10);
		layout.setPadding(new Insets(10,20,10,20));
		layout.setAlignment(Pos.TOP_CENTER);
		layout.getChildren().addAll(label,fieldNombre,fieldPais,fieldMunicipio,
				fieldLocalidad,fieldDireccion,fieldNumTel,fieldCorreo,addButton,cancelButton);
		BackgroundFill backgroundFill =
		        new BackgroundFill(
		                Color.valueOf("#fabebe"),
		                new CornerRadii(10),
		                new Insets(10)
		                );

		Background background =
		        new Background(backgroundFill);
		layout.setBackground(background);

		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		
		return answer;
	}
	
	private static boolean addCenterDB() throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(URI.create
				(Utilities.getBaseURL() + "/Centros/registrarcentro.php?nombre="+Utilities.stringToUTF(fieldNombre.getText())+
						"&pais="+Utilities.stringToUTF(fieldPais.getText())+"&municipio="+Utilities.stringToUTF(fieldMunicipio.getText())+"&localidad="+Utilities.stringToUTF(fieldLocalidad.getText())+
						"&direccion="+Utilities.stringToUTF(fieldDireccion.getText())+"&numtel="+Utilities.stringToUTF(fieldNumTel.getText())+
						"&correo="+Utilities.stringToUTF(fieldCorreo.getText()))).GET().build();
		
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		System.out.println(response.body());
		if (response.body().contains("exito")) {
			AlertBox.display("Exito", "Se registró el centro con éxito");
			return true; 
		}
		else {
			AlertBox.display("Error", response.body());
			return false;
		}
	}
}

