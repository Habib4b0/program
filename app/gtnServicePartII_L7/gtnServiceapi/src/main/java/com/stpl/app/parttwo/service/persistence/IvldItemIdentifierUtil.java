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

import com.stpl.app.parttwo.model.IvldItemIdentifier;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the ivld item identifier service. This utility wraps {@link com.stpl.app.parttwo.service.persistence.impl.IvldItemIdentifierPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldItemIdentifierPersistence
 * @see com.stpl.app.parttwo.service.persistence.impl.IvldItemIdentifierPersistenceImpl
 * @generated
 */
@ProviderType
public class IvldItemIdentifierUtil {
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
	public static void clearCache(IvldItemIdentifier ivldItemIdentifier) {
		getPersistence().clearCache(ivldItemIdentifier);
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
	public static List<IvldItemIdentifier> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<IvldItemIdentifier> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<IvldItemIdentifier> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<IvldItemIdentifier> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static IvldItemIdentifier update(
		IvldItemIdentifier ivldItemIdentifier) {
		return getPersistence().update(ivldItemIdentifier);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static IvldItemIdentifier update(
		IvldItemIdentifier ivldItemIdentifier, ServiceContext serviceContext) {
		return getPersistence().update(ivldItemIdentifier, serviceContext);
	}

	/**
	* Caches the ivld item identifier in the entity cache if it is enabled.
	*
	* @param ivldItemIdentifier the ivld item identifier
	*/
	public static void cacheResult(IvldItemIdentifier ivldItemIdentifier) {
		getPersistence().cacheResult(ivldItemIdentifier);
	}

	/**
	* Caches the ivld item identifiers in the entity cache if it is enabled.
	*
	* @param ivldItemIdentifiers the ivld item identifiers
	*/
	public static void cacheResult(List<IvldItemIdentifier> ivldItemIdentifiers) {
		getPersistence().cacheResult(ivldItemIdentifiers);
	}

	/**
	* Creates a new ivld item identifier with the primary key. Does not add the ivld item identifier to the database.
	*
	* @param ivldItemIdentifierSid the primary key for the new ivld item identifier
	* @return the new ivld item identifier
	*/
	public static IvldItemIdentifier create(int ivldItemIdentifierSid) {
		return getPersistence().create(ivldItemIdentifierSid);
	}

	/**
	* Removes the ivld item identifier with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldItemIdentifierSid the primary key of the ivld item identifier
	* @return the ivld item identifier that was removed
	* @throws NoSuchIvldItemIdentifierException if a ivld item identifier with the primary key could not be found
	*/
	public static IvldItemIdentifier remove(int ivldItemIdentifierSid)
		throws com.stpl.app.parttwo.exception.NoSuchIvldItemIdentifierException {
		return getPersistence().remove(ivldItemIdentifierSid);
	}

	public static IvldItemIdentifier updateImpl(
		IvldItemIdentifier ivldItemIdentifier) {
		return getPersistence().updateImpl(ivldItemIdentifier);
	}

	/**
	* Returns the ivld item identifier with the primary key or throws a {@link NoSuchIvldItemIdentifierException} if it could not be found.
	*
	* @param ivldItemIdentifierSid the primary key of the ivld item identifier
	* @return the ivld item identifier
	* @throws NoSuchIvldItemIdentifierException if a ivld item identifier with the primary key could not be found
	*/
	public static IvldItemIdentifier findByPrimaryKey(int ivldItemIdentifierSid)
		throws com.stpl.app.parttwo.exception.NoSuchIvldItemIdentifierException {
		return getPersistence().findByPrimaryKey(ivldItemIdentifierSid);
	}

	/**
	* Returns the ivld item identifier with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldItemIdentifierSid the primary key of the ivld item identifier
	* @return the ivld item identifier, or <code>null</code> if a ivld item identifier with the primary key could not be found
	*/
	public static IvldItemIdentifier fetchByPrimaryKey(
		int ivldItemIdentifierSid) {
		return getPersistence().fetchByPrimaryKey(ivldItemIdentifierSid);
	}

	public static java.util.Map<java.io.Serializable, IvldItemIdentifier> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ivld item identifiers.
	*
	* @return the ivld item identifiers
	*/
	public static List<IvldItemIdentifier> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ivld item identifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld item identifiers
	* @param end the upper bound of the range of ivld item identifiers (not inclusive)
	* @return the range of ivld item identifiers
	*/
	public static List<IvldItemIdentifier> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ivld item identifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld item identifiers
	* @param end the upper bound of the range of ivld item identifiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivld item identifiers
	*/
	public static List<IvldItemIdentifier> findAll(int start, int end,
		OrderByComparator<IvldItemIdentifier> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ivld item identifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld item identifiers
	* @param end the upper bound of the range of ivld item identifiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivld item identifiers
	*/
	public static List<IvldItemIdentifier> findAll(int start, int end,
		OrderByComparator<IvldItemIdentifier> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ivld item identifiers from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ivld item identifiers.
	*
	* @return the number of ivld item identifiers
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static IvldItemIdentifierPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<IvldItemIdentifierPersistence, IvldItemIdentifierPersistence> _serviceTracker =
		ServiceTrackerFactory.open(IvldItemIdentifierPersistence.class);
}