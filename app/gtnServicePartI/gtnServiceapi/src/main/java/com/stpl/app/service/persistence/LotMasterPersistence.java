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

import com.stpl.app.exception.NoSuchLotMasterException;
import com.stpl.app.model.LotMaster;

import java.util.Date;

/**
 * The persistence interface for the lot master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.LotMasterPersistenceImpl
 * @see LotMasterUtil
 * @generated
 */
@ProviderType
public interface LotMasterPersistence extends BasePersistence<LotMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LotMasterUtil} to access the lot master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the lot masters where itemId = &#63;.
	*
	* @param itemId the item ID
	* @return the matching lot masters
	*/
	public java.util.List<LotMaster> findByItemId(java.lang.String itemId);

	/**
	* Returns a range of all the lot masters where itemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemId the item ID
	* @param start the lower bound of the range of lot masters
	* @param end the upper bound of the range of lot masters (not inclusive)
	* @return the range of matching lot masters
	*/
	public java.util.List<LotMaster> findByItemId(java.lang.String itemId,
		int start, int end);

	/**
	* Returns an ordered range of all the lot masters where itemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemId the item ID
	* @param start the lower bound of the range of lot masters
	* @param end the upper bound of the range of lot masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching lot masters
	*/
	public java.util.List<LotMaster> findByItemId(java.lang.String itemId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LotMaster> orderByComparator);

	/**
	* Returns an ordered range of all the lot masters where itemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemId the item ID
	* @param start the lower bound of the range of lot masters
	* @param end the upper bound of the range of lot masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching lot masters
	*/
	public java.util.List<LotMaster> findByItemId(java.lang.String itemId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LotMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first lot master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching lot master
	* @throws NoSuchLotMasterException if a matching lot master could not be found
	*/
	public LotMaster findByItemId_First(java.lang.String itemId,
		com.liferay.portal.kernel.util.OrderByComparator<LotMaster> orderByComparator)
		throws NoSuchLotMasterException;

	/**
	* Returns the first lot master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching lot master, or <code>null</code> if a matching lot master could not be found
	*/
	public LotMaster fetchByItemId_First(java.lang.String itemId,
		com.liferay.portal.kernel.util.OrderByComparator<LotMaster> orderByComparator);

	/**
	* Returns the last lot master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching lot master
	* @throws NoSuchLotMasterException if a matching lot master could not be found
	*/
	public LotMaster findByItemId_Last(java.lang.String itemId,
		com.liferay.portal.kernel.util.OrderByComparator<LotMaster> orderByComparator)
		throws NoSuchLotMasterException;

	/**
	* Returns the last lot master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching lot master, or <code>null</code> if a matching lot master could not be found
	*/
	public LotMaster fetchByItemId_Last(java.lang.String itemId,
		com.liferay.portal.kernel.util.OrderByComparator<LotMaster> orderByComparator);

	/**
	* Returns the lot masters before and after the current lot master in the ordered set where itemId = &#63;.
	*
	* @param lotMasterSid the primary key of the current lot master
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next lot master
	* @throws NoSuchLotMasterException if a lot master with the primary key could not be found
	*/
	public LotMaster[] findByItemId_PrevAndNext(int lotMasterSid,
		java.lang.String itemId,
		com.liferay.portal.kernel.util.OrderByComparator<LotMaster> orderByComparator)
		throws NoSuchLotMasterException;

	/**
	* Removes all the lot masters where itemId = &#63; from the database.
	*
	* @param itemId the item ID
	*/
	public void removeByItemId(java.lang.String itemId);

	/**
	* Returns the number of lot masters where itemId = &#63;.
	*
	* @param itemId the item ID
	* @return the number of matching lot masters
	*/
	public int countByItemId(java.lang.String itemId);

	/**
	* Returns all the lot masters where lotNo = &#63;.
	*
	* @param lotNo the lot no
	* @return the matching lot masters
	*/
	public java.util.List<LotMaster> findByLotNo(java.lang.String lotNo);

	/**
	* Returns a range of all the lot masters where lotNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lotNo the lot no
	* @param start the lower bound of the range of lot masters
	* @param end the upper bound of the range of lot masters (not inclusive)
	* @return the range of matching lot masters
	*/
	public java.util.List<LotMaster> findByLotNo(java.lang.String lotNo,
		int start, int end);

	/**
	* Returns an ordered range of all the lot masters where lotNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lotNo the lot no
	* @param start the lower bound of the range of lot masters
	* @param end the upper bound of the range of lot masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching lot masters
	*/
	public java.util.List<LotMaster> findByLotNo(java.lang.String lotNo,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LotMaster> orderByComparator);

	/**
	* Returns an ordered range of all the lot masters where lotNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lotNo the lot no
	* @param start the lower bound of the range of lot masters
	* @param end the upper bound of the range of lot masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching lot masters
	*/
	public java.util.List<LotMaster> findByLotNo(java.lang.String lotNo,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LotMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first lot master in the ordered set where lotNo = &#63;.
	*
	* @param lotNo the lot no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching lot master
	* @throws NoSuchLotMasterException if a matching lot master could not be found
	*/
	public LotMaster findByLotNo_First(java.lang.String lotNo,
		com.liferay.portal.kernel.util.OrderByComparator<LotMaster> orderByComparator)
		throws NoSuchLotMasterException;

	/**
	* Returns the first lot master in the ordered set where lotNo = &#63;.
	*
	* @param lotNo the lot no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching lot master, or <code>null</code> if a matching lot master could not be found
	*/
	public LotMaster fetchByLotNo_First(java.lang.String lotNo,
		com.liferay.portal.kernel.util.OrderByComparator<LotMaster> orderByComparator);

	/**
	* Returns the last lot master in the ordered set where lotNo = &#63;.
	*
	* @param lotNo the lot no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching lot master
	* @throws NoSuchLotMasterException if a matching lot master could not be found
	*/
	public LotMaster findByLotNo_Last(java.lang.String lotNo,
		com.liferay.portal.kernel.util.OrderByComparator<LotMaster> orderByComparator)
		throws NoSuchLotMasterException;

	/**
	* Returns the last lot master in the ordered set where lotNo = &#63;.
	*
	* @param lotNo the lot no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching lot master, or <code>null</code> if a matching lot master could not be found
	*/
	public LotMaster fetchByLotNo_Last(java.lang.String lotNo,
		com.liferay.portal.kernel.util.OrderByComparator<LotMaster> orderByComparator);

	/**
	* Returns the lot masters before and after the current lot master in the ordered set where lotNo = &#63;.
	*
	* @param lotMasterSid the primary key of the current lot master
	* @param lotNo the lot no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next lot master
	* @throws NoSuchLotMasterException if a lot master with the primary key could not be found
	*/
	public LotMaster[] findByLotNo_PrevAndNext(int lotMasterSid,
		java.lang.String lotNo,
		com.liferay.portal.kernel.util.OrderByComparator<LotMaster> orderByComparator)
		throws NoSuchLotMasterException;

	/**
	* Removes all the lot masters where lotNo = &#63; from the database.
	*
	* @param lotNo the lot no
	*/
	public void removeByLotNo(java.lang.String lotNo);

	/**
	* Returns the number of lot masters where lotNo = &#63;.
	*
	* @param lotNo the lot no
	* @return the number of matching lot masters
	*/
	public int countByLotNo(java.lang.String lotNo);

	/**
	* Returns all the lot masters where lotExpirationDate = &#63;.
	*
	* @param lotExpirationDate the lot expiration date
	* @return the matching lot masters
	*/
	public java.util.List<LotMaster> findByLotExpirationDate(
		Date lotExpirationDate);

	/**
	* Returns a range of all the lot masters where lotExpirationDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lotExpirationDate the lot expiration date
	* @param start the lower bound of the range of lot masters
	* @param end the upper bound of the range of lot masters (not inclusive)
	* @return the range of matching lot masters
	*/
	public java.util.List<LotMaster> findByLotExpirationDate(
		Date lotExpirationDate, int start, int end);

	/**
	* Returns an ordered range of all the lot masters where lotExpirationDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lotExpirationDate the lot expiration date
	* @param start the lower bound of the range of lot masters
	* @param end the upper bound of the range of lot masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching lot masters
	*/
	public java.util.List<LotMaster> findByLotExpirationDate(
		Date lotExpirationDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LotMaster> orderByComparator);

	/**
	* Returns an ordered range of all the lot masters where lotExpirationDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lotExpirationDate the lot expiration date
	* @param start the lower bound of the range of lot masters
	* @param end the upper bound of the range of lot masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching lot masters
	*/
	public java.util.List<LotMaster> findByLotExpirationDate(
		Date lotExpirationDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LotMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first lot master in the ordered set where lotExpirationDate = &#63;.
	*
	* @param lotExpirationDate the lot expiration date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching lot master
	* @throws NoSuchLotMasterException if a matching lot master could not be found
	*/
	public LotMaster findByLotExpirationDate_First(Date lotExpirationDate,
		com.liferay.portal.kernel.util.OrderByComparator<LotMaster> orderByComparator)
		throws NoSuchLotMasterException;

	/**
	* Returns the first lot master in the ordered set where lotExpirationDate = &#63;.
	*
	* @param lotExpirationDate the lot expiration date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching lot master, or <code>null</code> if a matching lot master could not be found
	*/
	public LotMaster fetchByLotExpirationDate_First(Date lotExpirationDate,
		com.liferay.portal.kernel.util.OrderByComparator<LotMaster> orderByComparator);

	/**
	* Returns the last lot master in the ordered set where lotExpirationDate = &#63;.
	*
	* @param lotExpirationDate the lot expiration date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching lot master
	* @throws NoSuchLotMasterException if a matching lot master could not be found
	*/
	public LotMaster findByLotExpirationDate_Last(Date lotExpirationDate,
		com.liferay.portal.kernel.util.OrderByComparator<LotMaster> orderByComparator)
		throws NoSuchLotMasterException;

	/**
	* Returns the last lot master in the ordered set where lotExpirationDate = &#63;.
	*
	* @param lotExpirationDate the lot expiration date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching lot master, or <code>null</code> if a matching lot master could not be found
	*/
	public LotMaster fetchByLotExpirationDate_Last(Date lotExpirationDate,
		com.liferay.portal.kernel.util.OrderByComparator<LotMaster> orderByComparator);

	/**
	* Returns the lot masters before and after the current lot master in the ordered set where lotExpirationDate = &#63;.
	*
	* @param lotMasterSid the primary key of the current lot master
	* @param lotExpirationDate the lot expiration date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next lot master
	* @throws NoSuchLotMasterException if a lot master with the primary key could not be found
	*/
	public LotMaster[] findByLotExpirationDate_PrevAndNext(int lotMasterSid,
		Date lotExpirationDate,
		com.liferay.portal.kernel.util.OrderByComparator<LotMaster> orderByComparator)
		throws NoSuchLotMasterException;

	/**
	* Removes all the lot masters where lotExpirationDate = &#63; from the database.
	*
	* @param lotExpirationDate the lot expiration date
	*/
	public void removeByLotExpirationDate(Date lotExpirationDate);

	/**
	* Returns the number of lot masters where lotExpirationDate = &#63;.
	*
	* @param lotExpirationDate the lot expiration date
	* @return the number of matching lot masters
	*/
	public int countByLotExpirationDate(Date lotExpirationDate);

	/**
	* Returns all the lot masters where lastLotSoldDate = &#63;.
	*
	* @param lastLotSoldDate the last lot sold date
	* @return the matching lot masters
	*/
	public java.util.List<LotMaster> findByLastLotSoldDate(Date lastLotSoldDate);

	/**
	* Returns a range of all the lot masters where lastLotSoldDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lastLotSoldDate the last lot sold date
	* @param start the lower bound of the range of lot masters
	* @param end the upper bound of the range of lot masters (not inclusive)
	* @return the range of matching lot masters
	*/
	public java.util.List<LotMaster> findByLastLotSoldDate(
		Date lastLotSoldDate, int start, int end);

	/**
	* Returns an ordered range of all the lot masters where lastLotSoldDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lastLotSoldDate the last lot sold date
	* @param start the lower bound of the range of lot masters
	* @param end the upper bound of the range of lot masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching lot masters
	*/
	public java.util.List<LotMaster> findByLastLotSoldDate(
		Date lastLotSoldDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LotMaster> orderByComparator);

	/**
	* Returns an ordered range of all the lot masters where lastLotSoldDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lastLotSoldDate the last lot sold date
	* @param start the lower bound of the range of lot masters
	* @param end the upper bound of the range of lot masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching lot masters
	*/
	public java.util.List<LotMaster> findByLastLotSoldDate(
		Date lastLotSoldDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LotMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first lot master in the ordered set where lastLotSoldDate = &#63;.
	*
	* @param lastLotSoldDate the last lot sold date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching lot master
	* @throws NoSuchLotMasterException if a matching lot master could not be found
	*/
	public LotMaster findByLastLotSoldDate_First(Date lastLotSoldDate,
		com.liferay.portal.kernel.util.OrderByComparator<LotMaster> orderByComparator)
		throws NoSuchLotMasterException;

	/**
	* Returns the first lot master in the ordered set where lastLotSoldDate = &#63;.
	*
	* @param lastLotSoldDate the last lot sold date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching lot master, or <code>null</code> if a matching lot master could not be found
	*/
	public LotMaster fetchByLastLotSoldDate_First(Date lastLotSoldDate,
		com.liferay.portal.kernel.util.OrderByComparator<LotMaster> orderByComparator);

	/**
	* Returns the last lot master in the ordered set where lastLotSoldDate = &#63;.
	*
	* @param lastLotSoldDate the last lot sold date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching lot master
	* @throws NoSuchLotMasterException if a matching lot master could not be found
	*/
	public LotMaster findByLastLotSoldDate_Last(Date lastLotSoldDate,
		com.liferay.portal.kernel.util.OrderByComparator<LotMaster> orderByComparator)
		throws NoSuchLotMasterException;

	/**
	* Returns the last lot master in the ordered set where lastLotSoldDate = &#63;.
	*
	* @param lastLotSoldDate the last lot sold date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching lot master, or <code>null</code> if a matching lot master could not be found
	*/
	public LotMaster fetchByLastLotSoldDate_Last(Date lastLotSoldDate,
		com.liferay.portal.kernel.util.OrderByComparator<LotMaster> orderByComparator);

	/**
	* Returns the lot masters before and after the current lot master in the ordered set where lastLotSoldDate = &#63;.
	*
	* @param lotMasterSid the primary key of the current lot master
	* @param lastLotSoldDate the last lot sold date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next lot master
	* @throws NoSuchLotMasterException if a lot master with the primary key could not be found
	*/
	public LotMaster[] findByLastLotSoldDate_PrevAndNext(int lotMasterSid,
		Date lastLotSoldDate,
		com.liferay.portal.kernel.util.OrderByComparator<LotMaster> orderByComparator)
		throws NoSuchLotMasterException;

	/**
	* Removes all the lot masters where lastLotSoldDate = &#63; from the database.
	*
	* @param lastLotSoldDate the last lot sold date
	*/
	public void removeByLastLotSoldDate(Date lastLotSoldDate);

	/**
	* Returns the number of lot masters where lastLotSoldDate = &#63;.
	*
	* @param lastLotSoldDate the last lot sold date
	* @return the number of matching lot masters
	*/
	public int countByLastLotSoldDate(Date lastLotSoldDate);

	/**
	* Returns all the lot masters where itemId = &#63; and lotNo = &#63;.
	*
	* @param itemId the item ID
	* @param lotNo the lot no
	* @return the matching lot masters
	*/
	public java.util.List<LotMaster> findByLotUnique(java.lang.String itemId,
		java.lang.String lotNo);

	/**
	* Returns a range of all the lot masters where itemId = &#63; and lotNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemId the item ID
	* @param lotNo the lot no
	* @param start the lower bound of the range of lot masters
	* @param end the upper bound of the range of lot masters (not inclusive)
	* @return the range of matching lot masters
	*/
	public java.util.List<LotMaster> findByLotUnique(java.lang.String itemId,
		java.lang.String lotNo, int start, int end);

	/**
	* Returns an ordered range of all the lot masters where itemId = &#63; and lotNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemId the item ID
	* @param lotNo the lot no
	* @param start the lower bound of the range of lot masters
	* @param end the upper bound of the range of lot masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching lot masters
	*/
	public java.util.List<LotMaster> findByLotUnique(java.lang.String itemId,
		java.lang.String lotNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LotMaster> orderByComparator);

	/**
	* Returns an ordered range of all the lot masters where itemId = &#63; and lotNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemId the item ID
	* @param lotNo the lot no
	* @param start the lower bound of the range of lot masters
	* @param end the upper bound of the range of lot masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching lot masters
	*/
	public java.util.List<LotMaster> findByLotUnique(java.lang.String itemId,
		java.lang.String lotNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LotMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first lot master in the ordered set where itemId = &#63; and lotNo = &#63;.
	*
	* @param itemId the item ID
	* @param lotNo the lot no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching lot master
	* @throws NoSuchLotMasterException if a matching lot master could not be found
	*/
	public LotMaster findByLotUnique_First(java.lang.String itemId,
		java.lang.String lotNo,
		com.liferay.portal.kernel.util.OrderByComparator<LotMaster> orderByComparator)
		throws NoSuchLotMasterException;

	/**
	* Returns the first lot master in the ordered set where itemId = &#63; and lotNo = &#63;.
	*
	* @param itemId the item ID
	* @param lotNo the lot no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching lot master, or <code>null</code> if a matching lot master could not be found
	*/
	public LotMaster fetchByLotUnique_First(java.lang.String itemId,
		java.lang.String lotNo,
		com.liferay.portal.kernel.util.OrderByComparator<LotMaster> orderByComparator);

	/**
	* Returns the last lot master in the ordered set where itemId = &#63; and lotNo = &#63;.
	*
	* @param itemId the item ID
	* @param lotNo the lot no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching lot master
	* @throws NoSuchLotMasterException if a matching lot master could not be found
	*/
	public LotMaster findByLotUnique_Last(java.lang.String itemId,
		java.lang.String lotNo,
		com.liferay.portal.kernel.util.OrderByComparator<LotMaster> orderByComparator)
		throws NoSuchLotMasterException;

	/**
	* Returns the last lot master in the ordered set where itemId = &#63; and lotNo = &#63;.
	*
	* @param itemId the item ID
	* @param lotNo the lot no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching lot master, or <code>null</code> if a matching lot master could not be found
	*/
	public LotMaster fetchByLotUnique_Last(java.lang.String itemId,
		java.lang.String lotNo,
		com.liferay.portal.kernel.util.OrderByComparator<LotMaster> orderByComparator);

	/**
	* Returns the lot masters before and after the current lot master in the ordered set where itemId = &#63; and lotNo = &#63;.
	*
	* @param lotMasterSid the primary key of the current lot master
	* @param itemId the item ID
	* @param lotNo the lot no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next lot master
	* @throws NoSuchLotMasterException if a lot master with the primary key could not be found
	*/
	public LotMaster[] findByLotUnique_PrevAndNext(int lotMasterSid,
		java.lang.String itemId, java.lang.String lotNo,
		com.liferay.portal.kernel.util.OrderByComparator<LotMaster> orderByComparator)
		throws NoSuchLotMasterException;

	/**
	* Removes all the lot masters where itemId = &#63; and lotNo = &#63; from the database.
	*
	* @param itemId the item ID
	* @param lotNo the lot no
	*/
	public void removeByLotUnique(java.lang.String itemId,
		java.lang.String lotNo);

	/**
	* Returns the number of lot masters where itemId = &#63; and lotNo = &#63;.
	*
	* @param itemId the item ID
	* @param lotNo the lot no
	* @return the number of matching lot masters
	*/
	public int countByLotUnique(java.lang.String itemId, java.lang.String lotNo);

	/**
	* Caches the lot master in the entity cache if it is enabled.
	*
	* @param lotMaster the lot master
	*/
	public void cacheResult(LotMaster lotMaster);

	/**
	* Caches the lot masters in the entity cache if it is enabled.
	*
	* @param lotMasters the lot masters
	*/
	public void cacheResult(java.util.List<LotMaster> lotMasters);

	/**
	* Creates a new lot master with the primary key. Does not add the lot master to the database.
	*
	* @param lotMasterSid the primary key for the new lot master
	* @return the new lot master
	*/
	public LotMaster create(int lotMasterSid);

	/**
	* Removes the lot master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lotMasterSid the primary key of the lot master
	* @return the lot master that was removed
	* @throws NoSuchLotMasterException if a lot master with the primary key could not be found
	*/
	public LotMaster remove(int lotMasterSid) throws NoSuchLotMasterException;

	public LotMaster updateImpl(LotMaster lotMaster);

	/**
	* Returns the lot master with the primary key or throws a {@link NoSuchLotMasterException} if it could not be found.
	*
	* @param lotMasterSid the primary key of the lot master
	* @return the lot master
	* @throws NoSuchLotMasterException if a lot master with the primary key could not be found
	*/
	public LotMaster findByPrimaryKey(int lotMasterSid)
		throws NoSuchLotMasterException;

	/**
	* Returns the lot master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param lotMasterSid the primary key of the lot master
	* @return the lot master, or <code>null</code> if a lot master with the primary key could not be found
	*/
	public LotMaster fetchByPrimaryKey(int lotMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, LotMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the lot masters.
	*
	* @return the lot masters
	*/
	public java.util.List<LotMaster> findAll();

	/**
	* Returns a range of all the lot masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of lot masters
	* @param end the upper bound of the range of lot masters (not inclusive)
	* @return the range of lot masters
	*/
	public java.util.List<LotMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the lot masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of lot masters
	* @param end the upper bound of the range of lot masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of lot masters
	*/
	public java.util.List<LotMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LotMaster> orderByComparator);

	/**
	* Returns an ordered range of all the lot masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of lot masters
	* @param end the upper bound of the range of lot masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of lot masters
	*/
	public java.util.List<LotMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LotMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the lot masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of lot masters.
	*
	* @return the number of lot masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}