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

import com.stpl.app.exception.NoSuchBestPriceMasterException;
import com.stpl.app.model.BestPriceMaster;

/**
 * The persistence interface for the best price master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.BestPriceMasterPersistenceImpl
 * @see BestPriceMasterUtil
 * @generated
 */
@ProviderType
public interface BestPriceMasterPersistence extends BasePersistence<BestPriceMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link BestPriceMasterUtil} to access the best price master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the best price masters where itemId = &#63;.
	*
	* @param itemId the item ID
	* @return the matching best price masters
	*/
	public java.util.List<BestPriceMaster> findByItemId(java.lang.String itemId);

	/**
	* Returns a range of all the best price masters where itemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemId the item ID
	* @param start the lower bound of the range of best price masters
	* @param end the upper bound of the range of best price masters (not inclusive)
	* @return the range of matching best price masters
	*/
	public java.util.List<BestPriceMaster> findByItemId(
		java.lang.String itemId, int start, int end);

	/**
	* Returns an ordered range of all the best price masters where itemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemId the item ID
	* @param start the lower bound of the range of best price masters
	* @param end the upper bound of the range of best price masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching best price masters
	*/
	public java.util.List<BestPriceMaster> findByItemId(
		java.lang.String itemId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator);

	/**
	* Returns an ordered range of all the best price masters where itemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemId the item ID
	* @param start the lower bound of the range of best price masters
	* @param end the upper bound of the range of best price masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching best price masters
	*/
	public java.util.List<BestPriceMaster> findByItemId(
		java.lang.String itemId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first best price master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching best price master
	* @throws NoSuchBestPriceMasterException if a matching best price master could not be found
	*/
	public BestPriceMaster findByItemId_First(java.lang.String itemId,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator)
		throws NoSuchBestPriceMasterException;

	/**
	* Returns the first best price master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching best price master, or <code>null</code> if a matching best price master could not be found
	*/
	public BestPriceMaster fetchByItemId_First(java.lang.String itemId,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator);

	/**
	* Returns the last best price master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching best price master
	* @throws NoSuchBestPriceMasterException if a matching best price master could not be found
	*/
	public BestPriceMaster findByItemId_Last(java.lang.String itemId,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator)
		throws NoSuchBestPriceMasterException;

	/**
	* Returns the last best price master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching best price master, or <code>null</code> if a matching best price master could not be found
	*/
	public BestPriceMaster fetchByItemId_Last(java.lang.String itemId,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator);

	/**
	* Returns the best price masters before and after the current best price master in the ordered set where itemId = &#63;.
	*
	* @param bestPriceMasterSid the primary key of the current best price master
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next best price master
	* @throws NoSuchBestPriceMasterException if a best price master with the primary key could not be found
	*/
	public BestPriceMaster[] findByItemId_PrevAndNext(int bestPriceMasterSid,
		java.lang.String itemId,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator)
		throws NoSuchBestPriceMasterException;

	/**
	* Removes all the best price masters where itemId = &#63; from the database.
	*
	* @param itemId the item ID
	*/
	public void removeByItemId(java.lang.String itemId);

	/**
	* Returns the number of best price masters where itemId = &#63;.
	*
	* @param itemId the item ID
	* @return the number of matching best price masters
	*/
	public int countByItemId(java.lang.String itemId);

	/**
	* Returns all the best price masters where itemNo = &#63;.
	*
	* @param itemNo the item no
	* @return the matching best price masters
	*/
	public java.util.List<BestPriceMaster> findByItemNo(java.lang.String itemNo);

	/**
	* Returns a range of all the best price masters where itemNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemNo the item no
	* @param start the lower bound of the range of best price masters
	* @param end the upper bound of the range of best price masters (not inclusive)
	* @return the range of matching best price masters
	*/
	public java.util.List<BestPriceMaster> findByItemNo(
		java.lang.String itemNo, int start, int end);

	/**
	* Returns an ordered range of all the best price masters where itemNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemNo the item no
	* @param start the lower bound of the range of best price masters
	* @param end the upper bound of the range of best price masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching best price masters
	*/
	public java.util.List<BestPriceMaster> findByItemNo(
		java.lang.String itemNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator);

	/**
	* Returns an ordered range of all the best price masters where itemNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemNo the item no
	* @param start the lower bound of the range of best price masters
	* @param end the upper bound of the range of best price masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching best price masters
	*/
	public java.util.List<BestPriceMaster> findByItemNo(
		java.lang.String itemNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first best price master in the ordered set where itemNo = &#63;.
	*
	* @param itemNo the item no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching best price master
	* @throws NoSuchBestPriceMasterException if a matching best price master could not be found
	*/
	public BestPriceMaster findByItemNo_First(java.lang.String itemNo,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator)
		throws NoSuchBestPriceMasterException;

	/**
	* Returns the first best price master in the ordered set where itemNo = &#63;.
	*
	* @param itemNo the item no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching best price master, or <code>null</code> if a matching best price master could not be found
	*/
	public BestPriceMaster fetchByItemNo_First(java.lang.String itemNo,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator);

	/**
	* Returns the last best price master in the ordered set where itemNo = &#63;.
	*
	* @param itemNo the item no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching best price master
	* @throws NoSuchBestPriceMasterException if a matching best price master could not be found
	*/
	public BestPriceMaster findByItemNo_Last(java.lang.String itemNo,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator)
		throws NoSuchBestPriceMasterException;

	/**
	* Returns the last best price master in the ordered set where itemNo = &#63;.
	*
	* @param itemNo the item no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching best price master, or <code>null</code> if a matching best price master could not be found
	*/
	public BestPriceMaster fetchByItemNo_Last(java.lang.String itemNo,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator);

	/**
	* Returns the best price masters before and after the current best price master in the ordered set where itemNo = &#63;.
	*
	* @param bestPriceMasterSid the primary key of the current best price master
	* @param itemNo the item no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next best price master
	* @throws NoSuchBestPriceMasterException if a best price master with the primary key could not be found
	*/
	public BestPriceMaster[] findByItemNo_PrevAndNext(int bestPriceMasterSid,
		java.lang.String itemNo,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator)
		throws NoSuchBestPriceMasterException;

	/**
	* Removes all the best price masters where itemNo = &#63; from the database.
	*
	* @param itemNo the item no
	*/
	public void removeByItemNo(java.lang.String itemNo);

	/**
	* Returns the number of best price masters where itemNo = &#63;.
	*
	* @param itemNo the item no
	* @return the number of matching best price masters
	*/
	public int countByItemNo(java.lang.String itemNo);

	/**
	* Returns all the best price masters where contractNo = &#63;.
	*
	* @param contractNo the contract no
	* @return the matching best price masters
	*/
	public java.util.List<BestPriceMaster> findByContractNo(
		java.lang.String contractNo);

	/**
	* Returns a range of all the best price masters where contractNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contractNo the contract no
	* @param start the lower bound of the range of best price masters
	* @param end the upper bound of the range of best price masters (not inclusive)
	* @return the range of matching best price masters
	*/
	public java.util.List<BestPriceMaster> findByContractNo(
		java.lang.String contractNo, int start, int end);

	/**
	* Returns an ordered range of all the best price masters where contractNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contractNo the contract no
	* @param start the lower bound of the range of best price masters
	* @param end the upper bound of the range of best price masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching best price masters
	*/
	public java.util.List<BestPriceMaster> findByContractNo(
		java.lang.String contractNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator);

	/**
	* Returns an ordered range of all the best price masters where contractNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contractNo the contract no
	* @param start the lower bound of the range of best price masters
	* @param end the upper bound of the range of best price masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching best price masters
	*/
	public java.util.List<BestPriceMaster> findByContractNo(
		java.lang.String contractNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first best price master in the ordered set where contractNo = &#63;.
	*
	* @param contractNo the contract no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching best price master
	* @throws NoSuchBestPriceMasterException if a matching best price master could not be found
	*/
	public BestPriceMaster findByContractNo_First(java.lang.String contractNo,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator)
		throws NoSuchBestPriceMasterException;

	/**
	* Returns the first best price master in the ordered set where contractNo = &#63;.
	*
	* @param contractNo the contract no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching best price master, or <code>null</code> if a matching best price master could not be found
	*/
	public BestPriceMaster fetchByContractNo_First(
		java.lang.String contractNo,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator);

	/**
	* Returns the last best price master in the ordered set where contractNo = &#63;.
	*
	* @param contractNo the contract no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching best price master
	* @throws NoSuchBestPriceMasterException if a matching best price master could not be found
	*/
	public BestPriceMaster findByContractNo_Last(java.lang.String contractNo,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator)
		throws NoSuchBestPriceMasterException;

	/**
	* Returns the last best price master in the ordered set where contractNo = &#63;.
	*
	* @param contractNo the contract no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching best price master, or <code>null</code> if a matching best price master could not be found
	*/
	public BestPriceMaster fetchByContractNo_Last(java.lang.String contractNo,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator);

	/**
	* Returns the best price masters before and after the current best price master in the ordered set where contractNo = &#63;.
	*
	* @param bestPriceMasterSid the primary key of the current best price master
	* @param contractNo the contract no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next best price master
	* @throws NoSuchBestPriceMasterException if a best price master with the primary key could not be found
	*/
	public BestPriceMaster[] findByContractNo_PrevAndNext(
		int bestPriceMasterSid, java.lang.String contractNo,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator)
		throws NoSuchBestPriceMasterException;

	/**
	* Removes all the best price masters where contractNo = &#63; from the database.
	*
	* @param contractNo the contract no
	*/
	public void removeByContractNo(java.lang.String contractNo);

	/**
	* Returns the number of best price masters where contractNo = &#63;.
	*
	* @param contractNo the contract no
	* @return the number of matching best price masters
	*/
	public int countByContractNo(java.lang.String contractNo);

	/**
	* Returns all the best price masters where contractId = &#63;.
	*
	* @param contractId the contract ID
	* @return the matching best price masters
	*/
	public java.util.List<BestPriceMaster> findByContractId(
		java.lang.String contractId);

	/**
	* Returns a range of all the best price masters where contractId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contractId the contract ID
	* @param start the lower bound of the range of best price masters
	* @param end the upper bound of the range of best price masters (not inclusive)
	* @return the range of matching best price masters
	*/
	public java.util.List<BestPriceMaster> findByContractId(
		java.lang.String contractId, int start, int end);

	/**
	* Returns an ordered range of all the best price masters where contractId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contractId the contract ID
	* @param start the lower bound of the range of best price masters
	* @param end the upper bound of the range of best price masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching best price masters
	*/
	public java.util.List<BestPriceMaster> findByContractId(
		java.lang.String contractId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator);

	/**
	* Returns an ordered range of all the best price masters where contractId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contractId the contract ID
	* @param start the lower bound of the range of best price masters
	* @param end the upper bound of the range of best price masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching best price masters
	*/
	public java.util.List<BestPriceMaster> findByContractId(
		java.lang.String contractId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first best price master in the ordered set where contractId = &#63;.
	*
	* @param contractId the contract ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching best price master
	* @throws NoSuchBestPriceMasterException if a matching best price master could not be found
	*/
	public BestPriceMaster findByContractId_First(java.lang.String contractId,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator)
		throws NoSuchBestPriceMasterException;

	/**
	* Returns the first best price master in the ordered set where contractId = &#63;.
	*
	* @param contractId the contract ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching best price master, or <code>null</code> if a matching best price master could not be found
	*/
	public BestPriceMaster fetchByContractId_First(
		java.lang.String contractId,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator);

	/**
	* Returns the last best price master in the ordered set where contractId = &#63;.
	*
	* @param contractId the contract ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching best price master
	* @throws NoSuchBestPriceMasterException if a matching best price master could not be found
	*/
	public BestPriceMaster findByContractId_Last(java.lang.String contractId,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator)
		throws NoSuchBestPriceMasterException;

	/**
	* Returns the last best price master in the ordered set where contractId = &#63;.
	*
	* @param contractId the contract ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching best price master, or <code>null</code> if a matching best price master could not be found
	*/
	public BestPriceMaster fetchByContractId_Last(java.lang.String contractId,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator);

	/**
	* Returns the best price masters before and after the current best price master in the ordered set where contractId = &#63;.
	*
	* @param bestPriceMasterSid the primary key of the current best price master
	* @param contractId the contract ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next best price master
	* @throws NoSuchBestPriceMasterException if a best price master with the primary key could not be found
	*/
	public BestPriceMaster[] findByContractId_PrevAndNext(
		int bestPriceMasterSid, java.lang.String contractId,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator)
		throws NoSuchBestPriceMasterException;

	/**
	* Removes all the best price masters where contractId = &#63; from the database.
	*
	* @param contractId the contract ID
	*/
	public void removeByContractId(java.lang.String contractId);

	/**
	* Returns the number of best price masters where contractId = &#63;.
	*
	* @param contractId the contract ID
	* @return the number of matching best price masters
	*/
	public int countByContractId(java.lang.String contractId);

	/**
	* Returns all the best price masters where accountId = &#63;.
	*
	* @param accountId the account ID
	* @return the matching best price masters
	*/
	public java.util.List<BestPriceMaster> findByAccountId(
		java.lang.String accountId);

	/**
	* Returns a range of all the best price masters where accountId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountId the account ID
	* @param start the lower bound of the range of best price masters
	* @param end the upper bound of the range of best price masters (not inclusive)
	* @return the range of matching best price masters
	*/
	public java.util.List<BestPriceMaster> findByAccountId(
		java.lang.String accountId, int start, int end);

	/**
	* Returns an ordered range of all the best price masters where accountId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountId the account ID
	* @param start the lower bound of the range of best price masters
	* @param end the upper bound of the range of best price masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching best price masters
	*/
	public java.util.List<BestPriceMaster> findByAccountId(
		java.lang.String accountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator);

	/**
	* Returns an ordered range of all the best price masters where accountId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountId the account ID
	* @param start the lower bound of the range of best price masters
	* @param end the upper bound of the range of best price masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching best price masters
	*/
	public java.util.List<BestPriceMaster> findByAccountId(
		java.lang.String accountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first best price master in the ordered set where accountId = &#63;.
	*
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching best price master
	* @throws NoSuchBestPriceMasterException if a matching best price master could not be found
	*/
	public BestPriceMaster findByAccountId_First(java.lang.String accountId,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator)
		throws NoSuchBestPriceMasterException;

	/**
	* Returns the first best price master in the ordered set where accountId = &#63;.
	*
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching best price master, or <code>null</code> if a matching best price master could not be found
	*/
	public BestPriceMaster fetchByAccountId_First(java.lang.String accountId,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator);

	/**
	* Returns the last best price master in the ordered set where accountId = &#63;.
	*
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching best price master
	* @throws NoSuchBestPriceMasterException if a matching best price master could not be found
	*/
	public BestPriceMaster findByAccountId_Last(java.lang.String accountId,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator)
		throws NoSuchBestPriceMasterException;

	/**
	* Returns the last best price master in the ordered set where accountId = &#63;.
	*
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching best price master, or <code>null</code> if a matching best price master could not be found
	*/
	public BestPriceMaster fetchByAccountId_Last(java.lang.String accountId,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator);

	/**
	* Returns the best price masters before and after the current best price master in the ordered set where accountId = &#63;.
	*
	* @param bestPriceMasterSid the primary key of the current best price master
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next best price master
	* @throws NoSuchBestPriceMasterException if a best price master with the primary key could not be found
	*/
	public BestPriceMaster[] findByAccountId_PrevAndNext(
		int bestPriceMasterSid, java.lang.String accountId,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator)
		throws NoSuchBestPriceMasterException;

	/**
	* Removes all the best price masters where accountId = &#63; from the database.
	*
	* @param accountId the account ID
	*/
	public void removeByAccountId(java.lang.String accountId);

	/**
	* Returns the number of best price masters where accountId = &#63;.
	*
	* @param accountId the account ID
	* @return the number of matching best price masters
	*/
	public int countByAccountId(java.lang.String accountId);

	/**
	* Returns all the best price masters where period = &#63;.
	*
	* @param period the period
	* @return the matching best price masters
	*/
	public java.util.List<BestPriceMaster> findByPeriod(java.lang.String period);

	/**
	* Returns a range of all the best price masters where period = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param period the period
	* @param start the lower bound of the range of best price masters
	* @param end the upper bound of the range of best price masters (not inclusive)
	* @return the range of matching best price masters
	*/
	public java.util.List<BestPriceMaster> findByPeriod(
		java.lang.String period, int start, int end);

	/**
	* Returns an ordered range of all the best price masters where period = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param period the period
	* @param start the lower bound of the range of best price masters
	* @param end the upper bound of the range of best price masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching best price masters
	*/
	public java.util.List<BestPriceMaster> findByPeriod(
		java.lang.String period, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator);

	/**
	* Returns an ordered range of all the best price masters where period = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param period the period
	* @param start the lower bound of the range of best price masters
	* @param end the upper bound of the range of best price masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching best price masters
	*/
	public java.util.List<BestPriceMaster> findByPeriod(
		java.lang.String period, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first best price master in the ordered set where period = &#63;.
	*
	* @param period the period
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching best price master
	* @throws NoSuchBestPriceMasterException if a matching best price master could not be found
	*/
	public BestPriceMaster findByPeriod_First(java.lang.String period,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator)
		throws NoSuchBestPriceMasterException;

	/**
	* Returns the first best price master in the ordered set where period = &#63;.
	*
	* @param period the period
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching best price master, or <code>null</code> if a matching best price master could not be found
	*/
	public BestPriceMaster fetchByPeriod_First(java.lang.String period,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator);

	/**
	* Returns the last best price master in the ordered set where period = &#63;.
	*
	* @param period the period
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching best price master
	* @throws NoSuchBestPriceMasterException if a matching best price master could not be found
	*/
	public BestPriceMaster findByPeriod_Last(java.lang.String period,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator)
		throws NoSuchBestPriceMasterException;

	/**
	* Returns the last best price master in the ordered set where period = &#63;.
	*
	* @param period the period
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching best price master, or <code>null</code> if a matching best price master could not be found
	*/
	public BestPriceMaster fetchByPeriod_Last(java.lang.String period,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator);

	/**
	* Returns the best price masters before and after the current best price master in the ordered set where period = &#63;.
	*
	* @param bestPriceMasterSid the primary key of the current best price master
	* @param period the period
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next best price master
	* @throws NoSuchBestPriceMasterException if a best price master with the primary key could not be found
	*/
	public BestPriceMaster[] findByPeriod_PrevAndNext(int bestPriceMasterSid,
		java.lang.String period,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator)
		throws NoSuchBestPriceMasterException;

	/**
	* Removes all the best price masters where period = &#63; from the database.
	*
	* @param period the period
	*/
	public void removeByPeriod(java.lang.String period);

	/**
	* Returns the number of best price masters where period = &#63;.
	*
	* @param period the period
	* @return the number of matching best price masters
	*/
	public int countByPeriod(java.lang.String period);

	/**
	* Returns all the best price masters where itemId = &#63; and accountId = &#63; and period = &#63; and contractNo = &#63;.
	*
	* @param itemId the item ID
	* @param accountId the account ID
	* @param period the period
	* @param contractNo the contract no
	* @return the matching best price masters
	*/
	public java.util.List<BestPriceMaster> findByBestPriceUnique(
		java.lang.String itemId, java.lang.String accountId,
		java.lang.String period, java.lang.String contractNo);

	/**
	* Returns a range of all the best price masters where itemId = &#63; and accountId = &#63; and period = &#63; and contractNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemId the item ID
	* @param accountId the account ID
	* @param period the period
	* @param contractNo the contract no
	* @param start the lower bound of the range of best price masters
	* @param end the upper bound of the range of best price masters (not inclusive)
	* @return the range of matching best price masters
	*/
	public java.util.List<BestPriceMaster> findByBestPriceUnique(
		java.lang.String itemId, java.lang.String accountId,
		java.lang.String period, java.lang.String contractNo, int start, int end);

	/**
	* Returns an ordered range of all the best price masters where itemId = &#63; and accountId = &#63; and period = &#63; and contractNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemId the item ID
	* @param accountId the account ID
	* @param period the period
	* @param contractNo the contract no
	* @param start the lower bound of the range of best price masters
	* @param end the upper bound of the range of best price masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching best price masters
	*/
	public java.util.List<BestPriceMaster> findByBestPriceUnique(
		java.lang.String itemId, java.lang.String accountId,
		java.lang.String period, java.lang.String contractNo, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator);

	/**
	* Returns an ordered range of all the best price masters where itemId = &#63; and accountId = &#63; and period = &#63; and contractNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemId the item ID
	* @param accountId the account ID
	* @param period the period
	* @param contractNo the contract no
	* @param start the lower bound of the range of best price masters
	* @param end the upper bound of the range of best price masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching best price masters
	*/
	public java.util.List<BestPriceMaster> findByBestPriceUnique(
		java.lang.String itemId, java.lang.String accountId,
		java.lang.String period, java.lang.String contractNo, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first best price master in the ordered set where itemId = &#63; and accountId = &#63; and period = &#63; and contractNo = &#63;.
	*
	* @param itemId the item ID
	* @param accountId the account ID
	* @param period the period
	* @param contractNo the contract no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching best price master
	* @throws NoSuchBestPriceMasterException if a matching best price master could not be found
	*/
	public BestPriceMaster findByBestPriceUnique_First(
		java.lang.String itemId, java.lang.String accountId,
		java.lang.String period, java.lang.String contractNo,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator)
		throws NoSuchBestPriceMasterException;

	/**
	* Returns the first best price master in the ordered set where itemId = &#63; and accountId = &#63; and period = &#63; and contractNo = &#63;.
	*
	* @param itemId the item ID
	* @param accountId the account ID
	* @param period the period
	* @param contractNo the contract no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching best price master, or <code>null</code> if a matching best price master could not be found
	*/
	public BestPriceMaster fetchByBestPriceUnique_First(
		java.lang.String itemId, java.lang.String accountId,
		java.lang.String period, java.lang.String contractNo,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator);

	/**
	* Returns the last best price master in the ordered set where itemId = &#63; and accountId = &#63; and period = &#63; and contractNo = &#63;.
	*
	* @param itemId the item ID
	* @param accountId the account ID
	* @param period the period
	* @param contractNo the contract no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching best price master
	* @throws NoSuchBestPriceMasterException if a matching best price master could not be found
	*/
	public BestPriceMaster findByBestPriceUnique_Last(java.lang.String itemId,
		java.lang.String accountId, java.lang.String period,
		java.lang.String contractNo,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator)
		throws NoSuchBestPriceMasterException;

	/**
	* Returns the last best price master in the ordered set where itemId = &#63; and accountId = &#63; and period = &#63; and contractNo = &#63;.
	*
	* @param itemId the item ID
	* @param accountId the account ID
	* @param period the period
	* @param contractNo the contract no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching best price master, or <code>null</code> if a matching best price master could not be found
	*/
	public BestPriceMaster fetchByBestPriceUnique_Last(
		java.lang.String itemId, java.lang.String accountId,
		java.lang.String period, java.lang.String contractNo,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator);

	/**
	* Returns the best price masters before and after the current best price master in the ordered set where itemId = &#63; and accountId = &#63; and period = &#63; and contractNo = &#63;.
	*
	* @param bestPriceMasterSid the primary key of the current best price master
	* @param itemId the item ID
	* @param accountId the account ID
	* @param period the period
	* @param contractNo the contract no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next best price master
	* @throws NoSuchBestPriceMasterException if a best price master with the primary key could not be found
	*/
	public BestPriceMaster[] findByBestPriceUnique_PrevAndNext(
		int bestPriceMasterSid, java.lang.String itemId,
		java.lang.String accountId, java.lang.String period,
		java.lang.String contractNo,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator)
		throws NoSuchBestPriceMasterException;

	/**
	* Removes all the best price masters where itemId = &#63; and accountId = &#63; and period = &#63; and contractNo = &#63; from the database.
	*
	* @param itemId the item ID
	* @param accountId the account ID
	* @param period the period
	* @param contractNo the contract no
	*/
	public void removeByBestPriceUnique(java.lang.String itemId,
		java.lang.String accountId, java.lang.String period,
		java.lang.String contractNo);

	/**
	* Returns the number of best price masters where itemId = &#63; and accountId = &#63; and period = &#63; and contractNo = &#63;.
	*
	* @param itemId the item ID
	* @param accountId the account ID
	* @param period the period
	* @param contractNo the contract no
	* @return the number of matching best price masters
	*/
	public int countByBestPriceUnique(java.lang.String itemId,
		java.lang.String accountId, java.lang.String period,
		java.lang.String contractNo);

	/**
	* Caches the best price master in the entity cache if it is enabled.
	*
	* @param bestPriceMaster the best price master
	*/
	public void cacheResult(BestPriceMaster bestPriceMaster);

	/**
	* Caches the best price masters in the entity cache if it is enabled.
	*
	* @param bestPriceMasters the best price masters
	*/
	public void cacheResult(java.util.List<BestPriceMaster> bestPriceMasters);

	/**
	* Creates a new best price master with the primary key. Does not add the best price master to the database.
	*
	* @param bestPriceMasterSid the primary key for the new best price master
	* @return the new best price master
	*/
	public BestPriceMaster create(int bestPriceMasterSid);

	/**
	* Removes the best price master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param bestPriceMasterSid the primary key of the best price master
	* @return the best price master that was removed
	* @throws NoSuchBestPriceMasterException if a best price master with the primary key could not be found
	*/
	public BestPriceMaster remove(int bestPriceMasterSid)
		throws NoSuchBestPriceMasterException;

	public BestPriceMaster updateImpl(BestPriceMaster bestPriceMaster);

	/**
	* Returns the best price master with the primary key or throws a {@link NoSuchBestPriceMasterException} if it could not be found.
	*
	* @param bestPriceMasterSid the primary key of the best price master
	* @return the best price master
	* @throws NoSuchBestPriceMasterException if a best price master with the primary key could not be found
	*/
	public BestPriceMaster findByPrimaryKey(int bestPriceMasterSid)
		throws NoSuchBestPriceMasterException;

	/**
	* Returns the best price master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param bestPriceMasterSid the primary key of the best price master
	* @return the best price master, or <code>null</code> if a best price master with the primary key could not be found
	*/
	public BestPriceMaster fetchByPrimaryKey(int bestPriceMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, BestPriceMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the best price masters.
	*
	* @return the best price masters
	*/
	public java.util.List<BestPriceMaster> findAll();

	/**
	* Returns a range of all the best price masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of best price masters
	* @param end the upper bound of the range of best price masters (not inclusive)
	* @return the range of best price masters
	*/
	public java.util.List<BestPriceMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the best price masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of best price masters
	* @param end the upper bound of the range of best price masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of best price masters
	*/
	public java.util.List<BestPriceMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator);

	/**
	* Returns an ordered range of all the best price masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BestPriceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of best price masters
	* @param end the upper bound of the range of best price masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of best price masters
	*/
	public java.util.List<BestPriceMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BestPriceMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the best price masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of best price masters.
	*
	* @return the number of best price masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}