package com.stpl.app.service.persistence;

import com.stpl.app.model.ItemQualifier;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the item qualifier service. This utility wraps {@link ItemQualifierPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ItemQualifierPersistence
 * @see ItemQualifierPersistenceImpl
 * @generated
 */
public class ItemQualifierUtil {
    private static ItemQualifierPersistence _persistence;

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
    public static void clearCache(ItemQualifier itemQualifier) {
        getPersistence().clearCache(itemQualifier);
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
    public static List<ItemQualifier> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ItemQualifier> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ItemQualifier> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static ItemQualifier update(ItemQualifier itemQualifier)
        throws SystemException {
        return getPersistence().update(itemQualifier);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static ItemQualifier update(ItemQualifier itemQualifier,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(itemQualifier, serviceContext);
    }

    /**
    * Returns all the item qualifiers where itemQualifierValue = &#63;.
    *
    * @param itemQualifierValue the item qualifier value
    * @return the matching item qualifiers
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ItemQualifier> findByItemIrtQualifierName(
        java.lang.String itemQualifierValue)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByItemIrtQualifierName(itemQualifierValue);
    }

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
    public static java.util.List<com.stpl.app.model.ItemQualifier> findByItemIrtQualifierName(
        java.lang.String itemQualifierValue, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemIrtQualifierName(itemQualifierValue, start, end);
    }

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
    public static java.util.List<com.stpl.app.model.ItemQualifier> findByItemIrtQualifierName(
        java.lang.String itemQualifierValue, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemIrtQualifierName(itemQualifierValue, start, end,
            orderByComparator);
    }

    /**
    * Returns the first item qualifier in the ordered set where itemQualifierValue = &#63;.
    *
    * @param itemQualifierValue the item qualifier value
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item qualifier
    * @throws com.stpl.app.NoSuchItemQualifierException if a matching item qualifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemQualifier findByItemIrtQualifierName_First(
        java.lang.String itemQualifierValue,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemQualifierException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemIrtQualifierName_First(itemQualifierValue,
            orderByComparator);
    }

    /**
    * Returns the first item qualifier in the ordered set where itemQualifierValue = &#63;.
    *
    * @param itemQualifierValue the item qualifier value
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching item qualifier, or <code>null</code> if a matching item qualifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemQualifier fetchByItemIrtQualifierName_First(
        java.lang.String itemQualifierValue,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByItemIrtQualifierName_First(itemQualifierValue,
            orderByComparator);
    }

    /**
    * Returns the last item qualifier in the ordered set where itemQualifierValue = &#63;.
    *
    * @param itemQualifierValue the item qualifier value
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item qualifier
    * @throws com.stpl.app.NoSuchItemQualifierException if a matching item qualifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemQualifier findByItemIrtQualifierName_Last(
        java.lang.String itemQualifierValue,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemQualifierException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemIrtQualifierName_Last(itemQualifierValue,
            orderByComparator);
    }

    /**
    * Returns the last item qualifier in the ordered set where itemQualifierValue = &#63;.
    *
    * @param itemQualifierValue the item qualifier value
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching item qualifier, or <code>null</code> if a matching item qualifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemQualifier fetchByItemIrtQualifierName_Last(
        java.lang.String itemQualifierValue,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByItemIrtQualifierName_Last(itemQualifierValue,
            orderByComparator);
    }

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
    public static com.stpl.app.model.ItemQualifier[] findByItemIrtQualifierName_PrevAndNext(
        int itemQualifierSid, java.lang.String itemQualifierValue,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchItemQualifierException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemIrtQualifierName_PrevAndNext(itemQualifierSid,
            itemQualifierValue, orderByComparator);
    }

    /**
    * Removes all the item qualifiers where itemQualifierValue = &#63; from the database.
    *
    * @param itemQualifierValue the item qualifier value
    * @throws SystemException if a system exception occurred
    */
    public static void removeByItemIrtQualifierName(
        java.lang.String itemQualifierValue)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByItemIrtQualifierName(itemQualifierValue);
    }

    /**
    * Returns the number of item qualifiers where itemQualifierValue = &#63;.
    *
    * @param itemQualifierValue the item qualifier value
    * @return the number of matching item qualifiers
    * @throws SystemException if a system exception occurred
    */
    public static int countByItemIrtQualifierName(
        java.lang.String itemQualifierValue)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByItemIrtQualifierName(itemQualifierValue);
    }

    /**
    * Returns the item qualifier where itemQualifierName = &#63; or throws a {@link com.stpl.app.NoSuchItemQualifierException} if it could not be found.
    *
    * @param itemQualifierName the item qualifier name
    * @return the matching item qualifier
    * @throws com.stpl.app.NoSuchItemQualifierException if a matching item qualifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemQualifier findByItemIrtQualifierByName(
        java.lang.String itemQualifierName)
        throws com.stpl.app.NoSuchItemQualifierException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByItemIrtQualifierByName(itemQualifierName);
    }

    /**
    * Returns the item qualifier where itemQualifierName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param itemQualifierName the item qualifier name
    * @return the matching item qualifier, or <code>null</code> if a matching item qualifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemQualifier fetchByItemIrtQualifierByName(
        java.lang.String itemQualifierName)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByItemIrtQualifierByName(itemQualifierName);
    }

    /**
    * Returns the item qualifier where itemQualifierName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param itemQualifierName the item qualifier name
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching item qualifier, or <code>null</code> if a matching item qualifier could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemQualifier fetchByItemIrtQualifierByName(
        java.lang.String itemQualifierName, boolean retrieveFromCache)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByItemIrtQualifierByName(itemQualifierName,
            retrieveFromCache);
    }

    /**
    * Removes the item qualifier where itemQualifierName = &#63; from the database.
    *
    * @param itemQualifierName the item qualifier name
    * @return the item qualifier that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemQualifier removeByItemIrtQualifierByName(
        java.lang.String itemQualifierName)
        throws com.stpl.app.NoSuchItemQualifierException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().removeByItemIrtQualifierByName(itemQualifierName);
    }

    /**
    * Returns the number of item qualifiers where itemQualifierName = &#63;.
    *
    * @param itemQualifierName the item qualifier name
    * @return the number of matching item qualifiers
    * @throws SystemException if a system exception occurred
    */
    public static int countByItemIrtQualifierByName(
        java.lang.String itemQualifierName)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByItemIrtQualifierByName(itemQualifierName);
    }

    /**
    * Caches the item qualifier in the entity cache if it is enabled.
    *
    * @param itemQualifier the item qualifier
    */
    public static void cacheResult(
        com.stpl.app.model.ItemQualifier itemQualifier) {
        getPersistence().cacheResult(itemQualifier);
    }

    /**
    * Caches the item qualifiers in the entity cache if it is enabled.
    *
    * @param itemQualifiers the item qualifiers
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.ItemQualifier> itemQualifiers) {
        getPersistence().cacheResult(itemQualifiers);
    }

    /**
    * Creates a new item qualifier with the primary key. Does not add the item qualifier to the database.
    *
    * @param itemQualifierSid the primary key for the new item qualifier
    * @return the new item qualifier
    */
    public static com.stpl.app.model.ItemQualifier create(int itemQualifierSid) {
        return getPersistence().create(itemQualifierSid);
    }

    /**
    * Removes the item qualifier with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param itemQualifierSid the primary key of the item qualifier
    * @return the item qualifier that was removed
    * @throws com.stpl.app.NoSuchItemQualifierException if a item qualifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemQualifier remove(int itemQualifierSid)
        throws com.stpl.app.NoSuchItemQualifierException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(itemQualifierSid);
    }

    public static com.stpl.app.model.ItemQualifier updateImpl(
        com.stpl.app.model.ItemQualifier itemQualifier)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(itemQualifier);
    }

    /**
    * Returns the item qualifier with the primary key or throws a {@link com.stpl.app.NoSuchItemQualifierException} if it could not be found.
    *
    * @param itemQualifierSid the primary key of the item qualifier
    * @return the item qualifier
    * @throws com.stpl.app.NoSuchItemQualifierException if a item qualifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemQualifier findByPrimaryKey(
        int itemQualifierSid)
        throws com.stpl.app.NoSuchItemQualifierException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(itemQualifierSid);
    }

    /**
    * Returns the item qualifier with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param itemQualifierSid the primary key of the item qualifier
    * @return the item qualifier, or <code>null</code> if a item qualifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemQualifier fetchByPrimaryKey(
        int itemQualifierSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(itemQualifierSid);
    }

    /**
    * Returns all the item qualifiers.
    *
    * @return the item qualifiers
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ItemQualifier> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

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
    public static java.util.List<com.stpl.app.model.ItemQualifier> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

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
    public static java.util.List<com.stpl.app.model.ItemQualifier> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the item qualifiers from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of item qualifiers.
    *
    * @return the number of item qualifiers
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ItemQualifierPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ItemQualifierPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ItemQualifierPersistence.class.getName());

            ReferenceRegistry.registerReference(ItemQualifierUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ItemQualifierPersistence persistence) {
    }
}
