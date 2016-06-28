package com.stpl.app.service.persistence;

import com.stpl.app.model.ForecastConfig;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the forecast config service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ForecastConfigPersistenceImpl
 * @see ForecastConfigUtil
 * @generated
 */
public interface ForecastConfigPersistence extends BasePersistence<ForecastConfig> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ForecastConfigUtil} to access the forecast config persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the forecast config in the entity cache if it is enabled.
    *
    * @param forecastConfig the forecast config
    */
    public void cacheResult(com.stpl.app.model.ForecastConfig forecastConfig);

    /**
    * Caches the forecast configs in the entity cache if it is enabled.
    *
    * @param forecastConfigs the forecast configs
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.ForecastConfig> forecastConfigs);

    /**
    * Creates a new forecast config with the primary key. Does not add the forecast config to the database.
    *
    * @param forecastConfigSid the primary key for the new forecast config
    * @return the new forecast config
    */
    public com.stpl.app.model.ForecastConfig create(int forecastConfigSid);

    /**
    * Removes the forecast config with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param forecastConfigSid the primary key of the forecast config
    * @return the forecast config that was removed
    * @throws com.stpl.app.NoSuchForecastConfigException if a forecast config with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastConfig remove(int forecastConfigSid)
        throws com.stpl.app.NoSuchForecastConfigException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.ForecastConfig updateImpl(
        com.stpl.app.model.ForecastConfig forecastConfig)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the forecast config with the primary key or throws a {@link com.stpl.app.NoSuchForecastConfigException} if it could not be found.
    *
    * @param forecastConfigSid the primary key of the forecast config
    * @return the forecast config
    * @throws com.stpl.app.NoSuchForecastConfigException if a forecast config with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastConfig findByPrimaryKey(
        int forecastConfigSid)
        throws com.stpl.app.NoSuchForecastConfigException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the forecast config with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param forecastConfigSid the primary key of the forecast config
    * @return the forecast config, or <code>null</code> if a forecast config with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastConfig fetchByPrimaryKey(
        int forecastConfigSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the forecast configs.
    *
    * @return the forecast configs
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ForecastConfig> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

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
    public java.util.List<com.stpl.app.model.ForecastConfig> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

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
    public java.util.List<com.stpl.app.model.ForecastConfig> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the forecast configs from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of forecast configs.
    *
    * @return the number of forecast configs
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
