import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
public class guiFrame extends JFrame {
	
	private final myCanvas myCanvas;
	private final Buttons buttons;
	private  outputArea outputArea;
	private MNISTDataLoader M;
	
	public guiFrame() {
		super("Digit Prediction");
		
		setLayout(new GridLayout(2,1));
		
		try {
			M = new MNISTDataLoader();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		myCanvas = new myCanvas(this, outputArea);
		outputArea = new outputArea(this, myCanvas);
		buttons = new Buttons(this, outputArea, myCanvas, M);
		
		
		this.setPreferredSize(getSize());
		
		
	
		
		
		 setResizable(false);
	        setVisible(true);
	       // pack();
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	


	public static void main(String[] args) throws IOException {
        new guiFrame();
        
     
    }

	
	
	



}
