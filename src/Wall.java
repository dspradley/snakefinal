import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.ImageIcon;

import javafx.scene.shape.Line;

import com.sun.prism.image.Coords;

/**
 * @author Daniel Spradley Snake game Part 2. 12/5/17
 * 
 */
public class Wall extends GameObject implements GameObjectConstants {
    protected static int width;
    protected static int height;
    // HAVE TO MAKE IT STATIC TO ACCESS WITH GETTER!!!!!!!!!
    protected static String[][] gameBoard;
    protected static String wallCharacter = "X";
    protected static String blankSpot = ".";
    // By default snake won't have eaten at beginning.
    protected static boolean hasEaten = false;
    // Location of food to be updated.
    protected static Deque<Point> foodPoints = new LinkedList<>();
    protected static Deque<Point> wallPoints = new LinkedList<>();
    // For block image
    private Image wall2;
    private Image foodImg;

    // For snake/food class.
    protected static String food = "F";

    protected Wall(String[] wall) {
        super(wall);
        inputManipulator();
        loadImages();

    }

    /**
     * Default constructor
     * 
     * @param x
     * @param y
     */
    public Wall(int x, int y) {
        super(x, y);
    }

    /**
     * Splits apart map input for use.
     */
    public void inputManipulator() {
        // Creating our dimensions from first line.
        String holder2 = wall[0];
        String[] splitString2 = holder2.split(" ");
        width = Integer.parseInt(splitString2[0]);
        height = Integer.parseInt(splitString2[1]);
        // Creating our array to hold points now that we have width
        gameBoard = new String[height][width];

        // first we will extract our coordinates of the wall.
        for (int x = 1; x < wall.length; x++) {
            String holder = wall[x];
            String[] splitString = holder.split(" ");
            for (int y = 0; y < splitString.length;) {
                Point topLeft = new Point(Integer.parseInt(splitString[0]),
                        (Integer.parseInt(splitString[1])));
                Point botRight = new Point(Integer.parseInt(splitString[2]),
                        (Integer.parseInt(splitString[3])));

                filling(topLeft.x, topLeft.y, botRight.x, botRight.y);
                break;
            }

        }
    }

    private void filling(int xtopleft, int ytopleft, int xbottomright,
            int ybottomright) {
        // rectBuilder();
        int xneeded = xbottomright - xtopleft;
        for (int row = 0; row <= height - 1; row++) // loops through rows
        {
            for (int col = 0; col <= width - 1; col++) // loops through columns
            {
                if (xneeded > 0 && col >= xtopleft && col <= xbottomright
                        && row == ytopleft && row == ybottomright
                        && gameBoard[row][col] != wallCharacter) {
                    gameBoard[row][col] = wallCharacter;
                }
                if (row >= ytopleft && row <= ybottomright && col >= xtopleft
                        && col <= xbottomright
                        && gameBoard[row][col] != wallCharacter) {
                    gameBoard[row][col] = wallCharacter;
                } else if (col == 0 || col == (width - 1)) {
                    gameBoard[row][col] = wallCharacter;
                } else if (row == 0 || row == height - 1) {
                    gameBoard[row][col] = wallCharacter;
                } else if (gameBoard[row][col] != wallCharacter) {
                    gameBoard[row][col] = ".";
                }
            }
        }

        // Below will fill these into points.
        for (int row = 0; row <= height - 1; row++) // loops through rows
        {
            for (int col = 0; col <= width - 1; col++) // loops through columns
            {
                if (gameBoard[row][col] == wallCharacter) {
                    wallPoints.add(new Point(col, row));
                }
            }
        }

    }

    private void testPrinter() {
        // Prints out board with walls
        for (int row = 0; row <= height - 1; row++) // loops through rows
        {
            for (int col = 0; col <= width - 1; col++) // loops through columns
            {
                System.out.print(gameBoard[row][col] + " ");
            }
            System.out.print("\n"); // takes a new line before each new print
        }

    }

    /**
     * 
     * @return returns out board
     */
    public static String[][] getBoard() {
        return gameBoard;
    }

    /**
     * sets our board
     * 
     * @param p
     */
    public void setBoard(String[][] p) {
        gameBoard = p;
    }

    /**
     * This class paints our walls onto the board.
     * 
     * @param g
     */
    protected void paint(Graphics g) {
        g.setColor(Color.BLACK);
        // ReDrawing our walls
        for (Point p : wallPoints) {
            g.drawImage(wall2, (p.x) * 25, (p.y) * 25, null);

        }
        // ReDrawing our food points.
        for (Point p : foodPoints) {
            g.drawImage(foodImg, (p.x) * 25, (p.y) * 25, null);

        }

    }

    /*
     * Loads an image from the current folder
     */
    private void loadImages() {

        ImageIcon iih = new ImageIcon("Brick_Block.png");
        wall2 = iih.getImage();

        ImageIcon food = new ImageIcon("food.png");
        foodImg = food.getImage();

    }

}
