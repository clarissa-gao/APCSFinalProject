import processing.core.PApplet;

public class HealthSystem extends PApplet
{
	private float x, y, health;
	private int width, height;
	private int lives, counter, otherCounter;
	private boolean healthLeft;
	private int change;
	
	
	public HealthSystem()
	{
		x = 600;
		y = 300;
		
		width = 150;
		height = 75;
		
		lives = 3;
		healthLeft = true;
		change = 0;
	}
	
	public void draw(PApplet marker, float x, float y, float width, float height)
	{
		counter++;
		if(counter%1==0)
		{
			otherCounter++;
		}
		if(width-otherCounter+change <=0)
		{
			healthLeft = false;
			health = 0;
		}
		else
		{
			health = width-otherCounter+change;
		}
		
		marker.noFill();
		marker.rect(x, y, width, height);
		marker.fill(255, 0, 0);
		//System.out.println(change);
		String h = "";
		marker.rect(x, y, health, height);
		if(width-otherCounter+change < width/5)
			h = "About to die";
		else if(width-otherCounter+change < width*2/5)
			h = "Not healthy";
		else if(width-otherCounter+change < width*3/5)
			h = "Ok";
		else if (width-otherCounter+change < width*4/5)
			h = "Healthy";
		else
			h = "Amazing";
		
		marker.fill(0);
		marker.textSize(15);
		marker.text("Health Status: " + h, 585, 270);
		
		marker.fill(0);
	}
	
	public void change (int a)
	{
		change+=a;
	}
	
	public boolean healthLeft()
	{
		return healthLeft;
	}
	
}
