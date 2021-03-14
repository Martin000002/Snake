
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;


public class Font {
	private Image Font;
	private int[] offsets;
	private int[] widths; 
	private int imageSize;
	int[] p;
	
	Font() {
		Font = new Image("/Fuente.png");
		BufferedImage image = null;
		try {
			image = ImageIO.read(Image.class.getResource("/Fuente.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Font = Image.class.getResource("/res/Fuente")
		imageSize = Font.getH();
		offsets = new int[256];
		widths = new int[256];
		int unicode = 0;
		
		for(int i = 0; i < Font.getW(); i++) {
			//System.out.println("search for chars start_P: " + fontImage.getP()[i]);
			if (Font.getP()[i] == -16776961) { //0000ff = -16776961 ||||| 0xffff00ff = 65281
				offsets[unicode] = i;
			//	System.out.println("start of char found");
			}
			if (Font.getP()[i] == -256) { //ffff00 = -256
				widths[unicode] = i - offsets[unicode];
				unicode++;
			}
		}
	}
	public Image getFontImage() {
		return Font;
	}

	public void setFontImage(Image fontImage) {
		this.Font = fontImage;
	}

	public int[] getOffsets() {
		return offsets;
	}

	public void setOffsets(int[] offsets) {
		this.offsets = offsets;
	}

	public int[] getWidths() {
		return widths;
	}

	public void setWidths(int[] widths) {
		this.widths = widths;
	}

	public int getImageSize() {
		return imageSize;
	}
}