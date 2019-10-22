package dad.javafx.iniciosesionmvc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class Controller {
	public static final String SEPARATOR = ",";
	private Model Modelo = new Model();
	private View Vista = new View();

	public Controller() {
		Vista.getCancel().setOnAction(e -> actionButtonCancel(e));
		Vista.getAccess().setOnAction(e -> {
			try {
				actionButtonAccess(e);
			} catch (IOException e1) {

				e1.printStackTrace();
			}
		});

		Modelo.userProperty().bindBidirectional(Vista.getUser().textProperty());
		Modelo.passwordProperty().bindBidirectional(Vista.getPassword().textProperty());
	}

	private void actionButtonAccess(ActionEvent e) throws IOException {
		// Creamos un variable de comprobación
		boolean accesoCorrecto = false;
		// Creamos un lector para nuestro archivo csv
		FileReader archivo = new FileReader("users.csv");
		BufferedReader lectura = new BufferedReader(archivo);
		// Creamos dos variables donde guardaremos el nombre y contraseña de cada linea
		// csv y lo comprobaremos con los datos introducidos
		String nombre, contrasenniaMDCinco;
		try {
			// Lectura de la linea
			String lineaCSV = lectura.readLine();
			// Bucle, lee linea a linea hasta que que se termine el fichero y nuestra
			// variable de comprobación sea falsa
			// Si la variable cambia a true se rompe el buclque
			while (accesoCorrecto != true && null != lineaCSV) {
				// Pasamos los datos de la linea a un array
				String[] datos = lineaCSV.split(SEPARATOR);
				// Hacemos trim a cada dato, pues al hacer un split se queda con un espacio al
				// final y al comprobar puede dar errores
				nombre = datos[0].trim();
				contrasenniaMDCinco = datos[1].trim();
				// hacemos la comprobación
				// RECORDATORIO: comprobación de arrays con equals, el == se usa con valores
				// primitivos
				if (nombre.equals(Modelo.getUser())
						&& contrasenniaMDCinco.equals(creacionContrasenniaMD5(Modelo.getPassword()))) {
					MensajeAcceso();
					accesoCorrecto = true;
				}
				lineaCSV = lectura.readLine();
			}
			if (accesoCorrecto == false) {
				MensajeDenegado();
			}

		} catch (IOException er) {
			System.out.println("err");
		} finally {
			if (null != lectura) {
				lectura.close();
			}
		}
	}

	private void actionButtonCancel(ActionEvent e) {
		Platform.exit();
		System.exit(0);
	}

//Funciones extras
	private String creacionContrasenniaMD5(String dato) {

		return DigestUtils.md5Hex(dato).toUpperCase();
	}

	private void MensajeAcceso() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Iniciar sesión");
		alert.setHeaderText("Acceso permitido");
		alert.setContentText("Las credenciales de texto son válidas");

		// Capturamos si hemos presionado el botón aceptar para cerrar la aplicación
		// Esto está hecho simplemente para comprobar que todos los usuarios funcionaban
		// Si aceptamos se cierra el programa, si le damos al cierre de la pestaña de
		// información, cambiamos los valores de usuario y contraseña
		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent()) {
			Platform.exit();
		} else {
			Modelo.setUser("");
			Modelo.setPassword("");
		}

	}

	private void MensajeDenegado() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Iniciar sesión");
		alert.setHeaderText("Acceso Denegado");
		alert.setContentText("El usuario y/o contraseña no son válidos");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent()) {
			Modelo.setPassword("");
		} else {
			Modelo.setUser("");
			Modelo.setPassword("");
		}
	}

	public View getRoot() {
		return Vista;
	}

}
