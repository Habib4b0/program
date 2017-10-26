package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.VwItemMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the vw item master service. This utility wraps {@link VwItemMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwItemMasterPersistence
 * @see VwItemMasterPersistenceImpl
 * @generated
 */
public class VwItemMasterUtil {
    private static VwItemMasterPersistence _persistence;

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
    public static void clearCache(VwItemMaster vwItemMaster) {
        getPersistence().clearCache(vwItemMaster);
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
    public static List<VwItemMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<VwItemMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<VwItemMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static VwItemMaster update(VwItemMaster vwItemMaster)
        throws SystemException {
        return getPersistence().update(vwItemMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static VwItemMaster update(VwItemMaster vwItemMaster,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(vwItemMaster, serviceContext);
    }

    /**
    * Caches the vw item master in the entity cache if it is enabled.
    *
    * @param vwItemMaster the vw item master
    */
    public static void cacheResult(
        com.stpl.app.parttwo.model.VwItemMaster vwItemMaster) {
        getPersistence().cacheResult(vwItemMaster);
    }

    /**
    * Caches the vw item masters in the entity cache if it is enabled.
    *
    * @param vwItemMasters the vw item masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.VwItemMaster> vwItemMasters) {
        getPersistence().cacheResult(vwItemMasters);
    }

    /**
    * Creates a new vw item master with the primary key. Does not add the vw item master to the database.
    *
    * @param itemMasterSid the primary key for the new vw item master
    * @return the new vw item master
    */
    public static com.stpl.app.parttwo.model.VwItemMaster create(
        int itemMasterSid) {
        return getPersistence().create(itemMasterSid);
    }

    /**
    * Removes the vw item master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param itemMasterSid the primary key of the vw item master
    * @return the vw item master that was removed
    * @throws com.stpl.app.parttwo.NoSuchVwItemMasterException if a vw item master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.VwItemMaster remove(
        int itemMasterSid)
        throws com.stpl.app.parttwo.NoSuchVwItemMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(itemMasterSid);
    }

    public static com.stpl.app.parttwo.model.VwItemMaster updateImpl(
        com.stpl.app.parttwo.model.VwItemMaster vwItemMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(vwItemMaster);
    }

    /**
    * Returns the vw item master with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchVwItemMasterException} if it could not be found.
    *
    * @param itemMasterSid the primary key of the vw item master
    * @return the vw item master
    * @throws com.stpl.app.parttwo.NoSuchVwItemMasterException if a vw item master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.VwItemMaster findByPrimaryKey(
        int itemMasterSid)
        throws com.stpl.app.parttwo.NoSuchVwItemMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(itemMasterSid);
    }

    /**
    * Returns the vw item master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param itemMasterSid the primary key of the vw item master
    * @return the vw item master, or <code>null</code> if a vw item master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.VwItemMaster fetchByPrimaryKey(
        int itemMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(itemMasterSid);
    }

    /**
    * Returns all the vw item masters.
    *
    * @return the vw item masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.VwItemMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the vw item masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw item masters
    * @param end the upper bound of the range of vw item masters (not inclusive)
    * @return the range of vw item masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.VwItemMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the vw item masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw item masters
    * @param end the upper bound of the range of vw item masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of vw item masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.VwItemMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the vw item masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of vw item masters.
    *
    * @return the number of vw item masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static VwItemMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (VwItemMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.parttwo.service.ClpSerializer.getServletContextName(),
                    VwItemMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(VwItemMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(VwItemMasterPersistence persistence) {
    }
}
