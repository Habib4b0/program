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

import com.stpl.app.exception.NoSuchIvldSalesMasterException;
import com.stpl.app.model.IvldSalesMaster;
import com.stpl.app.service.IvldSalesMasterLocalServiceUtil;
import com.stpl.app.service.persistence.IvldSalesMasterPersistence;
import com.stpl.app.service.persistence.IvldSalesMasterUtil;

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
public class IvldSalesMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = IvldSalesMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<IvldSalesMaster> iterator = _ivldSalesMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldSalesMaster ivldSalesMaster = _persistence.create(pk);

		Assert.assertNotNull(ivldSalesMaster);

		Assert.assertEquals(ivldSalesMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		IvldSalesMaster newIvldSalesMaster = addIvldSalesMaster();

		_persistence.remove(newIvldSalesMaster);

		IvldSalesMaster existingIvldSalesMaster = _persistence.fetchByPrimaryKey(newIvldSalesMaster.getPrimaryKey());

		Assert.assertNull(existingIvldSalesMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addIvldSalesMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldSalesMaster newIvldSalesMaster = _persistence.create(pk);

		newIvldSalesMaster.setAccountId(RandomTestUtil.randomString());

		newIvldSalesMaster.setSalesIntfid(RandomTestUtil.randomString());

		newIvldSalesMaster.setModifiedDate(RandomTestUtil.nextDate());

		newIvldSalesMaster.setOrganizationKey(RandomTestUtil.randomString());

		newIvldSalesMaster.setDivisionId(RandomTestUtil.randomString());

		newIvldSalesMaster.setSource(RandomTestUtil.randomString());

		newIvldSalesMaster.setAddChgDelIndicator(RandomTestUtil.randomString());

		newIvldSalesMaster.setAnalysisCode(RandomTestUtil.randomString());

		newIvldSalesMaster.setDocType(RandomTestUtil.randomString());

		newIvldSalesMaster.setModifiedBy(RandomTestUtil.randomString());

		newIvldSalesMaster.setLotNo(RandomTestUtil.randomString());

		newIvldSalesMaster.setQuantity(RandomTestUtil.randomString());

		newIvldSalesMaster.setInvoiceLineNumber(RandomTestUtil.randomString());

		newIvldSalesMaster.setIdentifierCodeQualifier(RandomTestUtil.randomString());

		newIvldSalesMaster.setAccountCodeQualifier(RandomTestUtil.randomString());

		newIvldSalesMaster.setParentItemId(RandomTestUtil.randomString());

		newIvldSalesMaster.setAccountNo(RandomTestUtil.randomString());

		newIvldSalesMaster.setReasonForFailure(RandomTestUtil.randomString());

		newIvldSalesMaster.setAccountName(RandomTestUtil.randomString());

		newIvldSalesMaster.setProvisionId(RandomTestUtil.randomString());

		newIvldSalesMaster.setAmount(RandomTestUtil.randomString());

		newIvldSalesMaster.setMarketId(RandomTestUtil.randomString());

		newIvldSalesMaster.setIsActive(RandomTestUtil.randomString());

		newIvldSalesMaster.setWholesaleOwnerId(RandomTestUtil.randomString());

		newIvldSalesMaster.setPriceAdjustmentName(RandomTestUtil.randomString());

		newIvldSalesMaster.setSalesId(RandomTestUtil.randomString());

		newIvldSalesMaster.setErrorField(RandomTestUtil.randomString());

		newIvldSalesMaster.setRecordSequence(RandomTestUtil.randomString());

		newIvldSalesMaster.setPrice(RandomTestUtil.randomString());

		newIvldSalesMaster.setCustomerSubtype(RandomTestUtil.randomString());

		newIvldSalesMaster.setParentItemNo(RandomTestUtil.randomString());

		newIvldSalesMaster.setItemId(RandomTestUtil.randomString());

		newIvldSalesMaster.setOrderReceivedDate(RandomTestUtil.randomString());

		newIvldSalesMaster.setOrderNumber(RandomTestUtil.randomString());

		newIvldSalesMaster.setAccountType(RandomTestUtil.randomString());

		newIvldSalesMaster.setUploadDate(RandomTestUtil.randomString());

		newIvldSalesMaster.setCreatedBy(RandomTestUtil.randomString());

		newIvldSalesMaster.setCreatedDate(RandomTestUtil.nextDate());

		newIvldSalesMaster.setErrorCode(RandomTestUtil.randomString());

		newIvldSalesMaster.setItemUom(RandomTestUtil.randomString());

		newIvldSalesMaster.setIntfInsertedDate(RandomTestUtil.nextDate());

		newIvldSalesMaster.setInvoiceNumber(RandomTestUtil.randomString());

		newIvldSalesMaster.setOrderSubtype(RandomTestUtil.randomString());

		newIvldSalesMaster.setItemNo(RandomTestUtil.randomString());

		newIvldSalesMaster.setReprocessedFlag(RandomTestUtil.randomString());

		newIvldSalesMaster.setContractId(RandomTestUtil.randomString());

		newIvldSalesMaster.setCustomerCompanyCode(RandomTestUtil.randomString());

		newIvldSalesMaster.setOrderType(RandomTestUtil.randomString());

		newIvldSalesMaster.setCompanyStringId(RandomTestUtil.randomString());

		newIvldSalesMaster.setBrandId(RandomTestUtil.randomString());

		newIvldSalesMaster.setInvoiceDate(RandomTestUtil.randomString());

		newIvldSalesMaster.setBatchId(RandomTestUtil.randomString());

		newIvldSalesMaster.setContractNo(RandomTestUtil.randomString());

		newIvldSalesMaster.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldSalesMasters.add(_persistence.update(newIvldSalesMaster));

		IvldSalesMaster existingIvldSalesMaster = _persistence.findByPrimaryKey(newIvldSalesMaster.getPrimaryKey());

		Assert.assertEquals(existingIvldSalesMaster.getAccountId(),
			newIvldSalesMaster.getAccountId());
		Assert.assertEquals(existingIvldSalesMaster.getSalesIntfid(),
			newIvldSalesMaster.getSalesIntfid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldSalesMaster.getModifiedDate()),
			Time.getShortTimestamp(newIvldSalesMaster.getModifiedDate()));
		Assert.assertEquals(existingIvldSalesMaster.getOrganizationKey(),
			newIvldSalesMaster.getOrganizationKey());
		Assert.assertEquals(existingIvldSalesMaster.getDivisionId(),
			newIvldSalesMaster.getDivisionId());
		Assert.assertEquals(existingIvldSalesMaster.getSource(),
			newIvldSalesMaster.getSource());
		Assert.assertEquals(existingIvldSalesMaster.getAddChgDelIndicator(),
			newIvldSalesMaster.getAddChgDelIndicator());
		Assert.assertEquals(existingIvldSalesMaster.getAnalysisCode(),
			newIvldSalesMaster.getAnalysisCode());
		Assert.assertEquals(existingIvldSalesMaster.getIvldSalesMasterSid(),
			newIvldSalesMaster.getIvldSalesMasterSid());
		Assert.assertEquals(existingIvldSalesMaster.getDocType(),
			newIvldSalesMaster.getDocType());
		Assert.assertEquals(existingIvldSalesMaster.getModifiedBy(),
			newIvldSalesMaster.getModifiedBy());
		Assert.assertEquals(existingIvldSalesMaster.getLotNo(),
			newIvldSalesMaster.getLotNo());
		Assert.assertEquals(existingIvldSalesMaster.getQuantity(),
			newIvldSalesMaster.getQuantity());
		Assert.assertEquals(existingIvldSalesMaster.getInvoiceLineNumber(),
			newIvldSalesMaster.getInvoiceLineNumber());
		Assert.assertEquals(existingIvldSalesMaster.getIdentifierCodeQualifier(),
			newIvldSalesMaster.getIdentifierCodeQualifier());
		Assert.assertEquals(existingIvldSalesMaster.getAccountCodeQualifier(),
			newIvldSalesMaster.getAccountCodeQualifier());
		Assert.assertEquals(existingIvldSalesMaster.getParentItemId(),
			newIvldSalesMaster.getParentItemId());
		Assert.assertEquals(existingIvldSalesMaster.getAccountNo(),
			newIvldSalesMaster.getAccountNo());
		Assert.assertEquals(existingIvldSalesMaster.getReasonForFailure(),
			newIvldSalesMaster.getReasonForFailure());
		Assert.assertEquals(existingIvldSalesMaster.getAccountName(),
			newIvldSalesMaster.getAccountName());
		Assert.assertEquals(existingIvldSalesMaster.getProvisionId(),
			newIvldSalesMaster.getProvisionId());
		Assert.assertEquals(existingIvldSalesMaster.getAmount(),
			newIvldSalesMaster.getAmount());
		Assert.assertEquals(existingIvldSalesMaster.getMarketId(),
			newIvldSalesMaster.getMarketId());
		Assert.assertEquals(existingIvldSalesMaster.getIsActive(),
			newIvldSalesMaster.getIsActive());
		Assert.assertEquals(existingIvldSalesMaster.getWholesaleOwnerId(),
			newIvldSalesMaster.getWholesaleOwnerId());
		Assert.assertEquals(existingIvldSalesMaster.getPriceAdjustmentName(),
			newIvldSalesMaster.getPriceAdjustmentName());
		Assert.assertEquals(existingIvldSalesMaster.getSalesId(),
			newIvldSalesMaster.getSalesId());
		Assert.assertEquals(existingIvldSalesMaster.getErrorField(),
			newIvldSalesMaster.getErrorField());
		Assert.assertEquals(existingIvldSalesMaster.getRecordSequence(),
			newIvldSalesMaster.getRecordSequence());
		Assert.assertEquals(existingIvldSalesMaster.getPrice(),
			newIvldSalesMaster.getPrice());
		Assert.assertEquals(existingIvldSalesMaster.getCustomerSubtype(),
			newIvldSalesMaster.getCustomerSubtype());
		Assert.assertEquals(existingIvldSalesMaster.getParentItemNo(),
			newIvldSalesMaster.getParentItemNo());
		Assert.assertEquals(existingIvldSalesMaster.getItemId(),
			newIvldSalesMaster.getItemId());
		Assert.assertEquals(existingIvldSalesMaster.getOrderReceivedDate(),
			newIvldSalesMaster.getOrderReceivedDate());
		Assert.assertEquals(existingIvldSalesMaster.getOrderNumber(),
			newIvldSalesMaster.getOrderNumber());
		Assert.assertEquals(existingIvldSalesMaster.getAccountType(),
			newIvldSalesMaster.getAccountType());
		Assert.assertEquals(existingIvldSalesMaster.getUploadDate(),
			newIvldSalesMaster.getUploadDate());
		Assert.assertEquals(existingIvldSalesMaster.getCreatedBy(),
			newIvldSalesMaster.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldSalesMaster.getCreatedDate()),
			Time.getShortTimestamp(newIvldSalesMaster.getCreatedDate()));
		Assert.assertEquals(existingIvldSalesMaster.getErrorCode(),
			newIvldSalesMaster.getErrorCode());
		Assert.assertEquals(existingIvldSalesMaster.getItemUom(),
			newIvldSalesMaster.getItemUom());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldSalesMaster.getIntfInsertedDate()),
			Time.getShortTimestamp(newIvldSalesMaster.getIntfInsertedDate()));
		Assert.assertEquals(existingIvldSalesMaster.getInvoiceNumber(),
			newIvldSalesMaster.getInvoiceNumber());
		Assert.assertEquals(existingIvldSalesMaster.getOrderSubtype(),
			newIvldSalesMaster.getOrderSubtype());
		Assert.assertEquals(existingIvldSalesMaster.getItemNo(),
			newIvldSalesMaster.getItemNo());
		Assert.assertEquals(existingIvldSalesMaster.getReprocessedFlag(),
			newIvldSalesMaster.getReprocessedFlag());
		Assert.assertEquals(existingIvldSalesMaster.getContractId(),
			newIvldSalesMaster.getContractId());
		Assert.assertEquals(existingIvldSalesMaster.getCustomerCompanyCode(),
			newIvldSalesMaster.getCustomerCompanyCode());
		Assert.assertEquals(existingIvldSalesMaster.getOrderType(),
			newIvldSalesMaster.getOrderType());
		Assert.assertEquals(existingIvldSalesMaster.getCompanyStringId(),
			newIvldSalesMaster.getCompanyStringId());
		Assert.assertEquals(existingIvldSalesMaster.getBrandId(),
			newIvldSalesMaster.getBrandId());
		Assert.assertEquals(existingIvldSalesMaster.getInvoiceDate(),
			newIvldSalesMaster.getInvoiceDate());
		Assert.assertEquals(existingIvldSalesMaster.getBatchId(),
			newIvldSalesMaster.getBatchId());
		Assert.assertEquals(existingIvldSalesMaster.getContractNo(),
			newIvldSalesMaster.getContractNo());
		Assert.assertEquals(existingIvldSalesMaster.getCheckRecord(),
			newIvldSalesMaster.getCheckRecord());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		IvldSalesMaster newIvldSalesMaster = addIvldSalesMaster();

		IvldSalesMaster existingIvldSalesMaster = _persistence.findByPrimaryKey(newIvldSalesMaster.getPrimaryKey());

		Assert.assertEquals(existingIvldSalesMaster, newIvldSalesMaster);
	}

	@Test(expected = NoSuchIvldSalesMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<IvldSalesMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("IVLD_SALES_MASTER",
			"accountId", true, "salesIntfid", true, "modifiedDate", true,
			"organizationKey", true, "divisionId", true, "source", true,
			"addChgDelIndicator", true, "analysisCode", true,
			"ivldSalesMasterSid", true, "docType", true, "modifiedBy", true,
			"lotNo", true, "quantity", true, "invoiceLineNumber", true,
			"identifierCodeQualifier", true, "accountCodeQualifier", true,
			"parentItemId", true, "accountNo", true, "reasonForFailure", true,
			"accountName", true, "provisionId", true, "amount", true,
			"marketId", true, "isActive", true, "wholesaleOwnerId", true,
			"priceAdjustmentName", true, "salesId", true, "errorField", true,
			"recordSequence", true, "price", true, "customerSubtype", true,
			"parentItemNo", true, "itemId", true, "orderReceivedDate", true,
			"orderNumber", true, "accountType", true, "uploadDate", true,
			"createdBy", true, "createdDate", true, "errorCode", true,
			"itemUom", true, "intfInsertedDate", true, "invoiceNumber", true,
			"orderSubtype", true, "itemNo", true, "reprocessedFlag", true,
			"contractId", true, "customerCompanyCode", true, "orderType", true,
			"companyStringId", true, "brandId", true, "invoiceDate", true,
			"batchId", true, "contractNo", true, "checkRecord", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		IvldSalesMaster newIvldSalesMaster = addIvldSalesMaster();

		IvldSalesMaster existingIvldSalesMaster = _persistence.fetchByPrimaryKey(newIvldSalesMaster.getPrimaryKey());

		Assert.assertEquals(existingIvldSalesMaster, newIvldSalesMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldSalesMaster missingIvldSalesMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingIvldSalesMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		IvldSalesMaster newIvldSalesMaster1 = addIvldSalesMaster();
		IvldSalesMaster newIvldSalesMaster2 = addIvldSalesMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldSalesMaster1.getPrimaryKey());
		primaryKeys.add(newIvldSalesMaster2.getPrimaryKey());

		Map<Serializable, IvldSalesMaster> ivldSalesMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ivldSalesMasters.size());
		Assert.assertEquals(newIvldSalesMaster1,
			ivldSalesMasters.get(newIvldSalesMaster1.getPrimaryKey()));
		Assert.assertEquals(newIvldSalesMaster2,
			ivldSalesMasters.get(newIvldSalesMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, IvldSalesMaster> ivldSalesMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldSalesMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		IvldSalesMaster newIvldSalesMaster = addIvldSalesMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldSalesMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, IvldSalesMaster> ivldSalesMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldSalesMasters.size());
		Assert.assertEquals(newIvldSalesMaster,
			ivldSalesMasters.get(newIvldSalesMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, IvldSalesMaster> ivldSalesMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldSalesMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		IvldSalesMaster newIvldSalesMaster = addIvldSalesMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldSalesMaster.getPrimaryKey());

		Map<Serializable, IvldSalesMaster> ivldSalesMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldSalesMasters.size());
		Assert.assertEquals(newIvldSalesMaster,
			ivldSalesMasters.get(newIvldSalesMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = IvldSalesMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<IvldSalesMaster>() {
				@Override
				public void performAction(IvldSalesMaster ivldSalesMaster) {
					Assert.assertNotNull(ivldSalesMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		IvldSalesMaster newIvldSalesMaster = addIvldSalesMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldSalesMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldSalesMasterSid",
				newIvldSalesMaster.getIvldSalesMasterSid()));

		List<IvldSalesMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		IvldSalesMaster existingIvldSalesMaster = result.get(0);

		Assert.assertEquals(existingIvldSalesMaster, newIvldSalesMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldSalesMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldSalesMasterSid",
				RandomTestUtil.nextInt()));

		List<IvldSalesMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		IvldSalesMaster newIvldSalesMaster = addIvldSalesMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldSalesMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldSalesMasterSid"));

		Object newIvldSalesMasterSid = newIvldSalesMaster.getIvldSalesMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldSalesMasterSid",
				new Object[] { newIvldSalesMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingIvldSalesMasterSid = result.get(0);

		Assert.assertEquals(existingIvldSalesMasterSid, newIvldSalesMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldSalesMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldSalesMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldSalesMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected IvldSalesMaster addIvldSalesMaster() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldSalesMaster ivldSalesMaster = _persistence.create(pk);

		ivldSalesMaster.setAccountId(RandomTestUtil.randomString());

		ivldSalesMaster.setSalesIntfid(RandomTestUtil.randomString());

		ivldSalesMaster.setModifiedDate(RandomTestUtil.nextDate());

		ivldSalesMaster.setOrganizationKey(RandomTestUtil.randomString());

		ivldSalesMaster.setDivisionId(RandomTestUtil.randomString());

		ivldSalesMaster.setSource(RandomTestUtil.randomString());

		ivldSalesMaster.setAddChgDelIndicator(RandomTestUtil.randomString());

		ivldSalesMaster.setAnalysisCode(RandomTestUtil.randomString());

		ivldSalesMaster.setDocType(RandomTestUtil.randomString());

		ivldSalesMaster.setModifiedBy(RandomTestUtil.randomString());

		ivldSalesMaster.setLotNo(RandomTestUtil.randomString());

		ivldSalesMaster.setQuantity(RandomTestUtil.randomString());

		ivldSalesMaster.setInvoiceLineNumber(RandomTestUtil.randomString());

		ivldSalesMaster.setIdentifierCodeQualifier(RandomTestUtil.randomString());

		ivldSalesMaster.setAccountCodeQualifier(RandomTestUtil.randomString());

		ivldSalesMaster.setParentItemId(RandomTestUtil.randomString());

		ivldSalesMaster.setAccountNo(RandomTestUtil.randomString());

		ivldSalesMaster.setReasonForFailure(RandomTestUtil.randomString());

		ivldSalesMaster.setAccountName(RandomTestUtil.randomString());

		ivldSalesMaster.setProvisionId(RandomTestUtil.randomString());

		ivldSalesMaster.setAmount(RandomTestUtil.randomString());

		ivldSalesMaster.setMarketId(RandomTestUtil.randomString());

		ivldSalesMaster.setIsActive(RandomTestUtil.randomString());

		ivldSalesMaster.setWholesaleOwnerId(RandomTestUtil.randomString());

		ivldSalesMaster.setPriceAdjustmentName(RandomTestUtil.randomString());

		ivldSalesMaster.setSalesId(RandomTestUtil.randomString());

		ivldSalesMaster.setErrorField(RandomTestUtil.randomString());

		ivldSalesMaster.setRecordSequence(RandomTestUtil.randomString());

		ivldSalesMaster.setPrice(RandomTestUtil.randomString());

		ivldSalesMaster.setCustomerSubtype(RandomTestUtil.randomString());

		ivldSalesMaster.setParentItemNo(RandomTestUtil.randomString());

		ivldSalesMaster.setItemId(RandomTestUtil.randomString());

		ivldSalesMaster.setOrderReceivedDate(RandomTestUtil.randomString());

		ivldSalesMaster.setOrderNumber(RandomTestUtil.randomString());

		ivldSalesMaster.setAccountType(RandomTestUtil.randomString());

		ivldSalesMaster.setUploadDate(RandomTestUtil.randomString());

		ivldSalesMaster.setCreatedBy(RandomTestUtil.randomString());

		ivldSalesMaster.setCreatedDate(RandomTestUtil.nextDate());

		ivldSalesMaster.setErrorCode(RandomTestUtil.randomString());

		ivldSalesMaster.setItemUom(RandomTestUtil.randomString());

		ivldSalesMaster.setIntfInsertedDate(RandomTestUtil.nextDate());

		ivldSalesMaster.setInvoiceNumber(RandomTestUtil.randomString());

		ivldSalesMaster.setOrderSubtype(RandomTestUtil.randomString());

		ivldSalesMaster.setItemNo(RandomTestUtil.randomString());

		ivldSalesMaster.setReprocessedFlag(RandomTestUtil.randomString());

		ivldSalesMaster.setContractId(RandomTestUtil.randomString());

		ivldSalesMaster.setCustomerCompanyCode(RandomTestUtil.randomString());

		ivldSalesMaster.setOrderType(RandomTestUtil.randomString());

		ivldSalesMaster.setCompanyStringId(RandomTestUtil.randomString());

		ivldSalesMaster.setBrandId(RandomTestUtil.randomString());

		ivldSalesMaster.setInvoiceDate(RandomTestUtil.randomString());

		ivldSalesMaster.setBatchId(RandomTestUtil.randomString());

		ivldSalesMaster.setContractNo(RandomTestUtil.randomString());

		ivldSalesMaster.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldSalesMasters.add(_persistence.update(ivldSalesMaster));

		return ivldSalesMaster;
	}

	private List<IvldSalesMaster> _ivldSalesMasters = new ArrayList<IvldSalesMaster>();
	private IvldSalesMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}