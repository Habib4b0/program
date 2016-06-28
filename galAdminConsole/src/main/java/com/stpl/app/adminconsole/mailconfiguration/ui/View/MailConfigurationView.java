/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.mailconfiguration.ui.View;

import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.mailconfiguration.dto.MailConfigurationDTO;
import com.stpl.app.adminconsole.mailconfiguration.ui.Form.MailForm;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author manikandaprabu.g
 */
public class MailConfigurationView extends VerticalLayout implements View {

    private final static Logger LOGGER = Logger.getLogger(MailConfigurationView.class);
    /**
     * The DTO creation.
     */
    private static MailConfigurationDTO mailconfigurationdto = new MailConfigurationDTO();

    /**
     * The binder.
     */
    private CustomFieldGroup binder = new CustomFieldGroup(new BeanItem<MailConfigurationDTO>(mailconfigurationdto));

    public static final String NAME = StringUtils.EMPTY;

    MailForm mailForm;
    SessionDTO sessionDTO;

    public MailConfigurationView(final SessionDTO sessionDTO) throws SystemException, PortalException, Exception {
        super();
        this.sessionDTO = sessionDTO;
        mailForm = new MailForm(this.sessionDTO);
        setStyleName("bootstrap-mailconfig");
        LOGGER.info("inside MailConfiguration View");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        try {

            addComponent(mailForm);
            binder.setItemDataSource(new BeanItem<MailConfigurationDTO>(mailconfigurationdto));
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    private CustomFieldGroup getBinder() throws Exception {

        binder.bindMemberFields(this);
        return binder;
    }

}
