import java.util.ArrayList;

public class Cell {
    private Cell parent;
    private ArrayList<Cell> next;

    public Cell(Cell parent) {
        this.parent = parent;
        this.next = new ArrayList<Cell>();
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
}
