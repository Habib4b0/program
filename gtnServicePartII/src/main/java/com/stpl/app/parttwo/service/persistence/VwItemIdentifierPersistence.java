package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.VwItemIdentifier;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the vw item identifier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwItemIdentifierPersistenceImpl
 * @see VwItemIdentifierUtil
 * @generated
 */
public interface VwItemIdentifierPersistence extends BasePersistence<VwItemIdentifier> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link VwItemIdentifierUtil} to access the vw item identifier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the vw item identifier in the entity cache if it is enabled.
    *
    * @param vwItemIdentifier the vw item identifier
    */
    public void cacheResult(
        com.stpl.app.parttwo.model.VwItemIdentifier vwItemIdentifier);

    /**
    * Caches the vw item identifiers in the entity cache if it is enabled.
    *
    * @param vwItemIdentifiers the vw item identifiers
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.VwItemIdentifier> vwItemIdentifiers);

    /**
    * Creates a new vw item identifier with the primary key. Does not add the vw item identifier to the database.
    *
    * @param itemIdentifierSid the primary key for the new vw item identifier
    * @return the new vw item identifier
    */
    public com.stpl.app.parttwo.model.VwItemIdentifier create(
        int itemIdentifierSid);

    /**
    * Removes the vw item identifier with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param itemIdentifierSid the primary key of the vw item identifier
    * @return the vw item identifier that was removed
    * @throws com.stpl.app.parttwo.NoSuchVwItemIdentifierException if a vw item identifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.VwItemIdentifier remove(
        int itemIdentifierSid)
        throws com.stpl.app.parttwo.NoSuchVwItemIdentifierException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.VwItemIdentifier updateImpl(
        com.stpl.app.parttwo.model.VwItemIdentifier vwItemIdentifier)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the vw item identifier with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchVwItemIdentifierException} if it could not be found.
    *
    * @param itemIdentifierSid the primary key of the vw item identifier
    * @return the vw item identifier
    * @throws com.stpl.app.parttwo.NoSuchVwItemIdentifierException if a vw item identifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.VwItemIdentifier findByPrimaryKey(
        int itemIdentifierSid)
        throws com.stpl.app.parttwo.NoSuchVwItemIdentifierException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the vw item identifier with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param itemIdentifierSid the primary key of the vw item identifier
    * @return the vw item identifier, or <code>null</code> if a vw item identifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.VwItemIdentifier fetchByPrimaryKey(
        int itemIdentifierSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the vw item identifiers.
    *
    * @return the vw item identifiers
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.VwItemIdentifier> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the vw item identifiers.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw item identifiers
    * @param end the upper bound of the range of vw item identifiers (not inclusive)
    * @return the range of vw item identifiers
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.VwItemIdentifier> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the vw item identifiers.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw item identifiers
    * @param end the upper bound of the range of vw item identifiers (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of vw item identifiers
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.VwItemIdentifier> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the vw item identifiers from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of vw item identifiers.
    *
    * @return the number of vw item identifiers
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
