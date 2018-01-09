package com.stpl.app.ui;

/**
 * Custom Exception Class.
 *
 * @author shrihariharan
 */
public class StplR2Exception extends Exception {

	/**
	 * Default serial version.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new exception with <code>null</code> as its detail message.
	 * The cause is not initialized, and may subsequently be initialized by a
	 * call to {@link #initCause}.
	 */
	public StplR2Exception() {
		super();
	}

	/**
	 * Constructs a new exception with the specified detail message. The cause
	 * is not initialized, and may subsequently be initialized by a call to
	 * {@link #initCause}.
	 *
	 * @param message the detail message. The detail message is saved for later
	 * retrieval by the {@link #getMessage()} method.
	 */
	public StplR2Exception(final String message) {

		super(message);

	}

	/**
	 * Constructor for the class with the specified message.
	 *
	 * @param message the detail message. The detail message is saved for later
	 * retrieval by the {@link #getMessage()} method.
	 * @param cause the cause (which is saved for later retrieval by the
	 *         {@link #getCause()} method).  (A <tt>null</tt> value is
	 *         permitted, and indicates that the cause is nonexistent or
	 *         unknown.)
	 */
	public StplR2Exception(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor for the class with the specified message.
	 *
	 * @param cause the cause (which is saved for later retrieval by the
	 *         {@link #getCause()} method).  (A <tt>null</tt> value is
	 *         permitted, and indicates that the cause is nonexistent or
	 *         unknown.)
	 */
	public StplR2Exception(final Throwable cause) {
		super(cause);
	}
}
