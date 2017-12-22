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

import com.stpl.app.parttwo.exception.NoSuchStAdjustmentGtnDetailException;
import com.stpl.app.parttwo.model.StAdjustmentGtnDetail;

/**
 * The persistence interface for the st adjustment gtn detail service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.StAdjustmentGtnDetailPersistenceImpl
 * @see StAdjustmentGtnDetailUtil
 * @generated
 */
@ProviderType
public interface StAdjustmentGtnDetailPersistence extends BasePersistence<StAdjustmentGtnDetail> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link StAdjustmentGtnDetailUtil} to access the st adjustment gtn detail persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the st adjustment gtn detail in the entity cache if it is enabled.
	*
	* @param stAdjustmentGtnDetail the st adjustment gtn detail
	*/
	public void cacheResult(StAdjustmentGtnDetail stAdjustmentGtnDetail);

	/**
	* Caches the st adjustment gtn details in the entity cache if it is enabled.
	*
	* @param stAdjustmentGtnDetails the st adjustment gtn details
	*/
	public void cacheResult(
		java.util.List<StAdjustmentGtnDetail> stAdjustmentGtnDetails);

	/**
	* Creates a new st adjustment gtn detail with the primary key. Does not add the st adjustment gtn detail to the database.
	*
	* @param workflowId the primary key for the new st adjustment gtn detail
	* @return the new st adjustment gtn detail
	*/
	public StAdjustmentGtnDetail create(java.lang.String workflowId);

	/**
	* Removes the st adjustment gtn detail with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param workflowId the primary key of the st adjustment gtn detail
	* @return the st adjustment gtn detail that was removed
	* @throws NoSuchStAdjustmentGtnDetailException if a st adjustment gtn detail with the primary key could not be found
	*/
	public StAdjustmentGtnDetail remove(java.lang.String workflowId)
		throws NoSuchStAdjustmentGtnDetailException;

	public StAdjustmentGtnDetail updateImpl(
		StAdjustmentGtnDetail stAdjustmentGtnDetail);

	/**
	* Returns the st adjustment gtn detail with the primary key or throws a {@link NoSuchStAdjustmentGtnDetailException} if it could not be found.
	*
	* @param workflowId the primary key of the st adjustment gtn detail
	* @return the st adjustment gtn detail
	* @throws NoSuchStAdjustmentGtnDetailException if a st adjustment gtn detail with the primary key could not be found
	*/
	public StAdjustmentGtnDetail findByPrimaryKey(java.lang.String workflowId)
		throws NoSuchStAdjustmentGtnDetailException;

	/**
	* Returns the st adjustment gtn detail with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param workflowId the primary key of the st adjustment gtn detail
	* @return the st adjustment gtn detail, or <code>null</code> if a st adjustment gtn detail with the primary key could not be found
	*/
	public StAdjustmentGtnDetail fetchByPrimaryKey(java.lang.String workflowId);

	@Override
	public java.util.Map<java.io.Serializable, StAdjustmentGtnDetail> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the st adjustment gtn details.
	*
	* @return the st adjustment gtn details
	*/
	public java.util.List<StAdjustmentGtnDetail> findAll();

	/**
	* Returns a range of all the st adjustment gtn details.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StAdjustmentGtnDetailModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st adjustment gtn details
	* @param end the upper bound of the range of st adjustment gtn details (not inclusive)
	* @return the range of st adjustment gtn details
	*/
	public java.util.List<StAdjustmentGtnDetail> findAll(int start, int end);

	/**
	* Returns an ordered range of all the st adjustment gtn details.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StAdjustmentGtnDetailModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st adjustment gtn details
	* @param end the upper bound of the range of st adjustment gtn details (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of st adjustment gtn details
	*/
	public java.util.List<StAdjustmentGtnDetail> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StAdjustmentGtnDetail> orderByComparator);

	/**
	* Returns an ordered range of all the st adjustment gtn details.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StAdjustmentGtnDetailModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st adjustment gtn details
	* @param end the upper bound of the range of st adjustment gtn details (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of st adjustment gtn details
	*/
	public java.util.List<StAdjustmentGtnDetail> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StAdjustmentGtnDetail> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the st adjustment gtn details from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of st adjustment gtn details.
	*
	* @return the number of st adjustment gtn details
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}