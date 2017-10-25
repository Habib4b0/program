package com.stpl.app.service.persistence;

import com.stpl.app.model.VwIvldDemandForecastActual;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the vw ivld demand forecast actual service. This utility wraps {@link VwIvldDemandForecastActualPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwIvldDemandForecastActualPersistence
 * @see VwIvldDemandForecastActualPersistenceImpl
 * @generated
 */
public class VwIvldDemandForecastActualUtil {
    private static VwIvldDemandForecastActualPersistence _persistence;

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
        VwIvldDemandForecastActual vwIvldDemandForecastActual) {
        getPersistence().clearCache(vwIvldDemandForecastActual);
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
    public static List<VwIvldDemandForecastActual> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<VwIvldDemandForecastActual> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<VwIvldDemandForecastActual> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static VwIvldDemandForecastActual update(
        VwIvldDemandForecastActual vwIvldDemandForecastActual)
        throws SystemException {
        return getPersistence().update(vwIvldDemandForecastActual);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static VwIvldDemandForecastActual update(
        VwIvldDemandForecastActual vwIvldDemandForecastActual,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence()
                   .update(vwIvldDemandForecastActual, serviceContext);
    }

    /**
    * Caches the vw ivld demand forecast actual in the entity cache if it is enabled.
    *
    * @param vwIvldDemandForecastActual the vw ivld demand forecast actual
    */
    public static void cacheResult(
        com.stpl.app.model.VwIvldDemandForecastActual vwIvldDemandForecastActual) {
        getPersistence().cacheResult(vwIvldDemandForecastActual);
    }

    /**
    * Caches the vw ivld demand forecast actuals in the entity cache if it is enabled.
    *
    * @param vwIvldDemandForecastActuals the vw ivld demand forecast actuals
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.VwIvldDemandForecastActual> vwIvldDemandForecastActuals) {
        getPersistence().cacheResult(vwIvldDemandForecastActuals);
    }

    /**
    * Creates a new vw ivld demand forecast actual with the primary key. Does not add the vw ivld demand forecast actual to the database.
    *
    * @param ivldDemandActualForecastSid the primary key for the new vw ivld demand forecast actual
    * @return the new vw ivld demand forecast actual
    */
    public static com.stpl.app.model.VwIvldDemandForecastActual create(
        int ivldDemandActualForecastSid) {
        return getPersistence().create(ivldDemandActualForecastSid);
    }

    /**
    * Removes the vw ivld demand forecast actual with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldDemandActualForecastSid the primary key of the vw ivld demand forecast actual
    * @return the vw ivld demand forecast actual that was removed
    * @throws com.stpl.app.NoSuchVwIvldDemandForecastActualException if a vw ivld demand forecast actual with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.VwIvldDemandForecastActual remove(
        int ivldDemandActualForecastSid)
        throws com.stpl.app.NoSuchVwIvldDemandForecastActualException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(ivldDemandActualForecastSid);
    }

    public static com.stpl.app.model.VwIvldDemandForecastActual updateImpl(
        com.stpl.app.model.VwIvldDemandForecastActual vwIvldDemandForecastActual)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(vwIvldDemandForecastActual);
    }

    /**
    * Returns the vw ivld demand forecast actual with the primary key or throws a {@link com.stpl.app.NoSuchVwIvldDemandForecastActualException} if it could not be found.
    *
    * @param ivldDemandActualForecastSid the primary key of the vw ivld demand forecast actual
    * @return the vw ivld demand forecast actual
    * @throws com.stpl.app.NoSuchVwIvldDemandForecastActualException if a vw ivld demand forecast actual with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.VwIvldDemandForecastActual findByPrimaryKey(
        int ivldDemandActualForecastSid)
        throws com.stpl.app.NoSuchVwIvldDemandForecastActualException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(ivldDemandActualForecastSid);
    }

    /**
    * Returns the vw ivld demand forecast actual with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldDemandActualForecastSid the primary key of the vw ivld demand forecast actual
    * @return the vw ivld demand forecast actual, or <code>null</code> if a vw ivld demand forecast actual with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.VwIvldDemandForecastActual fetchByPrimaryKey(
        int ivldDemandActualForecastSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(ivldDemandActualForecastSid);
    }

    /**
    * Returns all the vw ivld demand forecast actuals.
    *
    * @return the vw ivld demand forecast actuals
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.VwIvldDemandForecastActual> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

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
    public static java.util.List<com.stpl.app.model.VwIvldDemandForecastActual> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

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
    public static java.util.List<com.stpl.app.model.VwIvldDemandForecastActual> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the vw ivld demand forecast actuals from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of vw ivld demand forecast actuals.
    *
    * @return the number of vw ivld demand forecast actuals
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static VwIvldDemandForecastActualPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (VwIvldDemandForecastActualPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    VwIvldDemandForecastActualPersistence.class.getName());

            ReferenceRegistry.registerReference(VwIvldDemandForecastActualUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(
        VwIvldDemandForecastActualPersistence persistence) {
    }
}
