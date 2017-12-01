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

import com.stpl.app.exception.NoSuchMasterDataFilesException;
import com.stpl.app.model.MasterDataFiles;

/**
 * The persistence interface for the master data files service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.MasterDataFilesPersistenceImpl
 * @see MasterDataFilesUtil
 * @generated
 */
@ProviderType
public interface MasterDataFilesPersistence extends BasePersistence<MasterDataFiles> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MasterDataFilesUtil} to access the master data files persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the master data files in the entity cache if it is enabled.
	*
	* @param masterDataFiles the master data files
	*/
	public void cacheResult(MasterDataFiles masterDataFiles);

	/**
	* Caches the master data fileses in the entity cache if it is enabled.
	*
	* @param masterDataFileses the master data fileses
	*/
	public void cacheResult(java.util.List<MasterDataFiles> masterDataFileses);

	/**
	* Creates a new master data files with the primary key. Does not add the master data files to the database.
	*
	* @param masterDataFilesSid the primary key for the new master data files
	* @return the new master data files
	*/
	public MasterDataFiles create(int masterDataFilesSid);

	/**
	* Removes the master data files with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param masterDataFilesSid the primary key of the master data files
	* @return the master data files that was removed
	* @throws NoSuchMasterDataFilesException if a master data files with the primary key could not be found
	*/
	public MasterDataFiles remove(int masterDataFilesSid)
		throws NoSuchMasterDataFilesException;

	public MasterDataFiles updateImpl(MasterDataFiles masterDataFiles);

	/**
	* Returns the master data files with the primary key or throws a {@link NoSuchMasterDataFilesException} if it could not be found.
	*
	* @param masterDataFilesSid the primary key of the master data files
	* @return the master data files
	* @throws NoSuchMasterDataFilesException if a master data files with the primary key could not be found
	*/
	public MasterDataFiles findByPrimaryKey(int masterDataFilesSid)
		throws NoSuchMasterDataFilesException;

	/**
	* Returns the master data files with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param masterDataFilesSid the primary key of the master data files
	* @return the master data files, or <code>null</code> if a master data files with the primary key could not be found
	*/
	public MasterDataFiles fetchByPrimaryKey(int masterDataFilesSid);

	@Override
	public java.util.Map<java.io.Serializable, MasterDataFiles> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the master data fileses.
	*
	* @return the master data fileses
	*/
	public java.util.List<MasterDataFiles> findAll();

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
	public java.util.List<MasterDataFiles> findAll(int start, int end);

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
	public java.util.List<MasterDataFiles> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MasterDataFiles> orderByComparator);

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
	public java.util.List<MasterDataFiles> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MasterDataFiles> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the master data fileses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of master data fileses.
	*
	* @return the number of master data fileses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}