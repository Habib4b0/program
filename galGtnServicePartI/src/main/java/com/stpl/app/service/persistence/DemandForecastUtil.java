package com.stpl.app.service.persistence;

import com.stpl.app.model.DemandForecast;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the demand forecast service. This utility wraps {@link DemandForecastPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see DemandForecastPersistence
 * @see DemandForecastPersistenceImpl
 * @generated
 */
public class DemandForecastUtil {
    private static DemandForecastPersistence _persistence;

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
    public static void clearCache(DemandForecast demandForecast) {
        getPersistence().clearCache(demandForecast);
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
    public static List<DemandForecast> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<DemandForecast> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<DemandForecast> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static DemandForecast update(DemandForecast demandForecast)
        throws SystemException {
        return getPersistence().update(demandForecast);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static DemandForecast update(DemandForecast demandForecast,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(demandForecast, serviceContext);
    }

    /**
    * Caches the demand forecast in the entity cache if it is enabled.
    *
    * @param demandForecast the demand forecast
    */
    public static void cacheResult(
        com.stpl.app.model.DemandForecast demandForecast) {
        getPersistence().cacheResult(demandForecast);
    }

    /**
    * Caches the demand forecasts in the entity cache if it is enabled.
    *
    * @param demandForecasts the demand forecasts
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.DemandForecast> demandForecasts) {
        getPersistence().cacheResult(demandForecasts);
    }

    /**
    * Creates a new demand forecast with the primary key. Does not add the demand forecast to the database.
    *
    * @param demandForecastSid the primary key for the new demand forecast
    * @return the new demand forecast
    */
    public static com.stpl.app.model.DemandForecast create(
        int demandForecastSid) {
        return getPersistence().create(demandForecastSid);
    }

    /**
    * Removes the demand forecast with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param demandForecastSid the primary key of the demand forecast
    * @return the demand forecast that was removed
    * @throws com.stpl.app.NoSuchDemandForecastException if a demand forecast with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.DemandForecast remove(
        int demandForecastSid)
        throws com.stpl.app.NoSuchDemandForecastException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(demandForecastSid);
    }

    public static com.stpl.app.model.DemandForecast updateImpl(
        com.stpl.app.model.DemandForecast demandForecast)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(demandForecast);
    }

    /**
    * Returns the demand forecast with the primary key or throws a {@link com.stpl.app.NoSuchDemandForecastException} if it could not be found.
    *
    * @param demandForecastSid the primary key of the demand forecast
    * @return the demand forecast
    * @throws com.stpl.app.NoSuchDemandForecastException if a demand forecast with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.DemandForecast findByPrimaryKey(
        int demandForecastSid)
        throws com.stpl.app.NoSuchDemandForecastException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(demandForecastSid);
    }

    /**
    * Returns the demand forecast with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param demandForecastSid the primary key of the demand forecast
    * @return the demand forecast, or <code>null</code> if a demand forecast with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.DemandForecast fetchByPrimaryKey(
        int demandForecastSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(demandForecastSid);
    }

    /**
    * Returns all the demand forecasts.
    *
    * @return the demand forecasts
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.DemandForecast> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the demand forecasts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DemandForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of demand forecasts
    * @param end the upper bound of the range of demand forecasts (not inclusive)
    * @return the range of demand forecasts
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.DemandForecast> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the demand forecasts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DemandForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of demand forecasts
    * @param end the upper bound of the range of demand forecasts (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of demand forecasts
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.DemandForecast> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the demand forecasts from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of demand forecasts.
    *
    * @return the number of demand forecasts
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static DemandForecastPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (DemandForecastPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    DemandForecastPersistence.class.getName());

            ReferenceRegistry.registerReference(DemandForecastUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(DemandForecastPersistence persistence) {
    }
}
