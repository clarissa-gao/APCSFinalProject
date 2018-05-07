import java.awt.Font;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JButton;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

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
		String whatToDo = "Move your character with the keyboard commands \n" + 
				"throughout the map (grid) to get to the final\n" + 
				"destination represented with the X! However, \n" + 
				"to actually be able to get to the final destination, \n" + 
				"you will have needed to collect special collectables \n" + 
				"(food, water, potions) which will randomly appear \n" + 
				"on the map one at a time by moving to those \n" + 
				"locations. Once you have gotten all the \n" + 
				"collectables, the final destination will unlock. While \n" + 
				"you are doing this, you must also avoid some \n" + 
				"obstructions (fire, river, rocks, thorns) or else you \n" + 
				"will die. Have fun!\n" + 
				"";
		this.text(whatToDo, 400, 200);
		this.textSize(35);
		this.text("WHAT TO DO", 450, 150);
		
		this.fill(200);
		this.rect(40, 385, 200, 75);
		this.fill(0);
		this.text("Back", 105, 435);
		
		if (panel.getInstructions().isVisible() && this.mousePressed == true)
		{
			if (mouseX >= 40 && mouseX <= 240 && mouseY >=385 && mouseY <= 460)
			{
				panel.getInstructions().setVisible(false);
				panel.getStart().setVisible(true);
			}
		}
		
		popMatrix();
	}


}
