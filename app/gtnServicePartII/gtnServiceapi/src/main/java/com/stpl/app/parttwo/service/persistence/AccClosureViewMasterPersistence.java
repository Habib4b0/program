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

import com.stpl.app.parttwo.exception.NoSuchAccClosureViewMasterException;
import com.stpl.app.parttwo.model.AccClosureViewMaster;

/**
 * The persistence interface for the acc closure view master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.AccClosureViewMasterPersistenceImpl
 * @see AccClosureViewMasterUtil
 * @generated
 */
@ProviderType
public interface AccClosureViewMasterPersistence extends BasePersistence<AccClosureViewMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AccClosureViewMasterUtil} to access the acc closure view master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the acc closure view master in the entity cache if it is enabled.
	*
	* @param accClosureViewMaster the acc closure view master
	*/
	public void cacheResult(AccClosureViewMaster accClosureViewMaster);

	/**
	* Caches the acc closure view masters in the entity cache if it is enabled.
	*
	* @param accClosureViewMasters the acc closure view masters
	*/
	public void cacheResult(
		java.util.List<AccClosureViewMaster> accClosureViewMasters);

	/**
	* Creates a new acc closure view master with the primary key. Does not add the acc closure view master to the database.
	*
	* @param accClosureViewMasterSid the primary key for the new acc closure view master
	* @return the new acc closure view master
	*/
	public AccClosureViewMaster create(int accClosureViewMasterSid);

	/**
	* Removes the acc closure view master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accClosureViewMasterSid the primary key of the acc closure view master
	* @return the acc closure view master that was removed
	* @throws NoSuchAccClosureViewMasterException if a acc closure view master with the primary key could not be found
	*/
	public AccClosureViewMaster remove(int accClosureViewMasterSid)
		throws NoSuchAccClosureViewMasterException;

	public AccClosureViewMaster updateImpl(
		AccClosureViewMaster accClosureViewMaster);

	/**
	* Returns the acc closure view master with the primary key or throws a {@link NoSuchAccClosureViewMasterException} if it could not be found.
	*
	* @param accClosureViewMasterSid the primary key of the acc closure view master
	* @return the acc closure view master
	* @throws NoSuchAccClosureViewMasterException if a acc closure view master with the primary key could not be found
	*/
	public AccClosureViewMaster findByPrimaryKey(int accClosureViewMasterSid)
		throws NoSuchAccClosureViewMasterException;

	/**
	* Returns the acc closure view master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accClosureViewMasterSid the primary key of the acc closure view master
	* @return the acc closure view master, or <code>null</code> if a acc closure view master with the primary key could not be found
	*/
	public AccClosureViewMaster fetchByPrimaryKey(int accClosureViewMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, AccClosureViewMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the acc closure view masters.
	*
	* @return the acc closure view masters
	*/
	public java.util.List<AccClosureViewMaster> findAll();

	/**
	* Returns a range of all the acc closure view masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccClosureViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of acc closure view masters
	* @param end the upper bound of the range of acc closure view masters (not inclusive)
	* @return the range of acc closure view masters
	*/
	public java.util.List<AccClosureViewMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the acc closure view masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccClosureViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of acc closure view masters
	* @param end the upper bound of the range of acc closure view masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of acc closure view masters
	*/
	public java.util.List<AccClosureViewMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccClosureViewMaster> orderByComparator);

	/**
	* Returns an ordered range of all the acc closure view masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccClosureViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of acc closure view masters
	* @param end the upper bound of the range of acc closure view masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of acc closure view masters
	*/
	public java.util.List<AccClosureViewMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccClosureViewMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the acc closure view masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of acc closure view masters.
	*
	* @return the number of acc closure view masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}