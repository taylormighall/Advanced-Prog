import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Color;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
public class guiFrame extends JFrame {
	
	private final myCanvas myCanvas;
	private final Buttons buttons;
	
	public guiFrame() {
		super("Digit Prediction");
		
		//setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		myCanvas = new myCanvas(this);
		buttons = new Buttons(this, myCanvas);
		
		add(myCanvas);
		add(buttons);
		
		myCanvas.setBackground(Color.white);
		
		 setResizable(false);
	        setVisible(true);
	        //pack();
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
	
	public void clearCanvas(myCanvas myCanvas) {
		
		
		
		
	}



}
