package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.IvldItemIdentifier;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ivld item identifier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldItemIdentifierPersistenceImpl
 * @see IvldItemIdentifierUtil
 * @generated
 */
public interface IvldItemIdentifierPersistence extends BasePersistence<IvldItemIdentifier> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link IvldItemIdentifierUtil} to access the ivld item identifier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the ivld item identifier in the entity cache if it is enabled.
    *
    * @param ivldItemIdentifier the ivld item identifier
    */
    public void cacheResult(
        com.stpl.app.parttwo.model.IvldItemIdentifier ivldItemIdentifier);

    /**
    * Caches the ivld item identifiers in the entity cache if it is enabled.
    *
    * @param ivldItemIdentifiers the ivld item identifiers
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.IvldItemIdentifier> ivldItemIdentifiers);

    /**
    * Creates a new ivld item identifier with the primary key. Does not add the ivld item identifier to the database.
    *
    * @param ivldItemIdentifierSid the primary key for the new ivld item identifier
    * @return the new ivld item identifier
    */
    public com.stpl.app.parttwo.model.IvldItemIdentifier create(
        int ivldItemIdentifierSid);

    /**
    * Removes the ivld item identifier with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldItemIdentifierSid the primary key of the ivld item identifier
    * @return the ivld item identifier that was removed
    * @throws com.stpl.app.parttwo.NoSuchIvldItemIdentifierException if a ivld item identifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.IvldItemIdentifier remove(
        int ivldItemIdentifierSid)
        throws com.stpl.app.parttwo.NoSuchIvldItemIdentifierException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.IvldItemIdentifier updateImpl(
        com.stpl.app.parttwo.model.IvldItemIdentifier ivldItemIdentifier)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld item identifier with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchIvldItemIdentifierException} if it could not be found.
    *
    * @param ivldItemIdentifierSid the primary key of the ivld item identifier
    * @return the ivld item identifier
    * @throws com.stpl.app.parttwo.NoSuchIvldItemIdentifierException if a ivld item identifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.IvldItemIdentifier findByPrimaryKey(
        int ivldItemIdentifierSid)
        throws com.stpl.app.parttwo.NoSuchIvldItemIdentifierException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld item identifier with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldItemIdentifierSid the primary key of the ivld item identifier
    * @return the ivld item identifier, or <code>null</code> if a ivld item identifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.IvldItemIdentifier fetchByPrimaryKey(
        int ivldItemIdentifierSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ivld item identifiers.
    *
    * @return the ivld item identifiers
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.IvldItemIdentifier> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ivld item identifiers.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld item identifiers
    * @param end the upper bound of the range of ivld item identifiers (not inclusive)
    * @return the range of ivld item identifiers
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.IvldItemIdentifier> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ivld item identifiers.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld item identifiers
    * @param end the upper bound of the range of ivld item identifiers (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ivld item identifiers
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.IvldItemIdentifier> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ivld item identifiers from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ivld item identifiers.
    *
    * @return the number of ivld item identifiers
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
