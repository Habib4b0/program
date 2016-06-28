package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link StNmPpaProjectionMasterLocalService}.
 *
 * @author
 * @see StNmPpaProjectionMasterLocalService
 * @generated
 */
public class StNmPpaProjectionMasterLocalServiceWrapper
    implements StNmPpaProjectionMasterLocalService,
        ServiceWrapper<StNmPpaProjectionMasterLocalService> {
    private StNmPpaProjectionMasterLocalService _stNmPpaProjectionMasterLocalService;

    public StNmPpaProjectionMasterLocalServiceWrapper(
        StNmPpaProjectionMasterLocalService stNmPpaProjectionMasterLocalService) {
        _stNmPpaProjectionMasterLocalService = stNmPpaProjectionMasterLocalService;
    }

    /**
    * Adds the st nm ppa projection master to the database. Also notifies the appropriate model listeners.
    *
    * @param stNmPpaProjectionMaster the st nm ppa projection master
    * @return the st nm ppa projection master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StNmPpaProjectionMaster addStNmPpaProjectionMaster(
        com.stpl.app.model.StNmPpaProjectionMaster stNmPpaProjectionMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stNmPpaProjectionMasterLocalService.addStNmPpaProjectionMaster(stNmPpaProjectionMaster);
    }

    /**
    * Creates a new st nm ppa projection master with the primary key. Does not add the st nm ppa projection master to the database.
    *
    * @param stNmPpaProjectionMasterPK the primary key for the new st nm ppa projection master
    * @return the new st nm ppa projection master
    */
    @Override
    public com.stpl.app.model.StNmPpaProjectionMaster createStNmPpaProjectionMaster(
        com.stpl.app.service.persistence.StNmPpaProjectionMasterPK stNmPpaProjectionMasterPK) {
        return _stNmPpaProjectionMasterLocalService.createStNmPpaProjectionMaster(stNmPpaProjectionMasterPK);
    }

    /**
    * Deletes the st nm ppa projection master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stNmPpaProjectionMasterPK the primary key of the st nm ppa projection master
    * @return the st nm ppa projection master that was removed
    * @throws PortalException if a st nm ppa projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StNmPpaProjectionMaster deleteStNmPpaProjectionMaster(
        com.stpl.app.service.persistence.StNmPpaProjectionMasterPK stNmPpaProjectionMasterPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stNmPpaProjectionMasterLocalService.deleteStNmPpaProjectionMaster(stNmPpaProjectionMasterPK);
    }

    /**
    * Deletes the st nm ppa projection master from the database. Also notifies the appropriate model listeners.
    *
    * @param stNmPpaProjectionMaster the st nm ppa projection master
    * @return the st nm ppa projection master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StNmPpaProjectionMaster deleteStNmPpaProjectionMaster(
        com.stpl.app.model.StNmPpaProjectionMaster stNmPpaProjectionMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stNmPpaProjectionMasterLocalService.deleteStNmPpaProjectionMaster(stNmPpaProjectionMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _stNmPpaProjectionMasterLocalService.dynamicQuery();
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
        return _stNmPpaProjectionMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _stNmPpaProjectionMasterLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _stNmPpaProjectionMasterLocalService.dynamicQuery(dynamicQuery,
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
        return _stNmPpaProjectionMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _stNmPpaProjectionMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.StNmPpaProjectionMaster fetchStNmPpaProjectionMaster(
        com.stpl.app.service.persistence.StNmPpaProjectionMasterPK stNmPpaProjectionMasterPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stNmPpaProjectionMasterLocalService.fetchStNmPpaProjectionMaster(stNmPpaProjectionMasterPK);
    }

    /**
    * Returns the st nm ppa projection master with the primary key.
    *
    * @param stNmPpaProjectionMasterPK the primary key of the st nm ppa projection master
    * @return the st nm ppa projection master
    * @throws PortalException if a st nm ppa projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StNmPpaProjectionMaster getStNmPpaProjectionMaster(
        com.stpl.app.service.persistence.StNmPpaProjectionMasterPK stNmPpaProjectionMasterPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stNmPpaProjectionMasterLocalService.getStNmPpaProjectionMaster(stNmPpaProjectionMasterPK);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stNmPpaProjectionMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the st nm ppa projection masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st nm ppa projection masters
    * @param end the upper bound of the range of st nm ppa projection masters (not inclusive)
    * @return the range of st nm ppa projection masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.StNmPpaProjectionMaster> getStNmPpaProjectionMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stNmPpaProjectionMasterLocalService.getStNmPpaProjectionMasters(start,
            end);
    }

    /**
    * Returns the number of st nm ppa projection masters.
    *
    * @return the number of st nm ppa projection masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getStNmPpaProjectionMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stNmPpaProjectionMasterLocalService.getStNmPpaProjectionMastersCount();
    }

    /**
    * Updates the st nm ppa projection master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param stNmPpaProjectionMaster the st nm ppa projection master
    * @return the st nm ppa projection master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StNmPpaProjectionMaster updateStNmPpaProjectionMaster(
        com.stpl.app.model.StNmPpaProjectionMaster stNmPpaProjectionMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stNmPpaProjectionMasterLocalService.updateStNmPpaProjectionMaster(stNmPpaProjectionMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _stNmPpaProjectionMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _stNmPpaProjectionMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _stNmPpaProjectionMasterLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public StNmPpaProjectionMasterLocalService getWrappedStNmPpaProjectionMasterLocalService() {
        return _stNmPpaProjectionMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedStNmPpaProjectionMasterLocalService(
        StNmPpaProjectionMasterLocalService stNmPpaProjectionMasterLocalService) {
        _stNmPpaProjectionMasterLocalService = stNmPpaProjectionMasterLocalService;
    }

    @Override
    public StNmPpaProjectionMasterLocalService getWrappedService() {
        return _stNmPpaProjectionMasterLocalService;
    }

    @Override
    public void setWrappedService(
        StNmPpaProjectionMasterLocalService stNmPpaProjectionMasterLocalService) {
        _stNmPpaProjectionMasterLocalService = stNmPpaProjectionMasterLocalService;
    }
}
