package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProjectionMasterLocalService}.
 *
 * @author
 * @see ProjectionMasterLocalService
 * @generated
 */
public class ProjectionMasterLocalServiceWrapper
    implements ProjectionMasterLocalService,
        ServiceWrapper<ProjectionMasterLocalService> {
    private ProjectionMasterLocalService _projectionMasterLocalService;

    public ProjectionMasterLocalServiceWrapper(
        ProjectionMasterLocalService projectionMasterLocalService) {
        _projectionMasterLocalService = projectionMasterLocalService;
    }

    /**
    * Adds the projection master to the database. Also notifies the appropriate model listeners.
    *
    * @param projectionMaster the projection master
    * @return the projection master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ProjectionMaster addProjectionMaster(
        com.stpl.app.model.ProjectionMaster projectionMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _projectionMasterLocalService.addProjectionMaster(projectionMaster);
    }

    /**
    * Creates a new projection master with the primary key. Does not add the projection master to the database.
    *
    * @param projectionMasterSid the primary key for the new projection master
    * @return the new projection master
    */
    @Override
    public com.stpl.app.model.ProjectionMaster createProjectionMaster(
        int projectionMasterSid) {
        return _projectionMasterLocalService.createProjectionMaster(projectionMasterSid);
    }

    /**
    * Deletes the projection master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param projectionMasterSid the primary key of the projection master
    * @return the projection master that was removed
    * @throws PortalException if a projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ProjectionMaster deleteProjectionMaster(
        int projectionMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _projectionMasterLocalService.deleteProjectionMaster(projectionMasterSid);
    }

    /**
    * Deletes the projection master from the database. Also notifies the appropriate model listeners.
    *
    * @param projectionMaster the projection master
    * @return the projection master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ProjectionMaster deleteProjectionMaster(
        com.stpl.app.model.ProjectionMaster projectionMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _projectionMasterLocalService.deleteProjectionMaster(projectionMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _projectionMasterLocalService.dynamicQuery();
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
        return _projectionMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _projectionMasterLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _projectionMasterLocalService.dynamicQuery(dynamicQuery, start,
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
        return _projectionMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _projectionMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.ProjectionMaster fetchProjectionMaster(
        int projectionMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _projectionMasterLocalService.fetchProjectionMaster(projectionMasterSid);
    }

    /**
    * Returns the projection master with the primary key.
    *
    * @param projectionMasterSid the primary key of the projection master
    * @return the projection master
    * @throws PortalException if a projection master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ProjectionMaster getProjectionMaster(
        int projectionMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _projectionMasterLocalService.getProjectionMaster(projectionMasterSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _projectionMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the projection masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of projection masters
    * @param end the upper bound of the range of projection masters (not inclusive)
    * @return the range of projection masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.ProjectionMaster> getProjectionMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _projectionMasterLocalService.getProjectionMasters(start, end);
    }

    /**
    * Returns the number of projection masters.
    *
    * @return the number of projection masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getProjectionMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _projectionMasterLocalService.getProjectionMastersCount();
    }

    /**
    * Updates the projection master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param projectionMaster the projection master
    * @return the projection master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ProjectionMaster updateProjectionMaster(
        com.stpl.app.model.ProjectionMaster projectionMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _projectionMasterLocalService.updateProjectionMaster(projectionMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _projectionMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _projectionMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _projectionMasterLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    @Override
    public java.util.List getRelationshipHierarchy(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return _projectionMasterLocalService.getRelationshipHierarchy(parameters);
    }

    @Override
    public java.util.List getCcpDetails(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return _projectionMasterLocalService.getCcpDetails(parameters);
    }

    @Override
    public java.util.List getCustomerProductGroup(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return _projectionMasterLocalService.getCustomerProductGroup(parameters);
    }

    @Override
    public java.util.List searchDsProjection(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return _projectionMasterLocalService.searchDsProjection(parameters);
    }

    @Override
    public java.util.List getProjection(int projectionId) {
        return _projectionMasterLocalService.getProjection(projectionId);
    }

    @Override
    public java.util.List getRelationShipValues(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return _projectionMasterLocalService.getRelationShipValues(parameters);
    }

    @Override
    public java.lang.String deleteProjection(java.lang.String TableName,
        int projectionID) {
        return _projectionMasterLocalService.deleteProjection(TableName,
            projectionID);
    }

    @Override
    public java.util.List getParentLevels(int levelNo,
        int relationshipLevelSid,
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return _projectionMasterLocalService.getParentLevels(levelNo,
            relationshipLevelSid, parameters);
    }

    @Override
    public java.util.List executeQuery(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return _projectionMasterLocalService.executeQuery(parameters);
    }

    @Override
    public java.util.List getItemsFromBrand(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return _projectionMasterLocalService.getItemsFromBrand(parameters);
    }

    @Override
    public java.util.List getInnerLevel(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return _projectionMasterLocalService.getInnerLevel(parameters);
    }

    @Override
    public void saveCcp(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        _projectionMasterLocalService.saveCcp(parameters);
    }

    @Override
    public void saveCcpForMandated(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        _projectionMasterLocalService.saveCcpForMandated(parameters);
    }

    @Override
    public java.util.List getCcpMap(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return _projectionMasterLocalService.getCcpMap(parameters);
    }

    @Override
    public java.util.List getLevelCcp(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return _projectionMasterLocalService.getLevelCcp(parameters);
    }

    @Override
    public java.lang.Object tempOperation(
        java.util.Map<java.lang.String, java.lang.Object> input,
        java.lang.String queryName) {
        return _projectionMasterLocalService.tempOperation(input, queryName);
    }

    @Override
    public java.util.List getChildLevels(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return _projectionMasterLocalService.getChildLevels(parameters);
    }

    @Override
    public java.util.List getRelationShipValuesforchannel(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return _projectionMasterLocalService.getRelationShipValuesforchannel(parameters);
    }

    @Override
    public java.util.List searchProjections(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return _projectionMasterLocalService.searchProjections(parameters);
    }

    @Override
    public int getForecastViewCount(java.lang.String viewName,
        java.lang.String viewType) {
        return _projectionMasterLocalService.getForecastViewCount(viewName,
            viewType);
    }

    @Override
    public java.util.List executeQueryforchannel(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return _projectionMasterLocalService.executeQueryforchannel(parameters);
    }

    @Override
    public java.util.List getCcpMapforchannel(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return _projectionMasterLocalService.getCcpMapforchannel(parameters);
    }

    @Override
    public void saveCcpforchannel(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        _projectionMasterLocalService.saveCcpforchannel(parameters);
    }

    @Override
    public java.util.List getCustomerProductGroupforchannel(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return _projectionMasterLocalService.getCustomerProductGroupforchannel(parameters);
    }

    @Override
    public java.util.List getInnerLevelforchannel(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return _projectionMasterLocalService.getInnerLevelforchannel(parameters);
    }

    @Override
    public java.util.List getParentLevelsforchannel(int levelNo,
        int relationshipLevelSid,
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return _projectionMasterLocalService.getParentLevelsforchannel(levelNo,
            relationshipLevelSid, parameters);
    }

    @Override
    public java.util.List getChildLevelsforchannel(
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return _projectionMasterLocalService.getChildLevelsforchannel(parameters);
    }

    @Override
    public java.util.List getProjectionforchannel(int projectionId) {
        return _projectionMasterLocalService.getProjectionforchannel(projectionId);
    }

    @Override
    public java.lang.String deleteProjectionforchannel(
        java.lang.String tableName, int projectionID) {
        return _projectionMasterLocalService.deleteProjectionforchannel(tableName,
            projectionID);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ProjectionMasterLocalService getWrappedProjectionMasterLocalService() {
        return _projectionMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedProjectionMasterLocalService(
        ProjectionMasterLocalService projectionMasterLocalService) {
        _projectionMasterLocalService = projectionMasterLocalService;
    }

    @Override
    public ProjectionMasterLocalService getWrappedService() {
        return _projectionMasterLocalService;
    }

    @Override
    public void setWrappedService(
        ProjectionMasterLocalService projectionMasterLocalService) {
        _projectionMasterLocalService = projectionMasterLocalService;
    }
}
