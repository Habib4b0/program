package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldDemandForecast;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ivld demand forecast service. This utility wraps {@link IvldDemandForecastPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldDemandForecastPersistence
 * @see IvldDemandForecastPersistenceImpl
 * @generated
 */
public class IvldDemandForecastUtil {
    private static IvldDemandForecastPersistence _persistence;

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
    public static void clearCache(IvldDemandForecast ivldDemandForecast) {
        getPersistence().clearCache(ivldDemandForecast);
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
    public static List<IvldDemandForecast> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<IvldDemandForecast> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<IvldDemandForecast> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static IvldDemandForecast update(
        IvldDemandForecast ivldDemandForecast) throws SystemException {
        return getPersistence().update(ivldDemandForecast);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static IvldDemandForecast update(
        IvldDemandForecast ivldDemandForecast, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(ivldDemandForecast, serviceContext);
    }

    /**
    * Caches the ivld demand forecast in the entity cache if it is enabled.
    *
    * @param ivldDemandForecast the ivld demand forecast
    */
    public static void cacheResult(
        com.stpl.app.model.IvldDemandForecast ivldDemandForecast) {
        getPersistence().cacheResult(ivldDemandForecast);
    }

    /**
    * Caches the ivld demand forecasts in the entity cache if it is enabled.
    *
    * @param ivldDemandForecasts the ivld demand forecasts
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.IvldDemandForecast> ivldDemandForecasts) {
        getPersistence().cacheResult(ivldDemandForecasts);
    }

    /**
    * Creates a new ivld demand forecast with the primary key. Does not add the ivld demand forecast to the database.
    *
    * @param ivldDemandForecastSid the primary key for the new ivld demand forecast
    * @return the new ivld demand forecast
    */
    public static com.stpl.app.model.IvldDemandForecast create(
        int ivldDemandForecastSid) {
        return getPersistence().create(ivldDemandForecastSid);
    }

    /**
    * Removes the ivld demand forecast with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldDemandForecastSid the primary key of the ivld demand forecast
    * @return the ivld demand forecast that was removed
    * @throws com.stpl.app.NoSuchIvldDemandForecastException if a ivld demand forecast with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldDemandForecast remove(
        int ivldDemandForecastSid)
        throws com.stpl.app.NoSuchIvldDemandForecastException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(ivldDemandForecastSid);
    }

    public static com.stpl.app.model.IvldDemandForecast updateImpl(
        com.stpl.app.model.IvldDemandForecast ivldDemandForecast)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(ivldDemandForecast);
    }

    /**
    * Returns the ivld demand forecast with the primary key or throws a {@link com.stpl.app.NoSuchIvldDemandForecastException} if it could not be found.
    *
    * @param ivldDemandForecastSid the primary key of the ivld demand forecast
    * @return the ivld demand forecast
    * @throws com.stpl.app.NoSuchIvldDemandForecastException if a ivld demand forecast with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldDemandForecast findByPrimaryKey(
        int ivldDemandForecastSid)
        throws com.stpl.app.NoSuchIvldDemandForecastException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(ivldDemandForecastSid);
    }

    /**
    * Returns the ivld demand forecast with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldDemandForecastSid the primary key of the ivld demand forecast
    * @return the ivld demand forecast, or <code>null</code> if a ivld demand forecast with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.IvldDemandForecast fetchByPrimaryKey(
        int ivldDemandForecastSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(ivldDemandForecastSid);
    }

    /**
    * Returns all the ivld demand forecasts.
    *
    * @return the ivld demand forecasts
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldDemandForecast> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the ivld demand forecasts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldDemandForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld demand forecasts
    * @param end the upper bound of the range of ivld demand forecasts (not inclusive)
    * @return the range of ivld demand forecasts
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldDemandForecast> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the ivld demand forecasts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldDemandForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld demand forecasts
    * @param end the upper bound of the range of ivld demand forecasts (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ivld demand forecasts
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.IvldDemandForecast> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ivld demand forecasts from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ivld demand forecasts.
    *
    * @return the number of ivld demand forecasts
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static IvldDemandForecastPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (IvldDemandForecastPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    IvldDemandForecastPersistence.class.getName());

            ReferenceRegistry.registerReference(IvldDemandForecastUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(IvldDemandForecastPersistence persistence) {
    }
}
