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

import com.stpl.app.exception.NoSuchMProjectionSelectionException;
import com.stpl.app.model.MProjectionSelection;
import com.stpl.app.service.MProjectionSelectionLocalServiceUtil;
import com.stpl.app.service.persistence.MProjectionSelectionPersistence;
import com.stpl.app.service.persistence.MProjectionSelectionUtil;

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
public class MProjectionSelectionPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = MProjectionSelectionUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<MProjectionSelection> iterator = _mProjectionSelections.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		MProjectionSelection mProjectionSelection = _persistence.create(pk);

		Assert.assertNotNull(mProjectionSelection);

		Assert.assertEquals(mProjectionSelection.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		MProjectionSelection newMProjectionSelection = addMProjectionSelection();

		_persistence.remove(newMProjectionSelection);

		MProjectionSelection existingMProjectionSelection = _persistence.fetchByPrimaryKey(newMProjectionSelection.getPrimaryKey());

		Assert.assertNull(existingMProjectionSelection);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addMProjectionSelection();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		MProjectionSelection newMProjectionSelection = _persistence.create(pk);

		newMProjectionSelection.setProjectionMasterSid(RandomTestUtil.nextInt());

		newMProjectionSelection.setFieldValues(RandomTestUtil.randomString());

		newMProjectionSelection.setFieldName(RandomTestUtil.randomString());

		newMProjectionSelection.setScreenName(RandomTestUtil.randomString());

		_mProjectionSelections.add(_persistence.update(newMProjectionSelection));

		MProjectionSelection existingMProjectionSelection = _persistence.findByPrimaryKey(newMProjectionSelection.getPrimaryKey());

		Assert.assertEquals(existingMProjectionSelection.getMProjectionSelectionSid(),
			newMProjectionSelection.getMProjectionSelectionSid());
		Assert.assertEquals(existingMProjectionSelection.getProjectionMasterSid(),
			newMProjectionSelection.getProjectionMasterSid());
		Assert.assertEquals(existingMProjectionSelection.getFieldValues(),
			newMProjectionSelection.getFieldValues());
		Assert.assertEquals(existingMProjectionSelection.getFieldName(),
			newMProjectionSelection.getFieldName());
		Assert.assertEquals(existingMProjectionSelection.getScreenName(),
			newMProjectionSelection.getScreenName());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		MProjectionSelection newMProjectionSelection = addMProjectionSelection();

		MProjectionSelection existingMProjectionSelection = _persistence.findByPrimaryKey(newMProjectionSelection.getPrimaryKey());

		Assert.assertEquals(existingMProjectionSelection,
			newMProjectionSelection);
	}

	@Test(expected = NoSuchMProjectionSelectionException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<MProjectionSelection> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("M_PROJECTION_SELECTION",
			"mProjectionSelectionSid", true, "projectionMasterSid", true,
			"fieldValues", true, "fieldName", true, "screenName", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		MProjectionSelection newMProjectionSelection = addMProjectionSelection();

		MProjectionSelection existingMProjectionSelection = _persistence.fetchByPrimaryKey(newMProjectionSelection.getPrimaryKey());

		Assert.assertEquals(existingMProjectionSelection,
			newMProjectionSelection);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		MProjectionSelection missingMProjectionSelection = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingMProjectionSelection);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		MProjectionSelection newMProjectionSelection1 = addMProjectionSelection();
		MProjectionSelection newMProjectionSelection2 = addMProjectionSelection();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMProjectionSelection1.getPrimaryKey());
		primaryKeys.add(newMProjectionSelection2.getPrimaryKey());

		Map<Serializable, MProjectionSelection> mProjectionSelections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, mProjectionSelections.size());
		Assert.assertEquals(newMProjectionSelection1,
			mProjectionSelections.get(newMProjectionSelection1.getPrimaryKey()));
		Assert.assertEquals(newMProjectionSelection2,
			mProjectionSelections.get(newMProjectionSelection2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, MProjectionSelection> mProjectionSelections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(mProjectionSelections.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		MProjectionSelection newMProjectionSelection = addMProjectionSelection();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMProjectionSelection.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, MProjectionSelection> mProjectionSelections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, mProjectionSelections.size());
		Assert.assertEquals(newMProjectionSelection,
			mProjectionSelections.get(newMProjectionSelection.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, MProjectionSelection> mProjectionSelections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(mProjectionSelections.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		MProjectionSelection newMProjectionSelection = addMProjectionSelection();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMProjectionSelection.getPrimaryKey());

		Map<Serializable, MProjectionSelection> mProjectionSelections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, mProjectionSelections.size());
		Assert.assertEquals(newMProjectionSelection,
			mProjectionSelections.get(newMProjectionSelection.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = MProjectionSelectionLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<MProjectionSelection>() {
				@Override
				public void performAction(
					MProjectionSelection mProjectionSelection) {
					Assert.assertNotNull(mProjectionSelection);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		MProjectionSelection newMProjectionSelection = addMProjectionSelection();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MProjectionSelection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("mProjectionSelectionSid",
				newMProjectionSelection.getMProjectionSelectionSid()));

		List<MProjectionSelection> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		MProjectionSelection existingMProjectionSelection = result.get(0);

		Assert.assertEquals(existingMProjectionSelection,
			newMProjectionSelection);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MProjectionSelection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("mProjectionSelectionSid",
				RandomTestUtil.nextInt()));

		List<MProjectionSelection> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		MProjectionSelection newMProjectionSelection = addMProjectionSelection();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MProjectionSelection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"mProjectionSelectionSid"));

		Object newMProjectionSelectionSid = newMProjectionSelection.getMProjectionSelectionSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("mProjectionSelectionSid",
				new Object[] { newMProjectionSelectionSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingMProjectionSelectionSid = result.get(0);

		Assert.assertEquals(existingMProjectionSelectionSid,
			newMProjectionSelectionSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MProjectionSelection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"mProjectionSelectionSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("mProjectionSelectionSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected MProjectionSelection addMProjectionSelection()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		MProjectionSelection mProjectionSelection = _persistence.create(pk);

		mProjectionSelection.setProjectionMasterSid(RandomTestUtil.nextInt());

		mProjectionSelection.setFieldValues(RandomTestUtil.randomString());

		mProjectionSelection.setFieldName(RandomTestUtil.randomString());

		mProjectionSelection.setScreenName(RandomTestUtil.randomString());

		_mProjectionSelections.add(_persistence.update(mProjectionSelection));

		return mProjectionSelection;
	}

	private List<MProjectionSelection> _mProjectionSelections = new ArrayList<MProjectionSelection>();
	private MProjectionSelectionPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}