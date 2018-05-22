import java.util.List;

@SuppressWarnings("InfiniteLoopStatement")
class GameLoop extends Thread {

    private static final long nanoPerSecond = 1000000000L;  // conversion factor between nanoseconds and seconds
    private static final long nanosecondsPerFrame = nanoPerSecond / 60; // targeting 60 FPS
    private static final long nanoPerMilli = 1000000L;  // conversion factor between nanoseconds and milliseconds

    private Game game;
    private List<Character> characters;
    private boolean paused = true;
    private boolean restart = false;

    GameLoop(Game game, List<Character> characters) {
        this.game = game;
        this.characters = characters;
    }

    public void togglePause() {
        paused = !paused;
    }

    public void tryRestart() {
        if (paused)
            restart = true;
    }

    public void run() {
        while (true) {
            long stepTime = System.nanoTime();  // record time before game step
            game.repaint();

            if (!paused) {
                for (Character character : characters)
                    character.step();

                if (game.countdown.getTimeLeft() == 0) {
                    game.resetPlayerPosition();
                    game.tracker.switchTurn(false);
                    game.countdown.setTimeLeft(31);
                }
            }

            if (restart) {
                restart = false;
                paused = false;
                game.resetPlayerPosition();
                game.resetAllScores();
                game.countdown.setTimeLeft(31);
            }


            // sleep until next step
            try {
                // sleep the difference until a proper frame time
                Thread.sleep((stepTime + nanosecondsPerFrame - System.nanoTime()) / nanoPerMilli);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
