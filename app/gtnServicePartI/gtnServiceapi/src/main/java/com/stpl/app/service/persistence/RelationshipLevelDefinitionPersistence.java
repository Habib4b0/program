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

import com.stpl.app.exception.NoSuchRelationshipLevelDefinitionException;
import com.stpl.app.model.RelationshipLevelDefinition;

/**
 * The persistence interface for the relationship level definition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.RelationshipLevelDefinitionPersistenceImpl
 * @see RelationshipLevelDefinitionUtil
 * @generated
 */
@ProviderType
public interface RelationshipLevelDefinitionPersistence extends BasePersistence<RelationshipLevelDefinition> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RelationshipLevelDefinitionUtil} to access the relationship level definition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the relationship level definition in the entity cache if it is enabled.
	*
	* @param relationshipLevelDefinition the relationship level definition
	*/
	public void cacheResult(
		RelationshipLevelDefinition relationshipLevelDefinition);

	/**
	* Caches the relationship level definitions in the entity cache if it is enabled.
	*
	* @param relationshipLevelDefinitions the relationship level definitions
	*/
	public void cacheResult(
		java.util.List<RelationshipLevelDefinition> relationshipLevelDefinitions);

	/**
	* Creates a new relationship level definition with the primary key. Does not add the relationship level definition to the database.
	*
	* @param relationshipLevelSid the primary key for the new relationship level definition
	* @return the new relationship level definition
	*/
	public RelationshipLevelDefinition create(int relationshipLevelSid);

	/**
	* Removes the relationship level definition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param relationshipLevelSid the primary key of the relationship level definition
	* @return the relationship level definition that was removed
	* @throws NoSuchRelationshipLevelDefinitionException if a relationship level definition with the primary key could not be found
	*/
	public RelationshipLevelDefinition remove(int relationshipLevelSid)
		throws NoSuchRelationshipLevelDefinitionException;

	public RelationshipLevelDefinition updateImpl(
		RelationshipLevelDefinition relationshipLevelDefinition);

	/**
	* Returns the relationship level definition with the primary key or throws a {@link NoSuchRelationshipLevelDefinitionException} if it could not be found.
	*
	* @param relationshipLevelSid the primary key of the relationship level definition
	* @return the relationship level definition
	* @throws NoSuchRelationshipLevelDefinitionException if a relationship level definition with the primary key could not be found
	*/
	public RelationshipLevelDefinition findByPrimaryKey(
		int relationshipLevelSid)
		throws NoSuchRelationshipLevelDefinitionException;

	/**
	* Returns the relationship level definition with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param relationshipLevelSid the primary key of the relationship level definition
	* @return the relationship level definition, or <code>null</code> if a relationship level definition with the primary key could not be found
	*/
	public RelationshipLevelDefinition fetchByPrimaryKey(
		int relationshipLevelSid);

	@Override
	public java.util.Map<java.io.Serializable, RelationshipLevelDefinition> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the relationship level definitions.
	*
	* @return the relationship level definitions
	*/
	public java.util.List<RelationshipLevelDefinition> findAll();

	/**
	* Returns a range of all the relationship level definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RelationshipLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of relationship level definitions
	* @param end the upper bound of the range of relationship level definitions (not inclusive)
	* @return the range of relationship level definitions
	*/
	public java.util.List<RelationshipLevelDefinition> findAll(int start,
		int end);

	/**
	* Returns an ordered range of all the relationship level definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RelationshipLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of relationship level definitions
	* @param end the upper bound of the range of relationship level definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of relationship level definitions
	*/
	public java.util.List<RelationshipLevelDefinition> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<RelationshipLevelDefinition> orderByComparator);

	/**
	* Returns an ordered range of all the relationship level definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RelationshipLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of relationship level definitions
	* @param end the upper bound of the range of relationship level definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of relationship level definitions
	*/
	public java.util.List<RelationshipLevelDefinition> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<RelationshipLevelDefinition> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the relationship level definitions from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of relationship level definitions.
	*
	* @return the number of relationship level definitions
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}