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

import com.stpl.app.exception.NoSuchAccrualDetailsException;
import com.stpl.app.model.AccrualDetails;
import com.stpl.app.service.AccrualDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.AccrualDetailsPersistence;
import com.stpl.app.service.persistence.AccrualDetailsUtil;

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
public class AccrualDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = AccrualDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<AccrualDetails> iterator = _accrualDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AccrualDetails accrualDetails = _persistence.create(pk);

		Assert.assertNotNull(accrualDetails);

		Assert.assertEquals(accrualDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		AccrualDetails newAccrualDetails = addAccrualDetails();

		_persistence.remove(newAccrualDetails);

		AccrualDetails existingAccrualDetails = _persistence.fetchByPrimaryKey(newAccrualDetails.getPrimaryKey());

		Assert.assertNull(existingAccrualDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addAccrualDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AccrualDetails newAccrualDetails = _persistence.create(pk);

		newAccrualDetails.setAccountId(RandomTestUtil.randomString());

		newAccrualDetails.setRecordCreatedDate(RandomTestUtil.nextDate());

		newAccrualDetails.setPostingIndicator(RandomTestUtil.randomString());

		newAccrualDetails.setBrandName(RandomTestUtil.randomString());

		newAccrualDetails.setAccrualPeriodEndDate(RandomTestUtil.nextDate());

		newAccrualDetails.setModifiedDate(RandomTestUtil.nextDate());

		newAccrualDetails.setSalesMasterId(RandomTestUtil.randomString());

		newAccrualDetails.setSource(RandomTestUtil.randomString());

		newAccrualDetails.setContractMasterSid(RandomTestUtil.nextInt());

		newAccrualDetails.setDocumentType(RandomTestUtil.randomString());

		newAccrualDetails.setInboundStatus(RandomTestUtil.randomString());

		newAccrualDetails.setModifiedBy(RandomTestUtil.nextInt());

		newAccrualDetails.setDeductionType(RandomTestUtil.randomString());

		newAccrualDetails.setCompanyMasterSid(RandomTestUtil.nextInt());

		newAccrualDetails.setContractName(RandomTestUtil.randomString());

		newAccrualDetails.setAccountNo(RandomTestUtil.randomString());

		newAccrualDetails.setAccountName(RandomTestUtil.randomString());

		newAccrualDetails.setVersionNo(RandomTestUtil.nextInt());

		newAccrualDetails.setProvisionId(RandomTestUtil.randomString());

		newAccrualDetails.setCompanyStringIdentifierCodeQualifier(RandomTestUtil.randomString());

		newAccrualDetails.setAmount(RandomTestUtil.nextDouble());

		newAccrualDetails.setSubLedger(RandomTestUtil.randomString());

		newAccrualDetails.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newAccrualDetails.setItemIdentifierCodeQualifier(RandomTestUtil.randomString());

		newAccrualDetails.setCompanyNo(RandomTestUtil.randomString());

		newAccrualDetails.setPostingStatus(RandomTestUtil.randomString());

		newAccrualDetails.setItemName(RandomTestUtil.randomString());

		newAccrualDetails.setRsModelSid(RandomTestUtil.nextInt());

		newAccrualDetails.setItemMasterSid(RandomTestUtil.nextInt());

		newAccrualDetails.setItemId(RandomTestUtil.randomString());

		newAccrualDetails.setBrandMasterSid(RandomTestUtil.nextInt());

		newAccrualDetails.setGlCompanyMasterSid(RandomTestUtil.randomString());

		newAccrualDetails.setCreatedBy(RandomTestUtil.nextInt());

		newAccrualDetails.setCreatedDate(RandomTestUtil.nextDate());

		newAccrualDetails.setAccrualPeriodStartDate(RandomTestUtil.nextDate());

		newAccrualDetails.setSubLedgerType(RandomTestUtil.randomString());

		newAccrualDetails.setProgramNo(RandomTestUtil.randomString());

		newAccrualDetails.setCategoryId(RandomTestUtil.randomString());

		newAccrualDetails.setItemNo(RandomTestUtil.randomString());

		newAccrualDetails.setDeductionSubType(RandomTestUtil.randomString());

		newAccrualDetails.setAcctIdentifierCodeQualifier(RandomTestUtil.randomString());

		newAccrualDetails.setContractId(RandomTestUtil.randomString());

		newAccrualDetails.setAccrualId(RandomTestUtil.randomString());

		newAccrualDetails.setCompanyStringId(RandomTestUtil.randomString());

		newAccrualDetails.setAccrualType(RandomTestUtil.randomString());

		newAccrualDetails.setBrandId(RandomTestUtil.randomString());

		newAccrualDetails.setPostingDate(RandomTestUtil.nextDate());

		newAccrualDetails.setGlDate(RandomTestUtil.nextDate());

		newAccrualDetails.setCompanyCostCenter(RandomTestUtil.randomString());

		newAccrualDetails.setGlAccount(RandomTestUtil.randomString());

		newAccrualDetails.setBatchId(RandomTestUtil.randomString());

		newAccrualDetails.setProgramType(RandomTestUtil.nextInt());

		newAccrualDetails.setContractNo(RandomTestUtil.randomString());

		_accrualDetailses.add(_persistence.update(newAccrualDetails));

		AccrualDetails existingAccrualDetails = _persistence.findByPrimaryKey(newAccrualDetails.getPrimaryKey());

		Assert.assertEquals(existingAccrualDetails.getAccountId(),
			newAccrualDetails.getAccountId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingAccrualDetails.getRecordCreatedDate()),
			Time.getShortTimestamp(newAccrualDetails.getRecordCreatedDate()));
		Assert.assertEquals(existingAccrualDetails.getPostingIndicator(),
			newAccrualDetails.getPostingIndicator());
		Assert.assertEquals(existingAccrualDetails.getBrandName(),
			newAccrualDetails.getBrandName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingAccrualDetails.getAccrualPeriodEndDate()),
			Time.getShortTimestamp(newAccrualDetails.getAccrualPeriodEndDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingAccrualDetails.getModifiedDate()),
			Time.getShortTimestamp(newAccrualDetails.getModifiedDate()));
		Assert.assertEquals(existingAccrualDetails.getSalesMasterId(),
			newAccrualDetails.getSalesMasterId());
		Assert.assertEquals(existingAccrualDetails.getSource(),
			newAccrualDetails.getSource());
		Assert.assertEquals(existingAccrualDetails.getContractMasterSid(),
			newAccrualDetails.getContractMasterSid());
		Assert.assertEquals(existingAccrualDetails.getAccrualDetailsSid(),
			newAccrualDetails.getAccrualDetailsSid());
		Assert.assertEquals(existingAccrualDetails.getDocumentType(),
			newAccrualDetails.getDocumentType());
		Assert.assertEquals(existingAccrualDetails.getInboundStatus(),
			newAccrualDetails.getInboundStatus());
		Assert.assertEquals(existingAccrualDetails.getModifiedBy(),
			newAccrualDetails.getModifiedBy());
		Assert.assertEquals(existingAccrualDetails.getDeductionType(),
			newAccrualDetails.getDeductionType());
		Assert.assertEquals(existingAccrualDetails.getCompanyMasterSid(),
			newAccrualDetails.getCompanyMasterSid());
		Assert.assertEquals(existingAccrualDetails.getContractName(),
			newAccrualDetails.getContractName());
		Assert.assertEquals(existingAccrualDetails.getAccountNo(),
			newAccrualDetails.getAccountNo());
		Assert.assertEquals(existingAccrualDetails.getAccountName(),
			newAccrualDetails.getAccountName());
		Assert.assertEquals(existingAccrualDetails.getVersionNo(),
			newAccrualDetails.getVersionNo());
		Assert.assertEquals(existingAccrualDetails.getProvisionId(),
			newAccrualDetails.getProvisionId());
		Assert.assertEquals(existingAccrualDetails.getCompanyStringIdentifierCodeQualifier(),
			newAccrualDetails.getCompanyStringIdentifierCodeQualifier());
		AssertUtils.assertEquals(existingAccrualDetails.getAmount(),
			newAccrualDetails.getAmount());
		Assert.assertEquals(existingAccrualDetails.getSubLedger(),
			newAccrualDetails.getSubLedger());
		Assert.assertEquals(existingAccrualDetails.getRecordLockStatus(),
			newAccrualDetails.getRecordLockStatus());
		Assert.assertEquals(existingAccrualDetails.getItemIdentifierCodeQualifier(),
			newAccrualDetails.getItemIdentifierCodeQualifier());
		Assert.assertEquals(existingAccrualDetails.getCompanyNo(),
			newAccrualDetails.getCompanyNo());
		Assert.assertEquals(existingAccrualDetails.getPostingStatus(),
			newAccrualDetails.getPostingStatus());
		Assert.assertEquals(existingAccrualDetails.getItemName(),
			newAccrualDetails.getItemName());
		Assert.assertEquals(existingAccrualDetails.getRsModelSid(),
			newAccrualDetails.getRsModelSid());
		Assert.assertEquals(existingAccrualDetails.getItemMasterSid(),
			newAccrualDetails.getItemMasterSid());
		Assert.assertEquals(existingAccrualDetails.getItemId(),
			newAccrualDetails.getItemId());
		Assert.assertEquals(existingAccrualDetails.getBrandMasterSid(),
			newAccrualDetails.getBrandMasterSid());
		Assert.assertEquals(existingAccrualDetails.getGlCompanyMasterSid(),
			newAccrualDetails.getGlCompanyMasterSid());
		Assert.assertEquals(existingAccrualDetails.getCreatedBy(),
			newAccrualDetails.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingAccrualDetails.getCreatedDate()),
			Time.getShortTimestamp(newAccrualDetails.getCreatedDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingAccrualDetails.getAccrualPeriodStartDate()),
			Time.getShortTimestamp(
				newAccrualDetails.getAccrualPeriodStartDate()));
		Assert.assertEquals(existingAccrualDetails.getSubLedgerType(),
			newAccrualDetails.getSubLedgerType());
		Assert.assertEquals(existingAccrualDetails.getProgramNo(),
			newAccrualDetails.getProgramNo());
		Assert.assertEquals(existingAccrualDetails.getCategoryId(),
			newAccrualDetails.getCategoryId());
		Assert.assertEquals(existingAccrualDetails.getItemNo(),
			newAccrualDetails.getItemNo());
		Assert.assertEquals(existingAccrualDetails.getDeductionSubType(),
			newAccrualDetails.getDeductionSubType());
		Assert.assertEquals(existingAccrualDetails.getAcctIdentifierCodeQualifier(),
			newAccrualDetails.getAcctIdentifierCodeQualifier());
		Assert.assertEquals(existingAccrualDetails.getContractId(),
			newAccrualDetails.getContractId());
		Assert.assertEquals(existingAccrualDetails.getAccrualId(),
			newAccrualDetails.getAccrualId());
		Assert.assertEquals(existingAccrualDetails.getCompanyStringId(),
			newAccrualDetails.getCompanyStringId());
		Assert.assertEquals(existingAccrualDetails.getAccrualType(),
			newAccrualDetails.getAccrualType());
		Assert.assertEquals(existingAccrualDetails.getBrandId(),
			newAccrualDetails.getBrandId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingAccrualDetails.getPostingDate()),
			Time.getShortTimestamp(newAccrualDetails.getPostingDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingAccrualDetails.getGlDate()),
			Time.getShortTimestamp(newAccrualDetails.getGlDate()));
		Assert.assertEquals(existingAccrualDetails.getCompanyCostCenter(),
			newAccrualDetails.getCompanyCostCenter());
		Assert.assertEquals(existingAccrualDetails.getGlAccount(),
			newAccrualDetails.getGlAccount());
		Assert.assertEquals(existingAccrualDetails.getBatchId(),
			newAccrualDetails.getBatchId());
		Assert.assertEquals(existingAccrualDetails.getProgramType(),
			newAccrualDetails.getProgramType());
		Assert.assertEquals(existingAccrualDetails.getContractNo(),
			newAccrualDetails.getContractNo());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		AccrualDetails newAccrualDetails = addAccrualDetails();

		AccrualDetails existingAccrualDetails = _persistence.findByPrimaryKey(newAccrualDetails.getPrimaryKey());

		Assert.assertEquals(existingAccrualDetails, newAccrualDetails);
	}

	@Test(expected = NoSuchAccrualDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<AccrualDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("ACCRUAL_DETAILS",
			"accountId", true, "recordCreatedDate", true, "postingIndicator",
			true, "brandName", true, "accrualPeriodEndDate", true,
			"modifiedDate", true, "salesMasterId", true, "source", true,
			"contractMasterSid", true, "accrualDetailsSid", true,
			"documentType", true, "inboundStatus", true, "modifiedBy", true,
			"deductionType", true, "companyMasterSid", true, "contractName",
			true, "accountNo", true, "accountName", true, "versionNo", true,
			"provisionId", true, "companyStringIdentifierCodeQualifier", true,
			"amount", true, "subLedger", true, "recordLockStatus", true,
			"itemIdentifierCodeQualifier", true, "companyNo", true,
			"postingStatus", true, "itemName", true, "rsModelSid", true,
			"itemMasterSid", true, "itemId", true, "brandMasterSid", true,
			"glCompanyMasterSid", true, "createdBy", true, "createdDate", true,
			"accrualPeriodStartDate", true, "subLedgerType", true, "programNo",
			true, "categoryId", true, "itemNo", true, "deductionSubType", true,
			"acctIdentifierCodeQualifier", true, "contractId", true,
			"accrualId", true, "companyStringId", true, "accrualType", true,
			"brandId", true, "postingDate", true, "glDate", true,
			"companyCostCenter", true, "glAccount", true, "batchId", true,
			"programType", true, "contractNo", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		AccrualDetails newAccrualDetails = addAccrualDetails();

		AccrualDetails existingAccrualDetails = _persistence.fetchByPrimaryKey(newAccrualDetails.getPrimaryKey());

		Assert.assertEquals(existingAccrualDetails, newAccrualDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AccrualDetails missingAccrualDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingAccrualDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		AccrualDetails newAccrualDetails1 = addAccrualDetails();
		AccrualDetails newAccrualDetails2 = addAccrualDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAccrualDetails1.getPrimaryKey());
		primaryKeys.add(newAccrualDetails2.getPrimaryKey());

		Map<Serializable, AccrualDetails> accrualDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, accrualDetailses.size());
		Assert.assertEquals(newAccrualDetails1,
			accrualDetailses.get(newAccrualDetails1.getPrimaryKey()));
		Assert.assertEquals(newAccrualDetails2,
			accrualDetailses.get(newAccrualDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, AccrualDetails> accrualDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(accrualDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		AccrualDetails newAccrualDetails = addAccrualDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAccrualDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, AccrualDetails> accrualDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, accrualDetailses.size());
		Assert.assertEquals(newAccrualDetails,
			accrualDetailses.get(newAccrualDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, AccrualDetails> accrualDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(accrualDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		AccrualDetails newAccrualDetails = addAccrualDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAccrualDetails.getPrimaryKey());

		Map<Serializable, AccrualDetails> accrualDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, accrualDetailses.size());
		Assert.assertEquals(newAccrualDetails,
			accrualDetailses.get(newAccrualDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = AccrualDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<AccrualDetails>() {
				@Override
				public void performAction(AccrualDetails accrualDetails) {
					Assert.assertNotNull(accrualDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		AccrualDetails newAccrualDetails = addAccrualDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AccrualDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("accrualDetailsSid",
				newAccrualDetails.getAccrualDetailsSid()));

		List<AccrualDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		AccrualDetails existingAccrualDetails = result.get(0);

		Assert.assertEquals(existingAccrualDetails, newAccrualDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AccrualDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("accrualDetailsSid",
				RandomTestUtil.nextInt()));

		List<AccrualDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		AccrualDetails newAccrualDetails = addAccrualDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AccrualDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"accrualDetailsSid"));

		Object newAccrualDetailsSid = newAccrualDetails.getAccrualDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("accrualDetailsSid",
				new Object[] { newAccrualDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingAccrualDetailsSid = result.get(0);

		Assert.assertEquals(existingAccrualDetailsSid, newAccrualDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AccrualDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"accrualDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("accrualDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected AccrualDetails addAccrualDetails() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AccrualDetails accrualDetails = _persistence.create(pk);

		accrualDetails.setAccountId(RandomTestUtil.randomString());

		accrualDetails.setRecordCreatedDate(RandomTestUtil.nextDate());

		accrualDetails.setPostingIndicator(RandomTestUtil.randomString());

		accrualDetails.setBrandName(RandomTestUtil.randomString());

		accrualDetails.setAccrualPeriodEndDate(RandomTestUtil.nextDate());

		accrualDetails.setModifiedDate(RandomTestUtil.nextDate());

		accrualDetails.setSalesMasterId(RandomTestUtil.randomString());

		accrualDetails.setSource(RandomTestUtil.randomString());

		accrualDetails.setContractMasterSid(RandomTestUtil.nextInt());

		accrualDetails.setDocumentType(RandomTestUtil.randomString());

		accrualDetails.setInboundStatus(RandomTestUtil.randomString());

		accrualDetails.setModifiedBy(RandomTestUtil.nextInt());

		accrualDetails.setDeductionType(RandomTestUtil.randomString());

		accrualDetails.setCompanyMasterSid(RandomTestUtil.nextInt());

		accrualDetails.setContractName(RandomTestUtil.randomString());

		accrualDetails.setAccountNo(RandomTestUtil.randomString());

		accrualDetails.setAccountName(RandomTestUtil.randomString());

		accrualDetails.setVersionNo(RandomTestUtil.nextInt());

		accrualDetails.setProvisionId(RandomTestUtil.randomString());

		accrualDetails.setCompanyStringIdentifierCodeQualifier(RandomTestUtil.randomString());

		accrualDetails.setAmount(RandomTestUtil.nextDouble());

		accrualDetails.setSubLedger(RandomTestUtil.randomString());

		accrualDetails.setRecordLockStatus(RandomTestUtil.randomBoolean());

		accrualDetails.setItemIdentifierCodeQualifier(RandomTestUtil.randomString());

		accrualDetails.setCompanyNo(RandomTestUtil.randomString());

		accrualDetails.setPostingStatus(RandomTestUtil.randomString());

		accrualDetails.setItemName(RandomTestUtil.randomString());

		accrualDetails.setRsModelSid(RandomTestUtil.nextInt());

		accrualDetails.setItemMasterSid(RandomTestUtil.nextInt());

		accrualDetails.setItemId(RandomTestUtil.randomString());

		accrualDetails.setBrandMasterSid(RandomTestUtil.nextInt());

		accrualDetails.setGlCompanyMasterSid(RandomTestUtil.randomString());

		accrualDetails.setCreatedBy(RandomTestUtil.nextInt());

		accrualDetails.setCreatedDate(RandomTestUtil.nextDate());

		accrualDetails.setAccrualPeriodStartDate(RandomTestUtil.nextDate());

		accrualDetails.setSubLedgerType(RandomTestUtil.randomString());

		accrualDetails.setProgramNo(RandomTestUtil.randomString());

		accrualDetails.setCategoryId(RandomTestUtil.randomString());

		accrualDetails.setItemNo(RandomTestUtil.randomString());

		accrualDetails.setDeductionSubType(RandomTestUtil.randomString());

		accrualDetails.setAcctIdentifierCodeQualifier(RandomTestUtil.randomString());

		accrualDetails.setContractId(RandomTestUtil.randomString());

		accrualDetails.setAccrualId(RandomTestUtil.randomString());

		accrualDetails.setCompanyStringId(RandomTestUtil.randomString());

		accrualDetails.setAccrualType(RandomTestUtil.randomString());

		accrualDetails.setBrandId(RandomTestUtil.randomString());

		accrualDetails.setPostingDate(RandomTestUtil.nextDate());

		accrualDetails.setGlDate(RandomTestUtil.nextDate());

		accrualDetails.setCompanyCostCenter(RandomTestUtil.randomString());

		accrualDetails.setGlAccount(RandomTestUtil.randomString());

		accrualDetails.setBatchId(RandomTestUtil.randomString());

		accrualDetails.setProgramType(RandomTestUtil.nextInt());

		accrualDetails.setContractNo(RandomTestUtil.randomString());

		_accrualDetailses.add(_persistence.update(accrualDetails));

		return accrualDetails;
	}

	private List<AccrualDetails> _accrualDetailses = new ArrayList<AccrualDetails>();
	private AccrualDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}