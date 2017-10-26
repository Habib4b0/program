package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link StChSalesProjectionMasterLocalService}.
 *
 * @author
 * @see StChSalesProjectionMasterLocalService
 * @generated
 */
public class StChSalesProjectionMasterLocalServiceWrapper
    implements StChSalesProjectionMasterLocalService,
        ServiceWrapper<StChSalesProjectionMasterLocalService> {
    private StChSalesProjectionMasterLocalService _stChSalesProjectionMasterLocalService;

    public StChSalesProjectionMasterLocalServiceWrapper(
        StChSalesProjectionMasterLocalService stChSalesProjectionMasterLocalService) {
        _stChSalesProjectionMasterLocalService = stChSalesProjectionMasterLocalService;
    }

    /**
    * Adds the st ch sales projection master to the database. Also notifies the appropriate model listeners.
    *
    * @param stChSalesProjectionMaster the st ch sales projection master
    * @return the st ch sales projection master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StChSalesProjectionMaster addStChSalesProjectionMaster(
        com.stpl.app.model.StChSalesProjectionMaster stChSalesProjectionMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stChSalesProjectionMasterLocalService.addStChSalesProjectionMaster(stChSalesProjectionMaster);
    }

    /**
    * Creates a new st ch sales projection master with the primary key. Does not add the st ch sales projection master to the database.
    *
    * @param stChSalesProjectionMasterPK the primary key for the new st ch sales projection master
    * @return the new st ch sales projection master
    */
    @Override
    public com.stpl.app.model.StChSalesProjectionMaster createStChSalesProjectionMaster(
        com.stpl.app.service.persistence.StChSalesProjectionMasterPK stChSalesProjectionMasterPK) {
        return _stChSalesProjectionMasterLocalService.createStChSalesProjectionMaster(stChSalesProjectionMasterPK);
    }

    /**
    * Deletes the st ch sales projection master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stChSalesProjectionMasterPK the primary key of the st ch sales projection master
    * @return the st ch sales projection master that was removed
    * @throws PortalException if a st ch sales projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StChSalesProjectionMaster deleteStChSalesProjectionMaster(
        com.stpl.app.service.persistence.StChSalesProjectionMasterPK stChSalesProjectionMasterPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stChSalesProjectionMasterLocalService.deleteStChSalesProjectionMaster(stChSalesProjectionMasterPK);
    }

    /**
    * Deletes the st ch sales projection master from the database. Also notifies the appropriate model listeners.
    *
    * @param stChSalesProjectionMaster the st ch sales projection master
    * @return the st ch sales projection master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StChSalesProjectionMaster deleteStChSalesProjectionMaster(
        com.stpl.app.model.StChSalesProjectionMaster stChSalesProjectionMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stChSalesProjectionMasterLocalService.deleteStChSalesProjectionMaster(stChSalesProjectionMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _stChSalesProjectionMasterLocalService.dynamicQuery();
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
        return _stChSalesProjectionMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _stChSalesProjectionMasterLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _stChSalesProjectionMasterLocalService.dynamicQuery(dynamicQuery,
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
        return _stChSalesProjectionMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _stChSalesProjectionMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.StChSalesProjectionMaster fetchStChSalesProjectionMaster(
        com.stpl.app.service.persistence.StChSalesProjectionMasterPK stChSalesProjectionMasterPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stChSalesProjectionMasterLocalService.fetchStChSalesProjectionMaster(stChSalesProjectionMasterPK);
    }

    /**
    * Returns the st ch sales projection master with the primary key.
    *
    * @param stChSalesProjectionMasterPK the primary key of the st ch sales projection master
    * @return the st ch sales projection master
    * @throws PortalException if a st ch sales projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StChSalesProjectionMaster getStChSalesProjectionMaster(
        com.stpl.app.service.persistence.StChSalesProjectionMasterPK stChSalesProjectionMasterPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stChSalesProjectionMasterLocalService.getStChSalesProjectionMaster(stChSalesProjectionMasterPK);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stChSalesProjectionMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the st ch sales projection masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st ch sales projection masters
    * @param end the upper bound of the range of st ch sales projection masters (not inclusive)
    * @return the range of st ch sales projection masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.StChSalesProjectionMaster> getStChSalesProjectionMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stChSalesProjectionMasterLocalService.getStChSalesProjectionMasters(start,
            end);
    }

    /**
    * Returns the number of st ch sales projection masters.
    *
    * @return the number of st ch sales projection masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getStChSalesProjectionMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stChSalesProjectionMasterLocalService.getStChSalesProjectionMastersCount();
    }

    /**
    * Updates the st ch sales projection master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param stChSalesProjectionMaster the st ch sales projection master
    * @return the st ch sales projection master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StChSalesProjectionMaster updateStChSalesProjectionMaster(
        com.stpl.app.model.StChSalesProjectionMaster stChSalesProjectionMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stChSalesProjectionMasterLocalService.updateStChSalesProjectionMaster(stChSalesProjectionMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _stChSalesProjectionMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _stChSalesProjectionMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _stChSalesProjectionMasterLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    @Override
    public java.util.List executeQuery(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return _stChSalesProjectionMasterLocalService.executeQuery(parameters);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public StChSalesProjectionMasterLocalService getWrappedStChSalesProjectionMasterLocalService() {
        return _stChSalesProjectionMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedStChSalesProjectionMasterLocalService(
        StChSalesProjectionMasterLocalService stChSalesProjectionMasterLocalService) {
        _stChSalesProjectionMasterLocalService = stChSalesProjectionMasterLocalService;
    }

    @Override
    public StChSalesProjectionMasterLocalService getWrappedService() {
        return _stChSalesProjectionMasterLocalService;
    }

    @Override
    public void setWrappedService(
        StChSalesProjectionMasterLocalService stChSalesProjectionMasterLocalService) {
        _stChSalesProjectionMasterLocalService = stChSalesProjectionMasterLocalService;
    }
}
