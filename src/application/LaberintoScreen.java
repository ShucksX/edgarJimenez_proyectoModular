package application;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LaberintoScreen implements EventHandler<KeyEvent> {
	final int windowSize = 800;
	final int gcPadding = 100;
	private int boardSize;
	private Laberinto maze;
	private GraphicsContext gc;
    private Canvas canvas;
    private Box keyboardNode;
    private int colSelection;
    private int rowSelection;
    private int colExit;
    private int rowExit;
    private AnimationTimer timer;
    private int time;
    private Timeline timeline;
    private int mazes = 0;
	private String userID;
    
	private Scene laberintoScene,endScene;
	private Stage gameStage;
	public LaberintoScreen(Stage stage, Scene clienteScene, String userId) {
		userID  = userId;
		maze = new Laberinto();
		maze.initLaberinto();
		rowSelection = maze.getEntryRow();
		colSelection = maze.getEntryCol();
		rowExit = maze.getExitRow();
		colExit = maze.getExitCol();
		time = 0;
		gameStage = stage;
		endScene = clienteScene;
		
		VBox layout = new VBox();
		layout.setSpacing(8);
		layout.setPadding(new Insets(10,10,10,10));
		layout.setAlignment(Pos.BASELINE_CENTER);
		
		Label lblTitulo = new Label ("Laberinto");
		Font fontTitulo = Font.font("Courier New",FontWeight.BOLD,22);
		lblTitulo.setFont(fontTitulo);
		
		Font fontInstrucciones = Font.font("Courier New",FontWeight.NORMAL,16);
		Label lblInstrucciones1 = new Label ("Resuelva el laberinto llevando el punto en la parte inferior a la salida");
		lblInstrucciones1.setFont(fontInstrucciones);
		
		Label lblInstrucciones2 = new Label ("Presione las flechas para mover el cursor");
		lblInstrucciones2.setFont(fontInstrucciones);
		
		Label lblInstrucciones3 = new Label("Puede presionar la tecla de escape para abandonar el test");
		lblInstrucciones3.setFont(fontInstrucciones);
		
		boardSize = maze.getBoardSize();
		canvas = new Canvas(windowSize, windowSize);
	    gc = canvas.getGraphicsContext2D();

	    keyboardNode = new Box();
	    keyboardNode.setFocusTraversable(true);
	    keyboardNode.requestFocus();
	    keyboardNode.setOnKeyPressed(this);
	    
	    timer = new MyTimer();
		
	    
	    layout.getChildren().addAll(lblTitulo,lblInstrucciones1,lblInstrucciones2,lblInstrucciones3,canvas,keyboardNode);
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
		laberintoScene = new Scene(layout,Utilities.windowWidth,Utilities.windowHeight);
		laberintoScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	}
	
	public void startGame() {
		timer.start();
		countTime();
	}
	
	public Scene getScene() {
		return laberintoScene;
	}

	@Override
	public void handle(KeyEvent e) {

        if (e.getCode() == KeyCode.LEFT) { // If left button pressed
            if (colSelection - 1 >= 0) {
            	if(!maze.getBoardPos(rowSelection, colSelection-1)) { //If its not a wall
            		colSelection--; 
            	}        
            }
        }
        if (e.getCode() == KeyCode.RIGHT ) { // If right button pressed
            if (colSelection + 1 < boardSize ) {
            	if (!maze.getBoardPos(rowSelection, colSelection+1)) { //If its not a wall
            		colSelection++; 
            	}
            }
        }
        if (e.getCode() == KeyCode.UP ) { // If up button pressed
            if (rowSelection - 1 >= 0) { 
            	if (!maze.getBoardPos(rowSelection-1, colSelection)){ //If its not a wall
            		rowSelection--;
            	}
            }
        }
        if (e.getCode() == KeyCode.DOWN) { // If down button pressed
            if (rowSelection + 1 < boardSize) { 
            	if(!maze.getBoardPos(rowSelection+1, colSelection))//If its not a wall
                rowSelection++; 
            }
        }
        if(e.getCode() == KeyCode.ESCAPE) {
        	endGame(gameStage);
        }
    }
	
	private class MyTimer extends AnimationTimer{

		@Override
		public void handle(long arg0) {
			//gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
			run();
			checkForEnd(gameStage);
		}
		
	}
	
	private void countTime() {
		timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
		    time++;    
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}
	
	public void run() {
        gcPrintGameBoard();
        showSelection();
    }
	
	public void printGameBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
            	if (maze.getBoardPos(i, j))
					System.out.print("1");
				else
					System.out.print("0");
            }
            System.out.println();
        }
    }

    public void gcPrintGameBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
            	gc.fillText(" ", (gcPadding + (20 * j)),(20 * i));
            	if (maze.getBoardPos(i, j)) {
            		gc.setFill(Color.BLACK);
                    gc.fillRect((gcPadding + (20 * j)), (20 * i),20,20);
            	}
            	else {
            		gc.setFill(Color.WHITE);
            		gc.fillRect((gcPadding + (20 * j)), (20 * i),20,20);
            	}
            }
        }
        gc.setFill(Color.BLUE);
        gc.fillRect((gcPadding + (20 * maze.getExitCol())), (20 * maze.getExitRow()), 20, 20);
    }
    
    public void showSelection() {
        gc.save(); // Saves gc state before printing
        gc.setFill(Color.RED);
        gc.fillRect((gcPadding + (20 * colSelection)), (20 * rowSelection), 20, 20);
        gc.setFill(Color.WHITE);
        gc.fillText(" ", (gcPadding + (20 * colSelection)),
                (20 * rowSelection));
        gc.restore(); // Restores saved gc state
    }
	
	public void checkForEnd(Stage gameStage) {
        if (rowSelection == rowExit && colSelection == colExit) {
        	if (mazes >= 5)
        	{
        		timeline.stop();
            	timer.stop();
                gameStage.setScene(endScene);
            	AlertBoxNonWait.display("Resultado", "Usted tardo "+ time + " segundos.");
            	try {
					registrarResultadoBD(0);
				} catch (IOException e) {
					AlertBox.display("Error", "La conexion al servidor se interrumpio");
					e.printStackTrace();
				} catch (InterruptedException e) {
					AlertBox.display("Error", "La conexion al servidor se interrumpio");
					e.printStackTrace();
				}
        	}
        	else {
	        	maze.initLaberinto();
	    		rowSelection = maze.getEntryRow();
	    		colSelection = maze.getEntryCol();
	    		rowExit = maze.getExitRow();
	    		colExit = maze.getExitCol();
	    		mazes++;
        	}
        	
        }
    }
	
	public void endGame(Stage gameStage) {
		if (ConfirmBox.display("Salir del test", "¿Seguro que desea abandonar el test?")) {
	    	timeline.stop();
	    	timer.stop();
	        gameStage.setScene(endScene);
    	}
	}
	
	public void registrarResultadoBD(int errCount) throws IOException, InterruptedException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(URI.create
				(Utilities.getBaseURL() + "/Resultado/resregistrarlaberinto.php?iduser="
		+userID + "&tiempo="+ time + "&fecharesultado=" +formatter.format(date))).GET().build();
		
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		System.out.println(response.body());
		if (!response.body().contains("Exito#")) {
			if (errCount < 5 ) {
				registrarResultadoBD(errCount+1);
			}
			else {
				AlertBoxNonWait.display("Error", "No se pudo registrar su resultado");
			}
		}
	}
}
