import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import processing.core.PApplet;

public class Grid 
{
	private int [][] grid;
	// 0 = empty, 1 = player
	private int row, column;
	private Character player;
	private float cellWidth, cellHeight, playerX, playerY;

	// Constructs an empty grid
	public Grid() 
	{
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
		playerX = (int)(column/2 * 24)-25;
		playerY = (int)((row-1)*22.8)+10;
	}
	
	public int getRow()
	{
		return row;
	}
	
	public int getColumn()
	{
		return column;
	}
	
	public void setPlayerX(float x)
	{
		playerX = x;
	}
	
	public float getPlayerX()
	{
		return playerX;
	}
	
	public void setPlayerY(float y)
	{
		playerY = y;
	}
	
	public float getPlayerY()
	{
		return playerY;
	}
	
	/**
	 * Optionally, complete this method to draw the grid on a PApplet.
	 * 
	 * @param marker The PApplet used for drawing.
	 * @param x The x pixel coordinate of the upper left corner of the grid drawing. 
	 * @param y The y pixel coordinate of the upper left corner of the grid drawing.
	 * @param width The pixel width of the grid drawing.
	 * @param height The pixel height of the grid drawing.
	 */
	public void draw(PApplet marker, float x, float y, float width, float height) 
	{	
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
				if (grid[i][j] == 1)
				{
					marker.fill(255, 0, 0);
					marker.rect(j*cellWidth + x, i*cellHeight + y, cellWidth, cellHeight);
				}
			}
		}
		player = new Character(marker.loadImage("mouse.png"), (int)playerX, (int)playerY);
		grid[row-1][column/2] = 1;
		player.draw(marker);
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