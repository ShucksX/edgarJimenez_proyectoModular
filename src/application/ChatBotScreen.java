package application;

import javafx.animation.PauseTransition;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.TextArea;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.scene.control.Label ;



public class ChatBotScreen {
	private Scene chatbotScene;
	private int cont;
	private Chatbot chatbot;
	public ChatBotScreen() {
		cont = 0;
		chatbot = new Chatbot();
		BorderPane layout = new BorderPane();
		Font fontTexto = Font.font("Courier New",FontWeight.NORMAL,16);
		
		TextField input = new TextField();
		input.setPromptText("Escribe aqui!");
		input.setFont(fontTexto);
		input.minWidth(1000);
		
		GridPane chat = new GridPane();
	    chat.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	    chat.setVgap(10);

	    ColumnConstraints c1 = new ColumnConstraints();
	    c1.setPercentWidth(100);
	    chat.getColumnConstraints().add(c1);
	    Label chatStart = new Label("Hola! Puedes preguntarme tus dudas con la barra de texto en la parte inferior.");
		chatStart.setFont(fontTexto);
        chatStart.setMinHeight(40);
		chatStart.setWrapText(true);
		chatStart.setMinWidth(90);
		chatStart.setMaxWidth(400);
        chatStart.getStyleClass().add("chat-bubble2");
        GridPane.setHalignment(chatStart, HPos.RIGHT);
        chat.addRow(cont, chatStart);
        cont++;
		
		Button enviar = new Button ();
		enviar.setMaxWidth(200);
		enviar.setText("Enviar");
		enviar.setFont(fontTexto);
		enviar.setDefaultButton(true);
		enviar.setOnAction(e-> {
			int maxwidth = 400;
			int minwidth = 90;
			Label chatMessage = new Label( input.getText());
			chatMessage.setFont(fontTexto);
			chatMessage.setMinHeight(40);
			chatMessage.setWrapText(true);
			chatMessage.setMinWidth(minwidth);
			chatMessage.setMaxWidth(maxwidth);
	        chatMessage.getStyleClass().add("chat-bubble");
	        GridPane.setHalignment(chatMessage, HPos.LEFT);
	        chat.addRow(cont, chatMessage);
	        cont++;
	        PauseTransition delay = new PauseTransition(Duration.seconds(1));
			delay.setOnFinished( event -> {
				String response = getResponse(chatMessage.getText().toLowerCase());
		        Label chatResponse = new Label(response);
				chatResponse.setFont(fontTexto);
		        chatResponse.setMinHeight(40);
				chatResponse.setWrapText(true);
				chatResponse.setMinWidth(minwidth);
				chatResponse.setMaxWidth(maxwidth);
		        chatResponse.getStyleClass().add("chat-bubble2");
		        GridPane.setHalignment(chatResponse, HPos.RIGHT);
		        chat.addRow(cont, chatResponse);
		        enviar.setDisable(false);
		        cont++;
			});
			delay.play();
	        
			enviar.setDisable(true);
			input.setText("");
			
		});
		
		HBox bottom = new HBox();
		HBox.setHgrow(input, Priority.ALWAYS);
		bottom.setPadding(new Insets(1,10,1,10));
		bottom.getChildren().addAll(input,enviar);
		
		
		ScrollPane center = new ScrollPane(chat);
	    center.setFitToWidth(true);
	    center.setPadding(new Insets(15,15,15,15));
	    BackgroundFill backgroundFill =
		        new BackgroundFill(
		                Color.valueOf("#92b9e8"),
		                new CornerRadii(10),
		                new Insets(10)
		                );

		Background background =
		        new Background(backgroundFill);
		center.setBackground(background);
		
		layout.setCenter(center);
		layout.setBottom(bottom);
		
		//Set Scene
		chatbotScene = new Scene(layout,500,800);
		chatbotScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	}
	
	private String getResponse(String input) {
		return chatbot.detectSentence(input);
	}
	
	public Scene getScene() {
		return chatbotScene;
	}
}
