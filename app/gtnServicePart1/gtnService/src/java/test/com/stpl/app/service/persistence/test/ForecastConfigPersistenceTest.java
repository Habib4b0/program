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

import com.stpl.app.exception.NoSuchForecastConfigException;
import com.stpl.app.model.ForecastConfig;
import com.stpl.app.service.ForecastConfigLocalServiceUtil;
import com.stpl.app.service.persistence.ForecastConfigPersistence;
import com.stpl.app.service.persistence.ForecastConfigUtil;

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
public class ForecastConfigPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ForecastConfigUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ForecastConfig> iterator = _forecastConfigs.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ForecastConfig forecastConfig = _persistence.create(pk);

		Assert.assertNotNull(forecastConfig);

		Assert.assertEquals(forecastConfig.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ForecastConfig newForecastConfig = addForecastConfig();

		_persistence.remove(newForecastConfig);

		ForecastConfig existingForecastConfig = _persistence.fetchByPrimaryKey(newForecastConfig.getPrimaryKey());

		Assert.assertNull(existingForecastConfig);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addForecastConfig();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ForecastConfig newForecastConfig = _persistence.create(pk);

		newForecastConfig.setProcessType(RandomTestUtil.randomBoolean());

		newForecastConfig.setToDate(RandomTestUtil.nextDate());

		newForecastConfig.setVersionNo(RandomTestUtil.nextInt());

		newForecastConfig.setModifiedDate(RandomTestUtil.nextDate());

		newForecastConfig.setFromDate(RandomTestUtil.nextDate());

		newForecastConfig.setProjValue(RandomTestUtil.nextInt());

		newForecastConfig.setCreatedBy(RandomTestUtil.nextInt());

		newForecastConfig.setCreatedDate(RandomTestUtil.nextDate());

		newForecastConfig.setProjFreq(RandomTestUtil.nextInt());

		newForecastConfig.setHistValue(RandomTestUtil.nextInt());

		newForecastConfig.setBusinessProcessType(RandomTestUtil.nextInt());

		newForecastConfig.setModifiedBy(RandomTestUtil.nextInt());

		newForecastConfig.setHistFreq(RandomTestUtil.nextInt());

		newForecastConfig.setActiveStartDate(RandomTestUtil.nextDate());

		newForecastConfig.setActiveEndDate(RandomTestUtil.nextDate());

		newForecastConfig.setProcessMode(RandomTestUtil.randomBoolean());

		newForecastConfig.setHistoricalDataIntervalFrom(RandomTestUtil.nextDate());

		newForecastConfig.setHistoricalTimePeriodFrom(RandomTestUtil.nextDate());

		newForecastConfig.setProjHistFreq(RandomTestUtil.nextInt());

		newForecastConfig.setFutureTimePeriodFrom(RandomTestUtil.nextDate());

		newForecastConfig.setHistoricalDataIntervalTo(RandomTestUtil.nextDate());

		newForecastConfig.setProjHistValue(RandomTestUtil.nextInt());

		_forecastConfigs.add(_persistence.update(newForecastConfig));

		ForecastConfig existingForecastConfig = _persistence.findByPrimaryKey(newForecastConfig.getPrimaryKey());

		Assert.assertEquals(existingForecastConfig.getProcessType(),
			newForecastConfig.getProcessType());
		Assert.assertEquals(Time.getShortTimestamp(
				existingForecastConfig.getToDate()),
			Time.getShortTimestamp(newForecastConfig.getToDate()));
		Assert.assertEquals(existingForecastConfig.getVersionNo(),
			newForecastConfig.getVersionNo());
		Assert.assertEquals(existingForecastConfig.getForecastConfigSid(),
			newForecastConfig.getForecastConfigSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingForecastConfig.getModifiedDate()),
			Time.getShortTimestamp(newForecastConfig.getModifiedDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingForecastConfig.getFromDate()),
			Time.getShortTimestamp(newForecastConfig.getFromDate()));
		Assert.assertEquals(existingForecastConfig.getProjValue(),
			newForecastConfig.getProjValue());
		Assert.assertEquals(existingForecastConfig.getCreatedBy(),
			newForecastConfig.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingForecastConfig.getCreatedDate()),
			Time.getShortTimestamp(newForecastConfig.getCreatedDate()));
		Assert.assertEquals(existingForecastConfig.getProjFreq(),
			newForecastConfig.getProjFreq());
		Assert.assertEquals(existingForecastConfig.getHistValue(),
			newForecastConfig.getHistValue());
		Assert.assertEquals(existingForecastConfig.getBusinessProcessType(),
			newForecastConfig.getBusinessProcessType());
		Assert.assertEquals(existingForecastConfig.getModifiedBy(),
			newForecastConfig.getModifiedBy());
		Assert.assertEquals(existingForecastConfig.getHistFreq(),
			newForecastConfig.getHistFreq());
		Assert.assertEquals(Time.getShortTimestamp(
				existingForecastConfig.getActiveStartDate()),
			Time.getShortTimestamp(newForecastConfig.getActiveStartDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingForecastConfig.getActiveEndDate()),
			Time.getShortTimestamp(newForecastConfig.getActiveEndDate()));
		Assert.assertEquals(existingForecastConfig.getProcessMode(),
			newForecastConfig.getProcessMode());
		Assert.assertEquals(Time.getShortTimestamp(
				existingForecastConfig.getHistoricalDataIntervalFrom()),
			Time.getShortTimestamp(
				newForecastConfig.getHistoricalDataIntervalFrom()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingForecastConfig.getHistoricalTimePeriodFrom()),
			Time.getShortTimestamp(
				newForecastConfig.getHistoricalTimePeriodFrom()));
		Assert.assertEquals(existingForecastConfig.getProjHistFreq(),
			newForecastConfig.getProjHistFreq());
		Assert.assertEquals(Time.getShortTimestamp(
				existingForecastConfig.getFutureTimePeriodFrom()),
			Time.getShortTimestamp(newForecastConfig.getFutureTimePeriodFrom()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingForecastConfig.getHistoricalDataIntervalTo()),
			Time.getShortTimestamp(
				newForecastConfig.getHistoricalDataIntervalTo()));
		Assert.assertEquals(existingForecastConfig.getProjHistValue(),
			newForecastConfig.getProjHistValue());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ForecastConfig newForecastConfig = addForecastConfig();

		ForecastConfig existingForecastConfig = _persistence.findByPrimaryKey(newForecastConfig.getPrimaryKey());

		Assert.assertEquals(existingForecastConfig, newForecastConfig);
	}

	@Test(expected = NoSuchForecastConfigException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<ForecastConfig> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("FORECAST_CONFIG",
			"processType", true, "toDate", true, "versionNo", true,
			"forecastConfigSid", true, "modifiedDate", true, "fromDate", true,
			"projValue", true, "createdBy", true, "createdDate", true,
			"projFreq", true, "histValue", true, "businessProcessType", true,
			"modifiedBy", true, "histFreq", true, "activeStartDate", true,
			"activeEndDate", true, "processMode", true,
			"historicalDataIntervalFrom", true, "historicalTimePeriodFrom",
			true, "projHistFreq", true, "futureTimePeriodFrom", true,
			"historicalDataIntervalTo", true, "projHistValue", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ForecastConfig newForecastConfig = addForecastConfig();

		ForecastConfig existingForecastConfig = _persistence.fetchByPrimaryKey(newForecastConfig.getPrimaryKey());

		Assert.assertEquals(existingForecastConfig, newForecastConfig);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ForecastConfig missingForecastConfig = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingForecastConfig);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ForecastConfig newForecastConfig1 = addForecastConfig();
		ForecastConfig newForecastConfig2 = addForecastConfig();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newForecastConfig1.getPrimaryKey());
		primaryKeys.add(newForecastConfig2.getPrimaryKey());

		Map<Serializable, ForecastConfig> forecastConfigs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, forecastConfigs.size());
		Assert.assertEquals(newForecastConfig1,
			forecastConfigs.get(newForecastConfig1.getPrimaryKey()));
		Assert.assertEquals(newForecastConfig2,
			forecastConfigs.get(newForecastConfig2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ForecastConfig> forecastConfigs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(forecastConfigs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ForecastConfig newForecastConfig = addForecastConfig();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newForecastConfig.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ForecastConfig> forecastConfigs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, forecastConfigs.size());
		Assert.assertEquals(newForecastConfig,
			forecastConfigs.get(newForecastConfig.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ForecastConfig> forecastConfigs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(forecastConfigs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ForecastConfig newForecastConfig = addForecastConfig();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newForecastConfig.getPrimaryKey());

		Map<Serializable, ForecastConfig> forecastConfigs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, forecastConfigs.size());
		Assert.assertEquals(newForecastConfig,
			forecastConfigs.get(newForecastConfig.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ForecastConfigLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ForecastConfig>() {
				@Override
				public void performAction(ForecastConfig forecastConfig) {
					Assert.assertNotNull(forecastConfig);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ForecastConfig newForecastConfig = addForecastConfig();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ForecastConfig.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("forecastConfigSid",
				newForecastConfig.getForecastConfigSid()));

		List<ForecastConfig> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ForecastConfig existingForecastConfig = result.get(0);

		Assert.assertEquals(existingForecastConfig, newForecastConfig);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ForecastConfig.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("forecastConfigSid",
				RandomTestUtil.nextInt()));

		List<ForecastConfig> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ForecastConfig newForecastConfig = addForecastConfig();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ForecastConfig.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"forecastConfigSid"));

		Object newForecastConfigSid = newForecastConfig.getForecastConfigSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("forecastConfigSid",
				new Object[] { newForecastConfigSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingForecastConfigSid = result.get(0);

		Assert.assertEquals(existingForecastConfigSid, newForecastConfigSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ForecastConfig.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"forecastConfigSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("forecastConfigSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ForecastConfig addForecastConfig() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ForecastConfig forecastConfig = _persistence.create(pk);

		forecastConfig.setProcessType(RandomTestUtil.randomBoolean());

		forecastConfig.setToDate(RandomTestUtil.nextDate());

		forecastConfig.setVersionNo(RandomTestUtil.nextInt());

		forecastConfig.setModifiedDate(RandomTestUtil.nextDate());

		forecastConfig.setFromDate(RandomTestUtil.nextDate());

		forecastConfig.setProjValue(RandomTestUtil.nextInt());

		forecastConfig.setCreatedBy(RandomTestUtil.nextInt());

		forecastConfig.setCreatedDate(RandomTestUtil.nextDate());

		forecastConfig.setProjFreq(RandomTestUtil.nextInt());

		forecastConfig.setHistValue(RandomTestUtil.nextInt());

		forecastConfig.setBusinessProcessType(RandomTestUtil.nextInt());

		forecastConfig.setModifiedBy(RandomTestUtil.nextInt());

		forecastConfig.setHistFreq(RandomTestUtil.nextInt());

		forecastConfig.setActiveStartDate(RandomTestUtil.nextDate());

		forecastConfig.setActiveEndDate(RandomTestUtil.nextDate());

		forecastConfig.setProcessMode(RandomTestUtil.randomBoolean());

		forecastConfig.setHistoricalDataIntervalFrom(RandomTestUtil.nextDate());

		forecastConfig.setHistoricalTimePeriodFrom(RandomTestUtil.nextDate());

		forecastConfig.setProjHistFreq(RandomTestUtil.nextInt());

		forecastConfig.setFutureTimePeriodFrom(RandomTestUtil.nextDate());

		forecastConfig.setHistoricalDataIntervalTo(RandomTestUtil.nextDate());

		forecastConfig.setProjHistValue(RandomTestUtil.nextInt());

		_forecastConfigs.add(_persistence.update(forecastConfig));

		return forecastConfig;
	}

	private List<ForecastConfig> _forecastConfigs = new ArrayList<ForecastConfig>();
	private ForecastConfigPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}