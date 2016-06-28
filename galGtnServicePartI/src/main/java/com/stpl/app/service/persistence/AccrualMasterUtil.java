package com.stpl.app.service.persistence;

import com.stpl.app.model.AccrualMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the accrual master service. This utility wraps {@link AccrualMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AccrualMasterPersistence
 * @see AccrualMasterPersistenceImpl
 * @generated
 */
public class AccrualMasterUtil {
    private static AccrualMasterPersistence _persistence;

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
    public static void clearCache(AccrualMaster accrualMaster) {
        getPersistence().clearCache(accrualMaster);
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
    public static List<AccrualMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<AccrualMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<AccrualMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static AccrualMaster update(AccrualMaster accrualMaster)
        throws SystemException {
        return getPersistence().update(accrualMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static AccrualMaster update(AccrualMaster accrualMaster,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(accrualMaster, serviceContext);
    }

    /**
    * Caches the accrual master in the entity cache if it is enabled.
    *
    * @param accrualMaster the accrual master
    */
    public static void cacheResult(
        com.stpl.app.model.AccrualMaster accrualMaster) {
        getPersistence().cacheResult(accrualMaster);
    }

    /**
    * Caches the accrual masters in the entity cache if it is enabled.
    *
    * @param accrualMasters the accrual masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.AccrualMaster> accrualMasters) {
        getPersistence().cacheResult(accrualMasters);
    }

    /**
    * Creates a new accrual master with the primary key. Does not add the accrual master to the database.
    *
    * @param accrualMasterSid the primary key for the new accrual master
    * @return the new accrual master
    */
    public static com.stpl.app.model.AccrualMaster create(int accrualMasterSid) {
        return getPersistence().create(accrualMasterSid);
    }

    /**
    * Removes the accrual master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param accrualMasterSid the primary key of the accrual master
    * @return the accrual master that was removed
    * @throws com.stpl.app.NoSuchAccrualMasterException if a accrual master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.AccrualMaster remove(int accrualMasterSid)
        throws com.stpl.app.NoSuchAccrualMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(accrualMasterSid);
    }

    public static com.stpl.app.model.AccrualMaster updateImpl(
        com.stpl.app.model.AccrualMaster accrualMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(accrualMaster);
    }

    /**
    * Returns the accrual master with the primary key or throws a {@link com.stpl.app.NoSuchAccrualMasterException} if it could not be found.
    *
    * @param accrualMasterSid the primary key of the accrual master
    * @return the accrual master
    * @throws com.stpl.app.NoSuchAccrualMasterException if a accrual master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.AccrualMaster findByPrimaryKey(
        int accrualMasterSid)
        throws com.stpl.app.NoSuchAccrualMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(accrualMasterSid);
    }

    /**
    * Returns the accrual master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param accrualMasterSid the primary key of the accrual master
    * @return the accrual master, or <code>null</code> if a accrual master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.AccrualMaster fetchByPrimaryKey(
        int accrualMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(accrualMasterSid);
    }

    /**
    * Returns all the accrual masters.
    *
    * @return the accrual masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.AccrualMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the accrual masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AccrualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of accrual masters
    * @param end the upper bound of the range of accrual masters (not inclusive)
    * @return the range of accrual masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.AccrualMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the accrual masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AccrualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of accrual masters
    * @param end the upper bound of the range of accrual masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of accrual masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.AccrualMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the accrual masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of accrual masters.
    *
    * @return the number of accrual masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static AccrualMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (AccrualMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    AccrualMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(AccrualMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(AccrualMasterPersistence persistence) {
    }
}
