package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.IvldCompanyIdentifier;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ivld company identifier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldCompanyIdentifierPersistenceImpl
 * @see IvldCompanyIdentifierUtil
 * @generated
 */
public interface IvldCompanyIdentifierPersistence extends BasePersistence<IvldCompanyIdentifier> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link IvldCompanyIdentifierUtil} to access the ivld company identifier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the ivld company identifier in the entity cache if it is enabled.
    *
    * @param ivldCompanyIdentifier the ivld company identifier
    */
    public void cacheResult(
        com.stpl.app.parttwo.model.IvldCompanyIdentifier ivldCompanyIdentifier);

    /**
    * Caches the ivld company identifiers in the entity cache if it is enabled.
    *
    * @param ivldCompanyIdentifiers the ivld company identifiers
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.IvldCompanyIdentifier> ivldCompanyIdentifiers);

    /**
    * Creates a new ivld company identifier with the primary key. Does not add the ivld company identifier to the database.
    *
    * @param ivldCompanyIdentifierSid the primary key for the new ivld company identifier
    * @return the new ivld company identifier
    */
    public com.stpl.app.parttwo.model.IvldCompanyIdentifier create(
        int ivldCompanyIdentifierSid);

    /**
    * Removes the ivld company identifier with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldCompanyIdentifierSid the primary key of the ivld company identifier
    * @return the ivld company identifier that was removed
    * @throws com.stpl.app.parttwo.NoSuchIvldCompanyIdentifierException if a ivld company identifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.IvldCompanyIdentifier remove(
        int ivldCompanyIdentifierSid)
        throws com.stpl.app.parttwo.NoSuchIvldCompanyIdentifierException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.IvldCompanyIdentifier updateImpl(
        com.stpl.app.parttwo.model.IvldCompanyIdentifier ivldCompanyIdentifier)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld company identifier with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchIvldCompanyIdentifierException} if it could not be found.
    *
    * @param ivldCompanyIdentifierSid the primary key of the ivld company identifier
    * @return the ivld company identifier
    * @throws com.stpl.app.parttwo.NoSuchIvldCompanyIdentifierException if a ivld company identifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.IvldCompanyIdentifier findByPrimaryKey(
        int ivldCompanyIdentifierSid)
        throws com.stpl.app.parttwo.NoSuchIvldCompanyIdentifierException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld company identifier with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldCompanyIdentifierSid the primary key of the ivld company identifier
    * @return the ivld company identifier, or <code>null</code> if a ivld company identifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.IvldCompanyIdentifier fetchByPrimaryKey(
        int ivldCompanyIdentifierSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ivld company identifiers.
    *
    * @return the ivld company identifiers
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.IvldCompanyIdentifier> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ivld company identifiers.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld company identifiers
    * @param end the upper bound of the range of ivld company identifiers (not inclusive)
    * @return the range of ivld company identifiers
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.IvldCompanyIdentifier> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ivld company identifiers.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld company identifiers
    * @param end the upper bound of the range of ivld company identifiers (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ivld company identifiers
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.IvldCompanyIdentifier> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ivld company identifiers from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ivld company identifiers.
    *
    * @return the number of ivld company identifiers
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
