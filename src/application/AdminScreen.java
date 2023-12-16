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

public class AdminScreen {
	private Scene adminScene;
	private Button botonCS,botonSucursales,botonAddAdmin,botonResultados;
	public AdminScreen(String userId) {
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
		
		Label lblTitulo = new Label ("Menu de administrador");
		lblTitulo.setFont(fontTitulo);
		
		botonSucursales = new Button();
		botonSucursales.setText("Administrar sucursales");
		botonSucursales.setMinWidth(320);
		botonSucursales.setFont(fontTexto);
		
		botonResultados = new Button();
		botonResultados.setText("Ver resultados de usuarios");
		botonResultados.setMinWidth(320);
		botonResultados.setFont(fontTexto);
		
		botonAddAdmin = new Button();
		botonAddAdmin.setText("Administrar usuarios");
		botonAddAdmin.setMinWidth(320);
		botonAddAdmin.setFont(fontTexto);
		
		botonCS = new Button();
		botonCS.setText("Cerrar sesion");
		botonCS.setMinWidth(320);
		botonCS.setFont(fontTexto);
		
		center.getChildren().addAll(lblTitulo,botonSucursales,botonResultados,botonAddAdmin,botonCS);
		
		Button botonConfig = new Button();
		Image img = new Image("config.png");
		ImageView view = new ImageView(img);
		view.setFitHeight(35);
		view.setPreserveRatio(true);
		botonConfig.setGraphic(view);
		botonConfig.setOnAction(e-> openConfig(userId));
		
		right.getChildren().add(botonConfig);
		right.getStylesheets().add(getClass().getResource("buttonConf.css").toExternalForm());
		
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
		adminScene = new Scene(layout,Utilities.windowWidth,Utilities.windowHeight);
		adminScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
	}
	
	public Button getBotonSucursales() {
		return botonSucursales;
	}
	
	public Button getBotonResultados() {
		return botonResultados;
	}
	
	public Button getBotonAddAdmin() {
		return botonAddAdmin;
	}
	
	public Button getBotonCS() {
		return botonCS;
	}
	
	public Scene getScene() {
		return adminScene;
	}
	
	private void openConfig(String userId) {
		try {
			UserConfigScreen.startConfig(userId);
		} catch (IOException e) {
			AlertBox.display("Error", "Hubo un error al conectar con el servidor");
			e.printStackTrace();
		} catch (InterruptedException e) {
			AlertBox.display("Error", "Conexion interrumpida");
			e.printStackTrace();
		}
	}
}
