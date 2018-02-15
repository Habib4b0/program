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
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchVwIvldDemandForecastActualException;
import com.stpl.app.model.VwIvldDemandForecastActual;
import com.stpl.app.service.VwIvldDemandForecastActualLocalServiceUtil;
import com.stpl.app.service.persistence.VwIvldDemandForecastActualPersistence;
import com.stpl.app.service.persistence.VwIvldDemandForecastActualUtil;

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
public class VwIvldDemandForecastActualPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = VwIvldDemandForecastActualUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<VwIvldDemandForecastActual> iterator = _vwIvldDemandForecastActuals.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwIvldDemandForecastActual vwIvldDemandForecastActual = _persistence.create(pk);

		Assert.assertNotNull(vwIvldDemandForecastActual);

		Assert.assertEquals(vwIvldDemandForecastActual.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		VwIvldDemandForecastActual newVwIvldDemandForecastActual = addVwIvldDemandForecastActual();

		_persistence.remove(newVwIvldDemandForecastActual);

		VwIvldDemandForecastActual existingVwIvldDemandForecastActual = _persistence.fetchByPrimaryKey(newVwIvldDemandForecastActual.getPrimaryKey());

		Assert.assertNull(existingVwIvldDemandForecastActual);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addVwIvldDemandForecastActual();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwIvldDemandForecastActual newVwIvldDemandForecastActual = _persistence.create(pk);

		newVwIvldDemandForecastActual.setDemandIntSid(RandomTestUtil.randomString());

		newVwIvldDemandForecastActual.setForecastYear(RandomTestUtil.randomString());

		newVwIvldDemandForecastActual.setGrossUnits(RandomTestUtil.randomString());

		newVwIvldDemandForecastActual.setBusinessUnitNo(RandomTestUtil.randomString());

		newVwIvldDemandForecastActual.setTotalDemandUnits(RandomTestUtil.randomString());

		newVwIvldDemandForecastActual.setBrandName(RandomTestUtil.randomString());

		newVwIvldDemandForecastActual.setItemId(RandomTestUtil.randomString());

		newVwIvldDemandForecastActual.setOrganizationKey(RandomTestUtil.randomString());

		newVwIvldDemandForecastActual.setSource(RandomTestUtil.randomString());

		newVwIvldDemandForecastActual.setMarketShareRatio(RandomTestUtil.randomString());

		newVwIvldDemandForecastActual.setBusinessUnitName(RandomTestUtil.randomString());

		newVwIvldDemandForecastActual.setAddChgDelIndicator(RandomTestUtil.randomString());

		newVwIvldDemandForecastActual.setErrorCode(RandomTestUtil.randomString());

		newVwIvldDemandForecastActual.setMarketShareUnits(RandomTestUtil.randomString());

		newVwIvldDemandForecastActual.setInventoryUnitChange(RandomTestUtil.randomString());

		newVwIvldDemandForecastActual.setReprocessedFlag(RandomTestUtil.randomString());

		newVwIvldDemandForecastActual.setUncapturedUnitsRatio(RandomTestUtil.randomString());

		newVwIvldDemandForecastActual.setReasonForFailure(RandomTestUtil.randomString());

		newVwIvldDemandForecastActual.setCountry(RandomTestUtil.randomString());

		newVwIvldDemandForecastActual.setForecastType(RandomTestUtil.randomString());

		newVwIvldDemandForecastActual.setBrandId(RandomTestUtil.randomString());

		newVwIvldDemandForecastActual.setIsForecast(RandomTestUtil.randomString());

		newVwIvldDemandForecastActual.setUncapturedUnits(RandomTestUtil.randomString());

		newVwIvldDemandForecastActual.setGrossPrice(RandomTestUtil.randomString());

		newVwIvldDemandForecastActual.setIsActive(RandomTestUtil.randomString());

		newVwIvldDemandForecastActual.setGrossAmount(RandomTestUtil.randomString());

		newVwIvldDemandForecastActual.setBatchId(RandomTestUtil.randomString());

		newVwIvldDemandForecastActual.setForecastVer(RandomTestUtil.randomString());

		newVwIvldDemandForecastActual.setItemName(RandomTestUtil.randomString());

		newVwIvldDemandForecastActual.setForecastMonth(RandomTestUtil.randomString());

		newVwIvldDemandForecastActual.setErrorField(RandomTestUtil.randomString());

		newVwIvldDemandForecastActual.setNetSalesPrice(RandomTestUtil.randomString());

		newVwIvldDemandForecastActual.setNetSalesAmount(RandomTestUtil.randomString());

		newVwIvldDemandForecastActual.setSegment(RandomTestUtil.randomString());

		newVwIvldDemandForecastActual.setTotalDemandAmount(RandomTestUtil.randomString());

		newVwIvldDemandForecastActual.setForecastName(RandomTestUtil.randomString());

		newVwIvldDemandForecastActual.setMarketSizeUnits(RandomTestUtil.randomString());

		newVwIvldDemandForecastActual.setCheckRecord(RandomTestUtil.randomBoolean());

		_vwIvldDemandForecastActuals.add(_persistence.update(
				newVwIvldDemandForecastActual));

		VwIvldDemandForecastActual existingVwIvldDemandForecastActual = _persistence.findByPrimaryKey(newVwIvldDemandForecastActual.getPrimaryKey());

		Assert.assertEquals(existingVwIvldDemandForecastActual.getDemandIntSid(),
			newVwIvldDemandForecastActual.getDemandIntSid());
		Assert.assertEquals(existingVwIvldDemandForecastActual.getForecastYear(),
			newVwIvldDemandForecastActual.getForecastYear());
		Assert.assertEquals(existingVwIvldDemandForecastActual.getGrossUnits(),
			newVwIvldDemandForecastActual.getGrossUnits());
		Assert.assertEquals(existingVwIvldDemandForecastActual.getBusinessUnitNo(),
			newVwIvldDemandForecastActual.getBusinessUnitNo());
		Assert.assertEquals(existingVwIvldDemandForecastActual.getTotalDemandUnits(),
			newVwIvldDemandForecastActual.getTotalDemandUnits());
		Assert.assertEquals(existingVwIvldDemandForecastActual.getBrandName(),
			newVwIvldDemandForecastActual.getBrandName());
		Assert.assertEquals(existingVwIvldDemandForecastActual.getItemId(),
			newVwIvldDemandForecastActual.getItemId());
		Assert.assertEquals(existingVwIvldDemandForecastActual.getOrganizationKey(),
			newVwIvldDemandForecastActual.getOrganizationKey());
		Assert.assertEquals(existingVwIvldDemandForecastActual.getSource(),
			newVwIvldDemandForecastActual.getSource());
		Assert.assertEquals(existingVwIvldDemandForecastActual.getMarketShareRatio(),
			newVwIvldDemandForecastActual.getMarketShareRatio());
		Assert.assertEquals(existingVwIvldDemandForecastActual.getIvldDemandActualForecastSid(),
			newVwIvldDemandForecastActual.getIvldDemandActualForecastSid());
		Assert.assertEquals(existingVwIvldDemandForecastActual.getBusinessUnitName(),
			newVwIvldDemandForecastActual.getBusinessUnitName());
		Assert.assertEquals(existingVwIvldDemandForecastActual.getAddChgDelIndicator(),
			newVwIvldDemandForecastActual.getAddChgDelIndicator());
		Assert.assertEquals(existingVwIvldDemandForecastActual.getErrorCode(),
			newVwIvldDemandForecastActual.getErrorCode());
		Assert.assertEquals(existingVwIvldDemandForecastActual.getMarketShareUnits(),
			newVwIvldDemandForecastActual.getMarketShareUnits());
		Assert.assertEquals(existingVwIvldDemandForecastActual.getInventoryUnitChange(),
			newVwIvldDemandForecastActual.getInventoryUnitChange());
		Assert.assertEquals(existingVwIvldDemandForecastActual.getReprocessedFlag(),
			newVwIvldDemandForecastActual.getReprocessedFlag());
		Assert.assertEquals(existingVwIvldDemandForecastActual.getUncapturedUnitsRatio(),
			newVwIvldDemandForecastActual.getUncapturedUnitsRatio());
		Assert.assertEquals(existingVwIvldDemandForecastActual.getReasonForFailure(),
			newVwIvldDemandForecastActual.getReasonForFailure());
		Assert.assertEquals(existingVwIvldDemandForecastActual.getCountry(),
			newVwIvldDemandForecastActual.getCountry());
		Assert.assertEquals(existingVwIvldDemandForecastActual.getForecastType(),
			newVwIvldDemandForecastActual.getForecastType());
		Assert.assertEquals(existingVwIvldDemandForecastActual.getBrandId(),
			newVwIvldDemandForecastActual.getBrandId());
		Assert.assertEquals(existingVwIvldDemandForecastActual.getIsForecast(),
			newVwIvldDemandForecastActual.getIsForecast());
		Assert.assertEquals(existingVwIvldDemandForecastActual.getUncapturedUnits(),
			newVwIvldDemandForecastActual.getUncapturedUnits());
		Assert.assertEquals(existingVwIvldDemandForecastActual.getGrossPrice(),
			newVwIvldDemandForecastActual.getGrossPrice());
		Assert.assertEquals(existingVwIvldDemandForecastActual.getIsActive(),
			newVwIvldDemandForecastActual.getIsActive());
		Assert.assertEquals(existingVwIvldDemandForecastActual.getGrossAmount(),
			newVwIvldDemandForecastActual.getGrossAmount());
		Assert.assertEquals(existingVwIvldDemandForecastActual.getBatchId(),
			newVwIvldDemandForecastActual.getBatchId());
		Assert.assertEquals(existingVwIvldDemandForecastActual.getForecastVer(),
			newVwIvldDemandForecastActual.getForecastVer());
		Assert.assertEquals(existingVwIvldDemandForecastActual.getItemName(),
			newVwIvldDemandForecastActual.getItemName());
		Assert.assertEquals(existingVwIvldDemandForecastActual.getForecastMonth(),
			newVwIvldDemandForecastActual.getForecastMonth());
		Assert.assertEquals(existingVwIvldDemandForecastActual.getErrorField(),
			newVwIvldDemandForecastActual.getErrorField());
		Assert.assertEquals(existingVwIvldDemandForecastActual.getNetSalesPrice(),
			newVwIvldDemandForecastActual.getNetSalesPrice());
		Assert.assertEquals(existingVwIvldDemandForecastActual.getNetSalesAmount(),
			newVwIvldDemandForecastActual.getNetSalesAmount());
		Assert.assertEquals(existingVwIvldDemandForecastActual.getSegment(),
			newVwIvldDemandForecastActual.getSegment());
		Assert.assertEquals(existingVwIvldDemandForecastActual.getTotalDemandAmount(),
			newVwIvldDemandForecastActual.getTotalDemandAmount());
		Assert.assertEquals(existingVwIvldDemandForecastActual.getForecastName(),
			newVwIvldDemandForecastActual.getForecastName());
		Assert.assertEquals(existingVwIvldDemandForecastActual.getMarketSizeUnits(),
			newVwIvldDemandForecastActual.getMarketSizeUnits());
		Assert.assertEquals(existingVwIvldDemandForecastActual.getCheckRecord(),
			newVwIvldDemandForecastActual.getCheckRecord());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		VwIvldDemandForecastActual newVwIvldDemandForecastActual = addVwIvldDemandForecastActual();

		VwIvldDemandForecastActual existingVwIvldDemandForecastActual = _persistence.findByPrimaryKey(newVwIvldDemandForecastActual.getPrimaryKey());

		Assert.assertEquals(existingVwIvldDemandForecastActual,
			newVwIvldDemandForecastActual);
	}

	@Test(expected = NoSuchVwIvldDemandForecastActualException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<VwIvldDemandForecastActual> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("VW_IVLD_DEMAND_FORECAST_ACTUAL",
			"demandIntSid", true, "forecastYear", true, "grossUnits", true,
			"businessUnitNo", true, "totalDemandUnits", true, "brandName",
			true, "itemId", true, "organizationKey", true, "source", true,
			"marketShareRatio", true, "ivldDemandActualForecastSid", true,
			"businessUnitName", true, "addChgDelIndicator", true, "errorCode",
			true, "marketShareUnits", true, "inventoryUnitChange", true,
			"reprocessedFlag", true, "uncapturedUnitsRatio", true,
			"reasonForFailure", true, "country", true, "forecastType", true,
			"brandId", true, "isForecast", true, "uncapturedUnits", true,
			"grossPrice", true, "isActive", true, "grossAmount", true,
			"batchId", true, "forecastVer", true, "itemName", true,
			"forecastMonth", true, "errorField", true, "netSalesPrice", true,
			"netSalesAmount", true, "segment", true, "totalDemandAmount", true,
			"forecastName", true, "marketSizeUnits", true, "checkRecord", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		VwIvldDemandForecastActual newVwIvldDemandForecastActual = addVwIvldDemandForecastActual();

		VwIvldDemandForecastActual existingVwIvldDemandForecastActual = _persistence.fetchByPrimaryKey(newVwIvldDemandForecastActual.getPrimaryKey());

		Assert.assertEquals(existingVwIvldDemandForecastActual,
			newVwIvldDemandForecastActual);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwIvldDemandForecastActual missingVwIvldDemandForecastActual = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingVwIvldDemandForecastActual);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		VwIvldDemandForecastActual newVwIvldDemandForecastActual1 = addVwIvldDemandForecastActual();
		VwIvldDemandForecastActual newVwIvldDemandForecastActual2 = addVwIvldDemandForecastActual();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwIvldDemandForecastActual1.getPrimaryKey());
		primaryKeys.add(newVwIvldDemandForecastActual2.getPrimaryKey());

		Map<Serializable, VwIvldDemandForecastActual> vwIvldDemandForecastActuals =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, vwIvldDemandForecastActuals.size());
		Assert.assertEquals(newVwIvldDemandForecastActual1,
			vwIvldDemandForecastActuals.get(
				newVwIvldDemandForecastActual1.getPrimaryKey()));
		Assert.assertEquals(newVwIvldDemandForecastActual2,
			vwIvldDemandForecastActuals.get(
				newVwIvldDemandForecastActual2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, VwIvldDemandForecastActual> vwIvldDemandForecastActuals =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(vwIvldDemandForecastActuals.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		VwIvldDemandForecastActual newVwIvldDemandForecastActual = addVwIvldDemandForecastActual();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwIvldDemandForecastActual.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, VwIvldDemandForecastActual> vwIvldDemandForecastActuals =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, vwIvldDemandForecastActuals.size());
		Assert.assertEquals(newVwIvldDemandForecastActual,
			vwIvldDemandForecastActuals.get(
				newVwIvldDemandForecastActual.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, VwIvldDemandForecastActual> vwIvldDemandForecastActuals =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(vwIvldDemandForecastActuals.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		VwIvldDemandForecastActual newVwIvldDemandForecastActual = addVwIvldDemandForecastActual();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwIvldDemandForecastActual.getPrimaryKey());

		Map<Serializable, VwIvldDemandForecastActual> vwIvldDemandForecastActuals =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, vwIvldDemandForecastActuals.size());
		Assert.assertEquals(newVwIvldDemandForecastActual,
			vwIvldDemandForecastActuals.get(
				newVwIvldDemandForecastActual.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = VwIvldDemandForecastActualLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<VwIvldDemandForecastActual>() {
				@Override
				public void performAction(
					VwIvldDemandForecastActual vwIvldDemandForecastActual) {
					Assert.assertNotNull(vwIvldDemandForecastActual);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		VwIvldDemandForecastActual newVwIvldDemandForecastActual = addVwIvldDemandForecastActual();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwIvldDemandForecastActual.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"ivldDemandActualForecastSid",
				newVwIvldDemandForecastActual.getIvldDemandActualForecastSid()));

		List<VwIvldDemandForecastActual> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		VwIvldDemandForecastActual existingVwIvldDemandForecastActual = result.get(0);

		Assert.assertEquals(existingVwIvldDemandForecastActual,
			newVwIvldDemandForecastActual);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwIvldDemandForecastActual.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"ivldDemandActualForecastSid", RandomTestUtil.nextInt()));

		List<VwIvldDemandForecastActual> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		VwIvldDemandForecastActual newVwIvldDemandForecastActual = addVwIvldDemandForecastActual();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwIvldDemandForecastActual.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldDemandActualForecastSid"));

		Object newIvldDemandActualForecastSid = newVwIvldDemandForecastActual.getIvldDemandActualForecastSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"ivldDemandActualForecastSid",
				new Object[] { newIvldDemandActualForecastSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingIvldDemandActualForecastSid = result.get(0);

		Assert.assertEquals(existingIvldDemandActualForecastSid,
			newIvldDemandActualForecastSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwIvldDemandForecastActual.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldDemandActualForecastSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"ivldDemandActualForecastSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected VwIvldDemandForecastActual addVwIvldDemandForecastActual()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwIvldDemandForecastActual vwIvldDemandForecastActual = _persistence.create(pk);

		vwIvldDemandForecastActual.setDemandIntSid(RandomTestUtil.randomString());

		vwIvldDemandForecastActual.setForecastYear(RandomTestUtil.randomString());

		vwIvldDemandForecastActual.setGrossUnits(RandomTestUtil.randomString());

		vwIvldDemandForecastActual.setBusinessUnitNo(RandomTestUtil.randomString());

		vwIvldDemandForecastActual.setTotalDemandUnits(RandomTestUtil.randomString());

		vwIvldDemandForecastActual.setBrandName(RandomTestUtil.randomString());

		vwIvldDemandForecastActual.setItemId(RandomTestUtil.randomString());

		vwIvldDemandForecastActual.setOrganizationKey(RandomTestUtil.randomString());

		vwIvldDemandForecastActual.setSource(RandomTestUtil.randomString());

		vwIvldDemandForecastActual.setMarketShareRatio(RandomTestUtil.randomString());

		vwIvldDemandForecastActual.setBusinessUnitName(RandomTestUtil.randomString());

		vwIvldDemandForecastActual.setAddChgDelIndicator(RandomTestUtil.randomString());

		vwIvldDemandForecastActual.setErrorCode(RandomTestUtil.randomString());

		vwIvldDemandForecastActual.setMarketShareUnits(RandomTestUtil.randomString());

		vwIvldDemandForecastActual.setInventoryUnitChange(RandomTestUtil.randomString());

		vwIvldDemandForecastActual.setReprocessedFlag(RandomTestUtil.randomString());

		vwIvldDemandForecastActual.setUncapturedUnitsRatio(RandomTestUtil.randomString());

		vwIvldDemandForecastActual.setReasonForFailure(RandomTestUtil.randomString());

		vwIvldDemandForecastActual.setCountry(RandomTestUtil.randomString());

		vwIvldDemandForecastActual.setForecastType(RandomTestUtil.randomString());

		vwIvldDemandForecastActual.setBrandId(RandomTestUtil.randomString());

		vwIvldDemandForecastActual.setIsForecast(RandomTestUtil.randomString());

		vwIvldDemandForecastActual.setUncapturedUnits(RandomTestUtil.randomString());

		vwIvldDemandForecastActual.setGrossPrice(RandomTestUtil.randomString());

		vwIvldDemandForecastActual.setIsActive(RandomTestUtil.randomString());

		vwIvldDemandForecastActual.setGrossAmount(RandomTestUtil.randomString());

		vwIvldDemandForecastActual.setBatchId(RandomTestUtil.randomString());

		vwIvldDemandForecastActual.setForecastVer(RandomTestUtil.randomString());

		vwIvldDemandForecastActual.setItemName(RandomTestUtil.randomString());

		vwIvldDemandForecastActual.setForecastMonth(RandomTestUtil.randomString());

		vwIvldDemandForecastActual.setErrorField(RandomTestUtil.randomString());

		vwIvldDemandForecastActual.setNetSalesPrice(RandomTestUtil.randomString());

		vwIvldDemandForecastActual.setNetSalesAmount(RandomTestUtil.randomString());

		vwIvldDemandForecastActual.setSegment(RandomTestUtil.randomString());

		vwIvldDemandForecastActual.setTotalDemandAmount(RandomTestUtil.randomString());

		vwIvldDemandForecastActual.setForecastName(RandomTestUtil.randomString());

		vwIvldDemandForecastActual.setMarketSizeUnits(RandomTestUtil.randomString());

		vwIvldDemandForecastActual.setCheckRecord(RandomTestUtil.randomBoolean());

		_vwIvldDemandForecastActuals.add(_persistence.update(
				vwIvldDemandForecastActual));

		return vwIvldDemandForecastActual;
	}

	private List<VwIvldDemandForecastActual> _vwIvldDemandForecastActuals = new ArrayList<VwIvldDemandForecastActual>();
	private VwIvldDemandForecastActualPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}