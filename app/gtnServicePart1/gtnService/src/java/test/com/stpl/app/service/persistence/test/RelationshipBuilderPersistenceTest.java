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

import com.stpl.app.exception.NoSuchRelationshipBuilderException;
import com.stpl.app.model.RelationshipBuilder;
import com.stpl.app.service.RelationshipBuilderLocalServiceUtil;
import com.stpl.app.service.persistence.RelationshipBuilderPersistence;
import com.stpl.app.service.persistence.RelationshipBuilderUtil;

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
public class RelationshipBuilderPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = RelationshipBuilderUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<RelationshipBuilder> iterator = _relationshipBuilders.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		RelationshipBuilder relationshipBuilder = _persistence.create(pk);

		Assert.assertNotNull(relationshipBuilder);

		Assert.assertEquals(relationshipBuilder.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		RelationshipBuilder newRelationshipBuilder = addRelationshipBuilder();

		_persistence.remove(newRelationshipBuilder);

		RelationshipBuilder existingRelationshipBuilder = _persistence.fetchByPrimaryKey(newRelationshipBuilder.getPrimaryKey());

		Assert.assertNull(existingRelationshipBuilder);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addRelationshipBuilder();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		RelationshipBuilder newRelationshipBuilder = _persistence.create(pk);

		newRelationshipBuilder.setStartDate(RandomTestUtil.nextDate());

		newRelationshipBuilder.setCreatedDate(RandomTestUtil.nextDate());

		newRelationshipBuilder.setCreatedBy(RandomTestUtil.nextInt());

		newRelationshipBuilder.setRelationshipDescription(RandomTestUtil.randomString());

		newRelationshipBuilder.setHierarchyDefinitionSid(RandomTestUtil.nextInt());

		newRelationshipBuilder.setVersionNo(RandomTestUtil.nextInt());

		newRelationshipBuilder.setRelationshipName(RandomTestUtil.randomString());

		newRelationshipBuilder.setHierarchyVersion(RandomTestUtil.nextInt());

		newRelationshipBuilder.setModifiedBy(RandomTestUtil.nextInt());

		newRelationshipBuilder.setModifiedDate(RandomTestUtil.nextDate());

		newRelationshipBuilder.setDeductionRelation(RandomTestUtil.nextInt());

		newRelationshipBuilder.setRelationshipType(RandomTestUtil.nextInt());

		newRelationshipBuilder.setBuildType(RandomTestUtil.randomString());

		_relationshipBuilders.add(_persistence.update(newRelationshipBuilder));

		RelationshipBuilder existingRelationshipBuilder = _persistence.findByPrimaryKey(newRelationshipBuilder.getPrimaryKey());

		Assert.assertEquals(Time.getShortTimestamp(
				existingRelationshipBuilder.getStartDate()),
			Time.getShortTimestamp(newRelationshipBuilder.getStartDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingRelationshipBuilder.getCreatedDate()),
			Time.getShortTimestamp(newRelationshipBuilder.getCreatedDate()));
		Assert.assertEquals(existingRelationshipBuilder.getCreatedBy(),
			newRelationshipBuilder.getCreatedBy());
		Assert.assertEquals(existingRelationshipBuilder.getRelationshipDescription(),
			newRelationshipBuilder.getRelationshipDescription());
		Assert.assertEquals(existingRelationshipBuilder.getHierarchyDefinitionSid(),
			newRelationshipBuilder.getHierarchyDefinitionSid());
		Assert.assertEquals(existingRelationshipBuilder.getVersionNo(),
			newRelationshipBuilder.getVersionNo());
		Assert.assertEquals(existingRelationshipBuilder.getRelationshipName(),
			newRelationshipBuilder.getRelationshipName());
		Assert.assertEquals(existingRelationshipBuilder.getRelationshipBuilderSid(),
			newRelationshipBuilder.getRelationshipBuilderSid());
		Assert.assertEquals(existingRelationshipBuilder.getHierarchyVersion(),
			newRelationshipBuilder.getHierarchyVersion());
		Assert.assertEquals(existingRelationshipBuilder.getModifiedBy(),
			newRelationshipBuilder.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingRelationshipBuilder.getModifiedDate()),
			Time.getShortTimestamp(newRelationshipBuilder.getModifiedDate()));
		Assert.assertEquals(existingRelationshipBuilder.getDeductionRelation(),
			newRelationshipBuilder.getDeductionRelation());
		Assert.assertEquals(existingRelationshipBuilder.getRelationshipType(),
			newRelationshipBuilder.getRelationshipType());
		Assert.assertEquals(existingRelationshipBuilder.getBuildType(),
			newRelationshipBuilder.getBuildType());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		RelationshipBuilder newRelationshipBuilder = addRelationshipBuilder();

		RelationshipBuilder existingRelationshipBuilder = _persistence.findByPrimaryKey(newRelationshipBuilder.getPrimaryKey());

		Assert.assertEquals(existingRelationshipBuilder, newRelationshipBuilder);
	}

	@Test(expected = NoSuchRelationshipBuilderException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<RelationshipBuilder> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("RELATIONSHIP_BUILDER",
			"startDate", true, "createdDate", true, "createdBy", true,
			"relationshipDescription", true, "hierarchyDefinitionSid", true,
			"versionNo", true, "relationshipName", true,
			"relationshipBuilderSid", true, "hierarchyVersion", true,
			"modifiedBy", true, "modifiedDate", true, "deductionRelation",
			true, "relationshipType", true, "buildType", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		RelationshipBuilder newRelationshipBuilder = addRelationshipBuilder();

		RelationshipBuilder existingRelationshipBuilder = _persistence.fetchByPrimaryKey(newRelationshipBuilder.getPrimaryKey());

		Assert.assertEquals(existingRelationshipBuilder, newRelationshipBuilder);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		RelationshipBuilder missingRelationshipBuilder = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingRelationshipBuilder);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		RelationshipBuilder newRelationshipBuilder1 = addRelationshipBuilder();
		RelationshipBuilder newRelationshipBuilder2 = addRelationshipBuilder();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRelationshipBuilder1.getPrimaryKey());
		primaryKeys.add(newRelationshipBuilder2.getPrimaryKey());

		Map<Serializable, RelationshipBuilder> relationshipBuilders = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, relationshipBuilders.size());
		Assert.assertEquals(newRelationshipBuilder1,
			relationshipBuilders.get(newRelationshipBuilder1.getPrimaryKey()));
		Assert.assertEquals(newRelationshipBuilder2,
			relationshipBuilders.get(newRelationshipBuilder2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, RelationshipBuilder> relationshipBuilders = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(relationshipBuilders.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		RelationshipBuilder newRelationshipBuilder = addRelationshipBuilder();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRelationshipBuilder.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, RelationshipBuilder> relationshipBuilders = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, relationshipBuilders.size());
		Assert.assertEquals(newRelationshipBuilder,
			relationshipBuilders.get(newRelationshipBuilder.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, RelationshipBuilder> relationshipBuilders = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(relationshipBuilders.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		RelationshipBuilder newRelationshipBuilder = addRelationshipBuilder();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRelationshipBuilder.getPrimaryKey());

		Map<Serializable, RelationshipBuilder> relationshipBuilders = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, relationshipBuilders.size());
		Assert.assertEquals(newRelationshipBuilder,
			relationshipBuilders.get(newRelationshipBuilder.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = RelationshipBuilderLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<RelationshipBuilder>() {
				@Override
				public void performAction(
					RelationshipBuilder relationshipBuilder) {
					Assert.assertNotNull(relationshipBuilder);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		RelationshipBuilder newRelationshipBuilder = addRelationshipBuilder();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RelationshipBuilder.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("relationshipBuilderSid",
				newRelationshipBuilder.getRelationshipBuilderSid()));

		List<RelationshipBuilder> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		RelationshipBuilder existingRelationshipBuilder = result.get(0);

		Assert.assertEquals(existingRelationshipBuilder, newRelationshipBuilder);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RelationshipBuilder.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("relationshipBuilderSid",
				RandomTestUtil.nextInt()));

		List<RelationshipBuilder> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		RelationshipBuilder newRelationshipBuilder = addRelationshipBuilder();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RelationshipBuilder.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"relationshipBuilderSid"));

		Object newRelationshipBuilderSid = newRelationshipBuilder.getRelationshipBuilderSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("relationshipBuilderSid",
				new Object[] { newRelationshipBuilderSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingRelationshipBuilderSid = result.get(0);

		Assert.assertEquals(existingRelationshipBuilderSid,
			newRelationshipBuilderSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RelationshipBuilder.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"relationshipBuilderSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("relationshipBuilderSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected RelationshipBuilder addRelationshipBuilder()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		RelationshipBuilder relationshipBuilder = _persistence.create(pk);

		relationshipBuilder.setStartDate(RandomTestUtil.nextDate());

		relationshipBuilder.setCreatedDate(RandomTestUtil.nextDate());

		relationshipBuilder.setCreatedBy(RandomTestUtil.nextInt());

		relationshipBuilder.setRelationshipDescription(RandomTestUtil.randomString());

		relationshipBuilder.setHierarchyDefinitionSid(RandomTestUtil.nextInt());

		relationshipBuilder.setVersionNo(RandomTestUtil.nextInt());

		relationshipBuilder.setRelationshipName(RandomTestUtil.randomString());

		relationshipBuilder.setHierarchyVersion(RandomTestUtil.nextInt());

		relationshipBuilder.setModifiedBy(RandomTestUtil.nextInt());

		relationshipBuilder.setModifiedDate(RandomTestUtil.nextDate());

		relationshipBuilder.setDeductionRelation(RandomTestUtil.nextInt());

		relationshipBuilder.setRelationshipType(RandomTestUtil.nextInt());

		relationshipBuilder.setBuildType(RandomTestUtil.randomString());

		_relationshipBuilders.add(_persistence.update(relationshipBuilder));

		return relationshipBuilder;
	}

	private List<RelationshipBuilder> _relationshipBuilders = new ArrayList<RelationshipBuilder>();
	private RelationshipBuilderPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}