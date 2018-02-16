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

import com.stpl.app.parttwo.model.IvldCompanyTradeClass;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the ivld company trade class service. This utility wraps {@link com.stpl.app.parttwo.service.persistence.impl.IvldCompanyTradeClassPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldCompanyTradeClassPersistence
 * @see com.stpl.app.parttwo.service.persistence.impl.IvldCompanyTradeClassPersistenceImpl
 * @generated
 */
@ProviderType
public class IvldCompanyTradeClassUtil {
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
	public static void clearCache(IvldCompanyTradeClass ivldCompanyTradeClass) {
		getPersistence().clearCache(ivldCompanyTradeClass);
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
	public static List<IvldCompanyTradeClass> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<IvldCompanyTradeClass> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<IvldCompanyTradeClass> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<IvldCompanyTradeClass> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static IvldCompanyTradeClass update(
		IvldCompanyTradeClass ivldCompanyTradeClass) {
		return getPersistence().update(ivldCompanyTradeClass);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static IvldCompanyTradeClass update(
		IvldCompanyTradeClass ivldCompanyTradeClass,
		ServiceContext serviceContext) {
		return getPersistence().update(ivldCompanyTradeClass, serviceContext);
	}

	/**
	* Caches the ivld company trade class in the entity cache if it is enabled.
	*
	* @param ivldCompanyTradeClass the ivld company trade class
	*/
	public static void cacheResult(IvldCompanyTradeClass ivldCompanyTradeClass) {
		getPersistence().cacheResult(ivldCompanyTradeClass);
	}

	/**
	* Caches the ivld company trade classes in the entity cache if it is enabled.
	*
	* @param ivldCompanyTradeClasses the ivld company trade classes
	*/
	public static void cacheResult(
		List<IvldCompanyTradeClass> ivldCompanyTradeClasses) {
		getPersistence().cacheResult(ivldCompanyTradeClasses);
	}

	/**
	* Creates a new ivld company trade class with the primary key. Does not add the ivld company trade class to the database.
	*
	* @param ivldCompanyTradeClassSid the primary key for the new ivld company trade class
	* @return the new ivld company trade class
	*/
	public static IvldCompanyTradeClass create(int ivldCompanyTradeClassSid) {
		return getPersistence().create(ivldCompanyTradeClassSid);
	}

	/**
	* Removes the ivld company trade class with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldCompanyTradeClassSid the primary key of the ivld company trade class
	* @return the ivld company trade class that was removed
	* @throws NoSuchIvldCompanyTradeClassException if a ivld company trade class with the primary key could not be found
	*/
	public static IvldCompanyTradeClass remove(int ivldCompanyTradeClassSid)
		throws com.stpl.app.parttwo.exception.NoSuchIvldCompanyTradeClassException {
		return getPersistence().remove(ivldCompanyTradeClassSid);
	}

	public static IvldCompanyTradeClass updateImpl(
		IvldCompanyTradeClass ivldCompanyTradeClass) {
		return getPersistence().updateImpl(ivldCompanyTradeClass);
	}

	/**
	* Returns the ivld company trade class with the primary key or throws a {@link NoSuchIvldCompanyTradeClassException} if it could not be found.
	*
	* @param ivldCompanyTradeClassSid the primary key of the ivld company trade class
	* @return the ivld company trade class
	* @throws NoSuchIvldCompanyTradeClassException if a ivld company trade class with the primary key could not be found
	*/
	public static IvldCompanyTradeClass findByPrimaryKey(
		int ivldCompanyTradeClassSid)
		throws com.stpl.app.parttwo.exception.NoSuchIvldCompanyTradeClassException {
		return getPersistence().findByPrimaryKey(ivldCompanyTradeClassSid);
	}

	/**
	* Returns the ivld company trade class with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldCompanyTradeClassSid the primary key of the ivld company trade class
	* @return the ivld company trade class, or <code>null</code> if a ivld company trade class with the primary key could not be found
	*/
	public static IvldCompanyTradeClass fetchByPrimaryKey(
		int ivldCompanyTradeClassSid) {
		return getPersistence().fetchByPrimaryKey(ivldCompanyTradeClassSid);
	}

	public static java.util.Map<java.io.Serializable, IvldCompanyTradeClass> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ivld company trade classes.
	*
	* @return the ivld company trade classes
	*/
	public static List<IvldCompanyTradeClass> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ivld company trade classes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld company trade classes
	* @param end the upper bound of the range of ivld company trade classes (not inclusive)
	* @return the range of ivld company trade classes
	*/
	public static List<IvldCompanyTradeClass> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ivld company trade classes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld company trade classes
	* @param end the upper bound of the range of ivld company trade classes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivld company trade classes
	*/
	public static List<IvldCompanyTradeClass> findAll(int start, int end,
		OrderByComparator<IvldCompanyTradeClass> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ivld company trade classes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld company trade classes
	* @param end the upper bound of the range of ivld company trade classes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivld company trade classes
	*/
	public static List<IvldCompanyTradeClass> findAll(int start, int end,
		OrderByComparator<IvldCompanyTradeClass> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ivld company trade classes from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ivld company trade classes.
	*
	* @return the number of ivld company trade classes
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static IvldCompanyTradeClassPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<IvldCompanyTradeClassPersistence, IvldCompanyTradeClassPersistence> _serviceTracker =
		ServiceTrackerFactory.open(IvldCompanyTradeClassPersistence.class);
}