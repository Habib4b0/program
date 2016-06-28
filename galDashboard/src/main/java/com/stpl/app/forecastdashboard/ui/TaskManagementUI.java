package com.stpl.app.forecastdashboard.ui;

import com.stpl.app.forecastdashboard.ui.form.TaskManagementForm;
import com.stpl.app.forecastdashboard.utils.CommonUtils;
import com.vaadin.addon.ipcforliferay.LiferayIPC;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nandhakumar
 */
public class TaskManagementUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        LiferayIPC liferayIPC = new LiferayIPC();
        liferayIPC.extend(getUI());
        CommonUtils.setLiferayIPC(liferayIPC);
        addStyleName("forecast-dashboard");
        Navigator navigator;
        final String userId = request.getRemoteUser();
        final String sessionId = request.getWrappedSession().getId();
        VaadinSession.getCurrent().setAttribute("sessionId", sessionId);
        VaadinSession.getCurrent().setAttribute("userId", userId);
        navigator = new Navigator(this, this);
        try {
            navigator.addView("", new TaskManagementForm());
        } catch (Exception ex) {
            Logger.getLogger(TaskManagementUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
