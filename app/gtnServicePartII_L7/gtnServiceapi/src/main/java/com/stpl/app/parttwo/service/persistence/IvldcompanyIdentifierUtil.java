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

import com.stpl.app.parttwo.model.IvldCompanyIdentifier;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the ivld company identifier service. This utility wraps {@link com.stpl.app.parttwo.service.persistence.impl.IvldCompanyIdentifierPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldCompanyIdentifierPersistence
 * @see com.stpl.app.parttwo.service.persistence.impl.IvldCompanyIdentifierPersistenceImpl
 * @generated
 */
@ProviderType
public class IvldCompanyIdentifierUtil {
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
	public static void clearCache(IvldCompanyIdentifier ivldCompanyIdentifier) {
		getPersistence().clearCache(ivldCompanyIdentifier);
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
	public static List<IvldCompanyIdentifier> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<IvldCompanyIdentifier> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<IvldCompanyIdentifier> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<IvldCompanyIdentifier> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static IvldCompanyIdentifier update(
		IvldCompanyIdentifier ivldCompanyIdentifier) {
		return getPersistence().update(ivldCompanyIdentifier);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static IvldCompanyIdentifier update(
		IvldCompanyIdentifier ivldCompanyIdentifier,
		ServiceContext serviceContext) {
		return getPersistence().update(ivldCompanyIdentifier, serviceContext);
	}

	/**
	* Caches the ivld company identifier in the entity cache if it is enabled.
	*
	* @param ivldCompanyIdentifier the ivld company identifier
	*/
	public static void cacheResult(IvldCompanyIdentifier ivldCompanyIdentifier) {
		getPersistence().cacheResult(ivldCompanyIdentifier);
	}

	/**
	* Caches the ivld company identifiers in the entity cache if it is enabled.
	*
	* @param ivldCompanyIdentifiers the ivld company identifiers
	*/
	public static void cacheResult(
		List<IvldCompanyIdentifier> ivldCompanyIdentifiers) {
		getPersistence().cacheResult(ivldCompanyIdentifiers);
	}

	/**
	* Creates a new ivld company identifier with the primary key. Does not add the ivld company identifier to the database.
	*
	* @param ivldCompanyIdentifierSid the primary key for the new ivld company identifier
	* @return the new ivld company identifier
	*/
	public static IvldCompanyIdentifier create(int ivldCompanyIdentifierSid) {
		return getPersistence().create(ivldCompanyIdentifierSid);
	}

	/**
	* Removes the ivld company identifier with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldCompanyIdentifierSid the primary key of the ivld company identifier
	* @return the ivld company identifier that was removed
	* @throws NoSuchIvldCompanyIdentifierException if a ivld company identifier with the primary key could not be found
	*/
	public static IvldCompanyIdentifier remove(int ivldCompanyIdentifierSid)
		throws com.stpl.app.parttwo.exception.NoSuchIvldCompanyIdentifierException {
		return getPersistence().remove(ivldCompanyIdentifierSid);
	}

	public static IvldCompanyIdentifier updateImpl(
		IvldCompanyIdentifier ivldCompanyIdentifier) {
		return getPersistence().updateImpl(ivldCompanyIdentifier);
	}

	/**
	* Returns the ivld company identifier with the primary key or throws a {@link NoSuchIvldCompanyIdentifierException} if it could not be found.
	*
	* @param ivldCompanyIdentifierSid the primary key of the ivld company identifier
	* @return the ivld company identifier
	* @throws NoSuchIvldCompanyIdentifierException if a ivld company identifier with the primary key could not be found
	*/
	public static IvldCompanyIdentifier findByPrimaryKey(
		int ivldCompanyIdentifierSid)
		throws com.stpl.app.parttwo.exception.NoSuchIvldCompanyIdentifierException {
		return getPersistence().findByPrimaryKey(ivldCompanyIdentifierSid);
	}

	/**
	* Returns the ivld company identifier with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldCompanyIdentifierSid the primary key of the ivld company identifier
	* @return the ivld company identifier, or <code>null</code> if a ivld company identifier with the primary key could not be found
	*/
	public static IvldCompanyIdentifier fetchByPrimaryKey(
		int ivldCompanyIdentifierSid) {
		return getPersistence().fetchByPrimaryKey(ivldCompanyIdentifierSid);
	}

	public static java.util.Map<java.io.Serializable, IvldCompanyIdentifier> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ivld company identifiers.
	*
	* @return the ivld company identifiers
	*/
	public static List<IvldCompanyIdentifier> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ivld company identifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld company identifiers
	* @param end the upper bound of the range of ivld company identifiers (not inclusive)
	* @return the range of ivld company identifiers
	*/
	public static List<IvldCompanyIdentifier> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ivld company identifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld company identifiers
	* @param end the upper bound of the range of ivld company identifiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivld company identifiers
	*/
	public static List<IvldCompanyIdentifier> findAll(int start, int end,
		OrderByComparator<IvldCompanyIdentifier> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ivld company identifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld company identifiers
	* @param end the upper bound of the range of ivld company identifiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivld company identifiers
	*/
	public static List<IvldCompanyIdentifier> findAll(int start, int end,
		OrderByComparator<IvldCompanyIdentifier> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ivld company identifiers from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ivld company identifiers.
	*
	* @return the number of ivld company identifiers
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static IvldCompanyIdentifierPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<IvldCompanyIdentifierPersistence, IvldCompanyIdentifierPersistence> _serviceTracker =
		ServiceTrackerFactory.open(IvldCompanyIdentifierPersistence.class);
}