package com.stpl.app.service.persistence;

import com.stpl.app.model.HistHierarchyLevelDefn;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the hist hierarchy level defn service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistHierarchyLevelDefnPersistenceImpl
 * @see HistHierarchyLevelDefnUtil
 * @generated
 */
public interface HistHierarchyLevelDefnPersistence extends BasePersistence<HistHierarchyLevelDefn> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link HistHierarchyLevelDefnUtil} to access the hist hierarchy level defn persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the hist hierarchy level defn in the entity cache if it is enabled.
    *
    * @param histHierarchyLevelDefn the hist hierarchy level defn
    */
    public void cacheResult(
        com.stpl.app.model.HistHierarchyLevelDefn histHierarchyLevelDefn);

    /**
    * Caches the hist hierarchy level defns in the entity cache if it is enabled.
    *
    * @param histHierarchyLevelDefns the hist hierarchy level defns
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.HistHierarchyLevelDefn> histHierarchyLevelDefns);

    /**
    * Creates a new hist hierarchy level defn with the primary key. Does not add the hist hierarchy level defn to the database.
    *
    * @param histHierarchyLevelDefnPK the primary key for the new hist hierarchy level defn
    * @return the new hist hierarchy level defn
    */
    public com.stpl.app.model.HistHierarchyLevelDefn create(
        HistHierarchyLevelDefnPK histHierarchyLevelDefnPK);

    /**
    * Removes the hist hierarchy level defn with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param histHierarchyLevelDefnPK the primary key of the hist hierarchy level defn
    * @return the hist hierarchy level defn that was removed
    * @throws com.stpl.app.NoSuchHistHierarchyLevelDefnException if a hist hierarchy level defn with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HistHierarchyLevelDefn remove(
        HistHierarchyLevelDefnPK histHierarchyLevelDefnPK)
        throws com.stpl.app.NoSuchHistHierarchyLevelDefnException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.HistHierarchyLevelDefn updateImpl(
        com.stpl.app.model.HistHierarchyLevelDefn histHierarchyLevelDefn)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the hist hierarchy level defn with the primary key or throws a {@link com.stpl.app.NoSuchHistHierarchyLevelDefnException} if it could not be found.
    *
    * @param histHierarchyLevelDefnPK the primary key of the hist hierarchy level defn
    * @return the hist hierarchy level defn
    * @throws com.stpl.app.NoSuchHistHierarchyLevelDefnException if a hist hierarchy level defn with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HistHierarchyLevelDefn findByPrimaryKey(
        HistHierarchyLevelDefnPK histHierarchyLevelDefnPK)
        throws com.stpl.app.NoSuchHistHierarchyLevelDefnException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the hist hierarchy level defn with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param histHierarchyLevelDefnPK the primary key of the hist hierarchy level defn
    * @return the hist hierarchy level defn, or <code>null</code> if a hist hierarchy level defn with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HistHierarchyLevelDefn fetchByPrimaryKey(
        HistHierarchyLevelDefnPK histHierarchyLevelDefnPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the hist hierarchy level defns.
    *
    * @return the hist hierarchy level defns
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HistHierarchyLevelDefn> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the hist hierarchy level defns.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistHierarchyLevelDefnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist hierarchy level defns
    * @param end the upper bound of the range of hist hierarchy level defns (not inclusive)
    * @return the range of hist hierarchy level defns
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HistHierarchyLevelDefn> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the hist hierarchy level defns.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistHierarchyLevelDefnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist hierarchy level defns
    * @param end the upper bound of the range of hist hierarchy level defns (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of hist hierarchy level defns
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HistHierarchyLevelDefn> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the hist hierarchy level defns from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of hist hierarchy level defns.
    *
    * @return the number of hist hierarchy level defns
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
