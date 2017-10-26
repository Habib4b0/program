package com.stpl.app.parttwo.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link VwAdjustDemandForecastActLocalService}.
 *
 * @author
 * @see VwAdjustDemandForecastActLocalService
 * @generated
 */
public class VwAdjustDemandForecastActLocalServiceWrapper
    implements VwAdjustDemandForecastActLocalService,
        ServiceWrapper<VwAdjustDemandForecastActLocalService> {
    private VwAdjustDemandForecastActLocalService _vwAdjustDemandForecastActLocalService;

    public VwAdjustDemandForecastActLocalServiceWrapper(
        VwAdjustDemandForecastActLocalService vwAdjustDemandForecastActLocalService) {
        _vwAdjustDemandForecastActLocalService = vwAdjustDemandForecastActLocalService;
    }

    /**
    * Adds the vw adjust demand forecast act to the database. Also notifies the appropriate model listeners.
    *
    * @param vwAdjustDemandForecastAct the vw adjust demand forecast act
    * @return the vw adjust demand forecast act that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwAdjustDemandForecastAct addVwAdjustDemandForecastAct(
        com.stpl.app.parttwo.model.VwAdjustDemandForecastAct vwAdjustDemandForecastAct)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwAdjustDemandForecastActLocalService.addVwAdjustDemandForecastAct(vwAdjustDemandForecastAct);
    }

    /**
    * Creates a new vw adjust demand forecast act with the primary key. Does not add the vw adjust demand forecast act to the database.
    *
    * @param adjustedDemandForecastId the primary key for the new vw adjust demand forecast act
    * @return the new vw adjust demand forecast act
    */
    @Override
    public com.stpl.app.parttwo.model.VwAdjustDemandForecastAct createVwAdjustDemandForecastAct(
        int adjustedDemandForecastId) {
        return _vwAdjustDemandForecastActLocalService.createVwAdjustDemandForecastAct(adjustedDemandForecastId);
    }

    /**
    * Deletes the vw adjust demand forecast act with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param adjustedDemandForecastId the primary key of the vw adjust demand forecast act
    * @return the vw adjust demand forecast act that was removed
    * @throws PortalException if a vw adjust demand forecast act with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwAdjustDemandForecastAct deleteVwAdjustDemandForecastAct(
        int adjustedDemandForecastId)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _vwAdjustDemandForecastActLocalService.deleteVwAdjustDemandForecastAct(adjustedDemandForecastId);
    }

    /**
    * Deletes the vw adjust demand forecast act from the database. Also notifies the appropriate model listeners.
    *
    * @param vwAdjustDemandForecastAct the vw adjust demand forecast act
    * @return the vw adjust demand forecast act that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwAdjustDemandForecastAct deleteVwAdjustDemandForecastAct(
        com.stpl.app.parttwo.model.VwAdjustDemandForecastAct vwAdjustDemandForecastAct)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwAdjustDemandForecastActLocalService.deleteVwAdjustDemandForecastAct(vwAdjustDemandForecastAct);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _vwAdjustDemandForecastActLocalService.dynamicQuery();
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
        return _vwAdjustDemandForecastActLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwAdjustDemandForecastActModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _vwAdjustDemandForecastActLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwAdjustDemandForecastActModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _vwAdjustDemandForecastActLocalService.dynamicQuery(dynamicQuery,
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
        return _vwAdjustDemandForecastActLocalService.dynamicQueryCount(dynamicQuery);
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
        return _vwAdjustDemandForecastActLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.parttwo.model.VwAdjustDemandForecastAct fetchVwAdjustDemandForecastAct(
        int adjustedDemandForecastId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwAdjustDemandForecastActLocalService.fetchVwAdjustDemandForecastAct(adjustedDemandForecastId);
    }

    /**
    * Returns the vw adjust demand forecast act with the primary key.
    *
    * @param adjustedDemandForecastId the primary key of the vw adjust demand forecast act
    * @return the vw adjust demand forecast act
    * @throws PortalException if a vw adjust demand forecast act with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwAdjustDemandForecastAct getVwAdjustDemandForecastAct(
        int adjustedDemandForecastId)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _vwAdjustDemandForecastActLocalService.getVwAdjustDemandForecastAct(adjustedDemandForecastId);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _vwAdjustDemandForecastActLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the vw adjust demand forecast acts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwAdjustDemandForecastActModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw adjust demand forecast acts
    * @param end the upper bound of the range of vw adjust demand forecast acts (not inclusive)
    * @return the range of vw adjust demand forecast acts
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.parttwo.model.VwAdjustDemandForecastAct> getVwAdjustDemandForecastActs(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwAdjustDemandForecastActLocalService.getVwAdjustDemandForecastActs(start,
            end);
    }

    /**
    * Returns the number of vw adjust demand forecast acts.
    *
    * @return the number of vw adjust demand forecast acts
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getVwAdjustDemandForecastActsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwAdjustDemandForecastActLocalService.getVwAdjustDemandForecastActsCount();
    }

    /**
    * Updates the vw adjust demand forecast act in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param vwAdjustDemandForecastAct the vw adjust demand forecast act
    * @return the vw adjust demand forecast act that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwAdjustDemandForecastAct updateVwAdjustDemandForecastAct(
        com.stpl.app.parttwo.model.VwAdjustDemandForecastAct vwAdjustDemandForecastAct)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwAdjustDemandForecastActLocalService.updateVwAdjustDemandForecastAct(vwAdjustDemandForecastAct);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _vwAdjustDemandForecastActLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _vwAdjustDemandForecastActLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _vwAdjustDemandForecastActLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public VwAdjustDemandForecastActLocalService getWrappedVwAdjustDemandForecastActLocalService() {
        return _vwAdjustDemandForecastActLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedVwAdjustDemandForecastActLocalService(
        VwAdjustDemandForecastActLocalService vwAdjustDemandForecastActLocalService) {
        _vwAdjustDemandForecastActLocalService = vwAdjustDemandForecastActLocalService;
    }

    @Override
    public VwAdjustDemandForecastActLocalService getWrappedService() {
        return _vwAdjustDemandForecastActLocalService;
    }

    @Override
    public void setWrappedService(
        VwAdjustDemandForecastActLocalService vwAdjustDemandForecastActLocalService) {
        _vwAdjustDemandForecastActLocalService = vwAdjustDemandForecastActLocalService;
    }
}
