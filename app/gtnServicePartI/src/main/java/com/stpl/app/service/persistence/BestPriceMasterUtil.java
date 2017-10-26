package com.stpl.app.service.persistence;

import com.stpl.app.model.BestPriceMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the best price master service. This utility wraps {@link BestPriceMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see BestPriceMasterPersistence
 * @see BestPriceMasterPersistenceImpl
 * @generated
 */
public class BestPriceMasterUtil {
    private static BestPriceMasterPersistence _persistence;

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
    public static void clearCache(BestPriceMaster bestPriceMaster) {
        getPersistence().clearCache(bestPriceMaster);
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
    public static List<BestPriceMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<BestPriceMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<BestPriceMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static BestPriceMaster update(BestPriceMaster bestPriceMaster)
        throws SystemException {
        return getPersistence().update(bestPriceMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static BestPriceMaster update(BestPriceMaster bestPriceMaster,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(bestPriceMaster, serviceContext);
    }

    /**
    * Returns all the best price masters where itemId = &#63;.
    *
    * @param itemId the item ID
    * @return the matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.BestPriceMaster> findByItemId(
        java.lang.String itemId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByItemId(itemId);
    }

    /**
    * Returns a range of all the best price masters where itemId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemId the item ID
    * @param start the lower bound of the range of best price masters
    * @param end the upper bound of the range of best price masters (not inclusive)
    * @return the range of matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.BestPriceMaster> findByItemId(
        java.lang.String itemId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByItemId(itemId, start, end);
    }

    /**
    * Returns an ordered range of all the best price masters where itemId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemId the item ID
    * @param start the lower bound of the range of best price masters
    * @param end the upper bound of the range of best price masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.BestPriceMaster> findByItemId(
        java.lang.String itemId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemId(itemId, start, end, orderByComparator);
    }

    /**
    * Returns the first best price master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BestPriceMaster findByItemId_First(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByItemId_First(itemId, orderByComparator);
    }

    /**
    * Returns the first best price master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching best price master, or <code>null</code> if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BestPriceMaster fetchByItemId_First(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByItemId_First(itemId, orderByComparator);
    }

    /**
    * Returns the last best price master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BestPriceMaster findByItemId_Last(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByItemId_Last(itemId, orderByComparator);
    }

    /**
    * Returns the last best price master in the ordered set where itemId = &#63;.
    *
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching best price master, or <code>null</code> if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BestPriceMaster fetchByItemId_Last(
        java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByItemId_Last(itemId, orderByComparator);
    }

    /**
    * Returns the best price masters before and after the current best price master in the ordered set where itemId = &#63;.
    *
    * @param bestPriceMasterSid the primary key of the current best price master
    * @param itemId the item ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a best price master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BestPriceMaster[] findByItemId_PrevAndNext(
        int bestPriceMasterSid, java.lang.String itemId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemId_PrevAndNext(bestPriceMasterSid, itemId,
            orderByComparator);
    }

    /**
    * Removes all the best price masters where itemId = &#63; from the database.
    *
    * @param itemId the item ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByItemId(java.lang.String itemId)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByItemId(itemId);
    }

    /**
    * Returns the number of best price masters where itemId = &#63;.
    *
    * @param itemId the item ID
    * @return the number of matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByItemId(java.lang.String itemId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByItemId(itemId);
    }

    /**
    * Returns all the best price masters where itemNo = &#63;.
    *
    * @param itemNo the item no
    * @return the matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.BestPriceMaster> findByItemNo(
        java.lang.String itemNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByItemNo(itemNo);
    }

    /**
    * Returns a range of all the best price masters where itemNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemNo the item no
    * @param start the lower bound of the range of best price masters
    * @param end the upper bound of the range of best price masters (not inclusive)
    * @return the range of matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.BestPriceMaster> findByItemNo(
        java.lang.String itemNo, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByItemNo(itemNo, start, end);
    }

    /**
    * Returns an ordered range of all the best price masters where itemNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemNo the item no
    * @param start the lower bound of the range of best price masters
    * @param end the upper bound of the range of best price masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.BestPriceMaster> findByItemNo(
        java.lang.String itemNo, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemNo(itemNo, start, end, orderByComparator);
    }

    /**
    * Returns the first best price master in the ordered set where itemNo = &#63;.
    *
    * @param itemNo the item no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BestPriceMaster findByItemNo_First(
        java.lang.String itemNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByItemNo_First(itemNo, orderByComparator);
    }

    /**
    * Returns the first best price master in the ordered set where itemNo = &#63;.
    *
    * @param itemNo the item no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching best price master, or <code>null</code> if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BestPriceMaster fetchByItemNo_First(
        java.lang.String itemNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByItemNo_First(itemNo, orderByComparator);
    }

    /**
    * Returns the last best price master in the ordered set where itemNo = &#63;.
    *
    * @param itemNo the item no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BestPriceMaster findByItemNo_Last(
        java.lang.String itemNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByItemNo_Last(itemNo, orderByComparator);
    }

    /**
    * Returns the last best price master in the ordered set where itemNo = &#63;.
    *
    * @param itemNo the item no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching best price master, or <code>null</code> if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BestPriceMaster fetchByItemNo_Last(
        java.lang.String itemNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByItemNo_Last(itemNo, orderByComparator);
    }

    /**
    * Returns the best price masters before and after the current best price master in the ordered set where itemNo = &#63;.
    *
    * @param bestPriceMasterSid the primary key of the current best price master
    * @param itemNo the item no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a best price master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BestPriceMaster[] findByItemNo_PrevAndNext(
        int bestPriceMasterSid, java.lang.String itemNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemNo_PrevAndNext(bestPriceMasterSid, itemNo,
            orderByComparator);
    }

    /**
    * Removes all the best price masters where itemNo = &#63; from the database.
    *
    * @param itemNo the item no
    * @throws SystemException if a system exception occurred
    */
    public static void removeByItemNo(java.lang.String itemNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByItemNo(itemNo);
    }

    /**
    * Returns the number of best price masters where itemNo = &#63;.
    *
    * @param itemNo the item no
    * @return the number of matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByItemNo(java.lang.String itemNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByItemNo(itemNo);
    }

    /**
    * Returns all the best price masters where contractNo = &#63;.
    *
    * @param contractNo the contract no
    * @return the matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.BestPriceMaster> findByContractNo(
        java.lang.String contractNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByContractNo(contractNo);
    }

    /**
    * Returns a range of all the best price masters where contractNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contractNo the contract no
    * @param start the lower bound of the range of best price masters
    * @param end the upper bound of the range of best price masters (not inclusive)
    * @return the range of matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.BestPriceMaster> findByContractNo(
        java.lang.String contractNo, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByContractNo(contractNo, start, end);
    }

    /**
    * Returns an ordered range of all the best price masters where contractNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contractNo the contract no
    * @param start the lower bound of the range of best price masters
    * @param end the upper bound of the range of best price masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.BestPriceMaster> findByContractNo(
        java.lang.String contractNo, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContractNo(contractNo, start, end, orderByComparator);
    }

    /**
    * Returns the first best price master in the ordered set where contractNo = &#63;.
    *
    * @param contractNo the contract no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BestPriceMaster findByContractNo_First(
        java.lang.String contractNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContractNo_First(contractNo, orderByComparator);
    }

    /**
    * Returns the first best price master in the ordered set where contractNo = &#63;.
    *
    * @param contractNo the contract no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching best price master, or <code>null</code> if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BestPriceMaster fetchByContractNo_First(
        java.lang.String contractNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByContractNo_First(contractNo, orderByComparator);
    }

    /**
    * Returns the last best price master in the ordered set where contractNo = &#63;.
    *
    * @param contractNo the contract no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BestPriceMaster findByContractNo_Last(
        java.lang.String contractNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContractNo_Last(contractNo, orderByComparator);
    }

    /**
    * Returns the last best price master in the ordered set where contractNo = &#63;.
    *
    * @param contractNo the contract no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching best price master, or <code>null</code> if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BestPriceMaster fetchByContractNo_Last(
        java.lang.String contractNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByContractNo_Last(contractNo, orderByComparator);
    }

    /**
    * Returns the best price masters before and after the current best price master in the ordered set where contractNo = &#63;.
    *
    * @param bestPriceMasterSid the primary key of the current best price master
    * @param contractNo the contract no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a best price master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BestPriceMaster[] findByContractNo_PrevAndNext(
        int bestPriceMasterSid, java.lang.String contractNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContractNo_PrevAndNext(bestPriceMasterSid,
            contractNo, orderByComparator);
    }

    /**
    * Removes all the best price masters where contractNo = &#63; from the database.
    *
    * @param contractNo the contract no
    * @throws SystemException if a system exception occurred
    */
    public static void removeByContractNo(java.lang.String contractNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByContractNo(contractNo);
    }

    /**
    * Returns the number of best price masters where contractNo = &#63;.
    *
    * @param contractNo the contract no
    * @return the number of matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByContractNo(java.lang.String contractNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByContractNo(contractNo);
    }

    /**
    * Returns all the best price masters where contractId = &#63;.
    *
    * @param contractId the contract ID
    * @return the matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.BestPriceMaster> findByContractId(
        java.lang.String contractId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByContractId(contractId);
    }

    /**
    * Returns a range of all the best price masters where contractId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contractId the contract ID
    * @param start the lower bound of the range of best price masters
    * @param end the upper bound of the range of best price masters (not inclusive)
    * @return the range of matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.BestPriceMaster> findByContractId(
        java.lang.String contractId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByContractId(contractId, start, end);
    }

    /**
    * Returns an ordered range of all the best price masters where contractId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contractId the contract ID
    * @param start the lower bound of the range of best price masters
    * @param end the upper bound of the range of best price masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.BestPriceMaster> findByContractId(
        java.lang.String contractId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContractId(contractId, start, end, orderByComparator);
    }

    /**
    * Returns the first best price master in the ordered set where contractId = &#63;.
    *
    * @param contractId the contract ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BestPriceMaster findByContractId_First(
        java.lang.String contractId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContractId_First(contractId, orderByComparator);
    }

    /**
    * Returns the first best price master in the ordered set where contractId = &#63;.
    *
    * @param contractId the contract ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching best price master, or <code>null</code> if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BestPriceMaster fetchByContractId_First(
        java.lang.String contractId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByContractId_First(contractId, orderByComparator);
    }

    /**
    * Returns the last best price master in the ordered set where contractId = &#63;.
    *
    * @param contractId the contract ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BestPriceMaster findByContractId_Last(
        java.lang.String contractId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContractId_Last(contractId, orderByComparator);
    }

    /**
    * Returns the last best price master in the ordered set where contractId = &#63;.
    *
    * @param contractId the contract ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching best price master, or <code>null</code> if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BestPriceMaster fetchByContractId_Last(
        java.lang.String contractId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByContractId_Last(contractId, orderByComparator);
    }

    /**
    * Returns the best price masters before and after the current best price master in the ordered set where contractId = &#63;.
    *
    * @param bestPriceMasterSid the primary key of the current best price master
    * @param contractId the contract ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a best price master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BestPriceMaster[] findByContractId_PrevAndNext(
        int bestPriceMasterSid, java.lang.String contractId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContractId_PrevAndNext(bestPriceMasterSid,
            contractId, orderByComparator);
    }

    /**
    * Removes all the best price masters where contractId = &#63; from the database.
    *
    * @param contractId the contract ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByContractId(java.lang.String contractId)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByContractId(contractId);
    }

    /**
    * Returns the number of best price masters where contractId = &#63;.
    *
    * @param contractId the contract ID
    * @return the number of matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByContractId(java.lang.String contractId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByContractId(contractId);
    }

    /**
    * Returns all the best price masters where accountId = &#63;.
    *
    * @param accountId the account ID
    * @return the matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.BestPriceMaster> findByAccountId(
        java.lang.String accountId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByAccountId(accountId);
    }

    /**
    * Returns a range of all the best price masters where accountId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param accountId the account ID
    * @param start the lower bound of the range of best price masters
    * @param end the upper bound of the range of best price masters (not inclusive)
    * @return the range of matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.BestPriceMaster> findByAccountId(
        java.lang.String accountId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByAccountId(accountId, start, end);
    }

    /**
    * Returns an ordered range of all the best price masters where accountId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param accountId the account ID
    * @param start the lower bound of the range of best price masters
    * @param end the upper bound of the range of best price masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.BestPriceMaster> findByAccountId(
        java.lang.String accountId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAccountId(accountId, start, end, orderByComparator);
    }

    /**
    * Returns the first best price master in the ordered set where accountId = &#63;.
    *
    * @param accountId the account ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BestPriceMaster findByAccountId_First(
        java.lang.String accountId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAccountId_First(accountId, orderByComparator);
    }

    /**
    * Returns the first best price master in the ordered set where accountId = &#63;.
    *
    * @param accountId the account ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching best price master, or <code>null</code> if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BestPriceMaster fetchByAccountId_First(
        java.lang.String accountId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByAccountId_First(accountId, orderByComparator);
    }

    /**
    * Returns the last best price master in the ordered set where accountId = &#63;.
    *
    * @param accountId the account ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BestPriceMaster findByAccountId_Last(
        java.lang.String accountId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAccountId_Last(accountId, orderByComparator);
    }

    /**
    * Returns the last best price master in the ordered set where accountId = &#63;.
    *
    * @param accountId the account ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching best price master, or <code>null</code> if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BestPriceMaster fetchByAccountId_Last(
        java.lang.String accountId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByAccountId_Last(accountId, orderByComparator);
    }

    /**
    * Returns the best price masters before and after the current best price master in the ordered set where accountId = &#63;.
    *
    * @param bestPriceMasterSid the primary key of the current best price master
    * @param accountId the account ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a best price master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BestPriceMaster[] findByAccountId_PrevAndNext(
        int bestPriceMasterSid, java.lang.String accountId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAccountId_PrevAndNext(bestPriceMasterSid, accountId,
            orderByComparator);
    }

    /**
    * Removes all the best price masters where accountId = &#63; from the database.
    *
    * @param accountId the account ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByAccountId(java.lang.String accountId)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByAccountId(accountId);
    }

    /**
    * Returns the number of best price masters where accountId = &#63;.
    *
    * @param accountId the account ID
    * @return the number of matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByAccountId(java.lang.String accountId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByAccountId(accountId);
    }

    /**
    * Returns all the best price masters where period = &#63;.
    *
    * @param period the period
    * @return the matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.BestPriceMaster> findByPeriod(
        java.lang.String period)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPeriod(period);
    }

    /**
    * Returns a range of all the best price masters where period = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param period the period
    * @param start the lower bound of the range of best price masters
    * @param end the upper bound of the range of best price masters (not inclusive)
    * @return the range of matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.BestPriceMaster> findByPeriod(
        java.lang.String period, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPeriod(period, start, end);
    }

    /**
    * Returns an ordered range of all the best price masters where period = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param period the period
    * @param start the lower bound of the range of best price masters
    * @param end the upper bound of the range of best price masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.BestPriceMaster> findByPeriod(
        java.lang.String period, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPeriod(period, start, end, orderByComparator);
    }

    /**
    * Returns the first best price master in the ordered set where period = &#63;.
    *
    * @param period the period
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BestPriceMaster findByPeriod_First(
        java.lang.String period,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPeriod_First(period, orderByComparator);
    }

    /**
    * Returns the first best price master in the ordered set where period = &#63;.
    *
    * @param period the period
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching best price master, or <code>null</code> if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BestPriceMaster fetchByPeriod_First(
        java.lang.String period,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPeriod_First(period, orderByComparator);
    }

    /**
    * Returns the last best price master in the ordered set where period = &#63;.
    *
    * @param period the period
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BestPriceMaster findByPeriod_Last(
        java.lang.String period,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPeriod_Last(period, orderByComparator);
    }

    /**
    * Returns the last best price master in the ordered set where period = &#63;.
    *
    * @param period the period
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching best price master, or <code>null</code> if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BestPriceMaster fetchByPeriod_Last(
        java.lang.String period,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPeriod_Last(period, orderByComparator);
    }

    /**
    * Returns the best price masters before and after the current best price master in the ordered set where period = &#63;.
    *
    * @param bestPriceMasterSid the primary key of the current best price master
    * @param period the period
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a best price master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BestPriceMaster[] findByPeriod_PrevAndNext(
        int bestPriceMasterSid, java.lang.String period,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPeriod_PrevAndNext(bestPriceMasterSid, period,
            orderByComparator);
    }

    /**
    * Removes all the best price masters where period = &#63; from the database.
    *
    * @param period the period
    * @throws SystemException if a system exception occurred
    */
    public static void removeByPeriod(java.lang.String period)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByPeriod(period);
    }

    /**
    * Returns the number of best price masters where period = &#63;.
    *
    * @param period the period
    * @return the number of matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByPeriod(java.lang.String period)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByPeriod(period);
    }

    /**
    * Returns all the best price masters where itemId = &#63; and accountId = &#63; and period = &#63; and contractNo = &#63;.
    *
    * @param itemId the item ID
    * @param accountId the account ID
    * @param period the period
    * @param contractNo the contract no
    * @return the matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.BestPriceMaster> findByBestPriceUnique(
        java.lang.String itemId, java.lang.String accountId,
        java.lang.String period, java.lang.String contractNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByBestPriceUnique(itemId, accountId, period, contractNo);
    }

    /**
    * Returns a range of all the best price masters where itemId = &#63; and accountId = &#63; and period = &#63; and contractNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemId the item ID
    * @param accountId the account ID
    * @param period the period
    * @param contractNo the contract no
    * @param start the lower bound of the range of best price masters
    * @param end the upper bound of the range of best price masters (not inclusive)
    * @return the range of matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.BestPriceMaster> findByBestPriceUnique(
        java.lang.String itemId, java.lang.String accountId,
        java.lang.String period, java.lang.String contractNo, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByBestPriceUnique(itemId, accountId, period,
            contractNo, start, end);
    }

    /**
    * Returns an ordered range of all the best price masters where itemId = &#63; and accountId = &#63; and period = &#63; and contractNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param itemId the item ID
    * @param accountId the account ID
    * @param period the period
    * @param contractNo the contract no
    * @param start the lower bound of the range of best price masters
    * @param end the upper bound of the range of best price masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.BestPriceMaster> findByBestPriceUnique(
        java.lang.String itemId, java.lang.String accountId,
        java.lang.String period, java.lang.String contractNo, int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByBestPriceUnique(itemId, accountId, period,
            contractNo, start, end, orderByComparator);
    }

    /**
    * Returns the first best price master in the ordered set where itemId = &#63; and accountId = &#63; and period = &#63; and contractNo = &#63;.
    *
    * @param itemId the item ID
    * @param accountId the account ID
    * @param period the period
    * @param contractNo the contract no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BestPriceMaster findByBestPriceUnique_First(
        java.lang.String itemId, java.lang.String accountId,
        java.lang.String period, java.lang.String contractNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByBestPriceUnique_First(itemId, accountId, period,
            contractNo, orderByComparator);
    }

    /**
    * Returns the first best price master in the ordered set where itemId = &#63; and accountId = &#63; and period = &#63; and contractNo = &#63;.
    *
    * @param itemId the item ID
    * @param accountId the account ID
    * @param period the period
    * @param contractNo the contract no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching best price master, or <code>null</code> if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BestPriceMaster fetchByBestPriceUnique_First(
        java.lang.String itemId, java.lang.String accountId,
        java.lang.String period, java.lang.String contractNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByBestPriceUnique_First(itemId, accountId, period,
            contractNo, orderByComparator);
    }

    /**
    * Returns the last best price master in the ordered set where itemId = &#63; and accountId = &#63; and period = &#63; and contractNo = &#63;.
    *
    * @param itemId the item ID
    * @param accountId the account ID
    * @param period the period
    * @param contractNo the contract no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BestPriceMaster findByBestPriceUnique_Last(
        java.lang.String itemId, java.lang.String accountId,
        java.lang.String period, java.lang.String contractNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByBestPriceUnique_Last(itemId, accountId, period,
            contractNo, orderByComparator);
    }

    /**
    * Returns the last best price master in the ordered set where itemId = &#63; and accountId = &#63; and period = &#63; and contractNo = &#63;.
    *
    * @param itemId the item ID
    * @param accountId the account ID
    * @param period the period
    * @param contractNo the contract no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching best price master, or <code>null</code> if a matching best price master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BestPriceMaster fetchByBestPriceUnique_Last(
        java.lang.String itemId, java.lang.String accountId,
        java.lang.String period, java.lang.String contractNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByBestPriceUnique_Last(itemId, accountId, period,
            contractNo, orderByComparator);
    }

    /**
    * Returns the best price masters before and after the current best price master in the ordered set where itemId = &#63; and accountId = &#63; and period = &#63; and contractNo = &#63;.
    *
    * @param bestPriceMasterSid the primary key of the current best price master
    * @param itemId the item ID
    * @param accountId the account ID
    * @param period the period
    * @param contractNo the contract no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a best price master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BestPriceMaster[] findByBestPriceUnique_PrevAndNext(
        int bestPriceMasterSid, java.lang.String itemId,
        java.lang.String accountId, java.lang.String period,
        java.lang.String contractNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByBestPriceUnique_PrevAndNext(bestPriceMasterSid,
            itemId, accountId, period, contractNo, orderByComparator);
    }

    /**
    * Removes all the best price masters where itemId = &#63; and accountId = &#63; and period = &#63; and contractNo = &#63; from the database.
    *
    * @param itemId the item ID
    * @param accountId the account ID
    * @param period the period
    * @param contractNo the contract no
    * @throws SystemException if a system exception occurred
    */
    public static void removeByBestPriceUnique(java.lang.String itemId,
        java.lang.String accountId, java.lang.String period,
        java.lang.String contractNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence()
            .removeByBestPriceUnique(itemId, accountId, period, contractNo);
    }

    /**
    * Returns the number of best price masters where itemId = &#63; and accountId = &#63; and period = &#63; and contractNo = &#63;.
    *
    * @param itemId the item ID
    * @param accountId the account ID
    * @param period the period
    * @param contractNo the contract no
    * @return the number of matching best price masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByBestPriceUnique(java.lang.String itemId,
        java.lang.String accountId, java.lang.String period,
        java.lang.String contractNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByBestPriceUnique(itemId, accountId, period, contractNo);
    }

    /**
    * Caches the best price master in the entity cache if it is enabled.
    *
    * @param bestPriceMaster the best price master
    */
    public static void cacheResult(
        com.stpl.app.model.BestPriceMaster bestPriceMaster) {
        getPersistence().cacheResult(bestPriceMaster);
    }

    /**
    * Caches the best price masters in the entity cache if it is enabled.
    *
    * @param bestPriceMasters the best price masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.BestPriceMaster> bestPriceMasters) {
        getPersistence().cacheResult(bestPriceMasters);
    }

    /**
    * Creates a new best price master with the primary key. Does not add the best price master to the database.
    *
    * @param bestPriceMasterSid the primary key for the new best price master
    * @return the new best price master
    */
    public static com.stpl.app.model.BestPriceMaster create(
        int bestPriceMasterSid) {
        return getPersistence().create(bestPriceMasterSid);
    }

    /**
    * Removes the best price master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param bestPriceMasterSid the primary key of the best price master
    * @return the best price master that was removed
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a best price master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BestPriceMaster remove(
        int bestPriceMasterSid)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(bestPriceMasterSid);
    }

    public static com.stpl.app.model.BestPriceMaster updateImpl(
        com.stpl.app.model.BestPriceMaster bestPriceMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(bestPriceMaster);
    }

    /**
    * Returns the best price master with the primary key or throws a {@link com.stpl.app.NoSuchBestPriceMasterException} if it could not be found.
    *
    * @param bestPriceMasterSid the primary key of the best price master
    * @return the best price master
    * @throws com.stpl.app.NoSuchBestPriceMasterException if a best price master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BestPriceMaster findByPrimaryKey(
        int bestPriceMasterSid)
        throws com.stpl.app.NoSuchBestPriceMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(bestPriceMasterSid);
    }

    /**
    * Returns the best price master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param bestPriceMasterSid the primary key of the best price master
    * @return the best price master, or <code>null</code> if a best price master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BestPriceMaster fetchByPrimaryKey(
        int bestPriceMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(bestPriceMasterSid);
    }

    /**
    * Returns all the best price masters.
    *
    * @return the best price masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.BestPriceMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the best price masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of best price masters
    * @param end the upper bound of the range of best price masters (not inclusive)
    * @return the range of best price masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.BestPriceMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the best price masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of best price masters
    * @param end the upper bound of the range of best price masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of best price masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.BestPriceMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the best price masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of best price masters.
    *
    * @return the number of best price masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static BestPriceMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (BestPriceMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    BestPriceMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(BestPriceMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(BestPriceMasterPersistence persistence) {
    }
}
