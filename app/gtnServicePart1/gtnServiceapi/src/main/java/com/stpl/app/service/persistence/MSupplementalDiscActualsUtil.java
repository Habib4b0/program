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

import com.stpl.app.model.MSupplementalDiscActuals;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the m supplemental disc actuals service. This utility wraps {@link com.stpl.app.service.persistence.impl.MSupplementalDiscActualsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MSupplementalDiscActualsPersistence
 * @see com.stpl.app.service.persistence.impl.MSupplementalDiscActualsPersistenceImpl
 * @generated
 */
@ProviderType
public class MSupplementalDiscActualsUtil {
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
		MSupplementalDiscActuals mSupplementalDiscActuals) {
		getPersistence().clearCache(mSupplementalDiscActuals);
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
	public static List<MSupplementalDiscActuals> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MSupplementalDiscActuals> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MSupplementalDiscActuals> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<MSupplementalDiscActuals> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static MSupplementalDiscActuals update(
		MSupplementalDiscActuals mSupplementalDiscActuals) {
		return getPersistence().update(mSupplementalDiscActuals);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static MSupplementalDiscActuals update(
		MSupplementalDiscActuals mSupplementalDiscActuals,
		ServiceContext serviceContext) {
		return getPersistence().update(mSupplementalDiscActuals, serviceContext);
	}

	/**
	* Caches the m supplemental disc actuals in the entity cache if it is enabled.
	*
	* @param mSupplementalDiscActuals the m supplemental disc actuals
	*/
	public static void cacheResult(
		MSupplementalDiscActuals mSupplementalDiscActuals) {
		getPersistence().cacheResult(mSupplementalDiscActuals);
	}

	/**
	* Caches the m supplemental disc actualses in the entity cache if it is enabled.
	*
	* @param mSupplementalDiscActualses the m supplemental disc actualses
	*/
	public static void cacheResult(
		List<MSupplementalDiscActuals> mSupplementalDiscActualses) {
		getPersistence().cacheResult(mSupplementalDiscActualses);
	}

	/**
	* Creates a new m supplemental disc actuals with the primary key. Does not add the m supplemental disc actuals to the database.
	*
	* @param mSupplementalDiscActualsPK the primary key for the new m supplemental disc actuals
	* @return the new m supplemental disc actuals
	*/
	public static MSupplementalDiscActuals create(
		MSupplementalDiscActualsPK mSupplementalDiscActualsPK) {
		return getPersistence().create(mSupplementalDiscActualsPK);
	}

	/**
	* Removes the m supplemental disc actuals with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param mSupplementalDiscActualsPK the primary key of the m supplemental disc actuals
	* @return the m supplemental disc actuals that was removed
	* @throws NoSuchMSupplementalDiscActualsException if a m supplemental disc actuals with the primary key could not be found
	*/
	public static MSupplementalDiscActuals remove(
		MSupplementalDiscActualsPK mSupplementalDiscActualsPK)
		throws com.stpl.app.exception.NoSuchMSupplementalDiscActualsException {
		return getPersistence().remove(mSupplementalDiscActualsPK);
	}

	public static MSupplementalDiscActuals updateImpl(
		MSupplementalDiscActuals mSupplementalDiscActuals) {
		return getPersistence().updateImpl(mSupplementalDiscActuals);
	}

	/**
	* Returns the m supplemental disc actuals with the primary key or throws a {@link NoSuchMSupplementalDiscActualsException} if it could not be found.
	*
	* @param mSupplementalDiscActualsPK the primary key of the m supplemental disc actuals
	* @return the m supplemental disc actuals
	* @throws NoSuchMSupplementalDiscActualsException if a m supplemental disc actuals with the primary key could not be found
	*/
	public static MSupplementalDiscActuals findByPrimaryKey(
		MSupplementalDiscActualsPK mSupplementalDiscActualsPK)
		throws com.stpl.app.exception.NoSuchMSupplementalDiscActualsException {
		return getPersistence().findByPrimaryKey(mSupplementalDiscActualsPK);
	}

	/**
	* Returns the m supplemental disc actuals with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param mSupplementalDiscActualsPK the primary key of the m supplemental disc actuals
	* @return the m supplemental disc actuals, or <code>null</code> if a m supplemental disc actuals with the primary key could not be found
	*/
	public static MSupplementalDiscActuals fetchByPrimaryKey(
		MSupplementalDiscActualsPK mSupplementalDiscActualsPK) {
		return getPersistence().fetchByPrimaryKey(mSupplementalDiscActualsPK);
	}

	public static java.util.Map<java.io.Serializable, MSupplementalDiscActuals> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the m supplemental disc actualses.
	*
	* @return the m supplemental disc actualses
	*/
	public static List<MSupplementalDiscActuals> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the m supplemental disc actualses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSupplementalDiscActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of m supplemental disc actualses
	* @param end the upper bound of the range of m supplemental disc actualses (not inclusive)
	* @return the range of m supplemental disc actualses
	*/
	public static List<MSupplementalDiscActuals> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the m supplemental disc actualses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSupplementalDiscActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of m supplemental disc actualses
	* @param end the upper bound of the range of m supplemental disc actualses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of m supplemental disc actualses
	*/
	public static List<MSupplementalDiscActuals> findAll(int start, int end,
		OrderByComparator<MSupplementalDiscActuals> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the m supplemental disc actualses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSupplementalDiscActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of m supplemental disc actualses
	* @param end the upper bound of the range of m supplemental disc actualses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of m supplemental disc actualses
	*/
	public static List<MSupplementalDiscActuals> findAll(int start, int end,
		OrderByComparator<MSupplementalDiscActuals> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the m supplemental disc actualses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of m supplemental disc actualses.
	*
	* @return the number of m supplemental disc actualses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static MSupplementalDiscActualsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MSupplementalDiscActualsPersistence, MSupplementalDiscActualsPersistence> _serviceTracker =
		ServiceTrackerFactory.open(MSupplementalDiscActualsPersistence.class);
}