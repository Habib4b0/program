package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CompanyParentDetailsLocalService}.
 *
 * @author
 * @see CompanyParentDetailsLocalService
 * @generated
 */
public class CompanyParentDetailsLocalServiceWrapper
    implements CompanyParentDetailsLocalService,
        ServiceWrapper<CompanyParentDetailsLocalService> {
    private CompanyParentDetailsLocalService _companyParentDetailsLocalService;

    public CompanyParentDetailsLocalServiceWrapper(
        CompanyParentDetailsLocalService companyParentDetailsLocalService) {
        _companyParentDetailsLocalService = companyParentDetailsLocalService;
    }

    /**
    * Adds the company parent details to the database. Also notifies the appropriate model listeners.
    *
    * @param companyParentDetails the company parent details
    * @return the company parent details that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CompanyParentDetails addCompanyParentDetails(
        com.stpl.app.model.CompanyParentDetails companyParentDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _companyParentDetailsLocalService.addCompanyParentDetails(companyParentDetails);
    }

    /**
    * Creates a new company parent details with the primary key. Does not add the company parent details to the database.
    *
    * @param companyParentDetailsSid the primary key for the new company parent details
    * @return the new company parent details
    */
    @Override
    public com.stpl.app.model.CompanyParentDetails createCompanyParentDetails(
        int companyParentDetailsSid) {
        return _companyParentDetailsLocalService.createCompanyParentDetails(companyParentDetailsSid);
    }

    /**
    * Deletes the company parent details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param companyParentDetailsSid the primary key of the company parent details
    * @return the company parent details that was removed
    * @throws PortalException if a company parent details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CompanyParentDetails deleteCompanyParentDetails(
        int companyParentDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _companyParentDetailsLocalService.deleteCompanyParentDetails(companyParentDetailsSid);
    }

    /**
    * Deletes the company parent details from the database. Also notifies the appropriate model listeners.
    *
    * @param companyParentDetails the company parent details
    * @return the company parent details that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CompanyParentDetails deleteCompanyParentDetails(
        com.stpl.app.model.CompanyParentDetails companyParentDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _companyParentDetailsLocalService.deleteCompanyParentDetails(companyParentDetails);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _companyParentDetailsLocalService.dynamicQuery();
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
        return _companyParentDetailsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyParentDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _companyParentDetailsLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyParentDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _companyParentDetailsLocalService.dynamicQuery(dynamicQuery,
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
        return _companyParentDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _companyParentDetailsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.CompanyParentDetails fetchCompanyParentDetails(
        int companyParentDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _companyParentDetailsLocalService.fetchCompanyParentDetails(companyParentDetailsSid);
    }

    /**
    * Returns the company parent details with the primary key.
    *
    * @param companyParentDetailsSid the primary key of the company parent details
    * @return the company parent details
    * @throws PortalException if a company parent details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CompanyParentDetails getCompanyParentDetails(
        int companyParentDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _companyParentDetailsLocalService.getCompanyParentDetails(companyParentDetailsSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _companyParentDetailsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the company parent detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyParentDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of company parent detailses
    * @param end the upper bound of the range of company parent detailses (not inclusive)
    * @return the range of company parent detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.CompanyParentDetails> getCompanyParentDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _companyParentDetailsLocalService.getCompanyParentDetailses(start,
            end);
    }

    /**
    * Returns the number of company parent detailses.
    *
    * @return the number of company parent detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getCompanyParentDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _companyParentDetailsLocalService.getCompanyParentDetailsesCount();
    }

    /**
    * Updates the company parent details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param companyParentDetails the company parent details
    * @return the company parent details that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CompanyParentDetails updateCompanyParentDetails(
        com.stpl.app.model.CompanyParentDetails companyParentDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _companyParentDetailsLocalService.updateCompanyParentDetails(companyParentDetails);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _companyParentDetailsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _companyParentDetailsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _companyParentDetailsLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public CompanyParentDetailsLocalService getWrappedCompanyParentDetailsLocalService() {
        return _companyParentDetailsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedCompanyParentDetailsLocalService(
        CompanyParentDetailsLocalService companyParentDetailsLocalService) {
        _companyParentDetailsLocalService = companyParentDetailsLocalService;
    }

    @Override
    public CompanyParentDetailsLocalService getWrappedService() {
        return _companyParentDetailsLocalService;
    }

    @Override
    public void setWrappedService(
        CompanyParentDetailsLocalService companyParentDetailsLocalService) {
        _companyParentDetailsLocalService = companyParentDetailsLocalService;
    }
}
