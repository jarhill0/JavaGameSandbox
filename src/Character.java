import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

class Character {
    BufferedImage icon;
    private double[] position = new double[2];
    private Game game;
    private Vector velocity = new Vector();

    private static double acceleration = 0.22;

    // remember which keys are being pressed
    private boolean down = false;
    private boolean up = false;
    private boolean left = false;
    private boolean right = false;

    Character(Game game, String iconName) {
        // The method isColliding assumes the image is a 50x50px image. Deal with it.
        this(game, iconName, 0, 0);
    }

    Character(Game game, String iconName, int x, int y) {
        // The method isColliding assumes the image is a 50x50px image. Deal with it.
        position[0] = x;
        position[1] = y;
        this.game = game;
        getImage(iconName);
    }

    private void getImage(String iconName) {
        try {
            icon = ImageIO.read(getClass().getResource("/" + iconName));
        } catch (IOException e) {
            System.out.println("Couldn't get image");
            System.exit(1);
        }
    }

    /*

    For both getX() and getY():

    Location is stored at decimal precision but returned in int precision because we can only paint to integer
    coordinates, and I'm not about to get into antialiasing stuff.

    */

    int getX() {
        return round(position[0]);
    }

    int getY() {
        return round(position[1]);
    }

    private static int round(double num) {
        return num % 1 < 0.5 ? (int) num : (int) num + 1;
    }

    void step() {
        // integrate acceleration -> velocity
        accelerate();

        // integrate velocity -> position
        position[0] += velocity.getX();
        position[1] += velocity.getY();
        constrainToWindow();
        frict();
    }


    // Make sure the character isn't off the edge of the window and handle bounce-back.
    private void constrainToWindow() {
        int width = game.getWidth();
        int height = game.getHeight();

        double bounciness = 0.1;

        if (position[0] + icon.getWidth() > width) { // if we're off the right edge
            // Bounce our x-velocity back. For example, (-1.1 * 3 px/s) + (3 px/s) results in a velocity of -0.3 px/s
            velocity.add(Vector.horizontalVector(-(bounciness + 1) * velocity.getX())); // The 1 cancels out the current velocity
            position[0] = width - icon.getWidth(); // push back from the wall
        } else if (position[0] < 0) { // if we're off the left edge
            // // Bounce our x-velocity back
            velocity.add(Vector.horizontalVector(-(bounciness + 1) * velocity.getX()));
            position[0] = 0; // push back from the wall
        }

        // same as above, only for the top and bottom edges

        if (position[1] + icon.getHeight() > height) {
            velocity.add(Vector.verticalVector(-(bounciness + 1) * velocity.getY()));
            position[1] = height - icon.getHeight();
        } else if (position[1] < 0) {
            velocity.add(Vector.verticalVector(-(bounciness + 1) * velocity.getY()));
            position[1] = 0;
        }
    }


    // Acceleration is an "all or nothing" thing -- no partial acceleration. Thus, up and down cancel out.
    //
    // Note: This is not TagPro-style acceleration. That is to say, accelerating down and right at the same time gives
    // the same magnitude of acceleration as accelerating right only. In other words, if a character is already
    // accelerating right, starting to accelerate up at the same time reduces the x-acceleration.
    private void accelerate() {
        Vector a = null;
        if (right == left && down == up) {
            // no "active" arrows; no acceleration
            return;
        } else if (right == left) { // down != up
            a = Vector.verticalVector(acceleration * (down ? 1 : -1));
        } else if (up == down) {
            a = Vector.horizontalVector(acceleration * (right ? 1 : -1));
        } else {
            if (right && down)
                a = new Vector(acceleration, Vector.EIGHTH); // 45-deg with specified magnitude
            if (left && down)
                a = new Vector(acceleration, 3 * Vector.EIGHTH);
            if (right && up)
                a = new Vector(acceleration, -Vector.EIGHTH);
            if (left && up)
                a = new Vector(acceleration, -3 * Vector.EIGHTH);
        }

        velocity.add(a); // update the velocity
    }

    private void frict() {
        // slowly reduces the velocity of the character proportionally to velocity.
        // idk whether this is accurate to real world physics
        // I don't think so, because this also has the (nice) side effect of capping a maximum velocity
        velocity.shortenInPlace(0.993);
    }

    boolean isColliding(Character c) {
        return isColliding(c.getX(), c.getY());
    }

    boolean isColliding(int x, int y) {
        // This method assumes that the coordinates represent the centers of each circular sprite.
        // This assumption is false, but the math works out because the error is cancelled out.

        int distanceX = getX() - x;
        int distanceY = getY() - y;
        double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

        return distance < 50; // diameter
    }

    // returns the center of mass of a collision between 2 circles as a length 2 array {x, y}
    int[] collisionCenter(Character c) {
        return collisionCenter(c.getX(), c.getY());
    }

    // returns the center of mass of a collision between 2 circles as a length 2 array {x, y}
    int[] collisionCenter(int x, int y) {
        int xSolution = (getX() + x) / 2;
        int ySolution = (getY() + y) / 2;
        return new int[]{xSolution + 25, ySolution + 25}; // offset for size of the images
    }

    void setUp(boolean up) {
        this.up = up;
    }

    void setDown(boolean down) {
        this.down = down;
    }

    void setLeft(boolean left) {
        this.left = left;
    }

    void setRight(boolean right) {
        this.right = right;
    }


}


