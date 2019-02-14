import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Color;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
public class guiFrame extends JFrame {
	
	private final myCanvas myCanvas;
	private final Buttons buttons;
	private  outputArea outputArea;
	
	public guiFrame() {
		super("Digit Prediction");
		
		setLayout(new GridLayout(2,1));
		
		
		myCanvas = new myCanvas(this, outputArea);
		outputArea = new outputArea(this, myCanvas);
		buttons = new Buttons(this,outputArea, myCanvas);
		
		this.setPreferredSize(getSize());
		
		
		
		
		
		 setResizable(false);
	        setVisible(true);
	        pack();
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	


	public static void main(String[] args) {
        new guiFrame();
    }

	public void saveCanvas() {
		
		Dimension size = myCanvas.getSize();
        BufferedImage image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        myCanvas.paintComponent(g2);
        try
        {
            ImageIO.write(image, "jpg", new File("snapshot.jpg"));
            System.out.println("Panel saved as Image.");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }	
		// TODO Auto-generated method stub
		
	}
	
	



}
