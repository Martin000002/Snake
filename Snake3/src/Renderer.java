import java.awt.image.DataBufferInt;
import java.util.ArrayList;

public class Renderer {
	GameLauncher gl;
	SnakeChunk sc;
	ArrayList<SnakeChunk> snakeFull;
	private int pw;
	private int ph;
	private int[] p;
	private Font font = new Font();
	
	
	Renderer(GameLauncher gl, ArrayList snakeFull) {
		this.gl = gl;
		this.sc = sc;
		this.snakeFull = snakeFull;
		pw = gl.getWwidth();
		ph = gl.getWheight();
		p = ((DataBufferInt) gl.getImg().getRaster().getDataBuffer()).getData();
		//System.out.println(p.length);
		
	}
	
	public void clear() {
		//System.out.println("rendering");
		for (int i = 0; i < p.length; i++) {
			p[i] = 0xff000000;
		}
	}
	public void process() {
		for(int i = 0; i < snakeFull.size(); i++) {
			SnakeChunk sc =  snakeFull.get(i);
			int x = sc.getX();
			int y = sc.getY();
			drawRect(sc.getColor(),x,y,16,16);
			//drawText("score" + gl.getScore(), 0xffffffff, 0,0);
		}
		
	}
	public void drawPixel(int color, int x, int y) {
		if ((x >= gl.getWwidth() || y >= gl.getWheight() || x < 0 || y < 0)) {
			return;
		}
		p[x + y * pw] = color;
	}
	
	public void drawRect(int color, int offx, int offy, int sx, int sy) {
		for(int x = 0; x < sx; x++) {
			for(int y = 0; y < sy; y++) {
				drawPixel(color, offx + x, offy + y);
			}
		}
	}
	public void drawText(String text, int color, int offx, int offy) {
		int offset = 0;
		for(int i = 0; i < text.length(); i++) {
			int unicode = text.codePointAt(i);
			for(int y = 0;y < font.getImageSize();y++) {
				for(int x = 0;x < font.getWidths()[unicode];x++) {
					if (font.getFontImage().getP()[(x + font.getOffsets()[unicode]) + y * font.getFontImage().getW()] == 0xffffffff) {
						drawPixel(color, x + offx + offset, y + offy);
					}
						
				}
			}
			offset += font.getWidths()[unicode];
		}
	}
}
//TODO   1.destruir y reconstruir la manzana al comerla V 2.destruir la serpiente al colisionar V 2.5 fuente 3.SFX? 4.Musica. 5.GameOverScreen 6.score






