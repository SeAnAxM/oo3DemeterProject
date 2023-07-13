import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;

public class GameCanvas extends GCanvas implements MouseListener {
    private static final int CANVAS_WIDTH = 800;   // Modify this to suit your needs
    private static final int CANVAS_HEIGHT = 600;  // Modify this to suit your needs

    // Add more constants as needed
    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 50;
    private static final int BUTTON_Y_OFFSET = 100;
    private static final int BUTTON_SPACING = 60;

    public GameCanvas() {
        setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        addMouseListener(this);
    }

    public void initializeMenu() {
        GLabel title = new GLabel("Chasing Change");
        title.setFont("Serif-Bold-48");  // Modify the font family, style, and size as needed
        title.setLocation((CANVAS_WIDTH - title.getWidth()) / 2, BUTTON_Y_OFFSET / 2);  // Center the title
        add(title);
        String[] menuOptions = {"Start Game", "View Stats and Achievements", "Quit"};
        for(int i = 0; i < menuOptions.length; i++) {
            createButton(menuOptions[i], (CANVAS_WIDTH - BUTTON_WIDTH) / 2, BUTTON_Y_OFFSET + i * (BUTTON_HEIGHT + BUTTON_SPACING));
        }
    }

    private void createButton(String label, int x, int y) {
        GRoundRect button = new GRoundRect(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
        GLabel buttonText = new GLabel(label, x, y);
        buttonText.move((BUTTON_WIDTH - buttonText.getWidth()) / 2, (BUTTON_HEIGHT + buttonText.getAscent()) / 2); // Center the label
        add(button);
        add(buttonText);
    }

    // MouseListener implementation
    @Override
    public void mouseClicked(MouseEvent e) {
        GLabel source = (GLabel) getElementAt(e.getX(), e.getY());

        if (source != null) {
            switch (source.getLabel()) {
                case "Start Game":
                    // Assuming you have the startLayout1 method in StartGame
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

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }
}