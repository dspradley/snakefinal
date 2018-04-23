import java.awt.Point;

/**
 * @author Daniel Spradley Snake game Part 2. 12/5/17
 * 
 */
public abstract class GameObject implements GameObjectConstants {
    // A rectangle specifies an area in a coordinate space that is enclosed by
    // the rectangle objects's upper left point(x,y) in the coordinate space,
    // its width, and its height.
    protected Point topLeft;
    protected Point bottomRight;
    protected Point singleLocation;
    protected String[] wall;
    // Simple array to keep track of items on board for now.
    protected String[][] gameBoard;

    protected GameObject(String[] wall) {
        this.wall = wall;
    }

    // Constructor for apple and snake.
    protected GameObject(int x, int y) {
        singleLocation = new Point(x, y);
    }

    // Constructor for our walls.
    protected GameObject(int x, int y, int lowerRightX, int lowerRightY) {
        topLeft = new Point(x, y);
        bottomRight = new Point(lowerRightX, lowerRightY);
    }

}
