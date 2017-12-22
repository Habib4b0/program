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

import com.stpl.app.parttwo.exception.NoSuchCustomerGtsForecastException;
import com.stpl.app.parttwo.model.CustomerGtsForecast;
import com.stpl.app.parttwo.service.CustomerGtsForecastLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.CustomerGtsForecastPersistence;
import com.stpl.app.parttwo.service.persistence.CustomerGtsForecastUtil;

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
public class CustomerGtsForecastPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = CustomerGtsForecastUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CustomerGtsForecast> iterator = _customerGtsForecasts.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CustomerGtsForecast customerGtsForecast = _persistence.create(pk);

		Assert.assertNotNull(customerGtsForecast);

		Assert.assertEquals(customerGtsForecast.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CustomerGtsForecast newCustomerGtsForecast = addCustomerGtsForecast();

		_persistence.remove(newCustomerGtsForecast);

		CustomerGtsForecast existingCustomerGtsForecast = _persistence.fetchByPrimaryKey(newCustomerGtsForecast.getPrimaryKey());

		Assert.assertNull(existingCustomerGtsForecast);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCustomerGtsForecast();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CustomerGtsForecast newCustomerGtsForecast = _persistence.create(pk);

		newCustomerGtsForecast.setPrice(RandomTestUtil.randomString());

		newCustomerGtsForecast.setItemMasterSid(RandomTestUtil.nextInt());

		newCustomerGtsForecast.setForecastYear(RandomTestUtil.randomString());

		newCustomerGtsForecast.setDeductionAmount(RandomTestUtil.randomString());

		newCustomerGtsForecast.setDeductionId(RandomTestUtil.randomString());

		newCustomerGtsForecast.setForecastDate(RandomTestUtil.nextDate());

		newCustomerGtsForecast.setItemId(RandomTestUtil.randomString());

		newCustomerGtsForecast.setModifiedDate(RandomTestUtil.nextDate());

		newCustomerGtsForecast.setBrandMasterSid(RandomTestUtil.nextInt());

		newCustomerGtsForecast.setSource(RandomTestUtil.randomString());

		newCustomerGtsForecast.setCreatedDate(RandomTestUtil.nextDate());

		newCustomerGtsForecast.setCreatedBy(RandomTestUtil.randomString());

		newCustomerGtsForecast.setAddChgDelIndicator(RandomTestUtil.randomString());

		newCustomerGtsForecast.setInboundStatus(RandomTestUtil.randomString());

		newCustomerGtsForecast.setModifiedBy(RandomTestUtil.randomString());

		newCustomerGtsForecast.setSalesAmount(RandomTestUtil.randomString());

		newCustomerGtsForecast.setDeductionType(RandomTestUtil.randomString());

		newCustomerGtsForecast.setCompanyMasterSid(RandomTestUtil.nextInt());

		newCustomerGtsForecast.setUnits(RandomTestUtil.randomString());

		newCustomerGtsForecast.setDeductionRate(RandomTestUtil.randomString());

		newCustomerGtsForecast.setCountry(RandomTestUtil.randomString());

		newCustomerGtsForecast.setCompanyIdString(RandomTestUtil.randomString());

		newCustomerGtsForecast.setForecastValueType(RandomTestUtil.randomString());

		newCustomerGtsForecast.setDeductionCategory(RandomTestUtil.randomString());

		newCustomerGtsForecast.setAdjustmentCode(RandomTestUtil.randomString());

		newCustomerGtsForecast.setDeductionProgramType(RandomTestUtil.randomString());

		newCustomerGtsForecast.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newCustomerGtsForecast.setSalesInclusion(RandomTestUtil.randomString());

		newCustomerGtsForecast.setForecastVer(RandomTestUtil.randomString());

		newCustomerGtsForecast.setBatchId(RandomTestUtil.randomString());

		newCustomerGtsForecast.setPriceType(RandomTestUtil.randomString());

		newCustomerGtsForecast.setForecastMonth(RandomTestUtil.randomString());

		newCustomerGtsForecast.setDeductionInclusion(RandomTestUtil.randomString());

		newCustomerGtsForecast.setSegment(RandomTestUtil.randomString());

		newCustomerGtsForecast.setBrand(RandomTestUtil.randomString());

		newCustomerGtsForecast.setForecastName(RandomTestUtil.randomString());

		_customerGtsForecasts.add(_persistence.update(newCustomerGtsForecast));

		CustomerGtsForecast existingCustomerGtsForecast = _persistence.findByPrimaryKey(newCustomerGtsForecast.getPrimaryKey());

		Assert.assertEquals(existingCustomerGtsForecast.getPrice(),
			newCustomerGtsForecast.getPrice());
		Assert.assertEquals(existingCustomerGtsForecast.getItemMasterSid(),
			newCustomerGtsForecast.getItemMasterSid());
		Assert.assertEquals(existingCustomerGtsForecast.getForecastYear(),
			newCustomerGtsForecast.getForecastYear());
		Assert.assertEquals(existingCustomerGtsForecast.getDeductionAmount(),
			newCustomerGtsForecast.getDeductionAmount());
		Assert.assertEquals(existingCustomerGtsForecast.getDeductionId(),
			newCustomerGtsForecast.getDeductionId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCustomerGtsForecast.getForecastDate()),
			Time.getShortTimestamp(newCustomerGtsForecast.getForecastDate()));
		Assert.assertEquals(existingCustomerGtsForecast.getItemId(),
			newCustomerGtsForecast.getItemId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCustomerGtsForecast.getModifiedDate()),
			Time.getShortTimestamp(newCustomerGtsForecast.getModifiedDate()));
		Assert.assertEquals(existingCustomerGtsForecast.getBrandMasterSid(),
			newCustomerGtsForecast.getBrandMasterSid());
		Assert.assertEquals(existingCustomerGtsForecast.getSource(),
			newCustomerGtsForecast.getSource());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCustomerGtsForecast.getCreatedDate()),
			Time.getShortTimestamp(newCustomerGtsForecast.getCreatedDate()));
		Assert.assertEquals(existingCustomerGtsForecast.getCreatedBy(),
			newCustomerGtsForecast.getCreatedBy());
		Assert.assertEquals(existingCustomerGtsForecast.getAddChgDelIndicator(),
			newCustomerGtsForecast.getAddChgDelIndicator());
		Assert.assertEquals(existingCustomerGtsForecast.getInboundStatus(),
			newCustomerGtsForecast.getInboundStatus());
		Assert.assertEquals(existingCustomerGtsForecast.getModifiedBy(),
			newCustomerGtsForecast.getModifiedBy());
		Assert.assertEquals(existingCustomerGtsForecast.getSalesAmount(),
			newCustomerGtsForecast.getSalesAmount());
		Assert.assertEquals(existingCustomerGtsForecast.getDeductionType(),
			newCustomerGtsForecast.getDeductionType());
		Assert.assertEquals(existingCustomerGtsForecast.getCompanyMasterSid(),
			newCustomerGtsForecast.getCompanyMasterSid());
		Assert.assertEquals(existingCustomerGtsForecast.getUnits(),
			newCustomerGtsForecast.getUnits());
		Assert.assertEquals(existingCustomerGtsForecast.getDeductionRate(),
			newCustomerGtsForecast.getDeductionRate());
		Assert.assertEquals(existingCustomerGtsForecast.getCustomerGtsForecastSid(),
			newCustomerGtsForecast.getCustomerGtsForecastSid());
		Assert.assertEquals(existingCustomerGtsForecast.getCountry(),
			newCustomerGtsForecast.getCountry());
		Assert.assertEquals(existingCustomerGtsForecast.getCompanyIdString(),
			newCustomerGtsForecast.getCompanyIdString());
		Assert.assertEquals(existingCustomerGtsForecast.getForecastValueType(),
			newCustomerGtsForecast.getForecastValueType());
		Assert.assertEquals(existingCustomerGtsForecast.getDeductionCategory(),
			newCustomerGtsForecast.getDeductionCategory());
		Assert.assertEquals(existingCustomerGtsForecast.getAdjustmentCode(),
			newCustomerGtsForecast.getAdjustmentCode());
		Assert.assertEquals(existingCustomerGtsForecast.getDeductionProgramType(),
			newCustomerGtsForecast.getDeductionProgramType());
		Assert.assertEquals(existingCustomerGtsForecast.getRecordLockStatus(),
			newCustomerGtsForecast.getRecordLockStatus());
		Assert.assertEquals(existingCustomerGtsForecast.getSalesInclusion(),
			newCustomerGtsForecast.getSalesInclusion());
		Assert.assertEquals(existingCustomerGtsForecast.getForecastVer(),
			newCustomerGtsForecast.getForecastVer());
		Assert.assertEquals(existingCustomerGtsForecast.getBatchId(),
			newCustomerGtsForecast.getBatchId());
		Assert.assertEquals(existingCustomerGtsForecast.getPriceType(),
			newCustomerGtsForecast.getPriceType());
		Assert.assertEquals(existingCustomerGtsForecast.getForecastMonth(),
			newCustomerGtsForecast.getForecastMonth());
		Assert.assertEquals(existingCustomerGtsForecast.getDeductionInclusion(),
			newCustomerGtsForecast.getDeductionInclusion());
		Assert.assertEquals(existingCustomerGtsForecast.getSegment(),
			newCustomerGtsForecast.getSegment());
		Assert.assertEquals(existingCustomerGtsForecast.getBrand(),
			newCustomerGtsForecast.getBrand());
		Assert.assertEquals(existingCustomerGtsForecast.getForecastName(),
			newCustomerGtsForecast.getForecastName());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CustomerGtsForecast newCustomerGtsForecast = addCustomerGtsForecast();

		CustomerGtsForecast existingCustomerGtsForecast = _persistence.findByPrimaryKey(newCustomerGtsForecast.getPrimaryKey());

		Assert.assertEquals(existingCustomerGtsForecast, newCustomerGtsForecast);
	}

	@Test(expected = NoSuchCustomerGtsForecastException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CustomerGtsForecast> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CUSTOMER_GTS_FORECAST",
			"price", true, "itemMasterSid", true, "forecastYear", true,
			"deductionAmount", true, "deductionId", true, "forecastDate", true,
			"itemId", true, "modifiedDate", true, "brandMasterSid", true,
			"source", true, "createdDate", true, "createdBy", true,
			"addChgDelIndicator", true, "inboundStatus", true, "modifiedBy",
			true, "salesAmount", true, "deductionType", true,
			"companyMasterSid", true, "units", true, "deductionRate", true,
			"customerGtsForecastSid", true, "country", true, "companyIdString",
			true, "forecastValueType", true, "deductionCategory", true,
			"adjustmentCode", true, "deductionProgramType", true,
			"recordLockStatus", true, "salesInclusion", true, "forecastVer",
			true, "batchId", true, "priceType", true, "forecastMonth", true,
			"deductionInclusion", true, "segment", true, "brand", true,
			"forecastName", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CustomerGtsForecast newCustomerGtsForecast = addCustomerGtsForecast();

		CustomerGtsForecast existingCustomerGtsForecast = _persistence.fetchByPrimaryKey(newCustomerGtsForecast.getPrimaryKey());

		Assert.assertEquals(existingCustomerGtsForecast, newCustomerGtsForecast);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CustomerGtsForecast missingCustomerGtsForecast = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCustomerGtsForecast);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CustomerGtsForecast newCustomerGtsForecast1 = addCustomerGtsForecast();
		CustomerGtsForecast newCustomerGtsForecast2 = addCustomerGtsForecast();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCustomerGtsForecast1.getPrimaryKey());
		primaryKeys.add(newCustomerGtsForecast2.getPrimaryKey());

		Map<Serializable, CustomerGtsForecast> customerGtsForecasts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, customerGtsForecasts.size());
		Assert.assertEquals(newCustomerGtsForecast1,
			customerGtsForecasts.get(newCustomerGtsForecast1.getPrimaryKey()));
		Assert.assertEquals(newCustomerGtsForecast2,
			customerGtsForecasts.get(newCustomerGtsForecast2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CustomerGtsForecast> customerGtsForecasts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(customerGtsForecasts.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CustomerGtsForecast newCustomerGtsForecast = addCustomerGtsForecast();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCustomerGtsForecast.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CustomerGtsForecast> customerGtsForecasts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, customerGtsForecasts.size());
		Assert.assertEquals(newCustomerGtsForecast,
			customerGtsForecasts.get(newCustomerGtsForecast.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CustomerGtsForecast> customerGtsForecasts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(customerGtsForecasts.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CustomerGtsForecast newCustomerGtsForecast = addCustomerGtsForecast();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCustomerGtsForecast.getPrimaryKey());

		Map<Serializable, CustomerGtsForecast> customerGtsForecasts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, customerGtsForecasts.size());
		Assert.assertEquals(newCustomerGtsForecast,
			customerGtsForecasts.get(newCustomerGtsForecast.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CustomerGtsForecastLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CustomerGtsForecast>() {
				@Override
				public void performAction(
					CustomerGtsForecast customerGtsForecast) {
					Assert.assertNotNull(customerGtsForecast);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CustomerGtsForecast newCustomerGtsForecast = addCustomerGtsForecast();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CustomerGtsForecast.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("customerGtsForecastSid",
				newCustomerGtsForecast.getCustomerGtsForecastSid()));

		List<CustomerGtsForecast> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CustomerGtsForecast existingCustomerGtsForecast = result.get(0);

		Assert.assertEquals(existingCustomerGtsForecast, newCustomerGtsForecast);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CustomerGtsForecast.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("customerGtsForecastSid",
				RandomTestUtil.nextInt()));

		List<CustomerGtsForecast> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CustomerGtsForecast newCustomerGtsForecast = addCustomerGtsForecast();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CustomerGtsForecast.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"customerGtsForecastSid"));

		Object newCustomerGtsForecastSid = newCustomerGtsForecast.getCustomerGtsForecastSid();

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
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CustomerGtsForecast.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"customerGtsForecastSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("customerGtsForecastSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected CustomerGtsForecast addCustomerGtsForecast()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		CustomerGtsForecast customerGtsForecast = _persistence.create(pk);

		customerGtsForecast.setPrice(RandomTestUtil.randomString());

		customerGtsForecast.setItemMasterSid(RandomTestUtil.nextInt());

		customerGtsForecast.setForecastYear(RandomTestUtil.randomString());

		customerGtsForecast.setDeductionAmount(RandomTestUtil.randomString());

		customerGtsForecast.setDeductionId(RandomTestUtil.randomString());

		customerGtsForecast.setForecastDate(RandomTestUtil.nextDate());

		customerGtsForecast.setItemId(RandomTestUtil.randomString());

		customerGtsForecast.setModifiedDate(RandomTestUtil.nextDate());

		customerGtsForecast.setBrandMasterSid(RandomTestUtil.nextInt());

		customerGtsForecast.setSource(RandomTestUtil.randomString());

		customerGtsForecast.setCreatedDate(RandomTestUtil.nextDate());

		customerGtsForecast.setCreatedBy(RandomTestUtil.randomString());

		customerGtsForecast.setAddChgDelIndicator(RandomTestUtil.randomString());

		customerGtsForecast.setInboundStatus(RandomTestUtil.randomString());

		customerGtsForecast.setModifiedBy(RandomTestUtil.randomString());

		customerGtsForecast.setSalesAmount(RandomTestUtil.randomString());

		customerGtsForecast.setDeductionType(RandomTestUtil.randomString());

		customerGtsForecast.setCompanyMasterSid(RandomTestUtil.nextInt());

		customerGtsForecast.setUnits(RandomTestUtil.randomString());

		customerGtsForecast.setDeductionRate(RandomTestUtil.randomString());

		customerGtsForecast.setCountry(RandomTestUtil.randomString());

		customerGtsForecast.setCompanyIdString(RandomTestUtil.randomString());

		customerGtsForecast.setForecastValueType(RandomTestUtil.randomString());

		customerGtsForecast.setDeductionCategory(RandomTestUtil.randomString());

		customerGtsForecast.setAdjustmentCode(RandomTestUtil.randomString());

		customerGtsForecast.setDeductionProgramType(RandomTestUtil.randomString());

		customerGtsForecast.setRecordLockStatus(RandomTestUtil.randomBoolean());

		customerGtsForecast.setSalesInclusion(RandomTestUtil.randomString());

		customerGtsForecast.setForecastVer(RandomTestUtil.randomString());

		customerGtsForecast.setBatchId(RandomTestUtil.randomString());

		customerGtsForecast.setPriceType(RandomTestUtil.randomString());

		customerGtsForecast.setForecastMonth(RandomTestUtil.randomString());

		customerGtsForecast.setDeductionInclusion(RandomTestUtil.randomString());

		customerGtsForecast.setSegment(RandomTestUtil.randomString());

		customerGtsForecast.setBrand(RandomTestUtil.randomString());

		customerGtsForecast.setForecastName(RandomTestUtil.randomString());

		_customerGtsForecasts.add(_persistence.update(customerGtsForecast));

		return customerGtsForecast;
	}

	private List<CustomerGtsForecast> _customerGtsForecasts = new ArrayList<CustomerGtsForecast>();
	private CustomerGtsForecastPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}