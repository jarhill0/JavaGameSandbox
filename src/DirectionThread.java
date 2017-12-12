public class DirectionThread extends Thread {
    private int change;
    public boolean enabled = false;
    private Character character;
    private Directions direction;

    public DirectionThread(Character character, int change, Directions direction) {
        this.change = change;
        this.character = character;
        this.direction = direction;

    }

    public void run() {
        while (true) {
            if (enabled) {
                character.move(change, direction);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        }
    }
}


