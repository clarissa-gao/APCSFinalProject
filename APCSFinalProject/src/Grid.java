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
	
	// 0 = empty, 2 = destination, -1 = obstruction, 3 = potion, 4 = shield
	private int row, column, pgLocX, pgLocY, numObstructions, collectsDrawn, numCollected; //player grid loc
	private Character player;
	private Collectable[] collectables;
	private float cellWidth, cellHeight, playerX, playerY;
	private long counter, move;
	private boolean obstructionsHaveMoved, isCollected, showFinal;

	// Constructs an grid with the character at a predetermined starting location, obstructions and
	// collectables placed at random locations, and a randomly generated destination
	/**
	 * Constructs a 25 by 25 space grid
	 */
	public Grid() 
	{
		numObstructions=0;collectsDrawn = 0; numCollected = 0;
		obstructionsHaveMoved = false; isCollected = false;
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
		int ran = (int) (Math.random()*25);
		grid[0][ran] = 2;
		playerX = (int)(column/2 * 24)-25;
		playerY = (int)((row-1)*22.8)+10;
		pgLocX = column/2;
		pgLocY = row-1;
		
		counter = 0;

		for (int i=0; i<5; i++) 
		{
			int rx = (int)(Math.random()*25);
			int ry = (int)(Math.random()*25);
			if (grid[rx][ry]==0) 
			{
				numObstructions++;
				grid[rx][ry]=-1;
			}
			else 
			{
				i--;
			}
		}
		collectables = new Collectable[6];//6 collectables??
		int x = (int)(Math.random()*25);
		int y = (int)(Math.random()*25);
		if (grid[x][y]==0) 
		{
			int collectable = (int)(Math.random()*2);
			grid[x][y]=collectable+3;
		}
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
		PImage finalLoc = marker.loadImage("treasurechest.png");
		PImage potion = marker.loadImage("potion.png");
		PImage shield = marker.loadImage("shield.jpg");
				
		cellWidth = width/grid.length; //24
		cellHeight = height/grid[0].length; //22.8
		//marker.background(255);
		
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
				marker.stroke(150);
				marker.rect(j*cellWidth + x, i*cellHeight + y, cellWidth, cellHeight);
				if (grid[i][j]==2 && showFinal==true) 
					marker.image(finalLoc, j*cellWidth + x, i*cellHeight + y, cellWidth, cellHeight);
				if (grid[i][j] == 3)
					marker.image(potion, j*cellWidth + x, i*cellHeight + y, cellWidth, cellHeight);
				if (grid[i][j] == 4)
					marker.image(shield, j*cellWidth + x, i*cellHeight + y, cellWidth, cellHeight);
			}
		}

		player = new Character(marker.loadImage("mouse.png"), (int)playerX, (int)playerY);
		player.draw(marker);	
		if (grid[this.pgLocY][this.pgLocX]==3 || grid[this.pgLocY][this.pgLocX]==4)
		{
			isCollected = true;
//			if (grid[this.pgLocY][this.pgLocX]==3)
//			{
//				collectables[numCollected] = new Healing(potion, 600+numCollected*45, 425);
//			}
//			if (grid[this.pgLocY][this.pgLocX]==4)
//			{
//				collectables[numCollected] = new Healing(shield, 600+numCollected*45, 425);
//			}
			grid[this.pgLocY][this.pgLocX]=0;
			isCollected = false;
			int xLoc = (int)(Math.random()*25);
			int yLoc = (int)(Math.random()*25);
			if (grid[xLoc][yLoc]==0) 
			{
				int collectable = (int)(Math.random()*2);
				grid[xLoc][yLoc]=collectable+3;
			}
			numCollected++;
		}
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
	
	public Collectable[] getCollectables()
	{
		return collectables;
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

}