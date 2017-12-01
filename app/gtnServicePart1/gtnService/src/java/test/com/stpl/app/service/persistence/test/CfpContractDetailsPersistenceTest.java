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

import com.stpl.app.exception.NoSuchCfpContractDetailsException;
import com.stpl.app.model.CfpContractDetails;
import com.stpl.app.service.CfpContractDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.CfpContractDetailsPersistence;
import com.stpl.app.service.persistence.CfpContractDetailsUtil;

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
public class CfpContractDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = CfpContractDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CfpContractDetails> iterator = _cfpContractDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CfpContractDetails cfpContractDetails = _persistence.create(pk);

		Assert.assertNotNull(cfpContractDetails);

		Assert.assertEquals(cfpContractDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CfpContractDetails newCfpContractDetails = addCfpContractDetails();

		_persistence.remove(newCfpContractDetails);

		CfpContractDetails existingCfpContractDetails = _persistence.fetchByPrimaryKey(newCfpContractDetails.getPrimaryKey());

		Assert.assertNull(existingCfpContractDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCfpContractDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CfpContractDetails newCfpContractDetails = _persistence.create(pk);

		newCfpContractDetails.setCreatedBy(RandomTestUtil.nextInt());

		newCfpContractDetails.setTradeClass(RandomTestUtil.nextInt());

		newCfpContractDetails.setTradeClassEndDate(RandomTestUtil.nextDate());

		newCfpContractDetails.setCfpContractSid(RandomTestUtil.nextInt());

		newCfpContractDetails.setModifiedBy(RandomTestUtil.nextInt());

		newCfpContractDetails.setCompanyStartDate(RandomTestUtil.nextDate());

		newCfpContractDetails.setTradeClassStartDate(RandomTestUtil.nextDate());

		newCfpContractDetails.setCreatedDate(RandomTestUtil.nextDate());

		newCfpContractDetails.setCfpContractAttachedDate(RandomTestUtil.nextDate());

		newCfpContractDetails.setCompanyEndDate(RandomTestUtil.nextDate());

		newCfpContractDetails.setCompanyMasterSid(RandomTestUtil.nextInt());

		newCfpContractDetails.setBatchId(RandomTestUtil.randomString());

		newCfpContractDetails.setModifiedDate(RandomTestUtil.nextDate());

		newCfpContractDetails.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newCfpContractDetails.setSource(RandomTestUtil.randomString());

		newCfpContractDetails.setCfpContractAttachedStatus(RandomTestUtil.nextInt());

		newCfpContractDetails.setInboundStatus(RandomTestUtil.randomString());

		_cfpContractDetailses.add(_persistence.update(newCfpContractDetails));

		CfpContractDetails existingCfpContractDetails = _persistence.findByPrimaryKey(newCfpContractDetails.getPrimaryKey());

		Assert.assertEquals(existingCfpContractDetails.getCreatedBy(),
			newCfpContractDetails.getCreatedBy());
		Assert.assertEquals(existingCfpContractDetails.getTradeClass(),
			newCfpContractDetails.getTradeClass());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCfpContractDetails.getTradeClassEndDate()),
			Time.getShortTimestamp(newCfpContractDetails.getTradeClassEndDate()));
		Assert.assertEquals(existingCfpContractDetails.getCfpContractSid(),
			newCfpContractDetails.getCfpContractSid());
		Assert.assertEquals(existingCfpContractDetails.getModifiedBy(),
			newCfpContractDetails.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCfpContractDetails.getCompanyStartDate()),
			Time.getShortTimestamp(newCfpContractDetails.getCompanyStartDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingCfpContractDetails.getTradeClassStartDate()),
			Time.getShortTimestamp(
				newCfpContractDetails.getTradeClassStartDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingCfpContractDetails.getCreatedDate()),
			Time.getShortTimestamp(newCfpContractDetails.getCreatedDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingCfpContractDetails.getCfpContractAttachedDate()),
			Time.getShortTimestamp(
				newCfpContractDetails.getCfpContractAttachedDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingCfpContractDetails.getCompanyEndDate()),
			Time.getShortTimestamp(newCfpContractDetails.getCompanyEndDate()));
		Assert.assertEquals(existingCfpContractDetails.getCompanyMasterSid(),
			newCfpContractDetails.getCompanyMasterSid());
		Assert.assertEquals(existingCfpContractDetails.getBatchId(),
			newCfpContractDetails.getBatchId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCfpContractDetails.getModifiedDate()),
			Time.getShortTimestamp(newCfpContractDetails.getModifiedDate()));
		Assert.assertEquals(existingCfpContractDetails.getRecordLockStatus(),
			newCfpContractDetails.getRecordLockStatus());
		Assert.assertEquals(existingCfpContractDetails.getSource(),
			newCfpContractDetails.getSource());
		Assert.assertEquals(existingCfpContractDetails.getCfpContractDetailsSid(),
			newCfpContractDetails.getCfpContractDetailsSid());
		Assert.assertEquals(existingCfpContractDetails.getCfpContractAttachedStatus(),
			newCfpContractDetails.getCfpContractAttachedStatus());
		Assert.assertEquals(existingCfpContractDetails.getInboundStatus(),
			newCfpContractDetails.getInboundStatus());
	}

	@Test
	public void testCountByCFPDetails() throws Exception {
		_persistence.countByCFPDetails(RandomTestUtil.nextInt(),
			RandomTestUtil.nextInt());

		_persistence.countByCFPDetails(0, 0);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CfpContractDetails newCfpContractDetails = addCfpContractDetails();

		CfpContractDetails existingCfpContractDetails = _persistence.findByPrimaryKey(newCfpContractDetails.getPrimaryKey());

		Assert.assertEquals(existingCfpContractDetails, newCfpContractDetails);
	}

	@Test(expected = NoSuchCfpContractDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CfpContractDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CFP_CONTRACT_DETAILS",
			"createdBy", true, "tradeClass", true, "tradeClassEndDate", true,
			"cfpContractSid", true, "modifiedBy", true, "companyStartDate",
			true, "tradeClassStartDate", true, "createdDate", true,
			"cfpContractAttachedDate", true, "companyEndDate", true,
			"companyMasterSid", true, "batchId", true, "modifiedDate", true,
			"recordLockStatus", true, "source", true, "cfpContractDetailsSid",
			true, "cfpContractAttachedStatus", true, "inboundStatus", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CfpContractDetails newCfpContractDetails = addCfpContractDetails();

		CfpContractDetails existingCfpContractDetails = _persistence.fetchByPrimaryKey(newCfpContractDetails.getPrimaryKey());

		Assert.assertEquals(existingCfpContractDetails, newCfpContractDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CfpContractDetails missingCfpContractDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCfpContractDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CfpContractDetails newCfpContractDetails1 = addCfpContractDetails();
		CfpContractDetails newCfpContractDetails2 = addCfpContractDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCfpContractDetails1.getPrimaryKey());
		primaryKeys.add(newCfpContractDetails2.getPrimaryKey());

		Map<Serializable, CfpContractDetails> cfpContractDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, cfpContractDetailses.size());
		Assert.assertEquals(newCfpContractDetails1,
			cfpContractDetailses.get(newCfpContractDetails1.getPrimaryKey()));
		Assert.assertEquals(newCfpContractDetails2,
			cfpContractDetailses.get(newCfpContractDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CfpContractDetails> cfpContractDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cfpContractDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CfpContractDetails newCfpContractDetails = addCfpContractDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCfpContractDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CfpContractDetails> cfpContractDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cfpContractDetailses.size());
		Assert.assertEquals(newCfpContractDetails,
			cfpContractDetailses.get(newCfpContractDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CfpContractDetails> cfpContractDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cfpContractDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CfpContractDetails newCfpContractDetails = addCfpContractDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCfpContractDetails.getPrimaryKey());

		Map<Serializable, CfpContractDetails> cfpContractDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cfpContractDetailses.size());
		Assert.assertEquals(newCfpContractDetails,
			cfpContractDetailses.get(newCfpContractDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CfpContractDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CfpContractDetails>() {
				@Override
				public void performAction(CfpContractDetails cfpContractDetails) {
					Assert.assertNotNull(cfpContractDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CfpContractDetails newCfpContractDetails = addCfpContractDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CfpContractDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("cfpContractDetailsSid",
				newCfpContractDetails.getCfpContractDetailsSid()));

		List<CfpContractDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CfpContractDetails existingCfpContractDetails = result.get(0);

		Assert.assertEquals(existingCfpContractDetails, newCfpContractDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CfpContractDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("cfpContractDetailsSid",
				RandomTestUtil.nextInt()));

		List<CfpContractDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CfpContractDetails newCfpContractDetails = addCfpContractDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CfpContractDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"cfpContractDetailsSid"));

		Object newCfpContractDetailsSid = newCfpContractDetails.getCfpContractDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("cfpContractDetailsSid",
				new Object[] { newCfpContractDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCfpContractDetailsSid = result.get(0);

		Assert.assertEquals(existingCfpContractDetailsSid,
			newCfpContractDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CfpContractDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"cfpContractDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("cfpContractDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected CfpContractDetails addCfpContractDetails()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		CfpContractDetails cfpContractDetails = _persistence.create(pk);

		cfpContractDetails.setCreatedBy(RandomTestUtil.nextInt());

		cfpContractDetails.setTradeClass(RandomTestUtil.nextInt());

		cfpContractDetails.setTradeClassEndDate(RandomTestUtil.nextDate());

		cfpContractDetails.setCfpContractSid(RandomTestUtil.nextInt());

		cfpContractDetails.setModifiedBy(RandomTestUtil.nextInt());

		cfpContractDetails.setCompanyStartDate(RandomTestUtil.nextDate());

		cfpContractDetails.setTradeClassStartDate(RandomTestUtil.nextDate());

		cfpContractDetails.setCreatedDate(RandomTestUtil.nextDate());

		cfpContractDetails.setCfpContractAttachedDate(RandomTestUtil.nextDate());

		cfpContractDetails.setCompanyEndDate(RandomTestUtil.nextDate());

		cfpContractDetails.setCompanyMasterSid(RandomTestUtil.nextInt());

		cfpContractDetails.setBatchId(RandomTestUtil.randomString());

		cfpContractDetails.setModifiedDate(RandomTestUtil.nextDate());

		cfpContractDetails.setRecordLockStatus(RandomTestUtil.randomBoolean());

		cfpContractDetails.setSource(RandomTestUtil.randomString());

		cfpContractDetails.setCfpContractAttachedStatus(RandomTestUtil.nextInt());

		cfpContractDetails.setInboundStatus(RandomTestUtil.randomString());

		_cfpContractDetailses.add(_persistence.update(cfpContractDetails));

		return cfpContractDetails;
	}

	private List<CfpContractDetails> _cfpContractDetailses = new ArrayList<CfpContractDetails>();
	private CfpContractDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}