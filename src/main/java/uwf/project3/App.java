package uwf.project3;
/*****************************************************************
Student Name: Ari Le
File Name: App.java
Assignment number: 3

The main javaFX thread that creates all the buttons and the window
to see six horses running
******************************************************************/
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {
	
	ArrayList<Horse> horses = new ArrayList<Horse>(); //the array list of Horse object
	Thread[] threads = new Thread[6]; //an array of Thread
	final int NUM_HORSES = 6; //the number of Horse object
	private ReentrantLock horseLock = new ReentrantLock(); //the ReentrantLock
	private boolean winner = false; //a boolean variable to determine if there's a winning horse or not
	
	public static void main(String[] args) {
		launch(args);
	}

    @Override
    public void start(Stage primaryStage) {
    	
    	//Create GridPane for the main window
    	GridPane gPane = new GridPane();
    	gPane.setMinSize(700, 700);
    	gPane.setPadding(new Insets(10, 10, 10, 10));
    	gPane.setVgap(5); 
    	gPane.setHgap(5);  
    	
    	//draw 6 horses and add them to the GridPane
    	for(int i = 0; i < NUM_HORSES; i++)
    	{
    		if(i == 0)
    		{
    			Horse horse = new Horse(this);
    			gPane.add(horse.drawHorse(), 0, 0);
    			horses.add(horse);
    		}
    		else
    		{
    			Horse horse = new Horse(horses.get(i - 1).getY(), i + 1, this);
    			gPane.add(horse.drawHorse(), 0, i);
    			horses.add(horse);
    		}
    	}
    	
    	//create hbox containing the 3 buttons and add the hbox into the GridPane
    	HBox hbox = addHBox();
    	gPane.add(hbox, 0, 6);
    	
    	Scene scene = new Scene(gPane);
    	
    	primaryStage.setTitle("Horse Race");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * A method to create the run button and to implement how it works
     * @return buttonStart
     */
    private Button createRunButton()
    {
    	Button buttonStart = new Button("Run");
    	buttonStart.setPrefSize(100, 20);
    	buttonStart.setOnAction(new EventHandler<ActionEvent>() {
    		
    		@Override
    		public void handle(ActionEvent event) {
    			for(int i = 0; i < NUM_HORSES; ++i)
    			{
    				setWinner(false);
    				threads[i] = new Thread(horses.get(i));
    				threads[i].start();
    			}
    		}
    	});
    	
    	return buttonStart;
    }
    
    /**
     * A method to create the reset button and to implement how it works
     * @return buttonReset
     */
    private Button createResetButton()
    {
    	Button buttonReset = new Button("Reset");
    	buttonReset.setPrefSize(100, 20);
    	buttonReset.setOnAction(new EventHandler<ActionEvent>() {
    		
    		@Override
    		public void handle(ActionEvent event) {
    			for(int i = 0; i < NUM_HORSES; ++i)
    			{	
    				setWinner(true);
    				horses.get(i).clearHorse(horses.get(i).getX());
    				horses.get(i).setX(20);
    				horses.get(i).setY(20);
    				horses.get(i).drawHorse();
    			}
    		}
    	});
    	
    	return buttonReset;
    }
    
    /**
     * A method to create Quit button and to implement how it works
     * @return buttonQuit
     */
    private Button createQuitButton()
    {
    	Button buttonQuit = new Button("Quit");
    	buttonQuit.setPrefSize(100, 20);
    	buttonQuit.setOnAction(new EventHandler<ActionEvent>() {
    		
    		@Override
    		public void handle(ActionEvent event) {
    			System.exit(0);
    		}
    	});
    	
    	return buttonQuit;
    }
    
    /**
     * A method to add all the buttons into an HBox
     * @return hbox
     */
    private HBox addHBox()
    {
    	HBox hbox = new HBox();
    	hbox.setPadding(new Insets(15, 20, 15, 190));
    	hbox.setSpacing(10);
    	
    	Button buttonStart = createRunButton();
    	
    	Button buttonReset = createResetButton();
    	
    	Button buttonQuit = createQuitButton();
    	
    	hbox.setStyle("-fx-background-color: #336699;");
    	
    	hbox.getChildren().addAll(buttonStart, buttonReset, buttonQuit);
    	
    	return hbox;
    }
    
	/**
	 * winner getter
	 * @return winner
	 */
	public boolean getWinner()
	{
		return winner;
	}
	
	/**
	 * winner setter
	 * @param win
	 */
	public void setWinner(boolean win)
	{
		winner = win;
	}
	
	/**
	 * horseLock getter
	 * @return horseLock
	 */
	public ReentrantLock getLock()
	{
		return horseLock;
	}
}