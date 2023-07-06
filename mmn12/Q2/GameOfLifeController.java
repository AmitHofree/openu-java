import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameOfLifeController {
    private static final int START_X_POSITION = 150;
    private static final int START_Y_POSITION = 30;
    private static final int CELL_WIDTH = 30;
    private static final int CELL_HEIGHT = 30;

    @FXML
    private Canvas canvas;
    private final GameOfLife game;

    public GameOfLifeController() {
        game = new GameOfLife();
    }

    public void initialize() {
        drawGame();
    }

    @FXML
    public void nextGenerationPressed(ActionEvent actionEvent) {
        game.advanceGeneration();
        drawGame();
    }

    @FXML
    public void resetPressed(ActionEvent actionEvent) {
        game.resetMatrix();
        drawGame();
    }

    private void drawGame() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        boolean[][] state = game.getState();
        gc.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
        gc.setStroke(Color.BLACK);
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[i].length; j++) {
                int positionX = START_X_POSITION + i * CELL_WIDTH;
                int positionY = START_Y_POSITION + j * CELL_HEIGHT;
                if (state[i][j]) {
                    gc.setFill(Color.BLACK);
                } else {
                    gc.setFill(Color.TRANSPARENT);
                }
                gc.strokeRect(positionX, positionY, CELL_WIDTH, CELL_HEIGHT);
                gc.fillRect(positionX, positionY, CELL_WIDTH, CELL_HEIGHT);
            }
        }

    }
}