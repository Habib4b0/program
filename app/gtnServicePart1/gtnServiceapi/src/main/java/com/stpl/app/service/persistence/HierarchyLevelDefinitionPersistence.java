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

import com.stpl.app.exception.NoSuchHierarchyLevelDefinitionException;
import com.stpl.app.model.HierarchyLevelDefinition;

/**
 * The persistence interface for the hierarchy level definition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.HierarchyLevelDefinitionPersistenceImpl
 * @see HierarchyLevelDefinitionUtil
 * @generated
 */
@ProviderType
public interface HierarchyLevelDefinitionPersistence extends BasePersistence<HierarchyLevelDefinition> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HierarchyLevelDefinitionUtil} to access the hierarchy level definition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the hierarchy level definition in the entity cache if it is enabled.
	*
	* @param hierarchyLevelDefinition the hierarchy level definition
	*/
	public void cacheResult(HierarchyLevelDefinition hierarchyLevelDefinition);

	/**
	* Caches the hierarchy level definitions in the entity cache if it is enabled.
	*
	* @param hierarchyLevelDefinitions the hierarchy level definitions
	*/
	public void cacheResult(
		java.util.List<HierarchyLevelDefinition> hierarchyLevelDefinitions);

	/**
	* Creates a new hierarchy level definition with the primary key. Does not add the hierarchy level definition to the database.
	*
	* @param hierarchyLevelDefinitionSid the primary key for the new hierarchy level definition
	* @return the new hierarchy level definition
	*/
	public HierarchyLevelDefinition create(int hierarchyLevelDefinitionSid);

	/**
	* Removes the hierarchy level definition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hierarchyLevelDefinitionSid the primary key of the hierarchy level definition
	* @return the hierarchy level definition that was removed
	* @throws NoSuchHierarchyLevelDefinitionException if a hierarchy level definition with the primary key could not be found
	*/
	public HierarchyLevelDefinition remove(int hierarchyLevelDefinitionSid)
		throws NoSuchHierarchyLevelDefinitionException;

	public HierarchyLevelDefinition updateImpl(
		HierarchyLevelDefinition hierarchyLevelDefinition);

	/**
	* Returns the hierarchy level definition with the primary key or throws a {@link NoSuchHierarchyLevelDefinitionException} if it could not be found.
	*
	* @param hierarchyLevelDefinitionSid the primary key of the hierarchy level definition
	* @return the hierarchy level definition
	* @throws NoSuchHierarchyLevelDefinitionException if a hierarchy level definition with the primary key could not be found
	*/
	public HierarchyLevelDefinition findByPrimaryKey(
		int hierarchyLevelDefinitionSid)
		throws NoSuchHierarchyLevelDefinitionException;

	/**
	* Returns the hierarchy level definition with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hierarchyLevelDefinitionSid the primary key of the hierarchy level definition
	* @return the hierarchy level definition, or <code>null</code> if a hierarchy level definition with the primary key could not be found
	*/
	public HierarchyLevelDefinition fetchByPrimaryKey(
		int hierarchyLevelDefinitionSid);

	@Override
	public java.util.Map<java.io.Serializable, HierarchyLevelDefinition> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the hierarchy level definitions.
	*
	* @return the hierarchy level definitions
	*/
	public java.util.List<HierarchyLevelDefinition> findAll();

	/**
	* Returns a range of all the hierarchy level definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HierarchyLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hierarchy level definitions
	* @param end the upper bound of the range of hierarchy level definitions (not inclusive)
	* @return the range of hierarchy level definitions
	*/
	public java.util.List<HierarchyLevelDefinition> findAll(int start, int end);

	/**
	* Returns an ordered range of all the hierarchy level definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HierarchyLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hierarchy level definitions
	* @param end the upper bound of the range of hierarchy level definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of hierarchy level definitions
	*/
	public java.util.List<HierarchyLevelDefinition> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HierarchyLevelDefinition> orderByComparator);

	/**
	* Returns an ordered range of all the hierarchy level definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HierarchyLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hierarchy level definitions
	* @param end the upper bound of the range of hierarchy level definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of hierarchy level definitions
	*/
	public java.util.List<HierarchyLevelDefinition> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HierarchyLevelDefinition> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the hierarchy level definitions from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of hierarchy level definitions.
	*
	* @return the number of hierarchy level definitions
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}