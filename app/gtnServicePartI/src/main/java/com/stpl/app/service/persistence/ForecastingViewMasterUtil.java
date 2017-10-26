package com.stpl.app.service.persistence;

import com.stpl.app.model.ForecastingViewMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the forecasting view master service. This utility wraps {@link ForecastingViewMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ForecastingViewMasterPersistence
 * @see ForecastingViewMasterPersistenceImpl
 * @generated
 */
public class ForecastingViewMasterUtil {
    private static ForecastingViewMasterPersistence _persistence;

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
    public static void clearCache(ForecastingViewMaster forecastingViewMaster) {
        getPersistence().clearCache(forecastingViewMaster);
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
    public static List<ForecastingViewMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ForecastingViewMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ForecastingViewMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static ForecastingViewMaster update(
        ForecastingViewMaster forecastingViewMaster) throws SystemException {
        return getPersistence().update(forecastingViewMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static ForecastingViewMaster update(
        ForecastingViewMaster forecastingViewMaster,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(forecastingViewMaster, serviceContext);
    }

    /**
    * Caches the forecasting view master in the entity cache if it is enabled.
    *
    * @param forecastingViewMaster the forecasting view master
    */
    public static void cacheResult(
        com.stpl.app.model.ForecastingViewMaster forecastingViewMaster) {
        getPersistence().cacheResult(forecastingViewMaster);
    }

    /**
    * Caches the forecasting view masters in the entity cache if it is enabled.
    *
    * @param forecastingViewMasters the forecasting view masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.ForecastingViewMaster> forecastingViewMasters) {
        getPersistence().cacheResult(forecastingViewMasters);
    }

    /**
    * Creates a new forecasting view master with the primary key. Does not add the forecasting view master to the database.
    *
    * @param viewId the primary key for the new forecasting view master
    * @return the new forecasting view master
    */
    public static com.stpl.app.model.ForecastingViewMaster create(int viewId) {
        return getPersistence().create(viewId);
    }

    /**
    * Removes the forecasting view master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param viewId the primary key of the forecasting view master
    * @return the forecasting view master that was removed
    * @throws com.stpl.app.NoSuchForecastingViewMasterException if a forecasting view master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ForecastingViewMaster remove(int viewId)
        throws com.stpl.app.NoSuchForecastingViewMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(viewId);
    }

    public static com.stpl.app.model.ForecastingViewMaster updateImpl(
        com.stpl.app.model.ForecastingViewMaster forecastingViewMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(forecastingViewMaster);
    }

    /**
    * Returns the forecasting view master with the primary key or throws a {@link com.stpl.app.NoSuchForecastingViewMasterException} if it could not be found.
    *
    * @param viewId the primary key of the forecasting view master
    * @return the forecasting view master
    * @throws com.stpl.app.NoSuchForecastingViewMasterException if a forecasting view master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ForecastingViewMaster findByPrimaryKey(
        int viewId)
        throws com.stpl.app.NoSuchForecastingViewMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(viewId);
    }

    /**
    * Returns the forecasting view master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param viewId the primary key of the forecasting view master
    * @return the forecasting view master, or <code>null</code> if a forecasting view master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ForecastingViewMaster fetchByPrimaryKey(
        int viewId) throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(viewId);
    }

    /**
    * Returns all the forecasting view masters.
    *
    * @return the forecasting view masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ForecastingViewMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

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
    public static java.util.List<com.stpl.app.model.ForecastingViewMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

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
    public static java.util.List<com.stpl.app.model.ForecastingViewMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the forecasting view masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of forecasting view masters.
    *
    * @return the number of forecasting view masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ForecastingViewMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ForecastingViewMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ForecastingViewMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(ForecastingViewMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ForecastingViewMasterPersistence persistence) {
    }
}
