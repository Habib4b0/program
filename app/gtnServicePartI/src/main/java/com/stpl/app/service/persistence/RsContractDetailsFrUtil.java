package com.stpl.app.service.persistence;

import com.stpl.app.model.RsContractDetailsFr;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the rs contract details fr service. This utility wraps {@link RsContractDetailsFrPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RsContractDetailsFrPersistence
 * @see RsContractDetailsFrPersistenceImpl
 * @generated
 */
public class RsContractDetailsFrUtil {
    private static RsContractDetailsFrPersistence _persistence;

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
    public static void clearCache(RsContractDetailsFr rsContractDetailsFr) {
        getPersistence().clearCache(rsContractDetailsFr);
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
    public static List<RsContractDetailsFr> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<RsContractDetailsFr> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<RsContractDetailsFr> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static RsContractDetailsFr update(
        RsContractDetailsFr rsContractDetailsFr) throws SystemException {
        return getPersistence().update(rsContractDetailsFr);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static RsContractDetailsFr update(
        RsContractDetailsFr rsContractDetailsFr, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(rsContractDetailsFr, serviceContext);
    }

    /**
    * Caches the rs contract details fr in the entity cache if it is enabled.
    *
    * @param rsContractDetailsFr the rs contract details fr
    */
    public static void cacheResult(
        com.stpl.app.model.RsContractDetailsFr rsContractDetailsFr) {
        getPersistence().cacheResult(rsContractDetailsFr);
    }

    /**
    * Caches the rs contract details frs in the entity cache if it is enabled.
    *
    * @param rsContractDetailsFrs the rs contract details frs
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.RsContractDetailsFr> rsContractDetailsFrs) {
        getPersistence().cacheResult(rsContractDetailsFrs);
    }

    /**
    * Creates a new rs contract details fr with the primary key. Does not add the rs contract details fr to the database.
    *
    * @param rsContractDetailsFrSid the primary key for the new rs contract details fr
    * @return the new rs contract details fr
    */
    public static com.stpl.app.model.RsContractDetailsFr create(
        int rsContractDetailsFrSid) {
        return getPersistence().create(rsContractDetailsFrSid);
    }

    /**
    * Removes the rs contract details fr with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param rsContractDetailsFrSid the primary key of the rs contract details fr
    * @return the rs contract details fr that was removed
    * @throws com.stpl.app.NoSuchRsContractDetailsFrException if a rs contract details fr with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsContractDetailsFr remove(
        int rsContractDetailsFrSid)
        throws com.stpl.app.NoSuchRsContractDetailsFrException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(rsContractDetailsFrSid);
    }

    public static com.stpl.app.model.RsContractDetailsFr updateImpl(
        com.stpl.app.model.RsContractDetailsFr rsContractDetailsFr)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(rsContractDetailsFr);
    }

    /**
    * Returns the rs contract details fr with the primary key or throws a {@link com.stpl.app.NoSuchRsContractDetailsFrException} if it could not be found.
    *
    * @param rsContractDetailsFrSid the primary key of the rs contract details fr
    * @return the rs contract details fr
    * @throws com.stpl.app.NoSuchRsContractDetailsFrException if a rs contract details fr with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsContractDetailsFr findByPrimaryKey(
        int rsContractDetailsFrSid)
        throws com.stpl.app.NoSuchRsContractDetailsFrException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(rsContractDetailsFrSid);
    }

    /**
    * Returns the rs contract details fr with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param rsContractDetailsFrSid the primary key of the rs contract details fr
    * @return the rs contract details fr, or <code>null</code> if a rs contract details fr with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RsContractDetailsFr fetchByPrimaryKey(
        int rsContractDetailsFrSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(rsContractDetailsFrSid);
    }

    /**
    * Returns all the rs contract details frs.
    *
    * @return the rs contract details frs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RsContractDetailsFr> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the rs contract details frs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsContractDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of rs contract details frs
    * @param end the upper bound of the range of rs contract details frs (not inclusive)
    * @return the range of rs contract details frs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RsContractDetailsFr> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the rs contract details frs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsContractDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of rs contract details frs
    * @param end the upper bound of the range of rs contract details frs (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of rs contract details frs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RsContractDetailsFr> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the rs contract details frs from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of rs contract details frs.
    *
    * @return the number of rs contract details frs
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static RsContractDetailsFrPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (RsContractDetailsFrPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    RsContractDetailsFrPersistence.class.getName());

            ReferenceRegistry.registerReference(RsContractDetailsFrUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(RsContractDetailsFrPersistence persistence) {
    }
}
