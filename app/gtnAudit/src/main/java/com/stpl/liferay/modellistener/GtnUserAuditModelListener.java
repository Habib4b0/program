package com.stpl.liferay.modellistener;

import java.time.LocalDateTime;
import java.util.Map;

import com.liferay.portal.kernel.audit.AuditRouter;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.PortalUtil;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = ModelListener.class)
public class GtnUserAuditModelListener extends GtnAuditBaseModelListener<User> {
    private static final Log _log = LogFactoryUtil.getLog(GtnUserAuditModelListener.class);

    @Reference
    private AuditRouter _auditRouter;

    @Reference
    private UserLocalService userLocalService;

    public void setEntityName() {
    }

    @Override
    public AuditRouter get_auditRouter() {
        return _auditRouter;
    }

    @Override
    public UserLocalService getUserLocalService() {
        return userLocalService;
    }

    @Activate
    @Modified
    protected void activate(BundleContext bundleContext, Map<String, Object> properties) {
        _log.info("User Model Listener Initiated");
    }

    @Deactivate
    @Modified
    protected void deactivate(BundleContext bundleContext, Map<String, Object> properties) {
        _log.info("User Model Listener Stopped ");
    }

    @Override
    public void onBeforeUpdate(User model) throws ModelListenerException {
        User userBeforeUpdate = model;
        boolean isLocked = model.getLockout();
        try {
            userBeforeUpdate = userLocalService.getUser(model.getUserId());

        } catch (PortalException e) {
            _log.error("Unable to find user in Audit " + model.getUserId());
        }
        boolean isPrevLocked = userBeforeUpdate.getLockout();
        String reason;
        if (isLocked && !isPrevLocked) {
            reason = " User " + System.lineSeparator() + model + System.lineSeparator() + " is lockedOut on "
                    + LocalDateTime.now();
        } else {

            reason = userBeforeUpdate.toString()+" changed to "+ model.toString();
        }
        logAuditMessage(PortalUtil.getDefaultCompanyId(), reason, _auditRouter, this.getClass().getName());
        super.onBeforeUpdate(model);
    }

    @Override
    public void onAfterAddAssociation(Object classPK, String associationClassName, Object associationClassPK)
            throws ModelListenerException {
        try {
            String classToBeAssociated = Class.forName(associationClassName).getSimpleName();
            String reason = " User  with pk " + classPK + "  was associated with " + classToBeAssociated
                    + " with primary key " + associationClassPK;
            logAuditMessage(PortalUtil.getDefaultCompanyId(), reason, _auditRouter, this.getClass().getName());

        } catch (ClassNotFoundException e) {
            _log.error("Class not found for " + associationClassName);
        }
        super.onAfterAddAssociation(classPK, associationClassName, associationClassPK);
    }

    @Override
    public void onAfterRemoveAssociation(Object classPK, String associationClassName, Object associationClassPK)
            throws ModelListenerException {
        try {
            String classToBeAssociated = Class.forName(associationClassName).getSimpleName();
            String reason = " Assocaition  " + classToBeAssociated + " with pk " + associationClassPK
                    + " was removed from User with primary key " + classPK;
            logAuditMessage(PortalUtil.getDefaultCompanyId(), reason, _auditRouter, this.getClass().getName());

        } catch (ClassNotFoundException e) {
            _log.error("Class not found for " + associationClassName);
        }
        super.onAfterRemoveAssociation(classPK, associationClassName, associationClassPK);
    }

}