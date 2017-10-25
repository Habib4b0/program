package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link HistWorkflowMasterLocalService}.
 *
 * @author
 * @see HistWorkflowMasterLocalService
 * @generated
 */
public class HistWorkflowMasterLocalServiceWrapper
    implements HistWorkflowMasterLocalService,
        ServiceWrapper<HistWorkflowMasterLocalService> {
    private HistWorkflowMasterLocalService _histWorkflowMasterLocalService;

    public HistWorkflowMasterLocalServiceWrapper(
        HistWorkflowMasterLocalService histWorkflowMasterLocalService) {
        _histWorkflowMasterLocalService = histWorkflowMasterLocalService;
    }

    /**
    * Adds the hist workflow master to the database. Also notifies the appropriate model listeners.
    *
    * @param histWorkflowMaster the hist workflow master
    * @return the hist workflow master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HistWorkflowMaster addHistWorkflowMaster(
        com.stpl.app.model.HistWorkflowMaster histWorkflowMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _histWorkflowMasterLocalService.addHistWorkflowMaster(histWorkflowMaster);
    }

    /**
    * Creates a new hist workflow master with the primary key. Does not add the hist workflow master to the database.
    *
    * @param workflowMasterSid the primary key for the new hist workflow master
    * @return the new hist workflow master
    */
    @Override
    public com.stpl.app.model.HistWorkflowMaster createHistWorkflowMaster(
        int workflowMasterSid) {
        return _histWorkflowMasterLocalService.createHistWorkflowMaster(workflowMasterSid);
    }

    /**
    * Deletes the hist workflow master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param workflowMasterSid the primary key of the hist workflow master
    * @return the hist workflow master that was removed
    * @throws PortalException if a hist workflow master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HistWorkflowMaster deleteHistWorkflowMaster(
        int workflowMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _histWorkflowMasterLocalService.deleteHistWorkflowMaster(workflowMasterSid);
    }

    /**
    * Deletes the hist workflow master from the database. Also notifies the appropriate model listeners.
    *
    * @param histWorkflowMaster the hist workflow master
    * @return the hist workflow master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HistWorkflowMaster deleteHistWorkflowMaster(
        com.stpl.app.model.HistWorkflowMaster histWorkflowMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _histWorkflowMasterLocalService.deleteHistWorkflowMaster(histWorkflowMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _histWorkflowMasterLocalService.dynamicQuery();
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
        return _histWorkflowMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistWorkflowMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _histWorkflowMasterLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistWorkflowMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _histWorkflowMasterLocalService.dynamicQuery(dynamicQuery,
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
        return _histWorkflowMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _histWorkflowMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.HistWorkflowMaster fetchHistWorkflowMaster(
        int workflowMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _histWorkflowMasterLocalService.fetchHistWorkflowMaster(workflowMasterSid);
    }

    /**
    * Returns the hist workflow master with the primary key.
    *
    * @param workflowMasterSid the primary key of the hist workflow master
    * @return the hist workflow master
    * @throws PortalException if a hist workflow master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HistWorkflowMaster getHistWorkflowMaster(
        int workflowMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _histWorkflowMasterLocalService.getHistWorkflowMaster(workflowMasterSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _histWorkflowMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the hist workflow masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistWorkflowMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist workflow masters
    * @param end the upper bound of the range of hist workflow masters (not inclusive)
    * @return the range of hist workflow masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.HistWorkflowMaster> getHistWorkflowMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _histWorkflowMasterLocalService.getHistWorkflowMasters(start, end);
    }

    /**
    * Returns the number of hist workflow masters.
    *
    * @return the number of hist workflow masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getHistWorkflowMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _histWorkflowMasterLocalService.getHistWorkflowMastersCount();
    }

    /**
    * Updates the hist workflow master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param histWorkflowMaster the hist workflow master
    * @return the hist workflow master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HistWorkflowMaster updateHistWorkflowMaster(
        com.stpl.app.model.HistWorkflowMaster histWorkflowMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _histWorkflowMasterLocalService.updateHistWorkflowMaster(histWorkflowMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _histWorkflowMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _histWorkflowMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _histWorkflowMasterLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public HistWorkflowMasterLocalService getWrappedHistWorkflowMasterLocalService() {
        return _histWorkflowMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedHistWorkflowMasterLocalService(
        HistWorkflowMasterLocalService histWorkflowMasterLocalService) {
        _histWorkflowMasterLocalService = histWorkflowMasterLocalService;
    }

    @Override
    public HistWorkflowMasterLocalService getWrappedService() {
        return _histWorkflowMasterLocalService;
    }

    @Override
    public void setWrappedService(
        HistWorkflowMasterLocalService histWorkflowMasterLocalService) {
        _histWorkflowMasterLocalService = histWorkflowMasterLocalService;
    }
}
