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

import com.stpl.app.exception.NoSuchStDeductionCalendarDetailsException;
import com.stpl.app.model.StDeductionCalendarDetails;

/**
 * The persistence interface for the st deduction calendar details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.StDeductionCalendarDetailsPersistenceImpl
 * @see StDeductionCalendarDetailsUtil
 * @generated
 */
@ProviderType
public interface StDeductionCalendarDetailsPersistence extends BasePersistence<StDeductionCalendarDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link StDeductionCalendarDetailsUtil} to access the st deduction calendar details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the st deduction calendar details in the entity cache if it is enabled.
	*
	* @param stDeductionCalendarDetails the st deduction calendar details
	*/
	public void cacheResult(
		StDeductionCalendarDetails stDeductionCalendarDetails);

	/**
	* Caches the st deduction calendar detailses in the entity cache if it is enabled.
	*
	* @param stDeductionCalendarDetailses the st deduction calendar detailses
	*/
	public void cacheResult(
		java.util.List<StDeductionCalendarDetails> stDeductionCalendarDetailses);

	/**
	* Creates a new st deduction calendar details with the primary key. Does not add the st deduction calendar details to the database.
	*
	* @param stDeductionCalendarDetailsPK the primary key for the new st deduction calendar details
	* @return the new st deduction calendar details
	*/
	public StDeductionCalendarDetails create(
		StDeductionCalendarDetailsPK stDeductionCalendarDetailsPK);

	/**
	* Removes the st deduction calendar details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stDeductionCalendarDetailsPK the primary key of the st deduction calendar details
	* @return the st deduction calendar details that was removed
	* @throws NoSuchStDeductionCalendarDetailsException if a st deduction calendar details with the primary key could not be found
	*/
	public StDeductionCalendarDetails remove(
		StDeductionCalendarDetailsPK stDeductionCalendarDetailsPK)
		throws NoSuchStDeductionCalendarDetailsException;

	public StDeductionCalendarDetails updateImpl(
		StDeductionCalendarDetails stDeductionCalendarDetails);

	/**
	* Returns the st deduction calendar details with the primary key or throws a {@link NoSuchStDeductionCalendarDetailsException} if it could not be found.
	*
	* @param stDeductionCalendarDetailsPK the primary key of the st deduction calendar details
	* @return the st deduction calendar details
	* @throws NoSuchStDeductionCalendarDetailsException if a st deduction calendar details with the primary key could not be found
	*/
	public StDeductionCalendarDetails findByPrimaryKey(
		StDeductionCalendarDetailsPK stDeductionCalendarDetailsPK)
		throws NoSuchStDeductionCalendarDetailsException;

	/**
	* Returns the st deduction calendar details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param stDeductionCalendarDetailsPK the primary key of the st deduction calendar details
	* @return the st deduction calendar details, or <code>null</code> if a st deduction calendar details with the primary key could not be found
	*/
	public StDeductionCalendarDetails fetchByPrimaryKey(
		StDeductionCalendarDetailsPK stDeductionCalendarDetailsPK);

	@Override
	public java.util.Map<java.io.Serializable, StDeductionCalendarDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the st deduction calendar detailses.
	*
	* @return the st deduction calendar detailses
	*/
	public java.util.List<StDeductionCalendarDetails> findAll();

	/**
	* Returns a range of all the st deduction calendar detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StDeductionCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st deduction calendar detailses
	* @param end the upper bound of the range of st deduction calendar detailses (not inclusive)
	* @return the range of st deduction calendar detailses
	*/
	public java.util.List<StDeductionCalendarDetails> findAll(int start, int end);

	/**
	* Returns an ordered range of all the st deduction calendar detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StDeductionCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st deduction calendar detailses
	* @param end the upper bound of the range of st deduction calendar detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of st deduction calendar detailses
	*/
	public java.util.List<StDeductionCalendarDetails> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<StDeductionCalendarDetails> orderByComparator);

	/**
	* Returns an ordered range of all the st deduction calendar detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StDeductionCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st deduction calendar detailses
	* @param end the upper bound of the range of st deduction calendar detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of st deduction calendar detailses
	*/
	public java.util.List<StDeductionCalendarDetails> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<StDeductionCalendarDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the st deduction calendar detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of st deduction calendar detailses.
	*
	* @return the number of st deduction calendar detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}