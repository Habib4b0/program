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

import com.stpl.app.parttwo.exception.NoSuchAcBrMethodologyDetailsException;
import com.stpl.app.parttwo.model.AcBrMethodologyDetails;

/**
 * The persistence interface for the ac br methodology details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.AcBrMethodologyDetailsPersistenceImpl
 * @see AcBrMethodologyDetailsUtil
 * @generated
 */
@ProviderType
public interface AcBrMethodologyDetailsPersistence extends BasePersistence<AcBrMethodologyDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AcBrMethodologyDetailsUtil} to access the ac br methodology details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ac br methodology details in the entity cache if it is enabled.
	*
	* @param acBrMethodologyDetails the ac br methodology details
	*/
	public void cacheResult(AcBrMethodologyDetails acBrMethodologyDetails);

	/**
	* Caches the ac br methodology detailses in the entity cache if it is enabled.
	*
	* @param acBrMethodologyDetailses the ac br methodology detailses
	*/
	public void cacheResult(
		java.util.List<AcBrMethodologyDetails> acBrMethodologyDetailses);

	/**
	* Creates a new ac br methodology details with the primary key. Does not add the ac br methodology details to the database.
	*
	* @param acBrMethodologyDetailsSid the primary key for the new ac br methodology details
	* @return the new ac br methodology details
	*/
	public AcBrMethodologyDetails create(int acBrMethodologyDetailsSid);

	/**
	* Removes the ac br methodology details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param acBrMethodologyDetailsSid the primary key of the ac br methodology details
	* @return the ac br methodology details that was removed
	* @throws NoSuchAcBrMethodologyDetailsException if a ac br methodology details with the primary key could not be found
	*/
	public AcBrMethodologyDetails remove(int acBrMethodologyDetailsSid)
		throws NoSuchAcBrMethodologyDetailsException;

	public AcBrMethodologyDetails updateImpl(
		AcBrMethodologyDetails acBrMethodologyDetails);

	/**
	* Returns the ac br methodology details with the primary key or throws a {@link NoSuchAcBrMethodologyDetailsException} if it could not be found.
	*
	* @param acBrMethodologyDetailsSid the primary key of the ac br methodology details
	* @return the ac br methodology details
	* @throws NoSuchAcBrMethodologyDetailsException if a ac br methodology details with the primary key could not be found
	*/
	public AcBrMethodologyDetails findByPrimaryKey(
		int acBrMethodologyDetailsSid)
		throws NoSuchAcBrMethodologyDetailsException;

	/**
	* Returns the ac br methodology details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param acBrMethodologyDetailsSid the primary key of the ac br methodology details
	* @return the ac br methodology details, or <code>null</code> if a ac br methodology details with the primary key could not be found
	*/
	public AcBrMethodologyDetails fetchByPrimaryKey(
		int acBrMethodologyDetailsSid);

	@Override
	public java.util.Map<java.io.Serializable, AcBrMethodologyDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ac br methodology detailses.
	*
	* @return the ac br methodology detailses
	*/
	public java.util.List<AcBrMethodologyDetails> findAll();

	/**
	* Returns a range of all the ac br methodology detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AcBrMethodologyDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ac br methodology detailses
	* @param end the upper bound of the range of ac br methodology detailses (not inclusive)
	* @return the range of ac br methodology detailses
	*/
	public java.util.List<AcBrMethodologyDetails> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ac br methodology detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AcBrMethodologyDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ac br methodology detailses
	* @param end the upper bound of the range of ac br methodology detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ac br methodology detailses
	*/
	public java.util.List<AcBrMethodologyDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AcBrMethodologyDetails> orderByComparator);

	/**
	* Returns an ordered range of all the ac br methodology detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AcBrMethodologyDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ac br methodology detailses
	* @param end the upper bound of the range of ac br methodology detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ac br methodology detailses
	*/
	public java.util.List<AcBrMethodologyDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AcBrMethodologyDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ac br methodology detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ac br methodology detailses.
	*
	* @return the number of ac br methodology detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}