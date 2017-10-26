package com.stpl.app.service.persistence;

import com.stpl.app.model.FcpActuals;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the fcp actuals service. This utility wraps {@link FcpActualsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see FcpActualsPersistence
 * @see FcpActualsPersistenceImpl
 * @generated
 */
public class FcpActualsUtil {
    private static FcpActualsPersistence _persistence;

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
    public static void clearCache(FcpActuals fcpActuals) {
        getPersistence().clearCache(fcpActuals);
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
    public static List<FcpActuals> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<FcpActuals> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<FcpActuals> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static FcpActuals update(FcpActuals fcpActuals)
        throws SystemException {
        return getPersistence().update(fcpActuals);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static FcpActuals update(FcpActuals fcpActuals,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(fcpActuals, serviceContext);
    }

    /**
    * Caches the fcp actuals in the entity cache if it is enabled.
    *
    * @param fcpActuals the fcp actuals
    */
    public static void cacheResult(com.stpl.app.model.FcpActuals fcpActuals) {
        getPersistence().cacheResult(fcpActuals);
    }

    /**
    * Caches the fcp actualses in the entity cache if it is enabled.
    *
    * @param fcpActualses the fcp actualses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.FcpActuals> fcpActualses) {
        getPersistence().cacheResult(fcpActualses);
    }

    /**
    * Creates a new fcp actuals with the primary key. Does not add the fcp actuals to the database.
    *
    * @param fcpActualsPK the primary key for the new fcp actuals
    * @return the new fcp actuals
    */
    public static com.stpl.app.model.FcpActuals create(
        FcpActualsPK fcpActualsPK) {
        return getPersistence().create(fcpActualsPK);
    }

    /**
    * Removes the fcp actuals with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param fcpActualsPK the primary key of the fcp actuals
    * @return the fcp actuals that was removed
    * @throws com.stpl.app.NoSuchFcpActualsException if a fcp actuals with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FcpActuals remove(
        FcpActualsPK fcpActualsPK)
        throws com.stpl.app.NoSuchFcpActualsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(fcpActualsPK);
    }

    public static com.stpl.app.model.FcpActuals updateImpl(
        com.stpl.app.model.FcpActuals fcpActuals)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(fcpActuals);
    }

    /**
    * Returns the fcp actuals with the primary key or throws a {@link com.stpl.app.NoSuchFcpActualsException} if it could not be found.
    *
    * @param fcpActualsPK the primary key of the fcp actuals
    * @return the fcp actuals
    * @throws com.stpl.app.NoSuchFcpActualsException if a fcp actuals with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FcpActuals findByPrimaryKey(
        FcpActualsPK fcpActualsPK)
        throws com.stpl.app.NoSuchFcpActualsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(fcpActualsPK);
    }

    /**
    * Returns the fcp actuals with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param fcpActualsPK the primary key of the fcp actuals
    * @return the fcp actuals, or <code>null</code> if a fcp actuals with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FcpActuals fetchByPrimaryKey(
        FcpActualsPK fcpActualsPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(fcpActualsPK);
    }

    /**
    * Returns all the fcp actualses.
    *
    * @return the fcp actualses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.FcpActuals> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the fcp actualses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FcpActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of fcp actualses
    * @param end the upper bound of the range of fcp actualses (not inclusive)
    * @return the range of fcp actualses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.FcpActuals> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the fcp actualses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FcpActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of fcp actualses
    * @param end the upper bound of the range of fcp actualses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of fcp actualses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.FcpActuals> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the fcp actualses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of fcp actualses.
    *
    * @return the number of fcp actualses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static FcpActualsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (FcpActualsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    FcpActualsPersistence.class.getName());

            ReferenceRegistry.registerReference(FcpActualsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(FcpActualsPersistence persistence) {
    }
}
