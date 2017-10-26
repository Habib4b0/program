package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CompanyQualifierLocalService}.
 *
 * @author
 * @see CompanyQualifierLocalService
 * @generated
 */
public class CompanyQualifierLocalServiceWrapper
    implements CompanyQualifierLocalService,
        ServiceWrapper<CompanyQualifierLocalService> {
    private CompanyQualifierLocalService _companyQualifierLocalService;

    public CompanyQualifierLocalServiceWrapper(
        CompanyQualifierLocalService companyQualifierLocalService) {
        _companyQualifierLocalService = companyQualifierLocalService;
    }

    /**
    * Adds the company qualifier to the database. Also notifies the appropriate model listeners.
    *
    * @param companyQualifier the company qualifier
    * @return the company qualifier that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CompanyQualifier addCompanyQualifier(
        com.stpl.app.model.CompanyQualifier companyQualifier)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _companyQualifierLocalService.addCompanyQualifier(companyQualifier);
    }

    /**
    * Creates a new company qualifier with the primary key. Does not add the company qualifier to the database.
    *
    * @param companyQualifierSid the primary key for the new company qualifier
    * @return the new company qualifier
    */
    @Override
    public com.stpl.app.model.CompanyQualifier createCompanyQualifier(
        int companyQualifierSid) {
        return _companyQualifierLocalService.createCompanyQualifier(companyQualifierSid);
    }

    /**
    * Deletes the company qualifier with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param companyQualifierSid the primary key of the company qualifier
    * @return the company qualifier that was removed
    * @throws PortalException if a company qualifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CompanyQualifier deleteCompanyQualifier(
        int companyQualifierSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _companyQualifierLocalService.deleteCompanyQualifier(companyQualifierSid);
    }

    /**
    * Deletes the company qualifier from the database. Also notifies the appropriate model listeners.
    *
    * @param companyQualifier the company qualifier
    * @return the company qualifier that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CompanyQualifier deleteCompanyQualifier(
        com.stpl.app.model.CompanyQualifier companyQualifier)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _companyQualifierLocalService.deleteCompanyQualifier(companyQualifier);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _companyQualifierLocalService.dynamicQuery();
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
        return _companyQualifierLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _companyQualifierLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _companyQualifierLocalService.dynamicQuery(dynamicQuery, start,
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
        return _companyQualifierLocalService.dynamicQueryCount(dynamicQuery);
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
        return _companyQualifierLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.CompanyQualifier fetchCompanyQualifier(
        int companyQualifierSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _companyQualifierLocalService.fetchCompanyQualifier(companyQualifierSid);
    }

    /**
    * Returns the company qualifier with the primary key.
    *
    * @param companyQualifierSid the primary key of the company qualifier
    * @return the company qualifier
    * @throws PortalException if a company qualifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CompanyQualifier getCompanyQualifier(
        int companyQualifierSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _companyQualifierLocalService.getCompanyQualifier(companyQualifierSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _companyQualifierLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the company qualifiers.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of company qualifiers
    * @param end the upper bound of the range of company qualifiers (not inclusive)
    * @return the range of company qualifiers
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.CompanyQualifier> getCompanyQualifiers(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _companyQualifierLocalService.getCompanyQualifiers(start, end);
    }

    /**
    * Returns the number of company qualifiers.
    *
    * @return the number of company qualifiers
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getCompanyQualifiersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _companyQualifierLocalService.getCompanyQualifiersCount();
    }

    /**
    * Updates the company qualifier in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param companyQualifier the company qualifier
    * @return the company qualifier that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CompanyQualifier updateCompanyQualifier(
        com.stpl.app.model.CompanyQualifier companyQualifier)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _companyQualifierLocalService.updateCompanyQualifier(companyQualifier);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _companyQualifierLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _companyQualifierLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _companyQualifierLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
    * Returns the company qualifier where companyQualifierName = &#63; or throws a {@link com.stpl.app.global.NoSuchCompanyQualifierException} if it could not be found.
    *
    * @param companyQualifierName the company qualifier name
    * @return the matching company qualifier
    * @throws com.stpl.app.global.NoSuchCompanyQualifierException if a matching company qualifier could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CompanyQualifier findByCompanyCrtQualifierByName(
        java.lang.String companyQualifierName)
        throws com.stpl.app.NoSuchCompanyQualifierException,
            com.stpl.portal.kernel.exception.SystemException {
        return _companyQualifierLocalService.findByCompanyCrtQualifierByName(companyQualifierName);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public CompanyQualifierLocalService getWrappedCompanyQualifierLocalService() {
        return _companyQualifierLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedCompanyQualifierLocalService(
        CompanyQualifierLocalService companyQualifierLocalService) {
        _companyQualifierLocalService = companyQualifierLocalService;
    }

    @Override
    public CompanyQualifierLocalService getWrappedService() {
        return _companyQualifierLocalService;
    }

    @Override
    public void setWrappedService(
        CompanyQualifierLocalService companyQualifierLocalService) {
        _companyQualifierLocalService = companyQualifierLocalService;
    }
}
