import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Grid test = new Grid(3, 4);
        System.out.println(Arrays.deepToString(test.grid));
        System.out.println(test.grid[1][2]);
        test.setCell(new Cell(), 2, 1);
        System.out.println(test.grid[1][2]);
        test.recursiveDFS();
        System.out.println(Arrays.deepToString(test.grid));
    }
}
