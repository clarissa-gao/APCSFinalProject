import java.util.ArrayList;

import processing.core.PImage;

public class Destructive extends Collectable{

	public static final int PRO_WIDTH = 45;
	public static final int PRO_HEIGHT = 45;
	private PImage explosion;
	//private PImage shield;

	public Destructive(PImage img, int x, int y) {
		super(img, x, y);
	}

//	public PImage destroy(ArrayList<Obstruction> obstruction, int PRO_WIDTH, int PRO_HEIGHT) {
//		obstruction.remove(indexObstruction(obstruction, PRO_WIDTH, PRO_HEIGHT));
//		return explosion;
//	}
}
