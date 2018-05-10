import java.util.ArrayList;

import processing.core.PImage;
/**
 * 
 * @author Clarissa Gao
 * 
 * Coded 5/10
 *
 *Destructive is a type of collectable object such that 
 *if collected by the Character and intersects an obstruction,
 *it destroys the obstruction and keeps Character safe.
 */
public class Destructive extends Collectable{

	public static final int PRO_WIDTH = 45;
	public static final int PRO_HEIGHT = 45;
	private PImage explosion;
	//private PImage shield;

	/**
	 * Constructs a Destructive object
	 * @param img - png image that represents Destructive object
	 * @param x - X coordinate of location of Destructive object
	 * @param y - Y coordinate of location of Destructive object
	 */
	public Destructive(PImage img, int x, int y) {
		super(img, x, y);
	}

	/**
	 * Destroys/removes obstructions that intersect with the Destructive object
	 * @param obstruction - ArrayList containing all obstruction objects on the grid
	 * @param PRO_WIDTH - width of destructive object
	 * @param PRO_HEIGHT - height of destructive object
	 * @return png image of an explosion
	 */
	public PImage destroy(ArrayList<Obstruction> obstruction, int PRO_WIDTH, int PRO_HEIGHT) {
		obstruction.remove(indexObstruction(obstruction, PRO_WIDTH, PRO_HEIGHT));
		return explosion;
	}
}
