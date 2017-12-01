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

import com.stpl.app.exception.NoSuchDeductionDetailsException;
import com.stpl.app.model.DeductionDetails;
import com.stpl.app.service.DeductionDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.DeductionDetailsPersistence;
import com.stpl.app.service.persistence.DeductionDetailsUtil;

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
public class DeductionDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = DeductionDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<DeductionDetails> iterator = _deductionDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		DeductionDetails deductionDetails = _persistence.create(pk);

		Assert.assertNotNull(deductionDetails);

		Assert.assertEquals(deductionDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		DeductionDetails newDeductionDetails = addDeductionDetails();

		_persistence.remove(newDeductionDetails);

		DeductionDetails existingDeductionDetails = _persistence.fetchByPrimaryKey(newDeductionDetails.getPrimaryKey());

		Assert.assertNull(existingDeductionDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addDeductionDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		DeductionDetails newDeductionDetails = _persistence.create(pk);

		newDeductionDetails.setCreatedBy(RandomTestUtil.nextInt());

		newDeductionDetails.setNetSalesFormulaMasterSid(RandomTestUtil.nextInt());

		newDeductionDetails.setModifiedBy(RandomTestUtil.nextInt());

		newDeductionDetails.setRsContractSid(RandomTestUtil.nextInt());

		newDeductionDetails.setCreatedDate(RandomTestUtil.nextDate());

		newDeductionDetails.setContractMasterSid(RandomTestUtil.nextInt());

		newDeductionDetails.setIndicator(RandomTestUtil.randomString());

		newDeductionDetails.setModifiedDate(RandomTestUtil.nextDate());

		newDeductionDetails.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newDeductionDetails.setSource(RandomTestUtil.randomString());

		newDeductionDetails.setCdrModelSid(RandomTestUtil.nextInt());

		newDeductionDetails.setInboundStatus(RandomTestUtil.randomString());

		newDeductionDetails.setDeductionSubType(RandomTestUtil.randomString());

		newDeductionDetails.setDeductionType(RandomTestUtil.randomString());

		newDeductionDetails.setDeductionCategory(RandomTestUtil.randomString());

		_deductionDetailses.add(_persistence.update(newDeductionDetails));

		DeductionDetails existingDeductionDetails = _persistence.findByPrimaryKey(newDeductionDetails.getPrimaryKey());

		Assert.assertEquals(existingDeductionDetails.getCreatedBy(),
			newDeductionDetails.getCreatedBy());
		Assert.assertEquals(existingDeductionDetails.getNetSalesFormulaMasterSid(),
			newDeductionDetails.getNetSalesFormulaMasterSid());
		Assert.assertEquals(existingDeductionDetails.getModifiedBy(),
			newDeductionDetails.getModifiedBy());
		Assert.assertEquals(existingDeductionDetails.getRsContractSid(),
			newDeductionDetails.getRsContractSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingDeductionDetails.getCreatedDate()),
			Time.getShortTimestamp(newDeductionDetails.getCreatedDate()));
		Assert.assertEquals(existingDeductionDetails.getContractMasterSid(),
			newDeductionDetails.getContractMasterSid());
		Assert.assertEquals(existingDeductionDetails.getDeductionDetailsSid(),
			newDeductionDetails.getDeductionDetailsSid());
		Assert.assertEquals(existingDeductionDetails.getIndicator(),
			newDeductionDetails.getIndicator());
		Assert.assertEquals(Time.getShortTimestamp(
				existingDeductionDetails.getModifiedDate()),
			Time.getShortTimestamp(newDeductionDetails.getModifiedDate()));
		Assert.assertEquals(existingDeductionDetails.getRecordLockStatus(),
			newDeductionDetails.getRecordLockStatus());
		Assert.assertEquals(existingDeductionDetails.getSource(),
			newDeductionDetails.getSource());
		Assert.assertEquals(existingDeductionDetails.getCdrModelSid(),
			newDeductionDetails.getCdrModelSid());
		Assert.assertEquals(existingDeductionDetails.getInboundStatus(),
			newDeductionDetails.getInboundStatus());
		Assert.assertEquals(existingDeductionDetails.getDeductionSubType(),
			newDeductionDetails.getDeductionSubType());
		Assert.assertEquals(existingDeductionDetails.getDeductionType(),
			newDeductionDetails.getDeductionType());
		Assert.assertEquals(existingDeductionDetails.getDeductionCategory(),
			newDeductionDetails.getDeductionCategory());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		DeductionDetails newDeductionDetails = addDeductionDetails();

		DeductionDetails existingDeductionDetails = _persistence.findByPrimaryKey(newDeductionDetails.getPrimaryKey());

		Assert.assertEquals(existingDeductionDetails, newDeductionDetails);
	}

	@Test(expected = NoSuchDeductionDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<DeductionDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("DEDUCTION_DETAILS",
			"createdBy", true, "netSalesFormulaMasterSid", true, "modifiedBy",
			true, "rsContractSid", true, "createdDate", true,
			"contractMasterSid", true, "deductionDetailsSid", true,
			"indicator", true, "modifiedDate", true, "recordLockStatus", true,
			"source", true, "cdrModelSid", true, "inboundStatus", true,
			"deductionSubType", true, "deductionType", true,
			"deductionCategory", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		DeductionDetails newDeductionDetails = addDeductionDetails();

		DeductionDetails existingDeductionDetails = _persistence.fetchByPrimaryKey(newDeductionDetails.getPrimaryKey());

		Assert.assertEquals(existingDeductionDetails, newDeductionDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		DeductionDetails missingDeductionDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingDeductionDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		DeductionDetails newDeductionDetails1 = addDeductionDetails();
		DeductionDetails newDeductionDetails2 = addDeductionDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDeductionDetails1.getPrimaryKey());
		primaryKeys.add(newDeductionDetails2.getPrimaryKey());

		Map<Serializable, DeductionDetails> deductionDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, deductionDetailses.size());
		Assert.assertEquals(newDeductionDetails1,
			deductionDetailses.get(newDeductionDetails1.getPrimaryKey()));
		Assert.assertEquals(newDeductionDetails2,
			deductionDetailses.get(newDeductionDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, DeductionDetails> deductionDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(deductionDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		DeductionDetails newDeductionDetails = addDeductionDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDeductionDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, DeductionDetails> deductionDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, deductionDetailses.size());
		Assert.assertEquals(newDeductionDetails,
			deductionDetailses.get(newDeductionDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, DeductionDetails> deductionDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(deductionDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		DeductionDetails newDeductionDetails = addDeductionDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDeductionDetails.getPrimaryKey());

		Map<Serializable, DeductionDetails> deductionDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, deductionDetailses.size());
		Assert.assertEquals(newDeductionDetails,
			deductionDetailses.get(newDeductionDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = DeductionDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<DeductionDetails>() {
				@Override
				public void performAction(DeductionDetails deductionDetails) {
					Assert.assertNotNull(deductionDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		DeductionDetails newDeductionDetails = addDeductionDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DeductionDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("deductionDetailsSid",
				newDeductionDetails.getDeductionDetailsSid()));

		List<DeductionDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		DeductionDetails existingDeductionDetails = result.get(0);

		Assert.assertEquals(existingDeductionDetails, newDeductionDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DeductionDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("deductionDetailsSid",
				RandomTestUtil.nextInt()));

		List<DeductionDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		DeductionDetails newDeductionDetails = addDeductionDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DeductionDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"deductionDetailsSid"));

		Object newDeductionDetailsSid = newDeductionDetails.getDeductionDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("deductionDetailsSid",
				new Object[] { newDeductionDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingDeductionDetailsSid = result.get(0);

		Assert.assertEquals(existingDeductionDetailsSid, newDeductionDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DeductionDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"deductionDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("deductionDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected DeductionDetails addDeductionDetails() throws Exception {
		int pk = RandomTestUtil.nextInt();

		DeductionDetails deductionDetails = _persistence.create(pk);

		deductionDetails.setCreatedBy(RandomTestUtil.nextInt());

		deductionDetails.setNetSalesFormulaMasterSid(RandomTestUtil.nextInt());

		deductionDetails.setModifiedBy(RandomTestUtil.nextInt());

		deductionDetails.setRsContractSid(RandomTestUtil.nextInt());

		deductionDetails.setCreatedDate(RandomTestUtil.nextDate());

		deductionDetails.setContractMasterSid(RandomTestUtil.nextInt());

		deductionDetails.setIndicator(RandomTestUtil.randomString());

		deductionDetails.setModifiedDate(RandomTestUtil.nextDate());

		deductionDetails.setRecordLockStatus(RandomTestUtil.randomBoolean());

		deductionDetails.setSource(RandomTestUtil.randomString());

		deductionDetails.setCdrModelSid(RandomTestUtil.nextInt());

		deductionDetails.setInboundStatus(RandomTestUtil.randomString());

		deductionDetails.setDeductionSubType(RandomTestUtil.randomString());

		deductionDetails.setDeductionType(RandomTestUtil.randomString());

		deductionDetails.setDeductionCategory(RandomTestUtil.randomString());

		_deductionDetailses.add(_persistence.update(deductionDetails));

		return deductionDetails;
	}

	private List<DeductionDetails> _deductionDetailses = new ArrayList<DeductionDetails>();
	private DeductionDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}