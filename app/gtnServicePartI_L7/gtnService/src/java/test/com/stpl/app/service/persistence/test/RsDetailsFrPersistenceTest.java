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

import com.stpl.app.exception.NoSuchRsDetailsFrException;
import com.stpl.app.model.RsDetailsFr;
import com.stpl.app.service.RsDetailsFrLocalServiceUtil;
import com.stpl.app.service.persistence.RsDetailsFrPersistence;
import com.stpl.app.service.persistence.RsDetailsFrUtil;

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
public class RsDetailsFrPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = RsDetailsFrUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<RsDetailsFr> iterator = _rsDetailsFrs.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		RsDetailsFr rsDetailsFr = _persistence.create(pk);

		Assert.assertNotNull(rsDetailsFr);

		Assert.assertEquals(rsDetailsFr.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		RsDetailsFr newRsDetailsFr = addRsDetailsFr();

		_persistence.remove(newRsDetailsFr);

		RsDetailsFr existingRsDetailsFr = _persistence.fetchByPrimaryKey(newRsDetailsFr.getPrimaryKey());

		Assert.assertNull(existingRsDetailsFr);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addRsDetailsFr();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		RsDetailsFr newRsDetailsFr = _persistence.create(pk);

		newRsDetailsFr.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newRsDetailsFr.setCreatedDate(RandomTestUtil.nextDate());

		newRsDetailsFr.setCreatedBy(RandomTestUtil.nextInt());

		newRsDetailsFr.setSource(RandomTestUtil.randomString());

		newRsDetailsFr.setFormulaMethodId(RandomTestUtil.randomString());

		newRsDetailsFr.setBatchId(RandomTestUtil.randomString());

		newRsDetailsFr.setModifiedBy(RandomTestUtil.nextInt());

		newRsDetailsFr.setInboundStatus(RandomTestUtil.randomString());

		newRsDetailsFr.setFormulaId(RandomTestUtil.nextInt());

		newRsDetailsFr.setItemMasterSid(RandomTestUtil.nextInt());

		newRsDetailsFr.setRsDetailsSid(RandomTestUtil.nextInt());

		newRsDetailsFr.setModifiedDate(RandomTestUtil.nextDate());

		_rsDetailsFrs.add(_persistence.update(newRsDetailsFr));

		RsDetailsFr existingRsDetailsFr = _persistence.findByPrimaryKey(newRsDetailsFr.getPrimaryKey());

		Assert.assertEquals(existingRsDetailsFr.getRecordLockStatus(),
			newRsDetailsFr.getRecordLockStatus());
		Assert.assertEquals(Time.getShortTimestamp(
				existingRsDetailsFr.getCreatedDate()),
			Time.getShortTimestamp(newRsDetailsFr.getCreatedDate()));
		Assert.assertEquals(existingRsDetailsFr.getCreatedBy(),
			newRsDetailsFr.getCreatedBy());
		Assert.assertEquals(existingRsDetailsFr.getSource(),
			newRsDetailsFr.getSource());
		Assert.assertEquals(existingRsDetailsFr.getFormulaMethodId(),
			newRsDetailsFr.getFormulaMethodId());
		Assert.assertEquals(existingRsDetailsFr.getBatchId(),
			newRsDetailsFr.getBatchId());
		Assert.assertEquals(existingRsDetailsFr.getModifiedBy(),
			newRsDetailsFr.getModifiedBy());
		Assert.assertEquals(existingRsDetailsFr.getInboundStatus(),
			newRsDetailsFr.getInboundStatus());
		Assert.assertEquals(existingRsDetailsFr.getFormulaId(),
			newRsDetailsFr.getFormulaId());
		Assert.assertEquals(existingRsDetailsFr.getItemMasterSid(),
			newRsDetailsFr.getItemMasterSid());
		Assert.assertEquals(existingRsDetailsFr.getRsDetailsSid(),
			newRsDetailsFr.getRsDetailsSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingRsDetailsFr.getModifiedDate()),
			Time.getShortTimestamp(newRsDetailsFr.getModifiedDate()));
		Assert.assertEquals(existingRsDetailsFr.getRsDetailsFrSid(),
			newRsDetailsFr.getRsDetailsFrSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		RsDetailsFr newRsDetailsFr = addRsDetailsFr();

		RsDetailsFr existingRsDetailsFr = _persistence.findByPrimaryKey(newRsDetailsFr.getPrimaryKey());

		Assert.assertEquals(existingRsDetailsFr, newRsDetailsFr);
	}

	@Test(expected = NoSuchRsDetailsFrException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<RsDetailsFr> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("RS_DETAILS_FR",
			"recordLockStatus", true, "createdDate", true, "createdBy", true,
			"source", true, "formulaMethodId", true, "batchId", true,
			"modifiedBy", true, "inboundStatus", true, "formulaId", true,
			"itemMasterSid", true, "rsDetailsSid", true, "modifiedDate", true,
			"rsDetailsFrSid", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		RsDetailsFr newRsDetailsFr = addRsDetailsFr();

		RsDetailsFr existingRsDetailsFr = _persistence.fetchByPrimaryKey(newRsDetailsFr.getPrimaryKey());

		Assert.assertEquals(existingRsDetailsFr, newRsDetailsFr);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		RsDetailsFr missingRsDetailsFr = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingRsDetailsFr);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		RsDetailsFr newRsDetailsFr1 = addRsDetailsFr();
		RsDetailsFr newRsDetailsFr2 = addRsDetailsFr();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRsDetailsFr1.getPrimaryKey());
		primaryKeys.add(newRsDetailsFr2.getPrimaryKey());

		Map<Serializable, RsDetailsFr> rsDetailsFrs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, rsDetailsFrs.size());
		Assert.assertEquals(newRsDetailsFr1,
			rsDetailsFrs.get(newRsDetailsFr1.getPrimaryKey()));
		Assert.assertEquals(newRsDetailsFr2,
			rsDetailsFrs.get(newRsDetailsFr2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, RsDetailsFr> rsDetailsFrs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(rsDetailsFrs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		RsDetailsFr newRsDetailsFr = addRsDetailsFr();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRsDetailsFr.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, RsDetailsFr> rsDetailsFrs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, rsDetailsFrs.size());
		Assert.assertEquals(newRsDetailsFr,
			rsDetailsFrs.get(newRsDetailsFr.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, RsDetailsFr> rsDetailsFrs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(rsDetailsFrs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		RsDetailsFr newRsDetailsFr = addRsDetailsFr();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRsDetailsFr.getPrimaryKey());

		Map<Serializable, RsDetailsFr> rsDetailsFrs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, rsDetailsFrs.size());
		Assert.assertEquals(newRsDetailsFr,
			rsDetailsFrs.get(newRsDetailsFr.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = RsDetailsFrLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<RsDetailsFr>() {
				@Override
				public void performAction(RsDetailsFr rsDetailsFr) {
					Assert.assertNotNull(rsDetailsFr);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		RsDetailsFr newRsDetailsFr = addRsDetailsFr();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RsDetailsFr.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("rsDetailsFrSid",
				newRsDetailsFr.getRsDetailsFrSid()));

		List<RsDetailsFr> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		RsDetailsFr existingRsDetailsFr = result.get(0);

		Assert.assertEquals(existingRsDetailsFr, newRsDetailsFr);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RsDetailsFr.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("rsDetailsFrSid",
				RandomTestUtil.nextInt()));

		List<RsDetailsFr> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		RsDetailsFr newRsDetailsFr = addRsDetailsFr();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RsDetailsFr.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"rsDetailsFrSid"));

		Object newRsDetailsFrSid = newRsDetailsFr.getRsDetailsFrSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("rsDetailsFrSid",
				new Object[] { newRsDetailsFrSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingRsDetailsFrSid = result.get(0);

		Assert.assertEquals(existingRsDetailsFrSid, newRsDetailsFrSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RsDetailsFr.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"rsDetailsFrSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("rsDetailsFrSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected RsDetailsFr addRsDetailsFr() throws Exception {
		int pk = RandomTestUtil.nextInt();

		RsDetailsFr rsDetailsFr = _persistence.create(pk);

		rsDetailsFr.setRecordLockStatus(RandomTestUtil.randomBoolean());

		rsDetailsFr.setCreatedDate(RandomTestUtil.nextDate());

		rsDetailsFr.setCreatedBy(RandomTestUtil.nextInt());

		rsDetailsFr.setSource(RandomTestUtil.randomString());

		rsDetailsFr.setFormulaMethodId(RandomTestUtil.randomString());

		rsDetailsFr.setBatchId(RandomTestUtil.randomString());

		rsDetailsFr.setModifiedBy(RandomTestUtil.nextInt());

		rsDetailsFr.setInboundStatus(RandomTestUtil.randomString());

		rsDetailsFr.setFormulaId(RandomTestUtil.nextInt());

		rsDetailsFr.setItemMasterSid(RandomTestUtil.nextInt());

		rsDetailsFr.setRsDetailsSid(RandomTestUtil.nextInt());

		rsDetailsFr.setModifiedDate(RandomTestUtil.nextDate());

		_rsDetailsFrs.add(_persistence.update(rsDetailsFr));

		return rsDetailsFr;
	}

	private List<RsDetailsFr> _rsDetailsFrs = new ArrayList<RsDetailsFr>();
	private RsDetailsFrPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}