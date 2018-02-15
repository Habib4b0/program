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

import com.stpl.app.exception.NoSuchAdditionalNotesException;
import com.stpl.app.model.AdditionalNotes;

/**
 * The persistence interface for the additional notes service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.AdditionalNotesPersistenceImpl
 * @see AdditionalNotesUtil
 * @generated
 */
@ProviderType
public interface AdditionalNotesPersistence extends BasePersistence<AdditionalNotes> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AdditionalNotesUtil} to access the additional notes persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the additional notes in the entity cache if it is enabled.
	*
	* @param additionalNotes the additional notes
	*/
	public void cacheResult(AdditionalNotes additionalNotes);

	/**
	* Caches the additional noteses in the entity cache if it is enabled.
	*
	* @param additionalNoteses the additional noteses
	*/
	public void cacheResult(java.util.List<AdditionalNotes> additionalNoteses);

	/**
	* Creates a new additional notes with the primary key. Does not add the additional notes to the database.
	*
	* @param additionalNotesId the primary key for the new additional notes
	* @return the new additional notes
	*/
	public AdditionalNotes create(int additionalNotesId);

	/**
	* Removes the additional notes with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param additionalNotesId the primary key of the additional notes
	* @return the additional notes that was removed
	* @throws NoSuchAdditionalNotesException if a additional notes with the primary key could not be found
	*/
	public AdditionalNotes remove(int additionalNotesId)
		throws NoSuchAdditionalNotesException;

	public AdditionalNotes updateImpl(AdditionalNotes additionalNotes);

	/**
	* Returns the additional notes with the primary key or throws a {@link NoSuchAdditionalNotesException} if it could not be found.
	*
	* @param additionalNotesId the primary key of the additional notes
	* @return the additional notes
	* @throws NoSuchAdditionalNotesException if a additional notes with the primary key could not be found
	*/
	public AdditionalNotes findByPrimaryKey(int additionalNotesId)
		throws NoSuchAdditionalNotesException;

	/**
	* Returns the additional notes with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param additionalNotesId the primary key of the additional notes
	* @return the additional notes, or <code>null</code> if a additional notes with the primary key could not be found
	*/
	public AdditionalNotes fetchByPrimaryKey(int additionalNotesId);

	@Override
	public java.util.Map<java.io.Serializable, AdditionalNotes> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the additional noteses.
	*
	* @return the additional noteses
	*/
	public java.util.List<AdditionalNotes> findAll();

	/**
	* Returns a range of all the additional noteses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AdditionalNotesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of additional noteses
	* @param end the upper bound of the range of additional noteses (not inclusive)
	* @return the range of additional noteses
	*/
	public java.util.List<AdditionalNotes> findAll(int start, int end);

	/**
	* Returns an ordered range of all the additional noteses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AdditionalNotesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of additional noteses
	* @param end the upper bound of the range of additional noteses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of additional noteses
	*/
	public java.util.List<AdditionalNotes> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AdditionalNotes> orderByComparator);

	/**
	* Returns an ordered range of all the additional noteses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AdditionalNotesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of additional noteses
	* @param end the upper bound of the range of additional noteses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of additional noteses
	*/
	public java.util.List<AdditionalNotes> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AdditionalNotes> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the additional noteses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of additional noteses.
	*
	* @return the number of additional noteses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}