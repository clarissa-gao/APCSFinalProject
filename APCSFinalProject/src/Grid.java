import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * @author Alex Wang
 * Coded May 10, 2018
 * 
 * This class represents the grid of the program. The class has a player (which the user controls), obstructions (which the player must avoid or else he/she will lose the game), collectables (the yellow rectangles that do not do anything yet), and a final location (represented by the treasure chest)
 */
public class Grid extends PApplet
{
	private int [][] grid;
	
	// 0 = empty, 2 = destination, -1 = fire, 3 = potion, 4 = shield, 5 = water bucket, -2 = stone, -3 = hole
	private int row, column, pgLocX, pgLocY, numObstructions, collectsDrawn, numCollected; //player grid loc
	private Character player;
	private float cellWidth, cellHeight, playerX, playerY;
	private long counter, move;
	private boolean obstructionsHaveMoved, isCollected, showFinal;
	private boolean hasBucket;
	private ArrayList<PImage>collectableImages;
	private HealthSystem h;

	// Constructs an grid with the character at a predetermined starting location, obstructions and
	// collectables placed at random locations, and a randomly generated destination
	/**
	 * Constructs a 25 by 25 space grid
	 */
	public Grid() 
	{
		h = new HealthSystem();
		numObstructions=0;collectsDrawn = 0; numCollected = 0;
		obstructionsHaveMoved = false; isCollected = false; hasBucket = false;
		row = 25;
		column = 25;
		grid = new int [column][row];
		for (int i = 0; i < grid.length; i++)
		{
			for (int a = 0; a < grid[0].length; a++)
			{
				grid [i][a] = 0;
			}
		}
		grid[row-1][column/2] = 1;
		
		playerX = (int)(column/2 * 24)-25;
		playerY = (int)((row-1)*22.8)+10;
		pgLocX = column/2;
		pgLocY = row-1;
		
		counter = 0;

		//fire
		for (int i=0; i<15; i++) 
		{
			int rx = (int)(Math.random()*25);
			int ry = (int)(Math.random()*25);
			if (grid[rx][ry] == 0) 
			{
				numObstructions++;
				grid[rx][ry]=-1;
			}
			else 
			{
				i--;
			}
		}
		
		//rock
		for (int i=0; i<10; i++) 
		{
			int rx = (int)(Math.random()*25);
			int ry = (int)(Math.random()*25);
			if (grid[rx][ry] == 0) 
			{
				numObstructions++;
				grid[rx][ry]=-2;
			}
			else 
			{
				i--;
			}
		}
		
		//hole
		for (int i=0; i<10; i++) 
		{
			int rx = (int)(Math.random()*25);
			int ry = (int)(Math.random()*25);
			if (grid[rx][ry] == 0) 
			{
				numObstructions++;
				grid[rx][ry]=-3;
			}
			else 
			{
				i--;
			}
		}
		
		
		
		int x = (int)(Math.random()*25);
		int y = (int)(Math.random()*25);
		boolean hasBeenDrawn = false;
		while(hasBeenDrawn == false)
		{
			if (grid[x][y]==0) 
			{
				int collectable = (int)(Math.random()*3);
				grid[x][y]=collectable+3;
				hasBeenDrawn=true;
			}
		}
		collectableImages = new ArrayList<PImage>();
	}
	
	public void clear(int x, int y)
	{
		grid[x][y] = 0;
	}
	
	public int getRow()
	{
		return row;
	}
	
	public int getColumn()
	{
		return column;
	}
	
	public int getpgLocX ()
	{
		return pgLocX;
	}
	
	public int getpgLocY()
	{
		return pgLocY;
	}
	
	public float getPlayerX()
	{
		return playerX;
	}
	
	public float getPlayerY()
	{
		return playerY;
	}
	
	public void setPlayerX(float x)
	{
		playerX = x;
	}
	
	public void setPlayerY(float y)
	{
		playerY = y;
	}
	
	public void setpgLoxY(int y)
	{
		pgLocY = y;
	}
	
	public void setpgLoxX(int x)
	{
		pgLocX = x;
	}
	
	public int getStatus (int x, int y)
	{
		if (x>=0 && x < row && y >=0 && y < column)
			return grid[y][x];
		else
			return 10;
	}
	
	public void setFinal(int x, int y)
	{
		grid[x][y]=2;
	}
	
	public void setLoc(int x, int y, int val)
	{
		grid[x][y]=val;
	}
	
	public boolean hasBucket()
	{
		return hasBucket;
	}
	
	public HealthSystem getHealthSystem()
	{
		return h;
	}
	
	public ArrayList<PImage> getCollectables()
	{
		return collectableImages;
	}
	
	public void setShowFinal(boolean d)
	{
		showFinal = d;
	}
	
	public boolean isCollected()
	{
		return isCollected;
	}
	
	public int getNumCollected()
	{
		return numCollected;
	}
	
	public int[][] getGrid()
	{
		return grid;
	}
		
	public float getCellWidth()
	{
		return cellWidth;
	}
	
	public float getCellHeight()
	{
		return cellHeight;
	}
	
	
	
	/**
	 * Optionally, complete this method to draw the grid on a PApplet.
	 * @param marker The PApplet used for drawing.
	 * @param x The x pixel coordinate of the upper left corner of the grid drawing. 
	 * @param y The y pixel coordinate of the upper left corner of the grid drawing.
	 * @param width The pixel width of the grid drawing.
	 * @param height The pixel height of the grid drawing.
	 */
	public void draw(PApplet marker, float x, float y, float width, float height) 
	{	
		counter++;
		if(counter%15==0)
			move++;
		
		PImage finalLoc = marker.loadImage("minnie.png");
		PImage potion = marker.loadImage("potion.png");
		PImage shield = marker.loadImage("mshield.gif");
		PImage bucket = marker.loadImage("waterbucket.png");
				
		cellWidth = width/grid.length; //22.8
		cellHeight = height/grid[0].length; //24

		
		marker.stroke(0);
		marker.textSize(30);
		marker.textSize(15);
		
		marker.stroke(0);
		for (int i = 0; i < grid[0].length; i++)
		{
			for (int j = 0; j < grid.length; j++)
			{
				marker.noFill();
				marker.strokeWeight(1);
				marker.stroke(0);
				marker.rect(j*cellWidth + x, i*cellHeight + y, cellWidth, cellHeight);
				if (grid[i][j]==2 && showFinal==true) 
					marker.image(finalLoc, j*cellWidth + x, i*cellHeight + y, cellWidth, cellHeight);
			}
		}

		player = new Character(marker.loadImage("mouse.png"), (int)playerX, (int)playerY);
		player.draw(marker);	
		if (grid[this.pgLocY][this.pgLocX]==3 || grid[this.pgLocY][this.pgLocX]==4 || grid[this.pgLocY][this.pgLocX]==5)
		{
			h.change(20);
			isCollected = true;
			if(grid[this.pgLocY][this.pgLocX]==3)
				collectableImages.add(potion);
			else if(grid[this.pgLocY][this.pgLocX]==4)
				collectableImages.add(shield);
			else if (grid[this.pgLocY][this.pgLocX]==5)
			{
				collectableImages.add(bucket);
				hasBucket = true;
			}
			
			grid[this.pgLocY][this.pgLocX]=0;
			
			isCollected = false;
			boolean hasBeenDrawn = false;
			while(hasBeenDrawn==false)
			{
				int xLoc = (int)(Math.random()*25);
				int yLoc = (int)(Math.random()*25);
				if (grid[xLoc][yLoc]==0) 
				{
					int collectable = (int)(Math.random()*3);
					grid[xLoc][yLoc]=collectable+3;
					hasBeenDrawn=true;
				}
				
			}
			numCollected++;
		}
	}

}