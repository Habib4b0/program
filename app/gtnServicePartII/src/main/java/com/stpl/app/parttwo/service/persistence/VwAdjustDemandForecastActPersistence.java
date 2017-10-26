package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.VwAdjustDemandForecastAct;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the vw adjust demand forecast act service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwAdjustDemandForecastActPersistenceImpl
 * @see VwAdjustDemandForecastActUtil
 * @generated
 */
public interface VwAdjustDemandForecastActPersistence extends BasePersistence<VwAdjustDemandForecastAct> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link VwAdjustDemandForecastActUtil} to access the vw adjust demand forecast act persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the vw adjust demand forecast act in the entity cache if it is enabled.
    *
    * @param vwAdjustDemandForecastAct the vw adjust demand forecast act
    */
    public void cacheResult(
        com.stpl.app.parttwo.model.VwAdjustDemandForecastAct vwAdjustDemandForecastAct);

    /**
    * Caches the vw adjust demand forecast acts in the entity cache if it is enabled.
    *
    * @param vwAdjustDemandForecastActs the vw adjust demand forecast acts
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.VwAdjustDemandForecastAct> vwAdjustDemandForecastActs);

    /**
    * Creates a new vw adjust demand forecast act with the primary key. Does not add the vw adjust demand forecast act to the database.
    *
    * @param adjustedDemandForecastId the primary key for the new vw adjust demand forecast act
    * @return the new vw adjust demand forecast act
    */
    public com.stpl.app.parttwo.model.VwAdjustDemandForecastAct create(
        int adjustedDemandForecastId);

    /**
    * Removes the vw adjust demand forecast act with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param adjustedDemandForecastId the primary key of the vw adjust demand forecast act
    * @return the vw adjust demand forecast act that was removed
    * @throws com.stpl.app.parttwo.NoSuchVwAdjustDemandForecastActException if a vw adjust demand forecast act with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.VwAdjustDemandForecastAct remove(
        int adjustedDemandForecastId)
        throws com.stpl.app.parttwo.NoSuchVwAdjustDemandForecastActException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.VwAdjustDemandForecastAct updateImpl(
        com.stpl.app.parttwo.model.VwAdjustDemandForecastAct vwAdjustDemandForecastAct)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the vw adjust demand forecast act with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchVwAdjustDemandForecastActException} if it could not be found.
    *
    * @param adjustedDemandForecastId the primary key of the vw adjust demand forecast act
    * @return the vw adjust demand forecast act
    * @throws com.stpl.app.parttwo.NoSuchVwAdjustDemandForecastActException if a vw adjust demand forecast act with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.VwAdjustDemandForecastAct findByPrimaryKey(
        int adjustedDemandForecastId)
        throws com.stpl.app.parttwo.NoSuchVwAdjustDemandForecastActException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the vw adjust demand forecast act with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param adjustedDemandForecastId the primary key of the vw adjust demand forecast act
    * @return the vw adjust demand forecast act, or <code>null</code> if a vw adjust demand forecast act with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.VwAdjustDemandForecastAct fetchByPrimaryKey(
        int adjustedDemandForecastId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the vw adjust demand forecast acts.
    *
    * @return the vw adjust demand forecast acts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.VwAdjustDemandForecastAct> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the vw adjust demand forecast acts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwAdjustDemandForecastActModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw adjust demand forecast acts
    * @param end the upper bound of the range of vw adjust demand forecast acts (not inclusive)
    * @return the range of vw adjust demand forecast acts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.VwAdjustDemandForecastAct> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the vw adjust demand forecast acts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwAdjustDemandForecastActModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw adjust demand forecast acts
    * @param end the upper bound of the range of vw adjust demand forecast acts (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of vw adjust demand forecast acts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.VwAdjustDemandForecastAct> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the vw adjust demand forecast acts from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of vw adjust demand forecast acts.
    *
    * @return the number of vw adjust demand forecast acts
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
