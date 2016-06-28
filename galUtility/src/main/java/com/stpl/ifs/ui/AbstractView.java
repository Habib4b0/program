package com.stpl.ifs.ui;

import com.vaadin.navigator.View;
import com.vaadin.server.UserError;
import com.vaadin.ui.VerticalLayout;
/**
 * Basic design for View
 * @author shrihariharan
 */
public abstract class AbstractView extends VerticalLayout implements View {
        private static final long serialVersionUID = 1L;
        /**
         * View Identifier
         */
	public static String NAME = "";
	
        /**
         * Creating the view
         */
        public AbstractView()
	{		
		setSpacing(true);
		setComponentError(new UserError(""));
	}
	

}
