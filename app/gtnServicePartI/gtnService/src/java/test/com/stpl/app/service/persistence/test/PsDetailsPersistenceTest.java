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

import com.stpl.app.exception.NoSuchPsDetailsException;
import com.stpl.app.model.PsDetails;
import com.stpl.app.service.PsDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.PsDetailsPersistence;
import com.stpl.app.service.persistence.PsDetailsUtil;

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
public class PsDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = PsDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<PsDetails> iterator = _psDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		PsDetails psDetails = _persistence.create(pk);

		Assert.assertNotNull(psDetails);

		Assert.assertEquals(psDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		PsDetails newPsDetails = addPsDetails();

		_persistence.remove(newPsDetails);

		PsDetails existingPsDetails = _persistence.fetchByPrimaryKey(newPsDetails.getPrimaryKey());

		Assert.assertNull(existingPsDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addPsDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		PsDetails newPsDetails = _persistence.create(pk);

		newPsDetails.setNepFormula(RandomTestUtil.nextInt());

		newPsDetails.setPrice(RandomTestUtil.nextDouble());

		newPsDetails.setItemMasterSid(RandomTestUtil.nextInt());

		newPsDetails.setResetType(RandomTestUtil.nextInt());

		newPsDetails.setPriceProtectionStartDate(RandomTestUtil.nextDate());

		newPsDetails.setResetDate(RandomTestUtil.nextDate());

		newPsDetails.setBasePrice(RandomTestUtil.nextDouble());

		newPsDetails.setItemPsAttachedDate(RandomTestUtil.nextDate());

		newPsDetails.setBrandMasterSid(RandomTestUtil.randomString());

		newPsDetails.setStatus(RandomTestUtil.nextInt());

		newPsDetails.setModifiedDate(RandomTestUtil.nextDate());

		newPsDetails.setItemPsAttachedStatus(RandomTestUtil.nextInt());

		newPsDetails.setRevisionDate(RandomTestUtil.nextDate());

		newPsDetails.setPriceTolerance(RandomTestUtil.nextDouble());

		newPsDetails.setCreatedDate(RandomTestUtil.nextDate());

		newPsDetails.setCreatedBy(RandomTestUtil.nextInt());

		newPsDetails.setSource(RandomTestUtil.randomString());

		newPsDetails.setPsModelSid(RandomTestUtil.nextInt());

		newPsDetails.setSuggestedPrice(RandomTestUtil.nextDouble());

		newPsDetails.setNetPriceTypeFormula(RandomTestUtil.randomString());

		newPsDetails.setPriceProtectionPriceType(RandomTestUtil.nextInt());

		newPsDetails.setModifiedBy(RandomTestUtil.nextInt());

		newPsDetails.setInboundStatus(RandomTestUtil.randomString());

		newPsDetails.setContractPrice(RandomTestUtil.nextDouble());

		newPsDetails.setIfpModelSid(RandomTestUtil.nextInt());

		newPsDetails.setPriceToleranceType(RandomTestUtil.nextInt());

		newPsDetails.setMaxIncrementalChange(RandomTestUtil.nextDouble());

		newPsDetails.setItemPricingQualifierSid(RandomTestUtil.nextInt());

		newPsDetails.setContractPriceEndDate(RandomTestUtil.nextDate());

		newPsDetails.setNep(RandomTestUtil.nextDouble());

		newPsDetails.setContractPriceStartDate(RandomTestUtil.nextDate());

		newPsDetails.setPriceToleranceFrequency(RandomTestUtil.nextInt());

		newPsDetails.setPriceProtectionEndDate(RandomTestUtil.nextDate());

		newPsDetails.setPriceProtectionStatus(RandomTestUtil.nextInt());

		newPsDetails.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newPsDetails.setResetEligible(RandomTestUtil.nextInt());

		newPsDetails.setBatchId(RandomTestUtil.randomString());

		newPsDetails.setPriceToleranceInterval(RandomTestUtil.nextInt());

		newPsDetails.setNetPriceType(RandomTestUtil.nextInt());

		newPsDetails.setPriceRevision(RandomTestUtil.nextDouble());

		newPsDetails.setResetFrequency(RandomTestUtil.nextInt());

		newPsDetails.setResetInterval(RandomTestUtil.nextInt());

		newPsDetails.setBasePriceType(RandomTestUtil.nextInt());

		newPsDetails.setBasePriceEntry(RandomTestUtil.nextDouble());

		newPsDetails.setBasePriceDate(RandomTestUtil.nextDate());

		newPsDetails.setNetBasePrice(RandomTestUtil.nextInt());

		newPsDetails.setBasePriceDdlb(RandomTestUtil.nextInt());

		newPsDetails.setSubsequentPeriodPriceType(RandomTestUtil.nextInt());

		newPsDetails.setNetSubsequentPeriodPrice(RandomTestUtil.nextInt());

		newPsDetails.setNetSubsequentPriceFormulaId(RandomTestUtil.nextInt());

		newPsDetails.setResetPriceType(RandomTestUtil.nextInt());

		newPsDetails.setNetResetPriceType(RandomTestUtil.nextInt());

		newPsDetails.setNetResetPriceFormulaId(RandomTestUtil.nextInt());

		newPsDetails.setNetBasePriceFormulaId(RandomTestUtil.nextInt());

		_psDetailses.add(_persistence.update(newPsDetails));

		PsDetails existingPsDetails = _persistence.findByPrimaryKey(newPsDetails.getPrimaryKey());

		Assert.assertEquals(existingPsDetails.getNepFormula(),
			newPsDetails.getNepFormula());
		AssertUtils.assertEquals(existingPsDetails.getPrice(),
			newPsDetails.getPrice());
		Assert.assertEquals(existingPsDetails.getItemMasterSid(),
			newPsDetails.getItemMasterSid());
		Assert.assertEquals(existingPsDetails.getResetType(),
			newPsDetails.getResetType());
		Assert.assertEquals(Time.getShortTimestamp(
				existingPsDetails.getPriceProtectionStartDate()),
			Time.getShortTimestamp(newPsDetails.getPriceProtectionStartDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingPsDetails.getResetDate()),
			Time.getShortTimestamp(newPsDetails.getResetDate()));
		AssertUtils.assertEquals(existingPsDetails.getBasePrice(),
			newPsDetails.getBasePrice());
		Assert.assertEquals(Time.getShortTimestamp(
				existingPsDetails.getItemPsAttachedDate()),
			Time.getShortTimestamp(newPsDetails.getItemPsAttachedDate()));
		Assert.assertEquals(existingPsDetails.getBrandMasterSid(),
			newPsDetails.getBrandMasterSid());
		Assert.assertEquals(existingPsDetails.getStatus(),
			newPsDetails.getStatus());
		Assert.assertEquals(Time.getShortTimestamp(
				existingPsDetails.getModifiedDate()),
			Time.getShortTimestamp(newPsDetails.getModifiedDate()));
		Assert.assertEquals(existingPsDetails.getItemPsAttachedStatus(),
			newPsDetails.getItemPsAttachedStatus());
		Assert.assertEquals(Time.getShortTimestamp(
				existingPsDetails.getRevisionDate()),
			Time.getShortTimestamp(newPsDetails.getRevisionDate()));
		AssertUtils.assertEquals(existingPsDetails.getPriceTolerance(),
			newPsDetails.getPriceTolerance());
		Assert.assertEquals(Time.getShortTimestamp(
				existingPsDetails.getCreatedDate()),
			Time.getShortTimestamp(newPsDetails.getCreatedDate()));
		Assert.assertEquals(existingPsDetails.getCreatedBy(),
			newPsDetails.getCreatedBy());
		Assert.assertEquals(existingPsDetails.getSource(),
			newPsDetails.getSource());
		Assert.assertEquals(existingPsDetails.getPsDetailsSid(),
			newPsDetails.getPsDetailsSid());
		Assert.assertEquals(existingPsDetails.getPsModelSid(),
			newPsDetails.getPsModelSid());
		AssertUtils.assertEquals(existingPsDetails.getSuggestedPrice(),
			newPsDetails.getSuggestedPrice());
		Assert.assertEquals(existingPsDetails.getNetPriceTypeFormula(),
			newPsDetails.getNetPriceTypeFormula());
		Assert.assertEquals(existingPsDetails.getPriceProtectionPriceType(),
			newPsDetails.getPriceProtectionPriceType());
		Assert.assertEquals(existingPsDetails.getModifiedBy(),
			newPsDetails.getModifiedBy());
		Assert.assertEquals(existingPsDetails.getInboundStatus(),
			newPsDetails.getInboundStatus());
		AssertUtils.assertEquals(existingPsDetails.getContractPrice(),
			newPsDetails.getContractPrice());
		Assert.assertEquals(existingPsDetails.getIfpModelSid(),
			newPsDetails.getIfpModelSid());
		Assert.assertEquals(existingPsDetails.getPriceToleranceType(),
			newPsDetails.getPriceToleranceType());
		AssertUtils.assertEquals(existingPsDetails.getMaxIncrementalChange(),
			newPsDetails.getMaxIncrementalChange());
		Assert.assertEquals(existingPsDetails.getItemPricingQualifierSid(),
			newPsDetails.getItemPricingQualifierSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingPsDetails.getContractPriceEndDate()),
			Time.getShortTimestamp(newPsDetails.getContractPriceEndDate()));
		AssertUtils.assertEquals(existingPsDetails.getNep(),
			newPsDetails.getNep());
		Assert.assertEquals(Time.getShortTimestamp(
				existingPsDetails.getContractPriceStartDate()),
			Time.getShortTimestamp(newPsDetails.getContractPriceStartDate()));
		Assert.assertEquals(existingPsDetails.getPriceToleranceFrequency(),
			newPsDetails.getPriceToleranceFrequency());
		Assert.assertEquals(Time.getShortTimestamp(
				existingPsDetails.getPriceProtectionEndDate()),
			Time.getShortTimestamp(newPsDetails.getPriceProtectionEndDate()));
		Assert.assertEquals(existingPsDetails.getPriceProtectionStatus(),
			newPsDetails.getPriceProtectionStatus());
		Assert.assertEquals(existingPsDetails.getRecordLockStatus(),
			newPsDetails.getRecordLockStatus());
		Assert.assertEquals(existingPsDetails.getResetEligible(),
			newPsDetails.getResetEligible());
		Assert.assertEquals(existingPsDetails.getBatchId(),
			newPsDetails.getBatchId());
		Assert.assertEquals(existingPsDetails.getPriceToleranceInterval(),
			newPsDetails.getPriceToleranceInterval());
		Assert.assertEquals(existingPsDetails.getNetPriceType(),
			newPsDetails.getNetPriceType());
		AssertUtils.assertEquals(existingPsDetails.getPriceRevision(),
			newPsDetails.getPriceRevision());
		Assert.assertEquals(existingPsDetails.getResetFrequency(),
			newPsDetails.getResetFrequency());
		Assert.assertEquals(existingPsDetails.getResetInterval(),
			newPsDetails.getResetInterval());
		Assert.assertEquals(existingPsDetails.getBasePriceType(),
			newPsDetails.getBasePriceType());
		AssertUtils.assertEquals(existingPsDetails.getBasePriceEntry(),
			newPsDetails.getBasePriceEntry());
		Assert.assertEquals(Time.getShortTimestamp(
				existingPsDetails.getBasePriceDate()),
			Time.getShortTimestamp(newPsDetails.getBasePriceDate()));
		Assert.assertEquals(existingPsDetails.getNetBasePrice(),
			newPsDetails.getNetBasePrice());
		Assert.assertEquals(existingPsDetails.getBasePriceDdlb(),
			newPsDetails.getBasePriceDdlb());
		Assert.assertEquals(existingPsDetails.getSubsequentPeriodPriceType(),
			newPsDetails.getSubsequentPeriodPriceType());
		Assert.assertEquals(existingPsDetails.getNetSubsequentPeriodPrice(),
			newPsDetails.getNetSubsequentPeriodPrice());
		Assert.assertEquals(existingPsDetails.getNetSubsequentPriceFormulaId(),
			newPsDetails.getNetSubsequentPriceFormulaId());
		Assert.assertEquals(existingPsDetails.getResetPriceType(),
			newPsDetails.getResetPriceType());
		Assert.assertEquals(existingPsDetails.getNetResetPriceType(),
			newPsDetails.getNetResetPriceType());
		Assert.assertEquals(existingPsDetails.getNetResetPriceFormulaId(),
			newPsDetails.getNetResetPriceFormulaId());
		Assert.assertEquals(existingPsDetails.getNetBasePriceFormulaId(),
			newPsDetails.getNetBasePriceFormulaId());
	}

	@Test
	public void testCountByPriceScheduleMasterDetails()
		throws Exception {
		_persistence.countByPriceScheduleMasterDetails(RandomTestUtil.nextInt());

		_persistence.countByPriceScheduleMasterDetails(0);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		PsDetails newPsDetails = addPsDetails();

		PsDetails existingPsDetails = _persistence.findByPrimaryKey(newPsDetails.getPrimaryKey());

		Assert.assertEquals(existingPsDetails, newPsDetails);
	}

	@Test(expected = NoSuchPsDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<PsDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("PS_DETAILS", "nepFormula",
			true, "price", true, "itemMasterSid", true, "resetType", true,
			"priceProtectionStartDate", true, "resetDate", true, "basePrice",
			true, "itemPsAttachedDate", true, "brandMasterSid", true, "status",
			true, "modifiedDate", true, "itemPsAttachedStatus", true,
			"revisionDate", true, "priceTolerance", true, "createdDate", true,
			"createdBy", true, "source", true, "psDetailsSid", true,
			"psModelSid", true, "suggestedPrice", true, "netPriceTypeFormula",
			true, "priceProtectionPriceType", true, "modifiedBy", true,
			"inboundStatus", true, "contractPrice", true, "ifpModelSid", true,
			"priceToleranceType", true, "maxIncrementalChange", true,
			"itemPricingQualifierSid", true, "contractPriceEndDate", true,
			"nep", true, "contractPriceStartDate", true,
			"priceToleranceFrequency", true, "priceProtectionEndDate", true,
			"priceProtectionStatus", true, "recordLockStatus", true,
			"resetEligible", true, "batchId", true, "priceToleranceInterval",
			true, "netPriceType", true, "priceRevision", true,
			"resetFrequency", true, "resetInterval", true, "basePriceType",
			true, "basePriceEntry", true, "basePriceDate", true,
			"netBasePrice", true, "basePriceDdlb", true,
			"subsequentPeriodPriceType", true, "netSubsequentPeriodPrice",
			true, "netSubsequentPriceFormulaId", true, "resetPriceType", true,
			"netResetPriceType", true, "netResetPriceFormulaId", true,
			"netBasePriceFormulaId", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		PsDetails newPsDetails = addPsDetails();

		PsDetails existingPsDetails = _persistence.fetchByPrimaryKey(newPsDetails.getPrimaryKey());

		Assert.assertEquals(existingPsDetails, newPsDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		PsDetails missingPsDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingPsDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		PsDetails newPsDetails1 = addPsDetails();
		PsDetails newPsDetails2 = addPsDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newPsDetails1.getPrimaryKey());
		primaryKeys.add(newPsDetails2.getPrimaryKey());

		Map<Serializable, PsDetails> psDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, psDetailses.size());
		Assert.assertEquals(newPsDetails1,
			psDetailses.get(newPsDetails1.getPrimaryKey()));
		Assert.assertEquals(newPsDetails2,
			psDetailses.get(newPsDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, PsDetails> psDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(psDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		PsDetails newPsDetails = addPsDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newPsDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, PsDetails> psDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, psDetailses.size());
		Assert.assertEquals(newPsDetails,
			psDetailses.get(newPsDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, PsDetails> psDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(psDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		PsDetails newPsDetails = addPsDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newPsDetails.getPrimaryKey());

		Map<Serializable, PsDetails> psDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, psDetailses.size());
		Assert.assertEquals(newPsDetails,
			psDetailses.get(newPsDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = PsDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<PsDetails>() {
				@Override
				public void performAction(PsDetails psDetails) {
					Assert.assertNotNull(psDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		PsDetails newPsDetails = addPsDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PsDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("psDetailsSid",
				newPsDetails.getPsDetailsSid()));

		List<PsDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		PsDetails existingPsDetails = result.get(0);

		Assert.assertEquals(existingPsDetails, newPsDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PsDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("psDetailsSid",
				RandomTestUtil.nextInt()));

		List<PsDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		PsDetails newPsDetails = addPsDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PsDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"psDetailsSid"));

		Object newPsDetailsSid = newPsDetails.getPsDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("psDetailsSid",
				new Object[] { newPsDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingPsDetailsSid = result.get(0);

		Assert.assertEquals(existingPsDetailsSid, newPsDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PsDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"psDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("psDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected PsDetails addPsDetails() throws Exception {
		int pk = RandomTestUtil.nextInt();

		PsDetails psDetails = _persistence.create(pk);

		psDetails.setNepFormula(RandomTestUtil.nextInt());

		psDetails.setPrice(RandomTestUtil.nextDouble());

		psDetails.setItemMasterSid(RandomTestUtil.nextInt());

		psDetails.setResetType(RandomTestUtil.nextInt());

		psDetails.setPriceProtectionStartDate(RandomTestUtil.nextDate());

		psDetails.setResetDate(RandomTestUtil.nextDate());

		psDetails.setBasePrice(RandomTestUtil.nextDouble());

		psDetails.setItemPsAttachedDate(RandomTestUtil.nextDate());

		psDetails.setBrandMasterSid(RandomTestUtil.randomString());

		psDetails.setStatus(RandomTestUtil.nextInt());

		psDetails.setModifiedDate(RandomTestUtil.nextDate());

		psDetails.setItemPsAttachedStatus(RandomTestUtil.nextInt());

		psDetails.setRevisionDate(RandomTestUtil.nextDate());

		psDetails.setPriceTolerance(RandomTestUtil.nextDouble());

		psDetails.setCreatedDate(RandomTestUtil.nextDate());

		psDetails.setCreatedBy(RandomTestUtil.nextInt());

		psDetails.setSource(RandomTestUtil.randomString());

		psDetails.setPsModelSid(RandomTestUtil.nextInt());

		psDetails.setSuggestedPrice(RandomTestUtil.nextDouble());

		psDetails.setNetPriceTypeFormula(RandomTestUtil.randomString());

		psDetails.setPriceProtectionPriceType(RandomTestUtil.nextInt());

		psDetails.setModifiedBy(RandomTestUtil.nextInt());

		psDetails.setInboundStatus(RandomTestUtil.randomString());

		psDetails.setContractPrice(RandomTestUtil.nextDouble());

		psDetails.setIfpModelSid(RandomTestUtil.nextInt());

		psDetails.setPriceToleranceType(RandomTestUtil.nextInt());

		psDetails.setMaxIncrementalChange(RandomTestUtil.nextDouble());

		psDetails.setItemPricingQualifierSid(RandomTestUtil.nextInt());

		psDetails.setContractPriceEndDate(RandomTestUtil.nextDate());

		psDetails.setNep(RandomTestUtil.nextDouble());

		psDetails.setContractPriceStartDate(RandomTestUtil.nextDate());

		psDetails.setPriceToleranceFrequency(RandomTestUtil.nextInt());

		psDetails.setPriceProtectionEndDate(RandomTestUtil.nextDate());

		psDetails.setPriceProtectionStatus(RandomTestUtil.nextInt());

		psDetails.setRecordLockStatus(RandomTestUtil.randomBoolean());

		psDetails.setResetEligible(RandomTestUtil.nextInt());

		psDetails.setBatchId(RandomTestUtil.randomString());

		psDetails.setPriceToleranceInterval(RandomTestUtil.nextInt());

		psDetails.setNetPriceType(RandomTestUtil.nextInt());

		psDetails.setPriceRevision(RandomTestUtil.nextDouble());

		psDetails.setResetFrequency(RandomTestUtil.nextInt());

		psDetails.setResetInterval(RandomTestUtil.nextInt());

		psDetails.setBasePriceType(RandomTestUtil.nextInt());

		psDetails.setBasePriceEntry(RandomTestUtil.nextDouble());

		psDetails.setBasePriceDate(RandomTestUtil.nextDate());

		psDetails.setNetBasePrice(RandomTestUtil.nextInt());

		psDetails.setBasePriceDdlb(RandomTestUtil.nextInt());

		psDetails.setSubsequentPeriodPriceType(RandomTestUtil.nextInt());

		psDetails.setNetSubsequentPeriodPrice(RandomTestUtil.nextInt());

		psDetails.setNetSubsequentPriceFormulaId(RandomTestUtil.nextInt());

		psDetails.setResetPriceType(RandomTestUtil.nextInt());

		psDetails.setNetResetPriceType(RandomTestUtil.nextInt());

		psDetails.setNetResetPriceFormulaId(RandomTestUtil.nextInt());

		psDetails.setNetBasePriceFormulaId(RandomTestUtil.nextInt());

		_psDetailses.add(_persistence.update(psDetails));

		return psDetails;
	}

	private List<PsDetails> _psDetailses = new ArrayList<PsDetails>();
	private PsDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}