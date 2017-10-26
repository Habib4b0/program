package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link RelationshipLevelDefinitionLocalService}.
 *
 * @author
 * @see RelationshipLevelDefinitionLocalService
 * @generated
 */
public class RelationshipLevelDefinitionLocalServiceWrapper
    implements RelationshipLevelDefinitionLocalService,
        ServiceWrapper<RelationshipLevelDefinitionLocalService> {
    private RelationshipLevelDefinitionLocalService _relationshipLevelDefinitionLocalService;

    public RelationshipLevelDefinitionLocalServiceWrapper(
        RelationshipLevelDefinitionLocalService relationshipLevelDefinitionLocalService) {
        _relationshipLevelDefinitionLocalService = relationshipLevelDefinitionLocalService;
    }

    /**
    * Adds the relationship level definition to the database. Also notifies the appropriate model listeners.
    *
    * @param relationshipLevelDefinition the relationship level definition
    * @return the relationship level definition that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.RelationshipLevelDefinition addRelationshipLevelDefinition(
        com.stpl.app.model.RelationshipLevelDefinition relationshipLevelDefinition)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _relationshipLevelDefinitionLocalService.addRelationshipLevelDefinition(relationshipLevelDefinition);
    }

    /**
    * Creates a new relationship level definition with the primary key. Does not add the relationship level definition to the database.
    *
    * @param relationshipLevelSid the primary key for the new relationship level definition
    * @return the new relationship level definition
    */
    @Override
    public com.stpl.app.model.RelationshipLevelDefinition createRelationshipLevelDefinition(
        int relationshipLevelSid) {
        return _relationshipLevelDefinitionLocalService.createRelationshipLevelDefinition(relationshipLevelSid);
    }

    /**
    * Deletes the relationship level definition with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param relationshipLevelSid the primary key of the relationship level definition
    * @return the relationship level definition that was removed
    * @throws PortalException if a relationship level definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.RelationshipLevelDefinition deleteRelationshipLevelDefinition(
        int relationshipLevelSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _relationshipLevelDefinitionLocalService.deleteRelationshipLevelDefinition(relationshipLevelSid);
    }

    /**
    * Deletes the relationship level definition from the database. Also notifies the appropriate model listeners.
    *
    * @param relationshipLevelDefinition the relationship level definition
    * @return the relationship level definition that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.RelationshipLevelDefinition deleteRelationshipLevelDefinition(
        com.stpl.app.model.RelationshipLevelDefinition relationshipLevelDefinition)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _relationshipLevelDefinitionLocalService.deleteRelationshipLevelDefinition(relationshipLevelDefinition);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _relationshipLevelDefinitionLocalService.dynamicQuery();
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
        return _relationshipLevelDefinitionLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RelationshipLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _relationshipLevelDefinitionLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RelationshipLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _relationshipLevelDefinitionLocalService.dynamicQuery(dynamicQuery,
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
        return _relationshipLevelDefinitionLocalService.dynamicQueryCount(dynamicQuery);
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
        return _relationshipLevelDefinitionLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.RelationshipLevelDefinition fetchRelationshipLevelDefinition(
        int relationshipLevelSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _relationshipLevelDefinitionLocalService.fetchRelationshipLevelDefinition(relationshipLevelSid);
    }

    /**
    * Returns the relationship level definition with the primary key.
    *
    * @param relationshipLevelSid the primary key of the relationship level definition
    * @return the relationship level definition
    * @throws PortalException if a relationship level definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.RelationshipLevelDefinition getRelationshipLevelDefinition(
        int relationshipLevelSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _relationshipLevelDefinitionLocalService.getRelationshipLevelDefinition(relationshipLevelSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _relationshipLevelDefinitionLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the relationship level definitions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RelationshipLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of relationship level definitions
    * @param end the upper bound of the range of relationship level definitions (not inclusive)
    * @return the range of relationship level definitions
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.RelationshipLevelDefinition> getRelationshipLevelDefinitions(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _relationshipLevelDefinitionLocalService.getRelationshipLevelDefinitions(start,
            end);
    }

    /**
    * Returns the number of relationship level definitions.
    *
    * @return the number of relationship level definitions
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getRelationshipLevelDefinitionsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _relationshipLevelDefinitionLocalService.getRelationshipLevelDefinitionsCount();
    }

    /**
    * Updates the relationship level definition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param relationshipLevelDefinition the relationship level definition
    * @return the relationship level definition that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.RelationshipLevelDefinition updateRelationshipLevelDefinition(
        com.stpl.app.model.RelationshipLevelDefinition relationshipLevelDefinition)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _relationshipLevelDefinitionLocalService.updateRelationshipLevelDefinition(relationshipLevelDefinition);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _relationshipLevelDefinitionLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _relationshipLevelDefinitionLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _relationshipLevelDefinitionLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public RelationshipLevelDefinitionLocalService getWrappedRelationshipLevelDefinitionLocalService() {
        return _relationshipLevelDefinitionLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedRelationshipLevelDefinitionLocalService(
        RelationshipLevelDefinitionLocalService relationshipLevelDefinitionLocalService) {
        _relationshipLevelDefinitionLocalService = relationshipLevelDefinitionLocalService;
    }

    @Override
    public RelationshipLevelDefinitionLocalService getWrappedService() {
        return _relationshipLevelDefinitionLocalService;
    }

    @Override
    public void setWrappedService(
        RelationshipLevelDefinitionLocalService relationshipLevelDefinitionLocalService) {
        _relationshipLevelDefinitionLocalService = relationshipLevelDefinitionLocalService;
    }
}
