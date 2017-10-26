package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ContractMasterLocalService}.
 *
 * @author
 * @see ContractMasterLocalService
 * @generated
 */
public class ContractMasterLocalServiceWrapper
    implements ContractMasterLocalService,
        ServiceWrapper<ContractMasterLocalService> {
    private ContractMasterLocalService _contractMasterLocalService;

    public ContractMasterLocalServiceWrapper(
        ContractMasterLocalService contractMasterLocalService) {
        _contractMasterLocalService = contractMasterLocalService;
    }

    /**
    * Adds the contract master to the database. Also notifies the appropriate model listeners.
    *
    * @param contractMaster the contract master
    * @return the contract master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ContractMaster addContractMaster(
        com.stpl.app.model.ContractMaster contractMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _contractMasterLocalService.addContractMaster(contractMaster);
    }

    /**
    * Creates a new contract master with the primary key. Does not add the contract master to the database.
    *
    * @param contractMasterSid the primary key for the new contract master
    * @return the new contract master
    */
    @Override
    public com.stpl.app.model.ContractMaster createContractMaster(
        int contractMasterSid) {
        return _contractMasterLocalService.createContractMaster(contractMasterSid);
    }

    /**
    * Deletes the contract master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param contractMasterSid the primary key of the contract master
    * @return the contract master that was removed
    * @throws PortalException if a contract master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ContractMaster deleteContractMaster(
        int contractMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _contractMasterLocalService.deleteContractMaster(contractMasterSid);
    }

    /**
    * Deletes the contract master from the database. Also notifies the appropriate model listeners.
    *
    * @param contractMaster the contract master
    * @return the contract master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ContractMaster deleteContractMaster(
        com.stpl.app.model.ContractMaster contractMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _contractMasterLocalService.deleteContractMaster(contractMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _contractMasterLocalService.dynamicQuery();
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
        return _contractMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ContractMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _contractMasterLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ContractMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _contractMasterLocalService.dynamicQuery(dynamicQuery, start,
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
        return _contractMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _contractMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.ContractMaster fetchContractMaster(
        int contractMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _contractMasterLocalService.fetchContractMaster(contractMasterSid);
    }

    /**
    * Returns the contract master with the primary key.
    *
    * @param contractMasterSid the primary key of the contract master
    * @return the contract master
    * @throws PortalException if a contract master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ContractMaster getContractMaster(
        int contractMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _contractMasterLocalService.getContractMaster(contractMasterSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _contractMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the contract masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ContractMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of contract masters
    * @param end the upper bound of the range of contract masters (not inclusive)
    * @return the range of contract masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.ContractMaster> getContractMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _contractMasterLocalService.getContractMasters(start, end);
    }

    /**
    * Returns the number of contract masters.
    *
    * @return the number of contract masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getContractMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _contractMasterLocalService.getContractMastersCount();
    }

    /**
    * Updates the contract master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param contractMaster the contract master
    * @return the contract master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ContractMaster updateContractMaster(
        com.stpl.app.model.ContractMaster contractMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _contractMasterLocalService.updateContractMaster(contractMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _contractMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _contractMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _contractMasterLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    @Override
    public java.util.List getContractPriceInfo(int contractSystemId, int cfpId,
        int ifpId, int psId) {
        return _contractMasterLocalService.getContractPriceInfo(contractSystemId,
            cfpId, ifpId, psId);
    }

    @Override
    public java.util.List getTradingPartnerList(java.lang.String companyId,
        java.lang.String companyNo, java.lang.String companyName,
        int companyStatus, int companyType,
        java.util.Map<java.lang.String, java.lang.Object> filterMap, int start,
        int offset, java.lang.String column, java.lang.String orederBy) {
        return _contractMasterLocalService.getTradingPartnerList(companyId,
            companyNo, companyName, companyStatus, companyType, filterMap,
            start, offset, column, orederBy);
    }

    @Override
    public java.util.List getContractList(java.lang.String contractId,
        java.lang.String contractNo, java.lang.String contractName,
        int contractStatus, int contractType, java.lang.String tradeClass,
        int tradingPartner,
        java.util.Map<java.lang.String, java.lang.Object> filterMap,
        java.lang.String orderBy, java.lang.String column, int start,
        int offset, boolean isCount) {
        return _contractMasterLocalService.getContractList(contractId,
            contractNo, contractName, contractStatus, contractType, tradeClass,
            tradingPartner, filterMap, orderBy, column, start, offset, isCount);
    }

    @Override
    public java.util.List fetchFieldsForSecurity(java.lang.String moduleName,
        java.lang.String tabName, java.lang.Object obj1, java.lang.Object obj2,
        java.lang.Object obj3) {
        return _contractMasterLocalService.fetchFieldsForSecurity(moduleName,
            tabName, obj1, obj2, obj3);
    }

    @Override
    public java.util.List searchContractsForPromoteTp(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return _contractMasterLocalService.searchContractsForPromoteTp(parameters);
    }

    @Override
    public java.lang.Object executeSelectQueries(java.lang.String[] queries) {
        return _contractMasterLocalService.executeSelectQueries(queries);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ContractMasterLocalService getWrappedContractMasterLocalService() {
        return _contractMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedContractMasterLocalService(
        ContractMasterLocalService contractMasterLocalService) {
        _contractMasterLocalService = contractMasterLocalService;
    }

    @Override
    public ContractMasterLocalService getWrappedService() {
        return _contractMasterLocalService;
    }

    @Override
    public void setWrappedService(
        ContractMasterLocalService contractMasterLocalService) {
        _contractMasterLocalService = contractMasterLocalService;
    }
}
