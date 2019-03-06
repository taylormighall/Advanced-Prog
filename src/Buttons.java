import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Buttons extends JPanel{
	
	private final myCanvas myCanvas;

	public Buttons(guiFrame guiFrame, myCanvas myCanvas, MNISTDataLoader M) {
		super(new GridLayout(1,3));
		this.myCanvas = myCanvas;
		
		// Initialises myCanvas and sets the layout of the buttons JPanel
		    
		
		JButton saveButton = new JButton("Save Drawing");
		saveButton.setPreferredSize(new Dimension(10,10));
		saveButton.setBorder(BorderFactory.createLineBorder(Color.black));
		add(saveButton);
		JButton clearButton = new JButton("Clear Drawing");
		clearButton.setPreferredSize(new Dimension(10,10));
		clearButton.setBorder(BorderFactory.createLineBorder(Color.black));
		 add(clearButton);
		 JButton uploadButton = new JButton("Upload Image");
		 uploadButton.setPreferredSize(new Dimension(10,10));
		 uploadButton.setBorder(BorderFactory.createLineBorder(Color.black));
		 add(uploadButton);
		 JButton Predict = new JButton("Predict Digit");
		 Predict.setBorder(BorderFactory.createLineBorder(Color.black));
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
	//Creates Buttons,assigns action listeners for them and adds them to the JFrame



	private void clearButton() {
	myCanvas.clear();
	//The Canvas Clear Button calls the Clear function from the myCanvas Class
	}
	

	private void Predict() throws Exception {
		
		//The Prediction method predicts the handwritten digit
		
		MNISTDataLoader.importMNIST();
		BufferedImage[] trainingImages = new BufferedImage[60000];
		trainingImages = MNISTDataLoader.getPictureList();
		BufferedImage d_img = myCanvas.getUploadedImg();
		int[] labels = new int[60000];
		labels = MNISTDataLoader.getLabels();
		
		//Loads the MNIST training images, their labels and the current image in the UploadedImg JLabel
		
		double[][] distance_label_array = new double[60000][2];
		
		for (int i = 0; i<60000; i++) {
			BufferedImage train = trainingImages[i];
			BufferedImage myimage = d_img;
		
		//This Loop assigns	the current training image to train so it can be used to compare to the users image
			
			
			if((train.getWidth() != myimage.getWidth()) || (train.getHeight() != myimage.getHeight())) {
				outputArea.getPrediction().setText("         Please Draw or Upload an Image");
			throw new Exception("The Images have different dimensions");
			
			}
			// Above checks whether the training image and users image are the same size so they can be compared correctly
			
			
			double pixel_error_sum = 0; 
			for (int row = 0; row< myimage.getHeight(); row++) {
				for(int col = 0; col<myimage.getWidth(); col++) {
					int train_pixel;
					train_pixel= train.getRGB(col, row);
					int myimage_pixel;
					myimage_pixel= myimage.getRGB(col, row);
					
					pixel_error_sum += Math.sqrt((Math.pow(myimage_pixel - train_pixel, 2) ));
					
				}
			}
			// These loops calculate the Euclidean distance between each pixel in the two images and add them together
			
			distance_label_array[i][0] = labels[i];
			distance_label_array[i][1] = pixel_error_sum;
			
			//This assigns the overall Euclidean distance difference and corresponding label to a 2d array
			
			
			
			
		}
	
		double sorted_distance_label_array[][] = new double[60000][2];
		
		
		Arrays.sort(distance_label_array, Comparator.comparingDouble(arr -> arr[1]));
		sorted_distance_label_array = distance_label_array;
	
		int[] closest_k_label_array = new int[100];
		for (int i = 0; i<100; i++) {
			
			closest_k_label_array[i] = (int) sorted_distance_label_array[i][0];
			
		}
		
		//This sorts the Array based on the distance difference and assigns the lowest 'K' number of labels to an array 
		
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
		
		// This calculates the most common label in the closest_k_label_array
		
		
		int count = 0;
		for (int i=0; i<closest_k_label_array.length;i++) {
			if (popular == closest_k_label_array[i]) {
				count++;
			}
			
			
		}
		
		// This counts how many of the most popular label there are
		
		
		System.out.println("The count is " + count);
		count = count * 100;
		double confidence = count/closest_k_label_array.length;
		
		System.out.println("Prediction is " + popular);
		System.out.println(confidence + "% Confidence");
		
		
		outputArea.getPrediction().setText("                  Prediction is " + popular + "  (" +  confidence + "% Confidence)");
			
		// This calculates the % confidence of the result and displays the Predicted digit with confidence		
	}
	
	private void uploadButton() throws IOException {
		myCanvas.upload();
    
      
	}
	//This function calls the upload function from the myCanvas class
  
	
	



	private void saveButton() {
		myCanvas.saveCanvas();
	
				
	}
	
	//This function calls the saveCanvas function from the myCanvas class
}


