package de.steinwedel.messagebox;

/**
 * An enumeration for identifying the kind of the button. The value affects the displayed button icon and caption.
 * 
 * @author Dieter Steinwedel
 */
public enum ButtonId {

	/**
	 * Self-explaining.
	 */
	OK,
	
	/**
	 * Self-explaining.
	 */
	ABORT,
	
	/**
	 * Self-explaining.
	 */
	CANCEL,
	
	/**
	 * Self-explaining.
	 */
	YES,
	
	/**
	 * Self-explaining.
	 */
	NO,
	
	/**
	 * Self-explaining.
	 */
	CLOSE,
	
	/**
	 * Self-explaining.
	 */
	SAVE,
	
	/**
	 * Self-explaining.
	 */
	RETRY,
	
	/**
	 * Self-explaining.
	 */
	IGNORE,
	
	/**
	 * Self-explaining. Keep in mind, that the dialog will not be closed, if the corresponding button is pressed!
	 */
	HELP,
	
	/**
	 * Use this, if you want to use a custom named and styled button.
	 */
	CUSTOM_1,
	
	/**
	 * Use this, if you want to use a custom named and styled button.
	 */
	CUSTOM_2,
	
	/**
	 * Use this, if you want to use a custom named and styled button.
	 */
	CUSTOM_3,
	
	/**
	 * Use this, if you want to use a custom named and styled button.
	 */
	CUSTOM_4,
	
	/**
	 * Use this, if you want to use a custom named and styled button.
	 */
	CUSTOM_5,
	
	/**
	 * Use this, if you want to increase the padding between two buttons.
	 */
	SPACER;
	
	/**
	 * Returns the key for the I18n.
	 */
	@Override
	public String toString() {
		return "mb_" + name();
	}
	
}
