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
	JLabel uploadDisplay;
	
	
	public Buttons(guiFrame guiFrame, outputArea outputArea, myCanvas myCanvas) {
		super(new GridLayout(1,3));
		this.guiFrame = guiFrame;
		this.myCanvas = myCanvas;
		this.setPreferredSize(getSize());
		this.outputArea = outputArea;
		uploadDisplay = new JLabel();
		 uploadDisplay.setPreferredSize(new Dimension(uploadDisplay.getX(), uploadDisplay.getY()));
		    
		   outputArea.add(uploadDisplay);
		    
		
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
		
		uploadButton.addActionListener((ActionEvent ae) -> {
            uploadButton();
        });
     
		guiFrame.add(this);
	}



	private void clearButton() {
	myCanvas.clear();
		
	}
	
	private void uploadButton() {
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
            uploadDisplay.setIcon(ResizeImage(path));
            
        }
         //if the user click on save in Jfilechooser


        else if(result == JFileChooser.CANCEL_OPTION){
            System.out.println("No File Select");
        }
      
    
      
	}
  
	  public ImageIcon ResizeImage(String ImagePath)
	    {
	        ImageIcon MyImage = new ImageIcon(ImagePath);
	        Image img = MyImage.getImage();
	       
	        Image newImg = img.getScaledInstance(uploadDisplay.getWidth(), uploadDisplay.getHeight(), Image.SCALE_SMOOTH);
	        ImageIcon image = new ImageIcon(newImg);
	        
	        
	        return image;
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


