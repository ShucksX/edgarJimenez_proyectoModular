package application;

public class Coordinate {
	private int x;
	private int y;
	
	public Coordinate(int xVal, int yVal) {
		x = xVal;
		y = yVal;
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
	
	public String getString() {
		return "X = " + x + "|Y = " + y;
	}
	
}
