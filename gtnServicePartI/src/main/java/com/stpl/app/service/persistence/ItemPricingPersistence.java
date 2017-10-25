package com.stpl.app.service.persistence;

import com.stpl.app.model.ItemPricing;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the item pricing service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ItemPricingPersistenceImpl
 * @see ItemPricingUtil
 * @generated
 */
public interface ItemPricingPersistence extends BasePersistence<ItemPricing> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ItemPricingUtil} to access the item pricing persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the item pricings where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63;.
    *
    * @param itemMasterSid the item master sid
    * @param itemUom the item uom
    * @param itemPricingQualifierSid the item pricing qualifier sid
    * @param pricingCodeStatus the pricing code status
    * @param startDate the start date
    * @return the matching item pricings
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemPricing> findByItemPricing(
        int itemMasterSid, int itemUom, int itemPricingQualifierSid,
        int pricingCodeStatus, java.util.Date startDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the item pricings where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemMasterSid the item master sid
    * @param itemUom the item uom
    * @param itemPricingQualifierSid the item pricing qualifier sid
    * @param pricingCodeStatus the pricing code status
    * @param startDate the start date
    * @param start the lower bound of the range of item pricings
    * @param end the upper bound of the range of item pricings (not inclusive)
    * @return the range of matching item pricings
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemPricing> findByItemPricing(
        int itemMasterSid, int itemUom, int itemPricingQualifierSid,
        int pricingCodeStatus, java.util.Date startDate, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the item pricings where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemMasterSid the item master sid
    * @param itemUom the item uom
    * @param itemPricingQualifierSid the item pricing qualifier sid
    * @param pricingCodeStatus the pricing code status
    * @param startDate the start date
    * @param start the lower bound of the range of item pricings
    * @param end the upper bound of the range of item pricings (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching item pricings
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemPricing> findByItemPricing(
        int itemMasterSid, int itemUom, int itemPricingQualifierSid,
        int pricingCodeStatus, java.util.Date startDate, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item pricing in the ordered set where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63;.
    *
    * @param itemMasterSid the item master sid
    * @param itemUom the item uom
    * @param itemPricingQualifierSid the item pricing qualifier sid
    * @param pricingCodeStatus the pricing code status
    * @param startDate the start date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item pricing
    * @throws com.stpl.app.NoSuchItemPricingException if a matching item pricing could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemPricing findByItemPricing_First(
        int itemMasterSid, int itemUom, int itemPricingQualifierSid,
        int pricingCodeStatus, java.util.Date startDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemPricingException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item pricing in the ordered set where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63;.
    *
    * @param itemMasterSid the item master sid
    * @param itemUom the item uom
    * @param itemPricingQualifierSid the item pricing qualifier sid
    * @param pricingCodeStatus the pricing code status
    * @param startDate the start date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item pricing, or <code>null</code> if a matching item pricing could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemPricing fetchByItemPricing_First(
        int itemMasterSid, int itemUom, int itemPricingQualifierSid,
        int pricingCodeStatus, java.util.Date startDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item pricing in the ordered set where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63;.
    *
    * @param itemMasterSid the item master sid
    * @param itemUom the item uom
    * @param itemPricingQualifierSid the item pricing qualifier sid
    * @param pricingCodeStatus the pricing code status
    * @param startDate the start date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item pricing
    * @throws com.stpl.app.NoSuchItemPricingException if a matching item pricing could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemPricing findByItemPricing_Last(
        int itemMasterSid, int itemUom, int itemPricingQualifierSid,
        int pricingCodeStatus, java.util.Date startDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemPricingException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item pricing in the ordered set where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63;.
    *
    * @param itemMasterSid the item master sid
    * @param itemUom the item uom
    * @param itemPricingQualifierSid the item pricing qualifier sid
    * @param pricingCodeStatus the pricing code status
    * @param startDate the start date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item pricing, or <code>null</code> if a matching item pricing could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemPricing fetchByItemPricing_Last(
        int itemMasterSid, int itemUom, int itemPricingQualifierSid,
        int pricingCodeStatus, java.util.Date startDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item pricings before and after the current item pricing in the ordered set where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63;.
    *
    * @param itemPricingSid the primary key of the current item pricing
    * @param itemMasterSid the item master sid
    * @param itemUom the item uom
    * @param itemPricingQualifierSid the item pricing qualifier sid
    * @param pricingCodeStatus the pricing code status
    * @param startDate the start date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next item pricing
    * @throws com.stpl.app.NoSuchItemPricingException if a item pricing with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemPricing[] findByItemPricing_PrevAndNext(
        int itemPricingSid, int itemMasterSid, int itemUom,
        int itemPricingQualifierSid, int pricingCodeStatus,
        java.util.Date startDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemPricingException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the item pricings where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63; from the database.
    *
    * @param itemMasterSid the item master sid
    * @param itemUom the item uom
    * @param itemPricingQualifierSid the item pricing qualifier sid
    * @param pricingCodeStatus the pricing code status
    * @param startDate the start date
    * @throws SystemException if a system exception occurred
    */
    public void removeByItemPricing(int itemMasterSid, int itemUom,
        int itemPricingQualifierSid, int pricingCodeStatus,
        java.util.Date startDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of item pricings where itemMasterSid = &#63; and itemUom = &#63; and itemPricingQualifierSid = &#63; and pricingCodeStatus = &#63; and startDate = &#63;.
    *
    * @param itemMasterSid the item master sid
    * @param itemUom the item uom
    * @param itemPricingQualifierSid the item pricing qualifier sid
    * @param pricingCodeStatus the pricing code status
    * @param startDate the start date
    * @return the number of matching item pricings
    * @throws SystemException if a system exception occurred
    */
    public int countByItemPricing(int itemMasterSid, int itemUom,
        int itemPricingQualifierSid, int pricingCodeStatus,
        java.util.Date startDate)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the item pricings where itemMasterSid = &#63;.
    *
    * @param itemMasterSid the item master sid
    * @return the matching item pricings
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemPricing> findByItemPricingDetails(
        int itemMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the item pricings where itemMasterSid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemMasterSid the item master sid
    * @param start the lower bound of the range of item pricings
    * @param end the upper bound of the range of item pricings (not inclusive)
    * @return the range of matching item pricings
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemPricing> findByItemPricingDetails(
        int itemMasterSid, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the item pricings where itemMasterSid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemMasterSid the item master sid
    * @param start the lower bound of the range of item pricings
    * @param end the upper bound of the range of item pricings (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching item pricings
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemPricing> findByItemPricingDetails(
        int itemMasterSid, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item pricing in the ordered set where itemMasterSid = &#63;.
    *
    * @param itemMasterSid the item master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item pricing
    * @throws com.stpl.app.NoSuchItemPricingException if a matching item pricing could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemPricing findByItemPricingDetails_First(
        int itemMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemPricingException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first item pricing in the ordered set where itemMasterSid = &#63;.
    *
    * @param itemMasterSid the item master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item pricing, or <code>null</code> if a matching item pricing could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemPricing fetchByItemPricingDetails_First(
        int itemMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item pricing in the ordered set where itemMasterSid = &#63;.
    *
    * @param itemMasterSid the item master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item pricing
    * @throws com.stpl.app.NoSuchItemPricingException if a matching item pricing could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemPricing findByItemPricingDetails_Last(
        int itemMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemPricingException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last item pricing in the ordered set where itemMasterSid = &#63;.
    *
    * @param itemMasterSid the item master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item pricing, or <code>null</code> if a matching item pricing could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemPricing fetchByItemPricingDetails_Last(
        int itemMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item pricings before and after the current item pricing in the ordered set where itemMasterSid = &#63;.
    *
    * @param itemPricingSid the primary key of the current item pricing
    * @param itemMasterSid the item master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next item pricing
    * @throws com.stpl.app.NoSuchItemPricingException if a item pricing with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemPricing[] findByItemPricingDetails_PrevAndNext(
        int itemPricingSid, int itemMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemPricingException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the item pricings where itemMasterSid = &#63; from the database.
    *
    * @param itemMasterSid the item master sid
    * @throws SystemException if a system exception occurred
    */
    public void removeByItemPricingDetails(int itemMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of item pricings where itemMasterSid = &#63;.
    *
    * @param itemMasterSid the item master sid
    * @return the number of matching item pricings
    * @throws SystemException if a system exception occurred
    */
    public int countByItemPricingDetails(int itemMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Caches the item pricing in the entity cache if it is enabled.
    *
    * @param itemPricing the item pricing
    */
    public void cacheResult(com.stpl.app.model.ItemPricing itemPricing);

    /**
    * Caches the item pricings in the entity cache if it is enabled.
    *
    * @param itemPricings the item pricings
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.ItemPricing> itemPricings);

    /**
    * Creates a new item pricing with the primary key. Does not add the item pricing to the database.
    *
    * @param itemPricingSid the primary key for the new item pricing
    * @return the new item pricing
    */
    public com.stpl.app.model.ItemPricing create(int itemPricingSid);

    /**
    * Removes the item pricing with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param itemPricingSid the primary key of the item pricing
    * @return the item pricing that was removed
    * @throws com.stpl.app.NoSuchItemPricingException if a item pricing with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemPricing remove(int itemPricingSid)
        throws com.stpl.app.NoSuchItemPricingException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.ItemPricing updateImpl(
        com.stpl.app.model.ItemPricing itemPricing)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item pricing with the primary key or throws a {@link com.stpl.app.NoSuchItemPricingException} if it could not be found.
    *
    * @param itemPricingSid the primary key of the item pricing
    * @return the item pricing
    * @throws com.stpl.app.NoSuchItemPricingException if a item pricing with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemPricing findByPrimaryKey(int itemPricingSid)
        throws com.stpl.app.NoSuchItemPricingException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item pricing with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param itemPricingSid the primary key of the item pricing
    * @return the item pricing, or <code>null</code> if a item pricing with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemPricing fetchByPrimaryKey(int itemPricingSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the item pricings.
    *
    * @return the item pricings
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemPricing> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the item pricings.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of item pricings
    * @param end the upper bound of the range of item pricings (not inclusive)
    * @return the range of item pricings
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemPricing> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the item pricings.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of item pricings
    * @param end the upper bound of the range of item pricings (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of item pricings
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemPricing> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the item pricings from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of item pricings.
    *
    * @return the number of item pricings
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
