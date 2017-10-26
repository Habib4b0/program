package com.stpl.app.service.persistence;

import com.stpl.app.model.CfpDetails;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the cfp details service. This utility wraps {@link CfpDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CfpDetailsPersistence
 * @see CfpDetailsPersistenceImpl
 * @generated
 */
public class CfpDetailsUtil {
    private static CfpDetailsPersistence _persistence;

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
    public static void clearCache(CfpDetails cfpDetails) {
        getPersistence().clearCache(cfpDetails);
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
    public static List<CfpDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<CfpDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<CfpDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static CfpDetails update(CfpDetails cfpDetails)
        throws SystemException {
        return getPersistence().update(cfpDetails);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static CfpDetails update(CfpDetails cfpDetails,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(cfpDetails, serviceContext);
    }

    /**
    * Returns all the cfp detailses where cfpModelSid = &#63;.
    *
    * @param cfpModelSid the cfp model sid
    * @return the matching cfp detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CfpDetails> findByCfpModelSid(
        int cfpModelSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByCfpModelSid(cfpModelSid);
    }

    /**
    * Returns a range of all the cfp detailses where cfpModelSid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param cfpModelSid the cfp model sid
    * @param start the lower bound of the range of cfp detailses
    * @param end the upper bound of the range of cfp detailses (not inclusive)
    * @return the range of matching cfp detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CfpDetails> findByCfpModelSid(
        int cfpModelSid, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByCfpModelSid(cfpModelSid, start, end);
    }

    /**
    * Returns an ordered range of all the cfp detailses where cfpModelSid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param cfpModelSid the cfp model sid
    * @param start the lower bound of the range of cfp detailses
    * @param end the upper bound of the range of cfp detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching cfp detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CfpDetails> findByCfpModelSid(
        int cfpModelSid, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCfpModelSid(cfpModelSid, start, end, orderByComparator);
    }

    /**
    * Returns the first cfp details in the ordered set where cfpModelSid = &#63;.
    *
    * @param cfpModelSid the cfp model sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cfp details
    * @throws com.stpl.app.NoSuchCfpDetailsException if a matching cfp details could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpDetails findByCfpModelSid_First(
        int cfpModelSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCfpModelSid_First(cfpModelSid, orderByComparator);
    }

    /**
    * Returns the first cfp details in the ordered set where cfpModelSid = &#63;.
    *
    * @param cfpModelSid the cfp model sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching cfp details, or <code>null</code> if a matching cfp details could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpDetails fetchByCfpModelSid_First(
        int cfpModelSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByCfpModelSid_First(cfpModelSid, orderByComparator);
    }

    /**
    * Returns the last cfp details in the ordered set where cfpModelSid = &#63;.
    *
    * @param cfpModelSid the cfp model sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cfp details
    * @throws com.stpl.app.NoSuchCfpDetailsException if a matching cfp details could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpDetails findByCfpModelSid_Last(
        int cfpModelSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCfpModelSid_Last(cfpModelSid, orderByComparator);
    }

    /**
    * Returns the last cfp details in the ordered set where cfpModelSid = &#63;.
    *
    * @param cfpModelSid the cfp model sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching cfp details, or <code>null</code> if a matching cfp details could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpDetails fetchByCfpModelSid_Last(
        int cfpModelSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByCfpModelSid_Last(cfpModelSid, orderByComparator);
    }

    /**
    * Returns the cfp detailses before and after the current cfp details in the ordered set where cfpModelSid = &#63;.
    *
    * @param cfpDetailsSid the primary key of the current cfp details
    * @param cfpModelSid the cfp model sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next cfp details
    * @throws com.stpl.app.NoSuchCfpDetailsException if a cfp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpDetails[] findByCfpModelSid_PrevAndNext(
        int cfpDetailsSid, int cfpModelSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchCfpDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCfpModelSid_PrevAndNext(cfpDetailsSid, cfpModelSid,
            orderByComparator);
    }

    /**
    * Removes all the cfp detailses where cfpModelSid = &#63; from the database.
    *
    * @param cfpModelSid the cfp model sid
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCfpModelSid(int cfpModelSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeByCfpModelSid(cfpModelSid);
    }

    /**
    * Returns the number of cfp detailses where cfpModelSid = &#63;.
    *
    * @param cfpModelSid the cfp model sid
    * @return the number of matching cfp detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countByCfpModelSid(int cfpModelSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countByCfpModelSid(cfpModelSid);
    }

    /**
    * Caches the cfp details in the entity cache if it is enabled.
    *
    * @param cfpDetails the cfp details
    */
    public static void cacheResult(com.stpl.app.model.CfpDetails cfpDetails) {
        getPersistence().cacheResult(cfpDetails);
    }

    /**
    * Caches the cfp detailses in the entity cache if it is enabled.
    *
    * @param cfpDetailses the cfp detailses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.CfpDetails> cfpDetailses) {
        getPersistence().cacheResult(cfpDetailses);
    }

    /**
    * Creates a new cfp details with the primary key. Does not add the cfp details to the database.
    *
    * @param cfpDetailsSid the primary key for the new cfp details
    * @return the new cfp details
    */
    public static com.stpl.app.model.CfpDetails create(int cfpDetailsSid) {
        return getPersistence().create(cfpDetailsSid);
    }

    /**
    * Removes the cfp details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param cfpDetailsSid the primary key of the cfp details
    * @return the cfp details that was removed
    * @throws com.stpl.app.NoSuchCfpDetailsException if a cfp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpDetails remove(int cfpDetailsSid)
        throws com.stpl.app.NoSuchCfpDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(cfpDetailsSid);
    }

    public static com.stpl.app.model.CfpDetails updateImpl(
        com.stpl.app.model.CfpDetails cfpDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(cfpDetails);
    }

    /**
    * Returns the cfp details with the primary key or throws a {@link com.stpl.app.NoSuchCfpDetailsException} if it could not be found.
    *
    * @param cfpDetailsSid the primary key of the cfp details
    * @return the cfp details
    * @throws com.stpl.app.NoSuchCfpDetailsException if a cfp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpDetails findByPrimaryKey(
        int cfpDetailsSid)
        throws com.stpl.app.NoSuchCfpDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(cfpDetailsSid);
    }

    /**
    * Returns the cfp details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param cfpDetailsSid the primary key of the cfp details
    * @return the cfp details, or <code>null</code> if a cfp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CfpDetails fetchByPrimaryKey(
        int cfpDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(cfpDetailsSid);
    }

    /**
    * Returns all the cfp detailses.
    *
    * @return the cfp detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CfpDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the cfp detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cfp detailses
    * @param end the upper bound of the range of cfp detailses (not inclusive)
    * @return the range of cfp detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CfpDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the cfp detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cfp detailses
    * @param end the upper bound of the range of cfp detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of cfp detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CfpDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the cfp detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of cfp detailses.
    *
    * @return the number of cfp detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static CfpDetailsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (CfpDetailsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    CfpDetailsPersistence.class.getName());

            ReferenceRegistry.registerReference(CfpDetailsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(CfpDetailsPersistence persistence) {
    }
}
