package de.steinwedel.messagebox.i18n;

import java.util.ListResourceBundle;

import de.steinwedel.messagebox.ButtonId;

/**
 * I18n for the button captions. Here is the French translation implemented.
 * 
 * @author Dieter Steinwedel
 */
public class MbResourceFr extends ListResourceBundle {

	/**
	 * See {@link java.util.ListResourceBundle#getContents()}
	 */
	@Override
	protected Object[][] getContents() {
		return new Object[][] {
				{ButtonId.OK.toString(), "Ok"},
				{ButtonId.ABORT.toString(), "Interrompre"},
				{ButtonId.CANCEL.toString(), "Annulér"},
				{ButtonId.YES.toString(), "Oui"},
				{ButtonId.NO.toString(), "Non"},
				{ButtonId.CLOSE.toString(), "Fermer"},
				{ButtonId.SAVE.toString(), "Enregistrer"},
				{ButtonId.RETRY.toString(), "Réessayer"},
				{ButtonId.IGNORE.toString(), "Ignorer"},
				{ButtonId.HELP.toString(), "Aide"},
		        {ButtonId.CUSTOM_1.toString(), "Custom 1"},
		        {ButtonId.CUSTOM_2.toString(), "Custom 2"},
		        {ButtonId.CUSTOM_3.toString(), "Custom 3"},
		        {ButtonId.CUSTOM_4.toString(), "Custom 4"},
		        {ButtonId.CUSTOM_5.toString(), "Custom 5"},
		};
	}

}
