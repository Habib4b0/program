package com.stpl.app.service.persistence;

import com.stpl.app.model.MParityLookup;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the m parity lookup service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MParityLookupPersistenceImpl
 * @see MParityLookupUtil
 * @generated
 */
public interface MParityLookupPersistence extends BasePersistence<MParityLookup> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link MParityLookupUtil} to access the m parity lookup persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the m parity lookup in the entity cache if it is enabled.
    *
    * @param mParityLookup the m parity lookup
    */
    public void cacheResult(com.stpl.app.model.MParityLookup mParityLookup);

    /**
    * Caches the m parity lookups in the entity cache if it is enabled.
    *
    * @param mParityLookups the m parity lookups
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.MParityLookup> mParityLookups);

    /**
    * Creates a new m parity lookup with the primary key. Does not add the m parity lookup to the database.
    *
    * @param mParityLookupSid the primary key for the new m parity lookup
    * @return the new m parity lookup
    */
    public com.stpl.app.model.MParityLookup create(int mParityLookupSid);

    /**
    * Removes the m parity lookup with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param mParityLookupSid the primary key of the m parity lookup
    * @return the m parity lookup that was removed
    * @throws com.stpl.app.NoSuchMParityLookupException if a m parity lookup with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.MParityLookup remove(int mParityLookupSid)
        throws com.stpl.app.NoSuchMParityLookupException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.MParityLookup updateImpl(
        com.stpl.app.model.MParityLookup mParityLookup)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the m parity lookup with the primary key or throws a {@link com.stpl.app.NoSuchMParityLookupException} if it could not be found.
    *
    * @param mParityLookupSid the primary key of the m parity lookup
    * @return the m parity lookup
    * @throws com.stpl.app.NoSuchMParityLookupException if a m parity lookup with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.MParityLookup findByPrimaryKey(
        int mParityLookupSid)
        throws com.stpl.app.NoSuchMParityLookupException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the m parity lookup with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param mParityLookupSid the primary key of the m parity lookup
    * @return the m parity lookup, or <code>null</code> if a m parity lookup with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.MParityLookup fetchByPrimaryKey(
        int mParityLookupSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the m parity lookups.
    *
    * @return the m parity lookups
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.MParityLookup> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the m parity lookups.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MParityLookupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of m parity lookups
    * @param end the upper bound of the range of m parity lookups (not inclusive)
    * @return the range of m parity lookups
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.MParityLookup> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the m parity lookups.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MParityLookupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of m parity lookups
    * @param end the upper bound of the range of m parity lookups (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of m parity lookups
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.MParityLookup> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the m parity lookups from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of m parity lookups.
    *
    * @return the number of m parity lookups
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
