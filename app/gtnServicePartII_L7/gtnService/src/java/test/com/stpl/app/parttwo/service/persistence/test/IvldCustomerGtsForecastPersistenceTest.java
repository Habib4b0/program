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

import com.stpl.app.parttwo.exception.NoSuchIvldCustomerGtsForecastException;
import com.stpl.app.parttwo.model.IvldCustomerGtsForecast;
import com.stpl.app.parttwo.service.IvldCustomerGtsForecastLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.IvldCustomerGtsForecastPersistence;
import com.stpl.app.parttwo.service.persistence.IvldCustomerGtsForecastUtil;

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
public class IvldCustomerGtsForecastPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = IvldCustomerGtsForecastUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<IvldCustomerGtsForecast> iterator = _ivldCustomerGtsForecasts.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldCustomerGtsForecast ivldCustomerGtsForecast = _persistence.create(pk);

		Assert.assertNotNull(ivldCustomerGtsForecast);

		Assert.assertEquals(ivldCustomerGtsForecast.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		IvldCustomerGtsForecast newIvldCustomerGtsForecast = addIvldCustomerGtsForecast();

		_persistence.remove(newIvldCustomerGtsForecast);

		IvldCustomerGtsForecast existingIvldCustomerGtsForecast = _persistence.fetchByPrimaryKey(newIvldCustomerGtsForecast.getPrimaryKey());

		Assert.assertNull(existingIvldCustomerGtsForecast);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addIvldCustomerGtsForecast();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldCustomerGtsForecast newIvldCustomerGtsForecast = _persistence.create(pk);

		newIvldCustomerGtsForecast.setPrice(RandomTestUtil.randomString());

		newIvldCustomerGtsForecast.setForecastYear(RandomTestUtil.randomString());

		newIvldCustomerGtsForecast.setDeductionAmount(RandomTestUtil.randomString());

		newIvldCustomerGtsForecast.setDeductionId(RandomTestUtil.randomString());

		newIvldCustomerGtsForecast.setForecastDate(RandomTestUtil.nextDate());

		newIvldCustomerGtsForecast.setItemId(RandomTestUtil.randomString());

		newIvldCustomerGtsForecast.setModifiedDate(RandomTestUtil.nextDate());

		newIvldCustomerGtsForecast.setSource(RandomTestUtil.randomString());

		newIvldCustomerGtsForecast.setCreatedDate(RandomTestUtil.nextDate());

		newIvldCustomerGtsForecast.setCreatedBy(RandomTestUtil.randomString());

		newIvldCustomerGtsForecast.setAddChgDelIndicator(RandomTestUtil.randomString());

		newIvldCustomerGtsForecast.setErrorCode(RandomTestUtil.randomString());

		newIvldCustomerGtsForecast.setIntfInsertedDate(RandomTestUtil.nextDate());

		newIvldCustomerGtsForecast.setModifiedBy(RandomTestUtil.randomString());

		newIvldCustomerGtsForecast.setSalesAmount(RandomTestUtil.randomString());

		newIvldCustomerGtsForecast.setReprocessedFlag(RandomTestUtil.randomString());

		newIvldCustomerGtsForecast.setUdc6(RandomTestUtil.randomString());

		newIvldCustomerGtsForecast.setUdc5(RandomTestUtil.randomString());

		newIvldCustomerGtsForecast.setDeductionType(RandomTestUtil.randomString());

		newIvldCustomerGtsForecast.setUdc4(RandomTestUtil.randomString());

		newIvldCustomerGtsForecast.setUdc1(RandomTestUtil.randomString());

		newIvldCustomerGtsForecast.setUnits(RandomTestUtil.randomString());

		newIvldCustomerGtsForecast.setDeductionRate(RandomTestUtil.randomString());

		newIvldCustomerGtsForecast.setUdc2(RandomTestUtil.randomString());

		newIvldCustomerGtsForecast.setUdc3(RandomTestUtil.randomString());

		newIvldCustomerGtsForecast.setReasonForFailure(RandomTestUtil.randomString());

		newIvldCustomerGtsForecast.setCountry(RandomTestUtil.randomString());

		newIvldCustomerGtsForecast.setCompanyIdString(RandomTestUtil.randomString());

		newIvldCustomerGtsForecast.setForecastValueType(RandomTestUtil.randomString());

		newIvldCustomerGtsForecast.setDeductionCategory(RandomTestUtil.randomString());

		newIvldCustomerGtsForecast.setAdjustmentCode(RandomTestUtil.randomString());

		newIvldCustomerGtsForecast.setDeductionProgramType(RandomTestUtil.randomString());

		newIvldCustomerGtsForecast.setCustomerGtsForecastIntfId(RandomTestUtil.nextInt());

		newIvldCustomerGtsForecast.setSalesInclusion(RandomTestUtil.randomString());

		newIvldCustomerGtsForecast.setForecastVer(RandomTestUtil.randomString());

		newIvldCustomerGtsForecast.setBatchId(RandomTestUtil.randomString());

		newIvldCustomerGtsForecast.setPriceType(RandomTestUtil.randomString());

		newIvldCustomerGtsForecast.setForecastMonth(RandomTestUtil.randomString());

		newIvldCustomerGtsForecast.setDeductionInclusion(RandomTestUtil.randomString());

		newIvldCustomerGtsForecast.setErrorField(RandomTestUtil.randomString());

		newIvldCustomerGtsForecast.setSegment(RandomTestUtil.randomString());

		newIvldCustomerGtsForecast.setBrand(RandomTestUtil.randomString());

		newIvldCustomerGtsForecast.setForecastName(RandomTestUtil.randomString());

		newIvldCustomerGtsForecast.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldCustomerGtsForecasts.add(_persistence.update(
				newIvldCustomerGtsForecast));

		IvldCustomerGtsForecast existingIvldCustomerGtsForecast = _persistence.findByPrimaryKey(newIvldCustomerGtsForecast.getPrimaryKey());

		Assert.assertEquals(existingIvldCustomerGtsForecast.getPrice(),
			newIvldCustomerGtsForecast.getPrice());
		Assert.assertEquals(existingIvldCustomerGtsForecast.getForecastYear(),
			newIvldCustomerGtsForecast.getForecastYear());
		Assert.assertEquals(existingIvldCustomerGtsForecast.getDeductionAmount(),
			newIvldCustomerGtsForecast.getDeductionAmount());
		Assert.assertEquals(existingIvldCustomerGtsForecast.getIvldCustomerGtsForecastSid(),
			newIvldCustomerGtsForecast.getIvldCustomerGtsForecastSid());
		Assert.assertEquals(existingIvldCustomerGtsForecast.getDeductionId(),
			newIvldCustomerGtsForecast.getDeductionId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldCustomerGtsForecast.getForecastDate()),
			Time.getShortTimestamp(newIvldCustomerGtsForecast.getForecastDate()));
		Assert.assertEquals(existingIvldCustomerGtsForecast.getItemId(),
			newIvldCustomerGtsForecast.getItemId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldCustomerGtsForecast.getModifiedDate()),
			Time.getShortTimestamp(newIvldCustomerGtsForecast.getModifiedDate()));
		Assert.assertEquals(existingIvldCustomerGtsForecast.getSource(),
			newIvldCustomerGtsForecast.getSource());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldCustomerGtsForecast.getCreatedDate()),
			Time.getShortTimestamp(newIvldCustomerGtsForecast.getCreatedDate()));
		Assert.assertEquals(existingIvldCustomerGtsForecast.getCreatedBy(),
			newIvldCustomerGtsForecast.getCreatedBy());
		Assert.assertEquals(existingIvldCustomerGtsForecast.getAddChgDelIndicator(),
			newIvldCustomerGtsForecast.getAddChgDelIndicator());
		Assert.assertEquals(existingIvldCustomerGtsForecast.getErrorCode(),
			newIvldCustomerGtsForecast.getErrorCode());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldCustomerGtsForecast.getIntfInsertedDate()),
			Time.getShortTimestamp(
				newIvldCustomerGtsForecast.getIntfInsertedDate()));
		Assert.assertEquals(existingIvldCustomerGtsForecast.getModifiedBy(),
			newIvldCustomerGtsForecast.getModifiedBy());
		Assert.assertEquals(existingIvldCustomerGtsForecast.getSalesAmount(),
			newIvldCustomerGtsForecast.getSalesAmount());
		Assert.assertEquals(existingIvldCustomerGtsForecast.getReprocessedFlag(),
			newIvldCustomerGtsForecast.getReprocessedFlag());
		Assert.assertEquals(existingIvldCustomerGtsForecast.getUdc6(),
			newIvldCustomerGtsForecast.getUdc6());
		Assert.assertEquals(existingIvldCustomerGtsForecast.getUdc5(),
			newIvldCustomerGtsForecast.getUdc5());
		Assert.assertEquals(existingIvldCustomerGtsForecast.getDeductionType(),
			newIvldCustomerGtsForecast.getDeductionType());
		Assert.assertEquals(existingIvldCustomerGtsForecast.getUdc4(),
			newIvldCustomerGtsForecast.getUdc4());
		Assert.assertEquals(existingIvldCustomerGtsForecast.getUdc1(),
			newIvldCustomerGtsForecast.getUdc1());
		Assert.assertEquals(existingIvldCustomerGtsForecast.getUnits(),
			newIvldCustomerGtsForecast.getUnits());
		Assert.assertEquals(existingIvldCustomerGtsForecast.getDeductionRate(),
			newIvldCustomerGtsForecast.getDeductionRate());
		Assert.assertEquals(existingIvldCustomerGtsForecast.getUdc2(),
			newIvldCustomerGtsForecast.getUdc2());
		Assert.assertEquals(existingIvldCustomerGtsForecast.getUdc3(),
			newIvldCustomerGtsForecast.getUdc3());
		Assert.assertEquals(existingIvldCustomerGtsForecast.getReasonForFailure(),
			newIvldCustomerGtsForecast.getReasonForFailure());
		Assert.assertEquals(existingIvldCustomerGtsForecast.getCountry(),
			newIvldCustomerGtsForecast.getCountry());
		Assert.assertEquals(existingIvldCustomerGtsForecast.getCompanyIdString(),
			newIvldCustomerGtsForecast.getCompanyIdString());
		Assert.assertEquals(existingIvldCustomerGtsForecast.getForecastValueType(),
			newIvldCustomerGtsForecast.getForecastValueType());
		Assert.assertEquals(existingIvldCustomerGtsForecast.getDeductionCategory(),
			newIvldCustomerGtsForecast.getDeductionCategory());
		Assert.assertEquals(existingIvldCustomerGtsForecast.getAdjustmentCode(),
			newIvldCustomerGtsForecast.getAdjustmentCode());
		Assert.assertEquals(existingIvldCustomerGtsForecast.getDeductionProgramType(),
			newIvldCustomerGtsForecast.getDeductionProgramType());
		Assert.assertEquals(existingIvldCustomerGtsForecast.getCustomerGtsForecastIntfId(),
			newIvldCustomerGtsForecast.getCustomerGtsForecastIntfId());
		Assert.assertEquals(existingIvldCustomerGtsForecast.getSalesInclusion(),
			newIvldCustomerGtsForecast.getSalesInclusion());
		Assert.assertEquals(existingIvldCustomerGtsForecast.getForecastVer(),
			newIvldCustomerGtsForecast.getForecastVer());
		Assert.assertEquals(existingIvldCustomerGtsForecast.getBatchId(),
			newIvldCustomerGtsForecast.getBatchId());
		Assert.assertEquals(existingIvldCustomerGtsForecast.getPriceType(),
			newIvldCustomerGtsForecast.getPriceType());
		Assert.assertEquals(existingIvldCustomerGtsForecast.getForecastMonth(),
			newIvldCustomerGtsForecast.getForecastMonth());
		Assert.assertEquals(existingIvldCustomerGtsForecast.getDeductionInclusion(),
			newIvldCustomerGtsForecast.getDeductionInclusion());
		Assert.assertEquals(existingIvldCustomerGtsForecast.getErrorField(),
			newIvldCustomerGtsForecast.getErrorField());
		Assert.assertEquals(existingIvldCustomerGtsForecast.getSegment(),
			newIvldCustomerGtsForecast.getSegment());
		Assert.assertEquals(existingIvldCustomerGtsForecast.getBrand(),
			newIvldCustomerGtsForecast.getBrand());
		Assert.assertEquals(existingIvldCustomerGtsForecast.getForecastName(),
			newIvldCustomerGtsForecast.getForecastName());
		Assert.assertEquals(existingIvldCustomerGtsForecast.getCheckRecord(),
			newIvldCustomerGtsForecast.getCheckRecord());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		IvldCustomerGtsForecast newIvldCustomerGtsForecast = addIvldCustomerGtsForecast();

		IvldCustomerGtsForecast existingIvldCustomerGtsForecast = _persistence.findByPrimaryKey(newIvldCustomerGtsForecast.getPrimaryKey());

		Assert.assertEquals(existingIvldCustomerGtsForecast,
			newIvldCustomerGtsForecast);
	}

	@Test(expected = NoSuchIvldCustomerGtsForecastException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<IvldCustomerGtsForecast> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("IVLD_CUSTOMER_GTS_FORECAST",
			"price", true, "forecastYear", true, "deductionAmount", true,
			"ivldCustomerGtsForecastSid", true, "deductionId", true,
			"forecastDate", true, "itemId", true, "modifiedDate", true,
			"source", true, "createdDate", true, "createdBy", true,
			"addChgDelIndicator", true, "errorCode", true, "intfInsertedDate",
			true, "modifiedBy", true, "salesAmount", true, "reprocessedFlag",
			true, "udc6", true, "udc5", true, "deductionType", true, "udc4",
			true, "udc1", true, "units", true, "deductionRate", true, "udc2",
			true, "udc3", true, "reasonForFailure", true, "country", true,
			"companyIdString", true, "forecastValueType", true,
			"deductionCategory", true, "adjustmentCode", true,
			"deductionProgramType", true, "customerGtsForecastIntfId", true,
			"salesInclusion", true, "forecastVer", true, "batchId", true,
			"priceType", true, "forecastMonth", true, "deductionInclusion",
			true, "errorField", true, "segment", true, "brand", true,
			"forecastName", true, "checkRecord", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		IvldCustomerGtsForecast newIvldCustomerGtsForecast = addIvldCustomerGtsForecast();

		IvldCustomerGtsForecast existingIvldCustomerGtsForecast = _persistence.fetchByPrimaryKey(newIvldCustomerGtsForecast.getPrimaryKey());

		Assert.assertEquals(existingIvldCustomerGtsForecast,
			newIvldCustomerGtsForecast);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldCustomerGtsForecast missingIvldCustomerGtsForecast = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingIvldCustomerGtsForecast);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		IvldCustomerGtsForecast newIvldCustomerGtsForecast1 = addIvldCustomerGtsForecast();
		IvldCustomerGtsForecast newIvldCustomerGtsForecast2 = addIvldCustomerGtsForecast();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldCustomerGtsForecast1.getPrimaryKey());
		primaryKeys.add(newIvldCustomerGtsForecast2.getPrimaryKey());

		Map<Serializable, IvldCustomerGtsForecast> ivldCustomerGtsForecasts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ivldCustomerGtsForecasts.size());
		Assert.assertEquals(newIvldCustomerGtsForecast1,
			ivldCustomerGtsForecasts.get(
				newIvldCustomerGtsForecast1.getPrimaryKey()));
		Assert.assertEquals(newIvldCustomerGtsForecast2,
			ivldCustomerGtsForecasts.get(
				newIvldCustomerGtsForecast2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, IvldCustomerGtsForecast> ivldCustomerGtsForecasts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldCustomerGtsForecasts.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		IvldCustomerGtsForecast newIvldCustomerGtsForecast = addIvldCustomerGtsForecast();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldCustomerGtsForecast.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, IvldCustomerGtsForecast> ivldCustomerGtsForecasts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldCustomerGtsForecasts.size());
		Assert.assertEquals(newIvldCustomerGtsForecast,
			ivldCustomerGtsForecasts.get(
				newIvldCustomerGtsForecast.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, IvldCustomerGtsForecast> ivldCustomerGtsForecasts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldCustomerGtsForecasts.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		IvldCustomerGtsForecast newIvldCustomerGtsForecast = addIvldCustomerGtsForecast();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldCustomerGtsForecast.getPrimaryKey());

		Map<Serializable, IvldCustomerGtsForecast> ivldCustomerGtsForecasts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldCustomerGtsForecasts.size());
		Assert.assertEquals(newIvldCustomerGtsForecast,
			ivldCustomerGtsForecasts.get(
				newIvldCustomerGtsForecast.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = IvldCustomerGtsForecastLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<IvldCustomerGtsForecast>() {
				@Override
				public void performAction(
					IvldCustomerGtsForecast ivldCustomerGtsForecast) {
					Assert.assertNotNull(ivldCustomerGtsForecast);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		IvldCustomerGtsForecast newIvldCustomerGtsForecast = addIvldCustomerGtsForecast();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldCustomerGtsForecast.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"ivldCustomerGtsForecastSid",
				newIvldCustomerGtsForecast.getIvldCustomerGtsForecastSid()));

		List<IvldCustomerGtsForecast> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		IvldCustomerGtsForecast existingIvldCustomerGtsForecast = result.get(0);

		Assert.assertEquals(existingIvldCustomerGtsForecast,
			newIvldCustomerGtsForecast);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldCustomerGtsForecast.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"ivldCustomerGtsForecastSid", RandomTestUtil.nextInt()));

		List<IvldCustomerGtsForecast> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		IvldCustomerGtsForecast newIvldCustomerGtsForecast = addIvldCustomerGtsForecast();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldCustomerGtsForecast.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldCustomerGtsForecastSid"));

		Object newIvldCustomerGtsForecastSid = newIvldCustomerGtsForecast.getIvldCustomerGtsForecastSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"ivldCustomerGtsForecastSid",
				new Object[] { newIvldCustomerGtsForecastSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingIvldCustomerGtsForecastSid = result.get(0);

		Assert.assertEquals(existingIvldCustomerGtsForecastSid,
			newIvldCustomerGtsForecastSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldCustomerGtsForecast.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldCustomerGtsForecastSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"ivldCustomerGtsForecastSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected IvldCustomerGtsForecast addIvldCustomerGtsForecast()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldCustomerGtsForecast ivldCustomerGtsForecast = _persistence.create(pk);

		ivldCustomerGtsForecast.setPrice(RandomTestUtil.randomString());

		ivldCustomerGtsForecast.setForecastYear(RandomTestUtil.randomString());

		ivldCustomerGtsForecast.setDeductionAmount(RandomTestUtil.randomString());

		ivldCustomerGtsForecast.setDeductionId(RandomTestUtil.randomString());

		ivldCustomerGtsForecast.setForecastDate(RandomTestUtil.nextDate());

		ivldCustomerGtsForecast.setItemId(RandomTestUtil.randomString());

		ivldCustomerGtsForecast.setModifiedDate(RandomTestUtil.nextDate());

		ivldCustomerGtsForecast.setSource(RandomTestUtil.randomString());

		ivldCustomerGtsForecast.setCreatedDate(RandomTestUtil.nextDate());

		ivldCustomerGtsForecast.setCreatedBy(RandomTestUtil.randomString());

		ivldCustomerGtsForecast.setAddChgDelIndicator(RandomTestUtil.randomString());

		ivldCustomerGtsForecast.setErrorCode(RandomTestUtil.randomString());

		ivldCustomerGtsForecast.setIntfInsertedDate(RandomTestUtil.nextDate());

		ivldCustomerGtsForecast.setModifiedBy(RandomTestUtil.randomString());

		ivldCustomerGtsForecast.setSalesAmount(RandomTestUtil.randomString());

		ivldCustomerGtsForecast.setReprocessedFlag(RandomTestUtil.randomString());

		ivldCustomerGtsForecast.setUdc6(RandomTestUtil.randomString());

		ivldCustomerGtsForecast.setUdc5(RandomTestUtil.randomString());

		ivldCustomerGtsForecast.setDeductionType(RandomTestUtil.randomString());

		ivldCustomerGtsForecast.setUdc4(RandomTestUtil.randomString());

		ivldCustomerGtsForecast.setUdc1(RandomTestUtil.randomString());

		ivldCustomerGtsForecast.setUnits(RandomTestUtil.randomString());

		ivldCustomerGtsForecast.setDeductionRate(RandomTestUtil.randomString());

		ivldCustomerGtsForecast.setUdc2(RandomTestUtil.randomString());

		ivldCustomerGtsForecast.setUdc3(RandomTestUtil.randomString());

		ivldCustomerGtsForecast.setReasonForFailure(RandomTestUtil.randomString());

		ivldCustomerGtsForecast.setCountry(RandomTestUtil.randomString());

		ivldCustomerGtsForecast.setCompanyIdString(RandomTestUtil.randomString());

		ivldCustomerGtsForecast.setForecastValueType(RandomTestUtil.randomString());

		ivldCustomerGtsForecast.setDeductionCategory(RandomTestUtil.randomString());

		ivldCustomerGtsForecast.setAdjustmentCode(RandomTestUtil.randomString());

		ivldCustomerGtsForecast.setDeductionProgramType(RandomTestUtil.randomString());

		ivldCustomerGtsForecast.setCustomerGtsForecastIntfId(RandomTestUtil.nextInt());

		ivldCustomerGtsForecast.setSalesInclusion(RandomTestUtil.randomString());

		ivldCustomerGtsForecast.setForecastVer(RandomTestUtil.randomString());

		ivldCustomerGtsForecast.setBatchId(RandomTestUtil.randomString());

		ivldCustomerGtsForecast.setPriceType(RandomTestUtil.randomString());

		ivldCustomerGtsForecast.setForecastMonth(RandomTestUtil.randomString());

		ivldCustomerGtsForecast.setDeductionInclusion(RandomTestUtil.randomString());

		ivldCustomerGtsForecast.setErrorField(RandomTestUtil.randomString());

		ivldCustomerGtsForecast.setSegment(RandomTestUtil.randomString());

		ivldCustomerGtsForecast.setBrand(RandomTestUtil.randomString());

		ivldCustomerGtsForecast.setForecastName(RandomTestUtil.randomString());

		ivldCustomerGtsForecast.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldCustomerGtsForecasts.add(_persistence.update(
				ivldCustomerGtsForecast));

		return ivldCustomerGtsForecast;
	}

	private List<IvldCustomerGtsForecast> _ivldCustomerGtsForecasts = new ArrayList<IvldCustomerGtsForecast>();
	private IvldCustomerGtsForecastPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}