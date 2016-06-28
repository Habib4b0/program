package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LotMasterLocalService}.
 *
 * @author
 * @see LotMasterLocalService
 * @generated
 */
public class LotMasterLocalServiceWrapper implements LotMasterLocalService,
    ServiceWrapper<LotMasterLocalService> {
    private LotMasterLocalService _lotMasterLocalService;

    public LotMasterLocalServiceWrapper(
        LotMasterLocalService lotMasterLocalService) {
        _lotMasterLocalService = lotMasterLocalService;
    }

    /**
    * Adds the lot master to the database. Also notifies the appropriate model listeners.
    *
    * @param lotMaster the lot master
    * @return the lot master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.LotMaster addLotMaster(
        com.stpl.app.model.LotMaster lotMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _lotMasterLocalService.addLotMaster(lotMaster);
    }

    /**
    * Creates a new lot master with the primary key. Does not add the lot master to the database.
    *
    * @param lotMasterSid the primary key for the new lot master
    * @return the new lot master
    */
    @Override
    public com.stpl.app.model.LotMaster createLotMaster(int lotMasterSid) {
        return _lotMasterLocalService.createLotMaster(lotMasterSid);
    }

    /**
    * Deletes the lot master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param lotMasterSid the primary key of the lot master
    * @return the lot master that was removed
    * @throws PortalException if a lot master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.LotMaster deleteLotMaster(int lotMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _lotMasterLocalService.deleteLotMaster(lotMasterSid);
    }

    /**
    * Deletes the lot master from the database. Also notifies the appropriate model listeners.
    *
    * @param lotMaster the lot master
    * @return the lot master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.LotMaster deleteLotMaster(
        com.stpl.app.model.LotMaster lotMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _lotMasterLocalService.deleteLotMaster(lotMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _lotMasterLocalService.dynamicQuery();
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
        return _lotMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _lotMasterLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _lotMasterLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _lotMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _lotMasterLocalService.dynamicQueryCount(dynamicQuery, projection);
    }

    @Override
    public com.stpl.app.model.LotMaster fetchLotMaster(int lotMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _lotMasterLocalService.fetchLotMaster(lotMasterSid);
    }

    /**
    * Returns the lot master with the primary key.
    *
    * @param lotMasterSid the primary key of the lot master
    * @return the lot master
    * @throws PortalException if a lot master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.LotMaster getLotMaster(int lotMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _lotMasterLocalService.getLotMaster(lotMasterSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _lotMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the lot masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of lot masters
    * @param end the upper bound of the range of lot masters (not inclusive)
    * @return the range of lot masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.LotMaster> getLotMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _lotMasterLocalService.getLotMasters(start, end);
    }

    /**
    * Returns the number of lot masters.
    *
    * @return the number of lot masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getLotMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _lotMasterLocalService.getLotMastersCount();
    }

    /**
    * Updates the lot master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param lotMaster the lot master
    * @return the lot master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.LotMaster updateLotMaster(
        com.stpl.app.model.LotMaster lotMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _lotMasterLocalService.updateLotMaster(lotMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _lotMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _lotMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _lotMasterLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public LotMasterLocalService getWrappedLotMasterLocalService() {
        return _lotMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedLotMasterLocalService(
        LotMasterLocalService lotMasterLocalService) {
        _lotMasterLocalService = lotMasterLocalService;
    }

    @Override
    public LotMasterLocalService getWrappedService() {
        return _lotMasterLocalService;
    }

    @Override
    public void setWrappedService(LotMasterLocalService lotMasterLocalService) {
        _lotMasterLocalService = lotMasterLocalService;
    }
}
