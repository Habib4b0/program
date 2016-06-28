package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link RebatePlanMasterLocalService}.
 *
 * @author
 * @see RebatePlanMasterLocalService
 * @generated
 */
public class RebatePlanMasterLocalServiceWrapper
    implements RebatePlanMasterLocalService,
        ServiceWrapper<RebatePlanMasterLocalService> {
    private RebatePlanMasterLocalService _rebatePlanMasterLocalService;

    public RebatePlanMasterLocalServiceWrapper(
        RebatePlanMasterLocalService rebatePlanMasterLocalService) {
        _rebatePlanMasterLocalService = rebatePlanMasterLocalService;
    }

    /**
    * Adds the rebate plan master to the database. Also notifies the appropriate model listeners.
    *
    * @param rebatePlanMaster the rebate plan master
    * @return the rebate plan master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.RebatePlanMaster addRebatePlanMaster(
        com.stpl.app.model.RebatePlanMaster rebatePlanMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _rebatePlanMasterLocalService.addRebatePlanMaster(rebatePlanMaster);
    }

    /**
    * Creates a new rebate plan master with the primary key. Does not add the rebate plan master to the database.
    *
    * @param rebatePlanMasterSid the primary key for the new rebate plan master
    * @return the new rebate plan master
    */
    @Override
    public com.stpl.app.model.RebatePlanMaster createRebatePlanMaster(
        int rebatePlanMasterSid) {
        return _rebatePlanMasterLocalService.createRebatePlanMaster(rebatePlanMasterSid);
    }

    /**
    * Deletes the rebate plan master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param rebatePlanMasterSid the primary key of the rebate plan master
    * @return the rebate plan master that was removed
    * @throws PortalException if a rebate plan master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.RebatePlanMaster deleteRebatePlanMaster(
        int rebatePlanMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _rebatePlanMasterLocalService.deleteRebatePlanMaster(rebatePlanMasterSid);
    }

    /**
    * Deletes the rebate plan master from the database. Also notifies the appropriate model listeners.
    *
    * @param rebatePlanMaster the rebate plan master
    * @return the rebate plan master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.RebatePlanMaster deleteRebatePlanMaster(
        com.stpl.app.model.RebatePlanMaster rebatePlanMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _rebatePlanMasterLocalService.deleteRebatePlanMaster(rebatePlanMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _rebatePlanMasterLocalService.dynamicQuery();
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
        return _rebatePlanMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _rebatePlanMasterLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _rebatePlanMasterLocalService.dynamicQuery(dynamicQuery, start,
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
        return _rebatePlanMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _rebatePlanMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.RebatePlanMaster fetchRebatePlanMaster(
        int rebatePlanMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _rebatePlanMasterLocalService.fetchRebatePlanMaster(rebatePlanMasterSid);
    }

    /**
    * Returns the rebate plan master with the primary key.
    *
    * @param rebatePlanMasterSid the primary key of the rebate plan master
    * @return the rebate plan master
    * @throws PortalException if a rebate plan master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.RebatePlanMaster getRebatePlanMaster(
        int rebatePlanMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _rebatePlanMasterLocalService.getRebatePlanMaster(rebatePlanMasterSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _rebatePlanMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the rebate plan masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of rebate plan masters
    * @param end the upper bound of the range of rebate plan masters (not inclusive)
    * @return the range of rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.RebatePlanMaster> getRebatePlanMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _rebatePlanMasterLocalService.getRebatePlanMasters(start, end);
    }

    /**
    * Returns the number of rebate plan masters.
    *
    * @return the number of rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getRebatePlanMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _rebatePlanMasterLocalService.getRebatePlanMastersCount();
    }

    /**
    * Updates the rebate plan master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param rebatePlanMaster the rebate plan master
    * @return the rebate plan master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.RebatePlanMaster updateRebatePlanMaster(
        com.stpl.app.model.RebatePlanMaster rebatePlanMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _rebatePlanMasterLocalService.updateRebatePlanMaster(rebatePlanMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _rebatePlanMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _rebatePlanMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _rebatePlanMasterLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public RebatePlanMasterLocalService getWrappedRebatePlanMasterLocalService() {
        return _rebatePlanMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedRebatePlanMasterLocalService(
        RebatePlanMasterLocalService rebatePlanMasterLocalService) {
        _rebatePlanMasterLocalService = rebatePlanMasterLocalService;
    }

    @Override
    public RebatePlanMasterLocalService getWrappedService() {
        return _rebatePlanMasterLocalService;
    }

    @Override
    public void setWrappedService(
        RebatePlanMasterLocalService rebatePlanMasterLocalService) {
        _rebatePlanMasterLocalService = rebatePlanMasterLocalService;
    }
}
