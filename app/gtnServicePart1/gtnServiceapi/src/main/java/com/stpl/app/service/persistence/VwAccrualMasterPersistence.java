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

import com.stpl.app.exception.NoSuchVwAccrualMasterException;
import com.stpl.app.model.VwAccrualMaster;

/**
 * The persistence interface for the vw accrual master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.VwAccrualMasterPersistenceImpl
 * @see VwAccrualMasterUtil
 * @generated
 */
@ProviderType
public interface VwAccrualMasterPersistence extends BasePersistence<VwAccrualMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link VwAccrualMasterUtil} to access the vw accrual master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the vw accrual master in the entity cache if it is enabled.
	*
	* @param vwAccrualMaster the vw accrual master
	*/
	public void cacheResult(VwAccrualMaster vwAccrualMaster);

	/**
	* Caches the vw accrual masters in the entity cache if it is enabled.
	*
	* @param vwAccrualMasters the vw accrual masters
	*/
	public void cacheResult(java.util.List<VwAccrualMaster> vwAccrualMasters);

	/**
	* Creates a new vw accrual master with the primary key. Does not add the vw accrual master to the database.
	*
	* @param accrualMasterSid the primary key for the new vw accrual master
	* @return the new vw accrual master
	*/
	public VwAccrualMaster create(int accrualMasterSid);

	/**
	* Removes the vw accrual master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accrualMasterSid the primary key of the vw accrual master
	* @return the vw accrual master that was removed
	* @throws NoSuchVwAccrualMasterException if a vw accrual master with the primary key could not be found
	*/
	public VwAccrualMaster remove(int accrualMasterSid)
		throws NoSuchVwAccrualMasterException;

	public VwAccrualMaster updateImpl(VwAccrualMaster vwAccrualMaster);

	/**
	* Returns the vw accrual master with the primary key or throws a {@link NoSuchVwAccrualMasterException} if it could not be found.
	*
	* @param accrualMasterSid the primary key of the vw accrual master
	* @return the vw accrual master
	* @throws NoSuchVwAccrualMasterException if a vw accrual master with the primary key could not be found
	*/
	public VwAccrualMaster findByPrimaryKey(int accrualMasterSid)
		throws NoSuchVwAccrualMasterException;

	/**
	* Returns the vw accrual master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accrualMasterSid the primary key of the vw accrual master
	* @return the vw accrual master, or <code>null</code> if a vw accrual master with the primary key could not be found
	*/
	public VwAccrualMaster fetchByPrimaryKey(int accrualMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, VwAccrualMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the vw accrual masters.
	*
	* @return the vw accrual masters
	*/
	public java.util.List<VwAccrualMaster> findAll();

	/**
	* Returns a range of all the vw accrual masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwAccrualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw accrual masters
	* @param end the upper bound of the range of vw accrual masters (not inclusive)
	* @return the range of vw accrual masters
	*/
	public java.util.List<VwAccrualMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the vw accrual masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwAccrualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw accrual masters
	* @param end the upper bound of the range of vw accrual masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of vw accrual masters
	*/
	public java.util.List<VwAccrualMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<VwAccrualMaster> orderByComparator);

	/**
	* Returns an ordered range of all the vw accrual masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwAccrualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw accrual masters
	* @param end the upper bound of the range of vw accrual masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of vw accrual masters
	*/
	public java.util.List<VwAccrualMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<VwAccrualMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the vw accrual masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of vw accrual masters.
	*
	* @return the number of vw accrual masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}