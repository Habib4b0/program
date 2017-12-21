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

import com.stpl.app.parttwo.exception.NoSuchVwCustomerGtsForecastException;
import com.stpl.app.parttwo.model.VwCustomerGtsForecast;
import com.stpl.app.parttwo.service.VwCustomerGtsForecastLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.VwCustomerGtsForecastPersistence;
import com.stpl.app.parttwo.service.persistence.VwCustomerGtsForecastUtil;

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
public class VwCustomerGtsForecastPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = VwCustomerGtsForecastUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<VwCustomerGtsForecast> iterator = _vwCustomerGtsForecasts.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwCustomerGtsForecast vwCustomerGtsForecast = _persistence.create(pk);

		Assert.assertNotNull(vwCustomerGtsForecast);

		Assert.assertEquals(vwCustomerGtsForecast.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		VwCustomerGtsForecast newVwCustomerGtsForecast = addVwCustomerGtsForecast();

		_persistence.remove(newVwCustomerGtsForecast);

		VwCustomerGtsForecast existingVwCustomerGtsForecast = _persistence.fetchByPrimaryKey(newVwCustomerGtsForecast.getPrimaryKey());

		Assert.assertNull(existingVwCustomerGtsForecast);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addVwCustomerGtsForecast();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwCustomerGtsForecast newVwCustomerGtsForecast = _persistence.create(pk);

		newVwCustomerGtsForecast.setPrice(RandomTestUtil.nextDouble());

		newVwCustomerGtsForecast.setForecastYear(RandomTestUtil.randomString());

		newVwCustomerGtsForecast.setDeductionAmount(RandomTestUtil.nextDouble());

		newVwCustomerGtsForecast.setDeductionId(RandomTestUtil.randomString());

		newVwCustomerGtsForecast.setForecastDate(RandomTestUtil.nextDate());

		newVwCustomerGtsForecast.setItemId(RandomTestUtil.randomString());

		newVwCustomerGtsForecast.setModifiedDate(RandomTestUtil.nextDate());

		newVwCustomerGtsForecast.setSource(RandomTestUtil.randomString());

		newVwCustomerGtsForecast.setCreatedBy(RandomTestUtil.randomString());

		newVwCustomerGtsForecast.setCreatedDate(RandomTestUtil.nextDate());

		newVwCustomerGtsForecast.setAddChgDelIndicator(RandomTestUtil.randomString());

		newVwCustomerGtsForecast.setModifiedBy(RandomTestUtil.randomString());

		newVwCustomerGtsForecast.setSalesAmount(RandomTestUtil.nextDouble());

		newVwCustomerGtsForecast.setUdc6(RandomTestUtil.randomString());

		newVwCustomerGtsForecast.setUdc5(RandomTestUtil.randomString());

		newVwCustomerGtsForecast.setDeductionType(RandomTestUtil.randomString());

		newVwCustomerGtsForecast.setUdc4(RandomTestUtil.randomString());

		newVwCustomerGtsForecast.setUnits(RandomTestUtil.nextDouble());

		newVwCustomerGtsForecast.setDeductionRate(RandomTestUtil.nextDouble());

		newVwCustomerGtsForecast.setUdc1(RandomTestUtil.randomString());

		newVwCustomerGtsForecast.setUdc2(RandomTestUtil.randomString());

		newVwCustomerGtsForecast.setUdc3(RandomTestUtil.randomString());

		newVwCustomerGtsForecast.setCountry(RandomTestUtil.randomString());

		newVwCustomerGtsForecast.setCompanyId(RandomTestUtil.randomString());

		newVwCustomerGtsForecast.setForecastValueType(RandomTestUtil.randomString());

		newVwCustomerGtsForecast.setDeductionCategory(RandomTestUtil.randomString());

		newVwCustomerGtsForecast.setAdjustmentCode(RandomTestUtil.randomString());

		newVwCustomerGtsForecast.setDeductionProgramType(RandomTestUtil.randomString());

		newVwCustomerGtsForecast.setCustomerGtsForecastIntfId(RandomTestUtil.nextInt());

		newVwCustomerGtsForecast.setSalesInclusion(RandomTestUtil.randomString());

		newVwCustomerGtsForecast.setForecastVer(RandomTestUtil.randomString());

		newVwCustomerGtsForecast.setBatchId(RandomTestUtil.randomString());

		newVwCustomerGtsForecast.setPriceType(RandomTestUtil.randomString());

		newVwCustomerGtsForecast.setForecastMonth(RandomTestUtil.randomString());

		newVwCustomerGtsForecast.setDeductionInclusion(RandomTestUtil.randomString());

		newVwCustomerGtsForecast.setSegment(RandomTestUtil.randomString());

		newVwCustomerGtsForecast.setBrand(RandomTestUtil.randomString());

		newVwCustomerGtsForecast.setForecastName(RandomTestUtil.randomString());

		_vwCustomerGtsForecasts.add(_persistence.update(
				newVwCustomerGtsForecast));

		VwCustomerGtsForecast existingVwCustomerGtsForecast = _persistence.findByPrimaryKey(newVwCustomerGtsForecast.getPrimaryKey());

		AssertUtils.assertEquals(existingVwCustomerGtsForecast.getPrice(),
			newVwCustomerGtsForecast.getPrice());
		Assert.assertEquals(existingVwCustomerGtsForecast.getForecastYear(),
			newVwCustomerGtsForecast.getForecastYear());
		AssertUtils.assertEquals(existingVwCustomerGtsForecast.getDeductionAmount(),
			newVwCustomerGtsForecast.getDeductionAmount());
		Assert.assertEquals(existingVwCustomerGtsForecast.getDeductionId(),
			newVwCustomerGtsForecast.getDeductionId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwCustomerGtsForecast.getForecastDate()),
			Time.getShortTimestamp(newVwCustomerGtsForecast.getForecastDate()));
		Assert.assertEquals(existingVwCustomerGtsForecast.getItemId(),
			newVwCustomerGtsForecast.getItemId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwCustomerGtsForecast.getModifiedDate()),
			Time.getShortTimestamp(newVwCustomerGtsForecast.getModifiedDate()));
		Assert.assertEquals(existingVwCustomerGtsForecast.getSource(),
			newVwCustomerGtsForecast.getSource());
		Assert.assertEquals(existingVwCustomerGtsForecast.getCreatedBy(),
			newVwCustomerGtsForecast.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwCustomerGtsForecast.getCreatedDate()),
			Time.getShortTimestamp(newVwCustomerGtsForecast.getCreatedDate()));
		Assert.assertEquals(existingVwCustomerGtsForecast.getAddChgDelIndicator(),
			newVwCustomerGtsForecast.getAddChgDelIndicator());
		Assert.assertEquals(existingVwCustomerGtsForecast.getModifiedBy(),
			newVwCustomerGtsForecast.getModifiedBy());
		AssertUtils.assertEquals(existingVwCustomerGtsForecast.getSalesAmount(),
			newVwCustomerGtsForecast.getSalesAmount());
		Assert.assertEquals(existingVwCustomerGtsForecast.getUdc6(),
			newVwCustomerGtsForecast.getUdc6());
		Assert.assertEquals(existingVwCustomerGtsForecast.getUdc5(),
			newVwCustomerGtsForecast.getUdc5());
		Assert.assertEquals(existingVwCustomerGtsForecast.getDeductionType(),
			newVwCustomerGtsForecast.getDeductionType());
		Assert.assertEquals(existingVwCustomerGtsForecast.getUdc4(),
			newVwCustomerGtsForecast.getUdc4());
		AssertUtils.assertEquals(existingVwCustomerGtsForecast.getUnits(),
			newVwCustomerGtsForecast.getUnits());
		AssertUtils.assertEquals(existingVwCustomerGtsForecast.getDeductionRate(),
			newVwCustomerGtsForecast.getDeductionRate());
		Assert.assertEquals(existingVwCustomerGtsForecast.getUdc1(),
			newVwCustomerGtsForecast.getUdc1());
		Assert.assertEquals(existingVwCustomerGtsForecast.getCustomerGtsForecastSid(),
			newVwCustomerGtsForecast.getCustomerGtsForecastSid());
		Assert.assertEquals(existingVwCustomerGtsForecast.getUdc2(),
			newVwCustomerGtsForecast.getUdc2());
		Assert.assertEquals(existingVwCustomerGtsForecast.getUdc3(),
			newVwCustomerGtsForecast.getUdc3());
		Assert.assertEquals(existingVwCustomerGtsForecast.getCountry(),
			newVwCustomerGtsForecast.getCountry());
		Assert.assertEquals(existingVwCustomerGtsForecast.getCompanyId(),
			newVwCustomerGtsForecast.getCompanyId());
		Assert.assertEquals(existingVwCustomerGtsForecast.getForecastValueType(),
			newVwCustomerGtsForecast.getForecastValueType());
		Assert.assertEquals(existingVwCustomerGtsForecast.getDeductionCategory(),
			newVwCustomerGtsForecast.getDeductionCategory());
		Assert.assertEquals(existingVwCustomerGtsForecast.getAdjustmentCode(),
			newVwCustomerGtsForecast.getAdjustmentCode());
		Assert.assertEquals(existingVwCustomerGtsForecast.getDeductionProgramType(),
			newVwCustomerGtsForecast.getDeductionProgramType());
		Assert.assertEquals(existingVwCustomerGtsForecast.getCustomerGtsForecastIntfId(),
			newVwCustomerGtsForecast.getCustomerGtsForecastIntfId());
		Assert.assertEquals(existingVwCustomerGtsForecast.getSalesInclusion(),
			newVwCustomerGtsForecast.getSalesInclusion());
		Assert.assertEquals(existingVwCustomerGtsForecast.getForecastVer(),
			newVwCustomerGtsForecast.getForecastVer());
		Assert.assertEquals(existingVwCustomerGtsForecast.getBatchId(),
			newVwCustomerGtsForecast.getBatchId());
		Assert.assertEquals(existingVwCustomerGtsForecast.getPriceType(),
			newVwCustomerGtsForecast.getPriceType());
		Assert.assertEquals(existingVwCustomerGtsForecast.getForecastMonth(),
			newVwCustomerGtsForecast.getForecastMonth());
		Assert.assertEquals(existingVwCustomerGtsForecast.getDeductionInclusion(),
			newVwCustomerGtsForecast.getDeductionInclusion());
		Assert.assertEquals(existingVwCustomerGtsForecast.getSegment(),
			newVwCustomerGtsForecast.getSegment());
		Assert.assertEquals(existingVwCustomerGtsForecast.getBrand(),
			newVwCustomerGtsForecast.getBrand());
		Assert.assertEquals(existingVwCustomerGtsForecast.getForecastName(),
			newVwCustomerGtsForecast.getForecastName());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		VwCustomerGtsForecast newVwCustomerGtsForecast = addVwCustomerGtsForecast();

		VwCustomerGtsForecast existingVwCustomerGtsForecast = _persistence.findByPrimaryKey(newVwCustomerGtsForecast.getPrimaryKey());

		Assert.assertEquals(existingVwCustomerGtsForecast,
			newVwCustomerGtsForecast);
	}

	@Test(expected = NoSuchVwCustomerGtsForecastException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<VwCustomerGtsForecast> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("VW_CUSTOMER_GTS_FORECAST",
			"price", true, "forecastYear", true, "deductionAmount", true,
			"deductionId", true, "forecastDate", true, "itemId", true,
			"modifiedDate", true, "source", true, "createdBy", true,
			"createdDate", true, "addChgDelIndicator", true, "modifiedBy",
			true, "salesAmount", true, "udc6", true, "udc5", true,
			"deductionType", true, "udc4", true, "units", true,
			"deductionRate", true, "udc1", true, "customerGtsForecastSid",
			true, "udc2", true, "udc3", true, "country", true, "companyId",
			true, "forecastValueType", true, "deductionCategory", true,
			"adjustmentCode", true, "deductionProgramType", true,
			"customerGtsForecastIntfId", true, "salesInclusion", true,
			"forecastVer", true, "batchId", true, "priceType", true,
			"forecastMonth", true, "deductionInclusion", true, "segment", true,
			"brand", true, "forecastName", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		VwCustomerGtsForecast newVwCustomerGtsForecast = addVwCustomerGtsForecast();

		VwCustomerGtsForecast existingVwCustomerGtsForecast = _persistence.fetchByPrimaryKey(newVwCustomerGtsForecast.getPrimaryKey());

		Assert.assertEquals(existingVwCustomerGtsForecast,
			newVwCustomerGtsForecast);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwCustomerGtsForecast missingVwCustomerGtsForecast = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingVwCustomerGtsForecast);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		VwCustomerGtsForecast newVwCustomerGtsForecast1 = addVwCustomerGtsForecast();
		VwCustomerGtsForecast newVwCustomerGtsForecast2 = addVwCustomerGtsForecast();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwCustomerGtsForecast1.getPrimaryKey());
		primaryKeys.add(newVwCustomerGtsForecast2.getPrimaryKey());

		Map<Serializable, VwCustomerGtsForecast> vwCustomerGtsForecasts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, vwCustomerGtsForecasts.size());
		Assert.assertEquals(newVwCustomerGtsForecast1,
			vwCustomerGtsForecasts.get(
				newVwCustomerGtsForecast1.getPrimaryKey()));
		Assert.assertEquals(newVwCustomerGtsForecast2,
			vwCustomerGtsForecasts.get(
				newVwCustomerGtsForecast2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, VwCustomerGtsForecast> vwCustomerGtsForecasts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(vwCustomerGtsForecasts.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		VwCustomerGtsForecast newVwCustomerGtsForecast = addVwCustomerGtsForecast();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwCustomerGtsForecast.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, VwCustomerGtsForecast> vwCustomerGtsForecasts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, vwCustomerGtsForecasts.size());
		Assert.assertEquals(newVwCustomerGtsForecast,
			vwCustomerGtsForecasts.get(newVwCustomerGtsForecast.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, VwCustomerGtsForecast> vwCustomerGtsForecasts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(vwCustomerGtsForecasts.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		VwCustomerGtsForecast newVwCustomerGtsForecast = addVwCustomerGtsForecast();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwCustomerGtsForecast.getPrimaryKey());

		Map<Serializable, VwCustomerGtsForecast> vwCustomerGtsForecasts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, vwCustomerGtsForecasts.size());
		Assert.assertEquals(newVwCustomerGtsForecast,
			vwCustomerGtsForecasts.get(newVwCustomerGtsForecast.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = VwCustomerGtsForecastLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<VwCustomerGtsForecast>() {
				@Override
				public void performAction(
					VwCustomerGtsForecast vwCustomerGtsForecast) {
					Assert.assertNotNull(vwCustomerGtsForecast);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		VwCustomerGtsForecast newVwCustomerGtsForecast = addVwCustomerGtsForecast();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwCustomerGtsForecast.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("customerGtsForecastSid",
				newVwCustomerGtsForecast.getCustomerGtsForecastSid()));

		List<VwCustomerGtsForecast> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		VwCustomerGtsForecast existingVwCustomerGtsForecast = result.get(0);

		Assert.assertEquals(existingVwCustomerGtsForecast,
			newVwCustomerGtsForecast);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwCustomerGtsForecast.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("customerGtsForecastSid",
				RandomTestUtil.nextInt()));

		List<VwCustomerGtsForecast> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		VwCustomerGtsForecast newVwCustomerGtsForecast = addVwCustomerGtsForecast();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwCustomerGtsForecast.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"customerGtsForecastSid"));

		Object newCustomerGtsForecastSid = newVwCustomerGtsForecast.getCustomerGtsForecastSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("customerGtsForecastSid",
				new Object[] { newCustomerGtsForecastSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCustomerGtsForecastSid = result.get(0);

		Assert.assertEquals(existingCustomerGtsForecastSid,
			newCustomerGtsForecastSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwCustomerGtsForecast.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"customerGtsForecastSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("customerGtsForecastSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected VwCustomerGtsForecast addVwCustomerGtsForecast()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwCustomerGtsForecast vwCustomerGtsForecast = _persistence.create(pk);

		vwCustomerGtsForecast.setPrice(RandomTestUtil.nextDouble());

		vwCustomerGtsForecast.setForecastYear(RandomTestUtil.randomString());

		vwCustomerGtsForecast.setDeductionAmount(RandomTestUtil.nextDouble());

		vwCustomerGtsForecast.setDeductionId(RandomTestUtil.randomString());

		vwCustomerGtsForecast.setForecastDate(RandomTestUtil.nextDate());

		vwCustomerGtsForecast.setItemId(RandomTestUtil.randomString());

		vwCustomerGtsForecast.setModifiedDate(RandomTestUtil.nextDate());

		vwCustomerGtsForecast.setSource(RandomTestUtil.randomString());

		vwCustomerGtsForecast.setCreatedBy(RandomTestUtil.randomString());

		vwCustomerGtsForecast.setCreatedDate(RandomTestUtil.nextDate());

		vwCustomerGtsForecast.setAddChgDelIndicator(RandomTestUtil.randomString());

		vwCustomerGtsForecast.setModifiedBy(RandomTestUtil.randomString());

		vwCustomerGtsForecast.setSalesAmount(RandomTestUtil.nextDouble());

		vwCustomerGtsForecast.setUdc6(RandomTestUtil.randomString());

		vwCustomerGtsForecast.setUdc5(RandomTestUtil.randomString());

		vwCustomerGtsForecast.setDeductionType(RandomTestUtil.randomString());

		vwCustomerGtsForecast.setUdc4(RandomTestUtil.randomString());

		vwCustomerGtsForecast.setUnits(RandomTestUtil.nextDouble());

		vwCustomerGtsForecast.setDeductionRate(RandomTestUtil.nextDouble());

		vwCustomerGtsForecast.setUdc1(RandomTestUtil.randomString());

		vwCustomerGtsForecast.setUdc2(RandomTestUtil.randomString());

		vwCustomerGtsForecast.setUdc3(RandomTestUtil.randomString());

		vwCustomerGtsForecast.setCountry(RandomTestUtil.randomString());

		vwCustomerGtsForecast.setCompanyId(RandomTestUtil.randomString());

		vwCustomerGtsForecast.setForecastValueType(RandomTestUtil.randomString());

		vwCustomerGtsForecast.setDeductionCategory(RandomTestUtil.randomString());

		vwCustomerGtsForecast.setAdjustmentCode(RandomTestUtil.randomString());

		vwCustomerGtsForecast.setDeductionProgramType(RandomTestUtil.randomString());

		vwCustomerGtsForecast.setCustomerGtsForecastIntfId(RandomTestUtil.nextInt());

		vwCustomerGtsForecast.setSalesInclusion(RandomTestUtil.randomString());

		vwCustomerGtsForecast.setForecastVer(RandomTestUtil.randomString());

		vwCustomerGtsForecast.setBatchId(RandomTestUtil.randomString());

		vwCustomerGtsForecast.setPriceType(RandomTestUtil.randomString());

		vwCustomerGtsForecast.setForecastMonth(RandomTestUtil.randomString());

		vwCustomerGtsForecast.setDeductionInclusion(RandomTestUtil.randomString());

		vwCustomerGtsForecast.setSegment(RandomTestUtil.randomString());

		vwCustomerGtsForecast.setBrand(RandomTestUtil.randomString());

		vwCustomerGtsForecast.setForecastName(RandomTestUtil.randomString());

		_vwCustomerGtsForecasts.add(_persistence.update(vwCustomerGtsForecast));

		return vwCustomerGtsForecast;
	}

	private List<VwCustomerGtsForecast> _vwCustomerGtsForecasts = new ArrayList<VwCustomerGtsForecast>();
	private VwCustomerGtsForecastPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}