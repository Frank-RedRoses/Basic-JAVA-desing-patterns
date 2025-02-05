/*
 * This is the typical structure of a game application.
 */
public class App {

    public static void main(String[] args) {
        InputSystem input = new InputSystem();
        GameObjects objects = new GameObjects();
        GameConsole screen = new GameConsole();

        while(true) {
            //Input
            input.getInput();

            // Update game objects (player, enemies, etc)
            objects.update(input);

            // Draw
            screen.draw(objects);
        }
    }
}
