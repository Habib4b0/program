package com.stpl.app.service.persistence;

import com.stpl.app.model.VwDemandForecastActual;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the vw demand forecast actual service. This utility wraps {@link VwDemandForecastActualPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwDemandForecastActualPersistence
 * @see VwDemandForecastActualPersistenceImpl
 * @generated
 */
public class VwDemandForecastActualUtil {
    private static VwDemandForecastActualPersistence _persistence;

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
    public static void clearCache(VwDemandForecastActual vwDemandForecastActual) {
        getPersistence().clearCache(vwDemandForecastActual);
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
    public static List<VwDemandForecastActual> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<VwDemandForecastActual> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<VwDemandForecastActual> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static VwDemandForecastActual update(
        VwDemandForecastActual vwDemandForecastActual)
        throws SystemException {
        return getPersistence().update(vwDemandForecastActual);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static VwDemandForecastActual update(
        VwDemandForecastActual vwDemandForecastActual,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(vwDemandForecastActual, serviceContext);
    }

    /**
    * Caches the vw demand forecast actual in the entity cache if it is enabled.
    *
    * @param vwDemandForecastActual the vw demand forecast actual
    */
    public static void cacheResult(
        com.stpl.app.model.VwDemandForecastActual vwDemandForecastActual) {
        getPersistence().cacheResult(vwDemandForecastActual);
    }

    /**
    * Caches the vw demand forecast actuals in the entity cache if it is enabled.
    *
    * @param vwDemandForecastActuals the vw demand forecast actuals
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.VwDemandForecastActual> vwDemandForecastActuals) {
        getPersistence().cacheResult(vwDemandForecastActuals);
    }

    /**
    * Creates a new vw demand forecast actual with the primary key. Does not add the vw demand forecast actual to the database.
    *
    * @param demandForecastActualSid the primary key for the new vw demand forecast actual
    * @return the new vw demand forecast actual
    */
    public static com.stpl.app.model.VwDemandForecastActual create(
        int demandForecastActualSid) {
        return getPersistence().create(demandForecastActualSid);
    }

    /**
    * Removes the vw demand forecast actual with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param demandForecastActualSid the primary key of the vw demand forecast actual
    * @return the vw demand forecast actual that was removed
    * @throws com.stpl.app.NoSuchVwDemandForecastActualException if a vw demand forecast actual with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.VwDemandForecastActual remove(
        int demandForecastActualSid)
        throws com.stpl.app.NoSuchVwDemandForecastActualException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(demandForecastActualSid);
    }

    public static com.stpl.app.model.VwDemandForecastActual updateImpl(
        com.stpl.app.model.VwDemandForecastActual vwDemandForecastActual)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(vwDemandForecastActual);
    }

    /**
    * Returns the vw demand forecast actual with the primary key or throws a {@link com.stpl.app.NoSuchVwDemandForecastActualException} if it could not be found.
    *
    * @param demandForecastActualSid the primary key of the vw demand forecast actual
    * @return the vw demand forecast actual
    * @throws com.stpl.app.NoSuchVwDemandForecastActualException if a vw demand forecast actual with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.VwDemandForecastActual findByPrimaryKey(
        int demandForecastActualSid)
        throws com.stpl.app.NoSuchVwDemandForecastActualException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(demandForecastActualSid);
    }

    /**
    * Returns the vw demand forecast actual with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param demandForecastActualSid the primary key of the vw demand forecast actual
    * @return the vw demand forecast actual, or <code>null</code> if a vw demand forecast actual with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.VwDemandForecastActual fetchByPrimaryKey(
        int demandForecastActualSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(demandForecastActualSid);
    }

    /**
    * Returns all the vw demand forecast actuals.
    *
    * @return the vw demand forecast actuals
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.VwDemandForecastActual> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the vw demand forecast actuals.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwDemandForecastActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw demand forecast actuals
    * @param end the upper bound of the range of vw demand forecast actuals (not inclusive)
    * @return the range of vw demand forecast actuals
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.VwDemandForecastActual> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the vw demand forecast actuals.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwDemandForecastActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw demand forecast actuals
    * @param end the upper bound of the range of vw demand forecast actuals (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of vw demand forecast actuals
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.VwDemandForecastActual> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the vw demand forecast actuals from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of vw demand forecast actuals.
    *
    * @return the number of vw demand forecast actuals
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static VwDemandForecastActualPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (VwDemandForecastActualPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    VwDemandForecastActualPersistence.class.getName());

            ReferenceRegistry.registerReference(VwDemandForecastActualUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(VwDemandForecastActualPersistence persistence) {
    }
}
