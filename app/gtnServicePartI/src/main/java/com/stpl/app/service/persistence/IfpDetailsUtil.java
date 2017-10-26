package com.stpl.app.service.persistence;

import com.stpl.app.model.IfpDetails;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ifp details service. This utility wraps {@link IfpDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IfpDetailsPersistence
 * @see IfpDetailsPersistenceImpl
 * @generated
 */
public class IfpDetailsUtil {
    private static IfpDetailsPersistence _persistence;

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
    public static void clearCache(IfpDetails ifpDetails) {
        getPersistence().clearCache(ifpDetails);
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
    public static List<IfpDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<IfpDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<IfpDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static IfpDetails update(IfpDetails ifpDetails)
        throws SystemException {
        return getPersistence().update(ifpDetails);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static IfpDetails update(IfpDetails ifpDetails,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(ifpDetails, serviceContext);
    }

    /**
    * Returns all the ifp detailses where ifpModelSid = &#63;.
    *
    * @param ifpModelSid the ifp model sid
    * @return the matching ifp detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IfpDetails> findByItemFamilyPlanDetails(
        int ifpModelSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByItemFamilyPlanDetails(ifpModelSid);
    }

    /**
    * Returns a range of all the ifp detailses where ifpModelSid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param ifpModelSid the ifp model sid
    * @param start the lower bound of the range of ifp detailses
    * @param end the upper bound of the range of ifp detailses (not inclusive)
    * @return the range of matching ifp detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IfpDetails> findByItemFamilyPlanDetails(
        int ifpModelSid, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemFamilyPlanDetails(ifpModelSid, start, end);
    }

    /**
    * Returns an ordered range of all the ifp detailses where ifpModelSid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param ifpModelSid the ifp model sid
    * @param start the lower bound of the range of ifp detailses
    * @param end the upper bound of the range of ifp detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching ifp detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IfpDetails> findByItemFamilyPlanDetails(
        int ifpModelSid, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemFamilyPlanDetails(ifpModelSid, start, end,
            orderByComparator);
    }

    /**
    * Returns the first ifp details in the ordered set where ifpModelSid = &#63;.
    *
    * @param ifpModelSid the ifp model sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ifp details
    * @throws com.stpl.app.NoSuchIfpDetailsException if a matching ifp details could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IfpDetails findByItemFamilyPlanDetails_First(
        int ifpModelSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchIfpDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemFamilyPlanDetails_First(ifpModelSid,
            orderByComparator);
    }

    /**
    * Returns the first ifp details in the ordered set where ifpModelSid = &#63;.
    *
    * @param ifpModelSid the ifp model sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ifp details, or <code>null</code> if a matching ifp details could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IfpDetails fetchByItemFamilyPlanDetails_First(
        int ifpModelSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByItemFamilyPlanDetails_First(ifpModelSid,
            orderByComparator);
    }

    /**
    * Returns the last ifp details in the ordered set where ifpModelSid = &#63;.
    *
    * @param ifpModelSid the ifp model sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ifp details
    * @throws com.stpl.app.NoSuchIfpDetailsException if a matching ifp details could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IfpDetails findByItemFamilyPlanDetails_Last(
        int ifpModelSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchIfpDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemFamilyPlanDetails_Last(ifpModelSid,
            orderByComparator);
    }

    /**
    * Returns the last ifp details in the ordered set where ifpModelSid = &#63;.
    *
    * @param ifpModelSid the ifp model sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ifp details, or <code>null</code> if a matching ifp details could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IfpDetails fetchByItemFamilyPlanDetails_Last(
        int ifpModelSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByItemFamilyPlanDetails_Last(ifpModelSid,
            orderByComparator);
    }

    /**
    * Returns the ifp detailses before and after the current ifp details in the ordered set where ifpModelSid = &#63;.
    *
    * @param ifpDetailsSid the primary key of the current ifp details
    * @param ifpModelSid the ifp model sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next ifp details
    * @throws com.stpl.app.NoSuchIfpDetailsException if a ifp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IfpDetails[] findByItemFamilyPlanDetails_PrevAndNext(
        int ifpDetailsSid, int ifpModelSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchIfpDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByItemFamilyPlanDetails_PrevAndNext(ifpDetailsSid,
            ifpModelSid, orderByComparator);
    }

    /**
    * Removes all the ifp detailses where ifpModelSid = &#63; from the database.
    *
    * @param ifpModelSid the ifp model sid
    * @throws SystemException if a system exception occurred
    */
    public static void removeByItemFamilyPlanDetails(int ifpModelSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByItemFamilyPlanDetails(ifpModelSid);
    }

    /**
    * Returns the number of ifp detailses where ifpModelSid = &#63;.
    *
    * @param ifpModelSid the ifp model sid
    * @return the number of matching ifp detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countByItemFamilyPlanDetails(int ifpModelSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByItemFamilyPlanDetails(ifpModelSid);
    }

    /**
    * Caches the ifp details in the entity cache if it is enabled.
    *
    * @param ifpDetails the ifp details
    */
    public static void cacheResult(com.stpl.app.model.IfpDetails ifpDetails) {
        getPersistence().cacheResult(ifpDetails);
    }

    /**
    * Caches the ifp detailses in the entity cache if it is enabled.
    *
    * @param ifpDetailses the ifp detailses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.IfpDetails> ifpDetailses) {
        getPersistence().cacheResult(ifpDetailses);
    }

    /**
    * Creates a new ifp details with the primary key. Does not add the ifp details to the database.
    *
    * @param ifpDetailsSid the primary key for the new ifp details
    * @return the new ifp details
    */
    public static com.stpl.app.model.IfpDetails create(int ifpDetailsSid) {
        return getPersistence().create(ifpDetailsSid);
    }

    /**
    * Removes the ifp details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ifpDetailsSid the primary key of the ifp details
    * @return the ifp details that was removed
    * @throws com.stpl.app.NoSuchIfpDetailsException if a ifp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IfpDetails remove(int ifpDetailsSid)
        throws com.stpl.app.NoSuchIfpDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(ifpDetailsSid);
    }

    public static com.stpl.app.model.IfpDetails updateImpl(
        com.stpl.app.model.IfpDetails ifpDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(ifpDetails);
    }

    /**
    * Returns the ifp details with the primary key or throws a {@link com.stpl.app.NoSuchIfpDetailsException} if it could not be found.
    *
    * @param ifpDetailsSid the primary key of the ifp details
    * @return the ifp details
    * @throws com.stpl.app.NoSuchIfpDetailsException if a ifp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IfpDetails findByPrimaryKey(
        int ifpDetailsSid)
        throws com.stpl.app.NoSuchIfpDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(ifpDetailsSid);
    }

    /**
    * Returns the ifp details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ifpDetailsSid the primary key of the ifp details
    * @return the ifp details, or <code>null</code> if a ifp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IfpDetails fetchByPrimaryKey(
        int ifpDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(ifpDetailsSid);
    }

    /**
    * Returns all the ifp detailses.
    *
    * @return the ifp detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IfpDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the ifp detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ifp detailses
    * @param end the upper bound of the range of ifp detailses (not inclusive)
    * @return the range of ifp detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IfpDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the ifp detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ifp detailses
    * @param end the upper bound of the range of ifp detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ifp detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IfpDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ifp detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ifp detailses.
    *
    * @return the number of ifp detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static IfpDetailsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (IfpDetailsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    IfpDetailsPersistence.class.getName());

            ReferenceRegistry.registerReference(IfpDetailsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(IfpDetailsPersistence persistence) {
    }
}
