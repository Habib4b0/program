package com.stpl.liferay.logon.events;

import java.util.Map;

import com.liferay.portal.kernel.audit.AuditException;
import com.liferay.portal.kernel.audit.AuditMessage;
import com.liferay.portal.kernel.audit.AuditRouter;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.security.auth.AuthFailure;
import com.liferay.portal.kernel.service.UserLocalService;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true, property = {"key=auth.failure"},
	service = AuthFailure.class
)
public class GtnAuthFailureMessageProducer implements AuthFailure {

    @Reference
    private AuditRouter auditRouter;

    @Reference
    private JSONFactory jsonFactory;

    @Reference
    private UserLocalService userLocalService;

    private static final Log _log = LogFactoryUtil.getLog(GtnAuthFailureMessageProducer.class);

    public void onFailureByEmailAddress(long companyId, String emailAddress, Map<String, String[]> headerMap,
            Map<String, String[]> parameterMap) throws AuthException {
        String reasonOfFailure = "User Authentication failed for email address " + emailAddress;
        logAuditMessage(companyId, reasonOfFailure,auditRouter);

    }

    @Deactivate
    @Modified
    protected void deactivate(BundleContext bundleContext, Map<String, Object> properties) {
        _log.info("Auth failure Audit producer Stopped ");
    }

    public static void logAuditMessage(long companyId,  String reason,AuditRouter auditRouter) {

        try {
            auditRouter.route(new AuditMessage("", companyId, 0L, "", User.class.getName(), null,
            reason));
        } catch (AuditException e) {
            if (_log.isWarnEnabled()) {
                _log.warn("Audit route not enabled ");
            }
        }
    }

    public void onFailureByScreenName(long companyId, String screenName, Map<String, String[]> headerMap,
            Map<String, String[]> parameterMap) throws AuthException {
                String reasonOfFailure = "User Authentication failed for Screen Name " + screenName;
                logAuditMessage(companyId, reasonOfFailure,auditRouter);
    }

    public void onFailureByUserId(long companyId, long userId, Map<String, String[]> headerMap,
            Map<String, String[]> parameterMap) throws AuthException {
                String reasonOfFailure = "User Authentication failed for user id " + userId;
            logAuditMessage(companyId, reasonOfFailure,auditRouter);

    }

}
