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

import com.stpl.app.exception.NoSuchRsContractDetailsException;
import com.stpl.app.model.RsContractDetails;
import com.stpl.app.service.RsContractDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.RsContractDetailsPersistence;
import com.stpl.app.service.persistence.RsContractDetailsUtil;

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
public class RsContractDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = RsContractDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<RsContractDetails> iterator = _rsContractDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		RsContractDetails rsContractDetails = _persistence.create(pk);

		Assert.assertNotNull(rsContractDetails);

		Assert.assertEquals(rsContractDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		RsContractDetails newRsContractDetails = addRsContractDetails();

		_persistence.remove(newRsContractDetails);

		RsContractDetails existingRsContractDetails = _persistence.fetchByPrimaryKey(newRsContractDetails.getPrimaryKey());

		Assert.assertNull(existingRsContractDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addRsContractDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		RsContractDetails newRsContractDetails = _persistence.create(pk);

		newRsContractDetails.setRebateAmount(RandomTestUtil.nextDouble());

		newRsContractDetails.setFormulaMethodId(RandomTestUtil.randomString());

		newRsContractDetails.setItemMasterSid(RandomTestUtil.nextInt());

		newRsContractDetails.setRebatePlanMasterSid(RandomTestUtil.randomString());

		newRsContractDetails.setModifiedDate(RandomTestUtil.nextDate());

		newRsContractDetails.setBundleNo(RandomTestUtil.randomString());

		newRsContractDetails.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newRsContractDetails.setCreatedDate(RandomTestUtil.nextDate());

		newRsContractDetails.setCreatedBy(RandomTestUtil.nextInt());

		newRsContractDetails.setSource(RandomTestUtil.randomString());

		newRsContractDetails.setRsContractSid(RandomTestUtil.nextInt());

		newRsContractDetails.setItemRebateEndDate(RandomTestUtil.nextDate());

		newRsContractDetails.setBatchId(RandomTestUtil.randomString());

		newRsContractDetails.setItemRebateStartDate(RandomTestUtil.nextDate());

		newRsContractDetails.setModifiedBy(RandomTestUtil.nextInt());

		newRsContractDetails.setFormulaId(RandomTestUtil.nextInt());

		newRsContractDetails.setInboundStatus(RandomTestUtil.randomString());

		newRsContractDetails.setDeductionCalendarMasterSid(RandomTestUtil.randomString());

		newRsContractDetails.setNetSalesFormulaMasterSid(RandomTestUtil.randomString());

		newRsContractDetails.setFormulaType(RandomTestUtil.nextDouble());

		newRsContractDetails.setNetSalesRule(RandomTestUtil.nextInt());

		newRsContractDetails.setEvaluationRule(RandomTestUtil.nextInt());

		newRsContractDetails.setEvaluationRuleBundle(RandomTestUtil.randomString());

		newRsContractDetails.setCalculationRule(RandomTestUtil.nextInt());

		newRsContractDetails.setCalculationRuleBundle(RandomTestUtil.randomString());

		newRsContractDetails.setRsContractAttachedDate(RandomTestUtil.nextDate());

		newRsContractDetails.setRsContractAttachedStatus(RandomTestUtil.nextInt());

		_rsContractDetailses.add(_persistence.update(newRsContractDetails));

		RsContractDetails existingRsContractDetails = _persistence.findByPrimaryKey(newRsContractDetails.getPrimaryKey());

		AssertUtils.assertEquals(existingRsContractDetails.getRebateAmount(),
			newRsContractDetails.getRebateAmount());
		Assert.assertEquals(existingRsContractDetails.getFormulaMethodId(),
			newRsContractDetails.getFormulaMethodId());
		Assert.assertEquals(existingRsContractDetails.getItemMasterSid(),
			newRsContractDetails.getItemMasterSid());
		Assert.assertEquals(existingRsContractDetails.getRebatePlanMasterSid(),
			newRsContractDetails.getRebatePlanMasterSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingRsContractDetails.getModifiedDate()),
			Time.getShortTimestamp(newRsContractDetails.getModifiedDate()));
		Assert.assertEquals(existingRsContractDetails.getRsContractDetailsSid(),
			newRsContractDetails.getRsContractDetailsSid());
		Assert.assertEquals(existingRsContractDetails.getBundleNo(),
			newRsContractDetails.getBundleNo());
		Assert.assertEquals(existingRsContractDetails.getRecordLockStatus(),
			newRsContractDetails.getRecordLockStatus());
		Assert.assertEquals(Time.getShortTimestamp(
				existingRsContractDetails.getCreatedDate()),
			Time.getShortTimestamp(newRsContractDetails.getCreatedDate()));
		Assert.assertEquals(existingRsContractDetails.getCreatedBy(),
			newRsContractDetails.getCreatedBy());
		Assert.assertEquals(existingRsContractDetails.getSource(),
			newRsContractDetails.getSource());
		Assert.assertEquals(existingRsContractDetails.getRsContractSid(),
			newRsContractDetails.getRsContractSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingRsContractDetails.getItemRebateEndDate()),
			Time.getShortTimestamp(newRsContractDetails.getItemRebateEndDate()));
		Assert.assertEquals(existingRsContractDetails.getBatchId(),
			newRsContractDetails.getBatchId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingRsContractDetails.getItemRebateStartDate()),
			Time.getShortTimestamp(
				newRsContractDetails.getItemRebateStartDate()));
		Assert.assertEquals(existingRsContractDetails.getModifiedBy(),
			newRsContractDetails.getModifiedBy());
		Assert.assertEquals(existingRsContractDetails.getFormulaId(),
			newRsContractDetails.getFormulaId());
		Assert.assertEquals(existingRsContractDetails.getInboundStatus(),
			newRsContractDetails.getInboundStatus());
		Assert.assertEquals(existingRsContractDetails.getDeductionCalendarMasterSid(),
			newRsContractDetails.getDeductionCalendarMasterSid());
		Assert.assertEquals(existingRsContractDetails.getNetSalesFormulaMasterSid(),
			newRsContractDetails.getNetSalesFormulaMasterSid());
		AssertUtils.assertEquals(existingRsContractDetails.getFormulaType(),
			newRsContractDetails.getFormulaType());
		Assert.assertEquals(existingRsContractDetails.getNetSalesRule(),
			newRsContractDetails.getNetSalesRule());
		Assert.assertEquals(existingRsContractDetails.getEvaluationRule(),
			newRsContractDetails.getEvaluationRule());
		Assert.assertEquals(existingRsContractDetails.getEvaluationRuleBundle(),
			newRsContractDetails.getEvaluationRuleBundle());
		Assert.assertEquals(existingRsContractDetails.getCalculationRule(),
			newRsContractDetails.getCalculationRule());
		Assert.assertEquals(existingRsContractDetails.getCalculationRuleBundle(),
			newRsContractDetails.getCalculationRuleBundle());
		Assert.assertEquals(Time.getShortTimestamp(
				existingRsContractDetails.getRsContractAttachedDate()),
			Time.getShortTimestamp(
				newRsContractDetails.getRsContractAttachedDate()));
		Assert.assertEquals(existingRsContractDetails.getRsContractAttachedStatus(),
			newRsContractDetails.getRsContractAttachedStatus());
	}

	@Test
	public void testCountByRebateScheduleDetails() throws Exception {
		_persistence.countByRebateScheduleDetails(RandomTestUtil.nextInt(),
			RandomTestUtil.nextInt());

		_persistence.countByRebateScheduleDetails(0, 0);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		RsContractDetails newRsContractDetails = addRsContractDetails();

		RsContractDetails existingRsContractDetails = _persistence.findByPrimaryKey(newRsContractDetails.getPrimaryKey());

		Assert.assertEquals(existingRsContractDetails, newRsContractDetails);
	}

	@Test(expected = NoSuchRsContractDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<RsContractDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("RS_CONTRACT_DETAILS",
			"rebateAmount", true, "formulaMethodId", true, "itemMasterSid",
			true, "rebatePlanMasterSid", true, "modifiedDate", true,
			"rsContractDetailsSid", true, "bundleNo", true, "recordLockStatus",
			true, "createdDate", true, "createdBy", true, "source", true,
			"rsContractSid", true, "itemRebateEndDate", true, "batchId", true,
			"itemRebateStartDate", true, "modifiedBy", true, "formulaId", true,
			"inboundStatus", true, "deductionCalendarMasterSid", true,
			"netSalesFormulaMasterSid", true, "formulaType", true,
			"netSalesRule", true, "evaluationRule", true,
			"evaluationRuleBundle", true, "calculationRule", true,
			"calculationRuleBundle", true, "rsContractAttachedDate", true,
			"rsContractAttachedStatus", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		RsContractDetails newRsContractDetails = addRsContractDetails();

		RsContractDetails existingRsContractDetails = _persistence.fetchByPrimaryKey(newRsContractDetails.getPrimaryKey());

		Assert.assertEquals(existingRsContractDetails, newRsContractDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		RsContractDetails missingRsContractDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingRsContractDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		RsContractDetails newRsContractDetails1 = addRsContractDetails();
		RsContractDetails newRsContractDetails2 = addRsContractDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRsContractDetails1.getPrimaryKey());
		primaryKeys.add(newRsContractDetails2.getPrimaryKey());

		Map<Serializable, RsContractDetails> rsContractDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, rsContractDetailses.size());
		Assert.assertEquals(newRsContractDetails1,
			rsContractDetailses.get(newRsContractDetails1.getPrimaryKey()));
		Assert.assertEquals(newRsContractDetails2,
			rsContractDetailses.get(newRsContractDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, RsContractDetails> rsContractDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(rsContractDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		RsContractDetails newRsContractDetails = addRsContractDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRsContractDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, RsContractDetails> rsContractDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, rsContractDetailses.size());
		Assert.assertEquals(newRsContractDetails,
			rsContractDetailses.get(newRsContractDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, RsContractDetails> rsContractDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(rsContractDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		RsContractDetails newRsContractDetails = addRsContractDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRsContractDetails.getPrimaryKey());

		Map<Serializable, RsContractDetails> rsContractDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, rsContractDetailses.size());
		Assert.assertEquals(newRsContractDetails,
			rsContractDetailses.get(newRsContractDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = RsContractDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<RsContractDetails>() {
				@Override
				public void performAction(RsContractDetails rsContractDetails) {
					Assert.assertNotNull(rsContractDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		RsContractDetails newRsContractDetails = addRsContractDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RsContractDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("rsContractDetailsSid",
				newRsContractDetails.getRsContractDetailsSid()));

		List<RsContractDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		RsContractDetails existingRsContractDetails = result.get(0);

		Assert.assertEquals(existingRsContractDetails, newRsContractDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RsContractDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("rsContractDetailsSid",
				RandomTestUtil.nextInt()));

		List<RsContractDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		RsContractDetails newRsContractDetails = addRsContractDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RsContractDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"rsContractDetailsSid"));

		Object newRsContractDetailsSid = newRsContractDetails.getRsContractDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("rsContractDetailsSid",
				new Object[] { newRsContractDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingRsContractDetailsSid = result.get(0);

		Assert.assertEquals(existingRsContractDetailsSid,
			newRsContractDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RsContractDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"rsContractDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("rsContractDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected RsContractDetails addRsContractDetails()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		RsContractDetails rsContractDetails = _persistence.create(pk);

		rsContractDetails.setRebateAmount(RandomTestUtil.nextDouble());

		rsContractDetails.setFormulaMethodId(RandomTestUtil.randomString());

		rsContractDetails.setItemMasterSid(RandomTestUtil.nextInt());

		rsContractDetails.setRebatePlanMasterSid(RandomTestUtil.randomString());

		rsContractDetails.setModifiedDate(RandomTestUtil.nextDate());

		rsContractDetails.setBundleNo(RandomTestUtil.randomString());

		rsContractDetails.setRecordLockStatus(RandomTestUtil.randomBoolean());

		rsContractDetails.setCreatedDate(RandomTestUtil.nextDate());

		rsContractDetails.setCreatedBy(RandomTestUtil.nextInt());

		rsContractDetails.setSource(RandomTestUtil.randomString());

		rsContractDetails.setRsContractSid(RandomTestUtil.nextInt());

		rsContractDetails.setItemRebateEndDate(RandomTestUtil.nextDate());

		rsContractDetails.setBatchId(RandomTestUtil.randomString());

		rsContractDetails.setItemRebateStartDate(RandomTestUtil.nextDate());

		rsContractDetails.setModifiedBy(RandomTestUtil.nextInt());

		rsContractDetails.setFormulaId(RandomTestUtil.nextInt());

		rsContractDetails.setInboundStatus(RandomTestUtil.randomString());

		rsContractDetails.setDeductionCalendarMasterSid(RandomTestUtil.randomString());

		rsContractDetails.setNetSalesFormulaMasterSid(RandomTestUtil.randomString());

		rsContractDetails.setFormulaType(RandomTestUtil.nextDouble());

		rsContractDetails.setNetSalesRule(RandomTestUtil.nextInt());

		rsContractDetails.setEvaluationRule(RandomTestUtil.nextInt());

		rsContractDetails.setEvaluationRuleBundle(RandomTestUtil.randomString());

		rsContractDetails.setCalculationRule(RandomTestUtil.nextInt());

		rsContractDetails.setCalculationRuleBundle(RandomTestUtil.randomString());

		rsContractDetails.setRsContractAttachedDate(RandomTestUtil.nextDate());

		rsContractDetails.setRsContractAttachedStatus(RandomTestUtil.nextInt());

		_rsContractDetailses.add(_persistence.update(rsContractDetails));

		return rsContractDetails;
	}

	private List<RsContractDetails> _rsContractDetailses = new ArrayList<RsContractDetails>();
	private RsContractDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}