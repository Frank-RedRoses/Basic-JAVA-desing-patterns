import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * This is not the Adapter Pattern!!!!!!!!!<br>
 * This example class uses the {@code WindowAdapter} class that resembles
 * the Adapter Pattern but is not implemented as one.
 * <p>Instead, {@code WindowAdapter} implement default (dummy) versions for
 * all the methods in the {@code WindowsListener} interface. This allows
 * developers to override only the required methods according to the
 * application requirements, reducing boilerplate code.
 * </p>
 * <p>
 * The {@code WindowAdapter} class is a convenience class that provides default,
 * empty implementations for all methods in the {@code WindowsListener} interface.
 * It is not an example of the Adapter Pattern but rather a utility to simplify
 * event handling in Java
 * </p>
 */
public class SwingApp {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            /**
             * In this example, only the windowClosing method is overridden,
             * while the other methods in WindowListener are ignored.
             * This demonstrates the flexibility and simplicity provided
             * by the {@code WindowAdapter} class.
             * @param e the event to be processed
             */
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("Window opened");
            }
        });
    }
}
