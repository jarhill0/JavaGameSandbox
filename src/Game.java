import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Main class
class Game extends JPanel {
    private Character primaryCharacter;
    private Character secondaryCharacter;
    private Explosion explosion;

    private List<Paintable> sprites = new ArrayList<Paintable>(3);

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

        explosion = new Explosion();

        primaryCharacter = new Character(this, "WASD.png");
        secondaryCharacter = new Character(this, "IJKL.png", initialWidth, initialHeight);
        ArrayList<Character> characters = new ArrayList<Character>(2);
        characters.add(primaryCharacter);
        characters.add(secondaryCharacter);
        myFrame.addKeyListener(new KeyboardHandler(characters));

        sprites.add(primaryCharacter); // will be painted first and potentially painted over
        sprites.add(secondaryCharacter);
        sprites.add(explosion);

        myFrame.setVisible(true);
        new GameLoop(this, characters).start(); // Starts infinite loop in painting/physics thread
    }

    public void paintComponent(Graphics g) {
        if (primaryCharacter == null || secondaryCharacter == null)
            return;

        if (primaryCharacter.isColliding(secondaryCharacter)) {
            g.setColor(backgroundHighlight);
            int[] collisionLocation = primaryCharacter.collisionCenter(secondaryCharacter);
            explosion.setVisible(collisionLocation[0], collisionLocation[1]);

        } else {
            g.setColor(backgroundDefault);
            explosion.setInvisible();
        }

        g.fillRect(0, 0, this.getWidth(), this.getHeight()); // fill the screen with the background color

        for (Paintable paintable : sprites)
            paintable.paint(g);
    }

    public static void main(String[] args) {
        new Game();
    }
}


