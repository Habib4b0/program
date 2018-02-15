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

import com.stpl.app.exception.NoSuchMProjectionSelectionException;
import com.stpl.app.model.MProjectionSelection;

/**
 * The persistence interface for the m projection selection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.MProjectionSelectionPersistenceImpl
 * @see MProjectionSelectionUtil
 * @generated
 */
@ProviderType
public interface MProjectionSelectionPersistence extends BasePersistence<MProjectionSelection> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MProjectionSelectionUtil} to access the m projection selection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the m projection selection in the entity cache if it is enabled.
	*
	* @param mProjectionSelection the m projection selection
	*/
	public void cacheResult(MProjectionSelection mProjectionSelection);

	/**
	* Caches the m projection selections in the entity cache if it is enabled.
	*
	* @param mProjectionSelections the m projection selections
	*/
	public void cacheResult(
		java.util.List<MProjectionSelection> mProjectionSelections);

	/**
	* Creates a new m projection selection with the primary key. Does not add the m projection selection to the database.
	*
	* @param mProjectionSelectionSid the primary key for the new m projection selection
	* @return the new m projection selection
	*/
	public MProjectionSelection create(int mProjectionSelectionSid);

	/**
	* Removes the m projection selection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param mProjectionSelectionSid the primary key of the m projection selection
	* @return the m projection selection that was removed
	* @throws NoSuchMProjectionSelectionException if a m projection selection with the primary key could not be found
	*/
	public MProjectionSelection remove(int mProjectionSelectionSid)
		throws NoSuchMProjectionSelectionException;

	public MProjectionSelection updateImpl(
		MProjectionSelection mProjectionSelection);

	/**
	* Returns the m projection selection with the primary key or throws a {@link NoSuchMProjectionSelectionException} if it could not be found.
	*
	* @param mProjectionSelectionSid the primary key of the m projection selection
	* @return the m projection selection
	* @throws NoSuchMProjectionSelectionException if a m projection selection with the primary key could not be found
	*/
	public MProjectionSelection findByPrimaryKey(int mProjectionSelectionSid)
		throws NoSuchMProjectionSelectionException;

	/**
	* Returns the m projection selection with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param mProjectionSelectionSid the primary key of the m projection selection
	* @return the m projection selection, or <code>null</code> if a m projection selection with the primary key could not be found
	*/
	public MProjectionSelection fetchByPrimaryKey(int mProjectionSelectionSid);

	@Override
	public java.util.Map<java.io.Serializable, MProjectionSelection> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the m projection selections.
	*
	* @return the m projection selections
	*/
	public java.util.List<MProjectionSelection> findAll();

	/**
	* Returns a range of all the m projection selections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of m projection selections
	* @param end the upper bound of the range of m projection selections (not inclusive)
	* @return the range of m projection selections
	*/
	public java.util.List<MProjectionSelection> findAll(int start, int end);

	/**
	* Returns an ordered range of all the m projection selections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of m projection selections
	* @param end the upper bound of the range of m projection selections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of m projection selections
	*/
	public java.util.List<MProjectionSelection> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MProjectionSelection> orderByComparator);

	/**
	* Returns an ordered range of all the m projection selections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of m projection selections
	* @param end the upper bound of the range of m projection selections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of m projection selections
	*/
	public java.util.List<MProjectionSelection> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MProjectionSelection> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the m projection selections from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of m projection selections.
	*
	* @return the number of m projection selections
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}