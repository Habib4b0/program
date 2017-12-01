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

import com.stpl.app.exception.NoSuchIfpDetailsException;
import com.stpl.app.model.IfpDetails;
import com.stpl.app.service.IfpDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.IfpDetailsPersistence;
import com.stpl.app.service.persistence.IfpDetailsUtil;

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
public class IfpDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = IfpDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<IfpDetails> iterator = _ifpDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IfpDetails ifpDetails = _persistence.create(pk);

		Assert.assertNotNull(ifpDetails);

		Assert.assertEquals(ifpDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		IfpDetails newIfpDetails = addIfpDetails();

		_persistence.remove(newIfpDetails);

		IfpDetails existingIfpDetails = _persistence.fetchByPrimaryKey(newIfpDetails.getPrimaryKey());

		Assert.assertNull(existingIfpDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addIfpDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IfpDetails newIfpDetails = _persistence.create(pk);

		newIfpDetails.setItemMasterSid(RandomTestUtil.nextInt());

		newIfpDetails.setEndDate(RandomTestUtil.nextDate());

		newIfpDetails.setModifiedDate(RandomTestUtil.nextDate());

		newIfpDetails.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newIfpDetails.setStartDate(RandomTestUtil.nextDate());

		newIfpDetails.setCreatedDate(RandomTestUtil.nextDate());

		newIfpDetails.setSource(RandomTestUtil.randomString());

		newIfpDetails.setCreatedBy(RandomTestUtil.nextInt());

		newIfpDetails.setItemIfpAttachedDate(RandomTestUtil.nextDate());

		newIfpDetails.setBatchId(RandomTestUtil.randomString());

		newIfpDetails.setModifiedBy(RandomTestUtil.nextInt());

		newIfpDetails.setInboundStatus(RandomTestUtil.randomString());

		newIfpDetails.setIfpModelSid(RandomTestUtil.nextInt());

		newIfpDetails.setItemIfpAttachedStatus(RandomTestUtil.nextInt());

		_ifpDetailses.add(_persistence.update(newIfpDetails));

		IfpDetails existingIfpDetails = _persistence.findByPrimaryKey(newIfpDetails.getPrimaryKey());

		Assert.assertEquals(existingIfpDetails.getItemMasterSid(),
			newIfpDetails.getItemMasterSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIfpDetails.getEndDate()),
			Time.getShortTimestamp(newIfpDetails.getEndDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingIfpDetails.getModifiedDate()),
			Time.getShortTimestamp(newIfpDetails.getModifiedDate()));
		Assert.assertEquals(existingIfpDetails.getRecordLockStatus(),
			newIfpDetails.getRecordLockStatus());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIfpDetails.getStartDate()),
			Time.getShortTimestamp(newIfpDetails.getStartDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingIfpDetails.getCreatedDate()),
			Time.getShortTimestamp(newIfpDetails.getCreatedDate()));
		Assert.assertEquals(existingIfpDetails.getSource(),
			newIfpDetails.getSource());
		Assert.assertEquals(existingIfpDetails.getCreatedBy(),
			newIfpDetails.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIfpDetails.getItemIfpAttachedDate()),
			Time.getShortTimestamp(newIfpDetails.getItemIfpAttachedDate()));
		Assert.assertEquals(existingIfpDetails.getBatchId(),
			newIfpDetails.getBatchId());
		Assert.assertEquals(existingIfpDetails.getModifiedBy(),
			newIfpDetails.getModifiedBy());
		Assert.assertEquals(existingIfpDetails.getInboundStatus(),
			newIfpDetails.getInboundStatus());
		Assert.assertEquals(existingIfpDetails.getIfpDetailsSid(),
			newIfpDetails.getIfpDetailsSid());
		Assert.assertEquals(existingIfpDetails.getIfpModelSid(),
			newIfpDetails.getIfpModelSid());
		Assert.assertEquals(existingIfpDetails.getItemIfpAttachedStatus(),
			newIfpDetails.getItemIfpAttachedStatus());
	}

	@Test
	public void testCountByItemFamilyPlanDetails() throws Exception {
		_persistence.countByItemFamilyPlanDetails(RandomTestUtil.nextInt());

		_persistence.countByItemFamilyPlanDetails(0);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		IfpDetails newIfpDetails = addIfpDetails();

		IfpDetails existingIfpDetails = _persistence.findByPrimaryKey(newIfpDetails.getPrimaryKey());

		Assert.assertEquals(existingIfpDetails, newIfpDetails);
	}

	@Test(expected = NoSuchIfpDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<IfpDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("ifp_details",
			"itemMasterSid", true, "endDate", true, "modifiedDate", true,
			"recordLockStatus", true, "startDate", true, "createdDate", true,
			"source", true, "createdBy", true, "itemIfpAttachedDate", true,
			"batchId", true, "modifiedBy", true, "inboundStatus", true,
			"ifpDetailsSid", true, "ifpModelSid", true,
			"itemIfpAttachedStatus", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		IfpDetails newIfpDetails = addIfpDetails();

		IfpDetails existingIfpDetails = _persistence.fetchByPrimaryKey(newIfpDetails.getPrimaryKey());

		Assert.assertEquals(existingIfpDetails, newIfpDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IfpDetails missingIfpDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingIfpDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		IfpDetails newIfpDetails1 = addIfpDetails();
		IfpDetails newIfpDetails2 = addIfpDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIfpDetails1.getPrimaryKey());
		primaryKeys.add(newIfpDetails2.getPrimaryKey());

		Map<Serializable, IfpDetails> ifpDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ifpDetailses.size());
		Assert.assertEquals(newIfpDetails1,
			ifpDetailses.get(newIfpDetails1.getPrimaryKey()));
		Assert.assertEquals(newIfpDetails2,
			ifpDetailses.get(newIfpDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, IfpDetails> ifpDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ifpDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		IfpDetails newIfpDetails = addIfpDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIfpDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, IfpDetails> ifpDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ifpDetailses.size());
		Assert.assertEquals(newIfpDetails,
			ifpDetailses.get(newIfpDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, IfpDetails> ifpDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ifpDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		IfpDetails newIfpDetails = addIfpDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIfpDetails.getPrimaryKey());

		Map<Serializable, IfpDetails> ifpDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ifpDetailses.size());
		Assert.assertEquals(newIfpDetails,
			ifpDetailses.get(newIfpDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = IfpDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<IfpDetails>() {
				@Override
				public void performAction(IfpDetails ifpDetails) {
					Assert.assertNotNull(ifpDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		IfpDetails newIfpDetails = addIfpDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IfpDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ifpDetailsSid",
				newIfpDetails.getIfpDetailsSid()));

		List<IfpDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		IfpDetails existingIfpDetails = result.get(0);

		Assert.assertEquals(existingIfpDetails, newIfpDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IfpDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ifpDetailsSid",
				RandomTestUtil.nextInt()));

		List<IfpDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		IfpDetails newIfpDetails = addIfpDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IfpDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ifpDetailsSid"));

		Object newIfpDetailsSid = newIfpDetails.getIfpDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("ifpDetailsSid",
				new Object[] { newIfpDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingIfpDetailsSid = result.get(0);

		Assert.assertEquals(existingIfpDetailsSid, newIfpDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IfpDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ifpDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("ifpDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected IfpDetails addIfpDetails() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IfpDetails ifpDetails = _persistence.create(pk);

		ifpDetails.setItemMasterSid(RandomTestUtil.nextInt());

		ifpDetails.setEndDate(RandomTestUtil.nextDate());

		ifpDetails.setModifiedDate(RandomTestUtil.nextDate());

		ifpDetails.setRecordLockStatus(RandomTestUtil.randomBoolean());

		ifpDetails.setStartDate(RandomTestUtil.nextDate());

		ifpDetails.setCreatedDate(RandomTestUtil.nextDate());

		ifpDetails.setSource(RandomTestUtil.randomString());

		ifpDetails.setCreatedBy(RandomTestUtil.nextInt());

		ifpDetails.setItemIfpAttachedDate(RandomTestUtil.nextDate());

		ifpDetails.setBatchId(RandomTestUtil.randomString());

		ifpDetails.setModifiedBy(RandomTestUtil.nextInt());

		ifpDetails.setInboundStatus(RandomTestUtil.randomString());

		ifpDetails.setIfpModelSid(RandomTestUtil.nextInt());

		ifpDetails.setItemIfpAttachedStatus(RandomTestUtil.nextInt());

		_ifpDetailses.add(_persistence.update(ifpDetails));

		return ifpDetails;
	}

	private List<IfpDetails> _ifpDetailses = new ArrayList<IfpDetails>();
	private IfpDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}