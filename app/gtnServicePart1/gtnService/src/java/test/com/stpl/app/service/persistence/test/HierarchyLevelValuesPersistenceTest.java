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

import com.stpl.app.exception.NoSuchHierarchyLevelValuesException;
import com.stpl.app.model.HierarchyLevelValues;
import com.stpl.app.service.HierarchyLevelValuesLocalServiceUtil;
import com.stpl.app.service.persistence.HierarchyLevelValuesPersistence;
import com.stpl.app.service.persistence.HierarchyLevelValuesUtil;

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
public class HierarchyLevelValuesPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = HierarchyLevelValuesUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<HierarchyLevelValues> iterator = _hierarchyLevelValueses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		HierarchyLevelValues hierarchyLevelValues = _persistence.create(pk);

		Assert.assertNotNull(hierarchyLevelValues);

		Assert.assertEquals(hierarchyLevelValues.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		HierarchyLevelValues newHierarchyLevelValues = addHierarchyLevelValues();

		_persistence.remove(newHierarchyLevelValues);

		HierarchyLevelValues existingHierarchyLevelValues = _persistence.fetchByPrimaryKey(newHierarchyLevelValues.getPrimaryKey());

		Assert.assertNull(existingHierarchyLevelValues);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addHierarchyLevelValues();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		HierarchyLevelValues newHierarchyLevelValues = _persistence.create(pk);

		newHierarchyLevelValues.setLevelValues(RandomTestUtil.randomString());

		newHierarchyLevelValues.setCreatedDate(RandomTestUtil.nextDate());

		newHierarchyLevelValues.setCreatedBy(RandomTestUtil.nextInt());

		newHierarchyLevelValues.setHierarchyLevelDefinitionSid(RandomTestUtil.nextInt());

		newHierarchyLevelValues.setVersionNo(RandomTestUtil.nextInt());

		newHierarchyLevelValues.setModifiedBy(RandomTestUtil.nextInt());

		newHierarchyLevelValues.setModifiedDate(RandomTestUtil.nextDate());

		_hierarchyLevelValueses.add(_persistence.update(newHierarchyLevelValues));

		HierarchyLevelValues existingHierarchyLevelValues = _persistence.findByPrimaryKey(newHierarchyLevelValues.getPrimaryKey());

		Assert.assertEquals(existingHierarchyLevelValues.getLevelValues(),
			newHierarchyLevelValues.getLevelValues());
		Assert.assertEquals(existingHierarchyLevelValues.getHierarchyLevelValuesSid(),
			newHierarchyLevelValues.getHierarchyLevelValuesSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingHierarchyLevelValues.getCreatedDate()),
			Time.getShortTimestamp(newHierarchyLevelValues.getCreatedDate()));
		Assert.assertEquals(existingHierarchyLevelValues.getCreatedBy(),
			newHierarchyLevelValues.getCreatedBy());
		Assert.assertEquals(existingHierarchyLevelValues.getHierarchyLevelDefinitionSid(),
			newHierarchyLevelValues.getHierarchyLevelDefinitionSid());
		Assert.assertEquals(existingHierarchyLevelValues.getVersionNo(),
			newHierarchyLevelValues.getVersionNo());
		Assert.assertEquals(existingHierarchyLevelValues.getModifiedBy(),
			newHierarchyLevelValues.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingHierarchyLevelValues.getModifiedDate()),
			Time.getShortTimestamp(newHierarchyLevelValues.getModifiedDate()));
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		HierarchyLevelValues newHierarchyLevelValues = addHierarchyLevelValues();

		HierarchyLevelValues existingHierarchyLevelValues = _persistence.findByPrimaryKey(newHierarchyLevelValues.getPrimaryKey());

		Assert.assertEquals(existingHierarchyLevelValues,
			newHierarchyLevelValues);
	}

	@Test(expected = NoSuchHierarchyLevelValuesException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<HierarchyLevelValues> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("HIERARCHY_LEVEL_VALUES",
			"levelValues", true, "hierarchyLevelValuesSid", true,
			"createdDate", true, "createdBy", true,
			"hierarchyLevelDefinitionSid", true, "versionNo", true,
			"modifiedBy", true, "modifiedDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		HierarchyLevelValues newHierarchyLevelValues = addHierarchyLevelValues();

		HierarchyLevelValues existingHierarchyLevelValues = _persistence.fetchByPrimaryKey(newHierarchyLevelValues.getPrimaryKey());

		Assert.assertEquals(existingHierarchyLevelValues,
			newHierarchyLevelValues);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		HierarchyLevelValues missingHierarchyLevelValues = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingHierarchyLevelValues);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		HierarchyLevelValues newHierarchyLevelValues1 = addHierarchyLevelValues();
		HierarchyLevelValues newHierarchyLevelValues2 = addHierarchyLevelValues();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHierarchyLevelValues1.getPrimaryKey());
		primaryKeys.add(newHierarchyLevelValues2.getPrimaryKey());

		Map<Serializable, HierarchyLevelValues> hierarchyLevelValueses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, hierarchyLevelValueses.size());
		Assert.assertEquals(newHierarchyLevelValues1,
			hierarchyLevelValueses.get(newHierarchyLevelValues1.getPrimaryKey()));
		Assert.assertEquals(newHierarchyLevelValues2,
			hierarchyLevelValueses.get(newHierarchyLevelValues2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, HierarchyLevelValues> hierarchyLevelValueses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(hierarchyLevelValueses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		HierarchyLevelValues newHierarchyLevelValues = addHierarchyLevelValues();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHierarchyLevelValues.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, HierarchyLevelValues> hierarchyLevelValueses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, hierarchyLevelValueses.size());
		Assert.assertEquals(newHierarchyLevelValues,
			hierarchyLevelValueses.get(newHierarchyLevelValues.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, HierarchyLevelValues> hierarchyLevelValueses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(hierarchyLevelValueses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		HierarchyLevelValues newHierarchyLevelValues = addHierarchyLevelValues();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHierarchyLevelValues.getPrimaryKey());

		Map<Serializable, HierarchyLevelValues> hierarchyLevelValueses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, hierarchyLevelValueses.size());
		Assert.assertEquals(newHierarchyLevelValues,
			hierarchyLevelValueses.get(newHierarchyLevelValues.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = HierarchyLevelValuesLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<HierarchyLevelValues>() {
				@Override
				public void performAction(
					HierarchyLevelValues hierarchyLevelValues) {
					Assert.assertNotNull(hierarchyLevelValues);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		HierarchyLevelValues newHierarchyLevelValues = addHierarchyLevelValues();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HierarchyLevelValues.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("hierarchyLevelValuesSid",
				newHierarchyLevelValues.getHierarchyLevelValuesSid()));

		List<HierarchyLevelValues> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		HierarchyLevelValues existingHierarchyLevelValues = result.get(0);

		Assert.assertEquals(existingHierarchyLevelValues,
			newHierarchyLevelValues);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HierarchyLevelValues.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("hierarchyLevelValuesSid",
				RandomTestUtil.nextInt()));

		List<HierarchyLevelValues> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		HierarchyLevelValues newHierarchyLevelValues = addHierarchyLevelValues();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HierarchyLevelValues.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"hierarchyLevelValuesSid"));

		Object newHierarchyLevelValuesSid = newHierarchyLevelValues.getHierarchyLevelValuesSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("hierarchyLevelValuesSid",
				new Object[] { newHierarchyLevelValuesSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingHierarchyLevelValuesSid = result.get(0);

		Assert.assertEquals(existingHierarchyLevelValuesSid,
			newHierarchyLevelValuesSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HierarchyLevelValues.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"hierarchyLevelValuesSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("hierarchyLevelValuesSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected HierarchyLevelValues addHierarchyLevelValues()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		HierarchyLevelValues hierarchyLevelValues = _persistence.create(pk);

		hierarchyLevelValues.setLevelValues(RandomTestUtil.randomString());

		hierarchyLevelValues.setCreatedDate(RandomTestUtil.nextDate());

		hierarchyLevelValues.setCreatedBy(RandomTestUtil.nextInt());

		hierarchyLevelValues.setHierarchyLevelDefinitionSid(RandomTestUtil.nextInt());

		hierarchyLevelValues.setVersionNo(RandomTestUtil.nextInt());

		hierarchyLevelValues.setModifiedBy(RandomTestUtil.nextInt());

		hierarchyLevelValues.setModifiedDate(RandomTestUtil.nextDate());

		_hierarchyLevelValueses.add(_persistence.update(hierarchyLevelValues));

		return hierarchyLevelValues;
	}

	private List<HierarchyLevelValues> _hierarchyLevelValueses = new ArrayList<HierarchyLevelValues>();
	private HierarchyLevelValuesPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}