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

import com.stpl.app.parttwo.exception.NoSuchIvldItemIdentifierException;
import com.stpl.app.parttwo.model.IvldItemIdentifier;

/**
 * The persistence interface for the ivld item identifier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.IvldItemIdentifierPersistenceImpl
 * @see IvldItemIdentifierUtil
 * @generated
 */
@ProviderType
public interface IvldItemIdentifierPersistence extends BasePersistence<IvldItemIdentifier> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link IvldItemIdentifierUtil} to access the ivld item identifier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ivld item identifier in the entity cache if it is enabled.
	*
	* @param ivldItemIdentifier the ivld item identifier
	*/
	public void cacheResult(IvldItemIdentifier ivldItemIdentifier);

	/**
	* Caches the ivld item identifiers in the entity cache if it is enabled.
	*
	* @param ivldItemIdentifiers the ivld item identifiers
	*/
	public void cacheResult(
		java.util.List<IvldItemIdentifier> ivldItemIdentifiers);

	/**
	* Creates a new ivld item identifier with the primary key. Does not add the ivld item identifier to the database.
	*
	* @param ivldItemIdentifierSid the primary key for the new ivld item identifier
	* @return the new ivld item identifier
	*/
	public IvldItemIdentifier create(int ivldItemIdentifierSid);

	/**
	* Removes the ivld item identifier with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldItemIdentifierSid the primary key of the ivld item identifier
	* @return the ivld item identifier that was removed
	* @throws NoSuchIvldItemIdentifierException if a ivld item identifier with the primary key could not be found
	*/
	public IvldItemIdentifier remove(int ivldItemIdentifierSid)
		throws NoSuchIvldItemIdentifierException;

	public IvldItemIdentifier updateImpl(IvldItemIdentifier ivldItemIdentifier);

	/**
	* Returns the ivld item identifier with the primary key or throws a {@link NoSuchIvldItemIdentifierException} if it could not be found.
	*
	* @param ivldItemIdentifierSid the primary key of the ivld item identifier
	* @return the ivld item identifier
	* @throws NoSuchIvldItemIdentifierException if a ivld item identifier with the primary key could not be found
	*/
	public IvldItemIdentifier findByPrimaryKey(int ivldItemIdentifierSid)
		throws NoSuchIvldItemIdentifierException;

	/**
	* Returns the ivld item identifier with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldItemIdentifierSid the primary key of the ivld item identifier
	* @return the ivld item identifier, or <code>null</code> if a ivld item identifier with the primary key could not be found
	*/
	public IvldItemIdentifier fetchByPrimaryKey(int ivldItemIdentifierSid);

	@Override
	public java.util.Map<java.io.Serializable, IvldItemIdentifier> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ivld item identifiers.
	*
	* @return the ivld item identifiers
	*/
	public java.util.List<IvldItemIdentifier> findAll();

	/**
	* Returns a range of all the ivld item identifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld item identifiers
	* @param end the upper bound of the range of ivld item identifiers (not inclusive)
	* @return the range of ivld item identifiers
	*/
	public java.util.List<IvldItemIdentifier> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ivld item identifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld item identifiers
	* @param end the upper bound of the range of ivld item identifiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivld item identifiers
	*/
	public java.util.List<IvldItemIdentifier> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldItemIdentifier> orderByComparator);

	/**
	* Returns an ordered range of all the ivld item identifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld item identifiers
	* @param end the upper bound of the range of ivld item identifiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivld item identifiers
	*/
	public java.util.List<IvldItemIdentifier> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldItemIdentifier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ivld item identifiers from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ivld item identifiers.
	*
	* @return the number of ivld item identifiers
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}