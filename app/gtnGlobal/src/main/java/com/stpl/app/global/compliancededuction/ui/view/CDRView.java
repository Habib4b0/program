/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.compliancededuction.ui.view;

import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.compliancededuction.dto.CDRDto;
import com.stpl.app.global.compliancededuction.ui.form.CDRForm;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.v7.ui.VerticalLayout;
import com.vaadin.v7.data.util.BeanItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sooriya.lakshmanan
 */
public class CDRView extends VerticalLayout implements View {

    /**
     * The Constant NAME.
     */
    public static final String NAME = "View";
    
    /**
     * The binder.
     */
    private ErrorfulFieldGroup binder = new ErrorfulFieldGroup(new BeanItem(new CDRDto()));
    /**
     * Session DTO
     */
    private final SessionDTO sessionDTO;
    private CDRForm cdrForm;
    private static final Logger LOGGER = LoggerFactory.getLogger(CDRView.class);

    /**
     * The Constructor.
     *
     * @throws PortalException
     * @throws SystemException
     * @throws Exception
     */
    public CDRView(SessionDTO sessionDTO) {
        super();
        this.sessionDTO = sessionDTO;
        setStyleName("bootstrap-company");
        setSpacing(true);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        LOGGER.debug("UserId  =========  {}",sessionDTO.getUserId());
        if (cdrForm != null) {
            this.removeAllComponents();
            binder = new ErrorfulFieldGroup(new BeanItem(new CDRDto()));
        }
     
        cdrForm = new CDRForm(binder, sessionDTO);
        addComponent(cdrForm);
    }
}
