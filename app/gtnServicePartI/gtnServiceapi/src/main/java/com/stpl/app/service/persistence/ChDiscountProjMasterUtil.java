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

import com.stpl.app.model.ChDiscountProjMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the ch discount proj master service. This utility wraps {@link com.stpl.app.service.persistence.impl.ChDiscountProjMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ChDiscountProjMasterPersistence
 * @see com.stpl.app.service.persistence.impl.ChDiscountProjMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class ChDiscountProjMasterUtil {
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
	public static void clearCache(ChDiscountProjMaster chDiscountProjMaster) {
		getPersistence().clearCache(chDiscountProjMaster);
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
	public static List<ChDiscountProjMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ChDiscountProjMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ChDiscountProjMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ChDiscountProjMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ChDiscountProjMaster update(
		ChDiscountProjMaster chDiscountProjMaster) {
		return getPersistence().update(chDiscountProjMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ChDiscountProjMaster update(
		ChDiscountProjMaster chDiscountProjMaster, ServiceContext serviceContext) {
		return getPersistence().update(chDiscountProjMaster, serviceContext);
	}

	/**
	* Caches the ch discount proj master in the entity cache if it is enabled.
	*
	* @param chDiscountProjMaster the ch discount proj master
	*/
	public static void cacheResult(ChDiscountProjMaster chDiscountProjMaster) {
		getPersistence().cacheResult(chDiscountProjMaster);
	}

	/**
	* Caches the ch discount proj masters in the entity cache if it is enabled.
	*
	* @param chDiscountProjMasters the ch discount proj masters
	*/
	public static void cacheResult(
		List<ChDiscountProjMaster> chDiscountProjMasters) {
		getPersistence().cacheResult(chDiscountProjMasters);
	}

	/**
	* Creates a new ch discount proj master with the primary key. Does not add the ch discount proj master to the database.
	*
	* @param chDiscountProjMasterPK the primary key for the new ch discount proj master
	* @return the new ch discount proj master
	*/
	public static ChDiscountProjMaster create(
		ChDiscountProjMasterPK chDiscountProjMasterPK) {
		return getPersistence().create(chDiscountProjMasterPK);
	}

	/**
	* Removes the ch discount proj master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param chDiscountProjMasterPK the primary key of the ch discount proj master
	* @return the ch discount proj master that was removed
	* @throws NoSuchChDiscountProjMasterException if a ch discount proj master with the primary key could not be found
	*/
	public static ChDiscountProjMaster remove(
		ChDiscountProjMasterPK chDiscountProjMasterPK)
		throws com.stpl.app.exception.NoSuchChDiscountProjMasterException {
		return getPersistence().remove(chDiscountProjMasterPK);
	}

	public static ChDiscountProjMaster updateImpl(
		ChDiscountProjMaster chDiscountProjMaster) {
		return getPersistence().updateImpl(chDiscountProjMaster);
	}

	/**
	* Returns the ch discount proj master with the primary key or throws a {@link NoSuchChDiscountProjMasterException} if it could not be found.
	*
	* @param chDiscountProjMasterPK the primary key of the ch discount proj master
	* @return the ch discount proj master
	* @throws NoSuchChDiscountProjMasterException if a ch discount proj master with the primary key could not be found
	*/
	public static ChDiscountProjMaster findByPrimaryKey(
		ChDiscountProjMasterPK chDiscountProjMasterPK)
		throws com.stpl.app.exception.NoSuchChDiscountProjMasterException {
		return getPersistence().findByPrimaryKey(chDiscountProjMasterPK);
	}

	/**
	* Returns the ch discount proj master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param chDiscountProjMasterPK the primary key of the ch discount proj master
	* @return the ch discount proj master, or <code>null</code> if a ch discount proj master with the primary key could not be found
	*/
	public static ChDiscountProjMaster fetchByPrimaryKey(
		ChDiscountProjMasterPK chDiscountProjMasterPK) {
		return getPersistence().fetchByPrimaryKey(chDiscountProjMasterPK);
	}

	public static java.util.Map<java.io.Serializable, ChDiscountProjMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ch discount proj masters.
	*
	* @return the ch discount proj masters
	*/
	public static List<ChDiscountProjMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ch discount proj masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch discount proj masters
	* @param end the upper bound of the range of ch discount proj masters (not inclusive)
	* @return the range of ch discount proj masters
	*/
	public static List<ChDiscountProjMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ch discount proj masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch discount proj masters
	* @param end the upper bound of the range of ch discount proj masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ch discount proj masters
	*/
	public static List<ChDiscountProjMaster> findAll(int start, int end,
		OrderByComparator<ChDiscountProjMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ch discount proj masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch discount proj masters
	* @param end the upper bound of the range of ch discount proj masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ch discount proj masters
	*/
	public static List<ChDiscountProjMaster> findAll(int start, int end,
		OrderByComparator<ChDiscountProjMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ch discount proj masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ch discount proj masters.
	*
	* @return the number of ch discount proj masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ChDiscountProjMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ChDiscountProjMasterPersistence, ChDiscountProjMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ChDiscountProjMasterPersistence.class);
}