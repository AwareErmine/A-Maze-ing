import java.util.ArrayList;

/**
 * Stores the coordinates of a cell and the cells "connected" to it
 * @see Grid uses cells in generating a maze using DFS
 */
public class Cell {
    private ArrayList<Cell> next;
    private final int x, y;

    /**
     * Initializes an empty arraylist of the cells connected to this cell and stores the cell's coordinates
     * @param x the x-coordinate of the cell
     * @param y the y-coordinate of the cell
     */
    public Cell(int x, int y) {
        this.next = new ArrayList<>();
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
