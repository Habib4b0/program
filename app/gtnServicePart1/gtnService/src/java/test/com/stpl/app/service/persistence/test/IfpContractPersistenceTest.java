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

import com.stpl.app.exception.NoSuchIfpContractException;
import com.stpl.app.model.IfpContract;
import com.stpl.app.service.IfpContractLocalServiceUtil;
import com.stpl.app.service.persistence.IfpContractPersistence;
import com.stpl.app.service.persistence.IfpContractUtil;

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
public class IfpContractPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = IfpContractUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<IfpContract> iterator = _ifpContracts.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IfpContract ifpContract = _persistence.create(pk);

		Assert.assertNotNull(ifpContract);

		Assert.assertEquals(ifpContract.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		IfpContract newIfpContract = addIfpContract();

		_persistence.remove(newIfpContract);

		IfpContract existingIfpContract = _persistence.fetchByPrimaryKey(newIfpContract.getPrimaryKey());

		Assert.assertNull(existingIfpContract);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addIfpContract();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IfpContract newIfpContract = _persistence.create(pk);

		newIfpContract.setCfpContractSid(RandomTestUtil.randomString());

		newIfpContract.setParentIfpName(RandomTestUtil.randomString());

		newIfpContract.setIfpContractAttachedDate(RandomTestUtil.nextDate());

		newIfpContract.setIfpStatus(RandomTestUtil.nextInt());

		newIfpContract.setIfpStartDate(RandomTestUtil.nextDate());

		newIfpContract.setIfpContractAttachedStatus(RandomTestUtil.nextInt());

		newIfpContract.setModifiedDate(RandomTestUtil.nextDate());

		newIfpContract.setIfpCategory(RandomTestUtil.nextInt());

		newIfpContract.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newIfpContract.setIfpEndDate(RandomTestUtil.nextDate());

		newIfpContract.setCreatedDate(RandomTestUtil.nextDate());

		newIfpContract.setCreatedBy(RandomTestUtil.nextInt());

		newIfpContract.setSource(RandomTestUtil.randomString());

		newIfpContract.setIfpDesignation(RandomTestUtil.randomString());

		newIfpContract.setParentIfpId(RandomTestUtil.randomString());

		newIfpContract.setBatchId(RandomTestUtil.randomString());

		newIfpContract.setContractMasterSid(RandomTestUtil.nextInt());

		newIfpContract.setIfpType(RandomTestUtil.nextInt());

		newIfpContract.setIfpName(RandomTestUtil.randomString());

		newIfpContract.setIfpNo(RandomTestUtil.randomString());

		newIfpContract.setModifiedBy(RandomTestUtil.nextInt());

		newIfpContract.setInboundStatus(RandomTestUtil.randomString());

		newIfpContract.setIfpModelSid(RandomTestUtil.nextInt());

		_ifpContracts.add(_persistence.update(newIfpContract));

		IfpContract existingIfpContract = _persistence.findByPrimaryKey(newIfpContract.getPrimaryKey());

		Assert.assertEquals(existingIfpContract.getCfpContractSid(),
			newIfpContract.getCfpContractSid());
		Assert.assertEquals(existingIfpContract.getParentIfpName(),
			newIfpContract.getParentIfpName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIfpContract.getIfpContractAttachedDate()),
			Time.getShortTimestamp(newIfpContract.getIfpContractAttachedDate()));
		Assert.assertEquals(existingIfpContract.getIfpStatus(),
			newIfpContract.getIfpStatus());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIfpContract.getIfpStartDate()),
			Time.getShortTimestamp(newIfpContract.getIfpStartDate()));
		Assert.assertEquals(existingIfpContract.getIfpContractAttachedStatus(),
			newIfpContract.getIfpContractAttachedStatus());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIfpContract.getModifiedDate()),
			Time.getShortTimestamp(newIfpContract.getModifiedDate()));
		Assert.assertEquals(existingIfpContract.getIfpCategory(),
			newIfpContract.getIfpCategory());
		Assert.assertEquals(existingIfpContract.getRecordLockStatus(),
			newIfpContract.getRecordLockStatus());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIfpContract.getIfpEndDate()),
			Time.getShortTimestamp(newIfpContract.getIfpEndDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingIfpContract.getCreatedDate()),
			Time.getShortTimestamp(newIfpContract.getCreatedDate()));
		Assert.assertEquals(existingIfpContract.getCreatedBy(),
			newIfpContract.getCreatedBy());
		Assert.assertEquals(existingIfpContract.getSource(),
			newIfpContract.getSource());
		Assert.assertEquals(existingIfpContract.getIfpDesignation(),
			newIfpContract.getIfpDesignation());
		Assert.assertEquals(existingIfpContract.getParentIfpId(),
			newIfpContract.getParentIfpId());
		Assert.assertEquals(existingIfpContract.getBatchId(),
			newIfpContract.getBatchId());
		Assert.assertEquals(existingIfpContract.getContractMasterSid(),
			newIfpContract.getContractMasterSid());
		Assert.assertEquals(existingIfpContract.getIfpType(),
			newIfpContract.getIfpType());
		Assert.assertEquals(existingIfpContract.getIfpName(),
			newIfpContract.getIfpName());
		Assert.assertEquals(existingIfpContract.getIfpNo(),
			newIfpContract.getIfpNo());
		Assert.assertEquals(existingIfpContract.getModifiedBy(),
			newIfpContract.getModifiedBy());
		Assert.assertEquals(existingIfpContract.getInboundStatus(),
			newIfpContract.getInboundStatus());
		Assert.assertEquals(existingIfpContract.getIfpContractSid(),
			newIfpContract.getIfpContractSid());
		Assert.assertEquals(existingIfpContract.getIfpModelSid(),
			newIfpContract.getIfpModelSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		IfpContract newIfpContract = addIfpContract();

		IfpContract existingIfpContract = _persistence.findByPrimaryKey(newIfpContract.getPrimaryKey());

		Assert.assertEquals(existingIfpContract, newIfpContract);
	}

	@Test(expected = NoSuchIfpContractException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<IfpContract> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("IFP_CONTRACT",
			"cfpContractSid", true, "parentIfpName", true,
			"ifpContractAttachedDate", true, "ifpStatus", true, "ifpStartDate",
			true, "ifpContractAttachedStatus", true, "modifiedDate", true,
			"ifpCategory", true, "recordLockStatus", true, "ifpEndDate", true,
			"createdDate", true, "createdBy", true, "source", true,
			"ifpDesignation", true, "parentIfpId", true, "batchId", true,
			"contractMasterSid", true, "ifpType", true, "ifpName", true,
			"ifpNo", true, "modifiedBy", true, "inboundStatus", true,
			"ifpContractSid", true, "ifpModelSid", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		IfpContract newIfpContract = addIfpContract();

		IfpContract existingIfpContract = _persistence.fetchByPrimaryKey(newIfpContract.getPrimaryKey());

		Assert.assertEquals(existingIfpContract, newIfpContract);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IfpContract missingIfpContract = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingIfpContract);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		IfpContract newIfpContract1 = addIfpContract();
		IfpContract newIfpContract2 = addIfpContract();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIfpContract1.getPrimaryKey());
		primaryKeys.add(newIfpContract2.getPrimaryKey());

		Map<Serializable, IfpContract> ifpContracts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ifpContracts.size());
		Assert.assertEquals(newIfpContract1,
			ifpContracts.get(newIfpContract1.getPrimaryKey()));
		Assert.assertEquals(newIfpContract2,
			ifpContracts.get(newIfpContract2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, IfpContract> ifpContracts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ifpContracts.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		IfpContract newIfpContract = addIfpContract();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIfpContract.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, IfpContract> ifpContracts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ifpContracts.size());
		Assert.assertEquals(newIfpContract,
			ifpContracts.get(newIfpContract.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, IfpContract> ifpContracts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ifpContracts.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		IfpContract newIfpContract = addIfpContract();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIfpContract.getPrimaryKey());

		Map<Serializable, IfpContract> ifpContracts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ifpContracts.size());
		Assert.assertEquals(newIfpContract,
			ifpContracts.get(newIfpContract.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = IfpContractLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<IfpContract>() {
				@Override
				public void performAction(IfpContract ifpContract) {
					Assert.assertNotNull(ifpContract);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		IfpContract newIfpContract = addIfpContract();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IfpContract.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ifpContractSid",
				newIfpContract.getIfpContractSid()));

		List<IfpContract> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		IfpContract existingIfpContract = result.get(0);

		Assert.assertEquals(existingIfpContract, newIfpContract);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IfpContract.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ifpContractSid",
				RandomTestUtil.nextInt()));

		List<IfpContract> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		IfpContract newIfpContract = addIfpContract();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IfpContract.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ifpContractSid"));

		Object newIfpContractSid = newIfpContract.getIfpContractSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("ifpContractSid",
				new Object[] { newIfpContractSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingIfpContractSid = result.get(0);

		Assert.assertEquals(existingIfpContractSid, newIfpContractSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IfpContract.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ifpContractSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("ifpContractSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected IfpContract addIfpContract() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IfpContract ifpContract = _persistence.create(pk);

		ifpContract.setCfpContractSid(RandomTestUtil.randomString());

		ifpContract.setParentIfpName(RandomTestUtil.randomString());

		ifpContract.setIfpContractAttachedDate(RandomTestUtil.nextDate());

		ifpContract.setIfpStatus(RandomTestUtil.nextInt());

		ifpContract.setIfpStartDate(RandomTestUtil.nextDate());

		ifpContract.setIfpContractAttachedStatus(RandomTestUtil.nextInt());

		ifpContract.setModifiedDate(RandomTestUtil.nextDate());

		ifpContract.setIfpCategory(RandomTestUtil.nextInt());

		ifpContract.setRecordLockStatus(RandomTestUtil.randomBoolean());

		ifpContract.setIfpEndDate(RandomTestUtil.nextDate());

		ifpContract.setCreatedDate(RandomTestUtil.nextDate());

		ifpContract.setCreatedBy(RandomTestUtil.nextInt());

		ifpContract.setSource(RandomTestUtil.randomString());

		ifpContract.setIfpDesignation(RandomTestUtil.randomString());

		ifpContract.setParentIfpId(RandomTestUtil.randomString());

		ifpContract.setBatchId(RandomTestUtil.randomString());

		ifpContract.setContractMasterSid(RandomTestUtil.nextInt());

		ifpContract.setIfpType(RandomTestUtil.nextInt());

		ifpContract.setIfpName(RandomTestUtil.randomString());

		ifpContract.setIfpNo(RandomTestUtil.randomString());

		ifpContract.setModifiedBy(RandomTestUtil.nextInt());

		ifpContract.setInboundStatus(RandomTestUtil.randomString());

		ifpContract.setIfpModelSid(RandomTestUtil.nextInt());

		_ifpContracts.add(_persistence.update(ifpContract));

		return ifpContract;
	}

	private List<IfpContract> _ifpContracts = new ArrayList<IfpContract>();
	private IfpContractPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}