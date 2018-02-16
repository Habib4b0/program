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

import com.stpl.app.exception.NoSuchIvldFormulaDetailsException;
import com.stpl.app.model.IvldFormulaDetails;

/**
 * The persistence interface for the ivld formula details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.IvldFormulaDetailsPersistenceImpl
 * @see IvldFormulaDetailsUtil
 * @generated
 */
@ProviderType
public interface IvldFormulaDetailsPersistence extends BasePersistence<IvldFormulaDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link IvldFormulaDetailsUtil} to access the ivld formula details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ivld formula details in the entity cache if it is enabled.
	*
	* @param ivldFormulaDetails the ivld formula details
	*/
	public void cacheResult(IvldFormulaDetails ivldFormulaDetails);

	/**
	* Caches the ivld formula detailses in the entity cache if it is enabled.
	*
	* @param ivldFormulaDetailses the ivld formula detailses
	*/
	public void cacheResult(
		java.util.List<IvldFormulaDetails> ivldFormulaDetailses);

	/**
	* Creates a new ivld formula details with the primary key. Does not add the ivld formula details to the database.
	*
	* @param ivldFormulaDetailsSid the primary key for the new ivld formula details
	* @return the new ivld formula details
	*/
	public IvldFormulaDetails create(int ivldFormulaDetailsSid);

	/**
	* Removes the ivld formula details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldFormulaDetailsSid the primary key of the ivld formula details
	* @return the ivld formula details that was removed
	* @throws NoSuchIvldFormulaDetailsException if a ivld formula details with the primary key could not be found
	*/
	public IvldFormulaDetails remove(int ivldFormulaDetailsSid)
		throws NoSuchIvldFormulaDetailsException;

	public IvldFormulaDetails updateImpl(IvldFormulaDetails ivldFormulaDetails);

	/**
	* Returns the ivld formula details with the primary key or throws a {@link NoSuchIvldFormulaDetailsException} if it could not be found.
	*
	* @param ivldFormulaDetailsSid the primary key of the ivld formula details
	* @return the ivld formula details
	* @throws NoSuchIvldFormulaDetailsException if a ivld formula details with the primary key could not be found
	*/
	public IvldFormulaDetails findByPrimaryKey(int ivldFormulaDetailsSid)
		throws NoSuchIvldFormulaDetailsException;

	/**
	* Returns the ivld formula details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldFormulaDetailsSid the primary key of the ivld formula details
	* @return the ivld formula details, or <code>null</code> if a ivld formula details with the primary key could not be found
	*/
	public IvldFormulaDetails fetchByPrimaryKey(int ivldFormulaDetailsSid);

	@Override
	public java.util.Map<java.io.Serializable, IvldFormulaDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ivld formula detailses.
	*
	* @return the ivld formula detailses
	*/
	public java.util.List<IvldFormulaDetails> findAll();

	/**
	* Returns a range of all the ivld formula detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldFormulaDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld formula detailses
	* @param end the upper bound of the range of ivld formula detailses (not inclusive)
	* @return the range of ivld formula detailses
	*/
	public java.util.List<IvldFormulaDetails> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ivld formula detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldFormulaDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld formula detailses
	* @param end the upper bound of the range of ivld formula detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivld formula detailses
	*/
	public java.util.List<IvldFormulaDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldFormulaDetails> orderByComparator);

	/**
	* Returns an ordered range of all the ivld formula detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldFormulaDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld formula detailses
	* @param end the upper bound of the range of ivld formula detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivld formula detailses
	*/
	public java.util.List<IvldFormulaDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldFormulaDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ivld formula detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ivld formula detailses.
	*
	* @return the number of ivld formula detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}