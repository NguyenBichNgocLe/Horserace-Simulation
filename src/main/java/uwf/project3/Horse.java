package uwf.project3;
/*****************************************************************
Student Name: Ari Le
File Name: Horse.java
Assignment number: 3

The Horse class that implements Runnable.
This class has methods to draw the horse and clear the horse
and make the horse run to the finish line
******************************************************************/
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Horse implements Runnable {
	
	private double x; //the x coordinate
	private double y; //the y coordinate
	private final double DEFAULT = 20; //the default x and y coordinate
	private final double FINISH = 600; //the finish line
	private int horseNum; //the horse number
	
	private App app; //an App object
	
	private Canvas canvas; //Canvas object
	private GraphicsContext gc; //GraphicsContext to draw on canvas
	
	private Random random; //Random object
	
	/**
	 * Constructor
	 * @param a
	 */
	public Horse(App a)
	{
		x = DEFAULT;
		y = DEFAULT;
		canvas = new Canvas(700, 100);
		gc = canvas.getGraphicsContext2D();
		random = new Random();
		horseNum = 1;
		app = a;
	}
	
	/**
	 * Constructor with parameters
	 * @param num
	 * @param numHorse
	 * @param a
	 */
	public Horse(double num, int numHorse, App a)
	{
		x = DEFAULT;
		y = num;
		canvas = new Canvas(700, 100);
		gc = canvas.getGraphicsContext2D();
		random = new Random();
		horseNum = numHorse;
		app = a;
	}
	
	
	/**
	 * A method to draw the horse
	 * @return canvas
	 */
	public Canvas drawHorse()
	{
		gc.strokeRect(getX(), getY(), 50, 30); //body
        gc.strokeRect(getX() + 50, getY() - 10, 25, 20); //head
        gc.strokeRect(getX(), getY() + 30, 15, 20); //left foot
        gc.strokeRect(getX() + 35, getY() + 30, 15, 20); //right foot
        
        return canvas;
	}
	
	/**
	 * A method to clear the horse
	 * @param num
	 * @return canvas
	 */
	public Canvas clearHorse(double num)
	{
        gc.clearRect(getX(), getY(), 55, 35);
        gc.clearRect(getX() + 50, getY() - 10, 30, 25);
        gc.clearRect(getX(), getY() + 30, 20, 25);
        gc.clearRect(getX() + 35, getY() + 30, 20, 25);
        
        return canvas;
	}

	@Override
	public void run() {
		long startTime = System.currentTimeMillis();
		while(true)
		{
			if(app.getWinner() == true)
				break;
			
			int num = random.nextInt(20);
			double val = num;
			app.getLock().lock();
			clearHorse(getX());
			setX(getX() + val);
			drawHorse();
			if(getX() >= FINISH)
			{
				long endTime = System.currentTimeMillis();
				double t = (endTime - startTime) / 1000.0;
				app.setWinner(true);
				JFrame f = new JFrame();
				JOptionPane.showMessageDialog(f, "The winner is horse #" + getHorseNum() + "\nTime: " + t + " seconds.");
			}
			app.getLock().unlock();
			try {
				Thread.sleep(100);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * x getter
	 * @return x
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * x setter
	 * @param x
	 */
	public void setX(double x) {
		this.x = x;
	}
	
	/**
	 * y getter
	 * @return y
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * y setter
	 * @param y
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * horseNum getter
	 * @return horseNum
	 */
	public int getHorseNum() {
		return horseNum;
	}

	/**
	 * horseNum setter
	 * @param horseNum
	 */
	public void setHorseNum(int horseNum) {
		this.horseNum = horseNum;
	}
	
}
