package com.familyservicestoronto.shortcut;

/**
 * Exception thrown when app with given package name is not installed on user device.
 */

public class AppNotFoundException extends Exception {

    public AppNotFoundException(String packageName) {
        super(String.format("App with package name \"%s\" is not installed.", packageName));
    }
}
