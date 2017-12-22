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

import com.stpl.app.parttwo.exception.NoSuchVwCompanyMasterException;
import com.stpl.app.parttwo.model.VwCompanyMaster;

/**
 * The persistence interface for the vw company master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.VwCompanyMasterPersistenceImpl
 * @see VwCompanyMasterUtil
 * @generated
 */
@ProviderType
public interface VwCompanyMasterPersistence extends BasePersistence<VwCompanyMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link VwCompanyMasterUtil} to access the vw company master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the vw company master in the entity cache if it is enabled.
	*
	* @param vwCompanyMaster the vw company master
	*/
	public void cacheResult(VwCompanyMaster vwCompanyMaster);

	/**
	* Caches the vw company masters in the entity cache if it is enabled.
	*
	* @param vwCompanyMasters the vw company masters
	*/
	public void cacheResult(java.util.List<VwCompanyMaster> vwCompanyMasters);

	/**
	* Creates a new vw company master with the primary key. Does not add the vw company master to the database.
	*
	* @param companyMasterSid the primary key for the new vw company master
	* @return the new vw company master
	*/
	public VwCompanyMaster create(int companyMasterSid);

	/**
	* Removes the vw company master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param companyMasterSid the primary key of the vw company master
	* @return the vw company master that was removed
	* @throws NoSuchVwCompanyMasterException if a vw company master with the primary key could not be found
	*/
	public VwCompanyMaster remove(int companyMasterSid)
		throws NoSuchVwCompanyMasterException;

	public VwCompanyMaster updateImpl(VwCompanyMaster vwCompanyMaster);

	/**
	* Returns the vw company master with the primary key or throws a {@link NoSuchVwCompanyMasterException} if it could not be found.
	*
	* @param companyMasterSid the primary key of the vw company master
	* @return the vw company master
	* @throws NoSuchVwCompanyMasterException if a vw company master with the primary key could not be found
	*/
	public VwCompanyMaster findByPrimaryKey(int companyMasterSid)
		throws NoSuchVwCompanyMasterException;

	/**
	* Returns the vw company master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param companyMasterSid the primary key of the vw company master
	* @return the vw company master, or <code>null</code> if a vw company master with the primary key could not be found
	*/
	public VwCompanyMaster fetchByPrimaryKey(int companyMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, VwCompanyMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the vw company masters.
	*
	* @return the vw company masters
	*/
	public java.util.List<VwCompanyMaster> findAll();

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
	public java.util.List<VwCompanyMaster> findAll(int start, int end);

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
	public java.util.List<VwCompanyMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<VwCompanyMaster> orderByComparator);

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
	public java.util.List<VwCompanyMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<VwCompanyMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the vw company masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of vw company masters.
	*
	* @return the number of vw company masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}