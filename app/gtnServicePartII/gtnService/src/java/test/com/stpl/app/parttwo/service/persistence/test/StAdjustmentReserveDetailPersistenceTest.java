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

import com.stpl.app.parttwo.exception.NoSuchStAdjustmentReserveDetailException;
import com.stpl.app.parttwo.model.StAdjustmentReserveDetail;
import com.stpl.app.parttwo.service.persistence.StAdjustmentReserveDetailPersistence;
import com.stpl.app.parttwo.service.persistence.StAdjustmentReserveDetailUtil;

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
public class StAdjustmentReserveDetailPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = StAdjustmentReserveDetailUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<StAdjustmentReserveDetail> iterator = _stAdjustmentReserveDetails.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		String pk = RandomTestUtil.randomString();

		StAdjustmentReserveDetail stAdjustmentReserveDetail = _persistence.create(pk);

		Assert.assertNotNull(stAdjustmentReserveDetail);

		Assert.assertEquals(stAdjustmentReserveDetail.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		StAdjustmentReserveDetail newStAdjustmentReserveDetail = addStAdjustmentReserveDetail();

		_persistence.remove(newStAdjustmentReserveDetail);

		StAdjustmentReserveDetail existingStAdjustmentReserveDetail = _persistence.fetchByPrimaryKey(newStAdjustmentReserveDetail.getPrimaryKey());

		Assert.assertNull(existingStAdjustmentReserveDetail);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addStAdjustmentReserveDetail();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		String pk = RandomTestUtil.randomString();

		StAdjustmentReserveDetail newStAdjustmentReserveDetail = _persistence.create(pk);

		newStAdjustmentReserveDetail.setAdjustmentCreatedDate(RandomTestUtil.nextDate());

		newStAdjustmentReserveDetail.setEtlCheckRecord(RandomTestUtil.randomBoolean());

		newStAdjustmentReserveDetail.setPostingIndicator(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setModifiedDate(RandomTestUtil.nextDate());

		newStAdjustmentReserveDetail.setAccount(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setCredit(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setWorkflowApprovedDate(RandomTestUtil.nextDate());

		newStAdjustmentReserveDetail.setSource(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setLineDescription(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setLedger(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setUdc6(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setUdc5(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setUdc4(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setWorkflowCreatedDate(RandomTestUtil.nextDate());

		newStAdjustmentReserveDetail.setUdc3(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setUdc2(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setUdc1(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setAdjustmentType(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setModifiedBy(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setCheckRecord(RandomTestUtil.randomBoolean());

		newStAdjustmentReserveDetail.setGlCompanyName(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setDivision(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setBalanceType(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setSessionId(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setJournalName(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setProject(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setDebit(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setAccountType(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setJournalDescription(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setCategory(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setCreatedBy(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setCreatedDate(RandomTestUtil.nextDate());

		newStAdjustmentReserveDetail.setBusinessUnitId(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setReversalPeriodDate(RandomTestUtil.nextDate());

		newStAdjustmentReserveDetail.setChartOfAccounts(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setUserId(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setBatchName(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setDatabase(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setCostCenter(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setOutboundStatus(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setDataAccessSet(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setFuture1(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setFuture2(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setWorkflowName(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setWorkflowCreatedBy(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setCurrency(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setBatchId(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setAccountCategory(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setReverseJournal(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setWorkflowApprovedBy(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setBrand(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setAccountingDate(RandomTestUtil.nextDate());

		newStAdjustmentReserveDetail.setRedemptionPeriod(RandomTestUtil.nextDate());

		newStAdjustmentReserveDetail.setOriginalBatchId(RandomTestUtil.randomString());

		newStAdjustmentReserveDetail.setAdjustmentLevel(RandomTestUtil.randomString());

		_stAdjustmentReserveDetails.add(_persistence.update(
				newStAdjustmentReserveDetail));

		StAdjustmentReserveDetail existingStAdjustmentReserveDetail = _persistence.findByPrimaryKey(newStAdjustmentReserveDetail.getPrimaryKey());

		Assert.assertEquals(Time.getShortTimestamp(
				existingStAdjustmentReserveDetail.getAdjustmentCreatedDate()),
			Time.getShortTimestamp(
				newStAdjustmentReserveDetail.getAdjustmentCreatedDate()));
		Assert.assertEquals(existingStAdjustmentReserveDetail.getEtlCheckRecord(),
			newStAdjustmentReserveDetail.getEtlCheckRecord());
		Assert.assertEquals(existingStAdjustmentReserveDetail.getPostingIndicator(),
			newStAdjustmentReserveDetail.getPostingIndicator());
		Assert.assertEquals(Time.getShortTimestamp(
				existingStAdjustmentReserveDetail.getModifiedDate()),
			Time.getShortTimestamp(
				newStAdjustmentReserveDetail.getModifiedDate()));
		Assert.assertEquals(existingStAdjustmentReserveDetail.getAccount(),
			newStAdjustmentReserveDetail.getAccount());
		Assert.assertEquals(existingStAdjustmentReserveDetail.getCredit(),
			newStAdjustmentReserveDetail.getCredit());
		Assert.assertEquals(Time.getShortTimestamp(
				existingStAdjustmentReserveDetail.getWorkflowApprovedDate()),
			Time.getShortTimestamp(
				newStAdjustmentReserveDetail.getWorkflowApprovedDate()));
		Assert.assertEquals(existingStAdjustmentReserveDetail.getSource(),
			newStAdjustmentReserveDetail.getSource());
		Assert.assertEquals(existingStAdjustmentReserveDetail.getLineDescription(),
			newStAdjustmentReserveDetail.getLineDescription());
		Assert.assertEquals(existingStAdjustmentReserveDetail.getLedger(),
			newStAdjustmentReserveDetail.getLedger());
		Assert.assertEquals(existingStAdjustmentReserveDetail.getUdc6(),
			newStAdjustmentReserveDetail.getUdc6());
		Assert.assertEquals(existingStAdjustmentReserveDetail.getUdc5(),
			newStAdjustmentReserveDetail.getUdc5());
		Assert.assertEquals(existingStAdjustmentReserveDetail.getUdc4(),
			newStAdjustmentReserveDetail.getUdc4());
		Assert.assertEquals(Time.getShortTimestamp(
				existingStAdjustmentReserveDetail.getWorkflowCreatedDate()),
			Time.getShortTimestamp(
				newStAdjustmentReserveDetail.getWorkflowCreatedDate()));
		Assert.assertEquals(existingStAdjustmentReserveDetail.getUdc3(),
			newStAdjustmentReserveDetail.getUdc3());
		Assert.assertEquals(existingStAdjustmentReserveDetail.getUdc2(),
			newStAdjustmentReserveDetail.getUdc2());
		Assert.assertEquals(existingStAdjustmentReserveDetail.getUdc1(),
			newStAdjustmentReserveDetail.getUdc1());
		Assert.assertEquals(existingStAdjustmentReserveDetail.getAdjustmentType(),
			newStAdjustmentReserveDetail.getAdjustmentType());
		Assert.assertEquals(existingStAdjustmentReserveDetail.getModifiedBy(),
			newStAdjustmentReserveDetail.getModifiedBy());
		Assert.assertEquals(existingStAdjustmentReserveDetail.getCheckRecord(),
			newStAdjustmentReserveDetail.getCheckRecord());
		Assert.assertEquals(existingStAdjustmentReserveDetail.getGlCompanyName(),
			newStAdjustmentReserveDetail.getGlCompanyName());
		Assert.assertEquals(existingStAdjustmentReserveDetail.getDivision(),
			newStAdjustmentReserveDetail.getDivision());
		Assert.assertEquals(existingStAdjustmentReserveDetail.getBalanceType(),
			newStAdjustmentReserveDetail.getBalanceType());
		Assert.assertEquals(existingStAdjustmentReserveDetail.getSessionId(),
			newStAdjustmentReserveDetail.getSessionId());
		Assert.assertEquals(existingStAdjustmentReserveDetail.getJournalName(),
			newStAdjustmentReserveDetail.getJournalName());
		Assert.assertEquals(existingStAdjustmentReserveDetail.getProject(),
			newStAdjustmentReserveDetail.getProject());
		Assert.assertEquals(existingStAdjustmentReserveDetail.getDebit(),
			newStAdjustmentReserveDetail.getDebit());
		Assert.assertEquals(existingStAdjustmentReserveDetail.getAccountType(),
			newStAdjustmentReserveDetail.getAccountType());
		Assert.assertEquals(existingStAdjustmentReserveDetail.getJournalDescription(),
			newStAdjustmentReserveDetail.getJournalDescription());
		Assert.assertEquals(existingStAdjustmentReserveDetail.getCategory(),
			newStAdjustmentReserveDetail.getCategory());
		Assert.assertEquals(existingStAdjustmentReserveDetail.getCreatedBy(),
			newStAdjustmentReserveDetail.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingStAdjustmentReserveDetail.getCreatedDate()),
			Time.getShortTimestamp(
				newStAdjustmentReserveDetail.getCreatedDate()));
		Assert.assertEquals(existingStAdjustmentReserveDetail.getBusinessUnitId(),
			newStAdjustmentReserveDetail.getBusinessUnitId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingStAdjustmentReserveDetail.getReversalPeriodDate()),
			Time.getShortTimestamp(
				newStAdjustmentReserveDetail.getReversalPeriodDate()));
		Assert.assertEquals(existingStAdjustmentReserveDetail.getWorkflowId(),
			newStAdjustmentReserveDetail.getWorkflowId());
		Assert.assertEquals(existingStAdjustmentReserveDetail.getChartOfAccounts(),
			newStAdjustmentReserveDetail.getChartOfAccounts());
		Assert.assertEquals(existingStAdjustmentReserveDetail.getUserId(),
			newStAdjustmentReserveDetail.getUserId());
		Assert.assertEquals(existingStAdjustmentReserveDetail.getBatchName(),
			newStAdjustmentReserveDetail.getBatchName());
		Assert.assertEquals(existingStAdjustmentReserveDetail.getDatabase(),
			newStAdjustmentReserveDetail.getDatabase());
		Assert.assertEquals(existingStAdjustmentReserveDetail.getCostCenter(),
			newStAdjustmentReserveDetail.getCostCenter());
		Assert.assertEquals(existingStAdjustmentReserveDetail.getOutboundStatus(),
			newStAdjustmentReserveDetail.getOutboundStatus());
		Assert.assertEquals(existingStAdjustmentReserveDetail.getDataAccessSet(),
			newStAdjustmentReserveDetail.getDataAccessSet());
		Assert.assertEquals(existingStAdjustmentReserveDetail.getFuture1(),
			newStAdjustmentReserveDetail.getFuture1());
		Assert.assertEquals(existingStAdjustmentReserveDetail.getFuture2(),
			newStAdjustmentReserveDetail.getFuture2());
		Assert.assertEquals(existingStAdjustmentReserveDetail.getWorkflowName(),
			newStAdjustmentReserveDetail.getWorkflowName());
		Assert.assertEquals(existingStAdjustmentReserveDetail.getWorkflowCreatedBy(),
			newStAdjustmentReserveDetail.getWorkflowCreatedBy());
		Assert.assertEquals(existingStAdjustmentReserveDetail.getCurrency(),
			newStAdjustmentReserveDetail.getCurrency());
		Assert.assertEquals(existingStAdjustmentReserveDetail.getBatchId(),
			newStAdjustmentReserveDetail.getBatchId());
		Assert.assertEquals(existingStAdjustmentReserveDetail.getAccountCategory(),
			newStAdjustmentReserveDetail.getAccountCategory());
		Assert.assertEquals(existingStAdjustmentReserveDetail.getReverseJournal(),
			newStAdjustmentReserveDetail.getReverseJournal());
		Assert.assertEquals(existingStAdjustmentReserveDetail.getWorkflowApprovedBy(),
			newStAdjustmentReserveDetail.getWorkflowApprovedBy());
		Assert.assertEquals(existingStAdjustmentReserveDetail.getBrand(),
			newStAdjustmentReserveDetail.getBrand());
		Assert.assertEquals(Time.getShortTimestamp(
				existingStAdjustmentReserveDetail.getAccountingDate()),
			Time.getShortTimestamp(
				newStAdjustmentReserveDetail.getAccountingDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingStAdjustmentReserveDetail.getRedemptionPeriod()),
			Time.getShortTimestamp(
				newStAdjustmentReserveDetail.getRedemptionPeriod()));
		Assert.assertEquals(existingStAdjustmentReserveDetail.getOriginalBatchId(),
			newStAdjustmentReserveDetail.getOriginalBatchId());
		Assert.assertEquals(existingStAdjustmentReserveDetail.getAdjustmentLevel(),
			newStAdjustmentReserveDetail.getAdjustmentLevel());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		StAdjustmentReserveDetail newStAdjustmentReserveDetail = addStAdjustmentReserveDetail();

		StAdjustmentReserveDetail existingStAdjustmentReserveDetail = _persistence.findByPrimaryKey(newStAdjustmentReserveDetail.getPrimaryKey());

		Assert.assertEquals(existingStAdjustmentReserveDetail,
			newStAdjustmentReserveDetail);
	}

	@Test(expected = NoSuchStAdjustmentReserveDetailException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		String pk = RandomTestUtil.randomString();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<StAdjustmentReserveDetail> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("ST_ADJUSTMENT_RESERVE_DETAIL",
			"adjustmentCreatedDate", true, "etlCheckRecord", true,
			"postingIndicator", true, "modifiedDate", true, "account", true,
			"credit", true, "workflowApprovedDate", true, "source", true,
			"lineDescription", true, "ledger", true, "udc6", true, "udc5",
			true, "udc4", true, "workflowCreatedDate", true, "udc3", true,
			"udc2", true, "udc1", true, "adjustmentType", true, "modifiedBy",
			true, "checkRecord", true, "glCompanyName", true, "division", true,
			"balanceType", true, "sessionId", true, "journalName", true,
			"project", true, "debit", true, "accountType", true,
			"journalDescription", true, "category", true, "createdBy", true,
			"createdDate", true, "businessUnitId", true, "reversalPeriodDate",
			true, "workflowId", true, "chartOfAccounts", true, "userId", true,
			"batchName", true, "database", true, "costCenter", true,
			"outboundStatus", true, "dataAccessSet", true, "future1", true,
			"future2", true, "workflowName", true, "workflowCreatedBy", true,
			"currency", true, "batchId", true, "accountCategory", true,
			"reverseJournal", true, "workflowApprovedBy", true, "brand", true,
			"accountingDate", true, "redemptionPeriod", true,
			"originalBatchId", true, "adjustmentLevel", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		StAdjustmentReserveDetail newStAdjustmentReserveDetail = addStAdjustmentReserveDetail();

		StAdjustmentReserveDetail existingStAdjustmentReserveDetail = _persistence.fetchByPrimaryKey(newStAdjustmentReserveDetail.getPrimaryKey());

		Assert.assertEquals(existingStAdjustmentReserveDetail,
			newStAdjustmentReserveDetail);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		String pk = RandomTestUtil.randomString();

		StAdjustmentReserveDetail missingStAdjustmentReserveDetail = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingStAdjustmentReserveDetail);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		StAdjustmentReserveDetail newStAdjustmentReserveDetail1 = addStAdjustmentReserveDetail();
		StAdjustmentReserveDetail newStAdjustmentReserveDetail2 = addStAdjustmentReserveDetail();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStAdjustmentReserveDetail1.getPrimaryKey());
		primaryKeys.add(newStAdjustmentReserveDetail2.getPrimaryKey());

		Map<Serializable, StAdjustmentReserveDetail> stAdjustmentReserveDetails = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, stAdjustmentReserveDetails.size());
		Assert.assertEquals(newStAdjustmentReserveDetail1,
			stAdjustmentReserveDetails.get(
				newStAdjustmentReserveDetail1.getPrimaryKey()));
		Assert.assertEquals(newStAdjustmentReserveDetail2,
			stAdjustmentReserveDetails.get(
				newStAdjustmentReserveDetail2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		String pk1 = RandomTestUtil.randomString();

		String pk2 = RandomTestUtil.randomString();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, StAdjustmentReserveDetail> stAdjustmentReserveDetails = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stAdjustmentReserveDetails.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		StAdjustmentReserveDetail newStAdjustmentReserveDetail = addStAdjustmentReserveDetail();

		String pk = RandomTestUtil.randomString();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStAdjustmentReserveDetail.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, StAdjustmentReserveDetail> stAdjustmentReserveDetails = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stAdjustmentReserveDetails.size());
		Assert.assertEquals(newStAdjustmentReserveDetail,
			stAdjustmentReserveDetails.get(
				newStAdjustmentReserveDetail.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, StAdjustmentReserveDetail> stAdjustmentReserveDetails = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stAdjustmentReserveDetails.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		StAdjustmentReserveDetail newStAdjustmentReserveDetail = addStAdjustmentReserveDetail();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStAdjustmentReserveDetail.getPrimaryKey());

		Map<Serializable, StAdjustmentReserveDetail> stAdjustmentReserveDetails = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stAdjustmentReserveDetails.size());
		Assert.assertEquals(newStAdjustmentReserveDetail,
			stAdjustmentReserveDetails.get(
				newStAdjustmentReserveDetail.getPrimaryKey()));
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		StAdjustmentReserveDetail newStAdjustmentReserveDetail = addStAdjustmentReserveDetail();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StAdjustmentReserveDetail.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("workflowId",
				newStAdjustmentReserveDetail.getWorkflowId()));

		List<StAdjustmentReserveDetail> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		StAdjustmentReserveDetail existingStAdjustmentReserveDetail = result.get(0);

		Assert.assertEquals(existingStAdjustmentReserveDetail,
			newStAdjustmentReserveDetail);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StAdjustmentReserveDetail.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("workflowId",
				RandomTestUtil.randomString()));

		List<StAdjustmentReserveDetail> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		StAdjustmentReserveDetail newStAdjustmentReserveDetail = addStAdjustmentReserveDetail();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StAdjustmentReserveDetail.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("workflowId"));

		Object newWorkflowId = newStAdjustmentReserveDetail.getWorkflowId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("workflowId",
				new Object[] { newWorkflowId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingWorkflowId = result.get(0);

		Assert.assertEquals(existingWorkflowId, newWorkflowId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StAdjustmentReserveDetail.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("workflowId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("workflowId",
				new Object[] { RandomTestUtil.randomString() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected StAdjustmentReserveDetail addStAdjustmentReserveDetail()
		throws Exception {
		String pk = RandomTestUtil.randomString();

		StAdjustmentReserveDetail stAdjustmentReserveDetail = _persistence.create(pk);

		stAdjustmentReserveDetail.setAdjustmentCreatedDate(RandomTestUtil.nextDate());

		stAdjustmentReserveDetail.setEtlCheckRecord(RandomTestUtil.randomBoolean());

		stAdjustmentReserveDetail.setPostingIndicator(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setModifiedDate(RandomTestUtil.nextDate());

		stAdjustmentReserveDetail.setAccount(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setCredit(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setWorkflowApprovedDate(RandomTestUtil.nextDate());

		stAdjustmentReserveDetail.setSource(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setLineDescription(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setLedger(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setUdc6(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setUdc5(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setUdc4(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setWorkflowCreatedDate(RandomTestUtil.nextDate());

		stAdjustmentReserveDetail.setUdc3(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setUdc2(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setUdc1(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setAdjustmentType(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setModifiedBy(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setCheckRecord(RandomTestUtil.randomBoolean());

		stAdjustmentReserveDetail.setGlCompanyName(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setDivision(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setBalanceType(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setSessionId(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setJournalName(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setProject(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setDebit(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setAccountType(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setJournalDescription(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setCategory(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setCreatedBy(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setCreatedDate(RandomTestUtil.nextDate());

		stAdjustmentReserveDetail.setBusinessUnitId(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setReversalPeriodDate(RandomTestUtil.nextDate());

		stAdjustmentReserveDetail.setChartOfAccounts(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setUserId(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setBatchName(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setDatabase(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setCostCenter(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setOutboundStatus(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setDataAccessSet(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setFuture1(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setFuture2(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setWorkflowName(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setWorkflowCreatedBy(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setCurrency(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setBatchId(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setAccountCategory(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setReverseJournal(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setWorkflowApprovedBy(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setBrand(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setAccountingDate(RandomTestUtil.nextDate());

		stAdjustmentReserveDetail.setRedemptionPeriod(RandomTestUtil.nextDate());

		stAdjustmentReserveDetail.setOriginalBatchId(RandomTestUtil.randomString());

		stAdjustmentReserveDetail.setAdjustmentLevel(RandomTestUtil.randomString());

		_stAdjustmentReserveDetails.add(_persistence.update(
				stAdjustmentReserveDetail));

		return stAdjustmentReserveDetail;
	}

	private List<StAdjustmentReserveDetail> _stAdjustmentReserveDetails = new ArrayList<StAdjustmentReserveDetail>();
	private StAdjustmentReserveDetailPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}