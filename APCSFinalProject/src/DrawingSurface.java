

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
 * Coded May 10th, 2018
 * 
 * The surface of the actual game. This surface has a grid, and also illustrates other data that is part of the game (ex: the items that have been collected)
 */
public class DrawingSurface extends PApplet {

	public static final int DRAWING_WIDTH = 800;
	public static final int DRAWING_HEIGHT = 600;

	private Rectangle screenRect;
	private Grid g;
	private PImage image;
	private Character player;
	private boolean canMove, timeLeft;
	private Obstruction o;
	private long startTime, endTime, counter, move;
	private int time, numCollectablesDrawn;
	private HealthSystem h;
	
//	private Mario mario;
	private ArrayList<Shape> obstacles;

	private ArrayList<Integer> keys;
	
	private ArrayList<PImage> assets;

	/**
	 * Constructs a Drawing Surface Class where the grid, the grids components, and other data is drawn on the screen.
	 */
	public DrawingSurface() {
		super();
		assets = new ArrayList<PImage>();
		keys = new ArrayList<Integer>();
		screenRect = new Rectangle(0,0,DRAWING_WIDTH,DRAWING_HEIGHT);
		g = new Grid();
		canMove = true;
		//image = this.loadImage("forest.png");
		startTime = this.second();
		counter = 0; move = 0;
		time = 0;
		timeLeft=true;
		h = new HealthSystem();
		numCollectablesDrawn=0;
	}


	public void makeObstruc() {
//		mario = new Mario(assets.get(0), DRAWING_WIDTH/2-Mario.MARIO_WIDTH/2,50);
		o = new Fire(assets.get(0), 30, 30);
	}
	
	public void runMe() {
		runSketch();
	}
	
	// The statements in the setup() function 
	// execute once when the program begins
	public void setup() {
		//size(0,0,PApplet.P3D);
		//assets.add(loadImage("mario.png"));
		//spawnNewMario();
		//assets.add(loadImage("mouse.png"));
		assets.add(loadImage("fire.png"));
		makeObstruc();
	}

	// The statements in draw() are executed until the 
	// program is stopped. Each statement is executed in 
	// sequence and after the last line is read, the first 
	// line is executed again.
	public void draw() 
	{
		// drawing stuff
		counter++;
		if(counter%10==0)
		{
			time++;
		}
		
		background(255); 

		pushMatrix();

		float ratioX = (float)width/DRAWING_WIDTH;
		float ratioY = (float)height/DRAWING_HEIGHT;

		scale(ratioX, ratioY);
		
		//time
		if(90-time<=0)
		{
			timeLeft=false;
		}
		else
		{
			this.textSize(15);
			this.text("TIME: " + (90-time), 650, 30);
			this.noFill();
			this.rect(580, 5, 200, 50);
		}
		
		
		int[][]grid = g.getGrid();
		
		g.draw(this, 0, 0, 570, 600);
		
		if (counter%15 == 0)
		{
			move++;
		}
		
		float cellWidth = 570/grid.length; 
		float cellHeight = 600/grid[0].length; 
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid[0].length; j++)
			{
				if(grid[i][j] == -1 && (cellWidth/2)+(j+move)*cellWidth < cellWidth*25 && j+1<25)
				{
					o.draw(this, (cellWidth/2)+(j+move)*cellWidth, i*cellHeight, cellWidth, cellHeight);
				}
//				if((cellWidth/2)+(j+move)*cellWidth > cellWidth*25)
//				{
//					o.draw(this, (cellWidth/2)+(0+move)*cellWidth, i*cellHeight, cellWidth, cellHeight);
//					grid[0][i]= -1;
//				}
//				if(grid[i][j] == -1 && counter%15 == 0 &&j+1<25)
//				{
//					o.draw(this, (cellWidth/2)+(j+move)*cellWidth, i*cellHeight, cellWidth, cellHeight);
//					grid[i][(int)(j+1)] = -1;
//					grid[i][j] = 0;
//					j++;
//				}
			}
		}
		
		this.stroke(0);
		this.fill(255);
		this.rect(585, 350, 200, 200);
		this.fill(0);
		this.text("Items Collected:", 625, 375);
		this.text(g.getNumCollected()+"/6", 675, 400);
		if(g.getNumCollected()==6)
		{
			g.setShowFinal(true);
		}
		ArrayList<PImage> collectableImages = g.getCollectables();
		int c = 0;
		if (collectableImages.size() > 0 && c<6)
		{
			numCollectablesDrawn= numCollectablesDrawn-collectableImages.size();
			for (PImage a : collectableImages)
			{
				if (c<3)
					this.image(a, 610 + 50*c, 410, 45, 45);
				else
					this.image(a, 610 + 50*(c-3), 460, 45, 45);
				c++;
			}
		}
		
		//health system
		h.draw(this, 585, 275, 200, 50);
		
		int px = g.getpgLocX();
		int py = g.getpgLocY();
		
		int status = g.getStatus(px, py);
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
		else if (status == -1)
		{
			//canMove = false;
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
		
		if (timeLeft==false)
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
		    if (keyCode == RIGHT && g.getpgLocX()<g.getColumn()-1) 
		    {	 
		    		g.setPlayerX(g.getPlayerX()+g.getCellWidth());
		    		g.setpgLoxX(g.getpgLocX()+1);
			}
		    else if (keyCode == LEFT && g.getpgLocX()>0)
		    {
		    		g.setPlayerX((g.getPlayerX()-g.getCellWidth()));
		    		g.setpgLoxX(g.getpgLocX()-1);
		    }
		    else if (keyCode == UP && g.getpgLocY() > 0)
		    {
		    		g.setPlayerY((g.getPlayerY()-g.getCellHeight()));
		    		g.setpgLoxY(g.getpgLocY()-1);
		    }
		    else if (keyCode == DOWN && g.getpgLocY()<g.getRow()-1)
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

