import java.util.ArrayList;

public class Grid {
    // Check what cells are visitable
    // Actual algorithm
    // Generate the actual, 2-d array (coordinates)

    Cell[][] grid;
    private int width, height;

    public Grid(int width, int height) {
        this.grid = new Cell[height][width];
        this.height = height;
        this.width = width;
    }

    // Carve path from thing
        // Current cell is the starting cell? Stop
        // Can we travel in a direction
            // Yes? Pick random of the directions we can travel in (here implies that direction hasn't been in path already)
                // Link cell, keep carving from that cell
                // Add new cell into where it is in the grid
            // No? Go back along path, continue carving from last cell (call method again from last cell)

    /* Grid looks like
    * [
    *   [null, null, null]
    *   [null, null, null]
    *   [null, null, null]
    *   [null, null, null]
    * ]
    * */

    public void recursiveDFS() {
        System.out.println(isValid(2, 3));

        // pass random x y and cell into the private declaration of carvePath
    }

    private void recursiveDPS(Cell c, int x, int y) {
        if (c.getParent() == null && c.getNext().size() > 0) return; // base case; we've returned to the original cell
    }

    public void setCell(Cell parent, int x, int y) {
        this.grid[y][x] = new Cell(parent, x, y);
        System.out.println(this.grid[y][x]);
    }

    private boolean isValid(int x, int y) {
        return (x < this.width) && (y < this.height) && (this.grid[y][x] == null);
    }
}
