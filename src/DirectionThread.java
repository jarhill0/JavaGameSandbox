public class DirectionThread extends Thread {
    private int change;
    public boolean enabled = false;
    private Character character;
    private int coordIndex;

    public DirectionThread(Character character, int coordIndex, int change) {
        this.change = change;
        this.character = character;
        this.coordIndex = coordIndex;

    }

    public void run() {
        while (true) {
            if (enabled) {
                character.position[coordIndex] += change;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        }
    }
}


