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

import com.stpl.app.exception.NoSuchIvldSalesMasterException;
import com.stpl.app.model.IvldSalesMaster;

/**
 * The persistence interface for the ivld sales master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.IvldSalesMasterPersistenceImpl
 * @see IvldSalesMasterUtil
 * @generated
 */
@ProviderType
public interface IvldSalesMasterPersistence extends BasePersistence<IvldSalesMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link IvldSalesMasterUtil} to access the ivld sales master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ivld sales master in the entity cache if it is enabled.
	*
	* @param ivldSalesMaster the ivld sales master
	*/
	public void cacheResult(IvldSalesMaster ivldSalesMaster);

	/**
	* Caches the ivld sales masters in the entity cache if it is enabled.
	*
	* @param ivldSalesMasters the ivld sales masters
	*/
	public void cacheResult(java.util.List<IvldSalesMaster> ivldSalesMasters);

	/**
	* Creates a new ivld sales master with the primary key. Does not add the ivld sales master to the database.
	*
	* @param ivldSalesMasterSid the primary key for the new ivld sales master
	* @return the new ivld sales master
	*/
	public IvldSalesMaster create(int ivldSalesMasterSid);

	/**
	* Removes the ivld sales master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldSalesMasterSid the primary key of the ivld sales master
	* @return the ivld sales master that was removed
	* @throws NoSuchIvldSalesMasterException if a ivld sales master with the primary key could not be found
	*/
	public IvldSalesMaster remove(int ivldSalesMasterSid)
		throws NoSuchIvldSalesMasterException;

	public IvldSalesMaster updateImpl(IvldSalesMaster ivldSalesMaster);

	/**
	* Returns the ivld sales master with the primary key or throws a {@link NoSuchIvldSalesMasterException} if it could not be found.
	*
	* @param ivldSalesMasterSid the primary key of the ivld sales master
	* @return the ivld sales master
	* @throws NoSuchIvldSalesMasterException if a ivld sales master with the primary key could not be found
	*/
	public IvldSalesMaster findByPrimaryKey(int ivldSalesMasterSid)
		throws NoSuchIvldSalesMasterException;

	/**
	* Returns the ivld sales master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldSalesMasterSid the primary key of the ivld sales master
	* @return the ivld sales master, or <code>null</code> if a ivld sales master with the primary key could not be found
	*/
	public IvldSalesMaster fetchByPrimaryKey(int ivldSalesMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, IvldSalesMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ivld sales masters.
	*
	* @return the ivld sales masters
	*/
	public java.util.List<IvldSalesMaster> findAll();

	/**
	* Returns a range of all the ivld sales masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldSalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld sales masters
	* @param end the upper bound of the range of ivld sales masters (not inclusive)
	* @return the range of ivld sales masters
	*/
	public java.util.List<IvldSalesMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ivld sales masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldSalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld sales masters
	* @param end the upper bound of the range of ivld sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivld sales masters
	*/
	public java.util.List<IvldSalesMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldSalesMaster> orderByComparator);

	/**
	* Returns an ordered range of all the ivld sales masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldSalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld sales masters
	* @param end the upper bound of the range of ivld sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivld sales masters
	*/
	public java.util.List<IvldSalesMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldSalesMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ivld sales masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ivld sales masters.
	*
	* @return the number of ivld sales masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}