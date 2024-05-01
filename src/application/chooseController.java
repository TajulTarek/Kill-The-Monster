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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class chooseController {
	@FXML
	Label nomode;
	@FXML 
	RadioButton punch,laser1,laser2;
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
	@FXML
	public void getmode(ActionEvent event) throws IOException, InterruptedException {
		if(punch.isSelected())
		{
			root=(Parent)FXMLLoader.load(getClass().getResource("punch.fxml"));
		}
		else if(laser1.isSelected())
		{
			root=(Parent)FXMLLoader.load(getClass().getResource("lasergunMode.fxml"));
		}
		else
		{
			root=(Parent)FXMLLoader.load(getClass().getResource("lasergunMode2.fxml"));
		}
	}
	public void play(ActionEvent event) throws InterruptedException {
		if(root==null)
		{
			nomode.setVisible(true);
		}
		else {
		stg= (Stage)((Node)event.getSource()).getScene().getWindow();
		scene =new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stg.setScene(scene);
		//stg.setFullScreen(true);
		stg.show();
		}
	}

	
	
}
