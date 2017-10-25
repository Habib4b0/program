package com.stpl.app.service.persistence;

import com.stpl.app.model.ItemIdentifier;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the item identifier service. This utility wraps {@link ItemIdentifierPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ItemIdentifierPersistence
 * @see ItemIdentifierPersistenceImpl
 * @generated
 */
public class ItemIdentifierUtil {
    private static ItemIdentifierPersistence _persistence;

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
    public static void clearCache(ItemIdentifier itemIdentifier) {
        getPersistence().clearCache(itemIdentifier);
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
    public static List<ItemIdentifier> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ItemIdentifier> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ItemIdentifier> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static ItemIdentifier update(ItemIdentifier itemIdentifier)
        throws SystemException {
        return getPersistence().update(itemIdentifier);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static ItemIdentifier update(ItemIdentifier itemIdentifier,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(itemIdentifier, serviceContext);
    }

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
    public static java.util.List<com.stpl.app.model.ItemIdentifier> findByItemIrtIdentifier(
        java.lang.String itemIdentifierValue, int itemQualifierSid,
        int identifierStatus, java.util.Date startDate)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemIrtIdentifier(itemIdentifierValue,
            itemQualifierSid, identifierStatus, startDate);
    }

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
    public static java.util.List<com.stpl.app.model.ItemIdentifier> findByItemIrtIdentifier(
        java.lang.String itemIdentifierValue, int itemQualifierSid,
        int identifierStatus, java.util.Date startDate, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemIrtIdentifier(itemIdentifierValue,
            itemQualifierSid, identifierStatus, startDate, start, end);
    }

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
    public static java.util.List<com.stpl.app.model.ItemIdentifier> findByItemIrtIdentifier(
        java.lang.String itemIdentifierValue, int itemQualifierSid,
        int identifierStatus, java.util.Date startDate, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemIrtIdentifier(itemIdentifierValue,
            itemQualifierSid, identifierStatus, startDate, start, end,
            orderByComparator);
    }

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
    public static com.stpl.app.model.ItemIdentifier findByItemIrtIdentifier_First(
        java.lang.String itemIdentifierValue, int itemQualifierSid,
        int identifierStatus, java.util.Date startDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemIdentifierException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemIrtIdentifier_First(itemIdentifierValue,
            itemQualifierSid, identifierStatus, startDate, orderByComparator);
    }

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
    public static com.stpl.app.model.ItemIdentifier fetchByItemIrtIdentifier_First(
        java.lang.String itemIdentifierValue, int itemQualifierSid,
        int identifierStatus, java.util.Date startDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByItemIrtIdentifier_First(itemIdentifierValue,
            itemQualifierSid, identifierStatus, startDate, orderByComparator);
    }

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
    public static com.stpl.app.model.ItemIdentifier findByItemIrtIdentifier_Last(
        java.lang.String itemIdentifierValue, int itemQualifierSid,
        int identifierStatus, java.util.Date startDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemIdentifierException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemIrtIdentifier_Last(itemIdentifierValue,
            itemQualifierSid, identifierStatus, startDate, orderByComparator);
    }

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
    public static com.stpl.app.model.ItemIdentifier fetchByItemIrtIdentifier_Last(
        java.lang.String itemIdentifierValue, int itemQualifierSid,
        int identifierStatus, java.util.Date startDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByItemIrtIdentifier_Last(itemIdentifierValue,
            itemQualifierSid, identifierStatus, startDate, orderByComparator);
    }

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
    public static com.stpl.app.model.ItemIdentifier[] findByItemIrtIdentifier_PrevAndNext(
        int itemIdentifierSid, java.lang.String itemIdentifierValue,
        int itemQualifierSid, int identifierStatus, java.util.Date startDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemIdentifierException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemIrtIdentifier_PrevAndNext(itemIdentifierSid,
            itemIdentifierValue, itemQualifierSid, identifierStatus, startDate,
            orderByComparator);
    }

    /**
    * Removes all the item identifiers where itemIdentifierValue = &#63; and itemQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63; from the database.
    *
    * @param itemIdentifierValue the item identifier value
    * @param itemQualifierSid the item qualifier sid
    * @param identifierStatus the identifier status
    * @param startDate the start date
    * @throws SystemException if a system exception occurred
    */
    public static void removeByItemIrtIdentifier(
        java.lang.String itemIdentifierValue, int itemQualifierSid,
        int identifierStatus, java.util.Date startDate)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence()
            .removeByItemIrtIdentifier(itemIdentifierValue, itemQualifierSid,
            identifierStatus, startDate);
    }

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
    public static int countByItemIrtIdentifier(
        java.lang.String itemIdentifierValue, int itemQualifierSid,
        int identifierStatus, java.util.Date startDate)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByItemIrtIdentifier(itemIdentifierValue,
            itemQualifierSid, identifierStatus, startDate);
    }

    /**
    * Returns all the item identifiers where itemMasterSid = &#63;.
    *
    * @param itemMasterSid the item master sid
    * @return the matching item identifiers
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ItemIdentifier> findByItemIrtIdentifierDetails(
        int itemMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByItemIrtIdentifierDetails(itemMasterSid);
    }

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
    public static java.util.List<com.stpl.app.model.ItemIdentifier> findByItemIrtIdentifierDetails(
        int itemMasterSid, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemIrtIdentifierDetails(itemMasterSid, start, end);
    }

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
    public static java.util.List<com.stpl.app.model.ItemIdentifier> findByItemIrtIdentifierDetails(
        int itemMasterSid, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemIrtIdentifierDetails(itemMasterSid, start, end,
            orderByComparator);
    }

    /**
    * Returns the first item identifier in the ordered set where itemMasterSid = &#63;.
    *
    * @param itemMasterSid the item master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item identifier
    * @throws com.stpl.app.NoSuchItemIdentifierException if a matching item identifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemIdentifier findByItemIrtIdentifierDetails_First(
        int itemMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemIdentifierException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemIrtIdentifierDetails_First(itemMasterSid,
            orderByComparator);
    }

    /**
    * Returns the first item identifier in the ordered set where itemMasterSid = &#63;.
    *
    * @param itemMasterSid the item master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item identifier, or <code>null</code> if a matching item identifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemIdentifier fetchByItemIrtIdentifierDetails_First(
        int itemMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByItemIrtIdentifierDetails_First(itemMasterSid,
            orderByComparator);
    }

    /**
    * Returns the last item identifier in the ordered set where itemMasterSid = &#63;.
    *
    * @param itemMasterSid the item master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item identifier
    * @throws com.stpl.app.NoSuchItemIdentifierException if a matching item identifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemIdentifier findByItemIrtIdentifierDetails_Last(
        int itemMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemIdentifierException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemIrtIdentifierDetails_Last(itemMasterSid,
            orderByComparator);
    }

    /**
    * Returns the last item identifier in the ordered set where itemMasterSid = &#63;.
    *
    * @param itemMasterSid the item master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item identifier, or <code>null</code> if a matching item identifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemIdentifier fetchByItemIrtIdentifierDetails_Last(
        int itemMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByItemIrtIdentifierDetails_Last(itemMasterSid,
            orderByComparator);
    }

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
    public static com.stpl.app.model.ItemIdentifier[] findByItemIrtIdentifierDetails_PrevAndNext(
        int itemIdentifierSid, int itemMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemIdentifierException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemIrtIdentifierDetails_PrevAndNext(itemIdentifierSid,
            itemMasterSid, orderByComparator);
    }

    /**
    * Removes all the item identifiers where itemMasterSid = &#63; from the database.
    *
    * @param itemMasterSid the item master sid
    * @throws SystemException if a system exception occurred
    */
    public static void removeByItemIrtIdentifierDetails(int itemMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByItemIrtIdentifierDetails(itemMasterSid);
    }

    /**
    * Returns the number of item identifiers where itemMasterSid = &#63;.
    *
    * @param itemMasterSid the item master sid
    * @return the number of matching item identifiers
    * @throws SystemException if a system exception occurred
    */
    public static int countByItemIrtIdentifierDetails(int itemMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByItemIrtIdentifierDetails(itemMasterSid);
    }

    /**
    * Caches the item identifier in the entity cache if it is enabled.
    *
    * @param itemIdentifier the item identifier
    */
    public static void cacheResult(
        com.stpl.app.model.ItemIdentifier itemIdentifier) {
        getPersistence().cacheResult(itemIdentifier);
    }

    /**
    * Caches the item identifiers in the entity cache if it is enabled.
    *
    * @param itemIdentifiers the item identifiers
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.ItemIdentifier> itemIdentifiers) {
        getPersistence().cacheResult(itemIdentifiers);
    }

    /**
    * Creates a new item identifier with the primary key. Does not add the item identifier to the database.
    *
    * @param itemIdentifierSid the primary key for the new item identifier
    * @return the new item identifier
    */
    public static com.stpl.app.model.ItemIdentifier create(
        int itemIdentifierSid) {
        return getPersistence().create(itemIdentifierSid);
    }

    /**
    * Removes the item identifier with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param itemIdentifierSid the primary key of the item identifier
    * @return the item identifier that was removed
    * @throws com.stpl.app.NoSuchItemIdentifierException if a item identifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemIdentifier remove(
        int itemIdentifierSid)
        throws com.stpl.app.NoSuchItemIdentifierException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(itemIdentifierSid);
    }

    public static com.stpl.app.model.ItemIdentifier updateImpl(
        com.stpl.app.model.ItemIdentifier itemIdentifier)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(itemIdentifier);
    }

    /**
    * Returns the item identifier with the primary key or throws a {@link com.stpl.app.NoSuchItemIdentifierException} if it could not be found.
    *
    * @param itemIdentifierSid the primary key of the item identifier
    * @return the item identifier
    * @throws com.stpl.app.NoSuchItemIdentifierException if a item identifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemIdentifier findByPrimaryKey(
        int itemIdentifierSid)
        throws com.stpl.app.NoSuchItemIdentifierException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(itemIdentifierSid);
    }

    /**
    * Returns the item identifier with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param itemIdentifierSid the primary key of the item identifier
    * @return the item identifier, or <code>null</code> if a item identifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemIdentifier fetchByPrimaryKey(
        int itemIdentifierSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(itemIdentifierSid);
    }

    /**
    * Returns all the item identifiers.
    *
    * @return the item identifiers
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ItemIdentifier> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

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
    public static java.util.List<com.stpl.app.model.ItemIdentifier> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

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
    public static java.util.List<com.stpl.app.model.ItemIdentifier> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the item identifiers from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of item identifiers.
    *
    * @return the number of item identifiers
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ItemIdentifierPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ItemIdentifierPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ItemIdentifierPersistence.class.getName());

            ReferenceRegistry.registerReference(ItemIdentifierUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ItemIdentifierPersistence persistence) {
    }
}
