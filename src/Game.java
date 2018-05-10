import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Main class
class Game extends JPanel {
    private Character primaryCharacter;
    private Character secondaryCharacter;
    private static Color backgroundDefault = Color.lightGray;
    private static Color backgroundHighlight = Color.yellow;


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

        primaryCharacter = new Character(this, "WASD.png");
        secondaryCharacter = new Character(this, "IJKL.png", initialWidth, initialHeight);
        ArrayList<Character> characters = new ArrayList<Character>(2);
        characters.add(primaryCharacter);
        characters.add(secondaryCharacter);

        myFrame.addKeyListener(new KeyboardHandler(characters));

        new GameLoop(this, characters).start(); // Starts infinite loop in painting/physics thread
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


