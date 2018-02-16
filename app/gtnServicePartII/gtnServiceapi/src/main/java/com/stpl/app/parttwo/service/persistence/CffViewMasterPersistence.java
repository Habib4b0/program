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

import com.stpl.app.parttwo.exception.NoSuchCffViewMasterException;
import com.stpl.app.parttwo.model.CffViewMaster;

/**
 * The persistence interface for the cff view master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.CffViewMasterPersistenceImpl
 * @see CffViewMasterUtil
 * @generated
 */
@ProviderType
public interface CffViewMasterPersistence extends BasePersistence<CffViewMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CffViewMasterUtil} to access the cff view master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the cff view master in the entity cache if it is enabled.
	*
	* @param cffViewMaster the cff view master
	*/
	public void cacheResult(CffViewMaster cffViewMaster);

	/**
	* Caches the cff view masters in the entity cache if it is enabled.
	*
	* @param cffViewMasters the cff view masters
	*/
	public void cacheResult(java.util.List<CffViewMaster> cffViewMasters);

	/**
	* Creates a new cff view master with the primary key. Does not add the cff view master to the database.
	*
	* @param cffViewMasterSid the primary key for the new cff view master
	* @return the new cff view master
	*/
	public CffViewMaster create(int cffViewMasterSid);

	/**
	* Removes the cff view master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cffViewMasterSid the primary key of the cff view master
	* @return the cff view master that was removed
	* @throws NoSuchCffViewMasterException if a cff view master with the primary key could not be found
	*/
	public CffViewMaster remove(int cffViewMasterSid)
		throws NoSuchCffViewMasterException;

	public CffViewMaster updateImpl(CffViewMaster cffViewMaster);

	/**
	* Returns the cff view master with the primary key or throws a {@link NoSuchCffViewMasterException} if it could not be found.
	*
	* @param cffViewMasterSid the primary key of the cff view master
	* @return the cff view master
	* @throws NoSuchCffViewMasterException if a cff view master with the primary key could not be found
	*/
	public CffViewMaster findByPrimaryKey(int cffViewMasterSid)
		throws NoSuchCffViewMasterException;

	/**
	* Returns the cff view master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param cffViewMasterSid the primary key of the cff view master
	* @return the cff view master, or <code>null</code> if a cff view master with the primary key could not be found
	*/
	public CffViewMaster fetchByPrimaryKey(int cffViewMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, CffViewMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the cff view masters.
	*
	* @return the cff view masters
	*/
	public java.util.List<CffViewMaster> findAll();

	/**
	* Returns a range of all the cff view masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff view masters
	* @param end the upper bound of the range of cff view masters (not inclusive)
	* @return the range of cff view masters
	*/
	public java.util.List<CffViewMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the cff view masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff view masters
	* @param end the upper bound of the range of cff view masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of cff view masters
	*/
	public java.util.List<CffViewMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CffViewMaster> orderByComparator);

	/**
	* Returns an ordered range of all the cff view masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff view masters
	* @param end the upper bound of the range of cff view masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of cff view masters
	*/
	public java.util.List<CffViewMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CffViewMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the cff view masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of cff view masters.
	*
	* @return the number of cff view masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}