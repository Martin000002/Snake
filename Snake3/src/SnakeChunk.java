
public class SnakeChunk {
	//Colors
	int black = 0xff000000;
	int white = 0xffffffff;
	int green = 0xff000077;
	int lenght;
	int x;
	int y;
	int color = 0xff005500;
	SnakeChunk(int x,int y) {
		this.x = x;//128
		this.y = y;//96
		
	}
	public int getLenght() {
		return lenght;
	}
	public void setLenght(int lenght) {
		this.lenght = lenght;
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
	public void setX() {
		// TODO Auto-generated method stub
		
	}
	public void setColor(int color) {
		this.color = color;
	}
	
}