import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Character {
    BufferedImage icon;
    private double[] position = new double[2];
    private Game game;
    private Vector velocity = new Vector();

    Character(Game game) {
        position[0] = 0;
        position[1] = 0;
        this.game = game;
        getImage();
    }

    public Character(Game game, int x, int y) {
        position[0] = x;
        position[1] = y;
        this.game = game;
        getImage();
    }

    private void getImage() {
        try {
            File file = new File(new File(System.getProperty("user.dir"), "src"), "icon.png");
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


    void accelerate(double by, Directions d) {
        Vector a = null;
        switch (d) {
            case RIGHT:
                a = Vector.horizontalVector(by);
                break;
            case LEFT:
                a = Vector.horizontalVector(-by);
                break;
            case DOWN:
                a = Vector.verticalVector(by);
                break;
            case UP:
                a = Vector.verticalVector(-by);
                break;
        }

        velocity = velocity.add(a);
    }

    void frict() {
        velocity.shortenInPlace(0.995);
    }


}
