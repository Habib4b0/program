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
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchDemandForecastException;
import com.stpl.app.model.DemandForecast;
import com.stpl.app.service.DemandForecastLocalServiceUtil;
import com.stpl.app.service.persistence.DemandForecastPersistence;
import com.stpl.app.service.persistence.DemandForecastUtil;

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
public class DemandForecastPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = DemandForecastUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<DemandForecast> iterator = _demandForecasts.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		DemandForecast demandForecast = _persistence.create(pk);

		Assert.assertNotNull(demandForecast);

		Assert.assertEquals(demandForecast.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		DemandForecast newDemandForecast = addDemandForecast();

		_persistence.remove(newDemandForecast);

		DemandForecast existingDemandForecast = _persistence.fetchByPrimaryKey(newDemandForecast.getPrimaryKey());

		Assert.assertNull(existingDemandForecast);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addDemandForecast();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		DemandForecast newDemandForecast = _persistence.create(pk);

		newDemandForecast.setModifiedBy(RandomTestUtil.randomString());

		newDemandForecast.setCreatedDate(RandomTestUtil.nextDate());

		newDemandForecast.setTotalDemandUnits(RandomTestUtil.nextDouble());

		newDemandForecast.setBrandMasterSid(RandomTestUtil.nextInt());

		newDemandForecast.setMarketShareUnits(RandomTestUtil.nextDouble());

		newDemandForecast.setBatchId(RandomTestUtil.randomString());

		newDemandForecast.setGrossAmount(RandomTestUtil.nextDouble());

		newDemandForecast.setForecastVer(RandomTestUtil.randomString());

		newDemandForecast.setBrandId(RandomTestUtil.randomString());

		newDemandForecast.setGrossUnits(RandomTestUtil.nextDouble());

		newDemandForecast.setCountry(RandomTestUtil.randomString());

		newDemandForecast.setForecastType(RandomTestUtil.randomString());

		newDemandForecast.setItemMasterSid(RandomTestUtil.nextInt());

		newDemandForecast.setTotalDemandAmount(RandomTestUtil.nextDouble());

		newDemandForecast.setForecastMonth(RandomTestUtil.randomString());

		newDemandForecast.setOrganizationKey(RandomTestUtil.randomString());

		newDemandForecast.setCreatedBy(RandomTestUtil.randomString());

		newDemandForecast.setMarketSizeUnits(RandomTestUtil.nextDouble());

		newDemandForecast.setSegment(RandomTestUtil.randomString());

		newDemandForecast.setForecastYear(RandomTestUtil.randomString());

		newDemandForecast.setItemId(RandomTestUtil.randomString());

		newDemandForecast.setInventoryUnitChange(RandomTestUtil.nextDouble());

		newDemandForecast.setGrossPrice(RandomTestUtil.nextDouble());

		newDemandForecast.setForecastName(RandomTestUtil.randomString());

		newDemandForecast.setNetSalesAmount(RandomTestUtil.nextDouble());

		newDemandForecast.setModifiedDate(RandomTestUtil.nextDate());

		newDemandForecast.setItemIdentifier(RandomTestUtil.randomString());

		newDemandForecast.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newDemandForecast.setUncapturedUnitsRatio(RandomTestUtil.randomString());

		newDemandForecast.setItemIdentifierCodeQualifier(RandomTestUtil.randomString());

		newDemandForecast.setMarketShareRatio(RandomTestUtil.randomString());

		newDemandForecast.setSource(RandomTestUtil.randomString());

		newDemandForecast.setUncapturedUnits(RandomTestUtil.nextDouble());

		newDemandForecast.setNetSalesPrice(RandomTestUtil.nextDouble());

		newDemandForecast.setInboundStatus(RandomTestUtil.randomString());

		_demandForecasts.add(_persistence.update(newDemandForecast));

		DemandForecast existingDemandForecast = _persistence.findByPrimaryKey(newDemandForecast.getPrimaryKey());

		Assert.assertEquals(existingDemandForecast.getModifiedBy(),
			newDemandForecast.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingDemandForecast.getCreatedDate()),
			Time.getShortTimestamp(newDemandForecast.getCreatedDate()));
		AssertUtils.assertEquals(existingDemandForecast.getTotalDemandUnits(),
			newDemandForecast.getTotalDemandUnits());
		Assert.assertEquals(existingDemandForecast.getBrandMasterSid(),
			newDemandForecast.getBrandMasterSid());
		AssertUtils.assertEquals(existingDemandForecast.getMarketShareUnits(),
			newDemandForecast.getMarketShareUnits());
		Assert.assertEquals(existingDemandForecast.getBatchId(),
			newDemandForecast.getBatchId());
		AssertUtils.assertEquals(existingDemandForecast.getGrossAmount(),
			newDemandForecast.getGrossAmount());
		Assert.assertEquals(existingDemandForecast.getForecastVer(),
			newDemandForecast.getForecastVer());
		Assert.assertEquals(existingDemandForecast.getBrandId(),
			newDemandForecast.getBrandId());
		AssertUtils.assertEquals(existingDemandForecast.getGrossUnits(),
			newDemandForecast.getGrossUnits());
		Assert.assertEquals(existingDemandForecast.getCountry(),
			newDemandForecast.getCountry());
		Assert.assertEquals(existingDemandForecast.getDemandForecastSid(),
			newDemandForecast.getDemandForecastSid());
		Assert.assertEquals(existingDemandForecast.getForecastType(),
			newDemandForecast.getForecastType());
		Assert.assertEquals(existingDemandForecast.getItemMasterSid(),
			newDemandForecast.getItemMasterSid());
		AssertUtils.assertEquals(existingDemandForecast.getTotalDemandAmount(),
			newDemandForecast.getTotalDemandAmount());
		Assert.assertEquals(existingDemandForecast.getForecastMonth(),
			newDemandForecast.getForecastMonth());
		Assert.assertEquals(existingDemandForecast.getOrganizationKey(),
			newDemandForecast.getOrganizationKey());
		Assert.assertEquals(existingDemandForecast.getCreatedBy(),
			newDemandForecast.getCreatedBy());
		AssertUtils.assertEquals(existingDemandForecast.getMarketSizeUnits(),
			newDemandForecast.getMarketSizeUnits());
		Assert.assertEquals(existingDemandForecast.getSegment(),
			newDemandForecast.getSegment());
		Assert.assertEquals(existingDemandForecast.getForecastYear(),
			newDemandForecast.getForecastYear());
		Assert.assertEquals(existingDemandForecast.getItemId(),
			newDemandForecast.getItemId());
		AssertUtils.assertEquals(existingDemandForecast.getInventoryUnitChange(),
			newDemandForecast.getInventoryUnitChange());
		AssertUtils.assertEquals(existingDemandForecast.getGrossPrice(),
			newDemandForecast.getGrossPrice());
		Assert.assertEquals(existingDemandForecast.getForecastName(),
			newDemandForecast.getForecastName());
		AssertUtils.assertEquals(existingDemandForecast.getNetSalesAmount(),
			newDemandForecast.getNetSalesAmount());
		Assert.assertEquals(Time.getShortTimestamp(
				existingDemandForecast.getModifiedDate()),
			Time.getShortTimestamp(newDemandForecast.getModifiedDate()));
		Assert.assertEquals(existingDemandForecast.getItemIdentifier(),
			newDemandForecast.getItemIdentifier());
		Assert.assertEquals(existingDemandForecast.getRecordLockStatus(),
			newDemandForecast.getRecordLockStatus());
		Assert.assertEquals(existingDemandForecast.getUncapturedUnitsRatio(),
			newDemandForecast.getUncapturedUnitsRatio());
		Assert.assertEquals(existingDemandForecast.getItemIdentifierCodeQualifier(),
			newDemandForecast.getItemIdentifierCodeQualifier());
		Assert.assertEquals(existingDemandForecast.getMarketShareRatio(),
			newDemandForecast.getMarketShareRatio());
		Assert.assertEquals(existingDemandForecast.getSource(),
			newDemandForecast.getSource());
		AssertUtils.assertEquals(existingDemandForecast.getUncapturedUnits(),
			newDemandForecast.getUncapturedUnits());
		AssertUtils.assertEquals(existingDemandForecast.getNetSalesPrice(),
			newDemandForecast.getNetSalesPrice());
		Assert.assertEquals(existingDemandForecast.getInboundStatus(),
			newDemandForecast.getInboundStatus());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		DemandForecast newDemandForecast = addDemandForecast();

		DemandForecast existingDemandForecast = _persistence.findByPrimaryKey(newDemandForecast.getPrimaryKey());

		Assert.assertEquals(existingDemandForecast, newDemandForecast);
	}

	@Test(expected = NoSuchDemandForecastException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<DemandForecast> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("DEMAND_FORECAST",
			"modifiedBy", true, "createdDate", true, "totalDemandUnits", true,
			"brandMasterSid", true, "marketShareUnits", true, "batchId", true,
			"grossAmount", true, "forecastVer", true, "brandId", true,
			"grossUnits", true, "country", true, "demandForecastSid", true,
			"forecastType", true, "itemMasterSid", true, "totalDemandAmount",
			true, "forecastMonth", true, "organizationKey", true, "createdBy",
			true, "marketSizeUnits", true, "segment", true, "forecastYear",
			true, "itemId", true, "inventoryUnitChange", true, "grossPrice",
			true, "forecastName", true, "netSalesAmount", true, "modifiedDate",
			true, "itemIdentifier", true, "recordLockStatus", true,
			"uncapturedUnitsRatio", true, "itemIdentifierCodeQualifier", true,
			"marketShareRatio", true, "source", true, "uncapturedUnits", true,
			"netSalesPrice", true, "inboundStatus", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		DemandForecast newDemandForecast = addDemandForecast();

		DemandForecast existingDemandForecast = _persistence.fetchByPrimaryKey(newDemandForecast.getPrimaryKey());

		Assert.assertEquals(existingDemandForecast, newDemandForecast);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		DemandForecast missingDemandForecast = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingDemandForecast);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		DemandForecast newDemandForecast1 = addDemandForecast();
		DemandForecast newDemandForecast2 = addDemandForecast();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDemandForecast1.getPrimaryKey());
		primaryKeys.add(newDemandForecast2.getPrimaryKey());

		Map<Serializable, DemandForecast> demandForecasts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, demandForecasts.size());
		Assert.assertEquals(newDemandForecast1,
			demandForecasts.get(newDemandForecast1.getPrimaryKey()));
		Assert.assertEquals(newDemandForecast2,
			demandForecasts.get(newDemandForecast2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, DemandForecast> demandForecasts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(demandForecasts.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		DemandForecast newDemandForecast = addDemandForecast();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDemandForecast.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, DemandForecast> demandForecasts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, demandForecasts.size());
		Assert.assertEquals(newDemandForecast,
			demandForecasts.get(newDemandForecast.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, DemandForecast> demandForecasts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(demandForecasts.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		DemandForecast newDemandForecast = addDemandForecast();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDemandForecast.getPrimaryKey());

		Map<Serializable, DemandForecast> demandForecasts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, demandForecasts.size());
		Assert.assertEquals(newDemandForecast,
			demandForecasts.get(newDemandForecast.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = DemandForecastLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<DemandForecast>() {
				@Override
				public void performAction(DemandForecast demandForecast) {
					Assert.assertNotNull(demandForecast);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		DemandForecast newDemandForecast = addDemandForecast();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DemandForecast.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("demandForecastSid",
				newDemandForecast.getDemandForecastSid()));

		List<DemandForecast> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		DemandForecast existingDemandForecast = result.get(0);

		Assert.assertEquals(existingDemandForecast, newDemandForecast);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DemandForecast.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("demandForecastSid",
				RandomTestUtil.nextInt()));

		List<DemandForecast> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		DemandForecast newDemandForecast = addDemandForecast();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DemandForecast.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"demandForecastSid"));

		Object newDemandForecastSid = newDemandForecast.getDemandForecastSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("demandForecastSid",
				new Object[] { newDemandForecastSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingDemandForecastSid = result.get(0);

		Assert.assertEquals(existingDemandForecastSid, newDemandForecastSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DemandForecast.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"demandForecastSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("demandForecastSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected DemandForecast addDemandForecast() throws Exception {
		int pk = RandomTestUtil.nextInt();

		DemandForecast demandForecast = _persistence.create(pk);

		demandForecast.setModifiedBy(RandomTestUtil.randomString());

		demandForecast.setCreatedDate(RandomTestUtil.nextDate());

		demandForecast.setTotalDemandUnits(RandomTestUtil.nextDouble());

		demandForecast.setBrandMasterSid(RandomTestUtil.nextInt());

		demandForecast.setMarketShareUnits(RandomTestUtil.nextDouble());

		demandForecast.setBatchId(RandomTestUtil.randomString());

		demandForecast.setGrossAmount(RandomTestUtil.nextDouble());

		demandForecast.setForecastVer(RandomTestUtil.randomString());

		demandForecast.setBrandId(RandomTestUtil.randomString());

		demandForecast.setGrossUnits(RandomTestUtil.nextDouble());

		demandForecast.setCountry(RandomTestUtil.randomString());

		demandForecast.setForecastType(RandomTestUtil.randomString());

		demandForecast.setItemMasterSid(RandomTestUtil.nextInt());

		demandForecast.setTotalDemandAmount(RandomTestUtil.nextDouble());

		demandForecast.setForecastMonth(RandomTestUtil.randomString());

		demandForecast.setOrganizationKey(RandomTestUtil.randomString());

		demandForecast.setCreatedBy(RandomTestUtil.randomString());

		demandForecast.setMarketSizeUnits(RandomTestUtil.nextDouble());

		demandForecast.setSegment(RandomTestUtil.randomString());

		demandForecast.setForecastYear(RandomTestUtil.randomString());

		demandForecast.setItemId(RandomTestUtil.randomString());

		demandForecast.setInventoryUnitChange(RandomTestUtil.nextDouble());

		demandForecast.setGrossPrice(RandomTestUtil.nextDouble());

		demandForecast.setForecastName(RandomTestUtil.randomString());

		demandForecast.setNetSalesAmount(RandomTestUtil.nextDouble());

		demandForecast.setModifiedDate(RandomTestUtil.nextDate());

		demandForecast.setItemIdentifier(RandomTestUtil.randomString());

		demandForecast.setRecordLockStatus(RandomTestUtil.randomBoolean());

		demandForecast.setUncapturedUnitsRatio(RandomTestUtil.randomString());

		demandForecast.setItemIdentifierCodeQualifier(RandomTestUtil.randomString());

		demandForecast.setMarketShareRatio(RandomTestUtil.randomString());

		demandForecast.setSource(RandomTestUtil.randomString());

		demandForecast.setUncapturedUnits(RandomTestUtil.nextDouble());

		demandForecast.setNetSalesPrice(RandomTestUtil.nextDouble());

		demandForecast.setInboundStatus(RandomTestUtil.randomString());

		_demandForecasts.add(_persistence.update(demandForecast));

		return demandForecast;
	}

	private List<DemandForecast> _demandForecasts = new ArrayList<DemandForecast>();
	private DemandForecastPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}