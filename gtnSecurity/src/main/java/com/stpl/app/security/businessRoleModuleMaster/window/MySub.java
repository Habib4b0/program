package com.stpl.app.security.businessRoleModuleMaster.window;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class MySub extends Window  {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MySub() {
        super("Subs on Sale"); // Set window caption
        center();

        // Some basic content for the window
        VerticalLayout content = new VerticalLayout();
        content.addComponent(new Label("Just say it's OK!"));
        content.setMargin(true);
        setContent(content);
        setClosable(true);
    }
}
