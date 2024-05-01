package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.web.WebView;
import javafx.scene.image.Image;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = (Parent)FXMLLoader.load(getClass().getResource("Home.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			
			/*WebView webView = new WebView();
			String filePath = "file:///C:/Users/HP/eclipse-workspace/Kill_The_Monstar/src/pics/24kGoldn.mp3";
			webView.getEngine().loadContent("<html><body><audio autoplay loop><source src=\"" + filePath + "\" type=\"audio/mpeg\"></audio></body></html>");
*/
			//Image icon=new Image("img9.png");
			//primaryStage.getIcons().add(icon);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Kill The Monster !");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
