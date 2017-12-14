public class Inertia extends Thread {
    private Character character;

    Inertia(Character character) {
        this.character = character;
    }

    public void run() {
        while (true) {
            character.step();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
