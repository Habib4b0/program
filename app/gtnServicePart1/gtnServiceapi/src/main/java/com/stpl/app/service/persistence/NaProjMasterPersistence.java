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

import com.stpl.app.exception.NoSuchNaProjMasterException;
import com.stpl.app.model.NaProjMaster;

/**
 * The persistence interface for the na proj master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.NaProjMasterPersistenceImpl
 * @see NaProjMasterUtil
 * @generated
 */
@ProviderType
public interface NaProjMasterPersistence extends BasePersistence<NaProjMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link NaProjMasterUtil} to access the na proj master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the na proj master in the entity cache if it is enabled.
	*
	* @param naProjMaster the na proj master
	*/
	public void cacheResult(NaProjMaster naProjMaster);

	/**
	* Caches the na proj masters in the entity cache if it is enabled.
	*
	* @param naProjMasters the na proj masters
	*/
	public void cacheResult(java.util.List<NaProjMaster> naProjMasters);

	/**
	* Creates a new na proj master with the primary key. Does not add the na proj master to the database.
	*
	* @param naProjMasterSid the primary key for the new na proj master
	* @return the new na proj master
	*/
	public NaProjMaster create(int naProjMasterSid);

	/**
	* Removes the na proj master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param naProjMasterSid the primary key of the na proj master
	* @return the na proj master that was removed
	* @throws NoSuchNaProjMasterException if a na proj master with the primary key could not be found
	*/
	public NaProjMaster remove(int naProjMasterSid)
		throws NoSuchNaProjMasterException;

	public NaProjMaster updateImpl(NaProjMaster naProjMaster);

	/**
	* Returns the na proj master with the primary key or throws a {@link NoSuchNaProjMasterException} if it could not be found.
	*
	* @param naProjMasterSid the primary key of the na proj master
	* @return the na proj master
	* @throws NoSuchNaProjMasterException if a na proj master with the primary key could not be found
	*/
	public NaProjMaster findByPrimaryKey(int naProjMasterSid)
		throws NoSuchNaProjMasterException;

	/**
	* Returns the na proj master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param naProjMasterSid the primary key of the na proj master
	* @return the na proj master, or <code>null</code> if a na proj master with the primary key could not be found
	*/
	public NaProjMaster fetchByPrimaryKey(int naProjMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, NaProjMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the na proj masters.
	*
	* @return the na proj masters
	*/
	public java.util.List<NaProjMaster> findAll();

	/**
	* Returns a range of all the na proj masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NaProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of na proj masters
	* @param end the upper bound of the range of na proj masters (not inclusive)
	* @return the range of na proj masters
	*/
	public java.util.List<NaProjMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the na proj masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NaProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of na proj masters
	* @param end the upper bound of the range of na proj masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of na proj masters
	*/
	public java.util.List<NaProjMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NaProjMaster> orderByComparator);

	/**
	* Returns an ordered range of all the na proj masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NaProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of na proj masters
	* @param end the upper bound of the range of na proj masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of na proj masters
	*/
	public java.util.List<NaProjMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NaProjMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the na proj masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of na proj masters.
	*
	* @return the number of na proj masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}