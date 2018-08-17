package com.stpl.liferay.logon.events;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liferay.portal.kernel.audit.AuditMessage;
import com.liferay.portal.kernel.audit.AuditRouter;
import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.Portal;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;


@Component(immediate = true, property = { "key=login.events.post" }, service = LifecycleAction.class)
public class GtnLoginMessageProducer extends Action {

    @Reference
    private AuditRouter _auditRouter;

    @Reference
    private Portal _portal;

    @Override
    public void run(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        try {
            _doRun(request, response);
        } catch (Exception e) {
            throw new ActionException(e);
        }
    }

    private void _doRun(HttpServletRequest request, HttpServletResponse response) throws Exception {

        User user = _portal.getUser(request);
        if (user == null) {
            return;
         } 
        String message = "User " + user.getFullName() + " logged in at " + LocalDateTime.now();
        AuditMessage auditMessage = new AuditMessage("", user.getCompanyId(), user.getUserId(),
                user.getFullName(), User.class.getName(), String.valueOf(user.getUserId()),message);

        _auditRouter.route(auditMessage);
    }

}