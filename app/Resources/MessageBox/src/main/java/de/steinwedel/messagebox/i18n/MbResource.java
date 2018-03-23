package de.steinwedel.messagebox.i18n;

import java.util.ListResourceBundle;

import de.steinwedel.messagebox.ButtonId;

/**
 * I18n for the button captions. The Default values are English.
 * 
 * @author Dieter Steinwedel
 */
public class MbResource extends ListResourceBundle {

	/**
	 * See {@link java.util.ListResourceBundle#getContents()}
	 */
	@Override
	protected Object[][] getContents() {
		return new Object[][] {
				{ButtonId.OK.toString(), "Ok"},
		        {ButtonId.ABORT.toString(), "Abort"},
		        {ButtonId.CANCEL.toString(), "Cancel"},
		        {ButtonId.YES.toString(), "Yes"},
		        {ButtonId.NO.toString(), "No"},
		        {ButtonId.CLOSE.toString(), "Close"},
		        {ButtonId.SAVE.toString(), "Save"},
		        {ButtonId.RETRY.toString(), "Retry"},
		        {ButtonId.IGNORE.toString(), "Ignore"},
		        {ButtonId.HELP.toString(), "Help"},
		        {ButtonId.CUSTOM_1.toString(), "Custom 1"},
		        {ButtonId.CUSTOM_2.toString(), "Custom 2"},
		        {ButtonId.CUSTOM_3.toString(), "Custom 3"},
		        {ButtonId.CUSTOM_4.toString(), "Custom 4"},
		        {ButtonId.CUSTOM_5.toString(), "Custom 5"},
		};
	}

}
