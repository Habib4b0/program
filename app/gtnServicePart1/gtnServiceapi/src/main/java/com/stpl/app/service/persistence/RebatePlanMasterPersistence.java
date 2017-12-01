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

import com.stpl.app.exception.NoSuchRebatePlanMasterException;
import com.stpl.app.model.RebatePlanMaster;

/**
 * The persistence interface for the rebate plan master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.RebatePlanMasterPersistenceImpl
 * @see RebatePlanMasterUtil
 * @generated
 */
@ProviderType
public interface RebatePlanMasterPersistence extends BasePersistence<RebatePlanMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RebatePlanMasterUtil} to access the rebate plan master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the rebate plan masters where rebatePlanId = &#63;.
	*
	* @param rebatePlanId the rebate plan ID
	* @return the matching rebate plan masters
	*/
	public java.util.List<RebatePlanMaster> findByRebatePlanId(
		java.lang.String rebatePlanId);

	/**
	* Returns a range of all the rebate plan masters where rebatePlanId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rebatePlanId the rebate plan ID
	* @param start the lower bound of the range of rebate plan masters
	* @param end the upper bound of the range of rebate plan masters (not inclusive)
	* @return the range of matching rebate plan masters
	*/
	public java.util.List<RebatePlanMaster> findByRebatePlanId(
		java.lang.String rebatePlanId, int start, int end);

	/**
	* Returns an ordered range of all the rebate plan masters where rebatePlanId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rebatePlanId the rebate plan ID
	* @param start the lower bound of the range of rebate plan masters
	* @param end the upper bound of the range of rebate plan masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rebate plan masters
	*/
	public java.util.List<RebatePlanMaster> findByRebatePlanId(
		java.lang.String rebatePlanId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RebatePlanMaster> orderByComparator);

	/**
	* Returns an ordered range of all the rebate plan masters where rebatePlanId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rebatePlanId the rebate plan ID
	* @param start the lower bound of the range of rebate plan masters
	* @param end the upper bound of the range of rebate plan masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching rebate plan masters
	*/
	public java.util.List<RebatePlanMaster> findByRebatePlanId(
		java.lang.String rebatePlanId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RebatePlanMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first rebate plan master in the ordered set where rebatePlanId = &#63;.
	*
	* @param rebatePlanId the rebate plan ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rebate plan master
	* @throws NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
	*/
	public RebatePlanMaster findByRebatePlanId_First(
		java.lang.String rebatePlanId,
		com.liferay.portal.kernel.util.OrderByComparator<RebatePlanMaster> orderByComparator)
		throws NoSuchRebatePlanMasterException;

	/**
	* Returns the first rebate plan master in the ordered set where rebatePlanId = &#63;.
	*
	* @param rebatePlanId the rebate plan ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
	*/
	public RebatePlanMaster fetchByRebatePlanId_First(
		java.lang.String rebatePlanId,
		com.liferay.portal.kernel.util.OrderByComparator<RebatePlanMaster> orderByComparator);

	/**
	* Returns the last rebate plan master in the ordered set where rebatePlanId = &#63;.
	*
	* @param rebatePlanId the rebate plan ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rebate plan master
	* @throws NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
	*/
	public RebatePlanMaster findByRebatePlanId_Last(
		java.lang.String rebatePlanId,
		com.liferay.portal.kernel.util.OrderByComparator<RebatePlanMaster> orderByComparator)
		throws NoSuchRebatePlanMasterException;

	/**
	* Returns the last rebate plan master in the ordered set where rebatePlanId = &#63;.
	*
	* @param rebatePlanId the rebate plan ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
	*/
	public RebatePlanMaster fetchByRebatePlanId_Last(
		java.lang.String rebatePlanId,
		com.liferay.portal.kernel.util.OrderByComparator<RebatePlanMaster> orderByComparator);

	/**
	* Returns the rebate plan masters before and after the current rebate plan master in the ordered set where rebatePlanId = &#63;.
	*
	* @param rebatePlanMasterSid the primary key of the current rebate plan master
	* @param rebatePlanId the rebate plan ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next rebate plan master
	* @throws NoSuchRebatePlanMasterException if a rebate plan master with the primary key could not be found
	*/
	public RebatePlanMaster[] findByRebatePlanId_PrevAndNext(
		int rebatePlanMasterSid, java.lang.String rebatePlanId,
		com.liferay.portal.kernel.util.OrderByComparator<RebatePlanMaster> orderByComparator)
		throws NoSuchRebatePlanMasterException;

	/**
	* Removes all the rebate plan masters where rebatePlanId = &#63; from the database.
	*
	* @param rebatePlanId the rebate plan ID
	*/
	public void removeByRebatePlanId(java.lang.String rebatePlanId);

	/**
	* Returns the number of rebate plan masters where rebatePlanId = &#63;.
	*
	* @param rebatePlanId the rebate plan ID
	* @return the number of matching rebate plan masters
	*/
	public int countByRebatePlanId(java.lang.String rebatePlanId);

	/**
	* Returns all the rebate plan masters where rebatePlanNo = &#63;.
	*
	* @param rebatePlanNo the rebate plan no
	* @return the matching rebate plan masters
	*/
	public java.util.List<RebatePlanMaster> findByRebatePlanNo(
		java.lang.String rebatePlanNo);

	/**
	* Returns a range of all the rebate plan masters where rebatePlanNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rebatePlanNo the rebate plan no
	* @param start the lower bound of the range of rebate plan masters
	* @param end the upper bound of the range of rebate plan masters (not inclusive)
	* @return the range of matching rebate plan masters
	*/
	public java.util.List<RebatePlanMaster> findByRebatePlanNo(
		java.lang.String rebatePlanNo, int start, int end);

	/**
	* Returns an ordered range of all the rebate plan masters where rebatePlanNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rebatePlanNo the rebate plan no
	* @param start the lower bound of the range of rebate plan masters
	* @param end the upper bound of the range of rebate plan masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rebate plan masters
	*/
	public java.util.List<RebatePlanMaster> findByRebatePlanNo(
		java.lang.String rebatePlanNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RebatePlanMaster> orderByComparator);

	/**
	* Returns an ordered range of all the rebate plan masters where rebatePlanNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rebatePlanNo the rebate plan no
	* @param start the lower bound of the range of rebate plan masters
	* @param end the upper bound of the range of rebate plan masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching rebate plan masters
	*/
	public java.util.List<RebatePlanMaster> findByRebatePlanNo(
		java.lang.String rebatePlanNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RebatePlanMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first rebate plan master in the ordered set where rebatePlanNo = &#63;.
	*
	* @param rebatePlanNo the rebate plan no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rebate plan master
	* @throws NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
	*/
	public RebatePlanMaster findByRebatePlanNo_First(
		java.lang.String rebatePlanNo,
		com.liferay.portal.kernel.util.OrderByComparator<RebatePlanMaster> orderByComparator)
		throws NoSuchRebatePlanMasterException;

	/**
	* Returns the first rebate plan master in the ordered set where rebatePlanNo = &#63;.
	*
	* @param rebatePlanNo the rebate plan no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
	*/
	public RebatePlanMaster fetchByRebatePlanNo_First(
		java.lang.String rebatePlanNo,
		com.liferay.portal.kernel.util.OrderByComparator<RebatePlanMaster> orderByComparator);

	/**
	* Returns the last rebate plan master in the ordered set where rebatePlanNo = &#63;.
	*
	* @param rebatePlanNo the rebate plan no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rebate plan master
	* @throws NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
	*/
	public RebatePlanMaster findByRebatePlanNo_Last(
		java.lang.String rebatePlanNo,
		com.liferay.portal.kernel.util.OrderByComparator<RebatePlanMaster> orderByComparator)
		throws NoSuchRebatePlanMasterException;

	/**
	* Returns the last rebate plan master in the ordered set where rebatePlanNo = &#63;.
	*
	* @param rebatePlanNo the rebate plan no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
	*/
	public RebatePlanMaster fetchByRebatePlanNo_Last(
		java.lang.String rebatePlanNo,
		com.liferay.portal.kernel.util.OrderByComparator<RebatePlanMaster> orderByComparator);

	/**
	* Returns the rebate plan masters before and after the current rebate plan master in the ordered set where rebatePlanNo = &#63;.
	*
	* @param rebatePlanMasterSid the primary key of the current rebate plan master
	* @param rebatePlanNo the rebate plan no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next rebate plan master
	* @throws NoSuchRebatePlanMasterException if a rebate plan master with the primary key could not be found
	*/
	public RebatePlanMaster[] findByRebatePlanNo_PrevAndNext(
		int rebatePlanMasterSid, java.lang.String rebatePlanNo,
		com.liferay.portal.kernel.util.OrderByComparator<RebatePlanMaster> orderByComparator)
		throws NoSuchRebatePlanMasterException;

	/**
	* Removes all the rebate plan masters where rebatePlanNo = &#63; from the database.
	*
	* @param rebatePlanNo the rebate plan no
	*/
	public void removeByRebatePlanNo(java.lang.String rebatePlanNo);

	/**
	* Returns the number of rebate plan masters where rebatePlanNo = &#63;.
	*
	* @param rebatePlanNo the rebate plan no
	* @return the number of matching rebate plan masters
	*/
	public int countByRebatePlanNo(java.lang.String rebatePlanNo);

	/**
	* Returns all the rebate plan masters where rebatePlanName = &#63;.
	*
	* @param rebatePlanName the rebate plan name
	* @return the matching rebate plan masters
	*/
	public java.util.List<RebatePlanMaster> findByRebatePlanName(
		java.lang.String rebatePlanName);

	/**
	* Returns a range of all the rebate plan masters where rebatePlanName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rebatePlanName the rebate plan name
	* @param start the lower bound of the range of rebate plan masters
	* @param end the upper bound of the range of rebate plan masters (not inclusive)
	* @return the range of matching rebate plan masters
	*/
	public java.util.List<RebatePlanMaster> findByRebatePlanName(
		java.lang.String rebatePlanName, int start, int end);

	/**
	* Returns an ordered range of all the rebate plan masters where rebatePlanName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rebatePlanName the rebate plan name
	* @param start the lower bound of the range of rebate plan masters
	* @param end the upper bound of the range of rebate plan masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rebate plan masters
	*/
	public java.util.List<RebatePlanMaster> findByRebatePlanName(
		java.lang.String rebatePlanName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RebatePlanMaster> orderByComparator);

	/**
	* Returns an ordered range of all the rebate plan masters where rebatePlanName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rebatePlanName the rebate plan name
	* @param start the lower bound of the range of rebate plan masters
	* @param end the upper bound of the range of rebate plan masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching rebate plan masters
	*/
	public java.util.List<RebatePlanMaster> findByRebatePlanName(
		java.lang.String rebatePlanName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RebatePlanMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first rebate plan master in the ordered set where rebatePlanName = &#63;.
	*
	* @param rebatePlanName the rebate plan name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rebate plan master
	* @throws NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
	*/
	public RebatePlanMaster findByRebatePlanName_First(
		java.lang.String rebatePlanName,
		com.liferay.portal.kernel.util.OrderByComparator<RebatePlanMaster> orderByComparator)
		throws NoSuchRebatePlanMasterException;

	/**
	* Returns the first rebate plan master in the ordered set where rebatePlanName = &#63;.
	*
	* @param rebatePlanName the rebate plan name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
	*/
	public RebatePlanMaster fetchByRebatePlanName_First(
		java.lang.String rebatePlanName,
		com.liferay.portal.kernel.util.OrderByComparator<RebatePlanMaster> orderByComparator);

	/**
	* Returns the last rebate plan master in the ordered set where rebatePlanName = &#63;.
	*
	* @param rebatePlanName the rebate plan name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rebate plan master
	* @throws NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
	*/
	public RebatePlanMaster findByRebatePlanName_Last(
		java.lang.String rebatePlanName,
		com.liferay.portal.kernel.util.OrderByComparator<RebatePlanMaster> orderByComparator)
		throws NoSuchRebatePlanMasterException;

	/**
	* Returns the last rebate plan master in the ordered set where rebatePlanName = &#63;.
	*
	* @param rebatePlanName the rebate plan name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
	*/
	public RebatePlanMaster fetchByRebatePlanName_Last(
		java.lang.String rebatePlanName,
		com.liferay.portal.kernel.util.OrderByComparator<RebatePlanMaster> orderByComparator);

	/**
	* Returns the rebate plan masters before and after the current rebate plan master in the ordered set where rebatePlanName = &#63;.
	*
	* @param rebatePlanMasterSid the primary key of the current rebate plan master
	* @param rebatePlanName the rebate plan name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next rebate plan master
	* @throws NoSuchRebatePlanMasterException if a rebate plan master with the primary key could not be found
	*/
	public RebatePlanMaster[] findByRebatePlanName_PrevAndNext(
		int rebatePlanMasterSid, java.lang.String rebatePlanName,
		com.liferay.portal.kernel.util.OrderByComparator<RebatePlanMaster> orderByComparator)
		throws NoSuchRebatePlanMasterException;

	/**
	* Removes all the rebate plan masters where rebatePlanName = &#63; from the database.
	*
	* @param rebatePlanName the rebate plan name
	*/
	public void removeByRebatePlanName(java.lang.String rebatePlanName);

	/**
	* Returns the number of rebate plan masters where rebatePlanName = &#63;.
	*
	* @param rebatePlanName the rebate plan name
	* @return the number of matching rebate plan masters
	*/
	public int countByRebatePlanName(java.lang.String rebatePlanName);

	/**
	* Returns all the rebate plan masters where rebatePlanStatus = &#63;.
	*
	* @param rebatePlanStatus the rebate plan status
	* @return the matching rebate plan masters
	*/
	public java.util.List<RebatePlanMaster> findByRebatePlanStatus(
		int rebatePlanStatus);

	/**
	* Returns a range of all the rebate plan masters where rebatePlanStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rebatePlanStatus the rebate plan status
	* @param start the lower bound of the range of rebate plan masters
	* @param end the upper bound of the range of rebate plan masters (not inclusive)
	* @return the range of matching rebate plan masters
	*/
	public java.util.List<RebatePlanMaster> findByRebatePlanStatus(
		int rebatePlanStatus, int start, int end);

	/**
	* Returns an ordered range of all the rebate plan masters where rebatePlanStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rebatePlanStatus the rebate plan status
	* @param start the lower bound of the range of rebate plan masters
	* @param end the upper bound of the range of rebate plan masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rebate plan masters
	*/
	public java.util.List<RebatePlanMaster> findByRebatePlanStatus(
		int rebatePlanStatus, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RebatePlanMaster> orderByComparator);

	/**
	* Returns an ordered range of all the rebate plan masters where rebatePlanStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rebatePlanStatus the rebate plan status
	* @param start the lower bound of the range of rebate plan masters
	* @param end the upper bound of the range of rebate plan masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching rebate plan masters
	*/
	public java.util.List<RebatePlanMaster> findByRebatePlanStatus(
		int rebatePlanStatus, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RebatePlanMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first rebate plan master in the ordered set where rebatePlanStatus = &#63;.
	*
	* @param rebatePlanStatus the rebate plan status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rebate plan master
	* @throws NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
	*/
	public RebatePlanMaster findByRebatePlanStatus_First(int rebatePlanStatus,
		com.liferay.portal.kernel.util.OrderByComparator<RebatePlanMaster> orderByComparator)
		throws NoSuchRebatePlanMasterException;

	/**
	* Returns the first rebate plan master in the ordered set where rebatePlanStatus = &#63;.
	*
	* @param rebatePlanStatus the rebate plan status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
	*/
	public RebatePlanMaster fetchByRebatePlanStatus_First(
		int rebatePlanStatus,
		com.liferay.portal.kernel.util.OrderByComparator<RebatePlanMaster> orderByComparator);

	/**
	* Returns the last rebate plan master in the ordered set where rebatePlanStatus = &#63;.
	*
	* @param rebatePlanStatus the rebate plan status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rebate plan master
	* @throws NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
	*/
	public RebatePlanMaster findByRebatePlanStatus_Last(int rebatePlanStatus,
		com.liferay.portal.kernel.util.OrderByComparator<RebatePlanMaster> orderByComparator)
		throws NoSuchRebatePlanMasterException;

	/**
	* Returns the last rebate plan master in the ordered set where rebatePlanStatus = &#63;.
	*
	* @param rebatePlanStatus the rebate plan status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
	*/
	public RebatePlanMaster fetchByRebatePlanStatus_Last(int rebatePlanStatus,
		com.liferay.portal.kernel.util.OrderByComparator<RebatePlanMaster> orderByComparator);

	/**
	* Returns the rebate plan masters before and after the current rebate plan master in the ordered set where rebatePlanStatus = &#63;.
	*
	* @param rebatePlanMasterSid the primary key of the current rebate plan master
	* @param rebatePlanStatus the rebate plan status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next rebate plan master
	* @throws NoSuchRebatePlanMasterException if a rebate plan master with the primary key could not be found
	*/
	public RebatePlanMaster[] findByRebatePlanStatus_PrevAndNext(
		int rebatePlanMasterSid, int rebatePlanStatus,
		com.liferay.portal.kernel.util.OrderByComparator<RebatePlanMaster> orderByComparator)
		throws NoSuchRebatePlanMasterException;

	/**
	* Removes all the rebate plan masters where rebatePlanStatus = &#63; from the database.
	*
	* @param rebatePlanStatus the rebate plan status
	*/
	public void removeByRebatePlanStatus(int rebatePlanStatus);

	/**
	* Returns the number of rebate plan masters where rebatePlanStatus = &#63;.
	*
	* @param rebatePlanStatus the rebate plan status
	* @return the number of matching rebate plan masters
	*/
	public int countByRebatePlanStatus(int rebatePlanStatus);

	/**
	* Returns all the rebate plan masters where rebatePlanType = &#63;.
	*
	* @param rebatePlanType the rebate plan type
	* @return the matching rebate plan masters
	*/
	public java.util.List<RebatePlanMaster> findByRebatePlanType(
		int rebatePlanType);

	/**
	* Returns a range of all the rebate plan masters where rebatePlanType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rebatePlanType the rebate plan type
	* @param start the lower bound of the range of rebate plan masters
	* @param end the upper bound of the range of rebate plan masters (not inclusive)
	* @return the range of matching rebate plan masters
	*/
	public java.util.List<RebatePlanMaster> findByRebatePlanType(
		int rebatePlanType, int start, int end);

	/**
	* Returns an ordered range of all the rebate plan masters where rebatePlanType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rebatePlanType the rebate plan type
	* @param start the lower bound of the range of rebate plan masters
	* @param end the upper bound of the range of rebate plan masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rebate plan masters
	*/
	public java.util.List<RebatePlanMaster> findByRebatePlanType(
		int rebatePlanType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RebatePlanMaster> orderByComparator);

	/**
	* Returns an ordered range of all the rebate plan masters where rebatePlanType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rebatePlanType the rebate plan type
	* @param start the lower bound of the range of rebate plan masters
	* @param end the upper bound of the range of rebate plan masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching rebate plan masters
	*/
	public java.util.List<RebatePlanMaster> findByRebatePlanType(
		int rebatePlanType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RebatePlanMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first rebate plan master in the ordered set where rebatePlanType = &#63;.
	*
	* @param rebatePlanType the rebate plan type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rebate plan master
	* @throws NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
	*/
	public RebatePlanMaster findByRebatePlanType_First(int rebatePlanType,
		com.liferay.portal.kernel.util.OrderByComparator<RebatePlanMaster> orderByComparator)
		throws NoSuchRebatePlanMasterException;

	/**
	* Returns the first rebate plan master in the ordered set where rebatePlanType = &#63;.
	*
	* @param rebatePlanType the rebate plan type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
	*/
	public RebatePlanMaster fetchByRebatePlanType_First(int rebatePlanType,
		com.liferay.portal.kernel.util.OrderByComparator<RebatePlanMaster> orderByComparator);

	/**
	* Returns the last rebate plan master in the ordered set where rebatePlanType = &#63;.
	*
	* @param rebatePlanType the rebate plan type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rebate plan master
	* @throws NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
	*/
	public RebatePlanMaster findByRebatePlanType_Last(int rebatePlanType,
		com.liferay.portal.kernel.util.OrderByComparator<RebatePlanMaster> orderByComparator)
		throws NoSuchRebatePlanMasterException;

	/**
	* Returns the last rebate plan master in the ordered set where rebatePlanType = &#63;.
	*
	* @param rebatePlanType the rebate plan type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
	*/
	public RebatePlanMaster fetchByRebatePlanType_Last(int rebatePlanType,
		com.liferay.portal.kernel.util.OrderByComparator<RebatePlanMaster> orderByComparator);

	/**
	* Returns the rebate plan masters before and after the current rebate plan master in the ordered set where rebatePlanType = &#63;.
	*
	* @param rebatePlanMasterSid the primary key of the current rebate plan master
	* @param rebatePlanType the rebate plan type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next rebate plan master
	* @throws NoSuchRebatePlanMasterException if a rebate plan master with the primary key could not be found
	*/
	public RebatePlanMaster[] findByRebatePlanType_PrevAndNext(
		int rebatePlanMasterSid, int rebatePlanType,
		com.liferay.portal.kernel.util.OrderByComparator<RebatePlanMaster> orderByComparator)
		throws NoSuchRebatePlanMasterException;

	/**
	* Removes all the rebate plan masters where rebatePlanType = &#63; from the database.
	*
	* @param rebatePlanType the rebate plan type
	*/
	public void removeByRebatePlanType(int rebatePlanType);

	/**
	* Returns the number of rebate plan masters where rebatePlanType = &#63;.
	*
	* @param rebatePlanType the rebate plan type
	* @return the number of matching rebate plan masters
	*/
	public int countByRebatePlanType(int rebatePlanType);

	/**
	* Caches the rebate plan master in the entity cache if it is enabled.
	*
	* @param rebatePlanMaster the rebate plan master
	*/
	public void cacheResult(RebatePlanMaster rebatePlanMaster);

	/**
	* Caches the rebate plan masters in the entity cache if it is enabled.
	*
	* @param rebatePlanMasters the rebate plan masters
	*/
	public void cacheResult(java.util.List<RebatePlanMaster> rebatePlanMasters);

	/**
	* Creates a new rebate plan master with the primary key. Does not add the rebate plan master to the database.
	*
	* @param rebatePlanMasterSid the primary key for the new rebate plan master
	* @return the new rebate plan master
	*/
	public RebatePlanMaster create(int rebatePlanMasterSid);

	/**
	* Removes the rebate plan master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param rebatePlanMasterSid the primary key of the rebate plan master
	* @return the rebate plan master that was removed
	* @throws NoSuchRebatePlanMasterException if a rebate plan master with the primary key could not be found
	*/
	public RebatePlanMaster remove(int rebatePlanMasterSid)
		throws NoSuchRebatePlanMasterException;

	public RebatePlanMaster updateImpl(RebatePlanMaster rebatePlanMaster);

	/**
	* Returns the rebate plan master with the primary key or throws a {@link NoSuchRebatePlanMasterException} if it could not be found.
	*
	* @param rebatePlanMasterSid the primary key of the rebate plan master
	* @return the rebate plan master
	* @throws NoSuchRebatePlanMasterException if a rebate plan master with the primary key could not be found
	*/
	public RebatePlanMaster findByPrimaryKey(int rebatePlanMasterSid)
		throws NoSuchRebatePlanMasterException;

	/**
	* Returns the rebate plan master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param rebatePlanMasterSid the primary key of the rebate plan master
	* @return the rebate plan master, or <code>null</code> if a rebate plan master with the primary key could not be found
	*/
	public RebatePlanMaster fetchByPrimaryKey(int rebatePlanMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, RebatePlanMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the rebate plan masters.
	*
	* @return the rebate plan masters
	*/
	public java.util.List<RebatePlanMaster> findAll();

	/**
	* Returns a range of all the rebate plan masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rebate plan masters
	* @param end the upper bound of the range of rebate plan masters (not inclusive)
	* @return the range of rebate plan masters
	*/
	public java.util.List<RebatePlanMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the rebate plan masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rebate plan masters
	* @param end the upper bound of the range of rebate plan masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of rebate plan masters
	*/
	public java.util.List<RebatePlanMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RebatePlanMaster> orderByComparator);

	/**
	* Returns an ordered range of all the rebate plan masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rebate plan masters
	* @param end the upper bound of the range of rebate plan masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of rebate plan masters
	*/
	public java.util.List<RebatePlanMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RebatePlanMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the rebate plan masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of rebate plan masters.
	*
	* @return the number of rebate plan masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}