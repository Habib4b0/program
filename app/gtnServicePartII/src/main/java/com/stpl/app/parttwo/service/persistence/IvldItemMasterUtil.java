package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.IvldItemMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ivld item master service. This utility wraps {@link IvldItemMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldItemMasterPersistence
 * @see IvldItemMasterPersistenceImpl
 * @generated
 */
public class IvldItemMasterUtil {
    private static IvldItemMasterPersistence _persistence;

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
    public static void clearCache(IvldItemMaster ivldItemMaster) {
        getPersistence().clearCache(ivldItemMaster);
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
    public static List<IvldItemMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<IvldItemMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<IvldItemMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static IvldItemMaster update(IvldItemMaster ivldItemMaster)
        throws SystemException {
        return getPersistence().update(ivldItemMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static IvldItemMaster update(IvldItemMaster ivldItemMaster,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(ivldItemMaster, serviceContext);
    }

    /**
    * Caches the ivld item master in the entity cache if it is enabled.
    *
    * @param ivldItemMaster the ivld item master
    */
    public static void cacheResult(
        com.stpl.app.parttwo.model.IvldItemMaster ivldItemMaster) {
        getPersistence().cacheResult(ivldItemMaster);
    }

    /**
    * Caches the ivld item masters in the entity cache if it is enabled.
    *
    * @param ivldItemMasters the ivld item masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.IvldItemMaster> ivldItemMasters) {
        getPersistence().cacheResult(ivldItemMasters);
    }

    /**
    * Creates a new ivld item master with the primary key. Does not add the ivld item master to the database.
    *
    * @param ivldItemMasterSid the primary key for the new ivld item master
    * @return the new ivld item master
    */
    public static com.stpl.app.parttwo.model.IvldItemMaster create(
        int ivldItemMasterSid) {
        return getPersistence().create(ivldItemMasterSid);
    }

    /**
    * Removes the ivld item master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldItemMasterSid the primary key of the ivld item master
    * @return the ivld item master that was removed
    * @throws com.stpl.app.parttwo.NoSuchIvldItemMasterException if a ivld item master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.IvldItemMaster remove(
        int ivldItemMasterSid)
        throws com.stpl.app.parttwo.NoSuchIvldItemMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(ivldItemMasterSid);
    }

    public static com.stpl.app.parttwo.model.IvldItemMaster updateImpl(
        com.stpl.app.parttwo.model.IvldItemMaster ivldItemMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(ivldItemMaster);
    }

    /**
    * Returns the ivld item master with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchIvldItemMasterException} if it could not be found.
    *
    * @param ivldItemMasterSid the primary key of the ivld item master
    * @return the ivld item master
    * @throws com.stpl.app.parttwo.NoSuchIvldItemMasterException if a ivld item master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.IvldItemMaster findByPrimaryKey(
        int ivldItemMasterSid)
        throws com.stpl.app.parttwo.NoSuchIvldItemMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(ivldItemMasterSid);
    }

    /**
    * Returns the ivld item master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldItemMasterSid the primary key of the ivld item master
    * @return the ivld item master, or <code>null</code> if a ivld item master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.IvldItemMaster fetchByPrimaryKey(
        int ivldItemMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(ivldItemMasterSid);
    }

    /**
    * Returns all the ivld item masters.
    *
    * @return the ivld item masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.IvldItemMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the ivld item masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld item masters
    * @param end the upper bound of the range of ivld item masters (not inclusive)
    * @return the range of ivld item masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.IvldItemMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the ivld item masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld item masters
    * @param end the upper bound of the range of ivld item masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ivld item masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.IvldItemMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ivld item masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ivld item masters.
    *
    * @return the number of ivld item masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static IvldItemMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (IvldItemMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.parttwo.service.ClpSerializer.getServletContextName(),
                    IvldItemMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(IvldItemMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(IvldItemMasterPersistence persistence) {
    }
}
