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
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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
		
		
		    
		
		JButton saveButton = new JButton("Save Drawing");
		saveButton.setPreferredSize(new Dimension(10,10));
		add(saveButton);
		JButton clearButton = new JButton("Clear Drawing");
		clearButton.setPreferredSize(new Dimension(10,10));
		 add(clearButton);
		 JButton uploadButton = new JButton("Upload Image");
		 uploadButton.setPreferredSize(new Dimension(10,10));
		 add(uploadButton);
		 JButton Predict = new JButton("Predict Digit");
		 add(Predict);
		saveButton.addActionListener((ActionEvent ae) -> {
	            saveButton();
	        });
		clearButton.addActionListener((ActionEvent ae) -> {
            clearButton();
        });
		Predict.addActionListener((ActionEvent ae) -> {
            try {
				Predict();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	
	private void Predict() throws Exception {
		MNISTDataLoader.importMNIST();
		BufferedImage[] trainingImages = new BufferedImage[6000];
		trainingImages = MNISTDataLoader.getPictureList();
		BufferedImage d_img = myCanvas.getUploadedImg();
		int[]trainingImagesRGB = MNISTDataLoader.getImgListRGB();
		int[] labels = new int[6000];
		labels = MNISTDataLoader.getLabels();
		int trainingDataset[][] = new int[6000][2];
		double[][] distance_label_array = new double[6000][2];
		for (int i = 0; i<6000; i++) {
			trainingDataset[i][0] = trainingImagesRGB[i];
			trainingDataset[i][1] = labels[i];
			BufferedImage train = trainingImages[i];
			BufferedImage myimage = d_img;
			
			ImageIcon d_imgIcon = new ImageIcon(myimage);
			
			if((train.getWidth() != myimage.getWidth()) || (train.getHeight() != myimage.getHeight())) {
				outputArea.getPrediction().setText("Please Draw or Upload an Image");
			throw new Exception("The Images have different dimensions");
			
			}
			
			double pixel_error_sum = 0; 
			for (int row = 0; row< myimage.getHeight(); row++) {
				for(int col = 0; col<myimage.getWidth(); col++) {
					int train_pixel[][] = new int[train.getWidth()][train.getHeight()];
					train_pixel[col][row]= train.getRGB(col, row);
					int myimage_pixel[][] = new int[myimage.getWidth()][myimage.getHeight()];
					myimage_pixel[col][row]= myimage.getRGB(col, row);
					
					pixel_error_sum += ((train_pixel[col][row] - myimage_pixel[col][row]) * (train_pixel[col][row] - myimage_pixel[col][row]) );
				}
			}
			double pixel_error = Math.sqrt(pixel_error_sum);
			distance_label_array[i][0] = labels[i];
			distance_label_array[i][1] = pixel_error;
			
			
			
			
			
		}
		
		double sorted_distance_label_array[][] = new double[6000][1];
		
		
		Arrays.sort(distance_label_array, Comparator.comparingDouble(z -> z[1]));
		sorted_distance_label_array = distance_label_array;
		//System.out.println(sorted_distance_label_array[0][1]);
		int[] closest_k_label_array = new int[100];
		for (int i = 0; i<100; i++) {
			
			closest_k_label_array[i] = (int) sorted_distance_label_array[i][0];
			
		}
		
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i : closest_k_label_array ) {
		    Integer count = map.get(i);
		    map.put(i, count != null ? count+1 : 0);
		}
		Integer popular = Collections.max(map.entrySet(),
			    new Comparator<Map.Entry<Integer, Integer>>() {
			    @Override
			    public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
			        return o1.getValue().compareTo(o2.getValue());
			    }
			}).getKey();
		
		int count = 0;
		for (int i=0; i<closest_k_label_array.length;i++) {
			if (popular == closest_k_label_array[i]) {
				count++;
			}	
			
		}
		System.out.println("The count is " + count);
		count = count * 100;
		double confidence = count/closest_k_label_array.length;
		
		System.out.println("Prediction is " + popular);
		System.out.println(confidence + "% Confidence");
		
		
		outputArea.getPrediction().setText("Prediction is " + popular + "  (" +  confidence + "% Confidence)");
		
		
		
//		
	}
	
	private void uploadButton() throws IOException {
		myCanvas.upload();
    
      
	}
  
	
	



	private void saveButton() {
		myCanvas.saveCanvas();
	
				
	}
	

}


