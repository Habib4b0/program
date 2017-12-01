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

import com.stpl.app.exception.NoSuchCpiIndexMasterException;
import com.stpl.app.model.CpiIndexMaster;

import java.util.Date;

/**
 * The persistence interface for the cpi index master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.CpiIndexMasterPersistenceImpl
 * @see CpiIndexMasterUtil
 * @generated
 */
@ProviderType
public interface CpiIndexMasterPersistence extends BasePersistence<CpiIndexMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CpiIndexMasterUtil} to access the cpi index master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the cpi index masters where status = &#63;.
	*
	* @param status the status
	* @return the matching cpi index masters
	*/
	public java.util.List<CpiIndexMaster> findByStatus(java.lang.String status);

	/**
	* Returns a range of all the cpi index masters where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @return the range of matching cpi index masters
	*/
	public java.util.List<CpiIndexMaster> findByStatus(
		java.lang.String status, int start, int end);

	/**
	* Returns an ordered range of all the cpi index masters where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cpi index masters
	*/
	public java.util.List<CpiIndexMaster> findByStatus(
		java.lang.String status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator);

	/**
	* Returns an ordered range of all the cpi index masters where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cpi index masters
	*/
	public java.util.List<CpiIndexMaster> findByStatus(
		java.lang.String status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first cpi index master in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cpi index master
	* @throws NoSuchCpiIndexMasterException if a matching cpi index master could not be found
	*/
	public CpiIndexMaster findByStatus_First(java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator)
		throws NoSuchCpiIndexMasterException;

	/**
	* Returns the first cpi index master in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
	*/
	public CpiIndexMaster fetchByStatus_First(java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator);

	/**
	* Returns the last cpi index master in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cpi index master
	* @throws NoSuchCpiIndexMasterException if a matching cpi index master could not be found
	*/
	public CpiIndexMaster findByStatus_Last(java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator)
		throws NoSuchCpiIndexMasterException;

	/**
	* Returns the last cpi index master in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
	*/
	public CpiIndexMaster fetchByStatus_Last(java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator);

	/**
	* Returns the cpi index masters before and after the current cpi index master in the ordered set where status = &#63;.
	*
	* @param cpiIndexMasterSid the primary key of the current cpi index master
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cpi index master
	* @throws NoSuchCpiIndexMasterException if a cpi index master with the primary key could not be found
	*/
	public CpiIndexMaster[] findByStatus_PrevAndNext(int cpiIndexMasterSid,
		java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator)
		throws NoSuchCpiIndexMasterException;

	/**
	* Removes all the cpi index masters where status = &#63; from the database.
	*
	* @param status the status
	*/
	public void removeByStatus(java.lang.String status);

	/**
	* Returns the number of cpi index masters where status = &#63;.
	*
	* @param status the status
	* @return the number of matching cpi index masters
	*/
	public int countByStatus(java.lang.String status);

	/**
	* Returns all the cpi index masters where indexId = &#63;.
	*
	* @param indexId the index ID
	* @return the matching cpi index masters
	*/
	public java.util.List<CpiIndexMaster> findByIndexId(
		java.lang.String indexId);

	/**
	* Returns a range of all the cpi index masters where indexId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param indexId the index ID
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @return the range of matching cpi index masters
	*/
	public java.util.List<CpiIndexMaster> findByIndexId(
		java.lang.String indexId, int start, int end);

	/**
	* Returns an ordered range of all the cpi index masters where indexId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param indexId the index ID
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cpi index masters
	*/
	public java.util.List<CpiIndexMaster> findByIndexId(
		java.lang.String indexId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator);

	/**
	* Returns an ordered range of all the cpi index masters where indexId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param indexId the index ID
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cpi index masters
	*/
	public java.util.List<CpiIndexMaster> findByIndexId(
		java.lang.String indexId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first cpi index master in the ordered set where indexId = &#63;.
	*
	* @param indexId the index ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cpi index master
	* @throws NoSuchCpiIndexMasterException if a matching cpi index master could not be found
	*/
	public CpiIndexMaster findByIndexId_First(java.lang.String indexId,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator)
		throws NoSuchCpiIndexMasterException;

	/**
	* Returns the first cpi index master in the ordered set where indexId = &#63;.
	*
	* @param indexId the index ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
	*/
	public CpiIndexMaster fetchByIndexId_First(java.lang.String indexId,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator);

	/**
	* Returns the last cpi index master in the ordered set where indexId = &#63;.
	*
	* @param indexId the index ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cpi index master
	* @throws NoSuchCpiIndexMasterException if a matching cpi index master could not be found
	*/
	public CpiIndexMaster findByIndexId_Last(java.lang.String indexId,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator)
		throws NoSuchCpiIndexMasterException;

	/**
	* Returns the last cpi index master in the ordered set where indexId = &#63;.
	*
	* @param indexId the index ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
	*/
	public CpiIndexMaster fetchByIndexId_Last(java.lang.String indexId,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator);

	/**
	* Returns the cpi index masters before and after the current cpi index master in the ordered set where indexId = &#63;.
	*
	* @param cpiIndexMasterSid the primary key of the current cpi index master
	* @param indexId the index ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cpi index master
	* @throws NoSuchCpiIndexMasterException if a cpi index master with the primary key could not be found
	*/
	public CpiIndexMaster[] findByIndexId_PrevAndNext(int cpiIndexMasterSid,
		java.lang.String indexId,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator)
		throws NoSuchCpiIndexMasterException;

	/**
	* Removes all the cpi index masters where indexId = &#63; from the database.
	*
	* @param indexId the index ID
	*/
	public void removeByIndexId(java.lang.String indexId);

	/**
	* Returns the number of cpi index masters where indexId = &#63;.
	*
	* @param indexId the index ID
	* @return the number of matching cpi index masters
	*/
	public int countByIndexId(java.lang.String indexId);

	/**
	* Returns all the cpi index masters where indexValue = &#63;.
	*
	* @param indexValue the index value
	* @return the matching cpi index masters
	*/
	public java.util.List<CpiIndexMaster> findByIndexValue(
		java.lang.String indexValue);

	/**
	* Returns a range of all the cpi index masters where indexValue = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param indexValue the index value
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @return the range of matching cpi index masters
	*/
	public java.util.List<CpiIndexMaster> findByIndexValue(
		java.lang.String indexValue, int start, int end);

	/**
	* Returns an ordered range of all the cpi index masters where indexValue = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param indexValue the index value
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cpi index masters
	*/
	public java.util.List<CpiIndexMaster> findByIndexValue(
		java.lang.String indexValue, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator);

	/**
	* Returns an ordered range of all the cpi index masters where indexValue = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param indexValue the index value
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cpi index masters
	*/
	public java.util.List<CpiIndexMaster> findByIndexValue(
		java.lang.String indexValue, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first cpi index master in the ordered set where indexValue = &#63;.
	*
	* @param indexValue the index value
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cpi index master
	* @throws NoSuchCpiIndexMasterException if a matching cpi index master could not be found
	*/
	public CpiIndexMaster findByIndexValue_First(java.lang.String indexValue,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator)
		throws NoSuchCpiIndexMasterException;

	/**
	* Returns the first cpi index master in the ordered set where indexValue = &#63;.
	*
	* @param indexValue the index value
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
	*/
	public CpiIndexMaster fetchByIndexValue_First(java.lang.String indexValue,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator);

	/**
	* Returns the last cpi index master in the ordered set where indexValue = &#63;.
	*
	* @param indexValue the index value
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cpi index master
	* @throws NoSuchCpiIndexMasterException if a matching cpi index master could not be found
	*/
	public CpiIndexMaster findByIndexValue_Last(java.lang.String indexValue,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator)
		throws NoSuchCpiIndexMasterException;

	/**
	* Returns the last cpi index master in the ordered set where indexValue = &#63;.
	*
	* @param indexValue the index value
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
	*/
	public CpiIndexMaster fetchByIndexValue_Last(java.lang.String indexValue,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator);

	/**
	* Returns the cpi index masters before and after the current cpi index master in the ordered set where indexValue = &#63;.
	*
	* @param cpiIndexMasterSid the primary key of the current cpi index master
	* @param indexValue the index value
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cpi index master
	* @throws NoSuchCpiIndexMasterException if a cpi index master with the primary key could not be found
	*/
	public CpiIndexMaster[] findByIndexValue_PrevAndNext(
		int cpiIndexMasterSid, java.lang.String indexValue,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator)
		throws NoSuchCpiIndexMasterException;

	/**
	* Removes all the cpi index masters where indexValue = &#63; from the database.
	*
	* @param indexValue the index value
	*/
	public void removeByIndexValue(java.lang.String indexValue);

	/**
	* Returns the number of cpi index masters where indexValue = &#63;.
	*
	* @param indexValue the index value
	* @return the number of matching cpi index masters
	*/
	public int countByIndexValue(java.lang.String indexValue);

	/**
	* Returns all the cpi index masters where indexType = &#63;.
	*
	* @param indexType the index type
	* @return the matching cpi index masters
	*/
	public java.util.List<CpiIndexMaster> findByIndexType(
		java.lang.String indexType);

	/**
	* Returns a range of all the cpi index masters where indexType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param indexType the index type
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @return the range of matching cpi index masters
	*/
	public java.util.List<CpiIndexMaster> findByIndexType(
		java.lang.String indexType, int start, int end);

	/**
	* Returns an ordered range of all the cpi index masters where indexType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param indexType the index type
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cpi index masters
	*/
	public java.util.List<CpiIndexMaster> findByIndexType(
		java.lang.String indexType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator);

	/**
	* Returns an ordered range of all the cpi index masters where indexType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param indexType the index type
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cpi index masters
	*/
	public java.util.List<CpiIndexMaster> findByIndexType(
		java.lang.String indexType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first cpi index master in the ordered set where indexType = &#63;.
	*
	* @param indexType the index type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cpi index master
	* @throws NoSuchCpiIndexMasterException if a matching cpi index master could not be found
	*/
	public CpiIndexMaster findByIndexType_First(java.lang.String indexType,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator)
		throws NoSuchCpiIndexMasterException;

	/**
	* Returns the first cpi index master in the ordered set where indexType = &#63;.
	*
	* @param indexType the index type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
	*/
	public CpiIndexMaster fetchByIndexType_First(java.lang.String indexType,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator);

	/**
	* Returns the last cpi index master in the ordered set where indexType = &#63;.
	*
	* @param indexType the index type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cpi index master
	* @throws NoSuchCpiIndexMasterException if a matching cpi index master could not be found
	*/
	public CpiIndexMaster findByIndexType_Last(java.lang.String indexType,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator)
		throws NoSuchCpiIndexMasterException;

	/**
	* Returns the last cpi index master in the ordered set where indexType = &#63;.
	*
	* @param indexType the index type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
	*/
	public CpiIndexMaster fetchByIndexType_Last(java.lang.String indexType,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator);

	/**
	* Returns the cpi index masters before and after the current cpi index master in the ordered set where indexType = &#63;.
	*
	* @param cpiIndexMasterSid the primary key of the current cpi index master
	* @param indexType the index type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cpi index master
	* @throws NoSuchCpiIndexMasterException if a cpi index master with the primary key could not be found
	*/
	public CpiIndexMaster[] findByIndexType_PrevAndNext(int cpiIndexMasterSid,
		java.lang.String indexType,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator)
		throws NoSuchCpiIndexMasterException;

	/**
	* Removes all the cpi index masters where indexType = &#63; from the database.
	*
	* @param indexType the index type
	*/
	public void removeByIndexType(java.lang.String indexType);

	/**
	* Returns the number of cpi index masters where indexType = &#63;.
	*
	* @param indexType the index type
	* @return the number of matching cpi index masters
	*/
	public int countByIndexType(java.lang.String indexType);

	/**
	* Returns all the cpi index masters where effectiveDate = &#63;.
	*
	* @param effectiveDate the effective date
	* @return the matching cpi index masters
	*/
	public java.util.List<CpiIndexMaster> findByEffectiveDate(
		Date effectiveDate);

	/**
	* Returns a range of all the cpi index masters where effectiveDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param effectiveDate the effective date
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @return the range of matching cpi index masters
	*/
	public java.util.List<CpiIndexMaster> findByEffectiveDate(
		Date effectiveDate, int start, int end);

	/**
	* Returns an ordered range of all the cpi index masters where effectiveDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param effectiveDate the effective date
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cpi index masters
	*/
	public java.util.List<CpiIndexMaster> findByEffectiveDate(
		Date effectiveDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator);

	/**
	* Returns an ordered range of all the cpi index masters where effectiveDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param effectiveDate the effective date
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cpi index masters
	*/
	public java.util.List<CpiIndexMaster> findByEffectiveDate(
		Date effectiveDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first cpi index master in the ordered set where effectiveDate = &#63;.
	*
	* @param effectiveDate the effective date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cpi index master
	* @throws NoSuchCpiIndexMasterException if a matching cpi index master could not be found
	*/
	public CpiIndexMaster findByEffectiveDate_First(Date effectiveDate,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator)
		throws NoSuchCpiIndexMasterException;

	/**
	* Returns the first cpi index master in the ordered set where effectiveDate = &#63;.
	*
	* @param effectiveDate the effective date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
	*/
	public CpiIndexMaster fetchByEffectiveDate_First(Date effectiveDate,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator);

	/**
	* Returns the last cpi index master in the ordered set where effectiveDate = &#63;.
	*
	* @param effectiveDate the effective date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cpi index master
	* @throws NoSuchCpiIndexMasterException if a matching cpi index master could not be found
	*/
	public CpiIndexMaster findByEffectiveDate_Last(Date effectiveDate,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator)
		throws NoSuchCpiIndexMasterException;

	/**
	* Returns the last cpi index master in the ordered set where effectiveDate = &#63;.
	*
	* @param effectiveDate the effective date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
	*/
	public CpiIndexMaster fetchByEffectiveDate_Last(Date effectiveDate,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator);

	/**
	* Returns the cpi index masters before and after the current cpi index master in the ordered set where effectiveDate = &#63;.
	*
	* @param cpiIndexMasterSid the primary key of the current cpi index master
	* @param effectiveDate the effective date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cpi index master
	* @throws NoSuchCpiIndexMasterException if a cpi index master with the primary key could not be found
	*/
	public CpiIndexMaster[] findByEffectiveDate_PrevAndNext(
		int cpiIndexMasterSid, Date effectiveDate,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator)
		throws NoSuchCpiIndexMasterException;

	/**
	* Removes all the cpi index masters where effectiveDate = &#63; from the database.
	*
	* @param effectiveDate the effective date
	*/
	public void removeByEffectiveDate(Date effectiveDate);

	/**
	* Returns the number of cpi index masters where effectiveDate = &#63;.
	*
	* @param effectiveDate the effective date
	* @return the number of matching cpi index masters
	*/
	public int countByEffectiveDate(Date effectiveDate);

	/**
	* Returns all the cpi index masters where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
	*
	* @param indexId the index ID
	* @param status the status
	* @param indexType the index type
	* @param effectiveDate the effective date
	* @return the matching cpi index masters
	*/
	public java.util.List<CpiIndexMaster> findByCpiIndexUnique(
		java.lang.String indexId, java.lang.String status,
		java.lang.String indexType, Date effectiveDate);

	/**
	* Returns a range of all the cpi index masters where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param indexId the index ID
	* @param status the status
	* @param indexType the index type
	* @param effectiveDate the effective date
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @return the range of matching cpi index masters
	*/
	public java.util.List<CpiIndexMaster> findByCpiIndexUnique(
		java.lang.String indexId, java.lang.String status,
		java.lang.String indexType, Date effectiveDate, int start, int end);

	/**
	* Returns an ordered range of all the cpi index masters where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param indexId the index ID
	* @param status the status
	* @param indexType the index type
	* @param effectiveDate the effective date
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cpi index masters
	*/
	public java.util.List<CpiIndexMaster> findByCpiIndexUnique(
		java.lang.String indexId, java.lang.String status,
		java.lang.String indexType, Date effectiveDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator);

	/**
	* Returns an ordered range of all the cpi index masters where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param indexId the index ID
	* @param status the status
	* @param indexType the index type
	* @param effectiveDate the effective date
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cpi index masters
	*/
	public java.util.List<CpiIndexMaster> findByCpiIndexUnique(
		java.lang.String indexId, java.lang.String status,
		java.lang.String indexType, Date effectiveDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first cpi index master in the ordered set where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
	*
	* @param indexId the index ID
	* @param status the status
	* @param indexType the index type
	* @param effectiveDate the effective date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cpi index master
	* @throws NoSuchCpiIndexMasterException if a matching cpi index master could not be found
	*/
	public CpiIndexMaster findByCpiIndexUnique_First(java.lang.String indexId,
		java.lang.String status, java.lang.String indexType,
		Date effectiveDate,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator)
		throws NoSuchCpiIndexMasterException;

	/**
	* Returns the first cpi index master in the ordered set where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
	*
	* @param indexId the index ID
	* @param status the status
	* @param indexType the index type
	* @param effectiveDate the effective date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
	*/
	public CpiIndexMaster fetchByCpiIndexUnique_First(
		java.lang.String indexId, java.lang.String status,
		java.lang.String indexType, Date effectiveDate,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator);

	/**
	* Returns the last cpi index master in the ordered set where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
	*
	* @param indexId the index ID
	* @param status the status
	* @param indexType the index type
	* @param effectiveDate the effective date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cpi index master
	* @throws NoSuchCpiIndexMasterException if a matching cpi index master could not be found
	*/
	public CpiIndexMaster findByCpiIndexUnique_Last(java.lang.String indexId,
		java.lang.String status, java.lang.String indexType,
		Date effectiveDate,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator)
		throws NoSuchCpiIndexMasterException;

	/**
	* Returns the last cpi index master in the ordered set where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
	*
	* @param indexId the index ID
	* @param status the status
	* @param indexType the index type
	* @param effectiveDate the effective date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
	*/
	public CpiIndexMaster fetchByCpiIndexUnique_Last(java.lang.String indexId,
		java.lang.String status, java.lang.String indexType,
		Date effectiveDate,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator);

	/**
	* Returns the cpi index masters before and after the current cpi index master in the ordered set where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
	*
	* @param cpiIndexMasterSid the primary key of the current cpi index master
	* @param indexId the index ID
	* @param status the status
	* @param indexType the index type
	* @param effectiveDate the effective date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cpi index master
	* @throws NoSuchCpiIndexMasterException if a cpi index master with the primary key could not be found
	*/
	public CpiIndexMaster[] findByCpiIndexUnique_PrevAndNext(
		int cpiIndexMasterSid, java.lang.String indexId,
		java.lang.String status, java.lang.String indexType,
		Date effectiveDate,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator)
		throws NoSuchCpiIndexMasterException;

	/**
	* Removes all the cpi index masters where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63; from the database.
	*
	* @param indexId the index ID
	* @param status the status
	* @param indexType the index type
	* @param effectiveDate the effective date
	*/
	public void removeByCpiIndexUnique(java.lang.String indexId,
		java.lang.String status, java.lang.String indexType, Date effectiveDate);

	/**
	* Returns the number of cpi index masters where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
	*
	* @param indexId the index ID
	* @param status the status
	* @param indexType the index type
	* @param effectiveDate the effective date
	* @return the number of matching cpi index masters
	*/
	public int countByCpiIndexUnique(java.lang.String indexId,
		java.lang.String status, java.lang.String indexType, Date effectiveDate);

	/**
	* Caches the cpi index master in the entity cache if it is enabled.
	*
	* @param cpiIndexMaster the cpi index master
	*/
	public void cacheResult(CpiIndexMaster cpiIndexMaster);

	/**
	* Caches the cpi index masters in the entity cache if it is enabled.
	*
	* @param cpiIndexMasters the cpi index masters
	*/
	public void cacheResult(java.util.List<CpiIndexMaster> cpiIndexMasters);

	/**
	* Creates a new cpi index master with the primary key. Does not add the cpi index master to the database.
	*
	* @param cpiIndexMasterSid the primary key for the new cpi index master
	* @return the new cpi index master
	*/
	public CpiIndexMaster create(int cpiIndexMasterSid);

	/**
	* Removes the cpi index master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cpiIndexMasterSid the primary key of the cpi index master
	* @return the cpi index master that was removed
	* @throws NoSuchCpiIndexMasterException if a cpi index master with the primary key could not be found
	*/
	public CpiIndexMaster remove(int cpiIndexMasterSid)
		throws NoSuchCpiIndexMasterException;

	public CpiIndexMaster updateImpl(CpiIndexMaster cpiIndexMaster);

	/**
	* Returns the cpi index master with the primary key or throws a {@link NoSuchCpiIndexMasterException} if it could not be found.
	*
	* @param cpiIndexMasterSid the primary key of the cpi index master
	* @return the cpi index master
	* @throws NoSuchCpiIndexMasterException if a cpi index master with the primary key could not be found
	*/
	public CpiIndexMaster findByPrimaryKey(int cpiIndexMasterSid)
		throws NoSuchCpiIndexMasterException;

	/**
	* Returns the cpi index master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param cpiIndexMasterSid the primary key of the cpi index master
	* @return the cpi index master, or <code>null</code> if a cpi index master with the primary key could not be found
	*/
	public CpiIndexMaster fetchByPrimaryKey(int cpiIndexMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, CpiIndexMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the cpi index masters.
	*
	* @return the cpi index masters
	*/
	public java.util.List<CpiIndexMaster> findAll();

	/**
	* Returns a range of all the cpi index masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @return the range of cpi index masters
	*/
	public java.util.List<CpiIndexMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the cpi index masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of cpi index masters
	*/
	public java.util.List<CpiIndexMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator);

	/**
	* Returns an ordered range of all the cpi index masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of cpi index masters
	*/
	public java.util.List<CpiIndexMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CpiIndexMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the cpi index masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of cpi index masters.
	*
	* @return the number of cpi index masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}