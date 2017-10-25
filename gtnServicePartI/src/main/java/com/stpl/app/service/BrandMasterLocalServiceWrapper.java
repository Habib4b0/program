package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link BrandMasterLocalService}.
 *
 * @author
 * @see BrandMasterLocalService
 * @generated
 */
public class BrandMasterLocalServiceWrapper implements BrandMasterLocalService,
    ServiceWrapper<BrandMasterLocalService> {
    private BrandMasterLocalService _brandMasterLocalService;

    public BrandMasterLocalServiceWrapper(
        BrandMasterLocalService brandMasterLocalService) {
        _brandMasterLocalService = brandMasterLocalService;
    }

    /**
    * Adds the brand master to the database. Also notifies the appropriate model listeners.
    *
    * @param brandMaster the brand master
    * @return the brand master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.BrandMaster addBrandMaster(
        com.stpl.app.model.BrandMaster brandMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _brandMasterLocalService.addBrandMaster(brandMaster);
    }

    /**
    * Creates a new brand master with the primary key. Does not add the brand master to the database.
    *
    * @param brandMasterSid the primary key for the new brand master
    * @return the new brand master
    */
    @Override
    public com.stpl.app.model.BrandMaster createBrandMaster(int brandMasterSid) {
        return _brandMasterLocalService.createBrandMaster(brandMasterSid);
    }

    /**
    * Deletes the brand master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param brandMasterSid the primary key of the brand master
    * @return the brand master that was removed
    * @throws PortalException if a brand master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.BrandMaster deleteBrandMaster(int brandMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _brandMasterLocalService.deleteBrandMaster(brandMasterSid);
    }

    /**
    * Deletes the brand master from the database. Also notifies the appropriate model listeners.
    *
    * @param brandMaster the brand master
    * @return the brand master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.BrandMaster deleteBrandMaster(
        com.stpl.app.model.BrandMaster brandMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _brandMasterLocalService.deleteBrandMaster(brandMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _brandMasterLocalService.dynamicQuery();
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
        return _brandMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BrandMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _brandMasterLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BrandMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _brandMasterLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _brandMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _brandMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.BrandMaster fetchBrandMaster(int brandMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _brandMasterLocalService.fetchBrandMaster(brandMasterSid);
    }

    /**
    * Returns the brand master with the primary key.
    *
    * @param brandMasterSid the primary key of the brand master
    * @return the brand master
    * @throws PortalException if a brand master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.BrandMaster getBrandMaster(int brandMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _brandMasterLocalService.getBrandMaster(brandMasterSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _brandMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the brand masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BrandMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of brand masters
    * @param end the upper bound of the range of brand masters (not inclusive)
    * @return the range of brand masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.BrandMaster> getBrandMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _brandMasterLocalService.getBrandMasters(start, end);
    }

    /**
    * Returns the number of brand masters.
    *
    * @return the number of brand masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getBrandMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _brandMasterLocalService.getBrandMastersCount();
    }

    /**
    * Updates the brand master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param brandMaster the brand master
    * @return the brand master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.BrandMaster updateBrandMaster(
        com.stpl.app.model.BrandMaster brandMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _brandMasterLocalService.updateBrandMaster(brandMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _brandMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _brandMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _brandMasterLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    @Override
    public java.lang.Object executeSelectQuery(java.lang.String query,
        java.lang.Object udc1, java.lang.Object udc2) {
        return _brandMasterLocalService.executeSelectQuery(query, udc1, udc2);
    }

    @Override
    public java.lang.Object executeBulkUpdateQuery(java.lang.String query,
        java.lang.Object udc1, java.lang.Object udc2) {
        return _brandMasterLocalService.executeBulkUpdateQuery(query, udc1, udc2);
    }

    @Override
    public java.lang.Object executeUpdateQuery(java.util.List<?> nmSalesList,
        java.lang.Object udc1, java.lang.Object udc2, java.lang.Object udc3) {
        return _brandMasterLocalService.executeUpdateQuery(nmSalesList, udc1,
            udc2, udc3);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public BrandMasterLocalService getWrappedBrandMasterLocalService() {
        return _brandMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedBrandMasterLocalService(
        BrandMasterLocalService brandMasterLocalService) {
        _brandMasterLocalService = brandMasterLocalService;
    }

    @Override
    public BrandMasterLocalService getWrappedService() {
        return _brandMasterLocalService;
    }

    @Override
    public void setWrappedService(
        BrandMasterLocalService brandMasterLocalService) {
        _brandMasterLocalService = brandMasterLocalService;
    }
}
