package com.stpl.app.service.persistence;

import com.stpl.app.model.RebatePlanTier;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the rebate plan tier service. This utility wraps {@link RebatePlanTierPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RebatePlanTierPersistence
 * @see RebatePlanTierPersistenceImpl
 * @generated
 */
public class RebatePlanTierUtil {
    private static RebatePlanTierPersistence _persistence;

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
    public static void clearCache(RebatePlanTier rebatePlanTier) {
        getPersistence().clearCache(rebatePlanTier);
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
    public static List<RebatePlanTier> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<RebatePlanTier> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<RebatePlanTier> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static RebatePlanTier update(RebatePlanTier rebatePlanTier)
        throws SystemException {
        return getPersistence().update(rebatePlanTier);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static RebatePlanTier update(RebatePlanTier rebatePlanTier,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(rebatePlanTier, serviceContext);
    }

    /**
    * Caches the rebate plan tier in the entity cache if it is enabled.
    *
    * @param rebatePlanTier the rebate plan tier
    */
    public static void cacheResult(
        com.stpl.app.model.RebatePlanTier rebatePlanTier) {
        getPersistence().cacheResult(rebatePlanTier);
    }

    /**
    * Caches the rebate plan tiers in the entity cache if it is enabled.
    *
    * @param rebatePlanTiers the rebate plan tiers
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.RebatePlanTier> rebatePlanTiers) {
        getPersistence().cacheResult(rebatePlanTiers);
    }

    /**
    * Creates a new rebate plan tier with the primary key. Does not add the rebate plan tier to the database.
    *
    * @param rebatePlanTierSid the primary key for the new rebate plan tier
    * @return the new rebate plan tier
    */
    public static com.stpl.app.model.RebatePlanTier create(
        int rebatePlanTierSid) {
        return getPersistence().create(rebatePlanTierSid);
    }

    /**
    * Removes the rebate plan tier with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param rebatePlanTierSid the primary key of the rebate plan tier
    * @return the rebate plan tier that was removed
    * @throws com.stpl.app.NoSuchRebatePlanTierException if a rebate plan tier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RebatePlanTier remove(
        int rebatePlanTierSid)
        throws com.stpl.app.NoSuchRebatePlanTierException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(rebatePlanTierSid);
    }

    public static com.stpl.app.model.RebatePlanTier updateImpl(
        com.stpl.app.model.RebatePlanTier rebatePlanTier)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(rebatePlanTier);
    }

    /**
    * Returns the rebate plan tier with the primary key or throws a {@link com.stpl.app.NoSuchRebatePlanTierException} if it could not be found.
    *
    * @param rebatePlanTierSid the primary key of the rebate plan tier
    * @return the rebate plan tier
    * @throws com.stpl.app.NoSuchRebatePlanTierException if a rebate plan tier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RebatePlanTier findByPrimaryKey(
        int rebatePlanTierSid)
        throws com.stpl.app.NoSuchRebatePlanTierException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(rebatePlanTierSid);
    }

    /**
    * Returns the rebate plan tier with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param rebatePlanTierSid the primary key of the rebate plan tier
    * @return the rebate plan tier, or <code>null</code> if a rebate plan tier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RebatePlanTier fetchByPrimaryKey(
        int rebatePlanTierSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(rebatePlanTierSid);
    }

    /**
    * Returns all the rebate plan tiers.
    *
    * @return the rebate plan tiers
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RebatePlanTier> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

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
    public static java.util.List<com.stpl.app.model.RebatePlanTier> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

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
    public static java.util.List<com.stpl.app.model.RebatePlanTier> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the rebate plan tiers from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of rebate plan tiers.
    *
    * @return the number of rebate plan tiers
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static RebatePlanTierPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (RebatePlanTierPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    RebatePlanTierPersistence.class.getName());

            ReferenceRegistry.registerReference(RebatePlanTierUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(RebatePlanTierPersistence persistence) {
    }
}
