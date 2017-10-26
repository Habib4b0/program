package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.VwAdjustDemandForecastAct;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the vw adjust demand forecast act service. This utility wraps {@link VwAdjustDemandForecastActPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwAdjustDemandForecastActPersistence
 * @see VwAdjustDemandForecastActPersistenceImpl
 * @generated
 */
public class VwAdjustDemandForecastActUtil {
    private static VwAdjustDemandForecastActPersistence _persistence;

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
    public static void clearCache(
        VwAdjustDemandForecastAct vwAdjustDemandForecastAct) {
        getPersistence().clearCache(vwAdjustDemandForecastAct);
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
    public static List<VwAdjustDemandForecastAct> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<VwAdjustDemandForecastAct> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<VwAdjustDemandForecastAct> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static VwAdjustDemandForecastAct update(
        VwAdjustDemandForecastAct vwAdjustDemandForecastAct)
        throws SystemException {
        return getPersistence().update(vwAdjustDemandForecastAct);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static VwAdjustDemandForecastAct update(
        VwAdjustDemandForecastAct vwAdjustDemandForecastAct,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(vwAdjustDemandForecastAct, serviceContext);
    }

    /**
    * Caches the vw adjust demand forecast act in the entity cache if it is enabled.
    *
    * @param vwAdjustDemandForecastAct the vw adjust demand forecast act
    */
    public static void cacheResult(
        com.stpl.app.parttwo.model.VwAdjustDemandForecastAct vwAdjustDemandForecastAct) {
        getPersistence().cacheResult(vwAdjustDemandForecastAct);
    }

    /**
    * Caches the vw adjust demand forecast acts in the entity cache if it is enabled.
    *
    * @param vwAdjustDemandForecastActs the vw adjust demand forecast acts
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.VwAdjustDemandForecastAct> vwAdjustDemandForecastActs) {
        getPersistence().cacheResult(vwAdjustDemandForecastActs);
    }

    /**
    * Creates a new vw adjust demand forecast act with the primary key. Does not add the vw adjust demand forecast act to the database.
    *
    * @param adjustedDemandForecastId the primary key for the new vw adjust demand forecast act
    * @return the new vw adjust demand forecast act
    */
    public static com.stpl.app.parttwo.model.VwAdjustDemandForecastAct create(
        int adjustedDemandForecastId) {
        return getPersistence().create(adjustedDemandForecastId);
    }

    /**
    * Removes the vw adjust demand forecast act with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param adjustedDemandForecastId the primary key of the vw adjust demand forecast act
    * @return the vw adjust demand forecast act that was removed
    * @throws com.stpl.app.parttwo.NoSuchVwAdjustDemandForecastActException if a vw adjust demand forecast act with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.VwAdjustDemandForecastAct remove(
        int adjustedDemandForecastId)
        throws com.stpl.app.parttwo.NoSuchVwAdjustDemandForecastActException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(adjustedDemandForecastId);
    }

    public static com.stpl.app.parttwo.model.VwAdjustDemandForecastAct updateImpl(
        com.stpl.app.parttwo.model.VwAdjustDemandForecastAct vwAdjustDemandForecastAct)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(vwAdjustDemandForecastAct);
    }

    /**
    * Returns the vw adjust demand forecast act with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchVwAdjustDemandForecastActException} if it could not be found.
    *
    * @param adjustedDemandForecastId the primary key of the vw adjust demand forecast act
    * @return the vw adjust demand forecast act
    * @throws com.stpl.app.parttwo.NoSuchVwAdjustDemandForecastActException if a vw adjust demand forecast act with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.VwAdjustDemandForecastAct findByPrimaryKey(
        int adjustedDemandForecastId)
        throws com.stpl.app.parttwo.NoSuchVwAdjustDemandForecastActException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(adjustedDemandForecastId);
    }

    /**
    * Returns the vw adjust demand forecast act with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param adjustedDemandForecastId the primary key of the vw adjust demand forecast act
    * @return the vw adjust demand forecast act, or <code>null</code> if a vw adjust demand forecast act with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.VwAdjustDemandForecastAct fetchByPrimaryKey(
        int adjustedDemandForecastId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(adjustedDemandForecastId);
    }

    /**
    * Returns all the vw adjust demand forecast acts.
    *
    * @return the vw adjust demand forecast acts
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.VwAdjustDemandForecastAct> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

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
    public static java.util.List<com.stpl.app.parttwo.model.VwAdjustDemandForecastAct> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

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
    public static java.util.List<com.stpl.app.parttwo.model.VwAdjustDemandForecastAct> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the vw adjust demand forecast acts from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of vw adjust demand forecast acts.
    *
    * @return the number of vw adjust demand forecast acts
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static VwAdjustDemandForecastActPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (VwAdjustDemandForecastActPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.parttwo.service.ClpSerializer.getServletContextName(),
                    VwAdjustDemandForecastActPersistence.class.getName());

            ReferenceRegistry.registerReference(VwAdjustDemandForecastActUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(VwAdjustDemandForecastActPersistence persistence) {
    }
}
