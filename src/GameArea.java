import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

/**
 * @author Daniel Spradley Snake game Part 2. 12/5/17
 * 
 */
public class GameArea extends JPanel implements ActionListener {
    private GameManager game;
    private GuiFrame guiFrame;
    // Set our current move
    private String currentMove = "";
    private Timer timer;
    // From game manager we get the user map if selected.
    private static String userInput;

    /**
     * Sets up our frame
     * 
     * @param GuiFrame
     */
    public GameArea(GuiFrame GuiFrame) {
        classTester();
        this.guiFrame = GuiFrame;
        setBackground(Color.LIGHT_GRAY);

        // Every x amount of milliseconds something happens in our action
        // listener.
        timer = new Timer(1000, this);
        timer.setInitialDelay(0);
        timer.start();

    }

    /**
     * Paints on walls and snake
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        GameManager.wall.paint(g);
        GameManager.snake.paint(g);

    }

    /**
     * stops the timer at button push
     */
    public void stopTimer() {
        timer.stop();
    }

    /**
     * starts the timer at button push
     */
    public void startTimer() {
        timer.start();
    }

    /**
     * Takes the usermap input from gamemanager at start.
     * 
     * @param i
     *            user input string
     */
    public static void userInput(String i) {
        userInput = i;
    }

    /**
     * Attempting to initiliaze my game here instead of tester
     */
    public void classTester() {
        game = new GameManager(userInput);
        // game.snakeMovement("RIGHT");

    }

    /**
     * Every x amount of milliseconds the snake moves but direction must be
     * inputed on direction keys.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        game.snakeMovement(currentMove);
        repaint();
    }

    /**
     * moves right
     */
    public void moveRight() {
        if (Snake.hitSomething == true) {
            stopTimer();
            int result = JOptionPane
                    .showConfirmDialog(guiFrame, "You're game has ended",
                            "Exit", JOptionPane.DEFAULT_OPTION);
            if (result == JOptionPane.OK_OPTION)
                System.exit(0);
        }
        currentMove = "RIGHT";

    }

    /**
     * moves up
     */
    public void moveUp() {
        if (Snake.hitSomething == true) {
            stopTimer();
            int result = JOptionPane
                    .showConfirmDialog(guiFrame, "You're game has ended",
                            "Exit", JOptionPane.DEFAULT_OPTION);
            if (result == JOptionPane.OK_OPTION)
                System.exit(0);
        }
        currentMove = "UP";

    }

    /**
     * moves left
     */
    public void moveLeft() {
        if (Snake.hitSomething == true) {
            stopTimer();
            int result = JOptionPane
                    .showConfirmDialog(guiFrame, "You're game has ended",
                            "Exit", JOptionPane.DEFAULT_OPTION);
            if (result == JOptionPane.OK_OPTION)
                System.exit(0);
        }
        currentMove = "LEFT";

    }

    /**
     * moves down
     */
    public void moveDown() {
        if (Snake.hitSomething == true) {
            stopTimer();
            int result = JOptionPane
                    .showConfirmDialog(guiFrame, "You're game has ended",
                            "Exit", JOptionPane.DEFAULT_OPTION);
            if (result == JOptionPane.OK_OPTION)
                System.exit(0);
        }
        currentMove = "DOWN";

    }

}
