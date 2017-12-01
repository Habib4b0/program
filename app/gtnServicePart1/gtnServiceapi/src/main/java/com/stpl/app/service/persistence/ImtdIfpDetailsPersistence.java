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

import com.stpl.app.exception.NoSuchImtdIfpDetailsException;
import com.stpl.app.model.ImtdIfpDetails;

import java.util.Date;

/**
 * The persistence interface for the imtd ifp details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.ImtdIfpDetailsPersistenceImpl
 * @see ImtdIfpDetailsUtil
 * @generated
 */
@ProviderType
public interface ImtdIfpDetailsPersistence extends BasePersistence<ImtdIfpDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ImtdIfpDetailsUtil} to access the imtd ifp details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the imtd ifp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63;.
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreateddate the imtd createddate
	* @return the matching imtd ifp detailses
	*/
	public java.util.List<ImtdIfpDetails> findByTempIfpSearch(int usersSid,
		java.lang.String sessionId, Date imtdCreateddate);

	/**
	* Returns a range of all the imtd ifp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdIfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreateddate the imtd createddate
	* @param start the lower bound of the range of imtd ifp detailses
	* @param end the upper bound of the range of imtd ifp detailses (not inclusive)
	* @return the range of matching imtd ifp detailses
	*/
	public java.util.List<ImtdIfpDetails> findByTempIfpSearch(int usersSid,
		java.lang.String sessionId, Date imtdCreateddate, int start, int end);

	/**
	* Returns an ordered range of all the imtd ifp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdIfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreateddate the imtd createddate
	* @param start the lower bound of the range of imtd ifp detailses
	* @param end the upper bound of the range of imtd ifp detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching imtd ifp detailses
	*/
	public java.util.List<ImtdIfpDetails> findByTempIfpSearch(int usersSid,
		java.lang.String sessionId, Date imtdCreateddate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImtdIfpDetails> orderByComparator);

	/**
	* Returns an ordered range of all the imtd ifp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdIfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreateddate the imtd createddate
	* @param start the lower bound of the range of imtd ifp detailses
	* @param end the upper bound of the range of imtd ifp detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching imtd ifp detailses
	*/
	public java.util.List<ImtdIfpDetails> findByTempIfpSearch(int usersSid,
		java.lang.String sessionId, Date imtdCreateddate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImtdIfpDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first imtd ifp details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63;.
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreateddate the imtd createddate
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching imtd ifp details
	* @throws NoSuchImtdIfpDetailsException if a matching imtd ifp details could not be found
	*/
	public ImtdIfpDetails findByTempIfpSearch_First(int usersSid,
		java.lang.String sessionId, Date imtdCreateddate,
		com.liferay.portal.kernel.util.OrderByComparator<ImtdIfpDetails> orderByComparator)
		throws NoSuchImtdIfpDetailsException;

	/**
	* Returns the first imtd ifp details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63;.
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreateddate the imtd createddate
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching imtd ifp details, or <code>null</code> if a matching imtd ifp details could not be found
	*/
	public ImtdIfpDetails fetchByTempIfpSearch_First(int usersSid,
		java.lang.String sessionId, Date imtdCreateddate,
		com.liferay.portal.kernel.util.OrderByComparator<ImtdIfpDetails> orderByComparator);

	/**
	* Returns the last imtd ifp details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63;.
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreateddate the imtd createddate
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching imtd ifp details
	* @throws NoSuchImtdIfpDetailsException if a matching imtd ifp details could not be found
	*/
	public ImtdIfpDetails findByTempIfpSearch_Last(int usersSid,
		java.lang.String sessionId, Date imtdCreateddate,
		com.liferay.portal.kernel.util.OrderByComparator<ImtdIfpDetails> orderByComparator)
		throws NoSuchImtdIfpDetailsException;

	/**
	* Returns the last imtd ifp details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63;.
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreateddate the imtd createddate
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching imtd ifp details, or <code>null</code> if a matching imtd ifp details could not be found
	*/
	public ImtdIfpDetails fetchByTempIfpSearch_Last(int usersSid,
		java.lang.String sessionId, Date imtdCreateddate,
		com.liferay.portal.kernel.util.OrderByComparator<ImtdIfpDetails> orderByComparator);

	/**
	* Returns the imtd ifp detailses before and after the current imtd ifp details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63;.
	*
	* @param imtdIfpDetailsSid the primary key of the current imtd ifp details
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreateddate the imtd createddate
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next imtd ifp details
	* @throws NoSuchImtdIfpDetailsException if a imtd ifp details with the primary key could not be found
	*/
	public ImtdIfpDetails[] findByTempIfpSearch_PrevAndNext(
		int imtdIfpDetailsSid, int usersSid, java.lang.String sessionId,
		Date imtdCreateddate,
		com.liferay.portal.kernel.util.OrderByComparator<ImtdIfpDetails> orderByComparator)
		throws NoSuchImtdIfpDetailsException;

	/**
	* Removes all the imtd ifp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63; from the database.
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreateddate the imtd createddate
	*/
	public void removeByTempIfpSearch(int usersSid, java.lang.String sessionId,
		Date imtdCreateddate);

	/**
	* Returns the number of imtd ifp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63;.
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreateddate the imtd createddate
	* @return the number of matching imtd ifp detailses
	*/
	public int countByTempIfpSearch(int usersSid, java.lang.String sessionId,
		Date imtdCreateddate);

	/**
	* Caches the imtd ifp details in the entity cache if it is enabled.
	*
	* @param imtdIfpDetails the imtd ifp details
	*/
	public void cacheResult(ImtdIfpDetails imtdIfpDetails);

	/**
	* Caches the imtd ifp detailses in the entity cache if it is enabled.
	*
	* @param imtdIfpDetailses the imtd ifp detailses
	*/
	public void cacheResult(java.util.List<ImtdIfpDetails> imtdIfpDetailses);

	/**
	* Creates a new imtd ifp details with the primary key. Does not add the imtd ifp details to the database.
	*
	* @param imtdIfpDetailsSid the primary key for the new imtd ifp details
	* @return the new imtd ifp details
	*/
	public ImtdIfpDetails create(int imtdIfpDetailsSid);

	/**
	* Removes the imtd ifp details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param imtdIfpDetailsSid the primary key of the imtd ifp details
	* @return the imtd ifp details that was removed
	* @throws NoSuchImtdIfpDetailsException if a imtd ifp details with the primary key could not be found
	*/
	public ImtdIfpDetails remove(int imtdIfpDetailsSid)
		throws NoSuchImtdIfpDetailsException;

	public ImtdIfpDetails updateImpl(ImtdIfpDetails imtdIfpDetails);

	/**
	* Returns the imtd ifp details with the primary key or throws a {@link NoSuchImtdIfpDetailsException} if it could not be found.
	*
	* @param imtdIfpDetailsSid the primary key of the imtd ifp details
	* @return the imtd ifp details
	* @throws NoSuchImtdIfpDetailsException if a imtd ifp details with the primary key could not be found
	*/
	public ImtdIfpDetails findByPrimaryKey(int imtdIfpDetailsSid)
		throws NoSuchImtdIfpDetailsException;

	/**
	* Returns the imtd ifp details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param imtdIfpDetailsSid the primary key of the imtd ifp details
	* @return the imtd ifp details, or <code>null</code> if a imtd ifp details with the primary key could not be found
	*/
	public ImtdIfpDetails fetchByPrimaryKey(int imtdIfpDetailsSid);

	@Override
	public java.util.Map<java.io.Serializable, ImtdIfpDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the imtd ifp detailses.
	*
	* @return the imtd ifp detailses
	*/
	public java.util.List<ImtdIfpDetails> findAll();

	/**
	* Returns a range of all the imtd ifp detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdIfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd ifp detailses
	* @param end the upper bound of the range of imtd ifp detailses (not inclusive)
	* @return the range of imtd ifp detailses
	*/
	public java.util.List<ImtdIfpDetails> findAll(int start, int end);

	/**
	* Returns an ordered range of all the imtd ifp detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdIfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd ifp detailses
	* @param end the upper bound of the range of imtd ifp detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of imtd ifp detailses
	*/
	public java.util.List<ImtdIfpDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImtdIfpDetails> orderByComparator);

	/**
	* Returns an ordered range of all the imtd ifp detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdIfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd ifp detailses
	* @param end the upper bound of the range of imtd ifp detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of imtd ifp detailses
	*/
	public java.util.List<ImtdIfpDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImtdIfpDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the imtd ifp detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of imtd ifp detailses.
	*
	* @return the number of imtd ifp detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}