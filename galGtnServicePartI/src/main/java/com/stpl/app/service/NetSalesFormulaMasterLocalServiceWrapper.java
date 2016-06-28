package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link NetSalesFormulaMasterLocalService}.
 *
 * @author
 * @see NetSalesFormulaMasterLocalService
 * @generated
 */
public class NetSalesFormulaMasterLocalServiceWrapper
    implements NetSalesFormulaMasterLocalService,
        ServiceWrapper<NetSalesFormulaMasterLocalService> {
    private NetSalesFormulaMasterLocalService _netSalesFormulaMasterLocalService;

    public NetSalesFormulaMasterLocalServiceWrapper(
        NetSalesFormulaMasterLocalService netSalesFormulaMasterLocalService) {
        _netSalesFormulaMasterLocalService = netSalesFormulaMasterLocalService;
    }

    /**
    * Adds the net sales formula master to the database. Also notifies the appropriate model listeners.
    *
    * @param netSalesFormulaMaster the net sales formula master
    * @return the net sales formula master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NetSalesFormulaMaster addNetSalesFormulaMaster(
        com.stpl.app.model.NetSalesFormulaMaster netSalesFormulaMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _netSalesFormulaMasterLocalService.addNetSalesFormulaMaster(netSalesFormulaMaster);
    }

    /**
    * Creates a new net sales formula master with the primary key. Does not add the net sales formula master to the database.
    *
    * @param netSalesFormulaMasterSid the primary key for the new net sales formula master
    * @return the new net sales formula master
    */
    @Override
    public com.stpl.app.model.NetSalesFormulaMaster createNetSalesFormulaMaster(
        int netSalesFormulaMasterSid) {
        return _netSalesFormulaMasterLocalService.createNetSalesFormulaMaster(netSalesFormulaMasterSid);
    }

    /**
    * Deletes the net sales formula master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param netSalesFormulaMasterSid the primary key of the net sales formula master
    * @return the net sales formula master that was removed
    * @throws PortalException if a net sales formula master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NetSalesFormulaMaster deleteNetSalesFormulaMaster(
        int netSalesFormulaMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _netSalesFormulaMasterLocalService.deleteNetSalesFormulaMaster(netSalesFormulaMasterSid);
    }

    /**
    * Deletes the net sales formula master from the database. Also notifies the appropriate model listeners.
    *
    * @param netSalesFormulaMaster the net sales formula master
    * @return the net sales formula master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NetSalesFormulaMaster deleteNetSalesFormulaMaster(
        com.stpl.app.model.NetSalesFormulaMaster netSalesFormulaMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _netSalesFormulaMasterLocalService.deleteNetSalesFormulaMaster(netSalesFormulaMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _netSalesFormulaMasterLocalService.dynamicQuery();
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
        return _netSalesFormulaMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NetSalesFormulaMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _netSalesFormulaMasterLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NetSalesFormulaMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _netSalesFormulaMasterLocalService.dynamicQuery(dynamicQuery,
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
        return _netSalesFormulaMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _netSalesFormulaMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.NetSalesFormulaMaster fetchNetSalesFormulaMaster(
        int netSalesFormulaMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _netSalesFormulaMasterLocalService.fetchNetSalesFormulaMaster(netSalesFormulaMasterSid);
    }

    /**
    * Returns the net sales formula master with the primary key.
    *
    * @param netSalesFormulaMasterSid the primary key of the net sales formula master
    * @return the net sales formula master
    * @throws PortalException if a net sales formula master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NetSalesFormulaMaster getNetSalesFormulaMaster(
        int netSalesFormulaMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _netSalesFormulaMasterLocalService.getNetSalesFormulaMaster(netSalesFormulaMasterSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _netSalesFormulaMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the net sales formula masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NetSalesFormulaMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of net sales formula masters
    * @param end the upper bound of the range of net sales formula masters (not inclusive)
    * @return the range of net sales formula masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.NetSalesFormulaMaster> getNetSalesFormulaMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _netSalesFormulaMasterLocalService.getNetSalesFormulaMasters(start,
            end);
    }

    /**
    * Returns the number of net sales formula masters.
    *
    * @return the number of net sales formula masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getNetSalesFormulaMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _netSalesFormulaMasterLocalService.getNetSalesFormulaMastersCount();
    }

    /**
    * Updates the net sales formula master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param netSalesFormulaMaster the net sales formula master
    * @return the net sales formula master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NetSalesFormulaMaster updateNetSalesFormulaMaster(
        com.stpl.app.model.NetSalesFormulaMaster netSalesFormulaMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _netSalesFormulaMasterLocalService.updateNetSalesFormulaMaster(netSalesFormulaMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _netSalesFormulaMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _netSalesFormulaMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _netSalesFormulaMasterLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public NetSalesFormulaMasterLocalService getWrappedNetSalesFormulaMasterLocalService() {
        return _netSalesFormulaMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedNetSalesFormulaMasterLocalService(
        NetSalesFormulaMasterLocalService netSalesFormulaMasterLocalService) {
        _netSalesFormulaMasterLocalService = netSalesFormulaMasterLocalService;
    }

    @Override
    public NetSalesFormulaMasterLocalService getWrappedService() {
        return _netSalesFormulaMasterLocalService;
    }

    @Override
    public void setWrappedService(
        NetSalesFormulaMasterLocalService netSalesFormulaMasterLocalService) {
        _netSalesFormulaMasterLocalService = netSalesFormulaMasterLocalService;
    }
}
