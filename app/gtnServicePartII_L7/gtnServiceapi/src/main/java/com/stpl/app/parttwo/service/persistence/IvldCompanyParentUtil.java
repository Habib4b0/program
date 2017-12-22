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

import com.stpl.app.parttwo.model.IvldCompanyParent;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the ivld company parent service. This utility wraps {@link com.stpl.app.parttwo.service.persistence.impl.IvldCompanyParentPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldCompanyParentPersistence
 * @see com.stpl.app.parttwo.service.persistence.impl.IvldCompanyParentPersistenceImpl
 * @generated
 */
@ProviderType
public class IvldCompanyParentUtil {
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
	public static void clearCache(IvldCompanyParent ivldCompanyParent) {
		getPersistence().clearCache(ivldCompanyParent);
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
	public static List<IvldCompanyParent> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<IvldCompanyParent> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<IvldCompanyParent> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<IvldCompanyParent> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static IvldCompanyParent update(IvldCompanyParent ivldCompanyParent) {
		return getPersistence().update(ivldCompanyParent);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static IvldCompanyParent update(
		IvldCompanyParent ivldCompanyParent, ServiceContext serviceContext) {
		return getPersistence().update(ivldCompanyParent, serviceContext);
	}

	/**
	* Caches the ivld company parent in the entity cache if it is enabled.
	*
	* @param ivldCompanyParent the ivld company parent
	*/
	public static void cacheResult(IvldCompanyParent ivldCompanyParent) {
		getPersistence().cacheResult(ivldCompanyParent);
	}

	/**
	* Caches the ivld company parents in the entity cache if it is enabled.
	*
	* @param ivldCompanyParents the ivld company parents
	*/
	public static void cacheResult(List<IvldCompanyParent> ivldCompanyParents) {
		getPersistence().cacheResult(ivldCompanyParents);
	}

	/**
	* Creates a new ivld company parent with the primary key. Does not add the ivld company parent to the database.
	*
	* @param ivldCompanyParentSid the primary key for the new ivld company parent
	* @return the new ivld company parent
	*/
	public static IvldCompanyParent create(int ivldCompanyParentSid) {
		return getPersistence().create(ivldCompanyParentSid);
	}

	/**
	* Removes the ivld company parent with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldCompanyParentSid the primary key of the ivld company parent
	* @return the ivld company parent that was removed
	* @throws NoSuchIvldCompanyParentException if a ivld company parent with the primary key could not be found
	*/
	public static IvldCompanyParent remove(int ivldCompanyParentSid)
		throws com.stpl.app.parttwo.exception.NoSuchIvldCompanyParentException {
		return getPersistence().remove(ivldCompanyParentSid);
	}

	public static IvldCompanyParent updateImpl(
		IvldCompanyParent ivldCompanyParent) {
		return getPersistence().updateImpl(ivldCompanyParent);
	}

	/**
	* Returns the ivld company parent with the primary key or throws a {@link NoSuchIvldCompanyParentException} if it could not be found.
	*
	* @param ivldCompanyParentSid the primary key of the ivld company parent
	* @return the ivld company parent
	* @throws NoSuchIvldCompanyParentException if a ivld company parent with the primary key could not be found
	*/
	public static IvldCompanyParent findByPrimaryKey(int ivldCompanyParentSid)
		throws com.stpl.app.parttwo.exception.NoSuchIvldCompanyParentException {
		return getPersistence().findByPrimaryKey(ivldCompanyParentSid);
	}

	/**
	* Returns the ivld company parent with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldCompanyParentSid the primary key of the ivld company parent
	* @return the ivld company parent, or <code>null</code> if a ivld company parent with the primary key could not be found
	*/
	public static IvldCompanyParent fetchByPrimaryKey(int ivldCompanyParentSid) {
		return getPersistence().fetchByPrimaryKey(ivldCompanyParentSid);
	}

	public static java.util.Map<java.io.Serializable, IvldCompanyParent> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ivld company parents.
	*
	* @return the ivld company parents
	*/
	public static List<IvldCompanyParent> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ivld company parents.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCompanyParentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld company parents
	* @param end the upper bound of the range of ivld company parents (not inclusive)
	* @return the range of ivld company parents
	*/
	public static List<IvldCompanyParent> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ivld company parents.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCompanyParentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld company parents
	* @param end the upper bound of the range of ivld company parents (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivld company parents
	*/
	public static List<IvldCompanyParent> findAll(int start, int end,
		OrderByComparator<IvldCompanyParent> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ivld company parents.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCompanyParentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld company parents
	* @param end the upper bound of the range of ivld company parents (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivld company parents
	*/
	public static List<IvldCompanyParent> findAll(int start, int end,
		OrderByComparator<IvldCompanyParent> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ivld company parents from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ivld company parents.
	*
	* @return the number of ivld company parents
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static IvldCompanyParentPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<IvldCompanyParentPersistence, IvldCompanyParentPersistence> _serviceTracker =
		ServiceTrackerFactory.open(IvldCompanyParentPersistence.class);
}