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

import com.stpl.app.exception.NoSuchImtdSalesBasisDetailsException;
import com.stpl.app.model.ImtdSalesBasisDetails;
import com.stpl.app.service.ImtdSalesBasisDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.ImtdSalesBasisDetailsPersistence;
import com.stpl.app.service.persistence.ImtdSalesBasisDetailsUtil;

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
public class ImtdSalesBasisDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ImtdSalesBasisDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ImtdSalesBasisDetails> iterator = _imtdSalesBasisDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ImtdSalesBasisDetails imtdSalesBasisDetails = _persistence.create(pk);

		Assert.assertNotNull(imtdSalesBasisDetails);

		Assert.assertEquals(imtdSalesBasisDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ImtdSalesBasisDetails newImtdSalesBasisDetails = addImtdSalesBasisDetails();

		_persistence.remove(newImtdSalesBasisDetails);

		ImtdSalesBasisDetails existingImtdSalesBasisDetails = _persistence.fetchByPrimaryKey(newImtdSalesBasisDetails.getPrimaryKey());

		Assert.assertNull(existingImtdSalesBasisDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addImtdSalesBasisDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ImtdSalesBasisDetails newImtdSalesBasisDetails = _persistence.create(pk);

		newImtdSalesBasisDetails.setCreatedBy(RandomTestUtil.nextInt());

		newImtdSalesBasisDetails.setNetSalesFormulaMasterSid(RandomTestUtil.nextInt());

		newImtdSalesBasisDetails.setUsersSid(RandomTestUtil.nextInt());

		newImtdSalesBasisDetails.setModifiedBy(RandomTestUtil.nextInt());

		newImtdSalesBasisDetails.setCreatedDate(RandomTestUtil.nextDate());

		newImtdSalesBasisDetails.setContractMasterSid(RandomTestUtil.nextInt());

		newImtdSalesBasisDetails.setCfpNo(RandomTestUtil.randomString());

		newImtdSalesBasisDetails.setImtdCreatedDate(RandomTestUtil.randomString());

		newImtdSalesBasisDetails.setContractNo(RandomTestUtil.randomString());

		newImtdSalesBasisDetails.setContractName(RandomTestUtil.randomString());

		newImtdSalesBasisDetails.setSalesBasisDetailsSid(RandomTestUtil.nextInt());

		newImtdSalesBasisDetails.setCheckRecord(RandomTestUtil.randomBoolean());

		newImtdSalesBasisDetails.setModifiedDate(RandomTestUtil.nextDate());

		newImtdSalesBasisDetails.setCustomerName(RandomTestUtil.randomString());

		newImtdSalesBasisDetails.setOperation(RandomTestUtil.randomString());

		newImtdSalesBasisDetails.setCustomerNo(RandomTestUtil.randomString());

		newImtdSalesBasisDetails.setCfpName(RandomTestUtil.randomString());

		newImtdSalesBasisDetails.setCdrModelSid(RandomTestUtil.nextInt());

		newImtdSalesBasisDetails.setSessionId(RandomTestUtil.randomString());

		newImtdSalesBasisDetails.setCfpContractDetailsSid(RandomTestUtil.nextInt());

		newImtdSalesBasisDetails.setInboundStatus(RandomTestUtil.randomString());

		_imtdSalesBasisDetailses.add(_persistence.update(
				newImtdSalesBasisDetails));

		ImtdSalesBasisDetails existingImtdSalesBasisDetails = _persistence.findByPrimaryKey(newImtdSalesBasisDetails.getPrimaryKey());

		Assert.assertEquals(existingImtdSalesBasisDetails.getCreatedBy(),
			newImtdSalesBasisDetails.getCreatedBy());
		Assert.assertEquals(existingImtdSalesBasisDetails.getNetSalesFormulaMasterSid(),
			newImtdSalesBasisDetails.getNetSalesFormulaMasterSid());
		Assert.assertEquals(existingImtdSalesBasisDetails.getUsersSid(),
			newImtdSalesBasisDetails.getUsersSid());
		Assert.assertEquals(existingImtdSalesBasisDetails.getModifiedBy(),
			newImtdSalesBasisDetails.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdSalesBasisDetails.getCreatedDate()),
			Time.getShortTimestamp(newImtdSalesBasisDetails.getCreatedDate()));
		Assert.assertEquals(existingImtdSalesBasisDetails.getContractMasterSid(),
			newImtdSalesBasisDetails.getContractMasterSid());
		Assert.assertEquals(existingImtdSalesBasisDetails.getCfpNo(),
			newImtdSalesBasisDetails.getCfpNo());
		Assert.assertEquals(existingImtdSalesBasisDetails.getImtdCreatedDate(),
			newImtdSalesBasisDetails.getImtdCreatedDate());
		Assert.assertEquals(existingImtdSalesBasisDetails.getContractNo(),
			newImtdSalesBasisDetails.getContractNo());
		Assert.assertEquals(existingImtdSalesBasisDetails.getContractName(),
			newImtdSalesBasisDetails.getContractName());
		Assert.assertEquals(existingImtdSalesBasisDetails.getSalesBasisDetailsSid(),
			newImtdSalesBasisDetails.getSalesBasisDetailsSid());
		Assert.assertEquals(existingImtdSalesBasisDetails.getCheckRecord(),
			newImtdSalesBasisDetails.getCheckRecord());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdSalesBasisDetails.getModifiedDate()),
			Time.getShortTimestamp(newImtdSalesBasisDetails.getModifiedDate()));
		Assert.assertEquals(existingImtdSalesBasisDetails.getCustomerName(),
			newImtdSalesBasisDetails.getCustomerName());
		Assert.assertEquals(existingImtdSalesBasisDetails.getOperation(),
			newImtdSalesBasisDetails.getOperation());
		Assert.assertEquals(existingImtdSalesBasisDetails.getCustomerNo(),
			newImtdSalesBasisDetails.getCustomerNo());
		Assert.assertEquals(existingImtdSalesBasisDetails.getImtdSalesBasisDetailsSid(),
			newImtdSalesBasisDetails.getImtdSalesBasisDetailsSid());
		Assert.assertEquals(existingImtdSalesBasisDetails.getCfpName(),
			newImtdSalesBasisDetails.getCfpName());
		Assert.assertEquals(existingImtdSalesBasisDetails.getCdrModelSid(),
			newImtdSalesBasisDetails.getCdrModelSid());
		Assert.assertEquals(existingImtdSalesBasisDetails.getSessionId(),
			newImtdSalesBasisDetails.getSessionId());
		Assert.assertEquals(existingImtdSalesBasisDetails.getCfpContractDetailsSid(),
			newImtdSalesBasisDetails.getCfpContractDetailsSid());
		Assert.assertEquals(existingImtdSalesBasisDetails.getInboundStatus(),
			newImtdSalesBasisDetails.getInboundStatus());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ImtdSalesBasisDetails newImtdSalesBasisDetails = addImtdSalesBasisDetails();

		ImtdSalesBasisDetails existingImtdSalesBasisDetails = _persistence.findByPrimaryKey(newImtdSalesBasisDetails.getPrimaryKey());

		Assert.assertEquals(existingImtdSalesBasisDetails,
			newImtdSalesBasisDetails);
	}

	@Test(expected = NoSuchImtdSalesBasisDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<ImtdSalesBasisDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("IMTD_SALES_BASIS_DETAILS",
			"createdBy", true, "netSalesFormulaMasterSid", true, "usersSid",
			true, "modifiedBy", true, "createdDate", true, "contractMasterSid",
			true, "cfpNo", true, "imtdCreatedDate", true, "contractNo", true,
			"contractName", true, "salesBasisDetailsSid", true, "checkRecord",
			true, "modifiedDate", true, "customerName", true, "operation",
			true, "customerNo", true, "imtdSalesBasisDetailsSid", true,
			"cfpName", true, "cdrModelSid", true, "sessionId", true,
			"cfpContractDetailsSid", true, "inboundStatus", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ImtdSalesBasisDetails newImtdSalesBasisDetails = addImtdSalesBasisDetails();

		ImtdSalesBasisDetails existingImtdSalesBasisDetails = _persistence.fetchByPrimaryKey(newImtdSalesBasisDetails.getPrimaryKey());

		Assert.assertEquals(existingImtdSalesBasisDetails,
			newImtdSalesBasisDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ImtdSalesBasisDetails missingImtdSalesBasisDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingImtdSalesBasisDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ImtdSalesBasisDetails newImtdSalesBasisDetails1 = addImtdSalesBasisDetails();
		ImtdSalesBasisDetails newImtdSalesBasisDetails2 = addImtdSalesBasisDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newImtdSalesBasisDetails1.getPrimaryKey());
		primaryKeys.add(newImtdSalesBasisDetails2.getPrimaryKey());

		Map<Serializable, ImtdSalesBasisDetails> imtdSalesBasisDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, imtdSalesBasisDetailses.size());
		Assert.assertEquals(newImtdSalesBasisDetails1,
			imtdSalesBasisDetailses.get(
				newImtdSalesBasisDetails1.getPrimaryKey()));
		Assert.assertEquals(newImtdSalesBasisDetails2,
			imtdSalesBasisDetailses.get(
				newImtdSalesBasisDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ImtdSalesBasisDetails> imtdSalesBasisDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(imtdSalesBasisDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ImtdSalesBasisDetails newImtdSalesBasisDetails = addImtdSalesBasisDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newImtdSalesBasisDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ImtdSalesBasisDetails> imtdSalesBasisDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, imtdSalesBasisDetailses.size());
		Assert.assertEquals(newImtdSalesBasisDetails,
			imtdSalesBasisDetailses.get(
				newImtdSalesBasisDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ImtdSalesBasisDetails> imtdSalesBasisDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(imtdSalesBasisDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ImtdSalesBasisDetails newImtdSalesBasisDetails = addImtdSalesBasisDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newImtdSalesBasisDetails.getPrimaryKey());

		Map<Serializable, ImtdSalesBasisDetails> imtdSalesBasisDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, imtdSalesBasisDetailses.size());
		Assert.assertEquals(newImtdSalesBasisDetails,
			imtdSalesBasisDetailses.get(
				newImtdSalesBasisDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ImtdSalesBasisDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ImtdSalesBasisDetails>() {
				@Override
				public void performAction(
					ImtdSalesBasisDetails imtdSalesBasisDetails) {
					Assert.assertNotNull(imtdSalesBasisDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ImtdSalesBasisDetails newImtdSalesBasisDetails = addImtdSalesBasisDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdSalesBasisDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"imtdSalesBasisDetailsSid",
				newImtdSalesBasisDetails.getImtdSalesBasisDetailsSid()));

		List<ImtdSalesBasisDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ImtdSalesBasisDetails existingImtdSalesBasisDetails = result.get(0);

		Assert.assertEquals(existingImtdSalesBasisDetails,
			newImtdSalesBasisDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdSalesBasisDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"imtdSalesBasisDetailsSid", RandomTestUtil.nextInt()));

		List<ImtdSalesBasisDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ImtdSalesBasisDetails newImtdSalesBasisDetails = addImtdSalesBasisDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdSalesBasisDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"imtdSalesBasisDetailsSid"));

		Object newImtdSalesBasisDetailsSid = newImtdSalesBasisDetails.getImtdSalesBasisDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"imtdSalesBasisDetailsSid",
				new Object[] { newImtdSalesBasisDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingImtdSalesBasisDetailsSid = result.get(0);

		Assert.assertEquals(existingImtdSalesBasisDetailsSid,
			newImtdSalesBasisDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdSalesBasisDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"imtdSalesBasisDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"imtdSalesBasisDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ImtdSalesBasisDetails addImtdSalesBasisDetails()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		ImtdSalesBasisDetails imtdSalesBasisDetails = _persistence.create(pk);

		imtdSalesBasisDetails.setCreatedBy(RandomTestUtil.nextInt());

		imtdSalesBasisDetails.setNetSalesFormulaMasterSid(RandomTestUtil.nextInt());

		imtdSalesBasisDetails.setUsersSid(RandomTestUtil.nextInt());

		imtdSalesBasisDetails.setModifiedBy(RandomTestUtil.nextInt());

		imtdSalesBasisDetails.setCreatedDate(RandomTestUtil.nextDate());

		imtdSalesBasisDetails.setContractMasterSid(RandomTestUtil.nextInt());

		imtdSalesBasisDetails.setCfpNo(RandomTestUtil.randomString());

		imtdSalesBasisDetails.setImtdCreatedDate(RandomTestUtil.randomString());

		imtdSalesBasisDetails.setContractNo(RandomTestUtil.randomString());

		imtdSalesBasisDetails.setContractName(RandomTestUtil.randomString());

		imtdSalesBasisDetails.setSalesBasisDetailsSid(RandomTestUtil.nextInt());

		imtdSalesBasisDetails.setCheckRecord(RandomTestUtil.randomBoolean());

		imtdSalesBasisDetails.setModifiedDate(RandomTestUtil.nextDate());

		imtdSalesBasisDetails.setCustomerName(RandomTestUtil.randomString());

		imtdSalesBasisDetails.setOperation(RandomTestUtil.randomString());

		imtdSalesBasisDetails.setCustomerNo(RandomTestUtil.randomString());

		imtdSalesBasisDetails.setCfpName(RandomTestUtil.randomString());

		imtdSalesBasisDetails.setCdrModelSid(RandomTestUtil.nextInt());

		imtdSalesBasisDetails.setSessionId(RandomTestUtil.randomString());

		imtdSalesBasisDetails.setCfpContractDetailsSid(RandomTestUtil.nextInt());

		imtdSalesBasisDetails.setInboundStatus(RandomTestUtil.randomString());

		_imtdSalesBasisDetailses.add(_persistence.update(imtdSalesBasisDetails));

		return imtdSalesBasisDetails;
	}

	private List<ImtdSalesBasisDetails> _imtdSalesBasisDetailses = new ArrayList<ImtdSalesBasisDetails>();
	private ImtdSalesBasisDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}