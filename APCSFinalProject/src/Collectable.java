

import java.awt.*;
import java.util.*;

import processing.core.PImage;
/**
 * 
 * @author Clarissa Gao
 *
 *Collectable is the superclass for all collectable objects represented by a png image
 *that enables the character to collect it and have interactions with other objects with it.
 */
public class Collectable extends Sprite {

	public static final int COLLECTABLE_WIDTH = 40;
	public static final int COLLECTABLE_HEIGHT = 60;
	private boolean collected;
	private PImage image;
	private int xLoc, yLoc;

	/**
	 * Constructs a Collectable object
	 * @param img - 
	 * @param x
	 * @param y
	 */
	public Collectable(PImage img, int x, int y) {
		super(img, x, y, COLLECTABLE_WIDTH, COLLECTABLE_HEIGHT);
		//boolean collected = collect;
		collected = false;
		xLoc = x;
		yLoc = y;
	}
	
	public void setStatus(boolean isCollected)
	{
		collected = isCollected;
	}

	/**
	 * Moves collectable object from side to side
	 * @param dir - range of motion in the x direction the collectable will move
	 */
	public void moveAroundX(int dir) {
		int to = dir;
		int from = dir;
		while (!collected && to!= 0) {
			super.moveByAmount(1, 0);
			to--;
		}
		while (!collected && from!= 0) {
			super.moveByAmount(-1, 0);
			from--;
			}
		to = dir;
		from = dir;
	}

	/**
	 * Moves collectable object up and down
	 * @param dir - range of motion in the y direction the collectable will move 
	 */
	public void moveAroundY(int dir) {
		int to = dir;
		int from = dir;
		while (!collected && to!= 0) {
			super.moveByAmount(0,-1);
			to--;
		}
		while (!collected && from!= 0) {
			super.moveByAmount(0,1);
			from--;
			}
		to = dir;
		from = dir;
	}
	
	/**
	 * Sets image to png image
	 * @param p - a png image that represents the collectable object
	 */
	public void setImage(PImage p)
	{
		image = p;
	}
	
	
	/**
	 * Returns png image that represents collectable object
	 */
	public PImage getPic()
	{
		return image;
	}
	
}


