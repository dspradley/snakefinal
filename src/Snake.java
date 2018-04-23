import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.ImageIcon;

/**
 * @author Daniel Spradley Snake game Part 2. 12/5/17
 * 
 */
public class Snake extends Wall implements GameObjectConstants {

    protected int xFirst;
    protected int yFirst;
    protected String[][] gameBoard = getBoard();
    protected int height = Wall.height;
    protected int width = Wall.width;
    protected String snake = "S";
    protected boolean originSet = false;
    protected String snakeTail = "s";
    protected String snakeHead = "S";
    protected String blank = ".";
    // To hold our points for snake.
    protected static Deque<Point> snakePoints = new LinkedList<>();
    // So we can check for a collision.
    public static boolean hitSomething;

    // For block image
    private Image snakeImg;

    protected Snake(int x, int y) {
        super(x, y);
        snakeCreation(x, y);
        loadImages();
    }

    protected void snakeCreation(int x, int y) {

        // Random for placing snake at random location
        Random rand = new Random();
        int xFood = rand.nextInt(width);
        int yFood = rand.nextInt(height);
        xFirst = 17;
        yFirst = yFood;
        xFirst = xFood;
        if (gameBoard[yFood][xFood] != "X" && originSet == false) {
            gameBoard[yFood][xFood] = snakeHead;
            originSet = true;
            snakePoints.add(new Point(xFirst, yFirst));
            // Update walls main board
            setBoard(gameBoard);
        } else {
            snakeCreation(1, 1);
        }

    }

    /**
     * Checking for a wall or out of bounds.
     * 
     * @param d
     * @return
     */
    protected boolean nothingInWay(String d) {
        Point lastPoint = snakePoints.peekFirst();

        int xCoord = lastPoint.x;
        int yCoord = lastPoint.y;

        boolean response = true;
        switch (d) {
        case "LEFT":

            if (gameBoard[yCoord][xCoord - 1] == "X"
                    || gameBoard[yCoord][xCoord - 1] == "s") {
                response = false;
                break;
            } else {
                response = true;
                break;
            }
        case "RIGHT":
            // x + 1
            if (gameBoard[yCoord][xCoord + 1] == "X"
                    || gameBoard[yCoord][xCoord + 1] == "s") {
                response = false;
                break;
            } else {
                response = true;
                break;
            }
        case "DOWN":
            // y + 1
            if (gameBoard[yCoord + 1][xCoord] == "X"
                    || gameBoard[yCoord + 1][xCoord] == "s") {
                response = false;
                break;
            } else {
                response = true;
                break;
            }
        case "UP":
            // y - 1
            if (gameBoard[yCoord - 1][xCoord] == "X"
                    || gameBoard[yCoord - 1][xCoord] == "s") {
                response = false;
                break;
            } else {
                response = true;
                break;
            }
        default:
            response = true;
        }
        return response;
    }

    /**
     * Detect if the next point is food.
     * 
     * @return
     */
    protected boolean foodDetection(String d) {
        Point lastPoint = snakePoints.peek();
        int xCoord = lastPoint.x;
        int yCoord = lastPoint.y;
        boolean response = false;
        switch (d) {
        case "LEFT":
            if (gameBoard[yCoord][xCoord - 1] == food) {
                response = true;
            }
        case "RIGHT":
            if (gameBoard[yCoord][xCoord + 1] == food) {
                response = true;
            }
        case "DOWN":
            if (gameBoard[yCoord + 1][xCoord] == food) {
                response = true;
            }
        case "UP":
            if (gameBoard[yCoord - 1][xCoord] == food) {
                response = true;
            }
        }
        return response;
    }

    protected void setDirection(String direction) {
        Point lastPoint = snakePoints.peekFirst();
        int xCoord = lastPoint.x;
        int yCoord = lastPoint.y;

        switch (direction) {
        case "LEFT":
            // Checks if walls or out of bounds is in our path.
            if (nothingInWay(direction) == true) {
                // If snake hasn't eaten.
                if (hasEaten == false) {

                    // Check if theres food in our way.
                    if (foodDetection(direction) == true) {
                        snakePoints.addFirst(new Point(xCoord - 1, yCoord));
                        gameBoard[yCoord][xCoord] = snakeTail;
                        gameBoard[yCoord][xCoord - 1] = snakeHead;
                        Food.newPoint(true);
                        hasEaten = true;

                    } else if (foodDetection(direction) == false) {
                        // WORKING
                        snakePoints.addFirst(new Point(xCoord - 1, yCoord));
                        gameBoard[snakePoints.peekLast().y][snakePoints
                                .peekLast().x] = blank;
                        snakePoints.removeLast();
                        gameBoard[snakePoints.peekFirst().y][snakePoints
                                .peekFirst().x] = snakeHead;
                    }

                }
                // If snake has eaten.
                if (hasEaten == true) {

                    if (foodDetection(direction) == true) {
                        gameBoard[yCoord][xCoord] = snakeTail;
                        gameBoard[yCoord][xCoord - 1] = snakeHead;
                        snakePoints.addFirst(new Point(xCoord - 1, yCoord));
                        Food.newPoint(true);
                    } else if (foodDetection(direction) == false) {
                        gameBoard[yCoord][xCoord] = snakeTail;
                        gameBoard[yCoord][xCoord - 1] = snakeHead;
                        // Removing last part of tail
                        gameBoard[snakePoints.peekLast().y][snakePoints
                                .peekLast().x] = blank;
                        // Remove old points
                        snakePoints.removeLast();
                        snakePoints.addFirst(new Point(xCoord - 1, yCoord));
                    }
                }
                // Update walls main board
                setBoard(gameBoard);
                // If it detects something
            } else if (nothingInWay(direction) == false) {
                System.out.println("You collided with something to the left");
                hitSomething = true;
            }
            break;
        case "RIGHT":
            // Checks if walls or out of bounds is in our path.
            if (nothingInWay(direction) == true) {
                // If snake hasn't eaten.
                if (hasEaten == false) {

                    // Check if theres food in our way.
                    if (foodDetection(direction) == true) {
                        snakePoints.addFirst(new Point(xCoord + 1, yCoord));
                        gameBoard[yCoord][xCoord] = snakeTail;
                        gameBoard[yCoord][xCoord + 1] = snakeHead;
                        Food.newPoint(true);
                        hasEaten = true;
                    } else {
                        // WORKING
                        snakePoints.addFirst(new Point(xCoord + 1, yCoord));
                        gameBoard[snakePoints.peekLast().y][snakePoints
                                .peekLast().x] = blank;
                        snakePoints.removeLast();
                        gameBoard[snakePoints.peekFirst().y][snakePoints
                                .peekFirst().x] = snakeHead;
                    }

                }
                // If snake has eaten.
                if (hasEaten == true) {

                    if (foodDetection(direction) == true) {
                        gameBoard[yCoord][xCoord] = snakeTail;
                        gameBoard[yCoord][xCoord + 1] = snakeHead;
                        snakePoints.addFirst(new Point(xCoord + 1, yCoord));
                        Food.newPoint(true);

                    } else if (foodDetection(direction) == false) {
                        System.out.println("Thi@@@@2wwwwsss");
                        gameBoard[yCoord][xCoord] = snakeTail;
                        gameBoard[yCoord][xCoord + 1] = snakeHead;
                        // Removing last part of tail
                        gameBoard[snakePoints.peekLast().y][snakePoints
                                .peekLast().x] = blank;
                        // Remove old points
                        snakePoints.removeLast();
                        snakePoints.addFirst(new Point(xCoord + 1, yCoord));
                    }
                }
                // Update walls main board
                setBoard(gameBoard);
                // If it detects something
            } else if (nothingInWay(direction) == false) {
                System.out.println("You collided with something to the right");
                hitSomething = true;
            }
            break;
        case "DOWN":
            // Checks if walls or out of bounds is in our path.
            if (nothingInWay(direction) == true) {
                // If snake hasn't eaten.
                if (hasEaten == false) {

                    // Check if theres food in our way.
                    if (foodDetection(direction) == true) {
                        snakePoints.addFirst(new Point(xCoord, yCoord + 1));
                        gameBoard[yCoord][xCoord] = snakeTail;
                        gameBoard[yCoord + 1][xCoord] = snakeHead;
                        Food.newPoint(true);
                        hasEaten = true;
                    } else {
                        // WORKING
                        snakePoints.addFirst(new Point(xCoord, yCoord + 1));
                        gameBoard[snakePoints.peekLast().y][snakePoints
                                .peekLast().x] = blank;
                        snakePoints.removeLast();
                        gameBoard[snakePoints.peekFirst().y][snakePoints
                                .peekFirst().x] = snakeHead;
                    }

                }
                // If snake has eaten.
                if (hasEaten == true) {

                    if (foodDetection(direction) == true) {
                        gameBoard[yCoord][xCoord] = snakeTail;
                        gameBoard[yCoord + 1][xCoord] = snakeHead;
                        snakePoints.addFirst(new Point(xCoord, yCoord + 1));
                        Food.newPoint(true);

                    } else if (foodDetection(direction) == false) {
                        System.out.println("Thi@@@@2wwwwsss");
                        gameBoard[yCoord][xCoord] = snakeTail;
                        gameBoard[yCoord + 1][xCoord] = snakeHead;
                        // Removing last part of tail
                        gameBoard[snakePoints.peekLast().y][snakePoints
                                .peekLast().x] = blank;
                        // Remove old points
                        snakePoints.removeLast();
                        snakePoints.addFirst(new Point(xCoord, yCoord + 1));
                    }
                }
                // Update walls main board
                setBoard(gameBoard);

                // If it detects something
            } else if (nothingInWay(direction) == false) {
                System.out.println("You collided with something below");
                hitSomething = true;
            }
            break;

        case "UP":
            // Checks if walls or out of bounds is in our path.
            if (nothingInWay(direction) == true) {
                // If snake hasn't eaten.
                if (hasEaten == false) {

                    // Check if theres food in our way.
                    if (foodDetection(direction) == true) {
                        snakePoints.addFirst(new Point(xCoord - 1, yCoord));
                        gameBoard[yCoord][xCoord] = snakeTail;
                        gameBoard[yCoord - 1][xCoord] = snakeHead;
                        Food.newPoint(true);
                        hasEaten = true;
                    } else {
                        // WORKING
                        snakePoints.addFirst(new Point(xCoord, yCoord - 1));
                        gameBoard[snakePoints.peekLast().y][snakePoints
                                .peekLast().x] = blank;
                        snakePoints.removeLast();
                        gameBoard[snakePoints.peekFirst().y][snakePoints
                                .peekFirst().x] = snakeHead;
                    }

                }
                // If snake has eaten.
                if (hasEaten == true) {

                    if (foodDetection(direction) == true) {
                        gameBoard[yCoord][xCoord] = snakeTail;
                        gameBoard[yCoord - 1][xCoord] = snakeHead;
                        snakePoints.addFirst(new Point(xCoord, yCoord - 1));
                        Food.newPoint(true);

                    } else if (foodDetection(direction) == false) {
                        gameBoard[yCoord][xCoord] = snakeTail;
                        gameBoard[yCoord - 1][xCoord] = snakeHead;
                        // Removing last part of tail
                        gameBoard[snakePoints.peekLast().y][snakePoints
                                .peekLast().x] = blank;
                        // Remove old points
                        snakePoints.removeLast();
                        snakePoints.addFirst(new Point(xCoord, yCoord - 1));
                    }
                }
                // Update walls main board
                setBoard(gameBoard);
                // If it detects something
            } else if (nothingInWay(direction) == false) {
                System.out.println("You collided with something above");
                hitSomething = true;
            }
            break;

        }

    }

    protected boolean hasEaten() {

        return false;
    }

    /**
     * will check for max length. Larger than 5 turns boolean val on.
     * 
     * @return
     */
    public static boolean maxLength() {
        boolean maxLength = false;
        if (snakePoints.size() == 4) {
            maxLength = true;
        }
        return maxLength;
    }

    private void testPrinter() {

        // Prints out board with walls
        for (int row = 0; row <= height - 1; row++) // loops through rows
        {
            for (int col = 0; col <= width - 1; col++) // loops through columns
            {
                System.out.print(gameBoard[row][col] + "");
            }
            System.out.print("\n"); // takes a new line before each new print
        }

    }

    /**
     * Redid toString to see the map in console
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        for (int row = 0; row <= height - 1; row++) // loops through rows
        {
            for (int col = 0; col <= width - 1; col++) // loops through columns
            {
                sb.append(gameBoard[row][col] + " ");
            }
            sb.append("\n"); // takes a new line before each new print
        }
        String returnString = sb.toString();
        return returnString;

    }

    protected void paint(Graphics g) {
        for (Point p : snakePoints) {
            g.drawImage(snakeImg, (p.x) * 25, (p.y) * 25, null);
        }
    }

    /*
     * Loads an image from the current folder
     */
    private void loadImages() {

        ImageIcon iih = new ImageIcon("mushroom.png");
        snakeImg = iih.getImage();
    }

}
