package com.stpl.app.service.persistence;

import com.stpl.app.model.StSelectionTable;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the st selection table service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StSelectionTablePersistenceImpl
 * @see StSelectionTableUtil
 * @generated
 */
public interface StSelectionTablePersistence extends BasePersistence<StSelectionTable> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link StSelectionTableUtil} to access the st selection table persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the st selection table in the entity cache if it is enabled.
    *
    * @param stSelectionTable the st selection table
    */
    public void cacheResult(
        com.stpl.app.model.StSelectionTable stSelectionTable);

    /**
    * Caches the st selection tables in the entity cache if it is enabled.
    *
    * @param stSelectionTables the st selection tables
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.StSelectionTable> stSelectionTables);

    /**
    * Creates a new st selection table with the primary key. Does not add the st selection table to the database.
    *
    * @param stSelectionTablePK the primary key for the new st selection table
    * @return the new st selection table
    */
    public com.stpl.app.model.StSelectionTable create(
        StSelectionTablePK stSelectionTablePK);

    /**
    * Removes the st selection table with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stSelectionTablePK the primary key of the st selection table
    * @return the st selection table that was removed
    * @throws com.stpl.app.NoSuchStSelectionTableException if a st selection table with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StSelectionTable remove(
        StSelectionTablePK stSelectionTablePK)
        throws com.stpl.app.NoSuchStSelectionTableException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.StSelectionTable updateImpl(
        com.stpl.app.model.StSelectionTable stSelectionTable)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st selection table with the primary key or throws a {@link com.stpl.app.NoSuchStSelectionTableException} if it could not be found.
    *
    * @param stSelectionTablePK the primary key of the st selection table
    * @return the st selection table
    * @throws com.stpl.app.NoSuchStSelectionTableException if a st selection table with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StSelectionTable findByPrimaryKey(
        StSelectionTablePK stSelectionTablePK)
        throws com.stpl.app.NoSuchStSelectionTableException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st selection table with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stSelectionTablePK the primary key of the st selection table
    * @return the st selection table, or <code>null</code> if a st selection table with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StSelectionTable fetchByPrimaryKey(
        StSelectionTablePK stSelectionTablePK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the st selection tables.
    *
    * @return the st selection tables
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StSelectionTable> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the st selection tables.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StSelectionTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st selection tables
    * @param end the upper bound of the range of st selection tables (not inclusive)
    * @return the range of st selection tables
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StSelectionTable> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the st selection tables.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StSelectionTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st selection tables
    * @param end the upper bound of the range of st selection tables (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st selection tables
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StSelectionTable> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the st selection tables from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of st selection tables.
    *
    * @return the number of st selection tables
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
