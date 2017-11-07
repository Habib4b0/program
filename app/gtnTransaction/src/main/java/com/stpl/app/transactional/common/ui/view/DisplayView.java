/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.transactional.common.ui.view;

import com.stpl.app.transactional.common.logic.CommonLogic;
import com.stpl.app.transactional.common.ui.form.TabSheetForm;
import com.stpl.app.transactional.common.ui.form.ViewForm;
import com.stpl.app.util.ConstantUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.VerticalLayout;
import org.jboss.logging.Logger;

/**
 *
 * @author sooriya.lakshmanan
 */
public class DisplayView extends VerticalLayout implements View {

    /**
     * The Constant NAME.
     */
    public static final String NAME = ConstantUtil.VIEW;

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(DisplayView.class);
    CommonLogic logic = new CommonLogic();

    /**
     * The Constructor.
     */
    public DisplayView() throws PortalException, SystemException {
        super();
        try {
            LOGGER.debug("Entering ForecastEditView");
            setStyleName("bootstrap-company");
            setSpacing(true);
        } catch (Exception e) {
            LOGGER.error(e);
           
        }
        LOGGER.debug("Ends ForecastEditView");
    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {
        try {
            this.removeAllComponents();
            final int forecastingMasterId = (Integer) VaadinSession.getCurrent().getAttribute("ForecastMasterId");
            LOGGER.debug("forecastingMasterId  " + forecastingMasterId);
            final int tabCount = (Integer) VaadinSession.getCurrent().getAttribute(ConstantUtil.TABCOUNT);

            LOGGER.debug("Tab Size  " + tabCount);
            if (tabCount == NumericConstants.TWO) {
                Object[] ob = logic.getFiledNames(ConstantUtil.INVENTORY_WITHDRAWAL_SUMMARY.equals((String) VaadinSession.getCurrent().getAttribute(ConstantUtil.SCREEN_NAME)) ? ConstantUtil.INVENTORY : (String) VaadinSession.getCurrent().getAttribute(ConstantUtil.SCREEN_NAME),ConstantUtil.VIEW);
                addComponent(new ViewForm(ob, logic.getValuesById(Integer.valueOf(forecastingMasterId), ob)));
            } else {
                addComponent(new TabSheetForm(forecastingMasterId));
            }
        } catch (Exception ex) {
            LOGGER.error(ex);

        }
    }
}
