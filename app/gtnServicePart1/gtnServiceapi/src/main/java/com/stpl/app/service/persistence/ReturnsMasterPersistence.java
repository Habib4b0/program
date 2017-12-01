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

import com.stpl.app.exception.NoSuchReturnsMasterException;
import com.stpl.app.model.ReturnsMaster;

/**
 * The persistence interface for the returns master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.ReturnsMasterPersistenceImpl
 * @see ReturnsMasterUtil
 * @generated
 */
@ProviderType
public interface ReturnsMasterPersistence extends BasePersistence<ReturnsMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ReturnsMasterUtil} to access the returns master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the returns master in the entity cache if it is enabled.
	*
	* @param returnsMaster the returns master
	*/
	public void cacheResult(ReturnsMaster returnsMaster);

	/**
	* Caches the returns masters in the entity cache if it is enabled.
	*
	* @param returnsMasters the returns masters
	*/
	public void cacheResult(java.util.List<ReturnsMaster> returnsMasters);

	/**
	* Creates a new returns master with the primary key. Does not add the returns master to the database.
	*
	* @param returnsMasterSid the primary key for the new returns master
	* @return the new returns master
	*/
	public ReturnsMaster create(int returnsMasterSid);

	/**
	* Removes the returns master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param returnsMasterSid the primary key of the returns master
	* @return the returns master that was removed
	* @throws NoSuchReturnsMasterException if a returns master with the primary key could not be found
	*/
	public ReturnsMaster remove(int returnsMasterSid)
		throws NoSuchReturnsMasterException;

	public ReturnsMaster updateImpl(ReturnsMaster returnsMaster);

	/**
	* Returns the returns master with the primary key or throws a {@link NoSuchReturnsMasterException} if it could not be found.
	*
	* @param returnsMasterSid the primary key of the returns master
	* @return the returns master
	* @throws NoSuchReturnsMasterException if a returns master with the primary key could not be found
	*/
	public ReturnsMaster findByPrimaryKey(int returnsMasterSid)
		throws NoSuchReturnsMasterException;

	/**
	* Returns the returns master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param returnsMasterSid the primary key of the returns master
	* @return the returns master, or <code>null</code> if a returns master with the primary key could not be found
	*/
	public ReturnsMaster fetchByPrimaryKey(int returnsMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, ReturnsMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the returns masters.
	*
	* @return the returns masters
	*/
	public java.util.List<ReturnsMaster> findAll();

	/**
	* Returns a range of all the returns masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReturnsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of returns masters
	* @param end the upper bound of the range of returns masters (not inclusive)
	* @return the range of returns masters
	*/
	public java.util.List<ReturnsMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the returns masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReturnsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of returns masters
	* @param end the upper bound of the range of returns masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of returns masters
	*/
	public java.util.List<ReturnsMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReturnsMaster> orderByComparator);

	/**
	* Returns an ordered range of all the returns masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReturnsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of returns masters
	* @param end the upper bound of the range of returns masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of returns masters
	*/
	public java.util.List<ReturnsMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReturnsMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the returns masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of returns masters.
	*
	* @return the number of returns masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}