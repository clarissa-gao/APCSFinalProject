

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;

public class DrawingSurface extends PApplet {

	public static final int DRAWING_WIDTH = 800;
	public static final int DRAWING_HEIGHT = 600;

	private Rectangle screenRect;
	private Grid g;

	private Character chara;
	private ArrayList<Shape> obstacles;

	private ArrayList<Integer> keys;
	
	private ArrayList<PImage> assets;

	public DrawingSurface() {
		super();
		assets = new ArrayList<PImage>();
		keys = new ArrayList<Integer>();
		screenRect = new Rectangle(0,0,DRAWING_WIDTH,DRAWING_HEIGHT);
		g = new Grid();
	}


	public void spawnNewChar() {
		chara = new Character(assets.get(0), DRAWING_WIDTH/2-Character.CHARACTER_WIDTH/2,50);
	}
	
	public void runMe() {
		runSketch();
	}

	// The statements in the setup() function 
	// execute once when the program begins
	public void setup() {
		//size(0,0,PApplet.P3D);
		assets.add(loadImage("pokepepe.png"));
		
		spawnNewChar();
	}

	// The statements in draw() are executed until the 
	// program is stopped. Each statement is executed in 
	// sequence and after the last line is read, the first 
	// line is executed again.
	public void draw() {

		// drawing stuff

		background(255);   

		pushMatrix();

		float ratioX = (float)width/DRAWING_WIDTH;
		float ratioY = (float)height/DRAWING_HEIGHT;

		scale(ratioX, ratioY);
		g.draw(this, 50, 50, 500, 500);
		this.stroke(0);
		this.fill(255);
		this.rect(575, 350, 200, 200);
		this.fill(0);
		this.text("Items Collected:", 615, 375);
		
		popMatrix();


		// modifying stuff
		if (isPressed(KeyEvent.VK_LEFT))
		{
			
		}
			
		if (isPressed(KeyEvent.VK_RIGHT))
		{
			
		}
			
		if (isPressed(KeyEvent.VK_UP))
		{
			
		}
			
	}


	public void keyPressed() {
		keys.add(keyCode);
	}

	public void keyReleased() {
		while(keys.contains(keyCode))
			keys.remove(new Integer(keyCode));
	}

	public boolean isPressed(Integer code) {
		return keys.contains(code);
	}


}

