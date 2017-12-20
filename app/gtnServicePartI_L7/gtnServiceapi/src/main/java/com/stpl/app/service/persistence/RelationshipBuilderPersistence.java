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

import com.stpl.app.exception.NoSuchRelationshipBuilderException;
import com.stpl.app.model.RelationshipBuilder;

/**
 * The persistence interface for the relationship builder service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.RelationshipBuilderPersistenceImpl
 * @see RelationshipBuilderUtil
 * @generated
 */
@ProviderType
public interface RelationshipBuilderPersistence extends BasePersistence<RelationshipBuilder> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RelationshipBuilderUtil} to access the relationship builder persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the relationship builder in the entity cache if it is enabled.
	*
	* @param relationshipBuilder the relationship builder
	*/
	public void cacheResult(RelationshipBuilder relationshipBuilder);

	/**
	* Caches the relationship builders in the entity cache if it is enabled.
	*
	* @param relationshipBuilders the relationship builders
	*/
	public void cacheResult(
		java.util.List<RelationshipBuilder> relationshipBuilders);

	/**
	* Creates a new relationship builder with the primary key. Does not add the relationship builder to the database.
	*
	* @param relationshipBuilderSid the primary key for the new relationship builder
	* @return the new relationship builder
	*/
	public RelationshipBuilder create(int relationshipBuilderSid);

	/**
	* Removes the relationship builder with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param relationshipBuilderSid the primary key of the relationship builder
	* @return the relationship builder that was removed
	* @throws NoSuchRelationshipBuilderException if a relationship builder with the primary key could not be found
	*/
	public RelationshipBuilder remove(int relationshipBuilderSid)
		throws NoSuchRelationshipBuilderException;

	public RelationshipBuilder updateImpl(
		RelationshipBuilder relationshipBuilder);

	/**
	* Returns the relationship builder with the primary key or throws a {@link NoSuchRelationshipBuilderException} if it could not be found.
	*
	* @param relationshipBuilderSid the primary key of the relationship builder
	* @return the relationship builder
	* @throws NoSuchRelationshipBuilderException if a relationship builder with the primary key could not be found
	*/
	public RelationshipBuilder findByPrimaryKey(int relationshipBuilderSid)
		throws NoSuchRelationshipBuilderException;

	/**
	* Returns the relationship builder with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param relationshipBuilderSid the primary key of the relationship builder
	* @return the relationship builder, or <code>null</code> if a relationship builder with the primary key could not be found
	*/
	public RelationshipBuilder fetchByPrimaryKey(int relationshipBuilderSid);

	@Override
	public java.util.Map<java.io.Serializable, RelationshipBuilder> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the relationship builders.
	*
	* @return the relationship builders
	*/
	public java.util.List<RelationshipBuilder> findAll();

	/**
	* Returns a range of all the relationship builders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RelationshipBuilderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of relationship builders
	* @param end the upper bound of the range of relationship builders (not inclusive)
	* @return the range of relationship builders
	*/
	public java.util.List<RelationshipBuilder> findAll(int start, int end);

	/**
	* Returns an ordered range of all the relationship builders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RelationshipBuilderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of relationship builders
	* @param end the upper bound of the range of relationship builders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of relationship builders
	*/
	public java.util.List<RelationshipBuilder> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RelationshipBuilder> orderByComparator);

	/**
	* Returns an ordered range of all the relationship builders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RelationshipBuilderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of relationship builders
	* @param end the upper bound of the range of relationship builders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of relationship builders
	*/
	public java.util.List<RelationshipBuilder> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RelationshipBuilder> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the relationship builders from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of relationship builders.
	*
	* @return the number of relationship builders
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}