package com.stpl.app.service;

import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.transaction.Isolation;
import com.stpl.portal.kernel.transaction.Propagation;
import com.stpl.portal.kernel.transaction.Transactional;
import com.stpl.portal.service.BaseLocalService;
import com.stpl.portal.service.InvokableLocalService;
import com.stpl.portal.service.PersistedModelLocalService;

/**
 * Provides the local service interface for ProjectionCustHierarchy. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author
 * @see ProjectionCustHierarchyLocalServiceUtil
 * @see com.stpl.app.service.base.ProjectionCustHierarchyLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.ProjectionCustHierarchyLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface ProjectionCustHierarchyLocalService extends BaseLocalService,
    InvokableLocalService, PersistedModelLocalService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ProjectionCustHierarchyLocalServiceUtil} to access the projection cust hierarchy local service. Add custom service methods to {@link com.stpl.app.service.impl.ProjectionCustHierarchyLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */

    /**
    * Adds the projection cust hierarchy to the database. Also notifies the appropriate model listeners.
    *
    * @param projectionCustHierarchy the projection cust hierarchy
    * @return the projection cust hierarchy that was added
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ProjectionCustHierarchy addProjectionCustHierarchy(
        com.stpl.app.model.ProjectionCustHierarchy projectionCustHierarchy)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Creates a new projection cust hierarchy with the primary key. Does not add the projection cust hierarchy to the database.
    *
    * @param projectionCustHierarchySid the primary key for the new projection cust hierarchy
    * @return the new projection cust hierarchy
    */
    public com.stpl.app.model.ProjectionCustHierarchy createProjectionCustHierarchy(
        int projectionCustHierarchySid);

    /**
    * Deletes the projection cust hierarchy with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param projectionCustHierarchySid the primary key of the projection cust hierarchy
    * @return the projection cust hierarchy that was removed
    * @throws PortalException if a projection cust hierarchy with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ProjectionCustHierarchy deleteProjectionCustHierarchy(
        int projectionCustHierarchySid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Deletes the projection cust hierarchy from the database. Also notifies the appropriate model listeners.
    *
    * @param projectionCustHierarchy the projection cust hierarchy
    * @return the projection cust hierarchy that was removed
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ProjectionCustHierarchy deleteProjectionCustHierarchy(
        com.stpl.app.model.ProjectionCustHierarchy projectionCustHierarchy)
        throws com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery();

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.stpl.portal.kernel.exception.SystemException;

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
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

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
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public long dynamicQueryCount(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public long dynamicQueryCount(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.stpl.portal.kernel.dao.orm.Projection projection)
        throws com.stpl.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.stpl.app.model.ProjectionCustHierarchy fetchProjectionCustHierarchy(
        int projectionCustHierarchySid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the projection cust hierarchy with the primary key.
    *
    * @param projectionCustHierarchySid the primary key of the projection cust hierarchy
    * @return the projection cust hierarchy
    * @throws PortalException if a projection cust hierarchy with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.stpl.app.model.ProjectionCustHierarchy getProjectionCustHierarchy(
        int projectionCustHierarchySid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException;

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
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.stpl.app.model.ProjectionCustHierarchy> getProjectionCustHierarchies(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of projection cust hierarchies.
    *
    * @return the number of projection cust hierarchies
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getProjectionCustHierarchiesCount()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Updates the projection cust hierarchy in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param projectionCustHierarchy the projection cust hierarchy
    * @return the projection cust hierarchy that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ProjectionCustHierarchy updateProjectionCustHierarchy(
        com.stpl.app.model.ProjectionCustHierarchy projectionCustHierarchy)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier();

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier);

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable;

    public void insert(java.util.List list, java.lang.String string1,
        java.lang.String string2);

    public java.util.List retrive(java.util.List list,
        java.lang.String string1, java.lang.String string2);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List getComparisonSearch(java.lang.String workflowStatus,
        java.lang.String marketType, java.lang.String brand,
        java.lang.String projName, java.lang.String contHldr,
        java.lang.String ndcNo, java.lang.String ndcName,
        java.lang.String desc, java.lang.String contract,
        java.lang.String from, java.lang.String to);
}
