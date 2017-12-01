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

import com.stpl.app.exception.NoSuchPriceScheduleDetailsException;
import com.stpl.app.model.PriceScheduleDetails;

/**
 * The persistence interface for the price schedule details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.PriceScheduleDetailsPersistenceImpl
 * @see PriceScheduleDetailsUtil
 * @generated
 */
@ProviderType
public interface PriceScheduleDetailsPersistence extends BasePersistence<PriceScheduleDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PriceScheduleDetailsUtil} to access the price schedule details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the price schedule detailses where priceScheduleSystemId = &#63;.
	*
	* @param priceScheduleSystemId the price schedule system ID
	* @return the matching price schedule detailses
	*/
	public java.util.List<PriceScheduleDetails> findByPriceScheduleMasterDetails(
		int priceScheduleSystemId);

	/**
	* Returns a range of all the price schedule detailses where priceScheduleSystemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param priceScheduleSystemId the price schedule system ID
	* @param start the lower bound of the range of price schedule detailses
	* @param end the upper bound of the range of price schedule detailses (not inclusive)
	* @return the range of matching price schedule detailses
	*/
	public java.util.List<PriceScheduleDetails> findByPriceScheduleMasterDetails(
		int priceScheduleSystemId, int start, int end);

	/**
	* Returns an ordered range of all the price schedule detailses where priceScheduleSystemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param priceScheduleSystemId the price schedule system ID
	* @param start the lower bound of the range of price schedule detailses
	* @param end the upper bound of the range of price schedule detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching price schedule detailses
	*/
	public java.util.List<PriceScheduleDetails> findByPriceScheduleMasterDetails(
		int priceScheduleSystemId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleDetails> orderByComparator);

	/**
	* Returns an ordered range of all the price schedule detailses where priceScheduleSystemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param priceScheduleSystemId the price schedule system ID
	* @param start the lower bound of the range of price schedule detailses
	* @param end the upper bound of the range of price schedule detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching price schedule detailses
	*/
	public java.util.List<PriceScheduleDetails> findByPriceScheduleMasterDetails(
		int priceScheduleSystemId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first price schedule details in the ordered set where priceScheduleSystemId = &#63;.
	*
	* @param priceScheduleSystemId the price schedule system ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching price schedule details
	* @throws NoSuchPriceScheduleDetailsException if a matching price schedule details could not be found
	*/
	public PriceScheduleDetails findByPriceScheduleMasterDetails_First(
		int priceScheduleSystemId,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleDetails> orderByComparator)
		throws NoSuchPriceScheduleDetailsException;

	/**
	* Returns the first price schedule details in the ordered set where priceScheduleSystemId = &#63;.
	*
	* @param priceScheduleSystemId the price schedule system ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching price schedule details, or <code>null</code> if a matching price schedule details could not be found
	*/
	public PriceScheduleDetails fetchByPriceScheduleMasterDetails_First(
		int priceScheduleSystemId,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleDetails> orderByComparator);

	/**
	* Returns the last price schedule details in the ordered set where priceScheduleSystemId = &#63;.
	*
	* @param priceScheduleSystemId the price schedule system ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching price schedule details
	* @throws NoSuchPriceScheduleDetailsException if a matching price schedule details could not be found
	*/
	public PriceScheduleDetails findByPriceScheduleMasterDetails_Last(
		int priceScheduleSystemId,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleDetails> orderByComparator)
		throws NoSuchPriceScheduleDetailsException;

	/**
	* Returns the last price schedule details in the ordered set where priceScheduleSystemId = &#63;.
	*
	* @param priceScheduleSystemId the price schedule system ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching price schedule details, or <code>null</code> if a matching price schedule details could not be found
	*/
	public PriceScheduleDetails fetchByPriceScheduleMasterDetails_Last(
		int priceScheduleSystemId,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleDetails> orderByComparator);

	/**
	* Returns the price schedule detailses before and after the current price schedule details in the ordered set where priceScheduleSystemId = &#63;.
	*
	* @param psDetailsSystemId the primary key of the current price schedule details
	* @param priceScheduleSystemId the price schedule system ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next price schedule details
	* @throws NoSuchPriceScheduleDetailsException if a price schedule details with the primary key could not be found
	*/
	public PriceScheduleDetails[] findByPriceScheduleMasterDetails_PrevAndNext(
		int psDetailsSystemId, int priceScheduleSystemId,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleDetails> orderByComparator)
		throws NoSuchPriceScheduleDetailsException;

	/**
	* Removes all the price schedule detailses where priceScheduleSystemId = &#63; from the database.
	*
	* @param priceScheduleSystemId the price schedule system ID
	*/
	public void removeByPriceScheduleMasterDetails(int priceScheduleSystemId);

	/**
	* Returns the number of price schedule detailses where priceScheduleSystemId = &#63;.
	*
	* @param priceScheduleSystemId the price schedule system ID
	* @return the number of matching price schedule detailses
	*/
	public int countByPriceScheduleMasterDetails(int priceScheduleSystemId);

	/**
	* Caches the price schedule details in the entity cache if it is enabled.
	*
	* @param priceScheduleDetails the price schedule details
	*/
	public void cacheResult(PriceScheduleDetails priceScheduleDetails);

	/**
	* Caches the price schedule detailses in the entity cache if it is enabled.
	*
	* @param priceScheduleDetailses the price schedule detailses
	*/
	public void cacheResult(
		java.util.List<PriceScheduleDetails> priceScheduleDetailses);

	/**
	* Creates a new price schedule details with the primary key. Does not add the price schedule details to the database.
	*
	* @param psDetailsSystemId the primary key for the new price schedule details
	* @return the new price schedule details
	*/
	public PriceScheduleDetails create(int psDetailsSystemId);

	/**
	* Removes the price schedule details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param psDetailsSystemId the primary key of the price schedule details
	* @return the price schedule details that was removed
	* @throws NoSuchPriceScheduleDetailsException if a price schedule details with the primary key could not be found
	*/
	public PriceScheduleDetails remove(int psDetailsSystemId)
		throws NoSuchPriceScheduleDetailsException;

	public PriceScheduleDetails updateImpl(
		PriceScheduleDetails priceScheduleDetails);

	/**
	* Returns the price schedule details with the primary key or throws a {@link NoSuchPriceScheduleDetailsException} if it could not be found.
	*
	* @param psDetailsSystemId the primary key of the price schedule details
	* @return the price schedule details
	* @throws NoSuchPriceScheduleDetailsException if a price schedule details with the primary key could not be found
	*/
	public PriceScheduleDetails findByPrimaryKey(int psDetailsSystemId)
		throws NoSuchPriceScheduleDetailsException;

	/**
	* Returns the price schedule details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param psDetailsSystemId the primary key of the price schedule details
	* @return the price schedule details, or <code>null</code> if a price schedule details with the primary key could not be found
	*/
	public PriceScheduleDetails fetchByPrimaryKey(int psDetailsSystemId);

	@Override
	public java.util.Map<java.io.Serializable, PriceScheduleDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the price schedule detailses.
	*
	* @return the price schedule detailses
	*/
	public java.util.List<PriceScheduleDetails> findAll();

	/**
	* Returns a range of all the price schedule detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of price schedule detailses
	* @param end the upper bound of the range of price schedule detailses (not inclusive)
	* @return the range of price schedule detailses
	*/
	public java.util.List<PriceScheduleDetails> findAll(int start, int end);

	/**
	* Returns an ordered range of all the price schedule detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of price schedule detailses
	* @param end the upper bound of the range of price schedule detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of price schedule detailses
	*/
	public java.util.List<PriceScheduleDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleDetails> orderByComparator);

	/**
	* Returns an ordered range of all the price schedule detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of price schedule detailses
	* @param end the upper bound of the range of price schedule detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of price schedule detailses
	*/
	public java.util.List<PriceScheduleDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PriceScheduleDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the price schedule detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of price schedule detailses.
	*
	* @return the number of price schedule detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}