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

import com.stpl.app.exception.NoSuchPsDetailsException;
import com.stpl.app.model.PsDetails;

/**
 * The persistence interface for the ps details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.PsDetailsPersistenceImpl
 * @see PsDetailsUtil
 * @generated
 */
@ProviderType
public interface PsDetailsPersistence extends BasePersistence<PsDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PsDetailsUtil} to access the ps details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the ps detailses where psModelSid = &#63;.
	*
	* @param psModelSid the ps model sid
	* @return the matching ps detailses
	*/
	public java.util.List<PsDetails> findByPriceScheduleMasterDetails(
		int psModelSid);

	/**
	* Returns a range of all the ps detailses where psModelSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param psModelSid the ps model sid
	* @param start the lower bound of the range of ps detailses
	* @param end the upper bound of the range of ps detailses (not inclusive)
	* @return the range of matching ps detailses
	*/
	public java.util.List<PsDetails> findByPriceScheduleMasterDetails(
		int psModelSid, int start, int end);

	/**
	* Returns an ordered range of all the ps detailses where psModelSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param psModelSid the ps model sid
	* @param start the lower bound of the range of ps detailses
	* @param end the upper bound of the range of ps detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ps detailses
	*/
	public java.util.List<PsDetails> findByPriceScheduleMasterDetails(
		int psModelSid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PsDetails> orderByComparator);

	/**
	* Returns an ordered range of all the ps detailses where psModelSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param psModelSid the ps model sid
	* @param start the lower bound of the range of ps detailses
	* @param end the upper bound of the range of ps detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ps detailses
	*/
	public java.util.List<PsDetails> findByPriceScheduleMasterDetails(
		int psModelSid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PsDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first ps details in the ordered set where psModelSid = &#63;.
	*
	* @param psModelSid the ps model sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ps details
	* @throws NoSuchPsDetailsException if a matching ps details could not be found
	*/
	public PsDetails findByPriceScheduleMasterDetails_First(int psModelSid,
		com.liferay.portal.kernel.util.OrderByComparator<PsDetails> orderByComparator)
		throws NoSuchPsDetailsException;

	/**
	* Returns the first ps details in the ordered set where psModelSid = &#63;.
	*
	* @param psModelSid the ps model sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ps details, or <code>null</code> if a matching ps details could not be found
	*/
	public PsDetails fetchByPriceScheduleMasterDetails_First(int psModelSid,
		com.liferay.portal.kernel.util.OrderByComparator<PsDetails> orderByComparator);

	/**
	* Returns the last ps details in the ordered set where psModelSid = &#63;.
	*
	* @param psModelSid the ps model sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ps details
	* @throws NoSuchPsDetailsException if a matching ps details could not be found
	*/
	public PsDetails findByPriceScheduleMasterDetails_Last(int psModelSid,
		com.liferay.portal.kernel.util.OrderByComparator<PsDetails> orderByComparator)
		throws NoSuchPsDetailsException;

	/**
	* Returns the last ps details in the ordered set where psModelSid = &#63;.
	*
	* @param psModelSid the ps model sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ps details, or <code>null</code> if a matching ps details could not be found
	*/
	public PsDetails fetchByPriceScheduleMasterDetails_Last(int psModelSid,
		com.liferay.portal.kernel.util.OrderByComparator<PsDetails> orderByComparator);

	/**
	* Returns the ps detailses before and after the current ps details in the ordered set where psModelSid = &#63;.
	*
	* @param psDetailsSid the primary key of the current ps details
	* @param psModelSid the ps model sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ps details
	* @throws NoSuchPsDetailsException if a ps details with the primary key could not be found
	*/
	public PsDetails[] findByPriceScheduleMasterDetails_PrevAndNext(
		int psDetailsSid, int psModelSid,
		com.liferay.portal.kernel.util.OrderByComparator<PsDetails> orderByComparator)
		throws NoSuchPsDetailsException;

	/**
	* Removes all the ps detailses where psModelSid = &#63; from the database.
	*
	* @param psModelSid the ps model sid
	*/
	public void removeByPriceScheduleMasterDetails(int psModelSid);

	/**
	* Returns the number of ps detailses where psModelSid = &#63;.
	*
	* @param psModelSid the ps model sid
	* @return the number of matching ps detailses
	*/
	public int countByPriceScheduleMasterDetails(int psModelSid);

	/**
	* Caches the ps details in the entity cache if it is enabled.
	*
	* @param psDetails the ps details
	*/
	public void cacheResult(PsDetails psDetails);

	/**
	* Caches the ps detailses in the entity cache if it is enabled.
	*
	* @param psDetailses the ps detailses
	*/
	public void cacheResult(java.util.List<PsDetails> psDetailses);

	/**
	* Creates a new ps details with the primary key. Does not add the ps details to the database.
	*
	* @param psDetailsSid the primary key for the new ps details
	* @return the new ps details
	*/
	public PsDetails create(int psDetailsSid);

	/**
	* Removes the ps details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param psDetailsSid the primary key of the ps details
	* @return the ps details that was removed
	* @throws NoSuchPsDetailsException if a ps details with the primary key could not be found
	*/
	public PsDetails remove(int psDetailsSid) throws NoSuchPsDetailsException;

	public PsDetails updateImpl(PsDetails psDetails);

	/**
	* Returns the ps details with the primary key or throws a {@link NoSuchPsDetailsException} if it could not be found.
	*
	* @param psDetailsSid the primary key of the ps details
	* @return the ps details
	* @throws NoSuchPsDetailsException if a ps details with the primary key could not be found
	*/
	public PsDetails findByPrimaryKey(int psDetailsSid)
		throws NoSuchPsDetailsException;

	/**
	* Returns the ps details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param psDetailsSid the primary key of the ps details
	* @return the ps details, or <code>null</code> if a ps details with the primary key could not be found
	*/
	public PsDetails fetchByPrimaryKey(int psDetailsSid);

	@Override
	public java.util.Map<java.io.Serializable, PsDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ps detailses.
	*
	* @return the ps detailses
	*/
	public java.util.List<PsDetails> findAll();

	/**
	* Returns a range of all the ps detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ps detailses
	* @param end the upper bound of the range of ps detailses (not inclusive)
	* @return the range of ps detailses
	*/
	public java.util.List<PsDetails> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ps detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ps detailses
	* @param end the upper bound of the range of ps detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ps detailses
	*/
	public java.util.List<PsDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PsDetails> orderByComparator);

	/**
	* Returns an ordered range of all the ps detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ps detailses
	* @param end the upper bound of the range of ps detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ps detailses
	*/
	public java.util.List<PsDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PsDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ps detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ps detailses.
	*
	* @return the number of ps detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}