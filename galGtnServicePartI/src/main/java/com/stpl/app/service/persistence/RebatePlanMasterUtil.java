package com.stpl.app.service.persistence;

import com.stpl.app.model.RebatePlanMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the rebate plan master service. This utility wraps {@link RebatePlanMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RebatePlanMasterPersistence
 * @see RebatePlanMasterPersistenceImpl
 * @generated
 */
public class RebatePlanMasterUtil {
    private static RebatePlanMasterPersistence _persistence;

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
    public static void clearCache(RebatePlanMaster rebatePlanMaster) {
        getPersistence().clearCache(rebatePlanMaster);
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
    public static List<RebatePlanMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<RebatePlanMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<RebatePlanMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static RebatePlanMaster update(RebatePlanMaster rebatePlanMaster)
        throws SystemException {
        return getPersistence().update(rebatePlanMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static RebatePlanMaster update(RebatePlanMaster rebatePlanMaster,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(rebatePlanMaster, serviceContext);
    }

    /**
    * Returns all the rebate plan masters where rebatePlanId = &#63;.
    *
    * @param rebatePlanId the rebate plan ID
    * @return the matching rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RebatePlanMaster> findByRebatePlanId(
        java.lang.String rebatePlanId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByRebatePlanId(rebatePlanId);
    }

    /**
    * Returns a range of all the rebate plan masters where rebatePlanId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rebatePlanId the rebate plan ID
    * @param start the lower bound of the range of rebate plan masters
    * @param end the upper bound of the range of rebate plan masters (not inclusive)
    * @return the range of matching rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RebatePlanMaster> findByRebatePlanId(
        java.lang.String rebatePlanId, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByRebatePlanId(rebatePlanId, start, end);
    }

    /**
    * Returns an ordered range of all the rebate plan masters where rebatePlanId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rebatePlanId the rebate plan ID
    * @param start the lower bound of the range of rebate plan masters
    * @param end the upper bound of the range of rebate plan masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RebatePlanMaster> findByRebatePlanId(
        java.lang.String rebatePlanId, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebatePlanId(rebatePlanId, start, end,
            orderByComparator);
    }

    /**
    * Returns the first rebate plan master in the ordered set where rebatePlanId = &#63;.
    *
    * @param rebatePlanId the rebate plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rebate plan master
    * @throws com.stpl.app.NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RebatePlanMaster findByRebatePlanId_First(
        java.lang.String rebatePlanId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRebatePlanMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebatePlanId_First(rebatePlanId, orderByComparator);
    }

    /**
    * Returns the first rebate plan master in the ordered set where rebatePlanId = &#63;.
    *
    * @param rebatePlanId the rebate plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RebatePlanMaster fetchByRebatePlanId_First(
        java.lang.String rebatePlanId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByRebatePlanId_First(rebatePlanId, orderByComparator);
    }

    /**
    * Returns the last rebate plan master in the ordered set where rebatePlanId = &#63;.
    *
    * @param rebatePlanId the rebate plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rebate plan master
    * @throws com.stpl.app.NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RebatePlanMaster findByRebatePlanId_Last(
        java.lang.String rebatePlanId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRebatePlanMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebatePlanId_Last(rebatePlanId, orderByComparator);
    }

    /**
    * Returns the last rebate plan master in the ordered set where rebatePlanId = &#63;.
    *
    * @param rebatePlanId the rebate plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RebatePlanMaster fetchByRebatePlanId_Last(
        java.lang.String rebatePlanId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByRebatePlanId_Last(rebatePlanId, orderByComparator);
    }

    /**
    * Returns the rebate plan masters before and after the current rebate plan master in the ordered set where rebatePlanId = &#63;.
    *
    * @param rebatePlanMasterSid the primary key of the current rebate plan master
    * @param rebatePlanId the rebate plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next rebate plan master
    * @throws com.stpl.app.NoSuchRebatePlanMasterException if a rebate plan master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RebatePlanMaster[] findByRebatePlanId_PrevAndNext(
        int rebatePlanMasterSid, java.lang.String rebatePlanId,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRebatePlanMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebatePlanId_PrevAndNext(rebatePlanMasterSid,
            rebatePlanId, orderByComparator);
    }

    /**
    * Removes all the rebate plan masters where rebatePlanId = &#63; from the database.
    *
    * @param rebatePlanId the rebate plan ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByRebatePlanId(java.lang.String rebatePlanId)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByRebatePlanId(rebatePlanId);
    }

    /**
    * Returns the number of rebate plan masters where rebatePlanId = &#63;.
    *
    * @param rebatePlanId the rebate plan ID
    * @return the number of matching rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByRebatePlanId(java.lang.String rebatePlanId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByRebatePlanId(rebatePlanId);
    }

    /**
    * Returns all the rebate plan masters where rebatePlanNo = &#63;.
    *
    * @param rebatePlanNo the rebate plan no
    * @return the matching rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RebatePlanMaster> findByRebatePlanNo(
        java.lang.String rebatePlanNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByRebatePlanNo(rebatePlanNo);
    }

    /**
    * Returns a range of all the rebate plan masters where rebatePlanNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rebatePlanNo the rebate plan no
    * @param start the lower bound of the range of rebate plan masters
    * @param end the upper bound of the range of rebate plan masters (not inclusive)
    * @return the range of matching rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RebatePlanMaster> findByRebatePlanNo(
        java.lang.String rebatePlanNo, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByRebatePlanNo(rebatePlanNo, start, end);
    }

    /**
    * Returns an ordered range of all the rebate plan masters where rebatePlanNo = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rebatePlanNo the rebate plan no
    * @param start the lower bound of the range of rebate plan masters
    * @param end the upper bound of the range of rebate plan masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RebatePlanMaster> findByRebatePlanNo(
        java.lang.String rebatePlanNo, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebatePlanNo(rebatePlanNo, start, end,
            orderByComparator);
    }

    /**
    * Returns the first rebate plan master in the ordered set where rebatePlanNo = &#63;.
    *
    * @param rebatePlanNo the rebate plan no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rebate plan master
    * @throws com.stpl.app.NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RebatePlanMaster findByRebatePlanNo_First(
        java.lang.String rebatePlanNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRebatePlanMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebatePlanNo_First(rebatePlanNo, orderByComparator);
    }

    /**
    * Returns the first rebate plan master in the ordered set where rebatePlanNo = &#63;.
    *
    * @param rebatePlanNo the rebate plan no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RebatePlanMaster fetchByRebatePlanNo_First(
        java.lang.String rebatePlanNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByRebatePlanNo_First(rebatePlanNo, orderByComparator);
    }

    /**
    * Returns the last rebate plan master in the ordered set where rebatePlanNo = &#63;.
    *
    * @param rebatePlanNo the rebate plan no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rebate plan master
    * @throws com.stpl.app.NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RebatePlanMaster findByRebatePlanNo_Last(
        java.lang.String rebatePlanNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRebatePlanMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebatePlanNo_Last(rebatePlanNo, orderByComparator);
    }

    /**
    * Returns the last rebate plan master in the ordered set where rebatePlanNo = &#63;.
    *
    * @param rebatePlanNo the rebate plan no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RebatePlanMaster fetchByRebatePlanNo_Last(
        java.lang.String rebatePlanNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByRebatePlanNo_Last(rebatePlanNo, orderByComparator);
    }

    /**
    * Returns the rebate plan masters before and after the current rebate plan master in the ordered set where rebatePlanNo = &#63;.
    *
    * @param rebatePlanMasterSid the primary key of the current rebate plan master
    * @param rebatePlanNo the rebate plan no
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next rebate plan master
    * @throws com.stpl.app.NoSuchRebatePlanMasterException if a rebate plan master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RebatePlanMaster[] findByRebatePlanNo_PrevAndNext(
        int rebatePlanMasterSid, java.lang.String rebatePlanNo,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRebatePlanMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebatePlanNo_PrevAndNext(rebatePlanMasterSid,
            rebatePlanNo, orderByComparator);
    }

    /**
    * Removes all the rebate plan masters where rebatePlanNo = &#63; from the database.
    *
    * @param rebatePlanNo the rebate plan no
    * @throws SystemException if a system exception occurred
    */
    public static void removeByRebatePlanNo(java.lang.String rebatePlanNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByRebatePlanNo(rebatePlanNo);
    }

    /**
    * Returns the number of rebate plan masters where rebatePlanNo = &#63;.
    *
    * @param rebatePlanNo the rebate plan no
    * @return the number of matching rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByRebatePlanNo(java.lang.String rebatePlanNo)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByRebatePlanNo(rebatePlanNo);
    }

    /**
    * Returns all the rebate plan masters where rebatePlanName = &#63;.
    *
    * @param rebatePlanName the rebate plan name
    * @return the matching rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RebatePlanMaster> findByRebatePlanName(
        java.lang.String rebatePlanName)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByRebatePlanName(rebatePlanName);
    }

    /**
    * Returns a range of all the rebate plan masters where rebatePlanName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rebatePlanName the rebate plan name
    * @param start the lower bound of the range of rebate plan masters
    * @param end the upper bound of the range of rebate plan masters (not inclusive)
    * @return the range of matching rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RebatePlanMaster> findByRebatePlanName(
        java.lang.String rebatePlanName, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByRebatePlanName(rebatePlanName, start, end);
    }

    /**
    * Returns an ordered range of all the rebate plan masters where rebatePlanName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rebatePlanName the rebate plan name
    * @param start the lower bound of the range of rebate plan masters
    * @param end the upper bound of the range of rebate plan masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RebatePlanMaster> findByRebatePlanName(
        java.lang.String rebatePlanName, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebatePlanName(rebatePlanName, start, end,
            orderByComparator);
    }

    /**
    * Returns the first rebate plan master in the ordered set where rebatePlanName = &#63;.
    *
    * @param rebatePlanName the rebate plan name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rebate plan master
    * @throws com.stpl.app.NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RebatePlanMaster findByRebatePlanName_First(
        java.lang.String rebatePlanName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRebatePlanMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebatePlanName_First(rebatePlanName, orderByComparator);
    }

    /**
    * Returns the first rebate plan master in the ordered set where rebatePlanName = &#63;.
    *
    * @param rebatePlanName the rebate plan name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RebatePlanMaster fetchByRebatePlanName_First(
        java.lang.String rebatePlanName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByRebatePlanName_First(rebatePlanName,
            orderByComparator);
    }

    /**
    * Returns the last rebate plan master in the ordered set where rebatePlanName = &#63;.
    *
    * @param rebatePlanName the rebate plan name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rebate plan master
    * @throws com.stpl.app.NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RebatePlanMaster findByRebatePlanName_Last(
        java.lang.String rebatePlanName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRebatePlanMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebatePlanName_Last(rebatePlanName, orderByComparator);
    }

    /**
    * Returns the last rebate plan master in the ordered set where rebatePlanName = &#63;.
    *
    * @param rebatePlanName the rebate plan name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RebatePlanMaster fetchByRebatePlanName_Last(
        java.lang.String rebatePlanName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByRebatePlanName_Last(rebatePlanName, orderByComparator);
    }

    /**
    * Returns the rebate plan masters before and after the current rebate plan master in the ordered set where rebatePlanName = &#63;.
    *
    * @param rebatePlanMasterSid the primary key of the current rebate plan master
    * @param rebatePlanName the rebate plan name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next rebate plan master
    * @throws com.stpl.app.NoSuchRebatePlanMasterException if a rebate plan master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RebatePlanMaster[] findByRebatePlanName_PrevAndNext(
        int rebatePlanMasterSid, java.lang.String rebatePlanName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRebatePlanMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebatePlanName_PrevAndNext(rebatePlanMasterSid,
            rebatePlanName, orderByComparator);
    }

    /**
    * Removes all the rebate plan masters where rebatePlanName = &#63; from the database.
    *
    * @param rebatePlanName the rebate plan name
    * @throws SystemException if a system exception occurred
    */
    public static void removeByRebatePlanName(java.lang.String rebatePlanName)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByRebatePlanName(rebatePlanName);
    }

    /**
    * Returns the number of rebate plan masters where rebatePlanName = &#63;.
    *
    * @param rebatePlanName the rebate plan name
    * @return the number of matching rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByRebatePlanName(java.lang.String rebatePlanName)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByRebatePlanName(rebatePlanName);
    }

    /**
    * Returns all the rebate plan masters where rebatePlanStatus = &#63;.
    *
    * @param rebatePlanStatus the rebate plan status
    * @return the matching rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RebatePlanMaster> findByRebatePlanStatus(
        int rebatePlanStatus)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByRebatePlanStatus(rebatePlanStatus);
    }

    /**
    * Returns a range of all the rebate plan masters where rebatePlanStatus = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rebatePlanStatus the rebate plan status
    * @param start the lower bound of the range of rebate plan masters
    * @param end the upper bound of the range of rebate plan masters (not inclusive)
    * @return the range of matching rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RebatePlanMaster> findByRebatePlanStatus(
        int rebatePlanStatus, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebatePlanStatus(rebatePlanStatus, start, end);
    }

    /**
    * Returns an ordered range of all the rebate plan masters where rebatePlanStatus = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rebatePlanStatus the rebate plan status
    * @param start the lower bound of the range of rebate plan masters
    * @param end the upper bound of the range of rebate plan masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RebatePlanMaster> findByRebatePlanStatus(
        int rebatePlanStatus, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebatePlanStatus(rebatePlanStatus, start, end,
            orderByComparator);
    }

    /**
    * Returns the first rebate plan master in the ordered set where rebatePlanStatus = &#63;.
    *
    * @param rebatePlanStatus the rebate plan status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rebate plan master
    * @throws com.stpl.app.NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RebatePlanMaster findByRebatePlanStatus_First(
        int rebatePlanStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRebatePlanMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebatePlanStatus_First(rebatePlanStatus,
            orderByComparator);
    }

    /**
    * Returns the first rebate plan master in the ordered set where rebatePlanStatus = &#63;.
    *
    * @param rebatePlanStatus the rebate plan status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RebatePlanMaster fetchByRebatePlanStatus_First(
        int rebatePlanStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByRebatePlanStatus_First(rebatePlanStatus,
            orderByComparator);
    }

    /**
    * Returns the last rebate plan master in the ordered set where rebatePlanStatus = &#63;.
    *
    * @param rebatePlanStatus the rebate plan status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rebate plan master
    * @throws com.stpl.app.NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RebatePlanMaster findByRebatePlanStatus_Last(
        int rebatePlanStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRebatePlanMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebatePlanStatus_Last(rebatePlanStatus,
            orderByComparator);
    }

    /**
    * Returns the last rebate plan master in the ordered set where rebatePlanStatus = &#63;.
    *
    * @param rebatePlanStatus the rebate plan status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RebatePlanMaster fetchByRebatePlanStatus_Last(
        int rebatePlanStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByRebatePlanStatus_Last(rebatePlanStatus,
            orderByComparator);
    }

    /**
    * Returns the rebate plan masters before and after the current rebate plan master in the ordered set where rebatePlanStatus = &#63;.
    *
    * @param rebatePlanMasterSid the primary key of the current rebate plan master
    * @param rebatePlanStatus the rebate plan status
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next rebate plan master
    * @throws com.stpl.app.NoSuchRebatePlanMasterException if a rebate plan master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RebatePlanMaster[] findByRebatePlanStatus_PrevAndNext(
        int rebatePlanMasterSid, int rebatePlanStatus,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRebatePlanMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebatePlanStatus_PrevAndNext(rebatePlanMasterSid,
            rebatePlanStatus, orderByComparator);
    }

    /**
    * Removes all the rebate plan masters where rebatePlanStatus = &#63; from the database.
    *
    * @param rebatePlanStatus the rebate plan status
    * @throws SystemException if a system exception occurred
    */
    public static void removeByRebatePlanStatus(int rebatePlanStatus)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByRebatePlanStatus(rebatePlanStatus);
    }

    /**
    * Returns the number of rebate plan masters where rebatePlanStatus = &#63;.
    *
    * @param rebatePlanStatus the rebate plan status
    * @return the number of matching rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByRebatePlanStatus(int rebatePlanStatus)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByRebatePlanStatus(rebatePlanStatus);
    }

    /**
    * Returns all the rebate plan masters where rebatePlanType = &#63;.
    *
    * @param rebatePlanType the rebate plan type
    * @return the matching rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RebatePlanMaster> findByRebatePlanType(
        int rebatePlanType)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByRebatePlanType(rebatePlanType);
    }

    /**
    * Returns a range of all the rebate plan masters where rebatePlanType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rebatePlanType the rebate plan type
    * @param start the lower bound of the range of rebate plan masters
    * @param end the upper bound of the range of rebate plan masters (not inclusive)
    * @return the range of matching rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RebatePlanMaster> findByRebatePlanType(
        int rebatePlanType, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByRebatePlanType(rebatePlanType, start, end);
    }

    /**
    * Returns an ordered range of all the rebate plan masters where rebatePlanType = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rebatePlanType the rebate plan type
    * @param start the lower bound of the range of rebate plan masters
    * @param end the upper bound of the range of rebate plan masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RebatePlanMaster> findByRebatePlanType(
        int rebatePlanType, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebatePlanType(rebatePlanType, start, end,
            orderByComparator);
    }

    /**
    * Returns the first rebate plan master in the ordered set where rebatePlanType = &#63;.
    *
    * @param rebatePlanType the rebate plan type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rebate plan master
    * @throws com.stpl.app.NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RebatePlanMaster findByRebatePlanType_First(
        int rebatePlanType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRebatePlanMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebatePlanType_First(rebatePlanType, orderByComparator);
    }

    /**
    * Returns the first rebate plan master in the ordered set where rebatePlanType = &#63;.
    *
    * @param rebatePlanType the rebate plan type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RebatePlanMaster fetchByRebatePlanType_First(
        int rebatePlanType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByRebatePlanType_First(rebatePlanType,
            orderByComparator);
    }

    /**
    * Returns the last rebate plan master in the ordered set where rebatePlanType = &#63;.
    *
    * @param rebatePlanType the rebate plan type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rebate plan master
    * @throws com.stpl.app.NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RebatePlanMaster findByRebatePlanType_Last(
        int rebatePlanType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRebatePlanMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebatePlanType_Last(rebatePlanType, orderByComparator);
    }

    /**
    * Returns the last rebate plan master in the ordered set where rebatePlanType = &#63;.
    *
    * @param rebatePlanType the rebate plan type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RebatePlanMaster fetchByRebatePlanType_Last(
        int rebatePlanType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByRebatePlanType_Last(rebatePlanType, orderByComparator);
    }

    /**
    * Returns the rebate plan masters before and after the current rebate plan master in the ordered set where rebatePlanType = &#63;.
    *
    * @param rebatePlanMasterSid the primary key of the current rebate plan master
    * @param rebatePlanType the rebate plan type
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next rebate plan master
    * @throws com.stpl.app.NoSuchRebatePlanMasterException if a rebate plan master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RebatePlanMaster[] findByRebatePlanType_PrevAndNext(
        int rebatePlanMasterSid, int rebatePlanType,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRebatePlanMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebatePlanType_PrevAndNext(rebatePlanMasterSid,
            rebatePlanType, orderByComparator);
    }

    /**
    * Removes all the rebate plan masters where rebatePlanType = &#63; from the database.
    *
    * @param rebatePlanType the rebate plan type
    * @throws SystemException if a system exception occurred
    */
    public static void removeByRebatePlanType(int rebatePlanType)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByRebatePlanType(rebatePlanType);
    }

    /**
    * Returns the number of rebate plan masters where rebatePlanType = &#63;.
    *
    * @param rebatePlanType the rebate plan type
    * @return the number of matching rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public static int countByRebatePlanType(int rebatePlanType)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByRebatePlanType(rebatePlanType);
    }

    /**
    * Caches the rebate plan master in the entity cache if it is enabled.
    *
    * @param rebatePlanMaster the rebate plan master
    */
    public static void cacheResult(
        com.stpl.app.model.RebatePlanMaster rebatePlanMaster) {
        getPersistence().cacheResult(rebatePlanMaster);
    }

    /**
    * Caches the rebate plan masters in the entity cache if it is enabled.
    *
    * @param rebatePlanMasters the rebate plan masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.RebatePlanMaster> rebatePlanMasters) {
        getPersistence().cacheResult(rebatePlanMasters);
    }

    /**
    * Creates a new rebate plan master with the primary key. Does not add the rebate plan master to the database.
    *
    * @param rebatePlanMasterSid the primary key for the new rebate plan master
    * @return the new rebate plan master
    */
    public static com.stpl.app.model.RebatePlanMaster create(
        int rebatePlanMasterSid) {
        return getPersistence().create(rebatePlanMasterSid);
    }

    /**
    * Removes the rebate plan master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param rebatePlanMasterSid the primary key of the rebate plan master
    * @return the rebate plan master that was removed
    * @throws com.stpl.app.NoSuchRebatePlanMasterException if a rebate plan master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RebatePlanMaster remove(
        int rebatePlanMasterSid)
        throws com.stpl.app.NoSuchRebatePlanMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(rebatePlanMasterSid);
    }

    public static com.stpl.app.model.RebatePlanMaster updateImpl(
        com.stpl.app.model.RebatePlanMaster rebatePlanMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(rebatePlanMaster);
    }

    /**
    * Returns the rebate plan master with the primary key or throws a {@link com.stpl.app.NoSuchRebatePlanMasterException} if it could not be found.
    *
    * @param rebatePlanMasterSid the primary key of the rebate plan master
    * @return the rebate plan master
    * @throws com.stpl.app.NoSuchRebatePlanMasterException if a rebate plan master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RebatePlanMaster findByPrimaryKey(
        int rebatePlanMasterSid)
        throws com.stpl.app.NoSuchRebatePlanMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(rebatePlanMasterSid);
    }

    /**
    * Returns the rebate plan master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param rebatePlanMasterSid the primary key of the rebate plan master
    * @return the rebate plan master, or <code>null</code> if a rebate plan master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RebatePlanMaster fetchByPrimaryKey(
        int rebatePlanMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(rebatePlanMasterSid);
    }

    /**
    * Returns all the rebate plan masters.
    *
    * @return the rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RebatePlanMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the rebate plan masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of rebate plan masters
    * @param end the upper bound of the range of rebate plan masters (not inclusive)
    * @return the range of rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RebatePlanMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the rebate plan masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of rebate plan masters
    * @param end the upper bound of the range of rebate plan masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RebatePlanMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the rebate plan masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of rebate plan masters.
    *
    * @return the number of rebate plan masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static RebatePlanMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (RebatePlanMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    RebatePlanMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(RebatePlanMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(RebatePlanMasterPersistence persistence) {
    }
}
