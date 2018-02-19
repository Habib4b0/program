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

import com.stpl.app.parttwo.model.IvldCompanyMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the ivld company master service. This utility wraps {@link com.stpl.app.parttwo.service.persistence.impl.IvldCompanyMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldCompanyMasterPersistence
 * @see com.stpl.app.parttwo.service.persistence.impl.IvldCompanyMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class IvldCompanyMasterUtil {
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
	public static void clearCache(IvldCompanyMaster ivldCompanyMaster) {
		getPersistence().clearCache(ivldCompanyMaster);
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
	public static List<IvldCompanyMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<IvldCompanyMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<IvldCompanyMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<IvldCompanyMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static IvldCompanyMaster update(IvldCompanyMaster ivldCompanyMaster) {
		return getPersistence().update(ivldCompanyMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static IvldCompanyMaster update(
		IvldCompanyMaster ivldCompanyMaster, ServiceContext serviceContext) {
		return getPersistence().update(ivldCompanyMaster, serviceContext);
	}

	/**
	* Caches the ivld company master in the entity cache if it is enabled.
	*
	* @param ivldCompanyMaster the ivld company master
	*/
	public static void cacheResult(IvldCompanyMaster ivldCompanyMaster) {
		getPersistence().cacheResult(ivldCompanyMaster);
	}

	/**
	* Caches the ivld company masters in the entity cache if it is enabled.
	*
	* @param ivldCompanyMasters the ivld company masters
	*/
	public static void cacheResult(List<IvldCompanyMaster> ivldCompanyMasters) {
		getPersistence().cacheResult(ivldCompanyMasters);
	}

	/**
	* Creates a new ivld company master with the primary key. Does not add the ivld company master to the database.
	*
	* @param ivldCompanyMasterSid the primary key for the new ivld company master
	* @return the new ivld company master
	*/
	public static IvldCompanyMaster create(int ivldCompanyMasterSid) {
		return getPersistence().create(ivldCompanyMasterSid);
	}

	/**
	* Removes the ivld company master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldCompanyMasterSid the primary key of the ivld company master
	* @return the ivld company master that was removed
	* @throws NoSuchIvldCompanyMasterException if a ivld company master with the primary key could not be found
	*/
	public static IvldCompanyMaster remove(int ivldCompanyMasterSid)
		throws com.stpl.app.parttwo.exception.NoSuchIvldCompanyMasterException {
		return getPersistence().remove(ivldCompanyMasterSid);
	}

	public static IvldCompanyMaster updateImpl(
		IvldCompanyMaster ivldCompanyMaster) {
		return getPersistence().updateImpl(ivldCompanyMaster);
	}

	/**
	* Returns the ivld company master with the primary key or throws a {@link NoSuchIvldCompanyMasterException} if it could not be found.
	*
	* @param ivldCompanyMasterSid the primary key of the ivld company master
	* @return the ivld company master
	* @throws NoSuchIvldCompanyMasterException if a ivld company master with the primary key could not be found
	*/
	public static IvldCompanyMaster findByPrimaryKey(int ivldCompanyMasterSid)
		throws com.stpl.app.parttwo.exception.NoSuchIvldCompanyMasterException {
		return getPersistence().findByPrimaryKey(ivldCompanyMasterSid);
	}

	/**
	* Returns the ivld company master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldCompanyMasterSid the primary key of the ivld company master
	* @return the ivld company master, or <code>null</code> if a ivld company master with the primary key could not be found
	*/
	public static IvldCompanyMaster fetchByPrimaryKey(int ivldCompanyMasterSid) {
		return getPersistence().fetchByPrimaryKey(ivldCompanyMasterSid);
	}

	public static java.util.Map<java.io.Serializable, IvldCompanyMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ivld company masters.
	*
	* @return the ivld company masters
	*/
	public static List<IvldCompanyMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ivld company masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld company masters
	* @param end the upper bound of the range of ivld company masters (not inclusive)
	* @return the range of ivld company masters
	*/
	public static List<IvldCompanyMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ivld company masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld company masters
	* @param end the upper bound of the range of ivld company masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivld company masters
	*/
	public static List<IvldCompanyMaster> findAll(int start, int end,
		OrderByComparator<IvldCompanyMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ivld company masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld company masters
	* @param end the upper bound of the range of ivld company masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivld company masters
	*/
	public static List<IvldCompanyMaster> findAll(int start, int end,
		OrderByComparator<IvldCompanyMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ivld company masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ivld company masters.
	*
	* @return the number of ivld company masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static IvldCompanyMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<IvldCompanyMasterPersistence, IvldCompanyMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(IvldCompanyMasterPersistence.class);
}