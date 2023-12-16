package application;

import javafx.stage.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.*;

public class AlertBox {
	
	public static void display (String title, String messsage) {
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(350);
		window.setResizable(false);

		Font fontTexto = Font.font("Courier New",FontWeight.NORMAL,16);
		Label label = new Label();
		label.setText(messsage);
		label.setFont(fontTexto);
		Button closeButton = new Button("Cerrar");
		closeButton.setOnAction(e -> window.close());
		closeButton.setFont(fontTexto);
		
		VBox layout = new VBox(40);
		layout.getChildren().addAll(label,closeButton);
		layout.setAlignment(Pos.CENTER);
		layout.setPadding(new Insets(10,10,10,10));

		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}
}
