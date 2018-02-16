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

import com.stpl.app.parttwo.exception.NoSuchStCffOutboundMasterException;
import com.stpl.app.parttwo.model.StCffOutboundMaster;

/**
 * The persistence interface for the st cff outbound master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.StCffOutboundMasterPersistenceImpl
 * @see StCffOutboundMasterUtil
 * @generated
 */
@ProviderType
public interface StCffOutboundMasterPersistence extends BasePersistence<StCffOutboundMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link StCffOutboundMasterUtil} to access the st cff outbound master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the st cff outbound master in the entity cache if it is enabled.
	*
	* @param stCffOutboundMaster the st cff outbound master
	*/
	public void cacheResult(StCffOutboundMaster stCffOutboundMaster);

	/**
	* Caches the st cff outbound masters in the entity cache if it is enabled.
	*
	* @param stCffOutboundMasters the st cff outbound masters
	*/
	public void cacheResult(
		java.util.List<StCffOutboundMaster> stCffOutboundMasters);

	/**
	* Creates a new st cff outbound master with the primary key. Does not add the st cff outbound master to the database.
	*
	* @param stCffOutboundMasterPK the primary key for the new st cff outbound master
	* @return the new st cff outbound master
	*/
	public StCffOutboundMaster create(
		StCffOutboundMasterPK stCffOutboundMasterPK);

	/**
	* Removes the st cff outbound master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stCffOutboundMasterPK the primary key of the st cff outbound master
	* @return the st cff outbound master that was removed
	* @throws NoSuchStCffOutboundMasterException if a st cff outbound master with the primary key could not be found
	*/
	public StCffOutboundMaster remove(
		StCffOutboundMasterPK stCffOutboundMasterPK)
		throws NoSuchStCffOutboundMasterException;

	public StCffOutboundMaster updateImpl(
		StCffOutboundMaster stCffOutboundMaster);

	/**
	* Returns the st cff outbound master with the primary key or throws a {@link NoSuchStCffOutboundMasterException} if it could not be found.
	*
	* @param stCffOutboundMasterPK the primary key of the st cff outbound master
	* @return the st cff outbound master
	* @throws NoSuchStCffOutboundMasterException if a st cff outbound master with the primary key could not be found
	*/
	public StCffOutboundMaster findByPrimaryKey(
		StCffOutboundMasterPK stCffOutboundMasterPK)
		throws NoSuchStCffOutboundMasterException;

	/**
	* Returns the st cff outbound master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param stCffOutboundMasterPK the primary key of the st cff outbound master
	* @return the st cff outbound master, or <code>null</code> if a st cff outbound master with the primary key could not be found
	*/
	public StCffOutboundMaster fetchByPrimaryKey(
		StCffOutboundMasterPK stCffOutboundMasterPK);

	@Override
	public java.util.Map<java.io.Serializable, StCffOutboundMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the st cff outbound masters.
	*
	* @return the st cff outbound masters
	*/
	public java.util.List<StCffOutboundMaster> findAll();

	/**
	* Returns a range of all the st cff outbound masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StCffOutboundMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st cff outbound masters
	* @param end the upper bound of the range of st cff outbound masters (not inclusive)
	* @return the range of st cff outbound masters
	*/
	public java.util.List<StCffOutboundMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the st cff outbound masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StCffOutboundMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st cff outbound masters
	* @param end the upper bound of the range of st cff outbound masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of st cff outbound masters
	*/
	public java.util.List<StCffOutboundMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StCffOutboundMaster> orderByComparator);

	/**
	* Returns an ordered range of all the st cff outbound masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StCffOutboundMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st cff outbound masters
	* @param end the upper bound of the range of st cff outbound masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of st cff outbound masters
	*/
	public java.util.List<StCffOutboundMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StCffOutboundMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the st cff outbound masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of st cff outbound masters.
	*
	* @return the number of st cff outbound masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}