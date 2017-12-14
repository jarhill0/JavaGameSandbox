public class Friction extends Thread {
    private Character character;

    Friction(Character character) {
        this.character = character;
    }

    public void run() {
        while (true) {
            character.frict();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
