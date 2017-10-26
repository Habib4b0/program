package com.stpl.app.parttwo.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link VwIvldAdjDemandForeActualLocalService}.
 *
 * @author
 * @see VwIvldAdjDemandForeActualLocalService
 * @generated
 */
public class VwIvldAdjDemandForeActualLocalServiceWrapper
    implements VwIvldAdjDemandForeActualLocalService,
        ServiceWrapper<VwIvldAdjDemandForeActualLocalService> {
    private VwIvldAdjDemandForeActualLocalService _vwIvldAdjDemandForeActualLocalService;

    public VwIvldAdjDemandForeActualLocalServiceWrapper(
        VwIvldAdjDemandForeActualLocalService vwIvldAdjDemandForeActualLocalService) {
        _vwIvldAdjDemandForeActualLocalService = vwIvldAdjDemandForeActualLocalService;
    }

    /**
    * Adds the vw ivld adj demand fore actual to the database. Also notifies the appropriate model listeners.
    *
    * @param vwIvldAdjDemandForeActual the vw ivld adj demand fore actual
    * @return the vw ivld adj demand fore actual that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual addVwIvldAdjDemandForeActual(
        com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwIvldAdjDemandForeActualLocalService.addVwIvldAdjDemandForeActual(vwIvldAdjDemandForeActual);
    }

    /**
    * Creates a new vw ivld adj demand fore actual with the primary key. Does not add the vw ivld adj demand fore actual to the database.
    *
    * @param ivldAdjustedDemandForecastSid the primary key for the new vw ivld adj demand fore actual
    * @return the new vw ivld adj demand fore actual
    */
    @Override
    public com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual createVwIvldAdjDemandForeActual(
        int ivldAdjustedDemandForecastSid) {
        return _vwIvldAdjDemandForeActualLocalService.createVwIvldAdjDemandForeActual(ivldAdjustedDemandForecastSid);
    }

    /**
    * Deletes the vw ivld adj demand fore actual with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldAdjustedDemandForecastSid the primary key of the vw ivld adj demand fore actual
    * @return the vw ivld adj demand fore actual that was removed
    * @throws PortalException if a vw ivld adj demand fore actual with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual deleteVwIvldAdjDemandForeActual(
        int ivldAdjustedDemandForecastSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _vwIvldAdjDemandForeActualLocalService.deleteVwIvldAdjDemandForeActual(ivldAdjustedDemandForecastSid);
    }

    /**
    * Deletes the vw ivld adj demand fore actual from the database. Also notifies the appropriate model listeners.
    *
    * @param vwIvldAdjDemandForeActual the vw ivld adj demand fore actual
    * @return the vw ivld adj demand fore actual that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual deleteVwIvldAdjDemandForeActual(
        com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwIvldAdjDemandForeActualLocalService.deleteVwIvldAdjDemandForeActual(vwIvldAdjDemandForeActual);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _vwIvldAdjDemandForeActualLocalService.dynamicQuery();
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
        return _vwIvldAdjDemandForeActualLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwIvldAdjDemandForeActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _vwIvldAdjDemandForeActualLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwIvldAdjDemandForeActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _vwIvldAdjDemandForeActualLocalService.dynamicQuery(dynamicQuery,
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
        return _vwIvldAdjDemandForeActualLocalService.dynamicQueryCount(dynamicQuery);
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
        return _vwIvldAdjDemandForeActualLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual fetchVwIvldAdjDemandForeActual(
        int ivldAdjustedDemandForecastSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwIvldAdjDemandForeActualLocalService.fetchVwIvldAdjDemandForeActual(ivldAdjustedDemandForecastSid);
    }

    /**
    * Returns the vw ivld adj demand fore actual with the primary key.
    *
    * @param ivldAdjustedDemandForecastSid the primary key of the vw ivld adj demand fore actual
    * @return the vw ivld adj demand fore actual
    * @throws PortalException if a vw ivld adj demand fore actual with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual getVwIvldAdjDemandForeActual(
        int ivldAdjustedDemandForecastSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _vwIvldAdjDemandForeActualLocalService.getVwIvldAdjDemandForeActual(ivldAdjustedDemandForecastSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _vwIvldAdjDemandForeActualLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the vw ivld adj demand fore actuals.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwIvldAdjDemandForeActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw ivld adj demand fore actuals
    * @param end the upper bound of the range of vw ivld adj demand fore actuals (not inclusive)
    * @return the range of vw ivld adj demand fore actuals
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual> getVwIvldAdjDemandForeActuals(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwIvldAdjDemandForeActualLocalService.getVwIvldAdjDemandForeActuals(start,
            end);
    }

    /**
    * Returns the number of vw ivld adj demand fore actuals.
    *
    * @return the number of vw ivld adj demand fore actuals
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getVwIvldAdjDemandForeActualsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwIvldAdjDemandForeActualLocalService.getVwIvldAdjDemandForeActualsCount();
    }

    /**
    * Updates the vw ivld adj demand fore actual in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param vwIvldAdjDemandForeActual the vw ivld adj demand fore actual
    * @return the vw ivld adj demand fore actual that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual updateVwIvldAdjDemandForeActual(
        com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwIvldAdjDemandForeActualLocalService.updateVwIvldAdjDemandForeActual(vwIvldAdjDemandForeActual);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _vwIvldAdjDemandForeActualLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _vwIvldAdjDemandForeActualLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _vwIvldAdjDemandForeActualLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public VwIvldAdjDemandForeActualLocalService getWrappedVwIvldAdjDemandForeActualLocalService() {
        return _vwIvldAdjDemandForeActualLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedVwIvldAdjDemandForeActualLocalService(
        VwIvldAdjDemandForeActualLocalService vwIvldAdjDemandForeActualLocalService) {
        _vwIvldAdjDemandForeActualLocalService = vwIvldAdjDemandForeActualLocalService;
    }

    @Override
    public VwIvldAdjDemandForeActualLocalService getWrappedService() {
        return _vwIvldAdjDemandForeActualLocalService;
    }

    @Override
    public void setWrappedService(
        VwIvldAdjDemandForeActualLocalService vwIvldAdjDemandForeActualLocalService) {
        _vwIvldAdjDemandForeActualLocalService = vwIvldAdjDemandForeActualLocalService;
    }
}
