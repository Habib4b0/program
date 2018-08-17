package com.stpl.app.audit;

import com.liferay.portal.kernel.audit.AuditException;
import com.liferay.portal.kernel.audit.AuditMessage;
import com.liferay.portal.kernel.audit.AuditRouter;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.UserLocalService;

import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

public class GtnAuditMessageProducer {

    private static final Log _log = LogFactoryUtil.getLog(GtnAuditMessageProducer.class);

    public String getReasonBasedOnCurrentUser(String reason) {
        ServiceReference<UserLocalService> userReference = FrameworkUtil.getBundle(this.getClass()).getBundleContext()
                .getServiceReference(UserLocalService.class);

        UserLocalService userLocalService = FrameworkUtil.getBundle(this.getClass()).getBundleContext()
                .getService(userReference);
                
        ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();
        String user = "Ghost (No user information Available) ";
        if (null != serviceContext) {
            try {
                User currentUser = userLocalService.getUser(serviceContext.getUserId());
                user = currentUser.getFullName() + " ( " + currentUser.getUserId() + " )";
            } catch (PortalException e) {
                _log.error("Erron on accessing user. User Id : " + serviceContext.getUserId());
            }
        }

        return reason + " by " + user;
    }

    public void logAuditMessage(long companyId, String reason, String className) {
        try {
            ServiceReference<AuditRouter> auditReference = FrameworkUtil.getBundle(this.getClass()).getBundleContext()
                    .getServiceReference(AuditRouter.class);
            AuditRouter router = FrameworkUtil.getBundle(this.getClass()).getBundleContext().getService(auditReference);
            String reasonWithCurrentUser = getReasonBasedOnCurrentUser(reason);
            router.route(new AuditMessage("", companyId, 0L, "", className, null, reasonWithCurrentUser));
        } catch (AuditException e) {
            if (_log.isWarnEnabled()) {
                _log.warn("Audit route not enabled ");
            }
        }
    }
}