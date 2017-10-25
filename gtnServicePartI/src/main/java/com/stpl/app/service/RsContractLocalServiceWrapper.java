package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link RsContractLocalService}.
 *
 * @author
 * @see RsContractLocalService
 * @generated
 */
public class RsContractLocalServiceWrapper implements RsContractLocalService,
    ServiceWrapper<RsContractLocalService> {
    private RsContractLocalService _rsContractLocalService;

    public RsContractLocalServiceWrapper(
        RsContractLocalService rsContractLocalService) {
        _rsContractLocalService = rsContractLocalService;
    }

    /**
    * Adds the rs contract to the database. Also notifies the appropriate model listeners.
    *
    * @param rsContract the rs contract
    * @return the rs contract that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.RsContract addRsContract(
        com.stpl.app.model.RsContract rsContract)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _rsContractLocalService.addRsContract(rsContract);
    }

    /**
    * Creates a new rs contract with the primary key. Does not add the rs contract to the database.
    *
    * @param rsContractSid the primary key for the new rs contract
    * @return the new rs contract
    */
    @Override
    public com.stpl.app.model.RsContract createRsContract(int rsContractSid) {
        return _rsContractLocalService.createRsContract(rsContractSid);
    }

    /**
    * Deletes the rs contract with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param rsContractSid the primary key of the rs contract
    * @return the rs contract that was removed
    * @throws PortalException if a rs contract with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.RsContract deleteRsContract(int rsContractSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _rsContractLocalService.deleteRsContract(rsContractSid);
    }

    /**
    * Deletes the rs contract from the database. Also notifies the appropriate model listeners.
    *
    * @param rsContract the rs contract
    * @return the rs contract that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.RsContract deleteRsContract(
        com.stpl.app.model.RsContract rsContract)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _rsContractLocalService.deleteRsContract(rsContract);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _rsContractLocalService.dynamicQuery();
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
        return _rsContractLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _rsContractLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _rsContractLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _rsContractLocalService.dynamicQueryCount(dynamicQuery);
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
        return _rsContractLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.RsContract fetchRsContract(int rsContractSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _rsContractLocalService.fetchRsContract(rsContractSid);
    }

    /**
    * Returns the rs contract with the primary key.
    *
    * @param rsContractSid the primary key of the rs contract
    * @return the rs contract
    * @throws PortalException if a rs contract with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.RsContract getRsContract(int rsContractSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _rsContractLocalService.getRsContract(rsContractSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _rsContractLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the rs contracts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of rs contracts
    * @param end the upper bound of the range of rs contracts (not inclusive)
    * @return the range of rs contracts
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.RsContract> getRsContracts(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _rsContractLocalService.getRsContracts(start, end);
    }

    /**
    * Returns the number of rs contracts.
    *
    * @return the number of rs contracts
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getRsContractsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _rsContractLocalService.getRsContractsCount();
    }

    /**
    * Updates the rs contract in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param rsContract the rs contract
    * @return the rs contract that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.RsContract updateRsContract(
        com.stpl.app.model.RsContract rsContract)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _rsContractLocalService.updateRsContract(rsContract);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _rsContractLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _rsContractLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _rsContractLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public RsContractLocalService getWrappedRsContractLocalService() {
        return _rsContractLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedRsContractLocalService(
        RsContractLocalService rsContractLocalService) {
        _rsContractLocalService = rsContractLocalService;
    }

    @Override
    public RsContractLocalService getWrappedService() {
        return _rsContractLocalService;
    }

    @Override
    public void setWrappedService(RsContractLocalService rsContractLocalService) {
        _rsContractLocalService = rsContractLocalService;
    }
}
