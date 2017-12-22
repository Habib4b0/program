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

import com.stpl.app.parttwo.exception.NoSuchAcFdAdjustmentSelectionException;
import com.stpl.app.parttwo.model.AcFdAdjustmentSelection;

/**
 * The persistence interface for the ac fd adjustment selection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.AcFdAdjustmentSelectionPersistenceImpl
 * @see AcFdAdjustmentSelectionUtil
 * @generated
 */
@ProviderType
public interface AcFdAdjustmentSelectionPersistence extends BasePersistence<AcFdAdjustmentSelection> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AcFdAdjustmentSelectionUtil} to access the ac fd adjustment selection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ac fd adjustment selection in the entity cache if it is enabled.
	*
	* @param acFdAdjustmentSelection the ac fd adjustment selection
	*/
	public void cacheResult(AcFdAdjustmentSelection acFdAdjustmentSelection);

	/**
	* Caches the ac fd adjustment selections in the entity cache if it is enabled.
	*
	* @param acFdAdjustmentSelections the ac fd adjustment selections
	*/
	public void cacheResult(
		java.util.List<AcFdAdjustmentSelection> acFdAdjustmentSelections);

	/**
	* Creates a new ac fd adjustment selection with the primary key. Does not add the ac fd adjustment selection to the database.
	*
	* @param accClosureMasterSid the primary key for the new ac fd adjustment selection
	* @return the new ac fd adjustment selection
	*/
	public AcFdAdjustmentSelection create(int accClosureMasterSid);

	/**
	* Removes the ac fd adjustment selection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accClosureMasterSid the primary key of the ac fd adjustment selection
	* @return the ac fd adjustment selection that was removed
	* @throws NoSuchAcFdAdjustmentSelectionException if a ac fd adjustment selection with the primary key could not be found
	*/
	public AcFdAdjustmentSelection remove(int accClosureMasterSid)
		throws NoSuchAcFdAdjustmentSelectionException;

	public AcFdAdjustmentSelection updateImpl(
		AcFdAdjustmentSelection acFdAdjustmentSelection);

	/**
	* Returns the ac fd adjustment selection with the primary key or throws a {@link NoSuchAcFdAdjustmentSelectionException} if it could not be found.
	*
	* @param accClosureMasterSid the primary key of the ac fd adjustment selection
	* @return the ac fd adjustment selection
	* @throws NoSuchAcFdAdjustmentSelectionException if a ac fd adjustment selection with the primary key could not be found
	*/
	public AcFdAdjustmentSelection findByPrimaryKey(int accClosureMasterSid)
		throws NoSuchAcFdAdjustmentSelectionException;

	/**
	* Returns the ac fd adjustment selection with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accClosureMasterSid the primary key of the ac fd adjustment selection
	* @return the ac fd adjustment selection, or <code>null</code> if a ac fd adjustment selection with the primary key could not be found
	*/
	public AcFdAdjustmentSelection fetchByPrimaryKey(int accClosureMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, AcFdAdjustmentSelection> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ac fd adjustment selections.
	*
	* @return the ac fd adjustment selections
	*/
	public java.util.List<AcFdAdjustmentSelection> findAll();

	/**
	* Returns a range of all the ac fd adjustment selections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AcFdAdjustmentSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ac fd adjustment selections
	* @param end the upper bound of the range of ac fd adjustment selections (not inclusive)
	* @return the range of ac fd adjustment selections
	*/
	public java.util.List<AcFdAdjustmentSelection> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ac fd adjustment selections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AcFdAdjustmentSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ac fd adjustment selections
	* @param end the upper bound of the range of ac fd adjustment selections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ac fd adjustment selections
	*/
	public java.util.List<AcFdAdjustmentSelection> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AcFdAdjustmentSelection> orderByComparator);

	/**
	* Returns an ordered range of all the ac fd adjustment selections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AcFdAdjustmentSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ac fd adjustment selections
	* @param end the upper bound of the range of ac fd adjustment selections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ac fd adjustment selections
	*/
	public java.util.List<AcFdAdjustmentSelection> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AcFdAdjustmentSelection> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ac fd adjustment selections from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ac fd adjustment selections.
	*
	* @return the number of ac fd adjustment selections
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}