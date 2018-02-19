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

import com.stpl.app.parttwo.exception.NoSuchCffMasterException;
import com.stpl.app.parttwo.model.CffMaster;

/**
 * The persistence interface for the cff master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.CffMasterPersistenceImpl
 * @see CffMasterUtil
 * @generated
 */
@ProviderType
public interface CffMasterPersistence extends BasePersistence<CffMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CffMasterUtil} to access the cff master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the cff master in the entity cache if it is enabled.
	*
	* @param cffMaster the cff master
	*/
	public void cacheResult(CffMaster cffMaster);

	/**
	* Caches the cff masters in the entity cache if it is enabled.
	*
	* @param cffMasters the cff masters
	*/
	public void cacheResult(java.util.List<CffMaster> cffMasters);

	/**
	* Creates a new cff master with the primary key. Does not add the cff master to the database.
	*
	* @param cffMasterSid the primary key for the new cff master
	* @return the new cff master
	*/
	public CffMaster create(int cffMasterSid);

	/**
	* Removes the cff master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cffMasterSid the primary key of the cff master
	* @return the cff master that was removed
	* @throws NoSuchCffMasterException if a cff master with the primary key could not be found
	*/
	public CffMaster remove(int cffMasterSid) throws NoSuchCffMasterException;

	public CffMaster updateImpl(CffMaster cffMaster);

	/**
	* Returns the cff master with the primary key or throws a {@link NoSuchCffMasterException} if it could not be found.
	*
	* @param cffMasterSid the primary key of the cff master
	* @return the cff master
	* @throws NoSuchCffMasterException if a cff master with the primary key could not be found
	*/
	public CffMaster findByPrimaryKey(int cffMasterSid)
		throws NoSuchCffMasterException;

	/**
	* Returns the cff master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param cffMasterSid the primary key of the cff master
	* @return the cff master, or <code>null</code> if a cff master with the primary key could not be found
	*/
	public CffMaster fetchByPrimaryKey(int cffMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, CffMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the cff masters.
	*
	* @return the cff masters
	*/
	public java.util.List<CffMaster> findAll();

	/**
	* Returns a range of all the cff masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff masters
	* @param end the upper bound of the range of cff masters (not inclusive)
	* @return the range of cff masters
	*/
	public java.util.List<CffMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the cff masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff masters
	* @param end the upper bound of the range of cff masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of cff masters
	*/
	public java.util.List<CffMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CffMaster> orderByComparator);

	/**
	* Returns an ordered range of all the cff masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff masters
	* @param end the upper bound of the range of cff masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of cff masters
	*/
	public java.util.List<CffMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CffMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the cff masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of cff masters.
	*
	* @return the number of cff masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}