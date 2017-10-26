package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link StMSupplementalDiscMasterLocalService}.
 *
 * @author
 * @see StMSupplementalDiscMasterLocalService
 * @generated
 */
public class StMSupplementalDiscMasterLocalServiceWrapper
    implements StMSupplementalDiscMasterLocalService,
        ServiceWrapper<StMSupplementalDiscMasterLocalService> {
    private StMSupplementalDiscMasterLocalService _stMSupplementalDiscMasterLocalService;

    public StMSupplementalDiscMasterLocalServiceWrapper(
        StMSupplementalDiscMasterLocalService stMSupplementalDiscMasterLocalService) {
        _stMSupplementalDiscMasterLocalService = stMSupplementalDiscMasterLocalService;
    }

    /**
    * Adds the st m supplemental disc master to the database. Also notifies the appropriate model listeners.
    *
    * @param stMSupplementalDiscMaster the st m supplemental disc master
    * @return the st m supplemental disc master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StMSupplementalDiscMaster addStMSupplementalDiscMaster(
        com.stpl.app.model.StMSupplementalDiscMaster stMSupplementalDiscMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stMSupplementalDiscMasterLocalService.addStMSupplementalDiscMaster(stMSupplementalDiscMaster);
    }

    /**
    * Creates a new st m supplemental disc master with the primary key. Does not add the st m supplemental disc master to the database.
    *
    * @param stMSupplementalDiscMasterPK the primary key for the new st m supplemental disc master
    * @return the new st m supplemental disc master
    */
    @Override
    public com.stpl.app.model.StMSupplementalDiscMaster createStMSupplementalDiscMaster(
        com.stpl.app.service.persistence.StMSupplementalDiscMasterPK stMSupplementalDiscMasterPK) {
        return _stMSupplementalDiscMasterLocalService.createStMSupplementalDiscMaster(stMSupplementalDiscMasterPK);
    }

    /**
    * Deletes the st m supplemental disc master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stMSupplementalDiscMasterPK the primary key of the st m supplemental disc master
    * @return the st m supplemental disc master that was removed
    * @throws PortalException if a st m supplemental disc master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StMSupplementalDiscMaster deleteStMSupplementalDiscMaster(
        com.stpl.app.service.persistence.StMSupplementalDiscMasterPK stMSupplementalDiscMasterPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stMSupplementalDiscMasterLocalService.deleteStMSupplementalDiscMaster(stMSupplementalDiscMasterPK);
    }

    /**
    * Deletes the st m supplemental disc master from the database. Also notifies the appropriate model listeners.
    *
    * @param stMSupplementalDiscMaster the st m supplemental disc master
    * @return the st m supplemental disc master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StMSupplementalDiscMaster deleteStMSupplementalDiscMaster(
        com.stpl.app.model.StMSupplementalDiscMaster stMSupplementalDiscMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stMSupplementalDiscMasterLocalService.deleteStMSupplementalDiscMaster(stMSupplementalDiscMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _stMSupplementalDiscMasterLocalService.dynamicQuery();
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
        return _stMSupplementalDiscMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StMSupplementalDiscMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _stMSupplementalDiscMasterLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StMSupplementalDiscMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _stMSupplementalDiscMasterLocalService.dynamicQuery(dynamicQuery,
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
        return _stMSupplementalDiscMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _stMSupplementalDiscMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.StMSupplementalDiscMaster fetchStMSupplementalDiscMaster(
        com.stpl.app.service.persistence.StMSupplementalDiscMasterPK stMSupplementalDiscMasterPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stMSupplementalDiscMasterLocalService.fetchStMSupplementalDiscMaster(stMSupplementalDiscMasterPK);
    }

    /**
    * Returns the st m supplemental disc master with the primary key.
    *
    * @param stMSupplementalDiscMasterPK the primary key of the st m supplemental disc master
    * @return the st m supplemental disc master
    * @throws PortalException if a st m supplemental disc master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StMSupplementalDiscMaster getStMSupplementalDiscMaster(
        com.stpl.app.service.persistence.StMSupplementalDiscMasterPK stMSupplementalDiscMasterPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stMSupplementalDiscMasterLocalService.getStMSupplementalDiscMaster(stMSupplementalDiscMasterPK);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stMSupplementalDiscMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the st m supplemental disc masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StMSupplementalDiscMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st m supplemental disc masters
    * @param end the upper bound of the range of st m supplemental disc masters (not inclusive)
    * @return the range of st m supplemental disc masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.StMSupplementalDiscMaster> getStMSupplementalDiscMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stMSupplementalDiscMasterLocalService.getStMSupplementalDiscMasters(start,
            end);
    }

    /**
    * Returns the number of st m supplemental disc masters.
    *
    * @return the number of st m supplemental disc masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getStMSupplementalDiscMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stMSupplementalDiscMasterLocalService.getStMSupplementalDiscMastersCount();
    }

    /**
    * Updates the st m supplemental disc master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param stMSupplementalDiscMaster the st m supplemental disc master
    * @return the st m supplemental disc master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StMSupplementalDiscMaster updateStMSupplementalDiscMaster(
        com.stpl.app.model.StMSupplementalDiscMaster stMSupplementalDiscMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stMSupplementalDiscMasterLocalService.updateStMSupplementalDiscMaster(stMSupplementalDiscMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _stMSupplementalDiscMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _stMSupplementalDiscMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _stMSupplementalDiscMasterLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public StMSupplementalDiscMasterLocalService getWrappedStMSupplementalDiscMasterLocalService() {
        return _stMSupplementalDiscMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedStMSupplementalDiscMasterLocalService(
        StMSupplementalDiscMasterLocalService stMSupplementalDiscMasterLocalService) {
        _stMSupplementalDiscMasterLocalService = stMSupplementalDiscMasterLocalService;
    }

    @Override
    public StMSupplementalDiscMasterLocalService getWrappedService() {
        return _stMSupplementalDiscMasterLocalService;
    }

    @Override
    public void setWrappedService(
        StMSupplementalDiscMasterLocalService stMSupplementalDiscMasterLocalService) {
        _stMSupplementalDiscMasterLocalService = stMSupplementalDiscMasterLocalService;
    }
}
