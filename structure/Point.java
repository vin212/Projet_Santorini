package structure;

public class Point {
	int x;
	int y;

	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}

	public void modifValeur (int x, int y){
		this.x = x;
		this.y = y;
	}

	public int getx(){
		return this.x;
	}

	public int gety(){
		return this.y;
	}

	public int CompareTo (Point p){
		if (p.x == this.x && p.y == this.y){
			return 0;
		} else if (this.x < p.x) {
			return -1;
		} else if (this.x > p.x) {
			return 1;
		} else if (this.x == p.x && this.y > p.y) {
			return 1;
		} else if (this.x == p.x && this.y < p.y){
			return -1;
		} else {
			return -2;
		}
	}

	public String toString (){
		return ("( " + this.x + ", " + this.y + ")");
	}
}