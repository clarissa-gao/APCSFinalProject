import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import processing.core.PApplet;

public class Grid 
{
	private int [][] grid;
	// 0 = empty, 1 = player, 2 = destination, -1 = obstruction
	private int row, column;
	private Character player;
	private ArrayList<Obstruction> obstructions;
	private float cellWidth, cellHeight, playerX, playerY;

	// Constructs an grid with the character at a predetermined starting location, obstructions and
	// collectables placed at random locations, and a randomly generated destination
	public Grid() 
	{
		row = 25;
		column = 25;
		grid = new int [column][row];
		obstructions = new ArrayList<Obstruction>();
		for (int i = 0; i < grid.length; i++)
		{
			for (int a = 0; a < grid[0].length; a++)
			{
				grid [i][a] = 0;
			}
		}
		int ran = (int) (Math.random()*25);
		grid[0][ran] = 2;
		playerX = (int)(column/2 * 24)-25;
		playerY = (int)((row-1)*22.8)+10;
		for (int i=0; i<5; i++) {
			int rx = (int)(Math.random()*25);
			int ry = (int)(Math.random()*25);
			if (grid[rx][ry]==0) 
				grid[rx][ry]=-1;
			else 
				i--;
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
				if (grid[i][j]==2) {
					marker.fill(0,0,255);
					marker.rect(j*cellWidth + x, i*cellHeight + y, cellWidth, cellHeight);
				}
				if (grid[i][j]==-1) {
					marker.fill(0,255,0);
					marker.rect(j*cellWidth + x, i*cellHeight + y, cellWidth, cellHeight);
				//	obstructions.add(new Rock(marker.loadImage("rock.png"), i, j));
				}
			}
		}
		//for (Obstruction o: obstructions) {
		//	o.draw(marker);
		//}
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