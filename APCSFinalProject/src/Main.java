import javax.swing.*;

import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

import java.awt.*;

public class Main extends PApplet
{

	private JFrame frame;
	private JPanel startScreen, iScreen;
	private JButton bt;
	private JLabel name;
	
	private StartButton start;    
	private Instructions instructions;
	private InstructionsSurface iSurface;
	private DrawingSurface drawSurface;
	
	private PSurfaceAWT.SmoothCanvas processingCanvas, otherCanvas;
	
	public Main() 
	{
		drawSurface = new DrawingSurface();
		iSurface = new InstructionsSurface();
		PApplet.runSketch(new String[] {""}, iSurface);
		PApplet.runSketch(new String[]{""}, drawSurface);
		
		PSurfaceAWT surf = (PSurfaceAWT) drawSurface.getSurface();
		PSurfaceAWT s = (PSurfaceAWT)iSurface.getSurface();
		processingCanvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		otherCanvas = (PSurfaceAWT.SmoothCanvas) s.getNative();
		frame = (JFrame)processingCanvas.getFrame();

		frame.setBounds(100,100, 1000, 600);
		frame.setMinimumSize(new Dimension(100,100));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);

		startScreen = new JPanel();
	    CardLayout cl = new CardLayout();
	    startScreen.setLayout(cl);
	    frame.getContentPane().removeAll();
	    
	    iScreen = new JPanel();
	    iScreen.setLayout(new CardLayout());
	    frame.getContentPane().removeAll();
	    
		start = new StartButton(this); 
	    drawSurface = new DrawingSurface();
	    
	    instructions = new Instructions(this);
	    iSurface = new InstructionsSurface();
//	    
//	    JLabel title = new JLabel("FINAL GAME");
//	    Font f = new Font("Serif", Font.BOLD, 70);
//	    title.setFont(f);
//	    title.setBounds(100, 100, 100, 50);
//	    startScreen.add(title);
	    
	    startScreen.add(start);
	    startScreen.add(processingCanvas);
	    
	    iScreen.add(instructions);
	    iScreen.add(otherCanvas);
	  
//	    name = new JLabel("NAME");
//	    name.setFont(new Font("Sans Serif", Font.BOLD, 80));
//	    name.setBounds(400, -50, 1000, 900);
//	    startScreen.add(name);
//	    
//	    Font startFont = new Font("Sans Serif", Font.BOLD, 70);
//	    bt = new JButton("Start");
//	    bt.setBackground(Color.WHITE);
//	    bt.setFont(startFont);
//	    bt.setBounds(100, 400, 200, 100);
//	    bt.addActionListener(start);
//	    startScreen.add(bt);
	    
	    frame.setLayout(new BorderLayout());
	    
	    frame.add(startScreen);
	    frame.add(iScreen);
	    frame.revalidate();
	    
	}
	

	public static void main(String[] args)
	{
		Main m = new Main();
	}
  
	public void changePanel() {
		((CardLayout)startScreen.getLayout()).next(startScreen);
		processingCanvas.requestFocus();
		
		((CardLayout)iScreen.getLayout()).next(iScreen);
		otherCanvas.requestFocus();
	}
  
}