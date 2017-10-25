package com.stpl.app.service.persistence;

import com.stpl.app.model.ForecastingFormula;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the forecasting formula service. This utility wraps {@link ForecastingFormulaPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ForecastingFormulaPersistence
 * @see ForecastingFormulaPersistenceImpl
 * @generated
 */
public class ForecastingFormulaUtil {
    private static ForecastingFormulaPersistence _persistence;

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
    public static void clearCache(ForecastingFormula forecastingFormula) {
        getPersistence().clearCache(forecastingFormula);
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
    public static List<ForecastingFormula> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ForecastingFormula> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ForecastingFormula> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static ForecastingFormula update(
        ForecastingFormula forecastingFormula) throws SystemException {
        return getPersistence().update(forecastingFormula);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static ForecastingFormula update(
        ForecastingFormula forecastingFormula, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(forecastingFormula, serviceContext);
    }

    /**
    * Caches the forecasting formula in the entity cache if it is enabled.
    *
    * @param forecastingFormula the forecasting formula
    */
    public static void cacheResult(
        com.stpl.app.model.ForecastingFormula forecastingFormula) {
        getPersistence().cacheResult(forecastingFormula);
    }

    /**
    * Caches the forecasting formulas in the entity cache if it is enabled.
    *
    * @param forecastingFormulas the forecasting formulas
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.ForecastingFormula> forecastingFormulas) {
        getPersistence().cacheResult(forecastingFormulas);
    }

    /**
    * Creates a new forecasting formula with the primary key. Does not add the forecasting formula to the database.
    *
    * @param forecastingFormulaSid the primary key for the new forecasting formula
    * @return the new forecasting formula
    */
    public static com.stpl.app.model.ForecastingFormula create(
        int forecastingFormulaSid) {
        return getPersistence().create(forecastingFormulaSid);
    }

    /**
    * Removes the forecasting formula with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param forecastingFormulaSid the primary key of the forecasting formula
    * @return the forecasting formula that was removed
    * @throws com.stpl.app.NoSuchForecastingFormulaException if a forecasting formula with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ForecastingFormula remove(
        int forecastingFormulaSid)
        throws com.stpl.app.NoSuchForecastingFormulaException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(forecastingFormulaSid);
    }

    public static com.stpl.app.model.ForecastingFormula updateImpl(
        com.stpl.app.model.ForecastingFormula forecastingFormula)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(forecastingFormula);
    }

    /**
    * Returns the forecasting formula with the primary key or throws a {@link com.stpl.app.NoSuchForecastingFormulaException} if it could not be found.
    *
    * @param forecastingFormulaSid the primary key of the forecasting formula
    * @return the forecasting formula
    * @throws com.stpl.app.NoSuchForecastingFormulaException if a forecasting formula with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ForecastingFormula findByPrimaryKey(
        int forecastingFormulaSid)
        throws com.stpl.app.NoSuchForecastingFormulaException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(forecastingFormulaSid);
    }

    /**
    * Returns the forecasting formula with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param forecastingFormulaSid the primary key of the forecasting formula
    * @return the forecasting formula, or <code>null</code> if a forecasting formula with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ForecastingFormula fetchByPrimaryKey(
        int forecastingFormulaSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(forecastingFormulaSid);
    }

    /**
    * Returns all the forecasting formulas.
    *
    * @return the forecasting formulas
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ForecastingFormula> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the forecasting formulas.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingFormulaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of forecasting formulas
    * @param end the upper bound of the range of forecasting formulas (not inclusive)
    * @return the range of forecasting formulas
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ForecastingFormula> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the forecasting formulas.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingFormulaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of forecasting formulas
    * @param end the upper bound of the range of forecasting formulas (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of forecasting formulas
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ForecastingFormula> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the forecasting formulas from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of forecasting formulas.
    *
    * @return the number of forecasting formulas
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ForecastingFormulaPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ForecastingFormulaPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ForecastingFormulaPersistence.class.getName());

            ReferenceRegistry.registerReference(ForecastingFormulaUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ForecastingFormulaPersistence persistence) {
    }
}
