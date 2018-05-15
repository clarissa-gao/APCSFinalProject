import processing.core.PApplet;

public class HealthSystem extends PApplet
{
	float x, y;
	int width, height;
	int lives, counter, otherCounter;
	boolean healthLeft;
	int change;
	
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
		if(counter%3==0)
		{
			otherCounter++;
		}
		if(width-otherCounter+change <=0)
		{
			healthLeft = false;
		}

		marker.noFill();
		marker.rect(x, y, width, height);
		marker.fill(255, 0, 0);
		marker.rect(x, y, width-otherCounter+change, height);
		
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
