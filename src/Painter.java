@SuppressWarnings("InfiniteLoopStatement")
class Painter extends Thread {

    private Game game;

    public Painter(Game game) {
        this.game = game;
    }

    public void run() {
        while (true) {
            if (game.updated) {
                game.repaint();
                game.updated = false;
            }

            try {
                long frameDelay = 1000 / 60; // 60 FPS :D
                Thread.sleep(frameDelay);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
