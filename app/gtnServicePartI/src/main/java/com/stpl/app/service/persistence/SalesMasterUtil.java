package com.stpl.app.service.persistence;

import com.stpl.app.model.SalesMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the sales master service. This utility wraps {@link SalesMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see SalesMasterPersistence
 * @see SalesMasterPersistenceImpl
 * @generated
 */
public class SalesMasterUtil {
    private static SalesMasterPersistence _persistence;

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
    public static void clearCache(SalesMaster salesMaster) {
        getPersistence().clearCache(salesMaster);
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
    public static List<SalesMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<SalesMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<SalesMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static SalesMaster update(SalesMaster salesMaster)
        throws SystemException {
        return getPersistence().update(salesMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static SalesMaster update(SalesMaster salesMaster,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(salesMaster, serviceContext);
    }

    /**
    * Returns all the sales masters where accountId = &#63;.
    *
    * @param accountId the account ID
    * @return the matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.SalesMaster> findByAccountId(
        java.lang.String accountId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByAccountId(accountId);
    }

    /**
    * Returns a range of all the sales masters where accountId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param accountId the account ID
    * @param start the lower bound of the range of sales masters
    * @param end the upper bound of the range of sales masters (not inclusive)
    * @return the range of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.SalesMaster> findByAccountId(
        java.lang.String accountId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByAccountId(accountId, start, end);
    }

    /**
    * Returns an ordered range of all the sales masters where accountId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param accountId the account ID
    * @param start the lower bound of the range of sales masters
    * @param end the upper bound of the range of sales masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.SalesMaster> findByAccountId(
        java.lang.String accountId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAccountId(accountId, start, end, orderByComparator);
    }

    /**
    * Returns the first sales master in the ordered set where accountId = &#63;.
    *
    * @param accountId the account ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster findByAccountId_First(
        java.lang.String accountId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAccountId_First(accountId, orderByComparator);
    }

    /**
    * Returns the first sales master in the ordered set where accountId = &#63;.
    *
    * @param accountId the account ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster fetchByAccountId_First(
        java.lang.String accountId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByAccountId_First(accountId, orderByComparator);
    }

    /**
    * Returns the last sales master in the ordered set where accountId = &#63;.
    *
    * @param accountId the account ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster findByAccountId_Last(
        java.lang.String accountId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAccountId_Last(accountId, orderByComparator);
    }

    /**
    * Returns the last sales master in the ordered set where accountId = &#63;.
    *
    * @param accountId the account ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster fetchByAccountId_Last(
        java.lang.String accountId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByAccountId_Last(accountId, orderByComparator);
    }

    /**
    * Returns the sales masters before and after the current sales master in the ordered set where accountId = &#63;.
    *
    * @param salesMasterSid the primary key of the current sales master
    * @param accountId the account ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a sales master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster[] findByAccountId_PrevAndNext(
        int salesMasterSid, java.lang.String accountId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAccountId_PrevAndNext(salesMasterSid, accountId,
            orderByComparator);
    }

    /**
    * Removes all the sales masters where accountId = &#63; from the database.
    *
    * @param accountId the account ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByAccountId(java.lang.String accountId)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByAccountId(accountId);
    }

    /**
    * Returns the number of sales masters where accountId = &#63;.
    *
    * @param accountId the account ID
    * @return the number of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByAccountId(java.lang.String accountId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByAccountId(accountId);
    }

    /**
    * Returns all the sales masters where itemNo = &#63;.
    *
    * @param itemNo the item no
    * @return the matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.SalesMaster> findByItemNo(
        java.lang.String itemNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByItemNo(itemNo);
    }

    /**
    * Returns a range of all the sales masters where itemNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemNo the item no
    * @param start the lower bound of the range of sales masters
    * @param end the upper bound of the range of sales masters (not inclusive)
    * @return the range of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.SalesMaster> findByItemNo(
        java.lang.String itemNo, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByItemNo(itemNo, start, end);
    }

    /**
    * Returns an ordered range of all the sales masters where itemNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemNo the item no
    * @param start the lower bound of the range of sales masters
    * @param end the upper bound of the range of sales masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.SalesMaster> findByItemNo(
        java.lang.String itemNo, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemNo(itemNo, start, end, orderByComparator);
    }

    /**
    * Returns the first sales master in the ordered set where itemNo = &#63;.
    *
    * @param itemNo the item no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster findByItemNo_First(
        java.lang.String itemNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByItemNo_First(itemNo, orderByComparator);
    }

    /**
    * Returns the first sales master in the ordered set where itemNo = &#63;.
    *
    * @param itemNo the item no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster fetchByItemNo_First(
        java.lang.String itemNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByItemNo_First(itemNo, orderByComparator);
    }

    /**
    * Returns the last sales master in the ordered set where itemNo = &#63;.
    *
    * @param itemNo the item no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster findByItemNo_Last(
        java.lang.String itemNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByItemNo_Last(itemNo, orderByComparator);
    }

    /**
    * Returns the last sales master in the ordered set where itemNo = &#63;.
    *
    * @param itemNo the item no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster fetchByItemNo_Last(
        java.lang.String itemNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByItemNo_Last(itemNo, orderByComparator);
    }

    /**
    * Returns the sales masters before and after the current sales master in the ordered set where itemNo = &#63;.
    *
    * @param salesMasterSid the primary key of the current sales master
    * @param itemNo the item no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a sales master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster[] findByItemNo_PrevAndNext(
        int salesMasterSid, java.lang.String itemNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemNo_PrevAndNext(salesMasterSid, itemNo,
            orderByComparator);
    }

    /**
    * Removes all the sales masters where itemNo = &#63; from the database.
    *
    * @param itemNo the item no
    * @throws SystemException if a system exception occurred
    */
    public static void removeByItemNo(java.lang.String itemNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByItemNo(itemNo);
    }

    /**
    * Returns the number of sales masters where itemNo = &#63;.
    *
    * @param itemNo the item no
    * @return the number of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByItemNo(java.lang.String itemNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByItemNo(itemNo);
    }

    /**
    * Returns all the sales masters where itemId = &#63;.
    *
    * @param itemId the item ID
    * @return the matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.SalesMaster> findByItemId(
        java.lang.String itemId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByItemId(itemId);
    }

    /**
    * Returns a range of all the sales masters where itemId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemId the item ID
    * @param start the lower bound of the range of sales masters
    * @param end the upper bound of the range of sales masters (not inclusive)
    * @return the range of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.SalesMaster> findByItemId(
        java.lang.String itemId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByItemId(itemId, start, end);
    }

    /**
    * Returns an ordered range of all the sales masters where itemId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemId the item ID
    * @param start the lower bound of the range of sales masters
    * @param end the upper bound of the range of sales masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.SalesMaster> findByItemId(
        java.lang.String itemId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemId(itemId, start, end, orderByComparator);
    }

    /**
    * Returns the first sales master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster findByItemId_First(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByItemId_First(itemId, orderByComparator);
    }

    /**
    * Returns the first sales master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster fetchByItemId_First(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByItemId_First(itemId, orderByComparator);
    }

    /**
    * Returns the last sales master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster findByItemId_Last(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByItemId_Last(itemId, orderByComparator);
    }

    /**
    * Returns the last sales master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster fetchByItemId_Last(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByItemId_Last(itemId, orderByComparator);
    }

    /**
    * Returns the sales masters before and after the current sales master in the ordered set where itemId = &#63;.
    *
    * @param salesMasterSid the primary key of the current sales master
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a sales master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster[] findByItemId_PrevAndNext(
        int salesMasterSid, java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemId_PrevAndNext(salesMasterSid, itemId,
            orderByComparator);
    }

    /**
    * Removes all the sales masters where itemId = &#63; from the database.
    *
    * @param itemId the item ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByItemId(java.lang.String itemId)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByItemId(itemId);
    }

    /**
    * Returns the number of sales masters where itemId = &#63;.
    *
    * @param itemId the item ID
    * @return the number of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByItemId(java.lang.String itemId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByItemId(itemId);
    }

    /**
    * Returns all the sales masters where salesId = &#63;.
    *
    * @param salesId the sales ID
    * @return the matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.SalesMaster> findBySalesId(
        java.lang.String salesId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findBySalesId(salesId);
    }

    /**
    * Returns a range of all the sales masters where salesId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param salesId the sales ID
    * @param start the lower bound of the range of sales masters
    * @param end the upper bound of the range of sales masters (not inclusive)
    * @return the range of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.SalesMaster> findBySalesId(
        java.lang.String salesId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findBySalesId(salesId, start, end);
    }

    /**
    * Returns an ordered range of all the sales masters where salesId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param salesId the sales ID
    * @param start the lower bound of the range of sales masters
    * @param end the upper bound of the range of sales masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.SalesMaster> findBySalesId(
        java.lang.String salesId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySalesId(salesId, start, end, orderByComparator);
    }

    /**
    * Returns the first sales master in the ordered set where salesId = &#63;.
    *
    * @param salesId the sales ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster findBySalesId_First(
        java.lang.String salesId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findBySalesId_First(salesId, orderByComparator);
    }

    /**
    * Returns the first sales master in the ordered set where salesId = &#63;.
    *
    * @param salesId the sales ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster fetchBySalesId_First(
        java.lang.String salesId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchBySalesId_First(salesId, orderByComparator);
    }

    /**
    * Returns the last sales master in the ordered set where salesId = &#63;.
    *
    * @param salesId the sales ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster findBySalesId_Last(
        java.lang.String salesId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findBySalesId_Last(salesId, orderByComparator);
    }

    /**
    * Returns the last sales master in the ordered set where salesId = &#63;.
    *
    * @param salesId the sales ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster fetchBySalesId_Last(
        java.lang.String salesId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchBySalesId_Last(salesId, orderByComparator);
    }

    /**
    * Returns the sales masters before and after the current sales master in the ordered set where salesId = &#63;.
    *
    * @param salesMasterSid the primary key of the current sales master
    * @param salesId the sales ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a sales master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster[] findBySalesId_PrevAndNext(
        int salesMasterSid, java.lang.String salesId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySalesId_PrevAndNext(salesMasterSid, salesId,
            orderByComparator);
    }

    /**
    * Removes all the sales masters where salesId = &#63; from the database.
    *
    * @param salesId the sales ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeBySalesId(java.lang.String salesId)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeBySalesId(salesId);
    }

    /**
    * Returns the number of sales masters where salesId = &#63;.
    *
    * @param salesId the sales ID
    * @return the number of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public static int countBySalesId(java.lang.String salesId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countBySalesId(salesId);
    }

    /**
    * Returns all the sales masters where accountNo = &#63;.
    *
    * @param accountNo the account no
    * @return the matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.SalesMaster> findByAccountNo(
        java.lang.String accountNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByAccountNo(accountNo);
    }

    /**
    * Returns a range of all the sales masters where accountNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param accountNo the account no
    * @param start the lower bound of the range of sales masters
    * @param end the upper bound of the range of sales masters (not inclusive)
    * @return the range of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.SalesMaster> findByAccountNo(
        java.lang.String accountNo, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByAccountNo(accountNo, start, end);
    }

    /**
    * Returns an ordered range of all the sales masters where accountNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param accountNo the account no
    * @param start the lower bound of the range of sales masters
    * @param end the upper bound of the range of sales masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.SalesMaster> findByAccountNo(
        java.lang.String accountNo, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAccountNo(accountNo, start, end, orderByComparator);
    }

    /**
    * Returns the first sales master in the ordered set where accountNo = &#63;.
    *
    * @param accountNo the account no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster findByAccountNo_First(
        java.lang.String accountNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAccountNo_First(accountNo, orderByComparator);
    }

    /**
    * Returns the first sales master in the ordered set where accountNo = &#63;.
    *
    * @param accountNo the account no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster fetchByAccountNo_First(
        java.lang.String accountNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByAccountNo_First(accountNo, orderByComparator);
    }

    /**
    * Returns the last sales master in the ordered set where accountNo = &#63;.
    *
    * @param accountNo the account no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster findByAccountNo_Last(
        java.lang.String accountNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAccountNo_Last(accountNo, orderByComparator);
    }

    /**
    * Returns the last sales master in the ordered set where accountNo = &#63;.
    *
    * @param accountNo the account no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster fetchByAccountNo_Last(
        java.lang.String accountNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByAccountNo_Last(accountNo, orderByComparator);
    }

    /**
    * Returns the sales masters before and after the current sales master in the ordered set where accountNo = &#63;.
    *
    * @param salesMasterSid the primary key of the current sales master
    * @param accountNo the account no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a sales master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster[] findByAccountNo_PrevAndNext(
        int salesMasterSid, java.lang.String accountNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAccountNo_PrevAndNext(salesMasterSid, accountNo,
            orderByComparator);
    }

    /**
    * Removes all the sales masters where accountNo = &#63; from the database.
    *
    * @param accountNo the account no
    * @throws SystemException if a system exception occurred
    */
    public static void removeByAccountNo(java.lang.String accountNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByAccountNo(accountNo);
    }

    /**
    * Returns the number of sales masters where accountNo = &#63;.
    *
    * @param accountNo the account no
    * @return the number of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByAccountNo(java.lang.String accountNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByAccountNo(accountNo);
    }

    /**
    * Returns all the sales masters where contractId = &#63;.
    *
    * @param contractId the contract ID
    * @return the matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.SalesMaster> findByContractId(
        java.lang.String contractId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByContractId(contractId);
    }

    /**
    * Returns a range of all the sales masters where contractId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contractId the contract ID
    * @param start the lower bound of the range of sales masters
    * @param end the upper bound of the range of sales masters (not inclusive)
    * @return the range of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.SalesMaster> findByContractId(
        java.lang.String contractId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByContractId(contractId, start, end);
    }

    /**
    * Returns an ordered range of all the sales masters where contractId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contractId the contract ID
    * @param start the lower bound of the range of sales masters
    * @param end the upper bound of the range of sales masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.SalesMaster> findByContractId(
        java.lang.String contractId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContractId(contractId, start, end, orderByComparator);
    }

    /**
    * Returns the first sales master in the ordered set where contractId = &#63;.
    *
    * @param contractId the contract ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster findByContractId_First(
        java.lang.String contractId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContractId_First(contractId, orderByComparator);
    }

    /**
    * Returns the first sales master in the ordered set where contractId = &#63;.
    *
    * @param contractId the contract ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster fetchByContractId_First(
        java.lang.String contractId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByContractId_First(contractId, orderByComparator);
    }

    /**
    * Returns the last sales master in the ordered set where contractId = &#63;.
    *
    * @param contractId the contract ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster findByContractId_Last(
        java.lang.String contractId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContractId_Last(contractId, orderByComparator);
    }

    /**
    * Returns the last sales master in the ordered set where contractId = &#63;.
    *
    * @param contractId the contract ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster fetchByContractId_Last(
        java.lang.String contractId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByContractId_Last(contractId, orderByComparator);
    }

    /**
    * Returns the sales masters before and after the current sales master in the ordered set where contractId = &#63;.
    *
    * @param salesMasterSid the primary key of the current sales master
    * @param contractId the contract ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a sales master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster[] findByContractId_PrevAndNext(
        int salesMasterSid, java.lang.String contractId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContractId_PrevAndNext(salesMasterSid, contractId,
            orderByComparator);
    }

    /**
    * Removes all the sales masters where contractId = &#63; from the database.
    *
    * @param contractId the contract ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByContractId(java.lang.String contractId)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByContractId(contractId);
    }

    /**
    * Returns the number of sales masters where contractId = &#63;.
    *
    * @param contractId the contract ID
    * @return the number of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByContractId(java.lang.String contractId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByContractId(contractId);
    }

    /**
    * Returns all the sales masters where companyId = &#63;.
    *
    * @param companyId the company ID
    * @return the matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.SalesMaster> findByCompanyId(
        java.lang.String companyId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByCompanyId(companyId);
    }

    /**
    * Returns a range of all the sales masters where companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyId the company ID
    * @param start the lower bound of the range of sales masters
    * @param end the upper bound of the range of sales masters (not inclusive)
    * @return the range of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.SalesMaster> findByCompanyId(
        java.lang.String companyId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByCompanyId(companyId, start, end);
    }

    /**
    * Returns an ordered range of all the sales masters where companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param companyId the company ID
    * @param start the lower bound of the range of sales masters
    * @param end the upper bound of the range of sales masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.SalesMaster> findByCompanyId(
        java.lang.String companyId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyId(companyId, start, end, orderByComparator);
    }

    /**
    * Returns the first sales master in the ordered set where companyId = &#63;.
    *
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster findByCompanyId_First(
        java.lang.String companyId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyId_First(companyId, orderByComparator);
    }

    /**
    * Returns the first sales master in the ordered set where companyId = &#63;.
    *
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster fetchByCompanyId_First(
        java.lang.String companyId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByCompanyId_First(companyId, orderByComparator);
    }

    /**
    * Returns the last sales master in the ordered set where companyId = &#63;.
    *
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster findByCompanyId_Last(
        java.lang.String companyId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyId_Last(companyId, orderByComparator);
    }

    /**
    * Returns the last sales master in the ordered set where companyId = &#63;.
    *
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster fetchByCompanyId_Last(
        java.lang.String companyId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByCompanyId_Last(companyId, orderByComparator);
    }

    /**
    * Returns the sales masters before and after the current sales master in the ordered set where companyId = &#63;.
    *
    * @param salesMasterSid the primary key of the current sales master
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a sales master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster[] findByCompanyId_PrevAndNext(
        int salesMasterSid, java.lang.String companyId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCompanyId_PrevAndNext(salesMasterSid, companyId,
            orderByComparator);
    }

    /**
    * Removes all the sales masters where companyId = &#63; from the database.
    *
    * @param companyId the company ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCompanyId(java.lang.String companyId)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByCompanyId(companyId);
    }

    /**
    * Returns the number of sales masters where companyId = &#63;.
    *
    * @param companyId the company ID
    * @return the number of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByCompanyId(java.lang.String companyId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByCompanyId(companyId);
    }

    /**
    * Returns all the sales masters where contractNo = &#63;.
    *
    * @param contractNo the contract no
    * @return the matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.SalesMaster> findByContractNo(
        java.lang.String contractNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByContractNo(contractNo);
    }

    /**
    * Returns a range of all the sales masters where contractNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contractNo the contract no
    * @param start the lower bound of the range of sales masters
    * @param end the upper bound of the range of sales masters (not inclusive)
    * @return the range of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.SalesMaster> findByContractNo(
        java.lang.String contractNo, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByContractNo(contractNo, start, end);
    }

    /**
    * Returns an ordered range of all the sales masters where contractNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contractNo the contract no
    * @param start the lower bound of the range of sales masters
    * @param end the upper bound of the range of sales masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.SalesMaster> findByContractNo(
        java.lang.String contractNo, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContractNo(contractNo, start, end, orderByComparator);
    }

    /**
    * Returns the first sales master in the ordered set where contractNo = &#63;.
    *
    * @param contractNo the contract no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster findByContractNo_First(
        java.lang.String contractNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContractNo_First(contractNo, orderByComparator);
    }

    /**
    * Returns the first sales master in the ordered set where contractNo = &#63;.
    *
    * @param contractNo the contract no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster fetchByContractNo_First(
        java.lang.String contractNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByContractNo_First(contractNo, orderByComparator);
    }

    /**
    * Returns the last sales master in the ordered set where contractNo = &#63;.
    *
    * @param contractNo the contract no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster findByContractNo_Last(
        java.lang.String contractNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContractNo_Last(contractNo, orderByComparator);
    }

    /**
    * Returns the last sales master in the ordered set where contractNo = &#63;.
    *
    * @param contractNo the contract no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster fetchByContractNo_Last(
        java.lang.String contractNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByContractNo_Last(contractNo, orderByComparator);
    }

    /**
    * Returns the sales masters before and after the current sales master in the ordered set where contractNo = &#63;.
    *
    * @param salesMasterSid the primary key of the current sales master
    * @param contractNo the contract no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a sales master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster[] findByContractNo_PrevAndNext(
        int salesMasterSid, java.lang.String contractNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContractNo_PrevAndNext(salesMasterSid, contractNo,
            orderByComparator);
    }

    /**
    * Removes all the sales masters where contractNo = &#63; from the database.
    *
    * @param contractNo the contract no
    * @throws SystemException if a system exception occurred
    */
    public static void removeByContractNo(java.lang.String contractNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByContractNo(contractNo);
    }

    /**
    * Returns the number of sales masters where contractNo = &#63;.
    *
    * @param contractNo the contract no
    * @return the number of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByContractNo(java.lang.String contractNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByContractNo(contractNo);
    }

    /**
    * Returns all the sales masters where salesId = &#63;.
    *
    * @param salesId the sales ID
    * @return the matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.SalesMaster> findBySalesUnique(
        java.lang.String salesId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findBySalesUnique(salesId);
    }

    /**
    * Returns a range of all the sales masters where salesId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param salesId the sales ID
    * @param start the lower bound of the range of sales masters
    * @param end the upper bound of the range of sales masters (not inclusive)
    * @return the range of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.SalesMaster> findBySalesUnique(
        java.lang.String salesId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findBySalesUnique(salesId, start, end);
    }

    /**
    * Returns an ordered range of all the sales masters where salesId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param salesId the sales ID
    * @param start the lower bound of the range of sales masters
    * @param end the upper bound of the range of sales masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.SalesMaster> findBySalesUnique(
        java.lang.String salesId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySalesUnique(salesId, start, end, orderByComparator);
    }

    /**
    * Returns the first sales master in the ordered set where salesId = &#63;.
    *
    * @param salesId the sales ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster findBySalesUnique_First(
        java.lang.String salesId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySalesUnique_First(salesId, orderByComparator);
    }

    /**
    * Returns the first sales master in the ordered set where salesId = &#63;.
    *
    * @param salesId the sales ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster fetchBySalesUnique_First(
        java.lang.String salesId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchBySalesUnique_First(salesId, orderByComparator);
    }

    /**
    * Returns the last sales master in the ordered set where salesId = &#63;.
    *
    * @param salesId the sales ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster findBySalesUnique_Last(
        java.lang.String salesId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySalesUnique_Last(salesId, orderByComparator);
    }

    /**
    * Returns the last sales master in the ordered set where salesId = &#63;.
    *
    * @param salesId the sales ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster fetchBySalesUnique_Last(
        java.lang.String salesId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchBySalesUnique_Last(salesId, orderByComparator);
    }

    /**
    * Returns the sales masters before and after the current sales master in the ordered set where salesId = &#63;.
    *
    * @param salesMasterSid the primary key of the current sales master
    * @param salesId the sales ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a sales master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster[] findBySalesUnique_PrevAndNext(
        int salesMasterSid, java.lang.String salesId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySalesUnique_PrevAndNext(salesMasterSid, salesId,
            orderByComparator);
    }

    /**
    * Removes all the sales masters where salesId = &#63; from the database.
    *
    * @param salesId the sales ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeBySalesUnique(java.lang.String salesId)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeBySalesUnique(salesId);
    }

    /**
    * Returns the number of sales masters where salesId = &#63;.
    *
    * @param salesId the sales ID
    * @return the number of matching sales masters
    * @throws SystemException if a system exception occurred
    */
    public static int countBySalesUnique(java.lang.String salesId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countBySalesUnique(salesId);
    }

    /**
    * Caches the sales master in the entity cache if it is enabled.
    *
    * @param salesMaster the sales master
    */
    public static void cacheResult(com.stpl.app.model.SalesMaster salesMaster) {
        getPersistence().cacheResult(salesMaster);
    }

    /**
    * Caches the sales masters in the entity cache if it is enabled.
    *
    * @param salesMasters the sales masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.SalesMaster> salesMasters) {
        getPersistence().cacheResult(salesMasters);
    }

    /**
    * Creates a new sales master with the primary key. Does not add the sales master to the database.
    *
    * @param salesMasterSid the primary key for the new sales master
    * @return the new sales master
    */
    public static com.stpl.app.model.SalesMaster create(int salesMasterSid) {
        return getPersistence().create(salesMasterSid);
    }

    /**
    * Removes the sales master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param salesMasterSid the primary key of the sales master
    * @return the sales master that was removed
    * @throws com.stpl.app.NoSuchSalesMasterException if a sales master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster remove(int salesMasterSid)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(salesMasterSid);
    }

    public static com.stpl.app.model.SalesMaster updateImpl(
        com.stpl.app.model.SalesMaster salesMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(salesMaster);
    }

    /**
    * Returns the sales master with the primary key or throws a {@link com.stpl.app.NoSuchSalesMasterException} if it could not be found.
    *
    * @param salesMasterSid the primary key of the sales master
    * @return the sales master
    * @throws com.stpl.app.NoSuchSalesMasterException if a sales master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster findByPrimaryKey(
        int salesMasterSid)
        throws com.stpl.app.NoSuchSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(salesMasterSid);
    }

    /**
    * Returns the sales master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param salesMasterSid the primary key of the sales master
    * @return the sales master, or <code>null</code> if a sales master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.SalesMaster fetchByPrimaryKey(
        int salesMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(salesMasterSid);
    }

    /**
    * Returns all the sales masters.
    *
    * @return the sales masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.SalesMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the sales masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of sales masters
    * @param end the upper bound of the range of sales masters (not inclusive)
    * @return the range of sales masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.SalesMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the sales masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of sales masters
    * @param end the upper bound of the range of sales masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of sales masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.SalesMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the sales masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of sales masters.
    *
    * @return the number of sales masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static SalesMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (SalesMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    SalesMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(SalesMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(SalesMasterPersistence persistence) {
    }
}
