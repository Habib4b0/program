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

import com.stpl.app.exception.NoSuchRsDetailsFrException;
import com.stpl.app.model.RsDetailsFr;

/**
 * The persistence interface for the rs details fr service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.RsDetailsFrPersistenceImpl
 * @see RsDetailsFrUtil
 * @generated
 */
@ProviderType
public interface RsDetailsFrPersistence extends BasePersistence<RsDetailsFr> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RsDetailsFrUtil} to access the rs details fr persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the rs details fr in the entity cache if it is enabled.
	*
	* @param rsDetailsFr the rs details fr
	*/
	public void cacheResult(RsDetailsFr rsDetailsFr);

	/**
	* Caches the rs details frs in the entity cache if it is enabled.
	*
	* @param rsDetailsFrs the rs details frs
	*/
	public void cacheResult(java.util.List<RsDetailsFr> rsDetailsFrs);

	/**
	* Creates a new rs details fr with the primary key. Does not add the rs details fr to the database.
	*
	* @param rsDetailsFrSid the primary key for the new rs details fr
	* @return the new rs details fr
	*/
	public RsDetailsFr create(int rsDetailsFrSid);

	/**
	* Removes the rs details fr with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param rsDetailsFrSid the primary key of the rs details fr
	* @return the rs details fr that was removed
	* @throws NoSuchRsDetailsFrException if a rs details fr with the primary key could not be found
	*/
	public RsDetailsFr remove(int rsDetailsFrSid)
		throws NoSuchRsDetailsFrException;

	public RsDetailsFr updateImpl(RsDetailsFr rsDetailsFr);

	/**
	* Returns the rs details fr with the primary key or throws a {@link NoSuchRsDetailsFrException} if it could not be found.
	*
	* @param rsDetailsFrSid the primary key of the rs details fr
	* @return the rs details fr
	* @throws NoSuchRsDetailsFrException if a rs details fr with the primary key could not be found
	*/
	public RsDetailsFr findByPrimaryKey(int rsDetailsFrSid)
		throws NoSuchRsDetailsFrException;

	/**
	* Returns the rs details fr with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param rsDetailsFrSid the primary key of the rs details fr
	* @return the rs details fr, or <code>null</code> if a rs details fr with the primary key could not be found
	*/
	public RsDetailsFr fetchByPrimaryKey(int rsDetailsFrSid);

	@Override
	public java.util.Map<java.io.Serializable, RsDetailsFr> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the rs details frs.
	*
	* @return the rs details frs
	*/
	public java.util.List<RsDetailsFr> findAll();

	/**
	* Returns a range of all the rs details frs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rs details frs
	* @param end the upper bound of the range of rs details frs (not inclusive)
	* @return the range of rs details frs
	*/
	public java.util.List<RsDetailsFr> findAll(int start, int end);

	/**
	* Returns an ordered range of all the rs details frs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rs details frs
	* @param end the upper bound of the range of rs details frs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of rs details frs
	*/
	public java.util.List<RsDetailsFr> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RsDetailsFr> orderByComparator);

	/**
	* Returns an ordered range of all the rs details frs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rs details frs
	* @param end the upper bound of the range of rs details frs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of rs details frs
	*/
	public java.util.List<RsDetailsFr> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RsDetailsFr> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the rs details frs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of rs details frs.
	*
	* @return the number of rs details frs
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}