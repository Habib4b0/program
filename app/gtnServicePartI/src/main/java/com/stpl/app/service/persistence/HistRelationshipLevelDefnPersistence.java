package com.stpl.app.service.persistence;

import com.stpl.app.model.HistRelationshipLevelDefn;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the hist relationship level defn service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistRelationshipLevelDefnPersistenceImpl
 * @see HistRelationshipLevelDefnUtil
 * @generated
 */
public interface HistRelationshipLevelDefnPersistence extends BasePersistence<HistRelationshipLevelDefn> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link HistRelationshipLevelDefnUtil} to access the hist relationship level defn persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the hist relationship level defn in the entity cache if it is enabled.
    *
    * @param histRelationshipLevelDefn the hist relationship level defn
    */
    public void cacheResult(
        com.stpl.app.model.HistRelationshipLevelDefn histRelationshipLevelDefn);

    /**
    * Caches the hist relationship level defns in the entity cache if it is enabled.
    *
    * @param histRelationshipLevelDefns the hist relationship level defns
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.HistRelationshipLevelDefn> histRelationshipLevelDefns);

    /**
    * Creates a new hist relationship level defn with the primary key. Does not add the hist relationship level defn to the database.
    *
    * @param histRelationshipLevelDefnPK the primary key for the new hist relationship level defn
    * @return the new hist relationship level defn
    */
    public com.stpl.app.model.HistRelationshipLevelDefn create(
        HistRelationshipLevelDefnPK histRelationshipLevelDefnPK);

    /**
    * Removes the hist relationship level defn with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param histRelationshipLevelDefnPK the primary key of the hist relationship level defn
    * @return the hist relationship level defn that was removed
    * @throws com.stpl.app.NoSuchHistRelationshipLevelDefnException if a hist relationship level defn with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HistRelationshipLevelDefn remove(
        HistRelationshipLevelDefnPK histRelationshipLevelDefnPK)
        throws com.stpl.app.NoSuchHistRelationshipLevelDefnException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.HistRelationshipLevelDefn updateImpl(
        com.stpl.app.model.HistRelationshipLevelDefn histRelationshipLevelDefn)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the hist relationship level defn with the primary key or throws a {@link com.stpl.app.NoSuchHistRelationshipLevelDefnException} if it could not be found.
    *
    * @param histRelationshipLevelDefnPK the primary key of the hist relationship level defn
    * @return the hist relationship level defn
    * @throws com.stpl.app.NoSuchHistRelationshipLevelDefnException if a hist relationship level defn with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HistRelationshipLevelDefn findByPrimaryKey(
        HistRelationshipLevelDefnPK histRelationshipLevelDefnPK)
        throws com.stpl.app.NoSuchHistRelationshipLevelDefnException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the hist relationship level defn with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param histRelationshipLevelDefnPK the primary key of the hist relationship level defn
    * @return the hist relationship level defn, or <code>null</code> if a hist relationship level defn with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HistRelationshipLevelDefn fetchByPrimaryKey(
        HistRelationshipLevelDefnPK histRelationshipLevelDefnPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the hist relationship level defns.
    *
    * @return the hist relationship level defns
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HistRelationshipLevelDefn> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the hist relationship level defns.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistRelationshipLevelDefnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist relationship level defns
    * @param end the upper bound of the range of hist relationship level defns (not inclusive)
    * @return the range of hist relationship level defns
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HistRelationshipLevelDefn> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the hist relationship level defns.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistRelationshipLevelDefnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist relationship level defns
    * @param end the upper bound of the range of hist relationship level defns (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of hist relationship level defns
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HistRelationshipLevelDefn> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the hist relationship level defns from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of hist relationship level defns.
    *
    * @return the number of hist relationship level defns
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
