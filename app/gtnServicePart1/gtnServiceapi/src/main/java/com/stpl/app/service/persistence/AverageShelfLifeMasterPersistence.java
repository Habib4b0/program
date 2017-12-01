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

import com.stpl.app.exception.NoSuchAverageShelfLifeMasterException;
import com.stpl.app.model.AverageShelfLifeMaster;

/**
 * The persistence interface for the average shelf life master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.AverageShelfLifeMasterPersistenceImpl
 * @see AverageShelfLifeMasterUtil
 * @generated
 */
@ProviderType
public interface AverageShelfLifeMasterPersistence extends BasePersistence<AverageShelfLifeMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AverageShelfLifeMasterUtil} to access the average shelf life master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the average shelf life masters where itemIdType = &#63;.
	*
	* @param itemIdType the item ID type
	* @return the matching average shelf life masters
	*/
	public java.util.List<AverageShelfLifeMaster> findByItemIdType(
		java.lang.String itemIdType);

	/**
	* Returns a range of all the average shelf life masters where itemIdType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemIdType the item ID type
	* @param start the lower bound of the range of average shelf life masters
	* @param end the upper bound of the range of average shelf life masters (not inclusive)
	* @return the range of matching average shelf life masters
	*/
	public java.util.List<AverageShelfLifeMaster> findByItemIdType(
		java.lang.String itemIdType, int start, int end);

	/**
	* Returns an ordered range of all the average shelf life masters where itemIdType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemIdType the item ID type
	* @param start the lower bound of the range of average shelf life masters
	* @param end the upper bound of the range of average shelf life masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching average shelf life masters
	*/
	public java.util.List<AverageShelfLifeMaster> findByItemIdType(
		java.lang.String itemIdType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AverageShelfLifeMaster> orderByComparator);

	/**
	* Returns an ordered range of all the average shelf life masters where itemIdType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemIdType the item ID type
	* @param start the lower bound of the range of average shelf life masters
	* @param end the upper bound of the range of average shelf life masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching average shelf life masters
	*/
	public java.util.List<AverageShelfLifeMaster> findByItemIdType(
		java.lang.String itemIdType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AverageShelfLifeMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first average shelf life master in the ordered set where itemIdType = &#63;.
	*
	* @param itemIdType the item ID type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching average shelf life master
	* @throws NoSuchAverageShelfLifeMasterException if a matching average shelf life master could not be found
	*/
	public AverageShelfLifeMaster findByItemIdType_First(
		java.lang.String itemIdType,
		com.liferay.portal.kernel.util.OrderByComparator<AverageShelfLifeMaster> orderByComparator)
		throws NoSuchAverageShelfLifeMasterException;

	/**
	* Returns the first average shelf life master in the ordered set where itemIdType = &#63;.
	*
	* @param itemIdType the item ID type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching average shelf life master, or <code>null</code> if a matching average shelf life master could not be found
	*/
	public AverageShelfLifeMaster fetchByItemIdType_First(
		java.lang.String itemIdType,
		com.liferay.portal.kernel.util.OrderByComparator<AverageShelfLifeMaster> orderByComparator);

	/**
	* Returns the last average shelf life master in the ordered set where itemIdType = &#63;.
	*
	* @param itemIdType the item ID type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching average shelf life master
	* @throws NoSuchAverageShelfLifeMasterException if a matching average shelf life master could not be found
	*/
	public AverageShelfLifeMaster findByItemIdType_Last(
		java.lang.String itemIdType,
		com.liferay.portal.kernel.util.OrderByComparator<AverageShelfLifeMaster> orderByComparator)
		throws NoSuchAverageShelfLifeMasterException;

	/**
	* Returns the last average shelf life master in the ordered set where itemIdType = &#63;.
	*
	* @param itemIdType the item ID type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching average shelf life master, or <code>null</code> if a matching average shelf life master could not be found
	*/
	public AverageShelfLifeMaster fetchByItemIdType_Last(
		java.lang.String itemIdType,
		com.liferay.portal.kernel.util.OrderByComparator<AverageShelfLifeMaster> orderByComparator);

	/**
	* Returns the average shelf life masters before and after the current average shelf life master in the ordered set where itemIdType = &#63;.
	*
	* @param averageShelfLifeMasterSid the primary key of the current average shelf life master
	* @param itemIdType the item ID type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next average shelf life master
	* @throws NoSuchAverageShelfLifeMasterException if a average shelf life master with the primary key could not be found
	*/
	public AverageShelfLifeMaster[] findByItemIdType_PrevAndNext(
		int averageShelfLifeMasterSid, java.lang.String itemIdType,
		com.liferay.portal.kernel.util.OrderByComparator<AverageShelfLifeMaster> orderByComparator)
		throws NoSuchAverageShelfLifeMasterException;

	/**
	* Removes all the average shelf life masters where itemIdType = &#63; from the database.
	*
	* @param itemIdType the item ID type
	*/
	public void removeByItemIdType(java.lang.String itemIdType);

	/**
	* Returns the number of average shelf life masters where itemIdType = &#63;.
	*
	* @param itemIdType the item ID type
	* @return the number of matching average shelf life masters
	*/
	public int countByItemIdType(java.lang.String itemIdType);

	/**
	* Returns all the average shelf life masters where itemId = &#63;.
	*
	* @param itemId the item ID
	* @return the matching average shelf life masters
	*/
	public java.util.List<AverageShelfLifeMaster> findByItemId(
		java.lang.String itemId);

	/**
	* Returns a range of all the average shelf life masters where itemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemId the item ID
	* @param start the lower bound of the range of average shelf life masters
	* @param end the upper bound of the range of average shelf life masters (not inclusive)
	* @return the range of matching average shelf life masters
	*/
	public java.util.List<AverageShelfLifeMaster> findByItemId(
		java.lang.String itemId, int start, int end);

	/**
	* Returns an ordered range of all the average shelf life masters where itemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemId the item ID
	* @param start the lower bound of the range of average shelf life masters
	* @param end the upper bound of the range of average shelf life masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching average shelf life masters
	*/
	public java.util.List<AverageShelfLifeMaster> findByItemId(
		java.lang.String itemId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AverageShelfLifeMaster> orderByComparator);

	/**
	* Returns an ordered range of all the average shelf life masters where itemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemId the item ID
	* @param start the lower bound of the range of average shelf life masters
	* @param end the upper bound of the range of average shelf life masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching average shelf life masters
	*/
	public java.util.List<AverageShelfLifeMaster> findByItemId(
		java.lang.String itemId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AverageShelfLifeMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first average shelf life master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching average shelf life master
	* @throws NoSuchAverageShelfLifeMasterException if a matching average shelf life master could not be found
	*/
	public AverageShelfLifeMaster findByItemId_First(java.lang.String itemId,
		com.liferay.portal.kernel.util.OrderByComparator<AverageShelfLifeMaster> orderByComparator)
		throws NoSuchAverageShelfLifeMasterException;

	/**
	* Returns the first average shelf life master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching average shelf life master, or <code>null</code> if a matching average shelf life master could not be found
	*/
	public AverageShelfLifeMaster fetchByItemId_First(java.lang.String itemId,
		com.liferay.portal.kernel.util.OrderByComparator<AverageShelfLifeMaster> orderByComparator);

	/**
	* Returns the last average shelf life master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching average shelf life master
	* @throws NoSuchAverageShelfLifeMasterException if a matching average shelf life master could not be found
	*/
	public AverageShelfLifeMaster findByItemId_Last(java.lang.String itemId,
		com.liferay.portal.kernel.util.OrderByComparator<AverageShelfLifeMaster> orderByComparator)
		throws NoSuchAverageShelfLifeMasterException;

	/**
	* Returns the last average shelf life master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching average shelf life master, or <code>null</code> if a matching average shelf life master could not be found
	*/
	public AverageShelfLifeMaster fetchByItemId_Last(java.lang.String itemId,
		com.liferay.portal.kernel.util.OrderByComparator<AverageShelfLifeMaster> orderByComparator);

	/**
	* Returns the average shelf life masters before and after the current average shelf life master in the ordered set where itemId = &#63;.
	*
	* @param averageShelfLifeMasterSid the primary key of the current average shelf life master
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next average shelf life master
	* @throws NoSuchAverageShelfLifeMasterException if a average shelf life master with the primary key could not be found
	*/
	public AverageShelfLifeMaster[] findByItemId_PrevAndNext(
		int averageShelfLifeMasterSid, java.lang.String itemId,
		com.liferay.portal.kernel.util.OrderByComparator<AverageShelfLifeMaster> orderByComparator)
		throws NoSuchAverageShelfLifeMasterException;

	/**
	* Removes all the average shelf life masters where itemId = &#63; from the database.
	*
	* @param itemId the item ID
	*/
	public void removeByItemId(java.lang.String itemId);

	/**
	* Returns the number of average shelf life masters where itemId = &#63;.
	*
	* @param itemId the item ID
	* @return the number of matching average shelf life masters
	*/
	public int countByItemId(java.lang.String itemId);

	/**
	* Returns all the average shelf life masters where avgShelfLife = &#63;.
	*
	* @param avgShelfLife the avg shelf life
	* @return the matching average shelf life masters
	*/
	public java.util.List<AverageShelfLifeMaster> findByAvgShelfLife(
		java.lang.String avgShelfLife);

	/**
	* Returns a range of all the average shelf life masters where avgShelfLife = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param avgShelfLife the avg shelf life
	* @param start the lower bound of the range of average shelf life masters
	* @param end the upper bound of the range of average shelf life masters (not inclusive)
	* @return the range of matching average shelf life masters
	*/
	public java.util.List<AverageShelfLifeMaster> findByAvgShelfLife(
		java.lang.String avgShelfLife, int start, int end);

	/**
	* Returns an ordered range of all the average shelf life masters where avgShelfLife = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param avgShelfLife the avg shelf life
	* @param start the lower bound of the range of average shelf life masters
	* @param end the upper bound of the range of average shelf life masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching average shelf life masters
	*/
	public java.util.List<AverageShelfLifeMaster> findByAvgShelfLife(
		java.lang.String avgShelfLife, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AverageShelfLifeMaster> orderByComparator);

	/**
	* Returns an ordered range of all the average shelf life masters where avgShelfLife = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param avgShelfLife the avg shelf life
	* @param start the lower bound of the range of average shelf life masters
	* @param end the upper bound of the range of average shelf life masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching average shelf life masters
	*/
	public java.util.List<AverageShelfLifeMaster> findByAvgShelfLife(
		java.lang.String avgShelfLife, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AverageShelfLifeMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first average shelf life master in the ordered set where avgShelfLife = &#63;.
	*
	* @param avgShelfLife the avg shelf life
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching average shelf life master
	* @throws NoSuchAverageShelfLifeMasterException if a matching average shelf life master could not be found
	*/
	public AverageShelfLifeMaster findByAvgShelfLife_First(
		java.lang.String avgShelfLife,
		com.liferay.portal.kernel.util.OrderByComparator<AverageShelfLifeMaster> orderByComparator)
		throws NoSuchAverageShelfLifeMasterException;

	/**
	* Returns the first average shelf life master in the ordered set where avgShelfLife = &#63;.
	*
	* @param avgShelfLife the avg shelf life
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching average shelf life master, or <code>null</code> if a matching average shelf life master could not be found
	*/
	public AverageShelfLifeMaster fetchByAvgShelfLife_First(
		java.lang.String avgShelfLife,
		com.liferay.portal.kernel.util.OrderByComparator<AverageShelfLifeMaster> orderByComparator);

	/**
	* Returns the last average shelf life master in the ordered set where avgShelfLife = &#63;.
	*
	* @param avgShelfLife the avg shelf life
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching average shelf life master
	* @throws NoSuchAverageShelfLifeMasterException if a matching average shelf life master could not be found
	*/
	public AverageShelfLifeMaster findByAvgShelfLife_Last(
		java.lang.String avgShelfLife,
		com.liferay.portal.kernel.util.OrderByComparator<AverageShelfLifeMaster> orderByComparator)
		throws NoSuchAverageShelfLifeMasterException;

	/**
	* Returns the last average shelf life master in the ordered set where avgShelfLife = &#63;.
	*
	* @param avgShelfLife the avg shelf life
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching average shelf life master, or <code>null</code> if a matching average shelf life master could not be found
	*/
	public AverageShelfLifeMaster fetchByAvgShelfLife_Last(
		java.lang.String avgShelfLife,
		com.liferay.portal.kernel.util.OrderByComparator<AverageShelfLifeMaster> orderByComparator);

	/**
	* Returns the average shelf life masters before and after the current average shelf life master in the ordered set where avgShelfLife = &#63;.
	*
	* @param averageShelfLifeMasterSid the primary key of the current average shelf life master
	* @param avgShelfLife the avg shelf life
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next average shelf life master
	* @throws NoSuchAverageShelfLifeMasterException if a average shelf life master with the primary key could not be found
	*/
	public AverageShelfLifeMaster[] findByAvgShelfLife_PrevAndNext(
		int averageShelfLifeMasterSid, java.lang.String avgShelfLife,
		com.liferay.portal.kernel.util.OrderByComparator<AverageShelfLifeMaster> orderByComparator)
		throws NoSuchAverageShelfLifeMasterException;

	/**
	* Removes all the average shelf life masters where avgShelfLife = &#63; from the database.
	*
	* @param avgShelfLife the avg shelf life
	*/
	public void removeByAvgShelfLife(java.lang.String avgShelfLife);

	/**
	* Returns the number of average shelf life masters where avgShelfLife = &#63;.
	*
	* @param avgShelfLife the avg shelf life
	* @return the number of matching average shelf life masters
	*/
	public int countByAvgShelfLife(java.lang.String avgShelfLife);

	/**
	* Returns all the average shelf life masters where itemIdType = &#63; and itemId = &#63;.
	*
	* @param itemIdType the item ID type
	* @param itemId the item ID
	* @return the matching average shelf life masters
	*/
	public java.util.List<AverageShelfLifeMaster> findByAverageShelfLifeUnique(
		java.lang.String itemIdType, java.lang.String itemId);

	/**
	* Returns a range of all the average shelf life masters where itemIdType = &#63; and itemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemIdType the item ID type
	* @param itemId the item ID
	* @param start the lower bound of the range of average shelf life masters
	* @param end the upper bound of the range of average shelf life masters (not inclusive)
	* @return the range of matching average shelf life masters
	*/
	public java.util.List<AverageShelfLifeMaster> findByAverageShelfLifeUnique(
		java.lang.String itemIdType, java.lang.String itemId, int start, int end);

	/**
	* Returns an ordered range of all the average shelf life masters where itemIdType = &#63; and itemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemIdType the item ID type
	* @param itemId the item ID
	* @param start the lower bound of the range of average shelf life masters
	* @param end the upper bound of the range of average shelf life masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching average shelf life masters
	*/
	public java.util.List<AverageShelfLifeMaster> findByAverageShelfLifeUnique(
		java.lang.String itemIdType, java.lang.String itemId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<AverageShelfLifeMaster> orderByComparator);

	/**
	* Returns an ordered range of all the average shelf life masters where itemIdType = &#63; and itemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemIdType the item ID type
	* @param itemId the item ID
	* @param start the lower bound of the range of average shelf life masters
	* @param end the upper bound of the range of average shelf life masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching average shelf life masters
	*/
	public java.util.List<AverageShelfLifeMaster> findByAverageShelfLifeUnique(
		java.lang.String itemIdType, java.lang.String itemId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<AverageShelfLifeMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first average shelf life master in the ordered set where itemIdType = &#63; and itemId = &#63;.
	*
	* @param itemIdType the item ID type
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching average shelf life master
	* @throws NoSuchAverageShelfLifeMasterException if a matching average shelf life master could not be found
	*/
	public AverageShelfLifeMaster findByAverageShelfLifeUnique_First(
		java.lang.String itemIdType, java.lang.String itemId,
		com.liferay.portal.kernel.util.OrderByComparator<AverageShelfLifeMaster> orderByComparator)
		throws NoSuchAverageShelfLifeMasterException;

	/**
	* Returns the first average shelf life master in the ordered set where itemIdType = &#63; and itemId = &#63;.
	*
	* @param itemIdType the item ID type
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching average shelf life master, or <code>null</code> if a matching average shelf life master could not be found
	*/
	public AverageShelfLifeMaster fetchByAverageShelfLifeUnique_First(
		java.lang.String itemIdType, java.lang.String itemId,
		com.liferay.portal.kernel.util.OrderByComparator<AverageShelfLifeMaster> orderByComparator);

	/**
	* Returns the last average shelf life master in the ordered set where itemIdType = &#63; and itemId = &#63;.
	*
	* @param itemIdType the item ID type
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching average shelf life master
	* @throws NoSuchAverageShelfLifeMasterException if a matching average shelf life master could not be found
	*/
	public AverageShelfLifeMaster findByAverageShelfLifeUnique_Last(
		java.lang.String itemIdType, java.lang.String itemId,
		com.liferay.portal.kernel.util.OrderByComparator<AverageShelfLifeMaster> orderByComparator)
		throws NoSuchAverageShelfLifeMasterException;

	/**
	* Returns the last average shelf life master in the ordered set where itemIdType = &#63; and itemId = &#63;.
	*
	* @param itemIdType the item ID type
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching average shelf life master, or <code>null</code> if a matching average shelf life master could not be found
	*/
	public AverageShelfLifeMaster fetchByAverageShelfLifeUnique_Last(
		java.lang.String itemIdType, java.lang.String itemId,
		com.liferay.portal.kernel.util.OrderByComparator<AverageShelfLifeMaster> orderByComparator);

	/**
	* Returns the average shelf life masters before and after the current average shelf life master in the ordered set where itemIdType = &#63; and itemId = &#63;.
	*
	* @param averageShelfLifeMasterSid the primary key of the current average shelf life master
	* @param itemIdType the item ID type
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next average shelf life master
	* @throws NoSuchAverageShelfLifeMasterException if a average shelf life master with the primary key could not be found
	*/
	public AverageShelfLifeMaster[] findByAverageShelfLifeUnique_PrevAndNext(
		int averageShelfLifeMasterSid, java.lang.String itemIdType,
		java.lang.String itemId,
		com.liferay.portal.kernel.util.OrderByComparator<AverageShelfLifeMaster> orderByComparator)
		throws NoSuchAverageShelfLifeMasterException;

	/**
	* Removes all the average shelf life masters where itemIdType = &#63; and itemId = &#63; from the database.
	*
	* @param itemIdType the item ID type
	* @param itemId the item ID
	*/
	public void removeByAverageShelfLifeUnique(java.lang.String itemIdType,
		java.lang.String itemId);

	/**
	* Returns the number of average shelf life masters where itemIdType = &#63; and itemId = &#63;.
	*
	* @param itemIdType the item ID type
	* @param itemId the item ID
	* @return the number of matching average shelf life masters
	*/
	public int countByAverageShelfLifeUnique(java.lang.String itemIdType,
		java.lang.String itemId);

	/**
	* Caches the average shelf life master in the entity cache if it is enabled.
	*
	* @param averageShelfLifeMaster the average shelf life master
	*/
	public void cacheResult(AverageShelfLifeMaster averageShelfLifeMaster);

	/**
	* Caches the average shelf life masters in the entity cache if it is enabled.
	*
	* @param averageShelfLifeMasters the average shelf life masters
	*/
	public void cacheResult(
		java.util.List<AverageShelfLifeMaster> averageShelfLifeMasters);

	/**
	* Creates a new average shelf life master with the primary key. Does not add the average shelf life master to the database.
	*
	* @param averageShelfLifeMasterSid the primary key for the new average shelf life master
	* @return the new average shelf life master
	*/
	public AverageShelfLifeMaster create(int averageShelfLifeMasterSid);

	/**
	* Removes the average shelf life master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param averageShelfLifeMasterSid the primary key of the average shelf life master
	* @return the average shelf life master that was removed
	* @throws NoSuchAverageShelfLifeMasterException if a average shelf life master with the primary key could not be found
	*/
	public AverageShelfLifeMaster remove(int averageShelfLifeMasterSid)
		throws NoSuchAverageShelfLifeMasterException;

	public AverageShelfLifeMaster updateImpl(
		AverageShelfLifeMaster averageShelfLifeMaster);

	/**
	* Returns the average shelf life master with the primary key or throws a {@link NoSuchAverageShelfLifeMasterException} if it could not be found.
	*
	* @param averageShelfLifeMasterSid the primary key of the average shelf life master
	* @return the average shelf life master
	* @throws NoSuchAverageShelfLifeMasterException if a average shelf life master with the primary key could not be found
	*/
	public AverageShelfLifeMaster findByPrimaryKey(
		int averageShelfLifeMasterSid)
		throws NoSuchAverageShelfLifeMasterException;

	/**
	* Returns the average shelf life master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param averageShelfLifeMasterSid the primary key of the average shelf life master
	* @return the average shelf life master, or <code>null</code> if a average shelf life master with the primary key could not be found
	*/
	public AverageShelfLifeMaster fetchByPrimaryKey(
		int averageShelfLifeMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, AverageShelfLifeMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the average shelf life masters.
	*
	* @return the average shelf life masters
	*/
	public java.util.List<AverageShelfLifeMaster> findAll();

	/**
	* Returns a range of all the average shelf life masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of average shelf life masters
	* @param end the upper bound of the range of average shelf life masters (not inclusive)
	* @return the range of average shelf life masters
	*/
	public java.util.List<AverageShelfLifeMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the average shelf life masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of average shelf life masters
	* @param end the upper bound of the range of average shelf life masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of average shelf life masters
	*/
	public java.util.List<AverageShelfLifeMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AverageShelfLifeMaster> orderByComparator);

	/**
	* Returns an ordered range of all the average shelf life masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AverageShelfLifeMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of average shelf life masters
	* @param end the upper bound of the range of average shelf life masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of average shelf life masters
	*/
	public java.util.List<AverageShelfLifeMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AverageShelfLifeMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the average shelf life masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of average shelf life masters.
	*
	* @return the number of average shelf life masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}