/*
 * Facade class
 * There is no fix definition of what Facade means
 * The general idea of this pattern is to simplify the call to objects
 * using an API, like this class, that hides the complexity of our game
 * implementation by just simply instantiating one object (Game) and
 * calling one method (update()).
 */
public class Game {
    private InputSystem input = new InputSystem();
    private GameObjects objects = new GameObjects();
    private GameConsole screen = new GameConsole();

    public void update() {
        //Input
        input.getInput();

        // Update game objects (player, enemies, etc)
        objects.update(input);

        // Draw
        screen.draw(objects);
    }
}
