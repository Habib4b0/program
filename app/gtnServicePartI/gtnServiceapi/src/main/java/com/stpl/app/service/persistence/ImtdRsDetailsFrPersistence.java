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

import com.stpl.app.exception.NoSuchImtdRsDetailsFrException;
import com.stpl.app.model.ImtdRsDetailsFr;

/**
 * The persistence interface for the imtd rs details fr service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.ImtdRsDetailsFrPersistenceImpl
 * @see ImtdRsDetailsFrUtil
 * @generated
 */
@ProviderType
public interface ImtdRsDetailsFrPersistence extends BasePersistence<ImtdRsDetailsFr> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ImtdRsDetailsFrUtil} to access the imtd rs details fr persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the imtd rs details fr in the entity cache if it is enabled.
	*
	* @param imtdRsDetailsFr the imtd rs details fr
	*/
	public void cacheResult(ImtdRsDetailsFr imtdRsDetailsFr);

	/**
	* Caches the imtd rs details frs in the entity cache if it is enabled.
	*
	* @param imtdRsDetailsFrs the imtd rs details frs
	*/
	public void cacheResult(java.util.List<ImtdRsDetailsFr> imtdRsDetailsFrs);

	/**
	* Creates a new imtd rs details fr with the primary key. Does not add the imtd rs details fr to the database.
	*
	* @param imtdRsDetailsFrSid the primary key for the new imtd rs details fr
	* @return the new imtd rs details fr
	*/
	public ImtdRsDetailsFr create(int imtdRsDetailsFrSid);

	/**
	* Removes the imtd rs details fr with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param imtdRsDetailsFrSid the primary key of the imtd rs details fr
	* @return the imtd rs details fr that was removed
	* @throws NoSuchImtdRsDetailsFrException if a imtd rs details fr with the primary key could not be found
	*/
	public ImtdRsDetailsFr remove(int imtdRsDetailsFrSid)
		throws NoSuchImtdRsDetailsFrException;

	public ImtdRsDetailsFr updateImpl(ImtdRsDetailsFr imtdRsDetailsFr);

	/**
	* Returns the imtd rs details fr with the primary key or throws a {@link NoSuchImtdRsDetailsFrException} if it could not be found.
	*
	* @param imtdRsDetailsFrSid the primary key of the imtd rs details fr
	* @return the imtd rs details fr
	* @throws NoSuchImtdRsDetailsFrException if a imtd rs details fr with the primary key could not be found
	*/
	public ImtdRsDetailsFr findByPrimaryKey(int imtdRsDetailsFrSid)
		throws NoSuchImtdRsDetailsFrException;

	/**
	* Returns the imtd rs details fr with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param imtdRsDetailsFrSid the primary key of the imtd rs details fr
	* @return the imtd rs details fr, or <code>null</code> if a imtd rs details fr with the primary key could not be found
	*/
	public ImtdRsDetailsFr fetchByPrimaryKey(int imtdRsDetailsFrSid);

	@Override
	public java.util.Map<java.io.Serializable, ImtdRsDetailsFr> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the imtd rs details frs.
	*
	* @return the imtd rs details frs
	*/
	public java.util.List<ImtdRsDetailsFr> findAll();

	/**
	* Returns a range of all the imtd rs details frs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdRsDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd rs details frs
	* @param end the upper bound of the range of imtd rs details frs (not inclusive)
	* @return the range of imtd rs details frs
	*/
	public java.util.List<ImtdRsDetailsFr> findAll(int start, int end);

	/**
	* Returns an ordered range of all the imtd rs details frs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdRsDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd rs details frs
	* @param end the upper bound of the range of imtd rs details frs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of imtd rs details frs
	*/
	public java.util.List<ImtdRsDetailsFr> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImtdRsDetailsFr> orderByComparator);

	/**
	* Returns an ordered range of all the imtd rs details frs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdRsDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd rs details frs
	* @param end the upper bound of the range of imtd rs details frs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of imtd rs details frs
	*/
	public java.util.List<ImtdRsDetailsFr> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImtdRsDetailsFr> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the imtd rs details frs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of imtd rs details frs.
	*
	* @return the number of imtd rs details frs
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}