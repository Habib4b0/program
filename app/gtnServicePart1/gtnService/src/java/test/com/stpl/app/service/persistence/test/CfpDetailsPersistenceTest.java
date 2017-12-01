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

import com.stpl.app.exception.NoSuchCfpDetailsException;
import com.stpl.app.model.CfpDetails;
import com.stpl.app.service.CfpDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.CfpDetailsPersistence;
import com.stpl.app.service.persistence.CfpDetailsUtil;

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
public class CfpDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = CfpDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CfpDetails> iterator = _cfpDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CfpDetails cfpDetails = _persistence.create(pk);

		Assert.assertNotNull(cfpDetails);

		Assert.assertEquals(cfpDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CfpDetails newCfpDetails = addCfpDetails();

		_persistence.remove(newCfpDetails);

		CfpDetails existingCfpDetails = _persistence.fetchByPrimaryKey(newCfpDetails.getPrimaryKey());

		Assert.assertNull(existingCfpDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCfpDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CfpDetails newCfpDetails = _persistence.create(pk);

		newCfpDetails.setCreatedBy(RandomTestUtil.nextInt());

		newCfpDetails.setCompanyCfpAttachedStatus(RandomTestUtil.nextInt());

		newCfpDetails.setTradeClass(RandomTestUtil.nextInt());

		newCfpDetails.setTradeClassEndDate(RandomTestUtil.nextDate());

		newCfpDetails.setModifiedBy(RandomTestUtil.nextInt());

		newCfpDetails.setCompanyStartDate(RandomTestUtil.nextDate());

		newCfpDetails.setTradeClassStartDate(RandomTestUtil.nextDate());

		newCfpDetails.setCreatedDate(RandomTestUtil.nextDate());

		newCfpDetails.setCompanyEndDate(RandomTestUtil.nextDate());

		newCfpDetails.setCompanyMasterSid(RandomTestUtil.nextInt());

		newCfpDetails.setCompanyCfpAttachedDate(RandomTestUtil.nextDate());

		newCfpDetails.setCfpModelSid(RandomTestUtil.nextInt());

		newCfpDetails.setBatchId(RandomTestUtil.randomString());

		newCfpDetails.setModifiedDate(RandomTestUtil.nextDate());

		newCfpDetails.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newCfpDetails.setSource(RandomTestUtil.randomString());

		newCfpDetails.setInboundStatus(RandomTestUtil.randomString());

		_cfpDetailses.add(_persistence.update(newCfpDetails));

		CfpDetails existingCfpDetails = _persistence.findByPrimaryKey(newCfpDetails.getPrimaryKey());

		Assert.assertEquals(existingCfpDetails.getCreatedBy(),
			newCfpDetails.getCreatedBy());
		Assert.assertEquals(existingCfpDetails.getCompanyCfpAttachedStatus(),
			newCfpDetails.getCompanyCfpAttachedStatus());
		Assert.assertEquals(existingCfpDetails.getTradeClass(),
			newCfpDetails.getTradeClass());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCfpDetails.getTradeClassEndDate()),
			Time.getShortTimestamp(newCfpDetails.getTradeClassEndDate()));
		Assert.assertEquals(existingCfpDetails.getModifiedBy(),
			newCfpDetails.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCfpDetails.getCompanyStartDate()),
			Time.getShortTimestamp(newCfpDetails.getCompanyStartDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingCfpDetails.getTradeClassStartDate()),
			Time.getShortTimestamp(newCfpDetails.getTradeClassStartDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingCfpDetails.getCreatedDate()),
			Time.getShortTimestamp(newCfpDetails.getCreatedDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingCfpDetails.getCompanyEndDate()),
			Time.getShortTimestamp(newCfpDetails.getCompanyEndDate()));
		Assert.assertEquals(existingCfpDetails.getCompanyMasterSid(),
			newCfpDetails.getCompanyMasterSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCfpDetails.getCompanyCfpAttachedDate()),
			Time.getShortTimestamp(newCfpDetails.getCompanyCfpAttachedDate()));
		Assert.assertEquals(existingCfpDetails.getCfpModelSid(),
			newCfpDetails.getCfpModelSid());
		Assert.assertEquals(existingCfpDetails.getBatchId(),
			newCfpDetails.getBatchId());
		Assert.assertEquals(existingCfpDetails.getCfpDetailsSid(),
			newCfpDetails.getCfpDetailsSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCfpDetails.getModifiedDate()),
			Time.getShortTimestamp(newCfpDetails.getModifiedDate()));
		Assert.assertEquals(existingCfpDetails.getRecordLockStatus(),
			newCfpDetails.getRecordLockStatus());
		Assert.assertEquals(existingCfpDetails.getSource(),
			newCfpDetails.getSource());
		Assert.assertEquals(existingCfpDetails.getInboundStatus(),
			newCfpDetails.getInboundStatus());
	}

	@Test
	public void testCountByCfpModelSid() throws Exception {
		_persistence.countByCfpModelSid(RandomTestUtil.nextInt());

		_persistence.countByCfpModelSid(0);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CfpDetails newCfpDetails = addCfpDetails();

		CfpDetails existingCfpDetails = _persistence.findByPrimaryKey(newCfpDetails.getPrimaryKey());

		Assert.assertEquals(existingCfpDetails, newCfpDetails);
	}

	@Test(expected = NoSuchCfpDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CfpDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CFP_DETAILS", "createdBy",
			true, "companyCfpAttachedStatus", true, "tradeClass", true,
			"tradeClassEndDate", true, "modifiedBy", true, "companyStartDate",
			true, "tradeClassStartDate", true, "createdDate", true,
			"companyEndDate", true, "companyMasterSid", true,
			"companyCfpAttachedDate", true, "cfpModelSid", true, "batchId",
			true, "cfpDetailsSid", true, "modifiedDate", true,
			"recordLockStatus", true, "source", true, "inboundStatus", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CfpDetails newCfpDetails = addCfpDetails();

		CfpDetails existingCfpDetails = _persistence.fetchByPrimaryKey(newCfpDetails.getPrimaryKey());

		Assert.assertEquals(existingCfpDetails, newCfpDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CfpDetails missingCfpDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCfpDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CfpDetails newCfpDetails1 = addCfpDetails();
		CfpDetails newCfpDetails2 = addCfpDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCfpDetails1.getPrimaryKey());
		primaryKeys.add(newCfpDetails2.getPrimaryKey());

		Map<Serializable, CfpDetails> cfpDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, cfpDetailses.size());
		Assert.assertEquals(newCfpDetails1,
			cfpDetailses.get(newCfpDetails1.getPrimaryKey()));
		Assert.assertEquals(newCfpDetails2,
			cfpDetailses.get(newCfpDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CfpDetails> cfpDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cfpDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CfpDetails newCfpDetails = addCfpDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCfpDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CfpDetails> cfpDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cfpDetailses.size());
		Assert.assertEquals(newCfpDetails,
			cfpDetailses.get(newCfpDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CfpDetails> cfpDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cfpDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CfpDetails newCfpDetails = addCfpDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCfpDetails.getPrimaryKey());

		Map<Serializable, CfpDetails> cfpDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cfpDetailses.size());
		Assert.assertEquals(newCfpDetails,
			cfpDetailses.get(newCfpDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CfpDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CfpDetails>() {
				@Override
				public void performAction(CfpDetails cfpDetails) {
					Assert.assertNotNull(cfpDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CfpDetails newCfpDetails = addCfpDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CfpDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("cfpDetailsSid",
				newCfpDetails.getCfpDetailsSid()));

		List<CfpDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CfpDetails existingCfpDetails = result.get(0);

		Assert.assertEquals(existingCfpDetails, newCfpDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CfpDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("cfpDetailsSid",
				RandomTestUtil.nextInt()));

		List<CfpDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CfpDetails newCfpDetails = addCfpDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CfpDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"cfpDetailsSid"));

		Object newCfpDetailsSid = newCfpDetails.getCfpDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("cfpDetailsSid",
				new Object[] { newCfpDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCfpDetailsSid = result.get(0);

		Assert.assertEquals(existingCfpDetailsSid, newCfpDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CfpDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"cfpDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("cfpDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected CfpDetails addCfpDetails() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CfpDetails cfpDetails = _persistence.create(pk);

		cfpDetails.setCreatedBy(RandomTestUtil.nextInt());

		cfpDetails.setCompanyCfpAttachedStatus(RandomTestUtil.nextInt());

		cfpDetails.setTradeClass(RandomTestUtil.nextInt());

		cfpDetails.setTradeClassEndDate(RandomTestUtil.nextDate());

		cfpDetails.setModifiedBy(RandomTestUtil.nextInt());

		cfpDetails.setCompanyStartDate(RandomTestUtil.nextDate());

		cfpDetails.setTradeClassStartDate(RandomTestUtil.nextDate());

		cfpDetails.setCreatedDate(RandomTestUtil.nextDate());

		cfpDetails.setCompanyEndDate(RandomTestUtil.nextDate());

		cfpDetails.setCompanyMasterSid(RandomTestUtil.nextInt());

		cfpDetails.setCompanyCfpAttachedDate(RandomTestUtil.nextDate());

		cfpDetails.setCfpModelSid(RandomTestUtil.nextInt());

		cfpDetails.setBatchId(RandomTestUtil.randomString());

		cfpDetails.setModifiedDate(RandomTestUtil.nextDate());

		cfpDetails.setRecordLockStatus(RandomTestUtil.randomBoolean());

		cfpDetails.setSource(RandomTestUtil.randomString());

		cfpDetails.setInboundStatus(RandomTestUtil.randomString());

		_cfpDetailses.add(_persistence.update(cfpDetails));

		return cfpDetails;
	}

	private List<CfpDetails> _cfpDetailses = new ArrayList<CfpDetails>();
	private CfpDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}