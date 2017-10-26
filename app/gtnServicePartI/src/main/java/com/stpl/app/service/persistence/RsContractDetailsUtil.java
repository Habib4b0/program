package com.stpl.app.service.persistence;

import com.stpl.app.model.RsContractDetails;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the rs contract details service. This utility wraps {@link RsContractDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RsContractDetailsPersistence
 * @see RsContractDetailsPersistenceImpl
 * @generated
 */
public class RsContractDetailsUtil {
    private static RsContractDetailsPersistence _persistence;

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
    public static void clearCache(RsContractDetails rsContractDetails) {
        getPersistence().clearCache(rsContractDetails);
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
    public static List<RsContractDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<RsContractDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<RsContractDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static RsContractDetails update(RsContractDetails rsContractDetails)
        throws SystemException {
        return getPersistence().update(rsContractDetails);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static RsContractDetails update(
        RsContractDetails rsContractDetails, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(rsContractDetails, serviceContext);
    }

    /**
    * Returns all the rs contract detailses where rsContractSid = &#63; and itemMasterSid = &#63;.
    *
    * @param rsContractSid the rs contract sid
    * @param itemMasterSid the item master sid
    * @return the matching rs contract detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RsContractDetails> findByRebateScheduleDetails(
        int rsContractSid, int itemMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebateScheduleDetails(rsContractSid, itemMasterSid);
    }

    /**
    * Returns a range of all the rs contract detailses where rsContractSid = &#63; and itemMasterSid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rsContractSid the rs contract sid
    * @param itemMasterSid the item master sid
    * @param start the lower bound of the range of rs contract detailses
    * @param end the upper bound of the range of rs contract detailses (not inclusive)
    * @return the range of matching rs contract detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RsContractDetails> findByRebateScheduleDetails(
        int rsContractSid, int itemMasterSid, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebateScheduleDetails(rsContractSid, itemMasterSid,
            start, end);
    }

    /**
    * Returns an ordered range of all the rs contract detailses where rsContractSid = &#63; and itemMasterSid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param rsContractSid the rs contract sid
    * @param itemMasterSid the item master sid
    * @param start the lower bound of the range of rs contract detailses
    * @param end the upper bound of the range of rs contract detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rs contract detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RsContractDetails> findByRebateScheduleDetails(
        int rsContractSid, int itemMasterSid, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebateScheduleDetails(rsContractSid, itemMasterSid,
            start, end, orderByComparator);
    }

    /**
    * Returns the first rs contract details in the ordered set where rsContractSid = &#63; and itemMasterSid = &#63;.
    *
    * @param rsContractSid the rs contract sid
    * @param itemMasterSid the item master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rs contract details
    * @throws com.stpl.app.NoSuchRsContractDetailsException if a matching rs contract details could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsContractDetails findByRebateScheduleDetails_First(
        int rsContractSid, int itemMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRsContractDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebateScheduleDetails_First(rsContractSid,
            itemMasterSid, orderByComparator);
    }

    /**
    * Returns the first rs contract details in the ordered set where rsContractSid = &#63; and itemMasterSid = &#63;.
    *
    * @param rsContractSid the rs contract sid
    * @param itemMasterSid the item master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching rs contract details, or <code>null</code> if a matching rs contract details could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsContractDetails fetchByRebateScheduleDetails_First(
        int rsContractSid, int itemMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByRebateScheduleDetails_First(rsContractSid,
            itemMasterSid, orderByComparator);
    }

    /**
    * Returns the last rs contract details in the ordered set where rsContractSid = &#63; and itemMasterSid = &#63;.
    *
    * @param rsContractSid the rs contract sid
    * @param itemMasterSid the item master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rs contract details
    * @throws com.stpl.app.NoSuchRsContractDetailsException if a matching rs contract details could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsContractDetails findByRebateScheduleDetails_Last(
        int rsContractSid, int itemMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRsContractDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebateScheduleDetails_Last(rsContractSid,
            itemMasterSid, orderByComparator);
    }

    /**
    * Returns the last rs contract details in the ordered set where rsContractSid = &#63; and itemMasterSid = &#63;.
    *
    * @param rsContractSid the rs contract sid
    * @param itemMasterSid the item master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching rs contract details, or <code>null</code> if a matching rs contract details could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsContractDetails fetchByRebateScheduleDetails_Last(
        int rsContractSid, int itemMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByRebateScheduleDetails_Last(rsContractSid,
            itemMasterSid, orderByComparator);
    }

    /**
    * Returns the rs contract detailses before and after the current rs contract details in the ordered set where rsContractSid = &#63; and itemMasterSid = &#63;.
    *
    * @param rsContractDetailsSid the primary key of the current rs contract details
    * @param rsContractSid the rs contract sid
    * @param itemMasterSid the item master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next rs contract details
    * @throws com.stpl.app.NoSuchRsContractDetailsException if a rs contract details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsContractDetails[] findByRebateScheduleDetails_PrevAndNext(
        int rsContractDetailsSid, int rsContractSid, int itemMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchRsContractDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByRebateScheduleDetails_PrevAndNext(rsContractDetailsSid,
            rsContractSid, itemMasterSid, orderByComparator);
    }

    /**
    * Removes all the rs contract detailses where rsContractSid = &#63; and itemMasterSid = &#63; from the database.
    *
    * @param rsContractSid the rs contract sid
    * @param itemMasterSid the item master sid
    * @throws SystemException if a system exception occurred
    */
    public static void removeByRebateScheduleDetails(int rsContractSid,
        int itemMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence()
            .removeByRebateScheduleDetails(rsContractSid, itemMasterSid);
    }

    /**
    * Returns the number of rs contract detailses where rsContractSid = &#63; and itemMasterSid = &#63;.
    *
    * @param rsContractSid the rs contract sid
    * @param itemMasterSid the item master sid
    * @return the number of matching rs contract detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countByRebateScheduleDetails(int rsContractSid,
        int itemMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByRebateScheduleDetails(rsContractSid, itemMasterSid);
    }

    /**
    * Caches the rs contract details in the entity cache if it is enabled.
    *
    * @param rsContractDetails the rs contract details
    */
    public static void cacheResult(
        com.stpl.app.model.RsContractDetails rsContractDetails) {
        getPersistence().cacheResult(rsContractDetails);
    }

    /**
    * Caches the rs contract detailses in the entity cache if it is enabled.
    *
    * @param rsContractDetailses the rs contract detailses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.RsContractDetails> rsContractDetailses) {
        getPersistence().cacheResult(rsContractDetailses);
    }

    /**
    * Creates a new rs contract details with the primary key. Does not add the rs contract details to the database.
    *
    * @param rsContractDetailsSid the primary key for the new rs contract details
    * @return the new rs contract details
    */
    public static com.stpl.app.model.RsContractDetails create(
        int rsContractDetailsSid) {
        return getPersistence().create(rsContractDetailsSid);
    }

    /**
    * Removes the rs contract details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param rsContractDetailsSid the primary key of the rs contract details
    * @return the rs contract details that was removed
    * @throws com.stpl.app.NoSuchRsContractDetailsException if a rs contract details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsContractDetails remove(
        int rsContractDetailsSid)
        throws com.stpl.app.NoSuchRsContractDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(rsContractDetailsSid);
    }

    public static com.stpl.app.model.RsContractDetails updateImpl(
        com.stpl.app.model.RsContractDetails rsContractDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(rsContractDetails);
    }

    /**
    * Returns the rs contract details with the primary key or throws a {@link com.stpl.app.NoSuchRsContractDetailsException} if it could not be found.
    *
    * @param rsContractDetailsSid the primary key of the rs contract details
    * @return the rs contract details
    * @throws com.stpl.app.NoSuchRsContractDetailsException if a rs contract details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsContractDetails findByPrimaryKey(
        int rsContractDetailsSid)
        throws com.stpl.app.NoSuchRsContractDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(rsContractDetailsSid);
    }

    /**
    * Returns the rs contract details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param rsContractDetailsSid the primary key of the rs contract details
    * @return the rs contract details, or <code>null</code> if a rs contract details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsContractDetails fetchByPrimaryKey(
        int rsContractDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(rsContractDetailsSid);
    }

    /**
    * Returns all the rs contract detailses.
    *
    * @return the rs contract detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RsContractDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the rs contract detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of rs contract detailses
    * @param end the upper bound of the range of rs contract detailses (not inclusive)
    * @return the range of rs contract detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RsContractDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the rs contract detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of rs contract detailses
    * @param end the upper bound of the range of rs contract detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of rs contract detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RsContractDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the rs contract detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of rs contract detailses.
    *
    * @return the number of rs contract detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static RsContractDetailsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (RsContractDetailsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    RsContractDetailsPersistence.class.getName());

            ReferenceRegistry.registerReference(RsContractDetailsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(RsContractDetailsPersistence persistence) {
    }
}
