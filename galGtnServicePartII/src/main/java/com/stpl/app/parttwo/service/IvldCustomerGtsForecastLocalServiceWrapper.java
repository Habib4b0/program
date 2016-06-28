package com.stpl.app.parttwo.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link IvldCustomerGtsForecastLocalService}.
 *
 * @author
 * @see IvldCustomerGtsForecastLocalService
 * @generated
 */
public class IvldCustomerGtsForecastLocalServiceWrapper
    implements IvldCustomerGtsForecastLocalService,
        ServiceWrapper<IvldCustomerGtsForecastLocalService> {
    private IvldCustomerGtsForecastLocalService _ivldCustomerGtsForecastLocalService;

    public IvldCustomerGtsForecastLocalServiceWrapper(
        IvldCustomerGtsForecastLocalService ivldCustomerGtsForecastLocalService) {
        _ivldCustomerGtsForecastLocalService = ivldCustomerGtsForecastLocalService;
    }

    /**
    * Adds the ivld customer gts forecast to the database. Also notifies the appropriate model listeners.
    *
    * @param ivldCustomerGtsForecast the ivld customer gts forecast
    * @return the ivld customer gts forecast that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.IvldCustomerGtsForecast addIvldCustomerGtsForecast(
        com.stpl.app.parttwo.model.IvldCustomerGtsForecast ivldCustomerGtsForecast)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldCustomerGtsForecastLocalService.addIvldCustomerGtsForecast(ivldCustomerGtsForecast);
    }

    /**
    * Creates a new ivld customer gts forecast with the primary key. Does not add the ivld customer gts forecast to the database.
    *
    * @param ivldCustomerGtsForecastSid the primary key for the new ivld customer gts forecast
    * @return the new ivld customer gts forecast
    */
    @Override
    public com.stpl.app.parttwo.model.IvldCustomerGtsForecast createIvldCustomerGtsForecast(
        int ivldCustomerGtsForecastSid) {
        return _ivldCustomerGtsForecastLocalService.createIvldCustomerGtsForecast(ivldCustomerGtsForecastSid);
    }

    /**
    * Deletes the ivld customer gts forecast with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldCustomerGtsForecastSid the primary key of the ivld customer gts forecast
    * @return the ivld customer gts forecast that was removed
    * @throws PortalException if a ivld customer gts forecast with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.IvldCustomerGtsForecast deleteIvldCustomerGtsForecast(
        int ivldCustomerGtsForecastSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldCustomerGtsForecastLocalService.deleteIvldCustomerGtsForecast(ivldCustomerGtsForecastSid);
    }

    /**
    * Deletes the ivld customer gts forecast from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldCustomerGtsForecast the ivld customer gts forecast
    * @return the ivld customer gts forecast that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.IvldCustomerGtsForecast deleteIvldCustomerGtsForecast(
        com.stpl.app.parttwo.model.IvldCustomerGtsForecast ivldCustomerGtsForecast)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldCustomerGtsForecastLocalService.deleteIvldCustomerGtsForecast(ivldCustomerGtsForecast);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _ivldCustomerGtsForecastLocalService.dynamicQuery();
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
        return _ivldCustomerGtsForecastLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ivldCustomerGtsForecastLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ivldCustomerGtsForecastLocalService.dynamicQuery(dynamicQuery,
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
        return _ivldCustomerGtsForecastLocalService.dynamicQueryCount(dynamicQuery);
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
        return _ivldCustomerGtsForecastLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.parttwo.model.IvldCustomerGtsForecast fetchIvldCustomerGtsForecast(
        int ivldCustomerGtsForecastSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldCustomerGtsForecastLocalService.fetchIvldCustomerGtsForecast(ivldCustomerGtsForecastSid);
    }

    /**
    * Returns the ivld customer gts forecast with the primary key.
    *
    * @param ivldCustomerGtsForecastSid the primary key of the ivld customer gts forecast
    * @return the ivld customer gts forecast
    * @throws PortalException if a ivld customer gts forecast with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.IvldCustomerGtsForecast getIvldCustomerGtsForecast(
        int ivldCustomerGtsForecastSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldCustomerGtsForecastLocalService.getIvldCustomerGtsForecast(ivldCustomerGtsForecastSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldCustomerGtsForecastLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ivld customer gts forecasts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld customer gts forecasts
    * @param end the upper bound of the range of ivld customer gts forecasts (not inclusive)
    * @return the range of ivld customer gts forecasts
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.parttwo.model.IvldCustomerGtsForecast> getIvldCustomerGtsForecasts(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldCustomerGtsForecastLocalService.getIvldCustomerGtsForecasts(start,
            end);
    }

    /**
    * Returns the number of ivld customer gts forecasts.
    *
    * @return the number of ivld customer gts forecasts
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getIvldCustomerGtsForecastsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldCustomerGtsForecastLocalService.getIvldCustomerGtsForecastsCount();
    }

    /**
    * Updates the ivld customer gts forecast in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ivldCustomerGtsForecast the ivld customer gts forecast
    * @return the ivld customer gts forecast that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.IvldCustomerGtsForecast updateIvldCustomerGtsForecast(
        com.stpl.app.parttwo.model.IvldCustomerGtsForecast ivldCustomerGtsForecast)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldCustomerGtsForecastLocalService.updateIvldCustomerGtsForecast(ivldCustomerGtsForecast);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _ivldCustomerGtsForecastLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _ivldCustomerGtsForecastLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _ivldCustomerGtsForecastLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public IvldCustomerGtsForecastLocalService getWrappedIvldCustomerGtsForecastLocalService() {
        return _ivldCustomerGtsForecastLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedIvldCustomerGtsForecastLocalService(
        IvldCustomerGtsForecastLocalService ivldCustomerGtsForecastLocalService) {
        _ivldCustomerGtsForecastLocalService = ivldCustomerGtsForecastLocalService;
    }

    @Override
    public IvldCustomerGtsForecastLocalService getWrappedService() {
        return _ivldCustomerGtsForecastLocalService;
    }

    @Override
    public void setWrappedService(
        IvldCustomerGtsForecastLocalService ivldCustomerGtsForecastLocalService) {
        _ivldCustomerGtsForecastLocalService = ivldCustomerGtsForecastLocalService;
    }
}
