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

import com.stpl.app.exception.NoSuchGcmGlobalDetailsException;
import com.stpl.app.model.GcmGlobalDetails;
import com.stpl.app.service.GcmGlobalDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.GcmGlobalDetailsPersistence;
import com.stpl.app.service.persistence.GcmGlobalDetailsUtil;

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
public class GcmGlobalDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = GcmGlobalDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<GcmGlobalDetails> iterator = _gcmGlobalDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		GcmGlobalDetails gcmGlobalDetails = _persistence.create(pk);

		Assert.assertNotNull(gcmGlobalDetails);

		Assert.assertEquals(gcmGlobalDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		GcmGlobalDetails newGcmGlobalDetails = addGcmGlobalDetails();

		_persistence.remove(newGcmGlobalDetails);

		GcmGlobalDetails existingGcmGlobalDetails = _persistence.fetchByPrimaryKey(newGcmGlobalDetails.getPrimaryKey());

		Assert.assertNull(existingGcmGlobalDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addGcmGlobalDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		GcmGlobalDetails newGcmGlobalDetails = _persistence.create(pk);

		newGcmGlobalDetails.setItemStatus(RandomTestUtil.randomString());

		newGcmGlobalDetails.setFormulaMethodId(RandomTestUtil.randomString());

		newGcmGlobalDetails.setModuleName(RandomTestUtil.randomString());

		newGcmGlobalDetails.setPaymentFrequency(RandomTestUtil.randomString());

		newGcmGlobalDetails.setEndDate(RandomTestUtil.nextDate());

		newGcmGlobalDetails.setCfpStartDate(RandomTestUtil.nextDate());

		newGcmGlobalDetails.setPriceProtectionStartDate(RandomTestUtil.nextDate());

		newGcmGlobalDetails.setTempItemMasterSid(RandomTestUtil.randomString());

		newGcmGlobalDetails.setBrandName(RandomTestUtil.randomString());

		newGcmGlobalDetails.setModifiedDate(RandomTestUtil.nextDate());

		newGcmGlobalDetails.setContractMasterSid(RandomTestUtil.nextInt());

		newGcmGlobalDetails.setModifiedBy(RandomTestUtil.nextInt());

		newGcmGlobalDetails.setSubModuleName(RandomTestUtil.randomString());

		newGcmGlobalDetails.setTheraputicClass(RandomTestUtil.randomString());

		newGcmGlobalDetails.setCheckRecord(RandomTestUtil.randomBoolean());

		newGcmGlobalDetails.setPaymentMethod(RandomTestUtil.randomString());

		newGcmGlobalDetails.setContractPriceEndDate(RandomTestUtil.nextDate());

		newGcmGlobalDetails.setPsContractSid(RandomTestUtil.nextInt());

		newGcmGlobalDetails.setPriceProtectionEndDate(RandomTestUtil.nextDate());

		newGcmGlobalDetails.setStartDate(RandomTestUtil.nextDate());

		newGcmGlobalDetails.setScreenName(RandomTestUtil.randomString());

		newGcmGlobalDetails.setRsContractSid(RandomTestUtil.nextInt());

		newGcmGlobalDetails.setItemName(RandomTestUtil.randomString());

		newGcmGlobalDetails.setSessionId(RandomTestUtil.randomString());

		newGcmGlobalDetails.setCfpStatus(RandomTestUtil.randomString());

		newGcmGlobalDetails.setRsModelSid(RandomTestUtil.nextInt());

		newGcmGlobalDetails.setCfpContractSid(RandomTestUtil.nextInt());

		newGcmGlobalDetails.setPrice(RandomTestUtil.nextDouble());

		newGcmGlobalDetails.setTempEndDate(RandomTestUtil.nextDate());

		newGcmGlobalDetails.setItemMasterSid(RandomTestUtil.nextInt());

		newGcmGlobalDetails.setItemType(RandomTestUtil.randomString());

		newGcmGlobalDetails.setForecastingType(RandomTestUtil.randomString());

		newGcmGlobalDetails.setItemId(RandomTestUtil.randomString());

		newGcmGlobalDetails.setBasePrice(RandomTestUtil.nextDouble());

		newGcmGlobalDetails.setStatus(RandomTestUtil.randomString());

		newGcmGlobalDetails.setFormulaName(RandomTestUtil.randomString());

		newGcmGlobalDetails.setWorkflowMasterSid(RandomTestUtil.nextInt());

		newGcmGlobalDetails.setPriceTolerance(RandomTestUtil.nextDouble());

		newGcmGlobalDetails.setCreatedBy(RandomTestUtil.nextInt());

		newGcmGlobalDetails.setCreatedDate(RandomTestUtil.nextDate());

		newGcmGlobalDetails.setTempStartDate(RandomTestUtil.nextDate());

		newGcmGlobalDetails.setCfpEndDate(RandomTestUtil.nextDate());

		newGcmGlobalDetails.setPsModelSid(RandomTestUtil.nextInt());

		newGcmGlobalDetails.setFormulaId(RandomTestUtil.randomString());

		newGcmGlobalDetails.setItemNo(RandomTestUtil.randomString());

		newGcmGlobalDetails.setContractPrice(RandomTestUtil.nextDouble());

		newGcmGlobalDetails.setIfpModelSid(RandomTestUtil.nextInt());

		newGcmGlobalDetails.setPriceToleranceType(RandomTestUtil.nextInt());

		newGcmGlobalDetails.setRebateAmount(RandomTestUtil.nextInt());

		newGcmGlobalDetails.setUserId(RandomTestUtil.nextInt());

		newGcmGlobalDetails.setProjectionMasterSid(RandomTestUtil.nextInt());

		newGcmGlobalDetails.setContractPriceStartDate(RandomTestUtil.nextDate());

		newGcmGlobalDetails.setPriceToleranceFrequency(RandomTestUtil.nextInt());

		newGcmGlobalDetails.setIfpContractAttachedStatus(RandomTestUtil.randomString());

		newGcmGlobalDetails.setRebatePlanSystemId(RandomTestUtil.nextInt());

		newGcmGlobalDetails.setRebatePlanName(RandomTestUtil.randomString());

		newGcmGlobalDetails.setCalendar(RandomTestUtil.randomString());

		newGcmGlobalDetails.setPricingQualifierSid(RandomTestUtil.randomString());

		newGcmGlobalDetails.setTempStatus(RandomTestUtil.randomString());

		newGcmGlobalDetails.setItemRebateEndDate(RandomTestUtil.nextDate());

		newGcmGlobalDetails.setPriceToleranceInterval(RandomTestUtil.nextInt());

		newGcmGlobalDetails.setItemRebateStartDate(RandomTestUtil.nextDate());

		newGcmGlobalDetails.setOperation(RandomTestUtil.randomString());

		newGcmGlobalDetails.setCfpModelSid(RandomTestUtil.nextInt());

		newGcmGlobalDetails.setItemStatusSid(RandomTestUtil.nextInt());

		newGcmGlobalDetails.setIfpContractSid(RandomTestUtil.nextInt());

		_gcmGlobalDetailses.add(_persistence.update(newGcmGlobalDetails));

		GcmGlobalDetails existingGcmGlobalDetails = _persistence.findByPrimaryKey(newGcmGlobalDetails.getPrimaryKey());

		Assert.assertEquals(existingGcmGlobalDetails.getItemStatus(),
			newGcmGlobalDetails.getItemStatus());
		Assert.assertEquals(existingGcmGlobalDetails.getFormulaMethodId(),
			newGcmGlobalDetails.getFormulaMethodId());
		Assert.assertEquals(existingGcmGlobalDetails.getModuleName(),
			newGcmGlobalDetails.getModuleName());
		Assert.assertEquals(existingGcmGlobalDetails.getPaymentFrequency(),
			newGcmGlobalDetails.getPaymentFrequency());
		Assert.assertEquals(Time.getShortTimestamp(
				existingGcmGlobalDetails.getEndDate()),
			Time.getShortTimestamp(newGcmGlobalDetails.getEndDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingGcmGlobalDetails.getCfpStartDate()),
			Time.getShortTimestamp(newGcmGlobalDetails.getCfpStartDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingGcmGlobalDetails.getPriceProtectionStartDate()),
			Time.getShortTimestamp(
				newGcmGlobalDetails.getPriceProtectionStartDate()));
		Assert.assertEquals(existingGcmGlobalDetails.getTempItemMasterSid(),
			newGcmGlobalDetails.getTempItemMasterSid());
		Assert.assertEquals(existingGcmGlobalDetails.getBrandName(),
			newGcmGlobalDetails.getBrandName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingGcmGlobalDetails.getModifiedDate()),
			Time.getShortTimestamp(newGcmGlobalDetails.getModifiedDate()));
		Assert.assertEquals(existingGcmGlobalDetails.getContractMasterSid(),
			newGcmGlobalDetails.getContractMasterSid());
		Assert.assertEquals(existingGcmGlobalDetails.getModifiedBy(),
			newGcmGlobalDetails.getModifiedBy());
		Assert.assertEquals(existingGcmGlobalDetails.getSubModuleName(),
			newGcmGlobalDetails.getSubModuleName());
		Assert.assertEquals(existingGcmGlobalDetails.getTheraputicClass(),
			newGcmGlobalDetails.getTheraputicClass());
		Assert.assertEquals(existingGcmGlobalDetails.getGcmGlobalDetailsSid(),
			newGcmGlobalDetails.getGcmGlobalDetailsSid());
		Assert.assertEquals(existingGcmGlobalDetails.getCheckRecord(),
			newGcmGlobalDetails.getCheckRecord());
		Assert.assertEquals(existingGcmGlobalDetails.getPaymentMethod(),
			newGcmGlobalDetails.getPaymentMethod());
		Assert.assertEquals(Time.getShortTimestamp(
				existingGcmGlobalDetails.getContractPriceEndDate()),
			Time.getShortTimestamp(
				newGcmGlobalDetails.getContractPriceEndDate()));
		Assert.assertEquals(existingGcmGlobalDetails.getPsContractSid(),
			newGcmGlobalDetails.getPsContractSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingGcmGlobalDetails.getPriceProtectionEndDate()),
			Time.getShortTimestamp(
				newGcmGlobalDetails.getPriceProtectionEndDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingGcmGlobalDetails.getStartDate()),
			Time.getShortTimestamp(newGcmGlobalDetails.getStartDate()));
		Assert.assertEquals(existingGcmGlobalDetails.getScreenName(),
			newGcmGlobalDetails.getScreenName());
		Assert.assertEquals(existingGcmGlobalDetails.getRsContractSid(),
			newGcmGlobalDetails.getRsContractSid());
		Assert.assertEquals(existingGcmGlobalDetails.getItemName(),
			newGcmGlobalDetails.getItemName());
		Assert.assertEquals(existingGcmGlobalDetails.getSessionId(),
			newGcmGlobalDetails.getSessionId());
		Assert.assertEquals(existingGcmGlobalDetails.getCfpStatus(),
			newGcmGlobalDetails.getCfpStatus());
		Assert.assertEquals(existingGcmGlobalDetails.getRsModelSid(),
			newGcmGlobalDetails.getRsModelSid());
		Assert.assertEquals(existingGcmGlobalDetails.getCfpContractSid(),
			newGcmGlobalDetails.getCfpContractSid());
		AssertUtils.assertEquals(existingGcmGlobalDetails.getPrice(),
			newGcmGlobalDetails.getPrice());
		Assert.assertEquals(Time.getShortTimestamp(
				existingGcmGlobalDetails.getTempEndDate()),
			Time.getShortTimestamp(newGcmGlobalDetails.getTempEndDate()));
		Assert.assertEquals(existingGcmGlobalDetails.getItemMasterSid(),
			newGcmGlobalDetails.getItemMasterSid());
		Assert.assertEquals(existingGcmGlobalDetails.getItemType(),
			newGcmGlobalDetails.getItemType());
		Assert.assertEquals(existingGcmGlobalDetails.getForecastingType(),
			newGcmGlobalDetails.getForecastingType());
		Assert.assertEquals(existingGcmGlobalDetails.getItemId(),
			newGcmGlobalDetails.getItemId());
		AssertUtils.assertEquals(existingGcmGlobalDetails.getBasePrice(),
			newGcmGlobalDetails.getBasePrice());
		Assert.assertEquals(existingGcmGlobalDetails.getStatus(),
			newGcmGlobalDetails.getStatus());
		Assert.assertEquals(existingGcmGlobalDetails.getFormulaName(),
			newGcmGlobalDetails.getFormulaName());
		Assert.assertEquals(existingGcmGlobalDetails.getWorkflowMasterSid(),
			newGcmGlobalDetails.getWorkflowMasterSid());
		AssertUtils.assertEquals(existingGcmGlobalDetails.getPriceTolerance(),
			newGcmGlobalDetails.getPriceTolerance());
		Assert.assertEquals(existingGcmGlobalDetails.getCreatedBy(),
			newGcmGlobalDetails.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingGcmGlobalDetails.getCreatedDate()),
			Time.getShortTimestamp(newGcmGlobalDetails.getCreatedDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingGcmGlobalDetails.getTempStartDate()),
			Time.getShortTimestamp(newGcmGlobalDetails.getTempStartDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingGcmGlobalDetails.getCfpEndDate()),
			Time.getShortTimestamp(newGcmGlobalDetails.getCfpEndDate()));
		Assert.assertEquals(existingGcmGlobalDetails.getPsModelSid(),
			newGcmGlobalDetails.getPsModelSid());
		Assert.assertEquals(existingGcmGlobalDetails.getFormulaId(),
			newGcmGlobalDetails.getFormulaId());
		Assert.assertEquals(existingGcmGlobalDetails.getItemNo(),
			newGcmGlobalDetails.getItemNo());
		AssertUtils.assertEquals(existingGcmGlobalDetails.getContractPrice(),
			newGcmGlobalDetails.getContractPrice());
		Assert.assertEquals(existingGcmGlobalDetails.getIfpModelSid(),
			newGcmGlobalDetails.getIfpModelSid());
		Assert.assertEquals(existingGcmGlobalDetails.getPriceToleranceType(),
			newGcmGlobalDetails.getPriceToleranceType());
		Assert.assertEquals(existingGcmGlobalDetails.getRebateAmount(),
			newGcmGlobalDetails.getRebateAmount());
		Assert.assertEquals(existingGcmGlobalDetails.getUserId(),
			newGcmGlobalDetails.getUserId());
		Assert.assertEquals(existingGcmGlobalDetails.getProjectionMasterSid(),
			newGcmGlobalDetails.getProjectionMasterSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingGcmGlobalDetails.getContractPriceStartDate()),
			Time.getShortTimestamp(
				newGcmGlobalDetails.getContractPriceStartDate()));
		Assert.assertEquals(existingGcmGlobalDetails.getPriceToleranceFrequency(),
			newGcmGlobalDetails.getPriceToleranceFrequency());
		Assert.assertEquals(existingGcmGlobalDetails.getIfpContractAttachedStatus(),
			newGcmGlobalDetails.getIfpContractAttachedStatus());
		Assert.assertEquals(existingGcmGlobalDetails.getRebatePlanSystemId(),
			newGcmGlobalDetails.getRebatePlanSystemId());
		Assert.assertEquals(existingGcmGlobalDetails.getRebatePlanName(),
			newGcmGlobalDetails.getRebatePlanName());
		Assert.assertEquals(existingGcmGlobalDetails.getCalendar(),
			newGcmGlobalDetails.getCalendar());
		Assert.assertEquals(existingGcmGlobalDetails.getPricingQualifierSid(),
			newGcmGlobalDetails.getPricingQualifierSid());
		Assert.assertEquals(existingGcmGlobalDetails.getTempStatus(),
			newGcmGlobalDetails.getTempStatus());
		Assert.assertEquals(Time.getShortTimestamp(
				existingGcmGlobalDetails.getItemRebateEndDate()),
			Time.getShortTimestamp(newGcmGlobalDetails.getItemRebateEndDate()));
		Assert.assertEquals(existingGcmGlobalDetails.getPriceToleranceInterval(),
			newGcmGlobalDetails.getPriceToleranceInterval());
		Assert.assertEquals(Time.getShortTimestamp(
				existingGcmGlobalDetails.getItemRebateStartDate()),
			Time.getShortTimestamp(newGcmGlobalDetails.getItemRebateStartDate()));
		Assert.assertEquals(existingGcmGlobalDetails.getOperation(),
			newGcmGlobalDetails.getOperation());
		Assert.assertEquals(existingGcmGlobalDetails.getCfpModelSid(),
			newGcmGlobalDetails.getCfpModelSid());
		Assert.assertEquals(existingGcmGlobalDetails.getItemStatusSid(),
			newGcmGlobalDetails.getItemStatusSid());
		Assert.assertEquals(existingGcmGlobalDetails.getIfpContractSid(),
			newGcmGlobalDetails.getIfpContractSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		GcmGlobalDetails newGcmGlobalDetails = addGcmGlobalDetails();

		GcmGlobalDetails existingGcmGlobalDetails = _persistence.findByPrimaryKey(newGcmGlobalDetails.getPrimaryKey());

		Assert.assertEquals(existingGcmGlobalDetails, newGcmGlobalDetails);
	}

	@Test(expected = NoSuchGcmGlobalDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<GcmGlobalDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("GCM_GLOBAL_DETAILS",
			"itemStatus", true, "formulaMethodId", true, "moduleName", true,
			"paymentFrequency", true, "endDate", true, "cfpStartDate", true,
			"priceProtectionStartDate", true, "tempItemMasterSid", true,
			"brandName", true, "modifiedDate", true, "contractMasterSid", true,
			"modifiedBy", true, "subModuleName", true, "theraputicClass", true,
			"gcmGlobalDetailsSid", true, "checkRecord", true, "paymentMethod",
			true, "contractPriceEndDate", true, "psContractSid", true,
			"priceProtectionEndDate", true, "startDate", true, "screenName",
			true, "rsContractSid", true, "itemName", true, "sessionId", true,
			"cfpStatus", true, "rsModelSid", true, "cfpContractSid", true,
			"price", true, "tempEndDate", true, "itemMasterSid", true,
			"itemType", true, "forecastingType", true, "itemId", true,
			"basePrice", true, "status", true, "formulaName", true,
			"workflowMasterSid", true, "priceTolerance", true, "createdBy",
			true, "createdDate", true, "tempStartDate", true, "cfpEndDate",
			true, "psModelSid", true, "formulaId", true, "itemNo", true,
			"contractPrice", true, "ifpModelSid", true, "priceToleranceType",
			true, "rebateAmount", true, "userId", true, "projectionMasterSid",
			true, "contractPriceStartDate", true, "priceToleranceFrequency",
			true, "ifpContractAttachedStatus", true, "rebatePlanSystemId",
			true, "rebatePlanName", true, "calendar", true,
			"pricingQualifierSid", true, "tempStatus", true,
			"itemRebateEndDate", true, "priceToleranceInterval", true,
			"itemRebateStartDate", true, "operation", true, "cfpModelSid",
			true, "itemStatusSid", true, "ifpContractSid", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		GcmGlobalDetails newGcmGlobalDetails = addGcmGlobalDetails();

		GcmGlobalDetails existingGcmGlobalDetails = _persistence.fetchByPrimaryKey(newGcmGlobalDetails.getPrimaryKey());

		Assert.assertEquals(existingGcmGlobalDetails, newGcmGlobalDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		GcmGlobalDetails missingGcmGlobalDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingGcmGlobalDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		GcmGlobalDetails newGcmGlobalDetails1 = addGcmGlobalDetails();
		GcmGlobalDetails newGcmGlobalDetails2 = addGcmGlobalDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newGcmGlobalDetails1.getPrimaryKey());
		primaryKeys.add(newGcmGlobalDetails2.getPrimaryKey());

		Map<Serializable, GcmGlobalDetails> gcmGlobalDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, gcmGlobalDetailses.size());
		Assert.assertEquals(newGcmGlobalDetails1,
			gcmGlobalDetailses.get(newGcmGlobalDetails1.getPrimaryKey()));
		Assert.assertEquals(newGcmGlobalDetails2,
			gcmGlobalDetailses.get(newGcmGlobalDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, GcmGlobalDetails> gcmGlobalDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(gcmGlobalDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		GcmGlobalDetails newGcmGlobalDetails = addGcmGlobalDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newGcmGlobalDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, GcmGlobalDetails> gcmGlobalDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, gcmGlobalDetailses.size());
		Assert.assertEquals(newGcmGlobalDetails,
			gcmGlobalDetailses.get(newGcmGlobalDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, GcmGlobalDetails> gcmGlobalDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(gcmGlobalDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		GcmGlobalDetails newGcmGlobalDetails = addGcmGlobalDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newGcmGlobalDetails.getPrimaryKey());

		Map<Serializable, GcmGlobalDetails> gcmGlobalDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, gcmGlobalDetailses.size());
		Assert.assertEquals(newGcmGlobalDetails,
			gcmGlobalDetailses.get(newGcmGlobalDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = GcmGlobalDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<GcmGlobalDetails>() {
				@Override
				public void performAction(GcmGlobalDetails gcmGlobalDetails) {
					Assert.assertNotNull(gcmGlobalDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		GcmGlobalDetails newGcmGlobalDetails = addGcmGlobalDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(GcmGlobalDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("gcmGlobalDetailsSid",
				newGcmGlobalDetails.getGcmGlobalDetailsSid()));

		List<GcmGlobalDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		GcmGlobalDetails existingGcmGlobalDetails = result.get(0);

		Assert.assertEquals(existingGcmGlobalDetails, newGcmGlobalDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(GcmGlobalDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("gcmGlobalDetailsSid",
				RandomTestUtil.nextInt()));

		List<GcmGlobalDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		GcmGlobalDetails newGcmGlobalDetails = addGcmGlobalDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(GcmGlobalDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"gcmGlobalDetailsSid"));

		Object newGcmGlobalDetailsSid = newGcmGlobalDetails.getGcmGlobalDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("gcmGlobalDetailsSid",
				new Object[] { newGcmGlobalDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingGcmGlobalDetailsSid = result.get(0);

		Assert.assertEquals(existingGcmGlobalDetailsSid, newGcmGlobalDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(GcmGlobalDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"gcmGlobalDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("gcmGlobalDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected GcmGlobalDetails addGcmGlobalDetails() throws Exception {
		int pk = RandomTestUtil.nextInt();

		GcmGlobalDetails gcmGlobalDetails = _persistence.create(pk);

		gcmGlobalDetails.setItemStatus(RandomTestUtil.randomString());

		gcmGlobalDetails.setFormulaMethodId(RandomTestUtil.randomString());

		gcmGlobalDetails.setModuleName(RandomTestUtil.randomString());

		gcmGlobalDetails.setPaymentFrequency(RandomTestUtil.randomString());

		gcmGlobalDetails.setEndDate(RandomTestUtil.nextDate());

		gcmGlobalDetails.setCfpStartDate(RandomTestUtil.nextDate());

		gcmGlobalDetails.setPriceProtectionStartDate(RandomTestUtil.nextDate());

		gcmGlobalDetails.setTempItemMasterSid(RandomTestUtil.randomString());

		gcmGlobalDetails.setBrandName(RandomTestUtil.randomString());

		gcmGlobalDetails.setModifiedDate(RandomTestUtil.nextDate());

		gcmGlobalDetails.setContractMasterSid(RandomTestUtil.nextInt());

		gcmGlobalDetails.setModifiedBy(RandomTestUtil.nextInt());

		gcmGlobalDetails.setSubModuleName(RandomTestUtil.randomString());

		gcmGlobalDetails.setTheraputicClass(RandomTestUtil.randomString());

		gcmGlobalDetails.setCheckRecord(RandomTestUtil.randomBoolean());

		gcmGlobalDetails.setPaymentMethod(RandomTestUtil.randomString());

		gcmGlobalDetails.setContractPriceEndDate(RandomTestUtil.nextDate());

		gcmGlobalDetails.setPsContractSid(RandomTestUtil.nextInt());

		gcmGlobalDetails.setPriceProtectionEndDate(RandomTestUtil.nextDate());

		gcmGlobalDetails.setStartDate(RandomTestUtil.nextDate());

		gcmGlobalDetails.setScreenName(RandomTestUtil.randomString());

		gcmGlobalDetails.setRsContractSid(RandomTestUtil.nextInt());

		gcmGlobalDetails.setItemName(RandomTestUtil.randomString());

		gcmGlobalDetails.setSessionId(RandomTestUtil.randomString());

		gcmGlobalDetails.setCfpStatus(RandomTestUtil.randomString());

		gcmGlobalDetails.setRsModelSid(RandomTestUtil.nextInt());

		gcmGlobalDetails.setCfpContractSid(RandomTestUtil.nextInt());

		gcmGlobalDetails.setPrice(RandomTestUtil.nextDouble());

		gcmGlobalDetails.setTempEndDate(RandomTestUtil.nextDate());

		gcmGlobalDetails.setItemMasterSid(RandomTestUtil.nextInt());

		gcmGlobalDetails.setItemType(RandomTestUtil.randomString());

		gcmGlobalDetails.setForecastingType(RandomTestUtil.randomString());

		gcmGlobalDetails.setItemId(RandomTestUtil.randomString());

		gcmGlobalDetails.setBasePrice(RandomTestUtil.nextDouble());

		gcmGlobalDetails.setStatus(RandomTestUtil.randomString());

		gcmGlobalDetails.setFormulaName(RandomTestUtil.randomString());

		gcmGlobalDetails.setWorkflowMasterSid(RandomTestUtil.nextInt());

		gcmGlobalDetails.setPriceTolerance(RandomTestUtil.nextDouble());

		gcmGlobalDetails.setCreatedBy(RandomTestUtil.nextInt());

		gcmGlobalDetails.setCreatedDate(RandomTestUtil.nextDate());

		gcmGlobalDetails.setTempStartDate(RandomTestUtil.nextDate());

		gcmGlobalDetails.setCfpEndDate(RandomTestUtil.nextDate());

		gcmGlobalDetails.setPsModelSid(RandomTestUtil.nextInt());

		gcmGlobalDetails.setFormulaId(RandomTestUtil.randomString());

		gcmGlobalDetails.setItemNo(RandomTestUtil.randomString());

		gcmGlobalDetails.setContractPrice(RandomTestUtil.nextDouble());

		gcmGlobalDetails.setIfpModelSid(RandomTestUtil.nextInt());

		gcmGlobalDetails.setPriceToleranceType(RandomTestUtil.nextInt());

		gcmGlobalDetails.setRebateAmount(RandomTestUtil.nextInt());

		gcmGlobalDetails.setUserId(RandomTestUtil.nextInt());

		gcmGlobalDetails.setProjectionMasterSid(RandomTestUtil.nextInt());

		gcmGlobalDetails.setContractPriceStartDate(RandomTestUtil.nextDate());

		gcmGlobalDetails.setPriceToleranceFrequency(RandomTestUtil.nextInt());

		gcmGlobalDetails.setIfpContractAttachedStatus(RandomTestUtil.randomString());

		gcmGlobalDetails.setRebatePlanSystemId(RandomTestUtil.nextInt());

		gcmGlobalDetails.setRebatePlanName(RandomTestUtil.randomString());

		gcmGlobalDetails.setCalendar(RandomTestUtil.randomString());

		gcmGlobalDetails.setPricingQualifierSid(RandomTestUtil.randomString());

		gcmGlobalDetails.setTempStatus(RandomTestUtil.randomString());

		gcmGlobalDetails.setItemRebateEndDate(RandomTestUtil.nextDate());

		gcmGlobalDetails.setPriceToleranceInterval(RandomTestUtil.nextInt());

		gcmGlobalDetails.setItemRebateStartDate(RandomTestUtil.nextDate());

		gcmGlobalDetails.setOperation(RandomTestUtil.randomString());

		gcmGlobalDetails.setCfpModelSid(RandomTestUtil.nextInt());

		gcmGlobalDetails.setItemStatusSid(RandomTestUtil.nextInt());

		gcmGlobalDetails.setIfpContractSid(RandomTestUtil.nextInt());

		_gcmGlobalDetailses.add(_persistence.update(gcmGlobalDetails));

		return gcmGlobalDetails;
	}

	private List<GcmGlobalDetails> _gcmGlobalDetailses = new ArrayList<GcmGlobalDetails>();
	private GcmGlobalDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}