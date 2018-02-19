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

import com.stpl.app.exception.NoSuchGcmCompanyDetailsException;
import com.stpl.app.model.GcmCompanyDetails;
import com.stpl.app.service.GcmCompanyDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.GcmCompanyDetailsPersistence;
import com.stpl.app.service.persistence.GcmCompanyDetailsUtil;

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
public class GcmCompanyDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = GcmCompanyDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<GcmCompanyDetails> iterator = _gcmCompanyDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		GcmCompanyDetails gcmCompanyDetails = _persistence.create(pk);

		Assert.assertNotNull(gcmCompanyDetails);

		Assert.assertEquals(gcmCompanyDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		GcmCompanyDetails newGcmCompanyDetails = addGcmCompanyDetails();

		_persistence.remove(newGcmCompanyDetails);

		GcmCompanyDetails existingGcmCompanyDetails = _persistence.fetchByPrimaryKey(newGcmCompanyDetails.getPrimaryKey());

		Assert.assertNull(existingGcmCompanyDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addGcmCompanyDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		GcmCompanyDetails newGcmCompanyDetails = _persistence.create(pk);

		newGcmCompanyDetails.setCheckRecord(RandomTestUtil.randomBoolean());

		newGcmCompanyDetails.setUserId(RandomTestUtil.nextInt());

		newGcmCompanyDetails.setModuleName(RandomTestUtil.randomString());

		newGcmCompanyDetails.setCompanyStringId(RandomTestUtil.randomString());

		newGcmCompanyDetails.setCfpDetailsTradeClass(RandomTestUtil.randomString());

		newGcmCompanyDetails.setCompanyName(RandomTestUtil.randomString());

		newGcmCompanyDetails.setModifiedDate(RandomTestUtil.nextDate());

		newGcmCompanyDetails.setItemCfpDetailsSid(RandomTestUtil.nextInt());

		newGcmCompanyDetails.setCreatedDate(RandomTestUtil.nextDate());

		newGcmCompanyDetails.setCreatedBy(RandomTestUtil.nextInt());

		newGcmCompanyDetails.setCompanyStartDate(RandomTestUtil.nextDate());

		newGcmCompanyDetails.setCompanyNo(RandomTestUtil.randomString());

		newGcmCompanyDetails.setCompanyStatus(RandomTestUtil.randomString());

		newGcmCompanyDetails.setSessionId(RandomTestUtil.randomString());

		newGcmCompanyDetails.setCompanyEndDate(RandomTestUtil.nextDate());

		newGcmCompanyDetails.setCfpDetailsStartDate(RandomTestUtil.nextDate());

		newGcmCompanyDetails.setOperation(RandomTestUtil.randomString());

		newGcmCompanyDetails.setCfpModelSid(RandomTestUtil.nextInt());

		newGcmCompanyDetails.setModifiedBy(RandomTestUtil.nextInt());

		newGcmCompanyDetails.setSubModuleName(RandomTestUtil.randomString());

		newGcmCompanyDetails.setCfpDetailsEndDate(RandomTestUtil.nextDate());

		newGcmCompanyDetails.setCompanyStatusSid(RandomTestUtil.nextInt());

		newGcmCompanyDetails.setCompanyMasterSid(RandomTestUtil.nextInt());

		_gcmCompanyDetailses.add(_persistence.update(newGcmCompanyDetails));

		GcmCompanyDetails existingGcmCompanyDetails = _persistence.findByPrimaryKey(newGcmCompanyDetails.getPrimaryKey());

		Assert.assertEquals(existingGcmCompanyDetails.getCheckRecord(),
			newGcmCompanyDetails.getCheckRecord());
		Assert.assertEquals(existingGcmCompanyDetails.getUserId(),
			newGcmCompanyDetails.getUserId());
		Assert.assertEquals(existingGcmCompanyDetails.getModuleName(),
			newGcmCompanyDetails.getModuleName());
		Assert.assertEquals(existingGcmCompanyDetails.getCompanyStringId(),
			newGcmCompanyDetails.getCompanyStringId());
		Assert.assertEquals(existingGcmCompanyDetails.getCfpDetailsTradeClass(),
			newGcmCompanyDetails.getCfpDetailsTradeClass());
		Assert.assertEquals(existingGcmCompanyDetails.getCompanyName(),
			newGcmCompanyDetails.getCompanyName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingGcmCompanyDetails.getModifiedDate()),
			Time.getShortTimestamp(newGcmCompanyDetails.getModifiedDate()));
		Assert.assertEquals(existingGcmCompanyDetails.getGcmCompanyDetailsSid(),
			newGcmCompanyDetails.getGcmCompanyDetailsSid());
		Assert.assertEquals(existingGcmCompanyDetails.getItemCfpDetailsSid(),
			newGcmCompanyDetails.getItemCfpDetailsSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingGcmCompanyDetails.getCreatedDate()),
			Time.getShortTimestamp(newGcmCompanyDetails.getCreatedDate()));
		Assert.assertEquals(existingGcmCompanyDetails.getCreatedBy(),
			newGcmCompanyDetails.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingGcmCompanyDetails.getCompanyStartDate()),
			Time.getShortTimestamp(newGcmCompanyDetails.getCompanyStartDate()));
		Assert.assertEquals(existingGcmCompanyDetails.getCompanyNo(),
			newGcmCompanyDetails.getCompanyNo());
		Assert.assertEquals(existingGcmCompanyDetails.getCompanyStatus(),
			newGcmCompanyDetails.getCompanyStatus());
		Assert.assertEquals(existingGcmCompanyDetails.getSessionId(),
			newGcmCompanyDetails.getSessionId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingGcmCompanyDetails.getCompanyEndDate()),
			Time.getShortTimestamp(newGcmCompanyDetails.getCompanyEndDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingGcmCompanyDetails.getCfpDetailsStartDate()),
			Time.getShortTimestamp(
				newGcmCompanyDetails.getCfpDetailsStartDate()));
		Assert.assertEquals(existingGcmCompanyDetails.getOperation(),
			newGcmCompanyDetails.getOperation());
		Assert.assertEquals(existingGcmCompanyDetails.getCfpModelSid(),
			newGcmCompanyDetails.getCfpModelSid());
		Assert.assertEquals(existingGcmCompanyDetails.getModifiedBy(),
			newGcmCompanyDetails.getModifiedBy());
		Assert.assertEquals(existingGcmCompanyDetails.getSubModuleName(),
			newGcmCompanyDetails.getSubModuleName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingGcmCompanyDetails.getCfpDetailsEndDate()),
			Time.getShortTimestamp(newGcmCompanyDetails.getCfpDetailsEndDate()));
		Assert.assertEquals(existingGcmCompanyDetails.getCompanyStatusSid(),
			newGcmCompanyDetails.getCompanyStatusSid());
		Assert.assertEquals(existingGcmCompanyDetails.getCompanyMasterSid(),
			newGcmCompanyDetails.getCompanyMasterSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		GcmCompanyDetails newGcmCompanyDetails = addGcmCompanyDetails();

		GcmCompanyDetails existingGcmCompanyDetails = _persistence.findByPrimaryKey(newGcmCompanyDetails.getPrimaryKey());

		Assert.assertEquals(existingGcmCompanyDetails, newGcmCompanyDetails);
	}

	@Test(expected = NoSuchGcmCompanyDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<GcmCompanyDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("GCM_COMPANY_DETAILS",
			"checkRecord", true, "userId", true, "moduleName", true,
			"companyStringId", true, "cfpDetailsTradeClass", true,
			"companyName", true, "modifiedDate", true, "gcmCompanyDetailsSid",
			true, "itemCfpDetailsSid", true, "createdDate", true, "createdBy",
			true, "companyStartDate", true, "companyNo", true, "companyStatus",
			true, "sessionId", true, "companyEndDate", true,
			"cfpDetailsStartDate", true, "operation", true, "cfpModelSid",
			true, "modifiedBy", true, "subModuleName", true,
			"cfpDetailsEndDate", true, "companyStatusSid", true,
			"companyMasterSid", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		GcmCompanyDetails newGcmCompanyDetails = addGcmCompanyDetails();

		GcmCompanyDetails existingGcmCompanyDetails = _persistence.fetchByPrimaryKey(newGcmCompanyDetails.getPrimaryKey());

		Assert.assertEquals(existingGcmCompanyDetails, newGcmCompanyDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		GcmCompanyDetails missingGcmCompanyDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingGcmCompanyDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		GcmCompanyDetails newGcmCompanyDetails1 = addGcmCompanyDetails();
		GcmCompanyDetails newGcmCompanyDetails2 = addGcmCompanyDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newGcmCompanyDetails1.getPrimaryKey());
		primaryKeys.add(newGcmCompanyDetails2.getPrimaryKey());

		Map<Serializable, GcmCompanyDetails> gcmCompanyDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, gcmCompanyDetailses.size());
		Assert.assertEquals(newGcmCompanyDetails1,
			gcmCompanyDetailses.get(newGcmCompanyDetails1.getPrimaryKey()));
		Assert.assertEquals(newGcmCompanyDetails2,
			gcmCompanyDetailses.get(newGcmCompanyDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, GcmCompanyDetails> gcmCompanyDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(gcmCompanyDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		GcmCompanyDetails newGcmCompanyDetails = addGcmCompanyDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newGcmCompanyDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, GcmCompanyDetails> gcmCompanyDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, gcmCompanyDetailses.size());
		Assert.assertEquals(newGcmCompanyDetails,
			gcmCompanyDetailses.get(newGcmCompanyDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, GcmCompanyDetails> gcmCompanyDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(gcmCompanyDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		GcmCompanyDetails newGcmCompanyDetails = addGcmCompanyDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newGcmCompanyDetails.getPrimaryKey());

		Map<Serializable, GcmCompanyDetails> gcmCompanyDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, gcmCompanyDetailses.size());
		Assert.assertEquals(newGcmCompanyDetails,
			gcmCompanyDetailses.get(newGcmCompanyDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = GcmCompanyDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<GcmCompanyDetails>() {
				@Override
				public void performAction(GcmCompanyDetails gcmCompanyDetails) {
					Assert.assertNotNull(gcmCompanyDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		GcmCompanyDetails newGcmCompanyDetails = addGcmCompanyDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(GcmCompanyDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("gcmCompanyDetailsSid",
				newGcmCompanyDetails.getGcmCompanyDetailsSid()));

		List<GcmCompanyDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		GcmCompanyDetails existingGcmCompanyDetails = result.get(0);

		Assert.assertEquals(existingGcmCompanyDetails, newGcmCompanyDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(GcmCompanyDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("gcmCompanyDetailsSid",
				RandomTestUtil.nextInt()));

		List<GcmCompanyDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		GcmCompanyDetails newGcmCompanyDetails = addGcmCompanyDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(GcmCompanyDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"gcmCompanyDetailsSid"));

		Object newGcmCompanyDetailsSid = newGcmCompanyDetails.getGcmCompanyDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("gcmCompanyDetailsSid",
				new Object[] { newGcmCompanyDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingGcmCompanyDetailsSid = result.get(0);

		Assert.assertEquals(existingGcmCompanyDetailsSid,
			newGcmCompanyDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(GcmCompanyDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"gcmCompanyDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("gcmCompanyDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected GcmCompanyDetails addGcmCompanyDetails()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		GcmCompanyDetails gcmCompanyDetails = _persistence.create(pk);

		gcmCompanyDetails.setCheckRecord(RandomTestUtil.randomBoolean());

		gcmCompanyDetails.setUserId(RandomTestUtil.nextInt());

		gcmCompanyDetails.setModuleName(RandomTestUtil.randomString());

		gcmCompanyDetails.setCompanyStringId(RandomTestUtil.randomString());

		gcmCompanyDetails.setCfpDetailsTradeClass(RandomTestUtil.randomString());

		gcmCompanyDetails.setCompanyName(RandomTestUtil.randomString());

		gcmCompanyDetails.setModifiedDate(RandomTestUtil.nextDate());

		gcmCompanyDetails.setItemCfpDetailsSid(RandomTestUtil.nextInt());

		gcmCompanyDetails.setCreatedDate(RandomTestUtil.nextDate());

		gcmCompanyDetails.setCreatedBy(RandomTestUtil.nextInt());

		gcmCompanyDetails.setCompanyStartDate(RandomTestUtil.nextDate());

		gcmCompanyDetails.setCompanyNo(RandomTestUtil.randomString());

		gcmCompanyDetails.setCompanyStatus(RandomTestUtil.randomString());

		gcmCompanyDetails.setSessionId(RandomTestUtil.randomString());

		gcmCompanyDetails.setCompanyEndDate(RandomTestUtil.nextDate());

		gcmCompanyDetails.setCfpDetailsStartDate(RandomTestUtil.nextDate());

		gcmCompanyDetails.setOperation(RandomTestUtil.randomString());

		gcmCompanyDetails.setCfpModelSid(RandomTestUtil.nextInt());

		gcmCompanyDetails.setModifiedBy(RandomTestUtil.nextInt());

		gcmCompanyDetails.setSubModuleName(RandomTestUtil.randomString());

		gcmCompanyDetails.setCfpDetailsEndDate(RandomTestUtil.nextDate());

		gcmCompanyDetails.setCompanyStatusSid(RandomTestUtil.nextInt());

		gcmCompanyDetails.setCompanyMasterSid(RandomTestUtil.nextInt());

		_gcmCompanyDetailses.add(_persistence.update(gcmCompanyDetails));

		return gcmCompanyDetails;
	}

	private List<GcmCompanyDetails> _gcmCompanyDetailses = new ArrayList<GcmCompanyDetails>();
	private GcmCompanyDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}