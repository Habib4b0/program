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

import com.stpl.app.exception.NoSuchHierarchyDefinitionException;
import com.stpl.app.model.HierarchyDefinition;

/**
 * The persistence interface for the hierarchy definition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.HierarchyDefinitionPersistenceImpl
 * @see HierarchyDefinitionUtil
 * @generated
 */
@ProviderType
public interface HierarchyDefinitionPersistence extends BasePersistence<HierarchyDefinition> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HierarchyDefinitionUtil} to access the hierarchy definition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the hierarchy definition in the entity cache if it is enabled.
	*
	* @param hierarchyDefinition the hierarchy definition
	*/
	public void cacheResult(HierarchyDefinition hierarchyDefinition);

	/**
	* Caches the hierarchy definitions in the entity cache if it is enabled.
	*
	* @param hierarchyDefinitions the hierarchy definitions
	*/
	public void cacheResult(
		java.util.List<HierarchyDefinition> hierarchyDefinitions);

	/**
	* Creates a new hierarchy definition with the primary key. Does not add the hierarchy definition to the database.
	*
	* @param hierarchyDefinitionSid the primary key for the new hierarchy definition
	* @return the new hierarchy definition
	*/
	public HierarchyDefinition create(int hierarchyDefinitionSid);

	/**
	* Removes the hierarchy definition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hierarchyDefinitionSid the primary key of the hierarchy definition
	* @return the hierarchy definition that was removed
	* @throws NoSuchHierarchyDefinitionException if a hierarchy definition with the primary key could not be found
	*/
	public HierarchyDefinition remove(int hierarchyDefinitionSid)
		throws NoSuchHierarchyDefinitionException;

	public HierarchyDefinition updateImpl(
		HierarchyDefinition hierarchyDefinition);

	/**
	* Returns the hierarchy definition with the primary key or throws a {@link NoSuchHierarchyDefinitionException} if it could not be found.
	*
	* @param hierarchyDefinitionSid the primary key of the hierarchy definition
	* @return the hierarchy definition
	* @throws NoSuchHierarchyDefinitionException if a hierarchy definition with the primary key could not be found
	*/
	public HierarchyDefinition findByPrimaryKey(int hierarchyDefinitionSid)
		throws NoSuchHierarchyDefinitionException;

	/**
	* Returns the hierarchy definition with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hierarchyDefinitionSid the primary key of the hierarchy definition
	* @return the hierarchy definition, or <code>null</code> if a hierarchy definition with the primary key could not be found
	*/
	public HierarchyDefinition fetchByPrimaryKey(int hierarchyDefinitionSid);

	@Override
	public java.util.Map<java.io.Serializable, HierarchyDefinition> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the hierarchy definitions.
	*
	* @return the hierarchy definitions
	*/
	public java.util.List<HierarchyDefinition> findAll();

	/**
	* Returns a range of all the hierarchy definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hierarchy definitions
	* @param end the upper bound of the range of hierarchy definitions (not inclusive)
	* @return the range of hierarchy definitions
	*/
	public java.util.List<HierarchyDefinition> findAll(int start, int end);

	/**
	* Returns an ordered range of all the hierarchy definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hierarchy definitions
	* @param end the upper bound of the range of hierarchy definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of hierarchy definitions
	*/
	public java.util.List<HierarchyDefinition> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HierarchyDefinition> orderByComparator);

	/**
	* Returns an ordered range of all the hierarchy definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hierarchy definitions
	* @param end the upper bound of the range of hierarchy definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of hierarchy definitions
	*/
	public java.util.List<HierarchyDefinition> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HierarchyDefinition> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the hierarchy definitions from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of hierarchy definitions.
	*
	* @return the number of hierarchy definitions
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}