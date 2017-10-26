package com.stpl.app.service;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for WorkflowMaster. This utility wraps
 * {@link com.stpl.app.service.impl.WorkflowMasterLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see WorkflowMasterLocalService
 * @see com.stpl.app.service.base.WorkflowMasterLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.WorkflowMasterLocalServiceImpl
 * @generated
 */
public class WorkflowMasterLocalServiceUtil {
    private static WorkflowMasterLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.WorkflowMasterLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the workflow master to the database. Also notifies the appropriate model listeners.
    *
    * @param workflowMaster the workflow master
    * @return the workflow master that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.WorkflowMaster addWorkflowMaster(
        com.stpl.app.model.WorkflowMaster workflowMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().addWorkflowMaster(workflowMaster);
    }

    /**
    * Creates a new workflow master with the primary key. Does not add the workflow master to the database.
    *
    * @param workflowMasterSid the primary key for the new workflow master
    * @return the new workflow master
    */
    public static com.stpl.app.model.WorkflowMaster createWorkflowMaster(
        int workflowMasterSid) {
        return getService().createWorkflowMaster(workflowMasterSid);
    }

    /**
    * Deletes the workflow master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param workflowMasterSid the primary key of the workflow master
    * @return the workflow master that was removed
    * @throws PortalException if a workflow master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.WorkflowMaster deleteWorkflowMaster(
        int workflowMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteWorkflowMaster(workflowMasterSid);
    }

    /**
    * Deletes the workflow master from the database. Also notifies the appropriate model listeners.
    *
    * @param workflowMaster the workflow master
    * @return the workflow master that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.WorkflowMaster deleteWorkflowMaster(
        com.stpl.app.model.WorkflowMaster workflowMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteWorkflowMaster(workflowMaster);
    }

    public static com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return getService().dynamicQuery();
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WorkflowMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WorkflowMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService()
                   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.stpl.portal.kernel.dao.orm.Projection projection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery, projection);
    }

    public static com.stpl.app.model.WorkflowMaster fetchWorkflowMaster(
        int workflowMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().fetchWorkflowMaster(workflowMasterSid);
    }

    /**
    * Returns the workflow master with the primary key.
    *
    * @param workflowMasterSid the primary key of the workflow master
    * @return the workflow master
    * @throws PortalException if a workflow master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.WorkflowMaster getWorkflowMaster(
        int workflowMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getWorkflowMaster(workflowMasterSid);
    }

    public static com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the workflow masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WorkflowMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of workflow masters
    * @param end the upper bound of the range of workflow masters (not inclusive)
    * @return the range of workflow masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.WorkflowMaster> getWorkflowMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getWorkflowMasters(start, end);
    }

    /**
    * Returns the number of workflow masters.
    *
    * @return the number of workflow masters
    * @throws SystemException if a system exception occurred
    */
    public static int getWorkflowMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getWorkflowMastersCount();
    }

    /**
    * Updates the workflow master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param workflowMaster the workflow master
    * @return the workflow master that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.WorkflowMaster updateWorkflowMaster(
        com.stpl.app.model.WorkflowMaster workflowMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().updateWorkflowMaster(workflowMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public static java.lang.String getBeanIdentifier() {
        return getService().getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public static void setBeanIdentifier(java.lang.String beanIdentifier) {
        getService().setBeanIdentifier(beanIdentifier);
    }

    public static java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return getService().invokeMethod(name, parameterTypes, arguments);
    }

    public static void clearService() {
        _service = null;
    }

    public static WorkflowMasterLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    WorkflowMasterLocalService.class.getName());

            if (invokableLocalService instanceof WorkflowMasterLocalService) {
                _service = (WorkflowMasterLocalService) invokableLocalService;
            } else {
                _service = new WorkflowMasterLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(WorkflowMasterLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(WorkflowMasterLocalService service) {
    }
}
