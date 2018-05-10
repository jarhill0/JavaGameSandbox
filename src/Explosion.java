import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

class Explosion {
    BufferedImage image;
    private boolean visible = false;
    private int x;
    private int y;

    Explosion(String imageName) {
        loadImage(imageName);
    }

    Explosion() {
        this("explosion.png");
    }


    // set the center of the image
    void setVisible(int x, int y) {
        visible = true;
        this.x = x;
        this.y = y;
    }

    void setInvisible() {
        visible = false;
    }

    boolean isVisible() {
        return visible;
    }


    int getX() {
        return x - (image.getWidth() / 2);
    }

    int getY() {
        return y - (image.getHeight() / 2);
    }

    private void loadImage(String imageName) {
        try {
            image = ImageIO.read(getClass().getResource("/" + imageName));
        } catch (IOException e) {
            System.out.println("Couldn't get explosion image");
            System.exit(1);
        }
    }

}
