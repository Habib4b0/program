package com.stpl.app.service.persistence;

import com.stpl.app.model.VwIvldDemandForecastActual;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the vw ivld demand forecast actual service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwIvldDemandForecastActualPersistenceImpl
 * @see VwIvldDemandForecastActualUtil
 * @generated
 */
public interface VwIvldDemandForecastActualPersistence extends BasePersistence<VwIvldDemandForecastActual> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link VwIvldDemandForecastActualUtil} to access the vw ivld demand forecast actual persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the vw ivld demand forecast actual in the entity cache if it is enabled.
    *
    * @param vwIvldDemandForecastActual the vw ivld demand forecast actual
    */
    public void cacheResult(
        com.stpl.app.model.VwIvldDemandForecastActual vwIvldDemandForecastActual);

    /**
    * Caches the vw ivld demand forecast actuals in the entity cache if it is enabled.
    *
    * @param vwIvldDemandForecastActuals the vw ivld demand forecast actuals
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.VwIvldDemandForecastActual> vwIvldDemandForecastActuals);

    /**
    * Creates a new vw ivld demand forecast actual with the primary key. Does not add the vw ivld demand forecast actual to the database.
    *
    * @param ivldDemandActualForecastSid the primary key for the new vw ivld demand forecast actual
    * @return the new vw ivld demand forecast actual
    */
    public com.stpl.app.model.VwIvldDemandForecastActual create(
        int ivldDemandActualForecastSid);

    /**
    * Removes the vw ivld demand forecast actual with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldDemandActualForecastSid the primary key of the vw ivld demand forecast actual
    * @return the vw ivld demand forecast actual that was removed
    * @throws com.stpl.app.NoSuchVwIvldDemandForecastActualException if a vw ivld demand forecast actual with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.VwIvldDemandForecastActual remove(
        int ivldDemandActualForecastSid)
        throws com.stpl.app.NoSuchVwIvldDemandForecastActualException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.VwIvldDemandForecastActual updateImpl(
        com.stpl.app.model.VwIvldDemandForecastActual vwIvldDemandForecastActual)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the vw ivld demand forecast actual with the primary key or throws a {@link com.stpl.app.NoSuchVwIvldDemandForecastActualException} if it could not be found.
    *
    * @param ivldDemandActualForecastSid the primary key of the vw ivld demand forecast actual
    * @return the vw ivld demand forecast actual
    * @throws com.stpl.app.NoSuchVwIvldDemandForecastActualException if a vw ivld demand forecast actual with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.VwIvldDemandForecastActual findByPrimaryKey(
        int ivldDemandActualForecastSid)
        throws com.stpl.app.NoSuchVwIvldDemandForecastActualException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the vw ivld demand forecast actual with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldDemandActualForecastSid the primary key of the vw ivld demand forecast actual
    * @return the vw ivld demand forecast actual, or <code>null</code> if a vw ivld demand forecast actual with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.VwIvldDemandForecastActual fetchByPrimaryKey(
        int ivldDemandActualForecastSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the vw ivld demand forecast actuals.
    *
    * @return the vw ivld demand forecast actuals
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.VwIvldDemandForecastActual> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the vw ivld demand forecast actuals.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwIvldDemandForecastActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw ivld demand forecast actuals
    * @param end the upper bound of the range of vw ivld demand forecast actuals (not inclusive)
    * @return the range of vw ivld demand forecast actuals
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.VwIvldDemandForecastActual> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the vw ivld demand forecast actuals.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwIvldDemandForecastActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw ivld demand forecast actuals
    * @param end the upper bound of the range of vw ivld demand forecast actuals (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of vw ivld demand forecast actuals
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.VwIvldDemandForecastActual> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the vw ivld demand forecast actuals from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of vw ivld demand forecast actuals.
    *
    * @return the number of vw ivld demand forecast actuals
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
