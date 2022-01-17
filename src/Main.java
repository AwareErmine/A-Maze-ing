public class Main {
    public static void main(String[] args) {
        final long startTime = System.currentTimeMillis();
        int width = 25;
        int height = 25;
        Grid test = new Grid(width, height);
        test.recursiveDFS();
        test.printGrid();
        final long endTime = System.currentTimeMillis();
        System.out.printf("%s by %s maze\n", width, height);
        System.out.println("Total execution time: " + (endTime - startTime) + " milliseconds");
    }
}
