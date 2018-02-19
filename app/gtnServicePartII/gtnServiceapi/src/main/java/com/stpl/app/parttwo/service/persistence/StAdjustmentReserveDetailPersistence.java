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

package com.stpl.app.parttwo.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.stpl.app.parttwo.exception.NoSuchStAdjustmentReserveDetailException;
import com.stpl.app.parttwo.model.StAdjustmentReserveDetail;

/**
 * The persistence interface for the st adjustment reserve detail service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.StAdjustmentReserveDetailPersistenceImpl
 * @see StAdjustmentReserveDetailUtil
 * @generated
 */
@ProviderType
public interface StAdjustmentReserveDetailPersistence extends BasePersistence<StAdjustmentReserveDetail> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link StAdjustmentReserveDetailUtil} to access the st adjustment reserve detail persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the st adjustment reserve detail in the entity cache if it is enabled.
	*
	* @param stAdjustmentReserveDetail the st adjustment reserve detail
	*/
	public void cacheResult(StAdjustmentReserveDetail stAdjustmentReserveDetail);

	/**
	* Caches the st adjustment reserve details in the entity cache if it is enabled.
	*
	* @param stAdjustmentReserveDetails the st adjustment reserve details
	*/
	public void cacheResult(
		java.util.List<StAdjustmentReserveDetail> stAdjustmentReserveDetails);

	/**
	* Creates a new st adjustment reserve detail with the primary key. Does not add the st adjustment reserve detail to the database.
	*
	* @param workflowId the primary key for the new st adjustment reserve detail
	* @return the new st adjustment reserve detail
	*/
	public StAdjustmentReserveDetail create(java.lang.String workflowId);

	/**
	* Removes the st adjustment reserve detail with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param workflowId the primary key of the st adjustment reserve detail
	* @return the st adjustment reserve detail that was removed
	* @throws NoSuchStAdjustmentReserveDetailException if a st adjustment reserve detail with the primary key could not be found
	*/
	public StAdjustmentReserveDetail remove(java.lang.String workflowId)
		throws NoSuchStAdjustmentReserveDetailException;

	public StAdjustmentReserveDetail updateImpl(
		StAdjustmentReserveDetail stAdjustmentReserveDetail);

	/**
	* Returns the st adjustment reserve detail with the primary key or throws a {@link NoSuchStAdjustmentReserveDetailException} if it could not be found.
	*
	* @param workflowId the primary key of the st adjustment reserve detail
	* @return the st adjustment reserve detail
	* @throws NoSuchStAdjustmentReserveDetailException if a st adjustment reserve detail with the primary key could not be found
	*/
	public StAdjustmentReserveDetail findByPrimaryKey(
		java.lang.String workflowId)
		throws NoSuchStAdjustmentReserveDetailException;

	/**
	* Returns the st adjustment reserve detail with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param workflowId the primary key of the st adjustment reserve detail
	* @return the st adjustment reserve detail, or <code>null</code> if a st adjustment reserve detail with the primary key could not be found
	*/
	public StAdjustmentReserveDetail fetchByPrimaryKey(
		java.lang.String workflowId);

	@Override
	public java.util.Map<java.io.Serializable, StAdjustmentReserveDetail> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the st adjustment reserve details.
	*
	* @return the st adjustment reserve details
	*/
	public java.util.List<StAdjustmentReserveDetail> findAll();

	/**
	* Returns a range of all the st adjustment reserve details.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StAdjustmentReserveDetailModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st adjustment reserve details
	* @param end the upper bound of the range of st adjustment reserve details (not inclusive)
	* @return the range of st adjustment reserve details
	*/
	public java.util.List<StAdjustmentReserveDetail> findAll(int start, int end);

	/**
	* Returns an ordered range of all the st adjustment reserve details.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StAdjustmentReserveDetailModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st adjustment reserve details
	* @param end the upper bound of the range of st adjustment reserve details (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of st adjustment reserve details
	*/
	public java.util.List<StAdjustmentReserveDetail> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<StAdjustmentReserveDetail> orderByComparator);

	/**
	* Returns an ordered range of all the st adjustment reserve details.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StAdjustmentReserveDetailModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st adjustment reserve details
	* @param end the upper bound of the range of st adjustment reserve details (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of st adjustment reserve details
	*/
	public java.util.List<StAdjustmentReserveDetail> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<StAdjustmentReserveDetail> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the st adjustment reserve details from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of st adjustment reserve details.
	*
	* @return the number of st adjustment reserve details
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}