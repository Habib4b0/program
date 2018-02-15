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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchLotMasterException;
import com.stpl.app.model.LotMaster;
import com.stpl.app.service.LotMasterLocalServiceUtil;
import com.stpl.app.service.persistence.LotMasterPersistence;
import com.stpl.app.service.persistence.LotMasterUtil;

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
public class LotMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = LotMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<LotMaster> iterator = _lotMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		LotMaster lotMaster = _persistence.create(pk);

		Assert.assertNotNull(lotMaster);

		Assert.assertEquals(lotMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		LotMaster newLotMaster = addLotMaster();

		_persistence.remove(newLotMaster);

		LotMaster existingLotMaster = _persistence.fetchByPrimaryKey(newLotMaster.getPrimaryKey());

		Assert.assertNull(existingLotMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addLotMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		LotMaster newLotMaster = _persistence.create(pk);

		newLotMaster.setCreatedBy(RandomTestUtil.nextInt());

		newLotMaster.setModifiedBy(RandomTestUtil.nextInt());

		newLotMaster.setCreatedDate(RandomTestUtil.nextDate());

		newLotMaster.setItemId(RandomTestUtil.randomString());

		newLotMaster.setBatchId(RandomTestUtil.randomString());

		newLotMaster.setModifiedDate(RandomTestUtil.nextDate());

		newLotMaster.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newLotMaster.setLastLotSoldDate(RandomTestUtil.nextDate());

		newLotMaster.setLotExpirationDate(RandomTestUtil.nextDate());

		newLotMaster.setSource(RandomTestUtil.randomString());

		newLotMaster.setLotNo(RandomTestUtil.randomString());

		newLotMaster.setInboundStatus(RandomTestUtil.randomString());

		_lotMasters.add(_persistence.update(newLotMaster));

		LotMaster existingLotMaster = _persistence.findByPrimaryKey(newLotMaster.getPrimaryKey());

		Assert.assertEquals(existingLotMaster.getCreatedBy(),
			newLotMaster.getCreatedBy());
		Assert.assertEquals(existingLotMaster.getModifiedBy(),
			newLotMaster.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingLotMaster.getCreatedDate()),
			Time.getShortTimestamp(newLotMaster.getCreatedDate()));
		Assert.assertEquals(existingLotMaster.getItemId(),
			newLotMaster.getItemId());
		Assert.assertEquals(existingLotMaster.getBatchId(),
			newLotMaster.getBatchId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingLotMaster.getModifiedDate()),
			Time.getShortTimestamp(newLotMaster.getModifiedDate()));
		Assert.assertEquals(existingLotMaster.getRecordLockStatus(),
			newLotMaster.getRecordLockStatus());
		Assert.assertEquals(Time.getShortTimestamp(
				existingLotMaster.getLastLotSoldDate()),
			Time.getShortTimestamp(newLotMaster.getLastLotSoldDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingLotMaster.getLotExpirationDate()),
			Time.getShortTimestamp(newLotMaster.getLotExpirationDate()));
		Assert.assertEquals(existingLotMaster.getSource(),
			newLotMaster.getSource());
		Assert.assertEquals(existingLotMaster.getLotMasterSid(),
			newLotMaster.getLotMasterSid());
		Assert.assertEquals(existingLotMaster.getLotNo(),
			newLotMaster.getLotNo());
		Assert.assertEquals(existingLotMaster.getInboundStatus(),
			newLotMaster.getInboundStatus());
	}

	@Test
	public void testCountByItemId() throws Exception {
		_persistence.countByItemId(StringPool.BLANK);

		_persistence.countByItemId(StringPool.NULL);

		_persistence.countByItemId((String)null);
	}

	@Test
	public void testCountByLotNo() throws Exception {
		_persistence.countByLotNo(StringPool.BLANK);

		_persistence.countByLotNo(StringPool.NULL);

		_persistence.countByLotNo((String)null);
	}

	@Test
	public void testCountByLotExpirationDate() throws Exception {
		_persistence.countByLotExpirationDate(RandomTestUtil.nextDate());

		_persistence.countByLotExpirationDate(RandomTestUtil.nextDate());
	}

	@Test
	public void testCountByLastLotSoldDate() throws Exception {
		_persistence.countByLastLotSoldDate(RandomTestUtil.nextDate());

		_persistence.countByLastLotSoldDate(RandomTestUtil.nextDate());
	}

	@Test
	public void testCountByLotUnique() throws Exception {
		_persistence.countByLotUnique(StringPool.BLANK, StringPool.BLANK);

		_persistence.countByLotUnique(StringPool.NULL, StringPool.NULL);

		_persistence.countByLotUnique((String)null, (String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		LotMaster newLotMaster = addLotMaster();

		LotMaster existingLotMaster = _persistence.findByPrimaryKey(newLotMaster.getPrimaryKey());

		Assert.assertEquals(existingLotMaster, newLotMaster);
	}

	@Test(expected = NoSuchLotMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<LotMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("LOT_MASTER", "createdBy",
			true, "modifiedBy", true, "createdDate", true, "itemId", true,
			"batchId", true, "modifiedDate", true, "recordLockStatus", true,
			"lastLotSoldDate", true, "lotExpirationDate", true, "source", true,
			"lotMasterSid", true, "lotNo", true, "inboundStatus", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		LotMaster newLotMaster = addLotMaster();

		LotMaster existingLotMaster = _persistence.fetchByPrimaryKey(newLotMaster.getPrimaryKey());

		Assert.assertEquals(existingLotMaster, newLotMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		LotMaster missingLotMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingLotMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		LotMaster newLotMaster1 = addLotMaster();
		LotMaster newLotMaster2 = addLotMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLotMaster1.getPrimaryKey());
		primaryKeys.add(newLotMaster2.getPrimaryKey());

		Map<Serializable, LotMaster> lotMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, lotMasters.size());
		Assert.assertEquals(newLotMaster1,
			lotMasters.get(newLotMaster1.getPrimaryKey()));
		Assert.assertEquals(newLotMaster2,
			lotMasters.get(newLotMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, LotMaster> lotMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(lotMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		LotMaster newLotMaster = addLotMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLotMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, LotMaster> lotMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, lotMasters.size());
		Assert.assertEquals(newLotMaster,
			lotMasters.get(newLotMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, LotMaster> lotMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(lotMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		LotMaster newLotMaster = addLotMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLotMaster.getPrimaryKey());

		Map<Serializable, LotMaster> lotMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, lotMasters.size());
		Assert.assertEquals(newLotMaster,
			lotMasters.get(newLotMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = LotMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<LotMaster>() {
				@Override
				public void performAction(LotMaster lotMaster) {
					Assert.assertNotNull(lotMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		LotMaster newLotMaster = addLotMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LotMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("lotMasterSid",
				newLotMaster.getLotMasterSid()));

		List<LotMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		LotMaster existingLotMaster = result.get(0);

		Assert.assertEquals(existingLotMaster, newLotMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LotMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("lotMasterSid",
				RandomTestUtil.nextInt()));

		List<LotMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		LotMaster newLotMaster = addLotMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LotMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"lotMasterSid"));

		Object newLotMasterSid = newLotMaster.getLotMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("lotMasterSid",
				new Object[] { newLotMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingLotMasterSid = result.get(0);

		Assert.assertEquals(existingLotMasterSid, newLotMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LotMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"lotMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("lotMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected LotMaster addLotMaster() throws Exception {
		int pk = RandomTestUtil.nextInt();

		LotMaster lotMaster = _persistence.create(pk);

		lotMaster.setCreatedBy(RandomTestUtil.nextInt());

		lotMaster.setModifiedBy(RandomTestUtil.nextInt());

		lotMaster.setCreatedDate(RandomTestUtil.nextDate());

		lotMaster.setItemId(RandomTestUtil.randomString());

		lotMaster.setBatchId(RandomTestUtil.randomString());

		lotMaster.setModifiedDate(RandomTestUtil.nextDate());

		lotMaster.setRecordLockStatus(RandomTestUtil.randomBoolean());

		lotMaster.setLastLotSoldDate(RandomTestUtil.nextDate());

		lotMaster.setLotExpirationDate(RandomTestUtil.nextDate());

		lotMaster.setSource(RandomTestUtil.randomString());

		lotMaster.setLotNo(RandomTestUtil.randomString());

		lotMaster.setInboundStatus(RandomTestUtil.randomString());

		_lotMasters.add(_persistence.update(lotMaster));

		return lotMaster;
	}

	private List<LotMaster> _lotMasters = new ArrayList<LotMaster>();
	private LotMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}