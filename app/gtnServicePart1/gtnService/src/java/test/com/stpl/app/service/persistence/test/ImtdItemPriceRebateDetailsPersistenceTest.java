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

import com.stpl.app.exception.NoSuchImtdItemPriceRebateDetailsException;
import com.stpl.app.model.ImtdItemPriceRebateDetails;
import com.stpl.app.service.ImtdItemPriceRebateDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.ImtdItemPriceRebateDetailsPersistence;
import com.stpl.app.service.persistence.ImtdItemPriceRebateDetailsUtil;

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
public class ImtdItemPriceRebateDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ImtdItemPriceRebateDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ImtdItemPriceRebateDetails> iterator = _imtdItemPriceRebateDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ImtdItemPriceRebateDetails imtdItemPriceRebateDetails = _persistence.create(pk);

		Assert.assertNotNull(imtdItemPriceRebateDetails);

		Assert.assertEquals(imtdItemPriceRebateDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ImtdItemPriceRebateDetails newImtdItemPriceRebateDetails = addImtdItemPriceRebateDetails();

		_persistence.remove(newImtdItemPriceRebateDetails);

		ImtdItemPriceRebateDetails existingImtdItemPriceRebateDetails = _persistence.fetchByPrimaryKey(newImtdItemPriceRebateDetails.getPrimaryKey());

		Assert.assertNull(existingImtdItemPriceRebateDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addImtdItemPriceRebateDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ImtdItemPriceRebateDetails newImtdItemPriceRebateDetails = _persistence.create(pk);

		newImtdItemPriceRebateDetails.setFormulaMethodId(RandomTestUtil.randomString());

		newImtdItemPriceRebateDetails.setEndDate(RandomTestUtil.nextDate());

		newImtdItemPriceRebateDetails.setPriceProtectionStartDate(RandomTestUtil.nextDate());

		newImtdItemPriceRebateDetails.setItemPriceRevisionDate(RandomTestUtil.nextDate());

		newImtdItemPriceRebateDetails.setModifiedDate(RandomTestUtil.nextDate());

		newImtdItemPriceRebateDetails.setRsCheckRecord(RandomTestUtil.randomBoolean());

		newImtdItemPriceRebateDetails.setRebateRevisionDate(RandomTestUtil.nextDate());

		newImtdItemPriceRebateDetails.setContractMasterSid(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setImtdCreatedDate(RandomTestUtil.nextDate());

		newImtdItemPriceRebateDetails.setModifiedBy(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setUdc6(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setUdc5(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setUdc4(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setCheckRecord(RandomTestUtil.randomBoolean());

		newImtdItemPriceRebateDetails.setUdc1(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setUdc2(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setUdc3(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setContractPriceEndDate(RandomTestUtil.nextDate());

		newImtdItemPriceRebateDetails.setTotalVolumeCommitment(RandomTestUtil.nextDouble());

		newImtdItemPriceRebateDetails.setPriceProtectionEndDate(RandomTestUtil.nextDate());

		newImtdItemPriceRebateDetails.setRecordLockStatus(RandomTestUtil.randomString());

		newImtdItemPriceRebateDetails.setStartDate(RandomTestUtil.nextDate());

		newImtdItemPriceRebateDetails.setRebateProgramType(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setSessionId(RandomTestUtil.randomString());

		newImtdItemPriceRebateDetails.setItemName(RandomTestUtil.randomString());

		newImtdItemPriceRebateDetails.setPriceRevision(RandomTestUtil.nextDouble());

		newImtdItemPriceRebateDetails.setRsModelSid(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setPrice(RandomTestUtil.nextDouble());

		newImtdItemPriceRebateDetails.setRsAttachedStatus(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setItemMasterSid(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setTotalDollarCommitment(RandomTestUtil.nextDouble());

		newImtdItemPriceRebateDetails.setItemType(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setTotalMarketShareCommitmnet(RandomTestUtil.nextDouble());

		newImtdItemPriceRebateDetails.setItemId(RandomTestUtil.randomString());

		newImtdItemPriceRebateDetails.setBasePrice(RandomTestUtil.nextDouble());

		newImtdItemPriceRebateDetails.setBundleNo(RandomTestUtil.randomString());

		newImtdItemPriceRebateDetails.setFormulaName(RandomTestUtil.randomString());

		newImtdItemPriceRebateDetails.setPsStatus(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setPriceTolerance(RandomTestUtil.nextDouble());

		newImtdItemPriceRebateDetails.setCreatedDate(RandomTestUtil.nextDate());

		newImtdItemPriceRebateDetails.setCreatedBy(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setUsersSid(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setPsDetailsSid(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setSuggestedPrice(RandomTestUtil.nextDouble());

		newImtdItemPriceRebateDetails.setPsModelSid(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setFormulaId(RandomTestUtil.randomString());

		newImtdItemPriceRebateDetails.setCommitmentPeriod(RandomTestUtil.randomString());

		newImtdItemPriceRebateDetails.setItemNo(RandomTestUtil.randomString());

		newImtdItemPriceRebateDetails.setContractPrice(RandomTestUtil.nextDouble());

		newImtdItemPriceRebateDetails.setIfpDetailsSid(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setIfpModelSid(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setPriceToleranceType(RandomTestUtil.randomString());

		newImtdItemPriceRebateDetails.setRebateAmount(RandomTestUtil.nextDouble());

		newImtdItemPriceRebateDetails.setContractPriceStartDate(RandomTestUtil.nextDate());

		newImtdItemPriceRebateDetails.setRebateScheduleType(RandomTestUtil.randomString());

		newImtdItemPriceRebateDetails.setPriceToleranceFrequency(RandomTestUtil.randomString());

		newImtdItemPriceRebateDetails.setRebatePlanSystemId(RandomTestUtil.randomString());

		newImtdItemPriceRebateDetails.setAttachedDate(RandomTestUtil.nextDate());

		newImtdItemPriceRebateDetails.setPricePlanId(RandomTestUtil.randomString());

		newImtdItemPriceRebateDetails.setItemRebateEndDate(RandomTestUtil.nextDate());

		newImtdItemPriceRebateDetails.setPriceType(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setPriceToleranceInterval(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setRsAttachedDate(RandomTestUtil.nextDate());

		newImtdItemPriceRebateDetails.setItemRebateStartDate(RandomTestUtil.nextDate());

		newImtdItemPriceRebateDetails.setOperation(RandomTestUtil.randomString());

		newImtdItemPriceRebateDetails.setCfpModelSid(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setRsDetailsSid(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setAttachedStatus(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setPrimaryUom(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setPackageSize(RandomTestUtil.randomString());

		newImtdItemPriceRebateDetails.setDeductionCalendarMasterSid(RandomTestUtil.randomString());

		newImtdItemPriceRebateDetails.setRsContractDetailsDeductionCalendarNo(RandomTestUtil.randomString());

		newImtdItemPriceRebateDetails.setRsContractDetailsDeductionCalendarName(RandomTestUtil.randomString());

		newImtdItemPriceRebateDetails.setNetSalesFormulaMasterSid(RandomTestUtil.randomString());

		newImtdItemPriceRebateDetails.setRsContractDetailsNetSalesFormulaNo(RandomTestUtil.randomString());

		newImtdItemPriceRebateDetails.setRsContractDetailsNetSalesFormulaName(RandomTestUtil.randomString());

		newImtdItemPriceRebateDetails.setFormulaType(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setNetSalesRule(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setEvaluationRule(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setEvaluationRuleBundle(RandomTestUtil.randomString());

		newImtdItemPriceRebateDetails.setCalculationRule(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setCalculationRuleBundle(RandomTestUtil.randomString());

		newImtdItemPriceRebateDetails.setMaxIncrementalChange(RandomTestUtil.nextDouble());

		newImtdItemPriceRebateDetails.setResetEligible(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setResetType(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setResetDate(RandomTestUtil.nextDate());

		newImtdItemPriceRebateDetails.setResetInterval(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setResetFrequency(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setNetPriceType(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setNetPriceTypeFormula(RandomTestUtil.randomString());

		newImtdItemPriceRebateDetails.setPriceProtectionPriceType(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setNep(RandomTestUtil.nextDouble());

		newImtdItemPriceRebateDetails.setNepFormula(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setBrandMasterSid(RandomTestUtil.randomString());

		newImtdItemPriceRebateDetails.setPriceProtectionStatus(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setBasePriceType(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setBasePriceEntry(RandomTestUtil.nextDouble());

		newImtdItemPriceRebateDetails.setBasePriceDate(RandomTestUtil.nextDate());

		newImtdItemPriceRebateDetails.setBasePriceDdlb(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setNetBasePrice(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setSubsequentPeriodPriceType(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setResetPriceType(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setNetResetPriceType(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setNetResetPriceFormulaId(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setNetBasePriceFormulaId(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setNetSubsequentPriceFormulaId(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setNetSubsequentPeriodPrice(RandomTestUtil.nextInt());

		newImtdItemPriceRebateDetails.setRsContractDetailsRebatePlanName(RandomTestUtil.randomString());

		newImtdItemPriceRebateDetails.setRsContractDetailsFormulaNo(RandomTestUtil.randomString());

		newImtdItemPriceRebateDetails.setSource(RandomTestUtil.randomString());

		_imtdItemPriceRebateDetailses.add(_persistence.update(
				newImtdItemPriceRebateDetails));

		ImtdItemPriceRebateDetails existingImtdItemPriceRebateDetails = _persistence.findByPrimaryKey(newImtdItemPriceRebateDetails.getPrimaryKey());

		Assert.assertEquals(existingImtdItemPriceRebateDetails.getFormulaMethodId(),
			newImtdItemPriceRebateDetails.getFormulaMethodId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdItemPriceRebateDetails.getEndDate()),
			Time.getShortTimestamp(newImtdItemPriceRebateDetails.getEndDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdItemPriceRebateDetails.getPriceProtectionStartDate()),
			Time.getShortTimestamp(
				newImtdItemPriceRebateDetails.getPriceProtectionStartDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdItemPriceRebateDetails.getItemPriceRevisionDate()),
			Time.getShortTimestamp(
				newImtdItemPriceRebateDetails.getItemPriceRevisionDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdItemPriceRebateDetails.getModifiedDate()),
			Time.getShortTimestamp(
				newImtdItemPriceRebateDetails.getModifiedDate()));
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getRsCheckRecord(),
			newImtdItemPriceRebateDetails.getRsCheckRecord());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdItemPriceRebateDetails.getRebateRevisionDate()),
			Time.getShortTimestamp(
				newImtdItemPriceRebateDetails.getRebateRevisionDate()));
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getContractMasterSid(),
			newImtdItemPriceRebateDetails.getContractMasterSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdItemPriceRebateDetails.getImtdCreatedDate()),
			Time.getShortTimestamp(
				newImtdItemPriceRebateDetails.getImtdCreatedDate()));
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getModifiedBy(),
			newImtdItemPriceRebateDetails.getModifiedBy());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getUdc6(),
			newImtdItemPriceRebateDetails.getUdc6());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getUdc5(),
			newImtdItemPriceRebateDetails.getUdc5());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getUdc4(),
			newImtdItemPriceRebateDetails.getUdc4());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getCheckRecord(),
			newImtdItemPriceRebateDetails.getCheckRecord());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getUdc1(),
			newImtdItemPriceRebateDetails.getUdc1());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getUdc2(),
			newImtdItemPriceRebateDetails.getUdc2());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getUdc3(),
			newImtdItemPriceRebateDetails.getUdc3());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdItemPriceRebateDetails.getContractPriceEndDate()),
			Time.getShortTimestamp(
				newImtdItemPriceRebateDetails.getContractPriceEndDate()));
		AssertUtils.assertEquals(existingImtdItemPriceRebateDetails.getTotalVolumeCommitment(),
			newImtdItemPriceRebateDetails.getTotalVolumeCommitment());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdItemPriceRebateDetails.getPriceProtectionEndDate()),
			Time.getShortTimestamp(
				newImtdItemPriceRebateDetails.getPriceProtectionEndDate()));
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getRecordLockStatus(),
			newImtdItemPriceRebateDetails.getRecordLockStatus());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdItemPriceRebateDetails.getStartDate()),
			Time.getShortTimestamp(newImtdItemPriceRebateDetails.getStartDate()));
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getRebateProgramType(),
			newImtdItemPriceRebateDetails.getRebateProgramType());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getSessionId(),
			newImtdItemPriceRebateDetails.getSessionId());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getItemName(),
			newImtdItemPriceRebateDetails.getItemName());
		AssertUtils.assertEquals(existingImtdItemPriceRebateDetails.getPriceRevision(),
			newImtdItemPriceRebateDetails.getPriceRevision());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getRsModelSid(),
			newImtdItemPriceRebateDetails.getRsModelSid());
		AssertUtils.assertEquals(existingImtdItemPriceRebateDetails.getPrice(),
			newImtdItemPriceRebateDetails.getPrice());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getRsAttachedStatus(),
			newImtdItemPriceRebateDetails.getRsAttachedStatus());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getItemMasterSid(),
			newImtdItemPriceRebateDetails.getItemMasterSid());
		AssertUtils.assertEquals(existingImtdItemPriceRebateDetails.getTotalDollarCommitment(),
			newImtdItemPriceRebateDetails.getTotalDollarCommitment());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getItemType(),
			newImtdItemPriceRebateDetails.getItemType());
		AssertUtils.assertEquals(existingImtdItemPriceRebateDetails.getTotalMarketShareCommitmnet(),
			newImtdItemPriceRebateDetails.getTotalMarketShareCommitmnet());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getItemId(),
			newImtdItemPriceRebateDetails.getItemId());
		AssertUtils.assertEquals(existingImtdItemPriceRebateDetails.getBasePrice(),
			newImtdItemPriceRebateDetails.getBasePrice());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getBundleNo(),
			newImtdItemPriceRebateDetails.getBundleNo());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getFormulaName(),
			newImtdItemPriceRebateDetails.getFormulaName());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getPsStatus(),
			newImtdItemPriceRebateDetails.getPsStatus());
		AssertUtils.assertEquals(existingImtdItemPriceRebateDetails.getPriceTolerance(),
			newImtdItemPriceRebateDetails.getPriceTolerance());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdItemPriceRebateDetails.getCreatedDate()),
			Time.getShortTimestamp(
				newImtdItemPriceRebateDetails.getCreatedDate()));
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getCreatedBy(),
			newImtdItemPriceRebateDetails.getCreatedBy());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getUsersSid(),
			newImtdItemPriceRebateDetails.getUsersSid());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getPsDetailsSid(),
			newImtdItemPriceRebateDetails.getPsDetailsSid());
		AssertUtils.assertEquals(existingImtdItemPriceRebateDetails.getSuggestedPrice(),
			newImtdItemPriceRebateDetails.getSuggestedPrice());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getPsModelSid(),
			newImtdItemPriceRebateDetails.getPsModelSid());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getFormulaId(),
			newImtdItemPriceRebateDetails.getFormulaId());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getCommitmentPeriod(),
			newImtdItemPriceRebateDetails.getCommitmentPeriod());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getItemNo(),
			newImtdItemPriceRebateDetails.getItemNo());
		AssertUtils.assertEquals(existingImtdItemPriceRebateDetails.getContractPrice(),
			newImtdItemPriceRebateDetails.getContractPrice());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getIfpDetailsSid(),
			newImtdItemPriceRebateDetails.getIfpDetailsSid());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getIfpModelSid(),
			newImtdItemPriceRebateDetails.getIfpModelSid());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getPriceToleranceType(),
			newImtdItemPriceRebateDetails.getPriceToleranceType());
		AssertUtils.assertEquals(existingImtdItemPriceRebateDetails.getRebateAmount(),
			newImtdItemPriceRebateDetails.getRebateAmount());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdItemPriceRebateDetails.getContractPriceStartDate()),
			Time.getShortTimestamp(
				newImtdItemPriceRebateDetails.getContractPriceStartDate()));
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getRebateScheduleType(),
			newImtdItemPriceRebateDetails.getRebateScheduleType());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getPriceToleranceFrequency(),
			newImtdItemPriceRebateDetails.getPriceToleranceFrequency());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getImtdItemPriceRebateSid(),
			newImtdItemPriceRebateDetails.getImtdItemPriceRebateSid());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getRebatePlanSystemId(),
			newImtdItemPriceRebateDetails.getRebatePlanSystemId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdItemPriceRebateDetails.getAttachedDate()),
			Time.getShortTimestamp(
				newImtdItemPriceRebateDetails.getAttachedDate()));
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getPricePlanId(),
			newImtdItemPriceRebateDetails.getPricePlanId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdItemPriceRebateDetails.getItemRebateEndDate()),
			Time.getShortTimestamp(
				newImtdItemPriceRebateDetails.getItemRebateEndDate()));
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getPriceType(),
			newImtdItemPriceRebateDetails.getPriceType());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getPriceToleranceInterval(),
			newImtdItemPriceRebateDetails.getPriceToleranceInterval());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdItemPriceRebateDetails.getRsAttachedDate()),
			Time.getShortTimestamp(
				newImtdItemPriceRebateDetails.getRsAttachedDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdItemPriceRebateDetails.getItemRebateStartDate()),
			Time.getShortTimestamp(
				newImtdItemPriceRebateDetails.getItemRebateStartDate()));
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getOperation(),
			newImtdItemPriceRebateDetails.getOperation());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getCfpModelSid(),
			newImtdItemPriceRebateDetails.getCfpModelSid());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getRsDetailsSid(),
			newImtdItemPriceRebateDetails.getRsDetailsSid());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getAttachedStatus(),
			newImtdItemPriceRebateDetails.getAttachedStatus());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getPrimaryUom(),
			newImtdItemPriceRebateDetails.getPrimaryUom());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getPackageSize(),
			newImtdItemPriceRebateDetails.getPackageSize());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getDeductionCalendarMasterSid(),
			newImtdItemPriceRebateDetails.getDeductionCalendarMasterSid());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getRsContractDetailsDeductionCalendarNo(),
			newImtdItemPriceRebateDetails.getRsContractDetailsDeductionCalendarNo());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getRsContractDetailsDeductionCalendarName(),
			newImtdItemPriceRebateDetails.getRsContractDetailsDeductionCalendarName());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getNetSalesFormulaMasterSid(),
			newImtdItemPriceRebateDetails.getNetSalesFormulaMasterSid());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getRsContractDetailsNetSalesFormulaNo(),
			newImtdItemPriceRebateDetails.getRsContractDetailsNetSalesFormulaNo());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getRsContractDetailsNetSalesFormulaName(),
			newImtdItemPriceRebateDetails.getRsContractDetailsNetSalesFormulaName());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getFormulaType(),
			newImtdItemPriceRebateDetails.getFormulaType());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getNetSalesRule(),
			newImtdItemPriceRebateDetails.getNetSalesRule());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getEvaluationRule(),
			newImtdItemPriceRebateDetails.getEvaluationRule());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getEvaluationRuleBundle(),
			newImtdItemPriceRebateDetails.getEvaluationRuleBundle());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getCalculationRule(),
			newImtdItemPriceRebateDetails.getCalculationRule());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getCalculationRuleBundle(),
			newImtdItemPriceRebateDetails.getCalculationRuleBundle());
		AssertUtils.assertEquals(existingImtdItemPriceRebateDetails.getMaxIncrementalChange(),
			newImtdItemPriceRebateDetails.getMaxIncrementalChange());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getResetEligible(),
			newImtdItemPriceRebateDetails.getResetEligible());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getResetType(),
			newImtdItemPriceRebateDetails.getResetType());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdItemPriceRebateDetails.getResetDate()),
			Time.getShortTimestamp(newImtdItemPriceRebateDetails.getResetDate()));
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getResetInterval(),
			newImtdItemPriceRebateDetails.getResetInterval());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getResetFrequency(),
			newImtdItemPriceRebateDetails.getResetFrequency());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getNetPriceType(),
			newImtdItemPriceRebateDetails.getNetPriceType());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getNetPriceTypeFormula(),
			newImtdItemPriceRebateDetails.getNetPriceTypeFormula());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getPriceProtectionPriceType(),
			newImtdItemPriceRebateDetails.getPriceProtectionPriceType());
		AssertUtils.assertEquals(existingImtdItemPriceRebateDetails.getNep(),
			newImtdItemPriceRebateDetails.getNep());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getNepFormula(),
			newImtdItemPriceRebateDetails.getNepFormula());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getBrandMasterSid(),
			newImtdItemPriceRebateDetails.getBrandMasterSid());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getPriceProtectionStatus(),
			newImtdItemPriceRebateDetails.getPriceProtectionStatus());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getBasePriceType(),
			newImtdItemPriceRebateDetails.getBasePriceType());
		AssertUtils.assertEquals(existingImtdItemPriceRebateDetails.getBasePriceEntry(),
			newImtdItemPriceRebateDetails.getBasePriceEntry());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdItemPriceRebateDetails.getBasePriceDate()),
			Time.getShortTimestamp(
				newImtdItemPriceRebateDetails.getBasePriceDate()));
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getBasePriceDdlb(),
			newImtdItemPriceRebateDetails.getBasePriceDdlb());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getNetBasePrice(),
			newImtdItemPriceRebateDetails.getNetBasePrice());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getSubsequentPeriodPriceType(),
			newImtdItemPriceRebateDetails.getSubsequentPeriodPriceType());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getResetPriceType(),
			newImtdItemPriceRebateDetails.getResetPriceType());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getNetResetPriceType(),
			newImtdItemPriceRebateDetails.getNetResetPriceType());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getNetResetPriceFormulaId(),
			newImtdItemPriceRebateDetails.getNetResetPriceFormulaId());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getNetBasePriceFormulaId(),
			newImtdItemPriceRebateDetails.getNetBasePriceFormulaId());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getNetSubsequentPriceFormulaId(),
			newImtdItemPriceRebateDetails.getNetSubsequentPriceFormulaId());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getNetSubsequentPeriodPrice(),
			newImtdItemPriceRebateDetails.getNetSubsequentPeriodPrice());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getRsContractDetailsRebatePlanName(),
			newImtdItemPriceRebateDetails.getRsContractDetailsRebatePlanName());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getRsContractDetailsFormulaNo(),
			newImtdItemPriceRebateDetails.getRsContractDetailsFormulaNo());
		Assert.assertEquals(existingImtdItemPriceRebateDetails.getSource(),
			newImtdItemPriceRebateDetails.getSource());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ImtdItemPriceRebateDetails newImtdItemPriceRebateDetails = addImtdItemPriceRebateDetails();

		ImtdItemPriceRebateDetails existingImtdItemPriceRebateDetails = _persistence.findByPrimaryKey(newImtdItemPriceRebateDetails.getPrimaryKey());

		Assert.assertEquals(existingImtdItemPriceRebateDetails,
			newImtdItemPriceRebateDetails);
	}

	@Test(expected = NoSuchImtdItemPriceRebateDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<ImtdItemPriceRebateDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("IMTD_ITEM_PRICE_REBATE_DETAILS",
			"formulaMethodId", true, "endDate", true,
			"priceProtectionStartDate", true, "itemPriceRevisionDate", true,
			"modifiedDate", true, "rsCheckRecord", true, "rebateRevisionDate",
			true, "contractMasterSid", true, "imtdCreatedDate", true,
			"modifiedBy", true, "udc6", true, "udc5", true, "udc4", true,
			"checkRecord", true, "udc1", true, "udc2", true, "udc3", true,
			"contractPriceEndDate", true, "totalVolumeCommitment", true,
			"priceProtectionEndDate", true, "recordLockStatus", true,
			"startDate", true, "rebateProgramType", true, "sessionId", true,
			"itemName", true, "priceRevision", true, "rsModelSid", true,
			"price", true, "rsAttachedStatus", true, "itemMasterSid", true,
			"totalDollarCommitment", true, "itemType", true,
			"totalMarketShareCommitmnet", true, "itemId", true, "basePrice",
			true, "bundleNo", true, "formulaName", true, "psStatus", true,
			"priceTolerance", true, "createdDate", true, "createdBy", true,
			"usersSid", true, "psDetailsSid", true, "suggestedPrice", true,
			"psModelSid", true, "formulaId", true, "commitmentPeriod", true,
			"itemNo", true, "contractPrice", true, "ifpDetailsSid", true,
			"ifpModelSid", true, "priceToleranceType", true, "rebateAmount",
			true, "contractPriceStartDate", true, "rebateScheduleType", true,
			"priceToleranceFrequency", true, "imtdItemPriceRebateSid", true,
			"rebatePlanSystemId", true, "attachedDate", true, "pricePlanId",
			true, "itemRebateEndDate", true, "priceType", true,
			"priceToleranceInterval", true, "rsAttachedDate", true,
			"itemRebateStartDate", true, "operation", true, "cfpModelSid",
			true, "rsDetailsSid", true, "attachedStatus", true, "primaryUom",
			true, "packageSize", true, "deductionCalendarMasterSid", true,
			"rsContractDetailsDeductionCalendarNo", true,
			"rsContractDetailsDeductionCalendarName", true,
			"netSalesFormulaMasterSid", true,
			"rsContractDetailsNetSalesFormulaNo", true,
			"rsContractDetailsNetSalesFormulaName", true, "formulaType", true,
			"netSalesRule", true, "evaluationRule", true,
			"evaluationRuleBundle", true, "calculationRule", true,
			"calculationRuleBundle", true, "maxIncrementalChange", true,
			"resetEligible", true, "resetType", true, "resetDate", true,
			"resetInterval", true, "resetFrequency", true, "netPriceType",
			true, "netPriceTypeFormula", true, "priceProtectionPriceType",
			true, "nep", true, "nepFormula", true, "brandMasterSid", true,
			"priceProtectionStatus", true, "basePriceType", true,
			"basePriceEntry", true, "basePriceDate", true, "basePriceDdlb",
			true, "netBasePrice", true, "subsequentPeriodPriceType", true,
			"resetPriceType", true, "netResetPriceType", true,
			"netResetPriceFormulaId", true, "netBasePriceFormulaId", true,
			"netSubsequentPriceFormulaId", true, "netSubsequentPeriodPrice",
			true, "rsContractDetailsRebatePlanName", true,
			"rsContractDetailsFormulaNo", true, "source", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ImtdItemPriceRebateDetails newImtdItemPriceRebateDetails = addImtdItemPriceRebateDetails();

		ImtdItemPriceRebateDetails existingImtdItemPriceRebateDetails = _persistence.fetchByPrimaryKey(newImtdItemPriceRebateDetails.getPrimaryKey());

		Assert.assertEquals(existingImtdItemPriceRebateDetails,
			newImtdItemPriceRebateDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ImtdItemPriceRebateDetails missingImtdItemPriceRebateDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingImtdItemPriceRebateDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ImtdItemPriceRebateDetails newImtdItemPriceRebateDetails1 = addImtdItemPriceRebateDetails();
		ImtdItemPriceRebateDetails newImtdItemPriceRebateDetails2 = addImtdItemPriceRebateDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newImtdItemPriceRebateDetails1.getPrimaryKey());
		primaryKeys.add(newImtdItemPriceRebateDetails2.getPrimaryKey());

		Map<Serializable, ImtdItemPriceRebateDetails> imtdItemPriceRebateDetailses =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, imtdItemPriceRebateDetailses.size());
		Assert.assertEquals(newImtdItemPriceRebateDetails1,
			imtdItemPriceRebateDetailses.get(
				newImtdItemPriceRebateDetails1.getPrimaryKey()));
		Assert.assertEquals(newImtdItemPriceRebateDetails2,
			imtdItemPriceRebateDetailses.get(
				newImtdItemPriceRebateDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ImtdItemPriceRebateDetails> imtdItemPriceRebateDetailses =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(imtdItemPriceRebateDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ImtdItemPriceRebateDetails newImtdItemPriceRebateDetails = addImtdItemPriceRebateDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newImtdItemPriceRebateDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ImtdItemPriceRebateDetails> imtdItemPriceRebateDetailses =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, imtdItemPriceRebateDetailses.size());
		Assert.assertEquals(newImtdItemPriceRebateDetails,
			imtdItemPriceRebateDetailses.get(
				newImtdItemPriceRebateDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ImtdItemPriceRebateDetails> imtdItemPriceRebateDetailses =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(imtdItemPriceRebateDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ImtdItemPriceRebateDetails newImtdItemPriceRebateDetails = addImtdItemPriceRebateDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newImtdItemPriceRebateDetails.getPrimaryKey());

		Map<Serializable, ImtdItemPriceRebateDetails> imtdItemPriceRebateDetailses =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, imtdItemPriceRebateDetailses.size());
		Assert.assertEquals(newImtdItemPriceRebateDetails,
			imtdItemPriceRebateDetailses.get(
				newImtdItemPriceRebateDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ImtdItemPriceRebateDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ImtdItemPriceRebateDetails>() {
				@Override
				public void performAction(
					ImtdItemPriceRebateDetails imtdItemPriceRebateDetails) {
					Assert.assertNotNull(imtdItemPriceRebateDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ImtdItemPriceRebateDetails newImtdItemPriceRebateDetails = addImtdItemPriceRebateDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdItemPriceRebateDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("imtdItemPriceRebateSid",
				newImtdItemPriceRebateDetails.getImtdItemPriceRebateSid()));

		List<ImtdItemPriceRebateDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ImtdItemPriceRebateDetails existingImtdItemPriceRebateDetails = result.get(0);

		Assert.assertEquals(existingImtdItemPriceRebateDetails,
			newImtdItemPriceRebateDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdItemPriceRebateDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("imtdItemPriceRebateSid",
				RandomTestUtil.nextInt()));

		List<ImtdItemPriceRebateDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ImtdItemPriceRebateDetails newImtdItemPriceRebateDetails = addImtdItemPriceRebateDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdItemPriceRebateDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"imtdItemPriceRebateSid"));

		Object newImtdItemPriceRebateSid = newImtdItemPriceRebateDetails.getImtdItemPriceRebateSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("imtdItemPriceRebateSid",
				new Object[] { newImtdItemPriceRebateSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingImtdItemPriceRebateSid = result.get(0);

		Assert.assertEquals(existingImtdItemPriceRebateSid,
			newImtdItemPriceRebateSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdItemPriceRebateDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"imtdItemPriceRebateSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("imtdItemPriceRebateSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ImtdItemPriceRebateDetails addImtdItemPriceRebateDetails()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		ImtdItemPriceRebateDetails imtdItemPriceRebateDetails = _persistence.create(pk);

		imtdItemPriceRebateDetails.setFormulaMethodId(RandomTestUtil.randomString());

		imtdItemPriceRebateDetails.setEndDate(RandomTestUtil.nextDate());

		imtdItemPriceRebateDetails.setPriceProtectionStartDate(RandomTestUtil.nextDate());

		imtdItemPriceRebateDetails.setItemPriceRevisionDate(RandomTestUtil.nextDate());

		imtdItemPriceRebateDetails.setModifiedDate(RandomTestUtil.nextDate());

		imtdItemPriceRebateDetails.setRsCheckRecord(RandomTestUtil.randomBoolean());

		imtdItemPriceRebateDetails.setRebateRevisionDate(RandomTestUtil.nextDate());

		imtdItemPriceRebateDetails.setContractMasterSid(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setImtdCreatedDate(RandomTestUtil.nextDate());

		imtdItemPriceRebateDetails.setModifiedBy(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setUdc6(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setUdc5(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setUdc4(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setCheckRecord(RandomTestUtil.randomBoolean());

		imtdItemPriceRebateDetails.setUdc1(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setUdc2(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setUdc3(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setContractPriceEndDate(RandomTestUtil.nextDate());

		imtdItemPriceRebateDetails.setTotalVolumeCommitment(RandomTestUtil.nextDouble());

		imtdItemPriceRebateDetails.setPriceProtectionEndDate(RandomTestUtil.nextDate());

		imtdItemPriceRebateDetails.setRecordLockStatus(RandomTestUtil.randomString());

		imtdItemPriceRebateDetails.setStartDate(RandomTestUtil.nextDate());

		imtdItemPriceRebateDetails.setRebateProgramType(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setSessionId(RandomTestUtil.randomString());

		imtdItemPriceRebateDetails.setItemName(RandomTestUtil.randomString());

		imtdItemPriceRebateDetails.setPriceRevision(RandomTestUtil.nextDouble());

		imtdItemPriceRebateDetails.setRsModelSid(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setPrice(RandomTestUtil.nextDouble());

		imtdItemPriceRebateDetails.setRsAttachedStatus(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setItemMasterSid(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setTotalDollarCommitment(RandomTestUtil.nextDouble());

		imtdItemPriceRebateDetails.setItemType(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setTotalMarketShareCommitmnet(RandomTestUtil.nextDouble());

		imtdItemPriceRebateDetails.setItemId(RandomTestUtil.randomString());

		imtdItemPriceRebateDetails.setBasePrice(RandomTestUtil.nextDouble());

		imtdItemPriceRebateDetails.setBundleNo(RandomTestUtil.randomString());

		imtdItemPriceRebateDetails.setFormulaName(RandomTestUtil.randomString());

		imtdItemPriceRebateDetails.setPsStatus(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setPriceTolerance(RandomTestUtil.nextDouble());

		imtdItemPriceRebateDetails.setCreatedDate(RandomTestUtil.nextDate());

		imtdItemPriceRebateDetails.setCreatedBy(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setUsersSid(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setPsDetailsSid(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setSuggestedPrice(RandomTestUtil.nextDouble());

		imtdItemPriceRebateDetails.setPsModelSid(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setFormulaId(RandomTestUtil.randomString());

		imtdItemPriceRebateDetails.setCommitmentPeriod(RandomTestUtil.randomString());

		imtdItemPriceRebateDetails.setItemNo(RandomTestUtil.randomString());

		imtdItemPriceRebateDetails.setContractPrice(RandomTestUtil.nextDouble());

		imtdItemPriceRebateDetails.setIfpDetailsSid(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setIfpModelSid(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setPriceToleranceType(RandomTestUtil.randomString());

		imtdItemPriceRebateDetails.setRebateAmount(RandomTestUtil.nextDouble());

		imtdItemPriceRebateDetails.setContractPriceStartDate(RandomTestUtil.nextDate());

		imtdItemPriceRebateDetails.setRebateScheduleType(RandomTestUtil.randomString());

		imtdItemPriceRebateDetails.setPriceToleranceFrequency(RandomTestUtil.randomString());

		imtdItemPriceRebateDetails.setRebatePlanSystemId(RandomTestUtil.randomString());

		imtdItemPriceRebateDetails.setAttachedDate(RandomTestUtil.nextDate());

		imtdItemPriceRebateDetails.setPricePlanId(RandomTestUtil.randomString());

		imtdItemPriceRebateDetails.setItemRebateEndDate(RandomTestUtil.nextDate());

		imtdItemPriceRebateDetails.setPriceType(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setPriceToleranceInterval(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setRsAttachedDate(RandomTestUtil.nextDate());

		imtdItemPriceRebateDetails.setItemRebateStartDate(RandomTestUtil.nextDate());

		imtdItemPriceRebateDetails.setOperation(RandomTestUtil.randomString());

		imtdItemPriceRebateDetails.setCfpModelSid(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setRsDetailsSid(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setAttachedStatus(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setPrimaryUom(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setPackageSize(RandomTestUtil.randomString());

		imtdItemPriceRebateDetails.setDeductionCalendarMasterSid(RandomTestUtil.randomString());

		imtdItemPriceRebateDetails.setRsContractDetailsDeductionCalendarNo(RandomTestUtil.randomString());

		imtdItemPriceRebateDetails.setRsContractDetailsDeductionCalendarName(RandomTestUtil.randomString());

		imtdItemPriceRebateDetails.setNetSalesFormulaMasterSid(RandomTestUtil.randomString());

		imtdItemPriceRebateDetails.setRsContractDetailsNetSalesFormulaNo(RandomTestUtil.randomString());

		imtdItemPriceRebateDetails.setRsContractDetailsNetSalesFormulaName(RandomTestUtil.randomString());

		imtdItemPriceRebateDetails.setFormulaType(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setNetSalesRule(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setEvaluationRule(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setEvaluationRuleBundle(RandomTestUtil.randomString());

		imtdItemPriceRebateDetails.setCalculationRule(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setCalculationRuleBundle(RandomTestUtil.randomString());

		imtdItemPriceRebateDetails.setMaxIncrementalChange(RandomTestUtil.nextDouble());

		imtdItemPriceRebateDetails.setResetEligible(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setResetType(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setResetDate(RandomTestUtil.nextDate());

		imtdItemPriceRebateDetails.setResetInterval(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setResetFrequency(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setNetPriceType(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setNetPriceTypeFormula(RandomTestUtil.randomString());

		imtdItemPriceRebateDetails.setPriceProtectionPriceType(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setNep(RandomTestUtil.nextDouble());

		imtdItemPriceRebateDetails.setNepFormula(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setBrandMasterSid(RandomTestUtil.randomString());

		imtdItemPriceRebateDetails.setPriceProtectionStatus(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setBasePriceType(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setBasePriceEntry(RandomTestUtil.nextDouble());

		imtdItemPriceRebateDetails.setBasePriceDate(RandomTestUtil.nextDate());

		imtdItemPriceRebateDetails.setBasePriceDdlb(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setNetBasePrice(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setSubsequentPeriodPriceType(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setResetPriceType(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setNetResetPriceType(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setNetResetPriceFormulaId(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setNetBasePriceFormulaId(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setNetSubsequentPriceFormulaId(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setNetSubsequentPeriodPrice(RandomTestUtil.nextInt());

		imtdItemPriceRebateDetails.setRsContractDetailsRebatePlanName(RandomTestUtil.randomString());

		imtdItemPriceRebateDetails.setRsContractDetailsFormulaNo(RandomTestUtil.randomString());

		imtdItemPriceRebateDetails.setSource(RandomTestUtil.randomString());

		_imtdItemPriceRebateDetailses.add(_persistence.update(
				imtdItemPriceRebateDetails));

		return imtdItemPriceRebateDetails;
	}

	private List<ImtdItemPriceRebateDetails> _imtdItemPriceRebateDetailses = new ArrayList<ImtdItemPriceRebateDetails>();
	private ImtdItemPriceRebateDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}