package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link IvldForecastSalesLocalService}.
 *
 * @author
 * @see IvldForecastSalesLocalService
 * @generated
 */
public class IvldForecastSalesLocalServiceWrapper
    implements IvldForecastSalesLocalService,
        ServiceWrapper<IvldForecastSalesLocalService> {
    private IvldForecastSalesLocalService _ivldForecastSalesLocalService;

    public IvldForecastSalesLocalServiceWrapper(
        IvldForecastSalesLocalService ivldForecastSalesLocalService) {
        _ivldForecastSalesLocalService = ivldForecastSalesLocalService;
    }

    /**
    * Adds the ivld forecast sales to the database. Also notifies the appropriate model listeners.
    *
    * @param ivldForecastSales the ivld forecast sales
    * @return the ivld forecast sales that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldForecastSales addIvldForecastSales(
        com.stpl.app.model.IvldForecastSales ivldForecastSales)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldForecastSalesLocalService.addIvldForecastSales(ivldForecastSales);
    }

    /**
    * Creates a new ivld forecast sales with the primary key. Does not add the ivld forecast sales to the database.
    *
    * @param ivldForecastSalesSid the primary key for the new ivld forecast sales
    * @return the new ivld forecast sales
    */
    @Override
    public com.stpl.app.model.IvldForecastSales createIvldForecastSales(
        int ivldForecastSalesSid) {
        return _ivldForecastSalesLocalService.createIvldForecastSales(ivldForecastSalesSid);
    }

    /**
    * Deletes the ivld forecast sales with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldForecastSalesSid the primary key of the ivld forecast sales
    * @return the ivld forecast sales that was removed
    * @throws PortalException if a ivld forecast sales with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldForecastSales deleteIvldForecastSales(
        int ivldForecastSalesSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldForecastSalesLocalService.deleteIvldForecastSales(ivldForecastSalesSid);
    }

    /**
    * Deletes the ivld forecast sales from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldForecastSales the ivld forecast sales
    * @return the ivld forecast sales that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldForecastSales deleteIvldForecastSales(
        com.stpl.app.model.IvldForecastSales ivldForecastSales)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldForecastSalesLocalService.deleteIvldForecastSales(ivldForecastSales);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _ivldForecastSalesLocalService.dynamicQuery();
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
        return _ivldForecastSalesLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldForecastSalesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ivldForecastSalesLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldForecastSalesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ivldForecastSalesLocalService.dynamicQuery(dynamicQuery, start,
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
        return _ivldForecastSalesLocalService.dynamicQueryCount(dynamicQuery);
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
        return _ivldForecastSalesLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.IvldForecastSales fetchIvldForecastSales(
        int ivldForecastSalesSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldForecastSalesLocalService.fetchIvldForecastSales(ivldForecastSalesSid);
    }

    /**
    * Returns the ivld forecast sales with the primary key.
    *
    * @param ivldForecastSalesSid the primary key of the ivld forecast sales
    * @return the ivld forecast sales
    * @throws PortalException if a ivld forecast sales with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldForecastSales getIvldForecastSales(
        int ivldForecastSalesSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldForecastSalesLocalService.getIvldForecastSales(ivldForecastSalesSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldForecastSalesLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ivld forecast saleses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldForecastSalesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld forecast saleses
    * @param end the upper bound of the range of ivld forecast saleses (not inclusive)
    * @return the range of ivld forecast saleses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.IvldForecastSales> getIvldForecastSaleses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldForecastSalesLocalService.getIvldForecastSaleses(start, end);
    }

    /**
    * Returns the number of ivld forecast saleses.
    *
    * @return the number of ivld forecast saleses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getIvldForecastSalesesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldForecastSalesLocalService.getIvldForecastSalesesCount();
    }

    /**
    * Updates the ivld forecast sales in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ivldForecastSales the ivld forecast sales
    * @return the ivld forecast sales that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldForecastSales updateIvldForecastSales(
        com.stpl.app.model.IvldForecastSales ivldForecastSales)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldForecastSalesLocalService.updateIvldForecastSales(ivldForecastSales);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _ivldForecastSalesLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _ivldForecastSalesLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _ivldForecastSalesLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public IvldForecastSalesLocalService getWrappedIvldForecastSalesLocalService() {
        return _ivldForecastSalesLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedIvldForecastSalesLocalService(
        IvldForecastSalesLocalService ivldForecastSalesLocalService) {
        _ivldForecastSalesLocalService = ivldForecastSalesLocalService;
    }

    @Override
    public IvldForecastSalesLocalService getWrappedService() {
        return _ivldForecastSalesLocalService;
    }

    @Override
    public void setWrappedService(
        IvldForecastSalesLocalService ivldForecastSalesLocalService) {
        _ivldForecastSalesLocalService = ivldForecastSalesLocalService;
    }
}
