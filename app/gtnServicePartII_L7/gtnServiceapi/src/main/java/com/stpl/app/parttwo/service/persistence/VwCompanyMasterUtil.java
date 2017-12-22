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

import com.stpl.app.parttwo.model.VwCompanyMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the vw company master service. This utility wraps {@link com.stpl.app.parttwo.service.persistence.impl.VwCompanyMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwCompanyMasterPersistence
 * @see com.stpl.app.parttwo.service.persistence.impl.VwCompanyMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class VwCompanyMasterUtil {
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
	public static void clearCache(VwCompanyMaster vwCompanyMaster) {
		getPersistence().clearCache(vwCompanyMaster);
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
	public static List<VwCompanyMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<VwCompanyMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<VwCompanyMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<VwCompanyMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static VwCompanyMaster update(VwCompanyMaster vwCompanyMaster) {
		return getPersistence().update(vwCompanyMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static VwCompanyMaster update(VwCompanyMaster vwCompanyMaster,
		ServiceContext serviceContext) {
		return getPersistence().update(vwCompanyMaster, serviceContext);
	}

	/**
	* Caches the vw company master in the entity cache if it is enabled.
	*
	* @param vwCompanyMaster the vw company master
	*/
	public static void cacheResult(VwCompanyMaster vwCompanyMaster) {
		getPersistence().cacheResult(vwCompanyMaster);
	}

	/**
	* Caches the vw company masters in the entity cache if it is enabled.
	*
	* @param vwCompanyMasters the vw company masters
	*/
	public static void cacheResult(List<VwCompanyMaster> vwCompanyMasters) {
		getPersistence().cacheResult(vwCompanyMasters);
	}

	/**
	* Creates a new vw company master with the primary key. Does not add the vw company master to the database.
	*
	* @param companyMasterSid the primary key for the new vw company master
	* @return the new vw company master
	*/
	public static VwCompanyMaster create(int companyMasterSid) {
		return getPersistence().create(companyMasterSid);
	}

	/**
	* Removes the vw company master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param companyMasterSid the primary key of the vw company master
	* @return the vw company master that was removed
	* @throws NoSuchVwCompanyMasterException if a vw company master with the primary key could not be found
	*/
	public static VwCompanyMaster remove(int companyMasterSid)
		throws com.stpl.app.parttwo.exception.NoSuchVwCompanyMasterException {
		return getPersistence().remove(companyMasterSid);
	}

	public static VwCompanyMaster updateImpl(VwCompanyMaster vwCompanyMaster) {
		return getPersistence().updateImpl(vwCompanyMaster);
	}

	/**
	* Returns the vw company master with the primary key or throws a {@link NoSuchVwCompanyMasterException} if it could not be found.
	*
	* @param companyMasterSid the primary key of the vw company master
	* @return the vw company master
	* @throws NoSuchVwCompanyMasterException if a vw company master with the primary key could not be found
	*/
	public static VwCompanyMaster findByPrimaryKey(int companyMasterSid)
		throws com.stpl.app.parttwo.exception.NoSuchVwCompanyMasterException {
		return getPersistence().findByPrimaryKey(companyMasterSid);
	}

	/**
	* Returns the vw company master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param companyMasterSid the primary key of the vw company master
	* @return the vw company master, or <code>null</code> if a vw company master with the primary key could not be found
	*/
	public static VwCompanyMaster fetchByPrimaryKey(int companyMasterSid) {
		return getPersistence().fetchByPrimaryKey(companyMasterSid);
	}

	public static java.util.Map<java.io.Serializable, VwCompanyMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the vw company masters.
	*
	* @return the vw company masters
	*/
	public static List<VwCompanyMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the vw company masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwCompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw company masters
	* @param end the upper bound of the range of vw company masters (not inclusive)
	* @return the range of vw company masters
	*/
	public static List<VwCompanyMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the vw company masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwCompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw company masters
	* @param end the upper bound of the range of vw company masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of vw company masters
	*/
	public static List<VwCompanyMaster> findAll(int start, int end,
		OrderByComparator<VwCompanyMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the vw company masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwCompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw company masters
	* @param end the upper bound of the range of vw company masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of vw company masters
	*/
	public static List<VwCompanyMaster> findAll(int start, int end,
		OrderByComparator<VwCompanyMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the vw company masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of vw company masters.
	*
	* @return the number of vw company masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static VwCompanyMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<VwCompanyMasterPersistence, VwCompanyMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(VwCompanyMasterPersistence.class);
}