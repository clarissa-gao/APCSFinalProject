import processing.core.PImage;


/**
 *
 * @author Karen Li
 * Coded May 10th, 2018
 * 
 * Obstruction in the form of fire. All of fire's characteristics are the same as an obstruction.
 */
public class Fire extends Obstruction {
//death, spreads across grid, maybe we can do a save the forest type of thing
	
	private PImage p;
	
	/**
	 * 
	 * @param img - PImage that will represent the fire in a grid
	 * @param x - x location of the fire (for grid format, NOT in pixels)
	 * @param y - y location of the fire (for grid format, NOT in pixels)
	 * 
	 * Constructs a Fire object
	 */
	public Fire(PImage img, int x, int y) {
		super(img, x, y);
		p = img;
	}
}
