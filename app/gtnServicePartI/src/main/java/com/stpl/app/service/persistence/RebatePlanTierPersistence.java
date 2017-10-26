package com.stpl.app.service.persistence;

import com.stpl.app.model.RebatePlanTier;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the rebate plan tier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RebatePlanTierPersistenceImpl
 * @see RebatePlanTierUtil
 * @generated
 */
public interface RebatePlanTierPersistence extends BasePersistence<RebatePlanTier> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link RebatePlanTierUtil} to access the rebate plan tier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the rebate plan tier in the entity cache if it is enabled.
    *
    * @param rebatePlanTier the rebate plan tier
    */
    public void cacheResult(com.stpl.app.model.RebatePlanTier rebatePlanTier);

    /**
    * Caches the rebate plan tiers in the entity cache if it is enabled.
    *
    * @param rebatePlanTiers the rebate plan tiers
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.RebatePlanTier> rebatePlanTiers);

    /**
    * Creates a new rebate plan tier with the primary key. Does not add the rebate plan tier to the database.
    *
    * @param rebatePlanTierSid the primary key for the new rebate plan tier
    * @return the new rebate plan tier
    */
    public com.stpl.app.model.RebatePlanTier create(int rebatePlanTierSid);

    /**
    * Removes the rebate plan tier with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param rebatePlanTierSid the primary key of the rebate plan tier
    * @return the rebate plan tier that was removed
    * @throws com.stpl.app.NoSuchRebatePlanTierException if a rebate plan tier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RebatePlanTier remove(int rebatePlanTierSid)
        throws com.stpl.app.NoSuchRebatePlanTierException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.RebatePlanTier updateImpl(
        com.stpl.app.model.RebatePlanTier rebatePlanTier)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the rebate plan tier with the primary key or throws a {@link com.stpl.app.NoSuchRebatePlanTierException} if it could not be found.
    *
    * @param rebatePlanTierSid the primary key of the rebate plan tier
    * @return the rebate plan tier
    * @throws com.stpl.app.NoSuchRebatePlanTierException if a rebate plan tier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RebatePlanTier findByPrimaryKey(
        int rebatePlanTierSid)
        throws com.stpl.app.NoSuchRebatePlanTierException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the rebate plan tier with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param rebatePlanTierSid the primary key of the rebate plan tier
    * @return the rebate plan tier, or <code>null</code> if a rebate plan tier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RebatePlanTier fetchByPrimaryKey(
        int rebatePlanTierSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the rebate plan tiers.
    *
    * @return the rebate plan tiers
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RebatePlanTier> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the rebate plan tiers.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanTierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of rebate plan tiers
    * @param end the upper bound of the range of rebate plan tiers (not inclusive)
    * @return the range of rebate plan tiers
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RebatePlanTier> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the rebate plan tiers.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebatePlanTierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of rebate plan tiers
    * @param end the upper bound of the range of rebate plan tiers (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of rebate plan tiers
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RebatePlanTier> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the rebate plan tiers from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of rebate plan tiers.
    *
    * @return the number of rebate plan tiers
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
