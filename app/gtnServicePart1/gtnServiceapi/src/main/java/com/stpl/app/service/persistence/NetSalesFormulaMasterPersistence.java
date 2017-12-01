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

import com.stpl.app.exception.NoSuchNetSalesFormulaMasterException;
import com.stpl.app.model.NetSalesFormulaMaster;

/**
 * The persistence interface for the net sales formula master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.NetSalesFormulaMasterPersistenceImpl
 * @see NetSalesFormulaMasterUtil
 * @generated
 */
@ProviderType
public interface NetSalesFormulaMasterPersistence extends BasePersistence<NetSalesFormulaMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link NetSalesFormulaMasterUtil} to access the net sales formula master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the net sales formula master in the entity cache if it is enabled.
	*
	* @param netSalesFormulaMaster the net sales formula master
	*/
	public void cacheResult(NetSalesFormulaMaster netSalesFormulaMaster);

	/**
	* Caches the net sales formula masters in the entity cache if it is enabled.
	*
	* @param netSalesFormulaMasters the net sales formula masters
	*/
	public void cacheResult(
		java.util.List<NetSalesFormulaMaster> netSalesFormulaMasters);

	/**
	* Creates a new net sales formula master with the primary key. Does not add the net sales formula master to the database.
	*
	* @param netSalesFormulaMasterSid the primary key for the new net sales formula master
	* @return the new net sales formula master
	*/
	public NetSalesFormulaMaster create(int netSalesFormulaMasterSid);

	/**
	* Removes the net sales formula master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param netSalesFormulaMasterSid the primary key of the net sales formula master
	* @return the net sales formula master that was removed
	* @throws NoSuchNetSalesFormulaMasterException if a net sales formula master with the primary key could not be found
	*/
	public NetSalesFormulaMaster remove(int netSalesFormulaMasterSid)
		throws NoSuchNetSalesFormulaMasterException;

	public NetSalesFormulaMaster updateImpl(
		NetSalesFormulaMaster netSalesFormulaMaster);

	/**
	* Returns the net sales formula master with the primary key or throws a {@link NoSuchNetSalesFormulaMasterException} if it could not be found.
	*
	* @param netSalesFormulaMasterSid the primary key of the net sales formula master
	* @return the net sales formula master
	* @throws NoSuchNetSalesFormulaMasterException if a net sales formula master with the primary key could not be found
	*/
	public NetSalesFormulaMaster findByPrimaryKey(int netSalesFormulaMasterSid)
		throws NoSuchNetSalesFormulaMasterException;

	/**
	* Returns the net sales formula master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param netSalesFormulaMasterSid the primary key of the net sales formula master
	* @return the net sales formula master, or <code>null</code> if a net sales formula master with the primary key could not be found
	*/
	public NetSalesFormulaMaster fetchByPrimaryKey(int netSalesFormulaMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, NetSalesFormulaMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the net sales formula masters.
	*
	* @return the net sales formula masters
	*/
	public java.util.List<NetSalesFormulaMaster> findAll();

	/**
	* Returns a range of all the net sales formula masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NetSalesFormulaMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of net sales formula masters
	* @param end the upper bound of the range of net sales formula masters (not inclusive)
	* @return the range of net sales formula masters
	*/
	public java.util.List<NetSalesFormulaMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the net sales formula masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NetSalesFormulaMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of net sales formula masters
	* @param end the upper bound of the range of net sales formula masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of net sales formula masters
	*/
	public java.util.List<NetSalesFormulaMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NetSalesFormulaMaster> orderByComparator);

	/**
	* Returns an ordered range of all the net sales formula masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NetSalesFormulaMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of net sales formula masters
	* @param end the upper bound of the range of net sales formula masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of net sales formula masters
	*/
	public java.util.List<NetSalesFormulaMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NetSalesFormulaMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the net sales formula masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of net sales formula masters.
	*
	* @return the number of net sales formula masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}