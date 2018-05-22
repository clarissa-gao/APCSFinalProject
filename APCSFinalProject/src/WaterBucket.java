import processing.core.PApplet;
import processing.core.PImage;


/**
 * 
 * @author Alex Wang
 * 
 * Collectable that is a healing potion. This potion can save the player and boost their health if they are about to/almost die
 */
public class WaterBucket extends Collectable{
	
	private PImage img;

	/**
	 * 
	 * @param img - PImage that will represent the Healing Object in a grid
	 * @param x - x location of the collectable (for grid format, NOT in pixels)
	 * @param y - y location of the collectable (for grid format, NOT in pixels)
	 * 
	 * Constructs a Healing object
	 */
	public WaterBucket(PImage img, int x, int y) {
		super(img, x, y);
		this.img=img;
		// TODO Auto-generated constructor stub
	}
	
	public void draw(PApplet m, float x, float y, float width, float height)
	{
		m.image(img, x, y, width, height);
	}

}
