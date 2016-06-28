/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.globalchange.ui.view;

import com.stpl.app.gcm.globalchange.dto.GlobalChangeDTO;
import com.stpl.app.gcm.globalchange.ui.form.GlobalChangeIndex;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.util.Constants;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.GLOBAL_CHANGE_INDEX;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.vaadin.data.util.BeanItem;
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
    SessionDTO sessionDTO;
    /**
     * DTO object for DataSelection
     */
    GlobalChangeDTO globalChangeDTO = new GlobalChangeDTO();
    private CustomFieldGroup promoteTpToChBinder = new CustomFieldGroup(new BeanItem<GlobalChangeDTO>(globalChangeDTO));

    /**
     * Default Enter Method
     *
     * @param event
     */
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        globalChangeDTO = new GlobalChangeDTO();
        promoteTpToChBinder = new CustomFieldGroup(new BeanItem<GlobalChangeDTO>(globalChangeDTO));
    }

    /**
     * Default constructor
     */
    public GlobalChangeUIMainView() throws Exception {
        addStyleName("bootstrap-ui");
        addStyleName(Constants.bootstrap);
        addStyleName(Constants.bootstrap_forecast_bootstrap_nm);
        addComponent(new GlobalChangeIndex(GLOBAL_CHANGE_INDEX.getConstant()));

    }
}
