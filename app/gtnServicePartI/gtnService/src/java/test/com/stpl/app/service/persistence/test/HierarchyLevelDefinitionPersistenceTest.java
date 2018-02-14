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

import com.stpl.app.exception.NoSuchHierarchyLevelDefinitionException;
import com.stpl.app.model.HierarchyLevelDefinition;
import com.stpl.app.service.HierarchyLevelDefinitionLocalServiceUtil;
import com.stpl.app.service.persistence.HierarchyLevelDefinitionPersistence;
import com.stpl.app.service.persistence.HierarchyLevelDefinitionUtil;

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
public class HierarchyLevelDefinitionPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = HierarchyLevelDefinitionUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<HierarchyLevelDefinition> iterator = _hierarchyLevelDefinitions.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		HierarchyLevelDefinition hierarchyLevelDefinition = _persistence.create(pk);

		Assert.assertNotNull(hierarchyLevelDefinition);

		Assert.assertEquals(hierarchyLevelDefinition.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		HierarchyLevelDefinition newHierarchyLevelDefinition = addHierarchyLevelDefinition();

		_persistence.remove(newHierarchyLevelDefinition);

		HierarchyLevelDefinition existingHierarchyLevelDefinition = _persistence.fetchByPrimaryKey(newHierarchyLevelDefinition.getPrimaryKey());

		Assert.assertNull(existingHierarchyLevelDefinition);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addHierarchyLevelDefinition();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		HierarchyLevelDefinition newHierarchyLevelDefinition = _persistence.create(pk);

		newHierarchyLevelDefinition.setTableName(RandomTestUtil.randomString());

		newHierarchyLevelDefinition.setCreatedDate(RandomTestUtil.nextDate());

		newHierarchyLevelDefinition.setCreatedBy(RandomTestUtil.nextInt());

		newHierarchyLevelDefinition.setLevelValueReference(RandomTestUtil.randomString());

		newHierarchyLevelDefinition.setFieldName(RandomTestUtil.randomString());

		newHierarchyLevelDefinition.setLevelNo(RandomTestUtil.nextInt());

		newHierarchyLevelDefinition.setHierarchyDefinitionSid(RandomTestUtil.nextInt());

		newHierarchyLevelDefinition.setVersionNo(RandomTestUtil.nextInt());

		newHierarchyLevelDefinition.setModifiedBy(RandomTestUtil.nextInt());

		newHierarchyLevelDefinition.setModifiedDate(RandomTestUtil.nextDate());

		newHierarchyLevelDefinition.setLevelName(RandomTestUtil.randomString());

		_hierarchyLevelDefinitions.add(_persistence.update(
				newHierarchyLevelDefinition));

		HierarchyLevelDefinition existingHierarchyLevelDefinition = _persistence.findByPrimaryKey(newHierarchyLevelDefinition.getPrimaryKey());

		Assert.assertEquals(existingHierarchyLevelDefinition.getTableName(),
			newHierarchyLevelDefinition.getTableName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingHierarchyLevelDefinition.getCreatedDate()),
			Time.getShortTimestamp(newHierarchyLevelDefinition.getCreatedDate()));
		Assert.assertEquals(existingHierarchyLevelDefinition.getCreatedBy(),
			newHierarchyLevelDefinition.getCreatedBy());
		Assert.assertEquals(existingHierarchyLevelDefinition.getLevelValueReference(),
			newHierarchyLevelDefinition.getLevelValueReference());
		Assert.assertEquals(existingHierarchyLevelDefinition.getFieldName(),
			newHierarchyLevelDefinition.getFieldName());
		Assert.assertEquals(existingHierarchyLevelDefinition.getLevelNo(),
			newHierarchyLevelDefinition.getLevelNo());
		Assert.assertEquals(existingHierarchyLevelDefinition.getHierarchyLevelDefinitionSid(),
			newHierarchyLevelDefinition.getHierarchyLevelDefinitionSid());
		Assert.assertEquals(existingHierarchyLevelDefinition.getHierarchyDefinitionSid(),
			newHierarchyLevelDefinition.getHierarchyDefinitionSid());
		Assert.assertEquals(existingHierarchyLevelDefinition.getVersionNo(),
			newHierarchyLevelDefinition.getVersionNo());
		Assert.assertEquals(existingHierarchyLevelDefinition.getModifiedBy(),
			newHierarchyLevelDefinition.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingHierarchyLevelDefinition.getModifiedDate()),
			Time.getShortTimestamp(
				newHierarchyLevelDefinition.getModifiedDate()));
		Assert.assertEquals(existingHierarchyLevelDefinition.getLevelName(),
			newHierarchyLevelDefinition.getLevelName());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		HierarchyLevelDefinition newHierarchyLevelDefinition = addHierarchyLevelDefinition();

		HierarchyLevelDefinition existingHierarchyLevelDefinition = _persistence.findByPrimaryKey(newHierarchyLevelDefinition.getPrimaryKey());

		Assert.assertEquals(existingHierarchyLevelDefinition,
			newHierarchyLevelDefinition);
	}

	@Test(expected = NoSuchHierarchyLevelDefinitionException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<HierarchyLevelDefinition> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("HIERARCHY_LEVEL_DEFINITION",
			"tableName", true, "createdDate", true, "createdBy", true,
			"levelValueReference", true, "fieldName", true, "levelNo", true,
			"hierarchyLevelDefinitionSid", true, "hierarchyDefinitionSid",
			true, "versionNo", true, "modifiedBy", true, "modifiedDate", true,
			"levelName", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		HierarchyLevelDefinition newHierarchyLevelDefinition = addHierarchyLevelDefinition();

		HierarchyLevelDefinition existingHierarchyLevelDefinition = _persistence.fetchByPrimaryKey(newHierarchyLevelDefinition.getPrimaryKey());

		Assert.assertEquals(existingHierarchyLevelDefinition,
			newHierarchyLevelDefinition);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		HierarchyLevelDefinition missingHierarchyLevelDefinition = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingHierarchyLevelDefinition);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		HierarchyLevelDefinition newHierarchyLevelDefinition1 = addHierarchyLevelDefinition();
		HierarchyLevelDefinition newHierarchyLevelDefinition2 = addHierarchyLevelDefinition();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHierarchyLevelDefinition1.getPrimaryKey());
		primaryKeys.add(newHierarchyLevelDefinition2.getPrimaryKey());

		Map<Serializable, HierarchyLevelDefinition> hierarchyLevelDefinitions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, hierarchyLevelDefinitions.size());
		Assert.assertEquals(newHierarchyLevelDefinition1,
			hierarchyLevelDefinitions.get(
				newHierarchyLevelDefinition1.getPrimaryKey()));
		Assert.assertEquals(newHierarchyLevelDefinition2,
			hierarchyLevelDefinitions.get(
				newHierarchyLevelDefinition2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, HierarchyLevelDefinition> hierarchyLevelDefinitions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(hierarchyLevelDefinitions.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		HierarchyLevelDefinition newHierarchyLevelDefinition = addHierarchyLevelDefinition();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHierarchyLevelDefinition.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, HierarchyLevelDefinition> hierarchyLevelDefinitions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, hierarchyLevelDefinitions.size());
		Assert.assertEquals(newHierarchyLevelDefinition,
			hierarchyLevelDefinitions.get(
				newHierarchyLevelDefinition.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, HierarchyLevelDefinition> hierarchyLevelDefinitions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(hierarchyLevelDefinitions.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		HierarchyLevelDefinition newHierarchyLevelDefinition = addHierarchyLevelDefinition();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHierarchyLevelDefinition.getPrimaryKey());

		Map<Serializable, HierarchyLevelDefinition> hierarchyLevelDefinitions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, hierarchyLevelDefinitions.size());
		Assert.assertEquals(newHierarchyLevelDefinition,
			hierarchyLevelDefinitions.get(
				newHierarchyLevelDefinition.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = HierarchyLevelDefinitionLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<HierarchyLevelDefinition>() {
				@Override
				public void performAction(
					HierarchyLevelDefinition hierarchyLevelDefinition) {
					Assert.assertNotNull(hierarchyLevelDefinition);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		HierarchyLevelDefinition newHierarchyLevelDefinition = addHierarchyLevelDefinition();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HierarchyLevelDefinition.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"hierarchyLevelDefinitionSid",
				newHierarchyLevelDefinition.getHierarchyLevelDefinitionSid()));

		List<HierarchyLevelDefinition> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		HierarchyLevelDefinition existingHierarchyLevelDefinition = result.get(0);

		Assert.assertEquals(existingHierarchyLevelDefinition,
			newHierarchyLevelDefinition);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HierarchyLevelDefinition.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"hierarchyLevelDefinitionSid", RandomTestUtil.nextInt()));

		List<HierarchyLevelDefinition> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		HierarchyLevelDefinition newHierarchyLevelDefinition = addHierarchyLevelDefinition();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HierarchyLevelDefinition.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"hierarchyLevelDefinitionSid"));

		Object newHierarchyLevelDefinitionSid = newHierarchyLevelDefinition.getHierarchyLevelDefinitionSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"hierarchyLevelDefinitionSid",
				new Object[] { newHierarchyLevelDefinitionSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingHierarchyLevelDefinitionSid = result.get(0);

		Assert.assertEquals(existingHierarchyLevelDefinitionSid,
			newHierarchyLevelDefinitionSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HierarchyLevelDefinition.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"hierarchyLevelDefinitionSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"hierarchyLevelDefinitionSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected HierarchyLevelDefinition addHierarchyLevelDefinition()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		HierarchyLevelDefinition hierarchyLevelDefinition = _persistence.create(pk);

		hierarchyLevelDefinition.setTableName(RandomTestUtil.randomString());

		hierarchyLevelDefinition.setCreatedDate(RandomTestUtil.nextDate());

		hierarchyLevelDefinition.setCreatedBy(RandomTestUtil.nextInt());

		hierarchyLevelDefinition.setLevelValueReference(RandomTestUtil.randomString());

		hierarchyLevelDefinition.setFieldName(RandomTestUtil.randomString());

		hierarchyLevelDefinition.setLevelNo(RandomTestUtil.nextInt());

		hierarchyLevelDefinition.setHierarchyDefinitionSid(RandomTestUtil.nextInt());

		hierarchyLevelDefinition.setVersionNo(RandomTestUtil.nextInt());

		hierarchyLevelDefinition.setModifiedBy(RandomTestUtil.nextInt());

		hierarchyLevelDefinition.setModifiedDate(RandomTestUtil.nextDate());

		hierarchyLevelDefinition.setLevelName(RandomTestUtil.randomString());

		_hierarchyLevelDefinitions.add(_persistence.update(
				hierarchyLevelDefinition));

		return hierarchyLevelDefinition;
	}

	private List<HierarchyLevelDefinition> _hierarchyLevelDefinitions = new ArrayList<HierarchyLevelDefinition>();
	private HierarchyLevelDefinitionPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}