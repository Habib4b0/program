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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchForecastingMasterException;
import com.stpl.app.model.ForecastingMaster;
import com.stpl.app.service.ForecastingMasterLocalServiceUtil;
import com.stpl.app.service.persistence.ForecastingMasterPersistence;
import com.stpl.app.service.persistence.ForecastingMasterUtil;

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
public class ForecastingMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ForecastingMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ForecastingMaster> iterator = _forecastingMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ForecastingMaster forecastingMaster = _persistence.create(pk);

		Assert.assertNotNull(forecastingMaster);

		Assert.assertEquals(forecastingMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ForecastingMaster newForecastingMaster = addForecastingMaster();

		_persistence.remove(newForecastingMaster);

		ForecastingMaster existingForecastingMaster = _persistence.fetchByPrimaryKey(newForecastingMaster.getPrimaryKey());

		Assert.assertNull(existingForecastingMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addForecastingMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ForecastingMaster newForecastingMaster = _persistence.create(pk);

		newForecastingMaster.setForecastValueType(RandomTestUtil.randomString());

		newForecastingMaster.setModifiedBy(RandomTestUtil.nextInt());

		newForecastingMaster.setCreatedDate(RandomTestUtil.nextDate());

		newForecastingMaster.setPercentageEstimate(RandomTestUtil.randomString());

		newForecastingMaster.setActualSalesPercentageMonth(RandomTestUtil.nextInt());

		newForecastingMaster.setNdc(RandomTestUtil.randomString());

		newForecastingMaster.setBatchId(RandomTestUtil.randomString());

		newForecastingMaster.setForecastVer(RandomTestUtil.randomString());

		newForecastingMaster.setCountry(RandomTestUtil.randomString());

		newForecastingMaster.setProduct(RandomTestUtil.randomString());

		newForecastingMaster.setForecastStartDate(RandomTestUtil.nextDate());

		newForecastingMaster.setForecastedSalesPercentage(RandomTestUtil.randomString());

		newForecastingMaster.setUnits(RandomTestUtil.nextDouble());

		newForecastingMaster.setDollars(RandomTestUtil.nextDouble());

		newForecastingMaster.setForecastMonth(RandomTestUtil.randomString());

		newForecastingMaster.setCreatedBy(RandomTestUtil.nextInt());

		newForecastingMaster.setSegment(RandomTestUtil.randomString());

		newForecastingMaster.setPrice(RandomTestUtil.nextDouble());

		newForecastingMaster.setActualSalesPercentage(RandomTestUtil.randomString());

		newForecastingMaster.setForecastYear(RandomTestUtil.randomString());

		newForecastingMaster.setForecastName(RandomTestUtil.randomString());

		newForecastingMaster.setForecastDate(RandomTestUtil.nextDate());

		newForecastingMaster.setModifiedDate(RandomTestUtil.nextDate());

		newForecastingMaster.setBrand(RandomTestUtil.randomString());

		newForecastingMaster.setForecastValue(RandomTestUtil.randomString());

		newForecastingMaster.setPercentageEstimateYear(RandomTestUtil.nextInt());

		newForecastingMaster.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newForecastingMaster.setSource(RandomTestUtil.randomString());

		newForecastingMaster.setForecastedSalesPercentMonth(RandomTestUtil.nextInt());

		newForecastingMaster.setInboundStatus(RandomTestUtil.randomString());

		newForecastingMaster.setBusinessUnit(RandomTestUtil.nextInt());

		_forecastingMasters.add(_persistence.update(newForecastingMaster));

		ForecastingMaster existingForecastingMaster = _persistence.findByPrimaryKey(newForecastingMaster.getPrimaryKey());

		Assert.assertEquals(existingForecastingMaster.getForecastValueType(),
			newForecastingMaster.getForecastValueType());
		Assert.assertEquals(existingForecastingMaster.getModifiedBy(),
			newForecastingMaster.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingForecastingMaster.getCreatedDate()),
			Time.getShortTimestamp(newForecastingMaster.getCreatedDate()));
		Assert.assertEquals(existingForecastingMaster.getPercentageEstimate(),
			newForecastingMaster.getPercentageEstimate());
		Assert.assertEquals(existingForecastingMaster.getActualSalesPercentageMonth(),
			newForecastingMaster.getActualSalesPercentageMonth());
		Assert.assertEquals(existingForecastingMaster.getNdc(),
			newForecastingMaster.getNdc());
		Assert.assertEquals(existingForecastingMaster.getBatchId(),
			newForecastingMaster.getBatchId());
		Assert.assertEquals(existingForecastingMaster.getForecastVer(),
			newForecastingMaster.getForecastVer());
		Assert.assertEquals(existingForecastingMaster.getCountry(),
			newForecastingMaster.getCountry());
		Assert.assertEquals(existingForecastingMaster.getProduct(),
			newForecastingMaster.getProduct());
		Assert.assertEquals(Time.getShortTimestamp(
				existingForecastingMaster.getForecastStartDate()),
			Time.getShortTimestamp(newForecastingMaster.getForecastStartDate()));
		Assert.assertEquals(existingForecastingMaster.getForecastedSalesPercentage(),
			newForecastingMaster.getForecastedSalesPercentage());
		AssertUtils.assertEquals(existingForecastingMaster.getUnits(),
			newForecastingMaster.getUnits());
		AssertUtils.assertEquals(existingForecastingMaster.getDollars(),
			newForecastingMaster.getDollars());
		Assert.assertEquals(existingForecastingMaster.getForecastMonth(),
			newForecastingMaster.getForecastMonth());
		Assert.assertEquals(existingForecastingMaster.getCreatedBy(),
			newForecastingMaster.getCreatedBy());
		Assert.assertEquals(existingForecastingMaster.getSegment(),
			newForecastingMaster.getSegment());
		AssertUtils.assertEquals(existingForecastingMaster.getPrice(),
			newForecastingMaster.getPrice());
		Assert.assertEquals(existingForecastingMaster.getActualSalesPercentage(),
			newForecastingMaster.getActualSalesPercentage());
		Assert.assertEquals(existingForecastingMaster.getForecastYear(),
			newForecastingMaster.getForecastYear());
		Assert.assertEquals(existingForecastingMaster.getForecastName(),
			newForecastingMaster.getForecastName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingForecastingMaster.getForecastDate()),
			Time.getShortTimestamp(newForecastingMaster.getForecastDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingForecastingMaster.getModifiedDate()),
			Time.getShortTimestamp(newForecastingMaster.getModifiedDate()));
		Assert.assertEquals(existingForecastingMaster.getBrand(),
			newForecastingMaster.getBrand());
		Assert.assertEquals(existingForecastingMaster.getForecastValue(),
			newForecastingMaster.getForecastValue());
		Assert.assertEquals(existingForecastingMaster.getPercentageEstimateYear(),
			newForecastingMaster.getPercentageEstimateYear());
		Assert.assertEquals(existingForecastingMaster.getRecordLockStatus(),
			newForecastingMaster.getRecordLockStatus());
		Assert.assertEquals(existingForecastingMaster.getSource(),
			newForecastingMaster.getSource());
		Assert.assertEquals(existingForecastingMaster.getForecastMasterSid(),
			newForecastingMaster.getForecastMasterSid());
		Assert.assertEquals(existingForecastingMaster.getForecastedSalesPercentMonth(),
			newForecastingMaster.getForecastedSalesPercentMonth());
		Assert.assertEquals(existingForecastingMaster.getInboundStatus(),
			newForecastingMaster.getInboundStatus());
		Assert.assertEquals(existingForecastingMaster.getBusinessUnit(),
			newForecastingMaster.getBusinessUnit());
	}

	@Test
	public void testCountByForecastYear() throws Exception {
		_persistence.countByForecastYear(StringPool.BLANK);

		_persistence.countByForecastYear(StringPool.NULL);

		_persistence.countByForecastYear((String)null);
	}

	@Test
	public void testCountByForecastMonth() throws Exception {
		_persistence.countByForecastMonth(StringPool.BLANK);

		_persistence.countByForecastMonth(StringPool.NULL);

		_persistence.countByForecastMonth((String)null);
	}

	@Test
	public void testCountByCountry() throws Exception {
		_persistence.countByCountry(StringPool.BLANK);

		_persistence.countByCountry(StringPool.NULL);

		_persistence.countByCountry((String)null);
	}

	@Test
	public void testCountByNdc() throws Exception {
		_persistence.countByNdc(StringPool.BLANK);

		_persistence.countByNdc(StringPool.NULL);

		_persistence.countByNdc((String)null);
	}

	@Test
	public void testCountByForecastStartDate() throws Exception {
		_persistence.countByForecastStartDate(RandomTestUtil.nextDate());

		_persistence.countByForecastStartDate(RandomTestUtil.nextDate());
	}

	@Test
	public void testCountByCreatedDate() throws Exception {
		_persistence.countByCreatedDate(RandomTestUtil.nextDate());

		_persistence.countByCreatedDate(RandomTestUtil.nextDate());
	}

	@Test
	public void testCountByForecastingSalesUnique() throws Exception {
		_persistence.countByForecastingSalesUnique(StringPool.BLANK,
			StringPool.BLANK, StringPool.BLANK, RandomTestUtil.nextDate(),
			RandomTestUtil.nextDate());

		_persistence.countByForecastingSalesUnique(StringPool.NULL,
			StringPool.NULL, StringPool.NULL, RandomTestUtil.nextDate(),
			RandomTestUtil.nextDate());

		_persistence.countByForecastingSalesUnique((String)null, (String)null,
			(String)null, RandomTestUtil.nextDate(), RandomTestUtil.nextDate());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ForecastingMaster newForecastingMaster = addForecastingMaster();

		ForecastingMaster existingForecastingMaster = _persistence.findByPrimaryKey(newForecastingMaster.getPrimaryKey());

		Assert.assertEquals(existingForecastingMaster, newForecastingMaster);
	}

	@Test(expected = NoSuchForecastingMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<ForecastingMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("FORECASTING_MASTER",
			"forecastValueType", true, "modifiedBy", true, "createdDate", true,
			"percentageEstimate", true, "actualSalesPercentageMonth", true,
			"ndc", true, "batchId", true, "forecastVer", true, "country", true,
			"product", true, "forecastStartDate", true,
			"forecastedSalesPercentage", true, "units", true, "dollars", true,
			"forecastMonth", true, "createdBy", true, "segment", true, "price",
			true, "actualSalesPercentage", true, "forecastYear", true,
			"forecastName", true, "forecastDate", true, "modifiedDate", true,
			"brand", true, "forecastValue", true, "percentageEstimateYear",
			true, "recordLockStatus", true, "source", true,
			"forecastMasterSid", true, "forecastedSalesPercentMonth", true,
			"inboundStatus", true, "businessUnit", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ForecastingMaster newForecastingMaster = addForecastingMaster();

		ForecastingMaster existingForecastingMaster = _persistence.fetchByPrimaryKey(newForecastingMaster.getPrimaryKey());

		Assert.assertEquals(existingForecastingMaster, newForecastingMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ForecastingMaster missingForecastingMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingForecastingMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ForecastingMaster newForecastingMaster1 = addForecastingMaster();
		ForecastingMaster newForecastingMaster2 = addForecastingMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newForecastingMaster1.getPrimaryKey());
		primaryKeys.add(newForecastingMaster2.getPrimaryKey());

		Map<Serializable, ForecastingMaster> forecastingMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, forecastingMasters.size());
		Assert.assertEquals(newForecastingMaster1,
			forecastingMasters.get(newForecastingMaster1.getPrimaryKey()));
		Assert.assertEquals(newForecastingMaster2,
			forecastingMasters.get(newForecastingMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ForecastingMaster> forecastingMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(forecastingMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ForecastingMaster newForecastingMaster = addForecastingMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newForecastingMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ForecastingMaster> forecastingMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, forecastingMasters.size());
		Assert.assertEquals(newForecastingMaster,
			forecastingMasters.get(newForecastingMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ForecastingMaster> forecastingMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(forecastingMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ForecastingMaster newForecastingMaster = addForecastingMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newForecastingMaster.getPrimaryKey());

		Map<Serializable, ForecastingMaster> forecastingMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, forecastingMasters.size());
		Assert.assertEquals(newForecastingMaster,
			forecastingMasters.get(newForecastingMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ForecastingMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ForecastingMaster>() {
				@Override
				public void performAction(ForecastingMaster forecastingMaster) {
					Assert.assertNotNull(forecastingMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ForecastingMaster newForecastingMaster = addForecastingMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ForecastingMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("forecastMasterSid",
				newForecastingMaster.getForecastMasterSid()));

		List<ForecastingMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ForecastingMaster existingForecastingMaster = result.get(0);

		Assert.assertEquals(existingForecastingMaster, newForecastingMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ForecastingMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("forecastMasterSid",
				RandomTestUtil.nextInt()));

		List<ForecastingMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ForecastingMaster newForecastingMaster = addForecastingMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ForecastingMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"forecastMasterSid"));

		Object newForecastMasterSid = newForecastingMaster.getForecastMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("forecastMasterSid",
				new Object[] { newForecastMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingForecastMasterSid = result.get(0);

		Assert.assertEquals(existingForecastMasterSid, newForecastMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ForecastingMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"forecastMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("forecastMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ForecastingMaster addForecastingMaster()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		ForecastingMaster forecastingMaster = _persistence.create(pk);

		forecastingMaster.setForecastValueType(RandomTestUtil.randomString());

		forecastingMaster.setModifiedBy(RandomTestUtil.nextInt());

		forecastingMaster.setCreatedDate(RandomTestUtil.nextDate());

		forecastingMaster.setPercentageEstimate(RandomTestUtil.randomString());

		forecastingMaster.setActualSalesPercentageMonth(RandomTestUtil.nextInt());

		forecastingMaster.setNdc(RandomTestUtil.randomString());

		forecastingMaster.setBatchId(RandomTestUtil.randomString());

		forecastingMaster.setForecastVer(RandomTestUtil.randomString());

		forecastingMaster.setCountry(RandomTestUtil.randomString());

		forecastingMaster.setProduct(RandomTestUtil.randomString());

		forecastingMaster.setForecastStartDate(RandomTestUtil.nextDate());

		forecastingMaster.setForecastedSalesPercentage(RandomTestUtil.randomString());

		forecastingMaster.setUnits(RandomTestUtil.nextDouble());

		forecastingMaster.setDollars(RandomTestUtil.nextDouble());

		forecastingMaster.setForecastMonth(RandomTestUtil.randomString());

		forecastingMaster.setCreatedBy(RandomTestUtil.nextInt());

		forecastingMaster.setSegment(RandomTestUtil.randomString());

		forecastingMaster.setPrice(RandomTestUtil.nextDouble());

		forecastingMaster.setActualSalesPercentage(RandomTestUtil.randomString());

		forecastingMaster.setForecastYear(RandomTestUtil.randomString());

		forecastingMaster.setForecastName(RandomTestUtil.randomString());

		forecastingMaster.setForecastDate(RandomTestUtil.nextDate());

		forecastingMaster.setModifiedDate(RandomTestUtil.nextDate());

		forecastingMaster.setBrand(RandomTestUtil.randomString());

		forecastingMaster.setForecastValue(RandomTestUtil.randomString());

		forecastingMaster.setPercentageEstimateYear(RandomTestUtil.nextInt());

		forecastingMaster.setRecordLockStatus(RandomTestUtil.randomBoolean());

		forecastingMaster.setSource(RandomTestUtil.randomString());

		forecastingMaster.setForecastedSalesPercentMonth(RandomTestUtil.nextInt());

		forecastingMaster.setInboundStatus(RandomTestUtil.randomString());

		forecastingMaster.setBusinessUnit(RandomTestUtil.nextInt());

		_forecastingMasters.add(_persistence.update(forecastingMaster));

		return forecastingMaster;
	}

	private List<ForecastingMaster> _forecastingMasters = new ArrayList<ForecastingMaster>();
	private ForecastingMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}