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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchStSelectionTableException;
import com.stpl.app.model.StSelectionTable;
import com.stpl.app.service.persistence.StSelectionTablePK;
import com.stpl.app.service.persistence.StSelectionTablePersistence;
import com.stpl.app.service.persistence.StSelectionTableUtil;

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
public class StSelectionTablePersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = StSelectionTableUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<StSelectionTable> iterator = _stSelectionTables.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		StSelectionTablePK pk = new StSelectionTablePK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt());

		StSelectionTable stSelectionTable = _persistence.create(pk);

		Assert.assertNotNull(stSelectionTable);

		Assert.assertEquals(stSelectionTable.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		StSelectionTable newStSelectionTable = addStSelectionTable();

		_persistence.remove(newStSelectionTable);

		StSelectionTable existingStSelectionTable = _persistence.fetchByPrimaryKey(newStSelectionTable.getPrimaryKey());

		Assert.assertNull(existingStSelectionTable);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addStSelectionTable();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		StSelectionTablePK pk = new StSelectionTablePK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt());

		StSelectionTable newStSelectionTable = _persistence.create(pk);

		newStSelectionTable.setCheckRecord(RandomTestUtil.randomBoolean());

		_stSelectionTables.add(_persistence.update(newStSelectionTable));

		StSelectionTable existingStSelectionTable = _persistence.findByPrimaryKey(newStSelectionTable.getPrimaryKey());

		Assert.assertEquals(existingStSelectionTable.getSelectionType(),
			newStSelectionTable.getSelectionType());
		Assert.assertEquals(existingStSelectionTable.getUserId(),
			newStSelectionTable.getUserId());
		Assert.assertEquals(existingStSelectionTable.getSessionId(),
			newStSelectionTable.getSessionId());
		Assert.assertEquals(existingStSelectionTable.getCompanyItemSid(),
			newStSelectionTable.getCompanyItemSid());
		Assert.assertEquals(existingStSelectionTable.getCheckRecord(),
			newStSelectionTable.getCheckRecord());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		StSelectionTable newStSelectionTable = addStSelectionTable();

		StSelectionTable existingStSelectionTable = _persistence.findByPrimaryKey(newStSelectionTable.getPrimaryKey());

		Assert.assertEquals(existingStSelectionTable, newStSelectionTable);
	}

	@Test(expected = NoSuchStSelectionTableException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		StSelectionTablePK pk = new StSelectionTablePK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		StSelectionTable newStSelectionTable = addStSelectionTable();

		StSelectionTable existingStSelectionTable = _persistence.fetchByPrimaryKey(newStSelectionTable.getPrimaryKey());

		Assert.assertEquals(existingStSelectionTable, newStSelectionTable);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		StSelectionTablePK pk = new StSelectionTablePK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt());

		StSelectionTable missingStSelectionTable = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingStSelectionTable);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		StSelectionTable newStSelectionTable1 = addStSelectionTable();
		StSelectionTable newStSelectionTable2 = addStSelectionTable();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStSelectionTable1.getPrimaryKey());
		primaryKeys.add(newStSelectionTable2.getPrimaryKey());

		Map<Serializable, StSelectionTable> stSelectionTables = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, stSelectionTables.size());
		Assert.assertEquals(newStSelectionTable1,
			stSelectionTables.get(newStSelectionTable1.getPrimaryKey()));
		Assert.assertEquals(newStSelectionTable2,
			stSelectionTables.get(newStSelectionTable2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		StSelectionTablePK pk1 = new StSelectionTablePK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt());

		StSelectionTablePK pk2 = new StSelectionTablePK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, StSelectionTable> stSelectionTables = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stSelectionTables.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		StSelectionTable newStSelectionTable = addStSelectionTable();

		StSelectionTablePK pk = new StSelectionTablePK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStSelectionTable.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, StSelectionTable> stSelectionTables = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stSelectionTables.size());
		Assert.assertEquals(newStSelectionTable,
			stSelectionTables.get(newStSelectionTable.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, StSelectionTable> stSelectionTables = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stSelectionTables.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		StSelectionTable newStSelectionTable = addStSelectionTable();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStSelectionTable.getPrimaryKey());

		Map<Serializable, StSelectionTable> stSelectionTables = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stSelectionTables.size());
		Assert.assertEquals(newStSelectionTable,
			stSelectionTables.get(newStSelectionTable.getPrimaryKey()));
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		StSelectionTable newStSelectionTable = addStSelectionTable();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StSelectionTable.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.selectionType",
				newStSelectionTable.getSelectionType()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				newStSelectionTable.getUserId()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				newStSelectionTable.getSessionId()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.companyItemSid",
				newStSelectionTable.getCompanyItemSid()));

		List<StSelectionTable> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		StSelectionTable existingStSelectionTable = result.get(0);

		Assert.assertEquals(existingStSelectionTable, newStSelectionTable);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StSelectionTable.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.selectionType",
				RandomTestUtil.randomString()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				RandomTestUtil.randomString()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.companyItemSid",
				RandomTestUtil.nextInt()));

		List<StSelectionTable> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		StSelectionTable newStSelectionTable = addStSelectionTable();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StSelectionTable.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.selectionType"));

		Object newSelectionType = newStSelectionTable.getSelectionType();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.selectionType",
				new Object[] { newSelectionType }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingSelectionType = result.get(0);

		Assert.assertEquals(existingSelectionType, newSelectionType);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StSelectionTable.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.selectionType"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.selectionType",
				new Object[] { RandomTestUtil.randomString() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected StSelectionTable addStSelectionTable() throws Exception {
		StSelectionTablePK pk = new StSelectionTablePK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt());

		StSelectionTable stSelectionTable = _persistence.create(pk);

		stSelectionTable.setCheckRecord(RandomTestUtil.randomBoolean());

		_stSelectionTables.add(_persistence.update(stSelectionTable));

		return stSelectionTable;
	}

	private List<StSelectionTable> _stSelectionTables = new ArrayList<StSelectionTable>();
	private StSelectionTablePersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}