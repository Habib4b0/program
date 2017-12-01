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

import com.stpl.app.exception.NoSuchSalesMasterException;
import com.stpl.app.model.SalesMaster;

/**
 * The persistence interface for the sales master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.SalesMasterPersistenceImpl
 * @see SalesMasterUtil
 * @generated
 */
@ProviderType
public interface SalesMasterPersistence extends BasePersistence<SalesMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SalesMasterUtil} to access the sales master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the sales masters where accountId = &#63;.
	*
	* @param accountId the account ID
	* @return the matching sales masters
	*/
	public java.util.List<SalesMaster> findByAccountId(
		java.lang.String accountId);

	/**
	* Returns a range of all the sales masters where accountId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountId the account ID
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @return the range of matching sales masters
	*/
	public java.util.List<SalesMaster> findByAccountId(
		java.lang.String accountId, int start, int end);

	/**
	* Returns an ordered range of all the sales masters where accountId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountId the account ID
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sales masters
	*/
	public java.util.List<SalesMaster> findByAccountId(
		java.lang.String accountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator);

	/**
	* Returns an ordered range of all the sales masters where accountId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountId the account ID
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sales masters
	*/
	public java.util.List<SalesMaster> findByAccountId(
		java.lang.String accountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first sales master in the ordered set where accountId = &#63;.
	*
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sales master
	* @throws NoSuchSalesMasterException if a matching sales master could not be found
	*/
	public SalesMaster findByAccountId_First(java.lang.String accountId,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator)
		throws NoSuchSalesMasterException;

	/**
	* Returns the first sales master in the ordered set where accountId = &#63;.
	*
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
	*/
	public SalesMaster fetchByAccountId_First(java.lang.String accountId,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator);

	/**
	* Returns the last sales master in the ordered set where accountId = &#63;.
	*
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sales master
	* @throws NoSuchSalesMasterException if a matching sales master could not be found
	*/
	public SalesMaster findByAccountId_Last(java.lang.String accountId,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator)
		throws NoSuchSalesMasterException;

	/**
	* Returns the last sales master in the ordered set where accountId = &#63;.
	*
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
	*/
	public SalesMaster fetchByAccountId_Last(java.lang.String accountId,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator);

	/**
	* Returns the sales masters before and after the current sales master in the ordered set where accountId = &#63;.
	*
	* @param salesMasterSid the primary key of the current sales master
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sales master
	* @throws NoSuchSalesMasterException if a sales master with the primary key could not be found
	*/
	public SalesMaster[] findByAccountId_PrevAndNext(int salesMasterSid,
		java.lang.String accountId,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator)
		throws NoSuchSalesMasterException;

	/**
	* Removes all the sales masters where accountId = &#63; from the database.
	*
	* @param accountId the account ID
	*/
	public void removeByAccountId(java.lang.String accountId);

	/**
	* Returns the number of sales masters where accountId = &#63;.
	*
	* @param accountId the account ID
	* @return the number of matching sales masters
	*/
	public int countByAccountId(java.lang.String accountId);

	/**
	* Returns all the sales masters where itemNo = &#63;.
	*
	* @param itemNo the item no
	* @return the matching sales masters
	*/
	public java.util.List<SalesMaster> findByItemNo(java.lang.String itemNo);

	/**
	* Returns a range of all the sales masters where itemNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemNo the item no
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @return the range of matching sales masters
	*/
	public java.util.List<SalesMaster> findByItemNo(java.lang.String itemNo,
		int start, int end);

	/**
	* Returns an ordered range of all the sales masters where itemNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemNo the item no
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sales masters
	*/
	public java.util.List<SalesMaster> findByItemNo(java.lang.String itemNo,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator);

	/**
	* Returns an ordered range of all the sales masters where itemNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemNo the item no
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sales masters
	*/
	public java.util.List<SalesMaster> findByItemNo(java.lang.String itemNo,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first sales master in the ordered set where itemNo = &#63;.
	*
	* @param itemNo the item no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sales master
	* @throws NoSuchSalesMasterException if a matching sales master could not be found
	*/
	public SalesMaster findByItemNo_First(java.lang.String itemNo,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator)
		throws NoSuchSalesMasterException;

	/**
	* Returns the first sales master in the ordered set where itemNo = &#63;.
	*
	* @param itemNo the item no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
	*/
	public SalesMaster fetchByItemNo_First(java.lang.String itemNo,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator);

	/**
	* Returns the last sales master in the ordered set where itemNo = &#63;.
	*
	* @param itemNo the item no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sales master
	* @throws NoSuchSalesMasterException if a matching sales master could not be found
	*/
	public SalesMaster findByItemNo_Last(java.lang.String itemNo,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator)
		throws NoSuchSalesMasterException;

	/**
	* Returns the last sales master in the ordered set where itemNo = &#63;.
	*
	* @param itemNo the item no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
	*/
	public SalesMaster fetchByItemNo_Last(java.lang.String itemNo,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator);

	/**
	* Returns the sales masters before and after the current sales master in the ordered set where itemNo = &#63;.
	*
	* @param salesMasterSid the primary key of the current sales master
	* @param itemNo the item no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sales master
	* @throws NoSuchSalesMasterException if a sales master with the primary key could not be found
	*/
	public SalesMaster[] findByItemNo_PrevAndNext(int salesMasterSid,
		java.lang.String itemNo,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator)
		throws NoSuchSalesMasterException;

	/**
	* Removes all the sales masters where itemNo = &#63; from the database.
	*
	* @param itemNo the item no
	*/
	public void removeByItemNo(java.lang.String itemNo);

	/**
	* Returns the number of sales masters where itemNo = &#63;.
	*
	* @param itemNo the item no
	* @return the number of matching sales masters
	*/
	public int countByItemNo(java.lang.String itemNo);

	/**
	* Returns all the sales masters where itemId = &#63;.
	*
	* @param itemId the item ID
	* @return the matching sales masters
	*/
	public java.util.List<SalesMaster> findByItemId(java.lang.String itemId);

	/**
	* Returns a range of all the sales masters where itemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemId the item ID
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @return the range of matching sales masters
	*/
	public java.util.List<SalesMaster> findByItemId(java.lang.String itemId,
		int start, int end);

	/**
	* Returns an ordered range of all the sales masters where itemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemId the item ID
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sales masters
	*/
	public java.util.List<SalesMaster> findByItemId(java.lang.String itemId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator);

	/**
	* Returns an ordered range of all the sales masters where itemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemId the item ID
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sales masters
	*/
	public java.util.List<SalesMaster> findByItemId(java.lang.String itemId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first sales master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sales master
	* @throws NoSuchSalesMasterException if a matching sales master could not be found
	*/
	public SalesMaster findByItemId_First(java.lang.String itemId,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator)
		throws NoSuchSalesMasterException;

	/**
	* Returns the first sales master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
	*/
	public SalesMaster fetchByItemId_First(java.lang.String itemId,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator);

	/**
	* Returns the last sales master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sales master
	* @throws NoSuchSalesMasterException if a matching sales master could not be found
	*/
	public SalesMaster findByItemId_Last(java.lang.String itemId,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator)
		throws NoSuchSalesMasterException;

	/**
	* Returns the last sales master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
	*/
	public SalesMaster fetchByItemId_Last(java.lang.String itemId,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator);

	/**
	* Returns the sales masters before and after the current sales master in the ordered set where itemId = &#63;.
	*
	* @param salesMasterSid the primary key of the current sales master
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sales master
	* @throws NoSuchSalesMasterException if a sales master with the primary key could not be found
	*/
	public SalesMaster[] findByItemId_PrevAndNext(int salesMasterSid,
		java.lang.String itemId,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator)
		throws NoSuchSalesMasterException;

	/**
	* Removes all the sales masters where itemId = &#63; from the database.
	*
	* @param itemId the item ID
	*/
	public void removeByItemId(java.lang.String itemId);

	/**
	* Returns the number of sales masters where itemId = &#63;.
	*
	* @param itemId the item ID
	* @return the number of matching sales masters
	*/
	public int countByItemId(java.lang.String itemId);

	/**
	* Returns all the sales masters where salesId = &#63;.
	*
	* @param salesId the sales ID
	* @return the matching sales masters
	*/
	public java.util.List<SalesMaster> findBySalesId(java.lang.String salesId);

	/**
	* Returns a range of all the sales masters where salesId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param salesId the sales ID
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @return the range of matching sales masters
	*/
	public java.util.List<SalesMaster> findBySalesId(java.lang.String salesId,
		int start, int end);

	/**
	* Returns an ordered range of all the sales masters where salesId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param salesId the sales ID
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sales masters
	*/
	public java.util.List<SalesMaster> findBySalesId(java.lang.String salesId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator);

	/**
	* Returns an ordered range of all the sales masters where salesId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param salesId the sales ID
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sales masters
	*/
	public java.util.List<SalesMaster> findBySalesId(java.lang.String salesId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first sales master in the ordered set where salesId = &#63;.
	*
	* @param salesId the sales ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sales master
	* @throws NoSuchSalesMasterException if a matching sales master could not be found
	*/
	public SalesMaster findBySalesId_First(java.lang.String salesId,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator)
		throws NoSuchSalesMasterException;

	/**
	* Returns the first sales master in the ordered set where salesId = &#63;.
	*
	* @param salesId the sales ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
	*/
	public SalesMaster fetchBySalesId_First(java.lang.String salesId,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator);

	/**
	* Returns the last sales master in the ordered set where salesId = &#63;.
	*
	* @param salesId the sales ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sales master
	* @throws NoSuchSalesMasterException if a matching sales master could not be found
	*/
	public SalesMaster findBySalesId_Last(java.lang.String salesId,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator)
		throws NoSuchSalesMasterException;

	/**
	* Returns the last sales master in the ordered set where salesId = &#63;.
	*
	* @param salesId the sales ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
	*/
	public SalesMaster fetchBySalesId_Last(java.lang.String salesId,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator);

	/**
	* Returns the sales masters before and after the current sales master in the ordered set where salesId = &#63;.
	*
	* @param salesMasterSid the primary key of the current sales master
	* @param salesId the sales ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sales master
	* @throws NoSuchSalesMasterException if a sales master with the primary key could not be found
	*/
	public SalesMaster[] findBySalesId_PrevAndNext(int salesMasterSid,
		java.lang.String salesId,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator)
		throws NoSuchSalesMasterException;

	/**
	* Removes all the sales masters where salesId = &#63; from the database.
	*
	* @param salesId the sales ID
	*/
	public void removeBySalesId(java.lang.String salesId);

	/**
	* Returns the number of sales masters where salesId = &#63;.
	*
	* @param salesId the sales ID
	* @return the number of matching sales masters
	*/
	public int countBySalesId(java.lang.String salesId);

	/**
	* Returns all the sales masters where accountNo = &#63;.
	*
	* @param accountNo the account no
	* @return the matching sales masters
	*/
	public java.util.List<SalesMaster> findByAccountNo(
		java.lang.String accountNo);

	/**
	* Returns a range of all the sales masters where accountNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountNo the account no
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @return the range of matching sales masters
	*/
	public java.util.List<SalesMaster> findByAccountNo(
		java.lang.String accountNo, int start, int end);

	/**
	* Returns an ordered range of all the sales masters where accountNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountNo the account no
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sales masters
	*/
	public java.util.List<SalesMaster> findByAccountNo(
		java.lang.String accountNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator);

	/**
	* Returns an ordered range of all the sales masters where accountNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountNo the account no
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sales masters
	*/
	public java.util.List<SalesMaster> findByAccountNo(
		java.lang.String accountNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first sales master in the ordered set where accountNo = &#63;.
	*
	* @param accountNo the account no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sales master
	* @throws NoSuchSalesMasterException if a matching sales master could not be found
	*/
	public SalesMaster findByAccountNo_First(java.lang.String accountNo,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator)
		throws NoSuchSalesMasterException;

	/**
	* Returns the first sales master in the ordered set where accountNo = &#63;.
	*
	* @param accountNo the account no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
	*/
	public SalesMaster fetchByAccountNo_First(java.lang.String accountNo,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator);

	/**
	* Returns the last sales master in the ordered set where accountNo = &#63;.
	*
	* @param accountNo the account no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sales master
	* @throws NoSuchSalesMasterException if a matching sales master could not be found
	*/
	public SalesMaster findByAccountNo_Last(java.lang.String accountNo,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator)
		throws NoSuchSalesMasterException;

	/**
	* Returns the last sales master in the ordered set where accountNo = &#63;.
	*
	* @param accountNo the account no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
	*/
	public SalesMaster fetchByAccountNo_Last(java.lang.String accountNo,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator);

	/**
	* Returns the sales masters before and after the current sales master in the ordered set where accountNo = &#63;.
	*
	* @param salesMasterSid the primary key of the current sales master
	* @param accountNo the account no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sales master
	* @throws NoSuchSalesMasterException if a sales master with the primary key could not be found
	*/
	public SalesMaster[] findByAccountNo_PrevAndNext(int salesMasterSid,
		java.lang.String accountNo,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator)
		throws NoSuchSalesMasterException;

	/**
	* Removes all the sales masters where accountNo = &#63; from the database.
	*
	* @param accountNo the account no
	*/
	public void removeByAccountNo(java.lang.String accountNo);

	/**
	* Returns the number of sales masters where accountNo = &#63;.
	*
	* @param accountNo the account no
	* @return the number of matching sales masters
	*/
	public int countByAccountNo(java.lang.String accountNo);

	/**
	* Returns all the sales masters where contractId = &#63;.
	*
	* @param contractId the contract ID
	* @return the matching sales masters
	*/
	public java.util.List<SalesMaster> findByContractId(
		java.lang.String contractId);

	/**
	* Returns a range of all the sales masters where contractId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contractId the contract ID
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @return the range of matching sales masters
	*/
	public java.util.List<SalesMaster> findByContractId(
		java.lang.String contractId, int start, int end);

	/**
	* Returns an ordered range of all the sales masters where contractId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contractId the contract ID
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sales masters
	*/
	public java.util.List<SalesMaster> findByContractId(
		java.lang.String contractId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator);

	/**
	* Returns an ordered range of all the sales masters where contractId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contractId the contract ID
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sales masters
	*/
	public java.util.List<SalesMaster> findByContractId(
		java.lang.String contractId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first sales master in the ordered set where contractId = &#63;.
	*
	* @param contractId the contract ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sales master
	* @throws NoSuchSalesMasterException if a matching sales master could not be found
	*/
	public SalesMaster findByContractId_First(java.lang.String contractId,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator)
		throws NoSuchSalesMasterException;

	/**
	* Returns the first sales master in the ordered set where contractId = &#63;.
	*
	* @param contractId the contract ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
	*/
	public SalesMaster fetchByContractId_First(java.lang.String contractId,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator);

	/**
	* Returns the last sales master in the ordered set where contractId = &#63;.
	*
	* @param contractId the contract ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sales master
	* @throws NoSuchSalesMasterException if a matching sales master could not be found
	*/
	public SalesMaster findByContractId_Last(java.lang.String contractId,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator)
		throws NoSuchSalesMasterException;

	/**
	* Returns the last sales master in the ordered set where contractId = &#63;.
	*
	* @param contractId the contract ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
	*/
	public SalesMaster fetchByContractId_Last(java.lang.String contractId,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator);

	/**
	* Returns the sales masters before and after the current sales master in the ordered set where contractId = &#63;.
	*
	* @param salesMasterSid the primary key of the current sales master
	* @param contractId the contract ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sales master
	* @throws NoSuchSalesMasterException if a sales master with the primary key could not be found
	*/
	public SalesMaster[] findByContractId_PrevAndNext(int salesMasterSid,
		java.lang.String contractId,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator)
		throws NoSuchSalesMasterException;

	/**
	* Removes all the sales masters where contractId = &#63; from the database.
	*
	* @param contractId the contract ID
	*/
	public void removeByContractId(java.lang.String contractId);

	/**
	* Returns the number of sales masters where contractId = &#63;.
	*
	* @param contractId the contract ID
	* @return the number of matching sales masters
	*/
	public int countByContractId(java.lang.String contractId);

	/**
	* Returns all the sales masters where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @return the matching sales masters
	*/
	public java.util.List<SalesMaster> findByCompanyId(
		java.lang.String companyStringId);

	/**
	* Returns a range of all the sales masters where companyStringId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyStringId the company string ID
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @return the range of matching sales masters
	*/
	public java.util.List<SalesMaster> findByCompanyId(
		java.lang.String companyStringId, int start, int end);

	/**
	* Returns an ordered range of all the sales masters where companyStringId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyStringId the company string ID
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sales masters
	*/
	public java.util.List<SalesMaster> findByCompanyId(
		java.lang.String companyStringId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator);

	/**
	* Returns an ordered range of all the sales masters where companyStringId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyStringId the company string ID
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sales masters
	*/
	public java.util.List<SalesMaster> findByCompanyId(
		java.lang.String companyStringId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first sales master in the ordered set where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sales master
	* @throws NoSuchSalesMasterException if a matching sales master could not be found
	*/
	public SalesMaster findByCompanyId_First(java.lang.String companyStringId,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator)
		throws NoSuchSalesMasterException;

	/**
	* Returns the first sales master in the ordered set where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
	*/
	public SalesMaster fetchByCompanyId_First(
		java.lang.String companyStringId,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator);

	/**
	* Returns the last sales master in the ordered set where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sales master
	* @throws NoSuchSalesMasterException if a matching sales master could not be found
	*/
	public SalesMaster findByCompanyId_Last(java.lang.String companyStringId,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator)
		throws NoSuchSalesMasterException;

	/**
	* Returns the last sales master in the ordered set where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
	*/
	public SalesMaster fetchByCompanyId_Last(java.lang.String companyStringId,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator);

	/**
	* Returns the sales masters before and after the current sales master in the ordered set where companyStringId = &#63;.
	*
	* @param salesMasterSid the primary key of the current sales master
	* @param companyStringId the company string ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sales master
	* @throws NoSuchSalesMasterException if a sales master with the primary key could not be found
	*/
	public SalesMaster[] findByCompanyId_PrevAndNext(int salesMasterSid,
		java.lang.String companyStringId,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator)
		throws NoSuchSalesMasterException;

	/**
	* Removes all the sales masters where companyStringId = &#63; from the database.
	*
	* @param companyStringId the company string ID
	*/
	public void removeByCompanyId(java.lang.String companyStringId);

	/**
	* Returns the number of sales masters where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @return the number of matching sales masters
	*/
	public int countByCompanyId(java.lang.String companyStringId);

	/**
	* Returns all the sales masters where contractNo = &#63;.
	*
	* @param contractNo the contract no
	* @return the matching sales masters
	*/
	public java.util.List<SalesMaster> findByContractNo(
		java.lang.String contractNo);

	/**
	* Returns a range of all the sales masters where contractNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contractNo the contract no
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @return the range of matching sales masters
	*/
	public java.util.List<SalesMaster> findByContractNo(
		java.lang.String contractNo, int start, int end);

	/**
	* Returns an ordered range of all the sales masters where contractNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contractNo the contract no
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sales masters
	*/
	public java.util.List<SalesMaster> findByContractNo(
		java.lang.String contractNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator);

	/**
	* Returns an ordered range of all the sales masters where contractNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contractNo the contract no
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sales masters
	*/
	public java.util.List<SalesMaster> findByContractNo(
		java.lang.String contractNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first sales master in the ordered set where contractNo = &#63;.
	*
	* @param contractNo the contract no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sales master
	* @throws NoSuchSalesMasterException if a matching sales master could not be found
	*/
	public SalesMaster findByContractNo_First(java.lang.String contractNo,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator)
		throws NoSuchSalesMasterException;

	/**
	* Returns the first sales master in the ordered set where contractNo = &#63;.
	*
	* @param contractNo the contract no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
	*/
	public SalesMaster fetchByContractNo_First(java.lang.String contractNo,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator);

	/**
	* Returns the last sales master in the ordered set where contractNo = &#63;.
	*
	* @param contractNo the contract no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sales master
	* @throws NoSuchSalesMasterException if a matching sales master could not be found
	*/
	public SalesMaster findByContractNo_Last(java.lang.String contractNo,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator)
		throws NoSuchSalesMasterException;

	/**
	* Returns the last sales master in the ordered set where contractNo = &#63;.
	*
	* @param contractNo the contract no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
	*/
	public SalesMaster fetchByContractNo_Last(java.lang.String contractNo,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator);

	/**
	* Returns the sales masters before and after the current sales master in the ordered set where contractNo = &#63;.
	*
	* @param salesMasterSid the primary key of the current sales master
	* @param contractNo the contract no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sales master
	* @throws NoSuchSalesMasterException if a sales master with the primary key could not be found
	*/
	public SalesMaster[] findByContractNo_PrevAndNext(int salesMasterSid,
		java.lang.String contractNo,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator)
		throws NoSuchSalesMasterException;

	/**
	* Removes all the sales masters where contractNo = &#63; from the database.
	*
	* @param contractNo the contract no
	*/
	public void removeByContractNo(java.lang.String contractNo);

	/**
	* Returns the number of sales masters where contractNo = &#63;.
	*
	* @param contractNo the contract no
	* @return the number of matching sales masters
	*/
	public int countByContractNo(java.lang.String contractNo);

	/**
	* Returns all the sales masters where salesId = &#63;.
	*
	* @param salesId the sales ID
	* @return the matching sales masters
	*/
	public java.util.List<SalesMaster> findBySalesUnique(
		java.lang.String salesId);

	/**
	* Returns a range of all the sales masters where salesId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param salesId the sales ID
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @return the range of matching sales masters
	*/
	public java.util.List<SalesMaster> findBySalesUnique(
		java.lang.String salesId, int start, int end);

	/**
	* Returns an ordered range of all the sales masters where salesId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param salesId the sales ID
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sales masters
	*/
	public java.util.List<SalesMaster> findBySalesUnique(
		java.lang.String salesId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator);

	/**
	* Returns an ordered range of all the sales masters where salesId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param salesId the sales ID
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sales masters
	*/
	public java.util.List<SalesMaster> findBySalesUnique(
		java.lang.String salesId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first sales master in the ordered set where salesId = &#63;.
	*
	* @param salesId the sales ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sales master
	* @throws NoSuchSalesMasterException if a matching sales master could not be found
	*/
	public SalesMaster findBySalesUnique_First(java.lang.String salesId,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator)
		throws NoSuchSalesMasterException;

	/**
	* Returns the first sales master in the ordered set where salesId = &#63;.
	*
	* @param salesId the sales ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
	*/
	public SalesMaster fetchBySalesUnique_First(java.lang.String salesId,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator);

	/**
	* Returns the last sales master in the ordered set where salesId = &#63;.
	*
	* @param salesId the sales ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sales master
	* @throws NoSuchSalesMasterException if a matching sales master could not be found
	*/
	public SalesMaster findBySalesUnique_Last(java.lang.String salesId,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator)
		throws NoSuchSalesMasterException;

	/**
	* Returns the last sales master in the ordered set where salesId = &#63;.
	*
	* @param salesId the sales ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
	*/
	public SalesMaster fetchBySalesUnique_Last(java.lang.String salesId,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator);

	/**
	* Returns the sales masters before and after the current sales master in the ordered set where salesId = &#63;.
	*
	* @param salesMasterSid the primary key of the current sales master
	* @param salesId the sales ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sales master
	* @throws NoSuchSalesMasterException if a sales master with the primary key could not be found
	*/
	public SalesMaster[] findBySalesUnique_PrevAndNext(int salesMasterSid,
		java.lang.String salesId,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator)
		throws NoSuchSalesMasterException;

	/**
	* Removes all the sales masters where salesId = &#63; from the database.
	*
	* @param salesId the sales ID
	*/
	public void removeBySalesUnique(java.lang.String salesId);

	/**
	* Returns the number of sales masters where salesId = &#63;.
	*
	* @param salesId the sales ID
	* @return the number of matching sales masters
	*/
	public int countBySalesUnique(java.lang.String salesId);

	/**
	* Caches the sales master in the entity cache if it is enabled.
	*
	* @param salesMaster the sales master
	*/
	public void cacheResult(SalesMaster salesMaster);

	/**
	* Caches the sales masters in the entity cache if it is enabled.
	*
	* @param salesMasters the sales masters
	*/
	public void cacheResult(java.util.List<SalesMaster> salesMasters);

	/**
	* Creates a new sales master with the primary key. Does not add the sales master to the database.
	*
	* @param salesMasterSid the primary key for the new sales master
	* @return the new sales master
	*/
	public SalesMaster create(int salesMasterSid);

	/**
	* Removes the sales master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param salesMasterSid the primary key of the sales master
	* @return the sales master that was removed
	* @throws NoSuchSalesMasterException if a sales master with the primary key could not be found
	*/
	public SalesMaster remove(int salesMasterSid)
		throws NoSuchSalesMasterException;

	public SalesMaster updateImpl(SalesMaster salesMaster);

	/**
	* Returns the sales master with the primary key or throws a {@link NoSuchSalesMasterException} if it could not be found.
	*
	* @param salesMasterSid the primary key of the sales master
	* @return the sales master
	* @throws NoSuchSalesMasterException if a sales master with the primary key could not be found
	*/
	public SalesMaster findByPrimaryKey(int salesMasterSid)
		throws NoSuchSalesMasterException;

	/**
	* Returns the sales master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param salesMasterSid the primary key of the sales master
	* @return the sales master, or <code>null</code> if a sales master with the primary key could not be found
	*/
	public SalesMaster fetchByPrimaryKey(int salesMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, SalesMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the sales masters.
	*
	* @return the sales masters
	*/
	public java.util.List<SalesMaster> findAll();

	/**
	* Returns a range of all the sales masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @return the range of sales masters
	*/
	public java.util.List<SalesMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the sales masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of sales masters
	*/
	public java.util.List<SalesMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator);

	/**
	* Returns an ordered range of all the sales masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of sales masters
	*/
	public java.util.List<SalesMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SalesMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the sales masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of sales masters.
	*
	* @return the number of sales masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}