package com.stpl.app.security.udc.ui.view;

import com.stpl.app.security.common.SessionDTO;
import com.stpl.app.security.udc.dto.HelperForm;
import com.stpl.app.security.udc.ui.form.UdcHelperForm;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.UserError;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class UdcView extends VerticalLayout implements View {

    public static final String NAME = "";
    private BeanItemContainer<HelperForm> searchResultbeans = new BeanItemContainer<HelperForm>(
            HelperForm.class);

    private Table table = new Table();
    private final SessionDTO sessionDTO;

    public UdcView(final SessionDTO sessionDTO) {
        setSpacing(true);
        this.sessionDTO = sessionDTO;
        addComponent(new UdcHelperForm(sessionDTO,searchResultbeans, table));
        setComponentError(new UserError(""));
    }

    public void enter(ViewChangeEvent event) {
        // TODO Auto-generated method stub

    }

	public SessionDTO getSessionDTO() {
		return sessionDTO;
	}

}
