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

import com.stpl.app.exception.NoSuchNaProjectionSelectionException;
import com.stpl.app.model.NaProjectionSelection;
import com.stpl.app.service.NaProjectionSelectionLocalServiceUtil;
import com.stpl.app.service.persistence.NaProjectionSelectionPersistence;
import com.stpl.app.service.persistence.NaProjectionSelectionUtil;

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
public class NaProjectionSelectionPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = NaProjectionSelectionUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<NaProjectionSelection> iterator = _naProjectionSelections.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		NaProjectionSelection naProjectionSelection = _persistence.create(pk);

		Assert.assertNotNull(naProjectionSelection);

		Assert.assertEquals(naProjectionSelection.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		NaProjectionSelection newNaProjectionSelection = addNaProjectionSelection();

		_persistence.remove(newNaProjectionSelection);

		NaProjectionSelection existingNaProjectionSelection = _persistence.fetchByPrimaryKey(newNaProjectionSelection.getPrimaryKey());

		Assert.assertNull(existingNaProjectionSelection);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addNaProjectionSelection();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		NaProjectionSelection newNaProjectionSelection = _persistence.create(pk);

		newNaProjectionSelection.setScreenName(RandomTestUtil.randomString());

		newNaProjectionSelection.setFieldName(RandomTestUtil.randomString());

		newNaProjectionSelection.setFieldValues(RandomTestUtil.randomString());

		newNaProjectionSelection.setNaProjMasterSid(RandomTestUtil.nextInt());

		_naProjectionSelections.add(_persistence.update(
				newNaProjectionSelection));

		NaProjectionSelection existingNaProjectionSelection = _persistence.findByPrimaryKey(newNaProjectionSelection.getPrimaryKey());

		Assert.assertEquals(existingNaProjectionSelection.getScreenName(),
			newNaProjectionSelection.getScreenName());
		Assert.assertEquals(existingNaProjectionSelection.getFieldName(),
			newNaProjectionSelection.getFieldName());
		Assert.assertEquals(existingNaProjectionSelection.getFieldValues(),
			newNaProjectionSelection.getFieldValues());
		Assert.assertEquals(existingNaProjectionSelection.getNaProjectionSelectionSid(),
			newNaProjectionSelection.getNaProjectionSelectionSid());
		Assert.assertEquals(existingNaProjectionSelection.getNaProjMasterSid(),
			newNaProjectionSelection.getNaProjMasterSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		NaProjectionSelection newNaProjectionSelection = addNaProjectionSelection();

		NaProjectionSelection existingNaProjectionSelection = _persistence.findByPrimaryKey(newNaProjectionSelection.getPrimaryKey());

		Assert.assertEquals(existingNaProjectionSelection,
			newNaProjectionSelection);
	}

	@Test(expected = NoSuchNaProjectionSelectionException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<NaProjectionSelection> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("NA_PROJECTION_SELECTION",
			"screenName", true, "fieldName", true, "fieldValues", true,
			"naProjectionSelectionSid", true, "naProjMasterSid", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		NaProjectionSelection newNaProjectionSelection = addNaProjectionSelection();

		NaProjectionSelection existingNaProjectionSelection = _persistence.fetchByPrimaryKey(newNaProjectionSelection.getPrimaryKey());

		Assert.assertEquals(existingNaProjectionSelection,
			newNaProjectionSelection);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		NaProjectionSelection missingNaProjectionSelection = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingNaProjectionSelection);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		NaProjectionSelection newNaProjectionSelection1 = addNaProjectionSelection();
		NaProjectionSelection newNaProjectionSelection2 = addNaProjectionSelection();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNaProjectionSelection1.getPrimaryKey());
		primaryKeys.add(newNaProjectionSelection2.getPrimaryKey());

		Map<Serializable, NaProjectionSelection> naProjectionSelections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, naProjectionSelections.size());
		Assert.assertEquals(newNaProjectionSelection1,
			naProjectionSelections.get(
				newNaProjectionSelection1.getPrimaryKey()));
		Assert.assertEquals(newNaProjectionSelection2,
			naProjectionSelections.get(
				newNaProjectionSelection2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, NaProjectionSelection> naProjectionSelections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(naProjectionSelections.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		NaProjectionSelection newNaProjectionSelection = addNaProjectionSelection();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNaProjectionSelection.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, NaProjectionSelection> naProjectionSelections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, naProjectionSelections.size());
		Assert.assertEquals(newNaProjectionSelection,
			naProjectionSelections.get(newNaProjectionSelection.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, NaProjectionSelection> naProjectionSelections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(naProjectionSelections.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		NaProjectionSelection newNaProjectionSelection = addNaProjectionSelection();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNaProjectionSelection.getPrimaryKey());

		Map<Serializable, NaProjectionSelection> naProjectionSelections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, naProjectionSelections.size());
		Assert.assertEquals(newNaProjectionSelection,
			naProjectionSelections.get(newNaProjectionSelection.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = NaProjectionSelectionLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<NaProjectionSelection>() {
				@Override
				public void performAction(
					NaProjectionSelection naProjectionSelection) {
					Assert.assertNotNull(naProjectionSelection);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		NaProjectionSelection newNaProjectionSelection = addNaProjectionSelection();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NaProjectionSelection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"naProjectionSelectionSid",
				newNaProjectionSelection.getNaProjectionSelectionSid()));

		List<NaProjectionSelection> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		NaProjectionSelection existingNaProjectionSelection = result.get(0);

		Assert.assertEquals(existingNaProjectionSelection,
			newNaProjectionSelection);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NaProjectionSelection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"naProjectionSelectionSid", RandomTestUtil.nextInt()));

		List<NaProjectionSelection> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		NaProjectionSelection newNaProjectionSelection = addNaProjectionSelection();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NaProjectionSelection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"naProjectionSelectionSid"));

		Object newNaProjectionSelectionSid = newNaProjectionSelection.getNaProjectionSelectionSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"naProjectionSelectionSid",
				new Object[] { newNaProjectionSelectionSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingNaProjectionSelectionSid = result.get(0);

		Assert.assertEquals(existingNaProjectionSelectionSid,
			newNaProjectionSelectionSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NaProjectionSelection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"naProjectionSelectionSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"naProjectionSelectionSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected NaProjectionSelection addNaProjectionSelection()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		NaProjectionSelection naProjectionSelection = _persistence.create(pk);

		naProjectionSelection.setScreenName(RandomTestUtil.randomString());

		naProjectionSelection.setFieldName(RandomTestUtil.randomString());

		naProjectionSelection.setFieldValues(RandomTestUtil.randomString());

		naProjectionSelection.setNaProjMasterSid(RandomTestUtil.nextInt());

		_naProjectionSelections.add(_persistence.update(naProjectionSelection));

		return naProjectionSelection;
	}

	private List<NaProjectionSelection> _naProjectionSelections = new ArrayList<NaProjectionSelection>();
	private NaProjectionSelectionPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}