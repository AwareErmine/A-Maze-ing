import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Grid {
    private Cell[][] grid;
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
        output.append("___".repeat(Math.max(0, this.width))).append("_\n");
        for (int r = 0; r < this.height; r++) {
            row = this.grid[r];
            output.append("|");
            for (int i = 0; i < this.width; i++) {
                // check for empty cell, ends, and whether it should be connected
                containsBelowY = (row[i] != null) && (r != this.height - 1) && row[i].getNext().contains(this.grid[r + 1][i]);
                containsNextX = (row[i] != null) && (i != this.width - 1) && row[i].getNext().contains(row[i + 1]);

                if (containsNextX && containsBelowY)
                    output.append("   ");
                else if (containsNextX)
                    output.append("___");
                else if (containsBelowY)
                    output.append((i != this.width - 1) ? "  |" : "  "); // don't add a second wall @ the ends
                else
                    output.append((i != this.width - 1) ? "__|" : "__");
            }
            output.append("|\n");
        }
        System.out.print(output);
    }

    public void recursiveDFS() {
        // pass random x y and cell into the private declaration
        int x = ThreadLocalRandom.current().nextInt(0, this.width);
        int y = ThreadLocalRandom.current().nextInt(0, this.height);
        this.grid[y][x] = new Cell(x, y);
        recursiveDFS(this.grid[y][x]);
    }

    private void recursiveDFS(Cell c) {
        int[] newPos = getValid(c.getX(), c.getY());
        while (newPos[0] != -1) {
            this.grid[newPos[1]][newPos[0]] = new Cell(newPos[0], newPos[1]);

            // add the cells to the next for each other
            c.addNext(this.grid[newPos[1]][newPos[0]]);
            this.grid[newPos[1]][newPos[0]].addNext(c);

            recursiveDFS(this.grid[newPos[1]][newPos[0]]);

            newPos = getValid(c.getX(), c.getY());
        }
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
            return available.get(ThreadLocalRandom.current().nextInt(0, available.size()));
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
