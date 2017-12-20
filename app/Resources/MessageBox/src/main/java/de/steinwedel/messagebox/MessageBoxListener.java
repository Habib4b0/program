package de.steinwedel.messagebox;


/**
 * This event listener is triggered when a button of the messagebox is pressed.
 * 
 * @author Dieter Steinwedel
 */
public interface MessageBoxListener {
	
	/**
	 * The method is triggered when a button of the message box is pressed. The
	 * parameter describes, which configured button was pressed.
	 * 
	 * @param buttonId 	The buttonId of the pressed button.
	 */
	public void buttonClicked(ButtonId buttonId);

}
