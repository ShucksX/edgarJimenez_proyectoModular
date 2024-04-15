package application;

import java.io.IOException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ClienteScreen {
	private Scene clienteScene;
	private Button botonCS,botonLaberinto,/*botonMemoPalabras,*/botonSiluetas,botonSopa,botonColores, botonResultado,botonChat, botonSucursales, botonContactos;
	public ClienteScreen(String userId, boolean modoNC) {
		BorderPane layout = new BorderPane();
		
		VBox center = new VBox();
		center.setSpacing(10);
		center.setPadding(new Insets(10,10,10,50));
		center.setAlignment(Pos.TOP_CENTER);
		VBox right = new VBox();
		right.setSpacing(1);
		right.setPadding(new Insets(10,15,1,1));
		right.setAlignment(Pos.BASELINE_CENTER);
		//Add components
		Font fontTitulo = Font.font("Courier New",FontWeight.BOLD,22);
		Font fontTexto = Font.font("Courier New",FontWeight.NORMAL,16);
		
		Label lblTitulo = new Label ("Seleccion de ejercicios");
		lblTitulo.setFont(fontTitulo);
		
		int minwidth = 300;
		botonLaberinto = new Button();
		botonLaberinto.setText("Laberinto");
		botonLaberinto.setMinWidth(minwidth);
		botonLaberinto.setFont(fontTexto);
		
		/*
		botonMemoPalabras = new Button();
		botonMemoPalabras.setText("Memorizar palabras");
		botonMemoPalabras.setMinWidth(minwidth);
		botonMemoPalabras.setFont(fontTexto);
		*/
		botonSiluetas = new Button();
		botonSiluetas.setText("Siluetas");
		botonSiluetas.setMinWidth(minwidth);
		botonSiluetas.setFont(fontTexto);
		
		botonSopa = new Button();
		botonSopa.setText("Sopa de letras");
		botonSopa.setMinWidth(minwidth);
		botonSopa.setFont(fontTexto);
		
		botonColores = new Button();
		botonColores.setText("Colores intermitentes");
		botonColores.setMinWidth(minwidth);
		botonColores.setFont(fontTexto);
		
		botonResultado = new Button();
		botonResultado.setText("Ver resultados");
		botonResultado.setMinWidth(minwidth);
		botonResultado.setFont(fontTexto);
		
		botonContactos = new Button();
		botonContactos.setText("Ver tus contactos");
		botonContactos.setMinWidth(minwidth);
		botonContactos.setFont(fontTexto);
		
		botonSucursales = new Button();
		botonSucursales.setText("Ver centros de atencion");
		botonSucursales.setMinWidth(minwidth);
		botonSucursales.setFont(fontTexto);
		
		botonChat = new Button();
		botonChat.setText("Utiliza chatbot si tienes dudas");
		botonChat.setMinWidth(320);
		botonChat.setFont(fontTexto);
		botonChat.setOnAction(e-> changeSceneChat());
		
		botonCS = new Button();
		botonCS.setText("Cerrar sesion");
		botonCS.setMinWidth(minwidth);
		botonCS.setFont(fontTexto);
		
		center.getChildren().addAll(lblTitulo,botonLaberinto,botonSiluetas,botonSopa,
				botonColores,botonResultado,botonContactos,botonSucursales,botonChat,botonCS);
		center.setPadding(new Insets(20,0,0,0));
		
		Button botonConfig = new Button();
		Image img = new Image("config.png");
		ImageView view = new ImageView(img);
		view.setFitHeight(35);
		view.setPreserveRatio(true);
		botonConfig.setGraphic(view);
		botonConfig.setOnAction(e-> {
			if(modoNC) {
				AlertBox.display("Alerta.", "La configuración de usuario no está disponible en modo sin conexión.");
			}
			else {
				openConfig(userId);
			}
			});
		
		right.getChildren().add(botonConfig);
		right.getStylesheets().add(getClass().getResource("buttonConf.css").toExternalForm());
		
		layout.setCenter(center);
		layout.setRight(right);
		BackgroundFill backgroundFill =
		        new BackgroundFill(
		                Color.valueOf("#b6d8f2"),
		                new CornerRadii(10),
		                new Insets(10)
		                );

		Background background =
		        new Background(backgroundFill);
		layout.setBackground(background);
		
		//Set Scene
		clienteScene = new Scene(layout,Utilities.windowWidth,Utilities.windowHeight);
		clienteScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
	}
	
	public Button getBotonCS() {
		return botonCS;
	}
	public Button getBotonLaberinto() {
		return botonLaberinto;
	}
	/*public Button getBotonMemoPalabras() {
		return botonMemoPalabras;
	}*/
	public Button getBotonSiluetas() {
		return botonSiluetas;
	}
	public Button getBotonSopa() {
		return botonSopa;
	}
	public Button getBotonColores() {
		return botonColores;
	}
	public Button getBotonResultado() {
		return botonResultado;
	}
	
	public Button getBotonContactos() {
		return botonContactos;
	}
	
	public Button getBotonSucursales() {
		return botonSucursales;
	}
	
	public Scene getScene() {
		return clienteScene;
	}
	
	public void changeSceneChat() {
		Stage window = new Stage();
		
		window.setTitle("Chatbot para dudas");
		window.setMinWidth(350);
		window.setResizable(true);
		
		ChatBotScreen sceneChatBot = new ChatBotScreen();
		
		window.setScene(sceneChatBot.getScene());
		window.show();
	}
	
	private void openConfig(String userId) {
		try {
			UserConfigScreen.startConfig(userId);
		} catch (IOException e) {
			AlertBox.display("Error.", "Hubo un error al conectar con el servidor.");
			e.printStackTrace();
		} catch (InterruptedException e) {
			AlertBox.display("Error.", "Conexion interrumpida.");
			e.printStackTrace();
		}
	}
	
}
