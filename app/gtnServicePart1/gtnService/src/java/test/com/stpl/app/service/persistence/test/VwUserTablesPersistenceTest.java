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

import com.stpl.app.exception.NoSuchVwUserTablesException;
import com.stpl.app.model.VwUserTables;
import com.stpl.app.service.VwUserTablesLocalServiceUtil;
import com.stpl.app.service.persistence.VwUserTablesPersistence;
import com.stpl.app.service.persistence.VwUserTablesUtil;

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
public class VwUserTablesPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = VwUserTablesUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<VwUserTables> iterator = _vwUserTableses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwUserTables vwUserTables = _persistence.create(pk);

		Assert.assertNotNull(vwUserTables);

		Assert.assertEquals(vwUserTables.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		VwUserTables newVwUserTables = addVwUserTables();

		_persistence.remove(newVwUserTables);

		VwUserTables existingVwUserTables = _persistence.fetchByPrimaryKey(newVwUserTables.getPrimaryKey());

		Assert.assertNull(existingVwUserTables);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addVwUserTables();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwUserTables newVwUserTables = _persistence.create(pk);

		newVwUserTables.setTableName(RandomTestUtil.randomString());

		newVwUserTables.setColumnName(RandomTestUtil.randomString());

		_vwUserTableses.add(_persistence.update(newVwUserTables));

		VwUserTables existingVwUserTables = _persistence.findByPrimaryKey(newVwUserTables.getPrimaryKey());

		Assert.assertEquals(existingVwUserTables.getUniqueId(),
			newVwUserTables.getUniqueId());
		Assert.assertEquals(existingVwUserTables.getTableName(),
			newVwUserTables.getTableName());
		Assert.assertEquals(existingVwUserTables.getColumnName(),
			newVwUserTables.getColumnName());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		VwUserTables newVwUserTables = addVwUserTables();

		VwUserTables existingVwUserTables = _persistence.findByPrimaryKey(newVwUserTables.getPrimaryKey());

		Assert.assertEquals(existingVwUserTables, newVwUserTables);
	}

	@Test(expected = NoSuchVwUserTablesException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<VwUserTables> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("VW_USER_TABLES",
			"uniqueId", true, "tableName", true, "columnName", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		VwUserTables newVwUserTables = addVwUserTables();

		VwUserTables existingVwUserTables = _persistence.fetchByPrimaryKey(newVwUserTables.getPrimaryKey());

		Assert.assertEquals(existingVwUserTables, newVwUserTables);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwUserTables missingVwUserTables = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingVwUserTables);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		VwUserTables newVwUserTables1 = addVwUserTables();
		VwUserTables newVwUserTables2 = addVwUserTables();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwUserTables1.getPrimaryKey());
		primaryKeys.add(newVwUserTables2.getPrimaryKey());

		Map<Serializable, VwUserTables> vwUserTableses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, vwUserTableses.size());
		Assert.assertEquals(newVwUserTables1,
			vwUserTableses.get(newVwUserTables1.getPrimaryKey()));
		Assert.assertEquals(newVwUserTables2,
			vwUserTableses.get(newVwUserTables2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, VwUserTables> vwUserTableses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(vwUserTableses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		VwUserTables newVwUserTables = addVwUserTables();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwUserTables.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, VwUserTables> vwUserTableses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, vwUserTableses.size());
		Assert.assertEquals(newVwUserTables,
			vwUserTableses.get(newVwUserTables.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, VwUserTables> vwUserTableses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(vwUserTableses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		VwUserTables newVwUserTables = addVwUserTables();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwUserTables.getPrimaryKey());

		Map<Serializable, VwUserTables> vwUserTableses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, vwUserTableses.size());
		Assert.assertEquals(newVwUserTables,
			vwUserTableses.get(newVwUserTables.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = VwUserTablesLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<VwUserTables>() {
				@Override
				public void performAction(VwUserTables vwUserTables) {
					Assert.assertNotNull(vwUserTables);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		VwUserTables newVwUserTables = addVwUserTables();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwUserTables.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("uniqueId",
				newVwUserTables.getUniqueId()));

		List<VwUserTables> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		VwUserTables existingVwUserTables = result.get(0);

		Assert.assertEquals(existingVwUserTables, newVwUserTables);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwUserTables.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("uniqueId",
				RandomTestUtil.nextInt()));

		List<VwUserTables> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		VwUserTables newVwUserTables = addVwUserTables();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwUserTables.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("uniqueId"));

		Object newUniqueId = newVwUserTables.getUniqueId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("uniqueId",
				new Object[] { newUniqueId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingUniqueId = result.get(0);

		Assert.assertEquals(existingUniqueId, newUniqueId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwUserTables.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("uniqueId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("uniqueId",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected VwUserTables addVwUserTables() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwUserTables vwUserTables = _persistence.create(pk);

		vwUserTables.setTableName(RandomTestUtil.randomString());

		vwUserTables.setColumnName(RandomTestUtil.randomString());

		_vwUserTableses.add(_persistence.update(vwUserTables));

		return vwUserTables;
	}

	private List<VwUserTables> _vwUserTableses = new ArrayList<VwUserTables>();
	private VwUserTablesPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}