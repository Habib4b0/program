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
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchHistHierarchyLevelValuesException;
import com.stpl.app.model.HistHierarchyLevelValues;
import com.stpl.app.service.HistHierarchyLevelValuesLocalServiceUtil;
import com.stpl.app.service.persistence.HistHierarchyLevelValuesPK;
import com.stpl.app.service.persistence.HistHierarchyLevelValuesPersistence;
import com.stpl.app.service.persistence.HistHierarchyLevelValuesUtil;

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
public class HistHierarchyLevelValuesPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = HistHierarchyLevelValuesUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<HistHierarchyLevelValues> iterator = _histHierarchyLevelValueses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		HistHierarchyLevelValuesPK pk = new HistHierarchyLevelValuesPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		HistHierarchyLevelValues histHierarchyLevelValues = _persistence.create(pk);

		Assert.assertNotNull(histHierarchyLevelValues);

		Assert.assertEquals(histHierarchyLevelValues.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		HistHierarchyLevelValues newHistHierarchyLevelValues = addHistHierarchyLevelValues();

		_persistence.remove(newHistHierarchyLevelValues);

		HistHierarchyLevelValues existingHistHierarchyLevelValues = _persistence.fetchByPrimaryKey(newHistHierarchyLevelValues.getPrimaryKey());

		Assert.assertNull(existingHistHierarchyLevelValues);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addHistHierarchyLevelValues();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		HistHierarchyLevelValuesPK pk = new HistHierarchyLevelValuesPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		HistHierarchyLevelValues newHistHierarchyLevelValues = _persistence.create(pk);

		newHistHierarchyLevelValues.setLevelValues(RandomTestUtil.randomString());

		newHistHierarchyLevelValues.setCreatedDate(RandomTestUtil.nextDate());

		newHistHierarchyLevelValues.setCreatedBy(RandomTestUtil.nextInt());

		newHistHierarchyLevelValues.setActionDate(RandomTestUtil.nextDate());

		newHistHierarchyLevelValues.setHierarchyLevelDefinitionSid(RandomTestUtil.nextInt());

		newHistHierarchyLevelValues.setModifiedBy(RandomTestUtil.nextInt());

		newHistHierarchyLevelValues.setModifiedDate(RandomTestUtil.nextDate());

		_histHierarchyLevelValueses.add(_persistence.update(
				newHistHierarchyLevelValues));

		HistHierarchyLevelValues existingHistHierarchyLevelValues = _persistence.findByPrimaryKey(newHistHierarchyLevelValues.getPrimaryKey());

		Assert.assertEquals(existingHistHierarchyLevelValues.getLevelValues(),
			newHistHierarchyLevelValues.getLevelValues());
		Assert.assertEquals(existingHistHierarchyLevelValues.getHierarchyLevelValuesSid(),
			newHistHierarchyLevelValues.getHierarchyLevelValuesSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingHistHierarchyLevelValues.getCreatedDate()),
			Time.getShortTimestamp(newHistHierarchyLevelValues.getCreatedDate()));
		Assert.assertEquals(existingHistHierarchyLevelValues.getCreatedBy(),
			newHistHierarchyLevelValues.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingHistHierarchyLevelValues.getActionDate()),
			Time.getShortTimestamp(newHistHierarchyLevelValues.getActionDate()));
		Assert.assertEquals(existingHistHierarchyLevelValues.getActionFlag(),
			newHistHierarchyLevelValues.getActionFlag());
		Assert.assertEquals(existingHistHierarchyLevelValues.getHierarchyLevelDefinitionSid(),
			newHistHierarchyLevelValues.getHierarchyLevelDefinitionSid());
		Assert.assertEquals(existingHistHierarchyLevelValues.getVersionNo(),
			newHistHierarchyLevelValues.getVersionNo());
		Assert.assertEquals(existingHistHierarchyLevelValues.getModifiedBy(),
			newHistHierarchyLevelValues.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingHistHierarchyLevelValues.getModifiedDate()),
			Time.getShortTimestamp(
				newHistHierarchyLevelValues.getModifiedDate()));
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		HistHierarchyLevelValues newHistHierarchyLevelValues = addHistHierarchyLevelValues();

		HistHierarchyLevelValues existingHistHierarchyLevelValues = _persistence.findByPrimaryKey(newHistHierarchyLevelValues.getPrimaryKey());

		Assert.assertEquals(existingHistHierarchyLevelValues,
			newHistHierarchyLevelValues);
	}

	@Test(expected = NoSuchHistHierarchyLevelValuesException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		HistHierarchyLevelValuesPK pk = new HistHierarchyLevelValuesPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		HistHierarchyLevelValues newHistHierarchyLevelValues = addHistHierarchyLevelValues();

		HistHierarchyLevelValues existingHistHierarchyLevelValues = _persistence.fetchByPrimaryKey(newHistHierarchyLevelValues.getPrimaryKey());

		Assert.assertEquals(existingHistHierarchyLevelValues,
			newHistHierarchyLevelValues);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		HistHierarchyLevelValuesPK pk = new HistHierarchyLevelValuesPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		HistHierarchyLevelValues missingHistHierarchyLevelValues = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingHistHierarchyLevelValues);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		HistHierarchyLevelValues newHistHierarchyLevelValues1 = addHistHierarchyLevelValues();
		HistHierarchyLevelValues newHistHierarchyLevelValues2 = addHistHierarchyLevelValues();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHistHierarchyLevelValues1.getPrimaryKey());
		primaryKeys.add(newHistHierarchyLevelValues2.getPrimaryKey());

		Map<Serializable, HistHierarchyLevelValues> histHierarchyLevelValueses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, histHierarchyLevelValueses.size());
		Assert.assertEquals(newHistHierarchyLevelValues1,
			histHierarchyLevelValueses.get(
				newHistHierarchyLevelValues1.getPrimaryKey()));
		Assert.assertEquals(newHistHierarchyLevelValues2,
			histHierarchyLevelValueses.get(
				newHistHierarchyLevelValues2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		HistHierarchyLevelValuesPK pk1 = new HistHierarchyLevelValuesPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		HistHierarchyLevelValuesPK pk2 = new HistHierarchyLevelValuesPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, HistHierarchyLevelValues> histHierarchyLevelValueses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(histHierarchyLevelValueses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		HistHierarchyLevelValues newHistHierarchyLevelValues = addHistHierarchyLevelValues();

		HistHierarchyLevelValuesPK pk = new HistHierarchyLevelValuesPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHistHierarchyLevelValues.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, HistHierarchyLevelValues> histHierarchyLevelValueses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, histHierarchyLevelValueses.size());
		Assert.assertEquals(newHistHierarchyLevelValues,
			histHierarchyLevelValueses.get(
				newHistHierarchyLevelValues.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, HistHierarchyLevelValues> histHierarchyLevelValueses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(histHierarchyLevelValueses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		HistHierarchyLevelValues newHistHierarchyLevelValues = addHistHierarchyLevelValues();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHistHierarchyLevelValues.getPrimaryKey());

		Map<Serializable, HistHierarchyLevelValues> histHierarchyLevelValueses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, histHierarchyLevelValueses.size());
		Assert.assertEquals(newHistHierarchyLevelValues,
			histHierarchyLevelValueses.get(
				newHistHierarchyLevelValues.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = HistHierarchyLevelValuesLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<HistHierarchyLevelValues>() {
				@Override
				public void performAction(
					HistHierarchyLevelValues histHierarchyLevelValues) {
					Assert.assertNotNull(histHierarchyLevelValues);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		HistHierarchyLevelValues newHistHierarchyLevelValues = addHistHierarchyLevelValues();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HistHierarchyLevelValues.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"id.hierarchyLevelValuesSid",
				newHistHierarchyLevelValues.getHierarchyLevelValuesSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.actionFlag",
				newHistHierarchyLevelValues.getActionFlag()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.versionNo",
				newHistHierarchyLevelValues.getVersionNo()));

		List<HistHierarchyLevelValues> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		HistHierarchyLevelValues existingHistHierarchyLevelValues = result.get(0);

		Assert.assertEquals(existingHistHierarchyLevelValues,
			newHistHierarchyLevelValues);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HistHierarchyLevelValues.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"id.hierarchyLevelValuesSid", RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.actionFlag",
				RandomTestUtil.randomString()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.versionNo",
				RandomTestUtil.nextInt()));

		List<HistHierarchyLevelValues> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		HistHierarchyLevelValues newHistHierarchyLevelValues = addHistHierarchyLevelValues();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HistHierarchyLevelValues.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.hierarchyLevelValuesSid"));

		Object newHierarchyLevelValuesSid = newHistHierarchyLevelValues.getHierarchyLevelValuesSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"id.hierarchyLevelValuesSid",
				new Object[] { newHierarchyLevelValuesSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingHierarchyLevelValuesSid = result.get(0);

		Assert.assertEquals(existingHierarchyLevelValuesSid,
			newHierarchyLevelValuesSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HistHierarchyLevelValues.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.hierarchyLevelValuesSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"id.hierarchyLevelValuesSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected HistHierarchyLevelValues addHistHierarchyLevelValues()
		throws Exception {
		HistHierarchyLevelValuesPK pk = new HistHierarchyLevelValuesPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		HistHierarchyLevelValues histHierarchyLevelValues = _persistence.create(pk);

		histHierarchyLevelValues.setLevelValues(RandomTestUtil.randomString());

		histHierarchyLevelValues.setCreatedDate(RandomTestUtil.nextDate());

		histHierarchyLevelValues.setCreatedBy(RandomTestUtil.nextInt());

		histHierarchyLevelValues.setActionDate(RandomTestUtil.nextDate());

		histHierarchyLevelValues.setHierarchyLevelDefinitionSid(RandomTestUtil.nextInt());

		histHierarchyLevelValues.setModifiedBy(RandomTestUtil.nextInt());

		histHierarchyLevelValues.setModifiedDate(RandomTestUtil.nextDate());

		_histHierarchyLevelValueses.add(_persistence.update(
				histHierarchyLevelValues));

		return histHierarchyLevelValues;
	}

	private List<HistHierarchyLevelValues> _histHierarchyLevelValueses = new ArrayList<HistHierarchyLevelValues>();
	private HistHierarchyLevelValuesPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}