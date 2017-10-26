package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldLotMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ivld lot master service. This utility wraps {@link IvldLotMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldLotMasterPersistence
 * @see IvldLotMasterPersistenceImpl
 * @generated
 */
public class IvldLotMasterUtil {
    private static IvldLotMasterPersistence _persistence;

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
    public static void clearCache(IvldLotMaster ivldLotMaster) {
        getPersistence().clearCache(ivldLotMaster);
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
    public static List<IvldLotMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<IvldLotMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<IvldLotMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static IvldLotMaster update(IvldLotMaster ivldLotMaster)
        throws SystemException {
        return getPersistence().update(ivldLotMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static IvldLotMaster update(IvldLotMaster ivldLotMaster,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(ivldLotMaster, serviceContext);
    }

    /**
    * Caches the ivld lot master in the entity cache if it is enabled.
    *
    * @param ivldLotMaster the ivld lot master
    */
    public static void cacheResult(
        com.stpl.app.model.IvldLotMaster ivldLotMaster) {
        getPersistence().cacheResult(ivldLotMaster);
    }

    /**
    * Caches the ivld lot masters in the entity cache if it is enabled.
    *
    * @param ivldLotMasters the ivld lot masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.IvldLotMaster> ivldLotMasters) {
        getPersistence().cacheResult(ivldLotMasters);
    }

    /**
    * Creates a new ivld lot master with the primary key. Does not add the ivld lot master to the database.
    *
    * @param ivldLotMasterSid the primary key for the new ivld lot master
    * @return the new ivld lot master
    */
    public static com.stpl.app.model.IvldLotMaster create(int ivldLotMasterSid) {
        return getPersistence().create(ivldLotMasterSid);
    }

    /**
    * Removes the ivld lot master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldLotMasterSid the primary key of the ivld lot master
    * @return the ivld lot master that was removed
    * @throws com.stpl.app.NoSuchIvldLotMasterException if a ivld lot master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldLotMaster remove(int ivldLotMasterSid)
        throws com.stpl.app.NoSuchIvldLotMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(ivldLotMasterSid);
    }

    public static com.stpl.app.model.IvldLotMaster updateImpl(
        com.stpl.app.model.IvldLotMaster ivldLotMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(ivldLotMaster);
    }

    /**
    * Returns the ivld lot master with the primary key or throws a {@link com.stpl.app.NoSuchIvldLotMasterException} if it could not be found.
    *
    * @param ivldLotMasterSid the primary key of the ivld lot master
    * @return the ivld lot master
    * @throws com.stpl.app.NoSuchIvldLotMasterException if a ivld lot master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldLotMaster findByPrimaryKey(
        int ivldLotMasterSid)
        throws com.stpl.app.NoSuchIvldLotMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(ivldLotMasterSid);
    }

    /**
    * Returns the ivld lot master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldLotMasterSid the primary key of the ivld lot master
    * @return the ivld lot master, or <code>null</code> if a ivld lot master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldLotMaster fetchByPrimaryKey(
        int ivldLotMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(ivldLotMasterSid);
    }

    /**
    * Returns all the ivld lot masters.
    *
    * @return the ivld lot masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldLotMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the ivld lot masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldLotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld lot masters
    * @param end the upper bound of the range of ivld lot masters (not inclusive)
    * @return the range of ivld lot masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldLotMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the ivld lot masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldLotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld lot masters
    * @param end the upper bound of the range of ivld lot masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ivld lot masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldLotMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ivld lot masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ivld lot masters.
    *
    * @return the number of ivld lot masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static IvldLotMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (IvldLotMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    IvldLotMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(IvldLotMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(IvldLotMasterPersistence persistence) {
    }
}
