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

import com.stpl.app.parttwo.exception.NoSuchCffApprovalDetailsException;
import com.stpl.app.parttwo.model.CffApprovalDetails;

/**
 * The persistence interface for the cff approval details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.CffApprovalDetailsPersistenceImpl
 * @see CffApprovalDetailsUtil
 * @generated
 */
@ProviderType
public interface CffApprovalDetailsPersistence extends BasePersistence<CffApprovalDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CffApprovalDetailsUtil} to access the cff approval details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the cff approval details in the entity cache if it is enabled.
	*
	* @param cffApprovalDetails the cff approval details
	*/
	public void cacheResult(CffApprovalDetails cffApprovalDetails);

	/**
	* Caches the cff approval detailses in the entity cache if it is enabled.
	*
	* @param cffApprovalDetailses the cff approval detailses
	*/
	public void cacheResult(
		java.util.List<CffApprovalDetails> cffApprovalDetailses);

	/**
	* Creates a new cff approval details with the primary key. Does not add the cff approval details to the database.
	*
	* @param cffApprovalDetailsSid the primary key for the new cff approval details
	* @return the new cff approval details
	*/
	public CffApprovalDetails create(int cffApprovalDetailsSid);

	/**
	* Removes the cff approval details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cffApprovalDetailsSid the primary key of the cff approval details
	* @return the cff approval details that was removed
	* @throws NoSuchCffApprovalDetailsException if a cff approval details with the primary key could not be found
	*/
	public CffApprovalDetails remove(int cffApprovalDetailsSid)
		throws NoSuchCffApprovalDetailsException;

	public CffApprovalDetails updateImpl(CffApprovalDetails cffApprovalDetails);

	/**
	* Returns the cff approval details with the primary key or throws a {@link NoSuchCffApprovalDetailsException} if it could not be found.
	*
	* @param cffApprovalDetailsSid the primary key of the cff approval details
	* @return the cff approval details
	* @throws NoSuchCffApprovalDetailsException if a cff approval details with the primary key could not be found
	*/
	public CffApprovalDetails findByPrimaryKey(int cffApprovalDetailsSid)
		throws NoSuchCffApprovalDetailsException;

	/**
	* Returns the cff approval details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param cffApprovalDetailsSid the primary key of the cff approval details
	* @return the cff approval details, or <code>null</code> if a cff approval details with the primary key could not be found
	*/
	public CffApprovalDetails fetchByPrimaryKey(int cffApprovalDetailsSid);

	@Override
	public java.util.Map<java.io.Serializable, CffApprovalDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the cff approval detailses.
	*
	* @return the cff approval detailses
	*/
	public java.util.List<CffApprovalDetails> findAll();

	/**
	* Returns a range of all the cff approval detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffApprovalDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff approval detailses
	* @param end the upper bound of the range of cff approval detailses (not inclusive)
	* @return the range of cff approval detailses
	*/
	public java.util.List<CffApprovalDetails> findAll(int start, int end);

	/**
	* Returns an ordered range of all the cff approval detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffApprovalDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff approval detailses
	* @param end the upper bound of the range of cff approval detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of cff approval detailses
	*/
	public java.util.List<CffApprovalDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CffApprovalDetails> orderByComparator);

	/**
	* Returns an ordered range of all the cff approval detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffApprovalDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff approval detailses
	* @param end the upper bound of the range of cff approval detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of cff approval detailses
	*/
	public java.util.List<CffApprovalDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CffApprovalDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the cff approval detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of cff approval detailses.
	*
	* @return the number of cff approval detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}