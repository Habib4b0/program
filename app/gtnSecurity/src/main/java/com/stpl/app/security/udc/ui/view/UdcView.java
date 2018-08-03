package com.stpl.app.security.udc.ui.view;

import com.stpl.app.security.common.SessionDTO;
import com.stpl.app.security.udc.dto.HelperForm;
import com.stpl.app.security.udc.ui.form.UdcHelperForm;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.UserError;
import com.vaadin.v7.ui.Table;
import com.vaadin.v7.ui.VerticalLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UdcView extends VerticalLayout implements View {

    public static final String NAME = "";
    private final SessionDTO sessionDTO;
     private static final Logger LOGGER = LoggerFactory.getLogger(UdcView.class
			.getName());

    public UdcView(final SessionDTO sessionDTO) {
        setSpacing(true);
        this.sessionDTO = sessionDTO;
        addComponent(new UdcHelperForm(sessionDTO,new BeanItemContainer<>(HelperForm.class), new Table()));
        setComponentError(new UserError(""));
    }

    @Override
    public void enter(ViewChangeEvent event) {
                    LOGGER.debug("Inside overriden method of enter: Class UdcView");

    }

	public SessionDTO getSessionDTO() {
		return sessionDTO;
	}

}
