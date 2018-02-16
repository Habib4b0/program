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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.stpl.app.exception.NoSuchFileManagementException;
import com.stpl.app.model.FileManagement;

/**
 * The persistence interface for the file management service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.FileManagementPersistenceImpl
 * @see FileManagementUtil
 * @generated
 */
@ProviderType
public interface FileManagementPersistence extends BasePersistence<FileManagement> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link FileManagementUtil} to access the file management persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the file management in the entity cache if it is enabled.
	*
	* @param fileManagement the file management
	*/
	public void cacheResult(FileManagement fileManagement);

	/**
	* Caches the file managements in the entity cache if it is enabled.
	*
	* @param fileManagements the file managements
	*/
	public void cacheResult(java.util.List<FileManagement> fileManagements);

	/**
	* Creates a new file management with the primary key. Does not add the file management to the database.
	*
	* @param fileManagementSid the primary key for the new file management
	* @return the new file management
	*/
	public FileManagement create(int fileManagementSid);

	/**
	* Removes the file management with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fileManagementSid the primary key of the file management
	* @return the file management that was removed
	* @throws NoSuchFileManagementException if a file management with the primary key could not be found
	*/
	public FileManagement remove(int fileManagementSid)
		throws NoSuchFileManagementException;

	public FileManagement updateImpl(FileManagement fileManagement);

	/**
	* Returns the file management with the primary key or throws a {@link NoSuchFileManagementException} if it could not be found.
	*
	* @param fileManagementSid the primary key of the file management
	* @return the file management
	* @throws NoSuchFileManagementException if a file management with the primary key could not be found
	*/
	public FileManagement findByPrimaryKey(int fileManagementSid)
		throws NoSuchFileManagementException;

	/**
	* Returns the file management with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param fileManagementSid the primary key of the file management
	* @return the file management, or <code>null</code> if a file management with the primary key could not be found
	*/
	public FileManagement fetchByPrimaryKey(int fileManagementSid);

	@Override
	public java.util.Map<java.io.Serializable, FileManagement> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the file managements.
	*
	* @return the file managements
	*/
	public java.util.List<FileManagement> findAll();

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
	public java.util.List<FileManagement> findAll(int start, int end);

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
	public java.util.List<FileManagement> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FileManagement> orderByComparator);

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
	public java.util.List<FileManagement> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FileManagement> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the file managements from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of file managements.
	*
	* @return the number of file managements
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}