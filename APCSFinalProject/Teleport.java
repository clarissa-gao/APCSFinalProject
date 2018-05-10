import java.awt.geom.Point2D;
import java.util.ArrayList;

import processing.core.PImage;

public class Teleport extends Collectable{
//teleports char to safe place
	public static final int PRO_WIDTH = 45;
	public static final int PRO_HEIGHT = 45;
	private PImage teleportation;

	public Teleport(PImage img, int x, int y) {
		super(img, x, y);
	}

	public Point2D.Double teleport(ArrayList<Obstruction> obstruction, int PRO_WIDTH, int PRO_HEIGHT) {
		for (Obstruction o: obstruction) {
			if (!o.intersects(x, y, width, height)) {
				return new Point2D.Double(x,y);
			}
	}

}
