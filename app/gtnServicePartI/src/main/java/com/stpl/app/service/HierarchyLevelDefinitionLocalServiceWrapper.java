package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link HierarchyLevelDefinitionLocalService}.
 *
 * @author
 * @see HierarchyLevelDefinitionLocalService
 * @generated
 */
public class HierarchyLevelDefinitionLocalServiceWrapper
    implements HierarchyLevelDefinitionLocalService,
        ServiceWrapper<HierarchyLevelDefinitionLocalService> {
    private HierarchyLevelDefinitionLocalService _hierarchyLevelDefinitionLocalService;

    public HierarchyLevelDefinitionLocalServiceWrapper(
        HierarchyLevelDefinitionLocalService hierarchyLevelDefinitionLocalService) {
        _hierarchyLevelDefinitionLocalService = hierarchyLevelDefinitionLocalService;
    }

    /**
    * Adds the hierarchy level definition to the database. Also notifies the appropriate model listeners.
    *
    * @param hierarchyLevelDefinition the hierarchy level definition
    * @return the hierarchy level definition that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HierarchyLevelDefinition addHierarchyLevelDefinition(
        com.stpl.app.model.HierarchyLevelDefinition hierarchyLevelDefinition)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _hierarchyLevelDefinitionLocalService.addHierarchyLevelDefinition(hierarchyLevelDefinition);
    }

    /**
    * Creates a new hierarchy level definition with the primary key. Does not add the hierarchy level definition to the database.
    *
    * @param hierarchyLevelDefinitionSid the primary key for the new hierarchy level definition
    * @return the new hierarchy level definition
    */
    @Override
    public com.stpl.app.model.HierarchyLevelDefinition createHierarchyLevelDefinition(
        int hierarchyLevelDefinitionSid) {
        return _hierarchyLevelDefinitionLocalService.createHierarchyLevelDefinition(hierarchyLevelDefinitionSid);
    }

    /**
    * Deletes the hierarchy level definition with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param hierarchyLevelDefinitionSid the primary key of the hierarchy level definition
    * @return the hierarchy level definition that was removed
    * @throws PortalException if a hierarchy level definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HierarchyLevelDefinition deleteHierarchyLevelDefinition(
        int hierarchyLevelDefinitionSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _hierarchyLevelDefinitionLocalService.deleteHierarchyLevelDefinition(hierarchyLevelDefinitionSid);
    }

    /**
    * Deletes the hierarchy level definition from the database. Also notifies the appropriate model listeners.
    *
    * @param hierarchyLevelDefinition the hierarchy level definition
    * @return the hierarchy level definition that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HierarchyLevelDefinition deleteHierarchyLevelDefinition(
        com.stpl.app.model.HierarchyLevelDefinition hierarchyLevelDefinition)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _hierarchyLevelDefinitionLocalService.deleteHierarchyLevelDefinition(hierarchyLevelDefinition);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _hierarchyLevelDefinitionLocalService.dynamicQuery();
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
        return _hierarchyLevelDefinitionLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HierarchyLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _hierarchyLevelDefinitionLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HierarchyLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _hierarchyLevelDefinitionLocalService.dynamicQuery(dynamicQuery,
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
        return _hierarchyLevelDefinitionLocalService.dynamicQueryCount(dynamicQuery);
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
        return _hierarchyLevelDefinitionLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.HierarchyLevelDefinition fetchHierarchyLevelDefinition(
        int hierarchyLevelDefinitionSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _hierarchyLevelDefinitionLocalService.fetchHierarchyLevelDefinition(hierarchyLevelDefinitionSid);
    }

    /**
    * Returns the hierarchy level definition with the primary key.
    *
    * @param hierarchyLevelDefinitionSid the primary key of the hierarchy level definition
    * @return the hierarchy level definition
    * @throws PortalException if a hierarchy level definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HierarchyLevelDefinition getHierarchyLevelDefinition(
        int hierarchyLevelDefinitionSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _hierarchyLevelDefinitionLocalService.getHierarchyLevelDefinition(hierarchyLevelDefinitionSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _hierarchyLevelDefinitionLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the hierarchy level definitions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HierarchyLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hierarchy level definitions
    * @param end the upper bound of the range of hierarchy level definitions (not inclusive)
    * @return the range of hierarchy level definitions
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.HierarchyLevelDefinition> getHierarchyLevelDefinitions(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _hierarchyLevelDefinitionLocalService.getHierarchyLevelDefinitions(start,
            end);
    }

    /**
    * Returns the number of hierarchy level definitions.
    *
    * @return the number of hierarchy level definitions
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getHierarchyLevelDefinitionsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _hierarchyLevelDefinitionLocalService.getHierarchyLevelDefinitionsCount();
    }

    /**
    * Updates the hierarchy level definition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param hierarchyLevelDefinition the hierarchy level definition
    * @return the hierarchy level definition that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HierarchyLevelDefinition updateHierarchyLevelDefinition(
        com.stpl.app.model.HierarchyLevelDefinition hierarchyLevelDefinition)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _hierarchyLevelDefinitionLocalService.updateHierarchyLevelDefinition(hierarchyLevelDefinition);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _hierarchyLevelDefinitionLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _hierarchyLevelDefinitionLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _hierarchyLevelDefinitionLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public HierarchyLevelDefinitionLocalService getWrappedHierarchyLevelDefinitionLocalService() {
        return _hierarchyLevelDefinitionLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedHierarchyLevelDefinitionLocalService(
        HierarchyLevelDefinitionLocalService hierarchyLevelDefinitionLocalService) {
        _hierarchyLevelDefinitionLocalService = hierarchyLevelDefinitionLocalService;
    }

    @Override
    public HierarchyLevelDefinitionLocalService getWrappedService() {
        return _hierarchyLevelDefinitionLocalService;
    }

    @Override
    public void setWrappedService(
        HierarchyLevelDefinitionLocalService hierarchyLevelDefinitionLocalService) {
        _hierarchyLevelDefinitionLocalService = hierarchyLevelDefinitionLocalService;
    }
}
