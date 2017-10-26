package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link HistRelationshipBuilderLocalService}.
 *
 * @author
 * @see HistRelationshipBuilderLocalService
 * @generated
 */
public class HistRelationshipBuilderLocalServiceWrapper
    implements HistRelationshipBuilderLocalService,
        ServiceWrapper<HistRelationshipBuilderLocalService> {
    private HistRelationshipBuilderLocalService _histRelationshipBuilderLocalService;

    public HistRelationshipBuilderLocalServiceWrapper(
        HistRelationshipBuilderLocalService histRelationshipBuilderLocalService) {
        _histRelationshipBuilderLocalService = histRelationshipBuilderLocalService;
    }

    /**
    * Adds the hist relationship builder to the database. Also notifies the appropriate model listeners.
    *
    * @param histRelationshipBuilder the hist relationship builder
    * @return the hist relationship builder that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HistRelationshipBuilder addHistRelationshipBuilder(
        com.stpl.app.model.HistRelationshipBuilder histRelationshipBuilder)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _histRelationshipBuilderLocalService.addHistRelationshipBuilder(histRelationshipBuilder);
    }

    /**
    * Creates a new hist relationship builder with the primary key. Does not add the hist relationship builder to the database.
    *
    * @param histRelationshipBuilderPK the primary key for the new hist relationship builder
    * @return the new hist relationship builder
    */
    @Override
    public com.stpl.app.model.HistRelationshipBuilder createHistRelationshipBuilder(
        com.stpl.app.service.persistence.HistRelationshipBuilderPK histRelationshipBuilderPK) {
        return _histRelationshipBuilderLocalService.createHistRelationshipBuilder(histRelationshipBuilderPK);
    }

    /**
    * Deletes the hist relationship builder with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param histRelationshipBuilderPK the primary key of the hist relationship builder
    * @return the hist relationship builder that was removed
    * @throws PortalException if a hist relationship builder with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HistRelationshipBuilder deleteHistRelationshipBuilder(
        com.stpl.app.service.persistence.HistRelationshipBuilderPK histRelationshipBuilderPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _histRelationshipBuilderLocalService.deleteHistRelationshipBuilder(histRelationshipBuilderPK);
    }

    /**
    * Deletes the hist relationship builder from the database. Also notifies the appropriate model listeners.
    *
    * @param histRelationshipBuilder the hist relationship builder
    * @return the hist relationship builder that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HistRelationshipBuilder deleteHistRelationshipBuilder(
        com.stpl.app.model.HistRelationshipBuilder histRelationshipBuilder)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _histRelationshipBuilderLocalService.deleteHistRelationshipBuilder(histRelationshipBuilder);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _histRelationshipBuilderLocalService.dynamicQuery();
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
        return _histRelationshipBuilderLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistRelationshipBuilderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _histRelationshipBuilderLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistRelationshipBuilderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _histRelationshipBuilderLocalService.dynamicQuery(dynamicQuery,
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
        return _histRelationshipBuilderLocalService.dynamicQueryCount(dynamicQuery);
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
        return _histRelationshipBuilderLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.HistRelationshipBuilder fetchHistRelationshipBuilder(
        com.stpl.app.service.persistence.HistRelationshipBuilderPK histRelationshipBuilderPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _histRelationshipBuilderLocalService.fetchHistRelationshipBuilder(histRelationshipBuilderPK);
    }

    /**
    * Returns the hist relationship builder with the primary key.
    *
    * @param histRelationshipBuilderPK the primary key of the hist relationship builder
    * @return the hist relationship builder
    * @throws PortalException if a hist relationship builder with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HistRelationshipBuilder getHistRelationshipBuilder(
        com.stpl.app.service.persistence.HistRelationshipBuilderPK histRelationshipBuilderPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _histRelationshipBuilderLocalService.getHistRelationshipBuilder(histRelationshipBuilderPK);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _histRelationshipBuilderLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the hist relationship builders.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistRelationshipBuilderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist relationship builders
    * @param end the upper bound of the range of hist relationship builders (not inclusive)
    * @return the range of hist relationship builders
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.HistRelationshipBuilder> getHistRelationshipBuilders(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _histRelationshipBuilderLocalService.getHistRelationshipBuilders(start,
            end);
    }

    /**
    * Returns the number of hist relationship builders.
    *
    * @return the number of hist relationship builders
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getHistRelationshipBuildersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _histRelationshipBuilderLocalService.getHistRelationshipBuildersCount();
    }

    /**
    * Updates the hist relationship builder in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param histRelationshipBuilder the hist relationship builder
    * @return the hist relationship builder that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HistRelationshipBuilder updateHistRelationshipBuilder(
        com.stpl.app.model.HistRelationshipBuilder histRelationshipBuilder)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _histRelationshipBuilderLocalService.updateHistRelationshipBuilder(histRelationshipBuilder);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _histRelationshipBuilderLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _histRelationshipBuilderLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _histRelationshipBuilderLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    @Override
    public java.util.List findByTableName(java.lang.String tableName,
        java.lang.String columnName) {
        return _histRelationshipBuilderLocalService.findByTableName(tableName,
            columnName);
    }

    @Override
    public java.util.List findByTableName(java.lang.String tableName,
        java.lang.String columnName, java.util.List hierListValues) {
        return _histRelationshipBuilderLocalService.findByTableName(tableName,
            columnName, hierListValues);
    }

    @Override
    public java.util.List findFilteredLevelValues(java.lang.String query) {
        return _histRelationshipBuilderLocalService.findFilteredLevelValues(query);
    }

    @Override
    public java.util.List executeQuery(java.lang.String query) {
        return _histRelationshipBuilderLocalService.executeQuery(query);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public HistRelationshipBuilderLocalService getWrappedHistRelationshipBuilderLocalService() {
        return _histRelationshipBuilderLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedHistRelationshipBuilderLocalService(
        HistRelationshipBuilderLocalService histRelationshipBuilderLocalService) {
        _histRelationshipBuilderLocalService = histRelationshipBuilderLocalService;
    }

    @Override
    public HistRelationshipBuilderLocalService getWrappedService() {
        return _histRelationshipBuilderLocalService;
    }

    @Override
    public void setWrappedService(
        HistRelationshipBuilderLocalService histRelationshipBuilderLocalService) {
        _histRelationshipBuilderLocalService = histRelationshipBuilderLocalService;
    }
}
