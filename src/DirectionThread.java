public class DirectionThread extends Thread {
    private int change;
    private Character character;

    public boolean leftEnabled = false;
    public boolean rightEnabled = false;
    public boolean downEnabled = false;
    public boolean upEnabled = false;


    public DirectionThread(Character character, int change) {
        this.change = change;
        this.character = character;

    }

    public void run() {
        while (true) {
            if (leftEnabled != rightEnabled) {
                // prevent jittery motion if they're both enabled.
                if (leftEnabled)
                    character.move(change, Directions.LEFT);
                else
                    character.move(change, Directions.RIGHT);
            }

            if (upEnabled != downEnabled) {
                // prevent jittery motion if they're both enabled.
                if (downEnabled)
                    character.move(change, Directions.DOWN);
                else
                    character.move(change, Directions.UP);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        }
    }
}


