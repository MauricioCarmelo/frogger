package gameengine;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImageManager {	
	
	
	// buffer que faz o pooling para o teclado
	HashMap<String, BufferedImage> images;
	BufferedImage image;
	
	// construtor
	public ImageManager() {
		images = new HashMap<String, BufferedImage>();
	}
	
	public BufferedImage loadImage(String file) {
		
		try {
			image = ImageIO.read(new File(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;		
	}
	
	
	public ArrayList<Image> getImages(int initialX, int initialY, int rows, int columns, int height, int width){
			ArrayList<Image> vehiclesImages = new ArrayList<Image>();
			
			BufferedImage singleVehicle;
			
			int y = initialY;
			
			for (int i = 0; i < columns; i++){
				for (int j = 0; j < rows; j++){
					singleVehicle = image.getSubimage(initialX, y, width, height);
					vehiclesImages.add(singleVehicle);
					y += height +1;
				}
				initialX += width + 1;
				y = initialY;
			}
		return vehiclesImages;	
	}
	
}


