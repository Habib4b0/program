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

import com.stpl.app.exception.NoSuchIvldGlBalanceException;
import com.stpl.app.model.IvldGlBalance;
import com.stpl.app.service.IvldGlBalanceLocalServiceUtil;
import com.stpl.app.service.persistence.IvldGlBalancePersistence;
import com.stpl.app.service.persistence.IvldGlBalanceUtil;

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
public class IvldGlBalancePersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = IvldGlBalanceUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<IvldGlBalance> iterator = _ivldGlBalances.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldGlBalance ivldGlBalance = _persistence.create(pk);

		Assert.assertNotNull(ivldGlBalance);

		Assert.assertEquals(ivldGlBalance.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		IvldGlBalance newIvldGlBalance = addIvldGlBalance();

		_persistence.remove(newIvldGlBalance);

		IvldGlBalance existingIvldGlBalance = _persistence.fetchByPrimaryKey(newIvldGlBalance.getPrimaryKey());

		Assert.assertNull(existingIvldGlBalance);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addIvldGlBalance();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldGlBalance newIvldGlBalance = _persistence.create(pk);

		newIvldGlBalance.setBalance(RandomTestUtil.randomString());

		newIvldGlBalance.setAccountNo(RandomTestUtil.randomString());

		newIvldGlBalance.setReasonForFailure(RandomTestUtil.randomString());

		newIvldGlBalance.setAccountId(RandomTestUtil.randomString());

		newIvldGlBalance.setYear(RandomTestUtil.randomString());

		newIvldGlBalance.setPeriod(RandomTestUtil.randomString());

		newIvldGlBalance.setModifiedDate(RandomTestUtil.nextDate());

		newIvldGlBalance.setIsActive(RandomTestUtil.randomString());

		newIvldGlBalance.setSource(RandomTestUtil.randomString());

		newIvldGlBalance.setUploadDate(RandomTestUtil.randomString());

		newIvldGlBalance.setCreatedBy(RandomTestUtil.randomString());

		newIvldGlBalance.setCreatedDate(RandomTestUtil.nextDate());

		newIvldGlBalance.setAddChgDelIndicator(RandomTestUtil.randomString());

		newIvldGlBalance.setBatchId(RandomTestUtil.randomString());

		newIvldGlBalance.setCloseIndicator(RandomTestUtil.randomString());

		newIvldGlBalance.setInsertedDate(RandomTestUtil.randomString());

		newIvldGlBalance.setErrorField(RandomTestUtil.randomString());

		newIvldGlBalance.setErrorCode(RandomTestUtil.randomString());

		newIvldGlBalance.setGlBalanceIntfid(RandomTestUtil.randomString());

		newIvldGlBalance.setIntfInsertedDate(RandomTestUtil.nextDate());

		newIvldGlBalance.setModifiedBy(RandomTestUtil.randomString());

		newIvldGlBalance.setReprocessedFlag(RandomTestUtil.randomString());

		newIvldGlBalance.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldGlBalances.add(_persistence.update(newIvldGlBalance));

		IvldGlBalance existingIvldGlBalance = _persistence.findByPrimaryKey(newIvldGlBalance.getPrimaryKey());

		Assert.assertEquals(existingIvldGlBalance.getBalance(),
			newIvldGlBalance.getBalance());
		Assert.assertEquals(existingIvldGlBalance.getAccountNo(),
			newIvldGlBalance.getAccountNo());
		Assert.assertEquals(existingIvldGlBalance.getReasonForFailure(),
			newIvldGlBalance.getReasonForFailure());
		Assert.assertEquals(existingIvldGlBalance.getAccountId(),
			newIvldGlBalance.getAccountId());
		Assert.assertEquals(existingIvldGlBalance.getYear(),
			newIvldGlBalance.getYear());
		Assert.assertEquals(existingIvldGlBalance.getPeriod(),
			newIvldGlBalance.getPeriod());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldGlBalance.getModifiedDate()),
			Time.getShortTimestamp(newIvldGlBalance.getModifiedDate()));
		Assert.assertEquals(existingIvldGlBalance.getIsActive(),
			newIvldGlBalance.getIsActive());
		Assert.assertEquals(existingIvldGlBalance.getSource(),
			newIvldGlBalance.getSource());
		Assert.assertEquals(existingIvldGlBalance.getUploadDate(),
			newIvldGlBalance.getUploadDate());
		Assert.assertEquals(existingIvldGlBalance.getCreatedBy(),
			newIvldGlBalance.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldGlBalance.getCreatedDate()),
			Time.getShortTimestamp(newIvldGlBalance.getCreatedDate()));
		Assert.assertEquals(existingIvldGlBalance.getAddChgDelIndicator(),
			newIvldGlBalance.getAddChgDelIndicator());
		Assert.assertEquals(existingIvldGlBalance.getBatchId(),
			newIvldGlBalance.getBatchId());
		Assert.assertEquals(existingIvldGlBalance.getCloseIndicator(),
			newIvldGlBalance.getCloseIndicator());
		Assert.assertEquals(existingIvldGlBalance.getInsertedDate(),
			newIvldGlBalance.getInsertedDate());
		Assert.assertEquals(existingIvldGlBalance.getErrorField(),
			newIvldGlBalance.getErrorField());
		Assert.assertEquals(existingIvldGlBalance.getIvldGlBalanceSid(),
			newIvldGlBalance.getIvldGlBalanceSid());
		Assert.assertEquals(existingIvldGlBalance.getErrorCode(),
			newIvldGlBalance.getErrorCode());
		Assert.assertEquals(existingIvldGlBalance.getGlBalanceIntfid(),
			newIvldGlBalance.getGlBalanceIntfid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldGlBalance.getIntfInsertedDate()),
			Time.getShortTimestamp(newIvldGlBalance.getIntfInsertedDate()));
		Assert.assertEquals(existingIvldGlBalance.getModifiedBy(),
			newIvldGlBalance.getModifiedBy());
		Assert.assertEquals(existingIvldGlBalance.getReprocessedFlag(),
			newIvldGlBalance.getReprocessedFlag());
		Assert.assertEquals(existingIvldGlBalance.getCheckRecord(),
			newIvldGlBalance.getCheckRecord());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		IvldGlBalance newIvldGlBalance = addIvldGlBalance();

		IvldGlBalance existingIvldGlBalance = _persistence.findByPrimaryKey(newIvldGlBalance.getPrimaryKey());

		Assert.assertEquals(existingIvldGlBalance, newIvldGlBalance);
	}

	@Test(expected = NoSuchIvldGlBalanceException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<IvldGlBalance> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("IVLD_GL_BALANCE",
			"balance", true, "accountNo", true, "reasonForFailure", true,
			"accountId", true, "year", true, "period", true, "modifiedDate",
			true, "isActive", true, "source", true, "uploadDate", true,
			"createdBy", true, "createdDate", true, "addChgDelIndicator", true,
			"batchId", true, "closeIndicator", true, "insertedDate", true,
			"errorField", true, "ivldGlBalanceSid", true, "errorCode", true,
			"glBalanceIntfid", true, "intfInsertedDate", true, "modifiedBy",
			true, "reprocessedFlag", true, "checkRecord", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		IvldGlBalance newIvldGlBalance = addIvldGlBalance();

		IvldGlBalance existingIvldGlBalance = _persistence.fetchByPrimaryKey(newIvldGlBalance.getPrimaryKey());

		Assert.assertEquals(existingIvldGlBalance, newIvldGlBalance);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldGlBalance missingIvldGlBalance = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingIvldGlBalance);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		IvldGlBalance newIvldGlBalance1 = addIvldGlBalance();
		IvldGlBalance newIvldGlBalance2 = addIvldGlBalance();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldGlBalance1.getPrimaryKey());
		primaryKeys.add(newIvldGlBalance2.getPrimaryKey());

		Map<Serializable, IvldGlBalance> ivldGlBalances = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ivldGlBalances.size());
		Assert.assertEquals(newIvldGlBalance1,
			ivldGlBalances.get(newIvldGlBalance1.getPrimaryKey()));
		Assert.assertEquals(newIvldGlBalance2,
			ivldGlBalances.get(newIvldGlBalance2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, IvldGlBalance> ivldGlBalances = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldGlBalances.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		IvldGlBalance newIvldGlBalance = addIvldGlBalance();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldGlBalance.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, IvldGlBalance> ivldGlBalances = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldGlBalances.size());
		Assert.assertEquals(newIvldGlBalance,
			ivldGlBalances.get(newIvldGlBalance.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, IvldGlBalance> ivldGlBalances = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldGlBalances.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		IvldGlBalance newIvldGlBalance = addIvldGlBalance();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldGlBalance.getPrimaryKey());

		Map<Serializable, IvldGlBalance> ivldGlBalances = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldGlBalances.size());
		Assert.assertEquals(newIvldGlBalance,
			ivldGlBalances.get(newIvldGlBalance.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = IvldGlBalanceLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<IvldGlBalance>() {
				@Override
				public void performAction(IvldGlBalance ivldGlBalance) {
					Assert.assertNotNull(ivldGlBalance);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		IvldGlBalance newIvldGlBalance = addIvldGlBalance();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldGlBalance.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldGlBalanceSid",
				newIvldGlBalance.getIvldGlBalanceSid()));

		List<IvldGlBalance> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		IvldGlBalance existingIvldGlBalance = result.get(0);

		Assert.assertEquals(existingIvldGlBalance, newIvldGlBalance);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldGlBalance.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldGlBalanceSid",
				RandomTestUtil.nextInt()));

		List<IvldGlBalance> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		IvldGlBalance newIvldGlBalance = addIvldGlBalance();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldGlBalance.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldGlBalanceSid"));

		Object newIvldGlBalanceSid = newIvldGlBalance.getIvldGlBalanceSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldGlBalanceSid",
				new Object[] { newIvldGlBalanceSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingIvldGlBalanceSid = result.get(0);

		Assert.assertEquals(existingIvldGlBalanceSid, newIvldGlBalanceSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldGlBalance.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldGlBalanceSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldGlBalanceSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected IvldGlBalance addIvldGlBalance() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldGlBalance ivldGlBalance = _persistence.create(pk);

		ivldGlBalance.setBalance(RandomTestUtil.randomString());

		ivldGlBalance.setAccountNo(RandomTestUtil.randomString());

		ivldGlBalance.setReasonForFailure(RandomTestUtil.randomString());

		ivldGlBalance.setAccountId(RandomTestUtil.randomString());

		ivldGlBalance.setYear(RandomTestUtil.randomString());

		ivldGlBalance.setPeriod(RandomTestUtil.randomString());

		ivldGlBalance.setModifiedDate(RandomTestUtil.nextDate());

		ivldGlBalance.setIsActive(RandomTestUtil.randomString());

		ivldGlBalance.setSource(RandomTestUtil.randomString());

		ivldGlBalance.setUploadDate(RandomTestUtil.randomString());

		ivldGlBalance.setCreatedBy(RandomTestUtil.randomString());

		ivldGlBalance.setCreatedDate(RandomTestUtil.nextDate());

		ivldGlBalance.setAddChgDelIndicator(RandomTestUtil.randomString());

		ivldGlBalance.setBatchId(RandomTestUtil.randomString());

		ivldGlBalance.setCloseIndicator(RandomTestUtil.randomString());

		ivldGlBalance.setInsertedDate(RandomTestUtil.randomString());

		ivldGlBalance.setErrorField(RandomTestUtil.randomString());

		ivldGlBalance.setErrorCode(RandomTestUtil.randomString());

		ivldGlBalance.setGlBalanceIntfid(RandomTestUtil.randomString());

		ivldGlBalance.setIntfInsertedDate(RandomTestUtil.nextDate());

		ivldGlBalance.setModifiedBy(RandomTestUtil.randomString());

		ivldGlBalance.setReprocessedFlag(RandomTestUtil.randomString());

		ivldGlBalance.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldGlBalances.add(_persistence.update(ivldGlBalance));

		return ivldGlBalance;
	}

	private List<IvldGlBalance> _ivldGlBalances = new ArrayList<IvldGlBalance>();
	private IvldGlBalancePersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}