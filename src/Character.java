import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Character {
    BufferedImage icon;
    private double[] position = new double[2];
    private Game game;
    private Vector velocity = new Vector();
    private Inertia inertia;
    private Friction friction;

    Character(Game game, String iconName) {
        position[0] = 0;
        position[1] = 0;
        this.game = game;
        getImage(iconName);
        inertia = new Inertia(this);
        friction = new Friction(this);
    }

    private void getImage(String iconName) {
        try {
            File file = new File(new File(System.getProperty("user.dir"), "src"), iconName);
            icon = ImageIO.read(file);
        } catch (IOException e) {
            System.out.println("Couldn't get image");
            System.exit(1);
        }
    }

    int getX() {
        return (int) position[0];
    }

    int getY() {
        return (int) position[1];
    }

    void step() {
        position[0] += velocity.getX();
        position[1] += velocity.getY();
        constrainToWindow();
    }

    private void constrainToWindow() {
        int width = game.getWidth();
        int height = game.getHeight();

        double bounciness = 0.1;

        if (position[0] + icon.getWidth() > width) {
            velocity = velocity.add(Vector.horizontalVector(-(bounciness + 1) * velocity.getX())); // The 1 cancels out the current velocity
            position[0] = width - icon.getWidth();
        } else if (position[0] < 0) {
            velocity = velocity.add(Vector.horizontalVector(-(bounciness + 1) * velocity.getX()));
            position[0] = 0;
        }


        if (position[1] + icon.getHeight() > height) {
            velocity = velocity.add(Vector.verticalVector(-(bounciness + 1) * velocity.getY()));
            position[1] = height - icon.getHeight();
        } else if (position[1] < 0) {
            velocity = velocity.add(Vector.verticalVector(-(bounciness + 1) * velocity.getY()));
            position[1] = 0;
        }
    }


    void accelerate(double mag, boolean right, boolean left, boolean down, boolean up) {
        Vector a = null;
        if (right == left && down == up) {
            // no "active" arrows; no acceleration
            return;
        } else if (right == left) { // down != up
            a = Vector.verticalVector(mag * (down ? 1 : -1));
        } else if (up == down) {
            a = Vector.horizontalVector(mag * (right ? 1 : -1));
        } else {
            if (right && down)
                a = new Vector(mag, Vector.EIGHTH);
            if (left && down)
                a = new Vector(mag, 3 * Vector.EIGHTH);
            if (right && up)
                a = new Vector(mag, -Vector.EIGHTH);
            if (left && up)
                a = new Vector(mag, -3 * Vector.EIGHTH);
        }

        velocity = velocity.add(a);
    }

    void frict() {
        velocity.shortenInPlace(0.99);
    }

    void startPhysics() {
        inertia.start();
        friction.start();
    }


}
