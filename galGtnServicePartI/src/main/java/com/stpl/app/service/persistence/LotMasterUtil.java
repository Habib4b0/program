package com.stpl.app.service.persistence;

import com.stpl.app.model.LotMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the lot master service. This utility wraps {@link LotMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see LotMasterPersistence
 * @see LotMasterPersistenceImpl
 * @generated
 */
public class LotMasterUtil {
    private static LotMasterPersistence _persistence;

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
    public static void clearCache(LotMaster lotMaster) {
        getPersistence().clearCache(lotMaster);
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
    public static List<LotMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<LotMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<LotMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static LotMaster update(LotMaster lotMaster)
        throws SystemException {
        return getPersistence().update(lotMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static LotMaster update(LotMaster lotMaster,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(lotMaster, serviceContext);
    }

    /**
    * Returns all the lot masters where itemId = &#63;.
    *
    * @param itemId the item ID
    * @return the matching lot masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.LotMaster> findByItemId(
        java.lang.String itemId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByItemId(itemId);
    }

    /**
    * Returns a range of all the lot masters where itemId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemId the item ID
    * @param start the lower bound of the range of lot masters
    * @param end the upper bound of the range of lot masters (not inclusive)
    * @return the range of matching lot masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.LotMaster> findByItemId(
        java.lang.String itemId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByItemId(itemId, start, end);
    }

    /**
    * Returns an ordered range of all the lot masters where itemId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemId the item ID
    * @param start the lower bound of the range of lot masters
    * @param end the upper bound of the range of lot masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching lot masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.LotMaster> findByItemId(
        java.lang.String itemId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemId(itemId, start, end, orderByComparator);
    }

    /**
    * Returns the first lot master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching lot master
    * @throws com.stpl.app.NoSuchLotMasterException if a matching lot master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.LotMaster findByItemId_First(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchLotMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByItemId_First(itemId, orderByComparator);
    }

    /**
    * Returns the first lot master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching lot master, or <code>null</code> if a matching lot master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.LotMaster fetchByItemId_First(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByItemId_First(itemId, orderByComparator);
    }

    /**
    * Returns the last lot master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching lot master
    * @throws com.stpl.app.NoSuchLotMasterException if a matching lot master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.LotMaster findByItemId_Last(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchLotMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByItemId_Last(itemId, orderByComparator);
    }

    /**
    * Returns the last lot master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching lot master, or <code>null</code> if a matching lot master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.LotMaster fetchByItemId_Last(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByItemId_Last(itemId, orderByComparator);
    }

    /**
    * Returns the lot masters before and after the current lot master in the ordered set where itemId = &#63;.
    *
    * @param lotMasterSid the primary key of the current lot master
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next lot master
    * @throws com.stpl.app.NoSuchLotMasterException if a lot master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.LotMaster[] findByItemId_PrevAndNext(
        int lotMasterSid, java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchLotMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemId_PrevAndNext(lotMasterSid, itemId,
            orderByComparator);
    }

    /**
    * Removes all the lot masters where itemId = &#63; from the database.
    *
    * @param itemId the item ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByItemId(java.lang.String itemId)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByItemId(itemId);
    }

    /**
    * Returns the number of lot masters where itemId = &#63;.
    *
    * @param itemId the item ID
    * @return the number of matching lot masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByItemId(java.lang.String itemId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByItemId(itemId);
    }

    /**
    * Returns all the lot masters where lotNo = &#63;.
    *
    * @param lotNo the lot no
    * @return the matching lot masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.LotMaster> findByLotNo(
        java.lang.String lotNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByLotNo(lotNo);
    }

    /**
    * Returns a range of all the lot masters where lotNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param lotNo the lot no
    * @param start the lower bound of the range of lot masters
    * @param end the upper bound of the range of lot masters (not inclusive)
    * @return the range of matching lot masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.LotMaster> findByLotNo(
        java.lang.String lotNo, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByLotNo(lotNo, start, end);
    }

    /**
    * Returns an ordered range of all the lot masters where lotNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param lotNo the lot no
    * @param start the lower bound of the range of lot masters
    * @param end the upper bound of the range of lot masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching lot masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.LotMaster> findByLotNo(
        java.lang.String lotNo, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByLotNo(lotNo, start, end, orderByComparator);
    }

    /**
    * Returns the first lot master in the ordered set where lotNo = &#63;.
    *
    * @param lotNo the lot no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching lot master
    * @throws com.stpl.app.NoSuchLotMasterException if a matching lot master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.LotMaster findByLotNo_First(
        java.lang.String lotNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchLotMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByLotNo_First(lotNo, orderByComparator);
    }

    /**
    * Returns the first lot master in the ordered set where lotNo = &#63;.
    *
    * @param lotNo the lot no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching lot master, or <code>null</code> if a matching lot master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.LotMaster fetchByLotNo_First(
        java.lang.String lotNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByLotNo_First(lotNo, orderByComparator);
    }

    /**
    * Returns the last lot master in the ordered set where lotNo = &#63;.
    *
    * @param lotNo the lot no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching lot master
    * @throws com.stpl.app.NoSuchLotMasterException if a matching lot master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.LotMaster findByLotNo_Last(
        java.lang.String lotNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchLotMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByLotNo_Last(lotNo, orderByComparator);
    }

    /**
    * Returns the last lot master in the ordered set where lotNo = &#63;.
    *
    * @param lotNo the lot no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching lot master, or <code>null</code> if a matching lot master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.LotMaster fetchByLotNo_Last(
        java.lang.String lotNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByLotNo_Last(lotNo, orderByComparator);
    }

    /**
    * Returns the lot masters before and after the current lot master in the ordered set where lotNo = &#63;.
    *
    * @param lotMasterSid the primary key of the current lot master
    * @param lotNo the lot no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next lot master
    * @throws com.stpl.app.NoSuchLotMasterException if a lot master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.LotMaster[] findByLotNo_PrevAndNext(
        int lotMasterSid, java.lang.String lotNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchLotMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByLotNo_PrevAndNext(lotMasterSid, lotNo,
            orderByComparator);
    }

    /**
    * Removes all the lot masters where lotNo = &#63; from the database.
    *
    * @param lotNo the lot no
    * @throws SystemException if a system exception occurred
    */
    public static void removeByLotNo(java.lang.String lotNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByLotNo(lotNo);
    }

    /**
    * Returns the number of lot masters where lotNo = &#63;.
    *
    * @param lotNo the lot no
    * @return the number of matching lot masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByLotNo(java.lang.String lotNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByLotNo(lotNo);
    }

    /**
    * Returns all the lot masters where lotExpirationDate = &#63;.
    *
    * @param lotExpirationDate the lot expiration date
    * @return the matching lot masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.LotMaster> findByLotExpirationDate(
        java.util.Date lotExpirationDate)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByLotExpirationDate(lotExpirationDate);
    }

    /**
    * Returns a range of all the lot masters where lotExpirationDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param lotExpirationDate the lot expiration date
    * @param start the lower bound of the range of lot masters
    * @param end the upper bound of the range of lot masters (not inclusive)
    * @return the range of matching lot masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.LotMaster> findByLotExpirationDate(
        java.util.Date lotExpirationDate, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByLotExpirationDate(lotExpirationDate, start, end);
    }

    /**
    * Returns an ordered range of all the lot masters where lotExpirationDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param lotExpirationDate the lot expiration date
    * @param start the lower bound of the range of lot masters
    * @param end the upper bound of the range of lot masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching lot masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.LotMaster> findByLotExpirationDate(
        java.util.Date lotExpirationDate, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByLotExpirationDate(lotExpirationDate, start, end,
            orderByComparator);
    }

    /**
    * Returns the first lot master in the ordered set where lotExpirationDate = &#63;.
    *
    * @param lotExpirationDate the lot expiration date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching lot master
    * @throws com.stpl.app.NoSuchLotMasterException if a matching lot master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.LotMaster findByLotExpirationDate_First(
        java.util.Date lotExpirationDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchLotMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByLotExpirationDate_First(lotExpirationDate,
            orderByComparator);
    }

    /**
    * Returns the first lot master in the ordered set where lotExpirationDate = &#63;.
    *
    * @param lotExpirationDate the lot expiration date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching lot master, or <code>null</code> if a matching lot master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.LotMaster fetchByLotExpirationDate_First(
        java.util.Date lotExpirationDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByLotExpirationDate_First(lotExpirationDate,
            orderByComparator);
    }

    /**
    * Returns the last lot master in the ordered set where lotExpirationDate = &#63;.
    *
    * @param lotExpirationDate the lot expiration date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching lot master
    * @throws com.stpl.app.NoSuchLotMasterException if a matching lot master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.LotMaster findByLotExpirationDate_Last(
        java.util.Date lotExpirationDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchLotMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByLotExpirationDate_Last(lotExpirationDate,
            orderByComparator);
    }

    /**
    * Returns the last lot master in the ordered set where lotExpirationDate = &#63;.
    *
    * @param lotExpirationDate the lot expiration date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching lot master, or <code>null</code> if a matching lot master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.LotMaster fetchByLotExpirationDate_Last(
        java.util.Date lotExpirationDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByLotExpirationDate_Last(lotExpirationDate,
            orderByComparator);
    }

    /**
    * Returns the lot masters before and after the current lot master in the ordered set where lotExpirationDate = &#63;.
    *
    * @param lotMasterSid the primary key of the current lot master
    * @param lotExpirationDate the lot expiration date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next lot master
    * @throws com.stpl.app.NoSuchLotMasterException if a lot master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.LotMaster[] findByLotExpirationDate_PrevAndNext(
        int lotMasterSid, java.util.Date lotExpirationDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchLotMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByLotExpirationDate_PrevAndNext(lotMasterSid,
            lotExpirationDate, orderByComparator);
    }

    /**
    * Removes all the lot masters where lotExpirationDate = &#63; from the database.
    *
    * @param lotExpirationDate the lot expiration date
    * @throws SystemException if a system exception occurred
    */
    public static void removeByLotExpirationDate(
        java.util.Date lotExpirationDate)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByLotExpirationDate(lotExpirationDate);
    }

    /**
    * Returns the number of lot masters where lotExpirationDate = &#63;.
    *
    * @param lotExpirationDate the lot expiration date
    * @return the number of matching lot masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByLotExpirationDate(java.util.Date lotExpirationDate)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByLotExpirationDate(lotExpirationDate);
    }

    /**
    * Returns all the lot masters where lastLotSoldDate = &#63;.
    *
    * @param lastLotSoldDate the last lot sold date
    * @return the matching lot masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.LotMaster> findByLastLotSoldDate(
        java.util.Date lastLotSoldDate)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByLastLotSoldDate(lastLotSoldDate);
    }

    /**
    * Returns a range of all the lot masters where lastLotSoldDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param lastLotSoldDate the last lot sold date
    * @param start the lower bound of the range of lot masters
    * @param end the upper bound of the range of lot masters (not inclusive)
    * @return the range of matching lot masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.LotMaster> findByLastLotSoldDate(
        java.util.Date lastLotSoldDate, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByLastLotSoldDate(lastLotSoldDate, start, end);
    }

    /**
    * Returns an ordered range of all the lot masters where lastLotSoldDate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param lastLotSoldDate the last lot sold date
    * @param start the lower bound of the range of lot masters
    * @param end the upper bound of the range of lot masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching lot masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.LotMaster> findByLastLotSoldDate(
        java.util.Date lastLotSoldDate, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByLastLotSoldDate(lastLotSoldDate, start, end,
            orderByComparator);
    }

    /**
    * Returns the first lot master in the ordered set where lastLotSoldDate = &#63;.
    *
    * @param lastLotSoldDate the last lot sold date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching lot master
    * @throws com.stpl.app.NoSuchLotMasterException if a matching lot master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.LotMaster findByLastLotSoldDate_First(
        java.util.Date lastLotSoldDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchLotMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByLastLotSoldDate_First(lastLotSoldDate,
            orderByComparator);
    }

    /**
    * Returns the first lot master in the ordered set where lastLotSoldDate = &#63;.
    *
    * @param lastLotSoldDate the last lot sold date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching lot master, or <code>null</code> if a matching lot master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.LotMaster fetchByLastLotSoldDate_First(
        java.util.Date lastLotSoldDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByLastLotSoldDate_First(lastLotSoldDate,
            orderByComparator);
    }

    /**
    * Returns the last lot master in the ordered set where lastLotSoldDate = &#63;.
    *
    * @param lastLotSoldDate the last lot sold date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching lot master
    * @throws com.stpl.app.NoSuchLotMasterException if a matching lot master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.LotMaster findByLastLotSoldDate_Last(
        java.util.Date lastLotSoldDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchLotMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByLastLotSoldDate_Last(lastLotSoldDate,
            orderByComparator);
    }

    /**
    * Returns the last lot master in the ordered set where lastLotSoldDate = &#63;.
    *
    * @param lastLotSoldDate the last lot sold date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching lot master, or <code>null</code> if a matching lot master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.LotMaster fetchByLastLotSoldDate_Last(
        java.util.Date lastLotSoldDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByLastLotSoldDate_Last(lastLotSoldDate,
            orderByComparator);
    }

    /**
    * Returns the lot masters before and after the current lot master in the ordered set where lastLotSoldDate = &#63;.
    *
    * @param lotMasterSid the primary key of the current lot master
    * @param lastLotSoldDate the last lot sold date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next lot master
    * @throws com.stpl.app.NoSuchLotMasterException if a lot master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.LotMaster[] findByLastLotSoldDate_PrevAndNext(
        int lotMasterSid, java.util.Date lastLotSoldDate,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchLotMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByLastLotSoldDate_PrevAndNext(lotMasterSid,
            lastLotSoldDate, orderByComparator);
    }

    /**
    * Removes all the lot masters where lastLotSoldDate = &#63; from the database.
    *
    * @param lastLotSoldDate the last lot sold date
    * @throws SystemException if a system exception occurred
    */
    public static void removeByLastLotSoldDate(java.util.Date lastLotSoldDate)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByLastLotSoldDate(lastLotSoldDate);
    }

    /**
    * Returns the number of lot masters where lastLotSoldDate = &#63;.
    *
    * @param lastLotSoldDate the last lot sold date
    * @return the number of matching lot masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByLastLotSoldDate(java.util.Date lastLotSoldDate)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByLastLotSoldDate(lastLotSoldDate);
    }

    /**
    * Returns all the lot masters where itemId = &#63; and lotNo = &#63;.
    *
    * @param itemId the item ID
    * @param lotNo the lot no
    * @return the matching lot masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.LotMaster> findByLotUnique(
        java.lang.String itemId, java.lang.String lotNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByLotUnique(itemId, lotNo);
    }

    /**
    * Returns a range of all the lot masters where itemId = &#63; and lotNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemId the item ID
    * @param lotNo the lot no
    * @param start the lower bound of the range of lot masters
    * @param end the upper bound of the range of lot masters (not inclusive)
    * @return the range of matching lot masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.LotMaster> findByLotUnique(
        java.lang.String itemId, java.lang.String lotNo, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByLotUnique(itemId, lotNo, start, end);
    }

    /**
    * Returns an ordered range of all the lot masters where itemId = &#63; and lotNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemId the item ID
    * @param lotNo the lot no
    * @param start the lower bound of the range of lot masters
    * @param end the upper bound of the range of lot masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching lot masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.LotMaster> findByLotUnique(
        java.lang.String itemId, java.lang.String lotNo, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByLotUnique(itemId, lotNo, start, end, orderByComparator);
    }

    /**
    * Returns the first lot master in the ordered set where itemId = &#63; and lotNo = &#63;.
    *
    * @param itemId the item ID
    * @param lotNo the lot no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching lot master
    * @throws com.stpl.app.NoSuchLotMasterException if a matching lot master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.LotMaster findByLotUnique_First(
        java.lang.String itemId, java.lang.String lotNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchLotMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByLotUnique_First(itemId, lotNo, orderByComparator);
    }

    /**
    * Returns the first lot master in the ordered set where itemId = &#63; and lotNo = &#63;.
    *
    * @param itemId the item ID
    * @param lotNo the lot no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching lot master, or <code>null</code> if a matching lot master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.LotMaster fetchByLotUnique_First(
        java.lang.String itemId, java.lang.String lotNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByLotUnique_First(itemId, lotNo, orderByComparator);
    }

    /**
    * Returns the last lot master in the ordered set where itemId = &#63; and lotNo = &#63;.
    *
    * @param itemId the item ID
    * @param lotNo the lot no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching lot master
    * @throws com.stpl.app.NoSuchLotMasterException if a matching lot master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.LotMaster findByLotUnique_Last(
        java.lang.String itemId, java.lang.String lotNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchLotMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByLotUnique_Last(itemId, lotNo, orderByComparator);
    }

    /**
    * Returns the last lot master in the ordered set where itemId = &#63; and lotNo = &#63;.
    *
    * @param itemId the item ID
    * @param lotNo the lot no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching lot master, or <code>null</code> if a matching lot master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.LotMaster fetchByLotUnique_Last(
        java.lang.String itemId, java.lang.String lotNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByLotUnique_Last(itemId, lotNo, orderByComparator);
    }

    /**
    * Returns the lot masters before and after the current lot master in the ordered set where itemId = &#63; and lotNo = &#63;.
    *
    * @param lotMasterSid the primary key of the current lot master
    * @param itemId the item ID
    * @param lotNo the lot no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next lot master
    * @throws com.stpl.app.NoSuchLotMasterException if a lot master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.LotMaster[] findByLotUnique_PrevAndNext(
        int lotMasterSid, java.lang.String itemId, java.lang.String lotNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchLotMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByLotUnique_PrevAndNext(lotMasterSid, itemId, lotNo,
            orderByComparator);
    }

    /**
    * Removes all the lot masters where itemId = &#63; and lotNo = &#63; from the database.
    *
    * @param itemId the item ID
    * @param lotNo the lot no
    * @throws SystemException if a system exception occurred
    */
    public static void removeByLotUnique(java.lang.String itemId,
        java.lang.String lotNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByLotUnique(itemId, lotNo);
    }

    /**
    * Returns the number of lot masters where itemId = &#63; and lotNo = &#63;.
    *
    * @param itemId the item ID
    * @param lotNo the lot no
    * @return the number of matching lot masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByLotUnique(java.lang.String itemId,
        java.lang.String lotNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByLotUnique(itemId, lotNo);
    }

    /**
    * Caches the lot master in the entity cache if it is enabled.
    *
    * @param lotMaster the lot master
    */
    public static void cacheResult(com.stpl.app.model.LotMaster lotMaster) {
        getPersistence().cacheResult(lotMaster);
    }

    /**
    * Caches the lot masters in the entity cache if it is enabled.
    *
    * @param lotMasters the lot masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.LotMaster> lotMasters) {
        getPersistence().cacheResult(lotMasters);
    }

    /**
    * Creates a new lot master with the primary key. Does not add the lot master to the database.
    *
    * @param lotMasterSid the primary key for the new lot master
    * @return the new lot master
    */
    public static com.stpl.app.model.LotMaster create(int lotMasterSid) {
        return getPersistence().create(lotMasterSid);
    }

    /**
    * Removes the lot master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param lotMasterSid the primary key of the lot master
    * @return the lot master that was removed
    * @throws com.stpl.app.NoSuchLotMasterException if a lot master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.LotMaster remove(int lotMasterSid)
        throws com.stpl.app.NoSuchLotMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(lotMasterSid);
    }

    public static com.stpl.app.model.LotMaster updateImpl(
        com.stpl.app.model.LotMaster lotMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(lotMaster);
    }

    /**
    * Returns the lot master with the primary key or throws a {@link com.stpl.app.NoSuchLotMasterException} if it could not be found.
    *
    * @param lotMasterSid the primary key of the lot master
    * @return the lot master
    * @throws com.stpl.app.NoSuchLotMasterException if a lot master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.LotMaster findByPrimaryKey(
        int lotMasterSid)
        throws com.stpl.app.NoSuchLotMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(lotMasterSid);
    }

    /**
    * Returns the lot master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param lotMasterSid the primary key of the lot master
    * @return the lot master, or <code>null</code> if a lot master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.LotMaster fetchByPrimaryKey(
        int lotMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(lotMasterSid);
    }

    /**
    * Returns all the lot masters.
    *
    * @return the lot masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.LotMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the lot masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of lot masters
    * @param end the upper bound of the range of lot masters (not inclusive)
    * @return the range of lot masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.LotMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the lot masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of lot masters
    * @param end the upper bound of the range of lot masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of lot masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.LotMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the lot masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of lot masters.
    *
    * @return the number of lot masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static LotMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (LotMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    LotMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(LotMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(LotMasterPersistence persistence) {
    }
}
