@SuppressWarnings("InfiniteLoopStatement")
class ArrowRepeater extends Thread {
    private double change;
    private Character character;

    public boolean leftEnabled = false;
    public boolean rightEnabled = false;
    public boolean downEnabled = false;
    public boolean upEnabled = false;


    public ArrowRepeater(Character character, double change) {
        this.change = change;
        this.character = character;

    }

    public void run() {
        while (true) {
            if (leftEnabled != rightEnabled) {
                // prevent jittery motion if they're both enabled.
                if (leftEnabled)
                    character.accelerate(change, Directions.LEFT);
                else
                    character.accelerate(change, Directions.RIGHT);
            }

            if (upEnabled != downEnabled) {
                // prevent jittery motion if they're both enabled.
                if (downEnabled)
                    character.accelerate(change, Directions.DOWN);
                else
                    character.accelerate(change, Directions.UP);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException ignored) {
            }
        }
    }
}


