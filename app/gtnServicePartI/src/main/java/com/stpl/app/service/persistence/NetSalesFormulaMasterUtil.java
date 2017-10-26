package com.stpl.app.service.persistence;

import com.stpl.app.model.NetSalesFormulaMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the net sales formula master service. This utility wraps {@link NetSalesFormulaMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NetSalesFormulaMasterPersistence
 * @see NetSalesFormulaMasterPersistenceImpl
 * @generated
 */
public class NetSalesFormulaMasterUtil {
    private static NetSalesFormulaMasterPersistence _persistence;

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
    public static void clearCache(NetSalesFormulaMaster netSalesFormulaMaster) {
        getPersistence().clearCache(netSalesFormulaMaster);
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
    public static List<NetSalesFormulaMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<NetSalesFormulaMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<NetSalesFormulaMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static NetSalesFormulaMaster update(
        NetSalesFormulaMaster netSalesFormulaMaster) throws SystemException {
        return getPersistence().update(netSalesFormulaMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static NetSalesFormulaMaster update(
        NetSalesFormulaMaster netSalesFormulaMaster,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(netSalesFormulaMaster, serviceContext);
    }

    /**
    * Caches the net sales formula master in the entity cache if it is enabled.
    *
    * @param netSalesFormulaMaster the net sales formula master
    */
    public static void cacheResult(
        com.stpl.app.model.NetSalesFormulaMaster netSalesFormulaMaster) {
        getPersistence().cacheResult(netSalesFormulaMaster);
    }

    /**
    * Caches the net sales formula masters in the entity cache if it is enabled.
    *
    * @param netSalesFormulaMasters the net sales formula masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.NetSalesFormulaMaster> netSalesFormulaMasters) {
        getPersistence().cacheResult(netSalesFormulaMasters);
    }

    /**
    * Creates a new net sales formula master with the primary key. Does not add the net sales formula master to the database.
    *
    * @param netSalesFormulaMasterSid the primary key for the new net sales formula master
    * @return the new net sales formula master
    */
    public static com.stpl.app.model.NetSalesFormulaMaster create(
        int netSalesFormulaMasterSid) {
        return getPersistence().create(netSalesFormulaMasterSid);
    }

    /**
    * Removes the net sales formula master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param netSalesFormulaMasterSid the primary key of the net sales formula master
    * @return the net sales formula master that was removed
    * @throws com.stpl.app.NoSuchNetSalesFormulaMasterException if a net sales formula master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NetSalesFormulaMaster remove(
        int netSalesFormulaMasterSid)
        throws com.stpl.app.NoSuchNetSalesFormulaMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(netSalesFormulaMasterSid);
    }

    public static com.stpl.app.model.NetSalesFormulaMaster updateImpl(
        com.stpl.app.model.NetSalesFormulaMaster netSalesFormulaMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(netSalesFormulaMaster);
    }

    /**
    * Returns the net sales formula master with the primary key or throws a {@link com.stpl.app.NoSuchNetSalesFormulaMasterException} if it could not be found.
    *
    * @param netSalesFormulaMasterSid the primary key of the net sales formula master
    * @return the net sales formula master
    * @throws com.stpl.app.NoSuchNetSalesFormulaMasterException if a net sales formula master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NetSalesFormulaMaster findByPrimaryKey(
        int netSalesFormulaMasterSid)
        throws com.stpl.app.NoSuchNetSalesFormulaMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(netSalesFormulaMasterSid);
    }

    /**
    * Returns the net sales formula master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param netSalesFormulaMasterSid the primary key of the net sales formula master
    * @return the net sales formula master, or <code>null</code> if a net sales formula master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NetSalesFormulaMaster fetchByPrimaryKey(
        int netSalesFormulaMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(netSalesFormulaMasterSid);
    }

    /**
    * Returns all the net sales formula masters.
    *
    * @return the net sales formula masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.NetSalesFormulaMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the net sales formula masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NetSalesFormulaMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of net sales formula masters
    * @param end the upper bound of the range of net sales formula masters (not inclusive)
    * @return the range of net sales formula masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.NetSalesFormulaMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the net sales formula masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NetSalesFormulaMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of net sales formula masters
    * @param end the upper bound of the range of net sales formula masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of net sales formula masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.NetSalesFormulaMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the net sales formula masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of net sales formula masters.
    *
    * @return the number of net sales formula masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static NetSalesFormulaMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (NetSalesFormulaMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    NetSalesFormulaMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(NetSalesFormulaMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(NetSalesFormulaMasterPersistence persistence) {
    }
}
