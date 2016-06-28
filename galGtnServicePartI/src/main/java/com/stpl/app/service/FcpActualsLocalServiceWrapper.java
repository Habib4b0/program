package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link FcpActualsLocalService}.
 *
 * @author
 * @see FcpActualsLocalService
 * @generated
 */
public class FcpActualsLocalServiceWrapper implements FcpActualsLocalService,
    ServiceWrapper<FcpActualsLocalService> {
    private FcpActualsLocalService _fcpActualsLocalService;

    public FcpActualsLocalServiceWrapper(
        FcpActualsLocalService fcpActualsLocalService) {
        _fcpActualsLocalService = fcpActualsLocalService;
    }

    /**
    * Adds the fcp actuals to the database. Also notifies the appropriate model listeners.
    *
    * @param fcpActuals the fcp actuals
    * @return the fcp actuals that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.FcpActuals addFcpActuals(
        com.stpl.app.model.FcpActuals fcpActuals)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _fcpActualsLocalService.addFcpActuals(fcpActuals);
    }

    /**
    * Creates a new fcp actuals with the primary key. Does not add the fcp actuals to the database.
    *
    * @param fcpActualsPK the primary key for the new fcp actuals
    * @return the new fcp actuals
    */
    @Override
    public com.stpl.app.model.FcpActuals createFcpActuals(
        com.stpl.app.service.persistence.FcpActualsPK fcpActualsPK) {
        return _fcpActualsLocalService.createFcpActuals(fcpActualsPK);
    }

    /**
    * Deletes the fcp actuals with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param fcpActualsPK the primary key of the fcp actuals
    * @return the fcp actuals that was removed
    * @throws PortalException if a fcp actuals with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.FcpActuals deleteFcpActuals(
        com.stpl.app.service.persistence.FcpActualsPK fcpActualsPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _fcpActualsLocalService.deleteFcpActuals(fcpActualsPK);
    }

    /**
    * Deletes the fcp actuals from the database. Also notifies the appropriate model listeners.
    *
    * @param fcpActuals the fcp actuals
    * @return the fcp actuals that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.FcpActuals deleteFcpActuals(
        com.stpl.app.model.FcpActuals fcpActuals)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _fcpActualsLocalService.deleteFcpActuals(fcpActuals);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _fcpActualsLocalService.dynamicQuery();
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
        return _fcpActualsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FcpActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _fcpActualsLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FcpActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _fcpActualsLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _fcpActualsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _fcpActualsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.FcpActuals fetchFcpActuals(
        com.stpl.app.service.persistence.FcpActualsPK fcpActualsPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _fcpActualsLocalService.fetchFcpActuals(fcpActualsPK);
    }

    /**
    * Returns the fcp actuals with the primary key.
    *
    * @param fcpActualsPK the primary key of the fcp actuals
    * @return the fcp actuals
    * @throws PortalException if a fcp actuals with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.FcpActuals getFcpActuals(
        com.stpl.app.service.persistence.FcpActualsPK fcpActualsPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _fcpActualsLocalService.getFcpActuals(fcpActualsPK);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _fcpActualsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the fcp actualses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FcpActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of fcp actualses
    * @param end the upper bound of the range of fcp actualses (not inclusive)
    * @return the range of fcp actualses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.FcpActuals> getFcpActualses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _fcpActualsLocalService.getFcpActualses(start, end);
    }

    /**
    * Returns the number of fcp actualses.
    *
    * @return the number of fcp actualses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getFcpActualsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _fcpActualsLocalService.getFcpActualsesCount();
    }

    /**
    * Updates the fcp actuals in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param fcpActuals the fcp actuals
    * @return the fcp actuals that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.FcpActuals updateFcpActuals(
        com.stpl.app.model.FcpActuals fcpActuals)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _fcpActualsLocalService.updateFcpActuals(fcpActuals);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _fcpActualsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _fcpActualsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _fcpActualsLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    @Override
    public java.lang.Object executeSelectQuery(java.lang.String query) {
        return _fcpActualsLocalService.executeSelectQuery(query);
    }

    @Override
    public java.lang.Object executeBulkUpdateQuery(java.lang.String query) {
        return _fcpActualsLocalService.executeBulkUpdateQuery(query);
    }

    @Override
    public java.lang.Object executeUpdateQuery(
        java.util.List<java.lang.StringBuilder> queryList) {
        return _fcpActualsLocalService.executeUpdateQuery(queryList);
    }

    @Override
    public java.lang.Object executeUpdateQuery(java.lang.String query) {
        return _fcpActualsLocalService.executeUpdateQuery(query);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public FcpActualsLocalService getWrappedFcpActualsLocalService() {
        return _fcpActualsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedFcpActualsLocalService(
        FcpActualsLocalService fcpActualsLocalService) {
        _fcpActualsLocalService = fcpActualsLocalService;
    }

    @Override
    public FcpActualsLocalService getWrappedService() {
        return _fcpActualsLocalService;
    }

    @Override
    public void setWrappedService(FcpActualsLocalService fcpActualsLocalService) {
        _fcpActualsLocalService = fcpActualsLocalService;
    }
}
