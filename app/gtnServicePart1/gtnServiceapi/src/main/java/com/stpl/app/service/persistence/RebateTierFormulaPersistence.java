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

import com.stpl.app.exception.NoSuchRebateTierFormulaException;
import com.stpl.app.model.RebateTierFormula;

/**
 * The persistence interface for the rebate tier formula service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.RebateTierFormulaPersistenceImpl
 * @see RebateTierFormulaUtil
 * @generated
 */
@ProviderType
public interface RebateTierFormulaPersistence extends BasePersistence<RebateTierFormula> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RebateTierFormulaUtil} to access the rebate tier formula persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the rebate tier formula in the entity cache if it is enabled.
	*
	* @param rebateTierFormula the rebate tier formula
	*/
	public void cacheResult(RebateTierFormula rebateTierFormula);

	/**
	* Caches the rebate tier formulas in the entity cache if it is enabled.
	*
	* @param rebateTierFormulas the rebate tier formulas
	*/
	public void cacheResult(
		java.util.List<RebateTierFormula> rebateTierFormulas);

	/**
	* Creates a new rebate tier formula with the primary key. Does not add the rebate tier formula to the database.
	*
	* @param rebateTierFormulaSid the primary key for the new rebate tier formula
	* @return the new rebate tier formula
	*/
	public RebateTierFormula create(int rebateTierFormulaSid);

	/**
	* Removes the rebate tier formula with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param rebateTierFormulaSid the primary key of the rebate tier formula
	* @return the rebate tier formula that was removed
	* @throws NoSuchRebateTierFormulaException if a rebate tier formula with the primary key could not be found
	*/
	public RebateTierFormula remove(int rebateTierFormulaSid)
		throws NoSuchRebateTierFormulaException;

	public RebateTierFormula updateImpl(RebateTierFormula rebateTierFormula);

	/**
	* Returns the rebate tier formula with the primary key or throws a {@link NoSuchRebateTierFormulaException} if it could not be found.
	*
	* @param rebateTierFormulaSid the primary key of the rebate tier formula
	* @return the rebate tier formula
	* @throws NoSuchRebateTierFormulaException if a rebate tier formula with the primary key could not be found
	*/
	public RebateTierFormula findByPrimaryKey(int rebateTierFormulaSid)
		throws NoSuchRebateTierFormulaException;

	/**
	* Returns the rebate tier formula with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param rebateTierFormulaSid the primary key of the rebate tier formula
	* @return the rebate tier formula, or <code>null</code> if a rebate tier formula with the primary key could not be found
	*/
	public RebateTierFormula fetchByPrimaryKey(int rebateTierFormulaSid);

	@Override
	public java.util.Map<java.io.Serializable, RebateTierFormula> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the rebate tier formulas.
	*
	* @return the rebate tier formulas
	*/
	public java.util.List<RebateTierFormula> findAll();

	/**
	* Returns a range of all the rebate tier formulas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebateTierFormulaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rebate tier formulas
	* @param end the upper bound of the range of rebate tier formulas (not inclusive)
	* @return the range of rebate tier formulas
	*/
	public java.util.List<RebateTierFormula> findAll(int start, int end);

	/**
	* Returns an ordered range of all the rebate tier formulas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebateTierFormulaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rebate tier formulas
	* @param end the upper bound of the range of rebate tier formulas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of rebate tier formulas
	*/
	public java.util.List<RebateTierFormula> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RebateTierFormula> orderByComparator);

	/**
	* Returns an ordered range of all the rebate tier formulas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebateTierFormulaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rebate tier formulas
	* @param end the upper bound of the range of rebate tier formulas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of rebate tier formulas
	*/
	public java.util.List<RebateTierFormula> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RebateTierFormula> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the rebate tier formulas from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of rebate tier formulas.
	*
	* @return the number of rebate tier formulas
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}