package com.caveofprogramming.designpattern.demo1.view;

import com.caveofprogramming.designpattern.demo1.model.Model;

import javax.swing.JFrame;

/**
 * This class represent the view of our application. You would not necessarily call
 * this class {@code View} nor would the root package necessarily be called {@code view}.
 * It is often more appropriate to give this class a name such as {@code MainFrame}.
 * The important concept is that this is the part of the code responsible for interacting
 * with the user.
 */
public class View extends JFrame {

    private final Model model;

    /**
     * The {@code View} constructor receives a reference to the {@code Model}
     * and that allows the {@code Model} to listen to the {@code View}.
     * @param model a reference to the model.
     */
    public View(Model model) {
        super("MVC Demo");  // The parent constructor (JFrame) accepts a title for the app.
        this.model = model;
    }
}
