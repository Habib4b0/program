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

import com.stpl.app.parttwo.model.VwCompanyTradeClass;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the vw company trade class service. This utility wraps {@link com.stpl.app.parttwo.service.persistence.impl.VwCompanyTradeClassPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwCompanyTradeClassPersistence
 * @see com.stpl.app.parttwo.service.persistence.impl.VwCompanyTradeClassPersistenceImpl
 * @generated
 */
@ProviderType
public class VwCompanyTradeClassUtil {
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
	public static void clearCache(VwCompanyTradeClass vwCompanyTradeClass) {
		getPersistence().clearCache(vwCompanyTradeClass);
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
	public static List<VwCompanyTradeClass> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<VwCompanyTradeClass> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<VwCompanyTradeClass> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<VwCompanyTradeClass> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static VwCompanyTradeClass update(
		VwCompanyTradeClass vwCompanyTradeClass) {
		return getPersistence().update(vwCompanyTradeClass);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static VwCompanyTradeClass update(
		VwCompanyTradeClass vwCompanyTradeClass, ServiceContext serviceContext) {
		return getPersistence().update(vwCompanyTradeClass, serviceContext);
	}

	/**
	* Caches the vw company trade class in the entity cache if it is enabled.
	*
	* @param vwCompanyTradeClass the vw company trade class
	*/
	public static void cacheResult(VwCompanyTradeClass vwCompanyTradeClass) {
		getPersistence().cacheResult(vwCompanyTradeClass);
	}

	/**
	* Caches the vw company trade classes in the entity cache if it is enabled.
	*
	* @param vwCompanyTradeClasses the vw company trade classes
	*/
	public static void cacheResult(
		List<VwCompanyTradeClass> vwCompanyTradeClasses) {
		getPersistence().cacheResult(vwCompanyTradeClasses);
	}

	/**
	* Creates a new vw company trade class with the primary key. Does not add the vw company trade class to the database.
	*
	* @param companyTradeClassSid the primary key for the new vw company trade class
	* @return the new vw company trade class
	*/
	public static VwCompanyTradeClass create(int companyTradeClassSid) {
		return getPersistence().create(companyTradeClassSid);
	}

	/**
	* Removes the vw company trade class with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param companyTradeClassSid the primary key of the vw company trade class
	* @return the vw company trade class that was removed
	* @throws NoSuchVwCompanyTradeClassException if a vw company trade class with the primary key could not be found
	*/
	public static VwCompanyTradeClass remove(int companyTradeClassSid)
		throws com.stpl.app.parttwo.exception.NoSuchVwCompanyTradeClassException {
		return getPersistence().remove(companyTradeClassSid);
	}

	public static VwCompanyTradeClass updateImpl(
		VwCompanyTradeClass vwCompanyTradeClass) {
		return getPersistence().updateImpl(vwCompanyTradeClass);
	}

	/**
	* Returns the vw company trade class with the primary key or throws a {@link NoSuchVwCompanyTradeClassException} if it could not be found.
	*
	* @param companyTradeClassSid the primary key of the vw company trade class
	* @return the vw company trade class
	* @throws NoSuchVwCompanyTradeClassException if a vw company trade class with the primary key could not be found
	*/
	public static VwCompanyTradeClass findByPrimaryKey(int companyTradeClassSid)
		throws com.stpl.app.parttwo.exception.NoSuchVwCompanyTradeClassException {
		return getPersistence().findByPrimaryKey(companyTradeClassSid);
	}

	/**
	* Returns the vw company trade class with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param companyTradeClassSid the primary key of the vw company trade class
	* @return the vw company trade class, or <code>null</code> if a vw company trade class with the primary key could not be found
	*/
	public static VwCompanyTradeClass fetchByPrimaryKey(
		int companyTradeClassSid) {
		return getPersistence().fetchByPrimaryKey(companyTradeClassSid);
	}

	public static java.util.Map<java.io.Serializable, VwCompanyTradeClass> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the vw company trade classes.
	*
	* @return the vw company trade classes
	*/
	public static List<VwCompanyTradeClass> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the vw company trade classes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwCompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw company trade classes
	* @param end the upper bound of the range of vw company trade classes (not inclusive)
	* @return the range of vw company trade classes
	*/
	public static List<VwCompanyTradeClass> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the vw company trade classes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwCompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw company trade classes
	* @param end the upper bound of the range of vw company trade classes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of vw company trade classes
	*/
	public static List<VwCompanyTradeClass> findAll(int start, int end,
		OrderByComparator<VwCompanyTradeClass> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the vw company trade classes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwCompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw company trade classes
	* @param end the upper bound of the range of vw company trade classes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of vw company trade classes
	*/
	public static List<VwCompanyTradeClass> findAll(int start, int end,
		OrderByComparator<VwCompanyTradeClass> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the vw company trade classes from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of vw company trade classes.
	*
	* @return the number of vw company trade classes
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static VwCompanyTradeClassPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<VwCompanyTradeClassPersistence, VwCompanyTradeClassPersistence> _serviceTracker =
		ServiceTrackerFactory.open(VwCompanyTradeClassPersistence.class);
}