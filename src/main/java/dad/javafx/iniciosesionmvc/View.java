package dad.javafx.iniciosesionmvc;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class View extends GridPane {
	
	private TextField user;
	private PasswordField password;
	private Button access;
	private Button cancel;
	
	public View() {
	super();
	user= new TextField();
	user.setPromptText("Usuario");
	
	password= new PasswordField();
	password.setPromptText("contraseña");
	
	access=new Button("Acceder");
	cancel= new Button("Cancelar");
	
	HBox boxButtons= new HBox(5,access,cancel);
	boxButtons.setAlignment(Pos.CENTER);
	
	this.setHgap(20);
	this.setVgap(20);
	this.setPadding(new Insets(5));
	this.addRow(0, new Label("Usuario:"),user);
	this.addRow(1, new Label("Contraseña:"),password);
	this.addRow(2, boxButtons);
	this.setAlignment(Pos.CENTER);
	GridPane.setColumnSpan(boxButtons, 2);

	}

	public TextField getUser() {
		return user;
	}

	public PasswordField getPassword() {
		return password;
	}

	public Button getAccess() {
		return access;
	}

	public Button getCancel() {
		return cancel;
	}

}
