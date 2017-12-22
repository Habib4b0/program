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

package com.stpl.app.parttwo.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.stpl.app.parttwo.model.AcFdAdjustmentSelection;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the ac fd adjustment selection service. This utility wraps {@link com.stpl.app.parttwo.service.persistence.impl.AcFdAdjustmentSelectionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AcFdAdjustmentSelectionPersistence
 * @see com.stpl.app.parttwo.service.persistence.impl.AcFdAdjustmentSelectionPersistenceImpl
 * @generated
 */
@ProviderType
public class AcFdAdjustmentSelectionUtil {
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
	public static void clearCache(
		AcFdAdjustmentSelection acFdAdjustmentSelection) {
		getPersistence().clearCache(acFdAdjustmentSelection);
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
	public static List<AcFdAdjustmentSelection> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AcFdAdjustmentSelection> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AcFdAdjustmentSelection> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AcFdAdjustmentSelection> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AcFdAdjustmentSelection update(
		AcFdAdjustmentSelection acFdAdjustmentSelection) {
		return getPersistence().update(acFdAdjustmentSelection);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AcFdAdjustmentSelection update(
		AcFdAdjustmentSelection acFdAdjustmentSelection,
		ServiceContext serviceContext) {
		return getPersistence().update(acFdAdjustmentSelection, serviceContext);
	}

	/**
	* Caches the ac fd adjustment selection in the entity cache if it is enabled.
	*
	* @param acFdAdjustmentSelection the ac fd adjustment selection
	*/
	public static void cacheResult(
		AcFdAdjustmentSelection acFdAdjustmentSelection) {
		getPersistence().cacheResult(acFdAdjustmentSelection);
	}

	/**
	* Caches the ac fd adjustment selections in the entity cache if it is enabled.
	*
	* @param acFdAdjustmentSelections the ac fd adjustment selections
	*/
	public static void cacheResult(
		List<AcFdAdjustmentSelection> acFdAdjustmentSelections) {
		getPersistence().cacheResult(acFdAdjustmentSelections);
	}

	/**
	* Creates a new ac fd adjustment selection with the primary key. Does not add the ac fd adjustment selection to the database.
	*
	* @param accClosureMasterSid the primary key for the new ac fd adjustment selection
	* @return the new ac fd adjustment selection
	*/
	public static AcFdAdjustmentSelection create(int accClosureMasterSid) {
		return getPersistence().create(accClosureMasterSid);
	}

	/**
	* Removes the ac fd adjustment selection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accClosureMasterSid the primary key of the ac fd adjustment selection
	* @return the ac fd adjustment selection that was removed
	* @throws NoSuchAcFdAdjustmentSelectionException if a ac fd adjustment selection with the primary key could not be found
	*/
	public static AcFdAdjustmentSelection remove(int accClosureMasterSid)
		throws com.stpl.app.parttwo.exception.NoSuchAcFdAdjustmentSelectionException {
		return getPersistence().remove(accClosureMasterSid);
	}

	public static AcFdAdjustmentSelection updateImpl(
		AcFdAdjustmentSelection acFdAdjustmentSelection) {
		return getPersistence().updateImpl(acFdAdjustmentSelection);
	}

	/**
	* Returns the ac fd adjustment selection with the primary key or throws a {@link NoSuchAcFdAdjustmentSelectionException} if it could not be found.
	*
	* @param accClosureMasterSid the primary key of the ac fd adjustment selection
	* @return the ac fd adjustment selection
	* @throws NoSuchAcFdAdjustmentSelectionException if a ac fd adjustment selection with the primary key could not be found
	*/
	public static AcFdAdjustmentSelection findByPrimaryKey(
		int accClosureMasterSid)
		throws com.stpl.app.parttwo.exception.NoSuchAcFdAdjustmentSelectionException {
		return getPersistence().findByPrimaryKey(accClosureMasterSid);
	}

	/**
	* Returns the ac fd adjustment selection with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accClosureMasterSid the primary key of the ac fd adjustment selection
	* @return the ac fd adjustment selection, or <code>null</code> if a ac fd adjustment selection with the primary key could not be found
	*/
	public static AcFdAdjustmentSelection fetchByPrimaryKey(
		int accClosureMasterSid) {
		return getPersistence().fetchByPrimaryKey(accClosureMasterSid);
	}

	public static java.util.Map<java.io.Serializable, AcFdAdjustmentSelection> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ac fd adjustment selections.
	*
	* @return the ac fd adjustment selections
	*/
	public static List<AcFdAdjustmentSelection> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ac fd adjustment selections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AcFdAdjustmentSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ac fd adjustment selections
	* @param end the upper bound of the range of ac fd adjustment selections (not inclusive)
	* @return the range of ac fd adjustment selections
	*/
	public static List<AcFdAdjustmentSelection> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ac fd adjustment selections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AcFdAdjustmentSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ac fd adjustment selections
	* @param end the upper bound of the range of ac fd adjustment selections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ac fd adjustment selections
	*/
	public static List<AcFdAdjustmentSelection> findAll(int start, int end,
		OrderByComparator<AcFdAdjustmentSelection> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ac fd adjustment selections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AcFdAdjustmentSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ac fd adjustment selections
	* @param end the upper bound of the range of ac fd adjustment selections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ac fd adjustment selections
	*/
	public static List<AcFdAdjustmentSelection> findAll(int start, int end,
		OrderByComparator<AcFdAdjustmentSelection> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ac fd adjustment selections from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ac fd adjustment selections.
	*
	* @return the number of ac fd adjustment selections
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static AcFdAdjustmentSelectionPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AcFdAdjustmentSelectionPersistence, AcFdAdjustmentSelectionPersistence> _serviceTracker =
		ServiceTrackerFactory.open(AcFdAdjustmentSelectionPersistence.class);
}