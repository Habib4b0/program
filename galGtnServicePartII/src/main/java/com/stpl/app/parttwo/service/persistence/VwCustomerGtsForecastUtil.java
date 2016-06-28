package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.VwCustomerGtsForecast;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the vw customer gts forecast service. This utility wraps {@link VwCustomerGtsForecastPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwCustomerGtsForecastPersistence
 * @see VwCustomerGtsForecastPersistenceImpl
 * @generated
 */
public class VwCustomerGtsForecastUtil {
    private static VwCustomerGtsForecastPersistence _persistence;

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
    public static void clearCache(VwCustomerGtsForecast vwCustomerGtsForecast) {
        getPersistence().clearCache(vwCustomerGtsForecast);
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
    public static List<VwCustomerGtsForecast> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<VwCustomerGtsForecast> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<VwCustomerGtsForecast> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static VwCustomerGtsForecast update(
        VwCustomerGtsForecast vwCustomerGtsForecast) throws SystemException {
        return getPersistence().update(vwCustomerGtsForecast);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static VwCustomerGtsForecast update(
        VwCustomerGtsForecast vwCustomerGtsForecast,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(vwCustomerGtsForecast, serviceContext);
    }

    /**
    * Caches the vw customer gts forecast in the entity cache if it is enabled.
    *
    * @param vwCustomerGtsForecast the vw customer gts forecast
    */
    public static void cacheResult(
        com.stpl.app.parttwo.model.VwCustomerGtsForecast vwCustomerGtsForecast) {
        getPersistence().cacheResult(vwCustomerGtsForecast);
    }

    /**
    * Caches the vw customer gts forecasts in the entity cache if it is enabled.
    *
    * @param vwCustomerGtsForecasts the vw customer gts forecasts
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.VwCustomerGtsForecast> vwCustomerGtsForecasts) {
        getPersistence().cacheResult(vwCustomerGtsForecasts);
    }

    /**
    * Creates a new vw customer gts forecast with the primary key. Does not add the vw customer gts forecast to the database.
    *
    * @param customerGtsForecastSid the primary key for the new vw customer gts forecast
    * @return the new vw customer gts forecast
    */
    public static com.stpl.app.parttwo.model.VwCustomerGtsForecast create(
        int customerGtsForecastSid) {
        return getPersistence().create(customerGtsForecastSid);
    }

    /**
    * Removes the vw customer gts forecast with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param customerGtsForecastSid the primary key of the vw customer gts forecast
    * @return the vw customer gts forecast that was removed
    * @throws com.stpl.app.parttwo.NoSuchVwCustomerGtsForecastException if a vw customer gts forecast with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.VwCustomerGtsForecast remove(
        int customerGtsForecastSid)
        throws com.stpl.app.parttwo.NoSuchVwCustomerGtsForecastException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(customerGtsForecastSid);
    }

    public static com.stpl.app.parttwo.model.VwCustomerGtsForecast updateImpl(
        com.stpl.app.parttwo.model.VwCustomerGtsForecast vwCustomerGtsForecast)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(vwCustomerGtsForecast);
    }

    /**
    * Returns the vw customer gts forecast with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchVwCustomerGtsForecastException} if it could not be found.
    *
    * @param customerGtsForecastSid the primary key of the vw customer gts forecast
    * @return the vw customer gts forecast
    * @throws com.stpl.app.parttwo.NoSuchVwCustomerGtsForecastException if a vw customer gts forecast with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.VwCustomerGtsForecast findByPrimaryKey(
        int customerGtsForecastSid)
        throws com.stpl.app.parttwo.NoSuchVwCustomerGtsForecastException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(customerGtsForecastSid);
    }

    /**
    * Returns the vw customer gts forecast with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param customerGtsForecastSid the primary key of the vw customer gts forecast
    * @return the vw customer gts forecast, or <code>null</code> if a vw customer gts forecast with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.VwCustomerGtsForecast fetchByPrimaryKey(
        int customerGtsForecastSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(customerGtsForecastSid);
    }

    /**
    * Returns all the vw customer gts forecasts.
    *
    * @return the vw customer gts forecasts
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.VwCustomerGtsForecast> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the vw customer gts forecasts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw customer gts forecasts
    * @param end the upper bound of the range of vw customer gts forecasts (not inclusive)
    * @return the range of vw customer gts forecasts
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.VwCustomerGtsForecast> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the vw customer gts forecasts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw customer gts forecasts
    * @param end the upper bound of the range of vw customer gts forecasts (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of vw customer gts forecasts
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.VwCustomerGtsForecast> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the vw customer gts forecasts from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of vw customer gts forecasts.
    *
    * @return the number of vw customer gts forecasts
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static VwCustomerGtsForecastPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (VwCustomerGtsForecastPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.parttwo.service.ClpSerializer.getServletContextName(),
                    VwCustomerGtsForecastPersistence.class.getName());

            ReferenceRegistry.registerReference(VwCustomerGtsForecastUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(VwCustomerGtsForecastPersistence persistence) {
    }
}
