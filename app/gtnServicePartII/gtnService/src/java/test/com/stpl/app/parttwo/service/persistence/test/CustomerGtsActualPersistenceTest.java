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

import com.stpl.app.parttwo.exception.NoSuchCustomerGtsActualException;
import com.stpl.app.parttwo.model.CustomerGtsActual;
import com.stpl.app.parttwo.service.CustomerGtsActualLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.CustomerGtsActualPersistence;
import com.stpl.app.parttwo.service.persistence.CustomerGtsActualUtil;

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
public class CustomerGtsActualPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = CustomerGtsActualUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CustomerGtsActual> iterator = _customerGtsActuals.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CustomerGtsActual customerGtsActual = _persistence.create(pk);

		Assert.assertNotNull(customerGtsActual);

		Assert.assertEquals(customerGtsActual.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CustomerGtsActual newCustomerGtsActual = addCustomerGtsActual();

		_persistence.remove(newCustomerGtsActual);

		CustomerGtsActual existingCustomerGtsActual = _persistence.fetchByPrimaryKey(newCustomerGtsActual.getPrimaryKey());

		Assert.assertNull(existingCustomerGtsActual);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCustomerGtsActual();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CustomerGtsActual newCustomerGtsActual = _persistence.create(pk);

		newCustomerGtsActual.setParentAccountId(RandomTestUtil.randomString());

		newCustomerGtsActual.setContractId(RandomTestUtil.randomString());

		newCustomerGtsActual.setAccountId(RandomTestUtil.randomString());

		newCustomerGtsActual.setOrderReceivedDate(RandomTestUtil.nextDate());

		newCustomerGtsActual.setItemId(RandomTestUtil.randomString());

		newCustomerGtsActual.setModifiedDate(RandomTestUtil.nextDate());

		newCustomerGtsActual.setAmount(RandomTestUtil.nextDouble());

		newCustomerGtsActual.setOrderNumber(RandomTestUtil.randomString());

		newCustomerGtsActual.setOrganizationKey(RandomTestUtil.randomString());

		newCustomerGtsActual.setInvoiceDate(RandomTestUtil.nextDate());

		newCustomerGtsActual.setCustomerGtsActualIntfId(RandomTestUtil.nextInt());

		newCustomerGtsActual.setCreatedDate(RandomTestUtil.nextDate());

		newCustomerGtsActual.setCreatedBy(RandomTestUtil.randomString());

		newCustomerGtsActual.setSource(RandomTestUtil.randomString());

		newCustomerGtsActual.setBatchId(RandomTestUtil.randomString());

		newCustomerGtsActual.setSalesId(RandomTestUtil.randomString());

		newCustomerGtsActual.setItemUom(RandomTestUtil.randomString());

		newCustomerGtsActual.setInboundStatus(RandomTestUtil.randomString());

		newCustomerGtsActual.setModifiedBy(RandomTestUtil.randomString());

		newCustomerGtsActual.setInvoiceNumber(RandomTestUtil.randomString());

		newCustomerGtsActual.setLotNo(RandomTestUtil.randomString());

		newCustomerGtsActual.setInvoiceLineNumber(RandomTestUtil.randomString());

		newCustomerGtsActual.setQuantity(RandomTestUtil.nextDouble());

		_customerGtsActuals.add(_persistence.update(newCustomerGtsActual));

		CustomerGtsActual existingCustomerGtsActual = _persistence.findByPrimaryKey(newCustomerGtsActual.getPrimaryKey());

		Assert.assertEquals(existingCustomerGtsActual.getParentAccountId(),
			newCustomerGtsActual.getParentAccountId());
		Assert.assertEquals(existingCustomerGtsActual.getContractId(),
			newCustomerGtsActual.getContractId());
		Assert.assertEquals(existingCustomerGtsActual.getAccountId(),
			newCustomerGtsActual.getAccountId());
		Assert.assertEquals(existingCustomerGtsActual.getCustomerGtsActualSid(),
			newCustomerGtsActual.getCustomerGtsActualSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCustomerGtsActual.getOrderReceivedDate()),
			Time.getShortTimestamp(newCustomerGtsActual.getOrderReceivedDate()));
		Assert.assertEquals(existingCustomerGtsActual.getItemId(),
			newCustomerGtsActual.getItemId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCustomerGtsActual.getModifiedDate()),
			Time.getShortTimestamp(newCustomerGtsActual.getModifiedDate()));
		AssertUtils.assertEquals(existingCustomerGtsActual.getAmount(),
			newCustomerGtsActual.getAmount());
		Assert.assertEquals(existingCustomerGtsActual.getOrderNumber(),
			newCustomerGtsActual.getOrderNumber());
		Assert.assertEquals(existingCustomerGtsActual.getOrganizationKey(),
			newCustomerGtsActual.getOrganizationKey());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCustomerGtsActual.getInvoiceDate()),
			Time.getShortTimestamp(newCustomerGtsActual.getInvoiceDate()));
		Assert.assertEquals(existingCustomerGtsActual.getCustomerGtsActualIntfId(),
			newCustomerGtsActual.getCustomerGtsActualIntfId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCustomerGtsActual.getCreatedDate()),
			Time.getShortTimestamp(newCustomerGtsActual.getCreatedDate()));
		Assert.assertEquals(existingCustomerGtsActual.getCreatedBy(),
			newCustomerGtsActual.getCreatedBy());
		Assert.assertEquals(existingCustomerGtsActual.getSource(),
			newCustomerGtsActual.getSource());
		Assert.assertEquals(existingCustomerGtsActual.getBatchId(),
			newCustomerGtsActual.getBatchId());
		Assert.assertEquals(existingCustomerGtsActual.getSalesId(),
			newCustomerGtsActual.getSalesId());
		Assert.assertEquals(existingCustomerGtsActual.getItemUom(),
			newCustomerGtsActual.getItemUom());
		Assert.assertEquals(existingCustomerGtsActual.getInboundStatus(),
			newCustomerGtsActual.getInboundStatus());
		Assert.assertEquals(existingCustomerGtsActual.getModifiedBy(),
			newCustomerGtsActual.getModifiedBy());
		Assert.assertEquals(existingCustomerGtsActual.getInvoiceNumber(),
			newCustomerGtsActual.getInvoiceNumber());
		Assert.assertEquals(existingCustomerGtsActual.getLotNo(),
			newCustomerGtsActual.getLotNo());
		Assert.assertEquals(existingCustomerGtsActual.getInvoiceLineNumber(),
			newCustomerGtsActual.getInvoiceLineNumber());
		AssertUtils.assertEquals(existingCustomerGtsActual.getQuantity(),
			newCustomerGtsActual.getQuantity());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CustomerGtsActual newCustomerGtsActual = addCustomerGtsActual();

		CustomerGtsActual existingCustomerGtsActual = _persistence.findByPrimaryKey(newCustomerGtsActual.getPrimaryKey());

		Assert.assertEquals(existingCustomerGtsActual, newCustomerGtsActual);
	}

	@Test(expected = NoSuchCustomerGtsActualException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CustomerGtsActual> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CUSTOMER_GTS_ACTUAL",
			"parentAccountId", true, "contractId", true, "accountId", true,
			"customerGtsActualSid", true, "orderReceivedDate", true, "itemId",
			true, "modifiedDate", true, "amount", true, "orderNumber", true,
			"organizationKey", true, "invoiceDate", true,
			"customerGtsActualIntfId", true, "createdDate", true, "createdBy",
			true, "source", true, "batchId", true, "salesId", true, "itemUom",
			true, "inboundStatus", true, "modifiedBy", true, "invoiceNumber",
			true, "lotNo", true, "invoiceLineNumber", true, "quantity", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CustomerGtsActual newCustomerGtsActual = addCustomerGtsActual();

		CustomerGtsActual existingCustomerGtsActual = _persistence.fetchByPrimaryKey(newCustomerGtsActual.getPrimaryKey());

		Assert.assertEquals(existingCustomerGtsActual, newCustomerGtsActual);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CustomerGtsActual missingCustomerGtsActual = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCustomerGtsActual);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CustomerGtsActual newCustomerGtsActual1 = addCustomerGtsActual();
		CustomerGtsActual newCustomerGtsActual2 = addCustomerGtsActual();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCustomerGtsActual1.getPrimaryKey());
		primaryKeys.add(newCustomerGtsActual2.getPrimaryKey());

		Map<Serializable, CustomerGtsActual> customerGtsActuals = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, customerGtsActuals.size());
		Assert.assertEquals(newCustomerGtsActual1,
			customerGtsActuals.get(newCustomerGtsActual1.getPrimaryKey()));
		Assert.assertEquals(newCustomerGtsActual2,
			customerGtsActuals.get(newCustomerGtsActual2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CustomerGtsActual> customerGtsActuals = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(customerGtsActuals.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CustomerGtsActual newCustomerGtsActual = addCustomerGtsActual();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCustomerGtsActual.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CustomerGtsActual> customerGtsActuals = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, customerGtsActuals.size());
		Assert.assertEquals(newCustomerGtsActual,
			customerGtsActuals.get(newCustomerGtsActual.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CustomerGtsActual> customerGtsActuals = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(customerGtsActuals.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CustomerGtsActual newCustomerGtsActual = addCustomerGtsActual();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCustomerGtsActual.getPrimaryKey());

		Map<Serializable, CustomerGtsActual> customerGtsActuals = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, customerGtsActuals.size());
		Assert.assertEquals(newCustomerGtsActual,
			customerGtsActuals.get(newCustomerGtsActual.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CustomerGtsActualLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CustomerGtsActual>() {
				@Override
				public void performAction(CustomerGtsActual customerGtsActual) {
					Assert.assertNotNull(customerGtsActual);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CustomerGtsActual newCustomerGtsActual = addCustomerGtsActual();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CustomerGtsActual.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("customerGtsActualSid",
				newCustomerGtsActual.getCustomerGtsActualSid()));

		List<CustomerGtsActual> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CustomerGtsActual existingCustomerGtsActual = result.get(0);

		Assert.assertEquals(existingCustomerGtsActual, newCustomerGtsActual);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CustomerGtsActual.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("customerGtsActualSid",
				RandomTestUtil.nextInt()));

		List<CustomerGtsActual> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CustomerGtsActual newCustomerGtsActual = addCustomerGtsActual();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CustomerGtsActual.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"customerGtsActualSid"));

		Object newCustomerGtsActualSid = newCustomerGtsActual.getCustomerGtsActualSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("customerGtsActualSid",
				new Object[] { newCustomerGtsActualSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCustomerGtsActualSid = result.get(0);

		Assert.assertEquals(existingCustomerGtsActualSid,
			newCustomerGtsActualSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CustomerGtsActual.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"customerGtsActualSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("customerGtsActualSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected CustomerGtsActual addCustomerGtsActual()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		CustomerGtsActual customerGtsActual = _persistence.create(pk);

		customerGtsActual.setParentAccountId(RandomTestUtil.randomString());

		customerGtsActual.setContractId(RandomTestUtil.randomString());

		customerGtsActual.setAccountId(RandomTestUtil.randomString());

		customerGtsActual.setOrderReceivedDate(RandomTestUtil.nextDate());

		customerGtsActual.setItemId(RandomTestUtil.randomString());

		customerGtsActual.setModifiedDate(RandomTestUtil.nextDate());

		customerGtsActual.setAmount(RandomTestUtil.nextDouble());

		customerGtsActual.setOrderNumber(RandomTestUtil.randomString());

		customerGtsActual.setOrganizationKey(RandomTestUtil.randomString());

		customerGtsActual.setInvoiceDate(RandomTestUtil.nextDate());

		customerGtsActual.setCustomerGtsActualIntfId(RandomTestUtil.nextInt());

		customerGtsActual.setCreatedDate(RandomTestUtil.nextDate());

		customerGtsActual.setCreatedBy(RandomTestUtil.randomString());

		customerGtsActual.setSource(RandomTestUtil.randomString());

		customerGtsActual.setBatchId(RandomTestUtil.randomString());

		customerGtsActual.setSalesId(RandomTestUtil.randomString());

		customerGtsActual.setItemUom(RandomTestUtil.randomString());

		customerGtsActual.setInboundStatus(RandomTestUtil.randomString());

		customerGtsActual.setModifiedBy(RandomTestUtil.randomString());

		customerGtsActual.setInvoiceNumber(RandomTestUtil.randomString());

		customerGtsActual.setLotNo(RandomTestUtil.randomString());

		customerGtsActual.setInvoiceLineNumber(RandomTestUtil.randomString());

		customerGtsActual.setQuantity(RandomTestUtil.nextDouble());

		_customerGtsActuals.add(_persistence.update(customerGtsActual));

		return customerGtsActual;
	}

	private List<CustomerGtsActual> _customerGtsActuals = new ArrayList<CustomerGtsActual>();
	private CustomerGtsActualPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}