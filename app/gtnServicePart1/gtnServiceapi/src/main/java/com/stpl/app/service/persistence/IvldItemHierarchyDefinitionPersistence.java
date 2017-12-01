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

import com.stpl.app.exception.NoSuchIvldItemHierarchyDefinitionException;
import com.stpl.app.model.IvldItemHierarchyDefinition;

/**
 * The persistence interface for the ivld item hierarchy definition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.IvldItemHierarchyDefinitionPersistenceImpl
 * @see IvldItemHierarchyDefinitionUtil
 * @generated
 */
@ProviderType
public interface IvldItemHierarchyDefinitionPersistence extends BasePersistence<IvldItemHierarchyDefinition> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link IvldItemHierarchyDefinitionUtil} to access the ivld item hierarchy definition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ivld item hierarchy definition in the entity cache if it is enabled.
	*
	* @param ivldItemHierarchyDefinition the ivld item hierarchy definition
	*/
	public void cacheResult(
		IvldItemHierarchyDefinition ivldItemHierarchyDefinition);

	/**
	* Caches the ivld item hierarchy definitions in the entity cache if it is enabled.
	*
	* @param ivldItemHierarchyDefinitions the ivld item hierarchy definitions
	*/
	public void cacheResult(
		java.util.List<IvldItemHierarchyDefinition> ivldItemHierarchyDefinitions);

	/**
	* Creates a new ivld item hierarchy definition with the primary key. Does not add the ivld item hierarchy definition to the database.
	*
	* @param ivldItemHierarchyDefinitionSid the primary key for the new ivld item hierarchy definition
	* @return the new ivld item hierarchy definition
	*/
	public IvldItemHierarchyDefinition create(
		int ivldItemHierarchyDefinitionSid);

	/**
	* Removes the ivld item hierarchy definition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldItemHierarchyDefinitionSid the primary key of the ivld item hierarchy definition
	* @return the ivld item hierarchy definition that was removed
	* @throws NoSuchIvldItemHierarchyDefinitionException if a ivld item hierarchy definition with the primary key could not be found
	*/
	public IvldItemHierarchyDefinition remove(
		int ivldItemHierarchyDefinitionSid)
		throws NoSuchIvldItemHierarchyDefinitionException;

	public IvldItemHierarchyDefinition updateImpl(
		IvldItemHierarchyDefinition ivldItemHierarchyDefinition);

	/**
	* Returns the ivld item hierarchy definition with the primary key or throws a {@link NoSuchIvldItemHierarchyDefinitionException} if it could not be found.
	*
	* @param ivldItemHierarchyDefinitionSid the primary key of the ivld item hierarchy definition
	* @return the ivld item hierarchy definition
	* @throws NoSuchIvldItemHierarchyDefinitionException if a ivld item hierarchy definition with the primary key could not be found
	*/
	public IvldItemHierarchyDefinition findByPrimaryKey(
		int ivldItemHierarchyDefinitionSid)
		throws NoSuchIvldItemHierarchyDefinitionException;

	/**
	* Returns the ivld item hierarchy definition with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldItemHierarchyDefinitionSid the primary key of the ivld item hierarchy definition
	* @return the ivld item hierarchy definition, or <code>null</code> if a ivld item hierarchy definition with the primary key could not be found
	*/
	public IvldItemHierarchyDefinition fetchByPrimaryKey(
		int ivldItemHierarchyDefinitionSid);

	@Override
	public java.util.Map<java.io.Serializable, IvldItemHierarchyDefinition> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ivld item hierarchy definitions.
	*
	* @return the ivld item hierarchy definitions
	*/
	public java.util.List<IvldItemHierarchyDefinition> findAll();

	/**
	* Returns a range of all the ivld item hierarchy definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldItemHierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld item hierarchy definitions
	* @param end the upper bound of the range of ivld item hierarchy definitions (not inclusive)
	* @return the range of ivld item hierarchy definitions
	*/
	public java.util.List<IvldItemHierarchyDefinition> findAll(int start,
		int end);

	/**
	* Returns an ordered range of all the ivld item hierarchy definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldItemHierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld item hierarchy definitions
	* @param end the upper bound of the range of ivld item hierarchy definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivld item hierarchy definitions
	*/
	public java.util.List<IvldItemHierarchyDefinition> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldItemHierarchyDefinition> orderByComparator);

	/**
	* Returns an ordered range of all the ivld item hierarchy definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldItemHierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld item hierarchy definitions
	* @param end the upper bound of the range of ivld item hierarchy definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivld item hierarchy definitions
	*/
	public java.util.List<IvldItemHierarchyDefinition> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldItemHierarchyDefinition> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ivld item hierarchy definitions from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ivld item hierarchy definitions.
	*
	* @return the number of ivld item hierarchy definitions
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}