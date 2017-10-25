package com.stpl.app.service.persistence;

import com.stpl.app.model.ForecastingViewMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the forecasting view master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ForecastingViewMasterPersistenceImpl
 * @see ForecastingViewMasterUtil
 * @generated
 */
public interface ForecastingViewMasterPersistence extends BasePersistence<ForecastingViewMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ForecastingViewMasterUtil} to access the forecasting view master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the forecasting view master in the entity cache if it is enabled.
    *
    * @param forecastingViewMaster the forecasting view master
    */
    public void cacheResult(
        com.stpl.app.model.ForecastingViewMaster forecastingViewMaster);

    /**
    * Caches the forecasting view masters in the entity cache if it is enabled.
    *
    * @param forecastingViewMasters the forecasting view masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.ForecastingViewMaster> forecastingViewMasters);

    /**
    * Creates a new forecasting view master with the primary key. Does not add the forecasting view master to the database.
    *
    * @param viewId the primary key for the new forecasting view master
    * @return the new forecasting view master
    */
    public com.stpl.app.model.ForecastingViewMaster create(int viewId);

    /**
    * Removes the forecasting view master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param viewId the primary key of the forecasting view master
    * @return the forecasting view master that was removed
    * @throws com.stpl.app.NoSuchForecastingViewMasterException if a forecasting view master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingViewMaster remove(int viewId)
        throws com.stpl.app.NoSuchForecastingViewMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.ForecastingViewMaster updateImpl(
        com.stpl.app.model.ForecastingViewMaster forecastingViewMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the forecasting view master with the primary key or throws a {@link com.stpl.app.NoSuchForecastingViewMasterException} if it could not be found.
    *
    * @param viewId the primary key of the forecasting view master
    * @return the forecasting view master
    * @throws com.stpl.app.NoSuchForecastingViewMasterException if a forecasting view master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingViewMaster findByPrimaryKey(int viewId)
        throws com.stpl.app.NoSuchForecastingViewMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the forecasting view master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param viewId the primary key of the forecasting view master
    * @return the forecasting view master, or <code>null</code> if a forecasting view master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingViewMaster fetchByPrimaryKey(
        int viewId) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the forecasting view masters.
    *
    * @return the forecasting view masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ForecastingViewMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the forecasting view masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of forecasting view masters
    * @param end the upper bound of the range of forecasting view masters (not inclusive)
    * @return the range of forecasting view masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ForecastingViewMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the forecasting view masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of forecasting view masters
    * @param end the upper bound of the range of forecasting view masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of forecasting view masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ForecastingViewMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the forecasting view masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of forecasting view masters.
    *
    * @return the number of forecasting view masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
