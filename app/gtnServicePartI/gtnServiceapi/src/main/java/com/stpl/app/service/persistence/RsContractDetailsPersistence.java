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

import com.stpl.app.exception.NoSuchRsContractDetailsException;
import com.stpl.app.model.RsContractDetails;

/**
 * The persistence interface for the rs contract details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.RsContractDetailsPersistenceImpl
 * @see RsContractDetailsUtil
 * @generated
 */
@ProviderType
public interface RsContractDetailsPersistence extends BasePersistence<RsContractDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RsContractDetailsUtil} to access the rs contract details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the rs contract detailses where rsContractSid = &#63; and itemMasterSid = &#63;.
	*
	* @param rsContractSid the rs contract sid
	* @param itemMasterSid the item master sid
	* @return the matching rs contract detailses
	*/
	public java.util.List<RsContractDetails> findByRebateScheduleDetails(
		int rsContractSid, int itemMasterSid);

	/**
	* Returns a range of all the rs contract detailses where rsContractSid = &#63; and itemMasterSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rsContractSid the rs contract sid
	* @param itemMasterSid the item master sid
	* @param start the lower bound of the range of rs contract detailses
	* @param end the upper bound of the range of rs contract detailses (not inclusive)
	* @return the range of matching rs contract detailses
	*/
	public java.util.List<RsContractDetails> findByRebateScheduleDetails(
		int rsContractSid, int itemMasterSid, int start, int end);

	/**
	* Returns an ordered range of all the rs contract detailses where rsContractSid = &#63; and itemMasterSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rsContractSid the rs contract sid
	* @param itemMasterSid the item master sid
	* @param start the lower bound of the range of rs contract detailses
	* @param end the upper bound of the range of rs contract detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rs contract detailses
	*/
	public java.util.List<RsContractDetails> findByRebateScheduleDetails(
		int rsContractSid, int itemMasterSid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RsContractDetails> orderByComparator);

	/**
	* Returns an ordered range of all the rs contract detailses where rsContractSid = &#63; and itemMasterSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rsContractSid the rs contract sid
	* @param itemMasterSid the item master sid
	* @param start the lower bound of the range of rs contract detailses
	* @param end the upper bound of the range of rs contract detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching rs contract detailses
	*/
	public java.util.List<RsContractDetails> findByRebateScheduleDetails(
		int rsContractSid, int itemMasterSid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RsContractDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first rs contract details in the ordered set where rsContractSid = &#63; and itemMasterSid = &#63;.
	*
	* @param rsContractSid the rs contract sid
	* @param itemMasterSid the item master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rs contract details
	* @throws NoSuchRsContractDetailsException if a matching rs contract details could not be found
	*/
	public RsContractDetails findByRebateScheduleDetails_First(
		int rsContractSid, int itemMasterSid,
		com.liferay.portal.kernel.util.OrderByComparator<RsContractDetails> orderByComparator)
		throws NoSuchRsContractDetailsException;

	/**
	* Returns the first rs contract details in the ordered set where rsContractSid = &#63; and itemMasterSid = &#63;.
	*
	* @param rsContractSid the rs contract sid
	* @param itemMasterSid the item master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rs contract details, or <code>null</code> if a matching rs contract details could not be found
	*/
	public RsContractDetails fetchByRebateScheduleDetails_First(
		int rsContractSid, int itemMasterSid,
		com.liferay.portal.kernel.util.OrderByComparator<RsContractDetails> orderByComparator);

	/**
	* Returns the last rs contract details in the ordered set where rsContractSid = &#63; and itemMasterSid = &#63;.
	*
	* @param rsContractSid the rs contract sid
	* @param itemMasterSid the item master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rs contract details
	* @throws NoSuchRsContractDetailsException if a matching rs contract details could not be found
	*/
	public RsContractDetails findByRebateScheduleDetails_Last(
		int rsContractSid, int itemMasterSid,
		com.liferay.portal.kernel.util.OrderByComparator<RsContractDetails> orderByComparator)
		throws NoSuchRsContractDetailsException;

	/**
	* Returns the last rs contract details in the ordered set where rsContractSid = &#63; and itemMasterSid = &#63;.
	*
	* @param rsContractSid the rs contract sid
	* @param itemMasterSid the item master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rs contract details, or <code>null</code> if a matching rs contract details could not be found
	*/
	public RsContractDetails fetchByRebateScheduleDetails_Last(
		int rsContractSid, int itemMasterSid,
		com.liferay.portal.kernel.util.OrderByComparator<RsContractDetails> orderByComparator);

	/**
	* Returns the rs contract detailses before and after the current rs contract details in the ordered set where rsContractSid = &#63; and itemMasterSid = &#63;.
	*
	* @param rsContractDetailsSid the primary key of the current rs contract details
	* @param rsContractSid the rs contract sid
	* @param itemMasterSid the item master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next rs contract details
	* @throws NoSuchRsContractDetailsException if a rs contract details with the primary key could not be found
	*/
	public RsContractDetails[] findByRebateScheduleDetails_PrevAndNext(
		int rsContractDetailsSid, int rsContractSid, int itemMasterSid,
		com.liferay.portal.kernel.util.OrderByComparator<RsContractDetails> orderByComparator)
		throws NoSuchRsContractDetailsException;

	/**
	* Removes all the rs contract detailses where rsContractSid = &#63; and itemMasterSid = &#63; from the database.
	*
	* @param rsContractSid the rs contract sid
	* @param itemMasterSid the item master sid
	*/
	public void removeByRebateScheduleDetails(int rsContractSid,
		int itemMasterSid);

	/**
	* Returns the number of rs contract detailses where rsContractSid = &#63; and itemMasterSid = &#63;.
	*
	* @param rsContractSid the rs contract sid
	* @param itemMasterSid the item master sid
	* @return the number of matching rs contract detailses
	*/
	public int countByRebateScheduleDetails(int rsContractSid, int itemMasterSid);

	/**
	* Caches the rs contract details in the entity cache if it is enabled.
	*
	* @param rsContractDetails the rs contract details
	*/
	public void cacheResult(RsContractDetails rsContractDetails);

	/**
	* Caches the rs contract detailses in the entity cache if it is enabled.
	*
	* @param rsContractDetailses the rs contract detailses
	*/
	public void cacheResult(
		java.util.List<RsContractDetails> rsContractDetailses);

	/**
	* Creates a new rs contract details with the primary key. Does not add the rs contract details to the database.
	*
	* @param rsContractDetailsSid the primary key for the new rs contract details
	* @return the new rs contract details
	*/
	public RsContractDetails create(int rsContractDetailsSid);

	/**
	* Removes the rs contract details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param rsContractDetailsSid the primary key of the rs contract details
	* @return the rs contract details that was removed
	* @throws NoSuchRsContractDetailsException if a rs contract details with the primary key could not be found
	*/
	public RsContractDetails remove(int rsContractDetailsSid)
		throws NoSuchRsContractDetailsException;

	public RsContractDetails updateImpl(RsContractDetails rsContractDetails);

	/**
	* Returns the rs contract details with the primary key or throws a {@link NoSuchRsContractDetailsException} if it could not be found.
	*
	* @param rsContractDetailsSid the primary key of the rs contract details
	* @return the rs contract details
	* @throws NoSuchRsContractDetailsException if a rs contract details with the primary key could not be found
	*/
	public RsContractDetails findByPrimaryKey(int rsContractDetailsSid)
		throws NoSuchRsContractDetailsException;

	/**
	* Returns the rs contract details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param rsContractDetailsSid the primary key of the rs contract details
	* @return the rs contract details, or <code>null</code> if a rs contract details with the primary key could not be found
	*/
	public RsContractDetails fetchByPrimaryKey(int rsContractDetailsSid);

	@Override
	public java.util.Map<java.io.Serializable, RsContractDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the rs contract detailses.
	*
	* @return the rs contract detailses
	*/
	public java.util.List<RsContractDetails> findAll();

	/**
	* Returns a range of all the rs contract detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rs contract detailses
	* @param end the upper bound of the range of rs contract detailses (not inclusive)
	* @return the range of rs contract detailses
	*/
	public java.util.List<RsContractDetails> findAll(int start, int end);

	/**
	* Returns an ordered range of all the rs contract detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rs contract detailses
	* @param end the upper bound of the range of rs contract detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of rs contract detailses
	*/
	public java.util.List<RsContractDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RsContractDetails> orderByComparator);

	/**
	* Returns an ordered range of all the rs contract detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rs contract detailses
	* @param end the upper bound of the range of rs contract detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of rs contract detailses
	*/
	public java.util.List<RsContractDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RsContractDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the rs contract detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of rs contract detailses.
	*
	* @return the number of rs contract detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}