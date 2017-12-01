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

import com.stpl.app.exception.NoSuchDocDetailsException;
import com.stpl.app.model.DocDetails;

/**
 * The persistence interface for the doc details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.DocDetailsPersistenceImpl
 * @see DocDetailsUtil
 * @generated
 */
@ProviderType
public interface DocDetailsPersistence extends BasePersistence<DocDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DocDetailsUtil} to access the doc details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the doc details in the entity cache if it is enabled.
	*
	* @param docDetails the doc details
	*/
	public void cacheResult(DocDetails docDetails);

	/**
	* Caches the doc detailses in the entity cache if it is enabled.
	*
	* @param docDetailses the doc detailses
	*/
	public void cacheResult(java.util.List<DocDetails> docDetailses);

	/**
	* Creates a new doc details with the primary key. Does not add the doc details to the database.
	*
	* @param docDetailsId the primary key for the new doc details
	* @return the new doc details
	*/
	public DocDetails create(int docDetailsId);

	/**
	* Removes the doc details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param docDetailsId the primary key of the doc details
	* @return the doc details that was removed
	* @throws NoSuchDocDetailsException if a doc details with the primary key could not be found
	*/
	public DocDetails remove(int docDetailsId) throws NoSuchDocDetailsException;

	public DocDetails updateImpl(DocDetails docDetails);

	/**
	* Returns the doc details with the primary key or throws a {@link NoSuchDocDetailsException} if it could not be found.
	*
	* @param docDetailsId the primary key of the doc details
	* @return the doc details
	* @throws NoSuchDocDetailsException if a doc details with the primary key could not be found
	*/
	public DocDetails findByPrimaryKey(int docDetailsId)
		throws NoSuchDocDetailsException;

	/**
	* Returns the doc details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param docDetailsId the primary key of the doc details
	* @return the doc details, or <code>null</code> if a doc details with the primary key could not be found
	*/
	public DocDetails fetchByPrimaryKey(int docDetailsId);

	@Override
	public java.util.Map<java.io.Serializable, DocDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the doc detailses.
	*
	* @return the doc detailses
	*/
	public java.util.List<DocDetails> findAll();

	/**
	* Returns a range of all the doc detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of doc detailses
	* @param end the upper bound of the range of doc detailses (not inclusive)
	* @return the range of doc detailses
	*/
	public java.util.List<DocDetails> findAll(int start, int end);

	/**
	* Returns an ordered range of all the doc detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of doc detailses
	* @param end the upper bound of the range of doc detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of doc detailses
	*/
	public java.util.List<DocDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DocDetails> orderByComparator);

	/**
	* Returns an ordered range of all the doc detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DocDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of doc detailses
	* @param end the upper bound of the range of doc detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of doc detailses
	*/
	public java.util.List<DocDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DocDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the doc detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of doc detailses.
	*
	* @return the number of doc detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}