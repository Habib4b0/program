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

import com.stpl.app.exception.NoSuchPsContractException;
import com.stpl.app.model.PsContract;
import com.stpl.app.service.PsContractLocalServiceUtil;
import com.stpl.app.service.persistence.PsContractPersistence;
import com.stpl.app.service.persistence.PsContractUtil;

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
public class PsContractPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = PsContractUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<PsContract> iterator = _psContracts.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		PsContract psContract = _persistence.create(pk);

		Assert.assertNotNull(psContract);

		Assert.assertEquals(psContract.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		PsContract newPsContract = addPsContract();

		_persistence.remove(newPsContract);

		PsContract existingPsContract = _persistence.fetchByPrimaryKey(newPsContract.getPrimaryKey());

		Assert.assertNull(existingPsContract);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addPsContract();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		PsContract newPsContract = _persistence.create(pk);

		newPsContract.setPsName(RandomTestUtil.randomString());

		newPsContract.setPsNo(RandomTestUtil.randomString());

		newPsContract.setCfpContractSid(RandomTestUtil.randomString());

		newPsContract.setPsType(RandomTestUtil.nextInt());

		newPsContract.setPsContractAttachedStatus(RandomTestUtil.nextInt());

		newPsContract.setModifiedDate(RandomTestUtil.nextDate());

		newPsContract.setPsCategory(RandomTestUtil.nextInt());

		newPsContract.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newPsContract.setPsStatus(RandomTestUtil.nextInt());

		newPsContract.setCreatedDate(RandomTestUtil.nextDate());

		newPsContract.setCreatedBy(RandomTestUtil.nextInt());

		newPsContract.setSource(RandomTestUtil.randomString());

		newPsContract.setParentPsId(RandomTestUtil.randomString());

		newPsContract.setPsDesignation(RandomTestUtil.randomString());

		newPsContract.setBatchId(RandomTestUtil.randomString());

		newPsContract.setContractMasterSid(RandomTestUtil.nextInt());

		newPsContract.setPsModelSid(RandomTestUtil.nextInt());

		newPsContract.setPsContractAttachedDate(RandomTestUtil.nextDate());

		newPsContract.setPsEndDate(RandomTestUtil.nextDate());

		newPsContract.setModifiedBy(RandomTestUtil.nextInt());

		newPsContract.setInboundStatus(RandomTestUtil.randomString());

		newPsContract.setParentPsName(RandomTestUtil.randomString());

		newPsContract.setPsStartDate(RandomTestUtil.nextDate());

		newPsContract.setIfpContractSid(RandomTestUtil.randomString());

		newPsContract.setPsTradeClass(RandomTestUtil.nextInt());

		_psContracts.add(_persistence.update(newPsContract));

		PsContract existingPsContract = _persistence.findByPrimaryKey(newPsContract.getPrimaryKey());

		Assert.assertEquals(existingPsContract.getPsName(),
			newPsContract.getPsName());
		Assert.assertEquals(existingPsContract.getPsNo(),
			newPsContract.getPsNo());
		Assert.assertEquals(existingPsContract.getCfpContractSid(),
			newPsContract.getCfpContractSid());
		Assert.assertEquals(existingPsContract.getPsContractSid(),
			newPsContract.getPsContractSid());
		Assert.assertEquals(existingPsContract.getPsType(),
			newPsContract.getPsType());
		Assert.assertEquals(existingPsContract.getPsContractAttachedStatus(),
			newPsContract.getPsContractAttachedStatus());
		Assert.assertEquals(Time.getShortTimestamp(
				existingPsContract.getModifiedDate()),
			Time.getShortTimestamp(newPsContract.getModifiedDate()));
		Assert.assertEquals(existingPsContract.getPsCategory(),
			newPsContract.getPsCategory());
		Assert.assertEquals(existingPsContract.getRecordLockStatus(),
			newPsContract.getRecordLockStatus());
		Assert.assertEquals(existingPsContract.getPsStatus(),
			newPsContract.getPsStatus());
		Assert.assertEquals(Time.getShortTimestamp(
				existingPsContract.getCreatedDate()),
			Time.getShortTimestamp(newPsContract.getCreatedDate()));
		Assert.assertEquals(existingPsContract.getCreatedBy(),
			newPsContract.getCreatedBy());
		Assert.assertEquals(existingPsContract.getSource(),
			newPsContract.getSource());
		Assert.assertEquals(existingPsContract.getParentPsId(),
			newPsContract.getParentPsId());
		Assert.assertEquals(existingPsContract.getPsDesignation(),
			newPsContract.getPsDesignation());
		Assert.assertEquals(existingPsContract.getBatchId(),
			newPsContract.getBatchId());
		Assert.assertEquals(existingPsContract.getContractMasterSid(),
			newPsContract.getContractMasterSid());
		Assert.assertEquals(existingPsContract.getPsModelSid(),
			newPsContract.getPsModelSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingPsContract.getPsContractAttachedDate()),
			Time.getShortTimestamp(newPsContract.getPsContractAttachedDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingPsContract.getPsEndDate()),
			Time.getShortTimestamp(newPsContract.getPsEndDate()));
		Assert.assertEquals(existingPsContract.getModifiedBy(),
			newPsContract.getModifiedBy());
		Assert.assertEquals(existingPsContract.getInboundStatus(),
			newPsContract.getInboundStatus());
		Assert.assertEquals(existingPsContract.getParentPsName(),
			newPsContract.getParentPsName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingPsContract.getPsStartDate()),
			Time.getShortTimestamp(newPsContract.getPsStartDate()));
		Assert.assertEquals(existingPsContract.getIfpContractSid(),
			newPsContract.getIfpContractSid());
		Assert.assertEquals(existingPsContract.getPsTradeClass(),
			newPsContract.getPsTradeClass());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		PsContract newPsContract = addPsContract();

		PsContract existingPsContract = _persistence.findByPrimaryKey(newPsContract.getPrimaryKey());

		Assert.assertEquals(existingPsContract, newPsContract);
	}

	@Test(expected = NoSuchPsContractException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<PsContract> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("PS_CONTRACT", "psName",
			true, "psNo", true, "cfpContractSid", true, "psContractSid", true,
			"psType", true, "psContractAttachedStatus", true, "modifiedDate",
			true, "psCategory", true, "recordLockStatus", true, "psStatus",
			true, "createdDate", true, "createdBy", true, "source", true,
			"parentPsId", true, "psDesignation", true, "batchId", true,
			"contractMasterSid", true, "psModelSid", true,
			"psContractAttachedDate", true, "psEndDate", true, "modifiedBy",
			true, "inboundStatus", true, "parentPsName", true, "psStartDate",
			true, "ifpContractSid", true, "psTradeClass", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		PsContract newPsContract = addPsContract();

		PsContract existingPsContract = _persistence.fetchByPrimaryKey(newPsContract.getPrimaryKey());

		Assert.assertEquals(existingPsContract, newPsContract);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		PsContract missingPsContract = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingPsContract);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		PsContract newPsContract1 = addPsContract();
		PsContract newPsContract2 = addPsContract();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newPsContract1.getPrimaryKey());
		primaryKeys.add(newPsContract2.getPrimaryKey());

		Map<Serializable, PsContract> psContracts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, psContracts.size());
		Assert.assertEquals(newPsContract1,
			psContracts.get(newPsContract1.getPrimaryKey()));
		Assert.assertEquals(newPsContract2,
			psContracts.get(newPsContract2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, PsContract> psContracts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(psContracts.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		PsContract newPsContract = addPsContract();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newPsContract.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, PsContract> psContracts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, psContracts.size());
		Assert.assertEquals(newPsContract,
			psContracts.get(newPsContract.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, PsContract> psContracts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(psContracts.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		PsContract newPsContract = addPsContract();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newPsContract.getPrimaryKey());

		Map<Serializable, PsContract> psContracts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, psContracts.size());
		Assert.assertEquals(newPsContract,
			psContracts.get(newPsContract.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = PsContractLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<PsContract>() {
				@Override
				public void performAction(PsContract psContract) {
					Assert.assertNotNull(psContract);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		PsContract newPsContract = addPsContract();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PsContract.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("psContractSid",
				newPsContract.getPsContractSid()));

		List<PsContract> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		PsContract existingPsContract = result.get(0);

		Assert.assertEquals(existingPsContract, newPsContract);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PsContract.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("psContractSid",
				RandomTestUtil.nextInt()));

		List<PsContract> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		PsContract newPsContract = addPsContract();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PsContract.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"psContractSid"));

		Object newPsContractSid = newPsContract.getPsContractSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("psContractSid",
				new Object[] { newPsContractSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingPsContractSid = result.get(0);

		Assert.assertEquals(existingPsContractSid, newPsContractSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PsContract.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"psContractSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("psContractSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected PsContract addPsContract() throws Exception {
		int pk = RandomTestUtil.nextInt();

		PsContract psContract = _persistence.create(pk);

		psContract.setPsName(RandomTestUtil.randomString());

		psContract.setPsNo(RandomTestUtil.randomString());

		psContract.setCfpContractSid(RandomTestUtil.randomString());

		psContract.setPsType(RandomTestUtil.nextInt());

		psContract.setPsContractAttachedStatus(RandomTestUtil.nextInt());

		psContract.setModifiedDate(RandomTestUtil.nextDate());

		psContract.setPsCategory(RandomTestUtil.nextInt());

		psContract.setRecordLockStatus(RandomTestUtil.randomBoolean());

		psContract.setPsStatus(RandomTestUtil.nextInt());

		psContract.setCreatedDate(RandomTestUtil.nextDate());

		psContract.setCreatedBy(RandomTestUtil.nextInt());

		psContract.setSource(RandomTestUtil.randomString());

		psContract.setParentPsId(RandomTestUtil.randomString());

		psContract.setPsDesignation(RandomTestUtil.randomString());

		psContract.setBatchId(RandomTestUtil.randomString());

		psContract.setContractMasterSid(RandomTestUtil.nextInt());

		psContract.setPsModelSid(RandomTestUtil.nextInt());

		psContract.setPsContractAttachedDate(RandomTestUtil.nextDate());

		psContract.setPsEndDate(RandomTestUtil.nextDate());

		psContract.setModifiedBy(RandomTestUtil.nextInt());

		psContract.setInboundStatus(RandomTestUtil.randomString());

		psContract.setParentPsName(RandomTestUtil.randomString());

		psContract.setPsStartDate(RandomTestUtil.nextDate());

		psContract.setIfpContractSid(RandomTestUtil.randomString());

		psContract.setPsTradeClass(RandomTestUtil.nextInt());

		_psContracts.add(_persistence.update(psContract));

		return psContract;
	}

	private List<PsContract> _psContracts = new ArrayList<PsContract>();
	private PsContractPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}