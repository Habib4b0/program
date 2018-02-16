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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.parttwo.exception.NoSuchStAdjustmentGtnDetailException;
import com.stpl.app.parttwo.model.StAdjustmentGtnDetail;
import com.stpl.app.parttwo.service.persistence.StAdjustmentGtnDetailPersistence;
import com.stpl.app.parttwo.service.persistence.StAdjustmentGtnDetailUtil;

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
public class StAdjustmentGtnDetailPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = StAdjustmentGtnDetailUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<StAdjustmentGtnDetail> iterator = _stAdjustmentGtnDetails.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		String pk = RandomTestUtil.randomString();

		StAdjustmentGtnDetail stAdjustmentGtnDetail = _persistence.create(pk);

		Assert.assertNotNull(stAdjustmentGtnDetail);

		Assert.assertEquals(stAdjustmentGtnDetail.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		StAdjustmentGtnDetail newStAdjustmentGtnDetail = addStAdjustmentGtnDetail();

		_persistence.remove(newStAdjustmentGtnDetail);

		StAdjustmentGtnDetail existingStAdjustmentGtnDetail = _persistence.fetchByPrimaryKey(newStAdjustmentGtnDetail.getPrimaryKey());

		Assert.assertNull(existingStAdjustmentGtnDetail);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addStAdjustmentGtnDetail();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		String pk = RandomTestUtil.randomString();

		StAdjustmentGtnDetail newStAdjustmentGtnDetail = _persistence.create(pk);

		newStAdjustmentGtnDetail.setAdjustmentCreatedDate(RandomTestUtil.nextDate());

		newStAdjustmentGtnDetail.setEtlCheckRecord(RandomTestUtil.randomBoolean());

		newStAdjustmentGtnDetail.setBusinessUnitNo(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setRedemptionPeriod(RandomTestUtil.nextDate());

		newStAdjustmentGtnDetail.setDeductionId(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setGlYear(RandomTestUtil.nextInt());

		newStAdjustmentGtnDetail.setBrandName(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setModifiedDate(RandomTestUtil.nextDate());

		newStAdjustmentGtnDetail.setAccount(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setSource(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setWorkflowApprovedDate(RandomTestUtil.nextDate());

		newStAdjustmentGtnDetail.setUdc6(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setBusinessUnitName(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setUdc5(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setWorkflowCreatedDate(RandomTestUtil.nextDate());

		newStAdjustmentGtnDetail.setUdc4(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setUdc3(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setUdc2(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setUdc1(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setAdjustmentType(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setModifiedBy(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setDeductionType(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setCheckRecord(RandomTestUtil.randomBoolean());

		newStAdjustmentGtnDetail.setContractName(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setDeductionRate(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setDeductionCategory(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setDeductionNo(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setCompanyNo(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setSessionId(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setGlcompanyId(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setItemName(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setDeductionInclusion(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setDeductionAmount(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setCompanyName(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setProject(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setDeductionUdc3(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setDeductionUdc4(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setDeductionUdc1(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setItemId(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setDeductionUdc2(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setAccountType(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setGlString(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setCreatedDate(RandomTestUtil.nextDate());

		newStAdjustmentGtnDetail.setCreatedBy(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setDeductionUdc6(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setDeductionUdc5(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setGlCompanyName(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setItemNo(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setContractId(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setDeductionProgram(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setBusinessUnitId(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setUserId(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setCostCenter(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setCompanyIdString(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setOutboundStatus(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setFuture1(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setBrandId(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setDeductionName(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setFuture2(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setWorkflowName(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setGlDate(RandomTestUtil.nextDate());

		newStAdjustmentGtnDetail.setWorkflowCreatedBy(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setGlMonth(RandomTestUtil.nextInt());

		newStAdjustmentGtnDetail.setBatchId(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setAccountCategory(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setGlCompanyNo(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setWorkflowApprovedBy(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setContractNo(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setOriginalBatchId(RandomTestUtil.randomString());

		newStAdjustmentGtnDetail.setAdjustmentLevel(RandomTestUtil.randomString());

		_stAdjustmentGtnDetails.add(_persistence.update(
				newStAdjustmentGtnDetail));

		StAdjustmentGtnDetail existingStAdjustmentGtnDetail = _persistence.findByPrimaryKey(newStAdjustmentGtnDetail.getPrimaryKey());

		Assert.assertEquals(Time.getShortTimestamp(
				existingStAdjustmentGtnDetail.getAdjustmentCreatedDate()),
			Time.getShortTimestamp(
				newStAdjustmentGtnDetail.getAdjustmentCreatedDate()));
		Assert.assertEquals(existingStAdjustmentGtnDetail.getEtlCheckRecord(),
			newStAdjustmentGtnDetail.getEtlCheckRecord());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getBusinessUnitNo(),
			newStAdjustmentGtnDetail.getBusinessUnitNo());
		Assert.assertEquals(Time.getShortTimestamp(
				existingStAdjustmentGtnDetail.getRedemptionPeriod()),
			Time.getShortTimestamp(
				newStAdjustmentGtnDetail.getRedemptionPeriod()));
		Assert.assertEquals(existingStAdjustmentGtnDetail.getDeductionId(),
			newStAdjustmentGtnDetail.getDeductionId());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getGlYear(),
			newStAdjustmentGtnDetail.getGlYear());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getBrandName(),
			newStAdjustmentGtnDetail.getBrandName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingStAdjustmentGtnDetail.getModifiedDate()),
			Time.getShortTimestamp(newStAdjustmentGtnDetail.getModifiedDate()));
		Assert.assertEquals(existingStAdjustmentGtnDetail.getAccount(),
			newStAdjustmentGtnDetail.getAccount());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getSource(),
			newStAdjustmentGtnDetail.getSource());
		Assert.assertEquals(Time.getShortTimestamp(
				existingStAdjustmentGtnDetail.getWorkflowApprovedDate()),
			Time.getShortTimestamp(
				newStAdjustmentGtnDetail.getWorkflowApprovedDate()));
		Assert.assertEquals(existingStAdjustmentGtnDetail.getUdc6(),
			newStAdjustmentGtnDetail.getUdc6());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getBusinessUnitName(),
			newStAdjustmentGtnDetail.getBusinessUnitName());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getUdc5(),
			newStAdjustmentGtnDetail.getUdc5());
		Assert.assertEquals(Time.getShortTimestamp(
				existingStAdjustmentGtnDetail.getWorkflowCreatedDate()),
			Time.getShortTimestamp(
				newStAdjustmentGtnDetail.getWorkflowCreatedDate()));
		Assert.assertEquals(existingStAdjustmentGtnDetail.getUdc4(),
			newStAdjustmentGtnDetail.getUdc4());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getUdc3(),
			newStAdjustmentGtnDetail.getUdc3());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getUdc2(),
			newStAdjustmentGtnDetail.getUdc2());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getUdc1(),
			newStAdjustmentGtnDetail.getUdc1());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getAdjustmentType(),
			newStAdjustmentGtnDetail.getAdjustmentType());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getModifiedBy(),
			newStAdjustmentGtnDetail.getModifiedBy());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getDeductionType(),
			newStAdjustmentGtnDetail.getDeductionType());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getCheckRecord(),
			newStAdjustmentGtnDetail.getCheckRecord());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getContractName(),
			newStAdjustmentGtnDetail.getContractName());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getDeductionRate(),
			newStAdjustmentGtnDetail.getDeductionRate());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getDeductionCategory(),
			newStAdjustmentGtnDetail.getDeductionCategory());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getDeductionNo(),
			newStAdjustmentGtnDetail.getDeductionNo());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getCompanyNo(),
			newStAdjustmentGtnDetail.getCompanyNo());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getSessionId(),
			newStAdjustmentGtnDetail.getSessionId());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getGlcompanyId(),
			newStAdjustmentGtnDetail.getGlcompanyId());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getItemName(),
			newStAdjustmentGtnDetail.getItemName());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getDeductionInclusion(),
			newStAdjustmentGtnDetail.getDeductionInclusion());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getDeductionAmount(),
			newStAdjustmentGtnDetail.getDeductionAmount());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getCompanyName(),
			newStAdjustmentGtnDetail.getCompanyName());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getProject(),
			newStAdjustmentGtnDetail.getProject());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getDeductionUdc3(),
			newStAdjustmentGtnDetail.getDeductionUdc3());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getDeductionUdc4(),
			newStAdjustmentGtnDetail.getDeductionUdc4());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getDeductionUdc1(),
			newStAdjustmentGtnDetail.getDeductionUdc1());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getItemId(),
			newStAdjustmentGtnDetail.getItemId());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getDeductionUdc2(),
			newStAdjustmentGtnDetail.getDeductionUdc2());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getAccountType(),
			newStAdjustmentGtnDetail.getAccountType());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getGlString(),
			newStAdjustmentGtnDetail.getGlString());
		Assert.assertEquals(Time.getShortTimestamp(
				existingStAdjustmentGtnDetail.getCreatedDate()),
			Time.getShortTimestamp(newStAdjustmentGtnDetail.getCreatedDate()));
		Assert.assertEquals(existingStAdjustmentGtnDetail.getCreatedBy(),
			newStAdjustmentGtnDetail.getCreatedBy());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getDeductionUdc6(),
			newStAdjustmentGtnDetail.getDeductionUdc6());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getDeductionUdc5(),
			newStAdjustmentGtnDetail.getDeductionUdc5());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getGlCompanyName(),
			newStAdjustmentGtnDetail.getGlCompanyName());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getWorkflowId(),
			newStAdjustmentGtnDetail.getWorkflowId());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getItemNo(),
			newStAdjustmentGtnDetail.getItemNo());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getContractId(),
			newStAdjustmentGtnDetail.getContractId());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getDeductionProgram(),
			newStAdjustmentGtnDetail.getDeductionProgram());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getBusinessUnitId(),
			newStAdjustmentGtnDetail.getBusinessUnitId());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getUserId(),
			newStAdjustmentGtnDetail.getUserId());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getCostCenter(),
			newStAdjustmentGtnDetail.getCostCenter());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getCompanyIdString(),
			newStAdjustmentGtnDetail.getCompanyIdString());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getOutboundStatus(),
			newStAdjustmentGtnDetail.getOutboundStatus());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getFuture1(),
			newStAdjustmentGtnDetail.getFuture1());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getBrandId(),
			newStAdjustmentGtnDetail.getBrandId());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getDeductionName(),
			newStAdjustmentGtnDetail.getDeductionName());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getFuture2(),
			newStAdjustmentGtnDetail.getFuture2());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getWorkflowName(),
			newStAdjustmentGtnDetail.getWorkflowName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingStAdjustmentGtnDetail.getGlDate()),
			Time.getShortTimestamp(newStAdjustmentGtnDetail.getGlDate()));
		Assert.assertEquals(existingStAdjustmentGtnDetail.getWorkflowCreatedBy(),
			newStAdjustmentGtnDetail.getWorkflowCreatedBy());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getGlMonth(),
			newStAdjustmentGtnDetail.getGlMonth());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getBatchId(),
			newStAdjustmentGtnDetail.getBatchId());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getAccountCategory(),
			newStAdjustmentGtnDetail.getAccountCategory());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getGlCompanyNo(),
			newStAdjustmentGtnDetail.getGlCompanyNo());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getWorkflowApprovedBy(),
			newStAdjustmentGtnDetail.getWorkflowApprovedBy());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getContractNo(),
			newStAdjustmentGtnDetail.getContractNo());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getOriginalBatchId(),
			newStAdjustmentGtnDetail.getOriginalBatchId());
		Assert.assertEquals(existingStAdjustmentGtnDetail.getAdjustmentLevel(),
			newStAdjustmentGtnDetail.getAdjustmentLevel());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		StAdjustmentGtnDetail newStAdjustmentGtnDetail = addStAdjustmentGtnDetail();

		StAdjustmentGtnDetail existingStAdjustmentGtnDetail = _persistence.findByPrimaryKey(newStAdjustmentGtnDetail.getPrimaryKey());

		Assert.assertEquals(existingStAdjustmentGtnDetail,
			newStAdjustmentGtnDetail);
	}

	@Test(expected = NoSuchStAdjustmentGtnDetailException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		String pk = RandomTestUtil.randomString();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<StAdjustmentGtnDetail> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("ST_ADJUSTMENT_GTN_DETAIL",
			"adjustmentCreatedDate", true, "etlCheckRecord", true,
			"businessUnitNo", true, "redemptionPeriod", true, "deductionId",
			true, "glYear", true, "brandName", true, "modifiedDate", true,
			"account", true, "source", true, "workflowApprovedDate", true,
			"udc6", true, "businessUnitName", true, "udc5", true,
			"workflowCreatedDate", true, "udc4", true, "udc3", true, "udc2",
			true, "udc1", true, "adjustmentType", true, "modifiedBy", true,
			"deductionType", true, "checkRecord", true, "contractName", true,
			"deductionRate", true, "deductionCategory", true, "deductionNo",
			true, "companyNo", true, "sessionId", true, "glcompanyId", true,
			"itemName", true, "deductionInclusion", true, "deductionAmount",
			true, "companyName", true, "project", true, "deductionUdc3", true,
			"deductionUdc4", true, "deductionUdc1", true, "itemId", true,
			"deductionUdc2", true, "accountType", true, "glString", true,
			"createdDate", true, "createdBy", true, "deductionUdc6", true,
			"deductionUdc5", true, "glCompanyName", true, "workflowId", true,
			"itemNo", true, "contractId", true, "deductionProgram", true,
			"businessUnitId", true, "userId", true, "costCenter", true,
			"companyIdString", true, "outboundStatus", true, "future1", true,
			"brandId", true, "deductionName", true, "future2", true,
			"workflowName", true, "glDate", true, "workflowCreatedBy", true,
			"glMonth", true, "batchId", true, "accountCategory", true,
			"glCompanyNo", true, "workflowApprovedBy", true, "contractNo",
			true, "originalBatchId", true, "adjustmentLevel", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		StAdjustmentGtnDetail newStAdjustmentGtnDetail = addStAdjustmentGtnDetail();

		StAdjustmentGtnDetail existingStAdjustmentGtnDetail = _persistence.fetchByPrimaryKey(newStAdjustmentGtnDetail.getPrimaryKey());

		Assert.assertEquals(existingStAdjustmentGtnDetail,
			newStAdjustmentGtnDetail);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		String pk = RandomTestUtil.randomString();

		StAdjustmentGtnDetail missingStAdjustmentGtnDetail = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingStAdjustmentGtnDetail);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		StAdjustmentGtnDetail newStAdjustmentGtnDetail1 = addStAdjustmentGtnDetail();
		StAdjustmentGtnDetail newStAdjustmentGtnDetail2 = addStAdjustmentGtnDetail();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStAdjustmentGtnDetail1.getPrimaryKey());
		primaryKeys.add(newStAdjustmentGtnDetail2.getPrimaryKey());

		Map<Serializable, StAdjustmentGtnDetail> stAdjustmentGtnDetails = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, stAdjustmentGtnDetails.size());
		Assert.assertEquals(newStAdjustmentGtnDetail1,
			stAdjustmentGtnDetails.get(
				newStAdjustmentGtnDetail1.getPrimaryKey()));
		Assert.assertEquals(newStAdjustmentGtnDetail2,
			stAdjustmentGtnDetails.get(
				newStAdjustmentGtnDetail2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		String pk1 = RandomTestUtil.randomString();

		String pk2 = RandomTestUtil.randomString();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, StAdjustmentGtnDetail> stAdjustmentGtnDetails = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stAdjustmentGtnDetails.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		StAdjustmentGtnDetail newStAdjustmentGtnDetail = addStAdjustmentGtnDetail();

		String pk = RandomTestUtil.randomString();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStAdjustmentGtnDetail.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, StAdjustmentGtnDetail> stAdjustmentGtnDetails = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stAdjustmentGtnDetails.size());
		Assert.assertEquals(newStAdjustmentGtnDetail,
			stAdjustmentGtnDetails.get(newStAdjustmentGtnDetail.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, StAdjustmentGtnDetail> stAdjustmentGtnDetails = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stAdjustmentGtnDetails.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		StAdjustmentGtnDetail newStAdjustmentGtnDetail = addStAdjustmentGtnDetail();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStAdjustmentGtnDetail.getPrimaryKey());

		Map<Serializable, StAdjustmentGtnDetail> stAdjustmentGtnDetails = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stAdjustmentGtnDetails.size());
		Assert.assertEquals(newStAdjustmentGtnDetail,
			stAdjustmentGtnDetails.get(newStAdjustmentGtnDetail.getPrimaryKey()));
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		StAdjustmentGtnDetail newStAdjustmentGtnDetail = addStAdjustmentGtnDetail();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StAdjustmentGtnDetail.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("workflowId",
				newStAdjustmentGtnDetail.getWorkflowId()));

		List<StAdjustmentGtnDetail> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		StAdjustmentGtnDetail existingStAdjustmentGtnDetail = result.get(0);

		Assert.assertEquals(existingStAdjustmentGtnDetail,
			newStAdjustmentGtnDetail);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StAdjustmentGtnDetail.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("workflowId",
				RandomTestUtil.randomString()));

		List<StAdjustmentGtnDetail> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		StAdjustmentGtnDetail newStAdjustmentGtnDetail = addStAdjustmentGtnDetail();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StAdjustmentGtnDetail.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("workflowId"));

		Object newWorkflowId = newStAdjustmentGtnDetail.getWorkflowId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("workflowId",
				new Object[] { newWorkflowId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingWorkflowId = result.get(0);

		Assert.assertEquals(existingWorkflowId, newWorkflowId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StAdjustmentGtnDetail.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("workflowId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("workflowId",
				new Object[] { RandomTestUtil.randomString() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected StAdjustmentGtnDetail addStAdjustmentGtnDetail()
		throws Exception {
		String pk = RandomTestUtil.randomString();

		StAdjustmentGtnDetail stAdjustmentGtnDetail = _persistence.create(pk);

		stAdjustmentGtnDetail.setAdjustmentCreatedDate(RandomTestUtil.nextDate());

		stAdjustmentGtnDetail.setEtlCheckRecord(RandomTestUtil.randomBoolean());

		stAdjustmentGtnDetail.setBusinessUnitNo(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setRedemptionPeriod(RandomTestUtil.nextDate());

		stAdjustmentGtnDetail.setDeductionId(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setGlYear(RandomTestUtil.nextInt());

		stAdjustmentGtnDetail.setBrandName(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setModifiedDate(RandomTestUtil.nextDate());

		stAdjustmentGtnDetail.setAccount(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setSource(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setWorkflowApprovedDate(RandomTestUtil.nextDate());

		stAdjustmentGtnDetail.setUdc6(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setBusinessUnitName(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setUdc5(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setWorkflowCreatedDate(RandomTestUtil.nextDate());

		stAdjustmentGtnDetail.setUdc4(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setUdc3(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setUdc2(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setUdc1(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setAdjustmentType(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setModifiedBy(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setDeductionType(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setCheckRecord(RandomTestUtil.randomBoolean());

		stAdjustmentGtnDetail.setContractName(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setDeductionRate(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setDeductionCategory(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setDeductionNo(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setCompanyNo(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setSessionId(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setGlcompanyId(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setItemName(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setDeductionInclusion(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setDeductionAmount(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setCompanyName(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setProject(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setDeductionUdc3(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setDeductionUdc4(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setDeductionUdc1(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setItemId(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setDeductionUdc2(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setAccountType(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setGlString(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setCreatedDate(RandomTestUtil.nextDate());

		stAdjustmentGtnDetail.setCreatedBy(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setDeductionUdc6(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setDeductionUdc5(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setGlCompanyName(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setItemNo(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setContractId(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setDeductionProgram(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setBusinessUnitId(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setUserId(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setCostCenter(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setCompanyIdString(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setOutboundStatus(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setFuture1(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setBrandId(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setDeductionName(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setFuture2(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setWorkflowName(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setGlDate(RandomTestUtil.nextDate());

		stAdjustmentGtnDetail.setWorkflowCreatedBy(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setGlMonth(RandomTestUtil.nextInt());

		stAdjustmentGtnDetail.setBatchId(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setAccountCategory(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setGlCompanyNo(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setWorkflowApprovedBy(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setContractNo(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setOriginalBatchId(RandomTestUtil.randomString());

		stAdjustmentGtnDetail.setAdjustmentLevel(RandomTestUtil.randomString());

		_stAdjustmentGtnDetails.add(_persistence.update(stAdjustmentGtnDetail));

		return stAdjustmentGtnDetail;
	}

	private List<StAdjustmentGtnDetail> _stAdjustmentGtnDetails = new ArrayList<StAdjustmentGtnDetail>();
	private StAdjustmentGtnDetailPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}