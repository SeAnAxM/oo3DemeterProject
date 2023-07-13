import acm.graphics.GCanvas;
import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GRect;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainMenu extends JFrame {

    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 600;

    private GameCanvas gameCanvas;
    private CharacterSetup characterSetup;

    private boolean characterSetupDone = false;

    public MainMenu(String title) {
        super(title);
        gameCanvas = new GameCanvas();
        add(gameCanvas);
        characterSetup = new CharacterSetup();
    }

    public static void main(String[] args) {
        MainMenu frame = new MainMenu("Chasing Change: The Escape Room for World Changers");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // override paint method
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (!characterSetupDone) {
            characterSetup.createAccount();
            characterSetupDone = true;
        }
        gameCanvas.initializeMenu();
    }

    private class GameCanvas extends GCanvas implements MouseListener {

        public GameCanvas() {
            addMouseListener(this);
        }

        public void initializeMenu() {
            removeAll();  // Clear the canvas

            // Create title
            GLabel title = new GLabel("Chasing Change");
            title.setFont(new Font("Serif", Font.BOLD, 50));
            title.setLocation((CANVAS_WIDTH - title.getWidth()) / 2, 100);
            add(title);

            // Create buttons
            createButton("Start Game", CANVAS_WIDTH / 2.0, 200);
            createButton("View Stats and Achievements", CANVAS_WIDTH / 2.0, 300);
            createButton("Quit", CANVAS_WIDTH / 2.0, 400);
        }

        private void createButton(String title, double x, double y) {
            GLabel label = new GLabel(title);
            label.setFont(new Font("Serif", Font.PLAIN, 24));
            double width = label.getWidth();
            double height = label.getHeight();

            GRect rect = new GRect(x - width / 2 - 10, y - height, width + 20, height + 10);
            rect.setFilled(true);
            rect.setColor(Color.LIGHT_GRAY);

            GCompound button = new GCompound();
            button.add(rect);
            button.add(label, x - width / 2, y);

            add(button);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            GCompound clicked = (GCompound) getElementAt(e.getX(), e.getY());
            if (clicked != null) {
                GLabel label = (GLabel) clicked.getElement(1);  // the label is the second element
                String buttonText = label.getLabel();

                removeAll();  // Clear the canvas

                switch (buttonText) {
                    case "Start Game":
                        StartGame startgame = new StartGame();
                        startgame.startLayout1();
                        break;
                    case "View Stats and Achievements":
                        Leaderboards leaderboards = new Leaderboards();
                        leaderboards.displayLeaderboards();
                        break;
                    case "Quit":
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid option selected. Please try again.");
                        break;
                }
            }
        }

        // Other required methods for the MouseListener interface
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
    }
}
