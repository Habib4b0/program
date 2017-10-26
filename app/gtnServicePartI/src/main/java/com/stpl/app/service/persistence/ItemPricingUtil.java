package com.stpl.app.service.persistence;

import com.stpl.app.model.ItemPricing;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the item pricing service. This utility wraps {@link ItemPricingPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ItemPricingPersistence
 * @see ItemPricingPersistenceImpl
 * @generated
 */
public class ItemPricingUtil {
    private static ItemPricingPersistence _persistence;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#clearCache()
     */
    public static void clearCache() {
        getPersistence().clearCache();
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#clearCache(com.stpl.portal.model.BaseModel)
     */
    public static void clearCache(ItemPricing itemPricing) {
        getPersistence().clearCache(itemPricing);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
     */
    public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().countWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
     */
    public static List<ItemPricing> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ItemPricing> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ItemPricing> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static ItemPricing update(ItemPricing itemPricing)
        throws SystemException {
        return getPersistence().update(itemPricing);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static ItemPricing update(ItemPricing itemPricing,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(itemPricing, serviceContext);
    }

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
    public static java.util.List<com.stpl.app.model.ItemPricing> findByItemPricing(
        int itemMasterSid, int itemUom, int itemPricingQualifierSid,
        int pricingCodeStatus, java.util.Date startDate)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemPricing(itemMasterSid, itemUom,
            itemPricingQualifierSid, pricingCodeStatus, startDate);
    }

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
    public static java.util.List<com.stpl.app.model.ItemPricing> findByItemPricing(
        int itemMasterSid, int itemUom, int itemPricingQualifierSid,
        int pricingCodeStatus, java.util.Date startDate, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemPricing(itemMasterSid, itemUom,
            itemPricingQualifierSid, pricingCodeStatus, startDate, start, end);
    }

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
    public static java.util.List<com.stpl.app.model.ItemPricing> findByItemPricing(
        int itemMasterSid, int itemUom, int itemPricingQualifierSid,
        int pricingCodeStatus, java.util.Date startDate, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemPricing(itemMasterSid, itemUom,
            itemPricingQualifierSid, pricingCodeStatus, startDate, start, end,
            orderByComparator);
    }

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
    public static com.stpl.app.model.ItemPricing findByItemPricing_First(
        int itemMasterSid, int itemUom, int itemPricingQualifierSid,
        int pricingCodeStatus, java.util.Date startDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemPricingException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemPricing_First(itemMasterSid, itemUom,
            itemPricingQualifierSid, pricingCodeStatus, startDate,
            orderByComparator);
    }

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
    public static com.stpl.app.model.ItemPricing fetchByItemPricing_First(
        int itemMasterSid, int itemUom, int itemPricingQualifierSid,
        int pricingCodeStatus, java.util.Date startDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByItemPricing_First(itemMasterSid, itemUom,
            itemPricingQualifierSid, pricingCodeStatus, startDate,
            orderByComparator);
    }

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
    public static com.stpl.app.model.ItemPricing findByItemPricing_Last(
        int itemMasterSid, int itemUom, int itemPricingQualifierSid,
        int pricingCodeStatus, java.util.Date startDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemPricingException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemPricing_Last(itemMasterSid, itemUom,
            itemPricingQualifierSid, pricingCodeStatus, startDate,
            orderByComparator);
    }

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
    public static com.stpl.app.model.ItemPricing fetchByItemPricing_Last(
        int itemMasterSid, int itemUom, int itemPricingQualifierSid,
        int pricingCodeStatus, java.util.Date startDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByItemPricing_Last(itemMasterSid, itemUom,
            itemPricingQualifierSid, pricingCodeStatus, startDate,
            orderByComparator);
    }

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
    public static com.stpl.app.model.ItemPricing[] findByItemPricing_PrevAndNext(
        int itemPricingSid, int itemMasterSid, int itemUom,
        int itemPricingQualifierSid, int pricingCodeStatus,
        java.util.Date startDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemPricingException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemPricing_PrevAndNext(itemPricingSid,
            itemMasterSid, itemUom, itemPricingQualifierSid, pricingCodeStatus,
            startDate, orderByComparator);
    }

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
    public static void removeByItemPricing(int itemMasterSid, int itemUom,
        int itemPricingQualifierSid, int pricingCodeStatus,
        java.util.Date startDate)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence()
            .removeByItemPricing(itemMasterSid, itemUom,
            itemPricingQualifierSid, pricingCodeStatus, startDate);
    }

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
    public static int countByItemPricing(int itemMasterSid, int itemUom,
        int itemPricingQualifierSid, int pricingCodeStatus,
        java.util.Date startDate)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByItemPricing(itemMasterSid, itemUom,
            itemPricingQualifierSid, pricingCodeStatus, startDate);
    }

    /**
    * Returns all the item pricings where itemMasterSid = &#63;.
    *
    * @param itemMasterSid the item master sid
    * @return the matching item pricings
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ItemPricing> findByItemPricingDetails(
        int itemMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByItemPricingDetails(itemMasterSid);
    }

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
    public static java.util.List<com.stpl.app.model.ItemPricing> findByItemPricingDetails(
        int itemMasterSid, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemPricingDetails(itemMasterSid, start, end);
    }

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
    public static java.util.List<com.stpl.app.model.ItemPricing> findByItemPricingDetails(
        int itemMasterSid, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemPricingDetails(itemMasterSid, start, end,
            orderByComparator);
    }

    /**
    * Returns the first item pricing in the ordered set where itemMasterSid = &#63;.
    *
    * @param itemMasterSid the item master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item pricing
    * @throws com.stpl.app.NoSuchItemPricingException if a matching item pricing could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemPricing findByItemPricingDetails_First(
        int itemMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemPricingException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemPricingDetails_First(itemMasterSid,
            orderByComparator);
    }

    /**
    * Returns the first item pricing in the ordered set where itemMasterSid = &#63;.
    *
    * @param itemMasterSid the item master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item pricing, or <code>null</code> if a matching item pricing could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemPricing fetchByItemPricingDetails_First(
        int itemMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByItemPricingDetails_First(itemMasterSid,
            orderByComparator);
    }

    /**
    * Returns the last item pricing in the ordered set where itemMasterSid = &#63;.
    *
    * @param itemMasterSid the item master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item pricing
    * @throws com.stpl.app.NoSuchItemPricingException if a matching item pricing could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemPricing findByItemPricingDetails_Last(
        int itemMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemPricingException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemPricingDetails_Last(itemMasterSid,
            orderByComparator);
    }

    /**
    * Returns the last item pricing in the ordered set where itemMasterSid = &#63;.
    *
    * @param itemMasterSid the item master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item pricing, or <code>null</code> if a matching item pricing could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemPricing fetchByItemPricingDetails_Last(
        int itemMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByItemPricingDetails_Last(itemMasterSid,
            orderByComparator);
    }

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
    public static com.stpl.app.model.ItemPricing[] findByItemPricingDetails_PrevAndNext(
        int itemPricingSid, int itemMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemPricingException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemPricingDetails_PrevAndNext(itemPricingSid,
            itemMasterSid, orderByComparator);
    }

    /**
    * Removes all the item pricings where itemMasterSid = &#63; from the database.
    *
    * @param itemMasterSid the item master sid
    * @throws SystemException if a system exception occurred
    */
    public static void removeByItemPricingDetails(int itemMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByItemPricingDetails(itemMasterSid);
    }

    /**
    * Returns the number of item pricings where itemMasterSid = &#63;.
    *
    * @param itemMasterSid the item master sid
    * @return the number of matching item pricings
    * @throws SystemException if a system exception occurred
    */
    public static int countByItemPricingDetails(int itemMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByItemPricingDetails(itemMasterSid);
    }

    /**
    * Caches the item pricing in the entity cache if it is enabled.
    *
    * @param itemPricing the item pricing
    */
    public static void cacheResult(com.stpl.app.model.ItemPricing itemPricing) {
        getPersistence().cacheResult(itemPricing);
    }

    /**
    * Caches the item pricings in the entity cache if it is enabled.
    *
    * @param itemPricings the item pricings
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.ItemPricing> itemPricings) {
        getPersistence().cacheResult(itemPricings);
    }

    /**
    * Creates a new item pricing with the primary key. Does not add the item pricing to the database.
    *
    * @param itemPricingSid the primary key for the new item pricing
    * @return the new item pricing
    */
    public static com.stpl.app.model.ItemPricing create(int itemPricingSid) {
        return getPersistence().create(itemPricingSid);
    }

    /**
    * Removes the item pricing with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param itemPricingSid the primary key of the item pricing
    * @return the item pricing that was removed
    * @throws com.stpl.app.NoSuchItemPricingException if a item pricing with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemPricing remove(int itemPricingSid)
        throws com.stpl.app.NoSuchItemPricingException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(itemPricingSid);
    }

    public static com.stpl.app.model.ItemPricing updateImpl(
        com.stpl.app.model.ItemPricing itemPricing)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(itemPricing);
    }

    /**
    * Returns the item pricing with the primary key or throws a {@link com.stpl.app.NoSuchItemPricingException} if it could not be found.
    *
    * @param itemPricingSid the primary key of the item pricing
    * @return the item pricing
    * @throws com.stpl.app.NoSuchItemPricingException if a item pricing with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemPricing findByPrimaryKey(
        int itemPricingSid)
        throws com.stpl.app.NoSuchItemPricingException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(itemPricingSid);
    }

    /**
    * Returns the item pricing with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param itemPricingSid the primary key of the item pricing
    * @return the item pricing, or <code>null</code> if a item pricing with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemPricing fetchByPrimaryKey(
        int itemPricingSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(itemPricingSid);
    }

    /**
    * Returns all the item pricings.
    *
    * @return the item pricings
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ItemPricing> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

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
    public static java.util.List<com.stpl.app.model.ItemPricing> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

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
    public static java.util.List<com.stpl.app.model.ItemPricing> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the item pricings from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of item pricings.
    *
    * @return the number of item pricings
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ItemPricingPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ItemPricingPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ItemPricingPersistence.class.getName());

            ReferenceRegistry.registerReference(ItemPricingUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ItemPricingPersistence persistence) {
    }
}
