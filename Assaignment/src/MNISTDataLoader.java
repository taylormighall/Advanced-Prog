import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.zip.GZIPInputStream;

import javax.swing.Icon;
import javax.swing.ImageIcon;



public class MNISTDataLoader{
	
	
	
	

	BufferedImage[] currentImgList = new BufferedImage[60000];



	@SuppressWarnings("resource")
	MNISTDataLoader() throws IOException{
	
	
	String train_label_filename = "C:\\Users\\tilak\\eclipse-workspace\\Advanced-Prog\\train-labels-idx1-ubyte\\train-labels.idx1-ubyte";
	 String train_image_filename = "C:\\Users\\tilak\\eclipse-workspace\\Advanced-Prog\\train-images-idx3-ubyte\\train-images.idx3-ubyte";
	 FileInputStream in_stream_labels = null;
	FileInputStream in_stream_images = null;
	
	
		
			in_stream_labels = new FileInputStream(new File(train_label_filename));
			in_stream_images = new FileInputStream(new File(train_image_filename));
		
		

	
	
	int labels_start_code = (in_stream_labels.read() <<24) |
			(in_stream_labels.read() << 16) | (in_stream_labels.read() << 8) | (in_stream_labels.read());
	System.out.println(labels_start_code);
	
	int images_start_code = (in_stream_images.read() <<24) |
			(in_stream_images.read() << 16) | (in_stream_images.read() << 8) | (in_stream_images.read());
	System.out.println(images_start_code);
	int number_of_labels = (in_stream_labels.read() <<24) |
			(in_stream_labels.read() << 16) | (in_stream_labels.read() << 8) | (in_stream_labels.read());
	System.out.println(number_of_labels);
	int number_of_images = (in_stream_images.read() <<24) |
			(in_stream_images.read() << 16) | (in_stream_images.read() << 8) | (in_stream_images.read());
	System.out.println(number_of_images);
	int image_height = (in_stream_images.read() <<24) |
			(in_stream_images.read() << 16) | (in_stream_images.read() << 8) | (in_stream_images.read());
	System.out.println(image_height);
	int image_width = (in_stream_images.read() <<24) |
			(in_stream_images.read() << 16) | (in_stream_images.read() << 8) | (in_stream_images.read());
	System.out.println(image_width);
	
//	byte[] label_data = new byte[number_of_labels];
//	
//	int image_size = image_height * image_width;
//	byte[] image_data = new byte[image_size * number_of_images];
//	
//	in_stream_labels.read(label_data);
//	
//	in_stream_images.read(image_data);
//	ArrayList<int[][]> image_list = new ArrayList<int[][]>();
//	int[][] image;
//	
//	for(int i=0;i<number_of_labels;i++) {
//		
//		int label = label_data[i];
//		System.out.println(label);
//		
//		image = new int[image_width][image_height];
//		
//		for(int row = 0; row<image_height;row++) {
//			for(int col = 0; col < image_width; col++) {
//				image[row][col] = image_data[(i*image_size) + ((row*image_width) + col)];
//			}
//		}
//		image_list.add(image);
//		
	}
	
	
//	int[] label_list = new int[number_of_labels]; 
//	byte[] label_data = new byte[number_of_labels];
//	
//	int image_size = image_height * image_width;
//	byte[] image_data = new byte[image_size * number_of_images];
//	
//	ArrayList<int[][]> image_list = new ArrayList<int[][]>();
//	int[][] image;
//	
//	for(int i=0; i<2; i++)
//	{
//		int label = label_data[i];
//		
//		image = new int[image_width][image_height];
//		
//		for(int row = 0; row <image_height; row++)
//		{ 
//			for (int col = 0; col<image_width; col++)
//		
//			{
//				
//			image[row][col]= image_data[(i*image_size) +((row*image_width) + col)];
//		}
//			image_list.add(image);
//			System.out.print(image_list);
//		}}
//	System.out.println("Done?");
	
	
	
	

	


	
	
	

	
	
	
//	for(int record = 0; record < 10; record++)
//	{
//		label = in_stream_labels.read();
//		label_list[record] = label;
//		
//		
//		int[] image_data2 = new int[image_width * image_height];
//		image_size = image_width * image_height;
//		
//	for(int pixel = 0; pixel < image_size; pixel++) {
//		
//		int gray_value = in_stream_images.read();
//		int rgb_value = 0xFF000000 | (gray_value << 16) | (gray_value) << 8 | (gray_value);
//		
//		image_data2[pixel] = rgb_value;
//		
//	}
//	}
	
	



		
	public ImageIcon currentImg() throws IOException {
	
	

		String train_label_filename = "C:\\Users\\tilak\\eclipse-workspace\\Advanced-Prog\\train-labels-idx1-ubyte\\train-labels.idx1-ubyte";
		String train_image_filename = "C:\\Users\\tilak\\eclipse-workspace\\Advanced-Prog\\train-images-idx3-ubyte\\train-images.idx3-ubyte";
		FileInputStream in_stream_labels = null;
		FileInputStream in_stream_images = null;
		
		
			
				in_stream_labels = new FileInputStream(new File(train_label_filename));
				in_stream_images = new FileInputStream(new File(train_image_filename));
		
				int labels_start_code = (in_stream_labels.read() <<24) |
						(in_stream_labels.read() << 16) | (in_stream_labels.read() << 8) | (in_stream_labels.read());
				System.out.println(labels_start_code);	
				int images_start_code = (in_stream_images.read() <<24) |
						(in_stream_images.read() << 16) | (in_stream_images.read() << 8) | (in_stream_images.read());
				int number_of_labels = (in_stream_labels.read() <<24) |
				(in_stream_labels.read() << 16) | (in_stream_labels.read() << 8) | (in_stream_labels.read());

		int number_of_images = (in_stream_images.read() <<24) |
				(in_stream_images.read() << 16) | (in_stream_images.read() << 8) | (in_stream_images.read());
		System.out.print(number_of_images);
		
		int image_height = (in_stream_images.read() <<24) |
				(in_stream_images.read() << 16) | (in_stream_images.read() << 8) | (in_stream_images.read());
		
		int image_width = (in_stream_images.read() <<24) |
				(in_stream_images.read() << 16) | (in_stream_images.read() << 8) | (in_stream_images.read());
		System.out.print(image_width);
		int[] label_list = new int[number_of_labels]; 
		byte[] label_data = new byte[number_of_labels];
		
		int image_size = image_width * image_height;
		
		
		
		
//		for(int record = 0; record < 1; record++)
//			{
//				int label = in_stream_labels.read();
//				label_list[record] = label;
//				
//				
//				int[] image_data = new int[image_width * image_height];
//				image_size = image_width * image_height;
//				
//			for(int pixel = 0; pixel < image_size; pixel++) {
//				
//				int gray_value = in_stream_images.read();
//				int rgb_value = 0xFF000000 | (gray_value << 16) | (gray_value) << 8 | (gray_value);
//				
//				image_data[pixel] = rgb_value;
//			}
//			
//			}
//		System.out.println("Hmm");
				
				ImageIcon Plz = null;
			
		
	int[] image_data;
	BufferedImage[] currentImgList = new BufferedImage[number_of_images];
	
			
	for (int record = 0; record < number_of_images/100; record++)
	{
		
		BufferedImage currentImg = new BufferedImage(image_width, image_height, BufferedImage.TYPE_INT_ARGB);
		
		
		int label = in_stream_labels.read();
		label_list[record] = label;
		image_data = new int[image_width* image_height];
		image_size = image_width * image_height;
		for (int pixel = 0; pixel < image_size; pixel++)
		{
			int gray_value = in_stream_images.read();
			int rgb_value = 0xFF000000 | (gray_value << 16) | (gray_value) << 8 | (gray_value);
			
			image_data[pixel] = rgb_value;
			
			
		}
		currentImg.setRGB(0, 0, image_width, image_height, image_data, 0, image_width);
		currentImgList[record] = currentImg;
		
		
		
		
	
		
	}
	Plz = new ImageIcon(currentImgList[50]);
	System.out.print("Added to List");
	return  Plz;
	}
	
	public  BufferedImage[] getPictureList() {
		return currentImgList ;
		
	}
	}
	

	
	
	
	
	