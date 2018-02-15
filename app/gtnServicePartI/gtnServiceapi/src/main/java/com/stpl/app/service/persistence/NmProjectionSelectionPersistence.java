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

import com.stpl.app.exception.NoSuchNmProjectionSelectionException;
import com.stpl.app.model.NmProjectionSelection;

/**
 * The persistence interface for the nm projection selection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.NmProjectionSelectionPersistenceImpl
 * @see NmProjectionSelectionUtil
 * @generated
 */
@ProviderType
public interface NmProjectionSelectionPersistence extends BasePersistence<NmProjectionSelection> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link NmProjectionSelectionUtil} to access the nm projection selection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the nm projection selection in the entity cache if it is enabled.
	*
	* @param nmProjectionSelection the nm projection selection
	*/
	public void cacheResult(NmProjectionSelection nmProjectionSelection);

	/**
	* Caches the nm projection selections in the entity cache if it is enabled.
	*
	* @param nmProjectionSelections the nm projection selections
	*/
	public void cacheResult(
		java.util.List<NmProjectionSelection> nmProjectionSelections);

	/**
	* Creates a new nm projection selection with the primary key. Does not add the nm projection selection to the database.
	*
	* @param nmProjectionSelectionSid the primary key for the new nm projection selection
	* @return the new nm projection selection
	*/
	public NmProjectionSelection create(int nmProjectionSelectionSid);

	/**
	* Removes the nm projection selection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param nmProjectionSelectionSid the primary key of the nm projection selection
	* @return the nm projection selection that was removed
	* @throws NoSuchNmProjectionSelectionException if a nm projection selection with the primary key could not be found
	*/
	public NmProjectionSelection remove(int nmProjectionSelectionSid)
		throws NoSuchNmProjectionSelectionException;

	public NmProjectionSelection updateImpl(
		NmProjectionSelection nmProjectionSelection);

	/**
	* Returns the nm projection selection with the primary key or throws a {@link NoSuchNmProjectionSelectionException} if it could not be found.
	*
	* @param nmProjectionSelectionSid the primary key of the nm projection selection
	* @return the nm projection selection
	* @throws NoSuchNmProjectionSelectionException if a nm projection selection with the primary key could not be found
	*/
	public NmProjectionSelection findByPrimaryKey(int nmProjectionSelectionSid)
		throws NoSuchNmProjectionSelectionException;

	/**
	* Returns the nm projection selection with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param nmProjectionSelectionSid the primary key of the nm projection selection
	* @return the nm projection selection, or <code>null</code> if a nm projection selection with the primary key could not be found
	*/
	public NmProjectionSelection fetchByPrimaryKey(int nmProjectionSelectionSid);

	@Override
	public java.util.Map<java.io.Serializable, NmProjectionSelection> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the nm projection selections.
	*
	* @return the nm projection selections
	*/
	public java.util.List<NmProjectionSelection> findAll();

	/**
	* Returns a range of all the nm projection selections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm projection selections
	* @param end the upper bound of the range of nm projection selections (not inclusive)
	* @return the range of nm projection selections
	*/
	public java.util.List<NmProjectionSelection> findAll(int start, int end);

	/**
	* Returns an ordered range of all the nm projection selections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm projection selections
	* @param end the upper bound of the range of nm projection selections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of nm projection selections
	*/
	public java.util.List<NmProjectionSelection> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NmProjectionSelection> orderByComparator);

	/**
	* Returns an ordered range of all the nm projection selections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm projection selections
	* @param end the upper bound of the range of nm projection selections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of nm projection selections
	*/
	public java.util.List<NmProjectionSelection> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NmProjectionSelection> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the nm projection selections from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of nm projection selections.
	*
	* @return the number of nm projection selections
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}