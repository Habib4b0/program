/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.globalchange.ui.view;

import com.stpl.app.gcm.globalchange.dto.GlobalChangeDTO;
import com.stpl.app.gcm.globalchange.ui.form.GlobalChangeIndex;
import com.stpl.app.gcm.util.Constants;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author alok.v
 */
public class GlobalChangeUIMainView extends VerticalLayout implements View {

    /**
     * View name for navigation
     */
    public static final String NAME = StringUtils.EMPTY;
    /**
     * DTO object for DataSelection
     */
    private GlobalChangeDTO globalChangeDTO = new GlobalChangeDTO();

    /**
     * Default Enter Method
     *
     * @param event
     */
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        setGlobalChangeDTO(new GlobalChangeDTO());
    }

    /**
     * Default constructor
     */
    public GlobalChangeUIMainView() {
        addStyleName("bootstrap-ui");
        addStyleName(Constants.BOOTSTRAP);
        addStyleName(Constants.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        addComponent(new GlobalChangeIndex());

    }

	public GlobalChangeDTO getGlobalChangeDTO() {
		return globalChangeDTO;
	}

	public void setGlobalChangeDTO(GlobalChangeDTO globalChangeDTO) {
		this.globalChangeDTO = globalChangeDTO;
	}
}
