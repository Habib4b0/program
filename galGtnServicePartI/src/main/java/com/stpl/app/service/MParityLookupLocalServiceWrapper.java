package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MParityLookupLocalService}.
 *
 * @author
 * @see MParityLookupLocalService
 * @generated
 */
public class MParityLookupLocalServiceWrapper
    implements MParityLookupLocalService,
        ServiceWrapper<MParityLookupLocalService> {
    private MParityLookupLocalService _mParityLookupLocalService;

    public MParityLookupLocalServiceWrapper(
        MParityLookupLocalService mParityLookupLocalService) {
        _mParityLookupLocalService = mParityLookupLocalService;
    }

    /**
    * Adds the m parity lookup to the database. Also notifies the appropriate model listeners.
    *
    * @param mParityLookup the m parity lookup
    * @return the m parity lookup that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MParityLookup addMParityLookup(
        com.stpl.app.model.MParityLookup mParityLookup)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mParityLookupLocalService.addMParityLookup(mParityLookup);
    }

    /**
    * Creates a new m parity lookup with the primary key. Does not add the m parity lookup to the database.
    *
    * @param mParityLookupSid the primary key for the new m parity lookup
    * @return the new m parity lookup
    */
    @Override
    public com.stpl.app.model.MParityLookup createMParityLookup(
        int mParityLookupSid) {
        return _mParityLookupLocalService.createMParityLookup(mParityLookupSid);
    }

    /**
    * Deletes the m parity lookup with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param mParityLookupSid the primary key of the m parity lookup
    * @return the m parity lookup that was removed
    * @throws PortalException if a m parity lookup with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MParityLookup deleteMParityLookup(
        int mParityLookupSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _mParityLookupLocalService.deleteMParityLookup(mParityLookupSid);
    }

    /**
    * Deletes the m parity lookup from the database. Also notifies the appropriate model listeners.
    *
    * @param mParityLookup the m parity lookup
    * @return the m parity lookup that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MParityLookup deleteMParityLookup(
        com.stpl.app.model.MParityLookup mParityLookup)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mParityLookupLocalService.deleteMParityLookup(mParityLookup);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _mParityLookupLocalService.dynamicQuery();
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
        return _mParityLookupLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MParityLookupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _mParityLookupLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MParityLookupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _mParityLookupLocalService.dynamicQuery(dynamicQuery, start,
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
        return _mParityLookupLocalService.dynamicQueryCount(dynamicQuery);
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
        return _mParityLookupLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.MParityLookup fetchMParityLookup(
        int mParityLookupSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mParityLookupLocalService.fetchMParityLookup(mParityLookupSid);
    }

    /**
    * Returns the m parity lookup with the primary key.
    *
    * @param mParityLookupSid the primary key of the m parity lookup
    * @return the m parity lookup
    * @throws PortalException if a m parity lookup with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MParityLookup getMParityLookup(
        int mParityLookupSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _mParityLookupLocalService.getMParityLookup(mParityLookupSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _mParityLookupLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the m parity lookups.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MParityLookupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of m parity lookups
    * @param end the upper bound of the range of m parity lookups (not inclusive)
    * @return the range of m parity lookups
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.MParityLookup> getMParityLookups(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mParityLookupLocalService.getMParityLookups(start, end);
    }

    /**
    * Returns the number of m parity lookups.
    *
    * @return the number of m parity lookups
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getMParityLookupsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mParityLookupLocalService.getMParityLookupsCount();
    }

    /**
    * Updates the m parity lookup in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param mParityLookup the m parity lookup
    * @return the m parity lookup that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MParityLookup updateMParityLookup(
        com.stpl.app.model.MParityLookup mParityLookup)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mParityLookupLocalService.updateMParityLookup(mParityLookup);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _mParityLookupLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _mParityLookupLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _mParityLookupLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public MParityLookupLocalService getWrappedMParityLookupLocalService() {
        return _mParityLookupLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedMParityLookupLocalService(
        MParityLookupLocalService mParityLookupLocalService) {
        _mParityLookupLocalService = mParityLookupLocalService;
    }

    @Override
    public MParityLookupLocalService getWrappedService() {
        return _mParityLookupLocalService;
    }

    @Override
    public void setWrappedService(
        MParityLookupLocalService mParityLookupLocalService) {
        _mParityLookupLocalService = mParityLookupLocalService;
    }
}
