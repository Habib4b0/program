package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.CffProdHierarchy;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the cff prod hierarchy service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CffProdHierarchyPersistenceImpl
 * @see CffProdHierarchyUtil
 * @generated
 */
public interface CffProdHierarchyPersistence extends BasePersistence<CffProdHierarchy> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link CffProdHierarchyUtil} to access the cff prod hierarchy persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the cff prod hierarchy in the entity cache if it is enabled.
    *
    * @param cffProdHierarchy the cff prod hierarchy
    */
    public void cacheResult(
        com.stpl.app.parttwo.model.CffProdHierarchy cffProdHierarchy);

    /**
    * Caches the cff prod hierarchies in the entity cache if it is enabled.
    *
    * @param cffProdHierarchies the cff prod hierarchies
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.CffProdHierarchy> cffProdHierarchies);

    /**
    * Creates a new cff prod hierarchy with the primary key. Does not add the cff prod hierarchy to the database.
    *
    * @param cffProdHierarchySid the primary key for the new cff prod hierarchy
    * @return the new cff prod hierarchy
    */
    public com.stpl.app.parttwo.model.CffProdHierarchy create(
        int cffProdHierarchySid);

    /**
    * Removes the cff prod hierarchy with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param cffProdHierarchySid the primary key of the cff prod hierarchy
    * @return the cff prod hierarchy that was removed
    * @throws com.stpl.app.parttwo.NoSuchCffProdHierarchyException if a cff prod hierarchy with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.CffProdHierarchy remove(
        int cffProdHierarchySid)
        throws com.stpl.app.parttwo.NoSuchCffProdHierarchyException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.CffProdHierarchy updateImpl(
        com.stpl.app.parttwo.model.CffProdHierarchy cffProdHierarchy)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the cff prod hierarchy with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchCffProdHierarchyException} if it could not be found.
    *
    * @param cffProdHierarchySid the primary key of the cff prod hierarchy
    * @return the cff prod hierarchy
    * @throws com.stpl.app.parttwo.NoSuchCffProdHierarchyException if a cff prod hierarchy with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.CffProdHierarchy findByPrimaryKey(
        int cffProdHierarchySid)
        throws com.stpl.app.parttwo.NoSuchCffProdHierarchyException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the cff prod hierarchy with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param cffProdHierarchySid the primary key of the cff prod hierarchy
    * @return the cff prod hierarchy, or <code>null</code> if a cff prod hierarchy with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.CffProdHierarchy fetchByPrimaryKey(
        int cffProdHierarchySid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the cff prod hierarchies.
    *
    * @return the cff prod hierarchies
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.CffProdHierarchy> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the cff prod hierarchies.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffProdHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cff prod hierarchies
    * @param end the upper bound of the range of cff prod hierarchies (not inclusive)
    * @return the range of cff prod hierarchies
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.CffProdHierarchy> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the cff prod hierarchies.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffProdHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cff prod hierarchies
    * @param end the upper bound of the range of cff prod hierarchies (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of cff prod hierarchies
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.CffProdHierarchy> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the cff prod hierarchies from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of cff prod hierarchies.
    *
    * @return the number of cff prod hierarchies
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
