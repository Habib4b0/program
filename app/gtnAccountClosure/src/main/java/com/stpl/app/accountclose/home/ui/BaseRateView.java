/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.home.ui;

import com.stpl.app.accountclose.dto.BaseRateDTO;
import com.stpl.app.accountclose.ui.layout.BaseRateViewWindow;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.stpl.app.accountclose.ui.form.BaseRateForm;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.VerticalLayout;
import org.jboss.logging.Logger;

/**
 *
 * @author alok.v
 */
public class BaseRateView  extends VerticalLayout{
     SessionDTO session;
    /**
     * View name for navigation.
     */
    public static final String NAME = "BASERATEVIEW";
      /**
     * DTO object for DataSelection.
     */
    private BaseRateDTO baseRateDTO = new BaseRateDTO();

    /**
     * Binder for DataSelection.
     */
    private CustomFieldGroup baseRateBinder = new CustomFieldGroup(new BeanItem<BaseRateDTO>(baseRateDTO));

    BaseRateForm baseRateForm;
    
    /**
     * The Constant LOGGER.
     */
  /**
     * Default constructor.
     */
    public BaseRateView(SessionDTO session, BaseRateDTO baseRateDTO, BaseRateViewWindow viewWindow) throws SystemException, Exception {
        this.session = session;
        this.baseRateDTO = baseRateDTO;
        addComponent(baseRateForm);
        enter();
    }


    /**
     * This method is always called before the view is shown on screen.
     *
     * @param event the event
     */
    public void enter() {
        baseRateBinder.setItemDataSource(new BeanItem<BaseRateDTO>(baseRateDTO));

    }
}
