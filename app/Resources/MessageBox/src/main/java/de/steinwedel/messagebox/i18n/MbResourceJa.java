package de.steinwedel.messagebox.i18n;

import java.util.ListResourceBundle;

import de.steinwedel.messagebox.ButtonId;

/**
 * I18n for the button captions. Here is the Japanese translation implemented.
 * 
 * @author Dieter Steinwedel
 */
public class MbResourceJa extends ListResourceBundle {

	/**
	 * See {@link java.util.ListResourceBundle#getContents()}
	 */
	@Override
	protected Object[][] getContents() {
		return new Object[][] {
				{ButtonId.OK.toString(), "OK"},
				{ButtonId.ABORT.toString(), "中止"},
				{ButtonId.CANCEL.toString(), "ャンセル"},
				{ButtonId.YES.toString(), "はい"},
				{ButtonId.NO.toString(), "いいえ"},
				{ButtonId.CLOSE.toString(), "閉じる"},
				{ButtonId.SAVE.toString(), "保存"},
				{ButtonId.RETRY.toString(), "再試行"},
				{ButtonId.IGNORE.toString(), "無視"},
				{ButtonId.HELP.toString(), "ヘルプ"},
		        {ButtonId.CUSTOM_1.toString(), "Custom 1"},
		        {ButtonId.CUSTOM_2.toString(), "Custom 2"},
		        {ButtonId.CUSTOM_3.toString(), "Custom 3"},
		        {ButtonId.CUSTOM_4.toString(), "Custom 4"},
		        {ButtonId.CUSTOM_5.toString(), "Custom 5"},
		};
	}

}
