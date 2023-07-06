import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class DrawShapesController {
    @FXML
    private Canvas canvas;
    private Random random;

    public DrawShapesController() {
        random = new Random();
    }

    public void initialize() {
        drawRandomShapes();
    }

    @FXML
    public void drawShapesButtonPressed(ActionEvent actionEvent) {
        drawRandomShapes();
    }

    /**
     * Draw 10 random shapes according to MMN guidelines
     */
    public void drawRandomShapes() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int i = 0; i < 10; i++) {
            drawRandomShape(gc);
        }
    }

    /**
     * Pick a random shape and draw it according to random parameters
     * @param gc - Graphics context to draw on
     */
    private void drawRandomShape(GraphicsContext gc) {
        int choice = random.nextInt(3);
        double posX = getRandomPosition(canvas.getWidth());
        double posY = getRandomPosition(canvas.getHeight());
        double width = getRandomLength(canvas.getWidth());
        double height = getRandomLength(canvas.getHeight());
        Color color = getRandomColor();
        switch (choice) {
            case 0:
                drawRectangle(gc, posX, posY, width, height, color);
                break;
            case 1:
                drawOval(gc, posX, posY, width, height, color);
                break;
            case 2:
                drawLine(gc, posX, posY, width, height, color);
                break;
        }
    }

    /**
     * Draw a rectangle according to the parameters
     * @param gc - Graphics context to draw on
     * @param posX - X position for the upper left corner of the rectangle
     * @param posY - Y position for the upper left corner of the rectangle
     * @param width - Width of the rectangle
     * @param height - Height of the rectangle
     * @param color - Color of the rectangle fill and stroke
     */
    private void drawRectangle(GraphicsContext gc, double posX, double posY, double width, double height, Color color) {
        gc.setFill(color);
        gc.setStroke(color);
        gc.fillRect(posX, posY, width, height);
    }

    /**
     * Draw an oval according to the parameters
     * @param gc - Graphics context to draw on
     * @param posX - X position for the upper left bound of the oval
     * @param posY - Y position for the upper left bound of the oval
     * @param width - Width of the oval
     * @param height - Height of the oval
     * @param color - Color of the rectangle fill and stroke
     */
    private void drawOval(GraphicsContext gc, double posX, double posY, double width, double height, Color color) {
        gc.setFill(color);
        gc.setStroke(color);
        gc.fillOval(posX, posY, width, height);
    }

    /**
     * Draw a line according to the parameters
     * @param gc - Graphics context to draw on
     * @param posX - X position for the beginning of the line
     * @param posY - Y position for the beginning of the line
     * @param width - Width of the blocking rectangle
     * @param height - Height of the blocking rectangle
     * @param color - Color of the line stroke
     */
    private void drawLine(GraphicsContext gc, double posX, double posY, double width, double height, Color color) {
        double dstPosX = random.nextBoolean() ? posX + width : posX - width;
        double dstPosY = random.nextBoolean() ? posY + height : posY - height;
        gc.setStroke(color);
        gc.strokeLine(posX, posY, dstPosX, dstPosY);
    }

    /**
     * Helper method to get a random position in the canvas
     * @param maxPosition - Maximum position on this axis
     * @return - Double representing a random coordinate smaller than maxPosition
     */
    private double getRandomPosition(double maxPosition) {
        return random.nextFloat() * maxPosition;
    }

    /**
     * Helper method to get a random length in the canvas
     * @param maxLength - Maximum length on this axis
     * @return - Double representing a random length smaller than maxLength
     */
    private double getRandomLength(double maxLength) {
        return random.nextFloat() * maxLength;
    }

    /**
     * Helper method to pick a random color
     * @return - A random RGB color
     */
    private Color getRandomColor() {
        Random random = new Random();
        int red = random.nextInt(255);
        int green = random.nextInt(255);
        int blue = random.nextInt(255);
        return Color.rgb(red, green, blue, 1.0);
    }
}
