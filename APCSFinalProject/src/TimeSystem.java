import processing.core.PApplet;

/**
*@author Alex Wang
*Represents the timer in the program that counts down fro 90 to 0 seconds
*/
public class TimeSystem extends PApplet
{
	private int x, y, width, height;
	private int counter, time;
	private boolean timeLeft;
	
	public TimeSystem()
	{
		counter = 0;
		time = 0;
		timeLeft = true;
	}
	
	public void draw(PApplet marker)
	{
		counter++;
		if(counter % 3 == 0)
		{
			time++;
		}
		
		if(90-time<=0)
		{
			timeLeft = false;
			marker.stroke(0);
			marker.fill(255);
			marker.rect(580, 15, 210, 50);
			marker.textSize(25);
			marker.fill(0);
			marker.text("TIME'S UP", 620, 50);
		}
		else
		{
			marker.stroke(0);
			marker.fill(255);
			marker.rect(580, 15, 210, 50);
			marker.textSize(25);
			marker.fill(0);
			marker.text("TIME: " + (90-time), 625, 50);
		}
	}
	
	public boolean isTimeUp()
	{
		return timeLeft;
	}

}
