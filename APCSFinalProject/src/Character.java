

import java.awt.*;
import java.util.*;

import processing.core.PImage;

public class Character extends Sprite {

	public static final int CHARACTER_WIDTH = 45;
	public static final int CHARACTER_HEIGHT = 45;

	public Character (PImage img, int x, int y) //x and y can be the grid space
	{
		super(img, x, y, CHARACTER_WIDTH, CHARACTER_HEIGHT);
	}

	// METHODS
	public void walk(int dir) {
		super.moveByAmount(dir, 0);
	}

	public void jump() {
		super.moveByAmount(0, -20);
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