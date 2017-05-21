package gameengine;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImageManager {	
	
	static private ImageManager object;
	
	// buffer que faz o pooling para o teclado
	HashMap<String, BufferedImage> images;
	
	// construtor
	private ImageManager() {
		images = new HashMap<String, BufferedImage>();
	}
	
	static public ImageManager getObject() {
		if(object == null) {
			object = new ImageManager();
		}
		return object;		
	}
	
	public BufferedImage loadImage(String fileName) throws IOException {
		URL url = getClass().getResource("/" + fileName);
		
		if( url == null ) {
			throw new RuntimeException("A imagem /" + fileName + " não foi encontrada");
		}
		else {
			String path = url.getPath();
			// se a imagem já está carregada no buffer images
			if( images.containsKey(path) ) {
				return images.get(path);
			}
			// se a imagem ainda não foi carregada
			else {
				BufferedImage img = ImageIO.read(url);
				images.put(path, img);
				return img;
			}
		}
	}
	
	public BufferedImage loadImage(String fileName, int x0, int y0, int x1, int y1) throws IOException {
		
		BufferedImage image = loadImage(fileName);
		BufferedImage img = image.getSubimage(x0, y0, x1, y1);
		return img;		
	}
	
}


