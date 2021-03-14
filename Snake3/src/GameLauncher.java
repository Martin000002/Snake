import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class GameLauncher implements Runnable{
	JFrame frame;
	Canvas canvas;
	static Board board;
	static Renderer renderer;
	static SnakeChunk snake;
	static ArrayList<SnakeChunk> snakeFull = new ArrayList<SnakeChunk>();
	static Fruit currentFruit;
	
	private int scale = 1, wheight = 720, wwidth = 1280;//Cuadricula de 45 * 80
	private boolean updating = true;
	int score = 0;
	BufferedImage img = new BufferedImage(wwidth, wheight, BufferedImage.TYPE_INT_RGB);
	private Graphics g;
	BufferStrategy bs;
	
	private final double UPDATE_CAP = 1.0/60.0;
	
	public static void main(String[] args) {
		GameLauncher gl = new GameLauncher();
		snake = new SnakeChunk(128,96);
		snakeFull.add(new SnakeChunk(128 + 16,96));
		snakeFull.add(new SnakeChunk(128 + 32,96));
		snakeFull.get(0).setColor(0xff00ff00);
		renderer = new Renderer(gl, snakeFull);
		
		snakeFull.add(snake);
		currentFruit = new Fruit();
		
		gl.createFrame();
		board = new Board(gl, snakeFull);
		Thread t = new Thread(gl);
		t.run();
		
		
	}
	
	public void createFrame() {
		frame = new JFrame();
		canvas = new Canvas();
		
		Dimension s = new Dimension((int) (wwidth * scale), (int) (wheight * scale));
		canvas.setMaximumSize(s);
		canvas.setMinimumSize(s);
		canvas.setPreferredSize(s);
		//System.out.println(s.getWidth());// TODO fix size
	
		
		
		frame.setSize(wwidth * scale, wheight * scale);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Snake");
		frame.setVisible(true);
		frame.setResizable(false);
		
		frame.add(canvas, BorderLayout.CENTER);
		 frame.pack();
		canvas.createBufferStrategy(2);
		bs = canvas.getBufferStrategy();
		g = bs.getDrawGraphics();
		
	}
	
	/*public BufferedImage EscalarImagen(BufferedImage img, double escalar) {
		BufferedImage oldImg = img;
		/*try {
			oldImg = ImageIO.read(new File(img));
		}catch(Exception e) {e.printStackTrace();}
		int ancho = oldImg.getWidth();
		int alto = oldImg.getHeight();
		int anchoEscalado = (int)(escalar * ancho);
		int altoEscalado = (int) (escalar * alto);
		
		// Definir la nueva imagen con los nuevos valores de escala.	//COMPLETLY USELESS
		BufferedImage newImg = new BufferedImage(anchoEscalado, 
				altoEscalado, oldImg.getType());
		Graphics2D g = newImg.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(newImg, 0, 0, anchoEscalado, altoEscalado, 0, 0, ancho, alto, null);
		g.dispose();
		return newImg;
	}*/
	

	public void updatePos() {
		for(int i = snakeFull.size() -1; i >= 1;i--) {
		//	System.out.println("updatePos" + i);
			//SnakeChunk closerHead = snakeFull.get(i-1);
			//SnakeChunk closerTail = snakeFull.get(i);
			if(!(i % 2 == 0)) {
				//snakeFull.get(i).setColor(0xff007700);
			}
				
			snakeFull.get(i).setX(snakeFull.get(i-1).getX());
			snakeFull.get(i).setY(snakeFull.get(i-1).getY());
			
			
		//	System.out.println("head(0) " + snakeFull.get(i-1).getX() + " tail(1) " + snakeFull.get(i).getX());
			//TODO tail does't show somehow
			 
		}
		
	}
	public void increaseSizeOfSnake() {
		snakeFull.add(new SnakeChunk(snakeFull.get(snakeFull.size() -1).getX(),snakeFull.get(snakeFull.size() -1).getY()));
		System.out.println(snakeFull.size());
		
	}
	
	public void collitionDetection() {
		System.out.println("head x,y" + snakeFull.get(0).getX() + " " + snakeFull.get(0).getY() + "fruit x,y" + 
				currentFruit.getX() * 16 + " " + currentFruit.getY() * 16);
		for(int i = 1; i < snakeFull.size();i++) {
				if(snakeFull.get(0).getX() == snakeFull.get(i).getX() && snakeFull.get(0).getY() == snakeFull.get(i).getY()) {
					System.out.println("lost");
					updating = false;
				}
			}
		if(snakeFull.get(0).getX() == currentFruit.getX() * 16 && snakeFull.get(0).getY() == currentFruit.getY() * 16) {
			increaseSizeOfSnake();
			System.out.println("snake big");
			currentFruit = new Fruit();
			score++;
			//snakeFull.get(0).setColor(0xffffffff);
		}
	}
	@Override
	public void run() {
		boolean running = true;
		boolean render = false;
		//timer setup
		double firstTime = 0;
		double lastTime = System.nanoTime() / 1000000000.0;
		double passedTime = 0;
		double unprocessedTime = 0;
		
		//fps counter
		double frameTime = 0;
		int fps = 0;
		int frames = 0;
		
		
		
		while(running) {
			//Timer Work
			firstTime = System.nanoTime() / 1000000000.0;
			passedTime = firstTime - lastTime;
			lastTime = firstTime;
			unprocessedTime += passedTime;
			frameTime += passedTime;
			
			while(unprocessedTime >= UPDATE_CAP) {
				unprocessedTime -= UPDATE_CAP;
				render = true;
				if(updating) {
					board.update(this, (float)UPDATE_CAP);
					
				}
			}
				if (frameTime > 1.0) {
					fps = frames;
					frames = 0;
				}
			
			
			if (render) {
				renderer.clear();
				renderer.process();
				renderer.drawRect(currentFruit.getColor(), currentFruit.getX() * 16, currentFruit.getY() * 16, 16, 16);
				renderer.drawText("score" + score, 0xffffffff, 0,0);
				if(!(updating)) {
					renderer.drawText("GAMEOVER "
							+ "press space", 0xffffffff, wwidth / 2 - 32, wheight / 2);
				}
				
				//renderer.drawRect(0xff990000, 7 * 16, 7 * 16, 16, 16);
				g.drawImage(img, 0, 0, wwidth * scale, wheight * scale, null);
				bs.show();
				frames++;
			}else {
				try {
					Thread.sleep(1);
				}catch(Exception e) {}
			}
			//System.out.println("fps: " + fps + " frameTime " + frameTime + " isUpdate " + (unprocessedTime >= UPDATE_CAP));
		}
			
			
		}
		
		
	public Canvas getCanvas() {
		return canvas;
	}
	public int getScale() {
		return scale;
	}
	public void setScale(int scale) {
		this.scale = scale;
	}
	public int getWheight() {
		return wheight;
	}
	public void setWheight(int wheight) {
		this.wheight = wheight;
	}
	public int getWwidth() {
		return wwidth;
	}
	public void setWwidth(int wwidth) {
		this.wwidth = wwidth;
	}
	public BufferedImage getImg() {
		return img;
	}

	public int getScore() {
		return score;
	}

	public boolean isUpdating() {
		return updating;
	}
}