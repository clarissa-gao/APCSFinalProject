import processing.core.PImage;

/**
 * 
 * @author Karen Li
 * Coded May 10th, 2018
 * 
 * Obstruction in the form of a hole. Hole characteristics are the same as a regular obstruction.
 */
public class Hole extends Obstruction{
//transports character to random location on grid
	/**
	 * 
	 * @param img - PImage that will represent the hole in a grid
	 * @param x - x location of the hole (for grid format, NOT in pixels)
	 * @param y - y location of the hole (for grid format, NOT in pixels)
	 * 
	 * Constructs a Hole object
	 */
	public Hole(PImage img, int x, int y) {
		super(img, x, y);
		
	}

}
