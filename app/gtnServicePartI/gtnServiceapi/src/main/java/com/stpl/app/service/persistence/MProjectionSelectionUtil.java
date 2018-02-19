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

import com.stpl.app.model.MProjectionSelection;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the m projection selection service. This utility wraps {@link com.stpl.app.service.persistence.impl.MProjectionSelectionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MProjectionSelectionPersistence
 * @see com.stpl.app.service.persistence.impl.MProjectionSelectionPersistenceImpl
 * @generated
 */
@ProviderType
public class MProjectionSelectionUtil {
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
	public static void clearCache(MProjectionSelection mProjectionSelection) {
		getPersistence().clearCache(mProjectionSelection);
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
	public static List<MProjectionSelection> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MProjectionSelection> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MProjectionSelection> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<MProjectionSelection> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static MProjectionSelection update(
		MProjectionSelection mProjectionSelection) {
		return getPersistence().update(mProjectionSelection);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static MProjectionSelection update(
		MProjectionSelection mProjectionSelection, ServiceContext serviceContext) {
		return getPersistence().update(mProjectionSelection, serviceContext);
	}

	/**
	* Caches the m projection selection in the entity cache if it is enabled.
	*
	* @param mProjectionSelection the m projection selection
	*/
	public static void cacheResult(MProjectionSelection mProjectionSelection) {
		getPersistence().cacheResult(mProjectionSelection);
	}

	/**
	* Caches the m projection selections in the entity cache if it is enabled.
	*
	* @param mProjectionSelections the m projection selections
	*/
	public static void cacheResult(
		List<MProjectionSelection> mProjectionSelections) {
		getPersistence().cacheResult(mProjectionSelections);
	}

	/**
	* Creates a new m projection selection with the primary key. Does not add the m projection selection to the database.
	*
	* @param mProjectionSelectionSid the primary key for the new m projection selection
	* @return the new m projection selection
	*/
	public static MProjectionSelection create(int mProjectionSelectionSid) {
		return getPersistence().create(mProjectionSelectionSid);
	}

	/**
	* Removes the m projection selection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param mProjectionSelectionSid the primary key of the m projection selection
	* @return the m projection selection that was removed
	* @throws NoSuchMProjectionSelectionException if a m projection selection with the primary key could not be found
	*/
	public static MProjectionSelection remove(int mProjectionSelectionSid)
		throws com.stpl.app.exception.NoSuchMProjectionSelectionException {
		return getPersistence().remove(mProjectionSelectionSid);
	}

	public static MProjectionSelection updateImpl(
		MProjectionSelection mProjectionSelection) {
		return getPersistence().updateImpl(mProjectionSelection);
	}

	/**
	* Returns the m projection selection with the primary key or throws a {@link NoSuchMProjectionSelectionException} if it could not be found.
	*
	* @param mProjectionSelectionSid the primary key of the m projection selection
	* @return the m projection selection
	* @throws NoSuchMProjectionSelectionException if a m projection selection with the primary key could not be found
	*/
	public static MProjectionSelection findByPrimaryKey(
		int mProjectionSelectionSid)
		throws com.stpl.app.exception.NoSuchMProjectionSelectionException {
		return getPersistence().findByPrimaryKey(mProjectionSelectionSid);
	}

	/**
	* Returns the m projection selection with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param mProjectionSelectionSid the primary key of the m projection selection
	* @return the m projection selection, or <code>null</code> if a m projection selection with the primary key could not be found
	*/
	public static MProjectionSelection fetchByPrimaryKey(
		int mProjectionSelectionSid) {
		return getPersistence().fetchByPrimaryKey(mProjectionSelectionSid);
	}

	public static java.util.Map<java.io.Serializable, MProjectionSelection> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the m projection selections.
	*
	* @return the m projection selections
	*/
	public static List<MProjectionSelection> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the m projection selections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of m projection selections
	* @param end the upper bound of the range of m projection selections (not inclusive)
	* @return the range of m projection selections
	*/
	public static List<MProjectionSelection> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the m projection selections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of m projection selections
	* @param end the upper bound of the range of m projection selections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of m projection selections
	*/
	public static List<MProjectionSelection> findAll(int start, int end,
		OrderByComparator<MProjectionSelection> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the m projection selections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of m projection selections
	* @param end the upper bound of the range of m projection selections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of m projection selections
	*/
	public static List<MProjectionSelection> findAll(int start, int end,
		OrderByComparator<MProjectionSelection> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the m projection selections from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of m projection selections.
	*
	* @return the number of m projection selections
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static MProjectionSelectionPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MProjectionSelectionPersistence, MProjectionSelectionPersistence> _serviceTracker =
		ServiceTrackerFactory.open(MProjectionSelectionPersistence.class);
}