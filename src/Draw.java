import com.sun.javafx.scene.SceneEventDispatcher;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Draw extends Application {
    public static void init(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Get Mazed");

        startMenu(primaryStage);
    }

    public static void startMenu(Stage stage) {
        Text title = new Text("Get Mazed!");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 22));

        Button startButton = new Button("Start");
        startButton.setOnAction(event -> {
            gameMenu(stage);
        });

        Button quitButton = new Button("Quit");
        quitButton.setOnAction(event -> {
            stage.close();
        });

        HBox buttons = new HBox(startButton, quitButton);
        buttons.setAlignment(Pos.BOTTOM_CENTER);

        VBox v = new VBox(title, buttons);
        v.setAlignment(Pos.TOP_CENTER);
        Scene s = new Scene(v, 500, 500);
        stage.setScene(s);
        stage.show();
    }

    public static void gameMenu(Stage stage) {
        Text title = new Text("Get Mazed!");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 22));

        Grid currentMaze = new Grid(25, 25);
        Canvas maze = drawMaze(currentMaze, 500, 500);

        VBox v = new VBox(title, maze);
        v.setAlignment(Pos.CENTER);
        Scene s = new Scene(v);
        stage.setScene(s);
        stage.show();
    }

    public static Canvas drawMaze(Grid maze, int length, int width) {
        Cell[][] grid = maze.getGrid();
        Canvas a = new Canvas();
        a.setHeight(length);
        a.setWidth(width);
        GraphicsContext g = a.getGraphicsContext2D();
        g.setFill(Color.valueOf("#ffffff"));

        // Outline
        g.strokeLine(0, 0, width, 0);
        g.strokeLine(0, 0, 0, length);

        boolean containsNextX, containsBelowY;
        final int cellWidth = width/grid[0].length;
        final int cellLength = length/grid.length;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                // check for empty cell, ends, and whether it should be connected
                containsBelowY = /*(grid[row][col] != null) && */ (row != grid.length - 1) && grid[row][col].getNext().contains(grid[row + 1][col]);
                containsNextX = (grid[row][col] != null) && (col != grid[row].length - 1) && grid[row][col].getNext().contains(grid[row][col + 1]);

                if (containsBelowY && containsNextX) {}
                    // don't draw anything
                else if (containsBelowY)
                    g.strokeLine((col+1)*cellWidth, row*cellLength,
                            (col+1)*cellWidth, (row+1)*cellLength);
                else if (containsNextX)
                    g.strokeLine(col*cellWidth, (row+1)*cellLength,
                            (col+1)*cellWidth, (row+1)*cellLength);
                else {
                    g.strokeLine((col+1)*cellWidth, row*cellLength,
                            (col+1)*cellWidth, (row+1)*cellLength);
                    g.strokeLine(col*cellWidth, (row+1)*cellLength,
                            (col+1)*cellWidth, (row+1)*cellLength);
                }
            }
        }


        return a;
    }
}
