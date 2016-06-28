package com.stpl.app.service.persistence;

import com.stpl.app.model.ForecastingFormula;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the forecasting formula service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ForecastingFormulaPersistenceImpl
 * @see ForecastingFormulaUtil
 * @generated
 */
public interface ForecastingFormulaPersistence extends BasePersistence<ForecastingFormula> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ForecastingFormulaUtil} to access the forecasting formula persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the forecasting formula in the entity cache if it is enabled.
    *
    * @param forecastingFormula the forecasting formula
    */
    public void cacheResult(
        com.stpl.app.model.ForecastingFormula forecastingFormula);

    /**
    * Caches the forecasting formulas in the entity cache if it is enabled.
    *
    * @param forecastingFormulas the forecasting formulas
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.ForecastingFormula> forecastingFormulas);

    /**
    * Creates a new forecasting formula with the primary key. Does not add the forecasting formula to the database.
    *
    * @param forecastingFormulaSid the primary key for the new forecasting formula
    * @return the new forecasting formula
    */
    public com.stpl.app.model.ForecastingFormula create(
        int forecastingFormulaSid);

    /**
    * Removes the forecasting formula with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param forecastingFormulaSid the primary key of the forecasting formula
    * @return the forecasting formula that was removed
    * @throws com.stpl.app.NoSuchForecastingFormulaException if a forecasting formula with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingFormula remove(
        int forecastingFormulaSid)
        throws com.stpl.app.NoSuchForecastingFormulaException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.ForecastingFormula updateImpl(
        com.stpl.app.model.ForecastingFormula forecastingFormula)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the forecasting formula with the primary key or throws a {@link com.stpl.app.NoSuchForecastingFormulaException} if it could not be found.
    *
    * @param forecastingFormulaSid the primary key of the forecasting formula
    * @return the forecasting formula
    * @throws com.stpl.app.NoSuchForecastingFormulaException if a forecasting formula with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingFormula findByPrimaryKey(
        int forecastingFormulaSid)
        throws com.stpl.app.NoSuchForecastingFormulaException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the forecasting formula with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param forecastingFormulaSid the primary key of the forecasting formula
    * @return the forecasting formula, or <code>null</code> if a forecasting formula with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ForecastingFormula fetchByPrimaryKey(
        int forecastingFormulaSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the forecasting formulas.
    *
    * @return the forecasting formulas
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ForecastingFormula> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

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
    public java.util.List<com.stpl.app.model.ForecastingFormula> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

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
    public java.util.List<com.stpl.app.model.ForecastingFormula> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the forecasting formulas from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of forecasting formulas.
    *
    * @return the number of forecasting formulas
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
