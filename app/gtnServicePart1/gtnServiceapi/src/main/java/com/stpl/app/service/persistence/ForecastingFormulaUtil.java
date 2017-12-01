/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.stpl.app.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.stpl.app.model.ForecastingFormula;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the forecasting formula service. This utility wraps {@link com.stpl.app.service.persistence.impl.ForecastingFormulaPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ForecastingFormulaPersistence
 * @see com.stpl.app.service.persistence.impl.ForecastingFormulaPersistenceImpl
 * @generated
 */
@ProviderType
public class ForecastingFormulaUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(ForecastingFormula forecastingFormula) {
		getPersistence().clearCache(forecastingFormula);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ForecastingFormula> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ForecastingFormula> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ForecastingFormula> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ForecastingFormula> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ForecastingFormula update(
		ForecastingFormula forecastingFormula) {
		return getPersistence().update(forecastingFormula);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ForecastingFormula update(
		ForecastingFormula forecastingFormula, ServiceContext serviceContext) {
		return getPersistence().update(forecastingFormula, serviceContext);
	}

	/**
	* Caches the forecasting formula in the entity cache if it is enabled.
	*
	* @param forecastingFormula the forecasting formula
	*/
	public static void cacheResult(ForecastingFormula forecastingFormula) {
		getPersistence().cacheResult(forecastingFormula);
	}

	/**
	* Caches the forecasting formulas in the entity cache if it is enabled.
	*
	* @param forecastingFormulas the forecasting formulas
	*/
	public static void cacheResult(List<ForecastingFormula> forecastingFormulas) {
		getPersistence().cacheResult(forecastingFormulas);
	}

	/**
	* Creates a new forecasting formula with the primary key. Does not add the forecasting formula to the database.
	*
	* @param forecastingFormulaSid the primary key for the new forecasting formula
	* @return the new forecasting formula
	*/
	public static ForecastingFormula create(int forecastingFormulaSid) {
		return getPersistence().create(forecastingFormulaSid);
	}

	/**
	* Removes the forecasting formula with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param forecastingFormulaSid the primary key of the forecasting formula
	* @return the forecasting formula that was removed
	* @throws NoSuchForecastingFormulaException if a forecasting formula with the primary key could not be found
	*/
	public static ForecastingFormula remove(int forecastingFormulaSid)
		throws com.stpl.app.exception.NoSuchForecastingFormulaException {
		return getPersistence().remove(forecastingFormulaSid);
	}

	public static ForecastingFormula updateImpl(
		ForecastingFormula forecastingFormula) {
		return getPersistence().updateImpl(forecastingFormula);
	}

	/**
	* Returns the forecasting formula with the primary key or throws a {@link NoSuchForecastingFormulaException} if it could not be found.
	*
	* @param forecastingFormulaSid the primary key of the forecasting formula
	* @return the forecasting formula
	* @throws NoSuchForecastingFormulaException if a forecasting formula with the primary key could not be found
	*/
	public static ForecastingFormula findByPrimaryKey(int forecastingFormulaSid)
		throws com.stpl.app.exception.NoSuchForecastingFormulaException {
		return getPersistence().findByPrimaryKey(forecastingFormulaSid);
	}

	/**
	* Returns the forecasting formula with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param forecastingFormulaSid the primary key of the forecasting formula
	* @return the forecasting formula, or <code>null</code> if a forecasting formula with the primary key could not be found
	*/
	public static ForecastingFormula fetchByPrimaryKey(
		int forecastingFormulaSid) {
		return getPersistence().fetchByPrimaryKey(forecastingFormulaSid);
	}

	public static java.util.Map<java.io.Serializable, ForecastingFormula> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the forecasting formulas.
	*
	* @return the forecasting formulas
	*/
	public static List<ForecastingFormula> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the forecasting formulas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastingFormulaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of forecasting formulas
	* @param end the upper bound of the range of forecasting formulas (not inclusive)
	* @return the range of forecasting formulas
	*/
	public static List<ForecastingFormula> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the forecasting formulas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastingFormulaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of forecasting formulas
	* @param end the upper bound of the range of forecasting formulas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of forecasting formulas
	*/
	public static List<ForecastingFormula> findAll(int start, int end,
		OrderByComparator<ForecastingFormula> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the forecasting formulas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastingFormulaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of forecasting formulas
	* @param end the upper bound of the range of forecasting formulas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of forecasting formulas
	*/
	public static List<ForecastingFormula> findAll(int start, int end,
		OrderByComparator<ForecastingFormula> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the forecasting formulas from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of forecasting formulas.
	*
	* @return the number of forecasting formulas
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ForecastingFormulaPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ForecastingFormulaPersistence, ForecastingFormulaPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ForecastingFormulaPersistence.class);
}