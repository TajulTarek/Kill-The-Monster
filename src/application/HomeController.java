package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeController {
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
	void switchTolevels(ActionEvent event) throws IOException {
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("chooseMode.fxml"));
		Stage stg= (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene =new Scene(root);
		scene.getStylesheets().add(getClass().getResource("levels.css").toExternalForm());
		
		stg.setScene(scene);
		//stg.setFullScreen(true);
		stg.show();
	}
	
	
}
