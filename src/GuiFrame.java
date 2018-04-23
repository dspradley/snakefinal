import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Daniel Spradley Snake game Part 2. 12/5/17
 * 
 */
public class GuiFrame extends JFrame implements KeyListener {

    private static int amountEaten;
    private JLabel foodLabel;
    private JButton startButton, stopButton;
    // Our game area is going to be going on in this.
    private GameArea actionPanel = new GameArea(null);

    GuiFrame() {
        guiInitializer();
    }

    /**
     * Updates the score.
     * 
     * @param b
     *            input true when something is eaten.
     */
    public static void setFoodEaten(boolean b) {
        if (b == true) {
            amountEaten++;
            System.out.println(amountEaten);

        }
    }

    private void guiInitializer() {
        JPanel controlPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        controlPanel.setBackground(Color.WHITE);
        rightPanel.setBackground(Color.yellow);

        rightPanel.setLayout(new GridLayout(3, 1));
        // rightPanel.setBackground(rightPanelColor);

        // Below will hold my foods eaten
        JPanel infoHolder = new JPanel();
        infoHolder.setLayout(new GridLayout(6, 1, 0, 0));
        // infoHolder.setBackground(rightPanelColor);

        // Create my Labels
        foodLabel = new JLabel("SCORE: " + amountEaten);

        // Create my buttons.
        startButton = new JButton("Start Time");
        startButton.setFocusable(false);
        stopButton = new JButton("Stop Time");
        stopButton.setFocusable(false);

        // Stops timer.
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                actionPanel.stopTimer();
            }
        });
        // Starts timer
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                actionPanel.startTimer();
            }
        });

        // TODO: Need to add the button addActionListener
        rightPanel.setPreferredSize(new Dimension(100, 100));

        // Returns content frame object for this frame
        Container pane = this.getContentPane();
        pane.setLayout(new BorderLayout());

        // Adding panels
        pane.add(actionPanel, BorderLayout.CENTER);
        pane.add(rightPanel, BorderLayout.LINE_END);

        // Hold controls for game
        controlPanel.add(startButton);
        controlPanel.add(stopButton);
        controlPanel.add(foodLabel);

        rightPanel.add(controlPanel);

        setTitle("Snake Game");
        setSize(1500, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFocusable(true);
        addKeyListener(this);
        this.requestFocus();
        setVisible(true);

    }

    /**
     * Nothing needed here.
     * 
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * 
     * @param e
     *            is our user inputs
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("right");
            actionPanel.moveRight();
            foodLabel.setText("Score: " + amountEaten);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("LEFT");
            actionPanel.moveLeft();
            foodLabel.setText("Score: " + amountEaten);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("UP");
            actionPanel.moveUp();
            foodLabel.setText("Score: " + amountEaten);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.println("DOWN");
            actionPanel.moveDown();
            foodLabel.setText("Score: " + amountEaten);
        } else {
            System.out.println("No Direction Set");
        }

    }

    /**
     * Nothing needed here
     * 
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }

}
