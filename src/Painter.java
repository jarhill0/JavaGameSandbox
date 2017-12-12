public class Painter extends Thread {

    private Game game;
    private static long frameDelay = 1000 / 60;  // 60 FPS, yo!

    public Painter(Game game) {
        this.game = game;
    }

    public void run() {
        while (true) {
            game.repaint();

            try {
                Thread.sleep(frameDelay);
            } catch (InterruptedException e) {
            }
        }
    }
}
