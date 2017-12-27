/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ui;

import com.stpl.app.gtnforecasting.ui.form.DataSelectionForm;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.HelperListUtil;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.CustomFieldGroup;
import static com.stpl.ifs.util.constants.GlobalConstants.*;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.VerticalLayout;
import org.apache.commons.lang.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * Adds the dataselection to the screen.
 *
 * @author soundarrajan
 */
public class ForecastMainView extends VerticalLayout implements View {

    /**
     * The Constant LOGGER.
     */
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(ForecastMainView.class);

    /**
     * View name for navigation.
     */
    public static final String NAME = StringUtils.EMPTY;

    /**
     * DTO object for DataSelection.
     */
    private DataSelectionDTO dataSelectionDTO = new DataSelectionDTO();

    /**
     * Binder for DataSelection.
     */
    private CustomFieldGroup dataSelectionBinder = new CustomFieldGroup(new BeanItem<>(dataSelectionDTO));

    /**
     * Default constructor.
     */
    public ForecastMainView() throws Exception {
        /**
         * CONSTRUCTOR.
         */
    }

    /**
     * This method is always called before the view is shown on screen.
     *
     * @param event the event
     */
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        dataSelectionDTO = new DataSelectionDTO();
        dataSelectionBinder = new CustomFieldGroup(new BeanItem<>(dataSelectionDTO));
        this.removeAllComponents(); // Added for GAL-9838
        String screenName = (String) VaadinSession.getCurrent().getAttribute(Constant.PORTLET_NAME);
        LOGGER.debug(" Screen Name --> " + screenName);
        if (screenName.equals(getCommercialConstant())) {
            HelperListUtil.getInstance().loadValuesWithListName(getCommercialConstant());
            screenName = CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED;
        } else if (screenName.equals(getGovernmentConstant())) {
            screenName = CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED;
        } else if (screenName.equals("Returns")) {
            screenName = CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS;
        } else if (screenName.equals("AccrualRateProjection")) {
            screenName = CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION;
        }
        try {
            addComponent(new DataSelectionForm(dataSelectionBinder, dataSelectionDTO, screenName));
        } catch (PortalException | SystemException exception) {
            LOGGER.error(exception.getMessage());
        }
    } // Ends here

}
