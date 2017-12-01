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

import com.stpl.app.exception.NoSuchNetSalesFormulaMasterException;
import com.stpl.app.model.NetSalesFormulaMaster;
import com.stpl.app.service.NetSalesFormulaMasterLocalServiceUtil;
import com.stpl.app.service.persistence.NetSalesFormulaMasterPersistence;
import com.stpl.app.service.persistence.NetSalesFormulaMasterUtil;

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
public class NetSalesFormulaMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = NetSalesFormulaMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<NetSalesFormulaMaster> iterator = _netSalesFormulaMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		NetSalesFormulaMaster netSalesFormulaMaster = _persistence.create(pk);

		Assert.assertNotNull(netSalesFormulaMaster);

		Assert.assertEquals(netSalesFormulaMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		NetSalesFormulaMaster newNetSalesFormulaMaster = addNetSalesFormulaMaster();

		_persistence.remove(newNetSalesFormulaMaster);

		NetSalesFormulaMaster existingNetSalesFormulaMaster = _persistence.fetchByPrimaryKey(newNetSalesFormulaMaster.getPrimaryKey());

		Assert.assertNull(existingNetSalesFormulaMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addNetSalesFormulaMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		NetSalesFormulaMaster newNetSalesFormulaMaster = _persistence.create(pk);

		newNetSalesFormulaMaster.setCreatedBy(RandomTestUtil.nextInt());

		newNetSalesFormulaMaster.setModifiedBy(RandomTestUtil.nextInt());

		newNetSalesFormulaMaster.setCreatedDate(RandomTestUtil.nextDate());

		newNetSalesFormulaMaster.setNetSalesFormulaName(RandomTestUtil.randomString());

		newNetSalesFormulaMaster.setNetSalesFormulaType(RandomTestUtil.nextInt());

		newNetSalesFormulaMaster.setNetSalesFormulaCategory(RandomTestUtil.nextInt());

		newNetSalesFormulaMaster.setContractSelection(RandomTestUtil.randomString());

		newNetSalesFormulaMaster.setModifiedDate(RandomTestUtil.nextDate());

		newNetSalesFormulaMaster.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newNetSalesFormulaMaster.setSource(RandomTestUtil.randomString());

		newNetSalesFormulaMaster.setNetSalesFormulaId(RandomTestUtil.randomString());

		newNetSalesFormulaMaster.setNetSalesFormulaNo(RandomTestUtil.randomString());

		newNetSalesFormulaMaster.setInboundStatus(RandomTestUtil.randomString());

		newNetSalesFormulaMaster.setCdrModelSid(RandomTestUtil.randomString());

		_netSalesFormulaMasters.add(_persistence.update(
				newNetSalesFormulaMaster));

		NetSalesFormulaMaster existingNetSalesFormulaMaster = _persistence.findByPrimaryKey(newNetSalesFormulaMaster.getPrimaryKey());

		Assert.assertEquals(existingNetSalesFormulaMaster.getCreatedBy(),
			newNetSalesFormulaMaster.getCreatedBy());
		Assert.assertEquals(existingNetSalesFormulaMaster.getNetSalesFormulaMasterSid(),
			newNetSalesFormulaMaster.getNetSalesFormulaMasterSid());
		Assert.assertEquals(existingNetSalesFormulaMaster.getModifiedBy(),
			newNetSalesFormulaMaster.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingNetSalesFormulaMaster.getCreatedDate()),
			Time.getShortTimestamp(newNetSalesFormulaMaster.getCreatedDate()));
		Assert.assertEquals(existingNetSalesFormulaMaster.getNetSalesFormulaName(),
			newNetSalesFormulaMaster.getNetSalesFormulaName());
		Assert.assertEquals(existingNetSalesFormulaMaster.getNetSalesFormulaType(),
			newNetSalesFormulaMaster.getNetSalesFormulaType());
		Assert.assertEquals(existingNetSalesFormulaMaster.getNetSalesFormulaCategory(),
			newNetSalesFormulaMaster.getNetSalesFormulaCategory());
		Assert.assertEquals(existingNetSalesFormulaMaster.getContractSelection(),
			newNetSalesFormulaMaster.getContractSelection());
		Assert.assertEquals(Time.getShortTimestamp(
				existingNetSalesFormulaMaster.getModifiedDate()),
			Time.getShortTimestamp(newNetSalesFormulaMaster.getModifiedDate()));
		Assert.assertEquals(existingNetSalesFormulaMaster.getRecordLockStatus(),
			newNetSalesFormulaMaster.getRecordLockStatus());
		Assert.assertEquals(existingNetSalesFormulaMaster.getSource(),
			newNetSalesFormulaMaster.getSource());
		Assert.assertEquals(existingNetSalesFormulaMaster.getNetSalesFormulaId(),
			newNetSalesFormulaMaster.getNetSalesFormulaId());
		Assert.assertEquals(existingNetSalesFormulaMaster.getNetSalesFormulaNo(),
			newNetSalesFormulaMaster.getNetSalesFormulaNo());
		Assert.assertEquals(existingNetSalesFormulaMaster.getInboundStatus(),
			newNetSalesFormulaMaster.getInboundStatus());
		Assert.assertEquals(existingNetSalesFormulaMaster.getCdrModelSid(),
			newNetSalesFormulaMaster.getCdrModelSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		NetSalesFormulaMaster newNetSalesFormulaMaster = addNetSalesFormulaMaster();

		NetSalesFormulaMaster existingNetSalesFormulaMaster = _persistence.findByPrimaryKey(newNetSalesFormulaMaster.getPrimaryKey());

		Assert.assertEquals(existingNetSalesFormulaMaster,
			newNetSalesFormulaMaster);
	}

	@Test(expected = NoSuchNetSalesFormulaMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<NetSalesFormulaMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("NET_SALES_FORMULA_MASTER",
			"createdBy", true, "netSalesFormulaMasterSid", true, "modifiedBy",
			true, "createdDate", true, "netSalesFormulaName", true,
			"netSalesFormulaType", true, "netSalesFormulaCategory", true,
			"contractSelection", true, "modifiedDate", true,
			"recordLockStatus", true, "source", true, "netSalesFormulaId",
			true, "netSalesFormulaNo", true, "inboundStatus", true,
			"cdrModelSid", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		NetSalesFormulaMaster newNetSalesFormulaMaster = addNetSalesFormulaMaster();

		NetSalesFormulaMaster existingNetSalesFormulaMaster = _persistence.fetchByPrimaryKey(newNetSalesFormulaMaster.getPrimaryKey());

		Assert.assertEquals(existingNetSalesFormulaMaster,
			newNetSalesFormulaMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		NetSalesFormulaMaster missingNetSalesFormulaMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingNetSalesFormulaMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		NetSalesFormulaMaster newNetSalesFormulaMaster1 = addNetSalesFormulaMaster();
		NetSalesFormulaMaster newNetSalesFormulaMaster2 = addNetSalesFormulaMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNetSalesFormulaMaster1.getPrimaryKey());
		primaryKeys.add(newNetSalesFormulaMaster2.getPrimaryKey());

		Map<Serializable, NetSalesFormulaMaster> netSalesFormulaMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, netSalesFormulaMasters.size());
		Assert.assertEquals(newNetSalesFormulaMaster1,
			netSalesFormulaMasters.get(
				newNetSalesFormulaMaster1.getPrimaryKey()));
		Assert.assertEquals(newNetSalesFormulaMaster2,
			netSalesFormulaMasters.get(
				newNetSalesFormulaMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, NetSalesFormulaMaster> netSalesFormulaMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(netSalesFormulaMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		NetSalesFormulaMaster newNetSalesFormulaMaster = addNetSalesFormulaMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNetSalesFormulaMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, NetSalesFormulaMaster> netSalesFormulaMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, netSalesFormulaMasters.size());
		Assert.assertEquals(newNetSalesFormulaMaster,
			netSalesFormulaMasters.get(newNetSalesFormulaMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, NetSalesFormulaMaster> netSalesFormulaMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(netSalesFormulaMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		NetSalesFormulaMaster newNetSalesFormulaMaster = addNetSalesFormulaMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNetSalesFormulaMaster.getPrimaryKey());

		Map<Serializable, NetSalesFormulaMaster> netSalesFormulaMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, netSalesFormulaMasters.size());
		Assert.assertEquals(newNetSalesFormulaMaster,
			netSalesFormulaMasters.get(newNetSalesFormulaMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = NetSalesFormulaMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<NetSalesFormulaMaster>() {
				@Override
				public void performAction(
					NetSalesFormulaMaster netSalesFormulaMaster) {
					Assert.assertNotNull(netSalesFormulaMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		NetSalesFormulaMaster newNetSalesFormulaMaster = addNetSalesFormulaMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NetSalesFormulaMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"netSalesFormulaMasterSid",
				newNetSalesFormulaMaster.getNetSalesFormulaMasterSid()));

		List<NetSalesFormulaMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		NetSalesFormulaMaster existingNetSalesFormulaMaster = result.get(0);

		Assert.assertEquals(existingNetSalesFormulaMaster,
			newNetSalesFormulaMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NetSalesFormulaMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"netSalesFormulaMasterSid", RandomTestUtil.nextInt()));

		List<NetSalesFormulaMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		NetSalesFormulaMaster newNetSalesFormulaMaster = addNetSalesFormulaMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NetSalesFormulaMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"netSalesFormulaMasterSid"));

		Object newNetSalesFormulaMasterSid = newNetSalesFormulaMaster.getNetSalesFormulaMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"netSalesFormulaMasterSid",
				new Object[] { newNetSalesFormulaMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingNetSalesFormulaMasterSid = result.get(0);

		Assert.assertEquals(existingNetSalesFormulaMasterSid,
			newNetSalesFormulaMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NetSalesFormulaMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"netSalesFormulaMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"netSalesFormulaMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected NetSalesFormulaMaster addNetSalesFormulaMaster()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		NetSalesFormulaMaster netSalesFormulaMaster = _persistence.create(pk);

		netSalesFormulaMaster.setCreatedBy(RandomTestUtil.nextInt());

		netSalesFormulaMaster.setModifiedBy(RandomTestUtil.nextInt());

		netSalesFormulaMaster.setCreatedDate(RandomTestUtil.nextDate());

		netSalesFormulaMaster.setNetSalesFormulaName(RandomTestUtil.randomString());

		netSalesFormulaMaster.setNetSalesFormulaType(RandomTestUtil.nextInt());

		netSalesFormulaMaster.setNetSalesFormulaCategory(RandomTestUtil.nextInt());

		netSalesFormulaMaster.setContractSelection(RandomTestUtil.randomString());

		netSalesFormulaMaster.setModifiedDate(RandomTestUtil.nextDate());

		netSalesFormulaMaster.setRecordLockStatus(RandomTestUtil.randomBoolean());

		netSalesFormulaMaster.setSource(RandomTestUtil.randomString());

		netSalesFormulaMaster.setNetSalesFormulaId(RandomTestUtil.randomString());

		netSalesFormulaMaster.setNetSalesFormulaNo(RandomTestUtil.randomString());

		netSalesFormulaMaster.setInboundStatus(RandomTestUtil.randomString());

		netSalesFormulaMaster.setCdrModelSid(RandomTestUtil.randomString());

		_netSalesFormulaMasters.add(_persistence.update(netSalesFormulaMaster));

		return netSalesFormulaMaster;
	}

	private List<NetSalesFormulaMaster> _netSalesFormulaMasters = new ArrayList<NetSalesFormulaMaster>();
	private NetSalesFormulaMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}