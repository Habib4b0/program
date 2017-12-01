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

package com.stpl.app.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchRelationshipLevelDefinitionException;
import com.stpl.app.model.RelationshipLevelDefinition;
import com.stpl.app.service.RelationshipLevelDefinitionLocalServiceUtil;
import com.stpl.app.service.persistence.RelationshipLevelDefinitionPersistence;
import com.stpl.app.service.persistence.RelationshipLevelDefinitionUtil;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.junit.runner.RunWith;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class RelationshipLevelDefinitionPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = RelationshipLevelDefinitionUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<RelationshipLevelDefinition> iterator = _relationshipLevelDefinitions.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		RelationshipLevelDefinition relationshipLevelDefinition = _persistence.create(pk);

		Assert.assertNotNull(relationshipLevelDefinition);

		Assert.assertEquals(relationshipLevelDefinition.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		RelationshipLevelDefinition newRelationshipLevelDefinition = addRelationshipLevelDefinition();

		_persistence.remove(newRelationshipLevelDefinition);

		RelationshipLevelDefinition existingRelationshipLevelDefinition = _persistence.fetchByPrimaryKey(newRelationshipLevelDefinition.getPrimaryKey());

		Assert.assertNull(existingRelationshipLevelDefinition);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addRelationshipLevelDefinition();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		RelationshipLevelDefinition newRelationshipLevelDefinition = _persistence.create(pk);

		newRelationshipLevelDefinition.setRelationshipLevelValues(RandomTestUtil.randomString());

		newRelationshipLevelDefinition.setHierarchyLevelDefinitionSid(RandomTestUtil.nextInt());

		newRelationshipLevelDefinition.setParentNode(RandomTestUtil.randomString());

		newRelationshipLevelDefinition.setVersionNo(RandomTestUtil.nextInt());

		newRelationshipLevelDefinition.setRelationshipBuilderSid(RandomTestUtil.nextInt());

		newRelationshipLevelDefinition.setModifiedDate(RandomTestUtil.nextDate());

		newRelationshipLevelDefinition.setCreatedBy(RandomTestUtil.nextInt());

		newRelationshipLevelDefinition.setCreatedDate(RandomTestUtil.nextDate());

		newRelationshipLevelDefinition.setLevelNo(RandomTestUtil.randomString());

		newRelationshipLevelDefinition.setModifiedBy(RandomTestUtil.nextInt());

		newRelationshipLevelDefinition.setHierarchyNo(RandomTestUtil.randomString());

		newRelationshipLevelDefinition.setFlag(RandomTestUtil.randomString());

		newRelationshipLevelDefinition.setLevelName(RandomTestUtil.randomString());

		newRelationshipLevelDefinition.setParentHierarchyNo(RandomTestUtil.randomString());

		_relationshipLevelDefinitions.add(_persistence.update(
				newRelationshipLevelDefinition));

		RelationshipLevelDefinition existingRelationshipLevelDefinition = _persistence.findByPrimaryKey(newRelationshipLevelDefinition.getPrimaryKey());

		Assert.assertEquals(existingRelationshipLevelDefinition.getRelationshipLevelValues(),
			newRelationshipLevelDefinition.getRelationshipLevelValues());
		Assert.assertEquals(existingRelationshipLevelDefinition.getHierarchyLevelDefinitionSid(),
			newRelationshipLevelDefinition.getHierarchyLevelDefinitionSid());
		Assert.assertEquals(existingRelationshipLevelDefinition.getParentNode(),
			newRelationshipLevelDefinition.getParentNode());
		Assert.assertEquals(existingRelationshipLevelDefinition.getVersionNo(),
			newRelationshipLevelDefinition.getVersionNo());
		Assert.assertEquals(existingRelationshipLevelDefinition.getRelationshipBuilderSid(),
			newRelationshipLevelDefinition.getRelationshipBuilderSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingRelationshipLevelDefinition.getModifiedDate()),
			Time.getShortTimestamp(
				newRelationshipLevelDefinition.getModifiedDate()));
		Assert.assertEquals(existingRelationshipLevelDefinition.getCreatedBy(),
			newRelationshipLevelDefinition.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingRelationshipLevelDefinition.getCreatedDate()),
			Time.getShortTimestamp(
				newRelationshipLevelDefinition.getCreatedDate()));
		Assert.assertEquals(existingRelationshipLevelDefinition.getLevelNo(),
			newRelationshipLevelDefinition.getLevelNo());
		Assert.assertEquals(existingRelationshipLevelDefinition.getModifiedBy(),
			newRelationshipLevelDefinition.getModifiedBy());
		Assert.assertEquals(existingRelationshipLevelDefinition.getHierarchyNo(),
			newRelationshipLevelDefinition.getHierarchyNo());
		Assert.assertEquals(existingRelationshipLevelDefinition.getRelationshipLevelSid(),
			newRelationshipLevelDefinition.getRelationshipLevelSid());
		Assert.assertEquals(existingRelationshipLevelDefinition.getFlag(),
			newRelationshipLevelDefinition.getFlag());
		Assert.assertEquals(existingRelationshipLevelDefinition.getLevelName(),
			newRelationshipLevelDefinition.getLevelName());
		Assert.assertEquals(existingRelationshipLevelDefinition.getParentHierarchyNo(),
			newRelationshipLevelDefinition.getParentHierarchyNo());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		RelationshipLevelDefinition newRelationshipLevelDefinition = addRelationshipLevelDefinition();

		RelationshipLevelDefinition existingRelationshipLevelDefinition = _persistence.findByPrimaryKey(newRelationshipLevelDefinition.getPrimaryKey());

		Assert.assertEquals(existingRelationshipLevelDefinition,
			newRelationshipLevelDefinition);
	}

	@Test(expected = NoSuchRelationshipLevelDefinitionException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<RelationshipLevelDefinition> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("RELATIONSHIP_LEVEL_DEFINITION",
			"relationshipLevelValues", true, "hierarchyLevelDefinitionSid",
			true, "parentNode", true, "versionNo", true,
			"relationshipBuilderSid", true, "modifiedDate", true, "createdBy",
			true, "createdDate", true, "levelNo", true, "modifiedBy", true,
			"hierarchyNo", true, "relationshipLevelSid", true, "flag", true,
			"levelName", true, "parentHierarchyNo", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		RelationshipLevelDefinition newRelationshipLevelDefinition = addRelationshipLevelDefinition();

		RelationshipLevelDefinition existingRelationshipLevelDefinition = _persistence.fetchByPrimaryKey(newRelationshipLevelDefinition.getPrimaryKey());

		Assert.assertEquals(existingRelationshipLevelDefinition,
			newRelationshipLevelDefinition);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		RelationshipLevelDefinition missingRelationshipLevelDefinition = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingRelationshipLevelDefinition);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		RelationshipLevelDefinition newRelationshipLevelDefinition1 = addRelationshipLevelDefinition();
		RelationshipLevelDefinition newRelationshipLevelDefinition2 = addRelationshipLevelDefinition();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRelationshipLevelDefinition1.getPrimaryKey());
		primaryKeys.add(newRelationshipLevelDefinition2.getPrimaryKey());

		Map<Serializable, RelationshipLevelDefinition> relationshipLevelDefinitions =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, relationshipLevelDefinitions.size());
		Assert.assertEquals(newRelationshipLevelDefinition1,
			relationshipLevelDefinitions.get(
				newRelationshipLevelDefinition1.getPrimaryKey()));
		Assert.assertEquals(newRelationshipLevelDefinition2,
			relationshipLevelDefinitions.get(
				newRelationshipLevelDefinition2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, RelationshipLevelDefinition> relationshipLevelDefinitions =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(relationshipLevelDefinitions.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		RelationshipLevelDefinition newRelationshipLevelDefinition = addRelationshipLevelDefinition();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRelationshipLevelDefinition.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, RelationshipLevelDefinition> relationshipLevelDefinitions =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, relationshipLevelDefinitions.size());
		Assert.assertEquals(newRelationshipLevelDefinition,
			relationshipLevelDefinitions.get(
				newRelationshipLevelDefinition.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, RelationshipLevelDefinition> relationshipLevelDefinitions =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(relationshipLevelDefinitions.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		RelationshipLevelDefinition newRelationshipLevelDefinition = addRelationshipLevelDefinition();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRelationshipLevelDefinition.getPrimaryKey());

		Map<Serializable, RelationshipLevelDefinition> relationshipLevelDefinitions =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, relationshipLevelDefinitions.size());
		Assert.assertEquals(newRelationshipLevelDefinition,
			relationshipLevelDefinitions.get(
				newRelationshipLevelDefinition.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = RelationshipLevelDefinitionLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<RelationshipLevelDefinition>() {
				@Override
				public void performAction(
					RelationshipLevelDefinition relationshipLevelDefinition) {
					Assert.assertNotNull(relationshipLevelDefinition);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		RelationshipLevelDefinition newRelationshipLevelDefinition = addRelationshipLevelDefinition();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RelationshipLevelDefinition.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("relationshipLevelSid",
				newRelationshipLevelDefinition.getRelationshipLevelSid()));

		List<RelationshipLevelDefinition> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		RelationshipLevelDefinition existingRelationshipLevelDefinition = result.get(0);

		Assert.assertEquals(existingRelationshipLevelDefinition,
			newRelationshipLevelDefinition);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RelationshipLevelDefinition.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("relationshipLevelSid",
				RandomTestUtil.nextInt()));

		List<RelationshipLevelDefinition> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		RelationshipLevelDefinition newRelationshipLevelDefinition = addRelationshipLevelDefinition();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RelationshipLevelDefinition.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"relationshipLevelSid"));

		Object newRelationshipLevelSid = newRelationshipLevelDefinition.getRelationshipLevelSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("relationshipLevelSid",
				new Object[] { newRelationshipLevelSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingRelationshipLevelSid = result.get(0);

		Assert.assertEquals(existingRelationshipLevelSid,
			newRelationshipLevelSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RelationshipLevelDefinition.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"relationshipLevelSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("relationshipLevelSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected RelationshipLevelDefinition addRelationshipLevelDefinition()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		RelationshipLevelDefinition relationshipLevelDefinition = _persistence.create(pk);

		relationshipLevelDefinition.setRelationshipLevelValues(RandomTestUtil.randomString());

		relationshipLevelDefinition.setHierarchyLevelDefinitionSid(RandomTestUtil.nextInt());

		relationshipLevelDefinition.setParentNode(RandomTestUtil.randomString());

		relationshipLevelDefinition.setVersionNo(RandomTestUtil.nextInt());

		relationshipLevelDefinition.setRelationshipBuilderSid(RandomTestUtil.nextInt());

		relationshipLevelDefinition.setModifiedDate(RandomTestUtil.nextDate());

		relationshipLevelDefinition.setCreatedBy(RandomTestUtil.nextInt());

		relationshipLevelDefinition.setCreatedDate(RandomTestUtil.nextDate());

		relationshipLevelDefinition.setLevelNo(RandomTestUtil.randomString());

		relationshipLevelDefinition.setModifiedBy(RandomTestUtil.nextInt());

		relationshipLevelDefinition.setHierarchyNo(RandomTestUtil.randomString());

		relationshipLevelDefinition.setFlag(RandomTestUtil.randomString());

		relationshipLevelDefinition.setLevelName(RandomTestUtil.randomString());

		relationshipLevelDefinition.setParentHierarchyNo(RandomTestUtil.randomString());

		_relationshipLevelDefinitions.add(_persistence.update(
				relationshipLevelDefinition));

		return relationshipLevelDefinition;
	}

	private List<RelationshipLevelDefinition> _relationshipLevelDefinitions = new ArrayList<RelationshipLevelDefinition>();
	private RelationshipLevelDefinitionPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}