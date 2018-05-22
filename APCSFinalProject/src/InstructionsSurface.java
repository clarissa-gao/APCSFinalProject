import java.awt.Font;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JButton;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

/**
* @author Alex Wang
* Class that represents the window that displays instructions to play the game. Details on keyboard controls and how to win/lose are here.
*/
public class InstructionsSurface extends PApplet
{
	
	public static final int DRAWING_WIDTH = 800;
	public static final int DRAWING_HEIGHT = 600;
	private Panel panel;
	
	public InstructionsSurface() {
		super();
		panel = new Panel();
	}
	
	public void runMe() {
		runSketch();
	}
	
	public void setup() {
		
	}
	
	public void draw() {
		background(255);
		pushMatrix();

		float ratioX = (float)width/DRAWING_WIDTH;
		float ratioY = (float)height/DRAWING_HEIGHT;

		scale(ratioX, ratioY);
		
		this.fill(0);
		this.textSize(50);
		this.text("INSTRUCTIONS", 250, 75);
		this.textSize(35);
		this.text("COMMANDS", 100, 150);
		this.textSize(15);
		String instructions =
				"Up arrow key: move 1 grid unit forward\n" + 
				"Down arrow key: move 1 grid backward\n" + 
				"Left arrow key: move 1 grid unit to the left\n" + 
				"Right arrow key: move 1 grid unit to the right\n" + 
				"";
		this.text(instructions, 40, 200);
		this.textSize(15);
		String whatToDo = "Use the commands to move Mickey Mouse around the \n" + 
				"grid! Have Mickey collect 6 collectables (shield, water, \n" + 
				"potion) in any amount in any order. Once Mickey has 6 \n" + 
				"collectables, he is finally strong enough to see Minnie \n" + 
				"in all of this chaos, so Minnie will appear on the grid. \n" + 
				"Move mickey to Minnie, and you will have saved their \n" + 
				"relationship! While you do this, however, there are \n" + 
				"certain things you must avoid. Avoid the stationary \n" + 
				"rocks and moving fire. If you hit the black hole, you will \n" + 
				"be transported to another random location. Have Fun!\n" + 
				"";
		this.text(whatToDo, 400, 200);
		this.textSize(35);
		this.text("WHAT TO DO", 450, 150);
		
		this.fill(200);
		this.fill(0);
		this.textSize(15);
		this.text("Click Anywhere to Go Back", 40, 580);
		
		if (panel.getInstructions().isVisible() && this.mousePressed == true)
		{
			panel.getInstructions().setVisible(false);
			panel.getStart().setVisible(true);
		}
		
		popMatrix();
	}


}
