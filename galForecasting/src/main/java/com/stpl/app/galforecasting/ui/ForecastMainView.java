/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.ui;

import com.stpl.app.galforecasting.ppaprojection.logic.PPAServiceSupport;
import com.stpl.app.galforecasting.ui.form.DataSelectionForm;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.app.galforecasting.utils.HelperListUtil;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.CustomFieldGroup;
import static com.stpl.ifs.util.constants.GlobalConstants.*;
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
    private CustomFieldGroup dataSelectionBinder = new CustomFieldGroup(new BeanItem<DataSelectionDTO>(dataSelectionDTO));

    /**
     * Default constructor.
     */
    public ForecastMainView() throws Exception {
        String screenName = (String)VaadinSession.getCurrent().getAttribute(Constant.PORTLET_NAME);
        LOGGER.info(" Screen Name --> "+screenName);
        if (screenName.equals(getCommercialConstant())) {
            HelperListUtil.getInstance().loadValuesWithListName(getCommercialConstant());
//            PPAServiceSupport.getInstance().getPriceTypeResults();
            screenName = CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED;
        } else if (screenName.equals(getGovernmentConstant())) {
            screenName = CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED;
        } else if (screenName.equals("Returns")) {
             screenName = CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS;       
        } 
        addComponent(new DataSelectionForm(dataSelectionBinder, dataSelectionDTO,screenName));
        
    }

    /**
     * This method is always called before the view is shown on screen.
     *
     * @param event the event
     */
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        dataSelectionDTO = new DataSelectionDTO();
        dataSelectionBinder = new CustomFieldGroup(new BeanItem<DataSelectionDTO>(dataSelectionDTO));
    }

}
