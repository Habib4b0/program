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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchBestPriceMasterException;
import com.stpl.app.model.BestPriceMaster;
import com.stpl.app.service.BestPriceMasterLocalServiceUtil;
import com.stpl.app.service.persistence.BestPriceMasterPersistence;
import com.stpl.app.service.persistence.BestPriceMasterUtil;

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
public class BestPriceMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = BestPriceMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<BestPriceMaster> iterator = _bestPriceMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		BestPriceMaster bestPriceMaster = _persistence.create(pk);

		Assert.assertNotNull(bestPriceMaster);

		Assert.assertEquals(bestPriceMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		BestPriceMaster newBestPriceMaster = addBestPriceMaster();

		_persistence.remove(newBestPriceMaster);

		BestPriceMaster existingBestPriceMaster = _persistence.fetchByPrimaryKey(newBestPriceMaster.getPrimaryKey());

		Assert.assertNull(existingBestPriceMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addBestPriceMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		BestPriceMaster newBestPriceMaster = _persistence.create(pk);

		newBestPriceMaster.setInitialBestPrice(RandomTestUtil.nextDouble());

		newBestPriceMaster.setCreatedBy(RandomTestUtil.nextInt());

		newBestPriceMaster.setItemNo(RandomTestUtil.randomString());

		newBestPriceMaster.setModifiedBy(RandomTestUtil.nextInt());

		newBestPriceMaster.setAccountId(RandomTestUtil.randomString());

		newBestPriceMaster.setCreatedDate(RandomTestUtil.nextDate());

		newBestPriceMaster.setItemId(RandomTestUtil.randomString());

		newBestPriceMaster.setItemDesc(RandomTestUtil.randomString());

		newBestPriceMaster.setSellingPrice(RandomTestUtil.nextDouble());

		newBestPriceMaster.setContractId(RandomTestUtil.randomString());

		newBestPriceMaster.setContractNo(RandomTestUtil.randomString());

		newBestPriceMaster.setBatchId(RandomTestUtil.randomString());

		newBestPriceMaster.setModifiedDate(RandomTestUtil.nextDate());

		newBestPriceMaster.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newBestPriceMaster.setBeginningWacPackage(RandomTestUtil.nextDouble());

		newBestPriceMaster.setInitialDiscount(RandomTestUtil.nextDouble());

		newBestPriceMaster.setPeriod(RandomTestUtil.randomString());

		newBestPriceMaster.setSource(RandomTestUtil.randomString());

		newBestPriceMaster.setContractStartDate(RandomTestUtil.nextDate());

		newBestPriceMaster.setContractEndDate(RandomTestUtil.nextDate());

		newBestPriceMaster.setInboundStatus(RandomTestUtil.randomString());

		_bestPriceMasters.add(_persistence.update(newBestPriceMaster));

		BestPriceMaster existingBestPriceMaster = _persistence.findByPrimaryKey(newBestPriceMaster.getPrimaryKey());

		AssertUtils.assertEquals(existingBestPriceMaster.getInitialBestPrice(),
			newBestPriceMaster.getInitialBestPrice());
		Assert.assertEquals(existingBestPriceMaster.getCreatedBy(),
			newBestPriceMaster.getCreatedBy());
		Assert.assertEquals(existingBestPriceMaster.getItemNo(),
			newBestPriceMaster.getItemNo());
		Assert.assertEquals(existingBestPriceMaster.getModifiedBy(),
			newBestPriceMaster.getModifiedBy());
		Assert.assertEquals(existingBestPriceMaster.getAccountId(),
			newBestPriceMaster.getAccountId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingBestPriceMaster.getCreatedDate()),
			Time.getShortTimestamp(newBestPriceMaster.getCreatedDate()));
		Assert.assertEquals(existingBestPriceMaster.getItemId(),
			newBestPriceMaster.getItemId());
		Assert.assertEquals(existingBestPriceMaster.getItemDesc(),
			newBestPriceMaster.getItemDesc());
		AssertUtils.assertEquals(existingBestPriceMaster.getSellingPrice(),
			newBestPriceMaster.getSellingPrice());
		Assert.assertEquals(existingBestPriceMaster.getContractId(),
			newBestPriceMaster.getContractId());
		Assert.assertEquals(existingBestPriceMaster.getContractNo(),
			newBestPriceMaster.getContractNo());
		Assert.assertEquals(existingBestPriceMaster.getBatchId(),
			newBestPriceMaster.getBatchId());
		Assert.assertEquals(existingBestPriceMaster.getBestPriceMasterSid(),
			newBestPriceMaster.getBestPriceMasterSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingBestPriceMaster.getModifiedDate()),
			Time.getShortTimestamp(newBestPriceMaster.getModifiedDate()));
		Assert.assertEquals(existingBestPriceMaster.getRecordLockStatus(),
			newBestPriceMaster.getRecordLockStatus());
		AssertUtils.assertEquals(existingBestPriceMaster.getBeginningWacPackage(),
			newBestPriceMaster.getBeginningWacPackage());
		AssertUtils.assertEquals(existingBestPriceMaster.getInitialDiscount(),
			newBestPriceMaster.getInitialDiscount());
		Assert.assertEquals(existingBestPriceMaster.getPeriod(),
			newBestPriceMaster.getPeriod());
		Assert.assertEquals(existingBestPriceMaster.getSource(),
			newBestPriceMaster.getSource());
		Assert.assertEquals(Time.getShortTimestamp(
				existingBestPriceMaster.getContractStartDate()),
			Time.getShortTimestamp(newBestPriceMaster.getContractStartDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingBestPriceMaster.getContractEndDate()),
			Time.getShortTimestamp(newBestPriceMaster.getContractEndDate()));
		Assert.assertEquals(existingBestPriceMaster.getInboundStatus(),
			newBestPriceMaster.getInboundStatus());
	}

	@Test
	public void testCountByItemId() throws Exception {
		_persistence.countByItemId(StringPool.BLANK);

		_persistence.countByItemId(StringPool.NULL);

		_persistence.countByItemId((String)null);
	}

	@Test
	public void testCountByItemNo() throws Exception {
		_persistence.countByItemNo(StringPool.BLANK);

		_persistence.countByItemNo(StringPool.NULL);

		_persistence.countByItemNo((String)null);
	}

	@Test
	public void testCountByContractNo() throws Exception {
		_persistence.countByContractNo(StringPool.BLANK);

		_persistence.countByContractNo(StringPool.NULL);

		_persistence.countByContractNo((String)null);
	}

	@Test
	public void testCountByContractId() throws Exception {
		_persistence.countByContractId(StringPool.BLANK);

		_persistence.countByContractId(StringPool.NULL);

		_persistence.countByContractId((String)null);
	}

	@Test
	public void testCountByAccountId() throws Exception {
		_persistence.countByAccountId(StringPool.BLANK);

		_persistence.countByAccountId(StringPool.NULL);

		_persistence.countByAccountId((String)null);
	}

	@Test
	public void testCountByPeriod() throws Exception {
		_persistence.countByPeriod(StringPool.BLANK);

		_persistence.countByPeriod(StringPool.NULL);

		_persistence.countByPeriod((String)null);
	}

	@Test
	public void testCountByBestPriceUnique() throws Exception {
		_persistence.countByBestPriceUnique(StringPool.BLANK, StringPool.BLANK,
			StringPool.BLANK, StringPool.BLANK);

		_persistence.countByBestPriceUnique(StringPool.NULL, StringPool.NULL,
			StringPool.NULL, StringPool.NULL);

		_persistence.countByBestPriceUnique((String)null, (String)null,
			(String)null, (String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		BestPriceMaster newBestPriceMaster = addBestPriceMaster();

		BestPriceMaster existingBestPriceMaster = _persistence.findByPrimaryKey(newBestPriceMaster.getPrimaryKey());

		Assert.assertEquals(existingBestPriceMaster, newBestPriceMaster);
	}

	@Test(expected = NoSuchBestPriceMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<BestPriceMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("BEST_PRICE_MASTER",
			"initialBestPrice", true, "createdBy", true, "itemNo", true,
			"modifiedBy", true, "accountId", true, "createdDate", true,
			"itemId", true, "itemDesc", true, "sellingPrice", true,
			"contractId", true, "contractNo", true, "batchId", true,
			"bestPriceMasterSid", true, "modifiedDate", true,
			"recordLockStatus", true, "beginningWacPackage", true,
			"initialDiscount", true, "period", true, "source", true,
			"contractStartDate", true, "contractEndDate", true,
			"inboundStatus", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		BestPriceMaster newBestPriceMaster = addBestPriceMaster();

		BestPriceMaster existingBestPriceMaster = _persistence.fetchByPrimaryKey(newBestPriceMaster.getPrimaryKey());

		Assert.assertEquals(existingBestPriceMaster, newBestPriceMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		BestPriceMaster missingBestPriceMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingBestPriceMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		BestPriceMaster newBestPriceMaster1 = addBestPriceMaster();
		BestPriceMaster newBestPriceMaster2 = addBestPriceMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newBestPriceMaster1.getPrimaryKey());
		primaryKeys.add(newBestPriceMaster2.getPrimaryKey());

		Map<Serializable, BestPriceMaster> bestPriceMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, bestPriceMasters.size());
		Assert.assertEquals(newBestPriceMaster1,
			bestPriceMasters.get(newBestPriceMaster1.getPrimaryKey()));
		Assert.assertEquals(newBestPriceMaster2,
			bestPriceMasters.get(newBestPriceMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, BestPriceMaster> bestPriceMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(bestPriceMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		BestPriceMaster newBestPriceMaster = addBestPriceMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newBestPriceMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, BestPriceMaster> bestPriceMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, bestPriceMasters.size());
		Assert.assertEquals(newBestPriceMaster,
			bestPriceMasters.get(newBestPriceMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, BestPriceMaster> bestPriceMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(bestPriceMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		BestPriceMaster newBestPriceMaster = addBestPriceMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newBestPriceMaster.getPrimaryKey());

		Map<Serializable, BestPriceMaster> bestPriceMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, bestPriceMasters.size());
		Assert.assertEquals(newBestPriceMaster,
			bestPriceMasters.get(newBestPriceMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = BestPriceMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<BestPriceMaster>() {
				@Override
				public void performAction(BestPriceMaster bestPriceMaster) {
					Assert.assertNotNull(bestPriceMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		BestPriceMaster newBestPriceMaster = addBestPriceMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(BestPriceMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("bestPriceMasterSid",
				newBestPriceMaster.getBestPriceMasterSid()));

		List<BestPriceMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		BestPriceMaster existingBestPriceMaster = result.get(0);

		Assert.assertEquals(existingBestPriceMaster, newBestPriceMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(BestPriceMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("bestPriceMasterSid",
				RandomTestUtil.nextInt()));

		List<BestPriceMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		BestPriceMaster newBestPriceMaster = addBestPriceMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(BestPriceMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"bestPriceMasterSid"));

		Object newBestPriceMasterSid = newBestPriceMaster.getBestPriceMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("bestPriceMasterSid",
				new Object[] { newBestPriceMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingBestPriceMasterSid = result.get(0);

		Assert.assertEquals(existingBestPriceMasterSid, newBestPriceMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(BestPriceMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"bestPriceMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("bestPriceMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected BestPriceMaster addBestPriceMaster() throws Exception {
		int pk = RandomTestUtil.nextInt();

		BestPriceMaster bestPriceMaster = _persistence.create(pk);

		bestPriceMaster.setInitialBestPrice(RandomTestUtil.nextDouble());

		bestPriceMaster.setCreatedBy(RandomTestUtil.nextInt());

		bestPriceMaster.setItemNo(RandomTestUtil.randomString());

		bestPriceMaster.setModifiedBy(RandomTestUtil.nextInt());

		bestPriceMaster.setAccountId(RandomTestUtil.randomString());

		bestPriceMaster.setCreatedDate(RandomTestUtil.nextDate());

		bestPriceMaster.setItemId(RandomTestUtil.randomString());

		bestPriceMaster.setItemDesc(RandomTestUtil.randomString());

		bestPriceMaster.setSellingPrice(RandomTestUtil.nextDouble());

		bestPriceMaster.setContractId(RandomTestUtil.randomString());

		bestPriceMaster.setContractNo(RandomTestUtil.randomString());

		bestPriceMaster.setBatchId(RandomTestUtil.randomString());

		bestPriceMaster.setModifiedDate(RandomTestUtil.nextDate());

		bestPriceMaster.setRecordLockStatus(RandomTestUtil.randomBoolean());

		bestPriceMaster.setBeginningWacPackage(RandomTestUtil.nextDouble());

		bestPriceMaster.setInitialDiscount(RandomTestUtil.nextDouble());

		bestPriceMaster.setPeriod(RandomTestUtil.randomString());

		bestPriceMaster.setSource(RandomTestUtil.randomString());

		bestPriceMaster.setContractStartDate(RandomTestUtil.nextDate());

		bestPriceMaster.setContractEndDate(RandomTestUtil.nextDate());

		bestPriceMaster.setInboundStatus(RandomTestUtil.randomString());

		_bestPriceMasters.add(_persistence.update(bestPriceMaster));

		return bestPriceMaster;
	}

	private List<BestPriceMaster> _bestPriceMasters = new ArrayList<BestPriceMaster>();
	private BestPriceMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}