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
	
	
	
	

	static BufferedImage[] ImgList = new BufferedImage[6000];
	static int[] label_list = new int[6000]; 



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
	
	byte[] label_data = new byte[number_of_labels];
	
	int image_size = image_width * image_height;
	}
	
	

		
	public static void importMNIST() throws IOException {
	
	

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
		
		byte[] label_data = new byte[number_of_labels];
		
		int image_size = image_width * image_height;
		
		int[] image_data;
	
	
			
	for (int record = 0; record < 6000; record++)
	{
		
		BufferedImage currentImg = new BufferedImage(image_width, image_height, BufferedImage.TYPE_INT_ARGB);
		
		int label = in_stream_labels.read();
		label_list[record] = label;
		image_data = new int[image_width* image_height];
		image_size = image_width * image_height;
		for (int pixel = 0; pixel < image_size; pixel++)
		{
			
			int gray_value = in_stream_images.read();
			int red = (gray_value >> 16) & 0xff;
    		int green = (gray_value >> 8) & 0xff;
    		int blue = (gray_value) & 0xff;
    		red = 255 - red;
    		green = 255 - green;
    		blue = 255 - blue;
    		int grayscale = (int) ((0.3 * red) + (0.59 * green) + (0.11 * blue));
    		int new_pixel_value = 0xFF000000 | (grayscale << 16) | (grayscale <<8)| (grayscale);
			image_data[pixel] = new_pixel_value;
			
		}
		currentImg.setRGB(0, 0, image_width, image_height, image_data, 0, image_width);
		ImgList[record] = currentImg;	
		}	
	
	}
	
	public static BufferedImage[] getPictureList() {
		
		return ImgList ;
		
	}
	
	public static int[] getLabels() {
		return label_list;
	}
	
	public static int[] getImgListRGB() {
		BufferedImage ImgList[] = new BufferedImage[6000];
		ImgList = getPictureList();
		int[] Lble = getLabels();
		int[] ImgListRGB = new int[6000];
		int width = 28;
		int height = 28;
		int[][] imgListRGB = new int[width][height];
		for (int i=0; i<6000; i++) {
			for(int row = 0; row< height; row++) {
				for (int col=0; col<width;col++) {
			
			imgListRGB[row][col] = ImgList[i].getRGB(col,row);
			ImgListRGB[i] = imgListRGB[row][col];
			
		
		}
			}
		}
		return ImgListRGB;
	}
	}
	


	
	
	
	
	