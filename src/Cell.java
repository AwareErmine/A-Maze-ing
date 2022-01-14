import java.util.ArrayList;
import java.util.Arrays;

public class Cell {
    private Cell parent;
    private ArrayList<Cell> next;
    private int x, y;

    public Cell() {
        this.parent = null;
        this.next = new ArrayList<Cell>();
    }

    public Cell(int x, int y) {
        this.parent = null;
        this.next = new ArrayList<Cell>();
        this.x = x;
        this.y = y;
    }

    public Cell(Cell parent, int x, int y) {
        this.parent = parent;
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
    public Cell getParent() {
        return parent;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    @Override
    public String toString() {
//        return String.format("(%s, %s){ %s }", x, y, parent);
        return String.format("(%s, %s)", x, y);
    }
}
