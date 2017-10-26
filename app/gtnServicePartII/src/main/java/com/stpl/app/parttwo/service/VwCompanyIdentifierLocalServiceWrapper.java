package com.stpl.app.parttwo.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link VwCompanyIdentifierLocalService}.
 *
 * @author
 * @see VwCompanyIdentifierLocalService
 * @generated
 */
public class VwCompanyIdentifierLocalServiceWrapper
    implements VwCompanyIdentifierLocalService,
        ServiceWrapper<VwCompanyIdentifierLocalService> {
    private VwCompanyIdentifierLocalService _vwCompanyIdentifierLocalService;

    public VwCompanyIdentifierLocalServiceWrapper(
        VwCompanyIdentifierLocalService vwCompanyIdentifierLocalService) {
        _vwCompanyIdentifierLocalService = vwCompanyIdentifierLocalService;
    }

    /**
    * Adds the vw company identifier to the database. Also notifies the appropriate model listeners.
    *
    * @param vwCompanyIdentifier the vw company identifier
    * @return the vw company identifier that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwCompanyIdentifier addVwCompanyIdentifier(
        com.stpl.app.parttwo.model.VwCompanyIdentifier vwCompanyIdentifier)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwCompanyIdentifierLocalService.addVwCompanyIdentifier(vwCompanyIdentifier);
    }

    /**
    * Creates a new vw company identifier with the primary key. Does not add the vw company identifier to the database.
    *
    * @param companyIdentifierSid the primary key for the new vw company identifier
    * @return the new vw company identifier
    */
    @Override
    public com.stpl.app.parttwo.model.VwCompanyIdentifier createVwCompanyIdentifier(
        int companyIdentifierSid) {
        return _vwCompanyIdentifierLocalService.createVwCompanyIdentifier(companyIdentifierSid);
    }

    /**
    * Deletes the vw company identifier with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param companyIdentifierSid the primary key of the vw company identifier
    * @return the vw company identifier that was removed
    * @throws PortalException if a vw company identifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwCompanyIdentifier deleteVwCompanyIdentifier(
        int companyIdentifierSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _vwCompanyIdentifierLocalService.deleteVwCompanyIdentifier(companyIdentifierSid);
    }

    /**
    * Deletes the vw company identifier from the database. Also notifies the appropriate model listeners.
    *
    * @param vwCompanyIdentifier the vw company identifier
    * @return the vw company identifier that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwCompanyIdentifier deleteVwCompanyIdentifier(
        com.stpl.app.parttwo.model.VwCompanyIdentifier vwCompanyIdentifier)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwCompanyIdentifierLocalService.deleteVwCompanyIdentifier(vwCompanyIdentifier);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _vwCompanyIdentifierLocalService.dynamicQuery();
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
        return _vwCompanyIdentifierLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _vwCompanyIdentifierLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _vwCompanyIdentifierLocalService.dynamicQuery(dynamicQuery,
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
        return _vwCompanyIdentifierLocalService.dynamicQueryCount(dynamicQuery);
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
        return _vwCompanyIdentifierLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.parttwo.model.VwCompanyIdentifier fetchVwCompanyIdentifier(
        int companyIdentifierSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwCompanyIdentifierLocalService.fetchVwCompanyIdentifier(companyIdentifierSid);
    }

    /**
    * Returns the vw company identifier with the primary key.
    *
    * @param companyIdentifierSid the primary key of the vw company identifier
    * @return the vw company identifier
    * @throws PortalException if a vw company identifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwCompanyIdentifier getVwCompanyIdentifier(
        int companyIdentifierSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _vwCompanyIdentifierLocalService.getVwCompanyIdentifier(companyIdentifierSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _vwCompanyIdentifierLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the vw company identifiers.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw company identifiers
    * @param end the upper bound of the range of vw company identifiers (not inclusive)
    * @return the range of vw company identifiers
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.parttwo.model.VwCompanyIdentifier> getVwCompanyIdentifiers(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwCompanyIdentifierLocalService.getVwCompanyIdentifiers(start,
            end);
    }

    /**
    * Returns the number of vw company identifiers.
    *
    * @return the number of vw company identifiers
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getVwCompanyIdentifiersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwCompanyIdentifierLocalService.getVwCompanyIdentifiersCount();
    }

    /**
    * Updates the vw company identifier in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param vwCompanyIdentifier the vw company identifier
    * @return the vw company identifier that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwCompanyIdentifier updateVwCompanyIdentifier(
        com.stpl.app.parttwo.model.VwCompanyIdentifier vwCompanyIdentifier)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwCompanyIdentifierLocalService.updateVwCompanyIdentifier(vwCompanyIdentifier);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _vwCompanyIdentifierLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _vwCompanyIdentifierLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _vwCompanyIdentifierLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public VwCompanyIdentifierLocalService getWrappedVwCompanyIdentifierLocalService() {
        return _vwCompanyIdentifierLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedVwCompanyIdentifierLocalService(
        VwCompanyIdentifierLocalService vwCompanyIdentifierLocalService) {
        _vwCompanyIdentifierLocalService = vwCompanyIdentifierLocalService;
    }

    @Override
    public VwCompanyIdentifierLocalService getWrappedService() {
        return _vwCompanyIdentifierLocalService;
    }

    @Override
    public void setWrappedService(
        VwCompanyIdentifierLocalService vwCompanyIdentifierLocalService) {
        _vwCompanyIdentifierLocalService = vwCompanyIdentifierLocalService;
    }
}
