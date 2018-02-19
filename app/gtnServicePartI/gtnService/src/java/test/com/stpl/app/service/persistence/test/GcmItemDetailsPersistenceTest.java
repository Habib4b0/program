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

import com.stpl.app.exception.NoSuchGcmItemDetailsException;
import com.stpl.app.model.GcmItemDetails;
import com.stpl.app.service.GcmItemDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.GcmItemDetailsPersistence;
import com.stpl.app.service.persistence.GcmItemDetailsUtil;

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
public class GcmItemDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = GcmItemDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<GcmItemDetails> iterator = _gcmItemDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		GcmItemDetails gcmItemDetails = _persistence.create(pk);

		Assert.assertNotNull(gcmItemDetails);

		Assert.assertEquals(gcmItemDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		GcmItemDetails newGcmItemDetails = addGcmItemDetails();

		_persistence.remove(newGcmItemDetails);

		GcmItemDetails existingGcmItemDetails = _persistence.fetchByPrimaryKey(newGcmItemDetails.getPrimaryKey());

		Assert.assertNull(existingGcmItemDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addGcmItemDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		GcmItemDetails newGcmItemDetails = _persistence.create(pk);

		newGcmItemDetails.setIfpDetailsEndDate(RandomTestUtil.nextDate());

		newGcmItemDetails.setItemStatus(RandomTestUtil.randomString());

		newGcmItemDetails.setCheckRecord(RandomTestUtil.randomBoolean());

		newGcmItemDetails.setIfpDetailsStartDate(RandomTestUtil.nextDate());

		newGcmItemDetails.setUserId(RandomTestUtil.nextInt());

		newGcmItemDetails.setItemMasterSid(RandomTestUtil.nextInt());

		newGcmItemDetails.setItemEndDate(RandomTestUtil.nextDate());

		newGcmItemDetails.setItemIfpDetailsSid(RandomTestUtil.nextInt());

		newGcmItemDetails.setItemId(RandomTestUtil.randomString());

		newGcmItemDetails.setBrandName(RandomTestUtil.randomString());

		newGcmItemDetails.setModifiedDate(RandomTestUtil.nextDate());

		newGcmItemDetails.setCreatedDate(RandomTestUtil.nextDate());

		newGcmItemDetails.setCreatedBy(RandomTestUtil.nextInt());

		newGcmItemDetails.setItemStartDate(RandomTestUtil.nextDate());

		newGcmItemDetails.setSessionId(RandomTestUtil.randomString());

		newGcmItemDetails.setItemName(RandomTestUtil.randomString());

		newGcmItemDetails.setOperation(RandomTestUtil.randomString());

		newGcmItemDetails.setModifiedBy(RandomTestUtil.nextInt());

		newGcmItemDetails.setInboundStatus(RandomTestUtil.randomString());

		newGcmItemDetails.setItemStatusSid(RandomTestUtil.nextInt());

		newGcmItemDetails.setItemNo(RandomTestUtil.randomString());

		newGcmItemDetails.setIfpModelSid(RandomTestUtil.nextInt());

		newGcmItemDetails.setTheraputicClass(RandomTestUtil.randomString());

		_gcmItemDetailses.add(_persistence.update(newGcmItemDetails));

		GcmItemDetails existingGcmItemDetails = _persistence.findByPrimaryKey(newGcmItemDetails.getPrimaryKey());

		Assert.assertEquals(Time.getShortTimestamp(
				existingGcmItemDetails.getIfpDetailsEndDate()),
			Time.getShortTimestamp(newGcmItemDetails.getIfpDetailsEndDate()));
		Assert.assertEquals(existingGcmItemDetails.getItemStatus(),
			newGcmItemDetails.getItemStatus());
		Assert.assertEquals(existingGcmItemDetails.getCheckRecord(),
			newGcmItemDetails.getCheckRecord());
		Assert.assertEquals(Time.getShortTimestamp(
				existingGcmItemDetails.getIfpDetailsStartDate()),
			Time.getShortTimestamp(newGcmItemDetails.getIfpDetailsStartDate()));
		Assert.assertEquals(existingGcmItemDetails.getUserId(),
			newGcmItemDetails.getUserId());
		Assert.assertEquals(existingGcmItemDetails.getItemMasterSid(),
			newGcmItemDetails.getItemMasterSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingGcmItemDetails.getItemEndDate()),
			Time.getShortTimestamp(newGcmItemDetails.getItemEndDate()));
		Assert.assertEquals(existingGcmItemDetails.getGcmItemDetailsSid(),
			newGcmItemDetails.getGcmItemDetailsSid());
		Assert.assertEquals(existingGcmItemDetails.getItemIfpDetailsSid(),
			newGcmItemDetails.getItemIfpDetailsSid());
		Assert.assertEquals(existingGcmItemDetails.getItemId(),
			newGcmItemDetails.getItemId());
		Assert.assertEquals(existingGcmItemDetails.getBrandName(),
			newGcmItemDetails.getBrandName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingGcmItemDetails.getModifiedDate()),
			Time.getShortTimestamp(newGcmItemDetails.getModifiedDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingGcmItemDetails.getCreatedDate()),
			Time.getShortTimestamp(newGcmItemDetails.getCreatedDate()));
		Assert.assertEquals(existingGcmItemDetails.getCreatedBy(),
			newGcmItemDetails.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingGcmItemDetails.getItemStartDate()),
			Time.getShortTimestamp(newGcmItemDetails.getItemStartDate()));
		Assert.assertEquals(existingGcmItemDetails.getSessionId(),
			newGcmItemDetails.getSessionId());
		Assert.assertEquals(existingGcmItemDetails.getItemName(),
			newGcmItemDetails.getItemName());
		Assert.assertEquals(existingGcmItemDetails.getOperation(),
			newGcmItemDetails.getOperation());
		Assert.assertEquals(existingGcmItemDetails.getModifiedBy(),
			newGcmItemDetails.getModifiedBy());
		Assert.assertEquals(existingGcmItemDetails.getInboundStatus(),
			newGcmItemDetails.getInboundStatus());
		Assert.assertEquals(existingGcmItemDetails.getItemStatusSid(),
			newGcmItemDetails.getItemStatusSid());
		Assert.assertEquals(existingGcmItemDetails.getItemNo(),
			newGcmItemDetails.getItemNo());
		Assert.assertEquals(existingGcmItemDetails.getIfpModelSid(),
			newGcmItemDetails.getIfpModelSid());
		Assert.assertEquals(existingGcmItemDetails.getTheraputicClass(),
			newGcmItemDetails.getTheraputicClass());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		GcmItemDetails newGcmItemDetails = addGcmItemDetails();

		GcmItemDetails existingGcmItemDetails = _persistence.findByPrimaryKey(newGcmItemDetails.getPrimaryKey());

		Assert.assertEquals(existingGcmItemDetails, newGcmItemDetails);
	}

	@Test(expected = NoSuchGcmItemDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<GcmItemDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("GCM_ITEM_DETAILS",
			"ifpDetailsEndDate", true, "itemStatus", true, "checkRecord", true,
			"ifpDetailsStartDate", true, "userId", true, "itemMasterSid", true,
			"itemEndDate", true, "gcmItemDetailsSid", true,
			"itemIfpDetailsSid", true, "itemId", true, "brandName", true,
			"modifiedDate", true, "createdDate", true, "createdBy", true,
			"itemStartDate", true, "sessionId", true, "itemName", true,
			"operation", true, "modifiedBy", true, "inboundStatus", true,
			"itemStatusSid", true, "itemNo", true, "ifpModelSid", true,
			"theraputicClass", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		GcmItemDetails newGcmItemDetails = addGcmItemDetails();

		GcmItemDetails existingGcmItemDetails = _persistence.fetchByPrimaryKey(newGcmItemDetails.getPrimaryKey());

		Assert.assertEquals(existingGcmItemDetails, newGcmItemDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		GcmItemDetails missingGcmItemDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingGcmItemDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		GcmItemDetails newGcmItemDetails1 = addGcmItemDetails();
		GcmItemDetails newGcmItemDetails2 = addGcmItemDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newGcmItemDetails1.getPrimaryKey());
		primaryKeys.add(newGcmItemDetails2.getPrimaryKey());

		Map<Serializable, GcmItemDetails> gcmItemDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, gcmItemDetailses.size());
		Assert.assertEquals(newGcmItemDetails1,
			gcmItemDetailses.get(newGcmItemDetails1.getPrimaryKey()));
		Assert.assertEquals(newGcmItemDetails2,
			gcmItemDetailses.get(newGcmItemDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, GcmItemDetails> gcmItemDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(gcmItemDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		GcmItemDetails newGcmItemDetails = addGcmItemDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newGcmItemDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, GcmItemDetails> gcmItemDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, gcmItemDetailses.size());
		Assert.assertEquals(newGcmItemDetails,
			gcmItemDetailses.get(newGcmItemDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, GcmItemDetails> gcmItemDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(gcmItemDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		GcmItemDetails newGcmItemDetails = addGcmItemDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newGcmItemDetails.getPrimaryKey());

		Map<Serializable, GcmItemDetails> gcmItemDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, gcmItemDetailses.size());
		Assert.assertEquals(newGcmItemDetails,
			gcmItemDetailses.get(newGcmItemDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = GcmItemDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<GcmItemDetails>() {
				@Override
				public void performAction(GcmItemDetails gcmItemDetails) {
					Assert.assertNotNull(gcmItemDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		GcmItemDetails newGcmItemDetails = addGcmItemDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(GcmItemDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("gcmItemDetailsSid",
				newGcmItemDetails.getGcmItemDetailsSid()));

		List<GcmItemDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		GcmItemDetails existingGcmItemDetails = result.get(0);

		Assert.assertEquals(existingGcmItemDetails, newGcmItemDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(GcmItemDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("gcmItemDetailsSid",
				RandomTestUtil.nextInt()));

		List<GcmItemDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		GcmItemDetails newGcmItemDetails = addGcmItemDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(GcmItemDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"gcmItemDetailsSid"));

		Object newGcmItemDetailsSid = newGcmItemDetails.getGcmItemDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("gcmItemDetailsSid",
				new Object[] { newGcmItemDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingGcmItemDetailsSid = result.get(0);

		Assert.assertEquals(existingGcmItemDetailsSid, newGcmItemDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(GcmItemDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"gcmItemDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("gcmItemDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected GcmItemDetails addGcmItemDetails() throws Exception {
		int pk = RandomTestUtil.nextInt();

		GcmItemDetails gcmItemDetails = _persistence.create(pk);

		gcmItemDetails.setIfpDetailsEndDate(RandomTestUtil.nextDate());

		gcmItemDetails.setItemStatus(RandomTestUtil.randomString());

		gcmItemDetails.setCheckRecord(RandomTestUtil.randomBoolean());

		gcmItemDetails.setIfpDetailsStartDate(RandomTestUtil.nextDate());

		gcmItemDetails.setUserId(RandomTestUtil.nextInt());

		gcmItemDetails.setItemMasterSid(RandomTestUtil.nextInt());

		gcmItemDetails.setItemEndDate(RandomTestUtil.nextDate());

		gcmItemDetails.setItemIfpDetailsSid(RandomTestUtil.nextInt());

		gcmItemDetails.setItemId(RandomTestUtil.randomString());

		gcmItemDetails.setBrandName(RandomTestUtil.randomString());

		gcmItemDetails.setModifiedDate(RandomTestUtil.nextDate());

		gcmItemDetails.setCreatedDate(RandomTestUtil.nextDate());

		gcmItemDetails.setCreatedBy(RandomTestUtil.nextInt());

		gcmItemDetails.setItemStartDate(RandomTestUtil.nextDate());

		gcmItemDetails.setSessionId(RandomTestUtil.randomString());

		gcmItemDetails.setItemName(RandomTestUtil.randomString());

		gcmItemDetails.setOperation(RandomTestUtil.randomString());

		gcmItemDetails.setModifiedBy(RandomTestUtil.nextInt());

		gcmItemDetails.setInboundStatus(RandomTestUtil.randomString());

		gcmItemDetails.setItemStatusSid(RandomTestUtil.nextInt());

		gcmItemDetails.setItemNo(RandomTestUtil.randomString());

		gcmItemDetails.setIfpModelSid(RandomTestUtil.nextInt());

		gcmItemDetails.setTheraputicClass(RandomTestUtil.randomString());

		_gcmItemDetailses.add(_persistence.update(gcmItemDetails));

		return gcmItemDetails;
	}

	private List<GcmItemDetails> _gcmItemDetailses = new ArrayList<GcmItemDetails>();
	private GcmItemDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}