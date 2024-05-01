package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class levelsController {
	@FXML
	Button exitbutton;
	@FXML
	void logout(ActionEvent event) {
		Stage st= (Stage) exitbutton.getScene().getWindow();
		st.close();
	}
	@FXML
	private Stage stg;
	@FXML
	private Scene scene;
	@FXML
	private Parent root;
	@FXML
	void switchToHome(ActionEvent event) throws IOException {
		root = (Parent)FXMLLoader.load(getClass().getResource("Home.fxml"));
		stg= (Stage)((Node)event.getSource()).getScene().getWindow();
		scene =new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stg.setScene(scene);
		//stg.setFullScreen(true);
		stg.show();
	}
	public void switchTolevel1(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("level1.fxml"));
		root = loader.load();
		lasergunModeController laserController= new lasergunModeController();
		
		stg= (Stage)((Node)event.getSource()).getScene().getWindow();
		scene =new Scene(root);
		
		scene.getStylesheets().add(getClass().getResource("level1.css").toExternalForm());
		stg.setScene(scene);
		//stg.setFullScreen(true);
		stg.show();
	}
	
}
