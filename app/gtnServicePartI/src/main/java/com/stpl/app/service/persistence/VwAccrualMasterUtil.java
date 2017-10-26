package com.stpl.app.service.persistence;

import com.stpl.app.model.VwAccrualMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the vw accrual master service. This utility wraps {@link VwAccrualMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwAccrualMasterPersistence
 * @see VwAccrualMasterPersistenceImpl
 * @generated
 */
public class VwAccrualMasterUtil {
    private static VwAccrualMasterPersistence _persistence;

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
    public static void clearCache(VwAccrualMaster vwAccrualMaster) {
        getPersistence().clearCache(vwAccrualMaster);
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
    public static List<VwAccrualMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<VwAccrualMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<VwAccrualMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static VwAccrualMaster update(VwAccrualMaster vwAccrualMaster)
        throws SystemException {
        return getPersistence().update(vwAccrualMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static VwAccrualMaster update(VwAccrualMaster vwAccrualMaster,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(vwAccrualMaster, serviceContext);
    }

    /**
    * Caches the vw accrual master in the entity cache if it is enabled.
    *
    * @param vwAccrualMaster the vw accrual master
    */
    public static void cacheResult(
        com.stpl.app.model.VwAccrualMaster vwAccrualMaster) {
        getPersistence().cacheResult(vwAccrualMaster);
    }

    /**
    * Caches the vw accrual masters in the entity cache if it is enabled.
    *
    * @param vwAccrualMasters the vw accrual masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.VwAccrualMaster> vwAccrualMasters) {
        getPersistence().cacheResult(vwAccrualMasters);
    }

    /**
    * Creates a new vw accrual master with the primary key. Does not add the vw accrual master to the database.
    *
    * @param accrualMasterSid the primary key for the new vw accrual master
    * @return the new vw accrual master
    */
    public static com.stpl.app.model.VwAccrualMaster create(
        int accrualMasterSid) {
        return getPersistence().create(accrualMasterSid);
    }

    /**
    * Removes the vw accrual master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param accrualMasterSid the primary key of the vw accrual master
    * @return the vw accrual master that was removed
    * @throws com.stpl.app.NoSuchVwAccrualMasterException if a vw accrual master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.VwAccrualMaster remove(
        int accrualMasterSid)
        throws com.stpl.app.NoSuchVwAccrualMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(accrualMasterSid);
    }

    public static com.stpl.app.model.VwAccrualMaster updateImpl(
        com.stpl.app.model.VwAccrualMaster vwAccrualMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(vwAccrualMaster);
    }

    /**
    * Returns the vw accrual master with the primary key or throws a {@link com.stpl.app.NoSuchVwAccrualMasterException} if it could not be found.
    *
    * @param accrualMasterSid the primary key of the vw accrual master
    * @return the vw accrual master
    * @throws com.stpl.app.NoSuchVwAccrualMasterException if a vw accrual master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.VwAccrualMaster findByPrimaryKey(
        int accrualMasterSid)
        throws com.stpl.app.NoSuchVwAccrualMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(accrualMasterSid);
    }

    /**
    * Returns the vw accrual master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param accrualMasterSid the primary key of the vw accrual master
    * @return the vw accrual master, or <code>null</code> if a vw accrual master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.VwAccrualMaster fetchByPrimaryKey(
        int accrualMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(accrualMasterSid);
    }

    /**
    * Returns all the vw accrual masters.
    *
    * @return the vw accrual masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.VwAccrualMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the vw accrual masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwAccrualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw accrual masters
    * @param end the upper bound of the range of vw accrual masters (not inclusive)
    * @return the range of vw accrual masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.VwAccrualMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the vw accrual masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwAccrualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw accrual masters
    * @param end the upper bound of the range of vw accrual masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of vw accrual masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.VwAccrualMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the vw accrual masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of vw accrual masters.
    *
    * @return the number of vw accrual masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static VwAccrualMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (VwAccrualMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    VwAccrualMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(VwAccrualMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(VwAccrualMasterPersistence persistence) {
    }
}
