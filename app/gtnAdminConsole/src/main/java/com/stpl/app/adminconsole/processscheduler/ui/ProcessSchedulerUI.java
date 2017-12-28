
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.processscheduler.ui;

import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.common.util.CommonUIUtil;
import com.stpl.app.adminconsole.processscheduler.ui.view.processSchedulerView;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.DateToStringConverterFactory;
import com.stpl.app.adminconsole.util.ErrorCodeUtil;
import com.stpl.app.adminconsole.util.ErrorCodes;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.jboss.logging.Logger;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

/**
 *
 * @author Jayaram
 */
@Theme("mytheme")
@Widgetset("com.stpl.app.v8.AppWidgetSet")
@Component(service = UI.class, property = {
        "com.liferay.portlet.display-category=Process Scheduler",
        "javax.portlet.name=ProcessScheduler",
        "javax.portlet.display-name=Process Scheduler",
        "com.vaadin.osgi.liferay.portlet-ui=true"}, scope = ServiceScope.PROTOTYPE)
public class ProcessSchedulerUI extends UI {

    private final static Logger LOGGER = Logger.getLogger(ProcessSchedulerUI.class);
    SessionDTO sessionDTO = new SessionDTO();

    @AutoGenerated
    Navigator navigator;

    @Override
    public void init(final VaadinRequest request) {
        try {
            LOGGER.info("inside init company");
            setId("ProcessScheduler");
            addStyleName("bootstrap");

            addStyleName("bootstrap-admin");
            CommonUIUtil.beforeUnloadCloseUi(this, sessionDTO);
            final String userId = request.getRemoteUser();
            sessionDTO.setUserId(userId);
            final Date tempDate = new Date();
            final SimpleDateFormat fmtID = new SimpleDateFormat("hhmmssms");
            String sessionId=fmtID.format(tempDate);
            sessionDTO.setArpSessionId(sessionId);
            LOGGER.info("USER_ID: "+userId);
            LOGGER.info("SESSION_ID: "+sessionId);
            VaadinSession.getCurrent().setConverterFactory(new DateToStringConverterFactory());
            navigator = new Navigator(this, this);
            navigator.addView(processSchedulerView.NAME, new processSchedulerView(sessionDTO));
        } catch (Exception e) {

            LOGGER.error(e);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1010));
        }
    }

}
