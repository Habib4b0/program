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

import com.stpl.app.exception.NoSuchPriceScheduleMasterException;
import com.stpl.app.model.PriceScheduleMaster;
import com.stpl.app.service.PriceScheduleMasterLocalServiceUtil;
import com.stpl.app.service.persistence.PriceScheduleMasterPersistence;
import com.stpl.app.service.persistence.PriceScheduleMasterUtil;

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
public class PriceScheduleMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = PriceScheduleMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<PriceScheduleMaster> iterator = _priceScheduleMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		PriceScheduleMaster priceScheduleMaster = _persistence.create(pk);

		Assert.assertNotNull(priceScheduleMaster);

		Assert.assertEquals(priceScheduleMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		PriceScheduleMaster newPriceScheduleMaster = addPriceScheduleMaster();

		_persistence.remove(newPriceScheduleMaster);

		PriceScheduleMaster existingPriceScheduleMaster = _persistence.fetchByPrimaryKey(newPriceScheduleMaster.getPrimaryKey());

		Assert.assertNull(existingPriceScheduleMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addPriceScheduleMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		PriceScheduleMaster newPriceScheduleMaster = _persistence.create(pk);

		newPriceScheduleMaster.setParentPriceScheduleName(RandomTestUtil.randomString());

		newPriceScheduleMaster.setParentPriceScheduleId(RandomTestUtil.randomString());

		newPriceScheduleMaster.setPriceScheduleStartDate(RandomTestUtil.nextDate());

		newPriceScheduleMaster.setPriceScheduleNo(RandomTestUtil.randomString());

		newPriceScheduleMaster.setPriceScheduleName(RandomTestUtil.randomString());

		newPriceScheduleMaster.setPriceScheduleId(RandomTestUtil.randomString());

		newPriceScheduleMaster.setPriceScheduleType(RandomTestUtil.randomString());

		newPriceScheduleMaster.setModifiedDate(RandomTestUtil.nextDate());

		newPriceScheduleMaster.setRecordLockStatus(RandomTestUtil.randomString());

		newPriceScheduleMaster.setCreatedDate(RandomTestUtil.nextDate());

		newPriceScheduleMaster.setCreatedBy(RandomTestUtil.randomString());

		newPriceScheduleMaster.setPriceScheduleDesignation(RandomTestUtil.randomString());

		newPriceScheduleMaster.setPriceScheduleEndDate(RandomTestUtil.nextDate());

		newPriceScheduleMaster.setPriceScheduleStatus(RandomTestUtil.randomString());

		newPriceScheduleMaster.setBatchId(RandomTestUtil.randomString());

		newPriceScheduleMaster.setPriceScheduleCategory(RandomTestUtil.randomString());

		newPriceScheduleMaster.setTradeClass(RandomTestUtil.randomString());

		newPriceScheduleMaster.setInboundStatus(RandomTestUtil.randomString());

		newPriceScheduleMaster.setModifiedBy(RandomTestUtil.randomString());

		_priceScheduleMasters.add(_persistence.update(newPriceScheduleMaster));

		PriceScheduleMaster existingPriceScheduleMaster = _persistence.findByPrimaryKey(newPriceScheduleMaster.getPrimaryKey());

		Assert.assertEquals(existingPriceScheduleMaster.getParentPriceScheduleName(),
			newPriceScheduleMaster.getParentPriceScheduleName());
		Assert.assertEquals(existingPriceScheduleMaster.getParentPriceScheduleId(),
			newPriceScheduleMaster.getParentPriceScheduleId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingPriceScheduleMaster.getPriceScheduleStartDate()),
			Time.getShortTimestamp(
				newPriceScheduleMaster.getPriceScheduleStartDate()));
		Assert.assertEquals(existingPriceScheduleMaster.getPriceScheduleNo(),
			newPriceScheduleMaster.getPriceScheduleNo());
		Assert.assertEquals(existingPriceScheduleMaster.getPriceScheduleName(),
			newPriceScheduleMaster.getPriceScheduleName());
		Assert.assertEquals(existingPriceScheduleMaster.getPriceScheduleId(),
			newPriceScheduleMaster.getPriceScheduleId());
		Assert.assertEquals(existingPriceScheduleMaster.getPriceScheduleType(),
			newPriceScheduleMaster.getPriceScheduleType());
		Assert.assertEquals(Time.getShortTimestamp(
				existingPriceScheduleMaster.getModifiedDate()),
			Time.getShortTimestamp(newPriceScheduleMaster.getModifiedDate()));
		Assert.assertEquals(existingPriceScheduleMaster.getPriceScheduleSystemId(),
			newPriceScheduleMaster.getPriceScheduleSystemId());
		Assert.assertEquals(existingPriceScheduleMaster.getRecordLockStatus(),
			newPriceScheduleMaster.getRecordLockStatus());
		Assert.assertEquals(Time.getShortTimestamp(
				existingPriceScheduleMaster.getCreatedDate()),
			Time.getShortTimestamp(newPriceScheduleMaster.getCreatedDate()));
		Assert.assertEquals(existingPriceScheduleMaster.getCreatedBy(),
			newPriceScheduleMaster.getCreatedBy());
		Assert.assertEquals(existingPriceScheduleMaster.getPriceScheduleDesignation(),
			newPriceScheduleMaster.getPriceScheduleDesignation());
		Assert.assertEquals(Time.getShortTimestamp(
				existingPriceScheduleMaster.getPriceScheduleEndDate()),
			Time.getShortTimestamp(
				newPriceScheduleMaster.getPriceScheduleEndDate()));
		Assert.assertEquals(existingPriceScheduleMaster.getPriceScheduleStatus(),
			newPriceScheduleMaster.getPriceScheduleStatus());
		Assert.assertEquals(existingPriceScheduleMaster.getBatchId(),
			newPriceScheduleMaster.getBatchId());
		Assert.assertEquals(existingPriceScheduleMaster.getPriceScheduleCategory(),
			newPriceScheduleMaster.getPriceScheduleCategory());
		Assert.assertEquals(existingPriceScheduleMaster.getTradeClass(),
			newPriceScheduleMaster.getTradeClass());
		Assert.assertEquals(existingPriceScheduleMaster.getInboundStatus(),
			newPriceScheduleMaster.getInboundStatus());
		Assert.assertEquals(existingPriceScheduleMaster.getModifiedBy(),
			newPriceScheduleMaster.getModifiedBy());
	}

	@Test
	public void testCountByPriceScheduleId() throws Exception {
		_persistence.countByPriceScheduleId(StringPool.BLANK);

		_persistence.countByPriceScheduleId(StringPool.NULL);

		_persistence.countByPriceScheduleId((String)null);
	}

	@Test
	public void testCountByPriceScheduleNo() throws Exception {
		_persistence.countByPriceScheduleNo(StringPool.BLANK);

		_persistence.countByPriceScheduleNo(StringPool.NULL);

		_persistence.countByPriceScheduleNo((String)null);
	}

	@Test
	public void testCountByPriceScheduleName() throws Exception {
		_persistence.countByPriceScheduleName(StringPool.BLANK);

		_persistence.countByPriceScheduleName(StringPool.NULL);

		_persistence.countByPriceScheduleName((String)null);
	}

	@Test
	public void testCountByPriceScheduleType() throws Exception {
		_persistence.countByPriceScheduleType(StringPool.BLANK);

		_persistence.countByPriceScheduleType(StringPool.NULL);

		_persistence.countByPriceScheduleType((String)null);
	}

	@Test
	public void testCountByPriceScheduleStatus() throws Exception {
		_persistence.countByPriceScheduleStatus(StringPool.BLANK);

		_persistence.countByPriceScheduleStatus(StringPool.NULL);

		_persistence.countByPriceScheduleStatus((String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		PriceScheduleMaster newPriceScheduleMaster = addPriceScheduleMaster();

		PriceScheduleMaster existingPriceScheduleMaster = _persistence.findByPrimaryKey(newPriceScheduleMaster.getPrimaryKey());

		Assert.assertEquals(existingPriceScheduleMaster, newPriceScheduleMaster);
	}

	@Test(expected = NoSuchPriceScheduleMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<PriceScheduleMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("PRICE_SCHEDULE_MASTER",
			"parentPriceScheduleName", true, "parentPriceScheduleId", true,
			"priceScheduleStartDate", true, "priceScheduleNo", true,
			"priceScheduleName", true, "priceScheduleId", true,
			"priceScheduleType", true, "modifiedDate", true,
			"priceScheduleSystemId", true, "recordLockStatus", true,
			"createdDate", true, "createdBy", true, "priceScheduleDesignation",
			true, "priceScheduleEndDate", true, "priceScheduleStatus", true,
			"batchId", true, "priceScheduleCategory", true, "tradeClass", true,
			"inboundStatus", true, "modifiedBy", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		PriceScheduleMaster newPriceScheduleMaster = addPriceScheduleMaster();

		PriceScheduleMaster existingPriceScheduleMaster = _persistence.fetchByPrimaryKey(newPriceScheduleMaster.getPrimaryKey());

		Assert.assertEquals(existingPriceScheduleMaster, newPriceScheduleMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		PriceScheduleMaster missingPriceScheduleMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingPriceScheduleMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		PriceScheduleMaster newPriceScheduleMaster1 = addPriceScheduleMaster();
		PriceScheduleMaster newPriceScheduleMaster2 = addPriceScheduleMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newPriceScheduleMaster1.getPrimaryKey());
		primaryKeys.add(newPriceScheduleMaster2.getPrimaryKey());

		Map<Serializable, PriceScheduleMaster> priceScheduleMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, priceScheduleMasters.size());
		Assert.assertEquals(newPriceScheduleMaster1,
			priceScheduleMasters.get(newPriceScheduleMaster1.getPrimaryKey()));
		Assert.assertEquals(newPriceScheduleMaster2,
			priceScheduleMasters.get(newPriceScheduleMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, PriceScheduleMaster> priceScheduleMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(priceScheduleMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		PriceScheduleMaster newPriceScheduleMaster = addPriceScheduleMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newPriceScheduleMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, PriceScheduleMaster> priceScheduleMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, priceScheduleMasters.size());
		Assert.assertEquals(newPriceScheduleMaster,
			priceScheduleMasters.get(newPriceScheduleMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, PriceScheduleMaster> priceScheduleMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(priceScheduleMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		PriceScheduleMaster newPriceScheduleMaster = addPriceScheduleMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newPriceScheduleMaster.getPrimaryKey());

		Map<Serializable, PriceScheduleMaster> priceScheduleMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, priceScheduleMasters.size());
		Assert.assertEquals(newPriceScheduleMaster,
			priceScheduleMasters.get(newPriceScheduleMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = PriceScheduleMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<PriceScheduleMaster>() {
				@Override
				public void performAction(
					PriceScheduleMaster priceScheduleMaster) {
					Assert.assertNotNull(priceScheduleMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		PriceScheduleMaster newPriceScheduleMaster = addPriceScheduleMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PriceScheduleMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("priceScheduleSystemId",
				newPriceScheduleMaster.getPriceScheduleSystemId()));

		List<PriceScheduleMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		PriceScheduleMaster existingPriceScheduleMaster = result.get(0);

		Assert.assertEquals(existingPriceScheduleMaster, newPriceScheduleMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PriceScheduleMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("priceScheduleSystemId",
				RandomTestUtil.nextInt()));

		List<PriceScheduleMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		PriceScheduleMaster newPriceScheduleMaster = addPriceScheduleMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PriceScheduleMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"priceScheduleSystemId"));

		Object newPriceScheduleSystemId = newPriceScheduleMaster.getPriceScheduleSystemId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("priceScheduleSystemId",
				new Object[] { newPriceScheduleSystemId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingPriceScheduleSystemId = result.get(0);

		Assert.assertEquals(existingPriceScheduleSystemId,
			newPriceScheduleSystemId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PriceScheduleMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"priceScheduleSystemId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("priceScheduleSystemId",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected PriceScheduleMaster addPriceScheduleMaster()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		PriceScheduleMaster priceScheduleMaster = _persistence.create(pk);

		priceScheduleMaster.setParentPriceScheduleName(RandomTestUtil.randomString());

		priceScheduleMaster.setParentPriceScheduleId(RandomTestUtil.randomString());

		priceScheduleMaster.setPriceScheduleStartDate(RandomTestUtil.nextDate());

		priceScheduleMaster.setPriceScheduleNo(RandomTestUtil.randomString());

		priceScheduleMaster.setPriceScheduleName(RandomTestUtil.randomString());

		priceScheduleMaster.setPriceScheduleId(RandomTestUtil.randomString());

		priceScheduleMaster.setPriceScheduleType(RandomTestUtil.randomString());

		priceScheduleMaster.setModifiedDate(RandomTestUtil.nextDate());

		priceScheduleMaster.setRecordLockStatus(RandomTestUtil.randomString());

		priceScheduleMaster.setCreatedDate(RandomTestUtil.nextDate());

		priceScheduleMaster.setCreatedBy(RandomTestUtil.randomString());

		priceScheduleMaster.setPriceScheduleDesignation(RandomTestUtil.randomString());

		priceScheduleMaster.setPriceScheduleEndDate(RandomTestUtil.nextDate());

		priceScheduleMaster.setPriceScheduleStatus(RandomTestUtil.randomString());

		priceScheduleMaster.setBatchId(RandomTestUtil.randomString());

		priceScheduleMaster.setPriceScheduleCategory(RandomTestUtil.randomString());

		priceScheduleMaster.setTradeClass(RandomTestUtil.randomString());

		priceScheduleMaster.setInboundStatus(RandomTestUtil.randomString());

		priceScheduleMaster.setModifiedBy(RandomTestUtil.randomString());

		_priceScheduleMasters.add(_persistence.update(priceScheduleMaster));

		return priceScheduleMaster;
	}

	private List<PriceScheduleMaster> _priceScheduleMasters = new ArrayList<PriceScheduleMaster>();
	private PriceScheduleMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}