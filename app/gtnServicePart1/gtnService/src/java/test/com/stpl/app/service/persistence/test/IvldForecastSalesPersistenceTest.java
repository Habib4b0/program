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

import com.stpl.app.exception.NoSuchIvldForecastSalesException;
import com.stpl.app.model.IvldForecastSales;
import com.stpl.app.service.IvldForecastSalesLocalServiceUtil;
import com.stpl.app.service.persistence.IvldForecastSalesPersistence;
import com.stpl.app.service.persistence.IvldForecastSalesUtil;

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
public class IvldForecastSalesPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = IvldForecastSalesUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<IvldForecastSales> iterator = _ivldForecastSaleses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldForecastSales ivldForecastSales = _persistence.create(pk);

		Assert.assertNotNull(ivldForecastSales);

		Assert.assertEquals(ivldForecastSales.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		IvldForecastSales newIvldForecastSales = addIvldForecastSales();

		_persistence.remove(newIvldForecastSales);

		IvldForecastSales existingIvldForecastSales = _persistence.fetchByPrimaryKey(newIvldForecastSales.getPrimaryKey());

		Assert.assertNull(existingIvldForecastSales);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addIvldForecastSales();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldForecastSales newIvldForecastSales = _persistence.create(pk);

		newIvldForecastSales.setPrice(RandomTestUtil.randomString());

		newIvldForecastSales.setForecastYear(RandomTestUtil.randomString());

		newIvldForecastSales.setForecastDate(RandomTestUtil.randomString());

		newIvldForecastSales.setModifiedDate(RandomTestUtil.nextDate());

		newIvldForecastSales.setForecastValue(RandomTestUtil.randomString());

		newIvldForecastSales.setForecastIntfid(RandomTestUtil.randomString());

		newIvldForecastSales.setDollars(RandomTestUtil.randomString());

		newIvldForecastSales.setNdc(RandomTestUtil.randomString());

		newIvldForecastSales.setActualSalesPercentage(RandomTestUtil.randomString());

		newIvldForecastSales.setSource(RandomTestUtil.randomString());

		newIvldForecastSales.setCreatedDate(RandomTestUtil.nextDate());

		newIvldForecastSales.setCreatedBy(RandomTestUtil.randomString());

		newIvldForecastSales.setAddChgDelIndicator(RandomTestUtil.randomString());

		newIvldForecastSales.setActualSalesPercentageMonth(RandomTestUtil.randomString());

		newIvldForecastSales.setErrorCode(RandomTestUtil.randomString());

		newIvldForecastSales.setIntfInsertedDate(RandomTestUtil.nextDate());

		newIvldForecastSales.setModifiedBy(RandomTestUtil.randomString());

		newIvldForecastSales.setReprocessedFlag(RandomTestUtil.randomString());

		newIvldForecastSales.setPercentageEstimate(RandomTestUtil.randomString());

		newIvldForecastSales.setPercentageEstimateYear(RandomTestUtil.randomString());

		newIvldForecastSales.setUnits(RandomTestUtil.randomString());

		newIvldForecastSales.setReasonForFailure(RandomTestUtil.randomString());

		newIvldForecastSales.setForecastStartDate(RandomTestUtil.randomString());

		newIvldForecastSales.setForecastValueType(RandomTestUtil.randomString());

		newIvldForecastSales.setForecastedSalesPercentMonth(RandomTestUtil.randomString());

		newIvldForecastSales.setCountry(RandomTestUtil.randomString());

		newIvldForecastSales.setProduct(RandomTestUtil.randomString());

		newIvldForecastSales.setBatchId(RandomTestUtil.randomString());

		newIvldForecastSales.setForecastVer(RandomTestUtil.randomString());

		newIvldForecastSales.setForecastMonth(RandomTestUtil.randomString());

		newIvldForecastSales.setErrorField(RandomTestUtil.randomString());

		newIvldForecastSales.setSegment(RandomTestUtil.randomString());

		newIvldForecastSales.setBrand(RandomTestUtil.randomString());

		newIvldForecastSales.setForecastedSalesPercentage(RandomTestUtil.randomString());

		newIvldForecastSales.setForecastName(RandomTestUtil.randomString());

		newIvldForecastSales.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldForecastSaleses.add(_persistence.update(newIvldForecastSales));

		IvldForecastSales existingIvldForecastSales = _persistence.findByPrimaryKey(newIvldForecastSales.getPrimaryKey());

		Assert.assertEquals(existingIvldForecastSales.getPrice(),
			newIvldForecastSales.getPrice());
		Assert.assertEquals(existingIvldForecastSales.getForecastYear(),
			newIvldForecastSales.getForecastYear());
		Assert.assertEquals(existingIvldForecastSales.getForecastDate(),
			newIvldForecastSales.getForecastDate());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldForecastSales.getModifiedDate()),
			Time.getShortTimestamp(newIvldForecastSales.getModifiedDate()));
		Assert.assertEquals(existingIvldForecastSales.getForecastValue(),
			newIvldForecastSales.getForecastValue());
		Assert.assertEquals(existingIvldForecastSales.getForecastIntfid(),
			newIvldForecastSales.getForecastIntfid());
		Assert.assertEquals(existingIvldForecastSales.getDollars(),
			newIvldForecastSales.getDollars());
		Assert.assertEquals(existingIvldForecastSales.getNdc(),
			newIvldForecastSales.getNdc());
		Assert.assertEquals(existingIvldForecastSales.getActualSalesPercentage(),
			newIvldForecastSales.getActualSalesPercentage());
		Assert.assertEquals(existingIvldForecastSales.getSource(),
			newIvldForecastSales.getSource());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldForecastSales.getCreatedDate()),
			Time.getShortTimestamp(newIvldForecastSales.getCreatedDate()));
		Assert.assertEquals(existingIvldForecastSales.getCreatedBy(),
			newIvldForecastSales.getCreatedBy());
		Assert.assertEquals(existingIvldForecastSales.getAddChgDelIndicator(),
			newIvldForecastSales.getAddChgDelIndicator());
		Assert.assertEquals(existingIvldForecastSales.getActualSalesPercentageMonth(),
			newIvldForecastSales.getActualSalesPercentageMonth());
		Assert.assertEquals(existingIvldForecastSales.getErrorCode(),
			newIvldForecastSales.getErrorCode());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldForecastSales.getIntfInsertedDate()),
			Time.getShortTimestamp(newIvldForecastSales.getIntfInsertedDate()));
		Assert.assertEquals(existingIvldForecastSales.getModifiedBy(),
			newIvldForecastSales.getModifiedBy());
		Assert.assertEquals(existingIvldForecastSales.getReprocessedFlag(),
			newIvldForecastSales.getReprocessedFlag());
		Assert.assertEquals(existingIvldForecastSales.getPercentageEstimate(),
			newIvldForecastSales.getPercentageEstimate());
		Assert.assertEquals(existingIvldForecastSales.getPercentageEstimateYear(),
			newIvldForecastSales.getPercentageEstimateYear());
		Assert.assertEquals(existingIvldForecastSales.getUnits(),
			newIvldForecastSales.getUnits());
		Assert.assertEquals(existingIvldForecastSales.getReasonForFailure(),
			newIvldForecastSales.getReasonForFailure());
		Assert.assertEquals(existingIvldForecastSales.getForecastStartDate(),
			newIvldForecastSales.getForecastStartDate());
		Assert.assertEquals(existingIvldForecastSales.getForecastValueType(),
			newIvldForecastSales.getForecastValueType());
		Assert.assertEquals(existingIvldForecastSales.getForecastedSalesPercentMonth(),
			newIvldForecastSales.getForecastedSalesPercentMonth());
		Assert.assertEquals(existingIvldForecastSales.getCountry(),
			newIvldForecastSales.getCountry());
		Assert.assertEquals(existingIvldForecastSales.getProduct(),
			newIvldForecastSales.getProduct());
		Assert.assertEquals(existingIvldForecastSales.getBatchId(),
			newIvldForecastSales.getBatchId());
		Assert.assertEquals(existingIvldForecastSales.getForecastVer(),
			newIvldForecastSales.getForecastVer());
		Assert.assertEquals(existingIvldForecastSales.getForecastMonth(),
			newIvldForecastSales.getForecastMonth());
		Assert.assertEquals(existingIvldForecastSales.getIvldForecastSalesSid(),
			newIvldForecastSales.getIvldForecastSalesSid());
		Assert.assertEquals(existingIvldForecastSales.getErrorField(),
			newIvldForecastSales.getErrorField());
		Assert.assertEquals(existingIvldForecastSales.getSegment(),
			newIvldForecastSales.getSegment());
		Assert.assertEquals(existingIvldForecastSales.getBrand(),
			newIvldForecastSales.getBrand());
		Assert.assertEquals(existingIvldForecastSales.getForecastedSalesPercentage(),
			newIvldForecastSales.getForecastedSalesPercentage());
		Assert.assertEquals(existingIvldForecastSales.getForecastName(),
			newIvldForecastSales.getForecastName());
		Assert.assertEquals(existingIvldForecastSales.getCheckRecord(),
			newIvldForecastSales.getCheckRecord());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		IvldForecastSales newIvldForecastSales = addIvldForecastSales();

		IvldForecastSales existingIvldForecastSales = _persistence.findByPrimaryKey(newIvldForecastSales.getPrimaryKey());

		Assert.assertEquals(existingIvldForecastSales, newIvldForecastSales);
	}

	@Test(expected = NoSuchIvldForecastSalesException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<IvldForecastSales> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("IVLD_FORECAST_SALES",
			"price", true, "forecastYear", true, "forecastDate", true,
			"modifiedDate", true, "forecastValue", true, "forecastIntfid",
			true, "dollars", true, "ndc", true, "actualSalesPercentage", true,
			"source", true, "createdDate", true, "createdBy", true,
			"addChgDelIndicator", true, "actualSalesPercentageMonth", true,
			"errorCode", true, "intfInsertedDate", true, "modifiedBy", true,
			"reprocessedFlag", true, "percentageEstimate", true,
			"percentageEstimateYear", true, "units", true, "reasonForFailure",
			true, "forecastStartDate", true, "forecastValueType", true,
			"forecastedSalesPercentMonth", true, "country", true, "product",
			true, "batchId", true, "forecastVer", true, "forecastMonth", true,
			"ivldForecastSalesSid", true, "errorField", true, "segment", true,
			"brand", true, "forecastedSalesPercentage", true, "forecastName",
			true, "checkRecord", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		IvldForecastSales newIvldForecastSales = addIvldForecastSales();

		IvldForecastSales existingIvldForecastSales = _persistence.fetchByPrimaryKey(newIvldForecastSales.getPrimaryKey());

		Assert.assertEquals(existingIvldForecastSales, newIvldForecastSales);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldForecastSales missingIvldForecastSales = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingIvldForecastSales);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		IvldForecastSales newIvldForecastSales1 = addIvldForecastSales();
		IvldForecastSales newIvldForecastSales2 = addIvldForecastSales();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldForecastSales1.getPrimaryKey());
		primaryKeys.add(newIvldForecastSales2.getPrimaryKey());

		Map<Serializable, IvldForecastSales> ivldForecastSaleses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ivldForecastSaleses.size());
		Assert.assertEquals(newIvldForecastSales1,
			ivldForecastSaleses.get(newIvldForecastSales1.getPrimaryKey()));
		Assert.assertEquals(newIvldForecastSales2,
			ivldForecastSaleses.get(newIvldForecastSales2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, IvldForecastSales> ivldForecastSaleses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldForecastSaleses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		IvldForecastSales newIvldForecastSales = addIvldForecastSales();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldForecastSales.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, IvldForecastSales> ivldForecastSaleses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldForecastSaleses.size());
		Assert.assertEquals(newIvldForecastSales,
			ivldForecastSaleses.get(newIvldForecastSales.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, IvldForecastSales> ivldForecastSaleses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldForecastSaleses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		IvldForecastSales newIvldForecastSales = addIvldForecastSales();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldForecastSales.getPrimaryKey());

		Map<Serializable, IvldForecastSales> ivldForecastSaleses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldForecastSaleses.size());
		Assert.assertEquals(newIvldForecastSales,
			ivldForecastSaleses.get(newIvldForecastSales.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = IvldForecastSalesLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<IvldForecastSales>() {
				@Override
				public void performAction(IvldForecastSales ivldForecastSales) {
					Assert.assertNotNull(ivldForecastSales);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		IvldForecastSales newIvldForecastSales = addIvldForecastSales();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldForecastSales.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldForecastSalesSid",
				newIvldForecastSales.getIvldForecastSalesSid()));

		List<IvldForecastSales> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		IvldForecastSales existingIvldForecastSales = result.get(0);

		Assert.assertEquals(existingIvldForecastSales, newIvldForecastSales);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldForecastSales.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldForecastSalesSid",
				RandomTestUtil.nextInt()));

		List<IvldForecastSales> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		IvldForecastSales newIvldForecastSales = addIvldForecastSales();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldForecastSales.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldForecastSalesSid"));

		Object newIvldForecastSalesSid = newIvldForecastSales.getIvldForecastSalesSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldForecastSalesSid",
				new Object[] { newIvldForecastSalesSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingIvldForecastSalesSid = result.get(0);

		Assert.assertEquals(existingIvldForecastSalesSid,
			newIvldForecastSalesSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldForecastSales.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldForecastSalesSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldForecastSalesSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected IvldForecastSales addIvldForecastSales()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldForecastSales ivldForecastSales = _persistence.create(pk);

		ivldForecastSales.setPrice(RandomTestUtil.randomString());

		ivldForecastSales.setForecastYear(RandomTestUtil.randomString());

		ivldForecastSales.setForecastDate(RandomTestUtil.randomString());

		ivldForecastSales.setModifiedDate(RandomTestUtil.nextDate());

		ivldForecastSales.setForecastValue(RandomTestUtil.randomString());

		ivldForecastSales.setForecastIntfid(RandomTestUtil.randomString());

		ivldForecastSales.setDollars(RandomTestUtil.randomString());

		ivldForecastSales.setNdc(RandomTestUtil.randomString());

		ivldForecastSales.setActualSalesPercentage(RandomTestUtil.randomString());

		ivldForecastSales.setSource(RandomTestUtil.randomString());

		ivldForecastSales.setCreatedDate(RandomTestUtil.nextDate());

		ivldForecastSales.setCreatedBy(RandomTestUtil.randomString());

		ivldForecastSales.setAddChgDelIndicator(RandomTestUtil.randomString());

		ivldForecastSales.setActualSalesPercentageMonth(RandomTestUtil.randomString());

		ivldForecastSales.setErrorCode(RandomTestUtil.randomString());

		ivldForecastSales.setIntfInsertedDate(RandomTestUtil.nextDate());

		ivldForecastSales.setModifiedBy(RandomTestUtil.randomString());

		ivldForecastSales.setReprocessedFlag(RandomTestUtil.randomString());

		ivldForecastSales.setPercentageEstimate(RandomTestUtil.randomString());

		ivldForecastSales.setPercentageEstimateYear(RandomTestUtil.randomString());

		ivldForecastSales.setUnits(RandomTestUtil.randomString());

		ivldForecastSales.setReasonForFailure(RandomTestUtil.randomString());

		ivldForecastSales.setForecastStartDate(RandomTestUtil.randomString());

		ivldForecastSales.setForecastValueType(RandomTestUtil.randomString());

		ivldForecastSales.setForecastedSalesPercentMonth(RandomTestUtil.randomString());

		ivldForecastSales.setCountry(RandomTestUtil.randomString());

		ivldForecastSales.setProduct(RandomTestUtil.randomString());

		ivldForecastSales.setBatchId(RandomTestUtil.randomString());

		ivldForecastSales.setForecastVer(RandomTestUtil.randomString());

		ivldForecastSales.setForecastMonth(RandomTestUtil.randomString());

		ivldForecastSales.setErrorField(RandomTestUtil.randomString());

		ivldForecastSales.setSegment(RandomTestUtil.randomString());

		ivldForecastSales.setBrand(RandomTestUtil.randomString());

		ivldForecastSales.setForecastedSalesPercentage(RandomTestUtil.randomString());

		ivldForecastSales.setForecastName(RandomTestUtil.randomString());

		ivldForecastSales.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldForecastSaleses.add(_persistence.update(ivldForecastSales));

		return ivldForecastSales;
	}

	private List<IvldForecastSales> _ivldForecastSaleses = new ArrayList<IvldForecastSales>();
	private IvldForecastSalesPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}