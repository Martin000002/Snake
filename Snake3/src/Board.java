import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Board implements KeyListener{
	JFrame frame;
	GameLauncher gl;
	SnakeChunk snake;
	ArrayList<SnakeChunk>snakeFull;
	
	boolean up;
	private float temp;
	private final int NUM_KEYS = 256;
	private boolean[] keys = new boolean[NUM_KEYS];
	private boolean[] keysLast = new boolean[NUM_KEYS];
	private String dir;
	private boolean moving = false;
	
	Board(GameLauncher gl, ArrayList<SnakeChunk> snakeFull) {
		this.snakeFull = snakeFull;
		this.gl = gl;
		this.snake = snakeFull.get(0);
		Canvas canvas = gl.getCanvas();
		canvas.addKeyListener(this);
	}
	
	public void setUpGame() {
		
	}
	public void update(GameLauncher gl, float dt) {
		if (gl.isUpdating()) {
			if(isKey(KeyEvent.VK_SPACE)) {
				System.out.println("space");
				gl = new GameLauncher();
			}
		}
		if (!(dir == null)) {
			moving = true;
		}
		if((temp == 0) && moving) {
			gl.collitionDetection();
			gl.updatePos();
		}

		//Advance in current direction
		if(dir == "up" && temp == 0) {
			snake.setY(snake.getY()-16);
		}
		if(dir == "down" && temp == 0) {
			snake.setY(snake.getY()+16);
		}
		if(dir == "right" && temp == 0) {
			snake.setX(snake.getX()+16);
		}
		if(dir == "left" && temp == 0) {
			snake.setX(snake.getX()-16);
		}
		
		if(isKey(KeyEvent.VK_UP )) {
			dir = "up";
			
		}
		if(isKey(KeyEvent.VK_DOWN )) {
			dir = "down";
			
		}
		if(isKey(KeyEvent.VK_RIGHT )) {
			dir = "right";
			
		}
		if(isKey(KeyEvent.VK_LEFT )) {
			dir = "left";
			
		}
		//System.out.println("head(0) " + snakeFull.get(0).getX() + " tail(1) " + snakeFull.get(1).getX());
		
		temp += dt * 40;
		if (temp > 3) {temp = 0;}
	}
	
	public boolean isKey(int keycode) {
		return keys[keycode];
		
	}
	public boolean isKeyUp(int keycode) {
		return (!keys[keycode] && keysLast[keycode]);
			
	}
	public boolean isKeyDown(int keycode) {
		return (keys[keycode] && !keysLast[keycode]);
			
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		System.out.println(keys[KeyEvent.VK_UP]);
		
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		
	}
}