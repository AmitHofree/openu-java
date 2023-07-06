import java.util.Random;

public class GameOfLife {
    private static final int BOARD_SIZE = 10;
    private boolean[][] matrix;
    private boolean isFirstStep;

    public GameOfLife() {
        isFirstStep = true;
        matrix = generateEmptyMatrix();
    }

    public boolean[][] getState() {
        return matrix;
    }

    public void resetMatrix() {
        isFirstStep = true;
        matrix = generateEmptyMatrix();
    }

    public void advanceGeneration() {
        if (isFirstStep) {
            isFirstStep = false;
            matrix = generateRandomMatrix();
        } else {
            matrix = generateNextGenerationMatrix(matrix);
        }
    }

    private static boolean[][] generateNextGenerationMatrix(boolean[][] currentGenerationMatrix) {
        boolean[][] newGenerationMatrix = new boolean[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                boolean isAlive = currentGenerationMatrix[i][j];
                int countLiveNeighbors = calculateLiveNeighborsCount(currentGenerationMatrix, i, j);
                newGenerationMatrix[i][j] = calculateNextState(isAlive, countLiveNeighbors);
            }
        }
        return newGenerationMatrix;
    }

    private static boolean calculateNextState(boolean isAlive, int countLiveNeighbors) {
        if (isAlive) {
            if (countLiveNeighbors <= 1) return false;
            return countLiveNeighbors < 4;
        } else {
            return countLiveNeighbors == 3;
        }
    }

    private static int calculateLiveNeighborsCount(boolean[][] currentMatrix, int i, int j) {
        int aliveNeighbours = 0;
        for (int k = -1; k <= 1; k++) {
            for (int l = -1; l <= 1; l++) {
                // Ignore matrix out of bounds, assume cells are dead
                // Also, no need to count the cell itself as a neighbor
                if ((k != 0 || l != 0) && (i + k) >= 0 && (i + k) < BOARD_SIZE && (j + l) >= 0 && (j + l) < BOARD_SIZE) {
                    aliveNeighbours += currentMatrix[i + k][j + l] ? 1 : 0;
                }
            }
        }
        return aliveNeighbours;
    }

    private static boolean[][] generateEmptyMatrix() {
        boolean[][] emptyMatrix = new boolean[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                emptyMatrix[i][j] = false;
            }
        }
        return emptyMatrix;
    }

    private static boolean[][] generateRandomMatrix() {
        Random random = new Random();
        boolean[][] randomMatrix = new boolean[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                randomMatrix[i][j] = random.nextBoolean();
            }
        }
        return randomMatrix;
    }
}
