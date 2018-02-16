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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchImtdRsDetailsException;
import com.stpl.app.model.ImtdRsDetails;
import com.stpl.app.service.ImtdRsDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.ImtdRsDetailsPersistence;
import com.stpl.app.service.persistence.ImtdRsDetailsUtil;

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
public class ImtdRsDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ImtdRsDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ImtdRsDetails> iterator = _imtdRsDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ImtdRsDetails imtdRsDetails = _persistence.create(pk);

		Assert.assertNotNull(imtdRsDetails);

		Assert.assertEquals(imtdRsDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ImtdRsDetails newImtdRsDetails = addImtdRsDetails();

		_persistence.remove(newImtdRsDetails);

		ImtdRsDetails existingImtdRsDetails = _persistence.fetchByPrimaryKey(newImtdRsDetails.getPrimaryKey());

		Assert.assertNull(existingImtdRsDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addImtdRsDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ImtdRsDetails newImtdRsDetails = _persistence.create(pk);

		newImtdRsDetails.setRsDetailsModifiedDate(RandomTestUtil.nextDate());

		newImtdRsDetails.setRsDetailsBundleNo(RandomTestUtil.randomString());

		newImtdRsDetails.setItemMasterSid(RandomTestUtil.nextInt());

		newImtdRsDetails.setItemId(RandomTestUtil.randomString());

		newImtdRsDetails.setRsDetailsFormulaMethodId(RandomTestUtil.randomString());

		newImtdRsDetails.setModifiedDate(RandomTestUtil.nextDate());

		newImtdRsDetails.setCreatedDate(RandomTestUtil.nextDate());

		newImtdRsDetails.setCreatedBy(RandomTestUtil.nextInt());

		newImtdRsDetails.setUsersSid(RandomTestUtil.randomString());

		newImtdRsDetails.setContractMasterSid(RandomTestUtil.nextInt());

		newImtdRsDetails.setRsDetailsFormulaId(RandomTestUtil.randomString());

		newImtdRsDetails.setImtdCreatedDate(RandomTestUtil.nextDate());

		newImtdRsDetails.setPsModelSid(RandomTestUtil.nextInt());

		newImtdRsDetails.setModifiedBy(RandomTestUtil.nextInt());

		newImtdRsDetails.setRsDetailsCreatedDate(RandomTestUtil.nextDate());

		newImtdRsDetails.setItemNo(RandomTestUtil.randomString());

		newImtdRsDetails.setRsDetailsFormulaName(RandomTestUtil.randomString());

		newImtdRsDetails.setUdc6(RandomTestUtil.randomString());

		newImtdRsDetails.setRsDetailsCreatedBy(RandomTestUtil.randomString());

		newImtdRsDetails.setUdc5(RandomTestUtil.randomString());

		newImtdRsDetails.setIfpModelSid(RandomTestUtil.nextInt());

		newImtdRsDetails.setUdc4(RandomTestUtil.randomString());

		newImtdRsDetails.setRsDetailsFormulaNo(RandomTestUtil.randomString());

		newImtdRsDetails.setCheckRecord(RandomTestUtil.randomBoolean());

		newImtdRsDetails.setRsId(RandomTestUtil.randomString());

		newImtdRsDetails.setUdc1(RandomTestUtil.randomString());

		newImtdRsDetails.setRsDetailsRebateAmount(RandomTestUtil.nextDouble());

		newImtdRsDetails.setUdc2(RandomTestUtil.randomString());

		newImtdRsDetails.setRsDetailsModifiedBy(RandomTestUtil.randomString());

		newImtdRsDetails.setUdc3(RandomTestUtil.randomString());

		newImtdRsDetails.setRebatePlanMasterSid(RandomTestUtil.randomString());

		newImtdRsDetails.setRsDetailsAttachedDate(RandomTestUtil.nextDate());

		newImtdRsDetails.setItemRebateEndDate(RandomTestUtil.nextDate());

		newImtdRsDetails.setRsDetailsRebatePlanName(RandomTestUtil.randomString());

		newImtdRsDetails.setItemRebateStartDate(RandomTestUtil.nextDate());

		newImtdRsDetails.setRsDetailsFormulaType(RandomTestUtil.randomString());

		newImtdRsDetails.setSessionId(RandomTestUtil.randomString());

		newImtdRsDetails.setItemName(RandomTestUtil.randomString());

		newImtdRsDetails.setOperation(RandomTestUtil.randomString());

		newImtdRsDetails.setCfpModelSid(RandomTestUtil.nextInt());

		newImtdRsDetails.setRsModelSid(RandomTestUtil.nextInt());

		newImtdRsDetails.setRsDetailsSid(RandomTestUtil.nextInt());

		newImtdRsDetails.setRsDetailsAttachedStatus(RandomTestUtil.nextInt());

		newImtdRsDetails.setRsDetailsNetSalesFormulaNo(RandomTestUtil.randomString());

		newImtdRsDetails.setRsDetailsNetSalesFormulaName(RandomTestUtil.randomString());

		newImtdRsDetails.setRsDetailsDeductionCalendarNo(RandomTestUtil.randomString());

		newImtdRsDetails.setRsDetailsDeductionCalendarName(RandomTestUtil.randomString());

		newImtdRsDetails.setDeductionCalendarMasterSid(RandomTestUtil.nextInt());

		newImtdRsDetails.setNetSalesFormulaMasterSid(RandomTestUtil.nextInt());

		newImtdRsDetails.setEvaluationRule(RandomTestUtil.randomString());

		newImtdRsDetails.setNetSalesRule(RandomTestUtil.randomString());

		newImtdRsDetails.setFormulaType(RandomTestUtil.randomString());

		newImtdRsDetails.setCalculationRule(RandomTestUtil.randomString());

		newImtdRsDetails.setCalculationRuleBundle(RandomTestUtil.randomString());

		newImtdRsDetails.setEvaluationRuleBundle(RandomTestUtil.randomString());

		_imtdRsDetailses.add(_persistence.update(newImtdRsDetails));

		ImtdRsDetails existingImtdRsDetails = _persistence.findByPrimaryKey(newImtdRsDetails.getPrimaryKey());

		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdRsDetails.getRsDetailsModifiedDate()),
			Time.getShortTimestamp(newImtdRsDetails.getRsDetailsModifiedDate()));
		Assert.assertEquals(existingImtdRsDetails.getRsDetailsBundleNo(),
			newImtdRsDetails.getRsDetailsBundleNo());
		Assert.assertEquals(existingImtdRsDetails.getItemMasterSid(),
			newImtdRsDetails.getItemMasterSid());
		Assert.assertEquals(existingImtdRsDetails.getImtdRsDetailsSid(),
			newImtdRsDetails.getImtdRsDetailsSid());
		Assert.assertEquals(existingImtdRsDetails.getItemId(),
			newImtdRsDetails.getItemId());
		Assert.assertEquals(existingImtdRsDetails.getRsDetailsFormulaMethodId(),
			newImtdRsDetails.getRsDetailsFormulaMethodId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdRsDetails.getModifiedDate()),
			Time.getShortTimestamp(newImtdRsDetails.getModifiedDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdRsDetails.getCreatedDate()),
			Time.getShortTimestamp(newImtdRsDetails.getCreatedDate()));
		Assert.assertEquals(existingImtdRsDetails.getCreatedBy(),
			newImtdRsDetails.getCreatedBy());
		Assert.assertEquals(existingImtdRsDetails.getUsersSid(),
			newImtdRsDetails.getUsersSid());
		Assert.assertEquals(existingImtdRsDetails.getContractMasterSid(),
			newImtdRsDetails.getContractMasterSid());
		Assert.assertEquals(existingImtdRsDetails.getRsDetailsFormulaId(),
			newImtdRsDetails.getRsDetailsFormulaId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdRsDetails.getImtdCreatedDate()),
			Time.getShortTimestamp(newImtdRsDetails.getImtdCreatedDate()));
		Assert.assertEquals(existingImtdRsDetails.getPsModelSid(),
			newImtdRsDetails.getPsModelSid());
		Assert.assertEquals(existingImtdRsDetails.getModifiedBy(),
			newImtdRsDetails.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdRsDetails.getRsDetailsCreatedDate()),
			Time.getShortTimestamp(newImtdRsDetails.getRsDetailsCreatedDate()));
		Assert.assertEquals(existingImtdRsDetails.getItemNo(),
			newImtdRsDetails.getItemNo());
		Assert.assertEquals(existingImtdRsDetails.getRsDetailsFormulaName(),
			newImtdRsDetails.getRsDetailsFormulaName());
		Assert.assertEquals(existingImtdRsDetails.getUdc6(),
			newImtdRsDetails.getUdc6());
		Assert.assertEquals(existingImtdRsDetails.getRsDetailsCreatedBy(),
			newImtdRsDetails.getRsDetailsCreatedBy());
		Assert.assertEquals(existingImtdRsDetails.getUdc5(),
			newImtdRsDetails.getUdc5());
		Assert.assertEquals(existingImtdRsDetails.getIfpModelSid(),
			newImtdRsDetails.getIfpModelSid());
		Assert.assertEquals(existingImtdRsDetails.getUdc4(),
			newImtdRsDetails.getUdc4());
		Assert.assertEquals(existingImtdRsDetails.getRsDetailsFormulaNo(),
			newImtdRsDetails.getRsDetailsFormulaNo());
		Assert.assertEquals(existingImtdRsDetails.getCheckRecord(),
			newImtdRsDetails.getCheckRecord());
		Assert.assertEquals(existingImtdRsDetails.getRsId(),
			newImtdRsDetails.getRsId());
		Assert.assertEquals(existingImtdRsDetails.getUdc1(),
			newImtdRsDetails.getUdc1());
		AssertUtils.assertEquals(existingImtdRsDetails.getRsDetailsRebateAmount(),
			newImtdRsDetails.getRsDetailsRebateAmount());
		Assert.assertEquals(existingImtdRsDetails.getUdc2(),
			newImtdRsDetails.getUdc2());
		Assert.assertEquals(existingImtdRsDetails.getRsDetailsModifiedBy(),
			newImtdRsDetails.getRsDetailsModifiedBy());
		Assert.assertEquals(existingImtdRsDetails.getUdc3(),
			newImtdRsDetails.getUdc3());
		Assert.assertEquals(existingImtdRsDetails.getRebatePlanMasterSid(),
			newImtdRsDetails.getRebatePlanMasterSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdRsDetails.getRsDetailsAttachedDate()),
			Time.getShortTimestamp(newImtdRsDetails.getRsDetailsAttachedDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdRsDetails.getItemRebateEndDate()),
			Time.getShortTimestamp(newImtdRsDetails.getItemRebateEndDate()));
		Assert.assertEquals(existingImtdRsDetails.getRsDetailsRebatePlanName(),
			newImtdRsDetails.getRsDetailsRebatePlanName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdRsDetails.getItemRebateStartDate()),
			Time.getShortTimestamp(newImtdRsDetails.getItemRebateStartDate()));
		Assert.assertEquals(existingImtdRsDetails.getRsDetailsFormulaType(),
			newImtdRsDetails.getRsDetailsFormulaType());
		Assert.assertEquals(existingImtdRsDetails.getSessionId(),
			newImtdRsDetails.getSessionId());
		Assert.assertEquals(existingImtdRsDetails.getItemName(),
			newImtdRsDetails.getItemName());
		Assert.assertEquals(existingImtdRsDetails.getOperation(),
			newImtdRsDetails.getOperation());
		Assert.assertEquals(existingImtdRsDetails.getCfpModelSid(),
			newImtdRsDetails.getCfpModelSid());
		Assert.assertEquals(existingImtdRsDetails.getRsModelSid(),
			newImtdRsDetails.getRsModelSid());
		Assert.assertEquals(existingImtdRsDetails.getRsDetailsSid(),
			newImtdRsDetails.getRsDetailsSid());
		Assert.assertEquals(existingImtdRsDetails.getRsDetailsAttachedStatus(),
			newImtdRsDetails.getRsDetailsAttachedStatus());
		Assert.assertEquals(existingImtdRsDetails.getRsDetailsNetSalesFormulaNo(),
			newImtdRsDetails.getRsDetailsNetSalesFormulaNo());
		Assert.assertEquals(existingImtdRsDetails.getRsDetailsNetSalesFormulaName(),
			newImtdRsDetails.getRsDetailsNetSalesFormulaName());
		Assert.assertEquals(existingImtdRsDetails.getRsDetailsDeductionCalendarNo(),
			newImtdRsDetails.getRsDetailsDeductionCalendarNo());
		Assert.assertEquals(existingImtdRsDetails.getRsDetailsDeductionCalendarName(),
			newImtdRsDetails.getRsDetailsDeductionCalendarName());
		Assert.assertEquals(existingImtdRsDetails.getDeductionCalendarMasterSid(),
			newImtdRsDetails.getDeductionCalendarMasterSid());
		Assert.assertEquals(existingImtdRsDetails.getNetSalesFormulaMasterSid(),
			newImtdRsDetails.getNetSalesFormulaMasterSid());
		Assert.assertEquals(existingImtdRsDetails.getEvaluationRule(),
			newImtdRsDetails.getEvaluationRule());
		Assert.assertEquals(existingImtdRsDetails.getNetSalesRule(),
			newImtdRsDetails.getNetSalesRule());
		Assert.assertEquals(existingImtdRsDetails.getFormulaType(),
			newImtdRsDetails.getFormulaType());
		Assert.assertEquals(existingImtdRsDetails.getCalculationRule(),
			newImtdRsDetails.getCalculationRule());
		Assert.assertEquals(existingImtdRsDetails.getCalculationRuleBundle(),
			newImtdRsDetails.getCalculationRuleBundle());
		Assert.assertEquals(existingImtdRsDetails.getEvaluationRuleBundle(),
			newImtdRsDetails.getEvaluationRuleBundle());
	}

	@Test
	public void testCountByTempRsSearch() throws Exception {
		_persistence.countByTempRsSearch(StringPool.BLANK, StringPool.BLANK,
			RandomTestUtil.nextDate());

		_persistence.countByTempRsSearch(StringPool.NULL, StringPool.NULL,
			RandomTestUtil.nextDate());

		_persistence.countByTempRsSearch((String)null, (String)null,
			RandomTestUtil.nextDate());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ImtdRsDetails newImtdRsDetails = addImtdRsDetails();

		ImtdRsDetails existingImtdRsDetails = _persistence.findByPrimaryKey(newImtdRsDetails.getPrimaryKey());

		Assert.assertEquals(existingImtdRsDetails, newImtdRsDetails);
	}

	@Test(expected = NoSuchImtdRsDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<ImtdRsDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("IMTD_RS_DETAILS",
			"rsDetailsModifiedDate", true, "rsDetailsBundleNo", true,
			"itemMasterSid", true, "imtdRsDetailsSid", true, "itemId", true,
			"rsDetailsFormulaMethodId", true, "modifiedDate", true,
			"createdDate", true, "createdBy", true, "usersSid", true,
			"contractMasterSid", true, "rsDetailsFormulaId", true,
			"imtdCreatedDate", true, "psModelSid", true, "modifiedBy", true,
			"rsDetailsCreatedDate", true, "itemNo", true,
			"rsDetailsFormulaName", true, "udc6", true, "rsDetailsCreatedBy",
			true, "udc5", true, "ifpModelSid", true, "udc4", true,
			"rsDetailsFormulaNo", true, "checkRecord", true, "rsId", true,
			"udc1", true, "rsDetailsRebateAmount", true, "udc2", true,
			"rsDetailsModifiedBy", true, "udc3", true, "rebatePlanMasterSid",
			true, "rsDetailsAttachedDate", true, "itemRebateEndDate", true,
			"rsDetailsRebatePlanName", true, "itemRebateStartDate", true,
			"rsDetailsFormulaType", true, "sessionId", true, "itemName", true,
			"operation", true, "cfpModelSid", true, "rsModelSid", true,
			"rsDetailsSid", true, "rsDetailsAttachedStatus", true,
			"rsDetailsNetSalesFormulaNo", true, "rsDetailsNetSalesFormulaName",
			true, "rsDetailsDeductionCalendarNo", true,
			"rsDetailsDeductionCalendarName", true,
			"deductionCalendarMasterSid", true, "netSalesFormulaMasterSid",
			true, "evaluationRule", true, "netSalesRule", true, "formulaType",
			true, "calculationRule", true, "calculationRuleBundle", true,
			"evaluationRuleBundle", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ImtdRsDetails newImtdRsDetails = addImtdRsDetails();

		ImtdRsDetails existingImtdRsDetails = _persistence.fetchByPrimaryKey(newImtdRsDetails.getPrimaryKey());

		Assert.assertEquals(existingImtdRsDetails, newImtdRsDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ImtdRsDetails missingImtdRsDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingImtdRsDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ImtdRsDetails newImtdRsDetails1 = addImtdRsDetails();
		ImtdRsDetails newImtdRsDetails2 = addImtdRsDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newImtdRsDetails1.getPrimaryKey());
		primaryKeys.add(newImtdRsDetails2.getPrimaryKey());

		Map<Serializable, ImtdRsDetails> imtdRsDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, imtdRsDetailses.size());
		Assert.assertEquals(newImtdRsDetails1,
			imtdRsDetailses.get(newImtdRsDetails1.getPrimaryKey()));
		Assert.assertEquals(newImtdRsDetails2,
			imtdRsDetailses.get(newImtdRsDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ImtdRsDetails> imtdRsDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(imtdRsDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ImtdRsDetails newImtdRsDetails = addImtdRsDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newImtdRsDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ImtdRsDetails> imtdRsDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, imtdRsDetailses.size());
		Assert.assertEquals(newImtdRsDetails,
			imtdRsDetailses.get(newImtdRsDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ImtdRsDetails> imtdRsDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(imtdRsDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ImtdRsDetails newImtdRsDetails = addImtdRsDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newImtdRsDetails.getPrimaryKey());

		Map<Serializable, ImtdRsDetails> imtdRsDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, imtdRsDetailses.size());
		Assert.assertEquals(newImtdRsDetails,
			imtdRsDetailses.get(newImtdRsDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ImtdRsDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ImtdRsDetails>() {
				@Override
				public void performAction(ImtdRsDetails imtdRsDetails) {
					Assert.assertNotNull(imtdRsDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ImtdRsDetails newImtdRsDetails = addImtdRsDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdRsDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("imtdRsDetailsSid",
				newImtdRsDetails.getImtdRsDetailsSid()));

		List<ImtdRsDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ImtdRsDetails existingImtdRsDetails = result.get(0);

		Assert.assertEquals(existingImtdRsDetails, newImtdRsDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdRsDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("imtdRsDetailsSid",
				RandomTestUtil.nextInt()));

		List<ImtdRsDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ImtdRsDetails newImtdRsDetails = addImtdRsDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdRsDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"imtdRsDetailsSid"));

		Object newImtdRsDetailsSid = newImtdRsDetails.getImtdRsDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("imtdRsDetailsSid",
				new Object[] { newImtdRsDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingImtdRsDetailsSid = result.get(0);

		Assert.assertEquals(existingImtdRsDetailsSid, newImtdRsDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdRsDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"imtdRsDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("imtdRsDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ImtdRsDetails addImtdRsDetails() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ImtdRsDetails imtdRsDetails = _persistence.create(pk);

		imtdRsDetails.setRsDetailsModifiedDate(RandomTestUtil.nextDate());

		imtdRsDetails.setRsDetailsBundleNo(RandomTestUtil.randomString());

		imtdRsDetails.setItemMasterSid(RandomTestUtil.nextInt());

		imtdRsDetails.setItemId(RandomTestUtil.randomString());

		imtdRsDetails.setRsDetailsFormulaMethodId(RandomTestUtil.randomString());

		imtdRsDetails.setModifiedDate(RandomTestUtil.nextDate());

		imtdRsDetails.setCreatedDate(RandomTestUtil.nextDate());

		imtdRsDetails.setCreatedBy(RandomTestUtil.nextInt());

		imtdRsDetails.setUsersSid(RandomTestUtil.randomString());

		imtdRsDetails.setContractMasterSid(RandomTestUtil.nextInt());

		imtdRsDetails.setRsDetailsFormulaId(RandomTestUtil.randomString());

		imtdRsDetails.setImtdCreatedDate(RandomTestUtil.nextDate());

		imtdRsDetails.setPsModelSid(RandomTestUtil.nextInt());

		imtdRsDetails.setModifiedBy(RandomTestUtil.nextInt());

		imtdRsDetails.setRsDetailsCreatedDate(RandomTestUtil.nextDate());

		imtdRsDetails.setItemNo(RandomTestUtil.randomString());

		imtdRsDetails.setRsDetailsFormulaName(RandomTestUtil.randomString());

		imtdRsDetails.setUdc6(RandomTestUtil.randomString());

		imtdRsDetails.setRsDetailsCreatedBy(RandomTestUtil.randomString());

		imtdRsDetails.setUdc5(RandomTestUtil.randomString());

		imtdRsDetails.setIfpModelSid(RandomTestUtil.nextInt());

		imtdRsDetails.setUdc4(RandomTestUtil.randomString());

		imtdRsDetails.setRsDetailsFormulaNo(RandomTestUtil.randomString());

		imtdRsDetails.setCheckRecord(RandomTestUtil.randomBoolean());

		imtdRsDetails.setRsId(RandomTestUtil.randomString());

		imtdRsDetails.setUdc1(RandomTestUtil.randomString());

		imtdRsDetails.setRsDetailsRebateAmount(RandomTestUtil.nextDouble());

		imtdRsDetails.setUdc2(RandomTestUtil.randomString());

		imtdRsDetails.setRsDetailsModifiedBy(RandomTestUtil.randomString());

		imtdRsDetails.setUdc3(RandomTestUtil.randomString());

		imtdRsDetails.setRebatePlanMasterSid(RandomTestUtil.randomString());

		imtdRsDetails.setRsDetailsAttachedDate(RandomTestUtil.nextDate());

		imtdRsDetails.setItemRebateEndDate(RandomTestUtil.nextDate());

		imtdRsDetails.setRsDetailsRebatePlanName(RandomTestUtil.randomString());

		imtdRsDetails.setItemRebateStartDate(RandomTestUtil.nextDate());

		imtdRsDetails.setRsDetailsFormulaType(RandomTestUtil.randomString());

		imtdRsDetails.setSessionId(RandomTestUtil.randomString());

		imtdRsDetails.setItemName(RandomTestUtil.randomString());

		imtdRsDetails.setOperation(RandomTestUtil.randomString());

		imtdRsDetails.setCfpModelSid(RandomTestUtil.nextInt());

		imtdRsDetails.setRsModelSid(RandomTestUtil.nextInt());

		imtdRsDetails.setRsDetailsSid(RandomTestUtil.nextInt());

		imtdRsDetails.setRsDetailsAttachedStatus(RandomTestUtil.nextInt());

		imtdRsDetails.setRsDetailsNetSalesFormulaNo(RandomTestUtil.randomString());

		imtdRsDetails.setRsDetailsNetSalesFormulaName(RandomTestUtil.randomString());

		imtdRsDetails.setRsDetailsDeductionCalendarNo(RandomTestUtil.randomString());

		imtdRsDetails.setRsDetailsDeductionCalendarName(RandomTestUtil.randomString());

		imtdRsDetails.setDeductionCalendarMasterSid(RandomTestUtil.nextInt());

		imtdRsDetails.setNetSalesFormulaMasterSid(RandomTestUtil.nextInt());

		imtdRsDetails.setEvaluationRule(RandomTestUtil.randomString());

		imtdRsDetails.setNetSalesRule(RandomTestUtil.randomString());

		imtdRsDetails.setFormulaType(RandomTestUtil.randomString());

		imtdRsDetails.setCalculationRule(RandomTestUtil.randomString());

		imtdRsDetails.setCalculationRuleBundle(RandomTestUtil.randomString());

		imtdRsDetails.setEvaluationRuleBundle(RandomTestUtil.randomString());

		_imtdRsDetailses.add(_persistence.update(imtdRsDetails));

		return imtdRsDetails;
	}

	private List<ImtdRsDetails> _imtdRsDetailses = new ArrayList<ImtdRsDetails>();
	private ImtdRsDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}