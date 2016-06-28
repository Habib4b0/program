package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ChActualSalesLocalService}.
 *
 * @author
 * @see ChActualSalesLocalService
 * @generated
 */
public class ChActualSalesLocalServiceWrapper
    implements ChActualSalesLocalService,
        ServiceWrapper<ChActualSalesLocalService> {
    private ChActualSalesLocalService _chActualSalesLocalService;

    public ChActualSalesLocalServiceWrapper(
        ChActualSalesLocalService chActualSalesLocalService) {
        _chActualSalesLocalService = chActualSalesLocalService;
    }

    /**
    * Adds the ch actual sales to the database. Also notifies the appropriate model listeners.
    *
    * @param chActualSales the ch actual sales
    * @return the ch actual sales that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ChActualSales addChActualSales(
        com.stpl.app.model.ChActualSales chActualSales)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _chActualSalesLocalService.addChActualSales(chActualSales);
    }

    /**
    * Creates a new ch actual sales with the primary key. Does not add the ch actual sales to the database.
    *
    * @param chActualSalesPK the primary key for the new ch actual sales
    * @return the new ch actual sales
    */
    @Override
    public com.stpl.app.model.ChActualSales createChActualSales(
        com.stpl.app.service.persistence.ChActualSalesPK chActualSalesPK) {
        return _chActualSalesLocalService.createChActualSales(chActualSalesPK);
    }

    /**
    * Deletes the ch actual sales with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param chActualSalesPK the primary key of the ch actual sales
    * @return the ch actual sales that was removed
    * @throws PortalException if a ch actual sales with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ChActualSales deleteChActualSales(
        com.stpl.app.service.persistence.ChActualSalesPK chActualSalesPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _chActualSalesLocalService.deleteChActualSales(chActualSalesPK);
    }

    /**
    * Deletes the ch actual sales from the database. Also notifies the appropriate model listeners.
    *
    * @param chActualSales the ch actual sales
    * @return the ch actual sales that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ChActualSales deleteChActualSales(
        com.stpl.app.model.ChActualSales chActualSales)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _chActualSalesLocalService.deleteChActualSales(chActualSales);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _chActualSalesLocalService.dynamicQuery();
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
        return _chActualSalesLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChActualSalesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _chActualSalesLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChActualSalesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _chActualSalesLocalService.dynamicQuery(dynamicQuery, start,
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
        return _chActualSalesLocalService.dynamicQueryCount(dynamicQuery);
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
        return _chActualSalesLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.ChActualSales fetchChActualSales(
        com.stpl.app.service.persistence.ChActualSalesPK chActualSalesPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _chActualSalesLocalService.fetchChActualSales(chActualSalesPK);
    }

    /**
    * Returns the ch actual sales with the primary key.
    *
    * @param chActualSalesPK the primary key of the ch actual sales
    * @return the ch actual sales
    * @throws PortalException if a ch actual sales with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ChActualSales getChActualSales(
        com.stpl.app.service.persistence.ChActualSalesPK chActualSalesPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _chActualSalesLocalService.getChActualSales(chActualSalesPK);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _chActualSalesLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ch actual saleses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChActualSalesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ch actual saleses
    * @param end the upper bound of the range of ch actual saleses (not inclusive)
    * @return the range of ch actual saleses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.ChActualSales> getChActualSaleses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _chActualSalesLocalService.getChActualSaleses(start, end);
    }

    /**
    * Returns the number of ch actual saleses.
    *
    * @return the number of ch actual saleses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getChActualSalesesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _chActualSalesLocalService.getChActualSalesesCount();
    }

    /**
    * Updates the ch actual sales in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param chActualSales the ch actual sales
    * @return the ch actual sales that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ChActualSales updateChActualSales(
        com.stpl.app.model.ChActualSales chActualSales)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _chActualSalesLocalService.updateChActualSales(chActualSales);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _chActualSalesLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _chActualSalesLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _chActualSalesLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ChActualSalesLocalService getWrappedChActualSalesLocalService() {
        return _chActualSalesLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedChActualSalesLocalService(
        ChActualSalesLocalService chActualSalesLocalService) {
        _chActualSalesLocalService = chActualSalesLocalService;
    }

    @Override
    public ChActualSalesLocalService getWrappedService() {
        return _chActualSalesLocalService;
    }

    @Override
    public void setWrappedService(
        ChActualSalesLocalService chActualSalesLocalService) {
        _chActualSalesLocalService = chActualSalesLocalService;
    }
}
