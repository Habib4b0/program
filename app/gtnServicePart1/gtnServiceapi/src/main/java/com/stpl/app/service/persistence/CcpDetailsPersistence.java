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

import com.stpl.app.exception.NoSuchCcpDetailsException;
import com.stpl.app.model.CcpDetails;

/**
 * The persistence interface for the ccp details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.CcpDetailsPersistenceImpl
 * @see CcpDetailsUtil
 * @generated
 */
@ProviderType
public interface CcpDetailsPersistence extends BasePersistence<CcpDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CcpDetailsUtil} to access the ccp details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ccp details in the entity cache if it is enabled.
	*
	* @param ccpDetails the ccp details
	*/
	public void cacheResult(CcpDetails ccpDetails);

	/**
	* Caches the ccp detailses in the entity cache if it is enabled.
	*
	* @param ccpDetailses the ccp detailses
	*/
	public void cacheResult(java.util.List<CcpDetails> ccpDetailses);

	/**
	* Creates a new ccp details with the primary key. Does not add the ccp details to the database.
	*
	* @param ccpDetailsSid the primary key for the new ccp details
	* @return the new ccp details
	*/
	public CcpDetails create(int ccpDetailsSid);

	/**
	* Removes the ccp details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ccpDetailsSid the primary key of the ccp details
	* @return the ccp details that was removed
	* @throws NoSuchCcpDetailsException if a ccp details with the primary key could not be found
	*/
	public CcpDetails remove(int ccpDetailsSid)
		throws NoSuchCcpDetailsException;

	public CcpDetails updateImpl(CcpDetails ccpDetails);

	/**
	* Returns the ccp details with the primary key or throws a {@link NoSuchCcpDetailsException} if it could not be found.
	*
	* @param ccpDetailsSid the primary key of the ccp details
	* @return the ccp details
	* @throws NoSuchCcpDetailsException if a ccp details with the primary key could not be found
	*/
	public CcpDetails findByPrimaryKey(int ccpDetailsSid)
		throws NoSuchCcpDetailsException;

	/**
	* Returns the ccp details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ccpDetailsSid the primary key of the ccp details
	* @return the ccp details, or <code>null</code> if a ccp details with the primary key could not be found
	*/
	public CcpDetails fetchByPrimaryKey(int ccpDetailsSid);

	@Override
	public java.util.Map<java.io.Serializable, CcpDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ccp detailses.
	*
	* @return the ccp detailses
	*/
	public java.util.List<CcpDetails> findAll();

	/**
	* Returns a range of all the ccp detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CcpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ccp detailses
	* @param end the upper bound of the range of ccp detailses (not inclusive)
	* @return the range of ccp detailses
	*/
	public java.util.List<CcpDetails> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ccp detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CcpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ccp detailses
	* @param end the upper bound of the range of ccp detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ccp detailses
	*/
	public java.util.List<CcpDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CcpDetails> orderByComparator);

	/**
	* Returns an ordered range of all the ccp detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CcpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ccp detailses
	* @param end the upper bound of the range of ccp detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ccp detailses
	*/
	public java.util.List<CcpDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CcpDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ccp detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ccp detailses.
	*
	* @return the number of ccp detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}