
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.view;

import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.ui.form.ConsolidatedFinancialForecastForm;
import com.stpl.app.cff.util.CommonUtils;
import com.stpl.app.cff.util.Constants;
import com.stpl.app.cff.util.ConstantsUtil;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.v7.ui.VerticalLayout;
import org.apache.commons.lang.StringUtils;

/**
 * Search page view class
 *
 * @author Manasa
 */
public class ConsolidatedFinancialForecastView extends VerticalLayout implements View {

    /**
     * The Constant NAME.
     */
    public static final String NAME = StringUtils.EMPTY;
    private ConsolidatedFinancialForecastForm mainForm;
    /**
     * The SessionDTO
     */
    private final SessionDTO sessionDTO = new SessionDTO();

    /**
     * Constructor
     *
     */
    public ConsolidatedFinancialForecastView() {
        super();
        addStyleName(Constants.BOOTSTRAP_UI);
        addStyleName(Constants.BOOTSTRAP);
        addStyleName(Constants.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        sessionDTO.setUserId((String) VaadinSession.getCurrent().getAttribute(CommonUtils.USERID_SESSION));
        sessionDTO.setSessionId((String) VaadinSession.getCurrent().getAttribute(ConstantsUtil.SESSION_ID));
        mainForm = new ConsolidatedFinancialForecastForm(sessionDTO);
        addComponent(mainForm);
    }

    /**
     * This method called whenever the search page gets loaded
     *
     * @param event - View Change event
     */
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        mainForm = new ConsolidatedFinancialForecastForm(sessionDTO);
    }
    
}