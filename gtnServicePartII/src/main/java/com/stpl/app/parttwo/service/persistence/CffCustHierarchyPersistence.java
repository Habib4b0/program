package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.CffCustHierarchy;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the cff cust hierarchy service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CffCustHierarchyPersistenceImpl
 * @see CffCustHierarchyUtil
 * @generated
 */
public interface CffCustHierarchyPersistence extends BasePersistence<CffCustHierarchy> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link CffCustHierarchyUtil} to access the cff cust hierarchy persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the cff cust hierarchy in the entity cache if it is enabled.
    *
    * @param cffCustHierarchy the cff cust hierarchy
    */
    public void cacheResult(
        com.stpl.app.parttwo.model.CffCustHierarchy cffCustHierarchy);

    /**
    * Caches the cff cust hierarchies in the entity cache if it is enabled.
    *
    * @param cffCustHierarchies the cff cust hierarchies
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.CffCustHierarchy> cffCustHierarchies);

    /**
    * Creates a new cff cust hierarchy with the primary key. Does not add the cff cust hierarchy to the database.
    *
    * @param cffCustHierarchySid the primary key for the new cff cust hierarchy
    * @return the new cff cust hierarchy
    */
    public com.stpl.app.parttwo.model.CffCustHierarchy create(
        int cffCustHierarchySid);

    /**
    * Removes the cff cust hierarchy with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param cffCustHierarchySid the primary key of the cff cust hierarchy
    * @return the cff cust hierarchy that was removed
    * @throws com.stpl.app.parttwo.NoSuchCffCustHierarchyException if a cff cust hierarchy with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.CffCustHierarchy remove(
        int cffCustHierarchySid)
        throws com.stpl.app.parttwo.NoSuchCffCustHierarchyException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.CffCustHierarchy updateImpl(
        com.stpl.app.parttwo.model.CffCustHierarchy cffCustHierarchy)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the cff cust hierarchy with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchCffCustHierarchyException} if it could not be found.
    *
    * @param cffCustHierarchySid the primary key of the cff cust hierarchy
    * @return the cff cust hierarchy
    * @throws com.stpl.app.parttwo.NoSuchCffCustHierarchyException if a cff cust hierarchy with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.CffCustHierarchy findByPrimaryKey(
        int cffCustHierarchySid)
        throws com.stpl.app.parttwo.NoSuchCffCustHierarchyException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the cff cust hierarchy with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param cffCustHierarchySid the primary key of the cff cust hierarchy
    * @return the cff cust hierarchy, or <code>null</code> if a cff cust hierarchy with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.CffCustHierarchy fetchByPrimaryKey(
        int cffCustHierarchySid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the cff cust hierarchies.
    *
    * @return the cff cust hierarchies
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.CffCustHierarchy> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the cff cust hierarchies.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffCustHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cff cust hierarchies
    * @param end the upper bound of the range of cff cust hierarchies (not inclusive)
    * @return the range of cff cust hierarchies
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.CffCustHierarchy> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the cff cust hierarchies.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffCustHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cff cust hierarchies
    * @param end the upper bound of the range of cff cust hierarchies (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of cff cust hierarchies
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.CffCustHierarchy> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the cff cust hierarchies from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of cff cust hierarchies.
    *
    * @return the number of cff cust hierarchies
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
