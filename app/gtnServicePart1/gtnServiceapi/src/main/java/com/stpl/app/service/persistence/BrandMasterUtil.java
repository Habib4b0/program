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

import com.stpl.app.model.BrandMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the brand master service. This utility wraps {@link com.stpl.app.service.persistence.impl.BrandMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see BrandMasterPersistence
 * @see com.stpl.app.service.persistence.impl.BrandMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class BrandMasterUtil {
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
	public static void clearCache(BrandMaster brandMaster) {
		getPersistence().clearCache(brandMaster);
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
	public static List<BrandMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<BrandMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<BrandMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<BrandMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static BrandMaster update(BrandMaster brandMaster) {
		return getPersistence().update(brandMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static BrandMaster update(BrandMaster brandMaster,
		ServiceContext serviceContext) {
		return getPersistence().update(brandMaster, serviceContext);
	}

	/**
	* Caches the brand master in the entity cache if it is enabled.
	*
	* @param brandMaster the brand master
	*/
	public static void cacheResult(BrandMaster brandMaster) {
		getPersistence().cacheResult(brandMaster);
	}

	/**
	* Caches the brand masters in the entity cache if it is enabled.
	*
	* @param brandMasters the brand masters
	*/
	public static void cacheResult(List<BrandMaster> brandMasters) {
		getPersistence().cacheResult(brandMasters);
	}

	/**
	* Creates a new brand master with the primary key. Does not add the brand master to the database.
	*
	* @param brandMasterSid the primary key for the new brand master
	* @return the new brand master
	*/
	public static BrandMaster create(int brandMasterSid) {
		return getPersistence().create(brandMasterSid);
	}

	/**
	* Removes the brand master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param brandMasterSid the primary key of the brand master
	* @return the brand master that was removed
	* @throws NoSuchBrandMasterException if a brand master with the primary key could not be found
	*/
	public static BrandMaster remove(int brandMasterSid)
		throws com.stpl.app.exception.NoSuchBrandMasterException {
		return getPersistence().remove(brandMasterSid);
	}

	public static BrandMaster updateImpl(BrandMaster brandMaster) {
		return getPersistence().updateImpl(brandMaster);
	}

	/**
	* Returns the brand master with the primary key or throws a {@link NoSuchBrandMasterException} if it could not be found.
	*
	* @param brandMasterSid the primary key of the brand master
	* @return the brand master
	* @throws NoSuchBrandMasterException if a brand master with the primary key could not be found
	*/
	public static BrandMaster findByPrimaryKey(int brandMasterSid)
		throws com.stpl.app.exception.NoSuchBrandMasterException {
		return getPersistence().findByPrimaryKey(brandMasterSid);
	}

	/**
	* Returns the brand master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param brandMasterSid the primary key of the brand master
	* @return the brand master, or <code>null</code> if a brand master with the primary key could not be found
	*/
	public static BrandMaster fetchByPrimaryKey(int brandMasterSid) {
		return getPersistence().fetchByPrimaryKey(brandMasterSid);
	}

	public static java.util.Map<java.io.Serializable, BrandMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the brand masters.
	*
	* @return the brand masters
	*/
	public static List<BrandMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the brand masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BrandMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of brand masters
	* @param end the upper bound of the range of brand masters (not inclusive)
	* @return the range of brand masters
	*/
	public static List<BrandMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the brand masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BrandMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of brand masters
	* @param end the upper bound of the range of brand masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of brand masters
	*/
	public static List<BrandMaster> findAll(int start, int end,
		OrderByComparator<BrandMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the brand masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BrandMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of brand masters
	* @param end the upper bound of the range of brand masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of brand masters
	*/
	public static List<BrandMaster> findAll(int start, int end,
		OrderByComparator<BrandMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the brand masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of brand masters.
	*
	* @return the number of brand masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static BrandMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<BrandMasterPersistence, BrandMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(BrandMasterPersistence.class);
}