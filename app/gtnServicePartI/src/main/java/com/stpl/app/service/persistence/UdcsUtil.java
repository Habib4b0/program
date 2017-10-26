package com.stpl.app.service.persistence;

import com.stpl.app.model.Udcs;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the udcs service. This utility wraps {@link UdcsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see UdcsPersistence
 * @see UdcsPersistenceImpl
 * @generated
 */
public class UdcsUtil {
    private static UdcsPersistence _persistence;

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
    public static void clearCache(Udcs udcs) {
        getPersistence().clearCache(udcs);
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
    public static List<Udcs> findWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<Udcs> findWithDynamicQuery(DynamicQuery dynamicQuery,
        int start, int end) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<Udcs> findWithDynamicQuery(DynamicQuery dynamicQuery,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static Udcs update(Udcs udcs) throws SystemException {
        return getPersistence().update(udcs);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static Udcs update(Udcs udcs, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(udcs, serviceContext);
    }

    /**
    * Caches the udcs in the entity cache if it is enabled.
    *
    * @param udcs the udcs
    */
    public static void cacheResult(com.stpl.app.model.Udcs udcs) {
        getPersistence().cacheResult(udcs);
    }

    /**
    * Caches the udcses in the entity cache if it is enabled.
    *
    * @param udcses the udcses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.Udcs> udcses) {
        getPersistence().cacheResult(udcses);
    }

    /**
    * Creates a new udcs with the primary key. Does not add the udcs to the database.
    *
    * @param udcsSid the primary key for the new udcs
    * @return the new udcs
    */
    public static com.stpl.app.model.Udcs create(int udcsSid) {
        return getPersistence().create(udcsSid);
    }

    /**
    * Removes the udcs with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param udcsSid the primary key of the udcs
    * @return the udcs that was removed
    * @throws com.stpl.app.NoSuchUdcsException if a udcs with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.Udcs remove(int udcsSid)
        throws com.stpl.app.NoSuchUdcsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(udcsSid);
    }

    public static com.stpl.app.model.Udcs updateImpl(
        com.stpl.app.model.Udcs udcs)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(udcs);
    }

    /**
    * Returns the udcs with the primary key or throws a {@link com.stpl.app.NoSuchUdcsException} if it could not be found.
    *
    * @param udcsSid the primary key of the udcs
    * @return the udcs
    * @throws com.stpl.app.NoSuchUdcsException if a udcs with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.Udcs findByPrimaryKey(int udcsSid)
        throws com.stpl.app.NoSuchUdcsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(udcsSid);
    }

    /**
    * Returns the udcs with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param udcsSid the primary key of the udcs
    * @return the udcs, or <code>null</code> if a udcs with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.Udcs fetchByPrimaryKey(int udcsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(udcsSid);
    }

    /**
    * Returns all the udcses.
    *
    * @return the udcses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.Udcs> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the udcses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.UdcsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of udcses
    * @param end the upper bound of the range of udcses (not inclusive)
    * @return the range of udcses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.Udcs> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the udcses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.UdcsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of udcses
    * @param end the upper bound of the range of udcses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of udcses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.Udcs> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the udcses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of udcses.
    *
    * @return the number of udcses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static UdcsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (UdcsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    UdcsPersistence.class.getName());

            ReferenceRegistry.registerReference(UdcsUtil.class, "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(UdcsPersistence persistence) {
    }
}
