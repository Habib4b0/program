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

import com.stpl.app.exception.NoSuchImtdCfpDetailsException;
import com.stpl.app.model.ImtdCfpDetails;

import java.util.Date;

/**
 * The persistence interface for the imtd cfp details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.ImtdCfpDetailsPersistenceImpl
 * @see ImtdCfpDetailsUtil
 * @generated
 */
@ProviderType
public interface ImtdCfpDetailsPersistence extends BasePersistence<ImtdCfpDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ImtdCfpDetailsUtil} to access the imtd cfp details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the imtd cfp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreatedDate the imtd created date
	* @return the matching imtd cfp detailses
	*/
	public java.util.List<ImtdCfpDetails> findByImtdCfpSearch(
		java.lang.String usersSid, java.lang.String sessionId,
		Date imtdCreatedDate);

	/**
	* Returns a range of all the imtd cfp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdCfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreatedDate the imtd created date
	* @param start the lower bound of the range of imtd cfp detailses
	* @param end the upper bound of the range of imtd cfp detailses (not inclusive)
	* @return the range of matching imtd cfp detailses
	*/
	public java.util.List<ImtdCfpDetails> findByImtdCfpSearch(
		java.lang.String usersSid, java.lang.String sessionId,
		Date imtdCreatedDate, int start, int end);

	/**
	* Returns an ordered range of all the imtd cfp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdCfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreatedDate the imtd created date
	* @param start the lower bound of the range of imtd cfp detailses
	* @param end the upper bound of the range of imtd cfp detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching imtd cfp detailses
	*/
	public java.util.List<ImtdCfpDetails> findByImtdCfpSearch(
		java.lang.String usersSid, java.lang.String sessionId,
		Date imtdCreatedDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImtdCfpDetails> orderByComparator);

	/**
	* Returns an ordered range of all the imtd cfp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdCfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreatedDate the imtd created date
	* @param start the lower bound of the range of imtd cfp detailses
	* @param end the upper bound of the range of imtd cfp detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching imtd cfp detailses
	*/
	public java.util.List<ImtdCfpDetails> findByImtdCfpSearch(
		java.lang.String usersSid, java.lang.String sessionId,
		Date imtdCreatedDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImtdCfpDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first imtd cfp details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreatedDate the imtd created date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching imtd cfp details
	* @throws NoSuchImtdCfpDetailsException if a matching imtd cfp details could not be found
	*/
	public ImtdCfpDetails findByImtdCfpSearch_First(java.lang.String usersSid,
		java.lang.String sessionId, Date imtdCreatedDate,
		com.liferay.portal.kernel.util.OrderByComparator<ImtdCfpDetails> orderByComparator)
		throws NoSuchImtdCfpDetailsException;

	/**
	* Returns the first imtd cfp details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreatedDate the imtd created date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching imtd cfp details, or <code>null</code> if a matching imtd cfp details could not be found
	*/
	public ImtdCfpDetails fetchByImtdCfpSearch_First(
		java.lang.String usersSid, java.lang.String sessionId,
		Date imtdCreatedDate,
		com.liferay.portal.kernel.util.OrderByComparator<ImtdCfpDetails> orderByComparator);

	/**
	* Returns the last imtd cfp details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreatedDate the imtd created date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching imtd cfp details
	* @throws NoSuchImtdCfpDetailsException if a matching imtd cfp details could not be found
	*/
	public ImtdCfpDetails findByImtdCfpSearch_Last(java.lang.String usersSid,
		java.lang.String sessionId, Date imtdCreatedDate,
		com.liferay.portal.kernel.util.OrderByComparator<ImtdCfpDetails> orderByComparator)
		throws NoSuchImtdCfpDetailsException;

	/**
	* Returns the last imtd cfp details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreatedDate the imtd created date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching imtd cfp details, or <code>null</code> if a matching imtd cfp details could not be found
	*/
	public ImtdCfpDetails fetchByImtdCfpSearch_Last(java.lang.String usersSid,
		java.lang.String sessionId, Date imtdCreatedDate,
		com.liferay.portal.kernel.util.OrderByComparator<ImtdCfpDetails> orderByComparator);

	/**
	* Returns the imtd cfp detailses before and after the current imtd cfp details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	*
	* @param imtdCfpDetailsSid the primary key of the current imtd cfp details
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreatedDate the imtd created date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next imtd cfp details
	* @throws NoSuchImtdCfpDetailsException if a imtd cfp details with the primary key could not be found
	*/
	public ImtdCfpDetails[] findByImtdCfpSearch_PrevAndNext(
		int imtdCfpDetailsSid, java.lang.String usersSid,
		java.lang.String sessionId, Date imtdCreatedDate,
		com.liferay.portal.kernel.util.OrderByComparator<ImtdCfpDetails> orderByComparator)
		throws NoSuchImtdCfpDetailsException;

	/**
	* Removes all the imtd cfp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63; from the database.
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreatedDate the imtd created date
	*/
	public void removeByImtdCfpSearch(java.lang.String usersSid,
		java.lang.String sessionId, Date imtdCreatedDate);

	/**
	* Returns the number of imtd cfp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreatedDate the imtd created date
	* @return the number of matching imtd cfp detailses
	*/
	public int countByImtdCfpSearch(java.lang.String usersSid,
		java.lang.String sessionId, Date imtdCreatedDate);

	/**
	* Caches the imtd cfp details in the entity cache if it is enabled.
	*
	* @param imtdCfpDetails the imtd cfp details
	*/
	public void cacheResult(ImtdCfpDetails imtdCfpDetails);

	/**
	* Caches the imtd cfp detailses in the entity cache if it is enabled.
	*
	* @param imtdCfpDetailses the imtd cfp detailses
	*/
	public void cacheResult(java.util.List<ImtdCfpDetails> imtdCfpDetailses);

	/**
	* Creates a new imtd cfp details with the primary key. Does not add the imtd cfp details to the database.
	*
	* @param imtdCfpDetailsSid the primary key for the new imtd cfp details
	* @return the new imtd cfp details
	*/
	public ImtdCfpDetails create(int imtdCfpDetailsSid);

	/**
	* Removes the imtd cfp details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param imtdCfpDetailsSid the primary key of the imtd cfp details
	* @return the imtd cfp details that was removed
	* @throws NoSuchImtdCfpDetailsException if a imtd cfp details with the primary key could not be found
	*/
	public ImtdCfpDetails remove(int imtdCfpDetailsSid)
		throws NoSuchImtdCfpDetailsException;

	public ImtdCfpDetails updateImpl(ImtdCfpDetails imtdCfpDetails);

	/**
	* Returns the imtd cfp details with the primary key or throws a {@link NoSuchImtdCfpDetailsException} if it could not be found.
	*
	* @param imtdCfpDetailsSid the primary key of the imtd cfp details
	* @return the imtd cfp details
	* @throws NoSuchImtdCfpDetailsException if a imtd cfp details with the primary key could not be found
	*/
	public ImtdCfpDetails findByPrimaryKey(int imtdCfpDetailsSid)
		throws NoSuchImtdCfpDetailsException;

	/**
	* Returns the imtd cfp details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param imtdCfpDetailsSid the primary key of the imtd cfp details
	* @return the imtd cfp details, or <code>null</code> if a imtd cfp details with the primary key could not be found
	*/
	public ImtdCfpDetails fetchByPrimaryKey(int imtdCfpDetailsSid);

	@Override
	public java.util.Map<java.io.Serializable, ImtdCfpDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the imtd cfp detailses.
	*
	* @return the imtd cfp detailses
	*/
	public java.util.List<ImtdCfpDetails> findAll();

	/**
	* Returns a range of all the imtd cfp detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdCfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd cfp detailses
	* @param end the upper bound of the range of imtd cfp detailses (not inclusive)
	* @return the range of imtd cfp detailses
	*/
	public java.util.List<ImtdCfpDetails> findAll(int start, int end);

	/**
	* Returns an ordered range of all the imtd cfp detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdCfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd cfp detailses
	* @param end the upper bound of the range of imtd cfp detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of imtd cfp detailses
	*/
	public java.util.List<ImtdCfpDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImtdCfpDetails> orderByComparator);

	/**
	* Returns an ordered range of all the imtd cfp detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdCfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd cfp detailses
	* @param end the upper bound of the range of imtd cfp detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of imtd cfp detailses
	*/
	public java.util.List<ImtdCfpDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImtdCfpDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the imtd cfp detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of imtd cfp detailses.
	*
	* @return the number of imtd cfp detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}