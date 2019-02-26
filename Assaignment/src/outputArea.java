import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class outputArea extends JPanel {
	private final guiFrame guiFrame;
	private final myCanvas myCanvas;
	public JLabel uploadDisplay;
	public JLabel Prediction;
	
	public outputArea(guiFrame guiFrame, myCanvas myCanvas) {
		
		setLayout(new GridLayout(1,3));
	
	
	 this.guiFrame = guiFrame;
	 this.myCanvas = myCanvas;
	 uploadDisplay = new JLabel();
	 uploadDisplay.setPreferredSize(new Dimension(uploadDisplay.getX(), uploadDisplay.getY()));
	 this.add(myCanvas);
	 add(uploadDisplay);
	
	Prediction = new JLabel();
	Prediction.setBackground(Color.blue);
	Prediction.setVisible(true);
	Prediction.setOpaque(true);
	this.add(Prediction);
	guiFrame.add(this);
	}
	
	public JLabel getUploadDisplay() {
		
		return uploadDisplay;
	}
	
	public JLabel getPrediction() {
		return Prediction;
	}

}
