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

import com.stpl.app.exception.NoSuchImtdPsDetailsException;
import com.stpl.app.model.ImtdPsDetails;
import com.stpl.app.service.ImtdPsDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.ImtdPsDetailsPersistence;
import com.stpl.app.service.persistence.ImtdPsDetailsUtil;

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
public class ImtdPsDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ImtdPsDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ImtdPsDetails> iterator = _imtdPsDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ImtdPsDetails imtdPsDetails = _persistence.create(pk);

		Assert.assertNotNull(imtdPsDetails);

		Assert.assertEquals(imtdPsDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ImtdPsDetails newImtdPsDetails = addImtdPsDetails();

		_persistence.remove(newImtdPsDetails);

		ImtdPsDetails existingImtdPsDetails = _persistence.fetchByPrimaryKey(newImtdPsDetails.getPrimaryKey());

		Assert.assertNull(existingImtdPsDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addImtdPsDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ImtdPsDetails newImtdPsDetails = _persistence.create(pk);

		newImtdPsDetails.setPsDetailsModifiedDate(RandomTestUtil.nextDate());

		newImtdPsDetails.setPsDetailsSuggestedPrice(RandomTestUtil.nextDouble());

		newImtdPsDetails.setPsDetailsContractPrice(RandomTestUtil.nextDouble());

		newImtdPsDetails.setResetDate(RandomTestUtil.nextDate());

		newImtdPsDetails.setPsDetailsAttachedStatus(RandomTestUtil.nextInt());

		newImtdPsDetails.setModifiedDate(RandomTestUtil.nextDate());

		newImtdPsDetails.setPsDetailsCreatedBy(RandomTestUtil.nextInt());

		newImtdPsDetails.setContractMasterSid(RandomTestUtil.nextInt());

		newImtdPsDetails.setPsDtlsContPriceEnddate(RandomTestUtil.nextDate());

		newImtdPsDetails.setPsDetailsPricPrtcnStdate(RandomTestUtil.nextDate());

		newImtdPsDetails.setImtdCreatedDate(RandomTestUtil.nextDate());

		newImtdPsDetails.setNetPriceTypeFormula(RandomTestUtil.randomString());

		newImtdPsDetails.setModifiedBy(RandomTestUtil.nextInt());

		newImtdPsDetails.setMaxIncrementalChange(RandomTestUtil.nextDouble());

		newImtdPsDetails.setPsDetailsPricePlanId(RandomTestUtil.randomString());

		newImtdPsDetails.setCheckRecord(RandomTestUtil.randomBoolean());

		newImtdPsDetails.setPsDtlsPriceToleranceFreq(RandomTestUtil.nextInt());

		newImtdPsDetails.setItemName(RandomTestUtil.randomString());

		newImtdPsDetails.setSessionId(RandomTestUtil.randomString());

		newImtdPsDetails.setResetFrequency(RandomTestUtil.nextInt());

		newImtdPsDetails.setPsDtlsPriceToleranceType(RandomTestUtil.nextInt());

		newImtdPsDetails.setPsDetailsPricetype(RandomTestUtil.nextInt());

		newImtdPsDetails.setPsDetailsPriceRevision(RandomTestUtil.nextDouble());

		newImtdPsDetails.setResetInterval(RandomTestUtil.nextInt());

		newImtdPsDetails.setIfpNo(RandomTestUtil.randomString());

		newImtdPsDetails.setPsDetailsAttachedDate(RandomTestUtil.nextDate());

		newImtdPsDetails.setNepFormula(RandomTestUtil.nextInt());

		newImtdPsDetails.setPsDetailsModifiedBy(RandomTestUtil.nextInt());

		newImtdPsDetails.setPsDtlsPriceToleranceIntrvl(RandomTestUtil.nextInt());

		newImtdPsDetails.setItemMasterSid(RandomTestUtil.nextInt());

		newImtdPsDetails.setResetType(RandomTestUtil.nextInt());

		newImtdPsDetails.setItemId(RandomTestUtil.randomString());

		newImtdPsDetails.setStatus(RandomTestUtil.nextInt());

		newImtdPsDetails.setBrandMasterSid(RandomTestUtil.nextInt());

		newImtdPsDetails.setPsDetailsPrice(RandomTestUtil.nextDouble());

		newImtdPsDetails.setPsDetailsCreatedDate(RandomTestUtil.nextDate());

		newImtdPsDetails.setUsersSid(RandomTestUtil.nextInt());

		newImtdPsDetails.setCreatedBy(RandomTestUtil.nextInt());

		newImtdPsDetails.setCreatedDate(RandomTestUtil.nextDate());

		newImtdPsDetails.setPsDetailsSid(RandomTestUtil.nextInt());

		newImtdPsDetails.setPsModelSid(RandomTestUtil.nextInt());

		newImtdPsDetails.setPriceProtectionPriceType(RandomTestUtil.nextInt());

		newImtdPsDetails.setPsDetailsBasePrice(RandomTestUtil.nextDouble());

		newImtdPsDetails.setItemNo(RandomTestUtil.randomString());

		newImtdPsDetails.setIfpModelSid(RandomTestUtil.nextInt());

		newImtdPsDetails.setPsDetailsRevisionDate(RandomTestUtil.nextDate());

		newImtdPsDetails.setNep(RandomTestUtil.nextDouble());

		newImtdPsDetails.setPsDetailsPriceTolerance(RandomTestUtil.nextDouble());

		newImtdPsDetails.setPriceProtectionStatus(RandomTestUtil.nextInt());

		newImtdPsDetails.setPsDtlsContPriceStartdate(RandomTestUtil.nextDate());

		newImtdPsDetails.setResetEligible(RandomTestUtil.nextInt());

		newImtdPsDetails.setNetPriceType(RandomTestUtil.nextInt());

		newImtdPsDetails.setOperation(RandomTestUtil.randomString());

		newImtdPsDetails.setCfpModelSid(RandomTestUtil.nextInt());

		newImtdPsDetails.setPsDetailsPricPrtcnEddate(RandomTestUtil.nextDate());

		newImtdPsDetails.setBasePriceType(RandomTestUtil.nextInt());

		newImtdPsDetails.setBasePriceEntry(RandomTestUtil.nextDouble());

		newImtdPsDetails.setBasePriceDate(RandomTestUtil.nextDate());

		newImtdPsDetails.setBasePriceDdlb(RandomTestUtil.nextInt());

		newImtdPsDetails.setNetBasePrice(RandomTestUtil.nextInt());

		newImtdPsDetails.setNetBasePriceFormulaId(RandomTestUtil.nextInt());

		newImtdPsDetails.setNetBasePriceFormulaNo(RandomTestUtil.randomString());

		newImtdPsDetails.setNetBasePriceFormulaName(RandomTestUtil.randomString());

		newImtdPsDetails.setSubsequentPeriodPriceType(RandomTestUtil.nextInt());

		newImtdPsDetails.setNetSubsequentPeriodPrice(RandomTestUtil.nextInt());

		newImtdPsDetails.setNetSubsequentPriceFormulaId(RandomTestUtil.nextInt());

		newImtdPsDetails.setNetSubsequentPriceFormulaNo(RandomTestUtil.randomString());

		newImtdPsDetails.setNetSubsequentPriceFormulaName(RandomTestUtil.randomString());

		newImtdPsDetails.setResetPriceType(RandomTestUtil.nextInt());

		newImtdPsDetails.setNetResetPriceType(RandomTestUtil.nextInt());

		newImtdPsDetails.setNetResetPriceFormulaId(RandomTestUtil.nextInt());

		newImtdPsDetails.setNetResetPriceFormulaNo(RandomTestUtil.randomString());

		newImtdPsDetails.setNetResetPriceFormulaName(RandomTestUtil.randomString());

		_imtdPsDetailses.add(_persistence.update(newImtdPsDetails));

		ImtdPsDetails existingImtdPsDetails = _persistence.findByPrimaryKey(newImtdPsDetails.getPrimaryKey());

		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdPsDetails.getPsDetailsModifiedDate()),
			Time.getShortTimestamp(newImtdPsDetails.getPsDetailsModifiedDate()));
		AssertUtils.assertEquals(existingImtdPsDetails.getPsDetailsSuggestedPrice(),
			newImtdPsDetails.getPsDetailsSuggestedPrice());
		AssertUtils.assertEquals(existingImtdPsDetails.getPsDetailsContractPrice(),
			newImtdPsDetails.getPsDetailsContractPrice());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdPsDetails.getResetDate()),
			Time.getShortTimestamp(newImtdPsDetails.getResetDate()));
		Assert.assertEquals(existingImtdPsDetails.getPsDetailsAttachedStatus(),
			newImtdPsDetails.getPsDetailsAttachedStatus());
		Assert.assertEquals(existingImtdPsDetails.getImtdPsDetailsSid(),
			newImtdPsDetails.getImtdPsDetailsSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdPsDetails.getModifiedDate()),
			Time.getShortTimestamp(newImtdPsDetails.getModifiedDate()));
		Assert.assertEquals(existingImtdPsDetails.getPsDetailsCreatedBy(),
			newImtdPsDetails.getPsDetailsCreatedBy());
		Assert.assertEquals(existingImtdPsDetails.getContractMasterSid(),
			newImtdPsDetails.getContractMasterSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdPsDetails.getPsDtlsContPriceEnddate()),
			Time.getShortTimestamp(newImtdPsDetails.getPsDtlsContPriceEnddate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdPsDetails.getPsDetailsPricPrtcnStdate()),
			Time.getShortTimestamp(
				newImtdPsDetails.getPsDetailsPricPrtcnStdate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdPsDetails.getImtdCreatedDate()),
			Time.getShortTimestamp(newImtdPsDetails.getImtdCreatedDate()));
		Assert.assertEquals(existingImtdPsDetails.getNetPriceTypeFormula(),
			newImtdPsDetails.getNetPriceTypeFormula());
		Assert.assertEquals(existingImtdPsDetails.getModifiedBy(),
			newImtdPsDetails.getModifiedBy());
		AssertUtils.assertEquals(existingImtdPsDetails.getMaxIncrementalChange(),
			newImtdPsDetails.getMaxIncrementalChange());
		Assert.assertEquals(existingImtdPsDetails.getPsDetailsPricePlanId(),
			newImtdPsDetails.getPsDetailsPricePlanId());
		Assert.assertEquals(existingImtdPsDetails.getCheckRecord(),
			newImtdPsDetails.getCheckRecord());
		Assert.assertEquals(existingImtdPsDetails.getPsDtlsPriceToleranceFreq(),
			newImtdPsDetails.getPsDtlsPriceToleranceFreq());
		Assert.assertEquals(existingImtdPsDetails.getItemName(),
			newImtdPsDetails.getItemName());
		Assert.assertEquals(existingImtdPsDetails.getSessionId(),
			newImtdPsDetails.getSessionId());
		Assert.assertEquals(existingImtdPsDetails.getResetFrequency(),
			newImtdPsDetails.getResetFrequency());
		Assert.assertEquals(existingImtdPsDetails.getPsDtlsPriceToleranceType(),
			newImtdPsDetails.getPsDtlsPriceToleranceType());
		Assert.assertEquals(existingImtdPsDetails.getPsDetailsPricetype(),
			newImtdPsDetails.getPsDetailsPricetype());
		AssertUtils.assertEquals(existingImtdPsDetails.getPsDetailsPriceRevision(),
			newImtdPsDetails.getPsDetailsPriceRevision());
		Assert.assertEquals(existingImtdPsDetails.getResetInterval(),
			newImtdPsDetails.getResetInterval());
		Assert.assertEquals(existingImtdPsDetails.getIfpNo(),
			newImtdPsDetails.getIfpNo());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdPsDetails.getPsDetailsAttachedDate()),
			Time.getShortTimestamp(newImtdPsDetails.getPsDetailsAttachedDate()));
		Assert.assertEquals(existingImtdPsDetails.getNepFormula(),
			newImtdPsDetails.getNepFormula());
		Assert.assertEquals(existingImtdPsDetails.getPsDetailsModifiedBy(),
			newImtdPsDetails.getPsDetailsModifiedBy());
		Assert.assertEquals(existingImtdPsDetails.getPsDtlsPriceToleranceIntrvl(),
			newImtdPsDetails.getPsDtlsPriceToleranceIntrvl());
		Assert.assertEquals(existingImtdPsDetails.getItemMasterSid(),
			newImtdPsDetails.getItemMasterSid());
		Assert.assertEquals(existingImtdPsDetails.getResetType(),
			newImtdPsDetails.getResetType());
		Assert.assertEquals(existingImtdPsDetails.getItemId(),
			newImtdPsDetails.getItemId());
		Assert.assertEquals(existingImtdPsDetails.getStatus(),
			newImtdPsDetails.getStatus());
		Assert.assertEquals(existingImtdPsDetails.getBrandMasterSid(),
			newImtdPsDetails.getBrandMasterSid());
		AssertUtils.assertEquals(existingImtdPsDetails.getPsDetailsPrice(),
			newImtdPsDetails.getPsDetailsPrice());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdPsDetails.getPsDetailsCreatedDate()),
			Time.getShortTimestamp(newImtdPsDetails.getPsDetailsCreatedDate()));
		Assert.assertEquals(existingImtdPsDetails.getUsersSid(),
			newImtdPsDetails.getUsersSid());
		Assert.assertEquals(existingImtdPsDetails.getCreatedBy(),
			newImtdPsDetails.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdPsDetails.getCreatedDate()),
			Time.getShortTimestamp(newImtdPsDetails.getCreatedDate()));
		Assert.assertEquals(existingImtdPsDetails.getPsDetailsSid(),
			newImtdPsDetails.getPsDetailsSid());
		Assert.assertEquals(existingImtdPsDetails.getPsModelSid(),
			newImtdPsDetails.getPsModelSid());
		Assert.assertEquals(existingImtdPsDetails.getPriceProtectionPriceType(),
			newImtdPsDetails.getPriceProtectionPriceType());
		AssertUtils.assertEquals(existingImtdPsDetails.getPsDetailsBasePrice(),
			newImtdPsDetails.getPsDetailsBasePrice());
		Assert.assertEquals(existingImtdPsDetails.getItemNo(),
			newImtdPsDetails.getItemNo());
		Assert.assertEquals(existingImtdPsDetails.getIfpModelSid(),
			newImtdPsDetails.getIfpModelSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdPsDetails.getPsDetailsRevisionDate()),
			Time.getShortTimestamp(newImtdPsDetails.getPsDetailsRevisionDate()));
		AssertUtils.assertEquals(existingImtdPsDetails.getNep(),
			newImtdPsDetails.getNep());
		AssertUtils.assertEquals(existingImtdPsDetails.getPsDetailsPriceTolerance(),
			newImtdPsDetails.getPsDetailsPriceTolerance());
		Assert.assertEquals(existingImtdPsDetails.getPriceProtectionStatus(),
			newImtdPsDetails.getPriceProtectionStatus());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdPsDetails.getPsDtlsContPriceStartdate()),
			Time.getShortTimestamp(
				newImtdPsDetails.getPsDtlsContPriceStartdate()));
		Assert.assertEquals(existingImtdPsDetails.getResetEligible(),
			newImtdPsDetails.getResetEligible());
		Assert.assertEquals(existingImtdPsDetails.getNetPriceType(),
			newImtdPsDetails.getNetPriceType());
		Assert.assertEquals(existingImtdPsDetails.getOperation(),
			newImtdPsDetails.getOperation());
		Assert.assertEquals(existingImtdPsDetails.getCfpModelSid(),
			newImtdPsDetails.getCfpModelSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdPsDetails.getPsDetailsPricPrtcnEddate()),
			Time.getShortTimestamp(
				newImtdPsDetails.getPsDetailsPricPrtcnEddate()));
		Assert.assertEquals(existingImtdPsDetails.getBasePriceType(),
			newImtdPsDetails.getBasePriceType());
		AssertUtils.assertEquals(existingImtdPsDetails.getBasePriceEntry(),
			newImtdPsDetails.getBasePriceEntry());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdPsDetails.getBasePriceDate()),
			Time.getShortTimestamp(newImtdPsDetails.getBasePriceDate()));
		Assert.assertEquals(existingImtdPsDetails.getBasePriceDdlb(),
			newImtdPsDetails.getBasePriceDdlb());
		Assert.assertEquals(existingImtdPsDetails.getNetBasePrice(),
			newImtdPsDetails.getNetBasePrice());
		Assert.assertEquals(existingImtdPsDetails.getNetBasePriceFormulaId(),
			newImtdPsDetails.getNetBasePriceFormulaId());
		Assert.assertEquals(existingImtdPsDetails.getNetBasePriceFormulaNo(),
			newImtdPsDetails.getNetBasePriceFormulaNo());
		Assert.assertEquals(existingImtdPsDetails.getNetBasePriceFormulaName(),
			newImtdPsDetails.getNetBasePriceFormulaName());
		Assert.assertEquals(existingImtdPsDetails.getSubsequentPeriodPriceType(),
			newImtdPsDetails.getSubsequentPeriodPriceType());
		Assert.assertEquals(existingImtdPsDetails.getNetSubsequentPeriodPrice(),
			newImtdPsDetails.getNetSubsequentPeriodPrice());
		Assert.assertEquals(existingImtdPsDetails.getNetSubsequentPriceFormulaId(),
			newImtdPsDetails.getNetSubsequentPriceFormulaId());
		Assert.assertEquals(existingImtdPsDetails.getNetSubsequentPriceFormulaNo(),
			newImtdPsDetails.getNetSubsequentPriceFormulaNo());
		Assert.assertEquals(existingImtdPsDetails.getNetSubsequentPriceFormulaName(),
			newImtdPsDetails.getNetSubsequentPriceFormulaName());
		Assert.assertEquals(existingImtdPsDetails.getResetPriceType(),
			newImtdPsDetails.getResetPriceType());
		Assert.assertEquals(existingImtdPsDetails.getNetResetPriceType(),
			newImtdPsDetails.getNetResetPriceType());
		Assert.assertEquals(existingImtdPsDetails.getNetResetPriceFormulaId(),
			newImtdPsDetails.getNetResetPriceFormulaId());
		Assert.assertEquals(existingImtdPsDetails.getNetResetPriceFormulaNo(),
			newImtdPsDetails.getNetResetPriceFormulaNo());
		Assert.assertEquals(existingImtdPsDetails.getNetResetPriceFormulaName(),
			newImtdPsDetails.getNetResetPriceFormulaName());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ImtdPsDetails newImtdPsDetails = addImtdPsDetails();

		ImtdPsDetails existingImtdPsDetails = _persistence.findByPrimaryKey(newImtdPsDetails.getPrimaryKey());

		Assert.assertEquals(existingImtdPsDetails, newImtdPsDetails);
	}

	@Test(expected = NoSuchImtdPsDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<ImtdPsDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("IMTD_PS_DETAILS",
			"psDetailsModifiedDate", true, "psDetailsSuggestedPrice", true,
			"psDetailsContractPrice", true, "resetDate", true,
			"psDetailsAttachedStatus", true, "imtdPsDetailsSid", true,
			"modifiedDate", true, "psDetailsCreatedBy", true,
			"contractMasterSid", true, "psDtlsContPriceEnddate", true,
			"psDetailsPricPrtcnStdate", true, "imtdCreatedDate", true,
			"netPriceTypeFormula", true, "modifiedBy", true,
			"maxIncrementalChange", true, "psDetailsPricePlanId", true,
			"checkRecord", true, "psDtlsPriceToleranceFreq", true, "itemName",
			true, "sessionId", true, "resetFrequency", true,
			"psDtlsPriceToleranceType", true, "psDetailsPricetype", true,
			"psDetailsPriceRevision", true, "resetInterval", true, "ifpNo",
			true, "psDetailsAttachedDate", true, "nepFormula", true,
			"psDetailsModifiedBy", true, "psDtlsPriceToleranceIntrvl", true,
			"itemMasterSid", true, "resetType", true, "itemId", true, "status",
			true, "brandMasterSid", true, "psDetailsPrice", true,
			"psDetailsCreatedDate", true, "usersSid", true, "createdBy", true,
			"createdDate", true, "psDetailsSid", true, "psModelSid", true,
			"priceProtectionPriceType", true, "psDetailsBasePrice", true,
			"itemNo", true, "ifpModelSid", true, "psDetailsRevisionDate", true,
			"nep", true, "psDetailsPriceTolerance", true,
			"priceProtectionStatus", true, "psDtlsContPriceStartdate", true,
			"resetEligible", true, "netPriceType", true, "operation", true,
			"cfpModelSid", true, "psDetailsPricPrtcnEddate", true,
			"basePriceType", true, "basePriceEntry", true, "basePriceDate",
			true, "basePriceDdlb", true, "netBasePrice", true,
			"netBasePriceFormulaId", true, "netBasePriceFormulaNo", true,
			"netBasePriceFormulaName", true, "subsequentPeriodPriceType", true,
			"netSubsequentPeriodPrice", true, "netSubsequentPriceFormulaId",
			true, "netSubsequentPriceFormulaNo", true,
			"netSubsequentPriceFormulaName", true, "resetPriceType", true,
			"netResetPriceType", true, "netResetPriceFormulaId", true,
			"netResetPriceFormulaNo", true, "netResetPriceFormulaName", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ImtdPsDetails newImtdPsDetails = addImtdPsDetails();

		ImtdPsDetails existingImtdPsDetails = _persistence.fetchByPrimaryKey(newImtdPsDetails.getPrimaryKey());

		Assert.assertEquals(existingImtdPsDetails, newImtdPsDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ImtdPsDetails missingImtdPsDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingImtdPsDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ImtdPsDetails newImtdPsDetails1 = addImtdPsDetails();
		ImtdPsDetails newImtdPsDetails2 = addImtdPsDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newImtdPsDetails1.getPrimaryKey());
		primaryKeys.add(newImtdPsDetails2.getPrimaryKey());

		Map<Serializable, ImtdPsDetails> imtdPsDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, imtdPsDetailses.size());
		Assert.assertEquals(newImtdPsDetails1,
			imtdPsDetailses.get(newImtdPsDetails1.getPrimaryKey()));
		Assert.assertEquals(newImtdPsDetails2,
			imtdPsDetailses.get(newImtdPsDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ImtdPsDetails> imtdPsDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(imtdPsDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ImtdPsDetails newImtdPsDetails = addImtdPsDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newImtdPsDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ImtdPsDetails> imtdPsDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, imtdPsDetailses.size());
		Assert.assertEquals(newImtdPsDetails,
			imtdPsDetailses.get(newImtdPsDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ImtdPsDetails> imtdPsDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(imtdPsDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ImtdPsDetails newImtdPsDetails = addImtdPsDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newImtdPsDetails.getPrimaryKey());

		Map<Serializable, ImtdPsDetails> imtdPsDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, imtdPsDetailses.size());
		Assert.assertEquals(newImtdPsDetails,
			imtdPsDetailses.get(newImtdPsDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ImtdPsDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ImtdPsDetails>() {
				@Override
				public void performAction(ImtdPsDetails imtdPsDetails) {
					Assert.assertNotNull(imtdPsDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ImtdPsDetails newImtdPsDetails = addImtdPsDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdPsDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("imtdPsDetailsSid",
				newImtdPsDetails.getImtdPsDetailsSid()));

		List<ImtdPsDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ImtdPsDetails existingImtdPsDetails = result.get(0);

		Assert.assertEquals(existingImtdPsDetails, newImtdPsDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdPsDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("imtdPsDetailsSid",
				RandomTestUtil.nextInt()));

		List<ImtdPsDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ImtdPsDetails newImtdPsDetails = addImtdPsDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdPsDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"imtdPsDetailsSid"));

		Object newImtdPsDetailsSid = newImtdPsDetails.getImtdPsDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("imtdPsDetailsSid",
				new Object[] { newImtdPsDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingImtdPsDetailsSid = result.get(0);

		Assert.assertEquals(existingImtdPsDetailsSid, newImtdPsDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdPsDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"imtdPsDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("imtdPsDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ImtdPsDetails addImtdPsDetails() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ImtdPsDetails imtdPsDetails = _persistence.create(pk);

		imtdPsDetails.setPsDetailsModifiedDate(RandomTestUtil.nextDate());

		imtdPsDetails.setPsDetailsSuggestedPrice(RandomTestUtil.nextDouble());

		imtdPsDetails.setPsDetailsContractPrice(RandomTestUtil.nextDouble());

		imtdPsDetails.setResetDate(RandomTestUtil.nextDate());

		imtdPsDetails.setPsDetailsAttachedStatus(RandomTestUtil.nextInt());

		imtdPsDetails.setModifiedDate(RandomTestUtil.nextDate());

		imtdPsDetails.setPsDetailsCreatedBy(RandomTestUtil.nextInt());

		imtdPsDetails.setContractMasterSid(RandomTestUtil.nextInt());

		imtdPsDetails.setPsDtlsContPriceEnddate(RandomTestUtil.nextDate());

		imtdPsDetails.setPsDetailsPricPrtcnStdate(RandomTestUtil.nextDate());

		imtdPsDetails.setImtdCreatedDate(RandomTestUtil.nextDate());

		imtdPsDetails.setNetPriceTypeFormula(RandomTestUtil.randomString());

		imtdPsDetails.setModifiedBy(RandomTestUtil.nextInt());

		imtdPsDetails.setMaxIncrementalChange(RandomTestUtil.nextDouble());

		imtdPsDetails.setPsDetailsPricePlanId(RandomTestUtil.randomString());

		imtdPsDetails.setCheckRecord(RandomTestUtil.randomBoolean());

		imtdPsDetails.setPsDtlsPriceToleranceFreq(RandomTestUtil.nextInt());

		imtdPsDetails.setItemName(RandomTestUtil.randomString());

		imtdPsDetails.setSessionId(RandomTestUtil.randomString());

		imtdPsDetails.setResetFrequency(RandomTestUtil.nextInt());

		imtdPsDetails.setPsDtlsPriceToleranceType(RandomTestUtil.nextInt());

		imtdPsDetails.setPsDetailsPricetype(RandomTestUtil.nextInt());

		imtdPsDetails.setPsDetailsPriceRevision(RandomTestUtil.nextDouble());

		imtdPsDetails.setResetInterval(RandomTestUtil.nextInt());

		imtdPsDetails.setIfpNo(RandomTestUtil.randomString());

		imtdPsDetails.setPsDetailsAttachedDate(RandomTestUtil.nextDate());

		imtdPsDetails.setNepFormula(RandomTestUtil.nextInt());

		imtdPsDetails.setPsDetailsModifiedBy(RandomTestUtil.nextInt());

		imtdPsDetails.setPsDtlsPriceToleranceIntrvl(RandomTestUtil.nextInt());

		imtdPsDetails.setItemMasterSid(RandomTestUtil.nextInt());

		imtdPsDetails.setResetType(RandomTestUtil.nextInt());

		imtdPsDetails.setItemId(RandomTestUtil.randomString());

		imtdPsDetails.setStatus(RandomTestUtil.nextInt());

		imtdPsDetails.setBrandMasterSid(RandomTestUtil.nextInt());

		imtdPsDetails.setPsDetailsPrice(RandomTestUtil.nextDouble());

		imtdPsDetails.setPsDetailsCreatedDate(RandomTestUtil.nextDate());

		imtdPsDetails.setUsersSid(RandomTestUtil.nextInt());

		imtdPsDetails.setCreatedBy(RandomTestUtil.nextInt());

		imtdPsDetails.setCreatedDate(RandomTestUtil.nextDate());

		imtdPsDetails.setPsDetailsSid(RandomTestUtil.nextInt());

		imtdPsDetails.setPsModelSid(RandomTestUtil.nextInt());

		imtdPsDetails.setPriceProtectionPriceType(RandomTestUtil.nextInt());

		imtdPsDetails.setPsDetailsBasePrice(RandomTestUtil.nextDouble());

		imtdPsDetails.setItemNo(RandomTestUtil.randomString());

		imtdPsDetails.setIfpModelSid(RandomTestUtil.nextInt());

		imtdPsDetails.setPsDetailsRevisionDate(RandomTestUtil.nextDate());

		imtdPsDetails.setNep(RandomTestUtil.nextDouble());

		imtdPsDetails.setPsDetailsPriceTolerance(RandomTestUtil.nextDouble());

		imtdPsDetails.setPriceProtectionStatus(RandomTestUtil.nextInt());

		imtdPsDetails.setPsDtlsContPriceStartdate(RandomTestUtil.nextDate());

		imtdPsDetails.setResetEligible(RandomTestUtil.nextInt());

		imtdPsDetails.setNetPriceType(RandomTestUtil.nextInt());

		imtdPsDetails.setOperation(RandomTestUtil.randomString());

		imtdPsDetails.setCfpModelSid(RandomTestUtil.nextInt());

		imtdPsDetails.setPsDetailsPricPrtcnEddate(RandomTestUtil.nextDate());

		imtdPsDetails.setBasePriceType(RandomTestUtil.nextInt());

		imtdPsDetails.setBasePriceEntry(RandomTestUtil.nextDouble());

		imtdPsDetails.setBasePriceDate(RandomTestUtil.nextDate());

		imtdPsDetails.setBasePriceDdlb(RandomTestUtil.nextInt());

		imtdPsDetails.setNetBasePrice(RandomTestUtil.nextInt());

		imtdPsDetails.setNetBasePriceFormulaId(RandomTestUtil.nextInt());

		imtdPsDetails.setNetBasePriceFormulaNo(RandomTestUtil.randomString());

		imtdPsDetails.setNetBasePriceFormulaName(RandomTestUtil.randomString());

		imtdPsDetails.setSubsequentPeriodPriceType(RandomTestUtil.nextInt());

		imtdPsDetails.setNetSubsequentPeriodPrice(RandomTestUtil.nextInt());

		imtdPsDetails.setNetSubsequentPriceFormulaId(RandomTestUtil.nextInt());

		imtdPsDetails.setNetSubsequentPriceFormulaNo(RandomTestUtil.randomString());

		imtdPsDetails.setNetSubsequentPriceFormulaName(RandomTestUtil.randomString());

		imtdPsDetails.setResetPriceType(RandomTestUtil.nextInt());

		imtdPsDetails.setNetResetPriceType(RandomTestUtil.nextInt());

		imtdPsDetails.setNetResetPriceFormulaId(RandomTestUtil.nextInt());

		imtdPsDetails.setNetResetPriceFormulaNo(RandomTestUtil.randomString());

		imtdPsDetails.setNetResetPriceFormulaName(RandomTestUtil.randomString());

		_imtdPsDetailses.add(_persistence.update(imtdPsDetails));

		return imtdPsDetails;
	}

	private List<ImtdPsDetails> _imtdPsDetailses = new ArrayList<ImtdPsDetails>();
	private ImtdPsDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}