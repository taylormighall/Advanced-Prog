import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;



public class MNISTDataLoader{
	
	static BufferedImage[] ImgList = new BufferedImage[60000];
	static int[] label_list = new int[60000]; 
	
	//Initialises the required Arrays for Images and their labels

	
	
	public static void importMNIST() throws Exception {
	
	

		String train_label_filename = "train-labels-idx1-ubyte\\train-labels.idx1-ubyte";
		String train_image_filename = "train-images-idx3-ubyte\\train-images.idx3-ubyte";
		FileInputStream in_stream_labels = null;
		FileInputStream in_stream_images = null;
		
		// Loads the train_image and train_label Files
		
		
			
				in_stream_labels = new FileInputStream(new File(train_label_filename));
				in_stream_images = new FileInputStream(new File(train_image_filename));
				
				//Creates new input streams
		
				int labels_start_code = (in_stream_labels.read() <<24) |
						(in_stream_labels.read() << 16) | (in_stream_labels.read() << 8) | (in_stream_labels.read());
				if (labels_start_code != 2049) {
					
					
					throw new Exception("The Label Magic number is Incorrect. It should be 2049, it is " + labels_start_code);
					
				}
				int images_start_code = (in_stream_images.read() <<24) |
						(in_stream_images.read() << 16) | (in_stream_images.read() << 8) | (in_stream_images.read());
				if (images_start_code != 2051) {
					
					
					throw new Exception("The Image Magic number is Incorrect. It should be 2051, it is " + images_start_code);
					
				}
				// This checks if the Magic Numbers for the files are correct and throws an exception if not
				
				int number_of_labels = (in_stream_labels.read() <<24) |
				(in_stream_labels.read() << 16) | (in_stream_labels.read() << 8) | (in_stream_labels.read());

		int number_of_images = (in_stream_images.read() <<24) |
				(in_stream_images.read() << 16) | (in_stream_images.read() << 8) | (in_stream_images.read());
		
		
		int image_height = (in_stream_images.read() <<24) |
				(in_stream_images.read() << 16) | (in_stream_images.read() << 8) | (in_stream_images.read());
		
		int image_width = (in_stream_images.read() <<24) |
				(in_stream_images.read() << 16) | (in_stream_images.read() << 8) | (in_stream_images.read());
		
		// Above reads the next bytes of the files to find the number of Labels, Number of Images, image height and image width
		
		
		int image_size = image_width * image_height;
		
		int[] image_data;
	
	
			
	for (int record = 0; record < number_of_images; record++)
	{
		
		BufferedImage currentImg = new BufferedImage(image_width, image_height, BufferedImage.TYPE_INT_ARGB);
		
		int label = in_stream_labels.read();
		label_list[record] = label;
		image_data = new int[image_width* image_height];
		image_size = image_width * image_height;
		//Creates a buffered Image and a label for every image
		for (int pixel = 0; pixel < image_size; pixel++)
		{
			
			int gray_value = in_stream_images.read();
			int red = (gray_value >> 16) & 0xff;
    		int green = (gray_value >> 8) & 0xff;
    		int blue = (gray_value) & 0xff;
    		int grayscale = (int) ((0.3 * red) + (0.59 * green) + (0.11 * blue));
    		int new_pixel_value = 0xFF000000 | (grayscale << 16) | (grayscale <<8)| (grayscale);
			image_data[pixel] = new_pixel_value;
			//Sets every image to grayscale and saves the Pixel Value
			
		}
		currentImg.setRGB(0, 0, image_width, image_height, image_data, 0, image_width);
		ImgList[record] = currentImg;
		
		//Adds each image to the ImgList
		
		
		}
	in_stream_labels.close();
	in_stream_images.close();
	
	//Closes Streams
	
	}
	
	public static BufferedImage[] getPictureList() {
		
		return ImgList ;
		
	} // Returns full Image List
	
	public static int[] getLabels() {
		return label_list;
	} // Returns full list of Labels corresponding with the Image List
	

	}
	


	
	
	
	
	