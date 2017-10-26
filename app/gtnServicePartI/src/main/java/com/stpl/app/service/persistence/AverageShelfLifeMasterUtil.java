package com.stpl.app.service.persistence;

import com.stpl.app.model.AverageShelfLifeMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the average shelf life master service. This utility wraps {@link AverageShelfLifeMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AverageShelfLifeMasterPersistence
 * @see AverageShelfLifeMasterPersistenceImpl
 * @generated
 */
public class AverageShelfLifeMasterUtil {
    private static AverageShelfLifeMasterPersistence _persistence;

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
    public static void clearCache(AverageShelfLifeMaster averageShelfLifeMaster) {
        getPersistence().clearCache(averageShelfLifeMaster);
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
    public static List<AverageShelfLifeMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<AverageShelfLifeMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<AverageShelfLifeMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static AverageShelfLifeMaster update(
        AverageShelfLifeMaster averageShelfLifeMaster)
        throws SystemException {
        return getPersistence().update(averageShelfLifeMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static AverageShelfLifeMaster update(
        AverageShelfLifeMaster averageShelfLifeMaster,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(averageShelfLifeMaster, serviceContext);
    }

    /**
    * Returns all the average shelf life masters where itemIdType = &#63;.
    *
    * @param itemIdType the item ID type
    * @return the matching average shelf life masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.AverageShelfLifeMaster> findByItemIdType(
        java.lang.String itemIdType)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByItemIdType(itemIdType);
    }

    /**
    * Returns a range of all the average shelf life masters where itemIdType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemIdType the item ID type
    * @param start the lower bound of the range of average shelf life masters
    * @param end the upper bound of the range of average shelf life masters (not inclusive)
    * @return the range of matching average shelf life masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.AverageShelfLifeMaster> findByItemIdType(
        java.lang.String itemIdType, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByItemIdType(itemIdType, start, end);
    }

    /**
    * Returns an ordered range of all the average shelf life masters where itemIdType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemIdType the item ID type
    * @param start the lower bound of the range of average shelf life masters
    * @param end the upper bound of the range of average shelf life masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching average shelf life masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.AverageShelfLifeMaster> findByItemIdType(
        java.lang.String itemIdType, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemIdType(itemIdType, start, end, orderByComparator);
    }

    /**
    * Returns the first average shelf life master in the ordered set where itemIdType = &#63;.
    *
    * @param itemIdType the item ID type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching average shelf life master
    * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a matching average shelf life master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.AverageShelfLifeMaster findByItemIdType_First(
        java.lang.String itemIdType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchAverageShelfLifeMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemIdType_First(itemIdType, orderByComparator);
    }

    /**
    * Returns the first average shelf life master in the ordered set where itemIdType = &#63;.
    *
    * @param itemIdType the item ID type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching average shelf life master, or <code>null</code> if a matching average shelf life master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.AverageShelfLifeMaster fetchByItemIdType_First(
        java.lang.String itemIdType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByItemIdType_First(itemIdType, orderByComparator);
    }

    /**
    * Returns the last average shelf life master in the ordered set where itemIdType = &#63;.
    *
    * @param itemIdType the item ID type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching average shelf life master
    * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a matching average shelf life master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.AverageShelfLifeMaster findByItemIdType_Last(
        java.lang.String itemIdType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchAverageShelfLifeMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemIdType_Last(itemIdType, orderByComparator);
    }

    /**
    * Returns the last average shelf life master in the ordered set where itemIdType = &#63;.
    *
    * @param itemIdType the item ID type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching average shelf life master, or <code>null</code> if a matching average shelf life master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.AverageShelfLifeMaster fetchByItemIdType_Last(
        java.lang.String itemIdType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByItemIdType_Last(itemIdType, orderByComparator);
    }

    /**
    * Returns the average shelf life masters before and after the current average shelf life master in the ordered set where itemIdType = &#63;.
    *
    * @param averageShelfLifeMasterSid the primary key of the current average shelf life master
    * @param itemIdType the item ID type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next average shelf life master
    * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a average shelf life master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.AverageShelfLifeMaster[] findByItemIdType_PrevAndNext(
        int averageShelfLifeMasterSid, java.lang.String itemIdType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchAverageShelfLifeMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemIdType_PrevAndNext(averageShelfLifeMasterSid,
            itemIdType, orderByComparator);
    }

    /**
    * Removes all the average shelf life masters where itemIdType = &#63; from the database.
    *
    * @param itemIdType the item ID type
    * @throws SystemException if a system exception occurred
    */
    public static void removeByItemIdType(java.lang.String itemIdType)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByItemIdType(itemIdType);
    }

    /**
    * Returns the number of average shelf life masters where itemIdType = &#63;.
    *
    * @param itemIdType the item ID type
    * @return the number of matching average shelf life masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByItemIdType(java.lang.String itemIdType)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByItemIdType(itemIdType);
    }

    /**
    * Returns all the average shelf life masters where itemId = &#63;.
    *
    * @param itemId the item ID
    * @return the matching average shelf life masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.AverageShelfLifeMaster> findByItemId(
        java.lang.String itemId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByItemId(itemId);
    }

    /**
    * Returns a range of all the average shelf life masters where itemId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemId the item ID
    * @param start the lower bound of the range of average shelf life masters
    * @param end the upper bound of the range of average shelf life masters (not inclusive)
    * @return the range of matching average shelf life masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.AverageShelfLifeMaster> findByItemId(
        java.lang.String itemId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByItemId(itemId, start, end);
    }

    /**
    * Returns an ordered range of all the average shelf life masters where itemId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemId the item ID
    * @param start the lower bound of the range of average shelf life masters
    * @param end the upper bound of the range of average shelf life masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching average shelf life masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.AverageShelfLifeMaster> findByItemId(
        java.lang.String itemId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemId(itemId, start, end, orderByComparator);
    }

    /**
    * Returns the first average shelf life master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching average shelf life master
    * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a matching average shelf life master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.AverageShelfLifeMaster findByItemId_First(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchAverageShelfLifeMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByItemId_First(itemId, orderByComparator);
    }

    /**
    * Returns the first average shelf life master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching average shelf life master, or <code>null</code> if a matching average shelf life master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.AverageShelfLifeMaster fetchByItemId_First(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByItemId_First(itemId, orderByComparator);
    }

    /**
    * Returns the last average shelf life master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching average shelf life master
    * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a matching average shelf life master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.AverageShelfLifeMaster findByItemId_Last(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchAverageShelfLifeMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByItemId_Last(itemId, orderByComparator);
    }

    /**
    * Returns the last average shelf life master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching average shelf life master, or <code>null</code> if a matching average shelf life master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.AverageShelfLifeMaster fetchByItemId_Last(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByItemId_Last(itemId, orderByComparator);
    }

    /**
    * Returns the average shelf life masters before and after the current average shelf life master in the ordered set where itemId = &#63;.
    *
    * @param averageShelfLifeMasterSid the primary key of the current average shelf life master
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next average shelf life master
    * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a average shelf life master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.AverageShelfLifeMaster[] findByItemId_PrevAndNext(
        int averageShelfLifeMasterSid, java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchAverageShelfLifeMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemId_PrevAndNext(averageShelfLifeMasterSid, itemId,
            orderByComparator);
    }

    /**
    * Removes all the average shelf life masters where itemId = &#63; from the database.
    *
    * @param itemId the item ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByItemId(java.lang.String itemId)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByItemId(itemId);
    }

    /**
    * Returns the number of average shelf life masters where itemId = &#63;.
    *
    * @param itemId the item ID
    * @return the number of matching average shelf life masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByItemId(java.lang.String itemId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByItemId(itemId);
    }

    /**
    * Returns all the average shelf life masters where avgShelfLife = &#63;.
    *
    * @param avgShelfLife the avg shelf life
    * @return the matching average shelf life masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.AverageShelfLifeMaster> findByAvgShelfLife(
        java.lang.String avgShelfLife)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByAvgShelfLife(avgShelfLife);
    }

    /**
    * Returns a range of all the average shelf life masters where avgShelfLife = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param avgShelfLife the avg shelf life
    * @param start the lower bound of the range of average shelf life masters
    * @param end the upper bound of the range of average shelf life masters (not inclusive)
    * @return the range of matching average shelf life masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.AverageShelfLifeMaster> findByAvgShelfLife(
        java.lang.String avgShelfLife, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByAvgShelfLife(avgShelfLife, start, end);
    }

    /**
    * Returns an ordered range of all the average shelf life masters where avgShelfLife = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param avgShelfLife the avg shelf life
    * @param start the lower bound of the range of average shelf life masters
    * @param end the upper bound of the range of average shelf life masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching average shelf life masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.AverageShelfLifeMaster> findByAvgShelfLife(
        java.lang.String avgShelfLife, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAvgShelfLife(avgShelfLife, start, end,
            orderByComparator);
    }

    /**
    * Returns the first average shelf life master in the ordered set where avgShelfLife = &#63;.
    *
    * @param avgShelfLife the avg shelf life
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching average shelf life master
    * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a matching average shelf life master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.AverageShelfLifeMaster findByAvgShelfLife_First(
        java.lang.String avgShelfLife,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchAverageShelfLifeMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAvgShelfLife_First(avgShelfLife, orderByComparator);
    }

    /**
    * Returns the first average shelf life master in the ordered set where avgShelfLife = &#63;.
    *
    * @param avgShelfLife the avg shelf life
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching average shelf life master, or <code>null</code> if a matching average shelf life master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.AverageShelfLifeMaster fetchByAvgShelfLife_First(
        java.lang.String avgShelfLife,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByAvgShelfLife_First(avgShelfLife, orderByComparator);
    }

    /**
    * Returns the last average shelf life master in the ordered set where avgShelfLife = &#63;.
    *
    * @param avgShelfLife the avg shelf life
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching average shelf life master
    * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a matching average shelf life master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.AverageShelfLifeMaster findByAvgShelfLife_Last(
        java.lang.String avgShelfLife,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchAverageShelfLifeMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAvgShelfLife_Last(avgShelfLife, orderByComparator);
    }

    /**
    * Returns the last average shelf life master in the ordered set where avgShelfLife = &#63;.
    *
    * @param avgShelfLife the avg shelf life
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching average shelf life master, or <code>null</code> if a matching average shelf life master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.AverageShelfLifeMaster fetchByAvgShelfLife_Last(
        java.lang.String avgShelfLife,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByAvgShelfLife_Last(avgShelfLife, orderByComparator);
    }

    /**
    * Returns the average shelf life masters before and after the current average shelf life master in the ordered set where avgShelfLife = &#63;.
    *
    * @param averageShelfLifeMasterSid the primary key of the current average shelf life master
    * @param avgShelfLife the avg shelf life
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next average shelf life master
    * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a average shelf life master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.AverageShelfLifeMaster[] findByAvgShelfLife_PrevAndNext(
        int averageShelfLifeMasterSid, java.lang.String avgShelfLife,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchAverageShelfLifeMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAvgShelfLife_PrevAndNext(averageShelfLifeMasterSid,
            avgShelfLife, orderByComparator);
    }

    /**
    * Removes all the average shelf life masters where avgShelfLife = &#63; from the database.
    *
    * @param avgShelfLife the avg shelf life
    * @throws SystemException if a system exception occurred
    */
    public static void removeByAvgShelfLife(java.lang.String avgShelfLife)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByAvgShelfLife(avgShelfLife);
    }

    /**
    * Returns the number of average shelf life masters where avgShelfLife = &#63;.
    *
    * @param avgShelfLife the avg shelf life
    * @return the number of matching average shelf life masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByAvgShelfLife(java.lang.String avgShelfLife)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByAvgShelfLife(avgShelfLife);
    }

    /**
    * Returns all the average shelf life masters where itemIdType = &#63; and itemId = &#63;.
    *
    * @param itemIdType the item ID type
    * @param itemId the item ID
    * @return the matching average shelf life masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.AverageShelfLifeMaster> findByAverageShelfLifeUnique(
        java.lang.String itemIdType, java.lang.String itemId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByAverageShelfLifeUnique(itemIdType, itemId);
    }

    /**
    * Returns a range of all the average shelf life masters where itemIdType = &#63; and itemId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemIdType the item ID type
    * @param itemId the item ID
    * @param start the lower bound of the range of average shelf life masters
    * @param end the upper bound of the range of average shelf life masters (not inclusive)
    * @return the range of matching average shelf life masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.AverageShelfLifeMaster> findByAverageShelfLifeUnique(
        java.lang.String itemIdType, java.lang.String itemId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAverageShelfLifeUnique(itemIdType, itemId, start, end);
    }

    /**
    * Returns an ordered range of all the average shelf life masters where itemIdType = &#63; and itemId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemIdType the item ID type
    * @param itemId the item ID
    * @param start the lower bound of the range of average shelf life masters
    * @param end the upper bound of the range of average shelf life masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching average shelf life masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.AverageShelfLifeMaster> findByAverageShelfLifeUnique(
        java.lang.String itemIdType, java.lang.String itemId, int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAverageShelfLifeUnique(itemIdType, itemId, start,
            end, orderByComparator);
    }

    /**
    * Returns the first average shelf life master in the ordered set where itemIdType = &#63; and itemId = &#63;.
    *
    * @param itemIdType the item ID type
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching average shelf life master
    * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a matching average shelf life master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.AverageShelfLifeMaster findByAverageShelfLifeUnique_First(
        java.lang.String itemIdType, java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchAverageShelfLifeMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAverageShelfLifeUnique_First(itemIdType, itemId,
            orderByComparator);
    }

    /**
    * Returns the first average shelf life master in the ordered set where itemIdType = &#63; and itemId = &#63;.
    *
    * @param itemIdType the item ID type
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching average shelf life master, or <code>null</code> if a matching average shelf life master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.AverageShelfLifeMaster fetchByAverageShelfLifeUnique_First(
        java.lang.String itemIdType, java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByAverageShelfLifeUnique_First(itemIdType, itemId,
            orderByComparator);
    }

    /**
    * Returns the last average shelf life master in the ordered set where itemIdType = &#63; and itemId = &#63;.
    *
    * @param itemIdType the item ID type
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching average shelf life master
    * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a matching average shelf life master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.AverageShelfLifeMaster findByAverageShelfLifeUnique_Last(
        java.lang.String itemIdType, java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchAverageShelfLifeMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAverageShelfLifeUnique_Last(itemIdType, itemId,
            orderByComparator);
    }

    /**
    * Returns the last average shelf life master in the ordered set where itemIdType = &#63; and itemId = &#63;.
    *
    * @param itemIdType the item ID type
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching average shelf life master, or <code>null</code> if a matching average shelf life master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.AverageShelfLifeMaster fetchByAverageShelfLifeUnique_Last(
        java.lang.String itemIdType, java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByAverageShelfLifeUnique_Last(itemIdType, itemId,
            orderByComparator);
    }

    /**
    * Returns the average shelf life masters before and after the current average shelf life master in the ordered set where itemIdType = &#63; and itemId = &#63;.
    *
    * @param averageShelfLifeMasterSid the primary key of the current average shelf life master
    * @param itemIdType the item ID type
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next average shelf life master
    * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a average shelf life master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.AverageShelfLifeMaster[] findByAverageShelfLifeUnique_PrevAndNext(
        int averageShelfLifeMasterSid, java.lang.String itemIdType,
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchAverageShelfLifeMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAverageShelfLifeUnique_PrevAndNext(averageShelfLifeMasterSid,
            itemIdType, itemId, orderByComparator);
    }

    /**
    * Removes all the average shelf life masters where itemIdType = &#63; and itemId = &#63; from the database.
    *
    * @param itemIdType the item ID type
    * @param itemId the item ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByAverageShelfLifeUnique(
        java.lang.String itemIdType, java.lang.String itemId)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByAverageShelfLifeUnique(itemIdType, itemId);
    }

    /**
    * Returns the number of average shelf life masters where itemIdType = &#63; and itemId = &#63;.
    *
    * @param itemIdType the item ID type
    * @param itemId the item ID
    * @return the number of matching average shelf life masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByAverageShelfLifeUnique(
        java.lang.String itemIdType, java.lang.String itemId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByAverageShelfLifeUnique(itemIdType, itemId);
    }

    /**
    * Caches the average shelf life master in the entity cache if it is enabled.
    *
    * @param averageShelfLifeMaster the average shelf life master
    */
    public static void cacheResult(
        com.stpl.app.model.AverageShelfLifeMaster averageShelfLifeMaster) {
        getPersistence().cacheResult(averageShelfLifeMaster);
    }

    /**
    * Caches the average shelf life masters in the entity cache if it is enabled.
    *
    * @param averageShelfLifeMasters the average shelf life masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.AverageShelfLifeMaster> averageShelfLifeMasters) {
        getPersistence().cacheResult(averageShelfLifeMasters);
    }

    /**
    * Creates a new average shelf life master with the primary key. Does not add the average shelf life master to the database.
    *
    * @param averageShelfLifeMasterSid the primary key for the new average shelf life master
    * @return the new average shelf life master
    */
    public static com.stpl.app.model.AverageShelfLifeMaster create(
        int averageShelfLifeMasterSid) {
        return getPersistence().create(averageShelfLifeMasterSid);
    }

    /**
    * Removes the average shelf life master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param averageShelfLifeMasterSid the primary key of the average shelf life master
    * @return the average shelf life master that was removed
    * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a average shelf life master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.AverageShelfLifeMaster remove(
        int averageShelfLifeMasterSid)
        throws com.stpl.app.NoSuchAverageShelfLifeMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(averageShelfLifeMasterSid);
    }

    public static com.stpl.app.model.AverageShelfLifeMaster updateImpl(
        com.stpl.app.model.AverageShelfLifeMaster averageShelfLifeMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(averageShelfLifeMaster);
    }

    /**
    * Returns the average shelf life master with the primary key or throws a {@link com.stpl.app.NoSuchAverageShelfLifeMasterException} if it could not be found.
    *
    * @param averageShelfLifeMasterSid the primary key of the average shelf life master
    * @return the average shelf life master
    * @throws com.stpl.app.NoSuchAverageShelfLifeMasterException if a average shelf life master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.AverageShelfLifeMaster findByPrimaryKey(
        int averageShelfLifeMasterSid)
        throws com.stpl.app.NoSuchAverageShelfLifeMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(averageShelfLifeMasterSid);
    }

    /**
    * Returns the average shelf life master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param averageShelfLifeMasterSid the primary key of the average shelf life master
    * @return the average shelf life master, or <code>null</code> if a average shelf life master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.AverageShelfLifeMaster fetchByPrimaryKey(
        int averageShelfLifeMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(averageShelfLifeMasterSid);
    }

    /**
    * Returns all the average shelf life masters.
    *
    * @return the average shelf life masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.AverageShelfLifeMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the average shelf life masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of average shelf life masters
    * @param end the upper bound of the range of average shelf life masters (not inclusive)
    * @return the range of average shelf life masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.AverageShelfLifeMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the average shelf life masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of average shelf life masters
    * @param end the upper bound of the range of average shelf life masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of average shelf life masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.AverageShelfLifeMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the average shelf life masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of average shelf life masters.
    *
    * @return the number of average shelf life masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static AverageShelfLifeMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (AverageShelfLifeMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    AverageShelfLifeMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(AverageShelfLifeMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(AverageShelfLifeMasterPersistence persistence) {
    }
}
