import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class Instructions extends JPanel implements ActionListener {
	
	Main w;
	
	public Instructions(Main w) {
		this.w = w;
		Font startFont = new Font("Serif", Font.ITALIC, 50);
		JButton button = new JButton("INSTRUCTIONS");
		button.setBackground(new Color(175, 244, 255)); 
		button.setOpaque(true); 
		button.setBorderPainted(false);
		this.setLayout(null);
		button.setFont(startFont);
		button.setBounds(400, 300, 200, 75);
		button.addActionListener(this);
		add(button);
	}
	
	public void actionPerformed(ActionEvent e) {
		w.changePanel();
	}
	
}