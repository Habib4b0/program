package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProjectionCustHierarchyLocalService}.
 *
 * @author
 * @see ProjectionCustHierarchyLocalService
 * @generated
 */
public class ProjectionCustHierarchyLocalServiceWrapper
    implements ProjectionCustHierarchyLocalService,
        ServiceWrapper<ProjectionCustHierarchyLocalService> {
    private ProjectionCustHierarchyLocalService _projectionCustHierarchyLocalService;

    public ProjectionCustHierarchyLocalServiceWrapper(
        ProjectionCustHierarchyLocalService projectionCustHierarchyLocalService) {
        _projectionCustHierarchyLocalService = projectionCustHierarchyLocalService;
    }

    /**
    * Adds the projection cust hierarchy to the database. Also notifies the appropriate model listeners.
    *
    * @param projectionCustHierarchy the projection cust hierarchy
    * @return the projection cust hierarchy that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ProjectionCustHierarchy addProjectionCustHierarchy(
        com.stpl.app.model.ProjectionCustHierarchy projectionCustHierarchy)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _projectionCustHierarchyLocalService.addProjectionCustHierarchy(projectionCustHierarchy);
    }

    /**
    * Creates a new projection cust hierarchy with the primary key. Does not add the projection cust hierarchy to the database.
    *
    * @param projectionCustHierarchySid the primary key for the new projection cust hierarchy
    * @return the new projection cust hierarchy
    */
    @Override
    public com.stpl.app.model.ProjectionCustHierarchy createProjectionCustHierarchy(
        int projectionCustHierarchySid) {
        return _projectionCustHierarchyLocalService.createProjectionCustHierarchy(projectionCustHierarchySid);
    }

    /**
    * Deletes the projection cust hierarchy with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param projectionCustHierarchySid the primary key of the projection cust hierarchy
    * @return the projection cust hierarchy that was removed
    * @throws PortalException if a projection cust hierarchy with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ProjectionCustHierarchy deleteProjectionCustHierarchy(
        int projectionCustHierarchySid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _projectionCustHierarchyLocalService.deleteProjectionCustHierarchy(projectionCustHierarchySid);
    }

    /**
    * Deletes the projection cust hierarchy from the database. Also notifies the appropriate model listeners.
    *
    * @param projectionCustHierarchy the projection cust hierarchy
    * @return the projection cust hierarchy that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ProjectionCustHierarchy deleteProjectionCustHierarchy(
        com.stpl.app.model.ProjectionCustHierarchy projectionCustHierarchy)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _projectionCustHierarchyLocalService.deleteProjectionCustHierarchy(projectionCustHierarchy);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _projectionCustHierarchyLocalService.dynamicQuery();
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
        return _projectionCustHierarchyLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionCustHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _projectionCustHierarchyLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionCustHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _projectionCustHierarchyLocalService.dynamicQuery(dynamicQuery,
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
        return _projectionCustHierarchyLocalService.dynamicQueryCount(dynamicQuery);
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
        return _projectionCustHierarchyLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.ProjectionCustHierarchy fetchProjectionCustHierarchy(
        int projectionCustHierarchySid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _projectionCustHierarchyLocalService.fetchProjectionCustHierarchy(projectionCustHierarchySid);
    }

    /**
    * Returns the projection cust hierarchy with the primary key.
    *
    * @param projectionCustHierarchySid the primary key of the projection cust hierarchy
    * @return the projection cust hierarchy
    * @throws PortalException if a projection cust hierarchy with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ProjectionCustHierarchy getProjectionCustHierarchy(
        int projectionCustHierarchySid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _projectionCustHierarchyLocalService.getProjectionCustHierarchy(projectionCustHierarchySid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _projectionCustHierarchyLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the projection cust hierarchies.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionCustHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of projection cust hierarchies
    * @param end the upper bound of the range of projection cust hierarchies (not inclusive)
    * @return the range of projection cust hierarchies
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.ProjectionCustHierarchy> getProjectionCustHierarchies(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _projectionCustHierarchyLocalService.getProjectionCustHierarchies(start,
            end);
    }

    /**
    * Returns the number of projection cust hierarchies.
    *
    * @return the number of projection cust hierarchies
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getProjectionCustHierarchiesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _projectionCustHierarchyLocalService.getProjectionCustHierarchiesCount();
    }

    /**
    * Updates the projection cust hierarchy in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param projectionCustHierarchy the projection cust hierarchy
    * @return the projection cust hierarchy that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ProjectionCustHierarchy updateProjectionCustHierarchy(
        com.stpl.app.model.ProjectionCustHierarchy projectionCustHierarchy)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _projectionCustHierarchyLocalService.updateProjectionCustHierarchy(projectionCustHierarchy);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _projectionCustHierarchyLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _projectionCustHierarchyLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _projectionCustHierarchyLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    @Override
    public void insert(java.util.List list, java.lang.String string1,
        java.lang.String string2) {
        _projectionCustHierarchyLocalService.insert(list, string1, string2);
    }

    @Override
    public java.util.List retrive(java.util.List list,
        java.lang.String string1, java.lang.String string2) {
        return _projectionCustHierarchyLocalService.retrive(list, string1,
            string2);
    }

    @Override
    public java.util.List getComparisonSearch(java.lang.String workflowStatus,
        java.lang.String marketType, java.lang.String brand,
        java.lang.String projName, java.lang.String contHldr,
        java.lang.String ndcNo, java.lang.String ndcName,
        java.lang.String desc, java.lang.String contract,
        java.lang.String from, java.lang.String to) {
        return _projectionCustHierarchyLocalService.getComparisonSearch(workflowStatus,
            marketType, brand, projName, contHldr, ndcNo, ndcName, desc,
            contract, from, to);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ProjectionCustHierarchyLocalService getWrappedProjectionCustHierarchyLocalService() {
        return _projectionCustHierarchyLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedProjectionCustHierarchyLocalService(
        ProjectionCustHierarchyLocalService projectionCustHierarchyLocalService) {
        _projectionCustHierarchyLocalService = projectionCustHierarchyLocalService;
    }

    @Override
    public ProjectionCustHierarchyLocalService getWrappedService() {
        return _projectionCustHierarchyLocalService;
    }

    @Override
    public void setWrappedService(
        ProjectionCustHierarchyLocalService projectionCustHierarchyLocalService) {
        _projectionCustHierarchyLocalService = projectionCustHierarchyLocalService;
    }
}
