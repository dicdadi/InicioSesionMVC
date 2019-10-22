package dad.javafx.iniciosesionmvc.application;


import dad.javafx.iniciosesionmvc.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InicioSesionAPP extends Application {
	private Controller controlador;
	@Override
	public void start(Stage primaryStage) throws Exception {
		controlador = new Controller();
		
		Scene scene = new Scene(controlador.getRoot(),320,200);
		
		primaryStage.setTitle("Iniciar sesión");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
		

	}

}
