import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class outputArea extends JPanel {
	private final guiFrame guiFrame;
	private final myCanvas myCanvas;
	
	public outputArea(guiFrame guiFrame, myCanvas myCanvas) {
		
		setLayout(new GridLayout(1,1));
	
	 this.guiFrame = guiFrame;
	 this.myCanvas = myCanvas;
	
	JLabel Prediction = new JLabel();
	Prediction.setBackground(Color.blue);
	Prediction.setVisible(true);
	Prediction.setOpaque(true);
	this.add(Prediction);
	
	guiFrame.add(this);
	}

}
