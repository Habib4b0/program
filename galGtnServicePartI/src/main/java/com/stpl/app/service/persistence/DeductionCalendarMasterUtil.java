package com.stpl.app.service.persistence;

import com.stpl.app.model.DeductionCalendarMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the deduction calendar master service. This utility wraps {@link DeductionCalendarMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see DeductionCalendarMasterPersistence
 * @see DeductionCalendarMasterPersistenceImpl
 * @generated
 */
public class DeductionCalendarMasterUtil {
    private static DeductionCalendarMasterPersistence _persistence;

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
    public static void clearCache(
        DeductionCalendarMaster deductionCalendarMaster) {
        getPersistence().clearCache(deductionCalendarMaster);
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
    public static List<DeductionCalendarMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<DeductionCalendarMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<DeductionCalendarMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static DeductionCalendarMaster update(
        DeductionCalendarMaster deductionCalendarMaster)
        throws SystemException {
        return getPersistence().update(deductionCalendarMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static DeductionCalendarMaster update(
        DeductionCalendarMaster deductionCalendarMaster,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(deductionCalendarMaster, serviceContext);
    }

    /**
    * Caches the deduction calendar master in the entity cache if it is enabled.
    *
    * @param deductionCalendarMaster the deduction calendar master
    */
    public static void cacheResult(
        com.stpl.app.model.DeductionCalendarMaster deductionCalendarMaster) {
        getPersistence().cacheResult(deductionCalendarMaster);
    }

    /**
    * Caches the deduction calendar masters in the entity cache if it is enabled.
    *
    * @param deductionCalendarMasters the deduction calendar masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.DeductionCalendarMaster> deductionCalendarMasters) {
        getPersistence().cacheResult(deductionCalendarMasters);
    }

    /**
    * Creates a new deduction calendar master with the primary key. Does not add the deduction calendar master to the database.
    *
    * @param deductionCalendarMasterSid the primary key for the new deduction calendar master
    * @return the new deduction calendar master
    */
    public static com.stpl.app.model.DeductionCalendarMaster create(
        int deductionCalendarMasterSid) {
        return getPersistence().create(deductionCalendarMasterSid);
    }

    /**
    * Removes the deduction calendar master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param deductionCalendarMasterSid the primary key of the deduction calendar master
    * @return the deduction calendar master that was removed
    * @throws com.stpl.app.NoSuchDeductionCalendarMasterException if a deduction calendar master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.DeductionCalendarMaster remove(
        int deductionCalendarMasterSid)
        throws com.stpl.app.NoSuchDeductionCalendarMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(deductionCalendarMasterSid);
    }

    public static com.stpl.app.model.DeductionCalendarMaster updateImpl(
        com.stpl.app.model.DeductionCalendarMaster deductionCalendarMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(deductionCalendarMaster);
    }

    /**
    * Returns the deduction calendar master with the primary key or throws a {@link com.stpl.app.NoSuchDeductionCalendarMasterException} if it could not be found.
    *
    * @param deductionCalendarMasterSid the primary key of the deduction calendar master
    * @return the deduction calendar master
    * @throws com.stpl.app.NoSuchDeductionCalendarMasterException if a deduction calendar master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.DeductionCalendarMaster findByPrimaryKey(
        int deductionCalendarMasterSid)
        throws com.stpl.app.NoSuchDeductionCalendarMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(deductionCalendarMasterSid);
    }

    /**
    * Returns the deduction calendar master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param deductionCalendarMasterSid the primary key of the deduction calendar master
    * @return the deduction calendar master, or <code>null</code> if a deduction calendar master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.DeductionCalendarMaster fetchByPrimaryKey(
        int deductionCalendarMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(deductionCalendarMasterSid);
    }

    /**
    * Returns all the deduction calendar masters.
    *
    * @return the deduction calendar masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.DeductionCalendarMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the deduction calendar masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of deduction calendar masters
    * @param end the upper bound of the range of deduction calendar masters (not inclusive)
    * @return the range of deduction calendar masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.DeductionCalendarMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the deduction calendar masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of deduction calendar masters
    * @param end the upper bound of the range of deduction calendar masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of deduction calendar masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.DeductionCalendarMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the deduction calendar masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of deduction calendar masters.
    *
    * @return the number of deduction calendar masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static DeductionCalendarMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (DeductionCalendarMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    DeductionCalendarMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(DeductionCalendarMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(DeductionCalendarMasterPersistence persistence) {
    }
}
