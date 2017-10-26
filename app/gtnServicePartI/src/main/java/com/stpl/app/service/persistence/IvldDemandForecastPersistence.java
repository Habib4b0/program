package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldDemandForecast;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ivld demand forecast service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldDemandForecastPersistenceImpl
 * @see IvldDemandForecastUtil
 * @generated
 */
public interface IvldDemandForecastPersistence extends BasePersistence<IvldDemandForecast> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link IvldDemandForecastUtil} to access the ivld demand forecast persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the ivld demand forecast in the entity cache if it is enabled.
    *
    * @param ivldDemandForecast the ivld demand forecast
    */
    public void cacheResult(
        com.stpl.app.model.IvldDemandForecast ivldDemandForecast);

    /**
    * Caches the ivld demand forecasts in the entity cache if it is enabled.
    *
    * @param ivldDemandForecasts the ivld demand forecasts
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.IvldDemandForecast> ivldDemandForecasts);

    /**
    * Creates a new ivld demand forecast with the primary key. Does not add the ivld demand forecast to the database.
    *
    * @param ivldDemandForecastSid the primary key for the new ivld demand forecast
    * @return the new ivld demand forecast
    */
    public com.stpl.app.model.IvldDemandForecast create(
        int ivldDemandForecastSid);

    /**
    * Removes the ivld demand forecast with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldDemandForecastSid the primary key of the ivld demand forecast
    * @return the ivld demand forecast that was removed
    * @throws com.stpl.app.NoSuchIvldDemandForecastException if a ivld demand forecast with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IvldDemandForecast remove(
        int ivldDemandForecastSid)
        throws com.stpl.app.NoSuchIvldDemandForecastException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.IvldDemandForecast updateImpl(
        com.stpl.app.model.IvldDemandForecast ivldDemandForecast)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld demand forecast with the primary key or throws a {@link com.stpl.app.NoSuchIvldDemandForecastException} if it could not be found.
    *
    * @param ivldDemandForecastSid the primary key of the ivld demand forecast
    * @return the ivld demand forecast
    * @throws com.stpl.app.NoSuchIvldDemandForecastException if a ivld demand forecast with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IvldDemandForecast findByPrimaryKey(
        int ivldDemandForecastSid)
        throws com.stpl.app.NoSuchIvldDemandForecastException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld demand forecast with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldDemandForecastSid the primary key of the ivld demand forecast
    * @return the ivld demand forecast, or <code>null</code> if a ivld demand forecast with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IvldDemandForecast fetchByPrimaryKey(
        int ivldDemandForecastSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ivld demand forecasts.
    *
    * @return the ivld demand forecasts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IvldDemandForecast> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

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
    public java.util.List<com.stpl.app.model.IvldDemandForecast> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

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
    public java.util.List<com.stpl.app.model.IvldDemandForecast> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ivld demand forecasts from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ivld demand forecasts.
    *
    * @return the number of ivld demand forecasts
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
