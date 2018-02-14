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

import com.stpl.app.exception.NoSuchTransactionModuleMasterException;
import com.stpl.app.model.TransactionModuleMaster;
import com.stpl.app.service.TransactionModuleMasterLocalServiceUtil;
import com.stpl.app.service.persistence.TransactionModuleMasterPersistence;
import com.stpl.app.service.persistence.TransactionModuleMasterUtil;

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
public class TransactionModuleMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = TransactionModuleMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<TransactionModuleMaster> iterator = _transactionModuleMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		TransactionModuleMaster transactionModuleMaster = _persistence.create(pk);

		Assert.assertNotNull(transactionModuleMaster);

		Assert.assertEquals(transactionModuleMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		TransactionModuleMaster newTransactionModuleMaster = addTransactionModuleMaster();

		_persistence.remove(newTransactionModuleMaster);

		TransactionModuleMaster existingTransactionModuleMaster = _persistence.fetchByPrimaryKey(newTransactionModuleMaster.getPrimaryKey());

		Assert.assertNull(existingTransactionModuleMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addTransactionModuleMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		TransactionModuleMaster newTransactionModuleMaster = _persistence.create(pk);

		newTransactionModuleMaster.setInvalidTableName(RandomTestUtil.randomString());

		newTransactionModuleMaster.setTableName(RandomTestUtil.randomString());

		newTransactionModuleMaster.setModuleName(RandomTestUtil.randomString());

		newTransactionModuleMaster.setTabName(RandomTestUtil.randomString());

		_transactionModuleMasters.add(_persistence.update(
				newTransactionModuleMaster));

		TransactionModuleMaster existingTransactionModuleMaster = _persistence.findByPrimaryKey(newTransactionModuleMaster.getPrimaryKey());

		Assert.assertEquals(existingTransactionModuleMaster.getTransactionModuleMasterSid(),
			newTransactionModuleMaster.getTransactionModuleMasterSid());
		Assert.assertEquals(existingTransactionModuleMaster.getInvalidTableName(),
			newTransactionModuleMaster.getInvalidTableName());
		Assert.assertEquals(existingTransactionModuleMaster.getTableName(),
			newTransactionModuleMaster.getTableName());
		Assert.assertEquals(existingTransactionModuleMaster.getModuleName(),
			newTransactionModuleMaster.getModuleName());
		Assert.assertEquals(existingTransactionModuleMaster.getTabName(),
			newTransactionModuleMaster.getTabName());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		TransactionModuleMaster newTransactionModuleMaster = addTransactionModuleMaster();

		TransactionModuleMaster existingTransactionModuleMaster = _persistence.findByPrimaryKey(newTransactionModuleMaster.getPrimaryKey());

		Assert.assertEquals(existingTransactionModuleMaster,
			newTransactionModuleMaster);
	}

	@Test(expected = NoSuchTransactionModuleMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<TransactionModuleMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("TRANSACTION_MODULE_MASTER",
			"transactionModuleMasterSid", true, "invalidTableName", true,
			"tableName", true, "moduleName", true, "tabName", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		TransactionModuleMaster newTransactionModuleMaster = addTransactionModuleMaster();

		TransactionModuleMaster existingTransactionModuleMaster = _persistence.fetchByPrimaryKey(newTransactionModuleMaster.getPrimaryKey());

		Assert.assertEquals(existingTransactionModuleMaster,
			newTransactionModuleMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		TransactionModuleMaster missingTransactionModuleMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingTransactionModuleMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		TransactionModuleMaster newTransactionModuleMaster1 = addTransactionModuleMaster();
		TransactionModuleMaster newTransactionModuleMaster2 = addTransactionModuleMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newTransactionModuleMaster1.getPrimaryKey());
		primaryKeys.add(newTransactionModuleMaster2.getPrimaryKey());

		Map<Serializable, TransactionModuleMaster> transactionModuleMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, transactionModuleMasters.size());
		Assert.assertEquals(newTransactionModuleMaster1,
			transactionModuleMasters.get(
				newTransactionModuleMaster1.getPrimaryKey()));
		Assert.assertEquals(newTransactionModuleMaster2,
			transactionModuleMasters.get(
				newTransactionModuleMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, TransactionModuleMaster> transactionModuleMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(transactionModuleMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		TransactionModuleMaster newTransactionModuleMaster = addTransactionModuleMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newTransactionModuleMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, TransactionModuleMaster> transactionModuleMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, transactionModuleMasters.size());
		Assert.assertEquals(newTransactionModuleMaster,
			transactionModuleMasters.get(
				newTransactionModuleMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, TransactionModuleMaster> transactionModuleMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(transactionModuleMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		TransactionModuleMaster newTransactionModuleMaster = addTransactionModuleMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newTransactionModuleMaster.getPrimaryKey());

		Map<Serializable, TransactionModuleMaster> transactionModuleMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, transactionModuleMasters.size());
		Assert.assertEquals(newTransactionModuleMaster,
			transactionModuleMasters.get(
				newTransactionModuleMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = TransactionModuleMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<TransactionModuleMaster>() {
				@Override
				public void performAction(
					TransactionModuleMaster transactionModuleMaster) {
					Assert.assertNotNull(transactionModuleMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		TransactionModuleMaster newTransactionModuleMaster = addTransactionModuleMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(TransactionModuleMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"transactionModuleMasterSid",
				newTransactionModuleMaster.getTransactionModuleMasterSid()));

		List<TransactionModuleMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		TransactionModuleMaster existingTransactionModuleMaster = result.get(0);

		Assert.assertEquals(existingTransactionModuleMaster,
			newTransactionModuleMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(TransactionModuleMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"transactionModuleMasterSid", RandomTestUtil.nextInt()));

		List<TransactionModuleMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		TransactionModuleMaster newTransactionModuleMaster = addTransactionModuleMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(TransactionModuleMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"transactionModuleMasterSid"));

		Object newTransactionModuleMasterSid = newTransactionModuleMaster.getTransactionModuleMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"transactionModuleMasterSid",
				new Object[] { newTransactionModuleMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingTransactionModuleMasterSid = result.get(0);

		Assert.assertEquals(existingTransactionModuleMasterSid,
			newTransactionModuleMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(TransactionModuleMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"transactionModuleMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"transactionModuleMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected TransactionModuleMaster addTransactionModuleMaster()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		TransactionModuleMaster transactionModuleMaster = _persistence.create(pk);

		transactionModuleMaster.setInvalidTableName(RandomTestUtil.randomString());

		transactionModuleMaster.setTableName(RandomTestUtil.randomString());

		transactionModuleMaster.setModuleName(RandomTestUtil.randomString());

		transactionModuleMaster.setTabName(RandomTestUtil.randomString());

		_transactionModuleMasters.add(_persistence.update(
				transactionModuleMaster));

		return transactionModuleMaster;
	}

	private List<TransactionModuleMaster> _transactionModuleMasters = new ArrayList<TransactionModuleMaster>();
	private TransactionModuleMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}