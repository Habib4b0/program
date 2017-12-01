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

import com.stpl.app.exception.NoSuchSalesMasterException;
import com.stpl.app.model.SalesMaster;
import com.stpl.app.service.SalesMasterLocalServiceUtil;
import com.stpl.app.service.persistence.SalesMasterPersistence;
import com.stpl.app.service.persistence.SalesMasterUtil;

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
public class SalesMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = SalesMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<SalesMaster> iterator = _salesMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		SalesMaster salesMaster = _persistence.create(pk);

		Assert.assertNotNull(salesMaster);

		Assert.assertEquals(salesMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		SalesMaster newSalesMaster = addSalesMaster();

		_persistence.remove(newSalesMaster);

		SalesMaster existingSalesMaster = _persistence.fetchByPrimaryKey(newSalesMaster.getPrimaryKey());

		Assert.assertNull(existingSalesMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addSalesMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		SalesMaster newSalesMaster = _persistence.create(pk);

		newSalesMaster.setItemNo(RandomTestUtil.randomString());

		newSalesMaster.setRecordSequence(RandomTestUtil.nextInt());

		newSalesMaster.setQuantity(RandomTestUtil.nextDouble());

		newSalesMaster.setAccountId(RandomTestUtil.randomString());

		newSalesMaster.setCreatedDate(RandomTestUtil.nextDate());

		newSalesMaster.setIdentifierCodeQualifier(RandomTestUtil.randomString());

		newSalesMaster.setIsActive(RandomTestUtil.randomString());

		newSalesMaster.setMarketId(RandomTestUtil.randomString());

		newSalesMaster.setInvoiceDate(RandomTestUtil.nextDate());

		newSalesMaster.setAccountName(RandomTestUtil.randomString());

		newSalesMaster.setDocType(RandomTestUtil.randomString());

		newSalesMaster.setOrderReceivedDate(RandomTestUtil.nextDate());

		newSalesMaster.setAmount(RandomTestUtil.nextDouble());

		newSalesMaster.setOrderSubtype(RandomTestUtil.randomString());

		newSalesMaster.setCreatedBy(RandomTestUtil.nextInt());

		newSalesMaster.setPrice(RandomTestUtil.nextDouble());

		newSalesMaster.setUploadDate(RandomTestUtil.nextDate());

		newSalesMaster.setItemId(RandomTestUtil.randomString());

		newSalesMaster.setPriceAdjustmentName(RandomTestUtil.randomString());

		newSalesMaster.setItemCodeQualifier(RandomTestUtil.randomString());

		newSalesMaster.setContractId(RandomTestUtil.randomString());

		newSalesMaster.setItemUom(RandomTestUtil.randomString());

		newSalesMaster.setModifiedDate(RandomTestUtil.nextDate());

		newSalesMaster.setCustomerSubtype(RandomTestUtil.randomString());

		newSalesMaster.setProvisionId(RandomTestUtil.nextInt());

		newSalesMaster.setWholesaleOwnerId(RandomTestUtil.randomString());

		newSalesMaster.setSource(RandomTestUtil.randomString());

		newSalesMaster.setAccountNo(RandomTestUtil.randomString());

		newSalesMaster.setLotNo(RandomTestUtil.randomString());

		newSalesMaster.setParentItemId(RandomTestUtil.randomString());

		newSalesMaster.setCustomerCompanyCode(RandomTestUtil.randomString());

		newSalesMaster.setAnalysisCode(RandomTestUtil.randomString());

		newSalesMaster.setAccountType(RandomTestUtil.randomString());

		newSalesMaster.setModifiedBy(RandomTestUtil.nextInt());

		newSalesMaster.setContractNo(RandomTestUtil.randomString());

		newSalesMaster.setBatchId(RandomTestUtil.randomString());

		newSalesMaster.setBrandId(RandomTestUtil.randomString());

		newSalesMaster.setSalesId(RandomTestUtil.randomString());

		newSalesMaster.setCompanyStringId(RandomTestUtil.randomString());

		newSalesMaster.setOrganizationKey(RandomTestUtil.randomString());

		newSalesMaster.setItemParentNo(RandomTestUtil.randomString());

		newSalesMaster.setInvoiceNumber(RandomTestUtil.randomString());

		newSalesMaster.setOrderType(RandomTestUtil.randomString());

		newSalesMaster.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newSalesMaster.setDivisionId(RandomTestUtil.randomString());

		newSalesMaster.setInvoiceLineNumber(RandomTestUtil.randomString());

		newSalesMaster.setOrderNumber(RandomTestUtil.randomString());

		newSalesMaster.setInboundStatus(RandomTestUtil.randomString());

		_salesMasters.add(_persistence.update(newSalesMaster));

		SalesMaster existingSalesMaster = _persistence.findByPrimaryKey(newSalesMaster.getPrimaryKey());

		Assert.assertEquals(existingSalesMaster.getItemNo(),
			newSalesMaster.getItemNo());
		Assert.assertEquals(existingSalesMaster.getRecordSequence(),
			newSalesMaster.getRecordSequence());
		AssertUtils.assertEquals(existingSalesMaster.getQuantity(),
			newSalesMaster.getQuantity());
		Assert.assertEquals(existingSalesMaster.getAccountId(),
			newSalesMaster.getAccountId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingSalesMaster.getCreatedDate()),
			Time.getShortTimestamp(newSalesMaster.getCreatedDate()));
		Assert.assertEquals(existingSalesMaster.getIdentifierCodeQualifier(),
			newSalesMaster.getIdentifierCodeQualifier());
		Assert.assertEquals(existingSalesMaster.getIsActive(),
			newSalesMaster.getIsActive());
		Assert.assertEquals(existingSalesMaster.getMarketId(),
			newSalesMaster.getMarketId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingSalesMaster.getInvoiceDate()),
			Time.getShortTimestamp(newSalesMaster.getInvoiceDate()));
		Assert.assertEquals(existingSalesMaster.getAccountName(),
			newSalesMaster.getAccountName());
		Assert.assertEquals(existingSalesMaster.getDocType(),
			newSalesMaster.getDocType());
		Assert.assertEquals(Time.getShortTimestamp(
				existingSalesMaster.getOrderReceivedDate()),
			Time.getShortTimestamp(newSalesMaster.getOrderReceivedDate()));
		AssertUtils.assertEquals(existingSalesMaster.getAmount(),
			newSalesMaster.getAmount());
		Assert.assertEquals(existingSalesMaster.getSalesMasterSid(),
			newSalesMaster.getSalesMasterSid());
		Assert.assertEquals(existingSalesMaster.getOrderSubtype(),
			newSalesMaster.getOrderSubtype());
		Assert.assertEquals(existingSalesMaster.getCreatedBy(),
			newSalesMaster.getCreatedBy());
		AssertUtils.assertEquals(existingSalesMaster.getPrice(),
			newSalesMaster.getPrice());
		Assert.assertEquals(Time.getShortTimestamp(
				existingSalesMaster.getUploadDate()),
			Time.getShortTimestamp(newSalesMaster.getUploadDate()));
		Assert.assertEquals(existingSalesMaster.getItemId(),
			newSalesMaster.getItemId());
		Assert.assertEquals(existingSalesMaster.getPriceAdjustmentName(),
			newSalesMaster.getPriceAdjustmentName());
		Assert.assertEquals(existingSalesMaster.getItemCodeQualifier(),
			newSalesMaster.getItemCodeQualifier());
		Assert.assertEquals(existingSalesMaster.getContractId(),
			newSalesMaster.getContractId());
		Assert.assertEquals(existingSalesMaster.getItemUom(),
			newSalesMaster.getItemUom());
		Assert.assertEquals(Time.getShortTimestamp(
				existingSalesMaster.getModifiedDate()),
			Time.getShortTimestamp(newSalesMaster.getModifiedDate()));
		Assert.assertEquals(existingSalesMaster.getCustomerSubtype(),
			newSalesMaster.getCustomerSubtype());
		Assert.assertEquals(existingSalesMaster.getProvisionId(),
			newSalesMaster.getProvisionId());
		Assert.assertEquals(existingSalesMaster.getWholesaleOwnerId(),
			newSalesMaster.getWholesaleOwnerId());
		Assert.assertEquals(existingSalesMaster.getSource(),
			newSalesMaster.getSource());
		Assert.assertEquals(existingSalesMaster.getAccountNo(),
			newSalesMaster.getAccountNo());
		Assert.assertEquals(existingSalesMaster.getLotNo(),
			newSalesMaster.getLotNo());
		Assert.assertEquals(existingSalesMaster.getParentItemId(),
			newSalesMaster.getParentItemId());
		Assert.assertEquals(existingSalesMaster.getCustomerCompanyCode(),
			newSalesMaster.getCustomerCompanyCode());
		Assert.assertEquals(existingSalesMaster.getAnalysisCode(),
			newSalesMaster.getAnalysisCode());
		Assert.assertEquals(existingSalesMaster.getAccountType(),
			newSalesMaster.getAccountType());
		Assert.assertEquals(existingSalesMaster.getModifiedBy(),
			newSalesMaster.getModifiedBy());
		Assert.assertEquals(existingSalesMaster.getContractNo(),
			newSalesMaster.getContractNo());
		Assert.assertEquals(existingSalesMaster.getBatchId(),
			newSalesMaster.getBatchId());
		Assert.assertEquals(existingSalesMaster.getBrandId(),
			newSalesMaster.getBrandId());
		Assert.assertEquals(existingSalesMaster.getSalesId(),
			newSalesMaster.getSalesId());
		Assert.assertEquals(existingSalesMaster.getCompanyStringId(),
			newSalesMaster.getCompanyStringId());
		Assert.assertEquals(existingSalesMaster.getOrganizationKey(),
			newSalesMaster.getOrganizationKey());
		Assert.assertEquals(existingSalesMaster.getItemParentNo(),
			newSalesMaster.getItemParentNo());
		Assert.assertEquals(existingSalesMaster.getInvoiceNumber(),
			newSalesMaster.getInvoiceNumber());
		Assert.assertEquals(existingSalesMaster.getOrderType(),
			newSalesMaster.getOrderType());
		Assert.assertEquals(existingSalesMaster.getRecordLockStatus(),
			newSalesMaster.getRecordLockStatus());
		Assert.assertEquals(existingSalesMaster.getDivisionId(),
			newSalesMaster.getDivisionId());
		Assert.assertEquals(existingSalesMaster.getInvoiceLineNumber(),
			newSalesMaster.getInvoiceLineNumber());
		Assert.assertEquals(existingSalesMaster.getOrderNumber(),
			newSalesMaster.getOrderNumber());
		Assert.assertEquals(existingSalesMaster.getInboundStatus(),
			newSalesMaster.getInboundStatus());
	}

	@Test
	public void testCountByAccountId() throws Exception {
		_persistence.countByAccountId(StringPool.BLANK);

		_persistence.countByAccountId(StringPool.NULL);

		_persistence.countByAccountId((String)null);
	}

	@Test
	public void testCountByItemNo() throws Exception {
		_persistence.countByItemNo(StringPool.BLANK);

		_persistence.countByItemNo(StringPool.NULL);

		_persistence.countByItemNo((String)null);
	}

	@Test
	public void testCountByItemId() throws Exception {
		_persistence.countByItemId(StringPool.BLANK);

		_persistence.countByItemId(StringPool.NULL);

		_persistence.countByItemId((String)null);
	}

	@Test
	public void testCountBySalesId() throws Exception {
		_persistence.countBySalesId(StringPool.BLANK);

		_persistence.countBySalesId(StringPool.NULL);

		_persistence.countBySalesId((String)null);
	}

	@Test
	public void testCountByAccountNo() throws Exception {
		_persistence.countByAccountNo(StringPool.BLANK);

		_persistence.countByAccountNo(StringPool.NULL);

		_persistence.countByAccountNo((String)null);
	}

	@Test
	public void testCountByContractId() throws Exception {
		_persistence.countByContractId(StringPool.BLANK);

		_persistence.countByContractId(StringPool.NULL);

		_persistence.countByContractId((String)null);
	}

	@Test
	public void testCountByCompanyId() throws Exception {
		_persistence.countByCompanyId(StringPool.BLANK);

		_persistence.countByCompanyId(StringPool.NULL);

		_persistence.countByCompanyId((String)null);
	}

	@Test
	public void testCountByContractNo() throws Exception {
		_persistence.countByContractNo(StringPool.BLANK);

		_persistence.countByContractNo(StringPool.NULL);

		_persistence.countByContractNo((String)null);
	}

	@Test
	public void testCountBySalesUnique() throws Exception {
		_persistence.countBySalesUnique(StringPool.BLANK);

		_persistence.countBySalesUnique(StringPool.NULL);

		_persistence.countBySalesUnique((String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		SalesMaster newSalesMaster = addSalesMaster();

		SalesMaster existingSalesMaster = _persistence.findByPrimaryKey(newSalesMaster.getPrimaryKey());

		Assert.assertEquals(existingSalesMaster, newSalesMaster);
	}

	@Test(expected = NoSuchSalesMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<SalesMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("SALES_MASTER", "itemNo",
			true, "recordSequence", true, "quantity", true, "accountId", true,
			"createdDate", true, "identifierCodeQualifier", true, "isActive",
			true, "marketId", true, "invoiceDate", true, "accountName", true,
			"docType", true, "orderReceivedDate", true, "amount", true,
			"salesMasterSid", true, "orderSubtype", true, "createdBy", true,
			"price", true, "uploadDate", true, "itemId", true,
			"priceAdjustmentName", true, "itemCodeQualifier", true,
			"contractId", true, "itemUom", true, "modifiedDate", true,
			"customerSubtype", true, "provisionId", true, "wholesaleOwnerId",
			true, "source", true, "accountNo", true, "lotNo", true,
			"parentItemId", true, "customerCompanyCode", true, "analysisCode",
			true, "accountType", true, "modifiedBy", true, "contractNo", true,
			"batchId", true, "brandId", true, "salesId", true,
			"companyStringId", true, "organizationKey", true, "itemParentNo",
			true, "invoiceNumber", true, "orderType", true, "recordLockStatus",
			true, "divisionId", true, "invoiceLineNumber", true, "orderNumber",
			true, "inboundStatus", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		SalesMaster newSalesMaster = addSalesMaster();

		SalesMaster existingSalesMaster = _persistence.fetchByPrimaryKey(newSalesMaster.getPrimaryKey());

		Assert.assertEquals(existingSalesMaster, newSalesMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		SalesMaster missingSalesMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingSalesMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		SalesMaster newSalesMaster1 = addSalesMaster();
		SalesMaster newSalesMaster2 = addSalesMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newSalesMaster1.getPrimaryKey());
		primaryKeys.add(newSalesMaster2.getPrimaryKey());

		Map<Serializable, SalesMaster> salesMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, salesMasters.size());
		Assert.assertEquals(newSalesMaster1,
			salesMasters.get(newSalesMaster1.getPrimaryKey()));
		Assert.assertEquals(newSalesMaster2,
			salesMasters.get(newSalesMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, SalesMaster> salesMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(salesMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		SalesMaster newSalesMaster = addSalesMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newSalesMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, SalesMaster> salesMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, salesMasters.size());
		Assert.assertEquals(newSalesMaster,
			salesMasters.get(newSalesMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, SalesMaster> salesMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(salesMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		SalesMaster newSalesMaster = addSalesMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newSalesMaster.getPrimaryKey());

		Map<Serializable, SalesMaster> salesMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, salesMasters.size());
		Assert.assertEquals(newSalesMaster,
			salesMasters.get(newSalesMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = SalesMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<SalesMaster>() {
				@Override
				public void performAction(SalesMaster salesMaster) {
					Assert.assertNotNull(salesMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		SalesMaster newSalesMaster = addSalesMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SalesMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("salesMasterSid",
				newSalesMaster.getSalesMasterSid()));

		List<SalesMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		SalesMaster existingSalesMaster = result.get(0);

		Assert.assertEquals(existingSalesMaster, newSalesMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SalesMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("salesMasterSid",
				RandomTestUtil.nextInt()));

		List<SalesMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		SalesMaster newSalesMaster = addSalesMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SalesMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"salesMasterSid"));

		Object newSalesMasterSid = newSalesMaster.getSalesMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("salesMasterSid",
				new Object[] { newSalesMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingSalesMasterSid = result.get(0);

		Assert.assertEquals(existingSalesMasterSid, newSalesMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SalesMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"salesMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("salesMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected SalesMaster addSalesMaster() throws Exception {
		int pk = RandomTestUtil.nextInt();

		SalesMaster salesMaster = _persistence.create(pk);

		salesMaster.setItemNo(RandomTestUtil.randomString());

		salesMaster.setRecordSequence(RandomTestUtil.nextInt());

		salesMaster.setQuantity(RandomTestUtil.nextDouble());

		salesMaster.setAccountId(RandomTestUtil.randomString());

		salesMaster.setCreatedDate(RandomTestUtil.nextDate());

		salesMaster.setIdentifierCodeQualifier(RandomTestUtil.randomString());

		salesMaster.setIsActive(RandomTestUtil.randomString());

		salesMaster.setMarketId(RandomTestUtil.randomString());

		salesMaster.setInvoiceDate(RandomTestUtil.nextDate());

		salesMaster.setAccountName(RandomTestUtil.randomString());

		salesMaster.setDocType(RandomTestUtil.randomString());

		salesMaster.setOrderReceivedDate(RandomTestUtil.nextDate());

		salesMaster.setAmount(RandomTestUtil.nextDouble());

		salesMaster.setOrderSubtype(RandomTestUtil.randomString());

		salesMaster.setCreatedBy(RandomTestUtil.nextInt());

		salesMaster.setPrice(RandomTestUtil.nextDouble());

		salesMaster.setUploadDate(RandomTestUtil.nextDate());

		salesMaster.setItemId(RandomTestUtil.randomString());

		salesMaster.setPriceAdjustmentName(RandomTestUtil.randomString());

		salesMaster.setItemCodeQualifier(RandomTestUtil.randomString());

		salesMaster.setContractId(RandomTestUtil.randomString());

		salesMaster.setItemUom(RandomTestUtil.randomString());

		salesMaster.setModifiedDate(RandomTestUtil.nextDate());

		salesMaster.setCustomerSubtype(RandomTestUtil.randomString());

		salesMaster.setProvisionId(RandomTestUtil.nextInt());

		salesMaster.setWholesaleOwnerId(RandomTestUtil.randomString());

		salesMaster.setSource(RandomTestUtil.randomString());

		salesMaster.setAccountNo(RandomTestUtil.randomString());

		salesMaster.setLotNo(RandomTestUtil.randomString());

		salesMaster.setParentItemId(RandomTestUtil.randomString());

		salesMaster.setCustomerCompanyCode(RandomTestUtil.randomString());

		salesMaster.setAnalysisCode(RandomTestUtil.randomString());

		salesMaster.setAccountType(RandomTestUtil.randomString());

		salesMaster.setModifiedBy(RandomTestUtil.nextInt());

		salesMaster.setContractNo(RandomTestUtil.randomString());

		salesMaster.setBatchId(RandomTestUtil.randomString());

		salesMaster.setBrandId(RandomTestUtil.randomString());

		salesMaster.setSalesId(RandomTestUtil.randomString());

		salesMaster.setCompanyStringId(RandomTestUtil.randomString());

		salesMaster.setOrganizationKey(RandomTestUtil.randomString());

		salesMaster.setItemParentNo(RandomTestUtil.randomString());

		salesMaster.setInvoiceNumber(RandomTestUtil.randomString());

		salesMaster.setOrderType(RandomTestUtil.randomString());

		salesMaster.setRecordLockStatus(RandomTestUtil.randomBoolean());

		salesMaster.setDivisionId(RandomTestUtil.randomString());

		salesMaster.setInvoiceLineNumber(RandomTestUtil.randomString());

		salesMaster.setOrderNumber(RandomTestUtil.randomString());

		salesMaster.setInboundStatus(RandomTestUtil.randomString());

		_salesMasters.add(_persistence.update(salesMaster));

		return salesMaster;
	}

	private List<SalesMaster> _salesMasters = new ArrayList<SalesMaster>();
	private SalesMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}