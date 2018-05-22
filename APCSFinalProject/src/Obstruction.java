import java.awt.geom.Rectangle2D;
import processing.core.PApplet;
import processing.core.PImage;

/**
* @author Karen Li
* Class that represents the main outline of an obstruction (things that kill the character when in contact). This is an abstract class, so many others extend from it.
*/
public abstract class Obstruction extends Sprite
{
	private int width, height;
	private PImage image;
	private int x, y;
	
	public static final int OB_WIDTH = 30;
	public static final int OB_HEIGHT = 30;
	
	public Obstruction(PImage img, int x, int y) {
		super(img, x, y, OB_WIDTH, OB_HEIGHT);
		image = img;
		this.x = x;
		this.y = y;
	}
	
	public void setImage(PImage p)
	{
		image = p;
	}
	
	public PImage getPic()
	{
		return image;
	}
	
	public void draw(PApplet marker)
	{
		marker.image(image, x, y);
	}
	
	public void draw(PApplet marker, float x, float y, float width, float height)
	{
		//marker.imageMode(marker.CORNER);
		marker.image(image, x, y, width, height);
	}
	
	public int getx()
	{
		return x;
	}
	
	public int gety()
	{
		return y;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
}
