package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class laserrulesController2 {
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
	void switchTopunch(ActionEvent event) throws IOException {
		root = (Parent)FXMLLoader.load(getClass().getResource("lasergunMode2.fxml"));
		stg= (Stage)((Node)event.getSource()).getScene().getWindow();
		scene =new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stg.setScene(scene);
		//stg.setFullScreen(true);
		stg.show();
	}
	
}
