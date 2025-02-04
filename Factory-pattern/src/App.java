/*
 * Factory pattern.
 * When to use it:
 * - Want to create multiple of objects that implement the same
 * interface or share a common parent.
 * - When object creation is complex. (e.g. requires many constructor parameters).
 * - To simplify the selection process when choosing between different objects types.
 * - When you want to decouple object creation from the client, improving maintainability.
 * - To enable loose coupling, making easier to swap implementation without modifying the
 * client code.
 * - When object instantiation logic changes frequently, centralizing it in a factory
 * prevents code duplication.
 * To support dependency injection, as factories can handle object creation and configuration
 * in a single place.
 */
public class App {

    public static void main(String[] args) {
        Animal animal = AnimalFactory.createAnimal(AnimalFactory.CAT);

        animal.speak(); // outputs "Meow"
        animal.eat();   // outputs "Chomp, chomp, chomp"


    }
}
