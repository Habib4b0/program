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

import com.stpl.app.parttwo.exception.NoSuchVwIvldAdjDemandForeActualException;
import com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual;
import com.stpl.app.parttwo.service.VwIvldAdjDemandForeActualLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.VwIvldAdjDemandForeActualPersistence;
import com.stpl.app.parttwo.service.persistence.VwIvldAdjDemandForeActualUtil;

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
public class VwIvldAdjDemandForeActualPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = VwIvldAdjDemandForeActualUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<VwIvldAdjDemandForeActual> iterator = _vwIvldAdjDemandForeActuals.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual = _persistence.create(pk);

		Assert.assertNotNull(vwIvldAdjDemandForeActual);

		Assert.assertEquals(vwIvldAdjDemandForeActual.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		VwIvldAdjDemandForeActual newVwIvldAdjDemandForeActual = addVwIvldAdjDemandForeActual();

		_persistence.remove(newVwIvldAdjDemandForeActual);

		VwIvldAdjDemandForeActual existingVwIvldAdjDemandForeActual = _persistence.fetchByPrimaryKey(newVwIvldAdjDemandForeActual.getPrimaryKey());

		Assert.assertNull(existingVwIvldAdjDemandForeActual);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addVwIvldAdjDemandForeActual();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwIvldAdjDemandForeActual newVwIvldAdjDemandForeActual = _persistence.create(pk);

		newVwIvldAdjDemandForeActual.setForecastVersion(RandomTestUtil.randomString());

		newVwIvldAdjDemandForeActual.setGrossUnits(RandomTestUtil.randomString());

		newVwIvldAdjDemandForeActual.setBusinessUnitNo(RandomTestUtil.randomString());

		newVwIvldAdjDemandForeActual.setYear(RandomTestUtil.randomString());

		newVwIvldAdjDemandForeActual.setItemId(RandomTestUtil.randomString());

		newVwIvldAdjDemandForeActual.setBrandName(RandomTestUtil.randomString());

		newVwIvldAdjDemandForeActual.setModifiedDate(RandomTestUtil.nextDate());

		newVwIvldAdjDemandForeActual.setOrganizationKey(RandomTestUtil.randomString());

		newVwIvldAdjDemandForeActual.setCreatedDate(RandomTestUtil.nextDate());

		newVwIvldAdjDemandForeActual.setCreatedBy(RandomTestUtil.randomString());

		newVwIvldAdjDemandForeActual.setSource(RandomTestUtil.randomString());

		newVwIvldAdjDemandForeActual.setMarketShareRatio(RandomTestUtil.randomString());

		newVwIvldAdjDemandForeActual.setBusinessUnitName(RandomTestUtil.randomString());

		newVwIvldAdjDemandForeActual.setAddChgDelIndicator(RandomTestUtil.randomString());

		newVwIvldAdjDemandForeActual.setItemIdentifier(RandomTestUtil.randomString());

		newVwIvldAdjDemandForeActual.setErrorCode(RandomTestUtil.randomString());

		newVwIvldAdjDemandForeActual.setModifiedBy(RandomTestUtil.randomString());

		newVwIvldAdjDemandForeActual.setMarketShareUnits(RandomTestUtil.randomString());

		newVwIvldAdjDemandForeActual.setMonth(RandomTestUtil.randomString());

		newVwIvldAdjDemandForeActual.setInventoryUnitChange(RandomTestUtil.randomString());

		newVwIvldAdjDemandForeActual.setReprocessedFlag(RandomTestUtil.randomString());

		newVwIvldAdjDemandForeActual.setUncapturedUnitsRatio(RandomTestUtil.randomString());

		newVwIvldAdjDemandForeActual.setReasonForFailure(RandomTestUtil.randomString());

		newVwIvldAdjDemandForeActual.setAdjustedDemandForecastIntfId(RandomTestUtil.randomString());

		newVwIvldAdjDemandForeActual.setCountry(RandomTestUtil.randomString());

		newVwIvldAdjDemandForeActual.setForecastType(RandomTestUtil.randomString());

		newVwIvldAdjDemandForeActual.setTotalAdjustedDemandUnits(RandomTestUtil.randomString());

		newVwIvldAdjDemandForeActual.setBrandId(RandomTestUtil.randomString());

		newVwIvldAdjDemandForeActual.setIsForecast(RandomTestUtil.randomString());

		newVwIvldAdjDemandForeActual.setTotalAdjustedDemandAmount(RandomTestUtil.randomString());

		newVwIvldAdjDemandForeActual.setUncapturedUnits(RandomTestUtil.randomString());

		newVwIvldAdjDemandForeActual.setGrossPrice(RandomTestUtil.randomString());

		newVwIvldAdjDemandForeActual.setGrossAmount(RandomTestUtil.randomString());

		newVwIvldAdjDemandForeActual.setItemIdentifierCodeQualifier(RandomTestUtil.randomString());

		newVwIvldAdjDemandForeActual.setBatchId(RandomTestUtil.randomString());

		newVwIvldAdjDemandForeActual.setItemName(RandomTestUtil.randomString());

		newVwIvldAdjDemandForeActual.setErrorField(RandomTestUtil.randomString());

		newVwIvldAdjDemandForeActual.setNetSalesPrice(RandomTestUtil.randomString());

		newVwIvldAdjDemandForeActual.setNetSalesAmount(RandomTestUtil.randomString());

		newVwIvldAdjDemandForeActual.setSegment(RandomTestUtil.randomString());

		newVwIvldAdjDemandForeActual.setForecastName(RandomTestUtil.randomString());

		newVwIvldAdjDemandForeActual.setMarketSizeUnits(RandomTestUtil.randomString());

		newVwIvldAdjDemandForeActual.setCheckRecord(RandomTestUtil.randomBoolean());

		_vwIvldAdjDemandForeActuals.add(_persistence.update(
				newVwIvldAdjDemandForeActual));

		VwIvldAdjDemandForeActual existingVwIvldAdjDemandForeActual = _persistence.findByPrimaryKey(newVwIvldAdjDemandForeActual.getPrimaryKey());

		Assert.assertEquals(existingVwIvldAdjDemandForeActual.getForecastVersion(),
			newVwIvldAdjDemandForeActual.getForecastVersion());
		Assert.assertEquals(existingVwIvldAdjDemandForeActual.getGrossUnits(),
			newVwIvldAdjDemandForeActual.getGrossUnits());
		Assert.assertEquals(existingVwIvldAdjDemandForeActual.getBusinessUnitNo(),
			newVwIvldAdjDemandForeActual.getBusinessUnitNo());
		Assert.assertEquals(existingVwIvldAdjDemandForeActual.getYear(),
			newVwIvldAdjDemandForeActual.getYear());
		Assert.assertEquals(existingVwIvldAdjDemandForeActual.getItemId(),
			newVwIvldAdjDemandForeActual.getItemId());
		Assert.assertEquals(existingVwIvldAdjDemandForeActual.getBrandName(),
			newVwIvldAdjDemandForeActual.getBrandName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwIvldAdjDemandForeActual.getModifiedDate()),
			Time.getShortTimestamp(
				newVwIvldAdjDemandForeActual.getModifiedDate()));
		Assert.assertEquals(existingVwIvldAdjDemandForeActual.getOrganizationKey(),
			newVwIvldAdjDemandForeActual.getOrganizationKey());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwIvldAdjDemandForeActual.getCreatedDate()),
			Time.getShortTimestamp(
				newVwIvldAdjDemandForeActual.getCreatedDate()));
		Assert.assertEquals(existingVwIvldAdjDemandForeActual.getCreatedBy(),
			newVwIvldAdjDemandForeActual.getCreatedBy());
		Assert.assertEquals(existingVwIvldAdjDemandForeActual.getSource(),
			newVwIvldAdjDemandForeActual.getSource());
		Assert.assertEquals(existingVwIvldAdjDemandForeActual.getMarketShareRatio(),
			newVwIvldAdjDemandForeActual.getMarketShareRatio());
		Assert.assertEquals(existingVwIvldAdjDemandForeActual.getBusinessUnitName(),
			newVwIvldAdjDemandForeActual.getBusinessUnitName());
		Assert.assertEquals(existingVwIvldAdjDemandForeActual.getAddChgDelIndicator(),
			newVwIvldAdjDemandForeActual.getAddChgDelIndicator());
		Assert.assertEquals(existingVwIvldAdjDemandForeActual.getItemIdentifier(),
			newVwIvldAdjDemandForeActual.getItemIdentifier());
		Assert.assertEquals(existingVwIvldAdjDemandForeActual.getErrorCode(),
			newVwIvldAdjDemandForeActual.getErrorCode());
		Assert.assertEquals(existingVwIvldAdjDemandForeActual.getModifiedBy(),
			newVwIvldAdjDemandForeActual.getModifiedBy());
		Assert.assertEquals(existingVwIvldAdjDemandForeActual.getMarketShareUnits(),
			newVwIvldAdjDemandForeActual.getMarketShareUnits());
		Assert.assertEquals(existingVwIvldAdjDemandForeActual.getMonth(),
			newVwIvldAdjDemandForeActual.getMonth());
		Assert.assertEquals(existingVwIvldAdjDemandForeActual.getInventoryUnitChange(),
			newVwIvldAdjDemandForeActual.getInventoryUnitChange());
		Assert.assertEquals(existingVwIvldAdjDemandForeActual.getReprocessedFlag(),
			newVwIvldAdjDemandForeActual.getReprocessedFlag());
		Assert.assertEquals(existingVwIvldAdjDemandForeActual.getUncapturedUnitsRatio(),
			newVwIvldAdjDemandForeActual.getUncapturedUnitsRatio());
		Assert.assertEquals(existingVwIvldAdjDemandForeActual.getReasonForFailure(),
			newVwIvldAdjDemandForeActual.getReasonForFailure());
		Assert.assertEquals(existingVwIvldAdjDemandForeActual.getAdjustedDemandForecastIntfId(),
			newVwIvldAdjDemandForeActual.getAdjustedDemandForecastIntfId());
		Assert.assertEquals(existingVwIvldAdjDemandForeActual.getCountry(),
			newVwIvldAdjDemandForeActual.getCountry());
		Assert.assertEquals(existingVwIvldAdjDemandForeActual.getForecastType(),
			newVwIvldAdjDemandForeActual.getForecastType());
		Assert.assertEquals(existingVwIvldAdjDemandForeActual.getTotalAdjustedDemandUnits(),
			newVwIvldAdjDemandForeActual.getTotalAdjustedDemandUnits());
		Assert.assertEquals(existingVwIvldAdjDemandForeActual.getBrandId(),
			newVwIvldAdjDemandForeActual.getBrandId());
		Assert.assertEquals(existingVwIvldAdjDemandForeActual.getIsForecast(),
			newVwIvldAdjDemandForeActual.getIsForecast());
		Assert.assertEquals(existingVwIvldAdjDemandForeActual.getTotalAdjustedDemandAmount(),
			newVwIvldAdjDemandForeActual.getTotalAdjustedDemandAmount());
		Assert.assertEquals(existingVwIvldAdjDemandForeActual.getUncapturedUnits(),
			newVwIvldAdjDemandForeActual.getUncapturedUnits());
		Assert.assertEquals(existingVwIvldAdjDemandForeActual.getGrossPrice(),
			newVwIvldAdjDemandForeActual.getGrossPrice());
		Assert.assertEquals(existingVwIvldAdjDemandForeActual.getGrossAmount(),
			newVwIvldAdjDemandForeActual.getGrossAmount());
		Assert.assertEquals(existingVwIvldAdjDemandForeActual.getItemIdentifierCodeQualifier(),
			newVwIvldAdjDemandForeActual.getItemIdentifierCodeQualifier());
		Assert.assertEquals(existingVwIvldAdjDemandForeActual.getBatchId(),
			newVwIvldAdjDemandForeActual.getBatchId());
		Assert.assertEquals(existingVwIvldAdjDemandForeActual.getItemName(),
			newVwIvldAdjDemandForeActual.getItemName());
		Assert.assertEquals(existingVwIvldAdjDemandForeActual.getErrorField(),
			newVwIvldAdjDemandForeActual.getErrorField());
		Assert.assertEquals(existingVwIvldAdjDemandForeActual.getNetSalesPrice(),
			newVwIvldAdjDemandForeActual.getNetSalesPrice());
		Assert.assertEquals(existingVwIvldAdjDemandForeActual.getNetSalesAmount(),
			newVwIvldAdjDemandForeActual.getNetSalesAmount());
		Assert.assertEquals(existingVwIvldAdjDemandForeActual.getSegment(),
			newVwIvldAdjDemandForeActual.getSegment());
		Assert.assertEquals(existingVwIvldAdjDemandForeActual.getForecastName(),
			newVwIvldAdjDemandForeActual.getForecastName());
		Assert.assertEquals(existingVwIvldAdjDemandForeActual.getIvldAdjustedDemandForecastSid(),
			newVwIvldAdjDemandForeActual.getIvldAdjustedDemandForecastSid());
		Assert.assertEquals(existingVwIvldAdjDemandForeActual.getMarketSizeUnits(),
			newVwIvldAdjDemandForeActual.getMarketSizeUnits());
		Assert.assertEquals(existingVwIvldAdjDemandForeActual.getCheckRecord(),
			newVwIvldAdjDemandForeActual.getCheckRecord());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		VwIvldAdjDemandForeActual newVwIvldAdjDemandForeActual = addVwIvldAdjDemandForeActual();

		VwIvldAdjDemandForeActual existingVwIvldAdjDemandForeActual = _persistence.findByPrimaryKey(newVwIvldAdjDemandForeActual.getPrimaryKey());

		Assert.assertEquals(existingVwIvldAdjDemandForeActual,
			newVwIvldAdjDemandForeActual);
	}

	@Test(expected = NoSuchVwIvldAdjDemandForeActualException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<VwIvldAdjDemandForeActual> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("VW_IVLD_ADJ_DEMAND_FORE_ACTUAL",
			"forecastVersion", true, "grossUnits", true, "businessUnitNo",
			true, "year", true, "itemId", true, "brandName", true,
			"modifiedDate", true, "organizationKey", true, "createdDate", true,
			"createdBy", true, "source", true, "marketShareRatio", true,
			"businessUnitName", true, "addChgDelIndicator", true,
			"itemIdentifier", true, "errorCode", true, "modifiedBy", true,
			"marketShareUnits", true, "month", true, "inventoryUnitChange",
			true, "reprocessedFlag", true, "uncapturedUnitsRatio", true,
			"reasonForFailure", true, "adjustedDemandForecastIntfId", true,
			"country", true, "forecastType", true, "totalAdjustedDemandUnits",
			true, "brandId", true, "isForecast", true,
			"totalAdjustedDemandAmount", true, "uncapturedUnits", true,
			"grossPrice", true, "grossAmount", true,
			"itemIdentifierCodeQualifier", true, "batchId", true, "itemName",
			true, "errorField", true, "netSalesPrice", true, "netSalesAmount",
			true, "segment", true, "forecastName", true,
			"ivldAdjustedDemandForecastSid", true, "marketSizeUnits", true,
			"checkRecord", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		VwIvldAdjDemandForeActual newVwIvldAdjDemandForeActual = addVwIvldAdjDemandForeActual();

		VwIvldAdjDemandForeActual existingVwIvldAdjDemandForeActual = _persistence.fetchByPrimaryKey(newVwIvldAdjDemandForeActual.getPrimaryKey());

		Assert.assertEquals(existingVwIvldAdjDemandForeActual,
			newVwIvldAdjDemandForeActual);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwIvldAdjDemandForeActual missingVwIvldAdjDemandForeActual = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingVwIvldAdjDemandForeActual);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		VwIvldAdjDemandForeActual newVwIvldAdjDemandForeActual1 = addVwIvldAdjDemandForeActual();
		VwIvldAdjDemandForeActual newVwIvldAdjDemandForeActual2 = addVwIvldAdjDemandForeActual();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwIvldAdjDemandForeActual1.getPrimaryKey());
		primaryKeys.add(newVwIvldAdjDemandForeActual2.getPrimaryKey());

		Map<Serializable, VwIvldAdjDemandForeActual> vwIvldAdjDemandForeActuals = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, vwIvldAdjDemandForeActuals.size());
		Assert.assertEquals(newVwIvldAdjDemandForeActual1,
			vwIvldAdjDemandForeActuals.get(
				newVwIvldAdjDemandForeActual1.getPrimaryKey()));
		Assert.assertEquals(newVwIvldAdjDemandForeActual2,
			vwIvldAdjDemandForeActuals.get(
				newVwIvldAdjDemandForeActual2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, VwIvldAdjDemandForeActual> vwIvldAdjDemandForeActuals = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(vwIvldAdjDemandForeActuals.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		VwIvldAdjDemandForeActual newVwIvldAdjDemandForeActual = addVwIvldAdjDemandForeActual();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwIvldAdjDemandForeActual.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, VwIvldAdjDemandForeActual> vwIvldAdjDemandForeActuals = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, vwIvldAdjDemandForeActuals.size());
		Assert.assertEquals(newVwIvldAdjDemandForeActual,
			vwIvldAdjDemandForeActuals.get(
				newVwIvldAdjDemandForeActual.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, VwIvldAdjDemandForeActual> vwIvldAdjDemandForeActuals = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(vwIvldAdjDemandForeActuals.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		VwIvldAdjDemandForeActual newVwIvldAdjDemandForeActual = addVwIvldAdjDemandForeActual();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwIvldAdjDemandForeActual.getPrimaryKey());

		Map<Serializable, VwIvldAdjDemandForeActual> vwIvldAdjDemandForeActuals = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, vwIvldAdjDemandForeActuals.size());
		Assert.assertEquals(newVwIvldAdjDemandForeActual,
			vwIvldAdjDemandForeActuals.get(
				newVwIvldAdjDemandForeActual.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = VwIvldAdjDemandForeActualLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<VwIvldAdjDemandForeActual>() {
				@Override
				public void performAction(
					VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual) {
					Assert.assertNotNull(vwIvldAdjDemandForeActual);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		VwIvldAdjDemandForeActual newVwIvldAdjDemandForeActual = addVwIvldAdjDemandForeActual();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwIvldAdjDemandForeActual.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"ivldAdjustedDemandForecastSid",
				newVwIvldAdjDemandForeActual.getIvldAdjustedDemandForecastSid()));

		List<VwIvldAdjDemandForeActual> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		VwIvldAdjDemandForeActual existingVwIvldAdjDemandForeActual = result.get(0);

		Assert.assertEquals(existingVwIvldAdjDemandForeActual,
			newVwIvldAdjDemandForeActual);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwIvldAdjDemandForeActual.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"ivldAdjustedDemandForecastSid", RandomTestUtil.nextInt()));

		List<VwIvldAdjDemandForeActual> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		VwIvldAdjDemandForeActual newVwIvldAdjDemandForeActual = addVwIvldAdjDemandForeActual();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwIvldAdjDemandForeActual.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldAdjustedDemandForecastSid"));

		Object newIvldAdjustedDemandForecastSid = newVwIvldAdjDemandForeActual.getIvldAdjustedDemandForecastSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"ivldAdjustedDemandForecastSid",
				new Object[] { newIvldAdjustedDemandForecastSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingIvldAdjustedDemandForecastSid = result.get(0);

		Assert.assertEquals(existingIvldAdjustedDemandForecastSid,
			newIvldAdjustedDemandForecastSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwIvldAdjDemandForeActual.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldAdjustedDemandForecastSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"ivldAdjustedDemandForecastSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected VwIvldAdjDemandForeActual addVwIvldAdjDemandForeActual()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual = _persistence.create(pk);

		vwIvldAdjDemandForeActual.setForecastVersion(RandomTestUtil.randomString());

		vwIvldAdjDemandForeActual.setGrossUnits(RandomTestUtil.randomString());

		vwIvldAdjDemandForeActual.setBusinessUnitNo(RandomTestUtil.randomString());

		vwIvldAdjDemandForeActual.setYear(RandomTestUtil.randomString());

		vwIvldAdjDemandForeActual.setItemId(RandomTestUtil.randomString());

		vwIvldAdjDemandForeActual.setBrandName(RandomTestUtil.randomString());

		vwIvldAdjDemandForeActual.setModifiedDate(RandomTestUtil.nextDate());

		vwIvldAdjDemandForeActual.setOrganizationKey(RandomTestUtil.randomString());

		vwIvldAdjDemandForeActual.setCreatedDate(RandomTestUtil.nextDate());

		vwIvldAdjDemandForeActual.setCreatedBy(RandomTestUtil.randomString());

		vwIvldAdjDemandForeActual.setSource(RandomTestUtil.randomString());

		vwIvldAdjDemandForeActual.setMarketShareRatio(RandomTestUtil.randomString());

		vwIvldAdjDemandForeActual.setBusinessUnitName(RandomTestUtil.randomString());

		vwIvldAdjDemandForeActual.setAddChgDelIndicator(RandomTestUtil.randomString());

		vwIvldAdjDemandForeActual.setItemIdentifier(RandomTestUtil.randomString());

		vwIvldAdjDemandForeActual.setErrorCode(RandomTestUtil.randomString());

		vwIvldAdjDemandForeActual.setModifiedBy(RandomTestUtil.randomString());

		vwIvldAdjDemandForeActual.setMarketShareUnits(RandomTestUtil.randomString());

		vwIvldAdjDemandForeActual.setMonth(RandomTestUtil.randomString());

		vwIvldAdjDemandForeActual.setInventoryUnitChange(RandomTestUtil.randomString());

		vwIvldAdjDemandForeActual.setReprocessedFlag(RandomTestUtil.randomString());

		vwIvldAdjDemandForeActual.setUncapturedUnitsRatio(RandomTestUtil.randomString());

		vwIvldAdjDemandForeActual.setReasonForFailure(RandomTestUtil.randomString());

		vwIvldAdjDemandForeActual.setAdjustedDemandForecastIntfId(RandomTestUtil.randomString());

		vwIvldAdjDemandForeActual.setCountry(RandomTestUtil.randomString());

		vwIvldAdjDemandForeActual.setForecastType(RandomTestUtil.randomString());

		vwIvldAdjDemandForeActual.setTotalAdjustedDemandUnits(RandomTestUtil.randomString());

		vwIvldAdjDemandForeActual.setBrandId(RandomTestUtil.randomString());

		vwIvldAdjDemandForeActual.setIsForecast(RandomTestUtil.randomString());

		vwIvldAdjDemandForeActual.setTotalAdjustedDemandAmount(RandomTestUtil.randomString());

		vwIvldAdjDemandForeActual.setUncapturedUnits(RandomTestUtil.randomString());

		vwIvldAdjDemandForeActual.setGrossPrice(RandomTestUtil.randomString());

		vwIvldAdjDemandForeActual.setGrossAmount(RandomTestUtil.randomString());

		vwIvldAdjDemandForeActual.setItemIdentifierCodeQualifier(RandomTestUtil.randomString());

		vwIvldAdjDemandForeActual.setBatchId(RandomTestUtil.randomString());

		vwIvldAdjDemandForeActual.setItemName(RandomTestUtil.randomString());

		vwIvldAdjDemandForeActual.setErrorField(RandomTestUtil.randomString());

		vwIvldAdjDemandForeActual.setNetSalesPrice(RandomTestUtil.randomString());

		vwIvldAdjDemandForeActual.setNetSalesAmount(RandomTestUtil.randomString());

		vwIvldAdjDemandForeActual.setSegment(RandomTestUtil.randomString());

		vwIvldAdjDemandForeActual.setForecastName(RandomTestUtil.randomString());

		vwIvldAdjDemandForeActual.setMarketSizeUnits(RandomTestUtil.randomString());

		vwIvldAdjDemandForeActual.setCheckRecord(RandomTestUtil.randomBoolean());

		_vwIvldAdjDemandForeActuals.add(_persistence.update(
				vwIvldAdjDemandForeActual));

		return vwIvldAdjDemandForeActual;
	}

	private List<VwIvldAdjDemandForeActual> _vwIvldAdjDemandForeActuals = new ArrayList<VwIvldAdjDemandForeActual>();
	private VwIvldAdjDemandForeActualPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}