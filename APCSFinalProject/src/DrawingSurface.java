

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * @author Alex Wang
 * Coded May 21th, 2018
 * 
 * The surface of the actual game. This surface has a grid, and also illustrates other data that is part of the game (ex: the items that have been collected and the obstructions). 
 * this area allows the player to move Mickey with the keyboard
 */
public class DrawingSurface extends PApplet {

	public static final int DRAWING_WIDTH = 800;
	public static final int DRAWING_HEIGHT = 600;

	private Grid g;
	private boolean canMove, timeLeft, done, shiftPressed, gameOver;
	private Obstruction o;
	private long counter;
	private int time, numCollectablesDrawn;
	private HealthSystem h;
	private Healing heal;
	private Protective shield;
	private Rock r;
	private Hole hole;
	private WaterBucket wb;
	private TimeSystem t;
	private PImage p;
	
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
		t = new TimeSystem();
		gameOver = false;
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
	
	public void makeHole(){
		hole = new Hole(assets.get(5), 30, 30);
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
		assets.add(loadImage("hole.png"));
		makeObstruc();
		makePotion();
		makeRock();
		makeShield();
		makeWaterBucket();
		makeHole();
		p = loadImage("space.jpg");
	}

	// The statements in draw() are executed until the 
	// program is stopped. Each statement is executed in 
	// sequence and after the last line is read, the first 
	// line is executed again.
	public void draw() 
	{

		background(11, 191, 44);
		
		counter++;

		pushMatrix();

		float ratioX = (float)width/DRAWING_WIDTH;
		float ratioY = (float)height/DRAWING_HEIGHT;

		scale(ratioX, ratioY);
		
		
		int px = g.getpgLocX();
		int py = g.getpgLocY();
		int status = g.getStatus(px, py);
		
		if (gameOver == false || status == 2)
		{
			g.draw(this, 0, 0, 570, 600);
			
			int count = 0;
			if (counter%3 == 0){
				for(int i = 0; i < grid.length; i++)	{
					for(int j = 0; j < grid[0].length; j++){
						if (grid[i][j] == -1)
						{
							count++;
							if (j < grid[0].length-1)
							{
								grid[i][j]=0;
								if(grid[i][j+1]==3 || grid[i][j+1]==4 || grid[i][j+1] == 5)
								{
									boolean drawn = false;
									while(drawn == false) 
									{
										int x = (int)(Math.random()*25);
										int y = (int)(Math.random()*25);
										if(grid[x][y]==0) 
										{
											grid[x][y]=3;
											drawn=true;
										}
									}
								} 
								
								if(grid[i][j+1]==2) 
								{
									if(j+1<24)
										grid[i][j+2]=-1;
									else
										grid[i][0]=-1;
								}
								else
								{
									grid[i][j+1] = -1;
								}
								j++;
							} 
							else if (j == grid[0].length-1) {
								grid[i][j]=0;
								grid[i][0]=-1;
							}
						}
						if(count>25)
						{
							j = 25;
							i = 25;
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
					else if(grid[i][j]==-3)
						hole.draw(this, g.getCellWidth()*j, g.getCellHeight()*i, g.getCellWidth(), g.getCellHeight());
					else if(grid[i][j]==3)
						heal.draw(this, g.getCellWidth()*j, g.getCellHeight()*i, g.getCellWidth(), g.getCellHeight());
					else if(grid[i][j]==4)
						shield.draw(this, g.getCellWidth()*j, g.getCellHeight()*i, g.getCellWidth(), g.getCellHeight());
					else if(grid[i][j]==5)
						wb.draw(this, g.getCellWidth()*j, g.getCellHeight()*i, g.getCellWidth(), g.getCellHeight());
				}
			}
		}
		
		this.textSize(10);
		String s = "Help Mickey find Minnie in all of this\nChaos! Navigate through the grid and\npick up 6 collectables. Avoid the fire and\nrocks. If you land on a black hole, you\nwill be randomly transported to another\nplace. Once you get the collectables\nMinnie will appear and you have\nSAVED THE OTP!!";
		this.text(s, 585, 100);
			
		//draws collectables box
		this.stroke(0);
		this.fill(255);
		this.rect(585, 350, 200, 200);
		this.fill(0);
		this.textSize(20);
		this.text("Items Collected:", 600, 375);
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
		
		//timer
		boolean timeLeft = t.isTimeUp();
		if(timeLeft == false)
		{
			gameOver = true;
		}
		t.draw(this);

		if (status == -3)
		{
			boolean hasMoved = false;
			while(hasMoved == false)
			{
				int x = (int)(Math.random()*25);
				int y = (int)(Math.random()*25);
				if (grid[x][y] == 0)
				{
					g.setpgLoxX(x);
					g.setpgLoxY(y);
					
					g.setPlayerX((x)*g.getCellWidth()-10);
					g.setPlayerY(y*g.getCellHeight()-20);
					
					hasMoved = true;
				}
			}
		}
		
		if(status == -1 || status == -2)
		{
			if(status == -1)
			{
				
			}
			gameOver = true;
		}
		
		this.stroke(0);
		h.draw(this, 585, 275, 200, 50);
		boolean healthLeft = h.healthLeft();
		if(healthLeft==false)
		{
			gameOver = true;
		}
		
		if (status == 2)
		{
			canMove = false;
			this.fill(200);
			this.stroke(200);
			this.rect(60, 210, 400, 225);
			this.fill(0);
			this.textSize(75);
			this.text("YOU WIN!", 70, 285);
			this.fill(255, 0, 0);
			this.rect(110, 320, 300, 75);
			this.fill(0);
			this.textSize(25);
			this.text("Press Anywhere to Quit", 120, 370);
			
			this.text("Game Summary", 160, 290);
			
			
			if (this.mousePressed)
			{
				System.exit(0);
			}
		}
		else if (gameOver)
		{
			canMove = false;
			this.fill(200);
			this.stroke(200);
			this.rect(60, 210, 400, 225);
			this.fill(0);
			this.textSize(75);
			this.text("YOU LOSE!", 70, 285);
			this.fill(255, 0, 0);
			this.rect(110, 320, 300, 75);
			this.fill(0);
			this.textSize(25);
			this.text("Press Anywhere to Quit", 120, 370);

			if (this.mousePressed)
			{
				System.exit(0);
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
		}	
	}

	public void keyReleased() {
		while(keys.contains(keyCode))
			keys.remove(new Integer(keyCode));
	}

	public boolean isPressed(Integer code) {
		return keys.contains(code);
	}
}
