package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link WorkflowProcessInfoLocalService}.
 *
 * @author
 * @see WorkflowProcessInfoLocalService
 * @generated
 */
public class WorkflowProcessInfoLocalServiceWrapper
    implements WorkflowProcessInfoLocalService,
        ServiceWrapper<WorkflowProcessInfoLocalService> {
    private WorkflowProcessInfoLocalService _workflowProcessInfoLocalService;

    public WorkflowProcessInfoLocalServiceWrapper(
        WorkflowProcessInfoLocalService workflowProcessInfoLocalService) {
        _workflowProcessInfoLocalService = workflowProcessInfoLocalService;
    }

    /**
    * Adds the workflow process info to the database. Also notifies the appropriate model listeners.
    *
    * @param workflowProcessInfo the workflow process info
    * @return the workflow process info that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.WorkflowProcessInfo addWorkflowProcessInfo(
        com.stpl.app.model.WorkflowProcessInfo workflowProcessInfo)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _workflowProcessInfoLocalService.addWorkflowProcessInfo(workflowProcessInfo);
    }

    /**
    * Creates a new workflow process info with the primary key. Does not add the workflow process info to the database.
    *
    * @param workflowProcessInfoSid the primary key for the new workflow process info
    * @return the new workflow process info
    */
    @Override
    public com.stpl.app.model.WorkflowProcessInfo createWorkflowProcessInfo(
        int workflowProcessInfoSid) {
        return _workflowProcessInfoLocalService.createWorkflowProcessInfo(workflowProcessInfoSid);
    }

    /**
    * Deletes the workflow process info with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param workflowProcessInfoSid the primary key of the workflow process info
    * @return the workflow process info that was removed
    * @throws PortalException if a workflow process info with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.WorkflowProcessInfo deleteWorkflowProcessInfo(
        int workflowProcessInfoSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _workflowProcessInfoLocalService.deleteWorkflowProcessInfo(workflowProcessInfoSid);
    }

    /**
    * Deletes the workflow process info from the database. Also notifies the appropriate model listeners.
    *
    * @param workflowProcessInfo the workflow process info
    * @return the workflow process info that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.WorkflowProcessInfo deleteWorkflowProcessInfo(
        com.stpl.app.model.WorkflowProcessInfo workflowProcessInfo)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _workflowProcessInfoLocalService.deleteWorkflowProcessInfo(workflowProcessInfo);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _workflowProcessInfoLocalService.dynamicQuery();
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _workflowProcessInfoLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WorkflowProcessInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException {
        return _workflowProcessInfoLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WorkflowProcessInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _workflowProcessInfoLocalService.dynamicQuery(dynamicQuery,
            start, end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    @Override
    public long dynamicQueryCount(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _workflowProcessInfoLocalService.dynamicQueryCount(dynamicQuery);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    @Override
    public long dynamicQueryCount(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.stpl.portal.kernel.dao.orm.Projection projection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _workflowProcessInfoLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.WorkflowProcessInfo fetchWorkflowProcessInfo(
        int workflowProcessInfoSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _workflowProcessInfoLocalService.fetchWorkflowProcessInfo(workflowProcessInfoSid);
    }

    /**
    * Returns the workflow process info with the primary key.
    *
    * @param workflowProcessInfoSid the primary key of the workflow process info
    * @return the workflow process info
    * @throws PortalException if a workflow process info with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.WorkflowProcessInfo getWorkflowProcessInfo(
        int workflowProcessInfoSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _workflowProcessInfoLocalService.getWorkflowProcessInfo(workflowProcessInfoSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _workflowProcessInfoLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the workflow process infos.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WorkflowProcessInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of workflow process infos
    * @param end the upper bound of the range of workflow process infos (not inclusive)
    * @return the range of workflow process infos
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.WorkflowProcessInfo> getWorkflowProcessInfos(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _workflowProcessInfoLocalService.getWorkflowProcessInfos(start,
            end);
    }

    /**
    * Returns the number of workflow process infos.
    *
    * @return the number of workflow process infos
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getWorkflowProcessInfosCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _workflowProcessInfoLocalService.getWorkflowProcessInfosCount();
    }

    /**
    * Updates the workflow process info in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param workflowProcessInfo the workflow process info
    * @return the workflow process info that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.WorkflowProcessInfo updateWorkflowProcessInfo(
        com.stpl.app.model.WorkflowProcessInfo workflowProcessInfo)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _workflowProcessInfoLocalService.updateWorkflowProcessInfo(workflowProcessInfo);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _workflowProcessInfoLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _workflowProcessInfoLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _workflowProcessInfoLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public WorkflowProcessInfoLocalService getWrappedWorkflowProcessInfoLocalService() {
        return _workflowProcessInfoLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedWorkflowProcessInfoLocalService(
        WorkflowProcessInfoLocalService workflowProcessInfoLocalService) {
        _workflowProcessInfoLocalService = workflowProcessInfoLocalService;
    }

    @Override
    public WorkflowProcessInfoLocalService getWrappedService() {
        return _workflowProcessInfoLocalService;
    }

    @Override
    public void setWrappedService(
        WorkflowProcessInfoLocalService workflowProcessInfoLocalService) {
        _workflowProcessInfoLocalService = workflowProcessInfoLocalService;
    }
}
