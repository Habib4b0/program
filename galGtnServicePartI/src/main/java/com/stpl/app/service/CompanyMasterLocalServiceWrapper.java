package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CompanyMasterLocalService}.
 *
 * @author
 * @see CompanyMasterLocalService
 * @generated
 */
public class CompanyMasterLocalServiceWrapper
    implements CompanyMasterLocalService,
        ServiceWrapper<CompanyMasterLocalService> {
    private CompanyMasterLocalService _companyMasterLocalService;

    public CompanyMasterLocalServiceWrapper(
        CompanyMasterLocalService companyMasterLocalService) {
        _companyMasterLocalService = companyMasterLocalService;
    }

    /**
    * Adds the company master to the database. Also notifies the appropriate model listeners.
    *
    * @param companyMaster the company master
    * @return the company master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CompanyMaster addCompanyMaster(
        com.stpl.app.model.CompanyMaster companyMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _companyMasterLocalService.addCompanyMaster(companyMaster);
    }

    /**
    * Creates a new company master with the primary key. Does not add the company master to the database.
    *
    * @param companyMasterSid the primary key for the new company master
    * @return the new company master
    */
    @Override
    public com.stpl.app.model.CompanyMaster createCompanyMaster(
        int companyMasterSid) {
        return _companyMasterLocalService.createCompanyMaster(companyMasterSid);
    }

    /**
    * Deletes the company master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param companyMasterSid the primary key of the company master
    * @return the company master that was removed
    * @throws PortalException if a company master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CompanyMaster deleteCompanyMaster(
        int companyMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _companyMasterLocalService.deleteCompanyMaster(companyMasterSid);
    }

    /**
    * Deletes the company master from the database. Also notifies the appropriate model listeners.
    *
    * @param companyMaster the company master
    * @return the company master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CompanyMaster deleteCompanyMaster(
        com.stpl.app.model.CompanyMaster companyMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _companyMasterLocalService.deleteCompanyMaster(companyMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _companyMasterLocalService.dynamicQuery();
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
        return _companyMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _companyMasterLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _companyMasterLocalService.dynamicQuery(dynamicQuery, start,
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
        return _companyMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _companyMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.CompanyMaster fetchCompanyMaster(
        int companyMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _companyMasterLocalService.fetchCompanyMaster(companyMasterSid);
    }

    /**
    * Returns the company master with the primary key.
    *
    * @param companyMasterSid the primary key of the company master
    * @return the company master
    * @throws PortalException if a company master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CompanyMaster getCompanyMaster(
        int companyMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _companyMasterLocalService.getCompanyMaster(companyMasterSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _companyMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the company masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of company masters
    * @param end the upper bound of the range of company masters (not inclusive)
    * @return the range of company masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.CompanyMaster> getCompanyMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _companyMasterLocalService.getCompanyMasters(start, end);
    }

    /**
    * Returns the number of company masters.
    *
    * @return the number of company masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getCompanyMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _companyMasterLocalService.getCompanyMastersCount();
    }

    /**
    * Updates the company master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param companyMaster the company master
    * @return the company master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CompanyMaster updateCompanyMaster(
        com.stpl.app.model.CompanyMaster companyMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _companyMasterLocalService.updateCompanyMaster(companyMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _companyMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _companyMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _companyMasterLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    @Override
    public java.util.List findCompanyMaster(java.lang.String companyId,
        java.lang.String companyNo, java.lang.String companyName,
        java.lang.String companyStatus, java.lang.String companyType,
        java.lang.String tradeClass, int identifierType,
        java.lang.String identifier, java.lang.String orderByColumn,
        java.lang.Boolean sortOrder) {
        return _companyMasterLocalService.findCompanyMaster(companyId,
            companyNo, companyName, companyStatus, companyType, tradeClass,
            identifierType, identifier, orderByColumn, sortOrder);
    }

    @Override
    public java.util.List getCompanyTradeClass(int companySystemId) {
        return _companyMasterLocalService.getCompanyTradeClass(companySystemId);
    }

    @Override
    public java.util.List getCompanyTradeClassUniqueCheck(
        java.lang.String tradeClass, java.util.Date tradeStartDate) {
        return _companyMasterLocalService.getCompanyTradeClassUniqueCheck(tradeClass,
            tradeStartDate);
    }

    @Override
    public java.util.List deleteCompanyTradeClassForUpdate(int companySystemId) {
        return _companyMasterLocalService.deleteCompanyTradeClassForUpdate(companySystemId);
    }

    @Override
    public java.util.List findCompanyMasterV1(java.lang.String companyId,
        java.lang.String companyNo, java.lang.String companyName,
        java.lang.String companyStatus, java.lang.String companyType,
        java.lang.String companyCategory, java.lang.String companyGroup,
        java.lang.String tradeClass, int identifierType,
        java.lang.String identifier, java.lang.String orderByColumn,
        java.lang.Boolean sortOrder, java.lang.Object index,
        java.lang.Object next,
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return _companyMasterLocalService.findCompanyMasterV1(companyId,
            companyNo, companyName, companyStatus, companyType,
            companyCategory, companyGroup, tradeClass, identifierType,
            identifier, orderByColumn, sortOrder, index, next, parameters);
    }

    @Override
    public java.util.List getCustomerSearchDetails(
        java.lang.String customerNo, java.lang.String tradeClass,
        java.lang.String customerStatus, java.lang.String state,
        java.lang.String customerName, java.lang.String customerType,
        java.lang.String city, java.lang.String zipCode) {
        return _companyMasterLocalService.getCustomerSearchDetails(customerNo,
            tradeClass, customerStatus, state, customerName, customerType,
            city, zipCode);
    }

    @Override
    public java.util.List getPriorParentNo(java.lang.String priorSystemId) {
        return _companyMasterLocalService.getPriorParentNo(priorSystemId);
    }

    @Override
    public java.util.List getAvailableSearchResults(
        java.lang.String searchCriteria) {
        return _companyMasterLocalService.getAvailableSearchResults(searchCriteria);
    }

    @Override
    public java.lang.Object executeSelectQuery(java.lang.String query,
        java.lang.Object udc1, java.lang.Object udc2) {
        return _companyMasterLocalService.executeSelectQuery(query, udc1, udc2);
    }

    @Override
    public java.util.List searchTPCompanies(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return _companyMasterLocalService.searchTPCompanies(parameters);
    }

    @Override
    public java.util.List getColumnNames(java.lang.String tablename) {
        return _companyMasterLocalService.getColumnNames(tablename);
    }

    @Override
    public java.util.List getCompanyTypeCount(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return _companyMasterLocalService.getCompanyTypeCount(parameters);
    }

    @Override
    public java.util.List searchCompanies(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return _companyMasterLocalService.searchCompanies(parameters);
    }

    @Override
    public java.util.List executeQuery(java.lang.String query) {
        return _companyMasterLocalService.executeQuery(query);
    }

    @Override
    public int executeUpdateQuery(java.lang.String query) {
        return _companyMasterLocalService.executeUpdateQuery(query);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public CompanyMasterLocalService getWrappedCompanyMasterLocalService() {
        return _companyMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedCompanyMasterLocalService(
        CompanyMasterLocalService companyMasterLocalService) {
        _companyMasterLocalService = companyMasterLocalService;
    }

    @Override
    public CompanyMasterLocalService getWrappedService() {
        return _companyMasterLocalService;
    }

    @Override
    public void setWrappedService(
        CompanyMasterLocalService companyMasterLocalService) {
        _companyMasterLocalService = companyMasterLocalService;
    }
}
