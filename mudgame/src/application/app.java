package application;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class app extends Application {

    /// Enter the rows, clms, and delay duration in milliseconds
    static map maze = new map(10, 30);
    /// Enter cell width in pixels
    int cellWidth = 30;

    @Override
    public void start(Stage primartStage) {
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        primartStage.setScene(new Scene(root, maze.clms * cellWidth, maze.rows * cellWidth));
        primartStage.show();
        primartStage.setTitle("Maze Generator");
        primartStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });

        Canvas canvas = new Canvas(maze.clms * cellWidth, maze.rows * cellWidth);
        root.add(canvas, 1, 1);

        final AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(final long now) {
                print(canvas.getGraphicsContext2D());
            }
        };

        Thread thread = new Thread(() -> {
            maze.create();
        });

        timer.start();
        thread.start();
    }

    void print(GraphicsContext gc){
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.GRAY);
        gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        for (int i = 0; i < maze.cells.length; i++) {
            for (int j = 0; j < maze.cells[i].length; j++) {
                printCell(gc, maze.cells[i][j]);
            }
        }
    }

    void printCell(GraphicsContext gc, Cell cell){
        int startX = cell.x * cellWidth;
        int startY = cell.y * cellWidth;

        // print color
        if(cell.isVisited){
            gc.setFill(Color.WHITE);
            gc.fillRect(startX, startY, cellWidth, cellWidth);
        }
            
        // print top line
        if(!cell.walls[0])
            gc.strokeLine(startX, startY, startX + cellWidth, startY);

        // print right line
        if(!cell.walls[1])
            gc.strokeLine(startX + cellWidth, startY, startX + cellWidth, startY + cellWidth);

        // print bottom line
        if(!cell.walls[2])
            gc.strokeLine(startX, startY + cellWidth, startX + cellWidth, startY + cellWidth);

        // print left line
        if(!cell.walls[3])
            gc.strokeLine(startX, startY, startX, startY + cellWidth);

    }
    
}
