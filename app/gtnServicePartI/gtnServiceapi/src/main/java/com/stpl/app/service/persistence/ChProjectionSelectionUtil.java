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

import com.stpl.app.model.ChProjectionSelection;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the ch projection selection service. This utility wraps {@link com.stpl.app.service.persistence.impl.ChProjectionSelectionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ChProjectionSelectionPersistence
 * @see com.stpl.app.service.persistence.impl.ChProjectionSelectionPersistenceImpl
 * @generated
 */
@ProviderType
public class ChProjectionSelectionUtil {
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
	public static void clearCache(ChProjectionSelection chProjectionSelection) {
		getPersistence().clearCache(chProjectionSelection);
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
	public static List<ChProjectionSelection> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ChProjectionSelection> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ChProjectionSelection> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ChProjectionSelection> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ChProjectionSelection update(
		ChProjectionSelection chProjectionSelection) {
		return getPersistence().update(chProjectionSelection);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ChProjectionSelection update(
		ChProjectionSelection chProjectionSelection,
		ServiceContext serviceContext) {
		return getPersistence().update(chProjectionSelection, serviceContext);
	}

	/**
	* Caches the ch projection selection in the entity cache if it is enabled.
	*
	* @param chProjectionSelection the ch projection selection
	*/
	public static void cacheResult(ChProjectionSelection chProjectionSelection) {
		getPersistence().cacheResult(chProjectionSelection);
	}

	/**
	* Caches the ch projection selections in the entity cache if it is enabled.
	*
	* @param chProjectionSelections the ch projection selections
	*/
	public static void cacheResult(
		List<ChProjectionSelection> chProjectionSelections) {
		getPersistence().cacheResult(chProjectionSelections);
	}

	/**
	* Creates a new ch projection selection with the primary key. Does not add the ch projection selection to the database.
	*
	* @param chProjectionSelectionSid the primary key for the new ch projection selection
	* @return the new ch projection selection
	*/
	public static ChProjectionSelection create(int chProjectionSelectionSid) {
		return getPersistence().create(chProjectionSelectionSid);
	}

	/**
	* Removes the ch projection selection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param chProjectionSelectionSid the primary key of the ch projection selection
	* @return the ch projection selection that was removed
	* @throws NoSuchChProjectionSelectionException if a ch projection selection with the primary key could not be found
	*/
	public static ChProjectionSelection remove(int chProjectionSelectionSid)
		throws com.stpl.app.exception.NoSuchChProjectionSelectionException {
		return getPersistence().remove(chProjectionSelectionSid);
	}

	public static ChProjectionSelection updateImpl(
		ChProjectionSelection chProjectionSelection) {
		return getPersistence().updateImpl(chProjectionSelection);
	}

	/**
	* Returns the ch projection selection with the primary key or throws a {@link NoSuchChProjectionSelectionException} if it could not be found.
	*
	* @param chProjectionSelectionSid the primary key of the ch projection selection
	* @return the ch projection selection
	* @throws NoSuchChProjectionSelectionException if a ch projection selection with the primary key could not be found
	*/
	public static ChProjectionSelection findByPrimaryKey(
		int chProjectionSelectionSid)
		throws com.stpl.app.exception.NoSuchChProjectionSelectionException {
		return getPersistence().findByPrimaryKey(chProjectionSelectionSid);
	}

	/**
	* Returns the ch projection selection with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param chProjectionSelectionSid the primary key of the ch projection selection
	* @return the ch projection selection, or <code>null</code> if a ch projection selection with the primary key could not be found
	*/
	public static ChProjectionSelection fetchByPrimaryKey(
		int chProjectionSelectionSid) {
		return getPersistence().fetchByPrimaryKey(chProjectionSelectionSid);
	}

	public static java.util.Map<java.io.Serializable, ChProjectionSelection> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ch projection selections.
	*
	* @return the ch projection selections
	*/
	public static List<ChProjectionSelection> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ch projection selections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch projection selections
	* @param end the upper bound of the range of ch projection selections (not inclusive)
	* @return the range of ch projection selections
	*/
	public static List<ChProjectionSelection> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ch projection selections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch projection selections
	* @param end the upper bound of the range of ch projection selections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ch projection selections
	*/
	public static List<ChProjectionSelection> findAll(int start, int end,
		OrderByComparator<ChProjectionSelection> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ch projection selections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch projection selections
	* @param end the upper bound of the range of ch projection selections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ch projection selections
	*/
	public static List<ChProjectionSelection> findAll(int start, int end,
		OrderByComparator<ChProjectionSelection> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ch projection selections from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ch projection selections.
	*
	* @return the number of ch projection selections
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ChProjectionSelectionPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ChProjectionSelectionPersistence, ChProjectionSelectionPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ChProjectionSelectionPersistence.class);
}