import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.SwingUtilities;

/**
 * @author Daniel Spradley Snake game Part 2. 12/5/17
 * 
 */
public class GameManager {
    // Original game manager variables below
    ArrayList<GameObject> objects = new ArrayList<GameObject>();
    public static Snake snake;
    String[] input;
    String direction;
    public static Wall wall;
    static String testing = null;

    public static void main(String[] args) {

        // Create an array to store our input
        ArrayList<String> myArrayList = new ArrayList<String>();
        // Check if command line is empty at start and use default map.
        if (args.length == 0) {
            // Default Map.
            testing = "20 11\n" + "1 0 18 0\n" + "1 10 18 10\n" + "0 1 0 9\n"
                    + "19 1 19 9\n" + "4 5 15 5\n";
        } else {
            Path file = Paths.get(args[0]);
            try (InputStream in = Files.newInputStream(file);
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(in))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // Need to add my input into an arraylist.
                    myArrayList.add(line);

                }
                // If file is not found it will catch the error and print it
                // out.
            } catch (IOException x) {
                System.err.println(x);
            }
            // Create a string from our array list and arrange by new line.
            StringBuilder sb = new StringBuilder();
            for (String s : myArrayList) {
                sb.append(s);
                sb.append("\n");
            }

            // Convert user input and pass to game manager.
            testing = sb.toString();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameArea.userInput(testing);
                new GuiFrame();
                // Input our string.

            }
        });

    }

    GameManager(String input) {
        // This will take input from gamemanagertester and split it by lines
        // using regex.
        this.input = input.split("\\r?\\n");
        creator();

    }

    /**
     * This method passes the coordinates for our wall into the Wall class and
     * makes new wall objects as needed. Will put most of this in tester and
     * create methods for calling them.
     */

    //
    public void creator() {
        Random r = new Random();
        // Feed wall parameters in and setup them
        wall = new Wall(input);
        objects.add(wall);
        // Random for foods. Will gen x=13,y=13 for now with fixed seed
        Random foodGen = new Random();
        // Food is creating random location from within class. The points for
        // this don't matter yet.
        Food food = new Food(1, 1);
        objects.add(food);
        // Create snake in random location. The points for this don't matter
        // yet.
        snake = new Snake(1, 1);
        // Setting gamemanager snake.
        this.snake = snake;

    }

    /**
     * This will handle the input for snake movement as it happens.
     * 
     * @param s
     */
    public void snakeMovement(String s) {
        snake.setDirection(s);
        System.out.println(snake.toString());
    }

    @Override
    public String toString() {
        return snake.toString();
    }

    protected void paintComponent(Graphics g) {

    }

}
