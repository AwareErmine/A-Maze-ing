//import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Grid test = new Grid(3, 3);

        test.recursiveDFS();
        for (Cell[] row : test.grid) {
            for (Cell c : row) {
                System.out.print(c + "" + c.getNext() + "    ");
            }
            System.out.println();
        }

//        System.out.println(Arrays.deepToString(test.grid));
        System.out.println();
        test.printGrid();
    }
}
