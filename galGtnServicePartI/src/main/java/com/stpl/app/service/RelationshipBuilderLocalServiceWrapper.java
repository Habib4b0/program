package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link RelationshipBuilderLocalService}.
 *
 * @author
 * @see RelationshipBuilderLocalService
 * @generated
 */
public class RelationshipBuilderLocalServiceWrapper
    implements RelationshipBuilderLocalService,
        ServiceWrapper<RelationshipBuilderLocalService> {
    private RelationshipBuilderLocalService _relationshipBuilderLocalService;

    public RelationshipBuilderLocalServiceWrapper(
        RelationshipBuilderLocalService relationshipBuilderLocalService) {
        _relationshipBuilderLocalService = relationshipBuilderLocalService;
    }

    /**
    * Adds the relationship builder to the database. Also notifies the appropriate model listeners.
    *
    * @param relationshipBuilder the relationship builder
    * @return the relationship builder that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.RelationshipBuilder addRelationshipBuilder(
        com.stpl.app.model.RelationshipBuilder relationshipBuilder)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _relationshipBuilderLocalService.addRelationshipBuilder(relationshipBuilder);
    }

    /**
    * Creates a new relationship builder with the primary key. Does not add the relationship builder to the database.
    *
    * @param relationshipBuilderSid the primary key for the new relationship builder
    * @return the new relationship builder
    */
    @Override
    public com.stpl.app.model.RelationshipBuilder createRelationshipBuilder(
        int relationshipBuilderSid) {
        return _relationshipBuilderLocalService.createRelationshipBuilder(relationshipBuilderSid);
    }

    /**
    * Deletes the relationship builder with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param relationshipBuilderSid the primary key of the relationship builder
    * @return the relationship builder that was removed
    * @throws PortalException if a relationship builder with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.RelationshipBuilder deleteRelationshipBuilder(
        int relationshipBuilderSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _relationshipBuilderLocalService.deleteRelationshipBuilder(relationshipBuilderSid);
    }

    /**
    * Deletes the relationship builder from the database. Also notifies the appropriate model listeners.
    *
    * @param relationshipBuilder the relationship builder
    * @return the relationship builder that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.RelationshipBuilder deleteRelationshipBuilder(
        com.stpl.app.model.RelationshipBuilder relationshipBuilder)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _relationshipBuilderLocalService.deleteRelationshipBuilder(relationshipBuilder);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _relationshipBuilderLocalService.dynamicQuery();
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
        return _relationshipBuilderLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RelationshipBuilderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _relationshipBuilderLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RelationshipBuilderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _relationshipBuilderLocalService.dynamicQuery(dynamicQuery,
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
        return _relationshipBuilderLocalService.dynamicQueryCount(dynamicQuery);
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
        return _relationshipBuilderLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.RelationshipBuilder fetchRelationshipBuilder(
        int relationshipBuilderSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _relationshipBuilderLocalService.fetchRelationshipBuilder(relationshipBuilderSid);
    }

    /**
    * Returns the relationship builder with the primary key.
    *
    * @param relationshipBuilderSid the primary key of the relationship builder
    * @return the relationship builder
    * @throws PortalException if a relationship builder with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.RelationshipBuilder getRelationshipBuilder(
        int relationshipBuilderSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _relationshipBuilderLocalService.getRelationshipBuilder(relationshipBuilderSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _relationshipBuilderLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the relationship builders.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RelationshipBuilderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of relationship builders
    * @param end the upper bound of the range of relationship builders (not inclusive)
    * @return the range of relationship builders
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.RelationshipBuilder> getRelationshipBuilders(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _relationshipBuilderLocalService.getRelationshipBuilders(start,
            end);
    }

    /**
    * Returns the number of relationship builders.
    *
    * @return the number of relationship builders
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getRelationshipBuildersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _relationshipBuilderLocalService.getRelationshipBuildersCount();
    }

    /**
    * Updates the relationship builder in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param relationshipBuilder the relationship builder
    * @return the relationship builder that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.RelationshipBuilder updateRelationshipBuilder(
        com.stpl.app.model.RelationshipBuilder relationshipBuilder)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _relationshipBuilderLocalService.updateRelationshipBuilder(relationshipBuilder);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _relationshipBuilderLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _relationshipBuilderLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _relationshipBuilderLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    @Override
    public java.util.List findByTableName(java.lang.String tableName,
        java.lang.String columnName)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _relationshipBuilderLocalService.findByTableName(tableName,
            columnName);
    }

    @Override
    public java.util.List findByTableName(java.lang.String tableName,
        java.lang.String columnName, java.util.List hierValues)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _relationshipBuilderLocalService.findByTableName(tableName,
            columnName, hierValues);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public RelationshipBuilderLocalService getWrappedRelationshipBuilderLocalService() {
        return _relationshipBuilderLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedRelationshipBuilderLocalService(
        RelationshipBuilderLocalService relationshipBuilderLocalService) {
        _relationshipBuilderLocalService = relationshipBuilderLocalService;
    }

    @Override
    public RelationshipBuilderLocalService getWrappedService() {
        return _relationshipBuilderLocalService;
    }

    @Override
    public void setWrappedService(
        RelationshipBuilderLocalService relationshipBuilderLocalService) {
        _relationshipBuilderLocalService = relationshipBuilderLocalService;
    }
}
