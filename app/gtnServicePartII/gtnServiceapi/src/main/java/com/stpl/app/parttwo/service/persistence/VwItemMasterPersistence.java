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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.stpl.app.parttwo.exception.NoSuchVwItemMasterException;
import com.stpl.app.parttwo.model.VwItemMaster;

/**
 * The persistence interface for the vw item master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.VwItemMasterPersistenceImpl
 * @see VwItemMasterUtil
 * @generated
 */
@ProviderType
public interface VwItemMasterPersistence extends BasePersistence<VwItemMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link VwItemMasterUtil} to access the vw item master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the vw item master in the entity cache if it is enabled.
	*
	* @param vwItemMaster the vw item master
	*/
	public void cacheResult(VwItemMaster vwItemMaster);

	/**
	* Caches the vw item masters in the entity cache if it is enabled.
	*
	* @param vwItemMasters the vw item masters
	*/
	public void cacheResult(java.util.List<VwItemMaster> vwItemMasters);

	/**
	* Creates a new vw item master with the primary key. Does not add the vw item master to the database.
	*
	* @param itemMasterSid the primary key for the new vw item master
	* @return the new vw item master
	*/
	public VwItemMaster create(int itemMasterSid);

	/**
	* Removes the vw item master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param itemMasterSid the primary key of the vw item master
	* @return the vw item master that was removed
	* @throws NoSuchVwItemMasterException if a vw item master with the primary key could not be found
	*/
	public VwItemMaster remove(int itemMasterSid)
		throws NoSuchVwItemMasterException;

	public VwItemMaster updateImpl(VwItemMaster vwItemMaster);

	/**
	* Returns the vw item master with the primary key or throws a {@link NoSuchVwItemMasterException} if it could not be found.
	*
	* @param itemMasterSid the primary key of the vw item master
	* @return the vw item master
	* @throws NoSuchVwItemMasterException if a vw item master with the primary key could not be found
	*/
	public VwItemMaster findByPrimaryKey(int itemMasterSid)
		throws NoSuchVwItemMasterException;

	/**
	* Returns the vw item master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param itemMasterSid the primary key of the vw item master
	* @return the vw item master, or <code>null</code> if a vw item master with the primary key could not be found
	*/
	public VwItemMaster fetchByPrimaryKey(int itemMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, VwItemMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the vw item masters.
	*
	* @return the vw item masters
	*/
	public java.util.List<VwItemMaster> findAll();

	/**
	* Returns a range of all the vw item masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw item masters
	* @param end the upper bound of the range of vw item masters (not inclusive)
	* @return the range of vw item masters
	*/
	public java.util.List<VwItemMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the vw item masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw item masters
	* @param end the upper bound of the range of vw item masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of vw item masters
	*/
	public java.util.List<VwItemMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<VwItemMaster> orderByComparator);

	/**
	* Returns an ordered range of all the vw item masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw item masters
	* @param end the upper bound of the range of vw item masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of vw item masters
	*/
	public java.util.List<VwItemMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<VwItemMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the vw item masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of vw item masters.
	*
	* @return the number of vw item masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}