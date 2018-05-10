

import java.awt.*;
import java.util.*;

import processing.core.PImage;

/**
 * 
 * @author Clarissa Gao
 * 
 * Coded 5/10
 *
 *Character that user controls that can move forwards and side to side
 *, it can also interact with different objects on the grid.
 *
 */
public class Character extends Sprite {

	public static final int CHARACTER_WIDTH = 45;
	public static final int CHARACTER_HEIGHT = 45;
	public int collectablesCollected;
	/**
	 * Constructs a character object
	 * @param img - png image that represents the Character object
	 * @param x - X coordinate of location of Character
	 * @param y - Y coordinate of location of Character
	 */
	public Character (PImage img, int x, int y) //x and y can be the grid space
	{
		super(img, x, y, CHARACTER_WIDTH, CHARACTER_HEIGHT);
		collectablesCollected = 0;
	}

	// METHODS
	public void walk(int dir) {
		super.moveByAmount(dir, 0);
	}

	public void jump() {
		super.moveByAmount(0, -20);
	}
	
	public void incrementNumCollectables()
	{
		collectablesCollected++;
	}

	public void act(ArrayList<Shape> obstacles) {
			for (Shape s: obstacles) {
			if (s.intersects(x, y, CHARACTER_WIDTH, CHARACTER_HEIGHT))
				return;
		}
		super.moveByAmount(0,10);
		// FALL (and stop when a platform is hit)
		
	}
}
