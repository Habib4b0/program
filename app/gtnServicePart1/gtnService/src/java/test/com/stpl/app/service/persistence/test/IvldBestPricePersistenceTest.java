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

import com.stpl.app.exception.NoSuchIvldBestPriceException;
import com.stpl.app.model.IvldBestPrice;
import com.stpl.app.service.IvldBestPriceLocalServiceUtil;
import com.stpl.app.service.persistence.IvldBestPricePersistence;
import com.stpl.app.service.persistence.IvldBestPriceUtil;

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
public class IvldBestPricePersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = IvldBestPriceUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<IvldBestPrice> iterator = _ivldBestPrices.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldBestPrice ivldBestPrice = _persistence.create(pk);

		Assert.assertNotNull(ivldBestPrice);

		Assert.assertEquals(ivldBestPrice.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		IvldBestPrice newIvldBestPrice = addIvldBestPrice();

		_persistence.remove(newIvldBestPrice);

		IvldBestPrice existingIvldBestPrice = _persistence.fetchByPrimaryKey(newIvldBestPrice.getPrimaryKey());

		Assert.assertNull(existingIvldBestPrice);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addIvldBestPrice();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldBestPrice newIvldBestPrice = _persistence.create(pk);

		newIvldBestPrice.setItemDesc(RandomTestUtil.randomString());

		newIvldBestPrice.setBestPriceIntfid(RandomTestUtil.randomString());

		newIvldBestPrice.setAccountId(RandomTestUtil.randomString());

		newIvldBestPrice.setSellingPrice(RandomTestUtil.randomString());

		newIvldBestPrice.setPeriod(RandomTestUtil.randomString());

		newIvldBestPrice.setItemId(RandomTestUtil.randomString());

		newIvldBestPrice.setModifiedDate(RandomTestUtil.nextDate());

		newIvldBestPrice.setCreatedBy(RandomTestUtil.randomString());

		newIvldBestPrice.setCreatedDate(RandomTestUtil.nextDate());

		newIvldBestPrice.setSource(RandomTestUtil.randomString());

		newIvldBestPrice.setAddChgDelIndicator(RandomTestUtil.randomString());

		newIvldBestPrice.setInitialDiscount(RandomTestUtil.randomString());

		newIvldBestPrice.setErrorCode(RandomTestUtil.randomString());

		newIvldBestPrice.setModifiedBy(RandomTestUtil.randomString());

		newIvldBestPrice.setIntfInsertedDate(RandomTestUtil.nextDate());

		newIvldBestPrice.setItemNo(RandomTestUtil.randomString());

		newIvldBestPrice.setReprocessedFlag(RandomTestUtil.randomString());

		newIvldBestPrice.setContractEndDate(RandomTestUtil.randomString());

		newIvldBestPrice.setContractId(RandomTestUtil.randomString());

		newIvldBestPrice.setBeginningWacPackage(RandomTestUtil.randomString());

		newIvldBestPrice.setReasonForFailure(RandomTestUtil.randomString());

		newIvldBestPrice.setContractStartDate(RandomTestUtil.randomString());

		newIvldBestPrice.setBatchId(RandomTestUtil.randomString());

		newIvldBestPrice.setErrorField(RandomTestUtil.randomString());

		newIvldBestPrice.setInitialBestPrice(RandomTestUtil.randomString());

		newIvldBestPrice.setContractNo(RandomTestUtil.randomString());

		newIvldBestPrice.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldBestPrices.add(_persistence.update(newIvldBestPrice));

		IvldBestPrice existingIvldBestPrice = _persistence.findByPrimaryKey(newIvldBestPrice.getPrimaryKey());

		Assert.assertEquals(existingIvldBestPrice.getItemDesc(),
			newIvldBestPrice.getItemDesc());
		Assert.assertEquals(existingIvldBestPrice.getBestPriceIntfid(),
			newIvldBestPrice.getBestPriceIntfid());
		Assert.assertEquals(existingIvldBestPrice.getAccountId(),
			newIvldBestPrice.getAccountId());
		Assert.assertEquals(existingIvldBestPrice.getSellingPrice(),
			newIvldBestPrice.getSellingPrice());
		Assert.assertEquals(existingIvldBestPrice.getPeriod(),
			newIvldBestPrice.getPeriod());
		Assert.assertEquals(existingIvldBestPrice.getItemId(),
			newIvldBestPrice.getItemId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldBestPrice.getModifiedDate()),
			Time.getShortTimestamp(newIvldBestPrice.getModifiedDate()));
		Assert.assertEquals(existingIvldBestPrice.getCreatedBy(),
			newIvldBestPrice.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldBestPrice.getCreatedDate()),
			Time.getShortTimestamp(newIvldBestPrice.getCreatedDate()));
		Assert.assertEquals(existingIvldBestPrice.getSource(),
			newIvldBestPrice.getSource());
		Assert.assertEquals(existingIvldBestPrice.getAddChgDelIndicator(),
			newIvldBestPrice.getAddChgDelIndicator());
		Assert.assertEquals(existingIvldBestPrice.getInitialDiscount(),
			newIvldBestPrice.getInitialDiscount());
		Assert.assertEquals(existingIvldBestPrice.getErrorCode(),
			newIvldBestPrice.getErrorCode());
		Assert.assertEquals(existingIvldBestPrice.getModifiedBy(),
			newIvldBestPrice.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldBestPrice.getIntfInsertedDate()),
			Time.getShortTimestamp(newIvldBestPrice.getIntfInsertedDate()));
		Assert.assertEquals(existingIvldBestPrice.getItemNo(),
			newIvldBestPrice.getItemNo());
		Assert.assertEquals(existingIvldBestPrice.getReprocessedFlag(),
			newIvldBestPrice.getReprocessedFlag());
		Assert.assertEquals(existingIvldBestPrice.getContractEndDate(),
			newIvldBestPrice.getContractEndDate());
		Assert.assertEquals(existingIvldBestPrice.getIvldBestPriceSid(),
			newIvldBestPrice.getIvldBestPriceSid());
		Assert.assertEquals(existingIvldBestPrice.getContractId(),
			newIvldBestPrice.getContractId());
		Assert.assertEquals(existingIvldBestPrice.getBeginningWacPackage(),
			newIvldBestPrice.getBeginningWacPackage());
		Assert.assertEquals(existingIvldBestPrice.getReasonForFailure(),
			newIvldBestPrice.getReasonForFailure());
		Assert.assertEquals(existingIvldBestPrice.getContractStartDate(),
			newIvldBestPrice.getContractStartDate());
		Assert.assertEquals(existingIvldBestPrice.getBatchId(),
			newIvldBestPrice.getBatchId());
		Assert.assertEquals(existingIvldBestPrice.getErrorField(),
			newIvldBestPrice.getErrorField());
		Assert.assertEquals(existingIvldBestPrice.getInitialBestPrice(),
			newIvldBestPrice.getInitialBestPrice());
		Assert.assertEquals(existingIvldBestPrice.getContractNo(),
			newIvldBestPrice.getContractNo());
		Assert.assertEquals(existingIvldBestPrice.getCheckRecord(),
			newIvldBestPrice.getCheckRecord());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		IvldBestPrice newIvldBestPrice = addIvldBestPrice();

		IvldBestPrice existingIvldBestPrice = _persistence.findByPrimaryKey(newIvldBestPrice.getPrimaryKey());

		Assert.assertEquals(existingIvldBestPrice, newIvldBestPrice);
	}

	@Test(expected = NoSuchIvldBestPriceException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<IvldBestPrice> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("IVLD_BEST_PRICE",
			"itemDesc", true, "bestPriceIntfid", true, "accountId", true,
			"sellingPrice", true, "period", true, "itemId", true,
			"modifiedDate", true, "createdBy", true, "createdDate", true,
			"source", true, "addChgDelIndicator", true, "initialDiscount",
			true, "errorCode", true, "modifiedBy", true, "intfInsertedDate",
			true, "itemNo", true, "reprocessedFlag", true, "contractEndDate",
			true, "ivldBestPriceSid", true, "contractId", true,
			"beginningWacPackage", true, "reasonForFailure", true,
			"contractStartDate", true, "batchId", true, "errorField", true,
			"initialBestPrice", true, "contractNo", true, "checkRecord", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		IvldBestPrice newIvldBestPrice = addIvldBestPrice();

		IvldBestPrice existingIvldBestPrice = _persistence.fetchByPrimaryKey(newIvldBestPrice.getPrimaryKey());

		Assert.assertEquals(existingIvldBestPrice, newIvldBestPrice);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldBestPrice missingIvldBestPrice = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingIvldBestPrice);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		IvldBestPrice newIvldBestPrice1 = addIvldBestPrice();
		IvldBestPrice newIvldBestPrice2 = addIvldBestPrice();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldBestPrice1.getPrimaryKey());
		primaryKeys.add(newIvldBestPrice2.getPrimaryKey());

		Map<Serializable, IvldBestPrice> ivldBestPrices = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ivldBestPrices.size());
		Assert.assertEquals(newIvldBestPrice1,
			ivldBestPrices.get(newIvldBestPrice1.getPrimaryKey()));
		Assert.assertEquals(newIvldBestPrice2,
			ivldBestPrices.get(newIvldBestPrice2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, IvldBestPrice> ivldBestPrices = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldBestPrices.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		IvldBestPrice newIvldBestPrice = addIvldBestPrice();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldBestPrice.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, IvldBestPrice> ivldBestPrices = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldBestPrices.size());
		Assert.assertEquals(newIvldBestPrice,
			ivldBestPrices.get(newIvldBestPrice.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, IvldBestPrice> ivldBestPrices = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldBestPrices.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		IvldBestPrice newIvldBestPrice = addIvldBestPrice();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldBestPrice.getPrimaryKey());

		Map<Serializable, IvldBestPrice> ivldBestPrices = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldBestPrices.size());
		Assert.assertEquals(newIvldBestPrice,
			ivldBestPrices.get(newIvldBestPrice.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = IvldBestPriceLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<IvldBestPrice>() {
				@Override
				public void performAction(IvldBestPrice ivldBestPrice) {
					Assert.assertNotNull(ivldBestPrice);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		IvldBestPrice newIvldBestPrice = addIvldBestPrice();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldBestPrice.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldBestPriceSid",
				newIvldBestPrice.getIvldBestPriceSid()));

		List<IvldBestPrice> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		IvldBestPrice existingIvldBestPrice = result.get(0);

		Assert.assertEquals(existingIvldBestPrice, newIvldBestPrice);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldBestPrice.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldBestPriceSid",
				RandomTestUtil.nextInt()));

		List<IvldBestPrice> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		IvldBestPrice newIvldBestPrice = addIvldBestPrice();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldBestPrice.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldBestPriceSid"));

		Object newIvldBestPriceSid = newIvldBestPrice.getIvldBestPriceSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldBestPriceSid",
				new Object[] { newIvldBestPriceSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingIvldBestPriceSid = result.get(0);

		Assert.assertEquals(existingIvldBestPriceSid, newIvldBestPriceSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldBestPrice.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldBestPriceSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldBestPriceSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected IvldBestPrice addIvldBestPrice() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldBestPrice ivldBestPrice = _persistence.create(pk);

		ivldBestPrice.setItemDesc(RandomTestUtil.randomString());

		ivldBestPrice.setBestPriceIntfid(RandomTestUtil.randomString());

		ivldBestPrice.setAccountId(RandomTestUtil.randomString());

		ivldBestPrice.setSellingPrice(RandomTestUtil.randomString());

		ivldBestPrice.setPeriod(RandomTestUtil.randomString());

		ivldBestPrice.setItemId(RandomTestUtil.randomString());

		ivldBestPrice.setModifiedDate(RandomTestUtil.nextDate());

		ivldBestPrice.setCreatedBy(RandomTestUtil.randomString());

		ivldBestPrice.setCreatedDate(RandomTestUtil.nextDate());

		ivldBestPrice.setSource(RandomTestUtil.randomString());

		ivldBestPrice.setAddChgDelIndicator(RandomTestUtil.randomString());

		ivldBestPrice.setInitialDiscount(RandomTestUtil.randomString());

		ivldBestPrice.setErrorCode(RandomTestUtil.randomString());

		ivldBestPrice.setModifiedBy(RandomTestUtil.randomString());

		ivldBestPrice.setIntfInsertedDate(RandomTestUtil.nextDate());

		ivldBestPrice.setItemNo(RandomTestUtil.randomString());

		ivldBestPrice.setReprocessedFlag(RandomTestUtil.randomString());

		ivldBestPrice.setContractEndDate(RandomTestUtil.randomString());

		ivldBestPrice.setContractId(RandomTestUtil.randomString());

		ivldBestPrice.setBeginningWacPackage(RandomTestUtil.randomString());

		ivldBestPrice.setReasonForFailure(RandomTestUtil.randomString());

		ivldBestPrice.setContractStartDate(RandomTestUtil.randomString());

		ivldBestPrice.setBatchId(RandomTestUtil.randomString());

		ivldBestPrice.setErrorField(RandomTestUtil.randomString());

		ivldBestPrice.setInitialBestPrice(RandomTestUtil.randomString());

		ivldBestPrice.setContractNo(RandomTestUtil.randomString());

		ivldBestPrice.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldBestPrices.add(_persistence.update(ivldBestPrice));

		return ivldBestPrice;
	}

	private List<IvldBestPrice> _ivldBestPrices = new ArrayList<IvldBestPrice>();
	private IvldBestPricePersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}