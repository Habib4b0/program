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

import com.stpl.app.exception.NoSuchImtdRsDetailsException;
import com.stpl.app.model.ImtdRsDetails;

import java.util.Date;

/**
 * The persistence interface for the imtd rs details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.ImtdRsDetailsPersistenceImpl
 * @see ImtdRsDetailsUtil
 * @generated
 */
@ProviderType
public interface ImtdRsDetailsPersistence extends BasePersistence<ImtdRsDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ImtdRsDetailsUtil} to access the imtd rs details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the imtd rs detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreatedDate the imtd created date
	* @return the matching imtd rs detailses
	*/
	public java.util.List<ImtdRsDetails> findByTempRsSearch(
		java.lang.String usersSid, java.lang.String sessionId,
		Date imtdCreatedDate);

	/**
	* Returns a range of all the imtd rs detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdRsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreatedDate the imtd created date
	* @param start the lower bound of the range of imtd rs detailses
	* @param end the upper bound of the range of imtd rs detailses (not inclusive)
	* @return the range of matching imtd rs detailses
	*/
	public java.util.List<ImtdRsDetails> findByTempRsSearch(
		java.lang.String usersSid, java.lang.String sessionId,
		Date imtdCreatedDate, int start, int end);

	/**
	* Returns an ordered range of all the imtd rs detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdRsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreatedDate the imtd created date
	* @param start the lower bound of the range of imtd rs detailses
	* @param end the upper bound of the range of imtd rs detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching imtd rs detailses
	*/
	public java.util.List<ImtdRsDetails> findByTempRsSearch(
		java.lang.String usersSid, java.lang.String sessionId,
		Date imtdCreatedDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImtdRsDetails> orderByComparator);

	/**
	* Returns an ordered range of all the imtd rs detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdRsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreatedDate the imtd created date
	* @param start the lower bound of the range of imtd rs detailses
	* @param end the upper bound of the range of imtd rs detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching imtd rs detailses
	*/
	public java.util.List<ImtdRsDetails> findByTempRsSearch(
		java.lang.String usersSid, java.lang.String sessionId,
		Date imtdCreatedDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImtdRsDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first imtd rs details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreatedDate the imtd created date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching imtd rs details
	* @throws NoSuchImtdRsDetailsException if a matching imtd rs details could not be found
	*/
	public ImtdRsDetails findByTempRsSearch_First(java.lang.String usersSid,
		java.lang.String sessionId, Date imtdCreatedDate,
		com.liferay.portal.kernel.util.OrderByComparator<ImtdRsDetails> orderByComparator)
		throws NoSuchImtdRsDetailsException;

	/**
	* Returns the first imtd rs details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreatedDate the imtd created date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching imtd rs details, or <code>null</code> if a matching imtd rs details could not be found
	*/
	public ImtdRsDetails fetchByTempRsSearch_First(java.lang.String usersSid,
		java.lang.String sessionId, Date imtdCreatedDate,
		com.liferay.portal.kernel.util.OrderByComparator<ImtdRsDetails> orderByComparator);

	/**
	* Returns the last imtd rs details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreatedDate the imtd created date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching imtd rs details
	* @throws NoSuchImtdRsDetailsException if a matching imtd rs details could not be found
	*/
	public ImtdRsDetails findByTempRsSearch_Last(java.lang.String usersSid,
		java.lang.String sessionId, Date imtdCreatedDate,
		com.liferay.portal.kernel.util.OrderByComparator<ImtdRsDetails> orderByComparator)
		throws NoSuchImtdRsDetailsException;

	/**
	* Returns the last imtd rs details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreatedDate the imtd created date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching imtd rs details, or <code>null</code> if a matching imtd rs details could not be found
	*/
	public ImtdRsDetails fetchByTempRsSearch_Last(java.lang.String usersSid,
		java.lang.String sessionId, Date imtdCreatedDate,
		com.liferay.portal.kernel.util.OrderByComparator<ImtdRsDetails> orderByComparator);

	/**
	* Returns the imtd rs detailses before and after the current imtd rs details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	*
	* @param imtdRsDetailsSid the primary key of the current imtd rs details
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreatedDate the imtd created date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next imtd rs details
	* @throws NoSuchImtdRsDetailsException if a imtd rs details with the primary key could not be found
	*/
	public ImtdRsDetails[] findByTempRsSearch_PrevAndNext(
		int imtdRsDetailsSid, java.lang.String usersSid,
		java.lang.String sessionId, Date imtdCreatedDate,
		com.liferay.portal.kernel.util.OrderByComparator<ImtdRsDetails> orderByComparator)
		throws NoSuchImtdRsDetailsException;

	/**
	* Removes all the imtd rs detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63; from the database.
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreatedDate the imtd created date
	*/
	public void removeByTempRsSearch(java.lang.String usersSid,
		java.lang.String sessionId, Date imtdCreatedDate);

	/**
	* Returns the number of imtd rs detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreatedDate the imtd created date
	* @return the number of matching imtd rs detailses
	*/
	public int countByTempRsSearch(java.lang.String usersSid,
		java.lang.String sessionId, Date imtdCreatedDate);

	/**
	* Caches the imtd rs details in the entity cache if it is enabled.
	*
	* @param imtdRsDetails the imtd rs details
	*/
	public void cacheResult(ImtdRsDetails imtdRsDetails);

	/**
	* Caches the imtd rs detailses in the entity cache if it is enabled.
	*
	* @param imtdRsDetailses the imtd rs detailses
	*/
	public void cacheResult(java.util.List<ImtdRsDetails> imtdRsDetailses);

	/**
	* Creates a new imtd rs details with the primary key. Does not add the imtd rs details to the database.
	*
	* @param imtdRsDetailsSid the primary key for the new imtd rs details
	* @return the new imtd rs details
	*/
	public ImtdRsDetails create(int imtdRsDetailsSid);

	/**
	* Removes the imtd rs details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param imtdRsDetailsSid the primary key of the imtd rs details
	* @return the imtd rs details that was removed
	* @throws NoSuchImtdRsDetailsException if a imtd rs details with the primary key could not be found
	*/
	public ImtdRsDetails remove(int imtdRsDetailsSid)
		throws NoSuchImtdRsDetailsException;

	public ImtdRsDetails updateImpl(ImtdRsDetails imtdRsDetails);

	/**
	* Returns the imtd rs details with the primary key or throws a {@link NoSuchImtdRsDetailsException} if it could not be found.
	*
	* @param imtdRsDetailsSid the primary key of the imtd rs details
	* @return the imtd rs details
	* @throws NoSuchImtdRsDetailsException if a imtd rs details with the primary key could not be found
	*/
	public ImtdRsDetails findByPrimaryKey(int imtdRsDetailsSid)
		throws NoSuchImtdRsDetailsException;

	/**
	* Returns the imtd rs details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param imtdRsDetailsSid the primary key of the imtd rs details
	* @return the imtd rs details, or <code>null</code> if a imtd rs details with the primary key could not be found
	*/
	public ImtdRsDetails fetchByPrimaryKey(int imtdRsDetailsSid);

	@Override
	public java.util.Map<java.io.Serializable, ImtdRsDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the imtd rs detailses.
	*
	* @return the imtd rs detailses
	*/
	public java.util.List<ImtdRsDetails> findAll();

	/**
	* Returns a range of all the imtd rs detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdRsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd rs detailses
	* @param end the upper bound of the range of imtd rs detailses (not inclusive)
	* @return the range of imtd rs detailses
	*/
	public java.util.List<ImtdRsDetails> findAll(int start, int end);

	/**
	* Returns an ordered range of all the imtd rs detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdRsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd rs detailses
	* @param end the upper bound of the range of imtd rs detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of imtd rs detailses
	*/
	public java.util.List<ImtdRsDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImtdRsDetails> orderByComparator);

	/**
	* Returns an ordered range of all the imtd rs detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdRsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd rs detailses
	* @param end the upper bound of the range of imtd rs detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of imtd rs detailses
	*/
	public java.util.List<ImtdRsDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImtdRsDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the imtd rs detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of imtd rs detailses.
	*
	* @return the number of imtd rs detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}