package com.stpl.app.service.persistence;

import com.stpl.app.model.ItemIdentifier;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the item identifier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ItemIdentifierPersistenceImpl
 * @see ItemIdentifierUtil
 * @generated
 */
public interface ItemIdentifierPersistence extends BasePersistence<ItemIdentifier> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ItemIdentifierUtil} to access the item identifier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the item identifiers where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
    *
    * @param itemIdentifierValue the item identifier value
    * @param itemQualifierSid the item qualifier sid
    * @param identifierStatus the identifier status
    * @param startDate the start date
    * @return the matching item identifiers
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemIdentifier> findByItemIrtIdentifier(
        java.lang.String itemIdentifierValue, int itemQualifierSid,
        int identifierStatus, java.util.Date startDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the item identifiers where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemIdentifierValue the item identifier value
    * @param itemQualifierSid the item qualifier sid
    * @param identifierStatus the identifier status
    * @param startDate the start date
    * @param start the lower bound of the range of item identifiers
    * @param end the upper bound of the range of item identifiers (not inclusive)
    * @return the range of matching item identifiers
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemIdentifier> findByItemIrtIdentifier(
        java.lang.String itemIdentifierValue, int itemQualifierSid,
        int identifierStatus, java.util.Date startDate, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the item identifiers where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemIdentifierValue the item identifier value
    * @param itemQualifierSid the item qualifier sid
    * @param identifierStatus the identifier status
    * @param startDate the start date
    * @param start the lower bound of the range of item identifiers
    * @param end the upper bound of the range of item identifiers (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching item identifiers
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemIdentifier> findByItemIrtIdentifier(
        java.lang.String itemIdentifierValue, int itemQualifierSid,
        int identifierStatus, java.util.Date startDate, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item identifier in the ordered set where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
    *
    * @param itemIdentifierValue the item identifier value
    * @param itemQualifierSid the item qualifier sid
    * @param identifierStatus the identifier status
    * @param startDate the start date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item identifier
    * @throws com.stpl.app.NoSuchItemIdentifierException if a matching item identifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemIdentifier findByItemIrtIdentifier_First(
        java.lang.String itemIdentifierValue, int itemQualifierSid,
        int identifierStatus, java.util.Date startDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemIdentifierException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item identifier in the ordered set where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
    *
    * @param itemIdentifierValue the item identifier value
    * @param itemQualifierSid the item qualifier sid
    * @param identifierStatus the identifier status
    * @param startDate the start date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item identifier, or <code>null</code> if a matching item identifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemIdentifier fetchByItemIrtIdentifier_First(
        java.lang.String itemIdentifierValue, int itemQualifierSid,
        int identifierStatus, java.util.Date startDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item identifier in the ordered set where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
    *
    * @param itemIdentifierValue the item identifier value
    * @param itemQualifierSid the item qualifier sid
    * @param identifierStatus the identifier status
    * @param startDate the start date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item identifier
    * @throws com.stpl.app.NoSuchItemIdentifierException if a matching item identifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemIdentifier findByItemIrtIdentifier_Last(
        java.lang.String itemIdentifierValue, int itemQualifierSid,
        int identifierStatus, java.util.Date startDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemIdentifierException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item identifier in the ordered set where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
    *
    * @param itemIdentifierValue the item identifier value
    * @param itemQualifierSid the item qualifier sid
    * @param identifierStatus the identifier status
    * @param startDate the start date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item identifier, or <code>null</code> if a matching item identifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemIdentifier fetchByItemIrtIdentifier_Last(
        java.lang.String itemIdentifierValue, int itemQualifierSid,
        int identifierStatus, java.util.Date startDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item identifiers before and after the current item identifier in the ordered set where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
    *
    * @param itemIdentifierSid the primary key of the current item identifier
    * @param itemIdentifierValue the item identifier value
    * @param itemQualifierSid the item qualifier sid
    * @param identifierStatus the identifier status
    * @param startDate the start date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next item identifier
    * @throws com.stpl.app.NoSuchItemIdentifierException if a item identifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemIdentifier[] findByItemIrtIdentifier_PrevAndNext(
        int itemIdentifierSid, java.lang.String itemIdentifierValue,
        int itemQualifierSid, int identifierStatus, java.util.Date startDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemIdentifierException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the item identifiers where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63; from the database.
    *
    * @param itemIdentifierValue the item identifier value
    * @param itemQualifierSid the item qualifier sid
    * @param identifierStatus the identifier status
    * @param startDate the start date
    * @throws SystemException if a system exception occurred
    */
    public void removeByItemIrtIdentifier(
        java.lang.String itemIdentifierValue, int itemQualifierSid,
        int identifierStatus, java.util.Date startDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of item identifiers where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
    *
    * @param itemIdentifierValue the item identifier value
    * @param itemQualifierSid the item qualifier sid
    * @param identifierStatus the identifier status
    * @param startDate the start date
    * @return the number of matching item identifiers
    * @throws SystemException if a system exception occurred
    */
    public int countByItemIrtIdentifier(java.lang.String itemIdentifierValue,
        int itemQualifierSid, int identifierStatus, java.util.Date startDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the item identifiers where itemMasterSid = &#63;.
    *
    * @param itemMasterSid the item master sid
    * @return the matching item identifiers
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemIdentifier> findByItemIrtIdentifierDetails(
        int itemMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the item identifiers where itemMasterSid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemMasterSid the item master sid
    * @param start the lower bound of the range of item identifiers
    * @param end the upper bound of the range of item identifiers (not inclusive)
    * @return the range of matching item identifiers
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemIdentifier> findByItemIrtIdentifierDetails(
        int itemMasterSid, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the item identifiers where itemMasterSid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemMasterSid the item master sid
    * @param start the lower bound of the range of item identifiers
    * @param end the upper bound of the range of item identifiers (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching item identifiers
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemIdentifier> findByItemIrtIdentifierDetails(
        int itemMasterSid, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item identifier in the ordered set where itemMasterSid = &#63;.
    *
    * @param itemMasterSid the item master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item identifier
    * @throws com.stpl.app.NoSuchItemIdentifierException if a matching item identifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemIdentifier findByItemIrtIdentifierDetails_First(
        int itemMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemIdentifierException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item identifier in the ordered set where itemMasterSid = &#63;.
    *
    * @param itemMasterSid the item master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item identifier, or <code>null</code> if a matching item identifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemIdentifier fetchByItemIrtIdentifierDetails_First(
        int itemMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item identifier in the ordered set where itemMasterSid = &#63;.
    *
    * @param itemMasterSid the item master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item identifier
    * @throws com.stpl.app.NoSuchItemIdentifierException if a matching item identifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemIdentifier findByItemIrtIdentifierDetails_Last(
        int itemMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemIdentifierException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item identifier in the ordered set where itemMasterSid = &#63;.
    *
    * @param itemMasterSid the item master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item identifier, or <code>null</code> if a matching item identifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemIdentifier fetchByItemIrtIdentifierDetails_Last(
        int itemMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item identifiers before and after the current item identifier in the ordered set where itemMasterSid = &#63;.
    *
    * @param itemIdentifierSid the primary key of the current item identifier
    * @param itemMasterSid the item master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next item identifier
    * @throws com.stpl.app.NoSuchItemIdentifierException if a item identifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemIdentifier[] findByItemIrtIdentifierDetails_PrevAndNext(
        int itemIdentifierSid, int itemMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemIdentifierException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the item identifiers where itemMasterSid = &#63; from the database.
    *
    * @param itemMasterSid the item master sid
    * @throws SystemException if a system exception occurred
    */
    public void removeByItemIrtIdentifierDetails(int itemMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of item identifiers where itemMasterSid = &#63;.
    *
    * @param itemMasterSid the item master sid
    * @return the number of matching item identifiers
    * @throws SystemException if a system exception occurred
    */
    public int countByItemIrtIdentifierDetails(int itemMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Caches the item identifier in the entity cache if it is enabled.
    *
    * @param itemIdentifier the item identifier
    */
    public void cacheResult(com.stpl.app.model.ItemIdentifier itemIdentifier);

    /**
    * Caches the item identifiers in the entity cache if it is enabled.
    *
    * @param itemIdentifiers the item identifiers
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.ItemIdentifier> itemIdentifiers);

    /**
    * Creates a new item identifier with the primary key. Does not add the item identifier to the database.
    *
    * @param itemIdentifierSid the primary key for the new item identifier
    * @return the new item identifier
    */
    public com.stpl.app.model.ItemIdentifier create(int itemIdentifierSid);

    /**
    * Removes the item identifier with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param itemIdentifierSid the primary key of the item identifier
    * @return the item identifier that was removed
    * @throws com.stpl.app.NoSuchItemIdentifierException if a item identifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemIdentifier remove(int itemIdentifierSid)
        throws com.stpl.app.NoSuchItemIdentifierException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.ItemIdentifier updateImpl(
        com.stpl.app.model.ItemIdentifier itemIdentifier)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item identifier with the primary key or throws a {@link com.stpl.app.NoSuchItemIdentifierException} if it could not be found.
    *
    * @param itemIdentifierSid the primary key of the item identifier
    * @return the item identifier
    * @throws com.stpl.app.NoSuchItemIdentifierException if a item identifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemIdentifier findByPrimaryKey(
        int itemIdentifierSid)
        throws com.stpl.app.NoSuchItemIdentifierException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item identifier with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param itemIdentifierSid the primary key of the item identifier
    * @return the item identifier, or <code>null</code> if a item identifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemIdentifier fetchByPrimaryKey(
        int itemIdentifierSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the item identifiers.
    *
    * @return the item identifiers
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemIdentifier> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the item identifiers.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of item identifiers
    * @param end the upper bound of the range of item identifiers (not inclusive)
    * @return the range of item identifiers
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemIdentifier> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the item identifiers.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of item identifiers
    * @param end the upper bound of the range of item identifiers (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of item identifiers
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemIdentifier> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the item identifiers from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of item identifiers.
    *
    * @return the number of item identifiers
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
