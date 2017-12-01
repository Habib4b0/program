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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.stpl.app.exception.NoSuchForecastingFormulaException;
import com.stpl.app.model.ForecastingFormula;

/**
 * The persistence interface for the forecasting formula service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.ForecastingFormulaPersistenceImpl
 * @see ForecastingFormulaUtil
 * @generated
 */
@ProviderType
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
	public void cacheResult(ForecastingFormula forecastingFormula);

	/**
	* Caches the forecasting formulas in the entity cache if it is enabled.
	*
	* @param forecastingFormulas the forecasting formulas
	*/
	public void cacheResult(
		java.util.List<ForecastingFormula> forecastingFormulas);

	/**
	* Creates a new forecasting formula with the primary key. Does not add the forecasting formula to the database.
	*
	* @param forecastingFormulaSid the primary key for the new forecasting formula
	* @return the new forecasting formula
	*/
	public ForecastingFormula create(int forecastingFormulaSid);

	/**
	* Removes the forecasting formula with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param forecastingFormulaSid the primary key of the forecasting formula
	* @return the forecasting formula that was removed
	* @throws NoSuchForecastingFormulaException if a forecasting formula with the primary key could not be found
	*/
	public ForecastingFormula remove(int forecastingFormulaSid)
		throws NoSuchForecastingFormulaException;

	public ForecastingFormula updateImpl(ForecastingFormula forecastingFormula);

	/**
	* Returns the forecasting formula with the primary key or throws a {@link NoSuchForecastingFormulaException} if it could not be found.
	*
	* @param forecastingFormulaSid the primary key of the forecasting formula
	* @return the forecasting formula
	* @throws NoSuchForecastingFormulaException if a forecasting formula with the primary key could not be found
	*/
	public ForecastingFormula findByPrimaryKey(int forecastingFormulaSid)
		throws NoSuchForecastingFormulaException;

	/**
	* Returns the forecasting formula with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param forecastingFormulaSid the primary key of the forecasting formula
	* @return the forecasting formula, or <code>null</code> if a forecasting formula with the primary key could not be found
	*/
	public ForecastingFormula fetchByPrimaryKey(int forecastingFormulaSid);

	@Override
	public java.util.Map<java.io.Serializable, ForecastingFormula> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the forecasting formulas.
	*
	* @return the forecasting formulas
	*/
	public java.util.List<ForecastingFormula> findAll();

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
	public java.util.List<ForecastingFormula> findAll(int start, int end);

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
	public java.util.List<ForecastingFormula> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingFormula> orderByComparator);

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
	public java.util.List<ForecastingFormula> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ForecastingFormula> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the forecasting formulas from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of forecasting formulas.
	*
	* @return the number of forecasting formulas
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}