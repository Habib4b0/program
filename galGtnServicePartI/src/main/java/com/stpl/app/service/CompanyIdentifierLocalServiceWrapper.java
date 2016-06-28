package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CompanyIdentifierLocalService}.
 *
 * @author
 * @see CompanyIdentifierLocalService
 * @generated
 */
public class CompanyIdentifierLocalServiceWrapper
    implements CompanyIdentifierLocalService,
        ServiceWrapper<CompanyIdentifierLocalService> {
    private CompanyIdentifierLocalService _companyIdentifierLocalService;

    public CompanyIdentifierLocalServiceWrapper(
        CompanyIdentifierLocalService companyIdentifierLocalService) {
        _companyIdentifierLocalService = companyIdentifierLocalService;
    }

    /**
    * Adds the company identifier to the database. Also notifies the appropriate model listeners.
    *
    * @param companyIdentifier the company identifier
    * @return the company identifier that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CompanyIdentifier addCompanyIdentifier(
        com.stpl.app.model.CompanyIdentifier companyIdentifier)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _companyIdentifierLocalService.addCompanyIdentifier(companyIdentifier);
    }

    /**
    * Creates a new company identifier with the primary key. Does not add the company identifier to the database.
    *
    * @param companyIdentifierSid the primary key for the new company identifier
    * @return the new company identifier
    */
    @Override
    public com.stpl.app.model.CompanyIdentifier createCompanyIdentifier(
        int companyIdentifierSid) {
        return _companyIdentifierLocalService.createCompanyIdentifier(companyIdentifierSid);
    }

    /**
    * Deletes the company identifier with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param companyIdentifierSid the primary key of the company identifier
    * @return the company identifier that was removed
    * @throws PortalException if a company identifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CompanyIdentifier deleteCompanyIdentifier(
        int companyIdentifierSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _companyIdentifierLocalService.deleteCompanyIdentifier(companyIdentifierSid);
    }

    /**
    * Deletes the company identifier from the database. Also notifies the appropriate model listeners.
    *
    * @param companyIdentifier the company identifier
    * @return the company identifier that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CompanyIdentifier deleteCompanyIdentifier(
        com.stpl.app.model.CompanyIdentifier companyIdentifier)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _companyIdentifierLocalService.deleteCompanyIdentifier(companyIdentifier);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _companyIdentifierLocalService.dynamicQuery();
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
        return _companyIdentifierLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _companyIdentifierLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _companyIdentifierLocalService.dynamicQuery(dynamicQuery, start,
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
        return _companyIdentifierLocalService.dynamicQueryCount(dynamicQuery);
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
        return _companyIdentifierLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.CompanyIdentifier fetchCompanyIdentifier(
        int companyIdentifierSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _companyIdentifierLocalService.fetchCompanyIdentifier(companyIdentifierSid);
    }

    /**
    * Returns the company identifier with the primary key.
    *
    * @param companyIdentifierSid the primary key of the company identifier
    * @return the company identifier
    * @throws PortalException if a company identifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CompanyIdentifier getCompanyIdentifier(
        int companyIdentifierSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _companyIdentifierLocalService.getCompanyIdentifier(companyIdentifierSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _companyIdentifierLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the company identifiers.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of company identifiers
    * @param end the upper bound of the range of company identifiers (not inclusive)
    * @return the range of company identifiers
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.CompanyIdentifier> getCompanyIdentifiers(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _companyIdentifierLocalService.getCompanyIdentifiers(start, end);
    }

    /**
    * Returns the number of company identifiers.
    *
    * @return the number of company identifiers
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getCompanyIdentifiersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _companyIdentifierLocalService.getCompanyIdentifiersCount();
    }

    /**
    * Updates the company identifier in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param companyIdentifier the company identifier
    * @return the company identifier that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CompanyIdentifier updateCompanyIdentifier(
        com.stpl.app.model.CompanyIdentifier companyIdentifier)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _companyIdentifierLocalService.updateCompanyIdentifier(companyIdentifier);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _companyIdentifierLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _companyIdentifierLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _companyIdentifierLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    @Override
    public java.util.List<com.stpl.app.model.CompanyIdentifier> findByCompanyCrtIdentifierDetails(
        int companyMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _companyIdentifierLocalService.findByCompanyCrtIdentifierDetails(companyMasterSid);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public CompanyIdentifierLocalService getWrappedCompanyIdentifierLocalService() {
        return _companyIdentifierLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedCompanyIdentifierLocalService(
        CompanyIdentifierLocalService companyIdentifierLocalService) {
        _companyIdentifierLocalService = companyIdentifierLocalService;
    }

    @Override
    public CompanyIdentifierLocalService getWrappedService() {
        return _companyIdentifierLocalService;
    }

    @Override
    public void setWrappedService(
        CompanyIdentifierLocalService companyIdentifierLocalService) {
        _companyIdentifierLocalService = companyIdentifierLocalService;
    }
}
