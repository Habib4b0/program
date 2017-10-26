package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link VwUserTablesLocalService}.
 *
 * @author
 * @see VwUserTablesLocalService
 * @generated
 */
public class VwUserTablesLocalServiceWrapper implements VwUserTablesLocalService,
    ServiceWrapper<VwUserTablesLocalService> {
    private VwUserTablesLocalService _vwUserTablesLocalService;

    public VwUserTablesLocalServiceWrapper(
        VwUserTablesLocalService vwUserTablesLocalService) {
        _vwUserTablesLocalService = vwUserTablesLocalService;
    }

    /**
    * Adds the vw user tables to the database. Also notifies the appropriate model listeners.
    *
    * @param vwUserTables the vw user tables
    * @return the vw user tables that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.VwUserTables addVwUserTables(
        com.stpl.app.model.VwUserTables vwUserTables)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwUserTablesLocalService.addVwUserTables(vwUserTables);
    }

    /**
    * Creates a new vw user tables with the primary key. Does not add the vw user tables to the database.
    *
    * @param uniqueId the primary key for the new vw user tables
    * @return the new vw user tables
    */
    @Override
    public com.stpl.app.model.VwUserTables createVwUserTables(int uniqueId) {
        return _vwUserTablesLocalService.createVwUserTables(uniqueId);
    }

    /**
    * Deletes the vw user tables with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param uniqueId the primary key of the vw user tables
    * @return the vw user tables that was removed
    * @throws PortalException if a vw user tables with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.VwUserTables deleteVwUserTables(int uniqueId)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _vwUserTablesLocalService.deleteVwUserTables(uniqueId);
    }

    /**
    * Deletes the vw user tables from the database. Also notifies the appropriate model listeners.
    *
    * @param vwUserTables the vw user tables
    * @return the vw user tables that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.VwUserTables deleteVwUserTables(
        com.stpl.app.model.VwUserTables vwUserTables)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwUserTablesLocalService.deleteVwUserTables(vwUserTables);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _vwUserTablesLocalService.dynamicQuery();
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
        return _vwUserTablesLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwUserTablesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _vwUserTablesLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwUserTablesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _vwUserTablesLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _vwUserTablesLocalService.dynamicQueryCount(dynamicQuery);
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
        return _vwUserTablesLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.VwUserTables fetchVwUserTables(int uniqueId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwUserTablesLocalService.fetchVwUserTables(uniqueId);
    }

    /**
    * Returns the vw user tables with the primary key.
    *
    * @param uniqueId the primary key of the vw user tables
    * @return the vw user tables
    * @throws PortalException if a vw user tables with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.VwUserTables getVwUserTables(int uniqueId)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _vwUserTablesLocalService.getVwUserTables(uniqueId);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _vwUserTablesLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the vw user tableses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwUserTablesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw user tableses
    * @param end the upper bound of the range of vw user tableses (not inclusive)
    * @return the range of vw user tableses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.VwUserTables> getVwUserTableses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwUserTablesLocalService.getVwUserTableses(start, end);
    }

    /**
    * Returns the number of vw user tableses.
    *
    * @return the number of vw user tableses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getVwUserTablesesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwUserTablesLocalService.getVwUserTablesesCount();
    }

    /**
    * Updates the vw user tables in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param vwUserTables the vw user tables
    * @return the vw user tables that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.VwUserTables updateVwUserTables(
        com.stpl.app.model.VwUserTables vwUserTables)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwUserTablesLocalService.updateVwUserTables(vwUserTables);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _vwUserTablesLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _vwUserTablesLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _vwUserTablesLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public VwUserTablesLocalService getWrappedVwUserTablesLocalService() {
        return _vwUserTablesLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedVwUserTablesLocalService(
        VwUserTablesLocalService vwUserTablesLocalService) {
        _vwUserTablesLocalService = vwUserTablesLocalService;
    }

    @Override
    public VwUserTablesLocalService getWrappedService() {
        return _vwUserTablesLocalService;
    }

    @Override
    public void setWrappedService(
        VwUserTablesLocalService vwUserTablesLocalService) {
        _vwUserTablesLocalService = vwUserTablesLocalService;
    }
}
