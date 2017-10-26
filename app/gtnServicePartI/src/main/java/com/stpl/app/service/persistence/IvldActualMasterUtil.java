package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldActualMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ivld actual master service. This utility wraps {@link IvldActualMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldActualMasterPersistence
 * @see IvldActualMasterPersistenceImpl
 * @generated
 */
public class IvldActualMasterUtil {
    private static IvldActualMasterPersistence _persistence;

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
    public static void clearCache(IvldActualMaster ivldActualMaster) {
        getPersistence().clearCache(ivldActualMaster);
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
    public static List<IvldActualMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<IvldActualMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<IvldActualMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static IvldActualMaster update(IvldActualMaster ivldActualMaster)
        throws SystemException {
        return getPersistence().update(ivldActualMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static IvldActualMaster update(IvldActualMaster ivldActualMaster,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(ivldActualMaster, serviceContext);
    }

    /**
    * Caches the ivld actual master in the entity cache if it is enabled.
    *
    * @param ivldActualMaster the ivld actual master
    */
    public static void cacheResult(
        com.stpl.app.model.IvldActualMaster ivldActualMaster) {
        getPersistence().cacheResult(ivldActualMaster);
    }

    /**
    * Caches the ivld actual masters in the entity cache if it is enabled.
    *
    * @param ivldActualMasters the ivld actual masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.IvldActualMaster> ivldActualMasters) {
        getPersistence().cacheResult(ivldActualMasters);
    }

    /**
    * Creates a new ivld actual master with the primary key. Does not add the ivld actual master to the database.
    *
    * @param ivldActualMasterSid the primary key for the new ivld actual master
    * @return the new ivld actual master
    */
    public static com.stpl.app.model.IvldActualMaster create(
        int ivldActualMasterSid) {
        return getPersistence().create(ivldActualMasterSid);
    }

    /**
    * Removes the ivld actual master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldActualMasterSid the primary key of the ivld actual master
    * @return the ivld actual master that was removed
    * @throws com.stpl.app.NoSuchIvldActualMasterException if a ivld actual master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldActualMaster remove(
        int ivldActualMasterSid)
        throws com.stpl.app.NoSuchIvldActualMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(ivldActualMasterSid);
    }

    public static com.stpl.app.model.IvldActualMaster updateImpl(
        com.stpl.app.model.IvldActualMaster ivldActualMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(ivldActualMaster);
    }

    /**
    * Returns the ivld actual master with the primary key or throws a {@link com.stpl.app.NoSuchIvldActualMasterException} if it could not be found.
    *
    * @param ivldActualMasterSid the primary key of the ivld actual master
    * @return the ivld actual master
    * @throws com.stpl.app.NoSuchIvldActualMasterException if a ivld actual master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldActualMaster findByPrimaryKey(
        int ivldActualMasterSid)
        throws com.stpl.app.NoSuchIvldActualMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(ivldActualMasterSid);
    }

    /**
    * Returns the ivld actual master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldActualMasterSid the primary key of the ivld actual master
    * @return the ivld actual master, or <code>null</code> if a ivld actual master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldActualMaster fetchByPrimaryKey(
        int ivldActualMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(ivldActualMasterSid);
    }

    /**
    * Returns all the ivld actual masters.
    *
    * @return the ivld actual masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldActualMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the ivld actual masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldActualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld actual masters
    * @param end the upper bound of the range of ivld actual masters (not inclusive)
    * @return the range of ivld actual masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldActualMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the ivld actual masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldActualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld actual masters
    * @param end the upper bound of the range of ivld actual masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ivld actual masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldActualMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ivld actual masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ivld actual masters.
    *
    * @return the number of ivld actual masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static IvldActualMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (IvldActualMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    IvldActualMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(IvldActualMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(IvldActualMasterPersistence persistence) {
    }
}
