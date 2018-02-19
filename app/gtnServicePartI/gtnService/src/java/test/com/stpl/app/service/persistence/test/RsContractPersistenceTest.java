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

import com.stpl.app.exception.NoSuchRsContractException;
import com.stpl.app.model.RsContract;
import com.stpl.app.service.RsContractLocalServiceUtil;
import com.stpl.app.service.persistence.RsContractPersistence;
import com.stpl.app.service.persistence.RsContractUtil;

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
public class RsContractPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = RsContractUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<RsContract> iterator = _rsContracts.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		RsContract rsContract = _persistence.create(pk);

		Assert.assertNotNull(rsContract);

		Assert.assertEquals(rsContract.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		RsContract newRsContract = addRsContract();

		_persistence.remove(newRsContract);

		RsContract existingRsContract = _persistence.fetchByPrimaryKey(newRsContract.getPrimaryKey());

		Assert.assertNull(existingRsContract);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addRsContract();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		RsContract newRsContract = _persistence.create(pk);

		newRsContract.setCfpContractSid(RandomTestUtil.randomString());

		newRsContract.setCreatedDate(RandomTestUtil.nextDate());

		newRsContract.setPsContractSid(RandomTestUtil.randomString());

		newRsContract.setRsName(RandomTestUtil.randomString());

		newRsContract.setRsStatus(RandomTestUtil.nextInt());

		newRsContract.setRsStartDate(RandomTestUtil.nextDate());

		newRsContract.setRsTransRefId(RandomTestUtil.randomString());

		newRsContract.setMakePayableTo(RandomTestUtil.randomString());

		newRsContract.setCreatedBy(RandomTestUtil.nextInt());

		newRsContract.setRsCategory(RandomTestUtil.nextInt());

		newRsContract.setRsTradeClass(RandomTestUtil.nextInt());

		newRsContract.setContractMasterSid(RandomTestUtil.nextInt());

		newRsContract.setRebateRuleType(RandomTestUtil.nextInt());

		newRsContract.setPaymentMethod(RandomTestUtil.nextInt());

		newRsContract.setRsContractAttachedDate(RandomTestUtil.nextDate());

		newRsContract.setRsAlias(RandomTestUtil.randomString());

		newRsContract.setFormulaMethodId(RandomTestUtil.randomString());

		newRsContract.setRebateProcessingType(RandomTestUtil.nextInt());

		newRsContract.setRsContractAttachedStatus(RandomTestUtil.nextInt());

		newRsContract.setInterestBearingBasis(RandomTestUtil.nextInt());

		newRsContract.setModifiedDate(RandomTestUtil.nextDate());

		newRsContract.setRsTransRefName(RandomTestUtil.randomString());

		newRsContract.setRebateProgramType(RandomTestUtil.nextInt());

		newRsContract.setRebatePlanLevel(RandomTestUtil.randomString());

		newRsContract.setSource(RandomTestUtil.randomString());

		newRsContract.setRsCalendar(RandomTestUtil.randomString());

		newRsContract.setRsType(RandomTestUtil.nextInt());

		newRsContract.setAddress1(RandomTestUtil.randomString());

		newRsContract.setAddress2(RandomTestUtil.randomString());

		newRsContract.setRsEndDate(RandomTestUtil.nextDate());

		newRsContract.setModifiedBy(RandomTestUtil.nextInt());

		newRsContract.setRsTransRefNo(RandomTestUtil.randomString());

		newRsContract.setZipCode(RandomTestUtil.randomString());

		newRsContract.setRebateRuleAssociation(RandomTestUtil.randomString());

		newRsContract.setState(RandomTestUtil.nextInt());

		newRsContract.setRebateFrequency(RandomTestUtil.nextInt());

		newRsContract.setRsDesignation(RandomTestUtil.randomString());

		newRsContract.setBatchId(RandomTestUtil.randomString());

		newRsContract.setIfpContractSid(RandomTestUtil.randomString());

		newRsContract.setPaymentTerms(RandomTestUtil.nextInt());

		newRsContract.setRsNo(RandomTestUtil.randomString());

		newRsContract.setRsModelSid(RandomTestUtil.nextInt());

		newRsContract.setRsValidationProfile(RandomTestUtil.nextInt());

		newRsContract.setPaymentGracePeriod(RandomTestUtil.randomString());

		newRsContract.setPaymentFrequency(RandomTestUtil.nextInt());

		newRsContract.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newRsContract.setRsId(RandomTestUtil.randomString());

		newRsContract.setCity(RandomTestUtil.randomString());

		newRsContract.setParentRsName(RandomTestUtil.randomString());

		newRsContract.setInterestBearingIndicator(RandomTestUtil.nextInt());

		newRsContract.setParentRsId(RandomTestUtil.randomString());

		newRsContract.setInboundStatus(RandomTestUtil.randomString());

		newRsContract.setCalculationType(RandomTestUtil.nextInt());

		newRsContract.setCalculationLevel(RandomTestUtil.nextInt());

		newRsContract.setCalculationRule(RandomTestUtil.randomString());

		newRsContract.setCalculationRuleLevel(RandomTestUtil.randomString());

		newRsContract.setEvaluationRuleType(RandomTestUtil.randomString());

		newRsContract.setEvaluationRuleLevel(RandomTestUtil.randomString());

		newRsContract.setEvaluationRuleOrAssociation(RandomTestUtil.randomString());

		newRsContract.setDeductionInclusion(RandomTestUtil.randomString());

		_rsContracts.add(_persistence.update(newRsContract));

		RsContract existingRsContract = _persistence.findByPrimaryKey(newRsContract.getPrimaryKey());

		Assert.assertEquals(existingRsContract.getCfpContractSid(),
			newRsContract.getCfpContractSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingRsContract.getCreatedDate()),
			Time.getShortTimestamp(newRsContract.getCreatedDate()));
		Assert.assertEquals(existingRsContract.getPsContractSid(),
			newRsContract.getPsContractSid());
		Assert.assertEquals(existingRsContract.getRsName(),
			newRsContract.getRsName());
		Assert.assertEquals(existingRsContract.getRsStatus(),
			newRsContract.getRsStatus());
		Assert.assertEquals(Time.getShortTimestamp(
				existingRsContract.getRsStartDate()),
			Time.getShortTimestamp(newRsContract.getRsStartDate()));
		Assert.assertEquals(existingRsContract.getRsTransRefId(),
			newRsContract.getRsTransRefId());
		Assert.assertEquals(existingRsContract.getMakePayableTo(),
			newRsContract.getMakePayableTo());
		Assert.assertEquals(existingRsContract.getCreatedBy(),
			newRsContract.getCreatedBy());
		Assert.assertEquals(existingRsContract.getRsCategory(),
			newRsContract.getRsCategory());
		Assert.assertEquals(existingRsContract.getRsTradeClass(),
			newRsContract.getRsTradeClass());
		Assert.assertEquals(existingRsContract.getContractMasterSid(),
			newRsContract.getContractMasterSid());
		Assert.assertEquals(existingRsContract.getRebateRuleType(),
			newRsContract.getRebateRuleType());
		Assert.assertEquals(existingRsContract.getPaymentMethod(),
			newRsContract.getPaymentMethod());
		Assert.assertEquals(Time.getShortTimestamp(
				existingRsContract.getRsContractAttachedDate()),
			Time.getShortTimestamp(newRsContract.getRsContractAttachedDate()));
		Assert.assertEquals(existingRsContract.getRsAlias(),
			newRsContract.getRsAlias());
		Assert.assertEquals(existingRsContract.getFormulaMethodId(),
			newRsContract.getFormulaMethodId());
		Assert.assertEquals(existingRsContract.getRebateProcessingType(),
			newRsContract.getRebateProcessingType());
		Assert.assertEquals(existingRsContract.getRsContractAttachedStatus(),
			newRsContract.getRsContractAttachedStatus());
		Assert.assertEquals(existingRsContract.getInterestBearingBasis(),
			newRsContract.getInterestBearingBasis());
		Assert.assertEquals(Time.getShortTimestamp(
				existingRsContract.getModifiedDate()),
			Time.getShortTimestamp(newRsContract.getModifiedDate()));
		Assert.assertEquals(existingRsContract.getRsTransRefName(),
			newRsContract.getRsTransRefName());
		Assert.assertEquals(existingRsContract.getRebateProgramType(),
			newRsContract.getRebateProgramType());
		Assert.assertEquals(existingRsContract.getRebatePlanLevel(),
			newRsContract.getRebatePlanLevel());
		Assert.assertEquals(existingRsContract.getSource(),
			newRsContract.getSource());
		Assert.assertEquals(existingRsContract.getRsCalendar(),
			newRsContract.getRsCalendar());
		Assert.assertEquals(existingRsContract.getRsType(),
			newRsContract.getRsType());
		Assert.assertEquals(existingRsContract.getAddress1(),
			newRsContract.getAddress1());
		Assert.assertEquals(existingRsContract.getAddress2(),
			newRsContract.getAddress2());
		Assert.assertEquals(Time.getShortTimestamp(
				existingRsContract.getRsEndDate()),
			Time.getShortTimestamp(newRsContract.getRsEndDate()));
		Assert.assertEquals(existingRsContract.getModifiedBy(),
			newRsContract.getModifiedBy());
		Assert.assertEquals(existingRsContract.getRsTransRefNo(),
			newRsContract.getRsTransRefNo());
		Assert.assertEquals(existingRsContract.getZipCode(),
			newRsContract.getZipCode());
		Assert.assertEquals(existingRsContract.getRebateRuleAssociation(),
			newRsContract.getRebateRuleAssociation());
		Assert.assertEquals(existingRsContract.getState(),
			newRsContract.getState());
		Assert.assertEquals(existingRsContract.getRebateFrequency(),
			newRsContract.getRebateFrequency());
		Assert.assertEquals(existingRsContract.getRsDesignation(),
			newRsContract.getRsDesignation());
		Assert.assertEquals(existingRsContract.getBatchId(),
			newRsContract.getBatchId());
		Assert.assertEquals(existingRsContract.getIfpContractSid(),
			newRsContract.getIfpContractSid());
		Assert.assertEquals(existingRsContract.getRsContractSid(),
			newRsContract.getRsContractSid());
		Assert.assertEquals(existingRsContract.getPaymentTerms(),
			newRsContract.getPaymentTerms());
		Assert.assertEquals(existingRsContract.getRsNo(),
			newRsContract.getRsNo());
		Assert.assertEquals(existingRsContract.getRsModelSid(),
			newRsContract.getRsModelSid());
		Assert.assertEquals(existingRsContract.getRsValidationProfile(),
			newRsContract.getRsValidationProfile());
		Assert.assertEquals(existingRsContract.getPaymentGracePeriod(),
			newRsContract.getPaymentGracePeriod());
		Assert.assertEquals(existingRsContract.getPaymentFrequency(),
			newRsContract.getPaymentFrequency());
		Assert.assertEquals(existingRsContract.getRecordLockStatus(),
			newRsContract.getRecordLockStatus());
		Assert.assertEquals(existingRsContract.getRsId(),
			newRsContract.getRsId());
		Assert.assertEquals(existingRsContract.getCity(),
			newRsContract.getCity());
		Assert.assertEquals(existingRsContract.getParentRsName(),
			newRsContract.getParentRsName());
		Assert.assertEquals(existingRsContract.getInterestBearingIndicator(),
			newRsContract.getInterestBearingIndicator());
		Assert.assertEquals(existingRsContract.getParentRsId(),
			newRsContract.getParentRsId());
		Assert.assertEquals(existingRsContract.getInboundStatus(),
			newRsContract.getInboundStatus());
		Assert.assertEquals(existingRsContract.getCalculationType(),
			newRsContract.getCalculationType());
		Assert.assertEquals(existingRsContract.getCalculationLevel(),
			newRsContract.getCalculationLevel());
		Assert.assertEquals(existingRsContract.getCalculationRule(),
			newRsContract.getCalculationRule());
		Assert.assertEquals(existingRsContract.getCalculationRuleLevel(),
			newRsContract.getCalculationRuleLevel());
		Assert.assertEquals(existingRsContract.getEvaluationRuleType(),
			newRsContract.getEvaluationRuleType());
		Assert.assertEquals(existingRsContract.getEvaluationRuleLevel(),
			newRsContract.getEvaluationRuleLevel());
		Assert.assertEquals(existingRsContract.getEvaluationRuleOrAssociation(),
			newRsContract.getEvaluationRuleOrAssociation());
		Assert.assertEquals(existingRsContract.getDeductionInclusion(),
			newRsContract.getDeductionInclusion());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		RsContract newRsContract = addRsContract();

		RsContract existingRsContract = _persistence.findByPrimaryKey(newRsContract.getPrimaryKey());

		Assert.assertEquals(existingRsContract, newRsContract);
	}

	@Test(expected = NoSuchRsContractException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<RsContract> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("RS_CONTRACT",
			"cfpContractSid", true, "createdDate", true, "psContractSid", true,
			"rsName", true, "rsStatus", true, "rsStartDate", true,
			"rsTransRefId", true, "makePayableTo", true, "createdBy", true,
			"rsCategory", true, "rsTradeClass", true, "contractMasterSid",
			true, "rebateRuleType", true, "paymentMethod", true,
			"rsContractAttachedDate", true, "rsAlias", true, "formulaMethodId",
			true, "rebateProcessingType", true, "rsContractAttachedStatus",
			true, "interestBearingBasis", true, "modifiedDate", true,
			"rsTransRefName", true, "rebateProgramType", true,
			"rebatePlanLevel", true, "source", true, "rsCalendar", true,
			"rsType", true, "address1", true, "address2", true, "rsEndDate",
			true, "modifiedBy", true, "rsTransRefNo", true, "zipCode", true,
			"rebateRuleAssociation", true, "state", true, "rebateFrequency",
			true, "rsDesignation", true, "batchId", true, "ifpContractSid",
			true, "rsContractSid", true, "paymentTerms", true, "rsNo", true,
			"rsModelSid", true, "rsValidationProfile", true,
			"paymentGracePeriod", true, "paymentFrequency", true,
			"recordLockStatus", true, "rsId", true, "city", true,
			"parentRsName", true, "interestBearingIndicator", true,
			"parentRsId", true, "inboundStatus", true, "calculationType", true,
			"calculationLevel", true, "calculationRule", true,
			"calculationRuleLevel", true, "evaluationRuleType", true,
			"evaluationRuleLevel", true, "evaluationRuleOrAssociation", true,
			"deductionInclusion", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		RsContract newRsContract = addRsContract();

		RsContract existingRsContract = _persistence.fetchByPrimaryKey(newRsContract.getPrimaryKey());

		Assert.assertEquals(existingRsContract, newRsContract);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		RsContract missingRsContract = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingRsContract);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		RsContract newRsContract1 = addRsContract();
		RsContract newRsContract2 = addRsContract();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRsContract1.getPrimaryKey());
		primaryKeys.add(newRsContract2.getPrimaryKey());

		Map<Serializable, RsContract> rsContracts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, rsContracts.size());
		Assert.assertEquals(newRsContract1,
			rsContracts.get(newRsContract1.getPrimaryKey()));
		Assert.assertEquals(newRsContract2,
			rsContracts.get(newRsContract2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, RsContract> rsContracts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(rsContracts.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		RsContract newRsContract = addRsContract();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRsContract.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, RsContract> rsContracts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, rsContracts.size());
		Assert.assertEquals(newRsContract,
			rsContracts.get(newRsContract.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, RsContract> rsContracts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(rsContracts.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		RsContract newRsContract = addRsContract();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRsContract.getPrimaryKey());

		Map<Serializable, RsContract> rsContracts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, rsContracts.size());
		Assert.assertEquals(newRsContract,
			rsContracts.get(newRsContract.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = RsContractLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<RsContract>() {
				@Override
				public void performAction(RsContract rsContract) {
					Assert.assertNotNull(rsContract);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		RsContract newRsContract = addRsContract();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RsContract.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("rsContractSid",
				newRsContract.getRsContractSid()));

		List<RsContract> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		RsContract existingRsContract = result.get(0);

		Assert.assertEquals(existingRsContract, newRsContract);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RsContract.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("rsContractSid",
				RandomTestUtil.nextInt()));

		List<RsContract> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		RsContract newRsContract = addRsContract();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RsContract.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"rsContractSid"));

		Object newRsContractSid = newRsContract.getRsContractSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("rsContractSid",
				new Object[] { newRsContractSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingRsContractSid = result.get(0);

		Assert.assertEquals(existingRsContractSid, newRsContractSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RsContract.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"rsContractSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("rsContractSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected RsContract addRsContract() throws Exception {
		int pk = RandomTestUtil.nextInt();

		RsContract rsContract = _persistence.create(pk);

		rsContract.setCfpContractSid(RandomTestUtil.randomString());

		rsContract.setCreatedDate(RandomTestUtil.nextDate());

		rsContract.setPsContractSid(RandomTestUtil.randomString());

		rsContract.setRsName(RandomTestUtil.randomString());

		rsContract.setRsStatus(RandomTestUtil.nextInt());

		rsContract.setRsStartDate(RandomTestUtil.nextDate());

		rsContract.setRsTransRefId(RandomTestUtil.randomString());

		rsContract.setMakePayableTo(RandomTestUtil.randomString());

		rsContract.setCreatedBy(RandomTestUtil.nextInt());

		rsContract.setRsCategory(RandomTestUtil.nextInt());

		rsContract.setRsTradeClass(RandomTestUtil.nextInt());

		rsContract.setContractMasterSid(RandomTestUtil.nextInt());

		rsContract.setRebateRuleType(RandomTestUtil.nextInt());

		rsContract.setPaymentMethod(RandomTestUtil.nextInt());

		rsContract.setRsContractAttachedDate(RandomTestUtil.nextDate());

		rsContract.setRsAlias(RandomTestUtil.randomString());

		rsContract.setFormulaMethodId(RandomTestUtil.randomString());

		rsContract.setRebateProcessingType(RandomTestUtil.nextInt());

		rsContract.setRsContractAttachedStatus(RandomTestUtil.nextInt());

		rsContract.setInterestBearingBasis(RandomTestUtil.nextInt());

		rsContract.setModifiedDate(RandomTestUtil.nextDate());

		rsContract.setRsTransRefName(RandomTestUtil.randomString());

		rsContract.setRebateProgramType(RandomTestUtil.nextInt());

		rsContract.setRebatePlanLevel(RandomTestUtil.randomString());

		rsContract.setSource(RandomTestUtil.randomString());

		rsContract.setRsCalendar(RandomTestUtil.randomString());

		rsContract.setRsType(RandomTestUtil.nextInt());

		rsContract.setAddress1(RandomTestUtil.randomString());

		rsContract.setAddress2(RandomTestUtil.randomString());

		rsContract.setRsEndDate(RandomTestUtil.nextDate());

		rsContract.setModifiedBy(RandomTestUtil.nextInt());

		rsContract.setRsTransRefNo(RandomTestUtil.randomString());

		rsContract.setZipCode(RandomTestUtil.randomString());

		rsContract.setRebateRuleAssociation(RandomTestUtil.randomString());

		rsContract.setState(RandomTestUtil.nextInt());

		rsContract.setRebateFrequency(RandomTestUtil.nextInt());

		rsContract.setRsDesignation(RandomTestUtil.randomString());

		rsContract.setBatchId(RandomTestUtil.randomString());

		rsContract.setIfpContractSid(RandomTestUtil.randomString());

		rsContract.setPaymentTerms(RandomTestUtil.nextInt());

		rsContract.setRsNo(RandomTestUtil.randomString());

		rsContract.setRsModelSid(RandomTestUtil.nextInt());

		rsContract.setRsValidationProfile(RandomTestUtil.nextInt());

		rsContract.setPaymentGracePeriod(RandomTestUtil.randomString());

		rsContract.setPaymentFrequency(RandomTestUtil.nextInt());

		rsContract.setRecordLockStatus(RandomTestUtil.randomBoolean());

		rsContract.setRsId(RandomTestUtil.randomString());

		rsContract.setCity(RandomTestUtil.randomString());

		rsContract.setParentRsName(RandomTestUtil.randomString());

		rsContract.setInterestBearingIndicator(RandomTestUtil.nextInt());

		rsContract.setParentRsId(RandomTestUtil.randomString());

		rsContract.setInboundStatus(RandomTestUtil.randomString());

		rsContract.setCalculationType(RandomTestUtil.nextInt());

		rsContract.setCalculationLevel(RandomTestUtil.nextInt());

		rsContract.setCalculationRule(RandomTestUtil.randomString());

		rsContract.setCalculationRuleLevel(RandomTestUtil.randomString());

		rsContract.setEvaluationRuleType(RandomTestUtil.randomString());

		rsContract.setEvaluationRuleLevel(RandomTestUtil.randomString());

		rsContract.setEvaluationRuleOrAssociation(RandomTestUtil.randomString());

		rsContract.setDeductionInclusion(RandomTestUtil.randomString());

		_rsContracts.add(_persistence.update(rsContract));

		return rsContract;
	}

	private List<RsContract> _rsContracts = new ArrayList<RsContract>();
	private RsContractPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}