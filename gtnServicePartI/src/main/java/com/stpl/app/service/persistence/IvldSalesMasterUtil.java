package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldSalesMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ivld sales master service. This utility wraps {@link IvldSalesMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldSalesMasterPersistence
 * @see IvldSalesMasterPersistenceImpl
 * @generated
 */
public class IvldSalesMasterUtil {
    private static IvldSalesMasterPersistence _persistence;

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
    public static void clearCache(IvldSalesMaster ivldSalesMaster) {
        getPersistence().clearCache(ivldSalesMaster);
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
    public static List<IvldSalesMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<IvldSalesMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<IvldSalesMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static IvldSalesMaster update(IvldSalesMaster ivldSalesMaster)
        throws SystemException {
        return getPersistence().update(ivldSalesMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static IvldSalesMaster update(IvldSalesMaster ivldSalesMaster,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(ivldSalesMaster, serviceContext);
    }

    /**
    * Caches the ivld sales master in the entity cache if it is enabled.
    *
    * @param ivldSalesMaster the ivld sales master
    */
    public static void cacheResult(
        com.stpl.app.model.IvldSalesMaster ivldSalesMaster) {
        getPersistence().cacheResult(ivldSalesMaster);
    }

    /**
    * Caches the ivld sales masters in the entity cache if it is enabled.
    *
    * @param ivldSalesMasters the ivld sales masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.IvldSalesMaster> ivldSalesMasters) {
        getPersistence().cacheResult(ivldSalesMasters);
    }

    /**
    * Creates a new ivld sales master with the primary key. Does not add the ivld sales master to the database.
    *
    * @param ivldSalesMasterSid the primary key for the new ivld sales master
    * @return the new ivld sales master
    */
    public static com.stpl.app.model.IvldSalesMaster create(
        int ivldSalesMasterSid) {
        return getPersistence().create(ivldSalesMasterSid);
    }

    /**
    * Removes the ivld sales master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldSalesMasterSid the primary key of the ivld sales master
    * @return the ivld sales master that was removed
    * @throws com.stpl.app.NoSuchIvldSalesMasterException if a ivld sales master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldSalesMaster remove(
        int ivldSalesMasterSid)
        throws com.stpl.app.NoSuchIvldSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(ivldSalesMasterSid);
    }

    public static com.stpl.app.model.IvldSalesMaster updateImpl(
        com.stpl.app.model.IvldSalesMaster ivldSalesMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(ivldSalesMaster);
    }

    /**
    * Returns the ivld sales master with the primary key or throws a {@link com.stpl.app.NoSuchIvldSalesMasterException} if it could not be found.
    *
    * @param ivldSalesMasterSid the primary key of the ivld sales master
    * @return the ivld sales master
    * @throws com.stpl.app.NoSuchIvldSalesMasterException if a ivld sales master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldSalesMaster findByPrimaryKey(
        int ivldSalesMasterSid)
        throws com.stpl.app.NoSuchIvldSalesMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(ivldSalesMasterSid);
    }

    /**
    * Returns the ivld sales master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldSalesMasterSid the primary key of the ivld sales master
    * @return the ivld sales master, or <code>null</code> if a ivld sales master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldSalesMaster fetchByPrimaryKey(
        int ivldSalesMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(ivldSalesMasterSid);
    }

    /**
    * Returns all the ivld sales masters.
    *
    * @return the ivld sales masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldSalesMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the ivld sales masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldSalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld sales masters
    * @param end the upper bound of the range of ivld sales masters (not inclusive)
    * @return the range of ivld sales masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldSalesMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the ivld sales masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldSalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld sales masters
    * @param end the upper bound of the range of ivld sales masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ivld sales masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldSalesMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ivld sales masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ivld sales masters.
    *
    * @return the number of ivld sales masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static IvldSalesMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (IvldSalesMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    IvldSalesMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(IvldSalesMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(IvldSalesMasterPersistence persistence) {
    }
}
