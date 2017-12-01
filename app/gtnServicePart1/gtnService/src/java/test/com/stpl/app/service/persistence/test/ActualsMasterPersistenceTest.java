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

import com.stpl.app.exception.NoSuchActualsMasterException;
import com.stpl.app.model.ActualsMaster;
import com.stpl.app.service.ActualsMasterLocalServiceUtil;
import com.stpl.app.service.persistence.ActualsMasterPersistence;
import com.stpl.app.service.persistence.ActualsMasterUtil;

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
public class ActualsMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ActualsMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ActualsMaster> iterator = _actualsMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ActualsMaster actualsMaster = _persistence.create(pk);

		Assert.assertNotNull(actualsMaster);

		Assert.assertEquals(actualsMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ActualsMaster newActualsMaster = addActualsMaster();

		_persistence.remove(newActualsMaster);

		ActualsMaster existingActualsMaster = _persistence.fetchByPrimaryKey(newActualsMaster.getPrimaryKey());

		Assert.assertNull(existingActualsMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addActualsMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ActualsMaster newActualsMaster = _persistence.create(pk);

		newActualsMaster.setQuantityInclusion(RandomTestUtil.randomString());

		newActualsMaster.setMandatedDiscountAmount(RandomTestUtil.randomString());

		newActualsMaster.setItemNo(RandomTestUtil.randomString());

		newActualsMaster.setAnalysisCode(RandomTestUtil.randomString());

		newActualsMaster.setRecordSequence(RandomTestUtil.randomString());

		newActualsMaster.setModifiedBy(RandomTestUtil.nextInt());

		newActualsMaster.setSettlementMethodNo(RandomTestUtil.randomString());

		newActualsMaster.setQuantity(RandomTestUtil.randomString());

		newActualsMaster.setAccountId(RandomTestUtil.randomString());

		newActualsMaster.setCreatedDate(RandomTestUtil.nextDate());

		newActualsMaster.setProvisionClaimIndicator(RandomTestUtil.randomString());

		newActualsMaster.setDispensedDate(RandomTestUtil.nextDate());

		newActualsMaster.setIsActive(RandomTestUtil.randomString());

		newActualsMaster.setBatchId(RandomTestUtil.randomString());

		newActualsMaster.setAccrualActualEndDate(RandomTestUtil.nextDate());

		newActualsMaster.setMarketId(RandomTestUtil.randomString());

		newActualsMaster.setBrandId(RandomTestUtil.randomString());

		newActualsMaster.setAccountName(RandomTestUtil.randomString());

		newActualsMaster.setAmount(RandomTestUtil.randomString());

		newActualsMaster.setAcctIdentifierCodeQualifier(RandomTestUtil.randomString());

		newActualsMaster.setOrganizationKey(RandomTestUtil.randomString());

		newActualsMaster.setCreatedBy(RandomTestUtil.nextInt());

		newActualsMaster.setAccrualProcessed(RandomTestUtil.randomString());

		newActualsMaster.setParentcomDivmktBrandProdkey(RandomTestUtil.randomString());

		newActualsMaster.setCashPaidDate(RandomTestUtil.nextDate());

		newActualsMaster.setSalesAmount(RandomTestUtil.randomString());

		newActualsMaster.setAccrualActualStartDate(RandomTestUtil.nextDate());

		newActualsMaster.setSettlementNo(RandomTestUtil.randomString());

		newActualsMaster.setPrice(RandomTestUtil.randomString());

		newActualsMaster.setUploadDate(RandomTestUtil.nextDate());

		newActualsMaster.setClaimIndicator(RandomTestUtil.randomString());

		newActualsMaster.setItemId(RandomTestUtil.randomString());

		newActualsMaster.setPriceAdjustmentName(RandomTestUtil.randomString());

		newActualsMaster.setContractId(RandomTestUtil.randomString());

		newActualsMaster.setModifiedDate(RandomTestUtil.nextDate());

		newActualsMaster.setActualId(RandomTestUtil.randomString());

		newActualsMaster.setProvisionId(RandomTestUtil.randomString());

		newActualsMaster.setSentOut(RandomTestUtil.randomString());

		newActualsMaster.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newActualsMaster.setDivisionId(RandomTestUtil.randomString());

		newActualsMaster.setItemIdentifierCodeQualifier(RandomTestUtil.randomString());

		newActualsMaster.setProgramStateCode(RandomTestUtil.randomString());

		newActualsMaster.setSource(RandomTestUtil.randomString());

		newActualsMaster.setInvoiceLineNo(RandomTestUtil.randomString());

		newActualsMaster.setAccountNo(RandomTestUtil.randomString());

		newActualsMaster.setComDivMktBrandProdKey(RandomTestUtil.randomString());

		newActualsMaster.setInboundStatus(RandomTestUtil.randomString());

		_actualsMasters.add(_persistence.update(newActualsMaster));

		ActualsMaster existingActualsMaster = _persistence.findByPrimaryKey(newActualsMaster.getPrimaryKey());

		Assert.assertEquals(existingActualsMaster.getQuantityInclusion(),
			newActualsMaster.getQuantityInclusion());
		Assert.assertEquals(existingActualsMaster.getMandatedDiscountAmount(),
			newActualsMaster.getMandatedDiscountAmount());
		Assert.assertEquals(existingActualsMaster.getItemNo(),
			newActualsMaster.getItemNo());
		Assert.assertEquals(existingActualsMaster.getAnalysisCode(),
			newActualsMaster.getAnalysisCode());
		Assert.assertEquals(existingActualsMaster.getRecordSequence(),
			newActualsMaster.getRecordSequence());
		Assert.assertEquals(existingActualsMaster.getModifiedBy(),
			newActualsMaster.getModifiedBy());
		Assert.assertEquals(existingActualsMaster.getSettlementMethodNo(),
			newActualsMaster.getSettlementMethodNo());
		Assert.assertEquals(existingActualsMaster.getQuantity(),
			newActualsMaster.getQuantity());
		Assert.assertEquals(existingActualsMaster.getAccountId(),
			newActualsMaster.getAccountId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingActualsMaster.getCreatedDate()),
			Time.getShortTimestamp(newActualsMaster.getCreatedDate()));
		Assert.assertEquals(existingActualsMaster.getProvisionClaimIndicator(),
			newActualsMaster.getProvisionClaimIndicator());
		Assert.assertEquals(Time.getShortTimestamp(
				existingActualsMaster.getDispensedDate()),
			Time.getShortTimestamp(newActualsMaster.getDispensedDate()));
		Assert.assertEquals(existingActualsMaster.getIsActive(),
			newActualsMaster.getIsActive());
		Assert.assertEquals(existingActualsMaster.getBatchId(),
			newActualsMaster.getBatchId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingActualsMaster.getAccrualActualEndDate()),
			Time.getShortTimestamp(newActualsMaster.getAccrualActualEndDate()));
		Assert.assertEquals(existingActualsMaster.getMarketId(),
			newActualsMaster.getMarketId());
		Assert.assertEquals(existingActualsMaster.getBrandId(),
			newActualsMaster.getBrandId());
		Assert.assertEquals(existingActualsMaster.getAccountName(),
			newActualsMaster.getAccountName());
		Assert.assertEquals(existingActualsMaster.getAmount(),
			newActualsMaster.getAmount());
		Assert.assertEquals(existingActualsMaster.getActualsMasterSid(),
			newActualsMaster.getActualsMasterSid());
		Assert.assertEquals(existingActualsMaster.getAcctIdentifierCodeQualifier(),
			newActualsMaster.getAcctIdentifierCodeQualifier());
		Assert.assertEquals(existingActualsMaster.getOrganizationKey(),
			newActualsMaster.getOrganizationKey());
		Assert.assertEquals(existingActualsMaster.getCreatedBy(),
			newActualsMaster.getCreatedBy());
		Assert.assertEquals(existingActualsMaster.getAccrualProcessed(),
			newActualsMaster.getAccrualProcessed());
		Assert.assertEquals(existingActualsMaster.getParentcomDivmktBrandProdkey(),
			newActualsMaster.getParentcomDivmktBrandProdkey());
		Assert.assertEquals(Time.getShortTimestamp(
				existingActualsMaster.getCashPaidDate()),
			Time.getShortTimestamp(newActualsMaster.getCashPaidDate()));
		Assert.assertEquals(existingActualsMaster.getSalesAmount(),
			newActualsMaster.getSalesAmount());
		Assert.assertEquals(Time.getShortTimestamp(
				existingActualsMaster.getAccrualActualStartDate()),
			Time.getShortTimestamp(newActualsMaster.getAccrualActualStartDate()));
		Assert.assertEquals(existingActualsMaster.getSettlementNo(),
			newActualsMaster.getSettlementNo());
		Assert.assertEquals(existingActualsMaster.getPrice(),
			newActualsMaster.getPrice());
		Assert.assertEquals(Time.getShortTimestamp(
				existingActualsMaster.getUploadDate()),
			Time.getShortTimestamp(newActualsMaster.getUploadDate()));
		Assert.assertEquals(existingActualsMaster.getClaimIndicator(),
			newActualsMaster.getClaimIndicator());
		Assert.assertEquals(existingActualsMaster.getItemId(),
			newActualsMaster.getItemId());
		Assert.assertEquals(existingActualsMaster.getPriceAdjustmentName(),
			newActualsMaster.getPriceAdjustmentName());
		Assert.assertEquals(existingActualsMaster.getContractId(),
			newActualsMaster.getContractId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingActualsMaster.getModifiedDate()),
			Time.getShortTimestamp(newActualsMaster.getModifiedDate()));
		Assert.assertEquals(existingActualsMaster.getActualId(),
			newActualsMaster.getActualId());
		Assert.assertEquals(existingActualsMaster.getProvisionId(),
			newActualsMaster.getProvisionId());
		Assert.assertEquals(existingActualsMaster.getSentOut(),
			newActualsMaster.getSentOut());
		Assert.assertEquals(existingActualsMaster.getRecordLockStatus(),
			newActualsMaster.getRecordLockStatus());
		Assert.assertEquals(existingActualsMaster.getDivisionId(),
			newActualsMaster.getDivisionId());
		Assert.assertEquals(existingActualsMaster.getItemIdentifierCodeQualifier(),
			newActualsMaster.getItemIdentifierCodeQualifier());
		Assert.assertEquals(existingActualsMaster.getProgramStateCode(),
			newActualsMaster.getProgramStateCode());
		Assert.assertEquals(existingActualsMaster.getSource(),
			newActualsMaster.getSource());
		Assert.assertEquals(existingActualsMaster.getInvoiceLineNo(),
			newActualsMaster.getInvoiceLineNo());
		Assert.assertEquals(existingActualsMaster.getAccountNo(),
			newActualsMaster.getAccountNo());
		Assert.assertEquals(existingActualsMaster.getComDivMktBrandProdKey(),
			newActualsMaster.getComDivMktBrandProdKey());
		Assert.assertEquals(existingActualsMaster.getInboundStatus(),
			newActualsMaster.getInboundStatus());
	}

	@Test
	public void testCountByAccountId() throws Exception {
		_persistence.countByAccountId(StringPool.BLANK);

		_persistence.countByAccountId(StringPool.NULL);

		_persistence.countByAccountId((String)null);
	}

	@Test
	public void testCountByActualId() throws Exception {
		_persistence.countByActualId(StringPool.BLANK);

		_persistence.countByActualId(StringPool.NULL);

		_persistence.countByActualId((String)null);
	}

	@Test
	public void testCountByDivisionId() throws Exception {
		_persistence.countByDivisionId(StringPool.BLANK);

		_persistence.countByDivisionId(StringPool.NULL);

		_persistence.countByDivisionId((String)null);
	}

	@Test
	public void testCountByContractId() throws Exception {
		_persistence.countByContractId(StringPool.BLANK);

		_persistence.countByContractId(StringPool.NULL);

		_persistence.countByContractId((String)null);
	}

	@Test
	public void testCountByProvisionId() throws Exception {
		_persistence.countByProvisionId(StringPool.BLANK);

		_persistence.countByProvisionId(StringPool.NULL);

		_persistence.countByProvisionId((String)null);
	}

	@Test
	public void testCountByItemId() throws Exception {
		_persistence.countByItemId(StringPool.BLANK);

		_persistence.countByItemId(StringPool.NULL);

		_persistence.countByItemId((String)null);
	}

	@Test
	public void testCountByItemNo() throws Exception {
		_persistence.countByItemNo(StringPool.BLANK);

		_persistence.countByItemNo(StringPool.NULL);

		_persistence.countByItemNo((String)null);
	}

	@Test
	public void testCountByAccountNo() throws Exception {
		_persistence.countByAccountNo(StringPool.BLANK);

		_persistence.countByAccountNo(StringPool.NULL);

		_persistence.countByAccountNo((String)null);
	}

	@Test
	public void testCountByMarketId() throws Exception {
		_persistence.countByMarketId(StringPool.BLANK);

		_persistence.countByMarketId(StringPool.NULL);

		_persistence.countByMarketId((String)null);
	}

	@Test
	public void testCountByBrandId() throws Exception {
		_persistence.countByBrandId(StringPool.BLANK);

		_persistence.countByBrandId(StringPool.NULL);

		_persistence.countByBrandId((String)null);
	}

	@Test
	public void testCountByActualsUnique() throws Exception {
		_persistence.countByActualsUnique(StringPool.BLANK);

		_persistence.countByActualsUnique(StringPool.NULL);

		_persistence.countByActualsUnique((String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ActualsMaster newActualsMaster = addActualsMaster();

		ActualsMaster existingActualsMaster = _persistence.findByPrimaryKey(newActualsMaster.getPrimaryKey());

		Assert.assertEquals(existingActualsMaster, newActualsMaster);
	}

	@Test(expected = NoSuchActualsMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<ActualsMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("ACTUALS_MASTER",
			"quantityInclusion", true, "mandatedDiscountAmount", true,
			"itemNo", true, "analysisCode", true, "recordSequence", true,
			"modifiedBy", true, "settlementMethodNo", true, "quantity", true,
			"accountId", true, "createdDate", true, "provisionClaimIndicator",
			true, "dispensedDate", true, "isActive", true, "batchId", true,
			"accrualActualEndDate", true, "marketId", true, "brandId", true,
			"accountName", true, "amount", true, "actualsMasterSid", true,
			"acctIdentifierCodeQualifier", true, "organizationKey", true,
			"createdBy", true, "accrualProcessed", true,
			"parentcomDivmktBrandProdkey", true, "cashPaidDate", true,
			"salesAmount", true, "accrualActualStartDate", true,
			"settlementNo", true, "price", true, "uploadDate", true,
			"claimIndicator", true, "itemId", true, "priceAdjustmentName",
			true, "contractId", true, "modifiedDate", true, "actualId", true,
			"provisionId", true, "sentOut", true, "recordLockStatus", true,
			"divisionId", true, "itemIdentifierCodeQualifier", true,
			"programStateCode", true, "source", true, "invoiceLineNo", true,
			"accountNo", true, "comDivMktBrandProdKey", true, "inboundStatus",
			true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ActualsMaster newActualsMaster = addActualsMaster();

		ActualsMaster existingActualsMaster = _persistence.fetchByPrimaryKey(newActualsMaster.getPrimaryKey());

		Assert.assertEquals(existingActualsMaster, newActualsMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ActualsMaster missingActualsMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingActualsMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ActualsMaster newActualsMaster1 = addActualsMaster();
		ActualsMaster newActualsMaster2 = addActualsMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newActualsMaster1.getPrimaryKey());
		primaryKeys.add(newActualsMaster2.getPrimaryKey());

		Map<Serializable, ActualsMaster> actualsMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, actualsMasters.size());
		Assert.assertEquals(newActualsMaster1,
			actualsMasters.get(newActualsMaster1.getPrimaryKey()));
		Assert.assertEquals(newActualsMaster2,
			actualsMasters.get(newActualsMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ActualsMaster> actualsMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(actualsMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ActualsMaster newActualsMaster = addActualsMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newActualsMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ActualsMaster> actualsMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, actualsMasters.size());
		Assert.assertEquals(newActualsMaster,
			actualsMasters.get(newActualsMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ActualsMaster> actualsMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(actualsMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ActualsMaster newActualsMaster = addActualsMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newActualsMaster.getPrimaryKey());

		Map<Serializable, ActualsMaster> actualsMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, actualsMasters.size());
		Assert.assertEquals(newActualsMaster,
			actualsMasters.get(newActualsMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ActualsMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ActualsMaster>() {
				@Override
				public void performAction(ActualsMaster actualsMaster) {
					Assert.assertNotNull(actualsMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ActualsMaster newActualsMaster = addActualsMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ActualsMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("actualsMasterSid",
				newActualsMaster.getActualsMasterSid()));

		List<ActualsMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ActualsMaster existingActualsMaster = result.get(0);

		Assert.assertEquals(existingActualsMaster, newActualsMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ActualsMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("actualsMasterSid",
				RandomTestUtil.nextInt()));

		List<ActualsMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ActualsMaster newActualsMaster = addActualsMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ActualsMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"actualsMasterSid"));

		Object newActualsMasterSid = newActualsMaster.getActualsMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("actualsMasterSid",
				new Object[] { newActualsMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingActualsMasterSid = result.get(0);

		Assert.assertEquals(existingActualsMasterSid, newActualsMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ActualsMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"actualsMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("actualsMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ActualsMaster addActualsMaster() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ActualsMaster actualsMaster = _persistence.create(pk);

		actualsMaster.setQuantityInclusion(RandomTestUtil.randomString());

		actualsMaster.setMandatedDiscountAmount(RandomTestUtil.randomString());

		actualsMaster.setItemNo(RandomTestUtil.randomString());

		actualsMaster.setAnalysisCode(RandomTestUtil.randomString());

		actualsMaster.setRecordSequence(RandomTestUtil.randomString());

		actualsMaster.setModifiedBy(RandomTestUtil.nextInt());

		actualsMaster.setSettlementMethodNo(RandomTestUtil.randomString());

		actualsMaster.setQuantity(RandomTestUtil.randomString());

		actualsMaster.setAccountId(RandomTestUtil.randomString());

		actualsMaster.setCreatedDate(RandomTestUtil.nextDate());

		actualsMaster.setProvisionClaimIndicator(RandomTestUtil.randomString());

		actualsMaster.setDispensedDate(RandomTestUtil.nextDate());

		actualsMaster.setIsActive(RandomTestUtil.randomString());

		actualsMaster.setBatchId(RandomTestUtil.randomString());

		actualsMaster.setAccrualActualEndDate(RandomTestUtil.nextDate());

		actualsMaster.setMarketId(RandomTestUtil.randomString());

		actualsMaster.setBrandId(RandomTestUtil.randomString());

		actualsMaster.setAccountName(RandomTestUtil.randomString());

		actualsMaster.setAmount(RandomTestUtil.randomString());

		actualsMaster.setAcctIdentifierCodeQualifier(RandomTestUtil.randomString());

		actualsMaster.setOrganizationKey(RandomTestUtil.randomString());

		actualsMaster.setCreatedBy(RandomTestUtil.nextInt());

		actualsMaster.setAccrualProcessed(RandomTestUtil.randomString());

		actualsMaster.setParentcomDivmktBrandProdkey(RandomTestUtil.randomString());

		actualsMaster.setCashPaidDate(RandomTestUtil.nextDate());

		actualsMaster.setSalesAmount(RandomTestUtil.randomString());

		actualsMaster.setAccrualActualStartDate(RandomTestUtil.nextDate());

		actualsMaster.setSettlementNo(RandomTestUtil.randomString());

		actualsMaster.setPrice(RandomTestUtil.randomString());

		actualsMaster.setUploadDate(RandomTestUtil.nextDate());

		actualsMaster.setClaimIndicator(RandomTestUtil.randomString());

		actualsMaster.setItemId(RandomTestUtil.randomString());

		actualsMaster.setPriceAdjustmentName(RandomTestUtil.randomString());

		actualsMaster.setContractId(RandomTestUtil.randomString());

		actualsMaster.setModifiedDate(RandomTestUtil.nextDate());

		actualsMaster.setActualId(RandomTestUtil.randomString());

		actualsMaster.setProvisionId(RandomTestUtil.randomString());

		actualsMaster.setSentOut(RandomTestUtil.randomString());

		actualsMaster.setRecordLockStatus(RandomTestUtil.randomBoolean());

		actualsMaster.setDivisionId(RandomTestUtil.randomString());

		actualsMaster.setItemIdentifierCodeQualifier(RandomTestUtil.randomString());

		actualsMaster.setProgramStateCode(RandomTestUtil.randomString());

		actualsMaster.setSource(RandomTestUtil.randomString());

		actualsMaster.setInvoiceLineNo(RandomTestUtil.randomString());

		actualsMaster.setAccountNo(RandomTestUtil.randomString());

		actualsMaster.setComDivMktBrandProdKey(RandomTestUtil.randomString());

		actualsMaster.setInboundStatus(RandomTestUtil.randomString());

		_actualsMasters.add(_persistence.update(actualsMaster));

		return actualsMaster;
	}

	private List<ActualsMaster> _actualsMasters = new ArrayList<ActualsMaster>();
	private ActualsMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}