package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.image.Image;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class punchController implements Initializable{
	@FXML
	AnchorPane resultpane;
	@FXML
	Label totalkill,totalpunch;
	@FXML
	Label punchcount;
	@FXML
	Label killcounter,ingameinstruction;
	@FXML
	void switchTochoosemode(ActionEvent event) throws IOException {
		root = (Parent)FXMLLoader.load(getClass().getResource("chooseMode.fxml"));
		stg= (Stage)((Node)event.getSource()).getScene().getWindow();
		scene =new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stg.setScene(scene);
		//stg.setFullScreen(true);
		stg.show();
	}
	@FXML
	void logout(ActionEvent event) {
		Stage st= (Stage) exitbutton.getScene().getWindow();
		st.close();
	}
	@FXML
	AnchorPane anchorpane;
	@FXML
	ProgressBar unayesHealth;
	@FXML
	ProgressBar billahHealth;
	@FXML
	ProgressBar nayemHealth;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		nayemHealth.setStyle("-fx-accent:green;");
		unayesHealth.setStyle("-fx-accent:green;");
		billahHealth.setStyle("-fx-accent:green;");
	}  
	@FXML
	Button startbutton,restartbutton,rulesbutton,scoreboard;
	@FXML
	ImageView leftborder,ground,rightborder;
	@FXML 
	Button nayem,unayes,billah;
	@FXML
	Button exitbutton;
	@FXML
	Timeline timeline1,timeline2,timeline3;
	
	int totalMon=0;

	@FXML
	private Stage stg;
	@FXML
	private Scene scene;
	@FXML
	private Parent root;
	@FXML
	private Parent winfx,losefx;
	

	double nayemHP=3,unayesHP=5,billahHP=10;
	int unayesCounter=5,nayemCounter=3,billahCounter=10;

	@FXML
	void hitnayem(ActionEvent event) {
		nayemCounter--;
		//System.out.println("Nayem re maro");
		double health= (double)nayemCounter/nayemHP;
		nayemHealth.setProgress(health);
		punchbarao();
	}
	@FXML
	void hitunayes(ActionEvent event) {
		unayesCounter--;
		//System.out.println("Unayes re maro");
		double health= (double)unayesCounter/unayesHP;
		unayesHealth.setProgress(health);
    	punchbarao();
	}
	@FXML
	void hitbillah(ActionEvent event) {
		billahCounter--;
		//System.out.println("Billah re maro");
		double health= (double)billahCounter/billahHP;
		billahHealth.setProgress(health);
    	punchbarao();
	}
	int r1=(int)((Math.random())*100);
	int r2=(int)((Math.random())*100);
	int r3=(int)((Math.random())*100);
	int f1=0,f2=0,f3=0;
    double incrementX1 = 0.7;
    double incrementY1 = 0.2;
    double incrementX2 = 0.6;
    double incrementY2 = 0.15;
    double incrementX3 = 0.4;
    double incrementY3 = 0.1;
	public void startGame(ActionEvent event) throws Exception {
		((AnchorPane)(startbutton.getParent())).getChildren().remove(startbutton);
		((AnchorPane)(rulesbutton.getParent())).getChildren().remove(rulesbutton);
		ingameinstruction.setVisible(false);
		
        double rightX = rightborder.getLayoutX();
        double leftX = leftborder.getLayoutX()+leftborder.getFitWidth();
        double groundY = ground.getLayoutY();
        
       	if (timeline1 != null && timeline1.getStatus() == Timeline.Status.RUNNING) {
        	return;
       	}
        if (timeline2 != null && timeline2.getStatus() == Timeline.Status.RUNNING) {
        	return;
        }
        if (timeline3 != null && timeline3.getStatus() == Timeline.Status.RUNNING) {
        	return;
        }


        
        timeline1 = new Timeline(new KeyFrame(Duration.millis(10), e -> {
        	//System.out.println(Thread.currentThread().getName());
        	
        	double newX=nayem.getLayoutX();
        	double newY=nayem.getLayoutY();
        	if(r1%2==0)
        	{
        		newX+=incrementX1;	
        	}
        	else
        	{
        		newX-=incrementX1;
        	}
        	newY+=incrementY1;
        	if (nayemCounter<=0) {
        		
        		nayemCounter=(int)nayemHP+1;
        		nayemHP=nayemCounter;
        		incrementX1=incrementX2+0.2;
        		incrementY1=incrementY2+0.05;
        		int x=(int)(Math.random()*1500);
        		nayem.setLayoutX(x);
        		nayem.setLayoutY(-40);
            	nayemHealth.setProgress(1);
            	killbarao();
            }
  
        	else if (newY>=ground.getLayoutY()-nayem.getHeight()) {
                khelasesh();
            }
            else if(newX >= rightX-nayem.getWidth())
            {
            	newX=newX-2*incrementX1;
            	changePosition( nayem , nayemHealth , newX , newY);
            	
            }
            else if(newX <=20)
            {
            	newX=newX+incrementX1;
            	changePosition( nayem , nayemHealth , newX , newY);
            }
            else {
            	changePosition( nayem , nayemHealth , newX , newY);
            }
            if(f1>=50)
            {
            	r1= (int)(Math.random()*100);
            	f1=0;
            }
            else
            {
            	f1++;
            }

        })
        		);
        


        
        

        
        timeline2 = new Timeline(new KeyFrame(Duration.millis(10), e -> {
        	//System.out.println(Thread.currentThread().getName());
        	
        	double newX=unayes.getLayoutX();
        	double newY=unayes.getLayoutY();
        	if(r2%2==0)
        	{
        		newX+=incrementX2;	
        	}
        	else
        	{
        		newX-=incrementX2;
        	}
        	newY+=incrementY2;
        	if (unayesCounter<=0) {
        		
        		unayesCounter=(int)unayesHP+1;
        		unayesHP=unayesCounter;
        		incrementX2=incrementX3+0.2;
        		incrementY2=incrementY3+0.05;
        		int x=(int)(Math.random()*1500);
                unayes.setLayoutX(x);
                unayes.setLayoutY(-90);
                unayesHealth.setProgress(1);
                killbarao();
            	
                
            }
  
        	else if (newY>=ground.getLayoutY()-unayes.getHeight()) {
        		khelasesh();
            }
            else if(newX >= rightX-unayes.getWidth())
            {
            	newX=newX-2*incrementX2;
            	changePosition( unayes , unayesHealth , newX , newY);
            	
            }
            else if(newX <=20)
            {
            	newX=newX+incrementX2;
            	changePosition( unayes , unayesHealth , newX , newY);
            }
            else {
            	changePosition( unayes , unayesHealth , newX , newY);
            }
            if(f2>=50)
            {
            	r2= (int)(Math.random()*100);
            	f2=0;
            }
            else
            {
            	f2++;
            }

        })
        		);
        
        
        

        
        timeline3 = new Timeline(new KeyFrame(Duration.millis(10), e -> {
        	//System.out.println(Thread.currentThread().getName());
        	
        	double newX=billah.getLayoutX();
        	double newY=billah.getLayoutY();
        	if(r3%2==0)
        	{
        		newX+=incrementX3;	
        	}
        	else
        	{
        		newX-=incrementX3;
        	}
        	newY+=incrementY3;
        	if (billahCounter<=0) {
        		
        		billahCounter=(int)billahHP+1;
        		billahHP=billahCounter;
        		incrementX3+=0.2;
        		incrementY3+=0.1;
        		int x=(int)(Math.random()*1500);
        		billah.setLayoutX(x);
        		billah.setLayoutY(-160);
        		billahHealth.setProgress(1);
            	killbarao();
                
            }
  
        	else if (newY>=ground.getLayoutY()-billah.getHeight()) {
        		khelasesh();
            }
            else if(newX >= rightX-billah.getWidth())
            {
            	newX=newX-2*incrementX2;
            	changePosition( billah , billahHealth , newX , newY);
            	
            }
            else if(newX <=20)
            {
            	newX=newX+incrementX2;
            	changePosition( billah , billahHealth , newX , newY);
            }
            else {
            	changePosition( billah , billahHealth , newX , newY);
            }
            if(f3>=50)
            {
            	r3= (int)(Math.random()*100);
            	f3=0;
            }
            else
            {
            	f3++;
            }

        })
        		);
        
        
        timeline1.setCycleCount(Timeline.INDEFINITE);
        timeline2.setCycleCount(Timeline.INDEFINITE);
        timeline3.setCycleCount(Timeline.INDEFINITE);
   
        timeline1.play();
        timeline2.play();
        timeline3.play();
                
        

        
    }
	
	
	
	public void restart(ActionEvent event) throws IOException {
		
		root = (Parent)FXMLLoader.load(getClass().getResource("punch.fxml"));
		stg= (Stage)((Node)event.getSource()).getScene().getWindow();
		scene =new Scene(root);
		scene.getStylesheets().add(getClass().getResource("level1.css").toExternalForm());
		stg.setScene(scene);
		//stg.setFullScreen(true);
		stg.show();
	}
	public void showrules(ActionEvent event) throws IOException {
		
		root = (Parent)FXMLLoader.load(getClass().getResource("punchrules.fxml"));
		stg= (Stage)((Node)event.getSource()).getScene().getWindow();
		scene =new Scene(root);
		scene.getStylesheets().add(getClass().getResource("level1.css").toExternalForm());
		stg.setScene(scene);
		//stg.setFullScreen(true);
		stg.show();
	}
	void khelasesh()
	{
        timeline1.stop();
        timeline2.stop();
        timeline3.stop();

        resultpane.setVisible(true);
        totalkill.setText("Total Kill : "+killcounter.getText());
        totalkill.setVisible(true);
        totalpunch.setText("Total Punch : "+punchcount.getText());
        totalpunch.setVisible(true);
        //restartbutton.setVisible(true);
        scoreboard.setVisible(true);
	}
	void changePosition(Button button, ProgressBar health,double newX,double newY) {
		button.setLayoutX(newX);
    	button.setLayoutY(newY);
    	health.setLayoutX(newX);
    	health.setLayoutY(newY-health.getHeight());
	}
	void killbarao() {
		String s=killcounter.getText();
    	int kill = Integer.parseInt(s);
    	kill++;
    	killcounter.setText(""+kill);
	}
	void punchbarao() {
		String s=punchcount.getText();
    	int punch = Integer.parseInt(s);
    	punch++;
    	punchcount.setText(""+punch);
	}
  

	
	
	
}
