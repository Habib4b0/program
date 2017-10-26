package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link IfpContractLocalService}.
 *
 * @author
 * @see IfpContractLocalService
 * @generated
 */
public class IfpContractLocalServiceWrapper implements IfpContractLocalService,
    ServiceWrapper<IfpContractLocalService> {
    private IfpContractLocalService _ifpContractLocalService;

    public IfpContractLocalServiceWrapper(
        IfpContractLocalService ifpContractLocalService) {
        _ifpContractLocalService = ifpContractLocalService;
    }

    /**
    * Adds the ifp contract to the database. Also notifies the appropriate model listeners.
    *
    * @param ifpContract the ifp contract
    * @return the ifp contract that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IfpContract addIfpContract(
        com.stpl.app.model.IfpContract ifpContract)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ifpContractLocalService.addIfpContract(ifpContract);
    }

    /**
    * Creates a new ifp contract with the primary key. Does not add the ifp contract to the database.
    *
    * @param ifpContractSid the primary key for the new ifp contract
    * @return the new ifp contract
    */
    @Override
    public com.stpl.app.model.IfpContract createIfpContract(int ifpContractSid) {
        return _ifpContractLocalService.createIfpContract(ifpContractSid);
    }

    /**
    * Deletes the ifp contract with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ifpContractSid the primary key of the ifp contract
    * @return the ifp contract that was removed
    * @throws PortalException if a ifp contract with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IfpContract deleteIfpContract(int ifpContractSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ifpContractLocalService.deleteIfpContract(ifpContractSid);
    }

    /**
    * Deletes the ifp contract from the database. Also notifies the appropriate model listeners.
    *
    * @param ifpContract the ifp contract
    * @return the ifp contract that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IfpContract deleteIfpContract(
        com.stpl.app.model.IfpContract ifpContract)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ifpContractLocalService.deleteIfpContract(ifpContract);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _ifpContractLocalService.dynamicQuery();
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
        return _ifpContractLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ifpContractLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ifpContractLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _ifpContractLocalService.dynamicQueryCount(dynamicQuery);
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
        return _ifpContractLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.IfpContract fetchIfpContract(int ifpContractSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ifpContractLocalService.fetchIfpContract(ifpContractSid);
    }

    /**
    * Returns the ifp contract with the primary key.
    *
    * @param ifpContractSid the primary key of the ifp contract
    * @return the ifp contract
    * @throws PortalException if a ifp contract with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IfpContract getIfpContract(int ifpContractSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ifpContractLocalService.getIfpContract(ifpContractSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ifpContractLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ifp contracts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ifp contracts
    * @param end the upper bound of the range of ifp contracts (not inclusive)
    * @return the range of ifp contracts
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.IfpContract> getIfpContracts(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ifpContractLocalService.getIfpContracts(start, end);
    }

    /**
    * Returns the number of ifp contracts.
    *
    * @return the number of ifp contracts
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getIfpContractsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ifpContractLocalService.getIfpContractsCount();
    }

    /**
    * Updates the ifp contract in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ifpContract the ifp contract
    * @return the ifp contract that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IfpContract updateIfpContract(
        com.stpl.app.model.IfpContract ifpContract)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ifpContractLocalService.updateIfpContract(ifpContract);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _ifpContractLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _ifpContractLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _ifpContractLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public IfpContractLocalService getWrappedIfpContractLocalService() {
        return _ifpContractLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedIfpContractLocalService(
        IfpContractLocalService ifpContractLocalService) {
        _ifpContractLocalService = ifpContractLocalService;
    }

    @Override
    public IfpContractLocalService getWrappedService() {
        return _ifpContractLocalService;
    }

    @Override
    public void setWrappedService(
        IfpContractLocalService ifpContractLocalService) {
        _ifpContractLocalService = ifpContractLocalService;
    }
}
