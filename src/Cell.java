import java.util.ArrayList;

public class Cell {
    private ArrayList<Cell> next;
    private int x, y;

    public Cell() {
        this.next = new ArrayList<Cell>();
    }

    public Cell(int x, int y) {
        this.next = new ArrayList<Cell>();
        this.x = x;
        this.y = y;
    }

    public void addNext(Cell c) {
        this.next.add(c);
    }

    public ArrayList<Cell> getNext() {
        return next;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", x, y);
    }
}
