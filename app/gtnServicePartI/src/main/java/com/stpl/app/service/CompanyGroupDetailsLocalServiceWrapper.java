package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CompanyGroupDetailsLocalService}.
 *
 * @author
 * @see CompanyGroupDetailsLocalService
 * @generated
 */
public class CompanyGroupDetailsLocalServiceWrapper
    implements CompanyGroupDetailsLocalService,
        ServiceWrapper<CompanyGroupDetailsLocalService> {
    private CompanyGroupDetailsLocalService _companyGroupDetailsLocalService;

    public CompanyGroupDetailsLocalServiceWrapper(
        CompanyGroupDetailsLocalService companyGroupDetailsLocalService) {
        _companyGroupDetailsLocalService = companyGroupDetailsLocalService;
    }

    /**
    * Adds the company group details to the database. Also notifies the appropriate model listeners.
    *
    * @param companyGroupDetails the company group details
    * @return the company group details that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CompanyGroupDetails addCompanyGroupDetails(
        com.stpl.app.model.CompanyGroupDetails companyGroupDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _companyGroupDetailsLocalService.addCompanyGroupDetails(companyGroupDetails);
    }

    /**
    * Creates a new company group details with the primary key. Does not add the company group details to the database.
    *
    * @param companyGroupDetailsSid the primary key for the new company group details
    * @return the new company group details
    */
    @Override
    public com.stpl.app.model.CompanyGroupDetails createCompanyGroupDetails(
        int companyGroupDetailsSid) {
        return _companyGroupDetailsLocalService.createCompanyGroupDetails(companyGroupDetailsSid);
    }

    /**
    * Deletes the company group details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param companyGroupDetailsSid the primary key of the company group details
    * @return the company group details that was removed
    * @throws PortalException if a company group details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CompanyGroupDetails deleteCompanyGroupDetails(
        int companyGroupDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _companyGroupDetailsLocalService.deleteCompanyGroupDetails(companyGroupDetailsSid);
    }

    /**
    * Deletes the company group details from the database. Also notifies the appropriate model listeners.
    *
    * @param companyGroupDetails the company group details
    * @return the company group details that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CompanyGroupDetails deleteCompanyGroupDetails(
        com.stpl.app.model.CompanyGroupDetails companyGroupDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _companyGroupDetailsLocalService.deleteCompanyGroupDetails(companyGroupDetails);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _companyGroupDetailsLocalService.dynamicQuery();
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
        return _companyGroupDetailsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _companyGroupDetailsLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _companyGroupDetailsLocalService.dynamicQuery(dynamicQuery,
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
        return _companyGroupDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _companyGroupDetailsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.CompanyGroupDetails fetchCompanyGroupDetails(
        int companyGroupDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _companyGroupDetailsLocalService.fetchCompanyGroupDetails(companyGroupDetailsSid);
    }

    /**
    * Returns the company group details with the primary key.
    *
    * @param companyGroupDetailsSid the primary key of the company group details
    * @return the company group details
    * @throws PortalException if a company group details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CompanyGroupDetails getCompanyGroupDetails(
        int companyGroupDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _companyGroupDetailsLocalService.getCompanyGroupDetails(companyGroupDetailsSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _companyGroupDetailsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the company group detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of company group detailses
    * @param end the upper bound of the range of company group detailses (not inclusive)
    * @return the range of company group detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.CompanyGroupDetails> getCompanyGroupDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _companyGroupDetailsLocalService.getCompanyGroupDetailses(start,
            end);
    }

    /**
    * Returns the number of company group detailses.
    *
    * @return the number of company group detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getCompanyGroupDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _companyGroupDetailsLocalService.getCompanyGroupDetailsesCount();
    }

    /**
    * Updates the company group details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param companyGroupDetails the company group details
    * @return the company group details that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CompanyGroupDetails updateCompanyGroupDetails(
        com.stpl.app.model.CompanyGroupDetails companyGroupDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _companyGroupDetailsLocalService.updateCompanyGroupDetails(companyGroupDetails);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _companyGroupDetailsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _companyGroupDetailsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _companyGroupDetailsLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public CompanyGroupDetailsLocalService getWrappedCompanyGroupDetailsLocalService() {
        return _companyGroupDetailsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedCompanyGroupDetailsLocalService(
        CompanyGroupDetailsLocalService companyGroupDetailsLocalService) {
        _companyGroupDetailsLocalService = companyGroupDetailsLocalService;
    }

    @Override
    public CompanyGroupDetailsLocalService getWrappedService() {
        return _companyGroupDetailsLocalService;
    }

    @Override
    public void setWrappedService(
        CompanyGroupDetailsLocalService companyGroupDetailsLocalService) {
        _companyGroupDetailsLocalService = companyGroupDetailsLocalService;
    }
}
