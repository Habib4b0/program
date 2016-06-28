package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.IvldCustomerGtsForecast;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ivld customer gts forecast service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldCustomerGtsForecastPersistenceImpl
 * @see IvldCustomerGtsForecastUtil
 * @generated
 */
public interface IvldCustomerGtsForecastPersistence extends BasePersistence<IvldCustomerGtsForecast> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link IvldCustomerGtsForecastUtil} to access the ivld customer gts forecast persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the ivld customer gts forecast in the entity cache if it is enabled.
    *
    * @param ivldCustomerGtsForecast the ivld customer gts forecast
    */
    public void cacheResult(
        com.stpl.app.parttwo.model.IvldCustomerGtsForecast ivldCustomerGtsForecast);

    /**
    * Caches the ivld customer gts forecasts in the entity cache if it is enabled.
    *
    * @param ivldCustomerGtsForecasts the ivld customer gts forecasts
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.IvldCustomerGtsForecast> ivldCustomerGtsForecasts);

    /**
    * Creates a new ivld customer gts forecast with the primary key. Does not add the ivld customer gts forecast to the database.
    *
    * @param ivldCustomerGtsForecastSid the primary key for the new ivld customer gts forecast
    * @return the new ivld customer gts forecast
    */
    public com.stpl.app.parttwo.model.IvldCustomerGtsForecast create(
        int ivldCustomerGtsForecastSid);

    /**
    * Removes the ivld customer gts forecast with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldCustomerGtsForecastSid the primary key of the ivld customer gts forecast
    * @return the ivld customer gts forecast that was removed
    * @throws com.stpl.app.parttwo.NoSuchIvldCustomerGtsForecastException if a ivld customer gts forecast with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.IvldCustomerGtsForecast remove(
        int ivldCustomerGtsForecastSid)
        throws com.stpl.app.parttwo.NoSuchIvldCustomerGtsForecastException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.IvldCustomerGtsForecast updateImpl(
        com.stpl.app.parttwo.model.IvldCustomerGtsForecast ivldCustomerGtsForecast)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld customer gts forecast with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchIvldCustomerGtsForecastException} if it could not be found.
    *
    * @param ivldCustomerGtsForecastSid the primary key of the ivld customer gts forecast
    * @return the ivld customer gts forecast
    * @throws com.stpl.app.parttwo.NoSuchIvldCustomerGtsForecastException if a ivld customer gts forecast with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.IvldCustomerGtsForecast findByPrimaryKey(
        int ivldCustomerGtsForecastSid)
        throws com.stpl.app.parttwo.NoSuchIvldCustomerGtsForecastException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld customer gts forecast with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldCustomerGtsForecastSid the primary key of the ivld customer gts forecast
    * @return the ivld customer gts forecast, or <code>null</code> if a ivld customer gts forecast with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.IvldCustomerGtsForecast fetchByPrimaryKey(
        int ivldCustomerGtsForecastSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ivld customer gts forecasts.
    *
    * @return the ivld customer gts forecasts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.IvldCustomerGtsForecast> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ivld customer gts forecasts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld customer gts forecasts
    * @param end the upper bound of the range of ivld customer gts forecasts (not inclusive)
    * @return the range of ivld customer gts forecasts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.IvldCustomerGtsForecast> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ivld customer gts forecasts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld customer gts forecasts
    * @param end the upper bound of the range of ivld customer gts forecasts (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ivld customer gts forecasts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.IvldCustomerGtsForecast> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ivld customer gts forecasts from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ivld customer gts forecasts.
    *
    * @return the number of ivld customer gts forecasts
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
