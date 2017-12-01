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

import com.stpl.app.exception.NoSuchIfpDetailsException;
import com.stpl.app.model.IfpDetails;

/**
 * The persistence interface for the ifp details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.IfpDetailsPersistenceImpl
 * @see IfpDetailsUtil
 * @generated
 */
@ProviderType
public interface IfpDetailsPersistence extends BasePersistence<IfpDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link IfpDetailsUtil} to access the ifp details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the ifp detailses where ifpModelSid = &#63;.
	*
	* @param ifpModelSid the ifp model sid
	* @return the matching ifp detailses
	*/
	public java.util.List<IfpDetails> findByItemFamilyPlanDetails(
		int ifpModelSid);

	/**
	* Returns a range of all the ifp detailses where ifpModelSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ifpModelSid the ifp model sid
	* @param start the lower bound of the range of ifp detailses
	* @param end the upper bound of the range of ifp detailses (not inclusive)
	* @return the range of matching ifp detailses
	*/
	public java.util.List<IfpDetails> findByItemFamilyPlanDetails(
		int ifpModelSid, int start, int end);

	/**
	* Returns an ordered range of all the ifp detailses where ifpModelSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ifpModelSid the ifp model sid
	* @param start the lower bound of the range of ifp detailses
	* @param end the upper bound of the range of ifp detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ifp detailses
	*/
	public java.util.List<IfpDetails> findByItemFamilyPlanDetails(
		int ifpModelSid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IfpDetails> orderByComparator);

	/**
	* Returns an ordered range of all the ifp detailses where ifpModelSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ifpModelSid the ifp model sid
	* @param start the lower bound of the range of ifp detailses
	* @param end the upper bound of the range of ifp detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ifp detailses
	*/
	public java.util.List<IfpDetails> findByItemFamilyPlanDetails(
		int ifpModelSid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IfpDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first ifp details in the ordered set where ifpModelSid = &#63;.
	*
	* @param ifpModelSid the ifp model sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ifp details
	* @throws NoSuchIfpDetailsException if a matching ifp details could not be found
	*/
	public IfpDetails findByItemFamilyPlanDetails_First(int ifpModelSid,
		com.liferay.portal.kernel.util.OrderByComparator<IfpDetails> orderByComparator)
		throws NoSuchIfpDetailsException;

	/**
	* Returns the first ifp details in the ordered set where ifpModelSid = &#63;.
	*
	* @param ifpModelSid the ifp model sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ifp details, or <code>null</code> if a matching ifp details could not be found
	*/
	public IfpDetails fetchByItemFamilyPlanDetails_First(int ifpModelSid,
		com.liferay.portal.kernel.util.OrderByComparator<IfpDetails> orderByComparator);

	/**
	* Returns the last ifp details in the ordered set where ifpModelSid = &#63;.
	*
	* @param ifpModelSid the ifp model sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ifp details
	* @throws NoSuchIfpDetailsException if a matching ifp details could not be found
	*/
	public IfpDetails findByItemFamilyPlanDetails_Last(int ifpModelSid,
		com.liferay.portal.kernel.util.OrderByComparator<IfpDetails> orderByComparator)
		throws NoSuchIfpDetailsException;

	/**
	* Returns the last ifp details in the ordered set where ifpModelSid = &#63;.
	*
	* @param ifpModelSid the ifp model sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ifp details, or <code>null</code> if a matching ifp details could not be found
	*/
	public IfpDetails fetchByItemFamilyPlanDetails_Last(int ifpModelSid,
		com.liferay.portal.kernel.util.OrderByComparator<IfpDetails> orderByComparator);

	/**
	* Returns the ifp detailses before and after the current ifp details in the ordered set where ifpModelSid = &#63;.
	*
	* @param ifpDetailsSid the primary key of the current ifp details
	* @param ifpModelSid the ifp model sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ifp details
	* @throws NoSuchIfpDetailsException if a ifp details with the primary key could not be found
	*/
	public IfpDetails[] findByItemFamilyPlanDetails_PrevAndNext(
		int ifpDetailsSid, int ifpModelSid,
		com.liferay.portal.kernel.util.OrderByComparator<IfpDetails> orderByComparator)
		throws NoSuchIfpDetailsException;

	/**
	* Removes all the ifp detailses where ifpModelSid = &#63; from the database.
	*
	* @param ifpModelSid the ifp model sid
	*/
	public void removeByItemFamilyPlanDetails(int ifpModelSid);

	/**
	* Returns the number of ifp detailses where ifpModelSid = &#63;.
	*
	* @param ifpModelSid the ifp model sid
	* @return the number of matching ifp detailses
	*/
	public int countByItemFamilyPlanDetails(int ifpModelSid);

	/**
	* Caches the ifp details in the entity cache if it is enabled.
	*
	* @param ifpDetails the ifp details
	*/
	public void cacheResult(IfpDetails ifpDetails);

	/**
	* Caches the ifp detailses in the entity cache if it is enabled.
	*
	* @param ifpDetailses the ifp detailses
	*/
	public void cacheResult(java.util.List<IfpDetails> ifpDetailses);

	/**
	* Creates a new ifp details with the primary key. Does not add the ifp details to the database.
	*
	* @param ifpDetailsSid the primary key for the new ifp details
	* @return the new ifp details
	*/
	public IfpDetails create(int ifpDetailsSid);

	/**
	* Removes the ifp details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ifpDetailsSid the primary key of the ifp details
	* @return the ifp details that was removed
	* @throws NoSuchIfpDetailsException if a ifp details with the primary key could not be found
	*/
	public IfpDetails remove(int ifpDetailsSid)
		throws NoSuchIfpDetailsException;

	public IfpDetails updateImpl(IfpDetails ifpDetails);

	/**
	* Returns the ifp details with the primary key or throws a {@link NoSuchIfpDetailsException} if it could not be found.
	*
	* @param ifpDetailsSid the primary key of the ifp details
	* @return the ifp details
	* @throws NoSuchIfpDetailsException if a ifp details with the primary key could not be found
	*/
	public IfpDetails findByPrimaryKey(int ifpDetailsSid)
		throws NoSuchIfpDetailsException;

	/**
	* Returns the ifp details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ifpDetailsSid the primary key of the ifp details
	* @return the ifp details, or <code>null</code> if a ifp details with the primary key could not be found
	*/
	public IfpDetails fetchByPrimaryKey(int ifpDetailsSid);

	@Override
	public java.util.Map<java.io.Serializable, IfpDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ifp detailses.
	*
	* @return the ifp detailses
	*/
	public java.util.List<IfpDetails> findAll();

	/**
	* Returns a range of all the ifp detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ifp detailses
	* @param end the upper bound of the range of ifp detailses (not inclusive)
	* @return the range of ifp detailses
	*/
	public java.util.List<IfpDetails> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ifp detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ifp detailses
	* @param end the upper bound of the range of ifp detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ifp detailses
	*/
	public java.util.List<IfpDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IfpDetails> orderByComparator);

	/**
	* Returns an ordered range of all the ifp detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ifp detailses
	* @param end the upper bound of the range of ifp detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ifp detailses
	*/
	public java.util.List<IfpDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IfpDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ifp detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ifp detailses.
	*
	* @return the number of ifp detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}