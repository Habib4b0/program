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

package com.stpl.app.parttwo.service.persistence.test;

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

import com.stpl.app.parttwo.exception.NoSuchVwAdjustDemandForecastActException;
import com.stpl.app.parttwo.model.VwAdjustDemandForecastAct;
import com.stpl.app.parttwo.service.VwAdjustDemandForecastActLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.VwAdjustDemandForecastActPersistence;
import com.stpl.app.parttwo.service.persistence.VwAdjustDemandForecastActUtil;

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
public class VwAdjustDemandForecastActPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = VwAdjustDemandForecastActUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<VwAdjustDemandForecastAct> iterator = _vwAdjustDemandForecastActs.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwAdjustDemandForecastAct vwAdjustDemandForecastAct = _persistence.create(pk);

		Assert.assertNotNull(vwAdjustDemandForecastAct);

		Assert.assertEquals(vwAdjustDemandForecastAct.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		VwAdjustDemandForecastAct newVwAdjustDemandForecastAct = addVwAdjustDemandForecastAct();

		_persistence.remove(newVwAdjustDemandForecastAct);

		VwAdjustDemandForecastAct existingVwAdjustDemandForecastAct = _persistence.fetchByPrimaryKey(newVwAdjustDemandForecastAct.getPrimaryKey());

		Assert.assertNull(existingVwAdjustDemandForecastAct);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addVwAdjustDemandForecastAct();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwAdjustDemandForecastAct newVwAdjustDemandForecastAct = _persistence.create(pk);

		newVwAdjustDemandForecastAct.setForecastVer(RandomTestUtil.randomString());

		newVwAdjustDemandForecastAct.setGrossUnits(RandomTestUtil.nextDouble());

		newVwAdjustDemandForecastAct.setBusinessUnitNo(RandomTestUtil.randomString());

		newVwAdjustDemandForecastAct.setForecastYear(RandomTestUtil.randomString());

		newVwAdjustDemandForecastAct.setBrandName(RandomTestUtil.randomString());

		newVwAdjustDemandForecastAct.setItemId(RandomTestUtil.randomString());

		newVwAdjustDemandForecastAct.setOrganizationKey(RandomTestUtil.randomString());

		newVwAdjustDemandForecastAct.setSource(RandomTestUtil.randomString());

		newVwAdjustDemandForecastAct.setMarketShareRatio(RandomTestUtil.nextInt());

		newVwAdjustDemandForecastAct.setBusinessUnitName(RandomTestUtil.randomString());

		newVwAdjustDemandForecastAct.setMarketShareUnits(RandomTestUtil.nextDouble());

		newVwAdjustDemandForecastAct.setForecastMonth(RandomTestUtil.randomString());

		newVwAdjustDemandForecastAct.setInventoryUnitChange(RandomTestUtil.nextDouble());

		newVwAdjustDemandForecastAct.setUncapturedUnitsRatio(RandomTestUtil.randomString());

		newVwAdjustDemandForecastAct.setCountry(RandomTestUtil.randomString());

		newVwAdjustDemandForecastAct.setForecastType(RandomTestUtil.randomString());

		newVwAdjustDemandForecastAct.setTotalDemandUnits(RandomTestUtil.nextDouble());

		newVwAdjustDemandForecastAct.setBrandId(RandomTestUtil.randomString());

		newVwAdjustDemandForecastAct.setIsForecast(RandomTestUtil.randomString());

		newVwAdjustDemandForecastAct.setTotalDemandAmount(RandomTestUtil.nextDouble());

		newVwAdjustDemandForecastAct.setUncapturedUnits(RandomTestUtil.nextDouble());

		newVwAdjustDemandForecastAct.setGrossPrice(RandomTestUtil.nextDouble());

		newVwAdjustDemandForecastAct.setGrossAmount(RandomTestUtil.nextDouble());

		newVwAdjustDemandForecastAct.setBatchId(RandomTestUtil.randomString());

		newVwAdjustDemandForecastAct.setItemName(RandomTestUtil.randomString());

		newVwAdjustDemandForecastAct.setNetSalesPrice(RandomTestUtil.nextDouble());

		newVwAdjustDemandForecastAct.setNetSalesAmount(RandomTestUtil.nextDouble());

		newVwAdjustDemandForecastAct.setSegment(RandomTestUtil.randomString());

		newVwAdjustDemandForecastAct.setForecastName(RandomTestUtil.randomString());

		newVwAdjustDemandForecastAct.setMarketSizeUnits(RandomTestUtil.nextDouble());

		_vwAdjustDemandForecastActs.add(_persistence.update(
				newVwAdjustDemandForecastAct));

		VwAdjustDemandForecastAct existingVwAdjustDemandForecastAct = _persistence.findByPrimaryKey(newVwAdjustDemandForecastAct.getPrimaryKey());

		Assert.assertEquals(existingVwAdjustDemandForecastAct.getForecastVer(),
			newVwAdjustDemandForecastAct.getForecastVer());
		AssertUtils.assertEquals(existingVwAdjustDemandForecastAct.getGrossUnits(),
			newVwAdjustDemandForecastAct.getGrossUnits());
		Assert.assertEquals(existingVwAdjustDemandForecastAct.getBusinessUnitNo(),
			newVwAdjustDemandForecastAct.getBusinessUnitNo());
		Assert.assertEquals(existingVwAdjustDemandForecastAct.getForecastYear(),
			newVwAdjustDemandForecastAct.getForecastYear());
		Assert.assertEquals(existingVwAdjustDemandForecastAct.getBrandName(),
			newVwAdjustDemandForecastAct.getBrandName());
		Assert.assertEquals(existingVwAdjustDemandForecastAct.getItemId(),
			newVwAdjustDemandForecastAct.getItemId());
		Assert.assertEquals(existingVwAdjustDemandForecastAct.getOrganizationKey(),
			newVwAdjustDemandForecastAct.getOrganizationKey());
		Assert.assertEquals(existingVwAdjustDemandForecastAct.getSource(),
			newVwAdjustDemandForecastAct.getSource());
		Assert.assertEquals(existingVwAdjustDemandForecastAct.getMarketShareRatio(),
			newVwAdjustDemandForecastAct.getMarketShareRatio());
		Assert.assertEquals(existingVwAdjustDemandForecastAct.getBusinessUnitName(),
			newVwAdjustDemandForecastAct.getBusinessUnitName());
		AssertUtils.assertEquals(existingVwAdjustDemandForecastAct.getMarketShareUnits(),
			newVwAdjustDemandForecastAct.getMarketShareUnits());
		Assert.assertEquals(existingVwAdjustDemandForecastAct.getForecastMonth(),
			newVwAdjustDemandForecastAct.getForecastMonth());
		AssertUtils.assertEquals(existingVwAdjustDemandForecastAct.getInventoryUnitChange(),
			newVwAdjustDemandForecastAct.getInventoryUnitChange());
		Assert.assertEquals(existingVwAdjustDemandForecastAct.getUncapturedUnitsRatio(),
			newVwAdjustDemandForecastAct.getUncapturedUnitsRatio());
		Assert.assertEquals(existingVwAdjustDemandForecastAct.getCountry(),
			newVwAdjustDemandForecastAct.getCountry());
		Assert.assertEquals(existingVwAdjustDemandForecastAct.getForecastType(),
			newVwAdjustDemandForecastAct.getForecastType());
		AssertUtils.assertEquals(existingVwAdjustDemandForecastAct.getTotalDemandUnits(),
			newVwAdjustDemandForecastAct.getTotalDemandUnits());
		Assert.assertEquals(existingVwAdjustDemandForecastAct.getBrandId(),
			newVwAdjustDemandForecastAct.getBrandId());
		Assert.assertEquals(existingVwAdjustDemandForecastAct.getIsForecast(),
			newVwAdjustDemandForecastAct.getIsForecast());
		AssertUtils.assertEquals(existingVwAdjustDemandForecastAct.getTotalDemandAmount(),
			newVwAdjustDemandForecastAct.getTotalDemandAmount());
		AssertUtils.assertEquals(existingVwAdjustDemandForecastAct.getUncapturedUnits(),
			newVwAdjustDemandForecastAct.getUncapturedUnits());
		AssertUtils.assertEquals(existingVwAdjustDemandForecastAct.getGrossPrice(),
			newVwAdjustDemandForecastAct.getGrossPrice());
		AssertUtils.assertEquals(existingVwAdjustDemandForecastAct.getGrossAmount(),
			newVwAdjustDemandForecastAct.getGrossAmount());
		Assert.assertEquals(existingVwAdjustDemandForecastAct.getBatchId(),
			newVwAdjustDemandForecastAct.getBatchId());
		Assert.assertEquals(existingVwAdjustDemandForecastAct.getAdjustedDemandForecastId(),
			newVwAdjustDemandForecastAct.getAdjustedDemandForecastId());
		Assert.assertEquals(existingVwAdjustDemandForecastAct.getItemName(),
			newVwAdjustDemandForecastAct.getItemName());
		AssertUtils.assertEquals(existingVwAdjustDemandForecastAct.getNetSalesPrice(),
			newVwAdjustDemandForecastAct.getNetSalesPrice());
		AssertUtils.assertEquals(existingVwAdjustDemandForecastAct.getNetSalesAmount(),
			newVwAdjustDemandForecastAct.getNetSalesAmount());
		Assert.assertEquals(existingVwAdjustDemandForecastAct.getSegment(),
			newVwAdjustDemandForecastAct.getSegment());
		Assert.assertEquals(existingVwAdjustDemandForecastAct.getForecastName(),
			newVwAdjustDemandForecastAct.getForecastName());
		AssertUtils.assertEquals(existingVwAdjustDemandForecastAct.getMarketSizeUnits(),
			newVwAdjustDemandForecastAct.getMarketSizeUnits());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		VwAdjustDemandForecastAct newVwAdjustDemandForecastAct = addVwAdjustDemandForecastAct();

		VwAdjustDemandForecastAct existingVwAdjustDemandForecastAct = _persistence.findByPrimaryKey(newVwAdjustDemandForecastAct.getPrimaryKey());

		Assert.assertEquals(existingVwAdjustDemandForecastAct,
			newVwAdjustDemandForecastAct);
	}

	@Test(expected = NoSuchVwAdjustDemandForecastActException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<VwAdjustDemandForecastAct> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("VW_ADJUST_DEMAND_FORECAST_ACT",
			"forecastVer", true, "grossUnits", true, "businessUnitNo", true,
			"forecastYear", true, "brandName", true, "itemId", true,
			"organizationKey", true, "source", true, "marketShareRatio", true,
			"businessUnitName", true, "marketShareUnits", true,
			"forecastMonth", true, "inventoryUnitChange", true,
			"uncapturedUnitsRatio", true, "country", true, "forecastType",
			true, "totalDemandUnits", true, "brandId", true, "isForecast",
			true, "totalDemandAmount", true, "uncapturedUnits", true,
			"grossPrice", true, "grossAmount", true, "batchId", true,
			"adjustedDemandForecastId", true, "itemName", true,
			"netSalesPrice", true, "netSalesAmount", true, "segment", true,
			"forecastName", true, "marketSizeUnits", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		VwAdjustDemandForecastAct newVwAdjustDemandForecastAct = addVwAdjustDemandForecastAct();

		VwAdjustDemandForecastAct existingVwAdjustDemandForecastAct = _persistence.fetchByPrimaryKey(newVwAdjustDemandForecastAct.getPrimaryKey());

		Assert.assertEquals(existingVwAdjustDemandForecastAct,
			newVwAdjustDemandForecastAct);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwAdjustDemandForecastAct missingVwAdjustDemandForecastAct = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingVwAdjustDemandForecastAct);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		VwAdjustDemandForecastAct newVwAdjustDemandForecastAct1 = addVwAdjustDemandForecastAct();
		VwAdjustDemandForecastAct newVwAdjustDemandForecastAct2 = addVwAdjustDemandForecastAct();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwAdjustDemandForecastAct1.getPrimaryKey());
		primaryKeys.add(newVwAdjustDemandForecastAct2.getPrimaryKey());

		Map<Serializable, VwAdjustDemandForecastAct> vwAdjustDemandForecastActs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, vwAdjustDemandForecastActs.size());
		Assert.assertEquals(newVwAdjustDemandForecastAct1,
			vwAdjustDemandForecastActs.get(
				newVwAdjustDemandForecastAct1.getPrimaryKey()));
		Assert.assertEquals(newVwAdjustDemandForecastAct2,
			vwAdjustDemandForecastActs.get(
				newVwAdjustDemandForecastAct2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, VwAdjustDemandForecastAct> vwAdjustDemandForecastActs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(vwAdjustDemandForecastActs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		VwAdjustDemandForecastAct newVwAdjustDemandForecastAct = addVwAdjustDemandForecastAct();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwAdjustDemandForecastAct.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, VwAdjustDemandForecastAct> vwAdjustDemandForecastActs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, vwAdjustDemandForecastActs.size());
		Assert.assertEquals(newVwAdjustDemandForecastAct,
			vwAdjustDemandForecastActs.get(
				newVwAdjustDemandForecastAct.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, VwAdjustDemandForecastAct> vwAdjustDemandForecastActs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(vwAdjustDemandForecastActs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		VwAdjustDemandForecastAct newVwAdjustDemandForecastAct = addVwAdjustDemandForecastAct();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwAdjustDemandForecastAct.getPrimaryKey());

		Map<Serializable, VwAdjustDemandForecastAct> vwAdjustDemandForecastActs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, vwAdjustDemandForecastActs.size());
		Assert.assertEquals(newVwAdjustDemandForecastAct,
			vwAdjustDemandForecastActs.get(
				newVwAdjustDemandForecastAct.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = VwAdjustDemandForecastActLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<VwAdjustDemandForecastAct>() {
				@Override
				public void performAction(
					VwAdjustDemandForecastAct vwAdjustDemandForecastAct) {
					Assert.assertNotNull(vwAdjustDemandForecastAct);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		VwAdjustDemandForecastAct newVwAdjustDemandForecastAct = addVwAdjustDemandForecastAct();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwAdjustDemandForecastAct.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"adjustedDemandForecastId",
				newVwAdjustDemandForecastAct.getAdjustedDemandForecastId()));

		List<VwAdjustDemandForecastAct> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		VwAdjustDemandForecastAct existingVwAdjustDemandForecastAct = result.get(0);

		Assert.assertEquals(existingVwAdjustDemandForecastAct,
			newVwAdjustDemandForecastAct);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwAdjustDemandForecastAct.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"adjustedDemandForecastId", RandomTestUtil.nextInt()));

		List<VwAdjustDemandForecastAct> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		VwAdjustDemandForecastAct newVwAdjustDemandForecastAct = addVwAdjustDemandForecastAct();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwAdjustDemandForecastAct.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"adjustedDemandForecastId"));

		Object newAdjustedDemandForecastId = newVwAdjustDemandForecastAct.getAdjustedDemandForecastId();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"adjustedDemandForecastId",
				new Object[] { newAdjustedDemandForecastId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingAdjustedDemandForecastId = result.get(0);

		Assert.assertEquals(existingAdjustedDemandForecastId,
			newAdjustedDemandForecastId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwAdjustDemandForecastAct.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"adjustedDemandForecastId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"adjustedDemandForecastId",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected VwAdjustDemandForecastAct addVwAdjustDemandForecastAct()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwAdjustDemandForecastAct vwAdjustDemandForecastAct = _persistence.create(pk);

		vwAdjustDemandForecastAct.setForecastVer(RandomTestUtil.randomString());

		vwAdjustDemandForecastAct.setGrossUnits(RandomTestUtil.nextDouble());

		vwAdjustDemandForecastAct.setBusinessUnitNo(RandomTestUtil.randomString());

		vwAdjustDemandForecastAct.setForecastYear(RandomTestUtil.randomString());

		vwAdjustDemandForecastAct.setBrandName(RandomTestUtil.randomString());

		vwAdjustDemandForecastAct.setItemId(RandomTestUtil.randomString());

		vwAdjustDemandForecastAct.setOrganizationKey(RandomTestUtil.randomString());

		vwAdjustDemandForecastAct.setSource(RandomTestUtil.randomString());

		vwAdjustDemandForecastAct.setMarketShareRatio(RandomTestUtil.nextInt());

		vwAdjustDemandForecastAct.setBusinessUnitName(RandomTestUtil.randomString());

		vwAdjustDemandForecastAct.setMarketShareUnits(RandomTestUtil.nextDouble());

		vwAdjustDemandForecastAct.setForecastMonth(RandomTestUtil.randomString());

		vwAdjustDemandForecastAct.setInventoryUnitChange(RandomTestUtil.nextDouble());

		vwAdjustDemandForecastAct.setUncapturedUnitsRatio(RandomTestUtil.randomString());

		vwAdjustDemandForecastAct.setCountry(RandomTestUtil.randomString());

		vwAdjustDemandForecastAct.setForecastType(RandomTestUtil.randomString());

		vwAdjustDemandForecastAct.setTotalDemandUnits(RandomTestUtil.nextDouble());

		vwAdjustDemandForecastAct.setBrandId(RandomTestUtil.randomString());

		vwAdjustDemandForecastAct.setIsForecast(RandomTestUtil.randomString());

		vwAdjustDemandForecastAct.setTotalDemandAmount(RandomTestUtil.nextDouble());

		vwAdjustDemandForecastAct.setUncapturedUnits(RandomTestUtil.nextDouble());

		vwAdjustDemandForecastAct.setGrossPrice(RandomTestUtil.nextDouble());

		vwAdjustDemandForecastAct.setGrossAmount(RandomTestUtil.nextDouble());

		vwAdjustDemandForecastAct.setBatchId(RandomTestUtil.randomString());

		vwAdjustDemandForecastAct.setItemName(RandomTestUtil.randomString());

		vwAdjustDemandForecastAct.setNetSalesPrice(RandomTestUtil.nextDouble());

		vwAdjustDemandForecastAct.setNetSalesAmount(RandomTestUtil.nextDouble());

		vwAdjustDemandForecastAct.setSegment(RandomTestUtil.randomString());

		vwAdjustDemandForecastAct.setForecastName(RandomTestUtil.randomString());

		vwAdjustDemandForecastAct.setMarketSizeUnits(RandomTestUtil.nextDouble());

		_vwAdjustDemandForecastActs.add(_persistence.update(
				vwAdjustDemandForecastAct));

		return vwAdjustDemandForecastAct;
	}

	private List<VwAdjustDemandForecastAct> _vwAdjustDemandForecastActs = new ArrayList<VwAdjustDemandForecastAct>();
	private VwAdjustDemandForecastActPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}