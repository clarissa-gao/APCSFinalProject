import processing.core.PImage;

/**
* @author Karen Li
* Represents an obstruction in the form of a rock
*/
public class Rock extends Obstruction{
//you don't die but you can't walk through it
	public Rock(PImage img, int x, int y) {
		super(img, x, y);
		
	}

}
