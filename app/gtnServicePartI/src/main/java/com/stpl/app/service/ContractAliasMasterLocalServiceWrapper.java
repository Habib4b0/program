package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ContractAliasMasterLocalService}.
 *
 * @author
 * @see ContractAliasMasterLocalService
 * @generated
 */
public class ContractAliasMasterLocalServiceWrapper
    implements ContractAliasMasterLocalService,
        ServiceWrapper<ContractAliasMasterLocalService> {
    private ContractAliasMasterLocalService _contractAliasMasterLocalService;

    public ContractAliasMasterLocalServiceWrapper(
        ContractAliasMasterLocalService contractAliasMasterLocalService) {
        _contractAliasMasterLocalService = contractAliasMasterLocalService;
    }

    /**
    * Adds the contract alias master to the database. Also notifies the appropriate model listeners.
    *
    * @param contractAliasMaster the contract alias master
    * @return the contract alias master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ContractAliasMaster addContractAliasMaster(
        com.stpl.app.model.ContractAliasMaster contractAliasMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _contractAliasMasterLocalService.addContractAliasMaster(contractAliasMaster);
    }

    /**
    * Creates a new contract alias master with the primary key. Does not add the contract alias master to the database.
    *
    * @param contractAliasMasterSid the primary key for the new contract alias master
    * @return the new contract alias master
    */
    @Override
    public com.stpl.app.model.ContractAliasMaster createContractAliasMaster(
        int contractAliasMasterSid) {
        return _contractAliasMasterLocalService.createContractAliasMaster(contractAliasMasterSid);
    }

    /**
    * Deletes the contract alias master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param contractAliasMasterSid the primary key of the contract alias master
    * @return the contract alias master that was removed
    * @throws PortalException if a contract alias master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ContractAliasMaster deleteContractAliasMaster(
        int contractAliasMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _contractAliasMasterLocalService.deleteContractAliasMaster(contractAliasMasterSid);
    }

    /**
    * Deletes the contract alias master from the database. Also notifies the appropriate model listeners.
    *
    * @param contractAliasMaster the contract alias master
    * @return the contract alias master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ContractAliasMaster deleteContractAliasMaster(
        com.stpl.app.model.ContractAliasMaster contractAliasMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _contractAliasMasterLocalService.deleteContractAliasMaster(contractAliasMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _contractAliasMasterLocalService.dynamicQuery();
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
        return _contractAliasMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ContractAliasMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _contractAliasMasterLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ContractAliasMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _contractAliasMasterLocalService.dynamicQuery(dynamicQuery,
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
        return _contractAliasMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _contractAliasMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.ContractAliasMaster fetchContractAliasMaster(
        int contractAliasMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _contractAliasMasterLocalService.fetchContractAliasMaster(contractAliasMasterSid);
    }

    /**
    * Returns the contract alias master with the primary key.
    *
    * @param contractAliasMasterSid the primary key of the contract alias master
    * @return the contract alias master
    * @throws PortalException if a contract alias master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ContractAliasMaster getContractAliasMaster(
        int contractAliasMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _contractAliasMasterLocalService.getContractAliasMaster(contractAliasMasterSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _contractAliasMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the contract alias masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ContractAliasMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of contract alias masters
    * @param end the upper bound of the range of contract alias masters (not inclusive)
    * @return the range of contract alias masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.ContractAliasMaster> getContractAliasMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _contractAliasMasterLocalService.getContractAliasMasters(start,
            end);
    }

    /**
    * Returns the number of contract alias masters.
    *
    * @return the number of contract alias masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getContractAliasMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _contractAliasMasterLocalService.getContractAliasMastersCount();
    }

    /**
    * Updates the contract alias master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param contractAliasMaster the contract alias master
    * @return the contract alias master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ContractAliasMaster updateContractAliasMaster(
        com.stpl.app.model.ContractAliasMaster contractAliasMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _contractAliasMasterLocalService.updateContractAliasMaster(contractAliasMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _contractAliasMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _contractAliasMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _contractAliasMasterLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    @Override
    public java.util.List<com.stpl.app.model.ContractAliasMaster> findByContractSystemId(
        int contractSystemId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _contractAliasMasterLocalService.findByContractSystemId(contractSystemId);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ContractAliasMasterLocalService getWrappedContractAliasMasterLocalService() {
        return _contractAliasMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedContractAliasMasterLocalService(
        ContractAliasMasterLocalService contractAliasMasterLocalService) {
        _contractAliasMasterLocalService = contractAliasMasterLocalService;
    }

    @Override
    public ContractAliasMasterLocalService getWrappedService() {
        return _contractAliasMasterLocalService;
    }

    @Override
    public void setWrappedService(
        ContractAliasMasterLocalService contractAliasMasterLocalService) {
        _contractAliasMasterLocalService = contractAliasMasterLocalService;
    }
}
