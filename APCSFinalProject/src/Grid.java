import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import processing.core.PApplet;
import processing.core.PImage;

public class Grid 
{
	private int [][] grid;
	private int [][] pictureOrganization;
	// 0 = empty, 1 = player, 2 = destination, -1 = obstruction
	private int row, column, pgLocX, pgLocY; //player grid loc
	private Character player;
	private ArrayList<Obstruction> obstructions;
	private float cellWidth, cellHeight, playerX, playerY;
	private long counter;

	// Constructs an grid with the character at a predetermined starting location, obstructions and
	// collectables placed at random locations, and a randomly generated destination
	public Grid() 
	{
		row = 25;
		column = 25;
		grid = new int [column][row];
		pictureOrganization = new int[column][row];
		obstructions = new ArrayList<Obstruction>();
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
				grid[rx][ry]=-1;
				pictureOrganization[rx][ry] = (int)(Math.random()*2+1); 
			}
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
	
	public int getpgLocX ()
	{
		return pgLocX;
	}
	
	public int getpgLocY()
	{
		return pgLocY;
	}
	
	public void setpgLoxY(int y)
	{
		pgLocY = y;
	}
	
	public void setpgLoxX(int x)
	{
		pgLocX = x;
	}
	
	public float getPlayerX()
	{
		if (playerX <= cellWidth*25)
			return playerX;
		else
			return cellWidth*25;
	}
	
	public void setPlayerY(float y)
	{
		playerY = y;
	}
	
	public float getPlayerY()
	{
		if (playerY <= cellHeight*25)
			return playerY;
		else
			return cellHeight*25;
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
	 * 
	 * @param marker The PApplet used for drawing.
	 * @param x The x pixel coordinate of the upper left corner of the grid drawing. 
	 * @param y The y pixel coordinate of the upper left corner of the grid drawing.
	 * @param width The pixel width of the grid drawing.
	 * @param height The pixel height of the grid drawing.
	 */
	public void draw(PApplet marker, float x, float y, float width, float height) 
	{	
		counter++;
		
		PImage finalLoc = marker.loadImage("treasurechest.png");
		PImage obstruction1 = marker.loadImage("fire.png");
		PImage obstruction2 = marker.loadImage("thorns.jpg");

		
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
				if (grid[i][j]==2) 
				{
					marker.image(finalLoc, j*cellWidth + x, i*cellHeight + y, cellWidth, cellHeight);
				}
				if (grid[i][j]==-1) 
				{
					if (pictureOrganization[i][j] == 1)
						marker.image(obstruction1, j*cellWidth + x, i*cellHeight + y, cellWidth, cellHeight);
					else
					{
						marker.image(obstruction2, j*cellWidth + x, i*cellHeight + y, cellWidth, cellHeight);
					}
				//	obstructions.add(new Rock(marker.loadImage("rock.png"), i, j));
				}
			}
		}
		//for (Obstruction o: obstructions) {
		//	o.draw(marker);
		//}
		player = new Character(marker.loadImage("mouse.png"), (int)playerX, (int)playerY);
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