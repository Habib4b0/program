package com.stpl.app.service.persistence;

import com.stpl.app.model.ItemPricingQualifier;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the item pricing qualifier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ItemPricingQualifierPersistenceImpl
 * @see ItemPricingQualifierUtil
 * @generated
 */
public interface ItemPricingQualifierPersistence extends BasePersistence<ItemPricingQualifier> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ItemPricingQualifierUtil} to access the item pricing qualifier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns the item pricing qualifier where itemPricingQualifierName = &#63; or throws a {@link com.stpl.app.NoSuchItemPricingQualifierException} if it could not be found.
    *
    * @param itemPricingQualifierName the item pricing qualifier name
    * @return the matching item pricing qualifier
    * @throws com.stpl.app.NoSuchItemPricingQualifierException if a matching item pricing qualifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemPricingQualifier findByitemPricingCodeQualifierByName(
        java.lang.String itemPricingQualifierName)
        throws com.stpl.app.NoSuchItemPricingQualifierException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item pricing qualifier where itemPricingQualifierName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param itemPricingQualifierName the item pricing qualifier name
    * @return the matching item pricing qualifier, or <code>null</code> if a matching item pricing qualifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemPricingQualifier fetchByitemPricingCodeQualifierByName(
        java.lang.String itemPricingQualifierName)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item pricing qualifier where itemPricingQualifierName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param itemPricingQualifierName the item pricing qualifier name
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching item pricing qualifier, or <code>null</code> if a matching item pricing qualifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemPricingQualifier fetchByitemPricingCodeQualifierByName(
        java.lang.String itemPricingQualifierName, boolean retrieveFromCache)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes the item pricing qualifier where itemPricingQualifierName = &#63; from the database.
    *
    * @param itemPricingQualifierName the item pricing qualifier name
    * @return the item pricing qualifier that was removed
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemPricingQualifier removeByitemPricingCodeQualifierByName(
        java.lang.String itemPricingQualifierName)
        throws com.stpl.app.NoSuchItemPricingQualifierException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of item pricing qualifiers where itemPricingQualifierName = &#63;.
    *
    * @param itemPricingQualifierName the item pricing qualifier name
    * @return the number of matching item pricing qualifiers
    * @throws SystemException if a system exception occurred
    */
    public int countByitemPricingCodeQualifierByName(
        java.lang.String itemPricingQualifierName)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Caches the item pricing qualifier in the entity cache if it is enabled.
    *
    * @param itemPricingQualifier the item pricing qualifier
    */
    public void cacheResult(
        com.stpl.app.model.ItemPricingQualifier itemPricingQualifier);

    /**
    * Caches the item pricing qualifiers in the entity cache if it is enabled.
    *
    * @param itemPricingQualifiers the item pricing qualifiers
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.ItemPricingQualifier> itemPricingQualifiers);

    /**
    * Creates a new item pricing qualifier with the primary key. Does not add the item pricing qualifier to the database.
    *
    * @param itemPricingQualifierSid the primary key for the new item pricing qualifier
    * @return the new item pricing qualifier
    */
    public com.stpl.app.model.ItemPricingQualifier create(
        int itemPricingQualifierSid);

    /**
    * Removes the item pricing qualifier with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param itemPricingQualifierSid the primary key of the item pricing qualifier
    * @return the item pricing qualifier that was removed
    * @throws com.stpl.app.NoSuchItemPricingQualifierException if a item pricing qualifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemPricingQualifier remove(
        int itemPricingQualifierSid)
        throws com.stpl.app.NoSuchItemPricingQualifierException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.ItemPricingQualifier updateImpl(
        com.stpl.app.model.ItemPricingQualifier itemPricingQualifier)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item pricing qualifier with the primary key or throws a {@link com.stpl.app.NoSuchItemPricingQualifierException} if it could not be found.
    *
    * @param itemPricingQualifierSid the primary key of the item pricing qualifier
    * @return the item pricing qualifier
    * @throws com.stpl.app.NoSuchItemPricingQualifierException if a item pricing qualifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemPricingQualifier findByPrimaryKey(
        int itemPricingQualifierSid)
        throws com.stpl.app.NoSuchItemPricingQualifierException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item pricing qualifier with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param itemPricingQualifierSid the primary key of the item pricing qualifier
    * @return the item pricing qualifier, or <code>null</code> if a item pricing qualifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemPricingQualifier fetchByPrimaryKey(
        int itemPricingQualifierSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the item pricing qualifiers.
    *
    * @return the item pricing qualifiers
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemPricingQualifier> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the item pricing qualifiers.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemPricingQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of item pricing qualifiers
    * @param end the upper bound of the range of item pricing qualifiers (not inclusive)
    * @return the range of item pricing qualifiers
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemPricingQualifier> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the item pricing qualifiers.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemPricingQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of item pricing qualifiers
    * @param end the upper bound of the range of item pricing qualifiers (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of item pricing qualifiers
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemPricingQualifier> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the item pricing qualifiers from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of item pricing qualifiers.
    *
    * @return the number of item pricing qualifiers
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
