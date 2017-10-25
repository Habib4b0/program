package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SalesBasisDetailsLocalService}.
 *
 * @author
 * @see SalesBasisDetailsLocalService
 * @generated
 */
public class SalesBasisDetailsLocalServiceWrapper
    implements SalesBasisDetailsLocalService,
        ServiceWrapper<SalesBasisDetailsLocalService> {
    private SalesBasisDetailsLocalService _salesBasisDetailsLocalService;

    public SalesBasisDetailsLocalServiceWrapper(
        SalesBasisDetailsLocalService salesBasisDetailsLocalService) {
        _salesBasisDetailsLocalService = salesBasisDetailsLocalService;
    }

    /**
    * Adds the sales basis details to the database. Also notifies the appropriate model listeners.
    *
    * @param salesBasisDetails the sales basis details
    * @return the sales basis details that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.SalesBasisDetails addSalesBasisDetails(
        com.stpl.app.model.SalesBasisDetails salesBasisDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _salesBasisDetailsLocalService.addSalesBasisDetails(salesBasisDetails);
    }

    /**
    * Creates a new sales basis details with the primary key. Does not add the sales basis details to the database.
    *
    * @param salesBasisDetailsSid the primary key for the new sales basis details
    * @return the new sales basis details
    */
    @Override
    public com.stpl.app.model.SalesBasisDetails createSalesBasisDetails(
        int salesBasisDetailsSid) {
        return _salesBasisDetailsLocalService.createSalesBasisDetails(salesBasisDetailsSid);
    }

    /**
    * Deletes the sales basis details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param salesBasisDetailsSid the primary key of the sales basis details
    * @return the sales basis details that was removed
    * @throws PortalException if a sales basis details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.SalesBasisDetails deleteSalesBasisDetails(
        int salesBasisDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _salesBasisDetailsLocalService.deleteSalesBasisDetails(salesBasisDetailsSid);
    }

    /**
    * Deletes the sales basis details from the database. Also notifies the appropriate model listeners.
    *
    * @param salesBasisDetails the sales basis details
    * @return the sales basis details that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.SalesBasisDetails deleteSalesBasisDetails(
        com.stpl.app.model.SalesBasisDetails salesBasisDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _salesBasisDetailsLocalService.deleteSalesBasisDetails(salesBasisDetails);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _salesBasisDetailsLocalService.dynamicQuery();
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
        return _salesBasisDetailsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesBasisDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _salesBasisDetailsLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesBasisDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _salesBasisDetailsLocalService.dynamicQuery(dynamicQuery, start,
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
        return _salesBasisDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _salesBasisDetailsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.SalesBasisDetails fetchSalesBasisDetails(
        int salesBasisDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _salesBasisDetailsLocalService.fetchSalesBasisDetails(salesBasisDetailsSid);
    }

    /**
    * Returns the sales basis details with the primary key.
    *
    * @param salesBasisDetailsSid the primary key of the sales basis details
    * @return the sales basis details
    * @throws PortalException if a sales basis details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.SalesBasisDetails getSalesBasisDetails(
        int salesBasisDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _salesBasisDetailsLocalService.getSalesBasisDetails(salesBasisDetailsSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _salesBasisDetailsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the sales basis detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesBasisDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of sales basis detailses
    * @param end the upper bound of the range of sales basis detailses (not inclusive)
    * @return the range of sales basis detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.SalesBasisDetails> getSalesBasisDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _salesBasisDetailsLocalService.getSalesBasisDetailses(start, end);
    }

    /**
    * Returns the number of sales basis detailses.
    *
    * @return the number of sales basis detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getSalesBasisDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _salesBasisDetailsLocalService.getSalesBasisDetailsesCount();
    }

    /**
    * Updates the sales basis details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param salesBasisDetails the sales basis details
    * @return the sales basis details that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.SalesBasisDetails updateSalesBasisDetails(
        com.stpl.app.model.SalesBasisDetails salesBasisDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _salesBasisDetailsLocalService.updateSalesBasisDetails(salesBasisDetails);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _salesBasisDetailsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _salesBasisDetailsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _salesBasisDetailsLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public SalesBasisDetailsLocalService getWrappedSalesBasisDetailsLocalService() {
        return _salesBasisDetailsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedSalesBasisDetailsLocalService(
        SalesBasisDetailsLocalService salesBasisDetailsLocalService) {
        _salesBasisDetailsLocalService = salesBasisDetailsLocalService;
    }

    @Override
    public SalesBasisDetailsLocalService getWrappedService() {
        return _salesBasisDetailsLocalService;
    }

    @Override
    public void setWrappedService(
        SalesBasisDetailsLocalService salesBasisDetailsLocalService) {
        _salesBasisDetailsLocalService = salesBasisDetailsLocalService;
    }
}
