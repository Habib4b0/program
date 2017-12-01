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

import com.stpl.app.exception.NoSuchDeductionCalendarDetailsException;
import com.stpl.app.model.DeductionCalendarDetails;

/**
 * The persistence interface for the deduction calendar details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.DeductionCalendarDetailsPersistenceImpl
 * @see DeductionCalendarDetailsUtil
 * @generated
 */
@ProviderType
public interface DeductionCalendarDetailsPersistence extends BasePersistence<DeductionCalendarDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DeductionCalendarDetailsUtil} to access the deduction calendar details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the deduction calendar details in the entity cache if it is enabled.
	*
	* @param deductionCalendarDetails the deduction calendar details
	*/
	public void cacheResult(DeductionCalendarDetails deductionCalendarDetails);

	/**
	* Caches the deduction calendar detailses in the entity cache if it is enabled.
	*
	* @param deductionCalendarDetailses the deduction calendar detailses
	*/
	public void cacheResult(
		java.util.List<DeductionCalendarDetails> deductionCalendarDetailses);

	/**
	* Creates a new deduction calendar details with the primary key. Does not add the deduction calendar details to the database.
	*
	* @param deductionCalendarDetailsPK the primary key for the new deduction calendar details
	* @return the new deduction calendar details
	*/
	public DeductionCalendarDetails create(
		DeductionCalendarDetailsPK deductionCalendarDetailsPK);

	/**
	* Removes the deduction calendar details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param deductionCalendarDetailsPK the primary key of the deduction calendar details
	* @return the deduction calendar details that was removed
	* @throws NoSuchDeductionCalendarDetailsException if a deduction calendar details with the primary key could not be found
	*/
	public DeductionCalendarDetails remove(
		DeductionCalendarDetailsPK deductionCalendarDetailsPK)
		throws NoSuchDeductionCalendarDetailsException;

	public DeductionCalendarDetails updateImpl(
		DeductionCalendarDetails deductionCalendarDetails);

	/**
	* Returns the deduction calendar details with the primary key or throws a {@link NoSuchDeductionCalendarDetailsException} if it could not be found.
	*
	* @param deductionCalendarDetailsPK the primary key of the deduction calendar details
	* @return the deduction calendar details
	* @throws NoSuchDeductionCalendarDetailsException if a deduction calendar details with the primary key could not be found
	*/
	public DeductionCalendarDetails findByPrimaryKey(
		DeductionCalendarDetailsPK deductionCalendarDetailsPK)
		throws NoSuchDeductionCalendarDetailsException;

	/**
	* Returns the deduction calendar details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param deductionCalendarDetailsPK the primary key of the deduction calendar details
	* @return the deduction calendar details, or <code>null</code> if a deduction calendar details with the primary key could not be found
	*/
	public DeductionCalendarDetails fetchByPrimaryKey(
		DeductionCalendarDetailsPK deductionCalendarDetailsPK);

	@Override
	public java.util.Map<java.io.Serializable, DeductionCalendarDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the deduction calendar detailses.
	*
	* @return the deduction calendar detailses
	*/
	public java.util.List<DeductionCalendarDetails> findAll();

	/**
	* Returns a range of all the deduction calendar detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeductionCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of deduction calendar detailses
	* @param end the upper bound of the range of deduction calendar detailses (not inclusive)
	* @return the range of deduction calendar detailses
	*/
	public java.util.List<DeductionCalendarDetails> findAll(int start, int end);

	/**
	* Returns an ordered range of all the deduction calendar detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeductionCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of deduction calendar detailses
	* @param end the upper bound of the range of deduction calendar detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of deduction calendar detailses
	*/
	public java.util.List<DeductionCalendarDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DeductionCalendarDetails> orderByComparator);

	/**
	* Returns an ordered range of all the deduction calendar detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeductionCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of deduction calendar detailses
	* @param end the upper bound of the range of deduction calendar detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of deduction calendar detailses
	*/
	public java.util.List<DeductionCalendarDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DeductionCalendarDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the deduction calendar detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of deduction calendar detailses.
	*
	* @return the number of deduction calendar detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}