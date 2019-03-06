import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MyCanvas extends JPanel {
    private int index = 0;
    private Point[] arr = new Point[100000];
   public BufferedImage image = new BufferedImage(200,200, BufferedImage.TYPE_INT_RGB);
   public BufferedImage outputImage = new BufferedImage(28, 28, BufferedImage.TYPE_INT_RGB);
    ArrayList<Point> points = new ArrayList<Point>();
   
    //Sets up the required variables to draw and save the points of the Canvas image
    
    public MyCanvas(GuiFrame guiFrame) {
        super();
        index = 0;
        this.addMouseListener(new CanvasControl());
        this.addMouseMotionListener(new CanvasControl());
        
        // Adds mouse listeners to the class
        
        setBackground(Color.black);
       guiFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
       guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       guiFrame.setVisible(true);
       this.setPreferredSize(new Dimension(200,200));
        
       //sets up the properties of the Canvas including making it black to align with the training and test data
       

    }
    

public void saveCanvas() {
		
		Dimension size = this.getSize();
        image = new BufferedImage(size.width,size.height , BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        this.paintComponent(g2);

        ImageIcon newImg = new ImageIcon(image);
        BufferedImage outputImage = new BufferedImage(28,
                28, image.getType());
 
        // scales the canvas to the size of the training data
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(image, 0, 0, 28, 28, null);
        
        
        image = outputImage;
        g2d.dispose();
        
        
        OutputArea.getUploadDisplay().setIcon(newImg);
		
		// This function saves what has been drawn on the Canvas to the Upload Display area so it can be predicted
	}

public void upload() throws IOException {
	JFileChooser file = new JFileChooser();
    file.setCurrentDirectory(new File(System.getProperty("user.home")));
    //filter the files
    FileNameExtensionFilter filter = new FileNameExtensionFilter(".Images", "jpg","gif","png");
    file.addChoosableFileFilter(filter);
    int result = file.showSaveDialog(null);
    
    if(result == JFileChooser.APPROVE_OPTION){
        File selectedFile = file.getSelectedFile();
        String path = selectedFile.getAbsolutePath();
        BufferedImage myPicture = ImageIO.read(new File(path));
        
        //if the user click on save in Jfilechooser
        
        int image_width = myPicture.getWidth();
        int image_height = myPicture.getHeight();

        for(int y = 0; y<image_height; y++) {
        	for (int x = 0; x<image_width; x++) {
        		int rgbvalue = myPicture.getRGB(x, y);
        		
        		
        		int red = (rgbvalue >> 16) & 0xff;
        		int green = (rgbvalue >> 8) & 0xff;
        		int blue = (rgbvalue) & 0xff;
        		int grayscale = (int) ((0.3 * red) + (0.59 * green) + (0.11 * blue));
        		int new_pixel_value = 0xFF000000 | (grayscale << 16) | (grayscale <<8)| (grayscale);
        		myPicture.setRGB(x,  y,  new_pixel_value);
        	}
        }
        
        // This section selects the selected image and gets the RGB for each pixel, it then converts it to grayscale
        
        Image newImg = myPicture.getScaledInstance(OutputArea.getUploadDisplay().getWidth(), OutputArea.getUploadDisplay().getHeight(), Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(newImg);
        
        BufferedImage outputImage = new BufferedImage(28,
                28, myPicture.getType());
 
        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(myPicture, 0, 0, 28, 28, null);
        image = outputImage;
        g2d.dispose();
        OutputArea.getUploadDisplay().setIcon(imageIcon);
        //sets the upload display area to the uploaded image
    }
     //if the user click on save in Jfilechooser


    else if(result == JFileChooser.CANCEL_OPTION){
        System.out.println("No File Select");
    }
  
}
  
	@Override
	public void paintComponent(Graphics g) {
		
		
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(30));
        g2.setColor(Color.white);
      
        for (int i = 0; i < index -1; i++) {
          
        	int linex = points.get(i).x; 
        	int liney = points.get(i).y;
        	int line2x = points.get(i ).x;
        	int line2y = points.get(i).y;
        
        g2.drawLine(linex, liney, line2x, line2y);
        
         
        	}	
        }
	// This paints the points that the mouse has been pressed over. The colour is white and stroke size 30 to align with the training data
	
	public BufferedImage getUploadedImg() {
		return image;
	}
	//This returns the image in the upload area for comparison with training data
	
	class CanvasControl extends MouseAdapter{
		
		// This is a class within myCanvas so both MouseAdapter and JPanel can be extended, meaning I only have to use the needed MouseAdapter methods
@Override
    public void mouseDragged(MouseEvent e) {
    	
    	arr[index] = new Point(e.getX(), e.getY());
    	points.add(new Point(e.getX(), e.getY()));
        index++;
        repaint();
        
        // This activates when the mouse is dragged. Points are added to the index array to be drawn
       
        
    }
@Override
    public void mousePressed(MouseEvent e) {
    	
    	
    	
    	arr[index] = new Point(e.getX(), e.getY());
    	points.add(new Point(e.getX(), e.getY()));
        index++;
        repaint();
        
        // This is the same as above but when the mouse is pressed
       
        
        
    }
    
  
	}
	 public void clear() {
	    	
	    	index = 0;
	    	points.clear();
	    	repaint();
	    }
	 // This method repaints the Canvas with nothing in the index, clearing the Canvas for other drawings
} 