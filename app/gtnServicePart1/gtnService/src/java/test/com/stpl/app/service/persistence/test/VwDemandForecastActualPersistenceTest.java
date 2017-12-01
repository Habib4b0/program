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
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchVwDemandForecastActualException;
import com.stpl.app.model.VwDemandForecastActual;
import com.stpl.app.service.VwDemandForecastActualLocalServiceUtil;
import com.stpl.app.service.persistence.VwDemandForecastActualPersistence;
import com.stpl.app.service.persistence.VwDemandForecastActualUtil;

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
public class VwDemandForecastActualPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = VwDemandForecastActualUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<VwDemandForecastActual> iterator = _vwDemandForecastActuals.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwDemandForecastActual vwDemandForecastActual = _persistence.create(pk);

		Assert.assertNotNull(vwDemandForecastActual);

		Assert.assertEquals(vwDemandForecastActual.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		VwDemandForecastActual newVwDemandForecastActual = addVwDemandForecastActual();

		_persistence.remove(newVwDemandForecastActual);

		VwDemandForecastActual existingVwDemandForecastActual = _persistence.fetchByPrimaryKey(newVwDemandForecastActual.getPrimaryKey());

		Assert.assertNull(existingVwDemandForecastActual);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addVwDemandForecastActual();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwDemandForecastActual newVwDemandForecastActual = _persistence.create(pk);

		newVwDemandForecastActual.setForecastYear(RandomTestUtil.randomString());

		newVwDemandForecastActual.setGrossUnits(RandomTestUtil.nextDouble());

		newVwDemandForecastActual.setBusinessUnitNo(RandomTestUtil.randomString());

		newVwDemandForecastActual.setTotalDemandUnits(RandomTestUtil.nextDouble());

		newVwDemandForecastActual.setBrandName(RandomTestUtil.randomString());

		newVwDemandForecastActual.setItemId(RandomTestUtil.randomString());

		newVwDemandForecastActual.setOrganizationKey(RandomTestUtil.randomString());

		newVwDemandForecastActual.setSource(RandomTestUtil.randomString());

		newVwDemandForecastActual.setMarketShareRatio(RandomTestUtil.randomString());

		newVwDemandForecastActual.setBusinessUnitName(RandomTestUtil.randomString());

		newVwDemandForecastActual.setMarketShareUnits(RandomTestUtil.nextDouble());

		newVwDemandForecastActual.setInventoryUnitChange(RandomTestUtil.nextDouble());

		newVwDemandForecastActual.setUncapturedUnitsRatio(RandomTestUtil.randomString());

		newVwDemandForecastActual.setCountry(RandomTestUtil.randomString());

		newVwDemandForecastActual.setForecastType(RandomTestUtil.randomString());

		newVwDemandForecastActual.setBrandId(RandomTestUtil.randomString());

		newVwDemandForecastActual.setIsForecast(RandomTestUtil.randomString());

		newVwDemandForecastActual.setUncapturedUnits(RandomTestUtil.nextDouble());

		newVwDemandForecastActual.setGrossPrice(RandomTestUtil.nextDouble());

		newVwDemandForecastActual.setIsActive(RandomTestUtil.randomString());

		newVwDemandForecastActual.setGrossAmount(RandomTestUtil.nextDouble());

		newVwDemandForecastActual.setBatchId(RandomTestUtil.randomString());

		newVwDemandForecastActual.setForecastVer(RandomTestUtil.randomString());

		newVwDemandForecastActual.setItemName(RandomTestUtil.randomString());

		newVwDemandForecastActual.setForecastMonth(RandomTestUtil.randomString());

		newVwDemandForecastActual.setNetSalesPrice(RandomTestUtil.nextDouble());

		newVwDemandForecastActual.setNetSalesAmount(RandomTestUtil.nextDouble());

		newVwDemandForecastActual.setSegment(RandomTestUtil.randomString());

		newVwDemandForecastActual.setTotalDemandAmount(RandomTestUtil.nextDouble());

		newVwDemandForecastActual.setForecastName(RandomTestUtil.randomString());

		newVwDemandForecastActual.setMarketSizeUnits(RandomTestUtil.nextDouble());

		newVwDemandForecastActual.setDemandId(RandomTestUtil.nextInt());

		_vwDemandForecastActuals.add(_persistence.update(
				newVwDemandForecastActual));

		VwDemandForecastActual existingVwDemandForecastActual = _persistence.findByPrimaryKey(newVwDemandForecastActual.getPrimaryKey());

		Assert.assertEquals(existingVwDemandForecastActual.getForecastYear(),
			newVwDemandForecastActual.getForecastYear());
		AssertUtils.assertEquals(existingVwDemandForecastActual.getGrossUnits(),
			newVwDemandForecastActual.getGrossUnits());
		Assert.assertEquals(existingVwDemandForecastActual.getBusinessUnitNo(),
			newVwDemandForecastActual.getBusinessUnitNo());
		AssertUtils.assertEquals(existingVwDemandForecastActual.getTotalDemandUnits(),
			newVwDemandForecastActual.getTotalDemandUnits());
		Assert.assertEquals(existingVwDemandForecastActual.getBrandName(),
			newVwDemandForecastActual.getBrandName());
		Assert.assertEquals(existingVwDemandForecastActual.getItemId(),
			newVwDemandForecastActual.getItemId());
		Assert.assertEquals(existingVwDemandForecastActual.getOrganizationKey(),
			newVwDemandForecastActual.getOrganizationKey());
		Assert.assertEquals(existingVwDemandForecastActual.getSource(),
			newVwDemandForecastActual.getSource());
		Assert.assertEquals(existingVwDemandForecastActual.getMarketShareRatio(),
			newVwDemandForecastActual.getMarketShareRatio());
		Assert.assertEquals(existingVwDemandForecastActual.getBusinessUnitName(),
			newVwDemandForecastActual.getBusinessUnitName());
		Assert.assertEquals(existingVwDemandForecastActual.getDemandForecastActualSid(),
			newVwDemandForecastActual.getDemandForecastActualSid());
		AssertUtils.assertEquals(existingVwDemandForecastActual.getMarketShareUnits(),
			newVwDemandForecastActual.getMarketShareUnits());
		AssertUtils.assertEquals(existingVwDemandForecastActual.getInventoryUnitChange(),
			newVwDemandForecastActual.getInventoryUnitChange());
		Assert.assertEquals(existingVwDemandForecastActual.getUncapturedUnitsRatio(),
			newVwDemandForecastActual.getUncapturedUnitsRatio());
		Assert.assertEquals(existingVwDemandForecastActual.getCountry(),
			newVwDemandForecastActual.getCountry());
		Assert.assertEquals(existingVwDemandForecastActual.getForecastType(),
			newVwDemandForecastActual.getForecastType());
		Assert.assertEquals(existingVwDemandForecastActual.getBrandId(),
			newVwDemandForecastActual.getBrandId());
		Assert.assertEquals(existingVwDemandForecastActual.getIsForecast(),
			newVwDemandForecastActual.getIsForecast());
		AssertUtils.assertEquals(existingVwDemandForecastActual.getUncapturedUnits(),
			newVwDemandForecastActual.getUncapturedUnits());
		AssertUtils.assertEquals(existingVwDemandForecastActual.getGrossPrice(),
			newVwDemandForecastActual.getGrossPrice());
		Assert.assertEquals(existingVwDemandForecastActual.getIsActive(),
			newVwDemandForecastActual.getIsActive());
		AssertUtils.assertEquals(existingVwDemandForecastActual.getGrossAmount(),
			newVwDemandForecastActual.getGrossAmount());
		Assert.assertEquals(existingVwDemandForecastActual.getBatchId(),
			newVwDemandForecastActual.getBatchId());
		Assert.assertEquals(existingVwDemandForecastActual.getForecastVer(),
			newVwDemandForecastActual.getForecastVer());
		Assert.assertEquals(existingVwDemandForecastActual.getItemName(),
			newVwDemandForecastActual.getItemName());
		Assert.assertEquals(existingVwDemandForecastActual.getForecastMonth(),
			newVwDemandForecastActual.getForecastMonth());
		AssertUtils.assertEquals(existingVwDemandForecastActual.getNetSalesPrice(),
			newVwDemandForecastActual.getNetSalesPrice());
		AssertUtils.assertEquals(existingVwDemandForecastActual.getNetSalesAmount(),
			newVwDemandForecastActual.getNetSalesAmount());
		Assert.assertEquals(existingVwDemandForecastActual.getSegment(),
			newVwDemandForecastActual.getSegment());
		AssertUtils.assertEquals(existingVwDemandForecastActual.getTotalDemandAmount(),
			newVwDemandForecastActual.getTotalDemandAmount());
		Assert.assertEquals(existingVwDemandForecastActual.getForecastName(),
			newVwDemandForecastActual.getForecastName());
		AssertUtils.assertEquals(existingVwDemandForecastActual.getMarketSizeUnits(),
			newVwDemandForecastActual.getMarketSizeUnits());
		Assert.assertEquals(existingVwDemandForecastActual.getDemandId(),
			newVwDemandForecastActual.getDemandId());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		VwDemandForecastActual newVwDemandForecastActual = addVwDemandForecastActual();

		VwDemandForecastActual existingVwDemandForecastActual = _persistence.findByPrimaryKey(newVwDemandForecastActual.getPrimaryKey());

		Assert.assertEquals(existingVwDemandForecastActual,
			newVwDemandForecastActual);
	}

	@Test(expected = NoSuchVwDemandForecastActualException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<VwDemandForecastActual> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("vw_DEMAND_FORECAST_ACTUAL",
			"forecastYear", true, "grossUnits", true, "businessUnitNo", true,
			"totalDemandUnits", true, "brandName", true, "itemId", true,
			"organizationKey", true, "source", true, "marketShareRatio", true,
			"businessUnitName", true, "demandForecastActualSid", true,
			"marketShareUnits", true, "inventoryUnitChange", true,
			"uncapturedUnitsRatio", true, "country", true, "forecastType",
			true, "brandId", true, "isForecast", true, "uncapturedUnits", true,
			"grossPrice", true, "isActive", true, "grossAmount", true,
			"batchId", true, "forecastVer", true, "itemName", true,
			"forecastMonth", true, "netSalesPrice", true, "netSalesAmount",
			true, "segment", true, "totalDemandAmount", true, "forecastName",
			true, "marketSizeUnits", true, "demandId", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		VwDemandForecastActual newVwDemandForecastActual = addVwDemandForecastActual();

		VwDemandForecastActual existingVwDemandForecastActual = _persistence.fetchByPrimaryKey(newVwDemandForecastActual.getPrimaryKey());

		Assert.assertEquals(existingVwDemandForecastActual,
			newVwDemandForecastActual);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwDemandForecastActual missingVwDemandForecastActual = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingVwDemandForecastActual);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		VwDemandForecastActual newVwDemandForecastActual1 = addVwDemandForecastActual();
		VwDemandForecastActual newVwDemandForecastActual2 = addVwDemandForecastActual();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwDemandForecastActual1.getPrimaryKey());
		primaryKeys.add(newVwDemandForecastActual2.getPrimaryKey());

		Map<Serializable, VwDemandForecastActual> vwDemandForecastActuals = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, vwDemandForecastActuals.size());
		Assert.assertEquals(newVwDemandForecastActual1,
			vwDemandForecastActuals.get(
				newVwDemandForecastActual1.getPrimaryKey()));
		Assert.assertEquals(newVwDemandForecastActual2,
			vwDemandForecastActuals.get(
				newVwDemandForecastActual2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, VwDemandForecastActual> vwDemandForecastActuals = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(vwDemandForecastActuals.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		VwDemandForecastActual newVwDemandForecastActual = addVwDemandForecastActual();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwDemandForecastActual.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, VwDemandForecastActual> vwDemandForecastActuals = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, vwDemandForecastActuals.size());
		Assert.assertEquals(newVwDemandForecastActual,
			vwDemandForecastActuals.get(
				newVwDemandForecastActual.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, VwDemandForecastActual> vwDemandForecastActuals = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(vwDemandForecastActuals.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		VwDemandForecastActual newVwDemandForecastActual = addVwDemandForecastActual();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwDemandForecastActual.getPrimaryKey());

		Map<Serializable, VwDemandForecastActual> vwDemandForecastActuals = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, vwDemandForecastActuals.size());
		Assert.assertEquals(newVwDemandForecastActual,
			vwDemandForecastActuals.get(
				newVwDemandForecastActual.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = VwDemandForecastActualLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<VwDemandForecastActual>() {
				@Override
				public void performAction(
					VwDemandForecastActual vwDemandForecastActual) {
					Assert.assertNotNull(vwDemandForecastActual);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		VwDemandForecastActual newVwDemandForecastActual = addVwDemandForecastActual();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwDemandForecastActual.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("demandForecastActualSid",
				newVwDemandForecastActual.getDemandForecastActualSid()));

		List<VwDemandForecastActual> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		VwDemandForecastActual existingVwDemandForecastActual = result.get(0);

		Assert.assertEquals(existingVwDemandForecastActual,
			newVwDemandForecastActual);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwDemandForecastActual.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("demandForecastActualSid",
				RandomTestUtil.nextInt()));

		List<VwDemandForecastActual> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		VwDemandForecastActual newVwDemandForecastActual = addVwDemandForecastActual();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwDemandForecastActual.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"demandForecastActualSid"));

		Object newDemandForecastActualSid = newVwDemandForecastActual.getDemandForecastActualSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("demandForecastActualSid",
				new Object[] { newDemandForecastActualSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingDemandForecastActualSid = result.get(0);

		Assert.assertEquals(existingDemandForecastActualSid,
			newDemandForecastActualSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwDemandForecastActual.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"demandForecastActualSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("demandForecastActualSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected VwDemandForecastActual addVwDemandForecastActual()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwDemandForecastActual vwDemandForecastActual = _persistence.create(pk);

		vwDemandForecastActual.setForecastYear(RandomTestUtil.randomString());

		vwDemandForecastActual.setGrossUnits(RandomTestUtil.nextDouble());

		vwDemandForecastActual.setBusinessUnitNo(RandomTestUtil.randomString());

		vwDemandForecastActual.setTotalDemandUnits(RandomTestUtil.nextDouble());

		vwDemandForecastActual.setBrandName(RandomTestUtil.randomString());

		vwDemandForecastActual.setItemId(RandomTestUtil.randomString());

		vwDemandForecastActual.setOrganizationKey(RandomTestUtil.randomString());

		vwDemandForecastActual.setSource(RandomTestUtil.randomString());

		vwDemandForecastActual.setMarketShareRatio(RandomTestUtil.randomString());

		vwDemandForecastActual.setBusinessUnitName(RandomTestUtil.randomString());

		vwDemandForecastActual.setMarketShareUnits(RandomTestUtil.nextDouble());

		vwDemandForecastActual.setInventoryUnitChange(RandomTestUtil.nextDouble());

		vwDemandForecastActual.setUncapturedUnitsRatio(RandomTestUtil.randomString());

		vwDemandForecastActual.setCountry(RandomTestUtil.randomString());

		vwDemandForecastActual.setForecastType(RandomTestUtil.randomString());

		vwDemandForecastActual.setBrandId(RandomTestUtil.randomString());

		vwDemandForecastActual.setIsForecast(RandomTestUtil.randomString());

		vwDemandForecastActual.setUncapturedUnits(RandomTestUtil.nextDouble());

		vwDemandForecastActual.setGrossPrice(RandomTestUtil.nextDouble());

		vwDemandForecastActual.setIsActive(RandomTestUtil.randomString());

		vwDemandForecastActual.setGrossAmount(RandomTestUtil.nextDouble());

		vwDemandForecastActual.setBatchId(RandomTestUtil.randomString());

		vwDemandForecastActual.setForecastVer(RandomTestUtil.randomString());

		vwDemandForecastActual.setItemName(RandomTestUtil.randomString());

		vwDemandForecastActual.setForecastMonth(RandomTestUtil.randomString());

		vwDemandForecastActual.setNetSalesPrice(RandomTestUtil.nextDouble());

		vwDemandForecastActual.setNetSalesAmount(RandomTestUtil.nextDouble());

		vwDemandForecastActual.setSegment(RandomTestUtil.randomString());

		vwDemandForecastActual.setTotalDemandAmount(RandomTestUtil.nextDouble());

		vwDemandForecastActual.setForecastName(RandomTestUtil.randomString());

		vwDemandForecastActual.setMarketSizeUnits(RandomTestUtil.nextDouble());

		vwDemandForecastActual.setDemandId(RandomTestUtil.nextInt());

		_vwDemandForecastActuals.add(_persistence.update(vwDemandForecastActual));

		return vwDemandForecastActual;
	}

	private List<VwDemandForecastActual> _vwDemandForecastActuals = new ArrayList<VwDemandForecastActual>();
	private VwDemandForecastActualPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}