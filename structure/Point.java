package structure;

public class Point implements Comparable<Point>, Cloneable{
	int x;
	int y;

	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}

	public Point (Point p)
	{
		this.x = p.getx();
		this.y = p.gety();
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

	@Override
	public boolean equals(Object o){
		if (o == this){
			return true;
		} else if (!( o instanceof Point)) {
			return false;
		}
		Point p = (Point) o; 
		return (p.x == this.x && this.y == p.y);
	}

	@Override
	public int compareTo(Point p) {
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

	@Override
	public Point clone(){
		Point resultat = new Point(x,y);
		return resultat;
	}

	public String toString (){
		return ("( " + this.x + ", " + this.y + ")");
	}

}