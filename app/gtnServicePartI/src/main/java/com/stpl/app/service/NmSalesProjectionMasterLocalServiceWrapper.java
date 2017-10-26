package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link NmSalesProjectionMasterLocalService}.
 *
 * @author
 * @see NmSalesProjectionMasterLocalService
 * @generated
 */
public class NmSalesProjectionMasterLocalServiceWrapper
    implements NmSalesProjectionMasterLocalService,
        ServiceWrapper<NmSalesProjectionMasterLocalService> {
    private NmSalesProjectionMasterLocalService _nmSalesProjectionMasterLocalService;

    public NmSalesProjectionMasterLocalServiceWrapper(
        NmSalesProjectionMasterLocalService nmSalesProjectionMasterLocalService) {
        _nmSalesProjectionMasterLocalService = nmSalesProjectionMasterLocalService;
    }

    /**
    * Adds the nm sales projection master to the database. Also notifies the appropriate model listeners.
    *
    * @param nmSalesProjectionMaster the nm sales projection master
    * @return the nm sales projection master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmSalesProjectionMaster addNmSalesProjectionMaster(
        com.stpl.app.model.NmSalesProjectionMaster nmSalesProjectionMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmSalesProjectionMasterLocalService.addNmSalesProjectionMaster(nmSalesProjectionMaster);
    }

    /**
    * Creates a new nm sales projection master with the primary key. Does not add the nm sales projection master to the database.
    *
    * @param projectionDetailsSid the primary key for the new nm sales projection master
    * @return the new nm sales projection master
    */
    @Override
    public com.stpl.app.model.NmSalesProjectionMaster createNmSalesProjectionMaster(
        int projectionDetailsSid) {
        return _nmSalesProjectionMasterLocalService.createNmSalesProjectionMaster(projectionDetailsSid);
    }

    /**
    * Deletes the nm sales projection master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param projectionDetailsSid the primary key of the nm sales projection master
    * @return the nm sales projection master that was removed
    * @throws PortalException if a nm sales projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmSalesProjectionMaster deleteNmSalesProjectionMaster(
        int projectionDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _nmSalesProjectionMasterLocalService.deleteNmSalesProjectionMaster(projectionDetailsSid);
    }

    /**
    * Deletes the nm sales projection master from the database. Also notifies the appropriate model listeners.
    *
    * @param nmSalesProjectionMaster the nm sales projection master
    * @return the nm sales projection master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmSalesProjectionMaster deleteNmSalesProjectionMaster(
        com.stpl.app.model.NmSalesProjectionMaster nmSalesProjectionMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmSalesProjectionMasterLocalService.deleteNmSalesProjectionMaster(nmSalesProjectionMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _nmSalesProjectionMasterLocalService.dynamicQuery();
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
        return _nmSalesProjectionMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _nmSalesProjectionMasterLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _nmSalesProjectionMasterLocalService.dynamicQuery(dynamicQuery,
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
        return _nmSalesProjectionMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _nmSalesProjectionMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.NmSalesProjectionMaster fetchNmSalesProjectionMaster(
        int projectionDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmSalesProjectionMasterLocalService.fetchNmSalesProjectionMaster(projectionDetailsSid);
    }

    /**
    * Returns the nm sales projection master with the primary key.
    *
    * @param projectionDetailsSid the primary key of the nm sales projection master
    * @return the nm sales projection master
    * @throws PortalException if a nm sales projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmSalesProjectionMaster getNmSalesProjectionMaster(
        int projectionDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _nmSalesProjectionMasterLocalService.getNmSalesProjectionMaster(projectionDetailsSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _nmSalesProjectionMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the nm sales projection masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of nm sales projection masters
    * @param end the upper bound of the range of nm sales projection masters (not inclusive)
    * @return the range of nm sales projection masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.NmSalesProjectionMaster> getNmSalesProjectionMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmSalesProjectionMasterLocalService.getNmSalesProjectionMasters(start,
            end);
    }

    /**
    * Returns the number of nm sales projection masters.
    *
    * @return the number of nm sales projection masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getNmSalesProjectionMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmSalesProjectionMasterLocalService.getNmSalesProjectionMastersCount();
    }

    /**
    * Updates the nm sales projection master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param nmSalesProjectionMaster the nm sales projection master
    * @return the nm sales projection master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmSalesProjectionMaster updateNmSalesProjectionMaster(
        com.stpl.app.model.NmSalesProjectionMaster nmSalesProjectionMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmSalesProjectionMasterLocalService.updateNmSalesProjectionMaster(nmSalesProjectionMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _nmSalesProjectionMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _nmSalesProjectionMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _nmSalesProjectionMasterLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    @Override
    public java.lang.Object executeSelectQuery(java.lang.String query,
        java.lang.Object udc1, java.lang.Object udc2) {
        return _nmSalesProjectionMasterLocalService.executeSelectQuery(query,
            udc1, udc2);
    }

    @Override
    public java.lang.Object executeBulkUpdateQuery(java.lang.String query,
        java.lang.Object udc1, java.lang.Object udc2) {
        return _nmSalesProjectionMasterLocalService.executeBulkUpdateQuery(query,
            udc1, udc2);
    }

    @Override
    public java.lang.Object executeUpdateQuery(java.util.List<?> nmSalesList,
        java.lang.Object udc1, java.lang.Object udc2, java.lang.Object udc3) {
        return _nmSalesProjectionMasterLocalService.executeUpdateQuery(nmSalesList,
            udc1, udc2, udc3);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public NmSalesProjectionMasterLocalService getWrappedNmSalesProjectionMasterLocalService() {
        return _nmSalesProjectionMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedNmSalesProjectionMasterLocalService(
        NmSalesProjectionMasterLocalService nmSalesProjectionMasterLocalService) {
        _nmSalesProjectionMasterLocalService = nmSalesProjectionMasterLocalService;
    }

    @Override
    public NmSalesProjectionMasterLocalService getWrappedService() {
        return _nmSalesProjectionMasterLocalService;
    }

    @Override
    public void setWrappedService(
        NmSalesProjectionMasterLocalService nmSalesProjectionMasterLocalService) {
        _nmSalesProjectionMasterLocalService = nmSalesProjectionMasterLocalService;
    }
}
