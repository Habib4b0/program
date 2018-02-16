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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchRsModelException;
import com.stpl.app.model.RsModel;
import com.stpl.app.service.RsModelLocalServiceUtil;
import com.stpl.app.service.persistence.RsModelPersistence;
import com.stpl.app.service.persistence.RsModelUtil;

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
public class RsModelPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = RsModelUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<RsModel> iterator = _rsModels.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		RsModel rsModel = _persistence.create(pk);

		Assert.assertNotNull(rsModel);

		Assert.assertEquals(rsModel.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		RsModel newRsModel = addRsModel();

		_persistence.remove(newRsModel);

		RsModel existingRsModel = _persistence.fetchByPrimaryKey(newRsModel.getPrimaryKey());

		Assert.assertNull(existingRsModel);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addRsModel();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		RsModel newRsModel = _persistence.create(pk);

		newRsModel.setFormulaMethodId(RandomTestUtil.randomString());

		newRsModel.setCalculationType(RandomTestUtil.nextInt());

		newRsModel.setRsStatus(RandomTestUtil.nextInt());

		newRsModel.setRsEndDate(RandomTestUtil.nextDate());

		newRsModel.setRsTransRefNo(RandomTestUtil.randomString());

		newRsModel.setPaymentFrequency(RandomTestUtil.nextInt());

		newRsModel.setModifiedDate(RandomTestUtil.nextDate());

		newRsModel.setCalculationLevel(RandomTestUtil.nextInt());

		newRsModel.setRsName(RandomTestUtil.randomString());

		newRsModel.setSource(RandomTestUtil.randomString());

		newRsModel.setRebatePlanLevel(RandomTestUtil.randomString());

		newRsModel.setRebateRuleType(RandomTestUtil.nextInt());

		newRsModel.setInboundStatus(RandomTestUtil.randomString());

		newRsModel.setModifiedBy(RandomTestUtil.nextInt());

		newRsModel.setRsAlias(RandomTestUtil.randomString());

		newRsModel.setRsId(RandomTestUtil.randomString());

		newRsModel.setPaymentMethod(RandomTestUtil.nextInt());

		newRsModel.setZipCode(RandomTestUtil.randomString());

		newRsModel.setRebateRuleAssociation(RandomTestUtil.randomString());

		newRsModel.setParentRsName(RandomTestUtil.randomString());

		newRsModel.setInternalNotes(RandomTestUtil.randomString());

		newRsModel.setPaymentLevel(RandomTestUtil.nextInt());

		newRsModel.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newRsModel.setRsCalendar(RandomTestUtil.nextInt());

		newRsModel.setRebateProgramType(RandomTestUtil.nextInt());

		newRsModel.setInterestBearingBasis(RandomTestUtil.nextInt());

		newRsModel.setCity(RandomTestUtil.randomString());

		newRsModel.setRebateProcessingType(RandomTestUtil.nextInt());

		newRsModel.setRsNo(RandomTestUtil.randomString());

		newRsModel.setState(RandomTestUtil.nextInt());

		newRsModel.setRebateFrequency(RandomTestUtil.nextInt());

		newRsModel.setParentRsId(RandomTestUtil.randomString());

		newRsModel.setRsType(RandomTestUtil.nextInt());

		newRsModel.setRsStartDate(RandomTestUtil.nextDate());

		newRsModel.setMakePayableTo(RandomTestUtil.randomString());

		newRsModel.setRsDesignation(RandomTestUtil.nextInt());

		newRsModel.setRsTransRefName(RandomTestUtil.randomString());

		newRsModel.setCreatedBy(RandomTestUtil.nextInt());

		newRsModel.setCreatedDate(RandomTestUtil.nextDate());

		newRsModel.setRsTransRefId(RandomTestUtil.randomString());

		newRsModel.setRsCategory(RandomTestUtil.nextInt());

		newRsModel.setRsTradeClass(RandomTestUtil.nextInt());

		newRsModel.setInterestBearingIndicator(RandomTestUtil.nextInt());

		newRsModel.setManfCompanyMasterSid(RandomTestUtil.nextInt());

		newRsModel.setPaymentTerms(RandomTestUtil.nextInt());

		newRsModel.setAddress1(RandomTestUtil.randomString());

		newRsModel.setAddress2(RandomTestUtil.randomString());

		newRsModel.setRsValidationProfile(RandomTestUtil.nextInt());

		newRsModel.setPaymentGracePeriod(RandomTestUtil.randomString());

		newRsModel.setBatchId(RandomTestUtil.randomString());

		newRsModel.setEvaluationRuleType(RandomTestUtil.randomString());

		newRsModel.setEvaluationRuleLevel(RandomTestUtil.randomString());

		newRsModel.setEvaluationRuleOrAssociation(RandomTestUtil.randomString());

		newRsModel.setCalculationRuleLevel(RandomTestUtil.randomString());

		newRsModel.setCalculationRule(RandomTestUtil.randomString());

		newRsModel.setDeductionInclusion(RandomTestUtil.randomString());

		_rsModels.add(_persistence.update(newRsModel));

		RsModel existingRsModel = _persistence.findByPrimaryKey(newRsModel.getPrimaryKey());

		Assert.assertEquals(existingRsModel.getFormulaMethodId(),
			newRsModel.getFormulaMethodId());
		Assert.assertEquals(existingRsModel.getCalculationType(),
			newRsModel.getCalculationType());
		Assert.assertEquals(existingRsModel.getRsStatus(),
			newRsModel.getRsStatus());
		Assert.assertEquals(Time.getShortTimestamp(
				existingRsModel.getRsEndDate()),
			Time.getShortTimestamp(newRsModel.getRsEndDate()));
		Assert.assertEquals(existingRsModel.getRsTransRefNo(),
			newRsModel.getRsTransRefNo());
		Assert.assertEquals(existingRsModel.getPaymentFrequency(),
			newRsModel.getPaymentFrequency());
		Assert.assertEquals(Time.getShortTimestamp(
				existingRsModel.getModifiedDate()),
			Time.getShortTimestamp(newRsModel.getModifiedDate()));
		Assert.assertEquals(existingRsModel.getCalculationLevel(),
			newRsModel.getCalculationLevel());
		Assert.assertEquals(existingRsModel.getRsName(), newRsModel.getRsName());
		Assert.assertEquals(existingRsModel.getSource(), newRsModel.getSource());
		Assert.assertEquals(existingRsModel.getRebatePlanLevel(),
			newRsModel.getRebatePlanLevel());
		Assert.assertEquals(existingRsModel.getRebateRuleType(),
			newRsModel.getRebateRuleType());
		Assert.assertEquals(existingRsModel.getInboundStatus(),
			newRsModel.getInboundStatus());
		Assert.assertEquals(existingRsModel.getModifiedBy(),
			newRsModel.getModifiedBy());
		Assert.assertEquals(existingRsModel.getRsAlias(),
			newRsModel.getRsAlias());
		Assert.assertEquals(existingRsModel.getRsId(), newRsModel.getRsId());
		Assert.assertEquals(existingRsModel.getPaymentMethod(),
			newRsModel.getPaymentMethod());
		Assert.assertEquals(existingRsModel.getZipCode(),
			newRsModel.getZipCode());
		Assert.assertEquals(existingRsModel.getRebateRuleAssociation(),
			newRsModel.getRebateRuleAssociation());
		Assert.assertEquals(existingRsModel.getParentRsName(),
			newRsModel.getParentRsName());
		Assert.assertEquals(existingRsModel.getInternalNotes(),
			newRsModel.getInternalNotes());
		Assert.assertEquals(existingRsModel.getPaymentLevel(),
			newRsModel.getPaymentLevel());
		Assert.assertEquals(existingRsModel.getRecordLockStatus(),
			newRsModel.getRecordLockStatus());
		Assert.assertEquals(existingRsModel.getRsCalendar(),
			newRsModel.getRsCalendar());
		Assert.assertEquals(existingRsModel.getRebateProgramType(),
			newRsModel.getRebateProgramType());
		Assert.assertEquals(existingRsModel.getInterestBearingBasis(),
			newRsModel.getInterestBearingBasis());
		Assert.assertEquals(existingRsModel.getRsModelSid(),
			newRsModel.getRsModelSid());
		Assert.assertEquals(existingRsModel.getCity(), newRsModel.getCity());
		Assert.assertEquals(existingRsModel.getRebateProcessingType(),
			newRsModel.getRebateProcessingType());
		Assert.assertEquals(existingRsModel.getRsNo(), newRsModel.getRsNo());
		Assert.assertEquals(existingRsModel.getState(), newRsModel.getState());
		Assert.assertEquals(existingRsModel.getRebateFrequency(),
			newRsModel.getRebateFrequency());
		Assert.assertEquals(existingRsModel.getParentRsId(),
			newRsModel.getParentRsId());
		Assert.assertEquals(existingRsModel.getRsType(), newRsModel.getRsType());
		Assert.assertEquals(Time.getShortTimestamp(
				existingRsModel.getRsStartDate()),
			Time.getShortTimestamp(newRsModel.getRsStartDate()));
		Assert.assertEquals(existingRsModel.getMakePayableTo(),
			newRsModel.getMakePayableTo());
		Assert.assertEquals(existingRsModel.getRsDesignation(),
			newRsModel.getRsDesignation());
		Assert.assertEquals(existingRsModel.getRsTransRefName(),
			newRsModel.getRsTransRefName());
		Assert.assertEquals(existingRsModel.getCreatedBy(),
			newRsModel.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingRsModel.getCreatedDate()),
			Time.getShortTimestamp(newRsModel.getCreatedDate()));
		Assert.assertEquals(existingRsModel.getRsTransRefId(),
			newRsModel.getRsTransRefId());
		Assert.assertEquals(existingRsModel.getRsCategory(),
			newRsModel.getRsCategory());
		Assert.assertEquals(existingRsModel.getRsTradeClass(),
			newRsModel.getRsTradeClass());
		Assert.assertEquals(existingRsModel.getInterestBearingIndicator(),
			newRsModel.getInterestBearingIndicator());
		Assert.assertEquals(existingRsModel.getManfCompanyMasterSid(),
			newRsModel.getManfCompanyMasterSid());
		Assert.assertEquals(existingRsModel.getPaymentTerms(),
			newRsModel.getPaymentTerms());
		Assert.assertEquals(existingRsModel.getAddress1(),
			newRsModel.getAddress1());
		Assert.assertEquals(existingRsModel.getAddress2(),
			newRsModel.getAddress2());
		Assert.assertEquals(existingRsModel.getRsValidationProfile(),
			newRsModel.getRsValidationProfile());
		Assert.assertEquals(existingRsModel.getPaymentGracePeriod(),
			newRsModel.getPaymentGracePeriod());
		Assert.assertEquals(existingRsModel.getBatchId(),
			newRsModel.getBatchId());
		Assert.assertEquals(existingRsModel.getEvaluationRuleType(),
			newRsModel.getEvaluationRuleType());
		Assert.assertEquals(existingRsModel.getEvaluationRuleLevel(),
			newRsModel.getEvaluationRuleLevel());
		Assert.assertEquals(existingRsModel.getEvaluationRuleOrAssociation(),
			newRsModel.getEvaluationRuleOrAssociation());
		Assert.assertEquals(existingRsModel.getCalculationRuleLevel(),
			newRsModel.getCalculationRuleLevel());
		Assert.assertEquals(existingRsModel.getCalculationRule(),
			newRsModel.getCalculationRule());
		Assert.assertEquals(existingRsModel.getDeductionInclusion(),
			newRsModel.getDeductionInclusion());
	}

	@Test
	public void testCountByRebateScheduleId() throws Exception {
		_persistence.countByRebateScheduleId(StringPool.BLANK);

		_persistence.countByRebateScheduleId(StringPool.NULL);

		_persistence.countByRebateScheduleId((String)null);
	}

	@Test
	public void testCountByRebateScheduleNo() throws Exception {
		_persistence.countByRebateScheduleNo(StringPool.BLANK);

		_persistence.countByRebateScheduleNo(StringPool.NULL);

		_persistence.countByRebateScheduleNo((String)null);
	}

	@Test
	public void testCountByRebateScheduleName() throws Exception {
		_persistence.countByRebateScheduleName(StringPool.BLANK);

		_persistence.countByRebateScheduleName(StringPool.NULL);

		_persistence.countByRebateScheduleName((String)null);
	}

	@Test
	public void testCountByRebateScheduleType() throws Exception {
		_persistence.countByRebateScheduleType(RandomTestUtil.nextInt());

		_persistence.countByRebateScheduleType(0);
	}

	@Test
	public void testCountByRebateScheduleStatus() throws Exception {
		_persistence.countByRebateScheduleStatus(RandomTestUtil.nextInt());

		_persistence.countByRebateScheduleStatus(0);
	}

	@Test
	public void testCountByRebateProgramType() throws Exception {
		_persistence.countByRebateProgramType(RandomTestUtil.nextInt());

		_persistence.countByRebateProgramType(0);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		RsModel newRsModel = addRsModel();

		RsModel existingRsModel = _persistence.findByPrimaryKey(newRsModel.getPrimaryKey());

		Assert.assertEquals(existingRsModel, newRsModel);
	}

	@Test(expected = NoSuchRsModelException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<RsModel> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("RS_MODEL",
			"formulaMethodId", true, "calculationType", true, "rsStatus", true,
			"rsEndDate", true, "rsTransRefNo", true, "paymentFrequency", true,
			"modifiedDate", true, "calculationLevel", true, "rsName", true,
			"source", true, "rebatePlanLevel", true, "rebateRuleType", true,
			"inboundStatus", true, "modifiedBy", true, "rsAlias", true, "rsId",
			true, "paymentMethod", true, "zipCode", true,
			"rebateRuleAssociation", true, "parentRsName", true,
			"internalNotes", true, "paymentLevel", true, "recordLockStatus",
			true, "rsCalendar", true, "rebateProgramType", true,
			"interestBearingBasis", true, "rsModelSid", true, "city", true,
			"rebateProcessingType", true, "rsNo", true, "state", true,
			"rebateFrequency", true, "parentRsId", true, "rsType", true,
			"rsStartDate", true, "makePayableTo", true, "rsDesignation", true,
			"rsTransRefName", true, "createdBy", true, "createdDate", true,
			"rsTransRefId", true, "rsCategory", true, "rsTradeClass", true,
			"interestBearingIndicator", true, "manfCompanyMasterSid", true,
			"paymentTerms", true, "address1", true, "address2", true,
			"rsValidationProfile", true, "paymentGracePeriod", true, "batchId",
			true, "evaluationRuleType", true, "evaluationRuleLevel", true,
			"evaluationRuleOrAssociation", true, "calculationRuleLevel", true,
			"calculationRule", true, "deductionInclusion", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		RsModel newRsModel = addRsModel();

		RsModel existingRsModel = _persistence.fetchByPrimaryKey(newRsModel.getPrimaryKey());

		Assert.assertEquals(existingRsModel, newRsModel);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		RsModel missingRsModel = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingRsModel);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		RsModel newRsModel1 = addRsModel();
		RsModel newRsModel2 = addRsModel();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRsModel1.getPrimaryKey());
		primaryKeys.add(newRsModel2.getPrimaryKey());

		Map<Serializable, RsModel> rsModels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, rsModels.size());
		Assert.assertEquals(newRsModel1,
			rsModels.get(newRsModel1.getPrimaryKey()));
		Assert.assertEquals(newRsModel2,
			rsModels.get(newRsModel2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, RsModel> rsModels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(rsModels.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		RsModel newRsModel = addRsModel();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRsModel.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, RsModel> rsModels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, rsModels.size());
		Assert.assertEquals(newRsModel, rsModels.get(newRsModel.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, RsModel> rsModels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(rsModels.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		RsModel newRsModel = addRsModel();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRsModel.getPrimaryKey());

		Map<Serializable, RsModel> rsModels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, rsModels.size());
		Assert.assertEquals(newRsModel, rsModels.get(newRsModel.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = RsModelLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<RsModel>() {
				@Override
				public void performAction(RsModel rsModel) {
					Assert.assertNotNull(rsModel);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		RsModel newRsModel = addRsModel();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RsModel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("rsModelSid",
				newRsModel.getRsModelSid()));

		List<RsModel> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		RsModel existingRsModel = result.get(0);

		Assert.assertEquals(existingRsModel, newRsModel);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RsModel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("rsModelSid",
				RandomTestUtil.nextInt()));

		List<RsModel> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		RsModel newRsModel = addRsModel();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RsModel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("rsModelSid"));

		Object newRsModelSid = newRsModel.getRsModelSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("rsModelSid",
				new Object[] { newRsModelSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingRsModelSid = result.get(0);

		Assert.assertEquals(existingRsModelSid, newRsModelSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RsModel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("rsModelSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("rsModelSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected RsModel addRsModel() throws Exception {
		int pk = RandomTestUtil.nextInt();

		RsModel rsModel = _persistence.create(pk);

		rsModel.setFormulaMethodId(RandomTestUtil.randomString());

		rsModel.setCalculationType(RandomTestUtil.nextInt());

		rsModel.setRsStatus(RandomTestUtil.nextInt());

		rsModel.setRsEndDate(RandomTestUtil.nextDate());

		rsModel.setRsTransRefNo(RandomTestUtil.randomString());

		rsModel.setPaymentFrequency(RandomTestUtil.nextInt());

		rsModel.setModifiedDate(RandomTestUtil.nextDate());

		rsModel.setCalculationLevel(RandomTestUtil.nextInt());

		rsModel.setRsName(RandomTestUtil.randomString());

		rsModel.setSource(RandomTestUtil.randomString());

		rsModel.setRebatePlanLevel(RandomTestUtil.randomString());

		rsModel.setRebateRuleType(RandomTestUtil.nextInt());

		rsModel.setInboundStatus(RandomTestUtil.randomString());

		rsModel.setModifiedBy(RandomTestUtil.nextInt());

		rsModel.setRsAlias(RandomTestUtil.randomString());

		rsModel.setRsId(RandomTestUtil.randomString());

		rsModel.setPaymentMethod(RandomTestUtil.nextInt());

		rsModel.setZipCode(RandomTestUtil.randomString());

		rsModel.setRebateRuleAssociation(RandomTestUtil.randomString());

		rsModel.setParentRsName(RandomTestUtil.randomString());

		rsModel.setInternalNotes(RandomTestUtil.randomString());

		rsModel.setPaymentLevel(RandomTestUtil.nextInt());

		rsModel.setRecordLockStatus(RandomTestUtil.randomBoolean());

		rsModel.setRsCalendar(RandomTestUtil.nextInt());

		rsModel.setRebateProgramType(RandomTestUtil.nextInt());

		rsModel.setInterestBearingBasis(RandomTestUtil.nextInt());

		rsModel.setCity(RandomTestUtil.randomString());

		rsModel.setRebateProcessingType(RandomTestUtil.nextInt());

		rsModel.setRsNo(RandomTestUtil.randomString());

		rsModel.setState(RandomTestUtil.nextInt());

		rsModel.setRebateFrequency(RandomTestUtil.nextInt());

		rsModel.setParentRsId(RandomTestUtil.randomString());

		rsModel.setRsType(RandomTestUtil.nextInt());

		rsModel.setRsStartDate(RandomTestUtil.nextDate());

		rsModel.setMakePayableTo(RandomTestUtil.randomString());

		rsModel.setRsDesignation(RandomTestUtil.nextInt());

		rsModel.setRsTransRefName(RandomTestUtil.randomString());

		rsModel.setCreatedBy(RandomTestUtil.nextInt());

		rsModel.setCreatedDate(RandomTestUtil.nextDate());

		rsModel.setRsTransRefId(RandomTestUtil.randomString());

		rsModel.setRsCategory(RandomTestUtil.nextInt());

		rsModel.setRsTradeClass(RandomTestUtil.nextInt());

		rsModel.setInterestBearingIndicator(RandomTestUtil.nextInt());

		rsModel.setManfCompanyMasterSid(RandomTestUtil.nextInt());

		rsModel.setPaymentTerms(RandomTestUtil.nextInt());

		rsModel.setAddress1(RandomTestUtil.randomString());

		rsModel.setAddress2(RandomTestUtil.randomString());

		rsModel.setRsValidationProfile(RandomTestUtil.nextInt());

		rsModel.setPaymentGracePeriod(RandomTestUtil.randomString());

		rsModel.setBatchId(RandomTestUtil.randomString());

		rsModel.setEvaluationRuleType(RandomTestUtil.randomString());

		rsModel.setEvaluationRuleLevel(RandomTestUtil.randomString());

		rsModel.setEvaluationRuleOrAssociation(RandomTestUtil.randomString());

		rsModel.setCalculationRuleLevel(RandomTestUtil.randomString());

		rsModel.setCalculationRule(RandomTestUtil.randomString());

		rsModel.setDeductionInclusion(RandomTestUtil.randomString());

		_rsModels.add(_persistence.update(rsModel));

		return rsModel;
	}

	private List<RsModel> _rsModels = new ArrayList<RsModel>();
	private RsModelPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}