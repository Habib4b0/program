package com.stpl.app.service.persistence;

import com.stpl.app.model.ItemQualifier;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the item qualifier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ItemQualifierPersistenceImpl
 * @see ItemQualifierUtil
 * @generated
 */
public interface ItemQualifierPersistence extends BasePersistence<ItemQualifier> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ItemQualifierUtil} to access the item qualifier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the item qualifiers where itemQualifierValue = &#63;.
    *
    * @param itemQualifierValue the item qualifier value
    * @return the matching item qualifiers
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemQualifier> findByItemIrtQualifierName(
        java.lang.String itemQualifierValue)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the item qualifiers where itemQualifierValue = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemQualifierValue the item qualifier value
    * @param start the lower bound of the range of item qualifiers
    * @param end the upper bound of the range of item qualifiers (not inclusive)
    * @return the range of matching item qualifiers
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemQualifier> findByItemIrtQualifierName(
        java.lang.String itemQualifierValue, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the item qualifiers where itemQualifierValue = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemQualifierValue the item qualifier value
    * @param start the lower bound of the range of item qualifiers
    * @param end the upper bound of the range of item qualifiers (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching item qualifiers
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemQualifier> findByItemIrtQualifierName(
        java.lang.String itemQualifierValue, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item qualifier in the ordered set where itemQualifierValue = &#63;.
    *
    * @param itemQualifierValue the item qualifier value
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item qualifier
    * @throws com.stpl.app.NoSuchItemQualifierException if a matching item qualifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemQualifier findByItemIrtQualifierName_First(
        java.lang.String itemQualifierValue,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemQualifierException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item qualifier in the ordered set where itemQualifierValue = &#63;.
    *
    * @param itemQualifierValue the item qualifier value
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item qualifier, or <code>null</code> if a matching item qualifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemQualifier fetchByItemIrtQualifierName_First(
        java.lang.String itemQualifierValue,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item qualifier in the ordered set where itemQualifierValue = &#63;.
    *
    * @param itemQualifierValue the item qualifier value
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item qualifier
    * @throws com.stpl.app.NoSuchItemQualifierException if a matching item qualifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemQualifier findByItemIrtQualifierName_Last(
        java.lang.String itemQualifierValue,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemQualifierException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item qualifier in the ordered set where itemQualifierValue = &#63;.
    *
    * @param itemQualifierValue the item qualifier value
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item qualifier, or <code>null</code> if a matching item qualifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemQualifier fetchByItemIrtQualifierName_Last(
        java.lang.String itemQualifierValue,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item qualifiers before and after the current item qualifier in the ordered set where itemQualifierValue = &#63;.
    *
    * @param itemQualifierSid the primary key of the current item qualifier
    * @param itemQualifierValue the item qualifier value
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next item qualifier
    * @throws com.stpl.app.NoSuchItemQualifierException if a item qualifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemQualifier[] findByItemIrtQualifierName_PrevAndNext(
        int itemQualifierSid, java.lang.String itemQualifierValue,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemQualifierException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the item qualifiers where itemQualifierValue = &#63; from the database.
    *
    * @param itemQualifierValue the item qualifier value
    * @throws SystemException if a system exception occurred
    */
    public void removeByItemIrtQualifierName(
        java.lang.String itemQualifierValue)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of item qualifiers where itemQualifierValue = &#63;.
    *
    * @param itemQualifierValue the item qualifier value
    * @return the number of matching item qualifiers
    * @throws SystemException if a system exception occurred
    */
    public int countByItemIrtQualifierName(java.lang.String itemQualifierValue)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item qualifier where itemQualifierName = &#63; or throws a {@link com.stpl.app.NoSuchItemQualifierException} if it could not be found.
    *
    * @param itemQualifierName the item qualifier name
    * @return the matching item qualifier
    * @throws com.stpl.app.NoSuchItemQualifierException if a matching item qualifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemQualifier findByItemIrtQualifierByName(
        java.lang.String itemQualifierName)
        throws com.stpl.app.NoSuchItemQualifierException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item qualifier where itemQualifierName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param itemQualifierName the item qualifier name
    * @return the matching item qualifier, or <code>null</code> if a matching item qualifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemQualifier fetchByItemIrtQualifierByName(
        java.lang.String itemQualifierName)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item qualifier where itemQualifierName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param itemQualifierName the item qualifier name
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching item qualifier, or <code>null</code> if a matching item qualifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemQualifier fetchByItemIrtQualifierByName(
        java.lang.String itemQualifierName, boolean retrieveFromCache)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes the item qualifier where itemQualifierName = &#63; from the database.
    *
    * @param itemQualifierName the item qualifier name
    * @return the item qualifier that was removed
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemQualifier removeByItemIrtQualifierByName(
        java.lang.String itemQualifierName)
        throws com.stpl.app.NoSuchItemQualifierException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of item qualifiers where itemQualifierName = &#63;.
    *
    * @param itemQualifierName the item qualifier name
    * @return the number of matching item qualifiers
    * @throws SystemException if a system exception occurred
    */
    public int countByItemIrtQualifierByName(java.lang.String itemQualifierName)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Caches the item qualifier in the entity cache if it is enabled.
    *
    * @param itemQualifier the item qualifier
    */
    public void cacheResult(com.stpl.app.model.ItemQualifier itemQualifier);

    /**
    * Caches the item qualifiers in the entity cache if it is enabled.
    *
    * @param itemQualifiers the item qualifiers
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.ItemQualifier> itemQualifiers);

    /**
    * Creates a new item qualifier with the primary key. Does not add the item qualifier to the database.
    *
    * @param itemQualifierSid the primary key for the new item qualifier
    * @return the new item qualifier
    */
    public com.stpl.app.model.ItemQualifier create(int itemQualifierSid);

    /**
    * Removes the item qualifier with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param itemQualifierSid the primary key of the item qualifier
    * @return the item qualifier that was removed
    * @throws com.stpl.app.NoSuchItemQualifierException if a item qualifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemQualifier remove(int itemQualifierSid)
        throws com.stpl.app.NoSuchItemQualifierException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.ItemQualifier updateImpl(
        com.stpl.app.model.ItemQualifier itemQualifier)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item qualifier with the primary key or throws a {@link com.stpl.app.NoSuchItemQualifierException} if it could not be found.
    *
    * @param itemQualifierSid the primary key of the item qualifier
    * @return the item qualifier
    * @throws com.stpl.app.NoSuchItemQualifierException if a item qualifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemQualifier findByPrimaryKey(
        int itemQualifierSid)
        throws com.stpl.app.NoSuchItemQualifierException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item qualifier with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param itemQualifierSid the primary key of the item qualifier
    * @return the item qualifier, or <code>null</code> if a item qualifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemQualifier fetchByPrimaryKey(
        int itemQualifierSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the item qualifiers.
    *
    * @return the item qualifiers
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemQualifier> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the item qualifiers.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of item qualifiers
    * @param end the upper bound of the range of item qualifiers (not inclusive)
    * @return the range of item qualifiers
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemQualifier> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the item qualifiers.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of item qualifiers
    * @param end the upper bound of the range of item qualifiers (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of item qualifiers
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemQualifier> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the item qualifiers from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of item qualifiers.
    *
    * @return the number of item qualifiers
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
