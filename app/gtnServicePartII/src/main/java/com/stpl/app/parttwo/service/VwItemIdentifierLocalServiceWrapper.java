package com.stpl.app.parttwo.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link VwItemIdentifierLocalService}.
 *
 * @author
 * @see VwItemIdentifierLocalService
 * @generated
 */
public class VwItemIdentifierLocalServiceWrapper
    implements VwItemIdentifierLocalService,
        ServiceWrapper<VwItemIdentifierLocalService> {
    private VwItemIdentifierLocalService _vwItemIdentifierLocalService;

    public VwItemIdentifierLocalServiceWrapper(
        VwItemIdentifierLocalService vwItemIdentifierLocalService) {
        _vwItemIdentifierLocalService = vwItemIdentifierLocalService;
    }

    /**
    * Adds the vw item identifier to the database. Also notifies the appropriate model listeners.
    *
    * @param vwItemIdentifier the vw item identifier
    * @return the vw item identifier that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwItemIdentifier addVwItemIdentifier(
        com.stpl.app.parttwo.model.VwItemIdentifier vwItemIdentifier)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwItemIdentifierLocalService.addVwItemIdentifier(vwItemIdentifier);
    }

    /**
    * Creates a new vw item identifier with the primary key. Does not add the vw item identifier to the database.
    *
    * @param itemIdentifierSid the primary key for the new vw item identifier
    * @return the new vw item identifier
    */
    @Override
    public com.stpl.app.parttwo.model.VwItemIdentifier createVwItemIdentifier(
        int itemIdentifierSid) {
        return _vwItemIdentifierLocalService.createVwItemIdentifier(itemIdentifierSid);
    }

    /**
    * Deletes the vw item identifier with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param itemIdentifierSid the primary key of the vw item identifier
    * @return the vw item identifier that was removed
    * @throws PortalException if a vw item identifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwItemIdentifier deleteVwItemIdentifier(
        int itemIdentifierSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _vwItemIdentifierLocalService.deleteVwItemIdentifier(itemIdentifierSid);
    }

    /**
    * Deletes the vw item identifier from the database. Also notifies the appropriate model listeners.
    *
    * @param vwItemIdentifier the vw item identifier
    * @return the vw item identifier that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwItemIdentifier deleteVwItemIdentifier(
        com.stpl.app.parttwo.model.VwItemIdentifier vwItemIdentifier)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwItemIdentifierLocalService.deleteVwItemIdentifier(vwItemIdentifier);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _vwItemIdentifierLocalService.dynamicQuery();
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
        return _vwItemIdentifierLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _vwItemIdentifierLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _vwItemIdentifierLocalService.dynamicQuery(dynamicQuery, start,
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
        return _vwItemIdentifierLocalService.dynamicQueryCount(dynamicQuery);
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
        return _vwItemIdentifierLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.parttwo.model.VwItemIdentifier fetchVwItemIdentifier(
        int itemIdentifierSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwItemIdentifierLocalService.fetchVwItemIdentifier(itemIdentifierSid);
    }

    /**
    * Returns the vw item identifier with the primary key.
    *
    * @param itemIdentifierSid the primary key of the vw item identifier
    * @return the vw item identifier
    * @throws PortalException if a vw item identifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwItemIdentifier getVwItemIdentifier(
        int itemIdentifierSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _vwItemIdentifierLocalService.getVwItemIdentifier(itemIdentifierSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _vwItemIdentifierLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the vw item identifiers.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw item identifiers
    * @param end the upper bound of the range of vw item identifiers (not inclusive)
    * @return the range of vw item identifiers
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.parttwo.model.VwItemIdentifier> getVwItemIdentifiers(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwItemIdentifierLocalService.getVwItemIdentifiers(start, end);
    }

    /**
    * Returns the number of vw item identifiers.
    *
    * @return the number of vw item identifiers
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getVwItemIdentifiersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwItemIdentifierLocalService.getVwItemIdentifiersCount();
    }

    /**
    * Updates the vw item identifier in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param vwItemIdentifier the vw item identifier
    * @return the vw item identifier that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwItemIdentifier updateVwItemIdentifier(
        com.stpl.app.parttwo.model.VwItemIdentifier vwItemIdentifier)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwItemIdentifierLocalService.updateVwItemIdentifier(vwItemIdentifier);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _vwItemIdentifierLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _vwItemIdentifierLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _vwItemIdentifierLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public VwItemIdentifierLocalService getWrappedVwItemIdentifierLocalService() {
        return _vwItemIdentifierLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedVwItemIdentifierLocalService(
        VwItemIdentifierLocalService vwItemIdentifierLocalService) {
        _vwItemIdentifierLocalService = vwItemIdentifierLocalService;
    }

    @Override
    public VwItemIdentifierLocalService getWrappedService() {
        return _vwItemIdentifierLocalService;
    }

    @Override
    public void setWrappedService(
        VwItemIdentifierLocalService vwItemIdentifierLocalService) {
        _vwItemIdentifierLocalService = vwItemIdentifierLocalService;
    }
}
