import java.util.ArrayList;

import processing.core.PImage;

public class Protective extends Collectable{
	
	public static final int PRO_WIDTH = 45;
	public static final int PRO_HEIGHT = 45;
	private PImage shield;

	public Protective(PImage img, int x, int y) {
		super(img, x, y);
	}

	public PImage protect(ArrayList<Obstruction> obstruction, int PRO_WIDTH, int PRO_HEIGHT) {
		return shield;
	}
}
