package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link HistHierarchyDefinitionLocalService}.
 *
 * @author
 * @see HistHierarchyDefinitionLocalService
 * @generated
 */
public class HistHierarchyDefinitionLocalServiceWrapper
    implements HistHierarchyDefinitionLocalService,
        ServiceWrapper<HistHierarchyDefinitionLocalService> {
    private HistHierarchyDefinitionLocalService _histHierarchyDefinitionLocalService;

    public HistHierarchyDefinitionLocalServiceWrapper(
        HistHierarchyDefinitionLocalService histHierarchyDefinitionLocalService) {
        _histHierarchyDefinitionLocalService = histHierarchyDefinitionLocalService;
    }

    /**
    * Adds the hist hierarchy definition to the database. Also notifies the appropriate model listeners.
    *
    * @param histHierarchyDefinition the hist hierarchy definition
    * @return the hist hierarchy definition that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HistHierarchyDefinition addHistHierarchyDefinition(
        com.stpl.app.model.HistHierarchyDefinition histHierarchyDefinition)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _histHierarchyDefinitionLocalService.addHistHierarchyDefinition(histHierarchyDefinition);
    }

    /**
    * Creates a new hist hierarchy definition with the primary key. Does not add the hist hierarchy definition to the database.
    *
    * @param histHierarchyDefinitionPK the primary key for the new hist hierarchy definition
    * @return the new hist hierarchy definition
    */
    @Override
    public com.stpl.app.model.HistHierarchyDefinition createHistHierarchyDefinition(
        com.stpl.app.service.persistence.HistHierarchyDefinitionPK histHierarchyDefinitionPK) {
        return _histHierarchyDefinitionLocalService.createHistHierarchyDefinition(histHierarchyDefinitionPK);
    }

    /**
    * Deletes the hist hierarchy definition with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param histHierarchyDefinitionPK the primary key of the hist hierarchy definition
    * @return the hist hierarchy definition that was removed
    * @throws PortalException if a hist hierarchy definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HistHierarchyDefinition deleteHistHierarchyDefinition(
        com.stpl.app.service.persistence.HistHierarchyDefinitionPK histHierarchyDefinitionPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _histHierarchyDefinitionLocalService.deleteHistHierarchyDefinition(histHierarchyDefinitionPK);
    }

    /**
    * Deletes the hist hierarchy definition from the database. Also notifies the appropriate model listeners.
    *
    * @param histHierarchyDefinition the hist hierarchy definition
    * @return the hist hierarchy definition that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HistHierarchyDefinition deleteHistHierarchyDefinition(
        com.stpl.app.model.HistHierarchyDefinition histHierarchyDefinition)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _histHierarchyDefinitionLocalService.deleteHistHierarchyDefinition(histHierarchyDefinition);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _histHierarchyDefinitionLocalService.dynamicQuery();
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
        return _histHierarchyDefinitionLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistHierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _histHierarchyDefinitionLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistHierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _histHierarchyDefinitionLocalService.dynamicQuery(dynamicQuery,
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
        return _histHierarchyDefinitionLocalService.dynamicQueryCount(dynamicQuery);
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
        return _histHierarchyDefinitionLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.HistHierarchyDefinition fetchHistHierarchyDefinition(
        com.stpl.app.service.persistence.HistHierarchyDefinitionPK histHierarchyDefinitionPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _histHierarchyDefinitionLocalService.fetchHistHierarchyDefinition(histHierarchyDefinitionPK);
    }

    /**
    * Returns the hist hierarchy definition with the primary key.
    *
    * @param histHierarchyDefinitionPK the primary key of the hist hierarchy definition
    * @return the hist hierarchy definition
    * @throws PortalException if a hist hierarchy definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HistHierarchyDefinition getHistHierarchyDefinition(
        com.stpl.app.service.persistence.HistHierarchyDefinitionPK histHierarchyDefinitionPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _histHierarchyDefinitionLocalService.getHistHierarchyDefinition(histHierarchyDefinitionPK);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _histHierarchyDefinitionLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the hist hierarchy definitions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistHierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist hierarchy definitions
    * @param end the upper bound of the range of hist hierarchy definitions (not inclusive)
    * @return the range of hist hierarchy definitions
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.HistHierarchyDefinition> getHistHierarchyDefinitions(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _histHierarchyDefinitionLocalService.getHistHierarchyDefinitions(start,
            end);
    }

    /**
    * Returns the number of hist hierarchy definitions.
    *
    * @return the number of hist hierarchy definitions
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getHistHierarchyDefinitionsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _histHierarchyDefinitionLocalService.getHistHierarchyDefinitionsCount();
    }

    /**
    * Updates the hist hierarchy definition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param histHierarchyDefinition the hist hierarchy definition
    * @return the hist hierarchy definition that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.HistHierarchyDefinition updateHistHierarchyDefinition(
        com.stpl.app.model.HistHierarchyDefinition histHierarchyDefinition)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _histHierarchyDefinitionLocalService.updateHistHierarchyDefinition(histHierarchyDefinition);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _histHierarchyDefinitionLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _histHierarchyDefinitionLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _histHierarchyDefinitionLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public HistHierarchyDefinitionLocalService getWrappedHistHierarchyDefinitionLocalService() {
        return _histHierarchyDefinitionLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedHistHierarchyDefinitionLocalService(
        HistHierarchyDefinitionLocalService histHierarchyDefinitionLocalService) {
        _histHierarchyDefinitionLocalService = histHierarchyDefinitionLocalService;
    }

    @Override
    public HistHierarchyDefinitionLocalService getWrappedService() {
        return _histHierarchyDefinitionLocalService;
    }

    @Override
    public void setWrappedService(
        HistHierarchyDefinitionLocalService histHierarchyDefinitionLocalService) {
        _histHierarchyDefinitionLocalService = histHierarchyDefinitionLocalService;
    }
}
