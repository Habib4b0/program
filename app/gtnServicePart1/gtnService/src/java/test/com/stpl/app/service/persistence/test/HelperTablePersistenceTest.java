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
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchHelperTableException;
import com.stpl.app.model.HelperTable;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.persistence.HelperTablePersistence;
import com.stpl.app.service.persistence.HelperTableUtil;

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
import java.util.Objects;
import java.util.Set;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class HelperTablePersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = HelperTableUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<HelperTable> iterator = _helperTables.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		HelperTable helperTable = _persistence.create(pk);

		Assert.assertNotNull(helperTable);

		Assert.assertEquals(helperTable.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		HelperTable newHelperTable = addHelperTable();

		_persistence.remove(newHelperTable);

		HelperTable existingHelperTable = _persistence.fetchByPrimaryKey(newHelperTable.getPrimaryKey());

		Assert.assertNull(existingHelperTable);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addHelperTable();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		HelperTable newHelperTable = _persistence.create(pk);

		newHelperTable.setCreatedDate(RandomTestUtil.nextDate());

		newHelperTable.setCreatedBy(RandomTestUtil.nextInt());

		newHelperTable.setDescription(RandomTestUtil.randomString());

		newHelperTable.setRefCount(RandomTestUtil.nextInt());

		newHelperTable.setListName(RandomTestUtil.randomString());

		newHelperTable.setModifiedBy(RandomTestUtil.nextInt());

		newHelperTable.setModifiedDate(RandomTestUtil.nextDate());

		newHelperTable.setAliasName(RandomTestUtil.randomString());

		_helperTables.add(_persistence.update(newHelperTable));

		HelperTable existingHelperTable = _persistence.findByPrimaryKey(newHelperTable.getPrimaryKey());

		Assert.assertEquals(Time.getShortTimestamp(
				existingHelperTable.getCreatedDate()),
			Time.getShortTimestamp(newHelperTable.getCreatedDate()));
		Assert.assertEquals(existingHelperTable.getCreatedBy(),
			newHelperTable.getCreatedBy());
		Assert.assertEquals(existingHelperTable.getDescription(),
			newHelperTable.getDescription());
		Assert.assertEquals(existingHelperTable.getRefCount(),
			newHelperTable.getRefCount());
		Assert.assertEquals(existingHelperTable.getListName(),
			newHelperTable.getListName());
		Assert.assertEquals(existingHelperTable.getHelperTableSid(),
			newHelperTable.getHelperTableSid());
		Assert.assertEquals(existingHelperTable.getModifiedBy(),
			newHelperTable.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingHelperTable.getModifiedDate()),
			Time.getShortTimestamp(newHelperTable.getModifiedDate()));
		Assert.assertEquals(existingHelperTable.getAliasName(),
			newHelperTable.getAliasName());
	}

	@Test
	public void testCountByHelperTableDetails() throws Exception {
		_persistence.countByHelperTableDetails(StringPool.BLANK);

		_persistence.countByHelperTableDetails(StringPool.NULL);

		_persistence.countByHelperTableDetails((String)null);
	}

	@Test
	public void testCountByHelperTableDetailsByHelperTableSid()
		throws Exception {
		_persistence.countByHelperTableDetailsByHelperTableSid(RandomTestUtil.nextInt());

		_persistence.countByHelperTableDetailsByHelperTableSid(0);
	}

	@Test
	public void testCountByHelperTableDetailsByDesc() throws Exception {
		_persistence.countByHelperTableDetailsByDesc(StringPool.BLANK);

		_persistence.countByHelperTableDetailsByDesc(StringPool.NULL);

		_persistence.countByHelperTableDetailsByDesc((String)null);
	}

	@Test
	public void testCountByHelperTableDetailsByCode() throws Exception {
		_persistence.countByHelperTableDetailsByCode(RandomTestUtil.nextInt());

		_persistence.countByHelperTableDetailsByCode(0);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		HelperTable newHelperTable = addHelperTable();

		HelperTable existingHelperTable = _persistence.findByPrimaryKey(newHelperTable.getPrimaryKey());

		Assert.assertEquals(existingHelperTable, newHelperTable);
	}

	@Test(expected = NoSuchHelperTableException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<HelperTable> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("HELPER_TABLE",
			"createdDate", true, "createdBy", true, "description", true,
			"refCount", true, "listName", true, "helperTableSid", true,
			"modifiedBy", true, "modifiedDate", true, "aliasName", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		HelperTable newHelperTable = addHelperTable();

		HelperTable existingHelperTable = _persistence.fetchByPrimaryKey(newHelperTable.getPrimaryKey());

		Assert.assertEquals(existingHelperTable, newHelperTable);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		HelperTable missingHelperTable = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingHelperTable);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		HelperTable newHelperTable1 = addHelperTable();
		HelperTable newHelperTable2 = addHelperTable();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHelperTable1.getPrimaryKey());
		primaryKeys.add(newHelperTable2.getPrimaryKey());

		Map<Serializable, HelperTable> helperTables = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, helperTables.size());
		Assert.assertEquals(newHelperTable1,
			helperTables.get(newHelperTable1.getPrimaryKey()));
		Assert.assertEquals(newHelperTable2,
			helperTables.get(newHelperTable2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, HelperTable> helperTables = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(helperTables.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		HelperTable newHelperTable = addHelperTable();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHelperTable.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, HelperTable> helperTables = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, helperTables.size());
		Assert.assertEquals(newHelperTable,
			helperTables.get(newHelperTable.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, HelperTable> helperTables = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(helperTables.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		HelperTable newHelperTable = addHelperTable();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHelperTable.getPrimaryKey());

		Map<Serializable, HelperTable> helperTables = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, helperTables.size());
		Assert.assertEquals(newHelperTable,
			helperTables.get(newHelperTable.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = HelperTableLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<HelperTable>() {
				@Override
				public void performAction(HelperTable helperTable) {
					Assert.assertNotNull(helperTable);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		HelperTable newHelperTable = addHelperTable();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("helperTableSid",
				newHelperTable.getHelperTableSid()));

		List<HelperTable> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		HelperTable existingHelperTable = result.get(0);

		Assert.assertEquals(existingHelperTable, newHelperTable);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("helperTableSid",
				RandomTestUtil.nextInt()));

		List<HelperTable> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		HelperTable newHelperTable = addHelperTable();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"helperTableSid"));

		Object newHelperTableSid = newHelperTable.getHelperTableSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("helperTableSid",
				new Object[] { newHelperTableSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingHelperTableSid = result.get(0);

		Assert.assertEquals(existingHelperTableSid, newHelperTableSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"helperTableSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("helperTableSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		HelperTable newHelperTable = addHelperTable();

		_persistence.clearCache();

		HelperTable existingHelperTable = _persistence.findByPrimaryKey(newHelperTable.getPrimaryKey());

		Assert.assertEquals(Integer.valueOf(
				existingHelperTable.getHelperTableSid()),
			ReflectionTestUtil.<Integer>invoke(existingHelperTable,
				"getOriginalHelperTableSid", new Class<?>[0]));

		Assert.assertTrue(Objects.equals(existingHelperTable.getDescription(),
				ReflectionTestUtil.invoke(existingHelperTable,
					"getOriginalDescription", new Class<?>[0])));

		Assert.assertEquals(Integer.valueOf(
				existingHelperTable.getHelperTableSid()),
			ReflectionTestUtil.<Integer>invoke(existingHelperTable,
				"getOriginalHelperTableSid", new Class<?>[0]));
	}

	protected HelperTable addHelperTable() throws Exception {
		int pk = RandomTestUtil.nextInt();

		HelperTable helperTable = _persistence.create(pk);

		helperTable.setCreatedDate(RandomTestUtil.nextDate());

		helperTable.setCreatedBy(RandomTestUtil.nextInt());

		helperTable.setDescription(RandomTestUtil.randomString());

		helperTable.setRefCount(RandomTestUtil.nextInt());

		helperTable.setListName(RandomTestUtil.randomString());

		helperTable.setModifiedBy(RandomTestUtil.nextInt());

		helperTable.setModifiedDate(RandomTestUtil.nextDate());

		helperTable.setAliasName(RandomTestUtil.randomString());

		_helperTables.add(_persistence.update(helperTable));

		return helperTable;
	}

	private List<HelperTable> _helperTables = new ArrayList<HelperTable>();
	private HelperTablePersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}