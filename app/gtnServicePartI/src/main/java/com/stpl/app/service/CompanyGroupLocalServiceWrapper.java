package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CompanyGroupLocalService}.
 *
 * @author
 * @see CompanyGroupLocalService
 * @generated
 */
public class CompanyGroupLocalServiceWrapper implements CompanyGroupLocalService,
    ServiceWrapper<CompanyGroupLocalService> {
    private CompanyGroupLocalService _companyGroupLocalService;

    public CompanyGroupLocalServiceWrapper(
        CompanyGroupLocalService companyGroupLocalService) {
        _companyGroupLocalService = companyGroupLocalService;
    }

    /**
    * Adds the company group to the database. Also notifies the appropriate model listeners.
    *
    * @param companyGroup the company group
    * @return the company group that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CompanyGroup addCompanyGroup(
        com.stpl.app.model.CompanyGroup companyGroup)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _companyGroupLocalService.addCompanyGroup(companyGroup);
    }

    /**
    * Creates a new company group with the primary key. Does not add the company group to the database.
    *
    * @param companyGroupSid the primary key for the new company group
    * @return the new company group
    */
    @Override
    public com.stpl.app.model.CompanyGroup createCompanyGroup(
        int companyGroupSid) {
        return _companyGroupLocalService.createCompanyGroup(companyGroupSid);
    }

    /**
    * Deletes the company group with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param companyGroupSid the primary key of the company group
    * @return the company group that was removed
    * @throws PortalException if a company group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CompanyGroup deleteCompanyGroup(
        int companyGroupSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _companyGroupLocalService.deleteCompanyGroup(companyGroupSid);
    }

    /**
    * Deletes the company group from the database. Also notifies the appropriate model listeners.
    *
    * @param companyGroup the company group
    * @return the company group that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CompanyGroup deleteCompanyGroup(
        com.stpl.app.model.CompanyGroup companyGroup)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _companyGroupLocalService.deleteCompanyGroup(companyGroup);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _companyGroupLocalService.dynamicQuery();
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
        return _companyGroupLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _companyGroupLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _companyGroupLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _companyGroupLocalService.dynamicQueryCount(dynamicQuery);
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
        return _companyGroupLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.CompanyGroup fetchCompanyGroup(
        int companyGroupSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _companyGroupLocalService.fetchCompanyGroup(companyGroupSid);
    }

    /**
    * Returns the company group with the primary key.
    *
    * @param companyGroupSid the primary key of the company group
    * @return the company group
    * @throws PortalException if a company group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CompanyGroup getCompanyGroup(int companyGroupSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _companyGroupLocalService.getCompanyGroup(companyGroupSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _companyGroupLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the company groups.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CompanyGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of company groups
    * @param end the upper bound of the range of company groups (not inclusive)
    * @return the range of company groups
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.CompanyGroup> getCompanyGroups(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _companyGroupLocalService.getCompanyGroups(start, end);
    }

    /**
    * Returns the number of company groups.
    *
    * @return the number of company groups
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getCompanyGroupsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _companyGroupLocalService.getCompanyGroupsCount();
    }

    /**
    * Updates the company group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param companyGroup the company group
    * @return the company group that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.CompanyGroup updateCompanyGroup(
        com.stpl.app.model.CompanyGroup companyGroup)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _companyGroupLocalService.updateCompanyGroup(companyGroup);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _companyGroupLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _companyGroupLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _companyGroupLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public CompanyGroupLocalService getWrappedCompanyGroupLocalService() {
        return _companyGroupLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedCompanyGroupLocalService(
        CompanyGroupLocalService companyGroupLocalService) {
        _companyGroupLocalService = companyGroupLocalService;
    }

    @Override
    public CompanyGroupLocalService getWrappedService() {
        return _companyGroupLocalService;
    }

    @Override
    public void setWrappedService(
        CompanyGroupLocalService companyGroupLocalService) {
        _companyGroupLocalService = companyGroupLocalService;
    }
}
