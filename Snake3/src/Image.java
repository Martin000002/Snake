import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {
	private int w, h;
	private int[] p;
	private boolean alfa = false;
	private String name;
	
	//Normal Constructor
	public Image(String path) {
		System.out.println("normal image created");
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(Image.class.getResource(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		w = image.getWidth();
		h = image.getHeight();
		p = image.getRGB(0, 0, w, h, null, 0, w);
		name = path;
		alfa = false;
		//System.out.println(p[0]);
		
		image.flush();
	}
	
	//Alfa Constructor
	public Image(String path, boolean a) {
		System.out.println("alternate constructor");
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(Image.class.getResource(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		w = image.getWidth();
		h = image.getHeight();
		p = image.getRGB(0, 0, w, h, null, 0, w);
		name = path;
		alfa = a;
		//System.out.println(p[0]);
		
		image.flush();
	}
	//TileImageConstructor
	public Image(int[] p, int w, int h) {
		this.p = p;
		this.w = w;
		this.h = h;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int[] getP() {
		return p;
	}

	public void setP(int[] p) {
		this.p = p;
	}

	public boolean isAlfa() {
		return alfa;
	}

	public void setAlfa(boolean alfa) {
		this.alfa = alfa;
	}

	public String getName() {
		return name;
	}
}