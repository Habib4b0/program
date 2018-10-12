
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.processscheduler.ui;

import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.common.util.CommonUIUtil;
import com.stpl.app.adminconsole.processscheduler.ui.view.ProcessSchedulerView;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

/**
 *
 * @author Jayaram
 */
@Theme("stpl")
@Widgetset("com.stpl.widgetset.vaadin.widgetset.AppWidgetSet") 
@Component(service = UI.class, property = {
        "com.liferay.portlet.display-category=Admin Console",
        "javax.portlet.name=ProcessScheduler",
        "javax.portlet.display-name=Process Scheduler",
        "com.vaadin.osgi.liferay.portlet-ui=true"}, scope = ServiceScope.PROTOTYPE)
public class ProcessSchedulerUI extends UI {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessSchedulerUI.class);
    private SessionDTO sessionDTO = new SessionDTO();

    public ProcessSchedulerUI(){
    	super();
    }
    @AutoGenerated
    private Navigator navigator;

    @Override
    public void init(final VaadinRequest request) {
        try {
            LOGGER.info("inside init company : Process schedular");
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
            LOGGER.info("USER_ID= {} ", userId);
            LOGGER.info("SESSION_ID= {} ", sessionId);
            VaadinSession.getCurrent().setConverterFactory(new DateToStringConverterFactory());
            navigator = new Navigator(this, this);
            navigator.addView(ProcessSchedulerView.NAME, new ProcessSchedulerView(sessionDTO));
        } catch (Exception e) {

            LOGGER.error(e.getMessage());
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1010));
        }
    }

}
