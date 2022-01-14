package main.java;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Grid test = new Grid(3, 3);
        test.recursiveDFS();
        System.out.println(Arrays.deepToString(test.grid));
        test.printGrid();
    }
}
