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

import com.stpl.app.model.MasterDataFiles;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the master data files service. This utility wraps {@link com.stpl.app.service.persistence.impl.MasterDataFilesPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MasterDataFilesPersistence
 * @see com.stpl.app.service.persistence.impl.MasterDataFilesPersistenceImpl
 * @generated
 */
@ProviderType
public class MasterDataFilesUtil {
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
	public static void clearCache(MasterDataFiles masterDataFiles) {
		getPersistence().clearCache(masterDataFiles);
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
	public static List<MasterDataFiles> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MasterDataFiles> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MasterDataFiles> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<MasterDataFiles> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static MasterDataFiles update(MasterDataFiles masterDataFiles) {
		return getPersistence().update(masterDataFiles);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static MasterDataFiles update(MasterDataFiles masterDataFiles,
		ServiceContext serviceContext) {
		return getPersistence().update(masterDataFiles, serviceContext);
	}

	/**
	* Caches the master data files in the entity cache if it is enabled.
	*
	* @param masterDataFiles the master data files
	*/
	public static void cacheResult(MasterDataFiles masterDataFiles) {
		getPersistence().cacheResult(masterDataFiles);
	}

	/**
	* Caches the master data fileses in the entity cache if it is enabled.
	*
	* @param masterDataFileses the master data fileses
	*/
	public static void cacheResult(List<MasterDataFiles> masterDataFileses) {
		getPersistence().cacheResult(masterDataFileses);
	}

	/**
	* Creates a new master data files with the primary key. Does not add the master data files to the database.
	*
	* @param masterDataFilesSid the primary key for the new master data files
	* @return the new master data files
	*/
	public static MasterDataFiles create(int masterDataFilesSid) {
		return getPersistence().create(masterDataFilesSid);
	}

	/**
	* Removes the master data files with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param masterDataFilesSid the primary key of the master data files
	* @return the master data files that was removed
	* @throws NoSuchMasterDataFilesException if a master data files with the primary key could not be found
	*/
	public static MasterDataFiles remove(int masterDataFilesSid)
		throws com.stpl.app.exception.NoSuchMasterDataFilesException {
		return getPersistence().remove(masterDataFilesSid);
	}

	public static MasterDataFiles updateImpl(MasterDataFiles masterDataFiles) {
		return getPersistence().updateImpl(masterDataFiles);
	}

	/**
	* Returns the master data files with the primary key or throws a {@link NoSuchMasterDataFilesException} if it could not be found.
	*
	* @param masterDataFilesSid the primary key of the master data files
	* @return the master data files
	* @throws NoSuchMasterDataFilesException if a master data files with the primary key could not be found
	*/
	public static MasterDataFiles findByPrimaryKey(int masterDataFilesSid)
		throws com.stpl.app.exception.NoSuchMasterDataFilesException {
		return getPersistence().findByPrimaryKey(masterDataFilesSid);
	}

	/**
	* Returns the master data files with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param masterDataFilesSid the primary key of the master data files
	* @return the master data files, or <code>null</code> if a master data files with the primary key could not be found
	*/
	public static MasterDataFiles fetchByPrimaryKey(int masterDataFilesSid) {
		return getPersistence().fetchByPrimaryKey(masterDataFilesSid);
	}

	public static java.util.Map<java.io.Serializable, MasterDataFiles> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the master data fileses.
	*
	* @return the master data fileses
	*/
	public static List<MasterDataFiles> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the master data fileses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MasterDataFilesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of master data fileses
	* @param end the upper bound of the range of master data fileses (not inclusive)
	* @return the range of master data fileses
	*/
	public static List<MasterDataFiles> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the master data fileses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MasterDataFilesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of master data fileses
	* @param end the upper bound of the range of master data fileses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of master data fileses
	*/
	public static List<MasterDataFiles> findAll(int start, int end,
		OrderByComparator<MasterDataFiles> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the master data fileses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MasterDataFilesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of master data fileses
	* @param end the upper bound of the range of master data fileses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of master data fileses
	*/
	public static List<MasterDataFiles> findAll(int start, int end,
		OrderByComparator<MasterDataFiles> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the master data fileses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of master data fileses.
	*
	* @return the number of master data fileses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static MasterDataFilesPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MasterDataFilesPersistence, MasterDataFilesPersistence> _serviceTracker =
		ServiceTrackerFactory.open(MasterDataFilesPersistence.class);
}