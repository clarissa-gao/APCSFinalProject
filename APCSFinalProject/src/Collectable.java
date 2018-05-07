

import java.awt.*;
import java.util.*;

import processing.core.PImage;

public class Collectable extends Sprite {

	public static final int COLLECTABLE_WIDTH = 40;
	public static final int COLLECTABLE_HEIGHT = 60;

	public Collectable(PImage img, int x, int y) {
		super(img, x, y, COLLECTABLE_WIDTH, COLLECTABLE_HEIGHT);
	}

	// METHODS
	public void moveAroundX(int dir) {
		super.moveByAmount(dir, 0);
		super.moveByAmount(-dir, 0);
	}

	public void moveAroundY(int dir) {
		super.moveByAmount(0, -dir);
		super.moveByAmount(0, dir);
	}
	
	public void collected () {
		
	}
	
	public void act(ArrayList<Shape> obstacles) {
			for (Shape s: obstacles) {
			if (s.intersects(x, y, COLLECTABLE_WIDTH, COLLECTABLE_HEIGHT))
				return;
		}
	}
}
