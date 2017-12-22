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
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.parttwo.exception.NoSuchAdjustedDemandForecastException;
import com.stpl.app.parttwo.model.AdjustedDemandForecast;
import com.stpl.app.parttwo.service.AdjustedDemandForecastLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.AdjustedDemandForecastPersistence;
import com.stpl.app.parttwo.service.persistence.AdjustedDemandForecastUtil;

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
public class AdjustedDemandForecastPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = AdjustedDemandForecastUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<AdjustedDemandForecast> iterator = _adjustedDemandForecasts.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AdjustedDemandForecast adjustedDemandForecast = _persistence.create(pk);

		Assert.assertNotNull(adjustedDemandForecast);

		Assert.assertEquals(adjustedDemandForecast.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		AdjustedDemandForecast newAdjustedDemandForecast = addAdjustedDemandForecast();

		_persistence.remove(newAdjustedDemandForecast);

		AdjustedDemandForecast existingAdjustedDemandForecast = _persistence.fetchByPrimaryKey(newAdjustedDemandForecast.getPrimaryKey());

		Assert.assertNull(existingAdjustedDemandForecast);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addAdjustedDemandForecast();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AdjustedDemandForecast newAdjustedDemandForecast = _persistence.create(pk);

		newAdjustedDemandForecast.setItemMasterSid(RandomTestUtil.nextInt());

		newAdjustedDemandForecast.setGrossUnits(RandomTestUtil.nextDouble());

		newAdjustedDemandForecast.setTotalDemandUnits(RandomTestUtil.nextDouble());

		newAdjustedDemandForecast.setYear(RandomTestUtil.randomString());

		newAdjustedDemandForecast.setItemId(RandomTestUtil.randomString());

		newAdjustedDemandForecast.setModifiedDate(RandomTestUtil.nextDate());

		newAdjustedDemandForecast.setBrandMasterSid(RandomTestUtil.nextInt());

		newAdjustedDemandForecast.setOrganizationKey(RandomTestUtil.randomString());

		newAdjustedDemandForecast.setSource(RandomTestUtil.randomString());

		newAdjustedDemandForecast.setCreatedDate(RandomTestUtil.nextDate());

		newAdjustedDemandForecast.setCreatedBy(RandomTestUtil.randomString());

		newAdjustedDemandForecast.setMarketShareRatio(RandomTestUtil.randomString());

		newAdjustedDemandForecast.setItemIdentifier(RandomTestUtil.randomString());

		newAdjustedDemandForecast.setInboundStatus(RandomTestUtil.randomString());

		newAdjustedDemandForecast.setModifiedBy(RandomTestUtil.randomString());

		newAdjustedDemandForecast.setMarketShareUnits(RandomTestUtil.nextDouble());

		newAdjustedDemandForecast.setMonth(RandomTestUtil.randomString());

		newAdjustedDemandForecast.setInventoryUnitChange(RandomTestUtil.nextDouble());

		newAdjustedDemandForecast.setUncapturedUnitsRatio(RandomTestUtil.randomString());

		newAdjustedDemandForecast.setCountry(RandomTestUtil.randomString());

		newAdjustedDemandForecast.setForecastType(RandomTestUtil.randomString());

		newAdjustedDemandForecast.setBrandId(RandomTestUtil.randomString());

		newAdjustedDemandForecast.setUncapturedUnits(RandomTestUtil.nextDouble());

		newAdjustedDemandForecast.setGrossPrice(RandomTestUtil.nextDouble());

		newAdjustedDemandForecast.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newAdjustedDemandForecast.setGrossAmount(RandomTestUtil.nextDouble());

		newAdjustedDemandForecast.setItemIdentifierCodeQualifier(RandomTestUtil.randomString());

		newAdjustedDemandForecast.setForecastVer(RandomTestUtil.randomString());

		newAdjustedDemandForecast.setBatchId(RandomTestUtil.randomString());

		newAdjustedDemandForecast.setNetSalesPrice(RandomTestUtil.nextDouble());

		newAdjustedDemandForecast.setNetSalesAmount(RandomTestUtil.nextDouble());

		newAdjustedDemandForecast.setSegment(RandomTestUtil.randomString());

		newAdjustedDemandForecast.setTotalDemandAmount(RandomTestUtil.nextDouble());

		newAdjustedDemandForecast.setForecastName(RandomTestUtil.randomString());

		newAdjustedDemandForecast.setMarketSizeUnits(RandomTestUtil.nextDouble());

		_adjustedDemandForecasts.add(_persistence.update(
				newAdjustedDemandForecast));

		AdjustedDemandForecast existingAdjustedDemandForecast = _persistence.findByPrimaryKey(newAdjustedDemandForecast.getPrimaryKey());

		Assert.assertEquals(existingAdjustedDemandForecast.getItemMasterSid(),
			newAdjustedDemandForecast.getItemMasterSid());
		Assert.assertEquals(existingAdjustedDemandForecast.getAdjustedDemandForecastSid(),
			newAdjustedDemandForecast.getAdjustedDemandForecastSid());
		AssertUtils.assertEquals(existingAdjustedDemandForecast.getGrossUnits(),
			newAdjustedDemandForecast.getGrossUnits());
		AssertUtils.assertEquals(existingAdjustedDemandForecast.getTotalDemandUnits(),
			newAdjustedDemandForecast.getTotalDemandUnits());
		Assert.assertEquals(existingAdjustedDemandForecast.getYear(),
			newAdjustedDemandForecast.getYear());
		Assert.assertEquals(existingAdjustedDemandForecast.getItemId(),
			newAdjustedDemandForecast.getItemId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingAdjustedDemandForecast.getModifiedDate()),
			Time.getShortTimestamp(newAdjustedDemandForecast.getModifiedDate()));
		Assert.assertEquals(existingAdjustedDemandForecast.getBrandMasterSid(),
			newAdjustedDemandForecast.getBrandMasterSid());
		Assert.assertEquals(existingAdjustedDemandForecast.getOrganizationKey(),
			newAdjustedDemandForecast.getOrganizationKey());
		Assert.assertEquals(existingAdjustedDemandForecast.getSource(),
			newAdjustedDemandForecast.getSource());
		Assert.assertEquals(Time.getShortTimestamp(
				existingAdjustedDemandForecast.getCreatedDate()),
			Time.getShortTimestamp(newAdjustedDemandForecast.getCreatedDate()));
		Assert.assertEquals(existingAdjustedDemandForecast.getCreatedBy(),
			newAdjustedDemandForecast.getCreatedBy());
		Assert.assertEquals(existingAdjustedDemandForecast.getMarketShareRatio(),
			newAdjustedDemandForecast.getMarketShareRatio());
		Assert.assertEquals(existingAdjustedDemandForecast.getItemIdentifier(),
			newAdjustedDemandForecast.getItemIdentifier());
		Assert.assertEquals(existingAdjustedDemandForecast.getInboundStatus(),
			newAdjustedDemandForecast.getInboundStatus());
		Assert.assertEquals(existingAdjustedDemandForecast.getModifiedBy(),
			newAdjustedDemandForecast.getModifiedBy());
		AssertUtils.assertEquals(existingAdjustedDemandForecast.getMarketShareUnits(),
			newAdjustedDemandForecast.getMarketShareUnits());
		Assert.assertEquals(existingAdjustedDemandForecast.getMonth(),
			newAdjustedDemandForecast.getMonth());
		AssertUtils.assertEquals(existingAdjustedDemandForecast.getInventoryUnitChange(),
			newAdjustedDemandForecast.getInventoryUnitChange());
		Assert.assertEquals(existingAdjustedDemandForecast.getUncapturedUnitsRatio(),
			newAdjustedDemandForecast.getUncapturedUnitsRatio());
		Assert.assertEquals(existingAdjustedDemandForecast.getCountry(),
			newAdjustedDemandForecast.getCountry());
		Assert.assertEquals(existingAdjustedDemandForecast.getForecastType(),
			newAdjustedDemandForecast.getForecastType());
		Assert.assertEquals(existingAdjustedDemandForecast.getBrandId(),
			newAdjustedDemandForecast.getBrandId());
		AssertUtils.assertEquals(existingAdjustedDemandForecast.getUncapturedUnits(),
			newAdjustedDemandForecast.getUncapturedUnits());
		AssertUtils.assertEquals(existingAdjustedDemandForecast.getGrossPrice(),
			newAdjustedDemandForecast.getGrossPrice());
		Assert.assertEquals(existingAdjustedDemandForecast.getRecordLockStatus(),
			newAdjustedDemandForecast.getRecordLockStatus());
		AssertUtils.assertEquals(existingAdjustedDemandForecast.getGrossAmount(),
			newAdjustedDemandForecast.getGrossAmount());
		Assert.assertEquals(existingAdjustedDemandForecast.getItemIdentifierCodeQualifier(),
			newAdjustedDemandForecast.getItemIdentifierCodeQualifier());
		Assert.assertEquals(existingAdjustedDemandForecast.getForecastVer(),
			newAdjustedDemandForecast.getForecastVer());
		Assert.assertEquals(existingAdjustedDemandForecast.getBatchId(),
			newAdjustedDemandForecast.getBatchId());
		AssertUtils.assertEquals(existingAdjustedDemandForecast.getNetSalesPrice(),
			newAdjustedDemandForecast.getNetSalesPrice());
		AssertUtils.assertEquals(existingAdjustedDemandForecast.getNetSalesAmount(),
			newAdjustedDemandForecast.getNetSalesAmount());
		Assert.assertEquals(existingAdjustedDemandForecast.getSegment(),
			newAdjustedDemandForecast.getSegment());
		AssertUtils.assertEquals(existingAdjustedDemandForecast.getTotalDemandAmount(),
			newAdjustedDemandForecast.getTotalDemandAmount());
		Assert.assertEquals(existingAdjustedDemandForecast.getForecastName(),
			newAdjustedDemandForecast.getForecastName());
		AssertUtils.assertEquals(existingAdjustedDemandForecast.getMarketSizeUnits(),
			newAdjustedDemandForecast.getMarketSizeUnits());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		AdjustedDemandForecast newAdjustedDemandForecast = addAdjustedDemandForecast();

		AdjustedDemandForecast existingAdjustedDemandForecast = _persistence.findByPrimaryKey(newAdjustedDemandForecast.getPrimaryKey());

		Assert.assertEquals(existingAdjustedDemandForecast,
			newAdjustedDemandForecast);
	}

	@Test(expected = NoSuchAdjustedDemandForecastException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<AdjustedDemandForecast> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("ADJUSTED_DEMAND_FORECAST",
			"itemMasterSid", true, "adjustedDemandForecastSid", true,
			"grossUnits", true, "totalDemandUnits", true, "year", true,
			"itemId", true, "modifiedDate", true, "brandMasterSid", true,
			"organizationKey", true, "source", true, "createdDate", true,
			"createdBy", true, "marketShareRatio", true, "itemIdentifier",
			true, "inboundStatus", true, "modifiedBy", true,
			"marketShareUnits", true, "month", true, "inventoryUnitChange",
			true, "uncapturedUnitsRatio", true, "country", true,
			"forecastType", true, "brandId", true, "uncapturedUnits", true,
			"grossPrice", true, "recordLockStatus", true, "grossAmount", true,
			"itemIdentifierCodeQualifier", true, "forecastVer", true,
			"batchId", true, "netSalesPrice", true, "netSalesAmount", true,
			"segment", true, "totalDemandAmount", true, "forecastName", true,
			"marketSizeUnits", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		AdjustedDemandForecast newAdjustedDemandForecast = addAdjustedDemandForecast();

		AdjustedDemandForecast existingAdjustedDemandForecast = _persistence.fetchByPrimaryKey(newAdjustedDemandForecast.getPrimaryKey());

		Assert.assertEquals(existingAdjustedDemandForecast,
			newAdjustedDemandForecast);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AdjustedDemandForecast missingAdjustedDemandForecast = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingAdjustedDemandForecast);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		AdjustedDemandForecast newAdjustedDemandForecast1 = addAdjustedDemandForecast();
		AdjustedDemandForecast newAdjustedDemandForecast2 = addAdjustedDemandForecast();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAdjustedDemandForecast1.getPrimaryKey());
		primaryKeys.add(newAdjustedDemandForecast2.getPrimaryKey());

		Map<Serializable, AdjustedDemandForecast> adjustedDemandForecasts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, adjustedDemandForecasts.size());
		Assert.assertEquals(newAdjustedDemandForecast1,
			adjustedDemandForecasts.get(
				newAdjustedDemandForecast1.getPrimaryKey()));
		Assert.assertEquals(newAdjustedDemandForecast2,
			adjustedDemandForecasts.get(
				newAdjustedDemandForecast2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, AdjustedDemandForecast> adjustedDemandForecasts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(adjustedDemandForecasts.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		AdjustedDemandForecast newAdjustedDemandForecast = addAdjustedDemandForecast();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAdjustedDemandForecast.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, AdjustedDemandForecast> adjustedDemandForecasts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, adjustedDemandForecasts.size());
		Assert.assertEquals(newAdjustedDemandForecast,
			adjustedDemandForecasts.get(
				newAdjustedDemandForecast.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, AdjustedDemandForecast> adjustedDemandForecasts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(adjustedDemandForecasts.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		AdjustedDemandForecast newAdjustedDemandForecast = addAdjustedDemandForecast();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAdjustedDemandForecast.getPrimaryKey());

		Map<Serializable, AdjustedDemandForecast> adjustedDemandForecasts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, adjustedDemandForecasts.size());
		Assert.assertEquals(newAdjustedDemandForecast,
			adjustedDemandForecasts.get(
				newAdjustedDemandForecast.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = AdjustedDemandForecastLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<AdjustedDemandForecast>() {
				@Override
				public void performAction(
					AdjustedDemandForecast adjustedDemandForecast) {
					Assert.assertNotNull(adjustedDemandForecast);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		AdjustedDemandForecast newAdjustedDemandForecast = addAdjustedDemandForecast();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AdjustedDemandForecast.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"adjustedDemandForecastSid",
				newAdjustedDemandForecast.getAdjustedDemandForecastSid()));

		List<AdjustedDemandForecast> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		AdjustedDemandForecast existingAdjustedDemandForecast = result.get(0);

		Assert.assertEquals(existingAdjustedDemandForecast,
			newAdjustedDemandForecast);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AdjustedDemandForecast.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"adjustedDemandForecastSid", RandomTestUtil.nextInt()));

		List<AdjustedDemandForecast> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		AdjustedDemandForecast newAdjustedDemandForecast = addAdjustedDemandForecast();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AdjustedDemandForecast.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"adjustedDemandForecastSid"));

		Object newAdjustedDemandForecastSid = newAdjustedDemandForecast.getAdjustedDemandForecastSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"adjustedDemandForecastSid",
				new Object[] { newAdjustedDemandForecastSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingAdjustedDemandForecastSid = result.get(0);

		Assert.assertEquals(existingAdjustedDemandForecastSid,
			newAdjustedDemandForecastSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AdjustedDemandForecast.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"adjustedDemandForecastSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"adjustedDemandForecastSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected AdjustedDemandForecast addAdjustedDemandForecast()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		AdjustedDemandForecast adjustedDemandForecast = _persistence.create(pk);

		adjustedDemandForecast.setItemMasterSid(RandomTestUtil.nextInt());

		adjustedDemandForecast.setGrossUnits(RandomTestUtil.nextDouble());

		adjustedDemandForecast.setTotalDemandUnits(RandomTestUtil.nextDouble());

		adjustedDemandForecast.setYear(RandomTestUtil.randomString());

		adjustedDemandForecast.setItemId(RandomTestUtil.randomString());

		adjustedDemandForecast.setModifiedDate(RandomTestUtil.nextDate());

		adjustedDemandForecast.setBrandMasterSid(RandomTestUtil.nextInt());

		adjustedDemandForecast.setOrganizationKey(RandomTestUtil.randomString());

		adjustedDemandForecast.setSource(RandomTestUtil.randomString());

		adjustedDemandForecast.setCreatedDate(RandomTestUtil.nextDate());

		adjustedDemandForecast.setCreatedBy(RandomTestUtil.randomString());

		adjustedDemandForecast.setMarketShareRatio(RandomTestUtil.randomString());

		adjustedDemandForecast.setItemIdentifier(RandomTestUtil.randomString());

		adjustedDemandForecast.setInboundStatus(RandomTestUtil.randomString());

		adjustedDemandForecast.setModifiedBy(RandomTestUtil.randomString());

		adjustedDemandForecast.setMarketShareUnits(RandomTestUtil.nextDouble());

		adjustedDemandForecast.setMonth(RandomTestUtil.randomString());

		adjustedDemandForecast.setInventoryUnitChange(RandomTestUtil.nextDouble());

		adjustedDemandForecast.setUncapturedUnitsRatio(RandomTestUtil.randomString());

		adjustedDemandForecast.setCountry(RandomTestUtil.randomString());

		adjustedDemandForecast.setForecastType(RandomTestUtil.randomString());

		adjustedDemandForecast.setBrandId(RandomTestUtil.randomString());

		adjustedDemandForecast.setUncapturedUnits(RandomTestUtil.nextDouble());

		adjustedDemandForecast.setGrossPrice(RandomTestUtil.nextDouble());

		adjustedDemandForecast.setRecordLockStatus(RandomTestUtil.randomBoolean());

		adjustedDemandForecast.setGrossAmount(RandomTestUtil.nextDouble());

		adjustedDemandForecast.setItemIdentifierCodeQualifier(RandomTestUtil.randomString());

		adjustedDemandForecast.setForecastVer(RandomTestUtil.randomString());

		adjustedDemandForecast.setBatchId(RandomTestUtil.randomString());

		adjustedDemandForecast.setNetSalesPrice(RandomTestUtil.nextDouble());

		adjustedDemandForecast.setNetSalesAmount(RandomTestUtil.nextDouble());

		adjustedDemandForecast.setSegment(RandomTestUtil.randomString());

		adjustedDemandForecast.setTotalDemandAmount(RandomTestUtil.nextDouble());

		adjustedDemandForecast.setForecastName(RandomTestUtil.randomString());

		adjustedDemandForecast.setMarketSizeUnits(RandomTestUtil.nextDouble());

		_adjustedDemandForecasts.add(_persistence.update(adjustedDemandForecast));

		return adjustedDemandForecast;
	}

	private List<AdjustedDemandForecast> _adjustedDemandForecasts = new ArrayList<AdjustedDemandForecast>();
	private AdjustedDemandForecastPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}