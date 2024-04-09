package application;

import javafx.animation.AnimationTimer;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.animation.*;
import javafx.util.Duration;
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

public class SopaScreen implements EventHandler<KeyEvent> {
	private AnimationTimer timer;
	final int windowSize = 600;
    private int boardSize;
    private int colSelection = 4;
    private int rowSelection = 6;
    private int time;
    private Timeline timeline;
    private final int VBOX_SPACING = 5;
    private Scene mainScene, endScene;
    private GraphicsContext gc;
    private String wordIn = new String();
    private Canvas canvas;
    private Box keyboardNode;
    private Sopa game;
    private Stage gameStage;
    private String userID;
    private boolean modoNC;
	public SopaScreen(Stage stage, Scene clienteScene, String userid, boolean modoNC) {
		this.modoNC = modoNC;
		userID = userid;
		game = new Sopa();
		gameStage = stage;
		endScene = clienteScene;
		time = 0;
		game.initSopaBoard();
		
		Font fontTitulo = Font.font("Courier New",FontWeight.BOLD,22);
		Font fontTexto = Font.font("Courier New",FontWeight.NORMAL,15);
		
		Label lblTitulo = new Label ("Sopa de letras");
		lblTitulo.setFont(fontTitulo);

		Label instrucciones1 = new Label ("Busque las palabras que se indican");
		instrucciones1.setFont(fontTexto);
		Label instrucciones2 = new Label("Presione las flechas en su teclado para mover el cursor");
		instrucciones2.setFont(fontTexto);
		Label instrucciones3 = new Label("Presione ENTER para escoger la letra en el cursor");
		instrucciones3.setFont(fontTexto);
		Label instrucciones4 = new Label("Retroceso/Delete -> Borrar letra     Escape -> Abandonar ejercicio");
		instrucciones4.setFont(fontTexto);
		
        boardSize = game.getBoardSize();
		canvas = new Canvas(windowSize, windowSize);
	    gc = canvas.getGraphicsContext2D();
	    gc.setFont(fontTexto);

	    keyboardNode = new Box();
	    keyboardNode.setFocusTraversable(true);
	    keyboardNode.requestFocus();
	    keyboardNode.setOnKeyPressed(this);

	    VBox layout = new VBox();
		layout.setSpacing(VBOX_SPACING);
		layout.setPadding(new Insets(80,10,10,10));
		layout.setAlignment(Pos.TOP_CENTER);
		
	    
	    timer = new MyTimer();
		
	    layout.getChildren().addAll(lblTitulo,instrucciones1,instrucciones2,instrucciones3,instrucciones4,canvas,keyboardNode);
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
		mainScene = new Scene(layout,Utilities.windowWidth,Utilities.windowHeight);
	}
	
	public void registrarResultadoBD(int errCount) throws IOException, InterruptedException {
		if (modoNC)
			return;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(URI.create
				(Utilities.getBaseURL() + "/Resultado/resregistrarsopa.php?iduser="
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
	
	public void startGame() {
		timer.start();
		countTime();
	}
	
	public Scene getScene() {
		return mainScene;
	}
	
	public void handle(KeyEvent e) {

        if (e.getCode() == KeyCode.LEFT) { // If left button pressed
            if (colSelection - 1 >= 0) {
                colSelection--; 
            }
        }
        if (e.getCode() == KeyCode.RIGHT) { // If right button pressed
            if (colSelection + 1 < boardSize) { 
                colSelection++; 
            }
        }
        if (e.getCode() == KeyCode.UP) { // If up button pressed
            if (rowSelection - 1 >= 0) { 
                rowSelection--;
            }
        }
        if (e.getCode() == KeyCode.DOWN) { // If down button pressed
            if (rowSelection + 1 < boardSize) { 
                rowSelection++; 
            }
        }
        if (e.getCode() == KeyCode.ENTER) { // If ENTER is presed
            game.selectFoundWord(rowSelection, colSelection);
        }
        if (e.getCode() == KeyCode.DELETE || e.getCode() == KeyCode.BACK_SPACE) { // If delete or backspace is pressed
            game.deleteLastLetter();
            game.wordSelectToString();
        }
        if(e.getCode() == KeyCode.ESCAPE) {
        	endGame(gameStage);
        }
    }
	
	private class MyTimer extends AnimationTimer{

		@Override
		public void handle(long arg0) {
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
			run();
			wordIn = game.getWordIn();
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
        printWordList();
        printSelectedWord();
    }
	
	public void printWordList() {
        int rowCounter = 0;
        int colCounter = 0;
        int indent = 30;
        for (int i = 0; i < game.getWordListSize(); i++) {
            gc.fillText(game.getWordListValue(i), (indent + 40 + (125 * colCounter)), (indent  + (20 * rowCounter)));
            colCounter++;
            if (colCounter == 4) {
                colCounter = 0;
                rowCounter++;
            }
        }
    }

    public void printGameBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                System.out.print(game.getBoardPos(i, j) + "  ");
            }
            System.out.println();
        }
    }

    public void gcPrintGameBoard() {
    	gc.setFill(Color.ORANGE);
        gc.fillRect(0,0, 580, 450);
        gc.setFill(Color.WHITE);
        gc.fillRect(10,10, 560, 430);
    	gc.setFill(Color.BLACK);
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                gc.fillText(String.valueOf(game.getBoardPos(i, j)), (165 + (20 * j)), (100 + (20 * i)));
            }
        }
    }


    public void showSelection() {
        gc.save(); // Saves gc state before printing
        gc.setFill(Color.BLACK);
        gc.fillRect((162 + (20 * colSelection)), (88 + (20 * rowSelection)), 15, 15);
        gc.setFill(Color.WHITE);
        gc.fillText(String.valueOf(game.getBoardPos(rowSelection, colSelection)), (165 + (20 * colSelection)),
                (100 + (20 * rowSelection)));
        gc.restore(); // Restores saved gc state
    }

    public void printSelectedWord() {
        gc.fillText("Palabra seleccionada: " + wordIn, 30, 75);
    }

    public void checkForEnd(Stage gameStage) {
        if (game.getWordListSize() == 0) {
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
    }
    
    public void endGame(Stage gameStage) {
    	if (ConfirmBox.display("Salir del ejercicio", "¿Seguro que desea abandonar el ejercicio?")) {
	    	timeline.stop();
	    	timer.stop();
	    	gameStage.setScene(endScene);
    	}
    }
}

