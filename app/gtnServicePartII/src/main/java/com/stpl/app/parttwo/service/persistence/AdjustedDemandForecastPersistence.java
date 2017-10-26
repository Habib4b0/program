package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.AdjustedDemandForecast;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the adjusted demand forecast service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AdjustedDemandForecastPersistenceImpl
 * @see AdjustedDemandForecastUtil
 * @generated
 */
public interface AdjustedDemandForecastPersistence extends BasePersistence<AdjustedDemandForecast> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link AdjustedDemandForecastUtil} to access the adjusted demand forecast persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the adjusted demand forecast in the entity cache if it is enabled.
    *
    * @param adjustedDemandForecast the adjusted demand forecast
    */
    public void cacheResult(
        com.stpl.app.parttwo.model.AdjustedDemandForecast adjustedDemandForecast);

    /**
    * Caches the adjusted demand forecasts in the entity cache if it is enabled.
    *
    * @param adjustedDemandForecasts the adjusted demand forecasts
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.AdjustedDemandForecast> adjustedDemandForecasts);

    /**
    * Creates a new adjusted demand forecast with the primary key. Does not add the adjusted demand forecast to the database.
    *
    * @param adjustedDemandForecastSid the primary key for the new adjusted demand forecast
    * @return the new adjusted demand forecast
    */
    public com.stpl.app.parttwo.model.AdjustedDemandForecast create(
        int adjustedDemandForecastSid);

    /**
    * Removes the adjusted demand forecast with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param adjustedDemandForecastSid the primary key of the adjusted demand forecast
    * @return the adjusted demand forecast that was removed
    * @throws com.stpl.app.parttwo.NoSuchAdjustedDemandForecastException if a adjusted demand forecast with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.AdjustedDemandForecast remove(
        int adjustedDemandForecastSid)
        throws com.stpl.app.parttwo.NoSuchAdjustedDemandForecastException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.AdjustedDemandForecast updateImpl(
        com.stpl.app.parttwo.model.AdjustedDemandForecast adjustedDemandForecast)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the adjusted demand forecast with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchAdjustedDemandForecastException} if it could not be found.
    *
    * @param adjustedDemandForecastSid the primary key of the adjusted demand forecast
    * @return the adjusted demand forecast
    * @throws com.stpl.app.parttwo.NoSuchAdjustedDemandForecastException if a adjusted demand forecast with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.AdjustedDemandForecast findByPrimaryKey(
        int adjustedDemandForecastSid)
        throws com.stpl.app.parttwo.NoSuchAdjustedDemandForecastException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the adjusted demand forecast with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param adjustedDemandForecastSid the primary key of the adjusted demand forecast
    * @return the adjusted demand forecast, or <code>null</code> if a adjusted demand forecast with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.AdjustedDemandForecast fetchByPrimaryKey(
        int adjustedDemandForecastSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the adjusted demand forecasts.
    *
    * @return the adjusted demand forecasts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.AdjustedDemandForecast> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the adjusted demand forecasts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AdjustedDemandForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of adjusted demand forecasts
    * @param end the upper bound of the range of adjusted demand forecasts (not inclusive)
    * @return the range of adjusted demand forecasts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.AdjustedDemandForecast> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the adjusted demand forecasts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AdjustedDemandForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of adjusted demand forecasts
    * @param end the upper bound of the range of adjusted demand forecasts (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of adjusted demand forecasts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.AdjustedDemandForecast> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the adjusted demand forecasts from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of adjusted demand forecasts.
    *
    * @return the number of adjusted demand forecasts
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
