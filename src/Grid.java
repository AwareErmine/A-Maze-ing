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
    * could also look like
    * | | | | |
    * =========
    * | | | | |
    * =========
    * | | | | |
    * */

    public void recursiveDFS() {
        System.out.println(isValid(2, 3));

        // pass random x y and cell into the private declaration of carvePath
        this.grid[0][0] = new Cell(0, 0);

        int[] pos = getValid(0, 0);
        recursiveDFS(this.grid[0][0], pos[0], pos[1]);
    }

    private void recursiveDFS(Cell c, int x, int y) {
        this.grid[x][y] = new Cell(c, x, y);

        int[] newPos = getValid(x, y);
        while (newPos[0] != -1) {
            recursiveDFS(this.grid[x][y], newPos[0], newPos[1]);
            this.grid[x][y].addNext(this.grid[newPos[0]][newPos[1]]);
            newPos = getValid(x, y);
        }
    }

    public void setCell(Cell parent, int x, int y) {
        this.grid[y][x] = new Cell(parent, x, y);
//        System.out.println(this.grid[y][x]);
    }

    /**
     * Gets a (random) valid cell (if available) around the cell passed
     * @param x x value of current
     * @param y y value of current cell
     * @return coordinates of valid cell or [-1, -1] if no cell is available
     */
    private int[] getValid(int x, int y) {
        ArrayList<int[]> available = allValid(x, y);
        return available.get((int)(available.size() * Math.random()));
    }

    private ArrayList<int[]> allValid(int x, int y) {
        ArrayList<int[]> available = new ArrayList<>();
        for (int l = -1; l <= 1; l += 2)
            if (isValid(x+l, y))
                available.add(new int[]{x+l, y});
        for (int h = -1; h <= 1; h += 2)
            if (isValid(x, y+h))
                available.add(new int[]{x, y+h});
        if (available.size() > 0)
            return available.get((int)(available.size()*Math.random()));
        else
            return new int[]{-1, -1};
        }
        return available;
    }

    private boolean isValid(int x, int y) {
        return (x >= 0 && x < this.width) && (y >= 0 && y < this.height) && (this.grid[y][x] == null);
    }
}
