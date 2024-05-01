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
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class lasergunModeController2 implements Initializable{
	
	private BooleanProperty dPressed = new SimpleBooleanProperty();
    private BooleanProperty aPressed = new SimpleBooleanProperty();
    private BooleanProperty jPressed = new SimpleBooleanProperty();
    private BooleanProperty lPressed = new SimpleBooleanProperty();
    private BooleanBinding keyPressed1 = dPressed.or(aPressed).or(jPressed).or(lPressed);
    //private BooleanBinding keyPressed2 = jPressed.or(lPressed);
    @FXML
    ImageView bluebonduk,redbonduk;
	@FXML
	AnchorPane resultpane;
	@FXML
	Label totalkill,ingameinstruction;
	@FXML
	Label killcounterB,killcounterR;

    private int movementVariable = 4;
    @FXML
    private ImageView bonduk10,bonduk11;

    @FXML
    private AnchorPane anchorpane;
    
    

    AnimationTimer timer1 = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {

            if(aPressed.get()) {
            	bonduk10.setLayoutX(bonduk10.getLayoutX() - movementVariable);
            	bluebonduk.setLayoutX(bluebonduk.getLayoutX() - movementVariable);
            	//System.out.println("AAAA");
            }

            if(dPressed.get()){
            	bonduk10.setLayoutX(bonduk10.getLayoutX() + movementVariable);
            	bluebonduk.setLayoutX(bluebonduk.getLayoutX() + movementVariable);
            }
            if(jPressed.get()) {
            	bonduk11.setLayoutX(bonduk11.getLayoutX() - movementVariable);
            	redbonduk.setLayoutX(redbonduk.getLayoutX() - movementVariable);
            }
            if(lPressed.get()) {
            	bonduk11.setLayoutX(bonduk11.getLayoutX() + movementVariable);
            	redbonduk.setLayoutX(redbonduk.getLayoutX() + movementVariable);
            }
            
        }
    };
    void movementSetup1(){
        anchorpane.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.A) {
                aPressed.set(true);
            }

            if(e.getCode() == KeyCode.D) {
                dPressed.set(true);
            }
            if(e.getCode() == KeyCode.J) {
                jPressed.set(true);
            }

            if(e.getCode() == KeyCode.L) {
                lPressed.set(true);
            }
        });

        anchorpane.setOnKeyReleased(e ->{
            if(e.getCode() == KeyCode.A) {
                aPressed.set(false);
            }

            if(e.getCode() == KeyCode.D) {
                dPressed.set(false);
            }
            if(e.getCode() == KeyCode.J) {
                jPressed.set(false);
            }

            if(e.getCode() == KeyCode.L) {
                lPressed.set(false);
            }
        });
    }

    @FXML
	ProgressBar unayesHealth;
	@FXML
	ProgressBar nayemHealth;
	@FXML 
	ProgressBar billahHealth;
	@FXML
	Label remaintime;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		nayemHealth.setStyle ("-fx-accent:green;");
		unayesHealth.setStyle("-fx-accent:green;");
		billahHealth.setStyle("-fx-accent:green;");
		movementSetup1();
		//movementSetup2();

        keyPressed1.addListener(((observableValue, aBoolean, t1) -> {
            if(!aBoolean){
                timer1.start();
            } else {
                timer1.stop();
            }
        }));
        
        SpinnerValueFactory<Integer> valueFactory = 
				new SpinnerValueFactory.IntegerSpinnerValueFactory(20, 300,60, 10);
        
        spinner.setValueFactory(valueFactory);
        currentValue = spinner.getValue();
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

	double nayemHP=300,unayesHP=500,billahHP=1000;
	int unayesCounter=500,nayemCounter=300,billahCounter=1000;
	

	@FXML
	private Spinner<Integer> spinner;
	
	int currentValue;
	
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
	int time;
	@FXML
	Label battletimelabel,leftpart,rightpart;
    double incrementX1 = 1;
    double incrementY1 = 0.25;
    double incrementX2 = 0.9;
    double incrementY2 = 0.2;
    double incrementX3 = 0.8;
    double incrementY3 = 0.15;
	@FXML
	public void startGame(ActionEvent event) throws Exception {
		((AnchorPane)(startbutton.getParent())).getChildren().remove(startbutton);
		((AnchorPane)(rulesbutton.getParent())).getChildren().remove(rulesbutton);
		battletimelabel.setVisible(false);
		spinner.setVisible(false);
		time=spinner.getValue();
		remaintime.setText(time+"");
		time=time*100+99;
		remaintime.setVisible(true);
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
        	time--;
        	int t=time/100;
        	remaintime.setText(t+"");
        	if(time<=0)
        	{
        		timeline1.stop();
                timeline2.stop();
                timeline3.stop();
                int leftkill= Integer.parseInt(killcounterB.getText());
                int rightkill= Integer.parseInt(killcounterR.getText());
                System.out.println(leftkill);
                System.out.println(rightkill);
                if(leftkill>rightkill)
                {
                	leftpart.setText("Winner !");
                	rightpart.setText("Loser !");
                }
                else if(leftkill<rightkill)
                {
                	leftpart.setText("Loser !");
                	rightpart.setText("Winner !");
                }
                else
                {
                   	leftpart.setText("Draw !");
                    rightpart.setText("Draw !"); 
                }
                leftpart.setVisible(true);
                rightpart.setVisible(true);
                
        	}
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
        	if(bonduk11.getLayoutX()>=nayem.getLayoutX() && bonduk11.getLayoutX()+bonduk11.getFitWidth()<=nayem.getLayoutX()+nayem.getWidth())
        	{
        		hitnayem();
        	}

        	
        	if (nayemCounter<=0) {
        		if(bonduk10.getLayoutX()>=nayem.getLayoutX() && bonduk10.getLayoutX()+bonduk10.getFitWidth()<=nayem.getLayoutX()+nayem.getWidth())
            	{
        			String s=killcounterB.getText();
                	int kill = Integer.parseInt(s);
                	kill++;
                	killcounterB.setText(""+kill);
            	}
        		if(bonduk11.getLayoutX()>=nayem.getLayoutX() && bonduk11.getLayoutX()+bonduk10.getFitWidth()<=nayem.getLayoutX()+nayem.getWidth())
            	{
        			String s=killcounterR.getText();
                	int kill = Integer.parseInt(s);
                	kill++;
                	killcounterR.setText(""+kill);
            	}
        		nayemCounter=(int)nayemHP+100;
        		nayemHP=nayemCounter;
        		int x=(int)(Math.random()*1400);
        		nayem.setLayoutX(x);
        		nayem.setLayoutY(-40);
            	nayemHealth.setProgress(1);
            	
            }
  
        	else if (newY>=ground.getLayoutY()-nayem.getHeight()) {
        		nayemCounter=(int)nayemHP+100;
        		nayemHP=nayemCounter;
        		int x=(int)(Math.random()*1400);
        		nayem.setLayoutX(x);
        		nayem.setLayoutY(-40);
            	nayemHealth.setProgress(1);
          
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
        	if(bonduk11.getLayoutX()>=unayes.getLayoutX() && bonduk11.getLayoutX()+bonduk11.getFitWidth()<=unayes.getLayoutX()+unayes.getWidth())
        	{
        		hitunayes();
        	}
        	
        	
        	
        	if (unayesCounter<=0) {
        		
        		if(bonduk10.getLayoutX()>=unayes.getLayoutX() && bonduk10.getLayoutX()+bonduk10.getFitWidth()<=unayes.getLayoutX()+unayes.getWidth())
            	{
        			String s=killcounterB.getText();
                	int kill = Integer.parseInt(s);
                	kill++;
                	killcounterB.setText(""+kill);
            	}
            	if(bonduk11.getLayoutX()>=unayes.getLayoutX() && bonduk11.getLayoutX()+bonduk11.getFitWidth()<=unayes.getLayoutX()+unayes.getWidth())
            	{
            		String s=killcounterR.getText();
                	int kill = Integer.parseInt(s);
                	kill++;
                	killcounterR.setText(""+kill);
            	}
        		unayesCounter=(int)unayesHP+100;
        		unayesHP=unayesCounter;
        		int x=(int)(Math.random()*1500);
        		unayes.setLayoutX(x);
        		unayes.setLayoutY(-90);
        		unayesHealth.setProgress(1);
            	
          
            }
  
        	else if (newY>=ground.getLayoutY()-unayes.getHeight()) {
            	
        		unayesCounter=(int)unayesHP+100;
        		unayesHP=unayesCounter;
        		int x=(int)(Math.random()*1500);
        		unayes.setLayoutX(x);
        		unayes.setLayoutY(-90);
        		unayesHealth.setProgress(1);
          
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
        	if(bonduk11.getLayoutX()>=billah.getLayoutX() && bonduk11.getLayoutX()+bonduk11.getFitWidth()<=billah.getLayoutX()+billah.getWidth())
        	{
        		hitbillah();
        	}
        	if (billahCounter<=0) {
        		
        		if(bonduk10.getLayoutX()>=billah.getLayoutX() && bonduk10.getLayoutX()+bonduk10.getFitWidth()<=billah.getLayoutX()+billah.getWidth())
            	{
        			String s=killcounterB.getText();
                	int kill = Integer.parseInt(s);
                	kill++;
                	killcounterB.setText(""+kill);
            	}
            	if(bonduk11.getLayoutX()>=billah.getLayoutX() && bonduk11.getLayoutX()+bonduk11.getFitWidth()<=billah.getLayoutX()+billah.getWidth())
            	{
            		String s=killcounterR.getText();
                	int kill = Integer.parseInt(s);
                	kill++;
                	killcounterR.setText(""+kill);
            	}
        		billahCounter=(int)billahHP+100;
        		billahHP=billahCounter;
        		int x=(int)(Math.random()*1500);
        		billah.setLayoutX(x);
        		billah.setLayoutY(-160);
        		billahHealth.setProgress(1);
            	
            	
                
            }
  
        	else if (newY>=ground.getLayoutY()-billah.getHeight()) {
        		
        		billahCounter=(int)billahHP+100;
        		billahHP=billahCounter;
        		int x=(int)(Math.random()*1500);
        		billah.setLayoutX(x);
        		billah.setLayoutY(-160);
        		billahHealth.setProgress(1);
          
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
		
		root = (Parent)FXMLLoader.load(getClass().getResource("lasergunMode2.fxml"));
		stg= (Stage)((Node)event.getSource()).getScene().getWindow();
		scene =new Scene(root);
		scene.getStylesheets().add(getClass().getResource("level1.css").toExternalForm());
		stg.setScene(scene);
		//stg.setFullScreen(true);
		stg.show();
	}
	public void showrules(ActionEvent event) throws IOException {
		
		root = (Parent)FXMLLoader.load(getClass().getResource("laserrules2.fxml"));
		stg= (Stage)((Node)event.getSource()).getScene().getWindow();
		scene =new Scene(root);
		scene.getStylesheets().add(getClass().getResource("level1.css").toExternalForm());
		stg.setScene(scene);
		//stg.setFullScreen(true);
		stg.show();
	}

  

	
	
	
}
