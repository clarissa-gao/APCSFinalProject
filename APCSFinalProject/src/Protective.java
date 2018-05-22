import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

/**
* @author clarissa gao
* One of the collectables that is in the form of a shield
*/
public class Protective extends Collectable{
	
	public static final int PRO_WIDTH = 45;
	public static final int PRO_HEIGHT = 45;
	private PImage shield;

	public Protective(PImage img, int x, int y) {
		super(img, x, y);
		shield = img;
	}

	public PImage protect(ArrayList<Obstruction> obstruction, int PRO_WIDTH, int PRO_HEIGHT) {
		return shield;
	}
	
	public void draw(PApplet m, float x, float y, float width, float height)
	{
		m.image(shield, x, y, width, height);
	}
}
