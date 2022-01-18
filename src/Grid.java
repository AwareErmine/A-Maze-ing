import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Grid {
    private Cell[][] grid;
    private final int width, height;
    private int[] current; // position of user
    private int[] finish; // goal of user

    /**
     * Stores a 2-d array representing a maze generated at a random cell
     * @param width the length of each array stored in grid
     * @param height the number of arrays of the width in the grid
     */
    public Grid(int width, int height) {
        this.grid = new Cell[height][width];
        this.height = height;
        this.width = width;
        recursiveDFS();
        int x = ThreadLocalRandom.current().nextInt(0, this.width);
        int y = ThreadLocalRandom.current().nextInt(0, this.height);
        finish = new int[]{x, y};
    }

    /**
     * Stores a 2-d array representing a maze generated started at a given cell
     * @param width the length of each array stored in grid
     * @param height the number of arrays of the width in the grid
     * @param start a {@link Cell Cell} to start
     */
    public Grid(int width, int height, Cell start) {
        this.grid = new Cell[height][width];
        this.height = height;
        this.width = width;
        recursiveDFS(start);
    }

    /**
     * Prints out an ascii representation of grid
     */
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

    /**
     * Stores a maze in grid using Recursive Depth-First Search, starting from a random location
     */
    private void recursiveDFS() {
        // pass random x y and cell into the private declaration
        int x = ThreadLocalRandom.current().nextInt(0, this.width);
        int y = ThreadLocalRandom.current().nextInt(0, this.height);
        this.grid[y][x] = new Cell(x, y);
        current = new int[]{x, y};
        recursiveDFS(this.grid[y][x]);
    }

    /**
     * Seeds grid with cells linking to each other
     * @param c a {@link Cell Cell} object to start linking from
     */
    private void recursiveDFS(Cell c) {
        int[] newPos = getValid(c.getX(), c.getY());
        while (newPos[0] != -1) { // when there are no cells available next to a cell
            // Set the cell at the coordinates to a cell with the given coordinates
            this.grid[newPos[1]][newPos[0]] = new Cell(newPos[0], newPos[1]);

            // add the cells to the next arrays for each other
            c.addNext(this.grid[newPos[1]][newPos[0]]);
            this.grid[newPos[1]][newPos[0]].addNext(c);

            // continue linking from whatever cells are possible from the new position
            recursiveDFS(this.grid[newPos[1]][newPos[0]]); // base case: when we're cornered and there's nowhere left to go

            newPos = getValid(c.getX(), c.getY()); // check the other valid cells nearby the cell passed into the function
        }
    }

    /**
     * Returns a (random) valid cell (if available) around the cell passed
     * @param x x value of cell
     * @param y y value of cell
     * @return coordinates of valid cell or [-1, -1] if no cell is available
     */
    private int[] getValid(int x, int y) {
        ArrayList<int[]> available = allValid(x, y);
        if (available.size() > 0)
            return available.get(ThreadLocalRandom.current().nextInt(0, available.size()));
        else
            return new int[]{-1, -1};
    }

    /**
     * Returns the x and y coordinates of every cell adjacent to given x and y coordinates that can be moved to
     * @param x x value of cell
     * @param y y value of cell
     * @return an integer array of the coordinates of all the valid cells adjacent to a cell
     */
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

    /**
     * Returns whether the cell at given x and y coordinates can be moved to
     * A cell can be moved to if it's within the bounds of the maze and hasn't already been travelled to
     * @param x x value of cell
     * @param y y value of cell
     * @return true if the cell can be moved to, false if it can't be
     */
    private boolean isValid(int x, int y) {
        return (x >= 0 && x < this.width) && (y >= 0 && y < this.height) && (this.grid[y][x] == null);
    }

    public Cell[][] getGrid() {
        return grid;
    }
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }

    public void move(char direction) {
        System.out.println("called");
        switch (direction) {
            case 'u':
                if (grid[current[1]][current[0]].getNext().contains(grid[current[1]-1][current[0]])) {
                    current[1] -= 1;
                    System.out.println("up");
                }
                break;
            case 'd':
                if (grid[current[1]][current[0]].getNext().contains(grid[current[1]+1][current[0]])) {
                    current[1] += 1;
                    System.out.println("down");
                }
                break;
            case 'l':
                if (grid[current[1]][current[0]].getNext().contains(grid[current[1]][current[0]-1])) {
                    current[0] -= 1;
                    System.out.println("left");
                }
                break;
            case 'r':
                if (grid[current[1]][current[0]].getNext().contains(grid[current[1]][current[0]+1])) {
                    current[0] += 1;
                    System.out.println("right");
                }
                break;
        }
    }

    public int[] getPos() {
        return current;
    }
    public int[] getFinish() {
        return finish;
    }
}
