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

import com.stpl.app.exception.NoSuchRsContractDetailsFrException;
import com.stpl.app.model.RsContractDetailsFr;
import com.stpl.app.service.RsContractDetailsFrLocalServiceUtil;
import com.stpl.app.service.persistence.RsContractDetailsFrPersistence;
import com.stpl.app.service.persistence.RsContractDetailsFrUtil;

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
public class RsContractDetailsFrPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = RsContractDetailsFrUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<RsContractDetailsFr> iterator = _rsContractDetailsFrs.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		RsContractDetailsFr rsContractDetailsFr = _persistence.create(pk);

		Assert.assertNotNull(rsContractDetailsFr);

		Assert.assertEquals(rsContractDetailsFr.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		RsContractDetailsFr newRsContractDetailsFr = addRsContractDetailsFr();

		_persistence.remove(newRsContractDetailsFr);

		RsContractDetailsFr existingRsContractDetailsFr = _persistence.fetchByPrimaryKey(newRsContractDetailsFr.getPrimaryKey());

		Assert.assertNull(existingRsContractDetailsFr);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addRsContractDetailsFr();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		RsContractDetailsFr newRsContractDetailsFr = _persistence.create(pk);

		newRsContractDetailsFr.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newRsContractDetailsFr.setCreatedDate(RandomTestUtil.nextDate());

		newRsContractDetailsFr.setCreatedBy(RandomTestUtil.nextInt());

		newRsContractDetailsFr.setSource(RandomTestUtil.randomString());

		newRsContractDetailsFr.setFormulaMethodId(RandomTestUtil.randomString());

		newRsContractDetailsFr.setItemMasterSid(RandomTestUtil.nextInt());

		newRsContractDetailsFr.setBatchId(RandomTestUtil.randomString());

		newRsContractDetailsFr.setModifiedBy(RandomTestUtil.nextInt());

		newRsContractDetailsFr.setInboundStatus(RandomTestUtil.randomString());

		newRsContractDetailsFr.setFormulaId(RandomTestUtil.nextInt());

		newRsContractDetailsFr.setModifiedDate(RandomTestUtil.nextDate());

		newRsContractDetailsFr.setRsContractDetailsSid(RandomTestUtil.nextInt());

		_rsContractDetailsFrs.add(_persistence.update(newRsContractDetailsFr));

		RsContractDetailsFr existingRsContractDetailsFr = _persistence.findByPrimaryKey(newRsContractDetailsFr.getPrimaryKey());

		Assert.assertEquals(existingRsContractDetailsFr.getRecordLockStatus(),
			newRsContractDetailsFr.getRecordLockStatus());
		Assert.assertEquals(Time.getShortTimestamp(
				existingRsContractDetailsFr.getCreatedDate()),
			Time.getShortTimestamp(newRsContractDetailsFr.getCreatedDate()));
		Assert.assertEquals(existingRsContractDetailsFr.getCreatedBy(),
			newRsContractDetailsFr.getCreatedBy());
		Assert.assertEquals(existingRsContractDetailsFr.getSource(),
			newRsContractDetailsFr.getSource());
		Assert.assertEquals(existingRsContractDetailsFr.getFormulaMethodId(),
			newRsContractDetailsFr.getFormulaMethodId());
		Assert.assertEquals(existingRsContractDetailsFr.getItemMasterSid(),
			newRsContractDetailsFr.getItemMasterSid());
		Assert.assertEquals(existingRsContractDetailsFr.getBatchId(),
			newRsContractDetailsFr.getBatchId());
		Assert.assertEquals(existingRsContractDetailsFr.getRsContractDetailsFrSid(),
			newRsContractDetailsFr.getRsContractDetailsFrSid());
		Assert.assertEquals(existingRsContractDetailsFr.getModifiedBy(),
			newRsContractDetailsFr.getModifiedBy());
		Assert.assertEquals(existingRsContractDetailsFr.getInboundStatus(),
			newRsContractDetailsFr.getInboundStatus());
		Assert.assertEquals(existingRsContractDetailsFr.getFormulaId(),
			newRsContractDetailsFr.getFormulaId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingRsContractDetailsFr.getModifiedDate()),
			Time.getShortTimestamp(newRsContractDetailsFr.getModifiedDate()));
		Assert.assertEquals(existingRsContractDetailsFr.getRsContractDetailsSid(),
			newRsContractDetailsFr.getRsContractDetailsSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		RsContractDetailsFr newRsContractDetailsFr = addRsContractDetailsFr();

		RsContractDetailsFr existingRsContractDetailsFr = _persistence.findByPrimaryKey(newRsContractDetailsFr.getPrimaryKey());

		Assert.assertEquals(existingRsContractDetailsFr, newRsContractDetailsFr);
	}

	@Test(expected = NoSuchRsContractDetailsFrException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<RsContractDetailsFr> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("RS_CONTRACT_DETAILS_FR",
			"recordLockStatus", true, "createdDate", true, "createdBy", true,
			"source", true, "formulaMethodId", true, "itemMasterSid", true,
			"batchId", true, "rsContractDetailsFrSid", true, "modifiedBy",
			true, "inboundStatus", true, "formulaId", true, "modifiedDate",
			true, "rsContractDetailsSid", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		RsContractDetailsFr newRsContractDetailsFr = addRsContractDetailsFr();

		RsContractDetailsFr existingRsContractDetailsFr = _persistence.fetchByPrimaryKey(newRsContractDetailsFr.getPrimaryKey());

		Assert.assertEquals(existingRsContractDetailsFr, newRsContractDetailsFr);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		RsContractDetailsFr missingRsContractDetailsFr = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingRsContractDetailsFr);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		RsContractDetailsFr newRsContractDetailsFr1 = addRsContractDetailsFr();
		RsContractDetailsFr newRsContractDetailsFr2 = addRsContractDetailsFr();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRsContractDetailsFr1.getPrimaryKey());
		primaryKeys.add(newRsContractDetailsFr2.getPrimaryKey());

		Map<Serializable, RsContractDetailsFr> rsContractDetailsFrs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, rsContractDetailsFrs.size());
		Assert.assertEquals(newRsContractDetailsFr1,
			rsContractDetailsFrs.get(newRsContractDetailsFr1.getPrimaryKey()));
		Assert.assertEquals(newRsContractDetailsFr2,
			rsContractDetailsFrs.get(newRsContractDetailsFr2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, RsContractDetailsFr> rsContractDetailsFrs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(rsContractDetailsFrs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		RsContractDetailsFr newRsContractDetailsFr = addRsContractDetailsFr();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRsContractDetailsFr.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, RsContractDetailsFr> rsContractDetailsFrs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, rsContractDetailsFrs.size());
		Assert.assertEquals(newRsContractDetailsFr,
			rsContractDetailsFrs.get(newRsContractDetailsFr.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, RsContractDetailsFr> rsContractDetailsFrs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(rsContractDetailsFrs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		RsContractDetailsFr newRsContractDetailsFr = addRsContractDetailsFr();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRsContractDetailsFr.getPrimaryKey());

		Map<Serializable, RsContractDetailsFr> rsContractDetailsFrs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, rsContractDetailsFrs.size());
		Assert.assertEquals(newRsContractDetailsFr,
			rsContractDetailsFrs.get(newRsContractDetailsFr.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = RsContractDetailsFrLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<RsContractDetailsFr>() {
				@Override
				public void performAction(
					RsContractDetailsFr rsContractDetailsFr) {
					Assert.assertNotNull(rsContractDetailsFr);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		RsContractDetailsFr newRsContractDetailsFr = addRsContractDetailsFr();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RsContractDetailsFr.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("rsContractDetailsFrSid",
				newRsContractDetailsFr.getRsContractDetailsFrSid()));

		List<RsContractDetailsFr> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		RsContractDetailsFr existingRsContractDetailsFr = result.get(0);

		Assert.assertEquals(existingRsContractDetailsFr, newRsContractDetailsFr);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RsContractDetailsFr.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("rsContractDetailsFrSid",
				RandomTestUtil.nextInt()));

		List<RsContractDetailsFr> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		RsContractDetailsFr newRsContractDetailsFr = addRsContractDetailsFr();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RsContractDetailsFr.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"rsContractDetailsFrSid"));

		Object newRsContractDetailsFrSid = newRsContractDetailsFr.getRsContractDetailsFrSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("rsContractDetailsFrSid",
				new Object[] { newRsContractDetailsFrSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingRsContractDetailsFrSid = result.get(0);

		Assert.assertEquals(existingRsContractDetailsFrSid,
			newRsContractDetailsFrSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RsContractDetailsFr.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"rsContractDetailsFrSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("rsContractDetailsFrSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected RsContractDetailsFr addRsContractDetailsFr()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		RsContractDetailsFr rsContractDetailsFr = _persistence.create(pk);

		rsContractDetailsFr.setRecordLockStatus(RandomTestUtil.randomBoolean());

		rsContractDetailsFr.setCreatedDate(RandomTestUtil.nextDate());

		rsContractDetailsFr.setCreatedBy(RandomTestUtil.nextInt());

		rsContractDetailsFr.setSource(RandomTestUtil.randomString());

		rsContractDetailsFr.setFormulaMethodId(RandomTestUtil.randomString());

		rsContractDetailsFr.setItemMasterSid(RandomTestUtil.nextInt());

		rsContractDetailsFr.setBatchId(RandomTestUtil.randomString());

		rsContractDetailsFr.setModifiedBy(RandomTestUtil.nextInt());

		rsContractDetailsFr.setInboundStatus(RandomTestUtil.randomString());

		rsContractDetailsFr.setFormulaId(RandomTestUtil.nextInt());

		rsContractDetailsFr.setModifiedDate(RandomTestUtil.nextDate());

		rsContractDetailsFr.setRsContractDetailsSid(RandomTestUtil.nextInt());

		_rsContractDetailsFrs.add(_persistence.update(rsContractDetailsFr));

		return rsContractDetailsFr;
	}

	private List<RsContractDetailsFr> _rsContractDetailsFrs = new ArrayList<RsContractDetailsFr>();
	private RsContractDetailsFrPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}