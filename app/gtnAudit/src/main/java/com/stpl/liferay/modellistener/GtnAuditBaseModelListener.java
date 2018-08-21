package com.stpl.liferay.modellistener;

import com.liferay.portal.kernel.audit.AuditException;
import com.liferay.portal.kernel.audit.AuditMessage;
import com.liferay.portal.kernel.audit.AuditRouter;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.PortalUtil;

public class GtnAuditBaseModelListener<T extends BaseModel<T>> extends BaseModelListener<T> {

    public AuditRouter get_auditRouter() {
        return null;
    }

    public UserLocalService getUserLocalService() {
        return null;
    }

    private static final Log _log = LogFactoryUtil.getLog(GtnAuditBaseModelListener.class);

    @Override
    public void onAfterCreate(T model) throws ModelListenerException {
        String userAddMessage = this.getClass().getSimpleName() + "  " + model + " created ";

        logAuditMessage(PortalUtil.getDefaultCompanyId(), userAddMessage, get_auditRouter(), this.getClass().getName());

        super.onAfterCreate(model);

    }

    @Override
    public void onAfterRemove(T model) throws ModelListenerException {
        String userDeleteMessage = this.getClass().getSimpleName() + " " + model + " deleted ";
        logAuditMessage(PortalUtil.getDefaultCompanyId(), userDeleteMessage, get_auditRouter(),
                this.getClass().getName());
        super.onAfterRemove(model);
    }

    public void logAuditMessage(long companyId, String reason, AuditRouter auditRouter, String className) {
        try {
            String reasonWithCurrentUser = getReasonBasedOnCurrentUser(reason);
            auditRouter.route(new AuditMessage("", companyId, 0L, "", className, null, reasonWithCurrentUser));
        } catch (AuditException e) {
            if (_log.isWarnEnabled()) {
                _log.warn("Audit route not enabled ");
            }
        }
    }

    public String getReasonBasedOnCurrentUser(String reason) {
        ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();
        String user = "Ghost (No user information Available) ";
        if (null != serviceContext) {
            try {
                User currentUser = this.getUserLocalService().getUser(serviceContext.getUserId());
                user = currentUser.getFullName() + " ( " + currentUser.getUserId() + " )";
            } catch (PortalException e) {
                _log.error("Erron on accessing user. User Id : " + serviceContext.getUserId());
            }
        }

        return reason + " by " + user;
    }

}