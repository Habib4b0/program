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

import com.stpl.app.exception.NoSuchRsContractDetailsFrException;
import com.stpl.app.model.RsContractDetailsFr;

/**
 * The persistence interface for the rs contract details fr service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.RsContractDetailsFrPersistenceImpl
 * @see RsContractDetailsFrUtil
 * @generated
 */
@ProviderType
public interface RsContractDetailsFrPersistence extends BasePersistence<RsContractDetailsFr> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RsContractDetailsFrUtil} to access the rs contract details fr persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the rs contract details fr in the entity cache if it is enabled.
	*
	* @param rsContractDetailsFr the rs contract details fr
	*/
	public void cacheResult(RsContractDetailsFr rsContractDetailsFr);

	/**
	* Caches the rs contract details frs in the entity cache if it is enabled.
	*
	* @param rsContractDetailsFrs the rs contract details frs
	*/
	public void cacheResult(
		java.util.List<RsContractDetailsFr> rsContractDetailsFrs);

	/**
	* Creates a new rs contract details fr with the primary key. Does not add the rs contract details fr to the database.
	*
	* @param rsContractDetailsFrSid the primary key for the new rs contract details fr
	* @return the new rs contract details fr
	*/
	public RsContractDetailsFr create(int rsContractDetailsFrSid);

	/**
	* Removes the rs contract details fr with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param rsContractDetailsFrSid the primary key of the rs contract details fr
	* @return the rs contract details fr that was removed
	* @throws NoSuchRsContractDetailsFrException if a rs contract details fr with the primary key could not be found
	*/
	public RsContractDetailsFr remove(int rsContractDetailsFrSid)
		throws NoSuchRsContractDetailsFrException;

	public RsContractDetailsFr updateImpl(
		RsContractDetailsFr rsContractDetailsFr);

	/**
	* Returns the rs contract details fr with the primary key or throws a {@link NoSuchRsContractDetailsFrException} if it could not be found.
	*
	* @param rsContractDetailsFrSid the primary key of the rs contract details fr
	* @return the rs contract details fr
	* @throws NoSuchRsContractDetailsFrException if a rs contract details fr with the primary key could not be found
	*/
	public RsContractDetailsFr findByPrimaryKey(int rsContractDetailsFrSid)
		throws NoSuchRsContractDetailsFrException;

	/**
	* Returns the rs contract details fr with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param rsContractDetailsFrSid the primary key of the rs contract details fr
	* @return the rs contract details fr, or <code>null</code> if a rs contract details fr with the primary key could not be found
	*/
	public RsContractDetailsFr fetchByPrimaryKey(int rsContractDetailsFrSid);

	@Override
	public java.util.Map<java.io.Serializable, RsContractDetailsFr> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the rs contract details frs.
	*
	* @return the rs contract details frs
	*/
	public java.util.List<RsContractDetailsFr> findAll();

	/**
	* Returns a range of all the rs contract details frs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsContractDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rs contract details frs
	* @param end the upper bound of the range of rs contract details frs (not inclusive)
	* @return the range of rs contract details frs
	*/
	public java.util.List<RsContractDetailsFr> findAll(int start, int end);

	/**
	* Returns an ordered range of all the rs contract details frs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsContractDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rs contract details frs
	* @param end the upper bound of the range of rs contract details frs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of rs contract details frs
	*/
	public java.util.List<RsContractDetailsFr> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RsContractDetailsFr> orderByComparator);

	/**
	* Returns an ordered range of all the rs contract details frs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsContractDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rs contract details frs
	* @param end the upper bound of the range of rs contract details frs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of rs contract details frs
	*/
	public java.util.List<RsContractDetailsFr> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RsContractDetailsFr> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the rs contract details frs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of rs contract details frs.
	*
	* @return the number of rs contract details frs
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}