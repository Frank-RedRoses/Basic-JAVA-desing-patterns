/*
 * This is the typical structure of a game application.
 */
public class App {

    public static void main(String[] args) {
        Game game = new Game();

        // Game loop
        while (true) {
            game.update();
        }
    }
}
