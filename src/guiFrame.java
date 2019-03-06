import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.JFrame;
public class guiFrame extends JFrame {
	
	private final myCanvas myCanvas;
	private  outputArea outputArea;
	private MNISTDataLoader M;
	
	
	
	public guiFrame() {
		super("Digit Prediction");
		
		setLayout(new GridLayout(2,1));
		myCanvas = new myCanvas(this, outputArea);
		outputArea = new outputArea(this, myCanvas);
		new Buttons(this, myCanvas, M);
		
		//Initialises the other classes that need to be loaded and sets the overall frame layout
		
		
		this.setPreferredSize(getSize());
		 setResizable(true);
	        setVisible(true);
	       pack();
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	// Sets the other properties of the window
	


	public static void main(String[] args) throws IOException {
        new guiFrame();
        
        //This is the main method that loads an instance of this class, also loading the other classes
        
     
    }


}
