import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Grid test = new Grid(3, 4);
        test.recursiveDFS();
        System.out.println(Arrays.deepToString(test.grid));
    }
}
