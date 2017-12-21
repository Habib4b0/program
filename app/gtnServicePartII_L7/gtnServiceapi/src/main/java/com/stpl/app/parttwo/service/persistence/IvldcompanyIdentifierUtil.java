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

import com.stpl.app.parttwo.model.IvldcompanyIdentifier;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the ivldcompany identifier service. This utility wraps {@link com.stpl.app.parttwo.service.persistence.impl.IvldcompanyIdentifierPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldcompanyIdentifierPersistence
 * @see com.stpl.app.parttwo.service.persistence.impl.IvldcompanyIdentifierPersistenceImpl
 * @generated
 */
@ProviderType
public class IvldcompanyIdentifierUtil {
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
	public static void clearCache(IvldcompanyIdentifier ivldcompanyIdentifier) {
		getPersistence().clearCache(ivldcompanyIdentifier);
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
	public static List<IvldcompanyIdentifier> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<IvldcompanyIdentifier> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<IvldcompanyIdentifier> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<IvldcompanyIdentifier> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static IvldcompanyIdentifier update(
		IvldcompanyIdentifier ivldcompanyIdentifier) {
		return getPersistence().update(ivldcompanyIdentifier);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static IvldcompanyIdentifier update(
		IvldcompanyIdentifier ivldcompanyIdentifier,
		ServiceContext serviceContext) {
		return getPersistence().update(ivldcompanyIdentifier, serviceContext);
	}

	/**
	* Caches the ivldcompany identifier in the entity cache if it is enabled.
	*
	* @param ivldcompanyIdentifier the ivldcompany identifier
	*/
	public static void cacheResult(IvldcompanyIdentifier ivldcompanyIdentifier) {
		getPersistence().cacheResult(ivldcompanyIdentifier);
	}

	/**
	* Caches the ivldcompany identifiers in the entity cache if it is enabled.
	*
	* @param ivldcompanyIdentifiers the ivldcompany identifiers
	*/
	public static void cacheResult(
		List<IvldcompanyIdentifier> ivldcompanyIdentifiers) {
		getPersistence().cacheResult(ivldcompanyIdentifiers);
	}

	/**
	* Creates a new ivldcompany identifier with the primary key. Does not add the ivldcompany identifier to the database.
	*
	* @param ivldcompanyIdentifierSid the primary key for the new ivldcompany identifier
	* @return the new ivldcompany identifier
	*/
	public static IvldcompanyIdentifier create(int ivldcompanyIdentifierSid) {
		return getPersistence().create(ivldcompanyIdentifierSid);
	}

	/**
	* Removes the ivldcompany identifier with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldcompanyIdentifierSid the primary key of the ivldcompany identifier
	* @return the ivldcompany identifier that was removed
	* @throws NoSuchIvldcompanyIdentifierException if a ivldcompany identifier with the primary key could not be found
	*/
	public static IvldcompanyIdentifier remove(int ivldcompanyIdentifierSid)
		throws com.stpl.app.parttwo.exception.NoSuchIvldcompanyIdentifierException {
		return getPersistence().remove(ivldcompanyIdentifierSid);
	}

	public static IvldcompanyIdentifier updateImpl(
		IvldcompanyIdentifier ivldcompanyIdentifier) {
		return getPersistence().updateImpl(ivldcompanyIdentifier);
	}

	/**
	* Returns the ivldcompany identifier with the primary key or throws a {@link NoSuchIvldcompanyIdentifierException} if it could not be found.
	*
	* @param ivldcompanyIdentifierSid the primary key of the ivldcompany identifier
	* @return the ivldcompany identifier
	* @throws NoSuchIvldcompanyIdentifierException if a ivldcompany identifier with the primary key could not be found
	*/
	public static IvldcompanyIdentifier findByPrimaryKey(
		int ivldcompanyIdentifierSid)
		throws com.stpl.app.parttwo.exception.NoSuchIvldcompanyIdentifierException {
		return getPersistence().findByPrimaryKey(ivldcompanyIdentifierSid);
	}

	/**
	* Returns the ivldcompany identifier with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldcompanyIdentifierSid the primary key of the ivldcompany identifier
	* @return the ivldcompany identifier, or <code>null</code> if a ivldcompany identifier with the primary key could not be found
	*/
	public static IvldcompanyIdentifier fetchByPrimaryKey(
		int ivldcompanyIdentifierSid) {
		return getPersistence().fetchByPrimaryKey(ivldcompanyIdentifierSid);
	}

	public static java.util.Map<java.io.Serializable, IvldcompanyIdentifier> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ivldcompany identifiers.
	*
	* @return the ivldcompany identifiers
	*/
	public static List<IvldcompanyIdentifier> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ivldcompany identifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldcompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivldcompany identifiers
	* @param end the upper bound of the range of ivldcompany identifiers (not inclusive)
	* @return the range of ivldcompany identifiers
	*/
	public static List<IvldcompanyIdentifier> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ivldcompany identifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldcompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivldcompany identifiers
	* @param end the upper bound of the range of ivldcompany identifiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivldcompany identifiers
	*/
	public static List<IvldcompanyIdentifier> findAll(int start, int end,
		OrderByComparator<IvldcompanyIdentifier> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ivldcompany identifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldcompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivldcompany identifiers
	* @param end the upper bound of the range of ivldcompany identifiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivldcompany identifiers
	*/
	public static List<IvldcompanyIdentifier> findAll(int start, int end,
		OrderByComparator<IvldcompanyIdentifier> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ivldcompany identifiers from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ivldcompany identifiers.
	*
	* @return the number of ivldcompany identifiers
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static IvldcompanyIdentifierPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<IvldcompanyIdentifierPersistence, IvldcompanyIdentifierPersistence> _serviceTracker =
		ServiceTrackerFactory.open(IvldcompanyIdentifierPersistence.class);
}