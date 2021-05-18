package structure;

public class Coup {
    Point initial;
    Point arrive;
    Point construction;

    public Coup(){}

    public Coup(Point dep, Point arr, Point cons){
        initial = dep;
        arrive = arr;
        construction = cons;
    }

    public Point getDepart(){
        return initial;
    }

    public Point getArrive(){
        return arrive;
    }

    public Point getConstruction(){
        return construction;
    }
}

