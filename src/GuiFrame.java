import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.JFrame;
public class GuiFrame extends JFrame {
	
	private MyCanvas myCanvas;
	private  OutputArea outputArea;
	private MNISTDataLoader M;
	
	
	
	public GuiFrame() {
		super("Digit Prediction");
		
		setLayout(new GridLayout(2,1));
		myCanvas = new MyCanvas(this);
		myCanvas = new MyCanvas(this, outputArea);
		outputArea = new OutputArea(this, myCanvas);
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
        new GuiFrame();
        
        //This is the main method that loads an instance of this class, also loading the other classes
        
     
    }


}
