package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link HelperTableLocalService}.
 *
 * @author
 * @see HelperTableLocalService
 * @generated
 */
public class HelperTableLocalServiceWrapper implements HelperTableLocalService,
    ServiceWrapper<HelperTableLocalService> {
    private HelperTableLocalService _helperTableLocalService;

    public HelperTableLocalServiceWrapper(
        HelperTableLocalService helperTableLocalService) {
        _helperTableLocalService = helperTableLocalService;
    }

    /**
    * Adds the helper table to the database. Also notifies the appropriate model listeners.
    *
    * @param helperTable the helper table
    * @return the helper table that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HelperTable addHelperTable(
        com.stpl.app.model.HelperTable helperTable)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _helperTableLocalService.addHelperTable(helperTable);
    }

    /**
    * Creates a new helper table with the primary key. Does not add the helper table to the database.
    *
    * @param helperTableSid the primary key for the new helper table
    * @return the new helper table
    */
    @Override
    public com.stpl.app.model.HelperTable createHelperTable(int helperTableSid) {
        return _helperTableLocalService.createHelperTable(helperTableSid);
    }

    /**
    * Deletes the helper table with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param helperTableSid the primary key of the helper table
    * @return the helper table that was removed
    * @throws PortalException if a helper table with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HelperTable deleteHelperTable(int helperTableSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _helperTableLocalService.deleteHelperTable(helperTableSid);
    }

    /**
    * Deletes the helper table from the database. Also notifies the appropriate model listeners.
    *
    * @param helperTable the helper table
    * @return the helper table that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HelperTable deleteHelperTable(
        com.stpl.app.model.HelperTable helperTable)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _helperTableLocalService.deleteHelperTable(helperTable);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _helperTableLocalService.dynamicQuery();
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
        return _helperTableLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HelperTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _helperTableLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HelperTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _helperTableLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _helperTableLocalService.dynamicQueryCount(dynamicQuery);
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
        return _helperTableLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.HelperTable fetchHelperTable(int helperTableSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _helperTableLocalService.fetchHelperTable(helperTableSid);
    }

    /**
    * Returns the helper table with the primary key.
    *
    * @param helperTableSid the primary key of the helper table
    * @return the helper table
    * @throws PortalException if a helper table with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HelperTable getHelperTable(int helperTableSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _helperTableLocalService.getHelperTable(helperTableSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _helperTableLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the helper tables.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HelperTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of helper tables
    * @param end the upper bound of the range of helper tables (not inclusive)
    * @return the range of helper tables
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.HelperTable> getHelperTables(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _helperTableLocalService.getHelperTables(start, end);
    }

    /**
    * Returns the number of helper tables.
    *
    * @return the number of helper tables
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getHelperTablesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _helperTableLocalService.getHelperTablesCount();
    }

    /**
    * Updates the helper table in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param helperTable the helper table
    * @return the helper table that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HelperTable updateHelperTable(
        com.stpl.app.model.HelperTable helperTable)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _helperTableLocalService.updateHelperTable(helperTable);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _helperTableLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _helperTableLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _helperTableLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    @Override
    public java.util.List<com.stpl.app.model.HelperTable> findByHelperTableDetails(
        java.lang.String listName)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _helperTableLocalService.findByHelperTableDetails(listName);
    }

    /**
    * Custom query to select records from data base
    *
    * @param query - Framed SQL Query
    * @return List<Object[]> result list
    */
    @Override
    public java.util.List executeSelectQuery(java.lang.String query) {
        return _helperTableLocalService.executeSelectQuery(query);
    }

    /**
    * Updates sql statement
    *
    * @param query
    */
    @Override
    public void executeUpdateQuery(java.lang.String query) {
        _helperTableLocalService.executeUpdateQuery(query);
    }

    @Override
    public int executeUpdateQueryCount(java.lang.String query) {
        return _helperTableLocalService.executeUpdateQueryCount(query);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public HelperTableLocalService getWrappedHelperTableLocalService() {
        return _helperTableLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedHelperTableLocalService(
        HelperTableLocalService helperTableLocalService) {
        _helperTableLocalService = helperTableLocalService;
    }

    @Override
    public HelperTableLocalService getWrappedService() {
        return _helperTableLocalService;
    }

    @Override
    public void setWrappedService(
        HelperTableLocalService helperTableLocalService) {
        _helperTableLocalService = helperTableLocalService;
    }
}
