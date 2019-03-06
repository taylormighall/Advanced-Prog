import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class outputArea extends JPanel {
	
	public static JLabel uploadDisplay;
	public static JLabel Prediction;
	
	
	public outputArea(guiFrame guiFrame, myCanvas myCanvas) {
		
		setLayout(new GridLayout(1,3));
	
	 uploadDisplay = new JLabel();
	 uploadDisplay.setPreferredSize(new Dimension(uploadDisplay.getX(), uploadDisplay.getY()));
	 uploadDisplay.setBorder(BorderFactory.createLineBorder(Color.white));
	 
	 //Creates and sets properties for the uploadDisplay JLabel
	
	 myCanvas.setBorder(BorderFactory.createLineBorder(Color.white));
	 
	 // Sets a border for the myCanvas Panel

	 add(myCanvas);
	 add(uploadDisplay);
	 
	 // Adds the myCanvas Panel and uploadDisplay JLabel to this panel
	
	Prediction = new JLabel();
	Prediction.setBackground(Color.black);
	Prediction.setFont(new Font("Serif", Font.PLAIN, 30));
	Prediction.setForeground(Color.white);
	Prediction.setVisible(true);
	Prediction.setOpaque(true);
	this.add(Prediction);
	
	//Creates and configures the Prediction JLabel, where the prediction is shown
	
	guiFrame.add(this);
	
	// Adds this Panel to the JFrame
	}
	
	public static JLabel getUploadDisplay() {
		
		return uploadDisplay;
	}
	//Returns the uploadDisplay JLabel
	
	public static JLabel getPrediction() {
		return Prediction;
	}
	//Returns the Prediction JLabel

}
