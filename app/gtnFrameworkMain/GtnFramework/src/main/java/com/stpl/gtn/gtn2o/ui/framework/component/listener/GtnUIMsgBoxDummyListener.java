package com.stpl.gtn.gtn2o.ui.framework.component.listener;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.MessageBoxListener;

public class GtnUIMsgBoxDummyListener implements MessageBoxListener {

	private static GtnUIMsgBoxDummyListener gtnUIMsgBoxDummyListener = new GtnUIMsgBoxDummyListener();

	public static GtnUIMsgBoxDummyListener getListener() {
		return gtnUIMsgBoxDummyListener;
	}

	@Override
	public void buttonClicked(ButtonId buttonId) {
		return;

	}

}
