import java.util.ArrayList;
import java.util.Arrays;

public class Grid {
    // Check what cells are visitable
    // Actual algorithm
    // Generate the actual, 2-d array (coordinates)

    Cell[][] grid;
    private final int width, height;

    public Grid(int width, int height) {
        this.grid = new Cell[height][width];
        this.height = height;
        this.width = width;
    }

    public void printGrid() {
        Cell[] row;
        boolean containsNextX, containsBelowY;
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < this.width; i++) {
            output.append("___");
        }
        output.append("_\n");
        for (int r = 0; r < this.height; r++) {
            output.append("|");
            row = this.grid[r];
            for (int i = 0; i < this.width; i++) {
                containsBelowY = (row[i] != null) && (r != this.height - 1) && row[i].getNext().contains(this.grid[r + 1][i]);
                containsNextX = (row[i] != null) && (i != this.width - 1) && row[i].getNext().contains(row[i + 1]);
                if (containsNextX && containsBelowY)
                    output.append("   ");
                else if (containsNextX)
                    output.append("___");
                else if (containsBelowY)
                    output.append((i != this.width - 1) ? "  |" : "  ");
                else
                    output.append((i != this.width - 1) ? "__|" : "__");
            }
            output.append("|\n");
        }
        System.out.print(output);
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
        // pass random x y and cell into the private declaration
        int x = Utils.getRandomNumber(0, this.width - 1);
        int y = Utils.getRandomNumber(0, this.height - 1);
        this.grid[y][x] = new Cell(x, y);
//        System.out.printf("Starting cell: [%s, %s]\n", x, y);
        recursiveDFS(this.grid[y][x]);
    }

    private void recursiveDFS(Cell c) {
        int[] newPos = getValid(c.getX(), c.getY());
        while (newPos[0] != -1) {
            this.grid[newPos[1]][newPos[0]] = new Cell(newPos[0], newPos[1]);

            // add the cells to the next for each other
            c.addNext(this.grid[newPos[1]][newPos[0]]);
            this.grid[newPos[1]][newPos[0]].addNext(c);

//            System.out.println(c + "" + c.getNext() + " --> " + Arrays.toString(newPos));
//            System.out.println(this.grid[newPos[1]][newPos[0]] + "" + this.grid[newPos[1]][newPos[0]].getNext());
            recursiveDFS(this.grid[newPos[1]][newPos[0]]);

            newPos = getValid(c.getX(), c.getY());
        }

//        this.printGrid();
//        System.out.println(c + "\n");
    }

    /**
     * Gets a (random) valid cell (if available) around the cell passed
     * @param x x value of current
     * @param y y value of current cell
     * @return coordinates of valid cell or [-1, -1] if no cell is available
     */
    private int[] getValid(int x, int y) {
        ArrayList<int[]> available = allValid(x, y);
        if (available.size() > 0)
            return available.get(Utils.getRandomNumber(0, available.size() - 1));
        else
            return new int[]{-1, -1};
    }

    private ArrayList<int[]> allValid(int x, int y) {
        ArrayList<int[]> available = new ArrayList<>();
        for (int i = -1; i <= 1; i += 2) {
            if (isValid(x, y + i))
                available.add(new int[]{x, y + i});
            if (isValid(x + i, y))
                available.add(new int[]{x + i, y});
        }
        return available;
    }

    private boolean isValid(int x, int y) {
        return (x >= 0 && x < this.width) && (y >= 0 && y < this.height) && (this.grid[y][x] == null);
    }
}
