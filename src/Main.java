import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Grid test = new Grid(10, 10);
        test.recursiveDFS();
//        System.out.println(Arrays.deepToString(test.grid));
        test.printGrid();
    }
}
