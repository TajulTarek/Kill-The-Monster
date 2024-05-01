package application;

import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javax.swing.JFrame;
import javafx.scene.image.Image;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;


public class lasergunModeController implements Initializable{
	
	//private BooleanProperty wPressed = new SimpleBooleanProperty();
    private BooleanProperty aPressed = new SimpleBooleanProperty();
    //private BooleanProperty sPressed = new SimpleBooleanProperty();
    private BooleanProperty dPressed = new SimpleBooleanProperty();
    private BooleanBinding keyPressed = (aPressed).or(dPressed);
    @FXML
    Label health;
    int healthvalue=100;
    @FXML
    ImageView bondukpic;
	@FXML
	AnchorPane resultpane;
	@FXML
	Label totalkill;
	@FXML
	Label killcounter,ingameinstruction,parcen;

    private int movementVariable = 5;
    @FXML
    private ImageView bonduk10;

    @FXML
    private AnchorPane anchorpane;
    
    
    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {

            if(aPressed.get()){
            	bonduk10.setLayoutX(bonduk10.getLayoutX() - movementVariable);
            	bondukpic.setLayoutX(bondukpic.getLayoutX()- movementVariable);
            	
            }

            if(dPressed.get()){
            	bonduk10.setLayoutX(bonduk10.getLayoutX() + movementVariable);
            	bondukpic.setLayoutX(bondukpic.getLayoutX()+ movementVariable);
            }
            
        }
    };
    public void movementSetup(){
        anchorpane.setOnKeyPressed(e -> {

            if(e.getCode() == KeyCode.A) {
                aPressed.set(true);
            }
            if(e.getCode() == KeyCode.D) {
                dPressed.set(true);
            }
        });

        anchorpane.setOnKeyReleased(e ->{

            if(e.getCode() == KeyCode.A) {
                aPressed.set(false);
            }

            if(e.getCode() == KeyCode.D) {
                dPressed.set(false);
            }
        });
    }

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
	ProgressBar unayesHealth;
	@FXML
	ProgressBar nayemHealth;
	@FXML 
	ProgressBar billahHealth;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		nayemHealth.setStyle("-fx-accent:green;");
		unayesHealth.setStyle("-fx-accent:green;");
		billahHealth.setStyle("-fx-accent:green;");
		movementSetup();

        keyPressed.addListener(((observableValue, aBoolean, t1) -> {
            if(!aBoolean){
                timer.start();
            } else {
                timer.stop();
            }
        }));
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

	double nayemHP=100,unayesHP=200,billahHP=500;
	int unayesCounter=200,nayemCounter=100,billahCounter=500;
	
	
	@FXML
	void hitnayem() {
		nayemCounter-=2;
		//System.out.println("Nayem re maro");
		double health= (double)nayemCounter/nayemHP;
		nayemHealth.setProgress(health);
	}
	@FXML
	void hitunayes() {
		unayesCounter-=2;
		//System.out.println("Unayes re maro");
		double health= (double)unayesCounter/unayesHP;
		unayesHealth.setProgress(health);
	}
	@FXML
	void hitbillah() {
		billahCounter-=2;
		//System.out.println("Billah re maro");
		double health= (double)billahCounter/billahHP;
		billahHealth.setProgress(health);
	}
	int r1=(int)((Math.random())*100);
	int r2=(int)((Math.random())*100);
	int r3=(int)((Math.random())*100);
	int f1=0,f2=0,f3=0;
    double incrementX1 = 1;
    double incrementY1 = 0.25;
    double incrementX2 = 0.9;
    double incrementY2 = 0.2;
    double incrementX3 = 0.8;
    double incrementY3 = 0.15;
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

        	if(bonduk10.getLayoutX()>=nayem.getLayoutX() && bonduk10.getLayoutX()+bonduk10.getFitWidth()<=nayem.getLayoutX()+nayem.getWidth())
        	{
        		hitnayem();
        	}

        	
        	if (nayemCounter<=0) {
        		
        		nayemCounter=(int)nayemHP+100;
        		nayemHP=nayemCounter;
        		incrementX1=incrementX2+0.2;
        		incrementY1=incrementY2+0.05;
        		int x=(int)(Math.random()*1500);
        		//System.out.println("Nayem "+x);
        		nayem.setLayoutX(x);
        		nayem.setLayoutY(-40);
            	nayemHealth.setProgress(1);
            	String s=killcounter.getText();
            	int kill = Integer.parseInt(s);
            	kill++;
            	killcounter.setText(""+kill);
            }
        	else if(healthvalue<=0)
        	{
        		timeline1.stop();
                timeline2.stop();
                timeline3.stop();
                
                resultpane.setVisible(true);
                totalkill.setText("Total Kill : "+killcounter.getText());
                totalkill.setVisible(true);
                restartbutton.setVisible(true);
                scoreboard.setVisible(true);
                parcen.setVisible(false);
        	}
        	else if (newY>=ground.getLayoutY()-nayem.getHeight()) {
        		
        		int remainhealth=(int) (nayemHealth.getProgress()*100);
        		
        		healthvalue-=remainhealth;
        		int x=(int)(Math.random()*1400);
        		nayem.setLayoutX(x);
        		nayem.setLayoutY(-40);
            	//nayemHealth.setProgress(1);
            	if(healthvalue<=0)
        		{
        			health.setText("Death !!!");
        			health.setStyle("-fx-text-fill: red;");
        			Font font = Font.font("Arial", FontWeight.BOLD, 24);
        	        health.setFont(font);
        		}
        		else health.setText(""+healthvalue);
        		
        		
                //timeline1.stop();
                //timeline2.stop();
                //timeline3.stop();
                
                //resultpane.setVisible(true);
                //totalkill.setText("Total Kill : "+killcounter.getText());
                //totalkill.setVisible(true);
                //restartbutton.setVisible(true);
                //scoreboard.setVisible(true);
                
          
            }
            else if(newX >= rightX-nayem.getWidth())
            {
            	newX=newX-2*incrementX1;
            	nayem.setLayoutX(newX);
            	nayem.setLayoutY(newY);
            	nayemHealth.setLayoutX(newX);
            	nayemHealth.setLayoutY(newY-nayemHealth.getHeight());
            	
            }
            else if(newX <=20)
            {
            	newX=newX+incrementX1;
            	nayem.setLayoutX(newX);
            	nayem.setLayoutY(newY);
            	nayemHealth.setLayoutX(newX);
            	nayemHealth.setLayoutY(newY-nayemHealth.getHeight());
            }
            else {
            	nayem.setLayoutX(newX);
            	nayem.setLayoutY(newY);
            	nayemHealth.setLayoutX(newX);
            	nayemHealth.setLayoutY(newY-nayemHealth.getHeight());
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
        	if(bonduk10.getLayoutX()>=unayes.getLayoutX() && bonduk10.getLayoutX()+bonduk10.getFitWidth()<=unayes.getLayoutX()+unayes.getWidth())
        	{
        		hitunayes();
        	}
        	
        	
        	
        	if (unayesCounter<=0) {
        		
        		unayesCounter=(int)unayesHP+100;
        		unayesHP=unayesCounter;
        		incrementX2=incrementX3+0.2;
        		incrementY2=incrementY3+0.05;
        		int x=(int)(Math.random()*1500);
        		//System.out.println("Nunayesm "+x);
        		unayes.setLayoutX(x);
        		unayes.setLayoutY(-40);
        		unayesHealth.setProgress(1);
            	String s=killcounter.getText();
            	int kill = Integer.parseInt(s);
            	kill++;
            	killcounter.setText(""+kill);
          
            }
  
        	else if(healthvalue<=0)
        	{
        		timeline1.stop();
                timeline2.stop();
                timeline3.stop();
                
                resultpane.setVisible(true);
                totalkill.setText("Total Kill : "+killcounter.getText());
                totalkill.setVisible(true);
                restartbutton.setVisible(true);
                scoreboard.setVisible(true);
                parcen.setVisible(false);

        	}
        	else if (newY>=ground.getLayoutY()-unayes.getHeight()) {
        		
        		int remainhealth=(int)(unayesHealth.getProgress()*100);
        		
        		healthvalue=healthvalue-(int)remainhealth;
        		int x=(int)(Math.random()*1400);
        		unayes.setLayoutX(x);
        		unayes.setLayoutY(-40);
            	//unayesHealth.setProgress(1);
            	if(healthvalue<=0)
        		{
        			health.setText("Death !!!");
        			health.setStyle("-fx-text-fill: red;");
        			Font font = Font.font("Arial", FontWeight.BOLD, 24);
        	        health.setFont(font);
        		}
        		else health.setText(""+healthvalue);
        		
        		
                //timeline1.stop();
                //timeline2.stop();
                //timeline3.stop();
                
                //resultpane.setVisible(true);
                //totalkill.setText("Total Kill : "+killcounter.getText());
                //totalkill.setVisible(true);
                //restartbutton.setVisible(true);
                //scoreboard.setVisible(true);
                
          
            }
            else if(newX >= rightX-unayes.getWidth())
            {
            	newX=newX-2*incrementX2;
            	unayes.setLayoutX(newX);
            	unayes.setLayoutY(newY);
            	unayesHealth.setLayoutX(newX);
            	unayesHealth.setLayoutY(newY-unayesHealth.getHeight());
            	
            }
            else if(newX <=20)
            {
            	newX=newX+incrementX2;
            	unayes.setLayoutX(newX);
            	unayes.setLayoutY(newY);
            	unayesHealth.setLayoutX(newX);
            	unayesHealth.setLayoutY(newY-unayesHealth.getHeight());
            }
            else {
            	unayes.setLayoutX(newX);
            	unayes.setLayoutY(newY);
            	unayesHealth.setLayoutX(newX);
            	unayesHealth.setLayoutY(newY-unayesHealth.getHeight());
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
        	if(bonduk10.getLayoutX()>=billah.getLayoutX() && bonduk10.getLayoutX()+bonduk10.getFitWidth()<=billah.getLayoutX()+billah.getWidth())
        	{
        		hitbillah();
        	}
        	if (billahCounter<=0) {
        		
        		billahCounter=(int)billahHP+100;
        		billahHP=billahCounter;
        		incrementX3+=0.2;
        		incrementY3+=0.1;
        		int x=(int)(Math.random()*1500);
        		billah.setLayoutX(x);
        		billah.setLayoutY(-160);
        		//System.out.println("B "+x);
        		billahHealth.setProgress(1);
            	String s=killcounter.getText();
            	int kill = Integer.parseInt(s);
            	kill++;
            	killcounter.setText(""+kill);
            	
            }
        	else if(healthvalue<=0)
        	{
        		timeline1.stop();
                timeline2.stop();
                timeline3.stop();
                
                resultpane.setVisible(true);
                totalkill.setText("Total Kill : "+killcounter.getText());
                totalkill.setVisible(true);
                restartbutton.setVisible(true);
                scoreboard.setVisible(true);
                parcen.setVisible(false);
        	}
        	else if (newY>=ground.getLayoutY()-billah.getHeight()) {
        		
        		int remainhealth=(int) (billahHealth.getProgress()*100);
        		
        		healthvalue-=remainhealth;
        		int x=(int)(Math.random()*1400);
        		billah.setLayoutX(x);
        		billah.setLayoutY(-40);
        		if(healthvalue<=0)
        		{
        			health.setText("Death !!!");
        			health.setStyle("-fx-text-fill: red;");
        			Font font = Font.font("Arial", FontWeight.BOLD, 24);
        	        health.setFont(font);
        		}
        		else health.setText(""+healthvalue);
        		
        		
                //timeline1.stop();
                //timeline2.stop();
                //timeline3.stop();
                
                //resultpane.setVisible(true);
                //totalkill.setText("Total Kill : "+killcounter.getText());
                //totalkill.setVisible(true);
                //restartbutton.setVisible(true);
                //scoreboard.setVisible(true);
                
          
            }
            else if(newX >= rightX-billah.getWidth())
            {
            	newX=newX-2*incrementX2;
            	billah.setLayoutX(newX);
            	billah.setLayoutY(newY);
            	billahHealth.setLayoutX(newX);
            	billahHealth.setLayoutY(newY-billahHealth.getHeight());
            	
            }
            else if(newX <=20)
            {
            	newX=newX+incrementX2;
            	billah.setLayoutX(newX);
            	billah.setLayoutY(newY);
            	billahHealth.setLayoutX(newX);
            	billahHealth.setLayoutY(newY-billahHealth.getHeight());
            }
            else {
            	billah.setLayoutX(newX);
            	billah.setLayoutY(newY);
            	billahHealth.setLayoutX(newX);
            	billahHealth.setLayoutY(newY-billahHealth.getHeight());
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
		
		root = (Parent)FXMLLoader.load(getClass().getResource("lasergunMode.fxml"));
		stg= (Stage)((Node)event.getSource()).getScene().getWindow();
		scene =new Scene(root);
		scene.getStylesheets().add(getClass().getResource("level1.css").toExternalForm());
		stg.setScene(scene);
		//stg.setFullScreen(true);
		stg.show();
	}
	public void showrules(ActionEvent event) throws IOException {
		
		root = (Parent)FXMLLoader.load(getClass().getResource("laserrules.fxml"));
		stg= (Stage)((Node)event.getSource()).getScene().getWindow();
		scene =new Scene(root);
		scene.getStylesheets().add(getClass().getResource("level1.css").toExternalForm());
		stg.setScene(scene);
		//stg.setFullScreen(true);
		stg.show();
	}

  

	
	
	
}
