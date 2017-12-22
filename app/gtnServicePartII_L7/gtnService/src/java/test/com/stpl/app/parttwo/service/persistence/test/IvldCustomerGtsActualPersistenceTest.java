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

import com.stpl.app.parttwo.exception.NoSuchIvldCustomerGtsActualException;
import com.stpl.app.parttwo.model.IvldCustomerGtsActual;
import com.stpl.app.parttwo.service.IvldCustomerGtsActualLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.IvldCustomerGtsActualPersistence;
import com.stpl.app.parttwo.service.persistence.IvldCustomerGtsActualUtil;

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
public class IvldCustomerGtsActualPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = IvldCustomerGtsActualUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<IvldCustomerGtsActual> iterator = _ivldCustomerGtsActuals.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldCustomerGtsActual ivldCustomerGtsActual = _persistence.create(pk);

		Assert.assertNotNull(ivldCustomerGtsActual);

		Assert.assertEquals(ivldCustomerGtsActual.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		IvldCustomerGtsActual newIvldCustomerGtsActual = addIvldCustomerGtsActual();

		_persistence.remove(newIvldCustomerGtsActual);

		IvldCustomerGtsActual existingIvldCustomerGtsActual = _persistence.fetchByPrimaryKey(newIvldCustomerGtsActual.getPrimaryKey());

		Assert.assertNull(existingIvldCustomerGtsActual);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addIvldCustomerGtsActual();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldCustomerGtsActual newIvldCustomerGtsActual = _persistence.create(pk);

		newIvldCustomerGtsActual.setParentAccountId(RandomTestUtil.randomString());

		newIvldCustomerGtsActual.setAccountId(RandomTestUtil.randomString());

		newIvldCustomerGtsActual.setItemId(RandomTestUtil.randomString());

		newIvldCustomerGtsActual.setOrderReceivedDate(RandomTestUtil.nextDate());

		newIvldCustomerGtsActual.setModifiedDate(RandomTestUtil.nextDate());

		newIvldCustomerGtsActual.setOrderNumber(RandomTestUtil.randomString());

		newIvldCustomerGtsActual.setOrganizationKey(RandomTestUtil.randomString());

		newIvldCustomerGtsActual.setSource(RandomTestUtil.randomString());

		newIvldCustomerGtsActual.setCreatedBy(RandomTestUtil.randomString());

		newIvldCustomerGtsActual.setCreatedDate(RandomTestUtil.randomString());

		newIvldCustomerGtsActual.setAddChgDelIndicator(RandomTestUtil.randomString());

		newIvldCustomerGtsActual.setErrorCode(RandomTestUtil.randomString());

		newIvldCustomerGtsActual.setItemUom(RandomTestUtil.randomString());

		newIvldCustomerGtsActual.setInvoiceNumber(RandomTestUtil.randomString());

		newIvldCustomerGtsActual.setModifiedBy(RandomTestUtil.randomString());

		newIvldCustomerGtsActual.setIntfInsertedDate(RandomTestUtil.nextDate());

		newIvldCustomerGtsActual.setLotNo(RandomTestUtil.randomString());

		newIvldCustomerGtsActual.setReprocessedFlag(RandomTestUtil.randomString());

		newIvldCustomerGtsActual.setQuantity(RandomTestUtil.randomString());

		newIvldCustomerGtsActual.setInvoiceLineNumber(RandomTestUtil.randomString());

		newIvldCustomerGtsActual.setContractId(RandomTestUtil.randomString());

		newIvldCustomerGtsActual.setReasonForFailure(RandomTestUtil.randomString());

		newIvldCustomerGtsActual.setAmount(RandomTestUtil.randomString());

		newIvldCustomerGtsActual.setInvoiceDate(RandomTestUtil.nextDate());

		newIvldCustomerGtsActual.setCustomerGtsActualIntfId(RandomTestUtil.randomString());

		newIvldCustomerGtsActual.setBatchId(RandomTestUtil.randomString());

		newIvldCustomerGtsActual.setSalesId(RandomTestUtil.randomString());

		newIvldCustomerGtsActual.setErrorField(RandomTestUtil.randomString());

		newIvldCustomerGtsActual.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldCustomerGtsActuals.add(_persistence.update(
				newIvldCustomerGtsActual));

		IvldCustomerGtsActual existingIvldCustomerGtsActual = _persistence.findByPrimaryKey(newIvldCustomerGtsActual.getPrimaryKey());

		Assert.assertEquals(existingIvldCustomerGtsActual.getParentAccountId(),
			newIvldCustomerGtsActual.getParentAccountId());
		Assert.assertEquals(existingIvldCustomerGtsActual.getIvldCustomerGtsActualSid(),
			newIvldCustomerGtsActual.getIvldCustomerGtsActualSid());
		Assert.assertEquals(existingIvldCustomerGtsActual.getAccountId(),
			newIvldCustomerGtsActual.getAccountId());
		Assert.assertEquals(existingIvldCustomerGtsActual.getItemId(),
			newIvldCustomerGtsActual.getItemId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldCustomerGtsActual.getOrderReceivedDate()),
			Time.getShortTimestamp(
				newIvldCustomerGtsActual.getOrderReceivedDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldCustomerGtsActual.getModifiedDate()),
			Time.getShortTimestamp(newIvldCustomerGtsActual.getModifiedDate()));
		Assert.assertEquals(existingIvldCustomerGtsActual.getOrderNumber(),
			newIvldCustomerGtsActual.getOrderNumber());
		Assert.assertEquals(existingIvldCustomerGtsActual.getOrganizationKey(),
			newIvldCustomerGtsActual.getOrganizationKey());
		Assert.assertEquals(existingIvldCustomerGtsActual.getSource(),
			newIvldCustomerGtsActual.getSource());
		Assert.assertEquals(existingIvldCustomerGtsActual.getCreatedBy(),
			newIvldCustomerGtsActual.getCreatedBy());
		Assert.assertEquals(existingIvldCustomerGtsActual.getCreatedDate(),
			newIvldCustomerGtsActual.getCreatedDate());
		Assert.assertEquals(existingIvldCustomerGtsActual.getAddChgDelIndicator(),
			newIvldCustomerGtsActual.getAddChgDelIndicator());
		Assert.assertEquals(existingIvldCustomerGtsActual.getErrorCode(),
			newIvldCustomerGtsActual.getErrorCode());
		Assert.assertEquals(existingIvldCustomerGtsActual.getItemUom(),
			newIvldCustomerGtsActual.getItemUom());
		Assert.assertEquals(existingIvldCustomerGtsActual.getInvoiceNumber(),
			newIvldCustomerGtsActual.getInvoiceNumber());
		Assert.assertEquals(existingIvldCustomerGtsActual.getModifiedBy(),
			newIvldCustomerGtsActual.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldCustomerGtsActual.getIntfInsertedDate()),
			Time.getShortTimestamp(
				newIvldCustomerGtsActual.getIntfInsertedDate()));
		Assert.assertEquals(existingIvldCustomerGtsActual.getLotNo(),
			newIvldCustomerGtsActual.getLotNo());
		Assert.assertEquals(existingIvldCustomerGtsActual.getReprocessedFlag(),
			newIvldCustomerGtsActual.getReprocessedFlag());
		Assert.assertEquals(existingIvldCustomerGtsActual.getQuantity(),
			newIvldCustomerGtsActual.getQuantity());
		Assert.assertEquals(existingIvldCustomerGtsActual.getInvoiceLineNumber(),
			newIvldCustomerGtsActual.getInvoiceLineNumber());
		Assert.assertEquals(existingIvldCustomerGtsActual.getContractId(),
			newIvldCustomerGtsActual.getContractId());
		Assert.assertEquals(existingIvldCustomerGtsActual.getReasonForFailure(),
			newIvldCustomerGtsActual.getReasonForFailure());
		Assert.assertEquals(existingIvldCustomerGtsActual.getAmount(),
			newIvldCustomerGtsActual.getAmount());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldCustomerGtsActual.getInvoiceDate()),
			Time.getShortTimestamp(newIvldCustomerGtsActual.getInvoiceDate()));
		Assert.assertEquals(existingIvldCustomerGtsActual.getCustomerGtsActualIntfId(),
			newIvldCustomerGtsActual.getCustomerGtsActualIntfId());
		Assert.assertEquals(existingIvldCustomerGtsActual.getBatchId(),
			newIvldCustomerGtsActual.getBatchId());
		Assert.assertEquals(existingIvldCustomerGtsActual.getSalesId(),
			newIvldCustomerGtsActual.getSalesId());
		Assert.assertEquals(existingIvldCustomerGtsActual.getErrorField(),
			newIvldCustomerGtsActual.getErrorField());
		Assert.assertEquals(existingIvldCustomerGtsActual.getCheckRecord(),
			newIvldCustomerGtsActual.getCheckRecord());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		IvldCustomerGtsActual newIvldCustomerGtsActual = addIvldCustomerGtsActual();

		IvldCustomerGtsActual existingIvldCustomerGtsActual = _persistence.findByPrimaryKey(newIvldCustomerGtsActual.getPrimaryKey());

		Assert.assertEquals(existingIvldCustomerGtsActual,
			newIvldCustomerGtsActual);
	}

	@Test(expected = NoSuchIvldCustomerGtsActualException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<IvldCustomerGtsActual> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("IVLD_CUSTOMER_GTS_ACTUAL",
			"parentAccountId", true, "ivldCustomerGtsActualSid", true,
			"accountId", true, "itemId", true, "orderReceivedDate", true,
			"modifiedDate", true, "orderNumber", true, "organizationKey", true,
			"source", true, "createdBy", true, "createdDate", true,
			"addChgDelIndicator", true, "errorCode", true, "itemUom", true,
			"invoiceNumber", true, "modifiedBy", true, "intfInsertedDate",
			true, "lotNo", true, "reprocessedFlag", true, "quantity", true,
			"invoiceLineNumber", true, "contractId", true, "reasonForFailure",
			true, "amount", true, "invoiceDate", true,
			"customerGtsActualIntfId", true, "batchId", true, "salesId", true,
			"errorField", true, "checkRecord", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		IvldCustomerGtsActual newIvldCustomerGtsActual = addIvldCustomerGtsActual();

		IvldCustomerGtsActual existingIvldCustomerGtsActual = _persistence.fetchByPrimaryKey(newIvldCustomerGtsActual.getPrimaryKey());

		Assert.assertEquals(existingIvldCustomerGtsActual,
			newIvldCustomerGtsActual);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldCustomerGtsActual missingIvldCustomerGtsActual = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingIvldCustomerGtsActual);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		IvldCustomerGtsActual newIvldCustomerGtsActual1 = addIvldCustomerGtsActual();
		IvldCustomerGtsActual newIvldCustomerGtsActual2 = addIvldCustomerGtsActual();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldCustomerGtsActual1.getPrimaryKey());
		primaryKeys.add(newIvldCustomerGtsActual2.getPrimaryKey());

		Map<Serializable, IvldCustomerGtsActual> ivldCustomerGtsActuals = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ivldCustomerGtsActuals.size());
		Assert.assertEquals(newIvldCustomerGtsActual1,
			ivldCustomerGtsActuals.get(
				newIvldCustomerGtsActual1.getPrimaryKey()));
		Assert.assertEquals(newIvldCustomerGtsActual2,
			ivldCustomerGtsActuals.get(
				newIvldCustomerGtsActual2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, IvldCustomerGtsActual> ivldCustomerGtsActuals = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldCustomerGtsActuals.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		IvldCustomerGtsActual newIvldCustomerGtsActual = addIvldCustomerGtsActual();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldCustomerGtsActual.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, IvldCustomerGtsActual> ivldCustomerGtsActuals = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldCustomerGtsActuals.size());
		Assert.assertEquals(newIvldCustomerGtsActual,
			ivldCustomerGtsActuals.get(newIvldCustomerGtsActual.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, IvldCustomerGtsActual> ivldCustomerGtsActuals = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldCustomerGtsActuals.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		IvldCustomerGtsActual newIvldCustomerGtsActual = addIvldCustomerGtsActual();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldCustomerGtsActual.getPrimaryKey());

		Map<Serializable, IvldCustomerGtsActual> ivldCustomerGtsActuals = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldCustomerGtsActuals.size());
		Assert.assertEquals(newIvldCustomerGtsActual,
			ivldCustomerGtsActuals.get(newIvldCustomerGtsActual.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = IvldCustomerGtsActualLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<IvldCustomerGtsActual>() {
				@Override
				public void performAction(
					IvldCustomerGtsActual ivldCustomerGtsActual) {
					Assert.assertNotNull(ivldCustomerGtsActual);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		IvldCustomerGtsActual newIvldCustomerGtsActual = addIvldCustomerGtsActual();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldCustomerGtsActual.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"ivldCustomerGtsActualSid",
				newIvldCustomerGtsActual.getIvldCustomerGtsActualSid()));

		List<IvldCustomerGtsActual> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		IvldCustomerGtsActual existingIvldCustomerGtsActual = result.get(0);

		Assert.assertEquals(existingIvldCustomerGtsActual,
			newIvldCustomerGtsActual);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldCustomerGtsActual.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"ivldCustomerGtsActualSid", RandomTestUtil.nextInt()));

		List<IvldCustomerGtsActual> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		IvldCustomerGtsActual newIvldCustomerGtsActual = addIvldCustomerGtsActual();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldCustomerGtsActual.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldCustomerGtsActualSid"));

		Object newIvldCustomerGtsActualSid = newIvldCustomerGtsActual.getIvldCustomerGtsActualSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"ivldCustomerGtsActualSid",
				new Object[] { newIvldCustomerGtsActualSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingIvldCustomerGtsActualSid = result.get(0);

		Assert.assertEquals(existingIvldCustomerGtsActualSid,
			newIvldCustomerGtsActualSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldCustomerGtsActual.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldCustomerGtsActualSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"ivldCustomerGtsActualSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected IvldCustomerGtsActual addIvldCustomerGtsActual()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldCustomerGtsActual ivldCustomerGtsActual = _persistence.create(pk);

		ivldCustomerGtsActual.setParentAccountId(RandomTestUtil.randomString());

		ivldCustomerGtsActual.setAccountId(RandomTestUtil.randomString());

		ivldCustomerGtsActual.setItemId(RandomTestUtil.randomString());

		ivldCustomerGtsActual.setOrderReceivedDate(RandomTestUtil.nextDate());

		ivldCustomerGtsActual.setModifiedDate(RandomTestUtil.nextDate());

		ivldCustomerGtsActual.setOrderNumber(RandomTestUtil.randomString());

		ivldCustomerGtsActual.setOrganizationKey(RandomTestUtil.randomString());

		ivldCustomerGtsActual.setSource(RandomTestUtil.randomString());

		ivldCustomerGtsActual.setCreatedBy(RandomTestUtil.randomString());

		ivldCustomerGtsActual.setCreatedDate(RandomTestUtil.randomString());

		ivldCustomerGtsActual.setAddChgDelIndicator(RandomTestUtil.randomString());

		ivldCustomerGtsActual.setErrorCode(RandomTestUtil.randomString());

		ivldCustomerGtsActual.setItemUom(RandomTestUtil.randomString());

		ivldCustomerGtsActual.setInvoiceNumber(RandomTestUtil.randomString());

		ivldCustomerGtsActual.setModifiedBy(RandomTestUtil.randomString());

		ivldCustomerGtsActual.setIntfInsertedDate(RandomTestUtil.nextDate());

		ivldCustomerGtsActual.setLotNo(RandomTestUtil.randomString());

		ivldCustomerGtsActual.setReprocessedFlag(RandomTestUtil.randomString());

		ivldCustomerGtsActual.setQuantity(RandomTestUtil.randomString());

		ivldCustomerGtsActual.setInvoiceLineNumber(RandomTestUtil.randomString());

		ivldCustomerGtsActual.setContractId(RandomTestUtil.randomString());

		ivldCustomerGtsActual.setReasonForFailure(RandomTestUtil.randomString());

		ivldCustomerGtsActual.setAmount(RandomTestUtil.randomString());

		ivldCustomerGtsActual.setInvoiceDate(RandomTestUtil.nextDate());

		ivldCustomerGtsActual.setCustomerGtsActualIntfId(RandomTestUtil.randomString());

		ivldCustomerGtsActual.setBatchId(RandomTestUtil.randomString());

		ivldCustomerGtsActual.setSalesId(RandomTestUtil.randomString());

		ivldCustomerGtsActual.setErrorField(RandomTestUtil.randomString());

		ivldCustomerGtsActual.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldCustomerGtsActuals.add(_persistence.update(ivldCustomerGtsActual));

		return ivldCustomerGtsActual;
	}

	private List<IvldCustomerGtsActual> _ivldCustomerGtsActuals = new ArrayList<IvldCustomerGtsActual>();
	private IvldCustomerGtsActualPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}