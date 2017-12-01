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

import com.stpl.app.model.RebateTierFormula;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the rebate tier formula service. This utility wraps {@link com.stpl.app.service.persistence.impl.RebateTierFormulaPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RebateTierFormulaPersistence
 * @see com.stpl.app.service.persistence.impl.RebateTierFormulaPersistenceImpl
 * @generated
 */
@ProviderType
public class RebateTierFormulaUtil {
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
	public static void clearCache(RebateTierFormula rebateTierFormula) {
		getPersistence().clearCache(rebateTierFormula);
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
	public static List<RebateTierFormula> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<RebateTierFormula> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<RebateTierFormula> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<RebateTierFormula> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static RebateTierFormula update(RebateTierFormula rebateTierFormula) {
		return getPersistence().update(rebateTierFormula);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static RebateTierFormula update(
		RebateTierFormula rebateTierFormula, ServiceContext serviceContext) {
		return getPersistence().update(rebateTierFormula, serviceContext);
	}

	/**
	* Caches the rebate tier formula in the entity cache if it is enabled.
	*
	* @param rebateTierFormula the rebate tier formula
	*/
	public static void cacheResult(RebateTierFormula rebateTierFormula) {
		getPersistence().cacheResult(rebateTierFormula);
	}

	/**
	* Caches the rebate tier formulas in the entity cache if it is enabled.
	*
	* @param rebateTierFormulas the rebate tier formulas
	*/
	public static void cacheResult(List<RebateTierFormula> rebateTierFormulas) {
		getPersistence().cacheResult(rebateTierFormulas);
	}

	/**
	* Creates a new rebate tier formula with the primary key. Does not add the rebate tier formula to the database.
	*
	* @param rebateTierFormulaSid the primary key for the new rebate tier formula
	* @return the new rebate tier formula
	*/
	public static RebateTierFormula create(int rebateTierFormulaSid) {
		return getPersistence().create(rebateTierFormulaSid);
	}

	/**
	* Removes the rebate tier formula with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param rebateTierFormulaSid the primary key of the rebate tier formula
	* @return the rebate tier formula that was removed
	* @throws NoSuchRebateTierFormulaException if a rebate tier formula with the primary key could not be found
	*/
	public static RebateTierFormula remove(int rebateTierFormulaSid)
		throws com.stpl.app.exception.NoSuchRebateTierFormulaException {
		return getPersistence().remove(rebateTierFormulaSid);
	}

	public static RebateTierFormula updateImpl(
		RebateTierFormula rebateTierFormula) {
		return getPersistence().updateImpl(rebateTierFormula);
	}

	/**
	* Returns the rebate tier formula with the primary key or throws a {@link NoSuchRebateTierFormulaException} if it could not be found.
	*
	* @param rebateTierFormulaSid the primary key of the rebate tier formula
	* @return the rebate tier formula
	* @throws NoSuchRebateTierFormulaException if a rebate tier formula with the primary key could not be found
	*/
	public static RebateTierFormula findByPrimaryKey(int rebateTierFormulaSid)
		throws com.stpl.app.exception.NoSuchRebateTierFormulaException {
		return getPersistence().findByPrimaryKey(rebateTierFormulaSid);
	}

	/**
	* Returns the rebate tier formula with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param rebateTierFormulaSid the primary key of the rebate tier formula
	* @return the rebate tier formula, or <code>null</code> if a rebate tier formula with the primary key could not be found
	*/
	public static RebateTierFormula fetchByPrimaryKey(int rebateTierFormulaSid) {
		return getPersistence().fetchByPrimaryKey(rebateTierFormulaSid);
	}

	public static java.util.Map<java.io.Serializable, RebateTierFormula> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the rebate tier formulas.
	*
	* @return the rebate tier formulas
	*/
	public static List<RebateTierFormula> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<RebateTierFormula> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<RebateTierFormula> findAll(int start, int end,
		OrderByComparator<RebateTierFormula> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<RebateTierFormula> findAll(int start, int end,
		OrderByComparator<RebateTierFormula> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the rebate tier formulas from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of rebate tier formulas.
	*
	* @return the number of rebate tier formulas
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static RebateTierFormulaPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<RebateTierFormulaPersistence, RebateTierFormulaPersistence> _serviceTracker =
		ServiceTrackerFactory.open(RebateTierFormulaPersistence.class);
}