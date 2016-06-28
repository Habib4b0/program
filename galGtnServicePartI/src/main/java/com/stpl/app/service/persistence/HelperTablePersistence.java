package com.stpl.app.service.persistence;

import com.stpl.app.model.HelperTable;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the helper table service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HelperTablePersistenceImpl
 * @see HelperTableUtil
 * @generated
 */
public interface HelperTablePersistence extends BasePersistence<HelperTable> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link HelperTableUtil} to access the helper table persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the helper tables where listName = &#63;.
    *
    * @param listName the list name
    * @return the matching helper tables
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HelperTable> findByHelperTableDetails(
        java.lang.String listName)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the helper tables where listName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HelperTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param listName the list name
    * @param start the lower bound of the range of helper tables
    * @param end the upper bound of the range of helper tables (not inclusive)
    * @return the range of matching helper tables
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HelperTable> findByHelperTableDetails(
        java.lang.String listName, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the helper tables where listName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HelperTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param listName the list name
    * @param start the lower bound of the range of helper tables
    * @param end the upper bound of the range of helper tables (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching helper tables
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HelperTable> findByHelperTableDetails(
        java.lang.String listName, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first helper table in the ordered set where listName = &#63;.
    *
    * @param listName the list name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching helper table
    * @throws com.stpl.app.NoSuchHelperTableException if a matching helper table could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HelperTable findByHelperTableDetails_First(
        java.lang.String listName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchHelperTableException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first helper table in the ordered set where listName = &#63;.
    *
    * @param listName the list name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching helper table, or <code>null</code> if a matching helper table could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HelperTable fetchByHelperTableDetails_First(
        java.lang.String listName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last helper table in the ordered set where listName = &#63;.
    *
    * @param listName the list name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching helper table
    * @throws com.stpl.app.NoSuchHelperTableException if a matching helper table could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HelperTable findByHelperTableDetails_Last(
        java.lang.String listName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchHelperTableException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last helper table in the ordered set where listName = &#63;.
    *
    * @param listName the list name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching helper table, or <code>null</code> if a matching helper table could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HelperTable fetchByHelperTableDetails_Last(
        java.lang.String listName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the helper tables before and after the current helper table in the ordered set where listName = &#63;.
    *
    * @param helperTableSid the primary key of the current helper table
    * @param listName the list name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next helper table
    * @throws com.stpl.app.NoSuchHelperTableException if a helper table with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HelperTable[] findByHelperTableDetails_PrevAndNext(
        int helperTableSid, java.lang.String listName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchHelperTableException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the helper tables where listName = &#63; from the database.
    *
    * @param listName the list name
    * @throws SystemException if a system exception occurred
    */
    public void removeByHelperTableDetails(java.lang.String listName)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of helper tables where listName = &#63;.
    *
    * @param listName the list name
    * @return the number of matching helper tables
    * @throws SystemException if a system exception occurred
    */
    public int countByHelperTableDetails(java.lang.String listName)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the helper table where helperTableSid = &#63; or throws a {@link com.stpl.app.NoSuchHelperTableException} if it could not be found.
    *
    * @param helperTableSid the helper table sid
    * @return the matching helper table
    * @throws com.stpl.app.NoSuchHelperTableException if a matching helper table could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HelperTable findByHelperTableDetailsByHelperTableSid(
        int helperTableSid)
        throws com.stpl.app.NoSuchHelperTableException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the helper table where helperTableSid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param helperTableSid the helper table sid
    * @return the matching helper table, or <code>null</code> if a matching helper table could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HelperTable fetchByHelperTableDetailsByHelperTableSid(
        int helperTableSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the helper table where helperTableSid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param helperTableSid the helper table sid
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching helper table, or <code>null</code> if a matching helper table could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HelperTable fetchByHelperTableDetailsByHelperTableSid(
        int helperTableSid, boolean retrieveFromCache)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes the helper table where helperTableSid = &#63; from the database.
    *
    * @param helperTableSid the helper table sid
    * @return the helper table that was removed
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HelperTable removeByHelperTableDetailsByHelperTableSid(
        int helperTableSid)
        throws com.stpl.app.NoSuchHelperTableException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of helper tables where helperTableSid = &#63;.
    *
    * @param helperTableSid the helper table sid
    * @return the number of matching helper tables
    * @throws SystemException if a system exception occurred
    */
    public int countByHelperTableDetailsByHelperTableSid(int helperTableSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the helper table where description = &#63; or throws a {@link com.stpl.app.NoSuchHelperTableException} if it could not be found.
    *
    * @param description the description
    * @return the matching helper table
    * @throws com.stpl.app.NoSuchHelperTableException if a matching helper table could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HelperTable findByHelperTableDetailsByDesc(
        java.lang.String description)
        throws com.stpl.app.NoSuchHelperTableException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the helper table where description = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param description the description
    * @return the matching helper table, or <code>null</code> if a matching helper table could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HelperTable fetchByHelperTableDetailsByDesc(
        java.lang.String description)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the helper table where description = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param description the description
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching helper table, or <code>null</code> if a matching helper table could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HelperTable fetchByHelperTableDetailsByDesc(
        java.lang.String description, boolean retrieveFromCache)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes the helper table where description = &#63; from the database.
    *
    * @param description the description
    * @return the helper table that was removed
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HelperTable removeByHelperTableDetailsByDesc(
        java.lang.String description)
        throws com.stpl.app.NoSuchHelperTableException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of helper tables where description = &#63;.
    *
    * @param description the description
    * @return the number of matching helper tables
    * @throws SystemException if a system exception occurred
    */
    public int countByHelperTableDetailsByDesc(java.lang.String description)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the helper table where helperTableSid = &#63; or throws a {@link com.stpl.app.NoSuchHelperTableException} if it could not be found.
    *
    * @param helperTableSid the helper table sid
    * @return the matching helper table
    * @throws com.stpl.app.NoSuchHelperTableException if a matching helper table could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HelperTable findByHelperTableDetailsByCode(
        int helperTableSid)
        throws com.stpl.app.NoSuchHelperTableException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the helper table where helperTableSid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param helperTableSid the helper table sid
    * @return the matching helper table, or <code>null</code> if a matching helper table could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HelperTable fetchByHelperTableDetailsByCode(
        int helperTableSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the helper table where helperTableSid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param helperTableSid the helper table sid
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching helper table, or <code>null</code> if a matching helper table could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HelperTable fetchByHelperTableDetailsByCode(
        int helperTableSid, boolean retrieveFromCache)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes the helper table where helperTableSid = &#63; from the database.
    *
    * @param helperTableSid the helper table sid
    * @return the helper table that was removed
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HelperTable removeByHelperTableDetailsByCode(
        int helperTableSid)
        throws com.stpl.app.NoSuchHelperTableException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of helper tables where helperTableSid = &#63;.
    *
    * @param helperTableSid the helper table sid
    * @return the number of matching helper tables
    * @throws SystemException if a system exception occurred
    */
    public int countByHelperTableDetailsByCode(int helperTableSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Caches the helper table in the entity cache if it is enabled.
    *
    * @param helperTable the helper table
    */
    public void cacheResult(com.stpl.app.model.HelperTable helperTable);

    /**
    * Caches the helper tables in the entity cache if it is enabled.
    *
    * @param helperTables the helper tables
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.HelperTable> helperTables);

    /**
    * Creates a new helper table with the primary key. Does not add the helper table to the database.
    *
    * @param helperTableSid the primary key for the new helper table
    * @return the new helper table
    */
    public com.stpl.app.model.HelperTable create(int helperTableSid);

    /**
    * Removes the helper table with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param helperTableSid the primary key of the helper table
    * @return the helper table that was removed
    * @throws com.stpl.app.NoSuchHelperTableException if a helper table with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HelperTable remove(int helperTableSid)
        throws com.stpl.app.NoSuchHelperTableException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.HelperTable updateImpl(
        com.stpl.app.model.HelperTable helperTable)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the helper table with the primary key or throws a {@link com.stpl.app.NoSuchHelperTableException} if it could not be found.
    *
    * @param helperTableSid the primary key of the helper table
    * @return the helper table
    * @throws com.stpl.app.NoSuchHelperTableException if a helper table with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HelperTable findByPrimaryKey(int helperTableSid)
        throws com.stpl.app.NoSuchHelperTableException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the helper table with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param helperTableSid the primary key of the helper table
    * @return the helper table, or <code>null</code> if a helper table with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HelperTable fetchByPrimaryKey(int helperTableSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the helper tables.
    *
    * @return the helper tables
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HelperTable> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the helper tables.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HelperTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of helper tables
    * @param end the upper bound of the range of helper tables (not inclusive)
    * @return the range of helper tables
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HelperTable> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the helper tables.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HelperTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of helper tables
    * @param end the upper bound of the range of helper tables (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of helper tables
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HelperTable> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the helper tables from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of helper tables.
    *
    * @return the number of helper tables
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
