package com.stpl.app.service.persistence;

import com.stpl.app.model.RsDetailsFr;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the rs details fr service. This utility wraps {@link RsDetailsFrPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RsDetailsFrPersistence
 * @see RsDetailsFrPersistenceImpl
 * @generated
 */
public class RsDetailsFrUtil {
    private static RsDetailsFrPersistence _persistence;

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
    public static void clearCache(RsDetailsFr rsDetailsFr) {
        getPersistence().clearCache(rsDetailsFr);
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
    public static List<RsDetailsFr> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<RsDetailsFr> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<RsDetailsFr> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static RsDetailsFr update(RsDetailsFr rsDetailsFr)
        throws SystemException {
        return getPersistence().update(rsDetailsFr);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static RsDetailsFr update(RsDetailsFr rsDetailsFr,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(rsDetailsFr, serviceContext);
    }

    /**
    * Caches the rs details fr in the entity cache if it is enabled.
    *
    * @param rsDetailsFr the rs details fr
    */
    public static void cacheResult(com.stpl.app.model.RsDetailsFr rsDetailsFr) {
        getPersistence().cacheResult(rsDetailsFr);
    }

    /**
    * Caches the rs details frs in the entity cache if it is enabled.
    *
    * @param rsDetailsFrs the rs details frs
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.RsDetailsFr> rsDetailsFrs) {
        getPersistence().cacheResult(rsDetailsFrs);
    }

    /**
    * Creates a new rs details fr with the primary key. Does not add the rs details fr to the database.
    *
    * @param rsDetailsFrSid the primary key for the new rs details fr
    * @return the new rs details fr
    */
    public static com.stpl.app.model.RsDetailsFr create(int rsDetailsFrSid) {
        return getPersistence().create(rsDetailsFrSid);
    }

    /**
    * Removes the rs details fr with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param rsDetailsFrSid the primary key of the rs details fr
    * @return the rs details fr that was removed
    * @throws com.stpl.app.NoSuchRsDetailsFrException if a rs details fr with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsDetailsFr remove(int rsDetailsFrSid)
        throws com.stpl.app.NoSuchRsDetailsFrException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(rsDetailsFrSid);
    }

    public static com.stpl.app.model.RsDetailsFr updateImpl(
        com.stpl.app.model.RsDetailsFr rsDetailsFr)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(rsDetailsFr);
    }

    /**
    * Returns the rs details fr with the primary key or throws a {@link com.stpl.app.NoSuchRsDetailsFrException} if it could not be found.
    *
    * @param rsDetailsFrSid the primary key of the rs details fr
    * @return the rs details fr
    * @throws com.stpl.app.NoSuchRsDetailsFrException if a rs details fr with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsDetailsFr findByPrimaryKey(
        int rsDetailsFrSid)
        throws com.stpl.app.NoSuchRsDetailsFrException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(rsDetailsFrSid);
    }

    /**
    * Returns the rs details fr with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param rsDetailsFrSid the primary key of the rs details fr
    * @return the rs details fr, or <code>null</code> if a rs details fr with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsDetailsFr fetchByPrimaryKey(
        int rsDetailsFrSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(rsDetailsFrSid);
    }

    /**
    * Returns all the rs details frs.
    *
    * @return the rs details frs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RsDetailsFr> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the rs details frs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of rs details frs
    * @param end the upper bound of the range of rs details frs (not inclusive)
    * @return the range of rs details frs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RsDetailsFr> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the rs details frs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of rs details frs
    * @param end the upper bound of the range of rs details frs (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of rs details frs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RsDetailsFr> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the rs details frs from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of rs details frs.
    *
    * @return the number of rs details frs
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static RsDetailsFrPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (RsDetailsFrPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    RsDetailsFrPersistence.class.getName());

            ReferenceRegistry.registerReference(RsDetailsFrUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(RsDetailsFrPersistence persistence) {
    }
}
