package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link HierarchyDefinitionLocalService}.
 *
 * @author
 * @see HierarchyDefinitionLocalService
 * @generated
 */
public class HierarchyDefinitionLocalServiceWrapper
    implements HierarchyDefinitionLocalService,
        ServiceWrapper<HierarchyDefinitionLocalService> {
    private HierarchyDefinitionLocalService _hierarchyDefinitionLocalService;

    public HierarchyDefinitionLocalServiceWrapper(
        HierarchyDefinitionLocalService hierarchyDefinitionLocalService) {
        _hierarchyDefinitionLocalService = hierarchyDefinitionLocalService;
    }

    /**
    * Adds the hierarchy definition to the database. Also notifies the appropriate model listeners.
    *
    * @param hierarchyDefinition the hierarchy definition
    * @return the hierarchy definition that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HierarchyDefinition addHierarchyDefinition(
        com.stpl.app.model.HierarchyDefinition hierarchyDefinition)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _hierarchyDefinitionLocalService.addHierarchyDefinition(hierarchyDefinition);
    }

    /**
    * Creates a new hierarchy definition with the primary key. Does not add the hierarchy definition to the database.
    *
    * @param hierarchyDefinitionSid the primary key for the new hierarchy definition
    * @return the new hierarchy definition
    */
    @Override
    public com.stpl.app.model.HierarchyDefinition createHierarchyDefinition(
        int hierarchyDefinitionSid) {
        return _hierarchyDefinitionLocalService.createHierarchyDefinition(hierarchyDefinitionSid);
    }

    /**
    * Deletes the hierarchy definition with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param hierarchyDefinitionSid the primary key of the hierarchy definition
    * @return the hierarchy definition that was removed
    * @throws PortalException if a hierarchy definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HierarchyDefinition deleteHierarchyDefinition(
        int hierarchyDefinitionSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _hierarchyDefinitionLocalService.deleteHierarchyDefinition(hierarchyDefinitionSid);
    }

    /**
    * Deletes the hierarchy definition from the database. Also notifies the appropriate model listeners.
    *
    * @param hierarchyDefinition the hierarchy definition
    * @return the hierarchy definition that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HierarchyDefinition deleteHierarchyDefinition(
        com.stpl.app.model.HierarchyDefinition hierarchyDefinition)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _hierarchyDefinitionLocalService.deleteHierarchyDefinition(hierarchyDefinition);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _hierarchyDefinitionLocalService.dynamicQuery();
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
        return _hierarchyDefinitionLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _hierarchyDefinitionLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _hierarchyDefinitionLocalService.dynamicQuery(dynamicQuery,
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
        return _hierarchyDefinitionLocalService.dynamicQueryCount(dynamicQuery);
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
        return _hierarchyDefinitionLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.HierarchyDefinition fetchHierarchyDefinition(
        int hierarchyDefinitionSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _hierarchyDefinitionLocalService.fetchHierarchyDefinition(hierarchyDefinitionSid);
    }

    /**
    * Returns the hierarchy definition with the primary key.
    *
    * @param hierarchyDefinitionSid the primary key of the hierarchy definition
    * @return the hierarchy definition
    * @throws PortalException if a hierarchy definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HierarchyDefinition getHierarchyDefinition(
        int hierarchyDefinitionSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _hierarchyDefinitionLocalService.getHierarchyDefinition(hierarchyDefinitionSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _hierarchyDefinitionLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the hierarchy definitions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hierarchy definitions
    * @param end the upper bound of the range of hierarchy definitions (not inclusive)
    * @return the range of hierarchy definitions
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.HierarchyDefinition> getHierarchyDefinitions(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _hierarchyDefinitionLocalService.getHierarchyDefinitions(start,
            end);
    }

    /**
    * Returns the number of hierarchy definitions.
    *
    * @return the number of hierarchy definitions
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getHierarchyDefinitionsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _hierarchyDefinitionLocalService.getHierarchyDefinitionsCount();
    }

    /**
    * Updates the hierarchy definition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param hierarchyDefinition the hierarchy definition
    * @return the hierarchy definition that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HierarchyDefinition updateHierarchyDefinition(
        com.stpl.app.model.HierarchyDefinition hierarchyDefinition)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _hierarchyDefinitionLocalService.updateHierarchyDefinition(hierarchyDefinition);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _hierarchyDefinitionLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _hierarchyDefinitionLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _hierarchyDefinitionLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    @Override
    public java.util.List getHierarchyGroup(java.lang.String hierarchyName,
        java.lang.String hierarchyType, java.lang.String customerOrProduct,
        java.lang.String action) {
        return _hierarchyDefinitionLocalService.getHierarchyGroup(hierarchyName,
            hierarchyType, customerOrProduct, action);
    }

    @Override
    public java.util.List getLevelsFromHierarchy(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return _hierarchyDefinitionLocalService.getLevelsFromHierarchy(parameters);
    }

    @Override
    public java.util.List getHierarchySystemId(int relationshipLevelId) {
        return _hierarchyDefinitionLocalService.getHierarchySystemId(relationshipLevelId);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public HierarchyDefinitionLocalService getWrappedHierarchyDefinitionLocalService() {
        return _hierarchyDefinitionLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedHierarchyDefinitionLocalService(
        HierarchyDefinitionLocalService hierarchyDefinitionLocalService) {
        _hierarchyDefinitionLocalService = hierarchyDefinitionLocalService;
    }

    @Override
    public HierarchyDefinitionLocalService getWrappedService() {
        return _hierarchyDefinitionLocalService;
    }

    @Override
    public void setWrappedService(
        HierarchyDefinitionLocalService hierarchyDefinitionLocalService) {
        _hierarchyDefinitionLocalService = hierarchyDefinitionLocalService;
    }
}
