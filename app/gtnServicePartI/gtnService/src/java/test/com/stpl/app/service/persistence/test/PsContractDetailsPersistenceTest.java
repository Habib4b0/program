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

import com.stpl.app.exception.NoSuchPsContractDetailsException;
import com.stpl.app.model.PsContractDetails;
import com.stpl.app.service.PsContractDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.PsContractDetailsPersistence;
import com.stpl.app.service.persistence.PsContractDetailsUtil;

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
public class PsContractDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = PsContractDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<PsContractDetails> iterator = _psContractDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		PsContractDetails psContractDetails = _persistence.create(pk);

		Assert.assertNotNull(psContractDetails);

		Assert.assertEquals(psContractDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		PsContractDetails newPsContractDetails = addPsContractDetails();

		_persistence.remove(newPsContractDetails);

		PsContractDetails existingPsContractDetails = _persistence.fetchByPrimaryKey(newPsContractDetails.getPrimaryKey());

		Assert.assertNull(existingPsContractDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addPsContractDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		PsContractDetails newPsContractDetails = _persistence.create(pk);

		newPsContractDetails.setPrice(RandomTestUtil.nextDouble());

		newPsContractDetails.setItemMasterSid(RandomTestUtil.nextInt());

		newPsContractDetails.setPriceProtectionStartDate(RandomTestUtil.nextDate());

		newPsContractDetails.setBasePrice(RandomTestUtil.nextDouble());

		newPsContractDetails.setModifiedDate(RandomTestUtil.nextDate());

		newPsContractDetails.setRevisionDate(RandomTestUtil.nextDate());

		newPsContractDetails.setPriceTolerance(RandomTestUtil.nextDouble());

		newPsContractDetails.setCreatedDate(RandomTestUtil.nextDate());

		newPsContractDetails.setSource(RandomTestUtil.randomString());

		newPsContractDetails.setCreatedBy(RandomTestUtil.nextInt());

		newPsContractDetails.setSuggestedPrice(RandomTestUtil.nextDouble());

		newPsContractDetails.setPsContractAttachedDate(RandomTestUtil.nextDate());

		newPsContractDetails.setModifiedBy(RandomTestUtil.nextInt());

		newPsContractDetails.setInboundStatus(RandomTestUtil.randomString());

		newPsContractDetails.setContractPrice(RandomTestUtil.nextDouble());

		newPsContractDetails.setPriceToleranceType(RandomTestUtil.nextInt());

		newPsContractDetails.setItemPricingQualifierSid(RandomTestUtil.nextInt());

		newPsContractDetails.setContractPriceEndDate(RandomTestUtil.nextDate());

		newPsContractDetails.setPriceToleranceFrequency(RandomTestUtil.nextInt());

		newPsContractDetails.setContractPriceStartDate(RandomTestUtil.nextDate());

		newPsContractDetails.setPsContractSid(RandomTestUtil.nextInt());

		newPsContractDetails.setPriceProtectionEndDate(RandomTestUtil.nextDate());

		newPsContractDetails.setPsContractAttachedStatus(RandomTestUtil.nextInt());

		newPsContractDetails.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newPsContractDetails.setBatchId(RandomTestUtil.randomString());

		newPsContractDetails.setPriceToleranceInterval(RandomTestUtil.nextInt());

		newPsContractDetails.setPriceRevision(RandomTestUtil.nextDouble());

		newPsContractDetails.setBrandMasterSid(RandomTestUtil.randomString());

		newPsContractDetails.setNep(RandomTestUtil.nextDouble());

		newPsContractDetails.setPriceProtectionStatus(RandomTestUtil.nextInt());

		newPsContractDetails.setPriceProtectionPriceType(RandomTestUtil.nextInt());

		newPsContractDetails.setNepFormula(RandomTestUtil.nextInt());

		newPsContractDetails.setMaxIncrementalChange(RandomTestUtil.nextDouble());

		newPsContractDetails.setResetEligible(RandomTestUtil.nextInt());

		newPsContractDetails.setResetType(RandomTestUtil.nextInt());

		newPsContractDetails.setResetDate(RandomTestUtil.nextDate());

		newPsContractDetails.setResetInterval(RandomTestUtil.nextInt());

		newPsContractDetails.setResetFrequency(RandomTestUtil.nextInt());

		newPsContractDetails.setNetPriceType(RandomTestUtil.nextInt());

		newPsContractDetails.setNetPriceTypeFormula(RandomTestUtil.randomString());

		newPsContractDetails.setBasePriceType(RandomTestUtil.nextInt());

		newPsContractDetails.setBasePriceEntry(RandomTestUtil.nextDouble());

		newPsContractDetails.setBasePriceDate(RandomTestUtil.nextDate());

		newPsContractDetails.setBasePriceDdlb(RandomTestUtil.nextInt());

		newPsContractDetails.setNetBasePrice(RandomTestUtil.nextInt());

		newPsContractDetails.setNetBasePriceFormulaId(RandomTestUtil.nextInt());

		newPsContractDetails.setSubsequentPeriodPriceType(RandomTestUtil.nextInt());

		newPsContractDetails.setNetSubsequentPeriodPrice(RandomTestUtil.nextInt());

		newPsContractDetails.setNetSubsequentPriceFormulaId(RandomTestUtil.nextInt());

		newPsContractDetails.setResetPriceType(RandomTestUtil.nextInt());

		newPsContractDetails.setNetResetPriceType(RandomTestUtil.nextInt());

		newPsContractDetails.setNetResetPriceFormulaId(RandomTestUtil.nextInt());

		_psContractDetailses.add(_persistence.update(newPsContractDetails));

		PsContractDetails existingPsContractDetails = _persistence.findByPrimaryKey(newPsContractDetails.getPrimaryKey());

		AssertUtils.assertEquals(existingPsContractDetails.getPrice(),
			newPsContractDetails.getPrice());
		Assert.assertEquals(existingPsContractDetails.getItemMasterSid(),
			newPsContractDetails.getItemMasterSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingPsContractDetails.getPriceProtectionStartDate()),
			Time.getShortTimestamp(
				newPsContractDetails.getPriceProtectionStartDate()));
		AssertUtils.assertEquals(existingPsContractDetails.getBasePrice(),
			newPsContractDetails.getBasePrice());
		Assert.assertEquals(Time.getShortTimestamp(
				existingPsContractDetails.getModifiedDate()),
			Time.getShortTimestamp(newPsContractDetails.getModifiedDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingPsContractDetails.getRevisionDate()),
			Time.getShortTimestamp(newPsContractDetails.getRevisionDate()));
		AssertUtils.assertEquals(existingPsContractDetails.getPriceTolerance(),
			newPsContractDetails.getPriceTolerance());
		Assert.assertEquals(Time.getShortTimestamp(
				existingPsContractDetails.getCreatedDate()),
			Time.getShortTimestamp(newPsContractDetails.getCreatedDate()));
		Assert.assertEquals(existingPsContractDetails.getSource(),
			newPsContractDetails.getSource());
		Assert.assertEquals(existingPsContractDetails.getCreatedBy(),
			newPsContractDetails.getCreatedBy());
		AssertUtils.assertEquals(existingPsContractDetails.getSuggestedPrice(),
			newPsContractDetails.getSuggestedPrice());
		Assert.assertEquals(Time.getShortTimestamp(
				existingPsContractDetails.getPsContractAttachedDate()),
			Time.getShortTimestamp(
				newPsContractDetails.getPsContractAttachedDate()));
		Assert.assertEquals(existingPsContractDetails.getPsContractDetailsSid(),
			newPsContractDetails.getPsContractDetailsSid());
		Assert.assertEquals(existingPsContractDetails.getModifiedBy(),
			newPsContractDetails.getModifiedBy());
		Assert.assertEquals(existingPsContractDetails.getInboundStatus(),
			newPsContractDetails.getInboundStatus());
		AssertUtils.assertEquals(existingPsContractDetails.getContractPrice(),
			newPsContractDetails.getContractPrice());
		Assert.assertEquals(existingPsContractDetails.getPriceToleranceType(),
			newPsContractDetails.getPriceToleranceType());
		Assert.assertEquals(existingPsContractDetails.getItemPricingQualifierSid(),
			newPsContractDetails.getItemPricingQualifierSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingPsContractDetails.getContractPriceEndDate()),
			Time.getShortTimestamp(
				newPsContractDetails.getContractPriceEndDate()));
		Assert.assertEquals(existingPsContractDetails.getPriceToleranceFrequency(),
			newPsContractDetails.getPriceToleranceFrequency());
		Assert.assertEquals(Time.getShortTimestamp(
				existingPsContractDetails.getContractPriceStartDate()),
			Time.getShortTimestamp(
				newPsContractDetails.getContractPriceStartDate()));
		Assert.assertEquals(existingPsContractDetails.getPsContractSid(),
			newPsContractDetails.getPsContractSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingPsContractDetails.getPriceProtectionEndDate()),
			Time.getShortTimestamp(
				newPsContractDetails.getPriceProtectionEndDate()));
		Assert.assertEquals(existingPsContractDetails.getPsContractAttachedStatus(),
			newPsContractDetails.getPsContractAttachedStatus());
		Assert.assertEquals(existingPsContractDetails.getRecordLockStatus(),
			newPsContractDetails.getRecordLockStatus());
		Assert.assertEquals(existingPsContractDetails.getBatchId(),
			newPsContractDetails.getBatchId());
		Assert.assertEquals(existingPsContractDetails.getPriceToleranceInterval(),
			newPsContractDetails.getPriceToleranceInterval());
		AssertUtils.assertEquals(existingPsContractDetails.getPriceRevision(),
			newPsContractDetails.getPriceRevision());
		Assert.assertEquals(existingPsContractDetails.getBrandMasterSid(),
			newPsContractDetails.getBrandMasterSid());
		AssertUtils.assertEquals(existingPsContractDetails.getNep(),
			newPsContractDetails.getNep());
		Assert.assertEquals(existingPsContractDetails.getPriceProtectionStatus(),
			newPsContractDetails.getPriceProtectionStatus());
		Assert.assertEquals(existingPsContractDetails.getPriceProtectionPriceType(),
			newPsContractDetails.getPriceProtectionPriceType());
		Assert.assertEquals(existingPsContractDetails.getNepFormula(),
			newPsContractDetails.getNepFormula());
		AssertUtils.assertEquals(existingPsContractDetails.getMaxIncrementalChange(),
			newPsContractDetails.getMaxIncrementalChange());
		Assert.assertEquals(existingPsContractDetails.getResetEligible(),
			newPsContractDetails.getResetEligible());
		Assert.assertEquals(existingPsContractDetails.getResetType(),
			newPsContractDetails.getResetType());
		Assert.assertEquals(Time.getShortTimestamp(
				existingPsContractDetails.getResetDate()),
			Time.getShortTimestamp(newPsContractDetails.getResetDate()));
		Assert.assertEquals(existingPsContractDetails.getResetInterval(),
			newPsContractDetails.getResetInterval());
		Assert.assertEquals(existingPsContractDetails.getResetFrequency(),
			newPsContractDetails.getResetFrequency());
		Assert.assertEquals(existingPsContractDetails.getNetPriceType(),
			newPsContractDetails.getNetPriceType());
		Assert.assertEquals(existingPsContractDetails.getNetPriceTypeFormula(),
			newPsContractDetails.getNetPriceTypeFormula());
		Assert.assertEquals(existingPsContractDetails.getBasePriceType(),
			newPsContractDetails.getBasePriceType());
		AssertUtils.assertEquals(existingPsContractDetails.getBasePriceEntry(),
			newPsContractDetails.getBasePriceEntry());
		Assert.assertEquals(Time.getShortTimestamp(
				existingPsContractDetails.getBasePriceDate()),
			Time.getShortTimestamp(newPsContractDetails.getBasePriceDate()));
		Assert.assertEquals(existingPsContractDetails.getBasePriceDdlb(),
			newPsContractDetails.getBasePriceDdlb());
		Assert.assertEquals(existingPsContractDetails.getNetBasePrice(),
			newPsContractDetails.getNetBasePrice());
		Assert.assertEquals(existingPsContractDetails.getNetBasePriceFormulaId(),
			newPsContractDetails.getNetBasePriceFormulaId());
		Assert.assertEquals(existingPsContractDetails.getSubsequentPeriodPriceType(),
			newPsContractDetails.getSubsequentPeriodPriceType());
		Assert.assertEquals(existingPsContractDetails.getNetSubsequentPeriodPrice(),
			newPsContractDetails.getNetSubsequentPeriodPrice());
		Assert.assertEquals(existingPsContractDetails.getNetSubsequentPriceFormulaId(),
			newPsContractDetails.getNetSubsequentPriceFormulaId());
		Assert.assertEquals(existingPsContractDetails.getResetPriceType(),
			newPsContractDetails.getResetPriceType());
		Assert.assertEquals(existingPsContractDetails.getNetResetPriceType(),
			newPsContractDetails.getNetResetPriceType());
		Assert.assertEquals(existingPsContractDetails.getNetResetPriceFormulaId(),
			newPsContractDetails.getNetResetPriceFormulaId());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		PsContractDetails newPsContractDetails = addPsContractDetails();

		PsContractDetails existingPsContractDetails = _persistence.findByPrimaryKey(newPsContractDetails.getPrimaryKey());

		Assert.assertEquals(existingPsContractDetails, newPsContractDetails);
	}

	@Test(expected = NoSuchPsContractDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<PsContractDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("PS_CONTRACT_DETAILS",
			"price", true, "itemMasterSid", true, "priceProtectionStartDate",
			true, "basePrice", true, "modifiedDate", true, "revisionDate",
			true, "priceTolerance", true, "createdDate", true, "source", true,
			"createdBy", true, "suggestedPrice", true,
			"psContractAttachedDate", true, "psContractDetailsSid", true,
			"modifiedBy", true, "inboundStatus", true, "contractPrice", true,
			"priceToleranceType", true, "itemPricingQualifierSid", true,
			"contractPriceEndDate", true, "priceToleranceFrequency", true,
			"contractPriceStartDate", true, "psContractSid", true,
			"priceProtectionEndDate", true, "psContractAttachedStatus", true,
			"recordLockStatus", true, "batchId", true,
			"priceToleranceInterval", true, "priceRevision", true,
			"brandMasterSid", true, "nep", true, "priceProtectionStatus", true,
			"priceProtectionPriceType", true, "nepFormula", true,
			"maxIncrementalChange", true, "resetEligible", true, "resetType",
			true, "resetDate", true, "resetInterval", true, "resetFrequency",
			true, "netPriceType", true, "netPriceTypeFormula", true,
			"basePriceType", true, "basePriceEntry", true, "basePriceDate",
			true, "basePriceDdlb", true, "netBasePrice", true,
			"netBasePriceFormulaId", true, "subsequentPeriodPriceType", true,
			"netSubsequentPeriodPrice", true, "netSubsequentPriceFormulaId",
			true, "resetPriceType", true, "netResetPriceType", true,
			"netResetPriceFormulaId", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		PsContractDetails newPsContractDetails = addPsContractDetails();

		PsContractDetails existingPsContractDetails = _persistence.fetchByPrimaryKey(newPsContractDetails.getPrimaryKey());

		Assert.assertEquals(existingPsContractDetails, newPsContractDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		PsContractDetails missingPsContractDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingPsContractDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		PsContractDetails newPsContractDetails1 = addPsContractDetails();
		PsContractDetails newPsContractDetails2 = addPsContractDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newPsContractDetails1.getPrimaryKey());
		primaryKeys.add(newPsContractDetails2.getPrimaryKey());

		Map<Serializable, PsContractDetails> psContractDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, psContractDetailses.size());
		Assert.assertEquals(newPsContractDetails1,
			psContractDetailses.get(newPsContractDetails1.getPrimaryKey()));
		Assert.assertEquals(newPsContractDetails2,
			psContractDetailses.get(newPsContractDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, PsContractDetails> psContractDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(psContractDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		PsContractDetails newPsContractDetails = addPsContractDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newPsContractDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, PsContractDetails> psContractDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, psContractDetailses.size());
		Assert.assertEquals(newPsContractDetails,
			psContractDetailses.get(newPsContractDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, PsContractDetails> psContractDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(psContractDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		PsContractDetails newPsContractDetails = addPsContractDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newPsContractDetails.getPrimaryKey());

		Map<Serializable, PsContractDetails> psContractDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, psContractDetailses.size());
		Assert.assertEquals(newPsContractDetails,
			psContractDetailses.get(newPsContractDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = PsContractDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<PsContractDetails>() {
				@Override
				public void performAction(PsContractDetails psContractDetails) {
					Assert.assertNotNull(psContractDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		PsContractDetails newPsContractDetails = addPsContractDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PsContractDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("psContractDetailsSid",
				newPsContractDetails.getPsContractDetailsSid()));

		List<PsContractDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		PsContractDetails existingPsContractDetails = result.get(0);

		Assert.assertEquals(existingPsContractDetails, newPsContractDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PsContractDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("psContractDetailsSid",
				RandomTestUtil.nextInt()));

		List<PsContractDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		PsContractDetails newPsContractDetails = addPsContractDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PsContractDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"psContractDetailsSid"));

		Object newPsContractDetailsSid = newPsContractDetails.getPsContractDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("psContractDetailsSid",
				new Object[] { newPsContractDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingPsContractDetailsSid = result.get(0);

		Assert.assertEquals(existingPsContractDetailsSid,
			newPsContractDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PsContractDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"psContractDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("psContractDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected PsContractDetails addPsContractDetails()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		PsContractDetails psContractDetails = _persistence.create(pk);

		psContractDetails.setPrice(RandomTestUtil.nextDouble());

		psContractDetails.setItemMasterSid(RandomTestUtil.nextInt());

		psContractDetails.setPriceProtectionStartDate(RandomTestUtil.nextDate());

		psContractDetails.setBasePrice(RandomTestUtil.nextDouble());

		psContractDetails.setModifiedDate(RandomTestUtil.nextDate());

		psContractDetails.setRevisionDate(RandomTestUtil.nextDate());

		psContractDetails.setPriceTolerance(RandomTestUtil.nextDouble());

		psContractDetails.setCreatedDate(RandomTestUtil.nextDate());

		psContractDetails.setSource(RandomTestUtil.randomString());

		psContractDetails.setCreatedBy(RandomTestUtil.nextInt());

		psContractDetails.setSuggestedPrice(RandomTestUtil.nextDouble());

		psContractDetails.setPsContractAttachedDate(RandomTestUtil.nextDate());

		psContractDetails.setModifiedBy(RandomTestUtil.nextInt());

		psContractDetails.setInboundStatus(RandomTestUtil.randomString());

		psContractDetails.setContractPrice(RandomTestUtil.nextDouble());

		psContractDetails.setPriceToleranceType(RandomTestUtil.nextInt());

		psContractDetails.setItemPricingQualifierSid(RandomTestUtil.nextInt());

		psContractDetails.setContractPriceEndDate(RandomTestUtil.nextDate());

		psContractDetails.setPriceToleranceFrequency(RandomTestUtil.nextInt());

		psContractDetails.setContractPriceStartDate(RandomTestUtil.nextDate());

		psContractDetails.setPsContractSid(RandomTestUtil.nextInt());

		psContractDetails.setPriceProtectionEndDate(RandomTestUtil.nextDate());

		psContractDetails.setPsContractAttachedStatus(RandomTestUtil.nextInt());

		psContractDetails.setRecordLockStatus(RandomTestUtil.randomBoolean());

		psContractDetails.setBatchId(RandomTestUtil.randomString());

		psContractDetails.setPriceToleranceInterval(RandomTestUtil.nextInt());

		psContractDetails.setPriceRevision(RandomTestUtil.nextDouble());

		psContractDetails.setBrandMasterSid(RandomTestUtil.randomString());

		psContractDetails.setNep(RandomTestUtil.nextDouble());

		psContractDetails.setPriceProtectionStatus(RandomTestUtil.nextInt());

		psContractDetails.setPriceProtectionPriceType(RandomTestUtil.nextInt());

		psContractDetails.setNepFormula(RandomTestUtil.nextInt());

		psContractDetails.setMaxIncrementalChange(RandomTestUtil.nextDouble());

		psContractDetails.setResetEligible(RandomTestUtil.nextInt());

		psContractDetails.setResetType(RandomTestUtil.nextInt());

		psContractDetails.setResetDate(RandomTestUtil.nextDate());

		psContractDetails.setResetInterval(RandomTestUtil.nextInt());

		psContractDetails.setResetFrequency(RandomTestUtil.nextInt());

		psContractDetails.setNetPriceType(RandomTestUtil.nextInt());

		psContractDetails.setNetPriceTypeFormula(RandomTestUtil.randomString());

		psContractDetails.setBasePriceType(RandomTestUtil.nextInt());

		psContractDetails.setBasePriceEntry(RandomTestUtil.nextDouble());

		psContractDetails.setBasePriceDate(RandomTestUtil.nextDate());

		psContractDetails.setBasePriceDdlb(RandomTestUtil.nextInt());

		psContractDetails.setNetBasePrice(RandomTestUtil.nextInt());

		psContractDetails.setNetBasePriceFormulaId(RandomTestUtil.nextInt());

		psContractDetails.setSubsequentPeriodPriceType(RandomTestUtil.nextInt());

		psContractDetails.setNetSubsequentPeriodPrice(RandomTestUtil.nextInt());

		psContractDetails.setNetSubsequentPriceFormulaId(RandomTestUtil.nextInt());

		psContractDetails.setResetPriceType(RandomTestUtil.nextInt());

		psContractDetails.setNetResetPriceType(RandomTestUtil.nextInt());

		psContractDetails.setNetResetPriceFormulaId(RandomTestUtil.nextInt());

		_psContractDetailses.add(_persistence.update(psContractDetails));

		return psContractDetails;
	}

	private List<PsContractDetails> _psContractDetailses = new ArrayList<PsContractDetails>();
	private PsContractDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}