/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.ui.view;

import com.stpl.app.accountclose.dto.MailServerConfigDTO;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.stpl.app.accountclose.ui.form.MailServerConfigIndex;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.INDICATOR_MAIL_SERVER_INDEX;
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
public class MailServerConfigurationMainView extends VerticalLayout implements View {

    /**
     * View name for navigation
     */
    public static final String NAME = StringUtils.EMPTY;
    SessionDTO sessionDTO;
    /**
     * DTO object for DataSelection
     */
    MailServerConfigDTO mailServerConfigDTO = new MailServerConfigDTO();
    private CustomFieldGroup mailServerConfigBinder = new CustomFieldGroup(new BeanItem<MailServerConfigDTO>(mailServerConfigDTO));

    public void enter(ViewChangeListener.ViewChangeEvent event) {
        mailServerConfigDTO = new MailServerConfigDTO();
        mailServerConfigBinder = new CustomFieldGroup(new BeanItem<MailServerConfigDTO>(mailServerConfigDTO));
    }

    /**
     * Default constructor
     */
    public MailServerConfigurationMainView() throws Exception {
        addStyleName("bootstrap-ui");
        addStyleName("bootstrap");
        addStyleName("bootstrap-forecast bootstrap-nm");
        addComponent(new MailServerConfigIndex(INDICATOR_MAIL_SERVER_INDEX.getConstant(), mailServerConfigDTO, mailServerConfigBinder));
    }
}
