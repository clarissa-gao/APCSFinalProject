import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class Instructions extends JPanel implements ActionListener {
	
	Main w;
	
	public Instructions(Main w) {
		this.w = w;
		Font startFont = new Font("Montserrat", Font.CENTER_BASELINE, 40);
		JButton button = new JButton("I N S T R U C T I O N S");
		button.setBackground(new Color(150, 244, 255)); 
		button.setOpaque(true); 
		button.setBorderPainted(false);
		this.setLayout(null);
		button.setFont(startFont);
		button.setBounds(250, 400, 500, 75);
		button.addActionListener(this);
		add(button);
	}
	
	public void actionPerformed(ActionEvent e) {
		w.changePanel();
	}
	
}