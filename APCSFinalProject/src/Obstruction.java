import java.awt.geom.Rectangle2D;
import processing.core.PApplet;
import processing.core.PImage;

public abstract class Obstruction extends Sprite
{
	private int width, height;
	private PImage image;
	
	public static final int OB_WIDTH = 30;
	public static final int OB_HEIGHT = 30;
	
	public Obstruction(PImage img, int x, int y) {
		super(img, x, y, OB_WIDTH, OB_HEIGHT);
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
