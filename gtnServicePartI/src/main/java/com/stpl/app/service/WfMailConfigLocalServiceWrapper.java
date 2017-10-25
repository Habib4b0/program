package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link WfMailConfigLocalService}.
 *
 * @author
 * @see WfMailConfigLocalService
 * @generated
 */
public class WfMailConfigLocalServiceWrapper implements WfMailConfigLocalService,
    ServiceWrapper<WfMailConfigLocalService> {
    private WfMailConfigLocalService _wfMailConfigLocalService;

    public WfMailConfigLocalServiceWrapper(
        WfMailConfigLocalService wfMailConfigLocalService) {
        _wfMailConfigLocalService = wfMailConfigLocalService;
    }

    /**
    * Adds the wf mail config to the database. Also notifies the appropriate model listeners.
    *
    * @param wfMailConfig the wf mail config
    * @return the wf mail config that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.WfMailConfig addWfMailConfig(
        com.stpl.app.model.WfMailConfig wfMailConfig)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _wfMailConfigLocalService.addWfMailConfig(wfMailConfig);
    }

    /**
    * Creates a new wf mail config with the primary key. Does not add the wf mail config to the database.
    *
    * @param wfMailConfigSid the primary key for the new wf mail config
    * @return the new wf mail config
    */
    @Override
    public com.stpl.app.model.WfMailConfig createWfMailConfig(
        int wfMailConfigSid) {
        return _wfMailConfigLocalService.createWfMailConfig(wfMailConfigSid);
    }

    /**
    * Deletes the wf mail config with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param wfMailConfigSid the primary key of the wf mail config
    * @return the wf mail config that was removed
    * @throws PortalException if a wf mail config with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.WfMailConfig deleteWfMailConfig(
        int wfMailConfigSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _wfMailConfigLocalService.deleteWfMailConfig(wfMailConfigSid);
    }

    /**
    * Deletes the wf mail config from the database. Also notifies the appropriate model listeners.
    *
    * @param wfMailConfig the wf mail config
    * @return the wf mail config that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.WfMailConfig deleteWfMailConfig(
        com.stpl.app.model.WfMailConfig wfMailConfig)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _wfMailConfigLocalService.deleteWfMailConfig(wfMailConfig);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _wfMailConfigLocalService.dynamicQuery();
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
        return _wfMailConfigLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WfMailConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _wfMailConfigLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WfMailConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _wfMailConfigLocalService.dynamicQuery(dynamicQuery, start, end,
            orderByComparator);
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
        return _wfMailConfigLocalService.dynamicQueryCount(dynamicQuery);
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
        return _wfMailConfigLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.WfMailConfig fetchWfMailConfig(
        int wfMailConfigSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _wfMailConfigLocalService.fetchWfMailConfig(wfMailConfigSid);
    }

    /**
    * Returns the wf mail config with the primary key.
    *
    * @param wfMailConfigSid the primary key of the wf mail config
    * @return the wf mail config
    * @throws PortalException if a wf mail config with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.WfMailConfig getWfMailConfig(int wfMailConfigSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _wfMailConfigLocalService.getWfMailConfig(wfMailConfigSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _wfMailConfigLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the wf mail configs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WfMailConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of wf mail configs
    * @param end the upper bound of the range of wf mail configs (not inclusive)
    * @return the range of wf mail configs
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.WfMailConfig> getWfMailConfigs(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _wfMailConfigLocalService.getWfMailConfigs(start, end);
    }

    /**
    * Returns the number of wf mail configs.
    *
    * @return the number of wf mail configs
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getWfMailConfigsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _wfMailConfigLocalService.getWfMailConfigsCount();
    }

    /**
    * Updates the wf mail config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param wfMailConfig the wf mail config
    * @return the wf mail config that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.WfMailConfig updateWfMailConfig(
        com.stpl.app.model.WfMailConfig wfMailConfig)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _wfMailConfigLocalService.updateWfMailConfig(wfMailConfig);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _wfMailConfigLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _wfMailConfigLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _wfMailConfigLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public WfMailConfigLocalService getWrappedWfMailConfigLocalService() {
        return _wfMailConfigLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedWfMailConfigLocalService(
        WfMailConfigLocalService wfMailConfigLocalService) {
        _wfMailConfigLocalService = wfMailConfigLocalService;
    }

    @Override
    public WfMailConfigLocalService getWrappedService() {
        return _wfMailConfigLocalService;
    }

    @Override
    public void setWrappedService(
        WfMailConfigLocalService wfMailConfigLocalService) {
        _wfMailConfigLocalService = wfMailConfigLocalService;
    }
}
