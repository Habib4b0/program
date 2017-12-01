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

import com.stpl.app.exception.NoSuchContractAliasMasterException;
import com.stpl.app.model.ContractAliasMaster;
import com.stpl.app.service.ContractAliasMasterLocalServiceUtil;
import com.stpl.app.service.persistence.ContractAliasMasterPersistence;
import com.stpl.app.service.persistence.ContractAliasMasterUtil;

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
public class ContractAliasMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ContractAliasMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ContractAliasMaster> iterator = _contractAliasMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ContractAliasMaster contractAliasMaster = _persistence.create(pk);

		Assert.assertNotNull(contractAliasMaster);

		Assert.assertEquals(contractAliasMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ContractAliasMaster newContractAliasMaster = addContractAliasMaster();

		_persistence.remove(newContractAliasMaster);

		ContractAliasMaster existingContractAliasMaster = _persistence.fetchByPrimaryKey(newContractAliasMaster.getPrimaryKey());

		Assert.assertNull(existingContractAliasMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addContractAliasMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ContractAliasMaster newContractAliasMaster = _persistence.create(pk);

		newContractAliasMaster.setContractAliasType(RandomTestUtil.nextInt());

		newContractAliasMaster.setTpCompanyMasterSid(RandomTestUtil.nextInt());

		newContractAliasMaster.setEndDate(RandomTestUtil.nextDate());

		newContractAliasMaster.setModifiedDate(RandomTestUtil.nextDate());

		newContractAliasMaster.setContractAliasNo(RandomTestUtil.randomString());

		newContractAliasMaster.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newContractAliasMaster.setStartDate(RandomTestUtil.nextDate());

		newContractAliasMaster.setCreatedDate(RandomTestUtil.nextDate());

		newContractAliasMaster.setSource(RandomTestUtil.randomString());

		newContractAliasMaster.setCreatedBy(RandomTestUtil.nextInt());

		newContractAliasMaster.setContractMasterSid(RandomTestUtil.nextInt());

		newContractAliasMaster.setBatchId(RandomTestUtil.randomString());

		newContractAliasMaster.setContractAliasName(RandomTestUtil.randomString());

		newContractAliasMaster.setModifiedBy(RandomTestUtil.nextInt());

		newContractAliasMaster.setInboundStatus(RandomTestUtil.randomString());

		_contractAliasMasters.add(_persistence.update(newContractAliasMaster));

		ContractAliasMaster existingContractAliasMaster = _persistence.findByPrimaryKey(newContractAliasMaster.getPrimaryKey());

		Assert.assertEquals(existingContractAliasMaster.getContractAliasType(),
			newContractAliasMaster.getContractAliasType());
		Assert.assertEquals(existingContractAliasMaster.getTpCompanyMasterSid(),
			newContractAliasMaster.getTpCompanyMasterSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingContractAliasMaster.getEndDate()),
			Time.getShortTimestamp(newContractAliasMaster.getEndDate()));
		Assert.assertEquals(existingContractAliasMaster.getContractAliasMasterSid(),
			newContractAliasMaster.getContractAliasMasterSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingContractAliasMaster.getModifiedDate()),
			Time.getShortTimestamp(newContractAliasMaster.getModifiedDate()));
		Assert.assertEquals(existingContractAliasMaster.getContractAliasNo(),
			newContractAliasMaster.getContractAliasNo());
		Assert.assertEquals(existingContractAliasMaster.getRecordLockStatus(),
			newContractAliasMaster.getRecordLockStatus());
		Assert.assertEquals(Time.getShortTimestamp(
				existingContractAliasMaster.getStartDate()),
			Time.getShortTimestamp(newContractAliasMaster.getStartDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingContractAliasMaster.getCreatedDate()),
			Time.getShortTimestamp(newContractAliasMaster.getCreatedDate()));
		Assert.assertEquals(existingContractAliasMaster.getSource(),
			newContractAliasMaster.getSource());
		Assert.assertEquals(existingContractAliasMaster.getCreatedBy(),
			newContractAliasMaster.getCreatedBy());
		Assert.assertEquals(existingContractAliasMaster.getContractMasterSid(),
			newContractAliasMaster.getContractMasterSid());
		Assert.assertEquals(existingContractAliasMaster.getBatchId(),
			newContractAliasMaster.getBatchId());
		Assert.assertEquals(existingContractAliasMaster.getContractAliasName(),
			newContractAliasMaster.getContractAliasName());
		Assert.assertEquals(existingContractAliasMaster.getModifiedBy(),
			newContractAliasMaster.getModifiedBy());
		Assert.assertEquals(existingContractAliasMaster.getInboundStatus(),
			newContractAliasMaster.getInboundStatus());
	}

	@Test
	public void testCountByContractSystemId() throws Exception {
		_persistence.countByContractSystemId(RandomTestUtil.nextInt());

		_persistence.countByContractSystemId(0);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ContractAliasMaster newContractAliasMaster = addContractAliasMaster();

		ContractAliasMaster existingContractAliasMaster = _persistence.findByPrimaryKey(newContractAliasMaster.getPrimaryKey());

		Assert.assertEquals(existingContractAliasMaster, newContractAliasMaster);
	}

	@Test(expected = NoSuchContractAliasMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<ContractAliasMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CONTRACT_ALIAS_MASTER",
			"contractAliasType", true, "tpCompanyMasterSid", true, "endDate",
			true, "contractAliasMasterSid", true, "modifiedDate", true,
			"contractAliasNo", true, "recordLockStatus", true, "startDate",
			true, "createdDate", true, "source", true, "createdBy", true,
			"contractMasterSid", true, "batchId", true, "contractAliasName",
			true, "modifiedBy", true, "inboundStatus", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ContractAliasMaster newContractAliasMaster = addContractAliasMaster();

		ContractAliasMaster existingContractAliasMaster = _persistence.fetchByPrimaryKey(newContractAliasMaster.getPrimaryKey());

		Assert.assertEquals(existingContractAliasMaster, newContractAliasMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ContractAliasMaster missingContractAliasMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingContractAliasMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ContractAliasMaster newContractAliasMaster1 = addContractAliasMaster();
		ContractAliasMaster newContractAliasMaster2 = addContractAliasMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newContractAliasMaster1.getPrimaryKey());
		primaryKeys.add(newContractAliasMaster2.getPrimaryKey());

		Map<Serializable, ContractAliasMaster> contractAliasMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, contractAliasMasters.size());
		Assert.assertEquals(newContractAliasMaster1,
			contractAliasMasters.get(newContractAliasMaster1.getPrimaryKey()));
		Assert.assertEquals(newContractAliasMaster2,
			contractAliasMasters.get(newContractAliasMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ContractAliasMaster> contractAliasMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(contractAliasMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ContractAliasMaster newContractAliasMaster = addContractAliasMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newContractAliasMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ContractAliasMaster> contractAliasMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, contractAliasMasters.size());
		Assert.assertEquals(newContractAliasMaster,
			contractAliasMasters.get(newContractAliasMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ContractAliasMaster> contractAliasMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(contractAliasMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ContractAliasMaster newContractAliasMaster = addContractAliasMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newContractAliasMaster.getPrimaryKey());

		Map<Serializable, ContractAliasMaster> contractAliasMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, contractAliasMasters.size());
		Assert.assertEquals(newContractAliasMaster,
			contractAliasMasters.get(newContractAliasMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ContractAliasMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ContractAliasMaster>() {
				@Override
				public void performAction(
					ContractAliasMaster contractAliasMaster) {
					Assert.assertNotNull(contractAliasMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ContractAliasMaster newContractAliasMaster = addContractAliasMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ContractAliasMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("contractAliasMasterSid",
				newContractAliasMaster.getContractAliasMasterSid()));

		List<ContractAliasMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ContractAliasMaster existingContractAliasMaster = result.get(0);

		Assert.assertEquals(existingContractAliasMaster, newContractAliasMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ContractAliasMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("contractAliasMasterSid",
				RandomTestUtil.nextInt()));

		List<ContractAliasMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ContractAliasMaster newContractAliasMaster = addContractAliasMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ContractAliasMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"contractAliasMasterSid"));

		Object newContractAliasMasterSid = newContractAliasMaster.getContractAliasMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("contractAliasMasterSid",
				new Object[] { newContractAliasMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingContractAliasMasterSid = result.get(0);

		Assert.assertEquals(existingContractAliasMasterSid,
			newContractAliasMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ContractAliasMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"contractAliasMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("contractAliasMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ContractAliasMaster addContractAliasMaster()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		ContractAliasMaster contractAliasMaster = _persistence.create(pk);

		contractAliasMaster.setContractAliasType(RandomTestUtil.nextInt());

		contractAliasMaster.setTpCompanyMasterSid(RandomTestUtil.nextInt());

		contractAliasMaster.setEndDate(RandomTestUtil.nextDate());

		contractAliasMaster.setModifiedDate(RandomTestUtil.nextDate());

		contractAliasMaster.setContractAliasNo(RandomTestUtil.randomString());

		contractAliasMaster.setRecordLockStatus(RandomTestUtil.randomBoolean());

		contractAliasMaster.setStartDate(RandomTestUtil.nextDate());

		contractAliasMaster.setCreatedDate(RandomTestUtil.nextDate());

		contractAliasMaster.setSource(RandomTestUtil.randomString());

		contractAliasMaster.setCreatedBy(RandomTestUtil.nextInt());

		contractAliasMaster.setContractMasterSid(RandomTestUtil.nextInt());

		contractAliasMaster.setBatchId(RandomTestUtil.randomString());

		contractAliasMaster.setContractAliasName(RandomTestUtil.randomString());

		contractAliasMaster.setModifiedBy(RandomTestUtil.nextInt());

		contractAliasMaster.setInboundStatus(RandomTestUtil.randomString());

		_contractAliasMasters.add(_persistence.update(contractAliasMaster));

		return contractAliasMaster;
	}

	private List<ContractAliasMaster> _contractAliasMasters = new ArrayList<ContractAliasMaster>();
	private ContractAliasMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}