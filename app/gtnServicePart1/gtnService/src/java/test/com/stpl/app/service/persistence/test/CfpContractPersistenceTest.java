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

import com.stpl.app.exception.NoSuchCfpContractException;
import com.stpl.app.model.CfpContract;
import com.stpl.app.service.CfpContractLocalServiceUtil;
import com.stpl.app.service.persistence.CfpContractPersistence;
import com.stpl.app.service.persistence.CfpContractUtil;

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
public class CfpContractPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = CfpContractUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CfpContract> iterator = _cfpContracts.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CfpContract cfpContract = _persistence.create(pk);

		Assert.assertNotNull(cfpContract);

		Assert.assertEquals(cfpContract.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CfpContract newCfpContract = addCfpContract();

		_persistence.remove(newCfpContract);

		CfpContract existingCfpContract = _persistence.fetchByPrimaryKey(newCfpContract.getPrimaryKey());

		Assert.assertNull(existingCfpContract);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCfpContract();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CfpContract newCfpContract = _persistence.create(pk);

		newCfpContract.setCreatedBy(RandomTestUtil.nextInt());

		newCfpContract.setCfpType(RandomTestUtil.nextInt());

		newCfpContract.setCfpTradeClass(RandomTestUtil.nextInt());

		newCfpContract.setModifiedBy(RandomTestUtil.nextInt());

		newCfpContract.setCreatedDate(RandomTestUtil.nextDate());

		newCfpContract.setContractMasterSid(RandomTestUtil.nextInt());

		newCfpContract.setCfpContractAttachedDate(RandomTestUtil.nextDate());

		newCfpContract.setCfpModelSid(RandomTestUtil.nextInt());

		newCfpContract.setBatchId(RandomTestUtil.randomString());

		newCfpContract.setModifiedDate(RandomTestUtil.nextDate());

		newCfpContract.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newCfpContract.setCfpDesignation(RandomTestUtil.randomString());

		newCfpContract.setCfpName(RandomTestUtil.randomString());

		newCfpContract.setCfpNo(RandomTestUtil.randomString());

		newCfpContract.setCfpCategory(RandomTestUtil.nextInt());

		newCfpContract.setSource(RandomTestUtil.randomString());

		newCfpContract.setCfpStatus(RandomTestUtil.nextInt());

		newCfpContract.setParentCfpId(RandomTestUtil.nextInt());

		newCfpContract.setCfpContractAttachedStatus(RandomTestUtil.nextInt());

		newCfpContract.setCfpStartDate(RandomTestUtil.nextDate());

		newCfpContract.setCfpEndDate(RandomTestUtil.nextDate());

		newCfpContract.setParentCfpName(RandomTestUtil.randomString());

		newCfpContract.setInboundStatus(RandomTestUtil.randomString());

		newCfpContract.setSalesInclusion(RandomTestUtil.nextInt());

		_cfpContracts.add(_persistence.update(newCfpContract));

		CfpContract existingCfpContract = _persistence.findByPrimaryKey(newCfpContract.getPrimaryKey());

		Assert.assertEquals(existingCfpContract.getCreatedBy(),
			newCfpContract.getCreatedBy());
		Assert.assertEquals(existingCfpContract.getCfpContractSid(),
			newCfpContract.getCfpContractSid());
		Assert.assertEquals(existingCfpContract.getCfpType(),
			newCfpContract.getCfpType());
		Assert.assertEquals(existingCfpContract.getCfpTradeClass(),
			newCfpContract.getCfpTradeClass());
		Assert.assertEquals(existingCfpContract.getModifiedBy(),
			newCfpContract.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCfpContract.getCreatedDate()),
			Time.getShortTimestamp(newCfpContract.getCreatedDate()));
		Assert.assertEquals(existingCfpContract.getContractMasterSid(),
			newCfpContract.getContractMasterSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCfpContract.getCfpContractAttachedDate()),
			Time.getShortTimestamp(newCfpContract.getCfpContractAttachedDate()));
		Assert.assertEquals(existingCfpContract.getCfpModelSid(),
			newCfpContract.getCfpModelSid());
		Assert.assertEquals(existingCfpContract.getBatchId(),
			newCfpContract.getBatchId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCfpContract.getModifiedDate()),
			Time.getShortTimestamp(newCfpContract.getModifiedDate()));
		Assert.assertEquals(existingCfpContract.getRecordLockStatus(),
			newCfpContract.getRecordLockStatus());
		Assert.assertEquals(existingCfpContract.getCfpDesignation(),
			newCfpContract.getCfpDesignation());
		Assert.assertEquals(existingCfpContract.getCfpName(),
			newCfpContract.getCfpName());
		Assert.assertEquals(existingCfpContract.getCfpNo(),
			newCfpContract.getCfpNo());
		Assert.assertEquals(existingCfpContract.getCfpCategory(),
			newCfpContract.getCfpCategory());
		Assert.assertEquals(existingCfpContract.getSource(),
			newCfpContract.getSource());
		Assert.assertEquals(existingCfpContract.getCfpStatus(),
			newCfpContract.getCfpStatus());
		Assert.assertEquals(existingCfpContract.getParentCfpId(),
			newCfpContract.getParentCfpId());
		Assert.assertEquals(existingCfpContract.getCfpContractAttachedStatus(),
			newCfpContract.getCfpContractAttachedStatus());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCfpContract.getCfpStartDate()),
			Time.getShortTimestamp(newCfpContract.getCfpStartDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingCfpContract.getCfpEndDate()),
			Time.getShortTimestamp(newCfpContract.getCfpEndDate()));
		Assert.assertEquals(existingCfpContract.getParentCfpName(),
			newCfpContract.getParentCfpName());
		Assert.assertEquals(existingCfpContract.getInboundStatus(),
			newCfpContract.getInboundStatus());
		Assert.assertEquals(existingCfpContract.getSalesInclusion(),
			newCfpContract.getSalesInclusion());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CfpContract newCfpContract = addCfpContract();

		CfpContract existingCfpContract = _persistence.findByPrimaryKey(newCfpContract.getPrimaryKey());

		Assert.assertEquals(existingCfpContract, newCfpContract);
	}

	@Test(expected = NoSuchCfpContractException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CfpContract> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CFP_CONTRACT", "createdBy",
			true, "cfpContractSid", true, "cfpType", true, "cfpTradeClass",
			true, "modifiedBy", true, "createdDate", true, "contractMasterSid",
			true, "cfpContractAttachedDate", true, "cfpModelSid", true,
			"batchId", true, "modifiedDate", true, "recordLockStatus", true,
			"cfpDesignation", true, "cfpName", true, "cfpNo", true,
			"cfpCategory", true, "source", true, "cfpStatus", true,
			"parentCfpId", true, "cfpContractAttachedStatus", true,
			"cfpStartDate", true, "cfpEndDate", true, "parentCfpName", true,
			"inboundStatus", true, "salesInclusion", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CfpContract newCfpContract = addCfpContract();

		CfpContract existingCfpContract = _persistence.fetchByPrimaryKey(newCfpContract.getPrimaryKey());

		Assert.assertEquals(existingCfpContract, newCfpContract);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CfpContract missingCfpContract = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCfpContract);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CfpContract newCfpContract1 = addCfpContract();
		CfpContract newCfpContract2 = addCfpContract();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCfpContract1.getPrimaryKey());
		primaryKeys.add(newCfpContract2.getPrimaryKey());

		Map<Serializable, CfpContract> cfpContracts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, cfpContracts.size());
		Assert.assertEquals(newCfpContract1,
			cfpContracts.get(newCfpContract1.getPrimaryKey()));
		Assert.assertEquals(newCfpContract2,
			cfpContracts.get(newCfpContract2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CfpContract> cfpContracts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cfpContracts.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CfpContract newCfpContract = addCfpContract();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCfpContract.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CfpContract> cfpContracts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cfpContracts.size());
		Assert.assertEquals(newCfpContract,
			cfpContracts.get(newCfpContract.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CfpContract> cfpContracts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cfpContracts.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CfpContract newCfpContract = addCfpContract();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCfpContract.getPrimaryKey());

		Map<Serializable, CfpContract> cfpContracts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cfpContracts.size());
		Assert.assertEquals(newCfpContract,
			cfpContracts.get(newCfpContract.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CfpContractLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CfpContract>() {
				@Override
				public void performAction(CfpContract cfpContract) {
					Assert.assertNotNull(cfpContract);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CfpContract newCfpContract = addCfpContract();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CfpContract.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("cfpContractSid",
				newCfpContract.getCfpContractSid()));

		List<CfpContract> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CfpContract existingCfpContract = result.get(0);

		Assert.assertEquals(existingCfpContract, newCfpContract);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CfpContract.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("cfpContractSid",
				RandomTestUtil.nextInt()));

		List<CfpContract> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CfpContract newCfpContract = addCfpContract();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CfpContract.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"cfpContractSid"));

		Object newCfpContractSid = newCfpContract.getCfpContractSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("cfpContractSid",
				new Object[] { newCfpContractSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCfpContractSid = result.get(0);

		Assert.assertEquals(existingCfpContractSid, newCfpContractSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CfpContract.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"cfpContractSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("cfpContractSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected CfpContract addCfpContract() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CfpContract cfpContract = _persistence.create(pk);

		cfpContract.setCreatedBy(RandomTestUtil.nextInt());

		cfpContract.setCfpType(RandomTestUtil.nextInt());

		cfpContract.setCfpTradeClass(RandomTestUtil.nextInt());

		cfpContract.setModifiedBy(RandomTestUtil.nextInt());

		cfpContract.setCreatedDate(RandomTestUtil.nextDate());

		cfpContract.setContractMasterSid(RandomTestUtil.nextInt());

		cfpContract.setCfpContractAttachedDate(RandomTestUtil.nextDate());

		cfpContract.setCfpModelSid(RandomTestUtil.nextInt());

		cfpContract.setBatchId(RandomTestUtil.randomString());

		cfpContract.setModifiedDate(RandomTestUtil.nextDate());

		cfpContract.setRecordLockStatus(RandomTestUtil.randomBoolean());

		cfpContract.setCfpDesignation(RandomTestUtil.randomString());

		cfpContract.setCfpName(RandomTestUtil.randomString());

		cfpContract.setCfpNo(RandomTestUtil.randomString());

		cfpContract.setCfpCategory(RandomTestUtil.nextInt());

		cfpContract.setSource(RandomTestUtil.randomString());

		cfpContract.setCfpStatus(RandomTestUtil.nextInt());

		cfpContract.setParentCfpId(RandomTestUtil.nextInt());

		cfpContract.setCfpContractAttachedStatus(RandomTestUtil.nextInt());

		cfpContract.setCfpStartDate(RandomTestUtil.nextDate());

		cfpContract.setCfpEndDate(RandomTestUtil.nextDate());

		cfpContract.setParentCfpName(RandomTestUtil.randomString());

		cfpContract.setInboundStatus(RandomTestUtil.randomString());

		cfpContract.setSalesInclusion(RandomTestUtil.nextInt());

		_cfpContracts.add(_persistence.update(cfpContract));

		return cfpContract;
	}

	private List<CfpContract> _cfpContracts = new ArrayList<CfpContract>();
	private CfpContractPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}