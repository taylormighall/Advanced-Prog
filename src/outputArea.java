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
	private final guiFrame guiFrame;
	private final myCanvas myCanvas;
	public static JLabel uploadDisplay;
	public JLabel Prediction;
	
	public outputArea(guiFrame guiFrame, myCanvas myCanvas) {
		
		setLayout(new GridLayout(1,3));
	
	
	 this.guiFrame = guiFrame;
	 this.myCanvas = myCanvas;
	 uploadDisplay = new JLabel();
	 uploadDisplay.setPreferredSize(new Dimension(uploadDisplay.getX(), uploadDisplay.getY()));
	
	myCanvas.setBorder(BorderFactory.createLineBorder(Color.black));
	uploadDisplay.setBorder(BorderFactory.createLineBorder(Color.black));
	 this.add(myCanvas);
	 add(uploadDisplay);
	
	Prediction = new JLabel();
	Prediction.setBackground(Color.black);
	Prediction.setFont(new Font("Serif", Font.PLAIN, 30));
	Prediction.setForeground(Color.white);
	
	Prediction.setVisible(true);
	Prediction.setOpaque(true);
	this.add(Prediction);
	guiFrame.add(this);
	}
	
	public static JLabel getUploadDisplay() {
		
		return uploadDisplay;
	}
	
	public JLabel getPrediction() {
		return Prediction;
	}

}
