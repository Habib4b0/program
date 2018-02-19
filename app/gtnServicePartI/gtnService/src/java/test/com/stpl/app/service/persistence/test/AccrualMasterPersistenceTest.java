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

import com.stpl.app.exception.NoSuchAccrualMasterException;
import com.stpl.app.model.AccrualMaster;
import com.stpl.app.service.AccrualMasterLocalServiceUtil;
import com.stpl.app.service.persistence.AccrualMasterPersistence;
import com.stpl.app.service.persistence.AccrualMasterUtil;

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
public class AccrualMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = AccrualMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<AccrualMaster> iterator = _accrualMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AccrualMaster accrualMaster = _persistence.create(pk);

		Assert.assertNotNull(accrualMaster);

		Assert.assertEquals(accrualMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		AccrualMaster newAccrualMaster = addAccrualMaster();

		_persistence.remove(newAccrualMaster);

		AccrualMaster existingAccrualMaster = _persistence.fetchByPrimaryKey(newAccrualMaster.getPrimaryKey());

		Assert.assertNull(existingAccrualMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addAccrualMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AccrualMaster newAccrualMaster = _persistence.create(pk);

		newAccrualMaster.setAccountId(RandomTestUtil.randomString());

		newAccrualMaster.setRecordCreatedDate(RandomTestUtil.nextDate());

		newAccrualMaster.setPostingIndicator(RandomTestUtil.randomString());

		newAccrualMaster.setBrandName(RandomTestUtil.randomString());

		newAccrualMaster.setAccrualPeriodEndDate(RandomTestUtil.nextDate());

		newAccrualMaster.setModifiedDate(RandomTestUtil.nextDate());

		newAccrualMaster.setSalesMasterId(RandomTestUtil.randomString());

		newAccrualMaster.setSource(RandomTestUtil.randomString());

		newAccrualMaster.setContractMasterSid(RandomTestUtil.nextInt());

		newAccrualMaster.setDocumentType(RandomTestUtil.randomString());

		newAccrualMaster.setInboundStatus(RandomTestUtil.randomString());

		newAccrualMaster.setModifiedBy(RandomTestUtil.nextInt());

		newAccrualMaster.setAcctIdentCodeQualifier(RandomTestUtil.randomString());

		newAccrualMaster.setCompIdentCodeQualifier(RandomTestUtil.randomString());

		newAccrualMaster.setCompanyMasterSid(RandomTestUtil.nextInt());

		newAccrualMaster.setContractName(RandomTestUtil.randomString());

		newAccrualMaster.setAccountNo(RandomTestUtil.randomString());

		newAccrualMaster.setAccountName(RandomTestUtil.randomString());

		newAccrualMaster.setProvisionId(RandomTestUtil.randomString());

		newAccrualMaster.setAmount(RandomTestUtil.nextDouble());

		newAccrualMaster.setSubLedger(RandomTestUtil.randomString());

		newAccrualMaster.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newAccrualMaster.setCompanyNo(RandomTestUtil.randomString());

		newAccrualMaster.setItemName(RandomTestUtil.randomString());

		newAccrualMaster.setRsModelSid(RandomTestUtil.nextInt());

		newAccrualMaster.setItemMasterSid(RandomTestUtil.nextInt());

		newAccrualMaster.setItemId(RandomTestUtil.randomString());

		newAccrualMaster.setBrandMasterSid(RandomTestUtil.nextInt());

		newAccrualMaster.setItemIdentCodeQualifier(RandomTestUtil.randomString());

		newAccrualMaster.setGlCompanyMasterSid(RandomTestUtil.randomString());

		newAccrualMaster.setCreatedBy(RandomTestUtil.nextInt());

		newAccrualMaster.setCreatedDate(RandomTestUtil.nextDate());

		newAccrualMaster.setAccrualPeriodStartDate(RandomTestUtil.nextDate());

		newAccrualMaster.setSubLedgerType(RandomTestUtil.randomString());

		newAccrualMaster.setProgramNo(RandomTestUtil.randomString());

		newAccrualMaster.setGlCompanyName(RandomTestUtil.randomString());

		newAccrualMaster.setCategoryId(RandomTestUtil.randomString());

		newAccrualMaster.setItemNo(RandomTestUtil.randomString());

		newAccrualMaster.setContractId(RandomTestUtil.randomString());

		newAccrualMaster.setAccrualId(RandomTestUtil.randomString());

		newAccrualMaster.setCompanyStringId(RandomTestUtil.randomString());

		newAccrualMaster.setAccrualType(RandomTestUtil.randomString());

		newAccrualMaster.setBrandId(RandomTestUtil.randomString());

		newAccrualMaster.setPostingDate(RandomTestUtil.nextDate());

		newAccrualMaster.setGlDate(RandomTestUtil.nextDate());

		newAccrualMaster.setGlAmount(RandomTestUtil.nextDouble());

		newAccrualMaster.setCompanyCostCenter(RandomTestUtil.randomString());

		newAccrualMaster.setGlAccount(RandomTestUtil.randomString());

		newAccrualMaster.setBatchId(RandomTestUtil.randomString());

		newAccrualMaster.setProgramType(RandomTestUtil.randomString());

		newAccrualMaster.setContractNo(RandomTestUtil.randomString());

		_accrualMasters.add(_persistence.update(newAccrualMaster));

		AccrualMaster existingAccrualMaster = _persistence.findByPrimaryKey(newAccrualMaster.getPrimaryKey());

		Assert.assertEquals(existingAccrualMaster.getAccountId(),
			newAccrualMaster.getAccountId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingAccrualMaster.getRecordCreatedDate()),
			Time.getShortTimestamp(newAccrualMaster.getRecordCreatedDate()));
		Assert.assertEquals(existingAccrualMaster.getPostingIndicator(),
			newAccrualMaster.getPostingIndicator());
		Assert.assertEquals(existingAccrualMaster.getBrandName(),
			newAccrualMaster.getBrandName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingAccrualMaster.getAccrualPeriodEndDate()),
			Time.getShortTimestamp(newAccrualMaster.getAccrualPeriodEndDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingAccrualMaster.getModifiedDate()),
			Time.getShortTimestamp(newAccrualMaster.getModifiedDate()));
		Assert.assertEquals(existingAccrualMaster.getSalesMasterId(),
			newAccrualMaster.getSalesMasterId());
		Assert.assertEquals(existingAccrualMaster.getSource(),
			newAccrualMaster.getSource());
		Assert.assertEquals(existingAccrualMaster.getContractMasterSid(),
			newAccrualMaster.getContractMasterSid());
		Assert.assertEquals(existingAccrualMaster.getDocumentType(),
			newAccrualMaster.getDocumentType());
		Assert.assertEquals(existingAccrualMaster.getInboundStatus(),
			newAccrualMaster.getInboundStatus());
		Assert.assertEquals(existingAccrualMaster.getModifiedBy(),
			newAccrualMaster.getModifiedBy());
		Assert.assertEquals(existingAccrualMaster.getAcctIdentCodeQualifier(),
			newAccrualMaster.getAcctIdentCodeQualifier());
		Assert.assertEquals(existingAccrualMaster.getCompIdentCodeQualifier(),
			newAccrualMaster.getCompIdentCodeQualifier());
		Assert.assertEquals(existingAccrualMaster.getCompanyMasterSid(),
			newAccrualMaster.getCompanyMasterSid());
		Assert.assertEquals(existingAccrualMaster.getContractName(),
			newAccrualMaster.getContractName());
		Assert.assertEquals(existingAccrualMaster.getAccountNo(),
			newAccrualMaster.getAccountNo());
		Assert.assertEquals(existingAccrualMaster.getAccountName(),
			newAccrualMaster.getAccountName());
		Assert.assertEquals(existingAccrualMaster.getProvisionId(),
			newAccrualMaster.getProvisionId());
		AssertUtils.assertEquals(existingAccrualMaster.getAmount(),
			newAccrualMaster.getAmount());
		Assert.assertEquals(existingAccrualMaster.getSubLedger(),
			newAccrualMaster.getSubLedger());
		Assert.assertEquals(existingAccrualMaster.getRecordLockStatus(),
			newAccrualMaster.getRecordLockStatus());
		Assert.assertEquals(existingAccrualMaster.getCompanyNo(),
			newAccrualMaster.getCompanyNo());
		Assert.assertEquals(existingAccrualMaster.getItemName(),
			newAccrualMaster.getItemName());
		Assert.assertEquals(existingAccrualMaster.getRsModelSid(),
			newAccrualMaster.getRsModelSid());
		Assert.assertEquals(existingAccrualMaster.getAccrualMasterSid(),
			newAccrualMaster.getAccrualMasterSid());
		Assert.assertEquals(existingAccrualMaster.getItemMasterSid(),
			newAccrualMaster.getItemMasterSid());
		Assert.assertEquals(existingAccrualMaster.getItemId(),
			newAccrualMaster.getItemId());
		Assert.assertEquals(existingAccrualMaster.getBrandMasterSid(),
			newAccrualMaster.getBrandMasterSid());
		Assert.assertEquals(existingAccrualMaster.getItemIdentCodeQualifier(),
			newAccrualMaster.getItemIdentCodeQualifier());
		Assert.assertEquals(existingAccrualMaster.getGlCompanyMasterSid(),
			newAccrualMaster.getGlCompanyMasterSid());
		Assert.assertEquals(existingAccrualMaster.getCreatedBy(),
			newAccrualMaster.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingAccrualMaster.getCreatedDate()),
			Time.getShortTimestamp(newAccrualMaster.getCreatedDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingAccrualMaster.getAccrualPeriodStartDate()),
			Time.getShortTimestamp(newAccrualMaster.getAccrualPeriodStartDate()));
		Assert.assertEquals(existingAccrualMaster.getSubLedgerType(),
			newAccrualMaster.getSubLedgerType());
		Assert.assertEquals(existingAccrualMaster.getProgramNo(),
			newAccrualMaster.getProgramNo());
		Assert.assertEquals(existingAccrualMaster.getGlCompanyName(),
			newAccrualMaster.getGlCompanyName());
		Assert.assertEquals(existingAccrualMaster.getCategoryId(),
			newAccrualMaster.getCategoryId());
		Assert.assertEquals(existingAccrualMaster.getItemNo(),
			newAccrualMaster.getItemNo());
		Assert.assertEquals(existingAccrualMaster.getContractId(),
			newAccrualMaster.getContractId());
		Assert.assertEquals(existingAccrualMaster.getAccrualId(),
			newAccrualMaster.getAccrualId());
		Assert.assertEquals(existingAccrualMaster.getCompanyStringId(),
			newAccrualMaster.getCompanyStringId());
		Assert.assertEquals(existingAccrualMaster.getAccrualType(),
			newAccrualMaster.getAccrualType());
		Assert.assertEquals(existingAccrualMaster.getBrandId(),
			newAccrualMaster.getBrandId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingAccrualMaster.getPostingDate()),
			Time.getShortTimestamp(newAccrualMaster.getPostingDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingAccrualMaster.getGlDate()),
			Time.getShortTimestamp(newAccrualMaster.getGlDate()));
		AssertUtils.assertEquals(existingAccrualMaster.getGlAmount(),
			newAccrualMaster.getGlAmount());
		Assert.assertEquals(existingAccrualMaster.getCompanyCostCenter(),
			newAccrualMaster.getCompanyCostCenter());
		Assert.assertEquals(existingAccrualMaster.getGlAccount(),
			newAccrualMaster.getGlAccount());
		Assert.assertEquals(existingAccrualMaster.getBatchId(),
			newAccrualMaster.getBatchId());
		Assert.assertEquals(existingAccrualMaster.getProgramType(),
			newAccrualMaster.getProgramType());
		Assert.assertEquals(existingAccrualMaster.getContractNo(),
			newAccrualMaster.getContractNo());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		AccrualMaster newAccrualMaster = addAccrualMaster();

		AccrualMaster existingAccrualMaster = _persistence.findByPrimaryKey(newAccrualMaster.getPrimaryKey());

		Assert.assertEquals(existingAccrualMaster, newAccrualMaster);
	}

	@Test(expected = NoSuchAccrualMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<AccrualMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("ACCRUAL_MASTER",
			"accountId", true, "recordCreatedDate", true, "postingIndicator",
			true, "brandName", true, "accrualPeriodEndDate", true,
			"modifiedDate", true, "salesMasterId", true, "source", true,
			"contractMasterSid", true, "documentType", true, "inboundStatus",
			true, "modifiedBy", true, "acctIdentCodeQualifier", true,
			"compIdentCodeQualifier", true, "companyMasterSid", true,
			"contractName", true, "accountNo", true, "accountName", true,
			"provisionId", true, "amount", true, "subLedger", true,
			"recordLockStatus", true, "companyNo", true, "itemName", true,
			"rsModelSid", true, "accrualMasterSid", true, "itemMasterSid",
			true, "itemId", true, "brandMasterSid", true,
			"itemIdentCodeQualifier", true, "glCompanyMasterSid", true,
			"createdBy", true, "createdDate", true, "accrualPeriodStartDate",
			true, "subLedgerType", true, "programNo", true, "glCompanyName",
			true, "categoryId", true, "itemNo", true, "contractId", true,
			"accrualId", true, "companyStringId", true, "accrualType", true,
			"brandId", true, "postingDate", true, "glDate", true, "glAmount",
			true, "companyCostCenter", true, "glAccount", true, "batchId",
			true, "programType", true, "contractNo", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		AccrualMaster newAccrualMaster = addAccrualMaster();

		AccrualMaster existingAccrualMaster = _persistence.fetchByPrimaryKey(newAccrualMaster.getPrimaryKey());

		Assert.assertEquals(existingAccrualMaster, newAccrualMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AccrualMaster missingAccrualMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingAccrualMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		AccrualMaster newAccrualMaster1 = addAccrualMaster();
		AccrualMaster newAccrualMaster2 = addAccrualMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAccrualMaster1.getPrimaryKey());
		primaryKeys.add(newAccrualMaster2.getPrimaryKey());

		Map<Serializable, AccrualMaster> accrualMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, accrualMasters.size());
		Assert.assertEquals(newAccrualMaster1,
			accrualMasters.get(newAccrualMaster1.getPrimaryKey()));
		Assert.assertEquals(newAccrualMaster2,
			accrualMasters.get(newAccrualMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, AccrualMaster> accrualMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(accrualMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		AccrualMaster newAccrualMaster = addAccrualMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAccrualMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, AccrualMaster> accrualMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, accrualMasters.size());
		Assert.assertEquals(newAccrualMaster,
			accrualMasters.get(newAccrualMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, AccrualMaster> accrualMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(accrualMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		AccrualMaster newAccrualMaster = addAccrualMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAccrualMaster.getPrimaryKey());

		Map<Serializable, AccrualMaster> accrualMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, accrualMasters.size());
		Assert.assertEquals(newAccrualMaster,
			accrualMasters.get(newAccrualMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = AccrualMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<AccrualMaster>() {
				@Override
				public void performAction(AccrualMaster accrualMaster) {
					Assert.assertNotNull(accrualMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		AccrualMaster newAccrualMaster = addAccrualMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AccrualMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("accrualMasterSid",
				newAccrualMaster.getAccrualMasterSid()));

		List<AccrualMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		AccrualMaster existingAccrualMaster = result.get(0);

		Assert.assertEquals(existingAccrualMaster, newAccrualMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AccrualMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("accrualMasterSid",
				RandomTestUtil.nextInt()));

		List<AccrualMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		AccrualMaster newAccrualMaster = addAccrualMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AccrualMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"accrualMasterSid"));

		Object newAccrualMasterSid = newAccrualMaster.getAccrualMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("accrualMasterSid",
				new Object[] { newAccrualMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingAccrualMasterSid = result.get(0);

		Assert.assertEquals(existingAccrualMasterSid, newAccrualMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AccrualMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"accrualMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("accrualMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected AccrualMaster addAccrualMaster() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AccrualMaster accrualMaster = _persistence.create(pk);

		accrualMaster.setAccountId(RandomTestUtil.randomString());

		accrualMaster.setRecordCreatedDate(RandomTestUtil.nextDate());

		accrualMaster.setPostingIndicator(RandomTestUtil.randomString());

		accrualMaster.setBrandName(RandomTestUtil.randomString());

		accrualMaster.setAccrualPeriodEndDate(RandomTestUtil.nextDate());

		accrualMaster.setModifiedDate(RandomTestUtil.nextDate());

		accrualMaster.setSalesMasterId(RandomTestUtil.randomString());

		accrualMaster.setSource(RandomTestUtil.randomString());

		accrualMaster.setContractMasterSid(RandomTestUtil.nextInt());

		accrualMaster.setDocumentType(RandomTestUtil.randomString());

		accrualMaster.setInboundStatus(RandomTestUtil.randomString());

		accrualMaster.setModifiedBy(RandomTestUtil.nextInt());

		accrualMaster.setAcctIdentCodeQualifier(RandomTestUtil.randomString());

		accrualMaster.setCompIdentCodeQualifier(RandomTestUtil.randomString());

		accrualMaster.setCompanyMasterSid(RandomTestUtil.nextInt());

		accrualMaster.setContractName(RandomTestUtil.randomString());

		accrualMaster.setAccountNo(RandomTestUtil.randomString());

		accrualMaster.setAccountName(RandomTestUtil.randomString());

		accrualMaster.setProvisionId(RandomTestUtil.randomString());

		accrualMaster.setAmount(RandomTestUtil.nextDouble());

		accrualMaster.setSubLedger(RandomTestUtil.randomString());

		accrualMaster.setRecordLockStatus(RandomTestUtil.randomBoolean());

		accrualMaster.setCompanyNo(RandomTestUtil.randomString());

		accrualMaster.setItemName(RandomTestUtil.randomString());

		accrualMaster.setRsModelSid(RandomTestUtil.nextInt());

		accrualMaster.setItemMasterSid(RandomTestUtil.nextInt());

		accrualMaster.setItemId(RandomTestUtil.randomString());

		accrualMaster.setBrandMasterSid(RandomTestUtil.nextInt());

		accrualMaster.setItemIdentCodeQualifier(RandomTestUtil.randomString());

		accrualMaster.setGlCompanyMasterSid(RandomTestUtil.randomString());

		accrualMaster.setCreatedBy(RandomTestUtil.nextInt());

		accrualMaster.setCreatedDate(RandomTestUtil.nextDate());

		accrualMaster.setAccrualPeriodStartDate(RandomTestUtil.nextDate());

		accrualMaster.setSubLedgerType(RandomTestUtil.randomString());

		accrualMaster.setProgramNo(RandomTestUtil.randomString());

		accrualMaster.setGlCompanyName(RandomTestUtil.randomString());

		accrualMaster.setCategoryId(RandomTestUtil.randomString());

		accrualMaster.setItemNo(RandomTestUtil.randomString());

		accrualMaster.setContractId(RandomTestUtil.randomString());

		accrualMaster.setAccrualId(RandomTestUtil.randomString());

		accrualMaster.setCompanyStringId(RandomTestUtil.randomString());

		accrualMaster.setAccrualType(RandomTestUtil.randomString());

		accrualMaster.setBrandId(RandomTestUtil.randomString());

		accrualMaster.setPostingDate(RandomTestUtil.nextDate());

		accrualMaster.setGlDate(RandomTestUtil.nextDate());

		accrualMaster.setGlAmount(RandomTestUtil.nextDouble());

		accrualMaster.setCompanyCostCenter(RandomTestUtil.randomString());

		accrualMaster.setGlAccount(RandomTestUtil.randomString());

		accrualMaster.setBatchId(RandomTestUtil.randomString());

		accrualMaster.setProgramType(RandomTestUtil.randomString());

		accrualMaster.setContractNo(RandomTestUtil.randomString());

		_accrualMasters.add(_persistence.update(accrualMaster));

		return accrualMaster;
	}

	private List<AccrualMaster> _accrualMasters = new ArrayList<AccrualMaster>();
	private AccrualMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}