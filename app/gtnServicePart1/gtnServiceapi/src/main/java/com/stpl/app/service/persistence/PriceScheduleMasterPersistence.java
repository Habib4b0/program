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

import com.stpl.app.exception.NoSuchPriceScheduleMasterException;
import com.stpl.app.model.PriceScheduleMaster;

/**
 * The persistence interface for the price schedule master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.PriceScheduleMasterPersistenceImpl
 * @see PriceScheduleMasterUtil
 * @generated
 */
@ProviderType
public interface PriceScheduleMasterPersistence extends BasePersistence<PriceScheduleMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PriceScheduleMasterUtil} to access the price schedule master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the price schedule masters where priceScheduleId = &#63;.
	*
	* @param priceScheduleId the price schedule ID
	* @return the matching price schedule masters
	*/
	public java.util.List<PriceScheduleMaster> findByPriceScheduleId(
		java.lang.String priceScheduleId);

	/**
	* Returns a range of all the price schedule masters where priceScheduleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param priceScheduleId the price schedule ID
	* @param start the lower bound of the range of price schedule masters
	* @param end the upper bound of the range of price schedule masters (not inclusive)
	* @return the range of matching price schedule masters
	*/
	public java.util.List<PriceScheduleMaster> findByPriceScheduleId(
		java.lang.String priceScheduleId, int start, int end);

	/**
	* Returns an ordered range of all the price schedule masters where priceScheduleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param priceScheduleId the price schedule ID
	* @param start the lower bound of the range of price schedule masters
	* @param end the upper bound of the range of price schedule masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching price schedule masters
	*/
	public java.util.List<PriceScheduleMaster> findByPriceScheduleId(
		java.lang.String priceScheduleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleMaster> orderByComparator);

	/**
	* Returns an ordered range of all the price schedule masters where priceScheduleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param priceScheduleId the price schedule ID
	* @param start the lower bound of the range of price schedule masters
	* @param end the upper bound of the range of price schedule masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching price schedule masters
	*/
	public java.util.List<PriceScheduleMaster> findByPriceScheduleId(
		java.lang.String priceScheduleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first price schedule master in the ordered set where priceScheduleId = &#63;.
	*
	* @param priceScheduleId the price schedule ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching price schedule master
	* @throws NoSuchPriceScheduleMasterException if a matching price schedule master could not be found
	*/
	public PriceScheduleMaster findByPriceScheduleId_First(
		java.lang.String priceScheduleId,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws NoSuchPriceScheduleMasterException;

	/**
	* Returns the first price schedule master in the ordered set where priceScheduleId = &#63;.
	*
	* @param priceScheduleId the price schedule ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching price schedule master, or <code>null</code> if a matching price schedule master could not be found
	*/
	public PriceScheduleMaster fetchByPriceScheduleId_First(
		java.lang.String priceScheduleId,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleMaster> orderByComparator);

	/**
	* Returns the last price schedule master in the ordered set where priceScheduleId = &#63;.
	*
	* @param priceScheduleId the price schedule ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching price schedule master
	* @throws NoSuchPriceScheduleMasterException if a matching price schedule master could not be found
	*/
	public PriceScheduleMaster findByPriceScheduleId_Last(
		java.lang.String priceScheduleId,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws NoSuchPriceScheduleMasterException;

	/**
	* Returns the last price schedule master in the ordered set where priceScheduleId = &#63;.
	*
	* @param priceScheduleId the price schedule ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching price schedule master, or <code>null</code> if a matching price schedule master could not be found
	*/
	public PriceScheduleMaster fetchByPriceScheduleId_Last(
		java.lang.String priceScheduleId,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleMaster> orderByComparator);

	/**
	* Returns the price schedule masters before and after the current price schedule master in the ordered set where priceScheduleId = &#63;.
	*
	* @param priceScheduleSystemId the primary key of the current price schedule master
	* @param priceScheduleId the price schedule ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next price schedule master
	* @throws NoSuchPriceScheduleMasterException if a price schedule master with the primary key could not be found
	*/
	public PriceScheduleMaster[] findByPriceScheduleId_PrevAndNext(
		int priceScheduleSystemId, java.lang.String priceScheduleId,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws NoSuchPriceScheduleMasterException;

	/**
	* Removes all the price schedule masters where priceScheduleId = &#63; from the database.
	*
	* @param priceScheduleId the price schedule ID
	*/
	public void removeByPriceScheduleId(java.lang.String priceScheduleId);

	/**
	* Returns the number of price schedule masters where priceScheduleId = &#63;.
	*
	* @param priceScheduleId the price schedule ID
	* @return the number of matching price schedule masters
	*/
	public int countByPriceScheduleId(java.lang.String priceScheduleId);

	/**
	* Returns all the price schedule masters where priceScheduleNo = &#63;.
	*
	* @param priceScheduleNo the price schedule no
	* @return the matching price schedule masters
	*/
	public java.util.List<PriceScheduleMaster> findByPriceScheduleNo(
		java.lang.String priceScheduleNo);

	/**
	* Returns a range of all the price schedule masters where priceScheduleNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param priceScheduleNo the price schedule no
	* @param start the lower bound of the range of price schedule masters
	* @param end the upper bound of the range of price schedule masters (not inclusive)
	* @return the range of matching price schedule masters
	*/
	public java.util.List<PriceScheduleMaster> findByPriceScheduleNo(
		java.lang.String priceScheduleNo, int start, int end);

	/**
	* Returns an ordered range of all the price schedule masters where priceScheduleNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param priceScheduleNo the price schedule no
	* @param start the lower bound of the range of price schedule masters
	* @param end the upper bound of the range of price schedule masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching price schedule masters
	*/
	public java.util.List<PriceScheduleMaster> findByPriceScheduleNo(
		java.lang.String priceScheduleNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleMaster> orderByComparator);

	/**
	* Returns an ordered range of all the price schedule masters where priceScheduleNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param priceScheduleNo the price schedule no
	* @param start the lower bound of the range of price schedule masters
	* @param end the upper bound of the range of price schedule masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching price schedule masters
	*/
	public java.util.List<PriceScheduleMaster> findByPriceScheduleNo(
		java.lang.String priceScheduleNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first price schedule master in the ordered set where priceScheduleNo = &#63;.
	*
	* @param priceScheduleNo the price schedule no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching price schedule master
	* @throws NoSuchPriceScheduleMasterException if a matching price schedule master could not be found
	*/
	public PriceScheduleMaster findByPriceScheduleNo_First(
		java.lang.String priceScheduleNo,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws NoSuchPriceScheduleMasterException;

	/**
	* Returns the first price schedule master in the ordered set where priceScheduleNo = &#63;.
	*
	* @param priceScheduleNo the price schedule no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching price schedule master, or <code>null</code> if a matching price schedule master could not be found
	*/
	public PriceScheduleMaster fetchByPriceScheduleNo_First(
		java.lang.String priceScheduleNo,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleMaster> orderByComparator);

	/**
	* Returns the last price schedule master in the ordered set where priceScheduleNo = &#63;.
	*
	* @param priceScheduleNo the price schedule no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching price schedule master
	* @throws NoSuchPriceScheduleMasterException if a matching price schedule master could not be found
	*/
	public PriceScheduleMaster findByPriceScheduleNo_Last(
		java.lang.String priceScheduleNo,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws NoSuchPriceScheduleMasterException;

	/**
	* Returns the last price schedule master in the ordered set where priceScheduleNo = &#63;.
	*
	* @param priceScheduleNo the price schedule no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching price schedule master, or <code>null</code> if a matching price schedule master could not be found
	*/
	public PriceScheduleMaster fetchByPriceScheduleNo_Last(
		java.lang.String priceScheduleNo,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleMaster> orderByComparator);

	/**
	* Returns the price schedule masters before and after the current price schedule master in the ordered set where priceScheduleNo = &#63;.
	*
	* @param priceScheduleSystemId the primary key of the current price schedule master
	* @param priceScheduleNo the price schedule no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next price schedule master
	* @throws NoSuchPriceScheduleMasterException if a price schedule master with the primary key could not be found
	*/
	public PriceScheduleMaster[] findByPriceScheduleNo_PrevAndNext(
		int priceScheduleSystemId, java.lang.String priceScheduleNo,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws NoSuchPriceScheduleMasterException;

	/**
	* Removes all the price schedule masters where priceScheduleNo = &#63; from the database.
	*
	* @param priceScheduleNo the price schedule no
	*/
	public void removeByPriceScheduleNo(java.lang.String priceScheduleNo);

	/**
	* Returns the number of price schedule masters where priceScheduleNo = &#63;.
	*
	* @param priceScheduleNo the price schedule no
	* @return the number of matching price schedule masters
	*/
	public int countByPriceScheduleNo(java.lang.String priceScheduleNo);

	/**
	* Returns all the price schedule masters where priceScheduleName = &#63;.
	*
	* @param priceScheduleName the price schedule name
	* @return the matching price schedule masters
	*/
	public java.util.List<PriceScheduleMaster> findByPriceScheduleName(
		java.lang.String priceScheduleName);

	/**
	* Returns a range of all the price schedule masters where priceScheduleName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param priceScheduleName the price schedule name
	* @param start the lower bound of the range of price schedule masters
	* @param end the upper bound of the range of price schedule masters (not inclusive)
	* @return the range of matching price schedule masters
	*/
	public java.util.List<PriceScheduleMaster> findByPriceScheduleName(
		java.lang.String priceScheduleName, int start, int end);

	/**
	* Returns an ordered range of all the price schedule masters where priceScheduleName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param priceScheduleName the price schedule name
	* @param start the lower bound of the range of price schedule masters
	* @param end the upper bound of the range of price schedule masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching price schedule masters
	*/
	public java.util.List<PriceScheduleMaster> findByPriceScheduleName(
		java.lang.String priceScheduleName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleMaster> orderByComparator);

	/**
	* Returns an ordered range of all the price schedule masters where priceScheduleName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param priceScheduleName the price schedule name
	* @param start the lower bound of the range of price schedule masters
	* @param end the upper bound of the range of price schedule masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching price schedule masters
	*/
	public java.util.List<PriceScheduleMaster> findByPriceScheduleName(
		java.lang.String priceScheduleName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first price schedule master in the ordered set where priceScheduleName = &#63;.
	*
	* @param priceScheduleName the price schedule name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching price schedule master
	* @throws NoSuchPriceScheduleMasterException if a matching price schedule master could not be found
	*/
	public PriceScheduleMaster findByPriceScheduleName_First(
		java.lang.String priceScheduleName,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws NoSuchPriceScheduleMasterException;

	/**
	* Returns the first price schedule master in the ordered set where priceScheduleName = &#63;.
	*
	* @param priceScheduleName the price schedule name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching price schedule master, or <code>null</code> if a matching price schedule master could not be found
	*/
	public PriceScheduleMaster fetchByPriceScheduleName_First(
		java.lang.String priceScheduleName,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleMaster> orderByComparator);

	/**
	* Returns the last price schedule master in the ordered set where priceScheduleName = &#63;.
	*
	* @param priceScheduleName the price schedule name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching price schedule master
	* @throws NoSuchPriceScheduleMasterException if a matching price schedule master could not be found
	*/
	public PriceScheduleMaster findByPriceScheduleName_Last(
		java.lang.String priceScheduleName,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws NoSuchPriceScheduleMasterException;

	/**
	* Returns the last price schedule master in the ordered set where priceScheduleName = &#63;.
	*
	* @param priceScheduleName the price schedule name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching price schedule master, or <code>null</code> if a matching price schedule master could not be found
	*/
	public PriceScheduleMaster fetchByPriceScheduleName_Last(
		java.lang.String priceScheduleName,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleMaster> orderByComparator);

	/**
	* Returns the price schedule masters before and after the current price schedule master in the ordered set where priceScheduleName = &#63;.
	*
	* @param priceScheduleSystemId the primary key of the current price schedule master
	* @param priceScheduleName the price schedule name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next price schedule master
	* @throws NoSuchPriceScheduleMasterException if a price schedule master with the primary key could not be found
	*/
	public PriceScheduleMaster[] findByPriceScheduleName_PrevAndNext(
		int priceScheduleSystemId, java.lang.String priceScheduleName,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws NoSuchPriceScheduleMasterException;

	/**
	* Removes all the price schedule masters where priceScheduleName = &#63; from the database.
	*
	* @param priceScheduleName the price schedule name
	*/
	public void removeByPriceScheduleName(java.lang.String priceScheduleName);

	/**
	* Returns the number of price schedule masters where priceScheduleName = &#63;.
	*
	* @param priceScheduleName the price schedule name
	* @return the number of matching price schedule masters
	*/
	public int countByPriceScheduleName(java.lang.String priceScheduleName);

	/**
	* Returns all the price schedule masters where priceScheduleType = &#63;.
	*
	* @param priceScheduleType the price schedule type
	* @return the matching price schedule masters
	*/
	public java.util.List<PriceScheduleMaster> findByPriceScheduleType(
		java.lang.String priceScheduleType);

	/**
	* Returns a range of all the price schedule masters where priceScheduleType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param priceScheduleType the price schedule type
	* @param start the lower bound of the range of price schedule masters
	* @param end the upper bound of the range of price schedule masters (not inclusive)
	* @return the range of matching price schedule masters
	*/
	public java.util.List<PriceScheduleMaster> findByPriceScheduleType(
		java.lang.String priceScheduleType, int start, int end);

	/**
	* Returns an ordered range of all the price schedule masters where priceScheduleType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param priceScheduleType the price schedule type
	* @param start the lower bound of the range of price schedule masters
	* @param end the upper bound of the range of price schedule masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching price schedule masters
	*/
	public java.util.List<PriceScheduleMaster> findByPriceScheduleType(
		java.lang.String priceScheduleType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleMaster> orderByComparator);

	/**
	* Returns an ordered range of all the price schedule masters where priceScheduleType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param priceScheduleType the price schedule type
	* @param start the lower bound of the range of price schedule masters
	* @param end the upper bound of the range of price schedule masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching price schedule masters
	*/
	public java.util.List<PriceScheduleMaster> findByPriceScheduleType(
		java.lang.String priceScheduleType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first price schedule master in the ordered set where priceScheduleType = &#63;.
	*
	* @param priceScheduleType the price schedule type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching price schedule master
	* @throws NoSuchPriceScheduleMasterException if a matching price schedule master could not be found
	*/
	public PriceScheduleMaster findByPriceScheduleType_First(
		java.lang.String priceScheduleType,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws NoSuchPriceScheduleMasterException;

	/**
	* Returns the first price schedule master in the ordered set where priceScheduleType = &#63;.
	*
	* @param priceScheduleType the price schedule type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching price schedule master, or <code>null</code> if a matching price schedule master could not be found
	*/
	public PriceScheduleMaster fetchByPriceScheduleType_First(
		java.lang.String priceScheduleType,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleMaster> orderByComparator);

	/**
	* Returns the last price schedule master in the ordered set where priceScheduleType = &#63;.
	*
	* @param priceScheduleType the price schedule type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching price schedule master
	* @throws NoSuchPriceScheduleMasterException if a matching price schedule master could not be found
	*/
	public PriceScheduleMaster findByPriceScheduleType_Last(
		java.lang.String priceScheduleType,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws NoSuchPriceScheduleMasterException;

	/**
	* Returns the last price schedule master in the ordered set where priceScheduleType = &#63;.
	*
	* @param priceScheduleType the price schedule type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching price schedule master, or <code>null</code> if a matching price schedule master could not be found
	*/
	public PriceScheduleMaster fetchByPriceScheduleType_Last(
		java.lang.String priceScheduleType,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleMaster> orderByComparator);

	/**
	* Returns the price schedule masters before and after the current price schedule master in the ordered set where priceScheduleType = &#63;.
	*
	* @param priceScheduleSystemId the primary key of the current price schedule master
	* @param priceScheduleType the price schedule type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next price schedule master
	* @throws NoSuchPriceScheduleMasterException if a price schedule master with the primary key could not be found
	*/
	public PriceScheduleMaster[] findByPriceScheduleType_PrevAndNext(
		int priceScheduleSystemId, java.lang.String priceScheduleType,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws NoSuchPriceScheduleMasterException;

	/**
	* Removes all the price schedule masters where priceScheduleType = &#63; from the database.
	*
	* @param priceScheduleType the price schedule type
	*/
	public void removeByPriceScheduleType(java.lang.String priceScheduleType);

	/**
	* Returns the number of price schedule masters where priceScheduleType = &#63;.
	*
	* @param priceScheduleType the price schedule type
	* @return the number of matching price schedule masters
	*/
	public int countByPriceScheduleType(java.lang.String priceScheduleType);

	/**
	* Returns all the price schedule masters where priceScheduleStatus = &#63;.
	*
	* @param priceScheduleStatus the price schedule status
	* @return the matching price schedule masters
	*/
	public java.util.List<PriceScheduleMaster> findByPriceScheduleStatus(
		java.lang.String priceScheduleStatus);

	/**
	* Returns a range of all the price schedule masters where priceScheduleStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param priceScheduleStatus the price schedule status
	* @param start the lower bound of the range of price schedule masters
	* @param end the upper bound of the range of price schedule masters (not inclusive)
	* @return the range of matching price schedule masters
	*/
	public java.util.List<PriceScheduleMaster> findByPriceScheduleStatus(
		java.lang.String priceScheduleStatus, int start, int end);

	/**
	* Returns an ordered range of all the price schedule masters where priceScheduleStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param priceScheduleStatus the price schedule status
	* @param start the lower bound of the range of price schedule masters
	* @param end the upper bound of the range of price schedule masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching price schedule masters
	*/
	public java.util.List<PriceScheduleMaster> findByPriceScheduleStatus(
		java.lang.String priceScheduleStatus, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleMaster> orderByComparator);

	/**
	* Returns an ordered range of all the price schedule masters where priceScheduleStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param priceScheduleStatus the price schedule status
	* @param start the lower bound of the range of price schedule masters
	* @param end the upper bound of the range of price schedule masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching price schedule masters
	*/
	public java.util.List<PriceScheduleMaster> findByPriceScheduleStatus(
		java.lang.String priceScheduleStatus, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first price schedule master in the ordered set where priceScheduleStatus = &#63;.
	*
	* @param priceScheduleStatus the price schedule status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching price schedule master
	* @throws NoSuchPriceScheduleMasterException if a matching price schedule master could not be found
	*/
	public PriceScheduleMaster findByPriceScheduleStatus_First(
		java.lang.String priceScheduleStatus,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws NoSuchPriceScheduleMasterException;

	/**
	* Returns the first price schedule master in the ordered set where priceScheduleStatus = &#63;.
	*
	* @param priceScheduleStatus the price schedule status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching price schedule master, or <code>null</code> if a matching price schedule master could not be found
	*/
	public PriceScheduleMaster fetchByPriceScheduleStatus_First(
		java.lang.String priceScheduleStatus,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleMaster> orderByComparator);

	/**
	* Returns the last price schedule master in the ordered set where priceScheduleStatus = &#63;.
	*
	* @param priceScheduleStatus the price schedule status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching price schedule master
	* @throws NoSuchPriceScheduleMasterException if a matching price schedule master could not be found
	*/
	public PriceScheduleMaster findByPriceScheduleStatus_Last(
		java.lang.String priceScheduleStatus,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws NoSuchPriceScheduleMasterException;

	/**
	* Returns the last price schedule master in the ordered set where priceScheduleStatus = &#63;.
	*
	* @param priceScheduleStatus the price schedule status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching price schedule master, or <code>null</code> if a matching price schedule master could not be found
	*/
	public PriceScheduleMaster fetchByPriceScheduleStatus_Last(
		java.lang.String priceScheduleStatus,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleMaster> orderByComparator);

	/**
	* Returns the price schedule masters before and after the current price schedule master in the ordered set where priceScheduleStatus = &#63;.
	*
	* @param priceScheduleSystemId the primary key of the current price schedule master
	* @param priceScheduleStatus the price schedule status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next price schedule master
	* @throws NoSuchPriceScheduleMasterException if a price schedule master with the primary key could not be found
	*/
	public PriceScheduleMaster[] findByPriceScheduleStatus_PrevAndNext(
		int priceScheduleSystemId, java.lang.String priceScheduleStatus,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws NoSuchPriceScheduleMasterException;

	/**
	* Removes all the price schedule masters where priceScheduleStatus = &#63; from the database.
	*
	* @param priceScheduleStatus the price schedule status
	*/
	public void removeByPriceScheduleStatus(
		java.lang.String priceScheduleStatus);

	/**
	* Returns the number of price schedule masters where priceScheduleStatus = &#63;.
	*
	* @param priceScheduleStatus the price schedule status
	* @return the number of matching price schedule masters
	*/
	public int countByPriceScheduleStatus(java.lang.String priceScheduleStatus);

	/**
	* Caches the price schedule master in the entity cache if it is enabled.
	*
	* @param priceScheduleMaster the price schedule master
	*/
	public void cacheResult(PriceScheduleMaster priceScheduleMaster);

	/**
	* Caches the price schedule masters in the entity cache if it is enabled.
	*
	* @param priceScheduleMasters the price schedule masters
	*/
	public void cacheResult(
		java.util.List<PriceScheduleMaster> priceScheduleMasters);

	/**
	* Creates a new price schedule master with the primary key. Does not add the price schedule master to the database.
	*
	* @param priceScheduleSystemId the primary key for the new price schedule master
	* @return the new price schedule master
	*/
	public PriceScheduleMaster create(int priceScheduleSystemId);

	/**
	* Removes the price schedule master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param priceScheduleSystemId the primary key of the price schedule master
	* @return the price schedule master that was removed
	* @throws NoSuchPriceScheduleMasterException if a price schedule master with the primary key could not be found
	*/
	public PriceScheduleMaster remove(int priceScheduleSystemId)
		throws NoSuchPriceScheduleMasterException;

	public PriceScheduleMaster updateImpl(
		PriceScheduleMaster priceScheduleMaster);

	/**
	* Returns the price schedule master with the primary key or throws a {@link NoSuchPriceScheduleMasterException} if it could not be found.
	*
	* @param priceScheduleSystemId the primary key of the price schedule master
	* @return the price schedule master
	* @throws NoSuchPriceScheduleMasterException if a price schedule master with the primary key could not be found
	*/
	public PriceScheduleMaster findByPrimaryKey(int priceScheduleSystemId)
		throws NoSuchPriceScheduleMasterException;

	/**
	* Returns the price schedule master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param priceScheduleSystemId the primary key of the price schedule master
	* @return the price schedule master, or <code>null</code> if a price schedule master with the primary key could not be found
	*/
	public PriceScheduleMaster fetchByPrimaryKey(int priceScheduleSystemId);

	@Override
	public java.util.Map<java.io.Serializable, PriceScheduleMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the price schedule masters.
	*
	* @return the price schedule masters
	*/
	public java.util.List<PriceScheduleMaster> findAll();

	/**
	* Returns a range of all the price schedule masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of price schedule masters
	* @param end the upper bound of the range of price schedule masters (not inclusive)
	* @return the range of price schedule masters
	*/
	public java.util.List<PriceScheduleMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the price schedule masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of price schedule masters
	* @param end the upper bound of the range of price schedule masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of price schedule masters
	*/
	public java.util.List<PriceScheduleMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleMaster> orderByComparator);

	/**
	* Returns an ordered range of all the price schedule masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of price schedule masters
	* @param end the upper bound of the range of price schedule masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of price schedule masters
	*/
	public java.util.List<PriceScheduleMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the price schedule masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of price schedule masters.
	*
	* @return the number of price schedule masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}