import java.awt.Rectangle;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class InstructionsSurface extends PApplet
{
	public InstructionsSurface() {
		super();
	}
	
	public void runMe() {
		runSketch();
	}
	
	public void setup() {
		
	}
	
	public void draw() {
		this.fill(255, 0, 0);
		this.rect(100, 100, 100, 100);
	}
}
