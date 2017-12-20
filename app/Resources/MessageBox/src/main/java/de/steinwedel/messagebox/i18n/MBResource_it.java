package de.steinwedel.messagebox.i18n;

import java.util.ListResourceBundle;

import de.steinwedel.messagebox.ButtonId;

/**
 * I18n for the button captions. Here is the Italian translation implemented.
 * 
 * @author Dieter Steinwedel
 */
public class MBResource_it extends ListResourceBundle {

	/**
	 * See {@link java.util.ListResourceBundle#getContents()}
	 */
	@Override
	protected Object[][] getContents() {
		return new Object[][] {
				{ButtonId.OK.toString(), "Ok"},
				{ButtonId.ABORT.toString(), "Termina"},
				{ButtonId.CANCEL.toString(), "Annulla"},
				{ButtonId.YES.toString(), "Sì"},
				{ButtonId.NO.toString(), "No"},
				{ButtonId.CLOSE.toString(), "Chiudi"},
				{ButtonId.SAVE.toString(), "Salva"},
				{ButtonId.RETRY.toString(), "Riprova"},
				{ButtonId.IGNORE.toString(), "Ignora"},
				{ButtonId.HELP.toString(), "Aiuto"},
		        {ButtonId.CUSTOM_1.toString(), "Custom 1"},
		        {ButtonId.CUSTOM_2.toString(), "Custom 2"},
		        {ButtonId.CUSTOM_3.toString(), "Custom 3"},
		        {ButtonId.CUSTOM_4.toString(), "Custom 4"},
		        {ButtonId.CUSTOM_5.toString(), "Custom 5"},
		};
	}

}
