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

import com.stpl.app.exception.NoSuchGcmContractDetailsException;
import com.stpl.app.model.GcmContractDetails;
import com.stpl.app.service.GcmContractDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.GcmContractDetailsPersistence;
import com.stpl.app.service.persistence.GcmContractDetailsUtil;

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
public class GcmContractDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = GcmContractDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<GcmContractDetails> iterator = _gcmContractDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		GcmContractDetails gcmContractDetails = _persistence.create(pk);

		Assert.assertNotNull(gcmContractDetails);

		Assert.assertEquals(gcmContractDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		GcmContractDetails newGcmContractDetails = addGcmContractDetails();

		_persistence.remove(newGcmContractDetails);

		GcmContractDetails existingGcmContractDetails = _persistence.fetchByPrimaryKey(newGcmContractDetails.getPrimaryKey());

		Assert.assertNull(existingGcmContractDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addGcmContractDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		GcmContractDetails newGcmContractDetails = _persistence.create(pk);

		newGcmContractDetails.setPaymentMethod(RandomTestUtil.randomString());

		newGcmContractDetails.setUserId(RandomTestUtil.nextInt());

		newGcmContractDetails.setEndDate(RandomTestUtil.nextDate());

		newGcmContractDetails.setPaymentFrequency(RandomTestUtil.randomString());

		newGcmContractDetails.setComponentId(RandomTestUtil.randomString());

		newGcmContractDetails.setModifiedDate(RandomTestUtil.nextDate());

		newGcmContractDetails.setComponentName(RandomTestUtil.randomString());

		newGcmContractDetails.setRsCalendar(RandomTestUtil.randomString());

		newGcmContractDetails.setFileName(RandomTestUtil.randomString());

		newGcmContractDetails.setStartDate(RandomTestUtil.nextDate());

		newGcmContractDetails.setPlanLevel(RandomTestUtil.randomString());

		newGcmContractDetails.setCreatedDate(RandomTestUtil.nextDate());

		newGcmContractDetails.setCreatedBy(RandomTestUtil.nextInt());

		newGcmContractDetails.setComponentNo(RandomTestUtil.randomString());

		newGcmContractDetails.setProgramType(RandomTestUtil.randomString());

		newGcmContractDetails.setSessionId(RandomTestUtil.randomString());

		newGcmContractDetails.setModifiedBy(RandomTestUtil.nextInt());

		newGcmContractDetails.setComponentStatus(RandomTestUtil.randomString());

		newGcmContractDetails.setComponentType(RandomTestUtil.randomString());

		_gcmContractDetailses.add(_persistence.update(newGcmContractDetails));

		GcmContractDetails existingGcmContractDetails = _persistence.findByPrimaryKey(newGcmContractDetails.getPrimaryKey());

		Assert.assertEquals(existingGcmContractDetails.getPaymentMethod(),
			newGcmContractDetails.getPaymentMethod());
		Assert.assertEquals(existingGcmContractDetails.getUserId(),
			newGcmContractDetails.getUserId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingGcmContractDetails.getEndDate()),
			Time.getShortTimestamp(newGcmContractDetails.getEndDate()));
		Assert.assertEquals(existingGcmContractDetails.getPaymentFrequency(),
			newGcmContractDetails.getPaymentFrequency());
		Assert.assertEquals(existingGcmContractDetails.getGcmContractDetailsSid(),
			newGcmContractDetails.getGcmContractDetailsSid());
		Assert.assertEquals(existingGcmContractDetails.getComponentId(),
			newGcmContractDetails.getComponentId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingGcmContractDetails.getModifiedDate()),
			Time.getShortTimestamp(newGcmContractDetails.getModifiedDate()));
		Assert.assertEquals(existingGcmContractDetails.getComponentName(),
			newGcmContractDetails.getComponentName());
		Assert.assertEquals(existingGcmContractDetails.getRsCalendar(),
			newGcmContractDetails.getRsCalendar());
		Assert.assertEquals(existingGcmContractDetails.getFileName(),
			newGcmContractDetails.getFileName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingGcmContractDetails.getStartDate()),
			Time.getShortTimestamp(newGcmContractDetails.getStartDate()));
		Assert.assertEquals(existingGcmContractDetails.getPlanLevel(),
			newGcmContractDetails.getPlanLevel());
		Assert.assertEquals(Time.getShortTimestamp(
				existingGcmContractDetails.getCreatedDate()),
			Time.getShortTimestamp(newGcmContractDetails.getCreatedDate()));
		Assert.assertEquals(existingGcmContractDetails.getCreatedBy(),
			newGcmContractDetails.getCreatedBy());
		Assert.assertEquals(existingGcmContractDetails.getComponentNo(),
			newGcmContractDetails.getComponentNo());
		Assert.assertEquals(existingGcmContractDetails.getProgramType(),
			newGcmContractDetails.getProgramType());
		Assert.assertEquals(existingGcmContractDetails.getSessionId(),
			newGcmContractDetails.getSessionId());
		Assert.assertEquals(existingGcmContractDetails.getModifiedBy(),
			newGcmContractDetails.getModifiedBy());
		Assert.assertEquals(existingGcmContractDetails.getComponentStatus(),
			newGcmContractDetails.getComponentStatus());
		Assert.assertEquals(existingGcmContractDetails.getComponentType(),
			newGcmContractDetails.getComponentType());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		GcmContractDetails newGcmContractDetails = addGcmContractDetails();

		GcmContractDetails existingGcmContractDetails = _persistence.findByPrimaryKey(newGcmContractDetails.getPrimaryKey());

		Assert.assertEquals(existingGcmContractDetails, newGcmContractDetails);
	}

	@Test(expected = NoSuchGcmContractDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<GcmContractDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("GCM_CONTRACT_DETAILS",
			"paymentMethod", true, "userId", true, "endDate", true,
			"paymentFrequency", true, "gcmContractDetailsSid", true,
			"componentId", true, "modifiedDate", true, "componentName", true,
			"rsCalendar", true, "fileName", true, "startDate", true,
			"planLevel", true, "createdDate", true, "createdBy", true,
			"componentNo", true, "programType", true, "sessionId", true,
			"modifiedBy", true, "componentStatus", true, "componentType", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		GcmContractDetails newGcmContractDetails = addGcmContractDetails();

		GcmContractDetails existingGcmContractDetails = _persistence.fetchByPrimaryKey(newGcmContractDetails.getPrimaryKey());

		Assert.assertEquals(existingGcmContractDetails, newGcmContractDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		GcmContractDetails missingGcmContractDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingGcmContractDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		GcmContractDetails newGcmContractDetails1 = addGcmContractDetails();
		GcmContractDetails newGcmContractDetails2 = addGcmContractDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newGcmContractDetails1.getPrimaryKey());
		primaryKeys.add(newGcmContractDetails2.getPrimaryKey());

		Map<Serializable, GcmContractDetails> gcmContractDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, gcmContractDetailses.size());
		Assert.assertEquals(newGcmContractDetails1,
			gcmContractDetailses.get(newGcmContractDetails1.getPrimaryKey()));
		Assert.assertEquals(newGcmContractDetails2,
			gcmContractDetailses.get(newGcmContractDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, GcmContractDetails> gcmContractDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(gcmContractDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		GcmContractDetails newGcmContractDetails = addGcmContractDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newGcmContractDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, GcmContractDetails> gcmContractDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, gcmContractDetailses.size());
		Assert.assertEquals(newGcmContractDetails,
			gcmContractDetailses.get(newGcmContractDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, GcmContractDetails> gcmContractDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(gcmContractDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		GcmContractDetails newGcmContractDetails = addGcmContractDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newGcmContractDetails.getPrimaryKey());

		Map<Serializable, GcmContractDetails> gcmContractDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, gcmContractDetailses.size());
		Assert.assertEquals(newGcmContractDetails,
			gcmContractDetailses.get(newGcmContractDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = GcmContractDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<GcmContractDetails>() {
				@Override
				public void performAction(GcmContractDetails gcmContractDetails) {
					Assert.assertNotNull(gcmContractDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		GcmContractDetails newGcmContractDetails = addGcmContractDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(GcmContractDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("gcmContractDetailsSid",
				newGcmContractDetails.getGcmContractDetailsSid()));

		List<GcmContractDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		GcmContractDetails existingGcmContractDetails = result.get(0);

		Assert.assertEquals(existingGcmContractDetails, newGcmContractDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(GcmContractDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("gcmContractDetailsSid",
				RandomTestUtil.nextInt()));

		List<GcmContractDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		GcmContractDetails newGcmContractDetails = addGcmContractDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(GcmContractDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"gcmContractDetailsSid"));

		Object newGcmContractDetailsSid = newGcmContractDetails.getGcmContractDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("gcmContractDetailsSid",
				new Object[] { newGcmContractDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingGcmContractDetailsSid = result.get(0);

		Assert.assertEquals(existingGcmContractDetailsSid,
			newGcmContractDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(GcmContractDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"gcmContractDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("gcmContractDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected GcmContractDetails addGcmContractDetails()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		GcmContractDetails gcmContractDetails = _persistence.create(pk);

		gcmContractDetails.setPaymentMethod(RandomTestUtil.randomString());

		gcmContractDetails.setUserId(RandomTestUtil.nextInt());

		gcmContractDetails.setEndDate(RandomTestUtil.nextDate());

		gcmContractDetails.setPaymentFrequency(RandomTestUtil.randomString());

		gcmContractDetails.setComponentId(RandomTestUtil.randomString());

		gcmContractDetails.setModifiedDate(RandomTestUtil.nextDate());

		gcmContractDetails.setComponentName(RandomTestUtil.randomString());

		gcmContractDetails.setRsCalendar(RandomTestUtil.randomString());

		gcmContractDetails.setFileName(RandomTestUtil.randomString());

		gcmContractDetails.setStartDate(RandomTestUtil.nextDate());

		gcmContractDetails.setPlanLevel(RandomTestUtil.randomString());

		gcmContractDetails.setCreatedDate(RandomTestUtil.nextDate());

		gcmContractDetails.setCreatedBy(RandomTestUtil.nextInt());

		gcmContractDetails.setComponentNo(RandomTestUtil.randomString());

		gcmContractDetails.setProgramType(RandomTestUtil.randomString());

		gcmContractDetails.setSessionId(RandomTestUtil.randomString());

		gcmContractDetails.setModifiedBy(RandomTestUtil.nextInt());

		gcmContractDetails.setComponentStatus(RandomTestUtil.randomString());

		gcmContractDetails.setComponentType(RandomTestUtil.randomString());

		_gcmContractDetailses.add(_persistence.update(gcmContractDetails));

		return gcmContractDetails;
	}

	private List<GcmContractDetails> _gcmContractDetailses = new ArrayList<GcmContractDetails>();
	private GcmContractDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}