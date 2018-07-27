/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ui;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.ui.form.DataSelectionForm;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.HelperListUtil;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import static com.stpl.ifs.util.constants.GlobalConstants.*;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.ui.VerticalLayout;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final Logger LOGGER = LoggerFactory.getLogger(ForecastMainView.class);

    /**
     * View name for navigation.
     */
    public static final String NAME = StringUtils.EMPTY;

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
        DataSelectionDTO dataSelectionDTO = new DataSelectionDTO();
        CustomFieldGroup dataSelectionBinder = new CustomFieldGroup(new BeanItem<>(dataSelectionDTO));
        this.removeAllComponents(); // Added for GAL-9838
        String screenName = (String) VaadinSession.getCurrent().getAttribute(Constant.PORTLET_NAME);
        LOGGER.debug(" Screen Name -->= {} " , screenName);
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
