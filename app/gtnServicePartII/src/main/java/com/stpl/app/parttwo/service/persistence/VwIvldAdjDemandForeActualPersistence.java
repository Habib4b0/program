package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the vw ivld adj demand fore actual service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwIvldAdjDemandForeActualPersistenceImpl
 * @see VwIvldAdjDemandForeActualUtil
 * @generated
 */
public interface VwIvldAdjDemandForeActualPersistence extends BasePersistence<VwIvldAdjDemandForeActual> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link VwIvldAdjDemandForeActualUtil} to access the vw ivld adj demand fore actual persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the vw ivld adj demand fore actual in the entity cache if it is enabled.
    *
    * @param vwIvldAdjDemandForeActual the vw ivld adj demand fore actual
    */
    public void cacheResult(
        com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual);

    /**
    * Caches the vw ivld adj demand fore actuals in the entity cache if it is enabled.
    *
    * @param vwIvldAdjDemandForeActuals the vw ivld adj demand fore actuals
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual> vwIvldAdjDemandForeActuals);

    /**
    * Creates a new vw ivld adj demand fore actual with the primary key. Does not add the vw ivld adj demand fore actual to the database.
    *
    * @param ivldAdjustedDemandForecastSid the primary key for the new vw ivld adj demand fore actual
    * @return the new vw ivld adj demand fore actual
    */
    public com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual create(
        int ivldAdjustedDemandForecastSid);

    /**
    * Removes the vw ivld adj demand fore actual with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldAdjustedDemandForecastSid the primary key of the vw ivld adj demand fore actual
    * @return the vw ivld adj demand fore actual that was removed
    * @throws com.stpl.app.parttwo.NoSuchVwIvldAdjDemandForeActualException if a vw ivld adj demand fore actual with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual remove(
        int ivldAdjustedDemandForecastSid)
        throws com.stpl.app.parttwo.NoSuchVwIvldAdjDemandForeActualException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual updateImpl(
        com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the vw ivld adj demand fore actual with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchVwIvldAdjDemandForeActualException} if it could not be found.
    *
    * @param ivldAdjustedDemandForecastSid the primary key of the vw ivld adj demand fore actual
    * @return the vw ivld adj demand fore actual
    * @throws com.stpl.app.parttwo.NoSuchVwIvldAdjDemandForeActualException if a vw ivld adj demand fore actual with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual findByPrimaryKey(
        int ivldAdjustedDemandForecastSid)
        throws com.stpl.app.parttwo.NoSuchVwIvldAdjDemandForeActualException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the vw ivld adj demand fore actual with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldAdjustedDemandForecastSid the primary key of the vw ivld adj demand fore actual
    * @return the vw ivld adj demand fore actual, or <code>null</code> if a vw ivld adj demand fore actual with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual fetchByPrimaryKey(
        int ivldAdjustedDemandForecastSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the vw ivld adj demand fore actuals.
    *
    * @return the vw ivld adj demand fore actuals
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the vw ivld adj demand fore actuals.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwIvldAdjDemandForeActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw ivld adj demand fore actuals
    * @param end the upper bound of the range of vw ivld adj demand fore actuals (not inclusive)
    * @return the range of vw ivld adj demand fore actuals
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the vw ivld adj demand fore actuals.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwIvldAdjDemandForeActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw ivld adj demand fore actuals
    * @param end the upper bound of the range of vw ivld adj demand fore actuals (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of vw ivld adj demand fore actuals
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the vw ivld adj demand fore actuals from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of vw ivld adj demand fore actuals.
    *
    * @return the number of vw ivld adj demand fore actuals
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
