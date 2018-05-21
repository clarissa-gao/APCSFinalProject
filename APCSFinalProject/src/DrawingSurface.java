

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * @author Alex Wang
 * Coded May 10th, 2018
 * 
 * The surface of the actual game. This surface has a grid, and also illustrates other data that is part of the game (ex: the items that have been collected)
 */
public class DrawingSurface extends PApplet {

	public static final int DRAWING_WIDTH = 800;
	public static final int DRAWING_HEIGHT = 600;

	private Grid g;
	private boolean canMove, timeLeft, done, shiftPressed;
	private Obstruction o;
	private long counter;
	private int time, numCollectablesDrawn;
	private HealthSystem h;
	private Healing heal;
	private Protective shield;
	private Rock r;
	private Hole hole;
	private WaterBucket wb;
	
	private ArrayList<Integer> keys;
	private ArrayList<PImage> assets;
	
	private int [][] grid;

	/**
	 * Constructs a Drawing Surface Class where the grid, the grids components, and other data is drawn on the screen.
	 */
	public DrawingSurface() {
		super();
		assets = new ArrayList<PImage>();
		keys = new ArrayList<Integer>();
		g = new Grid();
		grid = g.getGrid();
		counter = 0;
		time = 0;
		h = g.getHealthSystem();
		numCollectablesDrawn=0;
		canMove = true;
		timeLeft=true;
		done=false;
		shiftPressed = false;
	}


	public void makeObstruc() {
		o = new Fire(assets.get(0), 30, 30);
	}
	
	public void makePotion(){
		heal = new Healing(assets.get(1), 30, 30);
	}
	
	public void makeRock()	{
		r = new Rock(assets.get(3), 30, 30);
	}
	
	public void makeWaterBucket(){
		wb = new WaterBucket(assets.get(4), 30, 30);
	}
	
	public void makeShield(){
		shield = new Protective(assets.get(2), 30, 30);
	}
	
	
	public void runMe() {
		runSketch();
	}
	

	public void setup() {
		assets.add(loadImage("fire.png"));
		assets.add(loadImage("potion.png"));
		assets.add(loadImage("mshield.gif"));
		assets.add(loadImage("stone.png"));
		assets.add(loadImage("waterbucket.png"));
		makeObstruc();
		makePotion();
		makeRock();
		makeShield();
		makeWaterBucket();
	}

	// The statements in draw() are executed until the 
	// program is stopped. Each statement is executed in 
	// sequence and after the last line is read, the first 
	// line is executed again.
	public void draw() 
	{

		background(11, 191, 44);
		
		counter++;
		if(counter%4==0)
			time++; 

		pushMatrix();

		float ratioX = (float)width/DRAWING_WIDTH;
		float ratioY = (float)height/DRAWING_HEIGHT;

		scale(ratioX, ratioY);
		
		//timer
		if(90-time<=0)
			timeLeft=false;
		else
		{
			this.textSize(15);
			this.text("TIME: " + (90-time), 650, 30);
			this.noFill();
			this.rect(580, 5, 200, 50);
		}
		
		g.draw(this, 0, 0, 570, 600);
		
		if (counter%6 == 0){
			for(int i = 0; i < grid.length; i++)	{
				for(int j = 0; j < grid[0].length; j++){
					if (grid[i][j] == -1){
						if (j < grid[0].length-1){
							grid[i][j]=0;
							if(grid[i][j+1]==3 || grid[i][j+1]==4 || grid[i][j+1] == 5){
								boolean drawn = false;
								while(drawn == false) {
									int x = (int)(Math.random()*25);
									int y = (int)(Math.random()*25);
									if(grid[x][y]==0) {
										grid[x][y]=3;
										drawn=true;
									}
								}
							} if(grid[i][j+1]==2) {
								if(j+1<24)
									grid[i][j+2]=-1;
								else
									grid[i][0]=-1;
							}
							else
								grid[i][j+1] = -1;
							j++;
						} else if (j == grid[0].length-1) {
							grid[i][j]=0;
							grid[i][0]=-1;
						}
					}
				}
			}
		}
		
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid[0].length; j++)
			{
				if(grid[i][j] == -1)
					o.draw(this, (j)*g.getCellWidth(), i*g.getCellHeight(), g.getCellWidth(), g.getCellHeight());
				else if(grid[i][j]==-2)
					r.draw(this, g.getCellWidth()*j, g.getCellHeight()*i, g.getCellWidth(), g.getCellHeight());
				else if(grid[i][j]==3)
					heal.draw(this, g.getCellWidth()*j, g.getCellHeight()*i, g.getCellWidth(), g.getCellHeight());
				else if(grid[i][j]==4)
					shield.draw(this, g.getCellWidth()*j, g.getCellHeight()*i, g.getCellWidth(), g.getCellHeight());
				else if(grid[i][j]==5)
					wb.draw(this, g.getCellWidth()*j, g.getCellHeight()*i, g.getCellWidth(), g.getCellHeight());
			}
		}
		
		//draws collectables box
		this.stroke(0);
		this.fill(255);
		this.rect(585, 350, 200, 200);
		this.fill(0);
		this.text("Items Collected:", 625, 375);
		this.text(g.getNumCollected()+"/6", 675, 400);
		if(g.getNumCollected()==6 && done == false)
		{
			int x =(int)(Math.random()*24);
			int y =(int)(Math.random()*24);
			g.setFinal(x, y);
			g.setShowFinal(true);
			done=true;
		}
		ArrayList<PImage> collectableImages = g.getCollectables();
		int c = 0;
		if (collectableImages.size() > 0)
		{
			numCollectablesDrawn= numCollectablesDrawn-collectableImages.size();
			for (PImage a : collectableImages)
			{
				if (c<3)
					this.image(a, 610 + 50*c, 410, 45, 45);
				else if (c>=3 && c < 6)
					this.image(a, 610 + 50*(c-3), 460, 45, 45);
				c++;
			}
		}
		
		//health system
		int px = g.getpgLocX();
		int py = g.getpgLocY();
		int status = g.getStatus(px, py);
		this.stroke(0);
		h.draw(this, 585, 275, 200, 50);
		boolean healthLeft = h.healthLeft();
		if(healthLeft==false)
		{
			status = -1;
		}
		
		if (status == 2)
		{
			canMove = false;
			this.fill(200);
			this.stroke(200);
			this.rect(200, 200, 400, 225);
			this.fill(0);
			this.textSize(75);
			this.text("YOU WIN!", 225, 275);
			this.fill(255, 0, 0);
			this.rect(300, 300, 200, 100);
			this.fill(0);
			this.textSize(40);
			this.text("QUIT", 350, 360);
			if (this.mousePressed)
			{
				if (mouseX >= 300 && mouseX <= 500 && mouseY >= 300 && mouseY <= 400)
				{
					System.exit(0);
				}
			}
		}
		else if ((status == -1 || status == -2) && status != 2)
		{
			canMove = false;
			this.fill(200);
			this.stroke(200);
			this.rect(200, 200, 400, 225);
			this.fill(0);
			this.textSize(75);
			this.text("YOU LOSE!", 210, 275);
			this.fill(255, 0, 0);
			this.rect(300, 300, 200, 100);
			this.fill(0);
			this.textSize(40);
			this.text("QUIT", 350, 360);
			if (this.mousePressed)
			{
				if (mouseX >= 300 && mouseX <= 500 && mouseY >= 300 && mouseY <= 400)
				{
					System.exit(0);
				}
			}
		}
		else if (timeLeft==false && status != 2)
		{
			canMove = false;
			this.fill(200);
			this.stroke(200);
			this.rect(200, 200, 400, 225);
			this.fill(0);
			this.textSize(75);
			this.text("TIMES UP!", 210, 275);
			this.fill(255, 0, 0);
			this.rect(300, 300, 200, 100);
			this.fill(0);
			this.textSize(40);
			this.text("QUIT", 350, 360);
			if (this.mousePressed)
			{
				if (mouseX >= 300 && mouseX <= 500 && mouseY >= 300 && mouseY <= 400)
				{
					System.exit(0);
				}
			}
		}
		popMatrix();
	}

	public void keyPressed() {
		keys.add(keyCode);
		if (key == CODED && canMove == true)
		{
		    if (keyCode == KeyEvent.VK_RIGHT && g.getpgLocX()<g.getColumn()-1) 
		    {	 
		    		g.setPlayerX(g.getPlayerX()+g.getCellWidth());
		    		g.setpgLoxX(g.getpgLocX()+1);
			}
		    else if (keyCode == KeyEvent.VK_LEFT && g.getpgLocX()>0)
		    {
		    		g.setPlayerX((g.getPlayerX()-g.getCellWidth()));
		    		g.setpgLoxX(g.getpgLocX()-1);
		    }
		    else if (keyCode == KeyEvent.VK_UP && g.getpgLocY() > 0)
		    {
		    		g.setPlayerY((g.getPlayerY()-g.getCellHeight()));
		    		g.setpgLoxY(g.getpgLocY()-1);
		    }
		    else if (keyCode == KeyEvent.VK_DOWN && g.getpgLocY()<g.getRow()-1)
		    {
		    		g.setPlayerY((g.getPlayerY()+g.getCellHeight()));
		    		g.setpgLoxY(g.getpgLocY()+1);
		    }
		    
		    if (keyCode == KeyEvent.VK_SHIFT) 
		    {
		    		int a = g.getStatus(g.getpgLocX(), g.getpgLocY()-1);
		    		if(a == -1 && g.hasBucket()==true)
		    		{
		    			//this.text("LALALALALA", 100, 100);
		    			g.setLoc(g.getpgLocX(), g.getpgLocY()-1, 0);
		    		}
		    		
		    }
		}	
	}

	public void keyReleased() {
		while(keys.contains(keyCode))
			keys.remove(new Integer(keyCode));
	}

	public boolean isPressed(Integer code) {
		return keys.contains(code);
	}
	
	public void mouseOverButton(JButton j, Panel p) {
		if (j.contains(mouseX, mouseY)) {
			p.changeColor(j);
		}
	}
}

