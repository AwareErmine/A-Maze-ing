import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

        final TextField width = new TextField();
        width.setPromptText("enter desired maze width");

        final TextField height = new TextField();
        height.setPromptText("enter desired maze height");

        Button startButton = new Button("Start");
        startButton.setOnAction(event -> {
            int w = 25; // TODO: make text valid and user input handled by a separate class (maybe)
            int h = 25;
            if (width.getText() != null && !width.getText().isEmpty()) {
                w = Integer.parseInt(width.getText().replaceAll("[\\D]", ""));
                w = (w < 3 || Math.abs(w - h) > 15 ? 25 : w);
            }
            if (height.getText() != null && !height.getText().isEmpty()) {
                h = Integer.parseInt(height.getText().replaceAll("[\\D]", ""));
                h = (h < 3 || Math.abs(w - h) > 15 ? 25 : h);
            }
            Grid currentMaze = new Grid(w, h);
            gameMenu(stage, currentMaze);
        });

        Button quitButton = new Button("Quit");
        quitButton.setOnAction(event -> {
            stage.close();
        });

        HBox buttons = new HBox(startButton, quitButton);
        buttons.setAlignment(Pos.BOTTOM_CENTER);

        HBox inputs = new HBox(width, height);
        inputs.setAlignment(Pos.BOTTOM_CENTER);

        VBox v = new VBox(title, buttons, inputs);
        v.setAlignment(Pos.TOP_CENTER);

        Scene s = new Scene(v, 500, 500);
        stage.setScene(s);
        stage.show();
    }

    public static void gameMenu(Stage stage, Grid currentMaze) {
        Text title = new Text("Get Mazed!");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 22));

        Canvas maze = drawMaze(currentMaze, 500, 500);
        VBox v = new VBox(title, maze);
        v.setAlignment(Pos.CENTER);
        Scene s = new Scene(v);
        stage.setScene(s);
        stage.show();

        s.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT -> currentMaze.move('l');
                case RIGHT -> currentMaze.move('r');
                case UP -> currentMaze.move('u');
                case DOWN -> currentMaze.move('d');
            }
            gameMenu(stage, currentMaze);
        });
    }

    public static Canvas drawMaze(Grid maze, int length, int width) {
        Cell[][] grid = maze.getGrid();
        int[] pos = maze.getPos();
        int[] fin = maze.getFinish();
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

                if (fin[0] == col && fin[1] == row) {
                    g.setFill(Color.RED);
                    g.fillRect(col * cellWidth, row * cellLength, cellWidth, cellLength);
                    g.setFill(Color.WHITE);
                }
                if (pos[0] == col && pos[1] == row) {
                    g.setFill(Color.GREEN);
                    g.fillRect(col * cellWidth, row * cellLength, cellWidth, cellLength);
                    g.setFill(Color.WHITE);
                }

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
