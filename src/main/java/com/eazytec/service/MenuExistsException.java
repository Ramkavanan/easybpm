package com.eazytec.service;

/**
 * An exception that is thrown by classes wanting to trap unique 
 * constraint violations.  This is used to wrap Spring's 
 * DataIntegrityViolationException so it's checked in the web layer.
 *
 * @author mathi
 */
public class MenuExistsException extends Exception {
    private static final long serialVersionUID = 4050482305178810162L;

    /**
     * Constructor for RoleExistsException.
     *
     * @param message exception message
     */
    public MenuExistsException(final String message) {
        super(message);
    }
}