package com.caveofprogramming.designpattern.dao_factory.model;

import java.util.List;

public class LogDAO {
    public void addEntry(String message) {
        // Not implemented
        // Get current time and log message to database
    }

    public List<Log> getEntries(int number) {
        // Not implemented. Get lastest "number" log messages.
        return null;
    }

    // May not need update() or delete() in this example.
}
