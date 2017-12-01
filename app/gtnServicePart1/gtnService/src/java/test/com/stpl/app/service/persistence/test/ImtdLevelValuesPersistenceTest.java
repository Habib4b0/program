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

import com.stpl.app.exception.NoSuchImtdLevelValuesException;
import com.stpl.app.model.ImtdLevelValues;
import com.stpl.app.service.ImtdLevelValuesLocalServiceUtil;
import com.stpl.app.service.persistence.ImtdLevelValuesPersistence;
import com.stpl.app.service.persistence.ImtdLevelValuesUtil;

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
public class ImtdLevelValuesPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ImtdLevelValuesUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ImtdLevelValues> iterator = _imtdLevelValueses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ImtdLevelValues imtdLevelValues = _persistence.create(pk);

		Assert.assertNotNull(imtdLevelValues);

		Assert.assertEquals(imtdLevelValues.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ImtdLevelValues newImtdLevelValues = addImtdLevelValues();

		_persistence.remove(newImtdLevelValues);

		ImtdLevelValues existingImtdLevelValues = _persistence.fetchByPrimaryKey(newImtdLevelValues.getPrimaryKey());

		Assert.assertNull(existingImtdLevelValues);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addImtdLevelValues();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ImtdLevelValues newImtdLevelValues = _persistence.create(pk);

		newImtdLevelValues.setLevelValues(RandomTestUtil.randomString());

		newImtdLevelValues.setCreatedDate(RandomTestUtil.nextDate());

		newImtdLevelValues.setCreatedBy(RandomTestUtil.nextInt());

		newImtdLevelValues.setLevelNo(RandomTestUtil.nextInt());

		newImtdLevelValues.setVersionNo(RandomTestUtil.nextInt());

		newImtdLevelValues.setModifiedBy(RandomTestUtil.nextInt());

		newImtdLevelValues.setModifiedDate(RandomTestUtil.nextDate());

		newImtdLevelValues.setLevelName(RandomTestUtil.randomString());

		_imtdLevelValueses.add(_persistence.update(newImtdLevelValues));

		ImtdLevelValues existingImtdLevelValues = _persistence.findByPrimaryKey(newImtdLevelValues.getPrimaryKey());

		Assert.assertEquals(existingImtdLevelValues.getLevelValues(),
			newImtdLevelValues.getLevelValues());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdLevelValues.getCreatedDate()),
			Time.getShortTimestamp(newImtdLevelValues.getCreatedDate()));
		Assert.assertEquals(existingImtdLevelValues.getCreatedBy(),
			newImtdLevelValues.getCreatedBy());
		Assert.assertEquals(existingImtdLevelValues.getImtdLevelValuesSid(),
			newImtdLevelValues.getImtdLevelValuesSid());
		Assert.assertEquals(existingImtdLevelValues.getLevelNo(),
			newImtdLevelValues.getLevelNo());
		Assert.assertEquals(existingImtdLevelValues.getVersionNo(),
			newImtdLevelValues.getVersionNo());
		Assert.assertEquals(existingImtdLevelValues.getModifiedBy(),
			newImtdLevelValues.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdLevelValues.getModifiedDate()),
			Time.getShortTimestamp(newImtdLevelValues.getModifiedDate()));
		Assert.assertEquals(existingImtdLevelValues.getLevelName(),
			newImtdLevelValues.getLevelName());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ImtdLevelValues newImtdLevelValues = addImtdLevelValues();

		ImtdLevelValues existingImtdLevelValues = _persistence.findByPrimaryKey(newImtdLevelValues.getPrimaryKey());

		Assert.assertEquals(existingImtdLevelValues, newImtdLevelValues);
	}

	@Test(expected = NoSuchImtdLevelValuesException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<ImtdLevelValues> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("IMTD_LEVEL_VALUES",
			"levelValues", true, "createdDate", true, "createdBy", true,
			"imtdLevelValuesSid", true, "levelNo", true, "versionNo", true,
			"modifiedBy", true, "modifiedDate", true, "levelName", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ImtdLevelValues newImtdLevelValues = addImtdLevelValues();

		ImtdLevelValues existingImtdLevelValues = _persistence.fetchByPrimaryKey(newImtdLevelValues.getPrimaryKey());

		Assert.assertEquals(existingImtdLevelValues, newImtdLevelValues);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ImtdLevelValues missingImtdLevelValues = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingImtdLevelValues);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ImtdLevelValues newImtdLevelValues1 = addImtdLevelValues();
		ImtdLevelValues newImtdLevelValues2 = addImtdLevelValues();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newImtdLevelValues1.getPrimaryKey());
		primaryKeys.add(newImtdLevelValues2.getPrimaryKey());

		Map<Serializable, ImtdLevelValues> imtdLevelValueses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, imtdLevelValueses.size());
		Assert.assertEquals(newImtdLevelValues1,
			imtdLevelValueses.get(newImtdLevelValues1.getPrimaryKey()));
		Assert.assertEquals(newImtdLevelValues2,
			imtdLevelValueses.get(newImtdLevelValues2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ImtdLevelValues> imtdLevelValueses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(imtdLevelValueses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ImtdLevelValues newImtdLevelValues = addImtdLevelValues();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newImtdLevelValues.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ImtdLevelValues> imtdLevelValueses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, imtdLevelValueses.size());
		Assert.assertEquals(newImtdLevelValues,
			imtdLevelValueses.get(newImtdLevelValues.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ImtdLevelValues> imtdLevelValueses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(imtdLevelValueses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ImtdLevelValues newImtdLevelValues = addImtdLevelValues();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newImtdLevelValues.getPrimaryKey());

		Map<Serializable, ImtdLevelValues> imtdLevelValueses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, imtdLevelValueses.size());
		Assert.assertEquals(newImtdLevelValues,
			imtdLevelValueses.get(newImtdLevelValues.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ImtdLevelValuesLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ImtdLevelValues>() {
				@Override
				public void performAction(ImtdLevelValues imtdLevelValues) {
					Assert.assertNotNull(imtdLevelValues);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ImtdLevelValues newImtdLevelValues = addImtdLevelValues();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdLevelValues.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("imtdLevelValuesSid",
				newImtdLevelValues.getImtdLevelValuesSid()));

		List<ImtdLevelValues> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ImtdLevelValues existingImtdLevelValues = result.get(0);

		Assert.assertEquals(existingImtdLevelValues, newImtdLevelValues);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdLevelValues.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("imtdLevelValuesSid",
				RandomTestUtil.nextInt()));

		List<ImtdLevelValues> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ImtdLevelValues newImtdLevelValues = addImtdLevelValues();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdLevelValues.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"imtdLevelValuesSid"));

		Object newImtdLevelValuesSid = newImtdLevelValues.getImtdLevelValuesSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("imtdLevelValuesSid",
				new Object[] { newImtdLevelValuesSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingImtdLevelValuesSid = result.get(0);

		Assert.assertEquals(existingImtdLevelValuesSid, newImtdLevelValuesSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdLevelValues.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"imtdLevelValuesSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("imtdLevelValuesSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ImtdLevelValues addImtdLevelValues() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ImtdLevelValues imtdLevelValues = _persistence.create(pk);

		imtdLevelValues.setLevelValues(RandomTestUtil.randomString());

		imtdLevelValues.setCreatedDate(RandomTestUtil.nextDate());

		imtdLevelValues.setCreatedBy(RandomTestUtil.nextInt());

		imtdLevelValues.setLevelNo(RandomTestUtil.nextInt());

		imtdLevelValues.setVersionNo(RandomTestUtil.nextInt());

		imtdLevelValues.setModifiedBy(RandomTestUtil.nextInt());

		imtdLevelValues.setModifiedDate(RandomTestUtil.nextDate());

		imtdLevelValues.setLevelName(RandomTestUtil.randomString());

		_imtdLevelValueses.add(_persistence.update(imtdLevelValues));

		return imtdLevelValues;
	}

	private List<ImtdLevelValues> _imtdLevelValueses = new ArrayList<ImtdLevelValues>();
	private ImtdLevelValuesPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}