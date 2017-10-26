package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link StSelectionTableLocalService}.
 *
 * @author
 * @see StSelectionTableLocalService
 * @generated
 */
public class StSelectionTableLocalServiceWrapper
    implements StSelectionTableLocalService,
        ServiceWrapper<StSelectionTableLocalService> {
    private StSelectionTableLocalService _stSelectionTableLocalService;

    public StSelectionTableLocalServiceWrapper(
        StSelectionTableLocalService stSelectionTableLocalService) {
        _stSelectionTableLocalService = stSelectionTableLocalService;
    }

    /**
    * Adds the st selection table to the database. Also notifies the appropriate model listeners.
    *
    * @param stSelectionTable the st selection table
    * @return the st selection table that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StSelectionTable addStSelectionTable(
        com.stpl.app.model.StSelectionTable stSelectionTable)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stSelectionTableLocalService.addStSelectionTable(stSelectionTable);
    }

    /**
    * Creates a new st selection table with the primary key. Does not add the st selection table to the database.
    *
    * @param stSelectionTablePK the primary key for the new st selection table
    * @return the new st selection table
    */
    @Override
    public com.stpl.app.model.StSelectionTable createStSelectionTable(
        com.stpl.app.service.persistence.StSelectionTablePK stSelectionTablePK) {
        return _stSelectionTableLocalService.createStSelectionTable(stSelectionTablePK);
    }

    /**
    * Deletes the st selection table with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stSelectionTablePK the primary key of the st selection table
    * @return the st selection table that was removed
    * @throws PortalException if a st selection table with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StSelectionTable deleteStSelectionTable(
        com.stpl.app.service.persistence.StSelectionTablePK stSelectionTablePK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stSelectionTableLocalService.deleteStSelectionTable(stSelectionTablePK);
    }

    /**
    * Deletes the st selection table from the database. Also notifies the appropriate model listeners.
    *
    * @param stSelectionTable the st selection table
    * @return the st selection table that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StSelectionTable deleteStSelectionTable(
        com.stpl.app.model.StSelectionTable stSelectionTable)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stSelectionTableLocalService.deleteStSelectionTable(stSelectionTable);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _stSelectionTableLocalService.dynamicQuery();
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
        return _stSelectionTableLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StSelectionTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _stSelectionTableLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StSelectionTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _stSelectionTableLocalService.dynamicQuery(dynamicQuery, start,
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
        return _stSelectionTableLocalService.dynamicQueryCount(dynamicQuery);
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
        return _stSelectionTableLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.StSelectionTable fetchStSelectionTable(
        com.stpl.app.service.persistence.StSelectionTablePK stSelectionTablePK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stSelectionTableLocalService.fetchStSelectionTable(stSelectionTablePK);
    }

    /**
    * Returns the st selection table with the primary key.
    *
    * @param stSelectionTablePK the primary key of the st selection table
    * @return the st selection table
    * @throws PortalException if a st selection table with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StSelectionTable getStSelectionTable(
        com.stpl.app.service.persistence.StSelectionTablePK stSelectionTablePK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stSelectionTableLocalService.getStSelectionTable(stSelectionTablePK);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stSelectionTableLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the st selection tables.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StSelectionTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st selection tables
    * @param end the upper bound of the range of st selection tables (not inclusive)
    * @return the range of st selection tables
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.StSelectionTable> getStSelectionTables(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stSelectionTableLocalService.getStSelectionTables(start, end);
    }

    /**
    * Returns the number of st selection tables.
    *
    * @return the number of st selection tables
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getStSelectionTablesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stSelectionTableLocalService.getStSelectionTablesCount();
    }

    /**
    * Updates the st selection table in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param stSelectionTable the st selection table
    * @return the st selection table that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StSelectionTable updateStSelectionTable(
        com.stpl.app.model.StSelectionTable stSelectionTable)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stSelectionTableLocalService.updateStSelectionTable(stSelectionTable);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _stSelectionTableLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _stSelectionTableLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _stSelectionTableLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public StSelectionTableLocalService getWrappedStSelectionTableLocalService() {
        return _stSelectionTableLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedStSelectionTableLocalService(
        StSelectionTableLocalService stSelectionTableLocalService) {
        _stSelectionTableLocalService = stSelectionTableLocalService;
    }

    @Override
    public StSelectionTableLocalService getWrappedService() {
        return _stSelectionTableLocalService;
    }

    @Override
    public void setWrappedService(
        StSelectionTableLocalService stSelectionTableLocalService) {
        _stSelectionTableLocalService = stSelectionTableLocalService;
    }
}
