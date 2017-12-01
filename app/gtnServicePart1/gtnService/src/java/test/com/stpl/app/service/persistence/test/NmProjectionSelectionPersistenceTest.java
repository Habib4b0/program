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

import com.stpl.app.exception.NoSuchNmProjectionSelectionException;
import com.stpl.app.model.NmProjectionSelection;
import com.stpl.app.service.NmProjectionSelectionLocalServiceUtil;
import com.stpl.app.service.persistence.NmProjectionSelectionPersistence;
import com.stpl.app.service.persistence.NmProjectionSelectionUtil;

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
public class NmProjectionSelectionPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = NmProjectionSelectionUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<NmProjectionSelection> iterator = _nmProjectionSelections.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		NmProjectionSelection nmProjectionSelection = _persistence.create(pk);

		Assert.assertNotNull(nmProjectionSelection);

		Assert.assertEquals(nmProjectionSelection.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		NmProjectionSelection newNmProjectionSelection = addNmProjectionSelection();

		_persistence.remove(newNmProjectionSelection);

		NmProjectionSelection existingNmProjectionSelection = _persistence.fetchByPrimaryKey(newNmProjectionSelection.getPrimaryKey());

		Assert.assertNull(existingNmProjectionSelection);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addNmProjectionSelection();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		NmProjectionSelection newNmProjectionSelection = _persistence.create(pk);

		newNmProjectionSelection.setScreenName(RandomTestUtil.randomString());

		newNmProjectionSelection.setFieldName(RandomTestUtil.randomString());

		newNmProjectionSelection.setFieldValues(RandomTestUtil.randomString());

		newNmProjectionSelection.setProjectionMasterSid(RandomTestUtil.nextInt());

		_nmProjectionSelections.add(_persistence.update(
				newNmProjectionSelection));

		NmProjectionSelection existingNmProjectionSelection = _persistence.findByPrimaryKey(newNmProjectionSelection.getPrimaryKey());

		Assert.assertEquals(existingNmProjectionSelection.getScreenName(),
			newNmProjectionSelection.getScreenName());
		Assert.assertEquals(existingNmProjectionSelection.getNmProjectionSelectionSid(),
			newNmProjectionSelection.getNmProjectionSelectionSid());
		Assert.assertEquals(existingNmProjectionSelection.getFieldName(),
			newNmProjectionSelection.getFieldName());
		Assert.assertEquals(existingNmProjectionSelection.getFieldValues(),
			newNmProjectionSelection.getFieldValues());
		Assert.assertEquals(existingNmProjectionSelection.getProjectionMasterSid(),
			newNmProjectionSelection.getProjectionMasterSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		NmProjectionSelection newNmProjectionSelection = addNmProjectionSelection();

		NmProjectionSelection existingNmProjectionSelection = _persistence.findByPrimaryKey(newNmProjectionSelection.getPrimaryKey());

		Assert.assertEquals(existingNmProjectionSelection,
			newNmProjectionSelection);
	}

	@Test(expected = NoSuchNmProjectionSelectionException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<NmProjectionSelection> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("NM_PROJECTION_SELECTION",
			"screenName", true, "nmProjectionSelectionSid", true, "fieldName",
			true, "fieldValues", true, "projectionMasterSid", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		NmProjectionSelection newNmProjectionSelection = addNmProjectionSelection();

		NmProjectionSelection existingNmProjectionSelection = _persistence.fetchByPrimaryKey(newNmProjectionSelection.getPrimaryKey());

		Assert.assertEquals(existingNmProjectionSelection,
			newNmProjectionSelection);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		NmProjectionSelection missingNmProjectionSelection = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingNmProjectionSelection);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		NmProjectionSelection newNmProjectionSelection1 = addNmProjectionSelection();
		NmProjectionSelection newNmProjectionSelection2 = addNmProjectionSelection();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNmProjectionSelection1.getPrimaryKey());
		primaryKeys.add(newNmProjectionSelection2.getPrimaryKey());

		Map<Serializable, NmProjectionSelection> nmProjectionSelections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, nmProjectionSelections.size());
		Assert.assertEquals(newNmProjectionSelection1,
			nmProjectionSelections.get(
				newNmProjectionSelection1.getPrimaryKey()));
		Assert.assertEquals(newNmProjectionSelection2,
			nmProjectionSelections.get(
				newNmProjectionSelection2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, NmProjectionSelection> nmProjectionSelections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(nmProjectionSelections.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		NmProjectionSelection newNmProjectionSelection = addNmProjectionSelection();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNmProjectionSelection.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, NmProjectionSelection> nmProjectionSelections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, nmProjectionSelections.size());
		Assert.assertEquals(newNmProjectionSelection,
			nmProjectionSelections.get(newNmProjectionSelection.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, NmProjectionSelection> nmProjectionSelections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(nmProjectionSelections.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		NmProjectionSelection newNmProjectionSelection = addNmProjectionSelection();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNmProjectionSelection.getPrimaryKey());

		Map<Serializable, NmProjectionSelection> nmProjectionSelections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, nmProjectionSelections.size());
		Assert.assertEquals(newNmProjectionSelection,
			nmProjectionSelections.get(newNmProjectionSelection.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = NmProjectionSelectionLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<NmProjectionSelection>() {
				@Override
				public void performAction(
					NmProjectionSelection nmProjectionSelection) {
					Assert.assertNotNull(nmProjectionSelection);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		NmProjectionSelection newNmProjectionSelection = addNmProjectionSelection();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NmProjectionSelection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"nmProjectionSelectionSid",
				newNmProjectionSelection.getNmProjectionSelectionSid()));

		List<NmProjectionSelection> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		NmProjectionSelection existingNmProjectionSelection = result.get(0);

		Assert.assertEquals(existingNmProjectionSelection,
			newNmProjectionSelection);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NmProjectionSelection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"nmProjectionSelectionSid", RandomTestUtil.nextInt()));

		List<NmProjectionSelection> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		NmProjectionSelection newNmProjectionSelection = addNmProjectionSelection();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NmProjectionSelection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"nmProjectionSelectionSid"));

		Object newNmProjectionSelectionSid = newNmProjectionSelection.getNmProjectionSelectionSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"nmProjectionSelectionSid",
				new Object[] { newNmProjectionSelectionSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingNmProjectionSelectionSid = result.get(0);

		Assert.assertEquals(existingNmProjectionSelectionSid,
			newNmProjectionSelectionSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NmProjectionSelection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"nmProjectionSelectionSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"nmProjectionSelectionSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected NmProjectionSelection addNmProjectionSelection()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		NmProjectionSelection nmProjectionSelection = _persistence.create(pk);

		nmProjectionSelection.setScreenName(RandomTestUtil.randomString());

		nmProjectionSelection.setFieldName(RandomTestUtil.randomString());

		nmProjectionSelection.setFieldValues(RandomTestUtil.randomString());

		nmProjectionSelection.setProjectionMasterSid(RandomTestUtil.nextInt());

		_nmProjectionSelections.add(_persistence.update(nmProjectionSelection));

		return nmProjectionSelection;
	}

	private List<NmProjectionSelection> _nmProjectionSelections = new ArrayList<NmProjectionSelection>();
	private NmProjectionSelectionPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}