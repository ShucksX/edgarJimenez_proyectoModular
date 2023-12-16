package application;
	
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Stop;
import javafx.scene.paint.LinearGradient;
import javafx.stage.Stage;


public class Main extends Application {
	
	private String userId;
	private PrincipalScreen principalScene;
	private LoginScreen loginScene;
	private RegistroScreen registroScene;
	private ClienteScreen clienteScene;
	private AdminScreen adminScene;
	private LaberintoScreen laberintoScene;
	private ColoresScreen coloresScene;
	private ResultadosScreen resultadoScene;
	private SopaScreen sopaScene;
	private PalabrasScreen palabrasScene;
	private SucursalesScreen sucursalesScene;
	private VerResultadosScreen resultadosScene;
	private AddAdminScreen addAdminScene;
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setResizable(false);
			
			//Inicializar clases de escenas
			principalScene = new PrincipalScreen();
			loginScene = new LoginScreen();
			registroScene = new RegistroScreen();
			
			//Configurar botones para cambio de escena y terminar escenas
			principalScene.getBotonRegistro().setOnAction(e-> primaryStage.setScene(registroScene.getScene()));
			principalScene.getBotonLogin().setOnAction(e-> primaryStage.setScene(loginScene.getScene()));	
			loginScene.getBotonLogin().setOnAction(e-> 
			{
				try {
					ingresar(primaryStage);
				} catch (IOException e1) {
					AlertBox.display("Error", "No se pudo conectar a la base de datos\nRevise su conexion e intente de nuevo");
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					AlertBox.display("Error", e1.getMessage());
					e1.printStackTrace();
				}
			});
			loginScene.getBotonRegistro().setOnAction(e-> primaryStage.setScene(registroScene.getScene()));
			loginScene.getBotonRegresar().setOnAction(e-> primaryStage.setScene(principalScene.getScene()));
			
			registroScene.getBotonLogin().setOnAction(e-> primaryStage.setScene(loginScene.getScene()));
			registroScene.getBotonRegresar().setOnAction(e-> primaryStage.setScene(principalScene.getScene()));			
			//Configurar ventana principal
			primaryStage.setScene(principalScene.getScene());
			primaryStage.setTitle("Aplicacion para pruebas de Alzheimer");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void changeToAdminScreen(Stage primaryStage) {
		if(adminScene == null) {
			adminScene = new AdminScreen(userId);
			adminScene.getBotonSucursales().setOnAction(e-> changeToSucursalesScreen(primaryStage));
			adminScene.getBotonResultados().setOnAction(e-> changeToResultadosScreen(primaryStage));
			adminScene.getBotonAddAdmin().setOnAction(e-> changeToAddAdminScreen(primaryStage));
			adminScene.getBotonCS().setOnAction(e-> primaryStage.setScene(principalScene.getScene()));
		}
		primaryStage.setScene(adminScene.getScene());
	}
	
	private void changeToClienteScreen(Stage primaryStage) {
		if(clienteScene == null) {
			clienteScene = new ClienteScreen(userId);
			clienteScene.getBotonCS().setOnAction(e-> primaryStage.setScene(principalScene.getScene()));
			clienteScene.getBotonLaberinto().setOnAction(e-> changeToLaberintoScreen(primaryStage));
			clienteScene.getBotonMemoPalabras().setOnAction(e-> changeToPalabrasScreen(primaryStage));
			clienteScene.getBotonSopa().setOnAction(e-> changeToSopaScreen(primaryStage));
			clienteScene.getBotonColores().setOnAction(e-> changeToColoresScreen(primaryStage));
			clienteScene.getBotonResultado().setOnAction(e-> changeToResultadoScreen(primaryStage));
			clienteScene.getBotonSucursales().setOnAction(e-> changeToSucursalesCLScreen(primaryStage));
		}
		primaryStage.setScene(clienteScene.getScene());
	}
	
	private void changeToLaberintoScreen(Stage primaryStage) {
		laberintoScene = new LaberintoScreen(primaryStage, clienteScene.getScene(),userId);
		primaryStage.setScene(laberintoScene.getScene());
		laberintoScene.startGame();
	}
	
	private void changeToPalabrasScreen(Stage primaryStage) {
		palabrasScene = new PalabrasScreen();
		try {
			String respuesta = palabrasScene.buscarPalabraBD(userId);
			if(respuesta.contains("noe#")) {
				palabrasScene.setupScene1(primaryStage,userId,clienteScene);
				palabrasScene.getBotonVolver().setOnAction(e-> primaryStage.setScene(clienteScene.getScene()));
				palabrasScene.finishScene1();
				primaryStage.setScene(palabrasScene.getScene());
			}
			else if(respuesta.contains("temprano#")){
				AlertBox.display("Su test no esta disponible", "Aun no ha pasado un dia desde que inicio el test\nIntente de nuevo mas tarde.");
			}
			else {
				palabrasScene.setupScene2(primaryStage,userId,clienteScene,respuesta);
				palabrasScene.getBotonVolver().setOnAction(e-> primaryStage.setScene(clienteScene.getScene()));
				palabrasScene.finishScene2();
				primaryStage.setScene(palabrasScene.getScene());
			}
		} catch (IOException e) {
			AlertBox.display("Error", "Hubo un error al conectar a la base de datos\nRevise su conexion e intente de nuevo");
			e.printStackTrace();
		} catch (InterruptedException e) {
			AlertBox.display("Error", "Conexion interrumpida");
			e.printStackTrace();
		}
	}
	
	private void changeToSopaScreen(Stage primaryStage) {
		sopaScene = new SopaScreen(primaryStage, clienteScene.getScene(),userId);
		primaryStage.setScene(sopaScene.getScene());
		sopaScene.startGame();
	}
	
	private void changeToColoresScreen(Stage primaryStage) {
		coloresScene = new ColoresScreen(userId);
		coloresScene.getBotonVolver().setOnAction(e-> primaryStage.setScene(clienteScene.getScene()));
		coloresScene.getBotonIniciar().setOnAction(e-> coloresScene.comenzarTest(primaryStage,clienteScene));
		coloresScene.finishScene();
		primaryStage.setScene(coloresScene.getScene());
	}
	
	private void changeToResultadoScreen(Stage primaryStage) {
		resultadoScene = new ResultadosScreen(userId,primaryStage);
		resultadoScene.getBotonVolver().setOnAction(e-> primaryStage.setScene(clienteScene.getScene()));
		primaryStage.setScene(resultadoScene.getScene());
	}
	
	private void changeToSucursalesCLScreen(Stage primaryStage) {
		sucursalesScene = new SucursalesScreen(clienteScene.getScene(),primaryStage,false,userId);
		sucursalesScene.getBotonVolver().setOnAction(e-> primaryStage.setScene(clienteScene.getScene()));
		primaryStage.setScene(sucursalesScene.getScene());
	}
	
	private void changeToSucursalesScreen(Stage primaryStage) {
		sucursalesScene = new SucursalesScreen(adminScene.getScene(),primaryStage,true,userId);
		sucursalesScene.getBotonVolver().setOnAction(e-> primaryStage.setScene(adminScene.getScene()));
		primaryStage.setScene(sucursalesScene.getScene());
	}
	
	private void changeToResultadosScreen(Stage primaryStage) {
		resultadosScene = new VerResultadosScreen(userId,primaryStage);
		resultadosScene.getBotonVolver().setOnAction(e-> primaryStage.setScene(adminScene.getScene()));
		primaryStage.setScene(resultadosScene.getScene());
	}
	
	private void changeToAddAdminScreen(Stage primaryStage) {
		addAdminScene = new AddAdminScreen();
		addAdminScene.getBotonVolver().setOnAction(e-> primaryStage.setScene(adminScene.getScene()));
		addAdminScene.getRemAdmin().setOnAction(e-> {
			try {
				addAdminScene.removeAdmin(primaryStage, principalScene.getScene(),userId);
			} catch (IOException e1) {
				AlertBox.display("Error", "La conexion al servidor se interrumpio");
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				AlertBox.display("Error", "La conexion al servidor se interrumpio");
				e1.printStackTrace();
			}
		});
		primaryStage.setScene(addAdminScene.getScene());
	}
	
	private void ingresar(Stage primaryStage) throws IOException, InterruptedException {
		String txtCorreo = loginScene.getUsuario().getText();
		String txtContrasena = loginScene.getContrasena().getText();
		
		loginScene.cleanFields();

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(URI.create
				(Utilities.getBaseURL() + "/ingresar.php?correo="+txtCorreo+
						"&password="+txtContrasena)).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		//System.out.println(response.body());
		if (response.body().contains("unv"))
			AlertBox.display("Error", "Por favor verifique su cuenta en su correo");
		else if (response.body().contains("noe"))
			AlertBox.display("Error", "No se encontro su cuenta\nVerifique sus datos");
		else if (response.body().contains("adm")) {
			userId = response.body().substring(3);
			System.out.println(userId);
			if(ConfirmBox.display("Alerta", "Su cuenta es de tipo administrador\n¿Entrar a modo administrador?")) 
			{
				changeToAdminScreen(primaryStage);
			}
			else {
				changeToClienteScreen(primaryStage);
			}
		}
		else if (response.body().contains("vac"))
			AlertBox.display("Error", "Por favor llene los campos");
		else {
			userId = response.body().substring(3);
			System.out.println(userId);
			changeToClienteScreen(primaryStage);
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
