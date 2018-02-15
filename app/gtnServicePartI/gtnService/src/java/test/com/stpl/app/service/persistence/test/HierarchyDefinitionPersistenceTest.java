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

import com.stpl.app.exception.NoSuchHierarchyDefinitionException;
import com.stpl.app.model.HierarchyDefinition;
import com.stpl.app.service.HierarchyDefinitionLocalServiceUtil;
import com.stpl.app.service.persistence.HierarchyDefinitionPersistence;
import com.stpl.app.service.persistence.HierarchyDefinitionUtil;

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
public class HierarchyDefinitionPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = HierarchyDefinitionUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<HierarchyDefinition> iterator = _hierarchyDefinitions.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		HierarchyDefinition hierarchyDefinition = _persistence.create(pk);

		Assert.assertNotNull(hierarchyDefinition);

		Assert.assertEquals(hierarchyDefinition.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		HierarchyDefinition newHierarchyDefinition = addHierarchyDefinition();

		_persistence.remove(newHierarchyDefinition);

		HierarchyDefinition existingHierarchyDefinition = _persistence.fetchByPrimaryKey(newHierarchyDefinition.getPrimaryKey());

		Assert.assertNull(existingHierarchyDefinition);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addHierarchyDefinition();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		HierarchyDefinition newHierarchyDefinition = _persistence.create(pk);

		newHierarchyDefinition.setCreatedDate(RandomTestUtil.nextDate());

		newHierarchyDefinition.setCreatedBy(RandomTestUtil.nextInt());

		newHierarchyDefinition.setNoOfLevels(RandomTestUtil.nextInt());

		newHierarchyDefinition.setHierarchyType(RandomTestUtil.nextInt());

		newHierarchyDefinition.setHierarchyName(RandomTestUtil.randomString());

		newHierarchyDefinition.setVersionNo(RandomTestUtil.nextInt());

		newHierarchyDefinition.setModifiedBy(RandomTestUtil.nextInt());

		newHierarchyDefinition.setModifiedDate(RandomTestUtil.nextDate());

		newHierarchyDefinition.setHierarchyCategory(RandomTestUtil.nextInt());

		_hierarchyDefinitions.add(_persistence.update(newHierarchyDefinition));

		HierarchyDefinition existingHierarchyDefinition = _persistence.findByPrimaryKey(newHierarchyDefinition.getPrimaryKey());

		Assert.assertEquals(Time.getShortTimestamp(
				existingHierarchyDefinition.getCreatedDate()),
			Time.getShortTimestamp(newHierarchyDefinition.getCreatedDate()));
		Assert.assertEquals(existingHierarchyDefinition.getCreatedBy(),
			newHierarchyDefinition.getCreatedBy());
		Assert.assertEquals(existingHierarchyDefinition.getNoOfLevels(),
			newHierarchyDefinition.getNoOfLevels());
		Assert.assertEquals(existingHierarchyDefinition.getHierarchyType(),
			newHierarchyDefinition.getHierarchyType());
		Assert.assertEquals(existingHierarchyDefinition.getHierarchyName(),
			newHierarchyDefinition.getHierarchyName());
		Assert.assertEquals(existingHierarchyDefinition.getHierarchyDefinitionSid(),
			newHierarchyDefinition.getHierarchyDefinitionSid());
		Assert.assertEquals(existingHierarchyDefinition.getVersionNo(),
			newHierarchyDefinition.getVersionNo());
		Assert.assertEquals(existingHierarchyDefinition.getModifiedBy(),
			newHierarchyDefinition.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingHierarchyDefinition.getModifiedDate()),
			Time.getShortTimestamp(newHierarchyDefinition.getModifiedDate()));
		Assert.assertEquals(existingHierarchyDefinition.getHierarchyCategory(),
			newHierarchyDefinition.getHierarchyCategory());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		HierarchyDefinition newHierarchyDefinition = addHierarchyDefinition();

		HierarchyDefinition existingHierarchyDefinition = _persistence.findByPrimaryKey(newHierarchyDefinition.getPrimaryKey());

		Assert.assertEquals(existingHierarchyDefinition, newHierarchyDefinition);
	}

	@Test(expected = NoSuchHierarchyDefinitionException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<HierarchyDefinition> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("HIERARCHY_DEFINITION",
			"createdDate", true, "createdBy", true, "noOfLevels", true,
			"hierarchyType", true, "hierarchyName", true,
			"hierarchyDefinitionSid", true, "versionNo", true, "modifiedBy",
			true, "modifiedDate", true, "hierarchyCategory", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		HierarchyDefinition newHierarchyDefinition = addHierarchyDefinition();

		HierarchyDefinition existingHierarchyDefinition = _persistence.fetchByPrimaryKey(newHierarchyDefinition.getPrimaryKey());

		Assert.assertEquals(existingHierarchyDefinition, newHierarchyDefinition);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		HierarchyDefinition missingHierarchyDefinition = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingHierarchyDefinition);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		HierarchyDefinition newHierarchyDefinition1 = addHierarchyDefinition();
		HierarchyDefinition newHierarchyDefinition2 = addHierarchyDefinition();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHierarchyDefinition1.getPrimaryKey());
		primaryKeys.add(newHierarchyDefinition2.getPrimaryKey());

		Map<Serializable, HierarchyDefinition> hierarchyDefinitions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, hierarchyDefinitions.size());
		Assert.assertEquals(newHierarchyDefinition1,
			hierarchyDefinitions.get(newHierarchyDefinition1.getPrimaryKey()));
		Assert.assertEquals(newHierarchyDefinition2,
			hierarchyDefinitions.get(newHierarchyDefinition2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, HierarchyDefinition> hierarchyDefinitions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(hierarchyDefinitions.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		HierarchyDefinition newHierarchyDefinition = addHierarchyDefinition();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHierarchyDefinition.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, HierarchyDefinition> hierarchyDefinitions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, hierarchyDefinitions.size());
		Assert.assertEquals(newHierarchyDefinition,
			hierarchyDefinitions.get(newHierarchyDefinition.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, HierarchyDefinition> hierarchyDefinitions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(hierarchyDefinitions.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		HierarchyDefinition newHierarchyDefinition = addHierarchyDefinition();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHierarchyDefinition.getPrimaryKey());

		Map<Serializable, HierarchyDefinition> hierarchyDefinitions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, hierarchyDefinitions.size());
		Assert.assertEquals(newHierarchyDefinition,
			hierarchyDefinitions.get(newHierarchyDefinition.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = HierarchyDefinitionLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<HierarchyDefinition>() {
				@Override
				public void performAction(
					HierarchyDefinition hierarchyDefinition) {
					Assert.assertNotNull(hierarchyDefinition);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		HierarchyDefinition newHierarchyDefinition = addHierarchyDefinition();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HierarchyDefinition.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("hierarchyDefinitionSid",
				newHierarchyDefinition.getHierarchyDefinitionSid()));

		List<HierarchyDefinition> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		HierarchyDefinition existingHierarchyDefinition = result.get(0);

		Assert.assertEquals(existingHierarchyDefinition, newHierarchyDefinition);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HierarchyDefinition.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("hierarchyDefinitionSid",
				RandomTestUtil.nextInt()));

		List<HierarchyDefinition> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		HierarchyDefinition newHierarchyDefinition = addHierarchyDefinition();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HierarchyDefinition.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"hierarchyDefinitionSid"));

		Object newHierarchyDefinitionSid = newHierarchyDefinition.getHierarchyDefinitionSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("hierarchyDefinitionSid",
				new Object[] { newHierarchyDefinitionSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingHierarchyDefinitionSid = result.get(0);

		Assert.assertEquals(existingHierarchyDefinitionSid,
			newHierarchyDefinitionSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HierarchyDefinition.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"hierarchyDefinitionSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("hierarchyDefinitionSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected HierarchyDefinition addHierarchyDefinition()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		HierarchyDefinition hierarchyDefinition = _persistence.create(pk);

		hierarchyDefinition.setCreatedDate(RandomTestUtil.nextDate());

		hierarchyDefinition.setCreatedBy(RandomTestUtil.nextInt());

		hierarchyDefinition.setNoOfLevels(RandomTestUtil.nextInt());

		hierarchyDefinition.setHierarchyType(RandomTestUtil.nextInt());

		hierarchyDefinition.setHierarchyName(RandomTestUtil.randomString());

		hierarchyDefinition.setVersionNo(RandomTestUtil.nextInt());

		hierarchyDefinition.setModifiedBy(RandomTestUtil.nextInt());

		hierarchyDefinition.setModifiedDate(RandomTestUtil.nextDate());

		hierarchyDefinition.setHierarchyCategory(RandomTestUtil.nextInt());

		_hierarchyDefinitions.add(_persistence.update(hierarchyDefinition));

		return hierarchyDefinition;
	}

	private List<HierarchyDefinition> _hierarchyDefinitions = new ArrayList<HierarchyDefinition>();
	private HierarchyDefinitionPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}