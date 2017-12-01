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

import com.stpl.app.exception.NoSuchHistHierarchyDefinitionException;
import com.stpl.app.model.HistHierarchyDefinition;

/**
 * The persistence interface for the hist hierarchy definition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.HistHierarchyDefinitionPersistenceImpl
 * @see HistHierarchyDefinitionUtil
 * @generated
 */
@ProviderType
public interface HistHierarchyDefinitionPersistence extends BasePersistence<HistHierarchyDefinition> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HistHierarchyDefinitionUtil} to access the hist hierarchy definition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the hist hierarchy definition in the entity cache if it is enabled.
	*
	* @param histHierarchyDefinition the hist hierarchy definition
	*/
	public void cacheResult(HistHierarchyDefinition histHierarchyDefinition);

	/**
	* Caches the hist hierarchy definitions in the entity cache if it is enabled.
	*
	* @param histHierarchyDefinitions the hist hierarchy definitions
	*/
	public void cacheResult(
		java.util.List<HistHierarchyDefinition> histHierarchyDefinitions);

	/**
	* Creates a new hist hierarchy definition with the primary key. Does not add the hist hierarchy definition to the database.
	*
	* @param histHierarchyDefinitionPK the primary key for the new hist hierarchy definition
	* @return the new hist hierarchy definition
	*/
	public HistHierarchyDefinition create(
		HistHierarchyDefinitionPK histHierarchyDefinitionPK);

	/**
	* Removes the hist hierarchy definition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param histHierarchyDefinitionPK the primary key of the hist hierarchy definition
	* @return the hist hierarchy definition that was removed
	* @throws NoSuchHistHierarchyDefinitionException if a hist hierarchy definition with the primary key could not be found
	*/
	public HistHierarchyDefinition remove(
		HistHierarchyDefinitionPK histHierarchyDefinitionPK)
		throws NoSuchHistHierarchyDefinitionException;

	public HistHierarchyDefinition updateImpl(
		HistHierarchyDefinition histHierarchyDefinition);

	/**
	* Returns the hist hierarchy definition with the primary key or throws a {@link NoSuchHistHierarchyDefinitionException} if it could not be found.
	*
	* @param histHierarchyDefinitionPK the primary key of the hist hierarchy definition
	* @return the hist hierarchy definition
	* @throws NoSuchHistHierarchyDefinitionException if a hist hierarchy definition with the primary key could not be found
	*/
	public HistHierarchyDefinition findByPrimaryKey(
		HistHierarchyDefinitionPK histHierarchyDefinitionPK)
		throws NoSuchHistHierarchyDefinitionException;

	/**
	* Returns the hist hierarchy definition with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param histHierarchyDefinitionPK the primary key of the hist hierarchy definition
	* @return the hist hierarchy definition, or <code>null</code> if a hist hierarchy definition with the primary key could not be found
	*/
	public HistHierarchyDefinition fetchByPrimaryKey(
		HistHierarchyDefinitionPK histHierarchyDefinitionPK);

	@Override
	public java.util.Map<java.io.Serializable, HistHierarchyDefinition> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the hist hierarchy definitions.
	*
	* @return the hist hierarchy definitions
	*/
	public java.util.List<HistHierarchyDefinition> findAll();

	/**
	* Returns a range of all the hist hierarchy definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistHierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist hierarchy definitions
	* @param end the upper bound of the range of hist hierarchy definitions (not inclusive)
	* @return the range of hist hierarchy definitions
	*/
	public java.util.List<HistHierarchyDefinition> findAll(int start, int end);

	/**
	* Returns an ordered range of all the hist hierarchy definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistHierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist hierarchy definitions
	* @param end the upper bound of the range of hist hierarchy definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of hist hierarchy definitions
	*/
	public java.util.List<HistHierarchyDefinition> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HistHierarchyDefinition> orderByComparator);

	/**
	* Returns an ordered range of all the hist hierarchy definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistHierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist hierarchy definitions
	* @param end the upper bound of the range of hist hierarchy definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of hist hierarchy definitions
	*/
	public java.util.List<HistHierarchyDefinition> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HistHierarchyDefinition> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the hist hierarchy definitions from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of hist hierarchy definitions.
	*
	* @return the number of hist hierarchy definitions
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}