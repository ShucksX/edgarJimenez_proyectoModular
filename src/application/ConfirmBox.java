package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {
	
	static boolean answer;
	
	public static boolean display (String title, String messsage) {
		Stage window = new Stage();
		
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(350);
		window.setResizable(false);
		window.setOnCloseRequest(event -> {
		    answer = false;
		});
		
		Font fontTexto = Font.font("Courier New",FontWeight.NORMAL,16);
		Label label = new Label();
		label.setText(messsage);
		label.setFont(fontTexto);
		
		Button yesButton = new Button("Si");
		yesButton.setFont(fontTexto);
		Button noButton = new Button("No");
		noButton.setFont(fontTexto);
		
		yesButton.setOnAction(e->{
			answer = true;
			window.close();
		});
		
		noButton.setOnAction(e->{
			answer = false;
			window.close();
		});
		
		GridPane.setConstraints(label, 0, 0);
		GridPane.setConstraints(yesButton, 0, 1);
		GridPane.setConstraints(noButton, 1, 1);
		
		GridPane layout = new GridPane();
		layout.setHgap(10);
		layout.setVgap(20);
		layout.getChildren().addAll(label,yesButton,noButton);
		layout.setAlignment(Pos.CENTER);
		layout.setPadding(new Insets(10,10,10,10));

		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		
		
		
		return answer;
	}
}
