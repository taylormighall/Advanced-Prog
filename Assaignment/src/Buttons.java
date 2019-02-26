import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Buttons extends JPanel{
	
	private final guiFrame guiFrame;
	private final myCanvas myCanvas;
	private final outputArea outputArea;
	private final MNISTDataLoader M;

	
	
	public Buttons(guiFrame guiFrame, outputArea outputArea, myCanvas myCanvas, MNISTDataLoader M) {
		super(new GridLayout(1,3));
		this.guiFrame = guiFrame;
		this.myCanvas = myCanvas;
		this.setPreferredSize(getSize());
		this.outputArea = outputArea;
		this.M = M;
		
		
		    
		
		JButton saveButton = new JButton("Save");
		saveButton.setPreferredSize(new Dimension(10,10));
		add(saveButton);
		JButton clearButton = new JButton("Clear");
		clearButton.setPreferredSize(new Dimension(10,10));
		 add(clearButton);
		 JButton uploadButton = new JButton("Upload");
		 uploadButton.setPreferredSize(new Dimension(10,10));
		 add(uploadButton);
		 JButton Phil = new JButton("Phil");
		 add(Phil);
		saveButton.addActionListener((ActionEvent ae) -> {
	            saveButton();
	        });
		clearButton.addActionListener((ActionEvent ae) -> {
            clearButton();
        });
		Phil.addActionListener((ActionEvent ae) -> {
            Phil();
        });
		
		uploadButton.addActionListener((ActionEvent ae) -> {
            try {
				uploadButton();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
     
		guiFrame.add(this);
	}



	private void clearButton() {
	myCanvas.clear();
		
	}
	
	private void Phil() {
		try {
			outputArea.getPrediction().setIcon(M.currentImg());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//try {
			//outputArea.getPrediction().setIcon(M.currentImg());
		//} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		
	}
	
	private void uploadButton() throws IOException {
		JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        //filter the files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
         //if the user click on save in Jfilechooser
        if(result == JFileChooser.APPROVE_OPTION){
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            BufferedImage myPicture = ImageIO.read(new File(path));
            int image_width = myPicture.getWidth();
            int image_height = myPicture.getHeight();
//            int[] image_data = new int[image_width * image_height];
//            myPicture.getRGB(0, 0, image_width, image_height, image_data, 0, image_width);
//            for(int row =0; row<image_height;row++) {
//            	for (int col=0;col<image_width;col++) {
//            		int pixel = image_data[row * image_width + col];
//            	}
//            }
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
            Image newImg = myPicture.getScaledInstance(outputArea.getUploadDisplay().getWidth(), outputArea.getUploadDisplay().getHeight(), Image.SCALE_SMOOTH);
            ImageIcon image = new ImageIcon(newImg);
            
            outputArea.getUploadDisplay().setIcon(image);
            
        }
         //if the user click on save in Jfilechooser


        else if(result == JFileChooser.CANCEL_OPTION){
            System.out.println("No File Select");
        }
      
    
      
	}
  
	
	



	private void saveButton() {
		guiFrame.saveCanvas();
	
		
//		BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
//     Graphics graphics2D = image.getGraphics();
//     guiFrame.getmyCanvas().drawImage(image);
//      try {
//			ImageIO.write(image,"jpeg", new File("Practice1.jpeg"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
	}
}


