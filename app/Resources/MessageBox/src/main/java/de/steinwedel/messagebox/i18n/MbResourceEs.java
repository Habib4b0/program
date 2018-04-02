package de.steinwedel.messagebox.i18n;

import java.util.ListResourceBundle;

import de.steinwedel.messagebox.ButtonId;

/**
 * I18n for the button captions. Here is the Spanish translation implemented.
 * 
 * @author Dieter Steinwedel
 */
public class MbResourceEs extends ListResourceBundle {

	/**
	 * See {@link java.util.ListResourceBundle#getContents()}
	 */
	@Override
	protected Object[][] getContents() {
		return new Object[][] {
				{ButtonId.OK.toString(), "Aceptar"},
				{ButtonId.ABORT.toString(), "Interrumpir"},
				{ButtonId.CANCEL.toString(), "Cancelar"},
				{ButtonId.YES.toString(), "Sí"},
				{ButtonId.NO.toString(), "No"},
				{ButtonId.CLOSE.toString(), "Cerrar"},
				{ButtonId.SAVE.toString(), "Guardar"},
				{ButtonId.RETRY.toString(), "Reintentar"},
				{ButtonId.IGNORE.toString(), "Ignorar"},
				{ButtonId.HELP.toString(), "Ayuda"},
		        {ButtonId.CUSTOM_1.toString(), "Custom 1"},
		        {ButtonId.CUSTOM_2.toString(), "Custom 2"},
		        {ButtonId.CUSTOM_3.toString(), "Custom 3"},
		        {ButtonId.CUSTOM_4.toString(), "Custom 4"},
		        {ButtonId.CUSTOM_5.toString(), "Custom 5"},
		};
	}

}
