package com.stpl.gtn.gtn2o.ui.framework.component.vaadin8;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.stpl.addons.windowheaderextension.WindowHeaderExtension;
import com.vaadin.server.FontIcon;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Window;

public class GtnUIFrameworkWindowComponent extends Window {
	private static final long serialVersionUID = 7165043795917878232L;

	public GtnUIFrameworkWindowComponent(String caption) {
		super.setCaption(caption);
		addMinimizeExtension();
	}

	private void miniMizeWindow() {
		Optional<Window> minimizeTray = getWindowTray();
		WindowTray tray = (WindowTray) minimizeTray.orElseGet(this::createNewWindowTray);
		tray.addWindow(this);
		this.addStyleName("dumil");

	}

	private WindowTray createNewWindowTray() {
		WindowTray tray = new WindowTray();
		getUI().addWindow(tray);
		return tray;
	}

	private Optional<Window> getWindowTray() {
		return getUI().getWindows().stream().filter(window -> window instanceof WindowTray).findFirst();
	}

	private void addMinimizeExtension() {
		WindowHeaderExtension.extend(this, new MinimizeIcon(), this::miniMizeWindow);
	}

	class MinimizeIcon implements FontIcon {

		private static final long serialVersionUID = 7979027940347288873L;

		@Override
		public String getMIMEType() {
			return "";
		}

		@Override
		public String getHtml() {
			return "-";
		}

		@Override
		public String getFontFamily() {
			return "";
		}

		@Override
		public int getCodepoint() {

			return 0;
		}
	}

}

class WindowTray extends Window {

	private static final long serialVersionUID = 1L;
	private MenuBar minimizeTray = new MenuBar();
	private Map<String, Window> miniMizedWindowsMap = new HashMap<>();
	private MenuBar.MenuItem item = minimizeTray.addItem("window", null);

	public WindowTray() {
		super();
		init();
	}

	public void addWindow(Window subWindow) {

		miniMizedWindowsMap.put(subWindow.getCaption(), subWindow);
		item.addItem(subWindow.getCaption(), selectedItem -> {
			Window foundWindow = miniMizedWindowsMap.get(selectedItem.getText());
			foundWindow.removeStyleName("dumil");
			foundWindow.bringToFront();
			item.removeChild(selectedItem);
		});
	}

	private void init() {
		this.setClosable(false);
		this.setResizable(false);
		setContent(minimizeTray);
		this.setPosition(0, 500);
	}

	@Override
	public int hashCode() {
		final int Prime = 31;
		int result = super.hashCode();
		result = Prime * result + ((miniMizedWindowsMap == null) ? 0 : miniMizedWindowsMap.hashCode());
		result = Prime * result + ((minimizeTray == null) ? 0 : minimizeTray.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		WindowTray other = (WindowTray) obj;
		if (miniMizedWindowsMap == null) {
			if (other.miniMizedWindowsMap != null)
				return false;
		} else if (!miniMizedWindowsMap.equals(other.miniMizedWindowsMap))
			return false;
		if (minimizeTray == null) {
			if (other.minimizeTray != null)
				return false;
		} else if (!minimizeTray.equals(other.minimizeTray))
			return false;
		return true;
	}

}
