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

import com.stpl.app.model.NmProjectionSelection;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the nm projection selection service. This utility wraps {@link com.stpl.app.service.persistence.impl.NmProjectionSelectionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NmProjectionSelectionPersistence
 * @see com.stpl.app.service.persistence.impl.NmProjectionSelectionPersistenceImpl
 * @generated
 */
@ProviderType
public class NmProjectionSelectionUtil {
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
	public static void clearCache(NmProjectionSelection nmProjectionSelection) {
		getPersistence().clearCache(nmProjectionSelection);
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
	public static List<NmProjectionSelection> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<NmProjectionSelection> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<NmProjectionSelection> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<NmProjectionSelection> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static NmProjectionSelection update(
		NmProjectionSelection nmProjectionSelection) {
		return getPersistence().update(nmProjectionSelection);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static NmProjectionSelection update(
		NmProjectionSelection nmProjectionSelection,
		ServiceContext serviceContext) {
		return getPersistence().update(nmProjectionSelection, serviceContext);
	}

	/**
	* Caches the nm projection selection in the entity cache if it is enabled.
	*
	* @param nmProjectionSelection the nm projection selection
	*/
	public static void cacheResult(NmProjectionSelection nmProjectionSelection) {
		getPersistence().cacheResult(nmProjectionSelection);
	}

	/**
	* Caches the nm projection selections in the entity cache if it is enabled.
	*
	* @param nmProjectionSelections the nm projection selections
	*/
	public static void cacheResult(
		List<NmProjectionSelection> nmProjectionSelections) {
		getPersistence().cacheResult(nmProjectionSelections);
	}

	/**
	* Creates a new nm projection selection with the primary key. Does not add the nm projection selection to the database.
	*
	* @param nmProjectionSelectionSid the primary key for the new nm projection selection
	* @return the new nm projection selection
	*/
	public static NmProjectionSelection create(int nmProjectionSelectionSid) {
		return getPersistence().create(nmProjectionSelectionSid);
	}

	/**
	* Removes the nm projection selection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param nmProjectionSelectionSid the primary key of the nm projection selection
	* @return the nm projection selection that was removed
	* @throws NoSuchNmProjectionSelectionException if a nm projection selection with the primary key could not be found
	*/
	public static NmProjectionSelection remove(int nmProjectionSelectionSid)
		throws com.stpl.app.exception.NoSuchNmProjectionSelectionException {
		return getPersistence().remove(nmProjectionSelectionSid);
	}

	public static NmProjectionSelection updateImpl(
		NmProjectionSelection nmProjectionSelection) {
		return getPersistence().updateImpl(nmProjectionSelection);
	}

	/**
	* Returns the nm projection selection with the primary key or throws a {@link NoSuchNmProjectionSelectionException} if it could not be found.
	*
	* @param nmProjectionSelectionSid the primary key of the nm projection selection
	* @return the nm projection selection
	* @throws NoSuchNmProjectionSelectionException if a nm projection selection with the primary key could not be found
	*/
	public static NmProjectionSelection findByPrimaryKey(
		int nmProjectionSelectionSid)
		throws com.stpl.app.exception.NoSuchNmProjectionSelectionException {
		return getPersistence().findByPrimaryKey(nmProjectionSelectionSid);
	}

	/**
	* Returns the nm projection selection with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param nmProjectionSelectionSid the primary key of the nm projection selection
	* @return the nm projection selection, or <code>null</code> if a nm projection selection with the primary key could not be found
	*/
	public static NmProjectionSelection fetchByPrimaryKey(
		int nmProjectionSelectionSid) {
		return getPersistence().fetchByPrimaryKey(nmProjectionSelectionSid);
	}

	public static java.util.Map<java.io.Serializable, NmProjectionSelection> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the nm projection selections.
	*
	* @return the nm projection selections
	*/
	public static List<NmProjectionSelection> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the nm projection selections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm projection selections
	* @param end the upper bound of the range of nm projection selections (not inclusive)
	* @return the range of nm projection selections
	*/
	public static List<NmProjectionSelection> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the nm projection selections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm projection selections
	* @param end the upper bound of the range of nm projection selections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of nm projection selections
	*/
	public static List<NmProjectionSelection> findAll(int start, int end,
		OrderByComparator<NmProjectionSelection> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the nm projection selections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm projection selections
	* @param end the upper bound of the range of nm projection selections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of nm projection selections
	*/
	public static List<NmProjectionSelection> findAll(int start, int end,
		OrderByComparator<NmProjectionSelection> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the nm projection selections from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of nm projection selections.
	*
	* @return the number of nm projection selections
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static NmProjectionSelectionPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<NmProjectionSelectionPersistence, NmProjectionSelectionPersistence> _serviceTracker =
		ServiceTrackerFactory.open(NmProjectionSelectionPersistence.class);
}