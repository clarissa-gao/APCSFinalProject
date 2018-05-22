import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import processing.awt.PSurfaceAWT;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * @author Alex Wang
 * Class is the main class with the main method. This class represents the starting screen, and holds data from all of the other classes.
 */
public class Panel extends JPanel implements ActionListener
{
	//private JComboBox<String> combo;
	private JButton start, instructions;
	private static Panel openingScreen, instructionsScreen;
	private static JFrame initial, w, i;
	private JLabel name;
	private static DrawingSurface drawing = new DrawingSurface();
	private PImage background;
	
	public Panel()
	{
		super();
		
		setLayout(null);
		
		setBackground(new Color(11, 191, 44));
	    
		Font startFont = new Font("Serif", Font.ITALIC, 50);
	    start = new JButton("Start");
		start.setBackground(new Color(175, 244, 255)); 
		start.setOpaque(true); 
		start.setBorderPainted(false);
		this.setLayout(null);
	    start.setFont(startFont);
	    start.setBounds(375, 150, 300, 90);
	    start.addActionListener(this);
	    add(start);
	    
	    instructions = new JButton("Instructions");
	    instructions.setBackground(new Color(175, 244, 255));
	    instructions.setOpaque(true);
	    instructions.setBorderPainted(false);
	    setLayout(null);
	    instructions.setFont(startFont);
	    instructions.setBounds(375, 275, 300, 90);
	    instructions.addActionListener(this);
	    add(instructions);
	    
	    name = new JLabel("SAVE MICKEY AND MINNIE");
	    name.setFont(new Font("Serif", Font.BOLD, 50));
	    name.setBounds(175, -375, 1000, 900);
	    add(name);
	}
	
	public void PaintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		Font points = new Font ("San Serif", Font.BOLD, 100);
		g.setFont(points);
		g.drawString("OUR COOL FINAL PROJECT", 0, 100);
	}
  
	public void actionPerformed(ActionEvent e) 
	{			
	    JButton b = (JButton)e.getSource();
		if(b == start)
		{
			initial.setVisible(false);
			i.setVisible(false);
			w.setVisible(true);
			initial.dispose();	
		}
		else if (b == instructions)
		{
			initial.setVisible(false);
			w.setVisible(false);
			i.setVisible(true);
			initial.dispose();
		}
	}
	
	public JFrame getInstructions()
	{
		return i;
	}
	
	public JFrame getStart()
	{
		return initial;
	}
	
	public static void main(String[] args)
	{
		//DrawingSurface drawing = new DrawingSurface();
		PApplet.runSketch(new String[]{""}, drawing);
		PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		w = new JFrame("Game");
		w = (JFrame)canvas.getFrame();
	    w.setBounds(100, 100, 1000, 600);
	    w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    openingScreen = new Panel();
	    instructionsScreen = new Panel();
	    w.setResizable(true); 
	    //w.setVisible(false);
	    
	    
	    InstructionsSurface a = new InstructionsSurface();
		PApplet.runSketch(new String[]{""}, a);
		PSurfaceAWT s = (PSurfaceAWT) a.getSurface();
		PSurfaceAWT.SmoothCanvas c = (PSurfaceAWT.SmoothCanvas) s.getNative();
	    i = new JFrame("Instructions");
	    i = (JFrame)c.getFrame();
	    i.setBounds(100, 100, 1000, 600);
	    i.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    i.setResizable(true);
	    //i.setVisible(false);
	    
	    initial = new JFrame("Start Screen");
	    initial.setBounds(100, 100, 1000, 600);
	    initial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    initial.setResizable(true);
	    initial.add(openingScreen);
	    initial.add(instructionsScreen);
	    initial.setVisible(true);
	}
	


	public void changeColor(JButton j) {
		j.setBackground(new Color((int)j.getBackground().getRed()-10, (int)j.getBackground().getGreen()-10, (int)j.getBackground().getBlue()-10));
	}
}
