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

import com.stpl.app.exception.NoSuchRsDetailsException;
import com.stpl.app.model.RsDetails;
import com.stpl.app.service.RsDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.RsDetailsPersistence;
import com.stpl.app.service.persistence.RsDetailsUtil;

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
public class RsDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = RsDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<RsDetails> iterator = _rsDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		RsDetails rsDetails = _persistence.create(pk);

		Assert.assertNotNull(rsDetails);

		Assert.assertEquals(rsDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		RsDetails newRsDetails = addRsDetails();

		_persistence.remove(newRsDetails);

		RsDetails existingRsDetails = _persistence.fetchByPrimaryKey(newRsDetails.getPrimaryKey());

		Assert.assertNull(existingRsDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addRsDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		RsDetails newRsDetails = _persistence.create(pk);

		newRsDetails.setRebateAmount(RandomTestUtil.nextDouble());

		newRsDetails.setItemRsAttachedStatus(RandomTestUtil.nextInt());

		newRsDetails.setFormulaMethodId(RandomTestUtil.randomString());

		newRsDetails.setItemMasterSid(RandomTestUtil.nextInt());

		newRsDetails.setRebatePlanMasterSid(RandomTestUtil.nextInt());

		newRsDetails.setModifiedDate(RandomTestUtil.nextDate());

		newRsDetails.setBundleNo(RandomTestUtil.randomString());

		newRsDetails.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newRsDetails.setCreatedDate(RandomTestUtil.nextDate());

		newRsDetails.setCreatedBy(RandomTestUtil.nextInt());

		newRsDetails.setSource(RandomTestUtil.randomString());

		newRsDetails.setItemRebateEndDate(RandomTestUtil.nextDate());

		newRsDetails.setBatchId(RandomTestUtil.randomString());

		newRsDetails.setItemRebateStartDate(RandomTestUtil.nextDate());

		newRsDetails.setModifiedBy(RandomTestUtil.nextInt());

		newRsDetails.setInboundStatus(RandomTestUtil.randomString());

		newRsDetails.setRsModelSid(RandomTestUtil.nextInt());

		newRsDetails.setFormulaId(RandomTestUtil.nextInt());

		newRsDetails.setItemRsAttachedDate(RandomTestUtil.nextDate());

		newRsDetails.setIfpModelSid(RandomTestUtil.nextInt());

		newRsDetails.setDeductionCalendarMasterSid(RandomTestUtil.nextInt());

		newRsDetails.setNetSalesFormulaMasterSid(RandomTestUtil.nextInt());

		newRsDetails.setEvaluationRule(RandomTestUtil.randomString());

		newRsDetails.setNetSalesRule(RandomTestUtil.randomString());

		newRsDetails.setFormulaType(RandomTestUtil.randomString());

		newRsDetails.setCalculationRule(RandomTestUtil.randomString());

		newRsDetails.setCalculationRuleBundle(RandomTestUtil.randomString());

		newRsDetails.setEvaluationRuleBundle(RandomTestUtil.randomString());

		_rsDetailses.add(_persistence.update(newRsDetails));

		RsDetails existingRsDetails = _persistence.findByPrimaryKey(newRsDetails.getPrimaryKey());

		AssertUtils.assertEquals(existingRsDetails.getRebateAmount(),
			newRsDetails.getRebateAmount());
		Assert.assertEquals(existingRsDetails.getItemRsAttachedStatus(),
			newRsDetails.getItemRsAttachedStatus());
		Assert.assertEquals(existingRsDetails.getFormulaMethodId(),
			newRsDetails.getFormulaMethodId());
		Assert.assertEquals(existingRsDetails.getItemMasterSid(),
			newRsDetails.getItemMasterSid());
		Assert.assertEquals(existingRsDetails.getRebatePlanMasterSid(),
			newRsDetails.getRebatePlanMasterSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingRsDetails.getModifiedDate()),
			Time.getShortTimestamp(newRsDetails.getModifiedDate()));
		Assert.assertEquals(existingRsDetails.getBundleNo(),
			newRsDetails.getBundleNo());
		Assert.assertEquals(existingRsDetails.getRecordLockStatus(),
			newRsDetails.getRecordLockStatus());
		Assert.assertEquals(Time.getShortTimestamp(
				existingRsDetails.getCreatedDate()),
			Time.getShortTimestamp(newRsDetails.getCreatedDate()));
		Assert.assertEquals(existingRsDetails.getCreatedBy(),
			newRsDetails.getCreatedBy());
		Assert.assertEquals(existingRsDetails.getSource(),
			newRsDetails.getSource());
		Assert.assertEquals(Time.getShortTimestamp(
				existingRsDetails.getItemRebateEndDate()),
			Time.getShortTimestamp(newRsDetails.getItemRebateEndDate()));
		Assert.assertEquals(existingRsDetails.getBatchId(),
			newRsDetails.getBatchId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingRsDetails.getItemRebateStartDate()),
			Time.getShortTimestamp(newRsDetails.getItemRebateStartDate()));
		Assert.assertEquals(existingRsDetails.getModifiedBy(),
			newRsDetails.getModifiedBy());
		Assert.assertEquals(existingRsDetails.getInboundStatus(),
			newRsDetails.getInboundStatus());
		Assert.assertEquals(existingRsDetails.getRsDetailsSid(),
			newRsDetails.getRsDetailsSid());
		Assert.assertEquals(existingRsDetails.getRsModelSid(),
			newRsDetails.getRsModelSid());
		Assert.assertEquals(existingRsDetails.getFormulaId(),
			newRsDetails.getFormulaId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingRsDetails.getItemRsAttachedDate()),
			Time.getShortTimestamp(newRsDetails.getItemRsAttachedDate()));
		Assert.assertEquals(existingRsDetails.getIfpModelSid(),
			newRsDetails.getIfpModelSid());
		Assert.assertEquals(existingRsDetails.getDeductionCalendarMasterSid(),
			newRsDetails.getDeductionCalendarMasterSid());
		Assert.assertEquals(existingRsDetails.getNetSalesFormulaMasterSid(),
			newRsDetails.getNetSalesFormulaMasterSid());
		Assert.assertEquals(existingRsDetails.getEvaluationRule(),
			newRsDetails.getEvaluationRule());
		Assert.assertEquals(existingRsDetails.getNetSalesRule(),
			newRsDetails.getNetSalesRule());
		Assert.assertEquals(existingRsDetails.getFormulaType(),
			newRsDetails.getFormulaType());
		Assert.assertEquals(existingRsDetails.getCalculationRule(),
			newRsDetails.getCalculationRule());
		Assert.assertEquals(existingRsDetails.getCalculationRuleBundle(),
			newRsDetails.getCalculationRuleBundle());
		Assert.assertEquals(existingRsDetails.getEvaluationRuleBundle(),
			newRsDetails.getEvaluationRuleBundle());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		RsDetails newRsDetails = addRsDetails();

		RsDetails existingRsDetails = _persistence.findByPrimaryKey(newRsDetails.getPrimaryKey());

		Assert.assertEquals(existingRsDetails, newRsDetails);
	}

	@Test(expected = NoSuchRsDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<RsDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("RS_Details",
			"rebateAmount", true, "itemRsAttachedStatus", true,
			"formulaMethodId", true, "itemMasterSid", true,
			"rebatePlanMasterSid", true, "modifiedDate", true, "bundleNo",
			true, "recordLockStatus", true, "createdDate", true, "createdBy",
			true, "source", true, "itemRebateEndDate", true, "batchId", true,
			"itemRebateStartDate", true, "modifiedBy", true, "inboundStatus",
			true, "rsDetailsSid", true, "rsModelSid", true, "formulaId", true,
			"itemRsAttachedDate", true, "ifpModelSid", true,
			"deductionCalendarMasterSid", true, "netSalesFormulaMasterSid",
			true, "evaluationRule", true, "netSalesRule", true, "formulaType",
			true, "calculationRule", true, "calculationRuleBundle", true,
			"evaluationRuleBundle", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		RsDetails newRsDetails = addRsDetails();

		RsDetails existingRsDetails = _persistence.fetchByPrimaryKey(newRsDetails.getPrimaryKey());

		Assert.assertEquals(existingRsDetails, newRsDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		RsDetails missingRsDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingRsDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		RsDetails newRsDetails1 = addRsDetails();
		RsDetails newRsDetails2 = addRsDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRsDetails1.getPrimaryKey());
		primaryKeys.add(newRsDetails2.getPrimaryKey());

		Map<Serializable, RsDetails> rsDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, rsDetailses.size());
		Assert.assertEquals(newRsDetails1,
			rsDetailses.get(newRsDetails1.getPrimaryKey()));
		Assert.assertEquals(newRsDetails2,
			rsDetailses.get(newRsDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, RsDetails> rsDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(rsDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		RsDetails newRsDetails = addRsDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRsDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, RsDetails> rsDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, rsDetailses.size());
		Assert.assertEquals(newRsDetails,
			rsDetailses.get(newRsDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, RsDetails> rsDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(rsDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		RsDetails newRsDetails = addRsDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRsDetails.getPrimaryKey());

		Map<Serializable, RsDetails> rsDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, rsDetailses.size());
		Assert.assertEquals(newRsDetails,
			rsDetailses.get(newRsDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = RsDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<RsDetails>() {
				@Override
				public void performAction(RsDetails rsDetails) {
					Assert.assertNotNull(rsDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		RsDetails newRsDetails = addRsDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RsDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("rsDetailsSid",
				newRsDetails.getRsDetailsSid()));

		List<RsDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		RsDetails existingRsDetails = result.get(0);

		Assert.assertEquals(existingRsDetails, newRsDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RsDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("rsDetailsSid",
				RandomTestUtil.nextInt()));

		List<RsDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		RsDetails newRsDetails = addRsDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RsDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"rsDetailsSid"));

		Object newRsDetailsSid = newRsDetails.getRsDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("rsDetailsSid",
				new Object[] { newRsDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingRsDetailsSid = result.get(0);

		Assert.assertEquals(existingRsDetailsSid, newRsDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RsDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"rsDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("rsDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected RsDetails addRsDetails() throws Exception {
		int pk = RandomTestUtil.nextInt();

		RsDetails rsDetails = _persistence.create(pk);

		rsDetails.setRebateAmount(RandomTestUtil.nextDouble());

		rsDetails.setItemRsAttachedStatus(RandomTestUtil.nextInt());

		rsDetails.setFormulaMethodId(RandomTestUtil.randomString());

		rsDetails.setItemMasterSid(RandomTestUtil.nextInt());

		rsDetails.setRebatePlanMasterSid(RandomTestUtil.nextInt());

		rsDetails.setModifiedDate(RandomTestUtil.nextDate());

		rsDetails.setBundleNo(RandomTestUtil.randomString());

		rsDetails.setRecordLockStatus(RandomTestUtil.randomBoolean());

		rsDetails.setCreatedDate(RandomTestUtil.nextDate());

		rsDetails.setCreatedBy(RandomTestUtil.nextInt());

		rsDetails.setSource(RandomTestUtil.randomString());

		rsDetails.setItemRebateEndDate(RandomTestUtil.nextDate());

		rsDetails.setBatchId(RandomTestUtil.randomString());

		rsDetails.setItemRebateStartDate(RandomTestUtil.nextDate());

		rsDetails.setModifiedBy(RandomTestUtil.nextInt());

		rsDetails.setInboundStatus(RandomTestUtil.randomString());

		rsDetails.setRsModelSid(RandomTestUtil.nextInt());

		rsDetails.setFormulaId(RandomTestUtil.nextInt());

		rsDetails.setItemRsAttachedDate(RandomTestUtil.nextDate());

		rsDetails.setIfpModelSid(RandomTestUtil.nextInt());

		rsDetails.setDeductionCalendarMasterSid(RandomTestUtil.nextInt());

		rsDetails.setNetSalesFormulaMasterSid(RandomTestUtil.nextInt());

		rsDetails.setEvaluationRule(RandomTestUtil.randomString());

		rsDetails.setNetSalesRule(RandomTestUtil.randomString());

		rsDetails.setFormulaType(RandomTestUtil.randomString());

		rsDetails.setCalculationRule(RandomTestUtil.randomString());

		rsDetails.setCalculationRuleBundle(RandomTestUtil.randomString());

		rsDetails.setEvaluationRuleBundle(RandomTestUtil.randomString());

		_rsDetailses.add(_persistence.update(rsDetails));

		return rsDetails;
	}

	private List<RsDetails> _rsDetailses = new ArrayList<RsDetails>();
	private RsDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}