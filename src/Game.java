import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Main class
class Game extends JPanel {
    private Character primaryCharacter;
    private Character secondaryCharacter;
    private static Color backgroundDefault = Color.lightGray;
    private static Color backgroundHighlight = Color.yellow;
    private ArrowRepeater primaryArrowRepeater;
    private ArrowRepeater secondaryArrowRepeater;


    // Construct and start a game.
    private Game() {
        int initialWidth = 400 * 3 / 2;
        int initialHeight = 300 * 3 / 2;

        // Configure window
        JFrame myFrame = new JFrame();
        myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        myFrame.setResizable(true);
        myFrame.add(this);
        this.setPreferredSize(new Dimension(initialWidth, initialHeight));
        myFrame.pack();
        myFrame.setVisible(true);

        myFrame.addKeyListener(new KeyboardMan(this));

        primaryCharacter = new Character(this, "WASD.png");
        secondaryCharacter = new Character(this, "IJKL.png", initialWidth, initialHeight);
        ArrayList<Character> characters = new ArrayList<Character>(2);
        characters.add(primaryCharacter);
        characters.add(secondaryCharacter);

        primaryArrowRepeater = new ArrowRepeater(primaryCharacter, 0.06);
        secondaryArrowRepeater = new ArrowRepeater(secondaryCharacter, 0.06);
        primaryArrowRepeater.start();
        secondaryArrowRepeater.start();

        new GameLoop(this, characters).start(); // Starts infinite loop in painting/physics thread
    }

    void handle(Directions a, boolean pressed, WhichChar whichChar) {

        ArrowRepeater repeater = null;

        switch (whichChar) {
            case MAIN:
                repeater = primaryArrowRepeater;
                break;
            case SECONDARY:
                repeater = secondaryArrowRepeater;
                break;
        }

        switch (a) {
            case LEFT:
                repeater.leftEnabled = pressed;
                break;
            case RIGHT:
                repeater.rightEnabled = pressed;
                break;
            case DOWN:
                repeater.downEnabled = pressed;
                break;
            case UP:
                repeater.upEnabled = pressed;
                break;
        }
    }

    public void paintComponent(Graphics g) {
        if (primaryCharacter == null || secondaryCharacter == null)
            return;

        if (!primaryCharacter.isColliding(secondaryCharacter))
            g.setColor(backgroundDefault);
        else
            g.setColor(backgroundHighlight);

        g.fillRect(0, 0, this.getWidth(), this.getHeight()); // fill the screen with the background color

        // Paint characters
        g.drawImage(secondaryCharacter.icon, secondaryCharacter.getX(), secondaryCharacter.getY(), null);
        g.drawImage(primaryCharacter.icon, primaryCharacter.getX(), primaryCharacter.getY(), null);

    }

    public static void main(String[] args) {
        new Game();
    }
}


