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

import com.stpl.app.model.MedicaidUraActuals;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the medicaid ura actuals service. This utility wraps {@link com.stpl.app.service.persistence.impl.MedicaidUraActualsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MedicaidUraActualsPersistence
 * @see com.stpl.app.service.persistence.impl.MedicaidUraActualsPersistenceImpl
 * @generated
 */
@ProviderType
public class MedicaidUraActualsUtil {
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
	public static void clearCache(MedicaidUraActuals medicaidUraActuals) {
		getPersistence().clearCache(medicaidUraActuals);
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
	public static List<MedicaidUraActuals> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MedicaidUraActuals> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MedicaidUraActuals> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<MedicaidUraActuals> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static MedicaidUraActuals update(
		MedicaidUraActuals medicaidUraActuals) {
		return getPersistence().update(medicaidUraActuals);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static MedicaidUraActuals update(
		MedicaidUraActuals medicaidUraActuals, ServiceContext serviceContext) {
		return getPersistence().update(medicaidUraActuals, serviceContext);
	}

	/**
	* Caches the medicaid ura actuals in the entity cache if it is enabled.
	*
	* @param medicaidUraActuals the medicaid ura actuals
	*/
	public static void cacheResult(MedicaidUraActuals medicaidUraActuals) {
		getPersistence().cacheResult(medicaidUraActuals);
	}

	/**
	* Caches the medicaid ura actualses in the entity cache if it is enabled.
	*
	* @param medicaidUraActualses the medicaid ura actualses
	*/
	public static void cacheResult(
		List<MedicaidUraActuals> medicaidUraActualses) {
		getPersistence().cacheResult(medicaidUraActualses);
	}

	/**
	* Creates a new medicaid ura actuals with the primary key. Does not add the medicaid ura actuals to the database.
	*
	* @param medicaidUraActualsPK the primary key for the new medicaid ura actuals
	* @return the new medicaid ura actuals
	*/
	public static MedicaidUraActuals create(
		MedicaidUraActualsPK medicaidUraActualsPK) {
		return getPersistence().create(medicaidUraActualsPK);
	}

	/**
	* Removes the medicaid ura actuals with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param medicaidUraActualsPK the primary key of the medicaid ura actuals
	* @return the medicaid ura actuals that was removed
	* @throws NoSuchMedicaidUraActualsException if a medicaid ura actuals with the primary key could not be found
	*/
	public static MedicaidUraActuals remove(
		MedicaidUraActualsPK medicaidUraActualsPK)
		throws com.stpl.app.exception.NoSuchMedicaidUraActualsException {
		return getPersistence().remove(medicaidUraActualsPK);
	}

	public static MedicaidUraActuals updateImpl(
		MedicaidUraActuals medicaidUraActuals) {
		return getPersistence().updateImpl(medicaidUraActuals);
	}

	/**
	* Returns the medicaid ura actuals with the primary key or throws a {@link NoSuchMedicaidUraActualsException} if it could not be found.
	*
	* @param medicaidUraActualsPK the primary key of the medicaid ura actuals
	* @return the medicaid ura actuals
	* @throws NoSuchMedicaidUraActualsException if a medicaid ura actuals with the primary key could not be found
	*/
	public static MedicaidUraActuals findByPrimaryKey(
		MedicaidUraActualsPK medicaidUraActualsPK)
		throws com.stpl.app.exception.NoSuchMedicaidUraActualsException {
		return getPersistence().findByPrimaryKey(medicaidUraActualsPK);
	}

	/**
	* Returns the medicaid ura actuals with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param medicaidUraActualsPK the primary key of the medicaid ura actuals
	* @return the medicaid ura actuals, or <code>null</code> if a medicaid ura actuals with the primary key could not be found
	*/
	public static MedicaidUraActuals fetchByPrimaryKey(
		MedicaidUraActualsPK medicaidUraActualsPK) {
		return getPersistence().fetchByPrimaryKey(medicaidUraActualsPK);
	}

	public static java.util.Map<java.io.Serializable, MedicaidUraActuals> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the medicaid ura actualses.
	*
	* @return the medicaid ura actualses
	*/
	public static List<MedicaidUraActuals> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the medicaid ura actualses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MedicaidUraActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of medicaid ura actualses
	* @param end the upper bound of the range of medicaid ura actualses (not inclusive)
	* @return the range of medicaid ura actualses
	*/
	public static List<MedicaidUraActuals> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the medicaid ura actualses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MedicaidUraActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of medicaid ura actualses
	* @param end the upper bound of the range of medicaid ura actualses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of medicaid ura actualses
	*/
	public static List<MedicaidUraActuals> findAll(int start, int end,
		OrderByComparator<MedicaidUraActuals> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the medicaid ura actualses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MedicaidUraActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of medicaid ura actualses
	* @param end the upper bound of the range of medicaid ura actualses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of medicaid ura actualses
	*/
	public static List<MedicaidUraActuals> findAll(int start, int end,
		OrderByComparator<MedicaidUraActuals> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the medicaid ura actualses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of medicaid ura actualses.
	*
	* @return the number of medicaid ura actualses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static MedicaidUraActualsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MedicaidUraActualsPersistence, MedicaidUraActualsPersistence> _serviceTracker =
		ServiceTrackerFactory.open(MedicaidUraActualsPersistence.class);
}