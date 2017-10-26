package com.stpl.app.service.persistence;

import com.stpl.app.model.ProjectionCustHierarchy;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the projection cust hierarchy service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ProjectionCustHierarchyPersistenceImpl
 * @see ProjectionCustHierarchyUtil
 * @generated
 */
public interface ProjectionCustHierarchyPersistence extends BasePersistence<ProjectionCustHierarchy> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ProjectionCustHierarchyUtil} to access the projection cust hierarchy persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the projection cust hierarchy in the entity cache if it is enabled.
    *
    * @param projectionCustHierarchy the projection cust hierarchy
    */
    public void cacheResult(
        com.stpl.app.model.ProjectionCustHierarchy projectionCustHierarchy);

    /**
    * Caches the projection cust hierarchies in the entity cache if it is enabled.
    *
    * @param projectionCustHierarchies the projection cust hierarchies
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.ProjectionCustHierarchy> projectionCustHierarchies);

    /**
    * Creates a new projection cust hierarchy with the primary key. Does not add the projection cust hierarchy to the database.
    *
    * @param projectionCustHierarchySid the primary key for the new projection cust hierarchy
    * @return the new projection cust hierarchy
    */
    public com.stpl.app.model.ProjectionCustHierarchy create(
        int projectionCustHierarchySid);

    /**
    * Removes the projection cust hierarchy with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param projectionCustHierarchySid the primary key of the projection cust hierarchy
    * @return the projection cust hierarchy that was removed
    * @throws com.stpl.app.NoSuchProjectionCustHierarchyException if a projection cust hierarchy with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ProjectionCustHierarchy remove(
        int projectionCustHierarchySid)
        throws com.stpl.app.NoSuchProjectionCustHierarchyException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.ProjectionCustHierarchy updateImpl(
        com.stpl.app.model.ProjectionCustHierarchy projectionCustHierarchy)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the projection cust hierarchy with the primary key or throws a {@link com.stpl.app.NoSuchProjectionCustHierarchyException} if it could not be found.
    *
    * @param projectionCustHierarchySid the primary key of the projection cust hierarchy
    * @return the projection cust hierarchy
    * @throws com.stpl.app.NoSuchProjectionCustHierarchyException if a projection cust hierarchy with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ProjectionCustHierarchy findByPrimaryKey(
        int projectionCustHierarchySid)
        throws com.stpl.app.NoSuchProjectionCustHierarchyException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the projection cust hierarchy with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param projectionCustHierarchySid the primary key of the projection cust hierarchy
    * @return the projection cust hierarchy, or <code>null</code> if a projection cust hierarchy with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ProjectionCustHierarchy fetchByPrimaryKey(
        int projectionCustHierarchySid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the projection cust hierarchies.
    *
    * @return the projection cust hierarchies
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ProjectionCustHierarchy> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

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
    public java.util.List<com.stpl.app.model.ProjectionCustHierarchy> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the projection cust hierarchies.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionCustHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of projection cust hierarchies
    * @param end the upper bound of the range of projection cust hierarchies (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of projection cust hierarchies
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ProjectionCustHierarchy> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the projection cust hierarchies from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of projection cust hierarchies.
    *
    * @return the number of projection cust hierarchies
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
