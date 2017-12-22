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

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.AssertUtils;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.parttwo.exception.NoSuchStCffOutboundMasterException;
import com.stpl.app.parttwo.model.StCffOutboundMaster;
import com.stpl.app.parttwo.service.StCffOutboundMasterLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.StCffOutboundMasterPK;
import com.stpl.app.parttwo.service.persistence.StCffOutboundMasterPersistence;
import com.stpl.app.parttwo.service.persistence.StCffOutboundMasterUtil;

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
public class StCffOutboundMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = StCffOutboundMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<StCffOutboundMaster> iterator = _stCffOutboundMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		StCffOutboundMasterPK pk = new StCffOutboundMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		StCffOutboundMaster stCffOutboundMaster = _persistence.create(pk);

		Assert.assertNotNull(stCffOutboundMaster);

		Assert.assertEquals(stCffOutboundMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		StCffOutboundMaster newStCffOutboundMaster = addStCffOutboundMaster();

		_persistence.remove(newStCffOutboundMaster);

		StCffOutboundMaster existingStCffOutboundMaster = _persistence.fetchByPrimaryKey(newStCffOutboundMaster.getPrimaryKey());

		Assert.assertNull(existingStCffOutboundMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addStCffOutboundMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		StCffOutboundMasterPK pk = new StCffOutboundMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		StCffOutboundMaster newStCffOutboundMaster = _persistence.create(pk);

		newStCffOutboundMaster.setEtlCheckRecord(RandomTestUtil.randomBoolean());

		newStCffOutboundMaster.setCustomerName(RandomTestUtil.randomString());

		newStCffOutboundMaster.setContractHolderId(RandomTestUtil.randomString());

		newStCffOutboundMaster.setBusinessUnitNo(RandomTestUtil.randomString());

		newStCffOutboundMaster.setYear(RandomTestUtil.randomString());

		newStCffOutboundMaster.setFinancialForecastApprovalDate(RandomTestUtil.nextDate());

		newStCffOutboundMaster.setDeductionId(RandomTestUtil.randomString());

		newStCffOutboundMaster.setModifiedDate(RandomTestUtil.nextDate());

		newStCffOutboundMaster.setDeductionPerUnit(RandomTestUtil.nextDouble());

		newStCffOutboundMaster.setCogsPerUnit(RandomTestUtil.nextDouble());

		newStCffOutboundMaster.setContractType(RandomTestUtil.randomString());

		newStCffOutboundMaster.setSource(RandomTestUtil.randomString());

		newStCffOutboundMaster.setBusinessUnitName(RandomTestUtil.randomString());

		newStCffOutboundMaster.setContractMasterSid(RandomTestUtil.nextInt());

		newStCffOutboundMaster.setFinancialForecastId(RandomTestUtil.randomString());

		newStCffOutboundMaster.setProjectId(RandomTestUtil.randomString());

		newStCffOutboundMaster.setCustomerNo(RandomTestUtil.randomString());

		newStCffOutboundMaster.setModifiedBy(RandomTestUtil.randomString());

		newStCffOutboundMaster.setSalesDollars(RandomTestUtil.nextDouble());

		newStCffOutboundMaster.setMonth(RandomTestUtil.nextInt());

		newStCffOutboundMaster.setType(RandomTestUtil.randomString());

		newStCffOutboundMaster.setDeductionType(RandomTestUtil.randomString());

		newStCffOutboundMaster.setCompanyMasterSid(RandomTestUtil.nextInt());

		newStCffOutboundMaster.setCheckRecord(RandomTestUtil.randomBoolean());

		newStCffOutboundMaster.setContractName(RandomTestUtil.randomString());

		newStCffOutboundMaster.setDeductionRate(RandomTestUtil.nextDouble());

		newStCffOutboundMaster.setDeductionCategory(RandomTestUtil.randomString());

		newStCffOutboundMaster.setCogsAmount(RandomTestUtil.nextDouble());

		newStCffOutboundMaster.setDeductionNo(RandomTestUtil.randomString());

		newStCffOutboundMaster.setFinancialForecastCreationDate(RandomTestUtil.nextDate());

		newStCffOutboundMaster.setCompanyNo(RandomTestUtil.randomString());

		newStCffOutboundMaster.setSalesUnits(RandomTestUtil.nextDouble());

		newStCffOutboundMaster.setItemName(RandomTestUtil.randomString());

		newStCffOutboundMaster.setDeductionInclusion(RandomTestUtil.randomString());

		newStCffOutboundMaster.setContractHolderName(RandomTestUtil.randomString());

		newStCffOutboundMaster.setItemMasterSid(RandomTestUtil.nextInt());

		newStCffOutboundMaster.setCompanyName(RandomTestUtil.randomString());

		newStCffOutboundMaster.setCustomerId(RandomTestUtil.randomString());

		newStCffOutboundMaster.setItemId(RandomTestUtil.randomString());

		newStCffOutboundMaster.setNetProfitDollars(RandomTestUtil.nextDouble());

		newStCffOutboundMaster.setGlCompanyMasterSid(RandomTestUtil.nextInt());

		newStCffOutboundMaster.setCreatedDate(RandomTestUtil.nextDate());

		newStCffOutboundMaster.setCreatedBy(RandomTestUtil.randomString());

		newStCffOutboundMaster.setDeductionCategory1(RandomTestUtil.randomString());

		newStCffOutboundMaster.setDeductionCategory2(RandomTestUtil.randomString());

		newStCffOutboundMaster.setContractHolderNo(RandomTestUtil.randomString());

		newStCffOutboundMaster.setDeductionCategory3(RandomTestUtil.randomString());

		newStCffOutboundMaster.setItemNo(RandomTestUtil.randomString());

		newStCffOutboundMaster.setDeductionCategory4(RandomTestUtil.randomString());

		newStCffOutboundMaster.setDeductionCategory5(RandomTestUtil.randomString());

		newStCffOutboundMaster.setDeductionCategory6(RandomTestUtil.randomString());

		newStCffOutboundMaster.setContractId(RandomTestUtil.randomString());

		newStCffOutboundMaster.setDeductionProgram(RandomTestUtil.randomString());

		newStCffOutboundMaster.setBusinessUnitId(RandomTestUtil.randomString());

		newStCffOutboundMaster.setProjectionName(RandomTestUtil.randomString());

		newStCffOutboundMaster.setCompanyId(RandomTestUtil.randomString());

		newStCffOutboundMaster.setOutboundStatus(RandomTestUtil.randomString());

		newStCffOutboundMaster.setOriginalBatchId(RandomTestUtil.randomString());

		newStCffOutboundMaster.setDeductionName(RandomTestUtil.randomString());

		newStCffOutboundMaster.setNetProfitPerUnit(RandomTestUtil.nextDouble());

		newStCffOutboundMaster.setSalesInclusion(RandomTestUtil.randomString());

		newStCffOutboundMaster.setBatchId(RandomTestUtil.randomString());

		newStCffOutboundMaster.setFinancialForecastName(RandomTestUtil.randomString());

		newStCffOutboundMaster.setNetSalesDollar(RandomTestUtil.nextDouble());

		newStCffOutboundMaster.setDeductionDollars(RandomTestUtil.nextDouble());

		newStCffOutboundMaster.setContractNo(RandomTestUtil.randomString());

		_stCffOutboundMasters.add(_persistence.update(newStCffOutboundMaster));

		StCffOutboundMaster existingStCffOutboundMaster = _persistence.findByPrimaryKey(newStCffOutboundMaster.getPrimaryKey());

		Assert.assertEquals(existingStCffOutboundMaster.getEtlCheckRecord(),
			newStCffOutboundMaster.getEtlCheckRecord());
		Assert.assertEquals(existingStCffOutboundMaster.getCustomerName(),
			newStCffOutboundMaster.getCustomerName());
		Assert.assertEquals(existingStCffOutboundMaster.getContractHolderId(),
			newStCffOutboundMaster.getContractHolderId());
		Assert.assertEquals(existingStCffOutboundMaster.getBusinessUnitNo(),
			newStCffOutboundMaster.getBusinessUnitNo());
		Assert.assertEquals(existingStCffOutboundMaster.getYear(),
			newStCffOutboundMaster.getYear());
		Assert.assertEquals(Time.getShortTimestamp(
				existingStCffOutboundMaster.getFinancialForecastApprovalDate()),
			Time.getShortTimestamp(
				newStCffOutboundMaster.getFinancialForecastApprovalDate()));
		Assert.assertEquals(existingStCffOutboundMaster.getDeductionId(),
			newStCffOutboundMaster.getDeductionId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingStCffOutboundMaster.getModifiedDate()),
			Time.getShortTimestamp(newStCffOutboundMaster.getModifiedDate()));
		AssertUtils.assertEquals(existingStCffOutboundMaster.getDeductionPerUnit(),
			newStCffOutboundMaster.getDeductionPerUnit());
		AssertUtils.assertEquals(existingStCffOutboundMaster.getCogsPerUnit(),
			newStCffOutboundMaster.getCogsPerUnit());
		Assert.assertEquals(existingStCffOutboundMaster.getContractType(),
			newStCffOutboundMaster.getContractType());
		Assert.assertEquals(existingStCffOutboundMaster.getSource(),
			newStCffOutboundMaster.getSource());
		Assert.assertEquals(existingStCffOutboundMaster.getBusinessUnitName(),
			newStCffOutboundMaster.getBusinessUnitName());
		Assert.assertEquals(existingStCffOutboundMaster.getContractMasterSid(),
			newStCffOutboundMaster.getContractMasterSid());
		Assert.assertEquals(existingStCffOutboundMaster.getFinancialForecastId(),
			newStCffOutboundMaster.getFinancialForecastId());
		Assert.assertEquals(existingStCffOutboundMaster.getProjectId(),
			newStCffOutboundMaster.getProjectId());
		Assert.assertEquals(existingStCffOutboundMaster.getCustomerNo(),
			newStCffOutboundMaster.getCustomerNo());
		Assert.assertEquals(existingStCffOutboundMaster.getModifiedBy(),
			newStCffOutboundMaster.getModifiedBy());
		AssertUtils.assertEquals(existingStCffOutboundMaster.getSalesDollars(),
			newStCffOutboundMaster.getSalesDollars());
		Assert.assertEquals(existingStCffOutboundMaster.getMonth(),
			newStCffOutboundMaster.getMonth());
		Assert.assertEquals(existingStCffOutboundMaster.getCffDetailsSid(),
			newStCffOutboundMaster.getCffDetailsSid());
		Assert.assertEquals(existingStCffOutboundMaster.getType(),
			newStCffOutboundMaster.getType());
		Assert.assertEquals(existingStCffOutboundMaster.getDeductionType(),
			newStCffOutboundMaster.getDeductionType());
		Assert.assertEquals(existingStCffOutboundMaster.getCompanyMasterSid(),
			newStCffOutboundMaster.getCompanyMasterSid());
		Assert.assertEquals(existingStCffOutboundMaster.getCheckRecord(),
			newStCffOutboundMaster.getCheckRecord());
		Assert.assertEquals(existingStCffOutboundMaster.getContractName(),
			newStCffOutboundMaster.getContractName());
		AssertUtils.assertEquals(existingStCffOutboundMaster.getDeductionRate(),
			newStCffOutboundMaster.getDeductionRate());
		Assert.assertEquals(existingStCffOutboundMaster.getDeductionCategory(),
			newStCffOutboundMaster.getDeductionCategory());
		AssertUtils.assertEquals(existingStCffOutboundMaster.getCogsAmount(),
			newStCffOutboundMaster.getCogsAmount());
		Assert.assertEquals(existingStCffOutboundMaster.getDeductionNo(),
			newStCffOutboundMaster.getDeductionNo());
		Assert.assertEquals(Time.getShortTimestamp(
				existingStCffOutboundMaster.getFinancialForecastCreationDate()),
			Time.getShortTimestamp(
				newStCffOutboundMaster.getFinancialForecastCreationDate()));
		Assert.assertEquals(existingStCffOutboundMaster.getCompanyNo(),
			newStCffOutboundMaster.getCompanyNo());
		AssertUtils.assertEquals(existingStCffOutboundMaster.getSalesUnits(),
			newStCffOutboundMaster.getSalesUnits());
		Assert.assertEquals(existingStCffOutboundMaster.getSessionId(),
			newStCffOutboundMaster.getSessionId());
		Assert.assertEquals(existingStCffOutboundMaster.getItemName(),
			newStCffOutboundMaster.getItemName());
		Assert.assertEquals(existingStCffOutboundMaster.getDeductionInclusion(),
			newStCffOutboundMaster.getDeductionInclusion());
		Assert.assertEquals(existingStCffOutboundMaster.getRsModelSid(),
			newStCffOutboundMaster.getRsModelSid());
		Assert.assertEquals(existingStCffOutboundMaster.getContractHolderName(),
			newStCffOutboundMaster.getContractHolderName());
		Assert.assertEquals(existingStCffOutboundMaster.getItemMasterSid(),
			newStCffOutboundMaster.getItemMasterSid());
		Assert.assertEquals(existingStCffOutboundMaster.getCompanyName(),
			newStCffOutboundMaster.getCompanyName());
		Assert.assertEquals(existingStCffOutboundMaster.getCustomerId(),
			newStCffOutboundMaster.getCustomerId());
		Assert.assertEquals(existingStCffOutboundMaster.getItemId(),
			newStCffOutboundMaster.getItemId());
		AssertUtils.assertEquals(existingStCffOutboundMaster.getNetProfitDollars(),
			newStCffOutboundMaster.getNetProfitDollars());
		Assert.assertEquals(existingStCffOutboundMaster.getGlCompanyMasterSid(),
			newStCffOutboundMaster.getGlCompanyMasterSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingStCffOutboundMaster.getCreatedDate()),
			Time.getShortTimestamp(newStCffOutboundMaster.getCreatedDate()));
		Assert.assertEquals(existingStCffOutboundMaster.getCreatedBy(),
			newStCffOutboundMaster.getCreatedBy());
		Assert.assertEquals(existingStCffOutboundMaster.getDeductionCategory1(),
			newStCffOutboundMaster.getDeductionCategory1());
		Assert.assertEquals(existingStCffOutboundMaster.getDeductionCategory2(),
			newStCffOutboundMaster.getDeductionCategory2());
		Assert.assertEquals(existingStCffOutboundMaster.getContractHolderNo(),
			newStCffOutboundMaster.getContractHolderNo());
		Assert.assertEquals(existingStCffOutboundMaster.getDeductionCategory3(),
			newStCffOutboundMaster.getDeductionCategory3());
		Assert.assertEquals(existingStCffOutboundMaster.getItemNo(),
			newStCffOutboundMaster.getItemNo());
		Assert.assertEquals(existingStCffOutboundMaster.getDeductionCategory4(),
			newStCffOutboundMaster.getDeductionCategory4());
		Assert.assertEquals(existingStCffOutboundMaster.getDeductionCategory5(),
			newStCffOutboundMaster.getDeductionCategory5());
		Assert.assertEquals(existingStCffOutboundMaster.getDeductionCategory6(),
			newStCffOutboundMaster.getDeductionCategory6());
		Assert.assertEquals(existingStCffOutboundMaster.getContractId(),
			newStCffOutboundMaster.getContractId());
		Assert.assertEquals(existingStCffOutboundMaster.getDeductionProgram(),
			newStCffOutboundMaster.getDeductionProgram());
		Assert.assertEquals(existingStCffOutboundMaster.getBusinessUnitId(),
			newStCffOutboundMaster.getBusinessUnitId());
		Assert.assertEquals(existingStCffOutboundMaster.getProjectionName(),
			newStCffOutboundMaster.getProjectionName());
		Assert.assertEquals(existingStCffOutboundMaster.getUserId(),
			newStCffOutboundMaster.getUserId());
		Assert.assertEquals(existingStCffOutboundMaster.getCompanyId(),
			newStCffOutboundMaster.getCompanyId());
		Assert.assertEquals(existingStCffOutboundMaster.getOutboundStatus(),
			newStCffOutboundMaster.getOutboundStatus());
		Assert.assertEquals(existingStCffOutboundMaster.getOriginalBatchId(),
			newStCffOutboundMaster.getOriginalBatchId());
		Assert.assertEquals(existingStCffOutboundMaster.getDeductionName(),
			newStCffOutboundMaster.getDeductionName());
		AssertUtils.assertEquals(existingStCffOutboundMaster.getNetProfitPerUnit(),
			newStCffOutboundMaster.getNetProfitPerUnit());
		Assert.assertEquals(existingStCffOutboundMaster.getPeriodSid(),
			newStCffOutboundMaster.getPeriodSid());
		Assert.assertEquals(existingStCffOutboundMaster.getSalesInclusion(),
			newStCffOutboundMaster.getSalesInclusion());
		Assert.assertEquals(existingStCffOutboundMaster.getBatchId(),
			newStCffOutboundMaster.getBatchId());
		Assert.assertEquals(existingStCffOutboundMaster.getFinancialForecastName(),
			newStCffOutboundMaster.getFinancialForecastName());
		AssertUtils.assertEquals(existingStCffOutboundMaster.getNetSalesDollar(),
			newStCffOutboundMaster.getNetSalesDollar());
		AssertUtils.assertEquals(existingStCffOutboundMaster.getDeductionDollars(),
			newStCffOutboundMaster.getDeductionDollars());
		Assert.assertEquals(existingStCffOutboundMaster.getContractNo(),
			newStCffOutboundMaster.getContractNo());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		StCffOutboundMaster newStCffOutboundMaster = addStCffOutboundMaster();

		StCffOutboundMaster existingStCffOutboundMaster = _persistence.findByPrimaryKey(newStCffOutboundMaster.getPrimaryKey());

		Assert.assertEquals(existingStCffOutboundMaster, newStCffOutboundMaster);
	}

	@Test(expected = NoSuchStCffOutboundMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		StCffOutboundMasterPK pk = new StCffOutboundMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		StCffOutboundMaster newStCffOutboundMaster = addStCffOutboundMaster();

		StCffOutboundMaster existingStCffOutboundMaster = _persistence.fetchByPrimaryKey(newStCffOutboundMaster.getPrimaryKey());

		Assert.assertEquals(existingStCffOutboundMaster, newStCffOutboundMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		StCffOutboundMasterPK pk = new StCffOutboundMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		StCffOutboundMaster missingStCffOutboundMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingStCffOutboundMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		StCffOutboundMaster newStCffOutboundMaster1 = addStCffOutboundMaster();
		StCffOutboundMaster newStCffOutboundMaster2 = addStCffOutboundMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStCffOutboundMaster1.getPrimaryKey());
		primaryKeys.add(newStCffOutboundMaster2.getPrimaryKey());

		Map<Serializable, StCffOutboundMaster> stCffOutboundMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, stCffOutboundMasters.size());
		Assert.assertEquals(newStCffOutboundMaster1,
			stCffOutboundMasters.get(newStCffOutboundMaster1.getPrimaryKey()));
		Assert.assertEquals(newStCffOutboundMaster2,
			stCffOutboundMasters.get(newStCffOutboundMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		StCffOutboundMasterPK pk1 = new StCffOutboundMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		StCffOutboundMasterPK pk2 = new StCffOutboundMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, StCffOutboundMaster> stCffOutboundMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stCffOutboundMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		StCffOutboundMaster newStCffOutboundMaster = addStCffOutboundMaster();

		StCffOutboundMasterPK pk = new StCffOutboundMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStCffOutboundMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, StCffOutboundMaster> stCffOutboundMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stCffOutboundMasters.size());
		Assert.assertEquals(newStCffOutboundMaster,
			stCffOutboundMasters.get(newStCffOutboundMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, StCffOutboundMaster> stCffOutboundMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stCffOutboundMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		StCffOutboundMaster newStCffOutboundMaster = addStCffOutboundMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStCffOutboundMaster.getPrimaryKey());

		Map<Serializable, StCffOutboundMaster> stCffOutboundMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stCffOutboundMasters.size());
		Assert.assertEquals(newStCffOutboundMaster,
			stCffOutboundMasters.get(newStCffOutboundMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = StCffOutboundMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<StCffOutboundMaster>() {
				@Override
				public void performAction(
					StCffOutboundMaster stCffOutboundMaster) {
					Assert.assertNotNull(stCffOutboundMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		StCffOutboundMaster newStCffOutboundMaster = addStCffOutboundMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StCffOutboundMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.cffDetailsSid",
				newStCffOutboundMaster.getCffDetailsSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				newStCffOutboundMaster.getSessionId()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.rsModelSid",
				newStCffOutboundMaster.getRsModelSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				newStCffOutboundMaster.getUserId()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				newStCffOutboundMaster.getPeriodSid()));

		List<StCffOutboundMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		StCffOutboundMaster existingStCffOutboundMaster = result.get(0);

		Assert.assertEquals(existingStCffOutboundMaster, newStCffOutboundMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StCffOutboundMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.cffDetailsSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				RandomTestUtil.randomString()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.rsModelSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				RandomTestUtil.randomString()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				RandomTestUtil.nextInt()));

		List<StCffOutboundMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		StCffOutboundMaster newStCffOutboundMaster = addStCffOutboundMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StCffOutboundMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.cffDetailsSid"));

		Object newCffDetailsSid = newStCffOutboundMaster.getCffDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.cffDetailsSid",
				new Object[] { newCffDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCffDetailsSid = result.get(0);

		Assert.assertEquals(existingCffDetailsSid, newCffDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StCffOutboundMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.cffDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.cffDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected StCffOutboundMaster addStCffOutboundMaster()
		throws Exception {
		StCffOutboundMasterPK pk = new StCffOutboundMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		StCffOutboundMaster stCffOutboundMaster = _persistence.create(pk);

		stCffOutboundMaster.setEtlCheckRecord(RandomTestUtil.randomBoolean());

		stCffOutboundMaster.setCustomerName(RandomTestUtil.randomString());

		stCffOutboundMaster.setContractHolderId(RandomTestUtil.randomString());

		stCffOutboundMaster.setBusinessUnitNo(RandomTestUtil.randomString());

		stCffOutboundMaster.setYear(RandomTestUtil.randomString());

		stCffOutboundMaster.setFinancialForecastApprovalDate(RandomTestUtil.nextDate());

		stCffOutboundMaster.setDeductionId(RandomTestUtil.randomString());

		stCffOutboundMaster.setModifiedDate(RandomTestUtil.nextDate());

		stCffOutboundMaster.setDeductionPerUnit(RandomTestUtil.nextDouble());

		stCffOutboundMaster.setCogsPerUnit(RandomTestUtil.nextDouble());

		stCffOutboundMaster.setContractType(RandomTestUtil.randomString());

		stCffOutboundMaster.setSource(RandomTestUtil.randomString());

		stCffOutboundMaster.setBusinessUnitName(RandomTestUtil.randomString());

		stCffOutboundMaster.setContractMasterSid(RandomTestUtil.nextInt());

		stCffOutboundMaster.setFinancialForecastId(RandomTestUtil.randomString());

		stCffOutboundMaster.setProjectId(RandomTestUtil.randomString());

		stCffOutboundMaster.setCustomerNo(RandomTestUtil.randomString());

		stCffOutboundMaster.setModifiedBy(RandomTestUtil.randomString());

		stCffOutboundMaster.setSalesDollars(RandomTestUtil.nextDouble());

		stCffOutboundMaster.setMonth(RandomTestUtil.nextInt());

		stCffOutboundMaster.setType(RandomTestUtil.randomString());

		stCffOutboundMaster.setDeductionType(RandomTestUtil.randomString());

		stCffOutboundMaster.setCompanyMasterSid(RandomTestUtil.nextInt());

		stCffOutboundMaster.setCheckRecord(RandomTestUtil.randomBoolean());

		stCffOutboundMaster.setContractName(RandomTestUtil.randomString());

		stCffOutboundMaster.setDeductionRate(RandomTestUtil.nextDouble());

		stCffOutboundMaster.setDeductionCategory(RandomTestUtil.randomString());

		stCffOutboundMaster.setCogsAmount(RandomTestUtil.nextDouble());

		stCffOutboundMaster.setDeductionNo(RandomTestUtil.randomString());

		stCffOutboundMaster.setFinancialForecastCreationDate(RandomTestUtil.nextDate());

		stCffOutboundMaster.setCompanyNo(RandomTestUtil.randomString());

		stCffOutboundMaster.setSalesUnits(RandomTestUtil.nextDouble());

		stCffOutboundMaster.setItemName(RandomTestUtil.randomString());

		stCffOutboundMaster.setDeductionInclusion(RandomTestUtil.randomString());

		stCffOutboundMaster.setContractHolderName(RandomTestUtil.randomString());

		stCffOutboundMaster.setItemMasterSid(RandomTestUtil.nextInt());

		stCffOutboundMaster.setCompanyName(RandomTestUtil.randomString());

		stCffOutboundMaster.setCustomerId(RandomTestUtil.randomString());

		stCffOutboundMaster.setItemId(RandomTestUtil.randomString());

		stCffOutboundMaster.setNetProfitDollars(RandomTestUtil.nextDouble());

		stCffOutboundMaster.setGlCompanyMasterSid(RandomTestUtil.nextInt());

		stCffOutboundMaster.setCreatedDate(RandomTestUtil.nextDate());

		stCffOutboundMaster.setCreatedBy(RandomTestUtil.randomString());

		stCffOutboundMaster.setDeductionCategory1(RandomTestUtil.randomString());

		stCffOutboundMaster.setDeductionCategory2(RandomTestUtil.randomString());

		stCffOutboundMaster.setContractHolderNo(RandomTestUtil.randomString());

		stCffOutboundMaster.setDeductionCategory3(RandomTestUtil.randomString());

		stCffOutboundMaster.setItemNo(RandomTestUtil.randomString());

		stCffOutboundMaster.setDeductionCategory4(RandomTestUtil.randomString());

		stCffOutboundMaster.setDeductionCategory5(RandomTestUtil.randomString());

		stCffOutboundMaster.setDeductionCategory6(RandomTestUtil.randomString());

		stCffOutboundMaster.setContractId(RandomTestUtil.randomString());

		stCffOutboundMaster.setDeductionProgram(RandomTestUtil.randomString());

		stCffOutboundMaster.setBusinessUnitId(RandomTestUtil.randomString());

		stCffOutboundMaster.setProjectionName(RandomTestUtil.randomString());

		stCffOutboundMaster.setCompanyId(RandomTestUtil.randomString());

		stCffOutboundMaster.setOutboundStatus(RandomTestUtil.randomString());

		stCffOutboundMaster.setOriginalBatchId(RandomTestUtil.randomString());

		stCffOutboundMaster.setDeductionName(RandomTestUtil.randomString());

		stCffOutboundMaster.setNetProfitPerUnit(RandomTestUtil.nextDouble());

		stCffOutboundMaster.setSalesInclusion(RandomTestUtil.randomString());

		stCffOutboundMaster.setBatchId(RandomTestUtil.randomString());

		stCffOutboundMaster.setFinancialForecastName(RandomTestUtil.randomString());

		stCffOutboundMaster.setNetSalesDollar(RandomTestUtil.nextDouble());

		stCffOutboundMaster.setDeductionDollars(RandomTestUtil.nextDouble());

		stCffOutboundMaster.setContractNo(RandomTestUtil.randomString());

		_stCffOutboundMasters.add(_persistence.update(stCffOutboundMaster));

		return stCffOutboundMaster;
	}

	private List<StCffOutboundMaster> _stCffOutboundMasters = new ArrayList<StCffOutboundMaster>();
	private StCffOutboundMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}