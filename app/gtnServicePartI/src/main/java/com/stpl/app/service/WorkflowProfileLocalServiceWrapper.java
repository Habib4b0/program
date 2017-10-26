package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link WorkflowProfileLocalService}.
 *
 * @author
 * @see WorkflowProfileLocalService
 * @generated
 */
public class WorkflowProfileLocalServiceWrapper
    implements WorkflowProfileLocalService,
        ServiceWrapper<WorkflowProfileLocalService> {
    private WorkflowProfileLocalService _workflowProfileLocalService;

    public WorkflowProfileLocalServiceWrapper(
        WorkflowProfileLocalService workflowProfileLocalService) {
        _workflowProfileLocalService = workflowProfileLocalService;
    }

    /**
    * Adds the workflow profile to the database. Also notifies the appropriate model listeners.
    *
    * @param workflowProfile the workflow profile
    * @return the workflow profile that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.WorkflowProfile addWorkflowProfile(
        com.stpl.app.model.WorkflowProfile workflowProfile)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _workflowProfileLocalService.addWorkflowProfile(workflowProfile);
    }

    /**
    * Creates a new workflow profile with the primary key. Does not add the workflow profile to the database.
    *
    * @param processSid the primary key for the new workflow profile
    * @return the new workflow profile
    */
    @Override
    public com.stpl.app.model.WorkflowProfile createWorkflowProfile(
        int processSid) {
        return _workflowProfileLocalService.createWorkflowProfile(processSid);
    }

    /**
    * Deletes the workflow profile with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param processSid the primary key of the workflow profile
    * @return the workflow profile that was removed
    * @throws PortalException if a workflow profile with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.WorkflowProfile deleteWorkflowProfile(
        int processSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _workflowProfileLocalService.deleteWorkflowProfile(processSid);
    }

    /**
    * Deletes the workflow profile from the database. Also notifies the appropriate model listeners.
    *
    * @param workflowProfile the workflow profile
    * @return the workflow profile that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.WorkflowProfile deleteWorkflowProfile(
        com.stpl.app.model.WorkflowProfile workflowProfile)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _workflowProfileLocalService.deleteWorkflowProfile(workflowProfile);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _workflowProfileLocalService.dynamicQuery();
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
        return _workflowProfileLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WorkflowProfileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _workflowProfileLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WorkflowProfileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _workflowProfileLocalService.dynamicQuery(dynamicQuery, start,
            end, orderByComparator);
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
        return _workflowProfileLocalService.dynamicQueryCount(dynamicQuery);
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
        return _workflowProfileLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.WorkflowProfile fetchWorkflowProfile(
        int processSid) throws com.stpl.portal.kernel.exception.SystemException {
        return _workflowProfileLocalService.fetchWorkflowProfile(processSid);
    }

    /**
    * Returns the workflow profile with the primary key.
    *
    * @param processSid the primary key of the workflow profile
    * @return the workflow profile
    * @throws PortalException if a workflow profile with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.WorkflowProfile getWorkflowProfile(int processSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _workflowProfileLocalService.getWorkflowProfile(processSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _workflowProfileLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the workflow profiles.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WorkflowProfileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of workflow profiles
    * @param end the upper bound of the range of workflow profiles (not inclusive)
    * @return the range of workflow profiles
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.WorkflowProfile> getWorkflowProfiles(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _workflowProfileLocalService.getWorkflowProfiles(start, end);
    }

    /**
    * Returns the number of workflow profiles.
    *
    * @return the number of workflow profiles
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getWorkflowProfilesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _workflowProfileLocalService.getWorkflowProfilesCount();
    }

    /**
    * Updates the workflow profile in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param workflowProfile the workflow profile
    * @return the workflow profile that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.WorkflowProfile updateWorkflowProfile(
        com.stpl.app.model.WorkflowProfile workflowProfile)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _workflowProfileLocalService.updateWorkflowProfile(workflowProfile);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _workflowProfileLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _workflowProfileLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _workflowProfileLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public WorkflowProfileLocalService getWrappedWorkflowProfileLocalService() {
        return _workflowProfileLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedWorkflowProfileLocalService(
        WorkflowProfileLocalService workflowProfileLocalService) {
        _workflowProfileLocalService = workflowProfileLocalService;
    }

    @Override
    public WorkflowProfileLocalService getWrappedService() {
        return _workflowProfileLocalService;
    }

    @Override
    public void setWrappedService(
        WorkflowProfileLocalService workflowProfileLocalService) {
        _workflowProfileLocalService = workflowProfileLocalService;
    }
}
