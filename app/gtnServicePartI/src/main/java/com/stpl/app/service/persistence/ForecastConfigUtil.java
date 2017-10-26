package com.stpl.app.service.persistence;

import com.stpl.app.model.ForecastConfig;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the forecast config service. This utility wraps {@link ForecastConfigPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ForecastConfigPersistence
 * @see ForecastConfigPersistenceImpl
 * @generated
 */
public class ForecastConfigUtil {
    private static ForecastConfigPersistence _persistence;

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
    public static void clearCache(ForecastConfig forecastConfig) {
        getPersistence().clearCache(forecastConfig);
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
    public static List<ForecastConfig> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ForecastConfig> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ForecastConfig> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static ForecastConfig update(ForecastConfig forecastConfig)
        throws SystemException {
        return getPersistence().update(forecastConfig);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static ForecastConfig update(ForecastConfig forecastConfig,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(forecastConfig, serviceContext);
    }

    /**
    * Caches the forecast config in the entity cache if it is enabled.
    *
    * @param forecastConfig the forecast config
    */
    public static void cacheResult(
        com.stpl.app.model.ForecastConfig forecastConfig) {
        getPersistence().cacheResult(forecastConfig);
    }

    /**
    * Caches the forecast configs in the entity cache if it is enabled.
    *
    * @param forecastConfigs the forecast configs
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.ForecastConfig> forecastConfigs) {
        getPersistence().cacheResult(forecastConfigs);
    }

    /**
    * Creates a new forecast config with the primary key. Does not add the forecast config to the database.
    *
    * @param forecastConfigSid the primary key for the new forecast config
    * @return the new forecast config
    */
    public static com.stpl.app.model.ForecastConfig create(
        int forecastConfigSid) {
        return getPersistence().create(forecastConfigSid);
    }

    /**
    * Removes the forecast config with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param forecastConfigSid the primary key of the forecast config
    * @return the forecast config that was removed
    * @throws com.stpl.app.NoSuchForecastConfigException if a forecast config with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ForecastConfig remove(
        int forecastConfigSid)
        throws com.stpl.app.NoSuchForecastConfigException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(forecastConfigSid);
    }

    public static com.stpl.app.model.ForecastConfig updateImpl(
        com.stpl.app.model.ForecastConfig forecastConfig)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(forecastConfig);
    }

    /**
    * Returns the forecast config with the primary key or throws a {@link com.stpl.app.NoSuchForecastConfigException} if it could not be found.
    *
    * @param forecastConfigSid the primary key of the forecast config
    * @return the forecast config
    * @throws com.stpl.app.NoSuchForecastConfigException if a forecast config with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ForecastConfig findByPrimaryKey(
        int forecastConfigSid)
        throws com.stpl.app.NoSuchForecastConfigException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(forecastConfigSid);
    }

    /**
    * Returns the forecast config with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param forecastConfigSid the primary key of the forecast config
    * @return the forecast config, or <code>null</code> if a forecast config with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ForecastConfig fetchByPrimaryKey(
        int forecastConfigSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(forecastConfigSid);
    }

    /**
    * Returns all the forecast configs.
    *
    * @return the forecast configs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ForecastConfig> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the forecast configs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of forecast configs
    * @param end the upper bound of the range of forecast configs (not inclusive)
    * @return the range of forecast configs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ForecastConfig> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the forecast configs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of forecast configs
    * @param end the upper bound of the range of forecast configs (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of forecast configs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ForecastConfig> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the forecast configs from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of forecast configs.
    *
    * @return the number of forecast configs
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ForecastConfigPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ForecastConfigPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ForecastConfigPersistence.class.getName());

            ReferenceRegistry.registerReference(ForecastConfigUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ForecastConfigPersistence persistence) {
    }
}
