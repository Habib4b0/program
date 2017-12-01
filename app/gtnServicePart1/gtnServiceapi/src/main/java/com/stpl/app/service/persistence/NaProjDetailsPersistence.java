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

import com.stpl.app.exception.NoSuchNaProjDetailsException;
import com.stpl.app.model.NaProjDetails;

/**
 * The persistence interface for the na proj details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.NaProjDetailsPersistenceImpl
 * @see NaProjDetailsUtil
 * @generated
 */
@ProviderType
public interface NaProjDetailsPersistence extends BasePersistence<NaProjDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link NaProjDetailsUtil} to access the na proj details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the na proj details in the entity cache if it is enabled.
	*
	* @param naProjDetails the na proj details
	*/
	public void cacheResult(NaProjDetails naProjDetails);

	/**
	* Caches the na proj detailses in the entity cache if it is enabled.
	*
	* @param naProjDetailses the na proj detailses
	*/
	public void cacheResult(java.util.List<NaProjDetails> naProjDetailses);

	/**
	* Creates a new na proj details with the primary key. Does not add the na proj details to the database.
	*
	* @param naProjDetailsSid the primary key for the new na proj details
	* @return the new na proj details
	*/
	public NaProjDetails create(int naProjDetailsSid);

	/**
	* Removes the na proj details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param naProjDetailsSid the primary key of the na proj details
	* @return the na proj details that was removed
	* @throws NoSuchNaProjDetailsException if a na proj details with the primary key could not be found
	*/
	public NaProjDetails remove(int naProjDetailsSid)
		throws NoSuchNaProjDetailsException;

	public NaProjDetails updateImpl(NaProjDetails naProjDetails);

	/**
	* Returns the na proj details with the primary key or throws a {@link NoSuchNaProjDetailsException} if it could not be found.
	*
	* @param naProjDetailsSid the primary key of the na proj details
	* @return the na proj details
	* @throws NoSuchNaProjDetailsException if a na proj details with the primary key could not be found
	*/
	public NaProjDetails findByPrimaryKey(int naProjDetailsSid)
		throws NoSuchNaProjDetailsException;

	/**
	* Returns the na proj details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param naProjDetailsSid the primary key of the na proj details
	* @return the na proj details, or <code>null</code> if a na proj details with the primary key could not be found
	*/
	public NaProjDetails fetchByPrimaryKey(int naProjDetailsSid);

	@Override
	public java.util.Map<java.io.Serializable, NaProjDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the na proj detailses.
	*
	* @return the na proj detailses
	*/
	public java.util.List<NaProjDetails> findAll();

	/**
	* Returns a range of all the na proj detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NaProjDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of na proj detailses
	* @param end the upper bound of the range of na proj detailses (not inclusive)
	* @return the range of na proj detailses
	*/
	public java.util.List<NaProjDetails> findAll(int start, int end);

	/**
	* Returns an ordered range of all the na proj detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NaProjDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of na proj detailses
	* @param end the upper bound of the range of na proj detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of na proj detailses
	*/
	public java.util.List<NaProjDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NaProjDetails> orderByComparator);

	/**
	* Returns an ordered range of all the na proj detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NaProjDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of na proj detailses
	* @param end the upper bound of the range of na proj detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of na proj detailses
	*/
	public java.util.List<NaProjDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NaProjDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the na proj detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of na proj detailses.
	*
	* @return the number of na proj detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}