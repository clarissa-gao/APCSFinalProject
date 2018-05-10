import processing.core.PImage;


/**
 * 
 * @author Clarissa Gao
 * Coded May 10th, 2018
 * 
 * Collectable that is a healing potion. This potion can save the player and boost their health if they are about to/almost died.
 */
public class Healing extends Collectable{

	/**
	 * 
	 * @param img - PImage that will represent the Healing Object in a grid
	 * @param x - x location of the collectable (for grid format, NOT in pixels)
	 * @param y - y location of the collectable (for grid format, NOT in pixels)
	 * 
	 * Constructs a Healing object
	 */
	public Healing(PImage img, int x, int y) {
		super(img, x, y);
		// TODO Auto-generated constructor stub
	}

}
