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

import com.stpl.app.exception.NoSuchImtdDeductionDetailsException;
import com.stpl.app.model.ImtdDeductionDetails;
import com.stpl.app.service.ImtdDeductionDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.ImtdDeductionDetailsPersistence;
import com.stpl.app.service.persistence.ImtdDeductionDetailsUtil;

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
public class ImtdDeductionDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ImtdDeductionDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ImtdDeductionDetails> iterator = _imtdDeductionDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ImtdDeductionDetails imtdDeductionDetails = _persistence.create(pk);

		Assert.assertNotNull(imtdDeductionDetails);

		Assert.assertEquals(imtdDeductionDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ImtdDeductionDetails newImtdDeductionDetails = addImtdDeductionDetails();

		_persistence.remove(newImtdDeductionDetails);

		ImtdDeductionDetails existingImtdDeductionDetails = _persistence.fetchByPrimaryKey(newImtdDeductionDetails.getPrimaryKey());

		Assert.assertNull(existingImtdDeductionDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addImtdDeductionDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ImtdDeductionDetails newImtdDeductionDetails = _persistence.create(pk);

		newImtdDeductionDetails.setDeductionName(RandomTestUtil.randomString());

		newImtdDeductionDetails.setModifiedBy(RandomTestUtil.nextInt());

		newImtdDeductionDetails.setCreatedDate(RandomTestUtil.nextDate());

		newImtdDeductionDetails.setImtdCreatedDate(RandomTestUtil.randomString());

		newImtdDeductionDetails.setDeductionDetailsSid(RandomTestUtil.nextInt());

		newImtdDeductionDetails.setIndicator(RandomTestUtil.randomString());

		newImtdDeductionDetails.setContractNo(RandomTestUtil.randomString());

		newImtdDeductionDetails.setCheckRecord(RandomTestUtil.randomBoolean());

		newImtdDeductionDetails.setDeductionSubType(RandomTestUtil.randomString());

		newImtdDeductionDetails.setCdrModelSid(RandomTestUtil.nextInt());

		newImtdDeductionDetails.setCreatedBy(RandomTestUtil.nextInt());

		newImtdDeductionDetails.setDeductionNo(RandomTestUtil.randomString());

		newImtdDeductionDetails.setNetSalesFormulaMasterSid(RandomTestUtil.nextInt());

		newImtdDeductionDetails.setUsersSid(RandomTestUtil.nextInt());

		newImtdDeductionDetails.setContractMasterSid(RandomTestUtil.nextInt());

		newImtdDeductionDetails.setContractName(RandomTestUtil.randomString());

		newImtdDeductionDetails.setDeductionCategory(RandomTestUtil.randomString());

		newImtdDeductionDetails.setModifiedDate(RandomTestUtil.nextDate());

		newImtdDeductionDetails.setDeductionType(RandomTestUtil.randomString());

		newImtdDeductionDetails.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newImtdDeductionDetails.setOperation(RandomTestUtil.randomString());

		newImtdDeductionDetails.setSessionId(RandomTestUtil.randomString());

		newImtdDeductionDetails.setRsContractSid(RandomTestUtil.nextInt());

		newImtdDeductionDetails.setInboundStatus(RandomTestUtil.randomString());

		_imtdDeductionDetailses.add(_persistence.update(newImtdDeductionDetails));

		ImtdDeductionDetails existingImtdDeductionDetails = _persistence.findByPrimaryKey(newImtdDeductionDetails.getPrimaryKey());

		Assert.assertEquals(existingImtdDeductionDetails.getImtdDeductionDetailsSid(),
			newImtdDeductionDetails.getImtdDeductionDetailsSid());
		Assert.assertEquals(existingImtdDeductionDetails.getDeductionName(),
			newImtdDeductionDetails.getDeductionName());
		Assert.assertEquals(existingImtdDeductionDetails.getModifiedBy(),
			newImtdDeductionDetails.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdDeductionDetails.getCreatedDate()),
			Time.getShortTimestamp(newImtdDeductionDetails.getCreatedDate()));
		Assert.assertEquals(existingImtdDeductionDetails.getImtdCreatedDate(),
			newImtdDeductionDetails.getImtdCreatedDate());
		Assert.assertEquals(existingImtdDeductionDetails.getDeductionDetailsSid(),
			newImtdDeductionDetails.getDeductionDetailsSid());
		Assert.assertEquals(existingImtdDeductionDetails.getIndicator(),
			newImtdDeductionDetails.getIndicator());
		Assert.assertEquals(existingImtdDeductionDetails.getContractNo(),
			newImtdDeductionDetails.getContractNo());
		Assert.assertEquals(existingImtdDeductionDetails.getCheckRecord(),
			newImtdDeductionDetails.getCheckRecord());
		Assert.assertEquals(existingImtdDeductionDetails.getDeductionSubType(),
			newImtdDeductionDetails.getDeductionSubType());
		Assert.assertEquals(existingImtdDeductionDetails.getCdrModelSid(),
			newImtdDeductionDetails.getCdrModelSid());
		Assert.assertEquals(existingImtdDeductionDetails.getCreatedBy(),
			newImtdDeductionDetails.getCreatedBy());
		Assert.assertEquals(existingImtdDeductionDetails.getDeductionNo(),
			newImtdDeductionDetails.getDeductionNo());
		Assert.assertEquals(existingImtdDeductionDetails.getNetSalesFormulaMasterSid(),
			newImtdDeductionDetails.getNetSalesFormulaMasterSid());
		Assert.assertEquals(existingImtdDeductionDetails.getUsersSid(),
			newImtdDeductionDetails.getUsersSid());
		Assert.assertEquals(existingImtdDeductionDetails.getContractMasterSid(),
			newImtdDeductionDetails.getContractMasterSid());
		Assert.assertEquals(existingImtdDeductionDetails.getContractName(),
			newImtdDeductionDetails.getContractName());
		Assert.assertEquals(existingImtdDeductionDetails.getDeductionCategory(),
			newImtdDeductionDetails.getDeductionCategory());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdDeductionDetails.getModifiedDate()),
			Time.getShortTimestamp(newImtdDeductionDetails.getModifiedDate()));
		Assert.assertEquals(existingImtdDeductionDetails.getDeductionType(),
			newImtdDeductionDetails.getDeductionType());
		Assert.assertEquals(existingImtdDeductionDetails.getRecordLockStatus(),
			newImtdDeductionDetails.getRecordLockStatus());
		Assert.assertEquals(existingImtdDeductionDetails.getOperation(),
			newImtdDeductionDetails.getOperation());
		Assert.assertEquals(existingImtdDeductionDetails.getSessionId(),
			newImtdDeductionDetails.getSessionId());
		Assert.assertEquals(existingImtdDeductionDetails.getRsContractSid(),
			newImtdDeductionDetails.getRsContractSid());
		Assert.assertEquals(existingImtdDeductionDetails.getInboundStatus(),
			newImtdDeductionDetails.getInboundStatus());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ImtdDeductionDetails newImtdDeductionDetails = addImtdDeductionDetails();

		ImtdDeductionDetails existingImtdDeductionDetails = _persistence.findByPrimaryKey(newImtdDeductionDetails.getPrimaryKey());

		Assert.assertEquals(existingImtdDeductionDetails,
			newImtdDeductionDetails);
	}

	@Test(expected = NoSuchImtdDeductionDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<ImtdDeductionDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("IMTD_DEDUCTION_DETAILS",
			"imtdDeductionDetailsSid", true, "deductionName", true,
			"modifiedBy", true, "createdDate", true, "imtdCreatedDate", true,
			"deductionDetailsSid", true, "indicator", true, "contractNo", true,
			"checkRecord", true, "deductionSubType", true, "cdrModelSid", true,
			"createdBy", true, "deductionNo", true, "netSalesFormulaMasterSid",
			true, "usersSid", true, "contractMasterSid", true, "contractName",
			true, "deductionCategory", true, "modifiedDate", true,
			"deductionType", true, "recordLockStatus", true, "operation", true,
			"sessionId", true, "rsContractSid", true, "inboundStatus", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ImtdDeductionDetails newImtdDeductionDetails = addImtdDeductionDetails();

		ImtdDeductionDetails existingImtdDeductionDetails = _persistence.fetchByPrimaryKey(newImtdDeductionDetails.getPrimaryKey());

		Assert.assertEquals(existingImtdDeductionDetails,
			newImtdDeductionDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ImtdDeductionDetails missingImtdDeductionDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingImtdDeductionDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ImtdDeductionDetails newImtdDeductionDetails1 = addImtdDeductionDetails();
		ImtdDeductionDetails newImtdDeductionDetails2 = addImtdDeductionDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newImtdDeductionDetails1.getPrimaryKey());
		primaryKeys.add(newImtdDeductionDetails2.getPrimaryKey());

		Map<Serializable, ImtdDeductionDetails> imtdDeductionDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, imtdDeductionDetailses.size());
		Assert.assertEquals(newImtdDeductionDetails1,
			imtdDeductionDetailses.get(newImtdDeductionDetails1.getPrimaryKey()));
		Assert.assertEquals(newImtdDeductionDetails2,
			imtdDeductionDetailses.get(newImtdDeductionDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ImtdDeductionDetails> imtdDeductionDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(imtdDeductionDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ImtdDeductionDetails newImtdDeductionDetails = addImtdDeductionDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newImtdDeductionDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ImtdDeductionDetails> imtdDeductionDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, imtdDeductionDetailses.size());
		Assert.assertEquals(newImtdDeductionDetails,
			imtdDeductionDetailses.get(newImtdDeductionDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ImtdDeductionDetails> imtdDeductionDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(imtdDeductionDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ImtdDeductionDetails newImtdDeductionDetails = addImtdDeductionDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newImtdDeductionDetails.getPrimaryKey());

		Map<Serializable, ImtdDeductionDetails> imtdDeductionDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, imtdDeductionDetailses.size());
		Assert.assertEquals(newImtdDeductionDetails,
			imtdDeductionDetailses.get(newImtdDeductionDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ImtdDeductionDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ImtdDeductionDetails>() {
				@Override
				public void performAction(
					ImtdDeductionDetails imtdDeductionDetails) {
					Assert.assertNotNull(imtdDeductionDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ImtdDeductionDetails newImtdDeductionDetails = addImtdDeductionDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdDeductionDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("imtdDeductionDetailsSid",
				newImtdDeductionDetails.getImtdDeductionDetailsSid()));

		List<ImtdDeductionDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ImtdDeductionDetails existingImtdDeductionDetails = result.get(0);

		Assert.assertEquals(existingImtdDeductionDetails,
			newImtdDeductionDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdDeductionDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("imtdDeductionDetailsSid",
				RandomTestUtil.nextInt()));

		List<ImtdDeductionDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ImtdDeductionDetails newImtdDeductionDetails = addImtdDeductionDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdDeductionDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"imtdDeductionDetailsSid"));

		Object newImtdDeductionDetailsSid = newImtdDeductionDetails.getImtdDeductionDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("imtdDeductionDetailsSid",
				new Object[] { newImtdDeductionDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingImtdDeductionDetailsSid = result.get(0);

		Assert.assertEquals(existingImtdDeductionDetailsSid,
			newImtdDeductionDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdDeductionDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"imtdDeductionDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("imtdDeductionDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ImtdDeductionDetails addImtdDeductionDetails()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		ImtdDeductionDetails imtdDeductionDetails = _persistence.create(pk);

		imtdDeductionDetails.setDeductionName(RandomTestUtil.randomString());

		imtdDeductionDetails.setModifiedBy(RandomTestUtil.nextInt());

		imtdDeductionDetails.setCreatedDate(RandomTestUtil.nextDate());

		imtdDeductionDetails.setImtdCreatedDate(RandomTestUtil.randomString());

		imtdDeductionDetails.setDeductionDetailsSid(RandomTestUtil.nextInt());

		imtdDeductionDetails.setIndicator(RandomTestUtil.randomString());

		imtdDeductionDetails.setContractNo(RandomTestUtil.randomString());

		imtdDeductionDetails.setCheckRecord(RandomTestUtil.randomBoolean());

		imtdDeductionDetails.setDeductionSubType(RandomTestUtil.randomString());

		imtdDeductionDetails.setCdrModelSid(RandomTestUtil.nextInt());

		imtdDeductionDetails.setCreatedBy(RandomTestUtil.nextInt());

		imtdDeductionDetails.setDeductionNo(RandomTestUtil.randomString());

		imtdDeductionDetails.setNetSalesFormulaMasterSid(RandomTestUtil.nextInt());

		imtdDeductionDetails.setUsersSid(RandomTestUtil.nextInt());

		imtdDeductionDetails.setContractMasterSid(RandomTestUtil.nextInt());

		imtdDeductionDetails.setContractName(RandomTestUtil.randomString());

		imtdDeductionDetails.setDeductionCategory(RandomTestUtil.randomString());

		imtdDeductionDetails.setModifiedDate(RandomTestUtil.nextDate());

		imtdDeductionDetails.setDeductionType(RandomTestUtil.randomString());

		imtdDeductionDetails.setRecordLockStatus(RandomTestUtil.randomBoolean());

		imtdDeductionDetails.setOperation(RandomTestUtil.randomString());

		imtdDeductionDetails.setSessionId(RandomTestUtil.randomString());

		imtdDeductionDetails.setRsContractSid(RandomTestUtil.nextInt());

		imtdDeductionDetails.setInboundStatus(RandomTestUtil.randomString());

		_imtdDeductionDetailses.add(_persistence.update(imtdDeductionDetails));

		return imtdDeductionDetails;
	}

	private List<ImtdDeductionDetails> _imtdDeductionDetailses = new ArrayList<ImtdDeductionDetails>();
	private ImtdDeductionDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}