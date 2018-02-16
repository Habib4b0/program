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

import com.stpl.app.exception.NoSuchIvldDemandForecastException;
import com.stpl.app.model.IvldDemandForecast;
import com.stpl.app.service.IvldDemandForecastLocalServiceUtil;
import com.stpl.app.service.persistence.IvldDemandForecastPersistence;
import com.stpl.app.service.persistence.IvldDemandForecastUtil;

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
public class IvldDemandForecastPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = IvldDemandForecastUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<IvldDemandForecast> iterator = _ivldDemandForecasts.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldDemandForecast ivldDemandForecast = _persistence.create(pk);

		Assert.assertNotNull(ivldDemandForecast);

		Assert.assertEquals(ivldDemandForecast.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		IvldDemandForecast newIvldDemandForecast = addIvldDemandForecast();

		_persistence.remove(newIvldDemandForecast);

		IvldDemandForecast existingIvldDemandForecast = _persistence.fetchByPrimaryKey(newIvldDemandForecast.getPrimaryKey());

		Assert.assertNull(existingIvldDemandForecast);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addIvldDemandForecast();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldDemandForecast newIvldDemandForecast = _persistence.create(pk);

		newIvldDemandForecast.setForecastYear(RandomTestUtil.randomString());

		newIvldDemandForecast.setGrossUnits(RandomTestUtil.randomString());

		newIvldDemandForecast.setTotalDemandUnits(RandomTestUtil.randomString());

		newIvldDemandForecast.setItemId(RandomTestUtil.randomString());

		newIvldDemandForecast.setModifiedDate(RandomTestUtil.nextDate());

		newIvldDemandForecast.setOrganizationKey(RandomTestUtil.randomString());

		newIvldDemandForecast.setSource(RandomTestUtil.randomString());

		newIvldDemandForecast.setMarketShareRatio(RandomTestUtil.randomString());

		newIvldDemandForecast.setCreatedBy(RandomTestUtil.randomString());

		newIvldDemandForecast.setCreatedDate(RandomTestUtil.nextDate());

		newIvldDemandForecast.setAddChgDelIndicator(RandomTestUtil.randomString());

		newIvldDemandForecast.setItemIdentifier(RandomTestUtil.randomString());

		newIvldDemandForecast.setErrorCode(RandomTestUtil.randomString());

		newIvldDemandForecast.setIntfInsertedDate(RandomTestUtil.randomString());

		newIvldDemandForecast.setModifiedBy(RandomTestUtil.randomString());

		newIvldDemandForecast.setMarketShareUnits(RandomTestUtil.randomString());

		newIvldDemandForecast.setInventoryUnitChange(RandomTestUtil.randomString());

		newIvldDemandForecast.setReprocessedFlag(RandomTestUtil.randomString());

		newIvldDemandForecast.setUncapturedUnitsRatio(RandomTestUtil.randomString());

		newIvldDemandForecast.setReasonForFailure(RandomTestUtil.randomString());

		newIvldDemandForecast.setCountry(RandomTestUtil.randomString());

		newIvldDemandForecast.setForecastType(RandomTestUtil.randomString());

		newIvldDemandForecast.setBrandId(RandomTestUtil.randomString());

		newIvldDemandForecast.setDemandForecastInterfaceId(RandomTestUtil.randomString());

		newIvldDemandForecast.setUncapturedUnits(RandomTestUtil.randomString());

		newIvldDemandForecast.setGrossPrice(RandomTestUtil.randomString());

		newIvldDemandForecast.setGrossAmount(RandomTestUtil.randomString());

		newIvldDemandForecast.setItemIdentifierCodeQualifier(RandomTestUtil.randomString());

		newIvldDemandForecast.setForecastVer(RandomTestUtil.randomString());

		newIvldDemandForecast.setBatchId(RandomTestUtil.randomString());

		newIvldDemandForecast.setForecastMonth(RandomTestUtil.randomString());

		newIvldDemandForecast.setErrorField(RandomTestUtil.randomString());

		newIvldDemandForecast.setNetSalesPrice(RandomTestUtil.randomString());

		newIvldDemandForecast.setNetSalesAmount(RandomTestUtil.randomString());

		newIvldDemandForecast.setSegment(RandomTestUtil.randomString());

		newIvldDemandForecast.setTotalDemandAmount(RandomTestUtil.randomString());

		newIvldDemandForecast.setForecastName(RandomTestUtil.randomString());

		newIvldDemandForecast.setMarketSizeUnits(RandomTestUtil.randomString());

		newIvldDemandForecast.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldDemandForecasts.add(_persistence.update(newIvldDemandForecast));

		IvldDemandForecast existingIvldDemandForecast = _persistence.findByPrimaryKey(newIvldDemandForecast.getPrimaryKey());

		Assert.assertEquals(existingIvldDemandForecast.getForecastYear(),
			newIvldDemandForecast.getForecastYear());
		Assert.assertEquals(existingIvldDemandForecast.getGrossUnits(),
			newIvldDemandForecast.getGrossUnits());
		Assert.assertEquals(existingIvldDemandForecast.getTotalDemandUnits(),
			newIvldDemandForecast.getTotalDemandUnits());
		Assert.assertEquals(existingIvldDemandForecast.getItemId(),
			newIvldDemandForecast.getItemId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldDemandForecast.getModifiedDate()),
			Time.getShortTimestamp(newIvldDemandForecast.getModifiedDate()));
		Assert.assertEquals(existingIvldDemandForecast.getOrganizationKey(),
			newIvldDemandForecast.getOrganizationKey());
		Assert.assertEquals(existingIvldDemandForecast.getIvldDemandForecastSid(),
			newIvldDemandForecast.getIvldDemandForecastSid());
		Assert.assertEquals(existingIvldDemandForecast.getSource(),
			newIvldDemandForecast.getSource());
		Assert.assertEquals(existingIvldDemandForecast.getMarketShareRatio(),
			newIvldDemandForecast.getMarketShareRatio());
		Assert.assertEquals(existingIvldDemandForecast.getCreatedBy(),
			newIvldDemandForecast.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldDemandForecast.getCreatedDate()),
			Time.getShortTimestamp(newIvldDemandForecast.getCreatedDate()));
		Assert.assertEquals(existingIvldDemandForecast.getAddChgDelIndicator(),
			newIvldDemandForecast.getAddChgDelIndicator());
		Assert.assertEquals(existingIvldDemandForecast.getItemIdentifier(),
			newIvldDemandForecast.getItemIdentifier());
		Assert.assertEquals(existingIvldDemandForecast.getErrorCode(),
			newIvldDemandForecast.getErrorCode());
		Assert.assertEquals(existingIvldDemandForecast.getIntfInsertedDate(),
			newIvldDemandForecast.getIntfInsertedDate());
		Assert.assertEquals(existingIvldDemandForecast.getModifiedBy(),
			newIvldDemandForecast.getModifiedBy());
		Assert.assertEquals(existingIvldDemandForecast.getMarketShareUnits(),
			newIvldDemandForecast.getMarketShareUnits());
		Assert.assertEquals(existingIvldDemandForecast.getInventoryUnitChange(),
			newIvldDemandForecast.getInventoryUnitChange());
		Assert.assertEquals(existingIvldDemandForecast.getReprocessedFlag(),
			newIvldDemandForecast.getReprocessedFlag());
		Assert.assertEquals(existingIvldDemandForecast.getUncapturedUnitsRatio(),
			newIvldDemandForecast.getUncapturedUnitsRatio());
		Assert.assertEquals(existingIvldDemandForecast.getReasonForFailure(),
			newIvldDemandForecast.getReasonForFailure());
		Assert.assertEquals(existingIvldDemandForecast.getCountry(),
			newIvldDemandForecast.getCountry());
		Assert.assertEquals(existingIvldDemandForecast.getForecastType(),
			newIvldDemandForecast.getForecastType());
		Assert.assertEquals(existingIvldDemandForecast.getBrandId(),
			newIvldDemandForecast.getBrandId());
		Assert.assertEquals(existingIvldDemandForecast.getDemandForecastInterfaceId(),
			newIvldDemandForecast.getDemandForecastInterfaceId());
		Assert.assertEquals(existingIvldDemandForecast.getUncapturedUnits(),
			newIvldDemandForecast.getUncapturedUnits());
		Assert.assertEquals(existingIvldDemandForecast.getGrossPrice(),
			newIvldDemandForecast.getGrossPrice());
		Assert.assertEquals(existingIvldDemandForecast.getGrossAmount(),
			newIvldDemandForecast.getGrossAmount());
		Assert.assertEquals(existingIvldDemandForecast.getItemIdentifierCodeQualifier(),
			newIvldDemandForecast.getItemIdentifierCodeQualifier());
		Assert.assertEquals(existingIvldDemandForecast.getForecastVer(),
			newIvldDemandForecast.getForecastVer());
		Assert.assertEquals(existingIvldDemandForecast.getBatchId(),
			newIvldDemandForecast.getBatchId());
		Assert.assertEquals(existingIvldDemandForecast.getForecastMonth(),
			newIvldDemandForecast.getForecastMonth());
		Assert.assertEquals(existingIvldDemandForecast.getErrorField(),
			newIvldDemandForecast.getErrorField());
		Assert.assertEquals(existingIvldDemandForecast.getNetSalesPrice(),
			newIvldDemandForecast.getNetSalesPrice());
		Assert.assertEquals(existingIvldDemandForecast.getNetSalesAmount(),
			newIvldDemandForecast.getNetSalesAmount());
		Assert.assertEquals(existingIvldDemandForecast.getSegment(),
			newIvldDemandForecast.getSegment());
		Assert.assertEquals(existingIvldDemandForecast.getTotalDemandAmount(),
			newIvldDemandForecast.getTotalDemandAmount());
		Assert.assertEquals(existingIvldDemandForecast.getForecastName(),
			newIvldDemandForecast.getForecastName());
		Assert.assertEquals(existingIvldDemandForecast.getMarketSizeUnits(),
			newIvldDemandForecast.getMarketSizeUnits());
		Assert.assertEquals(existingIvldDemandForecast.getCheckRecord(),
			newIvldDemandForecast.getCheckRecord());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		IvldDemandForecast newIvldDemandForecast = addIvldDemandForecast();

		IvldDemandForecast existingIvldDemandForecast = _persistence.findByPrimaryKey(newIvldDemandForecast.getPrimaryKey());

		Assert.assertEquals(existingIvldDemandForecast, newIvldDemandForecast);
	}

	@Test(expected = NoSuchIvldDemandForecastException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<IvldDemandForecast> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("IVLD_DEMAND_FORECAST",
			"forecastYear", true, "grossUnits", true, "totalDemandUnits", true,
			"itemId", true, "modifiedDate", true, "organizationKey", true,
			"ivldDemandForecastSid", true, "source", true, "marketShareRatio",
			true, "createdBy", true, "createdDate", true, "addChgDelIndicator",
			true, "itemIdentifier", true, "errorCode", true,
			"intfInsertedDate", true, "modifiedBy", true, "marketShareUnits",
			true, "inventoryUnitChange", true, "reprocessedFlag", true,
			"uncapturedUnitsRatio", true, "reasonForFailure", true, "country",
			true, "forecastType", true, "brandId", true,
			"demandForecastInterfaceId", true, "uncapturedUnits", true,
			"grossPrice", true, "grossAmount", true,
			"itemIdentifierCodeQualifier", true, "forecastVer", true,
			"batchId", true, "forecastMonth", true, "errorField", true,
			"netSalesPrice", true, "netSalesAmount", true, "segment", true,
			"totalDemandAmount", true, "forecastName", true, "marketSizeUnits",
			true, "checkRecord", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		IvldDemandForecast newIvldDemandForecast = addIvldDemandForecast();

		IvldDemandForecast existingIvldDemandForecast = _persistence.fetchByPrimaryKey(newIvldDemandForecast.getPrimaryKey());

		Assert.assertEquals(existingIvldDemandForecast, newIvldDemandForecast);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldDemandForecast missingIvldDemandForecast = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingIvldDemandForecast);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		IvldDemandForecast newIvldDemandForecast1 = addIvldDemandForecast();
		IvldDemandForecast newIvldDemandForecast2 = addIvldDemandForecast();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldDemandForecast1.getPrimaryKey());
		primaryKeys.add(newIvldDemandForecast2.getPrimaryKey());

		Map<Serializable, IvldDemandForecast> ivldDemandForecasts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ivldDemandForecasts.size());
		Assert.assertEquals(newIvldDemandForecast1,
			ivldDemandForecasts.get(newIvldDemandForecast1.getPrimaryKey()));
		Assert.assertEquals(newIvldDemandForecast2,
			ivldDemandForecasts.get(newIvldDemandForecast2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, IvldDemandForecast> ivldDemandForecasts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldDemandForecasts.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		IvldDemandForecast newIvldDemandForecast = addIvldDemandForecast();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldDemandForecast.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, IvldDemandForecast> ivldDemandForecasts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldDemandForecasts.size());
		Assert.assertEquals(newIvldDemandForecast,
			ivldDemandForecasts.get(newIvldDemandForecast.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, IvldDemandForecast> ivldDemandForecasts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldDemandForecasts.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		IvldDemandForecast newIvldDemandForecast = addIvldDemandForecast();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldDemandForecast.getPrimaryKey());

		Map<Serializable, IvldDemandForecast> ivldDemandForecasts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldDemandForecasts.size());
		Assert.assertEquals(newIvldDemandForecast,
			ivldDemandForecasts.get(newIvldDemandForecast.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = IvldDemandForecastLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<IvldDemandForecast>() {
				@Override
				public void performAction(IvldDemandForecast ivldDemandForecast) {
					Assert.assertNotNull(ivldDemandForecast);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		IvldDemandForecast newIvldDemandForecast = addIvldDemandForecast();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldDemandForecast.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldDemandForecastSid",
				newIvldDemandForecast.getIvldDemandForecastSid()));

		List<IvldDemandForecast> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		IvldDemandForecast existingIvldDemandForecast = result.get(0);

		Assert.assertEquals(existingIvldDemandForecast, newIvldDemandForecast);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldDemandForecast.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldDemandForecastSid",
				RandomTestUtil.nextInt()));

		List<IvldDemandForecast> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		IvldDemandForecast newIvldDemandForecast = addIvldDemandForecast();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldDemandForecast.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldDemandForecastSid"));

		Object newIvldDemandForecastSid = newIvldDemandForecast.getIvldDemandForecastSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldDemandForecastSid",
				new Object[] { newIvldDemandForecastSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingIvldDemandForecastSid = result.get(0);

		Assert.assertEquals(existingIvldDemandForecastSid,
			newIvldDemandForecastSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldDemandForecast.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldDemandForecastSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldDemandForecastSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected IvldDemandForecast addIvldDemandForecast()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldDemandForecast ivldDemandForecast = _persistence.create(pk);

		ivldDemandForecast.setForecastYear(RandomTestUtil.randomString());

		ivldDemandForecast.setGrossUnits(RandomTestUtil.randomString());

		ivldDemandForecast.setTotalDemandUnits(RandomTestUtil.randomString());

		ivldDemandForecast.setItemId(RandomTestUtil.randomString());

		ivldDemandForecast.setModifiedDate(RandomTestUtil.nextDate());

		ivldDemandForecast.setOrganizationKey(RandomTestUtil.randomString());

		ivldDemandForecast.setSource(RandomTestUtil.randomString());

		ivldDemandForecast.setMarketShareRatio(RandomTestUtil.randomString());

		ivldDemandForecast.setCreatedBy(RandomTestUtil.randomString());

		ivldDemandForecast.setCreatedDate(RandomTestUtil.nextDate());

		ivldDemandForecast.setAddChgDelIndicator(RandomTestUtil.randomString());

		ivldDemandForecast.setItemIdentifier(RandomTestUtil.randomString());

		ivldDemandForecast.setErrorCode(RandomTestUtil.randomString());

		ivldDemandForecast.setIntfInsertedDate(RandomTestUtil.randomString());

		ivldDemandForecast.setModifiedBy(RandomTestUtil.randomString());

		ivldDemandForecast.setMarketShareUnits(RandomTestUtil.randomString());

		ivldDemandForecast.setInventoryUnitChange(RandomTestUtil.randomString());

		ivldDemandForecast.setReprocessedFlag(RandomTestUtil.randomString());

		ivldDemandForecast.setUncapturedUnitsRatio(RandomTestUtil.randomString());

		ivldDemandForecast.setReasonForFailure(RandomTestUtil.randomString());

		ivldDemandForecast.setCountry(RandomTestUtil.randomString());

		ivldDemandForecast.setForecastType(RandomTestUtil.randomString());

		ivldDemandForecast.setBrandId(RandomTestUtil.randomString());

		ivldDemandForecast.setDemandForecastInterfaceId(RandomTestUtil.randomString());

		ivldDemandForecast.setUncapturedUnits(RandomTestUtil.randomString());

		ivldDemandForecast.setGrossPrice(RandomTestUtil.randomString());

		ivldDemandForecast.setGrossAmount(RandomTestUtil.randomString());

		ivldDemandForecast.setItemIdentifierCodeQualifier(RandomTestUtil.randomString());

		ivldDemandForecast.setForecastVer(RandomTestUtil.randomString());

		ivldDemandForecast.setBatchId(RandomTestUtil.randomString());

		ivldDemandForecast.setForecastMonth(RandomTestUtil.randomString());

		ivldDemandForecast.setErrorField(RandomTestUtil.randomString());

		ivldDemandForecast.setNetSalesPrice(RandomTestUtil.randomString());

		ivldDemandForecast.setNetSalesAmount(RandomTestUtil.randomString());

		ivldDemandForecast.setSegment(RandomTestUtil.randomString());

		ivldDemandForecast.setTotalDemandAmount(RandomTestUtil.randomString());

		ivldDemandForecast.setForecastName(RandomTestUtil.randomString());

		ivldDemandForecast.setMarketSizeUnits(RandomTestUtil.randomString());

		ivldDemandForecast.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldDemandForecasts.add(_persistence.update(ivldDemandForecast));

		return ivldDemandForecast;
	}

	private List<IvldDemandForecast> _ivldDemandForecasts = new ArrayList<IvldDemandForecast>();
	private IvldDemandForecastPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}