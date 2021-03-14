import java.util.Random;

public class Fruit {
	private int x; 
	private int y;
	private int color = 0xff990000;
	
	Fruit() {
		Random rng = new Random();
		x = rng.nextInt(80);
		y = rng.nextInt(45);
		System.out.print("Fruit rng x " + (rng.nextInt(45)- 1)  + "Fruit rng y " + (rng.nextInt(80)-1));
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
}