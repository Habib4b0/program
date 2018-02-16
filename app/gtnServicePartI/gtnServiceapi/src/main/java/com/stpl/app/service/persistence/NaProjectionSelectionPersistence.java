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

import com.stpl.app.exception.NoSuchNaProjectionSelectionException;
import com.stpl.app.model.NaProjectionSelection;

/**
 * The persistence interface for the na projection selection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.NaProjectionSelectionPersistenceImpl
 * @see NaProjectionSelectionUtil
 * @generated
 */
@ProviderType
public interface NaProjectionSelectionPersistence extends BasePersistence<NaProjectionSelection> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link NaProjectionSelectionUtil} to access the na projection selection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the na projection selection in the entity cache if it is enabled.
	*
	* @param naProjectionSelection the na projection selection
	*/
	public void cacheResult(NaProjectionSelection naProjectionSelection);

	/**
	* Caches the na projection selections in the entity cache if it is enabled.
	*
	* @param naProjectionSelections the na projection selections
	*/
	public void cacheResult(
		java.util.List<NaProjectionSelection> naProjectionSelections);

	/**
	* Creates a new na projection selection with the primary key. Does not add the na projection selection to the database.
	*
	* @param naProjectionSelectionSid the primary key for the new na projection selection
	* @return the new na projection selection
	*/
	public NaProjectionSelection create(int naProjectionSelectionSid);

	/**
	* Removes the na projection selection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param naProjectionSelectionSid the primary key of the na projection selection
	* @return the na projection selection that was removed
	* @throws NoSuchNaProjectionSelectionException if a na projection selection with the primary key could not be found
	*/
	public NaProjectionSelection remove(int naProjectionSelectionSid)
		throws NoSuchNaProjectionSelectionException;

	public NaProjectionSelection updateImpl(
		NaProjectionSelection naProjectionSelection);

	/**
	* Returns the na projection selection with the primary key or throws a {@link NoSuchNaProjectionSelectionException} if it could not be found.
	*
	* @param naProjectionSelectionSid the primary key of the na projection selection
	* @return the na projection selection
	* @throws NoSuchNaProjectionSelectionException if a na projection selection with the primary key could not be found
	*/
	public NaProjectionSelection findByPrimaryKey(int naProjectionSelectionSid)
		throws NoSuchNaProjectionSelectionException;

	/**
	* Returns the na projection selection with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param naProjectionSelectionSid the primary key of the na projection selection
	* @return the na projection selection, or <code>null</code> if a na projection selection with the primary key could not be found
	*/
	public NaProjectionSelection fetchByPrimaryKey(int naProjectionSelectionSid);

	@Override
	public java.util.Map<java.io.Serializable, NaProjectionSelection> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the na projection selections.
	*
	* @return the na projection selections
	*/
	public java.util.List<NaProjectionSelection> findAll();

	/**
	* Returns a range of all the na projection selections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NaProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of na projection selections
	* @param end the upper bound of the range of na projection selections (not inclusive)
	* @return the range of na projection selections
	*/
	public java.util.List<NaProjectionSelection> findAll(int start, int end);

	/**
	* Returns an ordered range of all the na projection selections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NaProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of na projection selections
	* @param end the upper bound of the range of na projection selections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of na projection selections
	*/
	public java.util.List<NaProjectionSelection> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NaProjectionSelection> orderByComparator);

	/**
	* Returns an ordered range of all the na projection selections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NaProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of na projection selections
	* @param end the upper bound of the range of na projection selections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of na projection selections
	*/
	public java.util.List<NaProjectionSelection> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NaProjectionSelection> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the na projection selections from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of na projection selections.
	*
	* @return the number of na projection selections
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}