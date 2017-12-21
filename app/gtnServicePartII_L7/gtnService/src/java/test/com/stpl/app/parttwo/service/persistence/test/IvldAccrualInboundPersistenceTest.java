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

import com.stpl.app.parttwo.exception.NoSuchIvldAccrualInboundException;
import com.stpl.app.parttwo.model.IvldAccrualInbound;
import com.stpl.app.parttwo.service.IvldAccrualInboundLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.IvldAccrualInboundPersistence;
import com.stpl.app.parttwo.service.persistence.IvldAccrualInboundUtil;

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
public class IvldAccrualInboundPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = IvldAccrualInboundUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<IvldAccrualInbound> iterator = _ivldAccrualInbounds.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldAccrualInbound ivldAccrualInbound = _persistence.create(pk);

		Assert.assertNotNull(ivldAccrualInbound);

		Assert.assertEquals(ivldAccrualInbound.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		IvldAccrualInbound newIvldAccrualInbound = addIvldAccrualInbound();

		_persistence.remove(newIvldAccrualInbound);

		IvldAccrualInbound existingIvldAccrualInbound = _persistence.fetchByPrimaryKey(newIvldAccrualInbound.getPrimaryKey());

		Assert.assertNull(existingIvldAccrualInbound);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addIvldAccrualInbound();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldAccrualInbound newIvldAccrualInbound = _persistence.create(pk);

		newIvldAccrualInbound.setRecordCreatedDate(RandomTestUtil.randomString());

		newIvldAccrualInbound.setAccountId(RandomTestUtil.randomString());

		newIvldAccrualInbound.setPostingIndicator(RandomTestUtil.randomString());

		newIvldAccrualInbound.setItemId(RandomTestUtil.randomString());

		newIvldAccrualInbound.setModifiedDate(RandomTestUtil.nextDate());

		newIvldAccrualInbound.setAccrualPeriodEndDate(RandomTestUtil.randomString());

		newIvldAccrualInbound.setItemIdentCodeQualifier(RandomTestUtil.randomString());

		newIvldAccrualInbound.setGlCompanyMasterSid(RandomTestUtil.randomString());

		newIvldAccrualInbound.setSalesMasterId(RandomTestUtil.randomString());

		newIvldAccrualInbound.setCreatedDate(RandomTestUtil.nextDate());

		newIvldAccrualInbound.setCreatedBy(RandomTestUtil.randomString());

		newIvldAccrualInbound.setSource(RandomTestUtil.randomString());

		newIvldAccrualInbound.setAccrualPeriodStartDate(RandomTestUtil.randomString());

		newIvldAccrualInbound.setAddChgDelIndicator(RandomTestUtil.randomString());

		newIvldAccrualInbound.setSubLedgerType(RandomTestUtil.randomString());

		newIvldAccrualInbound.setProgramNo(RandomTestUtil.randomString());

		newIvldAccrualInbound.setDocumentType(RandomTestUtil.randomString());

		newIvldAccrualInbound.setAccrualIntfid(RandomTestUtil.randomString());

		newIvldAccrualInbound.setGlCompanyName(RandomTestUtil.randomString());

		newIvldAccrualInbound.setErrorCode(RandomTestUtil.randomString());

		newIvldAccrualInbound.setIntfInsertedDate(RandomTestUtil.nextDate());

		newIvldAccrualInbound.setModifiedBy(RandomTestUtil.randomString());

		newIvldAccrualInbound.setCategoryId(RandomTestUtil.randomString());

		newIvldAccrualInbound.setItemNo(RandomTestUtil.randomString());

		newIvldAccrualInbound.setReprocessedFlag(RandomTestUtil.randomString());

		newIvldAccrualInbound.setCompIdentCodeQualifier(RandomTestUtil.randomString());

		newIvldAccrualInbound.setAcctIdentCodeQualifier(RandomTestUtil.randomString());

		newIvldAccrualInbound.setContractId(RandomTestUtil.randomString());

		newIvldAccrualInbound.setAccountNo(RandomTestUtil.randomString());

		newIvldAccrualInbound.setAccrualId(RandomTestUtil.randomString());

		newIvldAccrualInbound.setReasonForFailure(RandomTestUtil.randomString());

		newIvldAccrualInbound.setCompanyId(RandomTestUtil.randomString());

		newIvldAccrualInbound.setAccountName(RandomTestUtil.randomString());

		newIvldAccrualInbound.setAccrualType(RandomTestUtil.randomString());

		newIvldAccrualInbound.setPostingDate(RandomTestUtil.randomString());

		newIvldAccrualInbound.setBrandId(RandomTestUtil.randomString());

		newIvldAccrualInbound.setProvisionId(RandomTestUtil.randomString());

		newIvldAccrualInbound.setAmount(RandomTestUtil.randomString());

		newIvldAccrualInbound.setGlDate(RandomTestUtil.randomString());

		newIvldAccrualInbound.setSubLedger(RandomTestUtil.randomString());

		newIvldAccrualInbound.setCompanyCostCenter(RandomTestUtil.randomString());

		newIvldAccrualInbound.setGlAccount(RandomTestUtil.randomString());

		newIvldAccrualInbound.setCompanyNo(RandomTestUtil.randomString());

		newIvldAccrualInbound.setBatchId(RandomTestUtil.randomString());

		newIvldAccrualInbound.setProgramType(RandomTestUtil.randomString());

		newIvldAccrualInbound.setItemName(RandomTestUtil.randomString());

		newIvldAccrualInbound.setErrorField(RandomTestUtil.randomString());

		newIvldAccrualInbound.setContractNo(RandomTestUtil.randomString());

		newIvldAccrualInbound.setBrandName(RandomTestUtil.randomString());

		newIvldAccrualInbound.setContractName(RandomTestUtil.randomString());

		newIvldAccrualInbound.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldAccrualInbounds.add(_persistence.update(newIvldAccrualInbound));

		IvldAccrualInbound existingIvldAccrualInbound = _persistence.findByPrimaryKey(newIvldAccrualInbound.getPrimaryKey());

		Assert.assertEquals(existingIvldAccrualInbound.getRecordCreatedDate(),
			newIvldAccrualInbound.getRecordCreatedDate());
		Assert.assertEquals(existingIvldAccrualInbound.getAccountId(),
			newIvldAccrualInbound.getAccountId());
		Assert.assertEquals(existingIvldAccrualInbound.getPostingIndicator(),
			newIvldAccrualInbound.getPostingIndicator());
		Assert.assertEquals(existingIvldAccrualInbound.getItemId(),
			newIvldAccrualInbound.getItemId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldAccrualInbound.getModifiedDate()),
			Time.getShortTimestamp(newIvldAccrualInbound.getModifiedDate()));
		Assert.assertEquals(existingIvldAccrualInbound.getAccrualPeriodEndDate(),
			newIvldAccrualInbound.getAccrualPeriodEndDate());
		Assert.assertEquals(existingIvldAccrualInbound.getItemIdentCodeQualifier(),
			newIvldAccrualInbound.getItemIdentCodeQualifier());
		Assert.assertEquals(existingIvldAccrualInbound.getGlCompanyMasterSid(),
			newIvldAccrualInbound.getGlCompanyMasterSid());
		Assert.assertEquals(existingIvldAccrualInbound.getSalesMasterId(),
			newIvldAccrualInbound.getSalesMasterId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldAccrualInbound.getCreatedDate()),
			Time.getShortTimestamp(newIvldAccrualInbound.getCreatedDate()));
		Assert.assertEquals(existingIvldAccrualInbound.getCreatedBy(),
			newIvldAccrualInbound.getCreatedBy());
		Assert.assertEquals(existingIvldAccrualInbound.getSource(),
			newIvldAccrualInbound.getSource());
		Assert.assertEquals(existingIvldAccrualInbound.getAccrualPeriodStartDate(),
			newIvldAccrualInbound.getAccrualPeriodStartDate());
		Assert.assertEquals(existingIvldAccrualInbound.getAddChgDelIndicator(),
			newIvldAccrualInbound.getAddChgDelIndicator());
		Assert.assertEquals(existingIvldAccrualInbound.getSubLedgerType(),
			newIvldAccrualInbound.getSubLedgerType());
		Assert.assertEquals(existingIvldAccrualInbound.getProgramNo(),
			newIvldAccrualInbound.getProgramNo());
		Assert.assertEquals(existingIvldAccrualInbound.getDocumentType(),
			newIvldAccrualInbound.getDocumentType());
		Assert.assertEquals(existingIvldAccrualInbound.getAccrualIntfid(),
			newIvldAccrualInbound.getAccrualIntfid());
		Assert.assertEquals(existingIvldAccrualInbound.getGlCompanyName(),
			newIvldAccrualInbound.getGlCompanyName());
		Assert.assertEquals(existingIvldAccrualInbound.getErrorCode(),
			newIvldAccrualInbound.getErrorCode());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldAccrualInbound.getIntfInsertedDate()),
			Time.getShortTimestamp(newIvldAccrualInbound.getIntfInsertedDate()));
		Assert.assertEquals(existingIvldAccrualInbound.getModifiedBy(),
			newIvldAccrualInbound.getModifiedBy());
		Assert.assertEquals(existingIvldAccrualInbound.getCategoryId(),
			newIvldAccrualInbound.getCategoryId());
		Assert.assertEquals(existingIvldAccrualInbound.getItemNo(),
			newIvldAccrualInbound.getItemNo());
		Assert.assertEquals(existingIvldAccrualInbound.getReprocessedFlag(),
			newIvldAccrualInbound.getReprocessedFlag());
		Assert.assertEquals(existingIvldAccrualInbound.getCompIdentCodeQualifier(),
			newIvldAccrualInbound.getCompIdentCodeQualifier());
		Assert.assertEquals(existingIvldAccrualInbound.getAcctIdentCodeQualifier(),
			newIvldAccrualInbound.getAcctIdentCodeQualifier());
		Assert.assertEquals(existingIvldAccrualInbound.getContractId(),
			newIvldAccrualInbound.getContractId());
		Assert.assertEquals(existingIvldAccrualInbound.getAccountNo(),
			newIvldAccrualInbound.getAccountNo());
		Assert.assertEquals(existingIvldAccrualInbound.getAccrualId(),
			newIvldAccrualInbound.getAccrualId());
		Assert.assertEquals(existingIvldAccrualInbound.getReasonForFailure(),
			newIvldAccrualInbound.getReasonForFailure());
		Assert.assertEquals(existingIvldAccrualInbound.getCompanyId(),
			newIvldAccrualInbound.getCompanyId());
		Assert.assertEquals(existingIvldAccrualInbound.getAccountName(),
			newIvldAccrualInbound.getAccountName());
		Assert.assertEquals(existingIvldAccrualInbound.getAccrualType(),
			newIvldAccrualInbound.getAccrualType());
		Assert.assertEquals(existingIvldAccrualInbound.getPostingDate(),
			newIvldAccrualInbound.getPostingDate());
		Assert.assertEquals(existingIvldAccrualInbound.getBrandId(),
			newIvldAccrualInbound.getBrandId());
		Assert.assertEquals(existingIvldAccrualInbound.getProvisionId(),
			newIvldAccrualInbound.getProvisionId());
		Assert.assertEquals(existingIvldAccrualInbound.getAmount(),
			newIvldAccrualInbound.getAmount());
		Assert.assertEquals(existingIvldAccrualInbound.getGlDate(),
			newIvldAccrualInbound.getGlDate());
		Assert.assertEquals(existingIvldAccrualInbound.getSubLedger(),
			newIvldAccrualInbound.getSubLedger());
		Assert.assertEquals(existingIvldAccrualInbound.getCompanyCostCenter(),
			newIvldAccrualInbound.getCompanyCostCenter());
		Assert.assertEquals(existingIvldAccrualInbound.getGlAccount(),
			newIvldAccrualInbound.getGlAccount());
		Assert.assertEquals(existingIvldAccrualInbound.getCompanyNo(),
			newIvldAccrualInbound.getCompanyNo());
		Assert.assertEquals(existingIvldAccrualInbound.getBatchId(),
			newIvldAccrualInbound.getBatchId());
		Assert.assertEquals(existingIvldAccrualInbound.getProgramType(),
			newIvldAccrualInbound.getProgramType());
		Assert.assertEquals(existingIvldAccrualInbound.getItemName(),
			newIvldAccrualInbound.getItemName());
		Assert.assertEquals(existingIvldAccrualInbound.getErrorField(),
			newIvldAccrualInbound.getErrorField());
		Assert.assertEquals(existingIvldAccrualInbound.getIvldAccrualInboundSid(),
			newIvldAccrualInbound.getIvldAccrualInboundSid());
		Assert.assertEquals(existingIvldAccrualInbound.getContractNo(),
			newIvldAccrualInbound.getContractNo());
		Assert.assertEquals(existingIvldAccrualInbound.getBrandName(),
			newIvldAccrualInbound.getBrandName());
		Assert.assertEquals(existingIvldAccrualInbound.getContractName(),
			newIvldAccrualInbound.getContractName());
		Assert.assertEquals(existingIvldAccrualInbound.getCheckRecord(),
			newIvldAccrualInbound.getCheckRecord());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		IvldAccrualInbound newIvldAccrualInbound = addIvldAccrualInbound();

		IvldAccrualInbound existingIvldAccrualInbound = _persistence.findByPrimaryKey(newIvldAccrualInbound.getPrimaryKey());

		Assert.assertEquals(existingIvldAccrualInbound, newIvldAccrualInbound);
	}

	@Test(expected = NoSuchIvldAccrualInboundException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<IvldAccrualInbound> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("IVLD_ACCRUAL_INBOUND",
			"recordCreatedDate", true, "accountId", true, "postingIndicator",
			true, "itemId", true, "modifiedDate", true, "accrualPeriodEndDate",
			true, "itemIdentCodeQualifier", true, "glCompanyMasterSid", true,
			"salesMasterId", true, "createdDate", true, "createdBy", true,
			"source", true, "accrualPeriodStartDate", true,
			"addChgDelIndicator", true, "subLedgerType", true, "programNo",
			true, "documentType", true, "accrualIntfid", true, "glCompanyName",
			true, "errorCode", true, "intfInsertedDate", true, "modifiedBy",
			true, "categoryId", true, "itemNo", true, "reprocessedFlag", true,
			"compIdentCodeQualifier", true, "acctIdentCodeQualifier", true,
			"contractId", true, "accountNo", true, "accrualId", true,
			"reasonForFailure", true, "companyId", true, "accountName", true,
			"accrualType", true, "postingDate", true, "brandId", true,
			"provisionId", true, "amount", true, "glDate", true, "subLedger",
			true, "companyCostCenter", true, "glAccount", true, "companyNo",
			true, "batchId", true, "programType", true, "itemName", true,
			"errorField", true, "ivldAccrualInboundSid", true, "contractNo",
			true, "brandName", true, "contractName", true, "checkRecord", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		IvldAccrualInbound newIvldAccrualInbound = addIvldAccrualInbound();

		IvldAccrualInbound existingIvldAccrualInbound = _persistence.fetchByPrimaryKey(newIvldAccrualInbound.getPrimaryKey());

		Assert.assertEquals(existingIvldAccrualInbound, newIvldAccrualInbound);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldAccrualInbound missingIvldAccrualInbound = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingIvldAccrualInbound);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		IvldAccrualInbound newIvldAccrualInbound1 = addIvldAccrualInbound();
		IvldAccrualInbound newIvldAccrualInbound2 = addIvldAccrualInbound();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldAccrualInbound1.getPrimaryKey());
		primaryKeys.add(newIvldAccrualInbound2.getPrimaryKey());

		Map<Serializable, IvldAccrualInbound> ivldAccrualInbounds = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ivldAccrualInbounds.size());
		Assert.assertEquals(newIvldAccrualInbound1,
			ivldAccrualInbounds.get(newIvldAccrualInbound1.getPrimaryKey()));
		Assert.assertEquals(newIvldAccrualInbound2,
			ivldAccrualInbounds.get(newIvldAccrualInbound2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, IvldAccrualInbound> ivldAccrualInbounds = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldAccrualInbounds.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		IvldAccrualInbound newIvldAccrualInbound = addIvldAccrualInbound();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldAccrualInbound.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, IvldAccrualInbound> ivldAccrualInbounds = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldAccrualInbounds.size());
		Assert.assertEquals(newIvldAccrualInbound,
			ivldAccrualInbounds.get(newIvldAccrualInbound.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, IvldAccrualInbound> ivldAccrualInbounds = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldAccrualInbounds.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		IvldAccrualInbound newIvldAccrualInbound = addIvldAccrualInbound();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldAccrualInbound.getPrimaryKey());

		Map<Serializable, IvldAccrualInbound> ivldAccrualInbounds = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldAccrualInbounds.size());
		Assert.assertEquals(newIvldAccrualInbound,
			ivldAccrualInbounds.get(newIvldAccrualInbound.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = IvldAccrualInboundLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<IvldAccrualInbound>() {
				@Override
				public void performAction(IvldAccrualInbound ivldAccrualInbound) {
					Assert.assertNotNull(ivldAccrualInbound);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		IvldAccrualInbound newIvldAccrualInbound = addIvldAccrualInbound();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldAccrualInbound.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldAccrualInboundSid",
				newIvldAccrualInbound.getIvldAccrualInboundSid()));

		List<IvldAccrualInbound> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		IvldAccrualInbound existingIvldAccrualInbound = result.get(0);

		Assert.assertEquals(existingIvldAccrualInbound, newIvldAccrualInbound);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldAccrualInbound.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldAccrualInboundSid",
				RandomTestUtil.nextInt()));

		List<IvldAccrualInbound> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		IvldAccrualInbound newIvldAccrualInbound = addIvldAccrualInbound();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldAccrualInbound.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldAccrualInboundSid"));

		Object newIvldAccrualInboundSid = newIvldAccrualInbound.getIvldAccrualInboundSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldAccrualInboundSid",
				new Object[] { newIvldAccrualInboundSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingIvldAccrualInboundSid = result.get(0);

		Assert.assertEquals(existingIvldAccrualInboundSid,
			newIvldAccrualInboundSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldAccrualInbound.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldAccrualInboundSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldAccrualInboundSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected IvldAccrualInbound addIvldAccrualInbound()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldAccrualInbound ivldAccrualInbound = _persistence.create(pk);

		ivldAccrualInbound.setRecordCreatedDate(RandomTestUtil.randomString());

		ivldAccrualInbound.setAccountId(RandomTestUtil.randomString());

		ivldAccrualInbound.setPostingIndicator(RandomTestUtil.randomString());

		ivldAccrualInbound.setItemId(RandomTestUtil.randomString());

		ivldAccrualInbound.setModifiedDate(RandomTestUtil.nextDate());

		ivldAccrualInbound.setAccrualPeriodEndDate(RandomTestUtil.randomString());

		ivldAccrualInbound.setItemIdentCodeQualifier(RandomTestUtil.randomString());

		ivldAccrualInbound.setGlCompanyMasterSid(RandomTestUtil.randomString());

		ivldAccrualInbound.setSalesMasterId(RandomTestUtil.randomString());

		ivldAccrualInbound.setCreatedDate(RandomTestUtil.nextDate());

		ivldAccrualInbound.setCreatedBy(RandomTestUtil.randomString());

		ivldAccrualInbound.setSource(RandomTestUtil.randomString());

		ivldAccrualInbound.setAccrualPeriodStartDate(RandomTestUtil.randomString());

		ivldAccrualInbound.setAddChgDelIndicator(RandomTestUtil.randomString());

		ivldAccrualInbound.setSubLedgerType(RandomTestUtil.randomString());

		ivldAccrualInbound.setProgramNo(RandomTestUtil.randomString());

		ivldAccrualInbound.setDocumentType(RandomTestUtil.randomString());

		ivldAccrualInbound.setAccrualIntfid(RandomTestUtil.randomString());

		ivldAccrualInbound.setGlCompanyName(RandomTestUtil.randomString());

		ivldAccrualInbound.setErrorCode(RandomTestUtil.randomString());

		ivldAccrualInbound.setIntfInsertedDate(RandomTestUtil.nextDate());

		ivldAccrualInbound.setModifiedBy(RandomTestUtil.randomString());

		ivldAccrualInbound.setCategoryId(RandomTestUtil.randomString());

		ivldAccrualInbound.setItemNo(RandomTestUtil.randomString());

		ivldAccrualInbound.setReprocessedFlag(RandomTestUtil.randomString());

		ivldAccrualInbound.setCompIdentCodeQualifier(RandomTestUtil.randomString());

		ivldAccrualInbound.setAcctIdentCodeQualifier(RandomTestUtil.randomString());

		ivldAccrualInbound.setContractId(RandomTestUtil.randomString());

		ivldAccrualInbound.setAccountNo(RandomTestUtil.randomString());

		ivldAccrualInbound.setAccrualId(RandomTestUtil.randomString());

		ivldAccrualInbound.setReasonForFailure(RandomTestUtil.randomString());

		ivldAccrualInbound.setCompanyId(RandomTestUtil.randomString());

		ivldAccrualInbound.setAccountName(RandomTestUtil.randomString());

		ivldAccrualInbound.setAccrualType(RandomTestUtil.randomString());

		ivldAccrualInbound.setPostingDate(RandomTestUtil.randomString());

		ivldAccrualInbound.setBrandId(RandomTestUtil.randomString());

		ivldAccrualInbound.setProvisionId(RandomTestUtil.randomString());

		ivldAccrualInbound.setAmount(RandomTestUtil.randomString());

		ivldAccrualInbound.setGlDate(RandomTestUtil.randomString());

		ivldAccrualInbound.setSubLedger(RandomTestUtil.randomString());

		ivldAccrualInbound.setCompanyCostCenter(RandomTestUtil.randomString());

		ivldAccrualInbound.setGlAccount(RandomTestUtil.randomString());

		ivldAccrualInbound.setCompanyNo(RandomTestUtil.randomString());

		ivldAccrualInbound.setBatchId(RandomTestUtil.randomString());

		ivldAccrualInbound.setProgramType(RandomTestUtil.randomString());

		ivldAccrualInbound.setItemName(RandomTestUtil.randomString());

		ivldAccrualInbound.setErrorField(RandomTestUtil.randomString());

		ivldAccrualInbound.setContractNo(RandomTestUtil.randomString());

		ivldAccrualInbound.setBrandName(RandomTestUtil.randomString());

		ivldAccrualInbound.setContractName(RandomTestUtil.randomString());

		ivldAccrualInbound.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldAccrualInbounds.add(_persistence.update(ivldAccrualInbound));

		return ivldAccrualInbound;
	}

	private List<IvldAccrualInbound> _ivldAccrualInbounds = new ArrayList<IvldAccrualInbound>();
	private IvldAccrualInboundPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}