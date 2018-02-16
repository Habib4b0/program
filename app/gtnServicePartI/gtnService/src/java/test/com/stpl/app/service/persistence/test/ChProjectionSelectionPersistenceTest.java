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
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchChProjectionSelectionException;
import com.stpl.app.model.ChProjectionSelection;
import com.stpl.app.service.ChProjectionSelectionLocalServiceUtil;
import com.stpl.app.service.persistence.ChProjectionSelectionPersistence;
import com.stpl.app.service.persistence.ChProjectionSelectionUtil;

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
public class ChProjectionSelectionPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ChProjectionSelectionUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ChProjectionSelection> iterator = _chProjectionSelections.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ChProjectionSelection chProjectionSelection = _persistence.create(pk);

		Assert.assertNotNull(chProjectionSelection);

		Assert.assertEquals(chProjectionSelection.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ChProjectionSelection newChProjectionSelection = addChProjectionSelection();

		_persistence.remove(newChProjectionSelection);

		ChProjectionSelection existingChProjectionSelection = _persistence.fetchByPrimaryKey(newChProjectionSelection.getPrimaryKey());

		Assert.assertNull(existingChProjectionSelection);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addChProjectionSelection();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ChProjectionSelection newChProjectionSelection = _persistence.create(pk);

		newChProjectionSelection.setScreenName(RandomTestUtil.randomString());

		newChProjectionSelection.setFieldName(RandomTestUtil.randomString());

		newChProjectionSelection.setFieldValues(RandomTestUtil.randomString());

		newChProjectionSelection.setProjectionMasterSid(RandomTestUtil.nextInt());

		_chProjectionSelections.add(_persistence.update(
				newChProjectionSelection));

		ChProjectionSelection existingChProjectionSelection = _persistence.findByPrimaryKey(newChProjectionSelection.getPrimaryKey());

		Assert.assertEquals(existingChProjectionSelection.getScreenName(),
			newChProjectionSelection.getScreenName());
		Assert.assertEquals(existingChProjectionSelection.getFieldName(),
			newChProjectionSelection.getFieldName());
		Assert.assertEquals(existingChProjectionSelection.getChProjectionSelectionSid(),
			newChProjectionSelection.getChProjectionSelectionSid());
		Assert.assertEquals(existingChProjectionSelection.getFieldValues(),
			newChProjectionSelection.getFieldValues());
		Assert.assertEquals(existingChProjectionSelection.getProjectionMasterSid(),
			newChProjectionSelection.getProjectionMasterSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ChProjectionSelection newChProjectionSelection = addChProjectionSelection();

		ChProjectionSelection existingChProjectionSelection = _persistence.findByPrimaryKey(newChProjectionSelection.getPrimaryKey());

		Assert.assertEquals(existingChProjectionSelection,
			newChProjectionSelection);
	}

	@Test(expected = NoSuchChProjectionSelectionException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<ChProjectionSelection> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CH_PROJECTION_SELECTION",
			"screenName", true, "fieldName", true, "chProjectionSelectionSid",
			true, "fieldValues", true, "projectionMasterSid", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ChProjectionSelection newChProjectionSelection = addChProjectionSelection();

		ChProjectionSelection existingChProjectionSelection = _persistence.fetchByPrimaryKey(newChProjectionSelection.getPrimaryKey());

		Assert.assertEquals(existingChProjectionSelection,
			newChProjectionSelection);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ChProjectionSelection missingChProjectionSelection = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingChProjectionSelection);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ChProjectionSelection newChProjectionSelection1 = addChProjectionSelection();
		ChProjectionSelection newChProjectionSelection2 = addChProjectionSelection();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newChProjectionSelection1.getPrimaryKey());
		primaryKeys.add(newChProjectionSelection2.getPrimaryKey());

		Map<Serializable, ChProjectionSelection> chProjectionSelections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, chProjectionSelections.size());
		Assert.assertEquals(newChProjectionSelection1,
			chProjectionSelections.get(
				newChProjectionSelection1.getPrimaryKey()));
		Assert.assertEquals(newChProjectionSelection2,
			chProjectionSelections.get(
				newChProjectionSelection2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ChProjectionSelection> chProjectionSelections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(chProjectionSelections.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ChProjectionSelection newChProjectionSelection = addChProjectionSelection();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newChProjectionSelection.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ChProjectionSelection> chProjectionSelections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, chProjectionSelections.size());
		Assert.assertEquals(newChProjectionSelection,
			chProjectionSelections.get(newChProjectionSelection.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ChProjectionSelection> chProjectionSelections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(chProjectionSelections.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ChProjectionSelection newChProjectionSelection = addChProjectionSelection();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newChProjectionSelection.getPrimaryKey());

		Map<Serializable, ChProjectionSelection> chProjectionSelections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, chProjectionSelections.size());
		Assert.assertEquals(newChProjectionSelection,
			chProjectionSelections.get(newChProjectionSelection.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ChProjectionSelectionLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ChProjectionSelection>() {
				@Override
				public void performAction(
					ChProjectionSelection chProjectionSelection) {
					Assert.assertNotNull(chProjectionSelection);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ChProjectionSelection newChProjectionSelection = addChProjectionSelection();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ChProjectionSelection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"chProjectionSelectionSid",
				newChProjectionSelection.getChProjectionSelectionSid()));

		List<ChProjectionSelection> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ChProjectionSelection existingChProjectionSelection = result.get(0);

		Assert.assertEquals(existingChProjectionSelection,
			newChProjectionSelection);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ChProjectionSelection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"chProjectionSelectionSid", RandomTestUtil.nextInt()));

		List<ChProjectionSelection> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ChProjectionSelection newChProjectionSelection = addChProjectionSelection();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ChProjectionSelection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"chProjectionSelectionSid"));

		Object newChProjectionSelectionSid = newChProjectionSelection.getChProjectionSelectionSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"chProjectionSelectionSid",
				new Object[] { newChProjectionSelectionSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingChProjectionSelectionSid = result.get(0);

		Assert.assertEquals(existingChProjectionSelectionSid,
			newChProjectionSelectionSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ChProjectionSelection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"chProjectionSelectionSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"chProjectionSelectionSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ChProjectionSelection addChProjectionSelection()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		ChProjectionSelection chProjectionSelection = _persistence.create(pk);

		chProjectionSelection.setScreenName(RandomTestUtil.randomString());

		chProjectionSelection.setFieldName(RandomTestUtil.randomString());

		chProjectionSelection.setFieldValues(RandomTestUtil.randomString());

		chProjectionSelection.setProjectionMasterSid(RandomTestUtil.nextInt());

		_chProjectionSelections.add(_persistence.update(chProjectionSelection));

		return chProjectionSelection;
	}

	private List<ChProjectionSelection> _chProjectionSelections = new ArrayList<ChProjectionSelection>();
	private ChProjectionSelectionPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}