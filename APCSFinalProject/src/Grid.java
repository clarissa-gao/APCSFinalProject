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
	private int row, column, playerX, playerY;

	// Constructs an empty grid
	public Grid() 
	{
		row = 35;
		column = 35;
		grid = new int [column][row];
		for (int i = 0; i < grid.length; i++)
		{
			for (int a = 0; a < grid[0].length; a++)
			{
				grid [i][a] = 0;
				if (i == column-1 && a == row/2)
				{
					grid[i][a] = 1;
					playerX = i;
					playerY = a;
				}
			}
		}
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

		float cellWidth = width/grid.length;
		float cellHeight = height/grid[0].length;
		
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
	}

}