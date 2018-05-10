import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Explosion {
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

    public void setVisible(int x, int y) {
        visible = true;
        this.x = x;
        this.y = y;
    }

    public void setInvisible() {
        visible = false;
    }

    public boolean isVisible() {
        return visible;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
