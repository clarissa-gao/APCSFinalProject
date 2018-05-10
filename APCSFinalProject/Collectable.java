

import java.awt.*;
import java.util.*;

import processing.core.PImage;

public class Collectable extends GameObject {

	public static final int COLLECTABLE_WIDTH = 40;
	public static final int COLLECTABLE_HEIGHT = 60;
	private boolean collected;
	private PImage image;

	public Collectable(PImage img, int x, int y) {
		super(img, x, y, COLLECTABLE_WIDTH, COLLECTABLE_HEIGHT);
		//boolean collected = collect;
	}

	// METHODS
	public void moveAroundX(int dir) {
		while (!collected) {
		super.moveByAmount(dir, 0);
		super.moveByAmount(-dir, 0);
		}
	}

	public void moveAroundY(int dir) {
		while (!collected) {
		super.moveByAmount(0, -dir);
		super.moveByAmount(0, dir);
		}
	}
	
	public void setImage(PImage p)
	{
		image = p;
	}
	
	public PImage getPic()
	{
		return image;
	}

}


