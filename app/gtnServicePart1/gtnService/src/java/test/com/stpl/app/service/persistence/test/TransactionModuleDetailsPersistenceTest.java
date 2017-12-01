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
import com.liferay.portal.kernel.test.AssertUtils;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchTransactionModuleDetailsException;
import com.stpl.app.model.TransactionModuleDetails;
import com.stpl.app.service.TransactionModuleDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.TransactionModuleDetailsPersistence;
import com.stpl.app.service.persistence.TransactionModuleDetailsUtil;

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
public class TransactionModuleDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = TransactionModuleDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<TransactionModuleDetails> iterator = _transactionModuleDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		TransactionModuleDetails transactionModuleDetails = _persistence.create(pk);

		Assert.assertNotNull(transactionModuleDetails);

		Assert.assertEquals(transactionModuleDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		TransactionModuleDetails newTransactionModuleDetails = addTransactionModuleDetails();

		_persistence.remove(newTransactionModuleDetails);

		TransactionModuleDetails existingTransactionModuleDetails = _persistence.fetchByPrimaryKey(newTransactionModuleDetails.getPrimaryKey());

		Assert.assertNull(existingTransactionModuleDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addTransactionModuleDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		TransactionModuleDetails newTransactionModuleDetails = _persistence.create(pk);

		newTransactionModuleDetails.setPropertyIndex(RandomTestUtil.nextDouble());

		newTransactionModuleDetails.setDisplayName(RandomTestUtil.randomString());

		newTransactionModuleDetails.setTransactionModuleMasterSid(RandomTestUtil.nextInt());

		newTransactionModuleDetails.setCategoryName(RandomTestUtil.randomString());

		newTransactionModuleDetails.setValidation(RandomTestUtil.randomString());

		newTransactionModuleDetails.setPropertyName(RandomTestUtil.randomString());

		newTransactionModuleDetails.setFlag(RandomTestUtil.randomString());

		_transactionModuleDetailses.add(_persistence.update(
				newTransactionModuleDetails));

		TransactionModuleDetails existingTransactionModuleDetails = _persistence.findByPrimaryKey(newTransactionModuleDetails.getPrimaryKey());

		AssertUtils.assertEquals(existingTransactionModuleDetails.getPropertyIndex(),
			newTransactionModuleDetails.getPropertyIndex());
		Assert.assertEquals(existingTransactionModuleDetails.getDisplayName(),
			newTransactionModuleDetails.getDisplayName());
		Assert.assertEquals(existingTransactionModuleDetails.getTransactionModuleMasterSid(),
			newTransactionModuleDetails.getTransactionModuleMasterSid());
		Assert.assertEquals(existingTransactionModuleDetails.getCategoryName(),
			newTransactionModuleDetails.getCategoryName());
		Assert.assertEquals(existingTransactionModuleDetails.getValidation(),
			newTransactionModuleDetails.getValidation());
		Assert.assertEquals(existingTransactionModuleDetails.getPropertyName(),
			newTransactionModuleDetails.getPropertyName());
		Assert.assertEquals(existingTransactionModuleDetails.getFlag(),
			newTransactionModuleDetails.getFlag());
		Assert.assertEquals(existingTransactionModuleDetails.getTransactionModuleDetailsSid(),
			newTransactionModuleDetails.getTransactionModuleDetailsSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		TransactionModuleDetails newTransactionModuleDetails = addTransactionModuleDetails();

		TransactionModuleDetails existingTransactionModuleDetails = _persistence.findByPrimaryKey(newTransactionModuleDetails.getPrimaryKey());

		Assert.assertEquals(existingTransactionModuleDetails,
			newTransactionModuleDetails);
	}

	@Test(expected = NoSuchTransactionModuleDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<TransactionModuleDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("TRANSACTION_MODULE_DETAILS",
			"propertyIndex", true, "displayName", true,
			"transactionModuleMasterSid", true, "categoryName", true,
			"validation", true, "propertyName", true, "flag", true,
			"transactionModuleDetailsSid", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		TransactionModuleDetails newTransactionModuleDetails = addTransactionModuleDetails();

		TransactionModuleDetails existingTransactionModuleDetails = _persistence.fetchByPrimaryKey(newTransactionModuleDetails.getPrimaryKey());

		Assert.assertEquals(existingTransactionModuleDetails,
			newTransactionModuleDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		TransactionModuleDetails missingTransactionModuleDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingTransactionModuleDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		TransactionModuleDetails newTransactionModuleDetails1 = addTransactionModuleDetails();
		TransactionModuleDetails newTransactionModuleDetails2 = addTransactionModuleDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newTransactionModuleDetails1.getPrimaryKey());
		primaryKeys.add(newTransactionModuleDetails2.getPrimaryKey());

		Map<Serializable, TransactionModuleDetails> transactionModuleDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, transactionModuleDetailses.size());
		Assert.assertEquals(newTransactionModuleDetails1,
			transactionModuleDetailses.get(
				newTransactionModuleDetails1.getPrimaryKey()));
		Assert.assertEquals(newTransactionModuleDetails2,
			transactionModuleDetailses.get(
				newTransactionModuleDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, TransactionModuleDetails> transactionModuleDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(transactionModuleDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		TransactionModuleDetails newTransactionModuleDetails = addTransactionModuleDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newTransactionModuleDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, TransactionModuleDetails> transactionModuleDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, transactionModuleDetailses.size());
		Assert.assertEquals(newTransactionModuleDetails,
			transactionModuleDetailses.get(
				newTransactionModuleDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, TransactionModuleDetails> transactionModuleDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(transactionModuleDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		TransactionModuleDetails newTransactionModuleDetails = addTransactionModuleDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newTransactionModuleDetails.getPrimaryKey());

		Map<Serializable, TransactionModuleDetails> transactionModuleDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, transactionModuleDetailses.size());
		Assert.assertEquals(newTransactionModuleDetails,
			transactionModuleDetailses.get(
				newTransactionModuleDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = TransactionModuleDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<TransactionModuleDetails>() {
				@Override
				public void performAction(
					TransactionModuleDetails transactionModuleDetails) {
					Assert.assertNotNull(transactionModuleDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		TransactionModuleDetails newTransactionModuleDetails = addTransactionModuleDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(TransactionModuleDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"transactionModuleDetailsSid",
				newTransactionModuleDetails.getTransactionModuleDetailsSid()));

		List<TransactionModuleDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		TransactionModuleDetails existingTransactionModuleDetails = result.get(0);

		Assert.assertEquals(existingTransactionModuleDetails,
			newTransactionModuleDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(TransactionModuleDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"transactionModuleDetailsSid", RandomTestUtil.nextInt()));

		List<TransactionModuleDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		TransactionModuleDetails newTransactionModuleDetails = addTransactionModuleDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(TransactionModuleDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"transactionModuleDetailsSid"));

		Object newTransactionModuleDetailsSid = newTransactionModuleDetails.getTransactionModuleDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"transactionModuleDetailsSid",
				new Object[] { newTransactionModuleDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingTransactionModuleDetailsSid = result.get(0);

		Assert.assertEquals(existingTransactionModuleDetailsSid,
			newTransactionModuleDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(TransactionModuleDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"transactionModuleDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"transactionModuleDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected TransactionModuleDetails addTransactionModuleDetails()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		TransactionModuleDetails transactionModuleDetails = _persistence.create(pk);

		transactionModuleDetails.setPropertyIndex(RandomTestUtil.nextDouble());

		transactionModuleDetails.setDisplayName(RandomTestUtil.randomString());

		transactionModuleDetails.setTransactionModuleMasterSid(RandomTestUtil.nextInt());

		transactionModuleDetails.setCategoryName(RandomTestUtil.randomString());

		transactionModuleDetails.setValidation(RandomTestUtil.randomString());

		transactionModuleDetails.setPropertyName(RandomTestUtil.randomString());

		transactionModuleDetails.setFlag(RandomTestUtil.randomString());

		_transactionModuleDetailses.add(_persistence.update(
				transactionModuleDetails));

		return transactionModuleDetails;
	}

	private List<TransactionModuleDetails> _transactionModuleDetailses = new ArrayList<TransactionModuleDetails>();
	private TransactionModuleDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}