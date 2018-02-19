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

import com.stpl.app.exception.NoSuchIvldActualMasterException;
import com.stpl.app.model.IvldActualMaster;
import com.stpl.app.service.IvldActualMasterLocalServiceUtil;
import com.stpl.app.service.persistence.IvldActualMasterPersistence;
import com.stpl.app.service.persistence.IvldActualMasterUtil;

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
public class IvldActualMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = IvldActualMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<IvldActualMaster> iterator = _ivldActualMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldActualMaster ivldActualMaster = _persistence.create(pk);

		Assert.assertNotNull(ivldActualMaster);

		Assert.assertEquals(ivldActualMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		IvldActualMaster newIvldActualMaster = addIvldActualMaster();

		_persistence.remove(newIvldActualMaster);

		IvldActualMaster existingIvldActualMaster = _persistence.fetchByPrimaryKey(newIvldActualMaster.getPrimaryKey());

		Assert.assertNull(existingIvldActualMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addIvldActualMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldActualMaster newIvldActualMaster = _persistence.create(pk);

		newIvldActualMaster.setActualIntfid(RandomTestUtil.randomString());

		newIvldActualMaster.setAccountId(RandomTestUtil.randomString());

		newIvldActualMaster.setProgramStateCode(RandomTestUtil.randomString());

		newIvldActualMaster.setSettlementNo(RandomTestUtil.randomString());

		newIvldActualMaster.setAccrualActualEndDate(RandomTestUtil.randomString());

		newIvldActualMaster.setActualId(RandomTestUtil.randomString());

		newIvldActualMaster.setModifiedDate(RandomTestUtil.nextDate());

		newIvldActualMaster.setDivisionId(RandomTestUtil.randomString());

		newIvldActualMaster.setOrganizationKey(RandomTestUtil.randomString());

		newIvldActualMaster.setQuantityInclusion(RandomTestUtil.randomString());

		newIvldActualMaster.setCashPaidDate(RandomTestUtil.randomString());

		newIvldActualMaster.setSource(RandomTestUtil.randomString());

		newIvldActualMaster.setAddChgDelIndicator(RandomTestUtil.randomString());

		newIvldActualMaster.setAnalysisCode(RandomTestUtil.randomString());

		newIvldActualMaster.setAccrualActualStartDate(RandomTestUtil.randomString());

		newIvldActualMaster.setModifiedBy(RandomTestUtil.randomString());

		newIvldActualMaster.setSalesAmount(RandomTestUtil.randomString());

		newIvldActualMaster.setQuantity(RandomTestUtil.randomString());

		newIvldActualMaster.setSentOut(RandomTestUtil.randomString());

		newIvldActualMaster.setAccountNo(RandomTestUtil.randomString());

		newIvldActualMaster.setReasonForFailure(RandomTestUtil.randomString());

		newIvldActualMaster.setAccountName(RandomTestUtil.randomString());

		newIvldActualMaster.setProvisionId(RandomTestUtil.randomString());

		newIvldActualMaster.setAmount(RandomTestUtil.randomString());

		newIvldActualMaster.setMarketId(RandomTestUtil.randomString());

		newIvldActualMaster.setIsActive(RandomTestUtil.randomString());

		newIvldActualMaster.setSettlementMethodNo(RandomTestUtil.randomString());

		newIvldActualMaster.setItemIdentifierCodeQualifier(RandomTestUtil.randomString());

		newIvldActualMaster.setPriceAdjustmentName(RandomTestUtil.randomString());

		newIvldActualMaster.setErrorField(RandomTestUtil.randomString());

		newIvldActualMaster.setRecordSequence(RandomTestUtil.randomString());

		newIvldActualMaster.setMandatedDiscountAmount(RandomTestUtil.randomString());

		newIvldActualMaster.setParentcomDivmktBrandProdkey(RandomTestUtil.randomString());

		newIvldActualMaster.setPrice(RandomTestUtil.randomString());

		newIvldActualMaster.setDispensedDate(RandomTestUtil.randomString());

		newIvldActualMaster.setItemId(RandomTestUtil.randomString());

		newIvldActualMaster.setAccrualProcessed(RandomTestUtil.randomString());

		newIvldActualMaster.setUploadDate(RandomTestUtil.randomString());

		newIvldActualMaster.setCreatedBy(RandomTestUtil.randomString());

		newIvldActualMaster.setCreatedDate(RandomTestUtil.nextDate());

		newIvldActualMaster.setInvoiceLineNo(RandomTestUtil.randomString());

		newIvldActualMaster.setErrorCode(RandomTestUtil.randomString());

		newIvldActualMaster.setIntfInsertedDate(RandomTestUtil.nextDate());

		newIvldActualMaster.setItemNo(RandomTestUtil.randomString());

		newIvldActualMaster.setReprocessedFlag(RandomTestUtil.randomString());

		newIvldActualMaster.setAcctIdentifierCodeQualifier(RandomTestUtil.randomString());

		newIvldActualMaster.setContractId(RandomTestUtil.randomString());

		newIvldActualMaster.setBrandId(RandomTestUtil.randomString());

		newIvldActualMaster.setComDivMktBrandProdKey(RandomTestUtil.randomString());

		newIvldActualMaster.setClaimIndicator(RandomTestUtil.randomString());

		newIvldActualMaster.setBatchId(RandomTestUtil.randomString());

		newIvldActualMaster.setProvisionClaimIndicator(RandomTestUtil.randomString());

		newIvldActualMaster.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldActualMasters.add(_persistence.update(newIvldActualMaster));

		IvldActualMaster existingIvldActualMaster = _persistence.findByPrimaryKey(newIvldActualMaster.getPrimaryKey());

		Assert.assertEquals(existingIvldActualMaster.getActualIntfid(),
			newIvldActualMaster.getActualIntfid());
		Assert.assertEquals(existingIvldActualMaster.getAccountId(),
			newIvldActualMaster.getAccountId());
		Assert.assertEquals(existingIvldActualMaster.getProgramStateCode(),
			newIvldActualMaster.getProgramStateCode());
		Assert.assertEquals(existingIvldActualMaster.getSettlementNo(),
			newIvldActualMaster.getSettlementNo());
		Assert.assertEquals(existingIvldActualMaster.getAccrualActualEndDate(),
			newIvldActualMaster.getAccrualActualEndDate());
		Assert.assertEquals(existingIvldActualMaster.getActualId(),
			newIvldActualMaster.getActualId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldActualMaster.getModifiedDate()),
			Time.getShortTimestamp(newIvldActualMaster.getModifiedDate()));
		Assert.assertEquals(existingIvldActualMaster.getDivisionId(),
			newIvldActualMaster.getDivisionId());
		Assert.assertEquals(existingIvldActualMaster.getOrganizationKey(),
			newIvldActualMaster.getOrganizationKey());
		Assert.assertEquals(existingIvldActualMaster.getQuantityInclusion(),
			newIvldActualMaster.getQuantityInclusion());
		Assert.assertEquals(existingIvldActualMaster.getCashPaidDate(),
			newIvldActualMaster.getCashPaidDate());
		Assert.assertEquals(existingIvldActualMaster.getSource(),
			newIvldActualMaster.getSource());
		Assert.assertEquals(existingIvldActualMaster.getAddChgDelIndicator(),
			newIvldActualMaster.getAddChgDelIndicator());
		Assert.assertEquals(existingIvldActualMaster.getAnalysisCode(),
			newIvldActualMaster.getAnalysisCode());
		Assert.assertEquals(existingIvldActualMaster.getAccrualActualStartDate(),
			newIvldActualMaster.getAccrualActualStartDate());
		Assert.assertEquals(existingIvldActualMaster.getModifiedBy(),
			newIvldActualMaster.getModifiedBy());
		Assert.assertEquals(existingIvldActualMaster.getSalesAmount(),
			newIvldActualMaster.getSalesAmount());
		Assert.assertEquals(existingIvldActualMaster.getQuantity(),
			newIvldActualMaster.getQuantity());
		Assert.assertEquals(existingIvldActualMaster.getSentOut(),
			newIvldActualMaster.getSentOut());
		Assert.assertEquals(existingIvldActualMaster.getAccountNo(),
			newIvldActualMaster.getAccountNo());
		Assert.assertEquals(existingIvldActualMaster.getReasonForFailure(),
			newIvldActualMaster.getReasonForFailure());
		Assert.assertEquals(existingIvldActualMaster.getAccountName(),
			newIvldActualMaster.getAccountName());
		Assert.assertEquals(existingIvldActualMaster.getProvisionId(),
			newIvldActualMaster.getProvisionId());
		Assert.assertEquals(existingIvldActualMaster.getAmount(),
			newIvldActualMaster.getAmount());
		Assert.assertEquals(existingIvldActualMaster.getMarketId(),
			newIvldActualMaster.getMarketId());
		Assert.assertEquals(existingIvldActualMaster.getIsActive(),
			newIvldActualMaster.getIsActive());
		Assert.assertEquals(existingIvldActualMaster.getSettlementMethodNo(),
			newIvldActualMaster.getSettlementMethodNo());
		Assert.assertEquals(existingIvldActualMaster.getItemIdentifierCodeQualifier(),
			newIvldActualMaster.getItemIdentifierCodeQualifier());
		Assert.assertEquals(existingIvldActualMaster.getPriceAdjustmentName(),
			newIvldActualMaster.getPriceAdjustmentName());
		Assert.assertEquals(existingIvldActualMaster.getErrorField(),
			newIvldActualMaster.getErrorField());
		Assert.assertEquals(existingIvldActualMaster.getRecordSequence(),
			newIvldActualMaster.getRecordSequence());
		Assert.assertEquals(existingIvldActualMaster.getMandatedDiscountAmount(),
			newIvldActualMaster.getMandatedDiscountAmount());
		Assert.assertEquals(existingIvldActualMaster.getParentcomDivmktBrandProdkey(),
			newIvldActualMaster.getParentcomDivmktBrandProdkey());
		Assert.assertEquals(existingIvldActualMaster.getPrice(),
			newIvldActualMaster.getPrice());
		Assert.assertEquals(existingIvldActualMaster.getDispensedDate(),
			newIvldActualMaster.getDispensedDate());
		Assert.assertEquals(existingIvldActualMaster.getItemId(),
			newIvldActualMaster.getItemId());
		Assert.assertEquals(existingIvldActualMaster.getAccrualProcessed(),
			newIvldActualMaster.getAccrualProcessed());
		Assert.assertEquals(existingIvldActualMaster.getUploadDate(),
			newIvldActualMaster.getUploadDate());
		Assert.assertEquals(existingIvldActualMaster.getCreatedBy(),
			newIvldActualMaster.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldActualMaster.getCreatedDate()),
			Time.getShortTimestamp(newIvldActualMaster.getCreatedDate()));
		Assert.assertEquals(existingIvldActualMaster.getInvoiceLineNo(),
			newIvldActualMaster.getInvoiceLineNo());
		Assert.assertEquals(existingIvldActualMaster.getErrorCode(),
			newIvldActualMaster.getErrorCode());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldActualMaster.getIntfInsertedDate()),
			Time.getShortTimestamp(newIvldActualMaster.getIntfInsertedDate()));
		Assert.assertEquals(existingIvldActualMaster.getItemNo(),
			newIvldActualMaster.getItemNo());
		Assert.assertEquals(existingIvldActualMaster.getReprocessedFlag(),
			newIvldActualMaster.getReprocessedFlag());
		Assert.assertEquals(existingIvldActualMaster.getAcctIdentifierCodeQualifier(),
			newIvldActualMaster.getAcctIdentifierCodeQualifier());
		Assert.assertEquals(existingIvldActualMaster.getContractId(),
			newIvldActualMaster.getContractId());
		Assert.assertEquals(existingIvldActualMaster.getBrandId(),
			newIvldActualMaster.getBrandId());
		Assert.assertEquals(existingIvldActualMaster.getComDivMktBrandProdKey(),
			newIvldActualMaster.getComDivMktBrandProdKey());
		Assert.assertEquals(existingIvldActualMaster.getClaimIndicator(),
			newIvldActualMaster.getClaimIndicator());
		Assert.assertEquals(existingIvldActualMaster.getIvldActualMasterSid(),
			newIvldActualMaster.getIvldActualMasterSid());
		Assert.assertEquals(existingIvldActualMaster.getBatchId(),
			newIvldActualMaster.getBatchId());
		Assert.assertEquals(existingIvldActualMaster.getProvisionClaimIndicator(),
			newIvldActualMaster.getProvisionClaimIndicator());
		Assert.assertEquals(existingIvldActualMaster.getCheckRecord(),
			newIvldActualMaster.getCheckRecord());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		IvldActualMaster newIvldActualMaster = addIvldActualMaster();

		IvldActualMaster existingIvldActualMaster = _persistence.findByPrimaryKey(newIvldActualMaster.getPrimaryKey());

		Assert.assertEquals(existingIvldActualMaster, newIvldActualMaster);
	}

	@Test(expected = NoSuchIvldActualMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<IvldActualMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("IVLD_ACTUAL_MASTER",
			"actualIntfid", true, "accountId", true, "programStateCode", true,
			"settlementNo", true, "accrualActualEndDate", true, "actualId",
			true, "modifiedDate", true, "divisionId", true, "organizationKey",
			true, "quantityInclusion", true, "cashPaidDate", true, "source",
			true, "addChgDelIndicator", true, "analysisCode", true,
			"accrualActualStartDate", true, "modifiedBy", true, "salesAmount",
			true, "quantity", true, "sentOut", true, "accountNo", true,
			"reasonForFailure", true, "accountName", true, "provisionId", true,
			"amount", true, "marketId", true, "isActive", true,
			"settlementMethodNo", true, "itemIdentifierCodeQualifier", true,
			"priceAdjustmentName", true, "errorField", true, "recordSequence",
			true, "mandatedDiscountAmount", true,
			"parentcomDivmktBrandProdkey", true, "price", true,
			"dispensedDate", true, "itemId", true, "accrualProcessed", true,
			"uploadDate", true, "createdBy", true, "createdDate", true,
			"invoiceLineNo", true, "errorCode", true, "intfInsertedDate", true,
			"itemNo", true, "reprocessedFlag", true,
			"acctIdentifierCodeQualifier", true, "contractId", true, "brandId",
			true, "comDivMktBrandProdKey", true, "claimIndicator", true,
			"ivldActualMasterSid", true, "batchId", true,
			"provisionClaimIndicator", true, "checkRecord", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		IvldActualMaster newIvldActualMaster = addIvldActualMaster();

		IvldActualMaster existingIvldActualMaster = _persistence.fetchByPrimaryKey(newIvldActualMaster.getPrimaryKey());

		Assert.assertEquals(existingIvldActualMaster, newIvldActualMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldActualMaster missingIvldActualMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingIvldActualMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		IvldActualMaster newIvldActualMaster1 = addIvldActualMaster();
		IvldActualMaster newIvldActualMaster2 = addIvldActualMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldActualMaster1.getPrimaryKey());
		primaryKeys.add(newIvldActualMaster2.getPrimaryKey());

		Map<Serializable, IvldActualMaster> ivldActualMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ivldActualMasters.size());
		Assert.assertEquals(newIvldActualMaster1,
			ivldActualMasters.get(newIvldActualMaster1.getPrimaryKey()));
		Assert.assertEquals(newIvldActualMaster2,
			ivldActualMasters.get(newIvldActualMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, IvldActualMaster> ivldActualMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldActualMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		IvldActualMaster newIvldActualMaster = addIvldActualMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldActualMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, IvldActualMaster> ivldActualMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldActualMasters.size());
		Assert.assertEquals(newIvldActualMaster,
			ivldActualMasters.get(newIvldActualMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, IvldActualMaster> ivldActualMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldActualMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		IvldActualMaster newIvldActualMaster = addIvldActualMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldActualMaster.getPrimaryKey());

		Map<Serializable, IvldActualMaster> ivldActualMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldActualMasters.size());
		Assert.assertEquals(newIvldActualMaster,
			ivldActualMasters.get(newIvldActualMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = IvldActualMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<IvldActualMaster>() {
				@Override
				public void performAction(IvldActualMaster ivldActualMaster) {
					Assert.assertNotNull(ivldActualMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		IvldActualMaster newIvldActualMaster = addIvldActualMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldActualMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldActualMasterSid",
				newIvldActualMaster.getIvldActualMasterSid()));

		List<IvldActualMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		IvldActualMaster existingIvldActualMaster = result.get(0);

		Assert.assertEquals(existingIvldActualMaster, newIvldActualMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldActualMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldActualMasterSid",
				RandomTestUtil.nextInt()));

		List<IvldActualMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		IvldActualMaster newIvldActualMaster = addIvldActualMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldActualMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldActualMasterSid"));

		Object newIvldActualMasterSid = newIvldActualMaster.getIvldActualMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldActualMasterSid",
				new Object[] { newIvldActualMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingIvldActualMasterSid = result.get(0);

		Assert.assertEquals(existingIvldActualMasterSid, newIvldActualMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldActualMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldActualMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldActualMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected IvldActualMaster addIvldActualMaster() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldActualMaster ivldActualMaster = _persistence.create(pk);

		ivldActualMaster.setActualIntfid(RandomTestUtil.randomString());

		ivldActualMaster.setAccountId(RandomTestUtil.randomString());

		ivldActualMaster.setProgramStateCode(RandomTestUtil.randomString());

		ivldActualMaster.setSettlementNo(RandomTestUtil.randomString());

		ivldActualMaster.setAccrualActualEndDate(RandomTestUtil.randomString());

		ivldActualMaster.setActualId(RandomTestUtil.randomString());

		ivldActualMaster.setModifiedDate(RandomTestUtil.nextDate());

		ivldActualMaster.setDivisionId(RandomTestUtil.randomString());

		ivldActualMaster.setOrganizationKey(RandomTestUtil.randomString());

		ivldActualMaster.setQuantityInclusion(RandomTestUtil.randomString());

		ivldActualMaster.setCashPaidDate(RandomTestUtil.randomString());

		ivldActualMaster.setSource(RandomTestUtil.randomString());

		ivldActualMaster.setAddChgDelIndicator(RandomTestUtil.randomString());

		ivldActualMaster.setAnalysisCode(RandomTestUtil.randomString());

		ivldActualMaster.setAccrualActualStartDate(RandomTestUtil.randomString());

		ivldActualMaster.setModifiedBy(RandomTestUtil.randomString());

		ivldActualMaster.setSalesAmount(RandomTestUtil.randomString());

		ivldActualMaster.setQuantity(RandomTestUtil.randomString());

		ivldActualMaster.setSentOut(RandomTestUtil.randomString());

		ivldActualMaster.setAccountNo(RandomTestUtil.randomString());

		ivldActualMaster.setReasonForFailure(RandomTestUtil.randomString());

		ivldActualMaster.setAccountName(RandomTestUtil.randomString());

		ivldActualMaster.setProvisionId(RandomTestUtil.randomString());

		ivldActualMaster.setAmount(RandomTestUtil.randomString());

		ivldActualMaster.setMarketId(RandomTestUtil.randomString());

		ivldActualMaster.setIsActive(RandomTestUtil.randomString());

		ivldActualMaster.setSettlementMethodNo(RandomTestUtil.randomString());

		ivldActualMaster.setItemIdentifierCodeQualifier(RandomTestUtil.randomString());

		ivldActualMaster.setPriceAdjustmentName(RandomTestUtil.randomString());

		ivldActualMaster.setErrorField(RandomTestUtil.randomString());

		ivldActualMaster.setRecordSequence(RandomTestUtil.randomString());

		ivldActualMaster.setMandatedDiscountAmount(RandomTestUtil.randomString());

		ivldActualMaster.setParentcomDivmktBrandProdkey(RandomTestUtil.randomString());

		ivldActualMaster.setPrice(RandomTestUtil.randomString());

		ivldActualMaster.setDispensedDate(RandomTestUtil.randomString());

		ivldActualMaster.setItemId(RandomTestUtil.randomString());

		ivldActualMaster.setAccrualProcessed(RandomTestUtil.randomString());

		ivldActualMaster.setUploadDate(RandomTestUtil.randomString());

		ivldActualMaster.setCreatedBy(RandomTestUtil.randomString());

		ivldActualMaster.setCreatedDate(RandomTestUtil.nextDate());

		ivldActualMaster.setInvoiceLineNo(RandomTestUtil.randomString());

		ivldActualMaster.setErrorCode(RandomTestUtil.randomString());

		ivldActualMaster.setIntfInsertedDate(RandomTestUtil.nextDate());

		ivldActualMaster.setItemNo(RandomTestUtil.randomString());

		ivldActualMaster.setReprocessedFlag(RandomTestUtil.randomString());

		ivldActualMaster.setAcctIdentifierCodeQualifier(RandomTestUtil.randomString());

		ivldActualMaster.setContractId(RandomTestUtil.randomString());

		ivldActualMaster.setBrandId(RandomTestUtil.randomString());

		ivldActualMaster.setComDivMktBrandProdKey(RandomTestUtil.randomString());

		ivldActualMaster.setClaimIndicator(RandomTestUtil.randomString());

		ivldActualMaster.setBatchId(RandomTestUtil.randomString());

		ivldActualMaster.setProvisionClaimIndicator(RandomTestUtil.randomString());

		ivldActualMaster.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldActualMasters.add(_persistence.update(ivldActualMaster));

		return ivldActualMaster;
	}

	private List<IvldActualMaster> _ivldActualMasters = new ArrayList<IvldActualMaster>();
	private IvldActualMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}