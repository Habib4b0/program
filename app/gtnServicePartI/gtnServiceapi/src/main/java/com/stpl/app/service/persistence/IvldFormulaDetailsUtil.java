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

import com.stpl.app.model.IvldFormulaDetails;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the ivld formula details service. This utility wraps {@link com.stpl.app.service.persistence.impl.IvldFormulaDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldFormulaDetailsPersistence
 * @see com.stpl.app.service.persistence.impl.IvldFormulaDetailsPersistenceImpl
 * @generated
 */
@ProviderType
public class IvldFormulaDetailsUtil {
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
	public static void clearCache(IvldFormulaDetails ivldFormulaDetails) {
		getPersistence().clearCache(ivldFormulaDetails);
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
	public static List<IvldFormulaDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<IvldFormulaDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<IvldFormulaDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<IvldFormulaDetails> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static IvldFormulaDetails update(
		IvldFormulaDetails ivldFormulaDetails) {
		return getPersistence().update(ivldFormulaDetails);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static IvldFormulaDetails update(
		IvldFormulaDetails ivldFormulaDetails, ServiceContext serviceContext) {
		return getPersistence().update(ivldFormulaDetails, serviceContext);
	}

	/**
	* Caches the ivld formula details in the entity cache if it is enabled.
	*
	* @param ivldFormulaDetails the ivld formula details
	*/
	public static void cacheResult(IvldFormulaDetails ivldFormulaDetails) {
		getPersistence().cacheResult(ivldFormulaDetails);
	}

	/**
	* Caches the ivld formula detailses in the entity cache if it is enabled.
	*
	* @param ivldFormulaDetailses the ivld formula detailses
	*/
	public static void cacheResult(
		List<IvldFormulaDetails> ivldFormulaDetailses) {
		getPersistence().cacheResult(ivldFormulaDetailses);
	}

	/**
	* Creates a new ivld formula details with the primary key. Does not add the ivld formula details to the database.
	*
	* @param ivldFormulaDetailsSid the primary key for the new ivld formula details
	* @return the new ivld formula details
	*/
	public static IvldFormulaDetails create(int ivldFormulaDetailsSid) {
		return getPersistence().create(ivldFormulaDetailsSid);
	}

	/**
	* Removes the ivld formula details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldFormulaDetailsSid the primary key of the ivld formula details
	* @return the ivld formula details that was removed
	* @throws NoSuchIvldFormulaDetailsException if a ivld formula details with the primary key could not be found
	*/
	public static IvldFormulaDetails remove(int ivldFormulaDetailsSid)
		throws com.stpl.app.exception.NoSuchIvldFormulaDetailsException {
		return getPersistence().remove(ivldFormulaDetailsSid);
	}

	public static IvldFormulaDetails updateImpl(
		IvldFormulaDetails ivldFormulaDetails) {
		return getPersistence().updateImpl(ivldFormulaDetails);
	}

	/**
	* Returns the ivld formula details with the primary key or throws a {@link NoSuchIvldFormulaDetailsException} if it could not be found.
	*
	* @param ivldFormulaDetailsSid the primary key of the ivld formula details
	* @return the ivld formula details
	* @throws NoSuchIvldFormulaDetailsException if a ivld formula details with the primary key could not be found
	*/
	public static IvldFormulaDetails findByPrimaryKey(int ivldFormulaDetailsSid)
		throws com.stpl.app.exception.NoSuchIvldFormulaDetailsException {
		return getPersistence().findByPrimaryKey(ivldFormulaDetailsSid);
	}

	/**
	* Returns the ivld formula details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldFormulaDetailsSid the primary key of the ivld formula details
	* @return the ivld formula details, or <code>null</code> if a ivld formula details with the primary key could not be found
	*/
	public static IvldFormulaDetails fetchByPrimaryKey(
		int ivldFormulaDetailsSid) {
		return getPersistence().fetchByPrimaryKey(ivldFormulaDetailsSid);
	}

	public static java.util.Map<java.io.Serializable, IvldFormulaDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ivld formula detailses.
	*
	* @return the ivld formula detailses
	*/
	public static List<IvldFormulaDetails> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ivld formula detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldFormulaDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld formula detailses
	* @param end the upper bound of the range of ivld formula detailses (not inclusive)
	* @return the range of ivld formula detailses
	*/
	public static List<IvldFormulaDetails> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ivld formula detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldFormulaDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld formula detailses
	* @param end the upper bound of the range of ivld formula detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivld formula detailses
	*/
	public static List<IvldFormulaDetails> findAll(int start, int end,
		OrderByComparator<IvldFormulaDetails> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ivld formula detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldFormulaDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld formula detailses
	* @param end the upper bound of the range of ivld formula detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivld formula detailses
	*/
	public static List<IvldFormulaDetails> findAll(int start, int end,
		OrderByComparator<IvldFormulaDetails> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ivld formula detailses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ivld formula detailses.
	*
	* @return the number of ivld formula detailses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static IvldFormulaDetailsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<IvldFormulaDetailsPersistence, IvldFormulaDetailsPersistence> _serviceTracker =
		ServiceTrackerFactory.open(IvldFormulaDetailsPersistence.class);
}