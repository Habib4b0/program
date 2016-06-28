package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MSalesProjectionMasterLocalService}.
 *
 * @author
 * @see MSalesProjectionMasterLocalService
 * @generated
 */
public class MSalesProjectionMasterLocalServiceWrapper
    implements MSalesProjectionMasterLocalService,
        ServiceWrapper<MSalesProjectionMasterLocalService> {
    private MSalesProjectionMasterLocalService _mSalesProjectionMasterLocalService;

    public MSalesProjectionMasterLocalServiceWrapper(
        MSalesProjectionMasterLocalService mSalesProjectionMasterLocalService) {
        _mSalesProjectionMasterLocalService = mSalesProjectionMasterLocalService;
    }

    /**
    * Adds the m sales projection master to the database. Also notifies the appropriate model listeners.
    *
    * @param mSalesProjectionMaster the m sales projection master
    * @return the m sales projection master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MSalesProjectionMaster addMSalesProjectionMaster(
        com.stpl.app.model.MSalesProjectionMaster mSalesProjectionMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mSalesProjectionMasterLocalService.addMSalesProjectionMaster(mSalesProjectionMaster);
    }

    /**
    * Creates a new m sales projection master with the primary key. Does not add the m sales projection master to the database.
    *
    * @param projectionDetailsSid the primary key for the new m sales projection master
    * @return the new m sales projection master
    */
    @Override
    public com.stpl.app.model.MSalesProjectionMaster createMSalesProjectionMaster(
        int projectionDetailsSid) {
        return _mSalesProjectionMasterLocalService.createMSalesProjectionMaster(projectionDetailsSid);
    }

    /**
    * Deletes the m sales projection master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param projectionDetailsSid the primary key of the m sales projection master
    * @return the m sales projection master that was removed
    * @throws PortalException if a m sales projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MSalesProjectionMaster deleteMSalesProjectionMaster(
        int projectionDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _mSalesProjectionMasterLocalService.deleteMSalesProjectionMaster(projectionDetailsSid);
    }

    /**
    * Deletes the m sales projection master from the database. Also notifies the appropriate model listeners.
    *
    * @param mSalesProjectionMaster the m sales projection master
    * @return the m sales projection master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MSalesProjectionMaster deleteMSalesProjectionMaster(
        com.stpl.app.model.MSalesProjectionMaster mSalesProjectionMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mSalesProjectionMasterLocalService.deleteMSalesProjectionMaster(mSalesProjectionMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _mSalesProjectionMasterLocalService.dynamicQuery();
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
        return _mSalesProjectionMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _mSalesProjectionMasterLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _mSalesProjectionMasterLocalService.dynamicQuery(dynamicQuery,
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
        return _mSalesProjectionMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _mSalesProjectionMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.MSalesProjectionMaster fetchMSalesProjectionMaster(
        int projectionDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mSalesProjectionMasterLocalService.fetchMSalesProjectionMaster(projectionDetailsSid);
    }

    /**
    * Returns the m sales projection master with the primary key.
    *
    * @param projectionDetailsSid the primary key of the m sales projection master
    * @return the m sales projection master
    * @throws PortalException if a m sales projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MSalesProjectionMaster getMSalesProjectionMaster(
        int projectionDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _mSalesProjectionMasterLocalService.getMSalesProjectionMaster(projectionDetailsSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _mSalesProjectionMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the m sales projection masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of m sales projection masters
    * @param end the upper bound of the range of m sales projection masters (not inclusive)
    * @return the range of m sales projection masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.MSalesProjectionMaster> getMSalesProjectionMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mSalesProjectionMasterLocalService.getMSalesProjectionMasters(start,
            end);
    }

    /**
    * Returns the number of m sales projection masters.
    *
    * @return the number of m sales projection masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getMSalesProjectionMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mSalesProjectionMasterLocalService.getMSalesProjectionMastersCount();
    }

    /**
    * Updates the m sales projection master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param mSalesProjectionMaster the m sales projection master
    * @return the m sales projection master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MSalesProjectionMaster updateMSalesProjectionMaster(
        com.stpl.app.model.MSalesProjectionMaster mSalesProjectionMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mSalesProjectionMasterLocalService.updateMSalesProjectionMaster(mSalesProjectionMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _mSalesProjectionMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _mSalesProjectionMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _mSalesProjectionMasterLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    @Override
    public java.lang.Object executeSelectQuery(java.lang.String string,
        java.lang.Object o, java.lang.Object o1) {
        return _mSalesProjectionMasterLocalService.executeSelectQuery(string,
            o, o1);
    }

    @Override
    public java.util.List executeUpdateQuery(java.lang.String query,
        java.lang.Object obj1, java.lang.Object obj2) {
        return _mSalesProjectionMasterLocalService.executeUpdateQuery(query,
            obj1, obj2);
    }

    @Override
    public java.lang.Object executeUpdateQuery(
        java.util.List<java.lang.StringBuilder> queryList,
        java.lang.Object obj1, java.lang.Object obj2) {
        return _mSalesProjectionMasterLocalService.executeUpdateQuery(queryList,
            obj1, obj2);
    }

    @Override
    public java.util.List getAssumptionResult(java.util.List input,
        java.lang.String queryName) {
        return _mSalesProjectionMasterLocalService.getAssumptionResult(input,
            queryName);
    }

    @Override
    public java.lang.Object executeUpdateSQL(java.lang.String query,
        java.lang.Object obj1, java.lang.Object obj2) {
        return _mSalesProjectionMasterLocalService.executeUpdateSQL(query,
            obj1, obj2);
    }

    @Override
    public java.lang.Object executeUpdateQuery(java.util.List<?> nmSalesList,
        java.lang.Object udc1, java.lang.Object udc2, java.lang.Object udc3) {
        return _mSalesProjectionMasterLocalService.executeUpdateQuery(nmSalesList,
            udc1, udc2, udc3);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public MSalesProjectionMasterLocalService getWrappedMSalesProjectionMasterLocalService() {
        return _mSalesProjectionMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedMSalesProjectionMasterLocalService(
        MSalesProjectionMasterLocalService mSalesProjectionMasterLocalService) {
        _mSalesProjectionMasterLocalService = mSalesProjectionMasterLocalService;
    }

    @Override
    public MSalesProjectionMasterLocalService getWrappedService() {
        return _mSalesProjectionMasterLocalService;
    }

    @Override
    public void setWrappedService(
        MSalesProjectionMasterLocalService mSalesProjectionMasterLocalService) {
        _mSalesProjectionMasterLocalService = mSalesProjectionMasterLocalService;
    }
}
