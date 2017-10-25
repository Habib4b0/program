package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link BestPriceMasterLocalService}.
 *
 * @author
 * @see BestPriceMasterLocalService
 * @generated
 */
public class BestPriceMasterLocalServiceWrapper
    implements BestPriceMasterLocalService,
        ServiceWrapper<BestPriceMasterLocalService> {
    private BestPriceMasterLocalService _bestPriceMasterLocalService;

    public BestPriceMasterLocalServiceWrapper(
        BestPriceMasterLocalService bestPriceMasterLocalService) {
        _bestPriceMasterLocalService = bestPriceMasterLocalService;
    }

    /**
    * Adds the best price master to the database. Also notifies the appropriate model listeners.
    *
    * @param bestPriceMaster the best price master
    * @return the best price master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.BestPriceMaster addBestPriceMaster(
        com.stpl.app.model.BestPriceMaster bestPriceMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _bestPriceMasterLocalService.addBestPriceMaster(bestPriceMaster);
    }

    /**
    * Creates a new best price master with the primary key. Does not add the best price master to the database.
    *
    * @param bestPriceMasterSid the primary key for the new best price master
    * @return the new best price master
    */
    @Override
    public com.stpl.app.model.BestPriceMaster createBestPriceMaster(
        int bestPriceMasterSid) {
        return _bestPriceMasterLocalService.createBestPriceMaster(bestPriceMasterSid);
    }

    /**
    * Deletes the best price master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param bestPriceMasterSid the primary key of the best price master
    * @return the best price master that was removed
    * @throws PortalException if a best price master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.BestPriceMaster deleteBestPriceMaster(
        int bestPriceMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _bestPriceMasterLocalService.deleteBestPriceMaster(bestPriceMasterSid);
    }

    /**
    * Deletes the best price master from the database. Also notifies the appropriate model listeners.
    *
    * @param bestPriceMaster the best price master
    * @return the best price master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.BestPriceMaster deleteBestPriceMaster(
        com.stpl.app.model.BestPriceMaster bestPriceMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _bestPriceMasterLocalService.deleteBestPriceMaster(bestPriceMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _bestPriceMasterLocalService.dynamicQuery();
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
        return _bestPriceMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _bestPriceMasterLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _bestPriceMasterLocalService.dynamicQuery(dynamicQuery, start,
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
        return _bestPriceMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _bestPriceMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.BestPriceMaster fetchBestPriceMaster(
        int bestPriceMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _bestPriceMasterLocalService.fetchBestPriceMaster(bestPriceMasterSid);
    }

    /**
    * Returns the best price master with the primary key.
    *
    * @param bestPriceMasterSid the primary key of the best price master
    * @return the best price master
    * @throws PortalException if a best price master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.BestPriceMaster getBestPriceMaster(
        int bestPriceMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _bestPriceMasterLocalService.getBestPriceMaster(bestPriceMasterSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _bestPriceMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the best price masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of best price masters
    * @param end the upper bound of the range of best price masters (not inclusive)
    * @return the range of best price masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.BestPriceMaster> getBestPriceMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _bestPriceMasterLocalService.getBestPriceMasters(start, end);
    }

    /**
    * Returns the number of best price masters.
    *
    * @return the number of best price masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getBestPriceMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _bestPriceMasterLocalService.getBestPriceMastersCount();
    }

    /**
    * Updates the best price master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param bestPriceMaster the best price master
    * @return the best price master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.BestPriceMaster updateBestPriceMaster(
        com.stpl.app.model.BestPriceMaster bestPriceMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _bestPriceMasterLocalService.updateBestPriceMaster(bestPriceMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _bestPriceMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _bestPriceMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _bestPriceMasterLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public BestPriceMasterLocalService getWrappedBestPriceMasterLocalService() {
        return _bestPriceMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedBestPriceMasterLocalService(
        BestPriceMasterLocalService bestPriceMasterLocalService) {
        _bestPriceMasterLocalService = bestPriceMasterLocalService;
    }

    @Override
    public BestPriceMasterLocalService getWrappedService() {
        return _bestPriceMasterLocalService;
    }

    @Override
    public void setWrappedService(
        BestPriceMasterLocalService bestPriceMasterLocalService) {
        _bestPriceMasterLocalService = bestPriceMasterLocalService;
    }
}
