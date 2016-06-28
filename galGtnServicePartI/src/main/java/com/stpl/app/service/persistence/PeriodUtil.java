package com.stpl.app.service.persistence;

import com.stpl.app.model.Period;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the period service. This utility wraps {@link PeriodPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see PeriodPersistence
 * @see PeriodPersistenceImpl
 * @generated
 */
public class PeriodUtil {
    private static PeriodPersistence _persistence;

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
    public static void clearCache(Period period) {
        getPersistence().clearCache(period);
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
    public static List<Period> findWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<Period> findWithDynamicQuery(DynamicQuery dynamicQuery,
        int start, int end) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<Period> findWithDynamicQuery(DynamicQuery dynamicQuery,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static Period update(Period period) throws SystemException {
        return getPersistence().update(period);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static Period update(Period period, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(period, serviceContext);
    }

    /**
    * Caches the period in the entity cache if it is enabled.
    *
    * @param period the period
    */
    public static void cacheResult(com.stpl.app.model.Period period) {
        getPersistence().cacheResult(period);
    }

    /**
    * Caches the periods in the entity cache if it is enabled.
    *
    * @param periods the periods
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.Period> periods) {
        getPersistence().cacheResult(periods);
    }

    /**
    * Creates a new period with the primary key. Does not add the period to the database.
    *
    * @param periodSid the primary key for the new period
    * @return the new period
    */
    public static com.stpl.app.model.Period create(int periodSid) {
        return getPersistence().create(periodSid);
    }

    /**
    * Removes the period with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param periodSid the primary key of the period
    * @return the period that was removed
    * @throws com.stpl.app.NoSuchPeriodException if a period with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.Period remove(int periodSid)
        throws com.stpl.app.NoSuchPeriodException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(periodSid);
    }

    public static com.stpl.app.model.Period updateImpl(
        com.stpl.app.model.Period period)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(period);
    }

    /**
    * Returns the period with the primary key or throws a {@link com.stpl.app.NoSuchPeriodException} if it could not be found.
    *
    * @param periodSid the primary key of the period
    * @return the period
    * @throws com.stpl.app.NoSuchPeriodException if a period with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.Period findByPrimaryKey(int periodSid)
        throws com.stpl.app.NoSuchPeriodException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(periodSid);
    }

    /**
    * Returns the period with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param periodSid the primary key of the period
    * @return the period, or <code>null</code> if a period with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.Period fetchByPrimaryKey(int periodSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(periodSid);
    }

    /**
    * Returns all the periods.
    *
    * @return the periods
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.Period> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the periods.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of periods
    * @param end the upper bound of the range of periods (not inclusive)
    * @return the range of periods
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.Period> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the periods.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of periods
    * @param end the upper bound of the range of periods (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of periods
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.Period> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the periods from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of periods.
    *
    * @return the number of periods
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static PeriodPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (PeriodPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    PeriodPersistence.class.getName());

            ReferenceRegistry.registerReference(PeriodUtil.class, "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(PeriodPersistence persistence) {
    }
}
