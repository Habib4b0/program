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

import com.stpl.app.model.FileManagement;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the file management service. This utility wraps {@link com.stpl.app.service.persistence.impl.FileManagementPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see FileManagementPersistence
 * @see com.stpl.app.service.persistence.impl.FileManagementPersistenceImpl
 * @generated
 */
@ProviderType
public class FileManagementUtil {
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
	public static void clearCache(FileManagement fileManagement) {
		getPersistence().clearCache(fileManagement);
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
	public static List<FileManagement> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<FileManagement> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<FileManagement> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<FileManagement> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static FileManagement update(FileManagement fileManagement) {
		return getPersistence().update(fileManagement);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static FileManagement update(FileManagement fileManagement,
		ServiceContext serviceContext) {
		return getPersistence().update(fileManagement, serviceContext);
	}

	/**
	* Caches the file management in the entity cache if it is enabled.
	*
	* @param fileManagement the file management
	*/
	public static void cacheResult(FileManagement fileManagement) {
		getPersistence().cacheResult(fileManagement);
	}

	/**
	* Caches the file managements in the entity cache if it is enabled.
	*
	* @param fileManagements the file managements
	*/
	public static void cacheResult(List<FileManagement> fileManagements) {
		getPersistence().cacheResult(fileManagements);
	}

	/**
	* Creates a new file management with the primary key. Does not add the file management to the database.
	*
	* @param fileManagementSid the primary key for the new file management
	* @return the new file management
	*/
	public static FileManagement create(int fileManagementSid) {
		return getPersistence().create(fileManagementSid);
	}

	/**
	* Removes the file management with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fileManagementSid the primary key of the file management
	* @return the file management that was removed
	* @throws NoSuchFileManagementException if a file management with the primary key could not be found
	*/
	public static FileManagement remove(int fileManagementSid)
		throws com.stpl.app.exception.NoSuchFileManagementException {
		return getPersistence().remove(fileManagementSid);
	}

	public static FileManagement updateImpl(FileManagement fileManagement) {
		return getPersistence().updateImpl(fileManagement);
	}

	/**
	* Returns the file management with the primary key or throws a {@link NoSuchFileManagementException} if it could not be found.
	*
	* @param fileManagementSid the primary key of the file management
	* @return the file management
	* @throws NoSuchFileManagementException if a file management with the primary key could not be found
	*/
	public static FileManagement findByPrimaryKey(int fileManagementSid)
		throws com.stpl.app.exception.NoSuchFileManagementException {
		return getPersistence().findByPrimaryKey(fileManagementSid);
	}

	/**
	* Returns the file management with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param fileManagementSid the primary key of the file management
	* @return the file management, or <code>null</code> if a file management with the primary key could not be found
	*/
	public static FileManagement fetchByPrimaryKey(int fileManagementSid) {
		return getPersistence().fetchByPrimaryKey(fileManagementSid);
	}

	public static java.util.Map<java.io.Serializable, FileManagement> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the file managements.
	*
	* @return the file managements
	*/
	public static List<FileManagement> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the file managements.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of file managements
	* @param end the upper bound of the range of file managements (not inclusive)
	* @return the range of file managements
	*/
	public static List<FileManagement> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the file managements.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of file managements
	* @param end the upper bound of the range of file managements (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of file managements
	*/
	public static List<FileManagement> findAll(int start, int end,
		OrderByComparator<FileManagement> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the file managements.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FileManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of file managements
	* @param end the upper bound of the range of file managements (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of file managements
	*/
	public static List<FileManagement> findAll(int start, int end,
		OrderByComparator<FileManagement> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the file managements from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of file managements.
	*
	* @return the number of file managements
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static FileManagementPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<FileManagementPersistence, FileManagementPersistence> _serviceTracker =
		ServiceTrackerFactory.open(FileManagementPersistence.class);
}