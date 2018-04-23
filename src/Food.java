import java.awt.Point;
import java.util.Random;

/**
 * @author Daniel Spradley Snake game Part 2. 12/5/17
 * 
 */
public class Food extends Wall implements GameObjectConstants {
    protected static String[][] gameBoard = getBoard();
    // Values from wall;
    protected static int gameHeight = height;
    protected static int gameWidth = width;

    protected static int foodEaten;

    protected Food(int x, int y) {
        super(x, y);
        firstLocation();

    }

    /**
     * This will set our beginning food at "random location"
     */
    protected void firstLocation() {

        Random rand = new Random();
        int xFood = rand.nextInt(width);
        int yFood = rand.nextInt(height);
        if (notOccupied(xFood, yFood) == true) {
            gameBoard[yFood][xFood] = food;
            foodPoints.add(new Point(xFood, yFood));
        } else {
            firstLocation();
        }

    }

    /**
     * Method to update our food if its eaten.
     */
    protected static int[] newPoint(boolean b) {
        Random random = new Random();

        int xCoord = random.nextInt(gameWidth);
        int yCoord = random.nextInt(gameHeight);
        // Checking if its the max length of 5 or not.
        if (Snake.maxLength() == false) {
            if (notOccupied(xCoord, yCoord) == true) {
                gameBoard[yCoord][xCoord] = food;
                // Remove the last point from gameBoard
                Point oldPoint = foodPoints.pop();
                int x = oldPoint.x;
                int y = oldPoint.y;
                gameBoard[y][x] = blankSpot;
                // Now we put in the new point.
                foodPoints.add(new Point(xCoord, yCoord));
                foodEaten++;
            } else {
                newPoint(true);
            }

            // Testing updating labels.
            GuiFrame.setFoodEaten(true);
        } else if (Snake.maxLength() == true) {
            // Remove old point
            Point oldPoint = foodPoints.pop();
            int x = oldPoint.x;
            int y = oldPoint.y;
            gameBoard[y][x] = blankSpot;
        }

        // /I don't think i need these values
        int[] returnVals = new int[1];
        return returnVals;
    }

    /**
     * 
     * @return food score
     */
    public int foodScore() {
        return foodEaten;
    }

    /**
     * This will input our "random" x/y and see if that location has something.
     * 
     * @param x
     * @param y
     * @return
     */
    protected static boolean notOccupied(int x, int y) {

        if (gameBoard[y][x] != "X" && gameBoard[y][x] != "S"
                && gameBoard[y][x] != "s") {
            return true;
        }
        return false;
    }

}
