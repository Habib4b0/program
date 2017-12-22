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

import com.stpl.app.parttwo.exception.NoSuchIvldcompanyIdentifierException;
import com.stpl.app.parttwo.model.IvldcompanyIdentifier;

/**
 * The persistence interface for the ivldcompany identifier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.IvldcompanyIdentifierPersistenceImpl
 * @see IvldcompanyIdentifierUtil
 * @generated
 */
@ProviderType
public interface IvldcompanyIdentifierPersistence extends BasePersistence<IvldcompanyIdentifier> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link IvldcompanyIdentifierUtil} to access the ivldcompany identifier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ivldcompany identifier in the entity cache if it is enabled.
	*
	* @param ivldcompanyIdentifier the ivldcompany identifier
	*/
	public void cacheResult(IvldcompanyIdentifier ivldcompanyIdentifier);

	/**
	* Caches the ivldcompany identifiers in the entity cache if it is enabled.
	*
	* @param ivldcompanyIdentifiers the ivldcompany identifiers
	*/
	public void cacheResult(
		java.util.List<IvldcompanyIdentifier> ivldcompanyIdentifiers);

	/**
	* Creates a new ivldcompany identifier with the primary key. Does not add the ivldcompany identifier to the database.
	*
	* @param ivldcompanyIdentifierSid the primary key for the new ivldcompany identifier
	* @return the new ivldcompany identifier
	*/
	public IvldcompanyIdentifier create(int ivldcompanyIdentifierSid);

	/**
	* Removes the ivldcompany identifier with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldcompanyIdentifierSid the primary key of the ivldcompany identifier
	* @return the ivldcompany identifier that was removed
	* @throws NoSuchIvldcompanyIdentifierException if a ivldcompany identifier with the primary key could not be found
	*/
	public IvldcompanyIdentifier remove(int ivldcompanyIdentifierSid)
		throws NoSuchIvldcompanyIdentifierException;

	public IvldcompanyIdentifier updateImpl(
		IvldcompanyIdentifier ivldcompanyIdentifier);

	/**
	* Returns the ivldcompany identifier with the primary key or throws a {@link NoSuchIvldcompanyIdentifierException} if it could not be found.
	*
	* @param ivldcompanyIdentifierSid the primary key of the ivldcompany identifier
	* @return the ivldcompany identifier
	* @throws NoSuchIvldcompanyIdentifierException if a ivldcompany identifier with the primary key could not be found
	*/
	public IvldcompanyIdentifier findByPrimaryKey(int ivldcompanyIdentifierSid)
		throws NoSuchIvldcompanyIdentifierException;

	/**
	* Returns the ivldcompany identifier with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldcompanyIdentifierSid the primary key of the ivldcompany identifier
	* @return the ivldcompany identifier, or <code>null</code> if a ivldcompany identifier with the primary key could not be found
	*/
	public IvldcompanyIdentifier fetchByPrimaryKey(int ivldcompanyIdentifierSid);

	@Override
	public java.util.Map<java.io.Serializable, IvldcompanyIdentifier> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ivldcompany identifiers.
	*
	* @return the ivldcompany identifiers
	*/
	public java.util.List<IvldcompanyIdentifier> findAll();

	/**
	* Returns a range of all the ivldcompany identifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldcompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivldcompany identifiers
	* @param end the upper bound of the range of ivldcompany identifiers (not inclusive)
	* @return the range of ivldcompany identifiers
	*/
	public java.util.List<IvldcompanyIdentifier> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ivldcompany identifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldcompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivldcompany identifiers
	* @param end the upper bound of the range of ivldcompany identifiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivldcompany identifiers
	*/
	public java.util.List<IvldcompanyIdentifier> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldcompanyIdentifier> orderByComparator);

	/**
	* Returns an ordered range of all the ivldcompany identifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldcompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivldcompany identifiers
	* @param end the upper bound of the range of ivldcompany identifiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivldcompany identifiers
	*/
	public java.util.List<IvldcompanyIdentifier> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldcompanyIdentifier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ivldcompany identifiers from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ivldcompany identifiers.
	*
	* @return the number of ivldcompany identifiers
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}