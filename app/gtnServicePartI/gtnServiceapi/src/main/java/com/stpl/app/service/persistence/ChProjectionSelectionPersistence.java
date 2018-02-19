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

import com.stpl.app.exception.NoSuchChProjectionSelectionException;
import com.stpl.app.model.ChProjectionSelection;

/**
 * The persistence interface for the ch projection selection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.ChProjectionSelectionPersistenceImpl
 * @see ChProjectionSelectionUtil
 * @generated
 */
@ProviderType
public interface ChProjectionSelectionPersistence extends BasePersistence<ChProjectionSelection> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ChProjectionSelectionUtil} to access the ch projection selection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ch projection selection in the entity cache if it is enabled.
	*
	* @param chProjectionSelection the ch projection selection
	*/
	public void cacheResult(ChProjectionSelection chProjectionSelection);

	/**
	* Caches the ch projection selections in the entity cache if it is enabled.
	*
	* @param chProjectionSelections the ch projection selections
	*/
	public void cacheResult(
		java.util.List<ChProjectionSelection> chProjectionSelections);

	/**
	* Creates a new ch projection selection with the primary key. Does not add the ch projection selection to the database.
	*
	* @param chProjectionSelectionSid the primary key for the new ch projection selection
	* @return the new ch projection selection
	*/
	public ChProjectionSelection create(int chProjectionSelectionSid);

	/**
	* Removes the ch projection selection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param chProjectionSelectionSid the primary key of the ch projection selection
	* @return the ch projection selection that was removed
	* @throws NoSuchChProjectionSelectionException if a ch projection selection with the primary key could not be found
	*/
	public ChProjectionSelection remove(int chProjectionSelectionSid)
		throws NoSuchChProjectionSelectionException;

	public ChProjectionSelection updateImpl(
		ChProjectionSelection chProjectionSelection);

	/**
	* Returns the ch projection selection with the primary key or throws a {@link NoSuchChProjectionSelectionException} if it could not be found.
	*
	* @param chProjectionSelectionSid the primary key of the ch projection selection
	* @return the ch projection selection
	* @throws NoSuchChProjectionSelectionException if a ch projection selection with the primary key could not be found
	*/
	public ChProjectionSelection findByPrimaryKey(int chProjectionSelectionSid)
		throws NoSuchChProjectionSelectionException;

	/**
	* Returns the ch projection selection with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param chProjectionSelectionSid the primary key of the ch projection selection
	* @return the ch projection selection, or <code>null</code> if a ch projection selection with the primary key could not be found
	*/
	public ChProjectionSelection fetchByPrimaryKey(int chProjectionSelectionSid);

	@Override
	public java.util.Map<java.io.Serializable, ChProjectionSelection> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ch projection selections.
	*
	* @return the ch projection selections
	*/
	public java.util.List<ChProjectionSelection> findAll();

	/**
	* Returns a range of all the ch projection selections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch projection selections
	* @param end the upper bound of the range of ch projection selections (not inclusive)
	* @return the range of ch projection selections
	*/
	public java.util.List<ChProjectionSelection> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ch projection selections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch projection selections
	* @param end the upper bound of the range of ch projection selections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ch projection selections
	*/
	public java.util.List<ChProjectionSelection> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ChProjectionSelection> orderByComparator);

	/**
	* Returns an ordered range of all the ch projection selections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch projection selections
	* @param end the upper bound of the range of ch projection selections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ch projection selections
	*/
	public java.util.List<ChProjectionSelection> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ChProjectionSelection> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ch projection selections from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ch projection selections.
	*
	* @return the number of ch projection selections
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}