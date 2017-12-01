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

import com.stpl.app.exception.NoSuchDeductionDetailsException;
import com.stpl.app.model.DeductionDetails;

/**
 * The persistence interface for the deduction details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.DeductionDetailsPersistenceImpl
 * @see DeductionDetailsUtil
 * @generated
 */
@ProviderType
public interface DeductionDetailsPersistence extends BasePersistence<DeductionDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DeductionDetailsUtil} to access the deduction details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the deduction details in the entity cache if it is enabled.
	*
	* @param deductionDetails the deduction details
	*/
	public void cacheResult(DeductionDetails deductionDetails);

	/**
	* Caches the deduction detailses in the entity cache if it is enabled.
	*
	* @param deductionDetailses the deduction detailses
	*/
	public void cacheResult(java.util.List<DeductionDetails> deductionDetailses);

	/**
	* Creates a new deduction details with the primary key. Does not add the deduction details to the database.
	*
	* @param deductionDetailsSid the primary key for the new deduction details
	* @return the new deduction details
	*/
	public DeductionDetails create(int deductionDetailsSid);

	/**
	* Removes the deduction details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param deductionDetailsSid the primary key of the deduction details
	* @return the deduction details that was removed
	* @throws NoSuchDeductionDetailsException if a deduction details with the primary key could not be found
	*/
	public DeductionDetails remove(int deductionDetailsSid)
		throws NoSuchDeductionDetailsException;

	public DeductionDetails updateImpl(DeductionDetails deductionDetails);

	/**
	* Returns the deduction details with the primary key or throws a {@link NoSuchDeductionDetailsException} if it could not be found.
	*
	* @param deductionDetailsSid the primary key of the deduction details
	* @return the deduction details
	* @throws NoSuchDeductionDetailsException if a deduction details with the primary key could not be found
	*/
	public DeductionDetails findByPrimaryKey(int deductionDetailsSid)
		throws NoSuchDeductionDetailsException;

	/**
	* Returns the deduction details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param deductionDetailsSid the primary key of the deduction details
	* @return the deduction details, or <code>null</code> if a deduction details with the primary key could not be found
	*/
	public DeductionDetails fetchByPrimaryKey(int deductionDetailsSid);

	@Override
	public java.util.Map<java.io.Serializable, DeductionDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the deduction detailses.
	*
	* @return the deduction detailses
	*/
	public java.util.List<DeductionDetails> findAll();

	/**
	* Returns a range of all the deduction detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeductionDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of deduction detailses
	* @param end the upper bound of the range of deduction detailses (not inclusive)
	* @return the range of deduction detailses
	*/
	public java.util.List<DeductionDetails> findAll(int start, int end);

	/**
	* Returns an ordered range of all the deduction detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeductionDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of deduction detailses
	* @param end the upper bound of the range of deduction detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of deduction detailses
	*/
	public java.util.List<DeductionDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DeductionDetails> orderByComparator);

	/**
	* Returns an ordered range of all the deduction detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeductionDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of deduction detailses
	* @param end the upper bound of the range of deduction detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of deduction detailses
	*/
	public java.util.List<DeductionDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DeductionDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the deduction detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of deduction detailses.
	*
	* @return the number of deduction detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}