package com.stpl.app.security.businessrolemodulemaster.window;

import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.VerticalLayout;
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
