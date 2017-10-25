package org.r2.etl.common.controller;

/**
 * The BPIETLException class is a user defined class to catch exception.
 */
public class BPIETLException extends Exception {
	

    
    
    /**
	 * Default serial version.
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Constructs a new exception with <code>null</code> as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public BPIETLException() {
	super();
    }

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param   message   the detail message. The detail message is saved for 
     *          later retrieval by the {@link #getMessage()} method.
     */
    public BPIETLException(final String message) {
    	
    	super(message);
	
    }
   

    /**
     * Constructor for the class with the specified message
     * @param message
     * @param cause
     */
    public BPIETLException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor for the class with the specified message
     * @param cause
     */
    public BPIETLException(final Throwable cause) {
        super(cause);
    }
		 }

