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

package com.stpl.app.parttwo.service.persistence.test;

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

import com.stpl.app.parttwo.exception.NoSuchStAccClosureDetailsException;
import com.stpl.app.parttwo.model.StAccClosureDetails;
import com.stpl.app.parttwo.service.StAccClosureDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.StAccClosureDetailsPersistence;
import com.stpl.app.parttwo.service.persistence.StAccClosureDetailsUtil;

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
public class StAccClosureDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = StAccClosureDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<StAccClosureDetails> iterator = _stAccClosureDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		StAccClosureDetails stAccClosureDetails = _persistence.create(pk);

		Assert.assertNotNull(stAccClosureDetails);

		Assert.assertEquals(stAccClosureDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		StAccClosureDetails newStAccClosureDetails = addStAccClosureDetails();

		_persistence.remove(newStAccClosureDetails);

		StAccClosureDetails existingStAccClosureDetails = _persistence.fetchByPrimaryKey(newStAccClosureDetails.getPrimaryKey());

		Assert.assertNull(existingStAccClosureDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addStAccClosureDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		StAccClosureDetails newStAccClosureDetails = _persistence.create(pk);

		newStAccClosureDetails.setLastModifiedDate(RandomTestUtil.nextDate());

		newStAccClosureDetails.setCheckRecord(RandomTestUtil.randomBoolean());

		newStAccClosureDetails.setContractName(RandomTestUtil.randomString());

		newStAccClosureDetails.setUserId(RandomTestUtil.nextInt());

		newStAccClosureDetails.setItemMasterSid(RandomTestUtil.nextInt());

		newStAccClosureDetails.setModuleName(RandomTestUtil.randomString());

		newStAccClosureDetails.setCompanyName(RandomTestUtil.randomString());

		newStAccClosureDetails.setBrandName(RandomTestUtil.randomString());

		newStAccClosureDetails.setCompanyCostCenter(RandomTestUtil.randomString());

		newStAccClosureDetails.setCompanyNo(RandomTestUtil.randomString());

		newStAccClosureDetails.setContractMasterSid(RandomTestUtil.nextInt());

		newStAccClosureDetails.setSessionId(RandomTestUtil.nextInt());

		newStAccClosureDetails.setCcpDetailsSid(RandomTestUtil.nextInt());

		newStAccClosureDetails.setItemName(RandomTestUtil.randomString());

		newStAccClosureDetails.setRsModelSid(RandomTestUtil.nextInt());

		newStAccClosureDetails.setContractNo(RandomTestUtil.randomString());

		newStAccClosureDetails.setCompanyMasterSid(RandomTestUtil.nextInt());

		newStAccClosureDetails.setNdc8(RandomTestUtil.randomString());

		_stAccClosureDetailses.add(_persistence.update(newStAccClosureDetails));

		StAccClosureDetails existingStAccClosureDetails = _persistence.findByPrimaryKey(newStAccClosureDetails.getPrimaryKey());

		Assert.assertEquals(Time.getShortTimestamp(
				existingStAccClosureDetails.getLastModifiedDate()),
			Time.getShortTimestamp(newStAccClosureDetails.getLastModifiedDate()));
		Assert.assertEquals(existingStAccClosureDetails.getCheckRecord(),
			newStAccClosureDetails.getCheckRecord());
		Assert.assertEquals(existingStAccClosureDetails.getContractName(),
			newStAccClosureDetails.getContractName());
		Assert.assertEquals(existingStAccClosureDetails.getUserId(),
			newStAccClosureDetails.getUserId());
		Assert.assertEquals(existingStAccClosureDetails.getItemMasterSid(),
			newStAccClosureDetails.getItemMasterSid());
		Assert.assertEquals(existingStAccClosureDetails.getModuleName(),
			newStAccClosureDetails.getModuleName());
		Assert.assertEquals(existingStAccClosureDetails.getCompanyName(),
			newStAccClosureDetails.getCompanyName());
		Assert.assertEquals(existingStAccClosureDetails.getBrandName(),
			newStAccClosureDetails.getBrandName());
		Assert.assertEquals(existingStAccClosureDetails.getCompanyCostCenter(),
			newStAccClosureDetails.getCompanyCostCenter());
		Assert.assertEquals(existingStAccClosureDetails.getCompanyNo(),
			newStAccClosureDetails.getCompanyNo());
		Assert.assertEquals(existingStAccClosureDetails.getContractMasterSid(),
			newStAccClosureDetails.getContractMasterSid());
		Assert.assertEquals(existingStAccClosureDetails.getSessionId(),
			newStAccClosureDetails.getSessionId());
		Assert.assertEquals(existingStAccClosureDetails.getCcpDetailsSid(),
			newStAccClosureDetails.getCcpDetailsSid());
		Assert.assertEquals(existingStAccClosureDetails.getItemName(),
			newStAccClosureDetails.getItemName());
		Assert.assertEquals(existingStAccClosureDetails.getAccClosureMasterSid(),
			newStAccClosureDetails.getAccClosureMasterSid());
		Assert.assertEquals(existingStAccClosureDetails.getRsModelSid(),
			newStAccClosureDetails.getRsModelSid());
		Assert.assertEquals(existingStAccClosureDetails.getContractNo(),
			newStAccClosureDetails.getContractNo());
		Assert.assertEquals(existingStAccClosureDetails.getCompanyMasterSid(),
			newStAccClosureDetails.getCompanyMasterSid());
		Assert.assertEquals(existingStAccClosureDetails.getNdc8(),
			newStAccClosureDetails.getNdc8());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		StAccClosureDetails newStAccClosureDetails = addStAccClosureDetails();

		StAccClosureDetails existingStAccClosureDetails = _persistence.findByPrimaryKey(newStAccClosureDetails.getPrimaryKey());

		Assert.assertEquals(existingStAccClosureDetails, newStAccClosureDetails);
	}

	@Test(expected = NoSuchStAccClosureDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<StAccClosureDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("ST_ACC_CLOSURE_DETAILS",
			"lastModifiedDate", true, "checkRecord", true, "contractName",
			true, "userId", true, "itemMasterSid", true, "moduleName", true,
			"companyName", true, "brandName", true, "companyCostCenter", true,
			"companyNo", true, "contractMasterSid", true, "sessionId", true,
			"ccpDetailsSid", true, "itemName", true, "accClosureMasterSid",
			true, "rsModelSid", true, "contractNo", true, "companyMasterSid",
			true, "ndc8", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		StAccClosureDetails newStAccClosureDetails = addStAccClosureDetails();

		StAccClosureDetails existingStAccClosureDetails = _persistence.fetchByPrimaryKey(newStAccClosureDetails.getPrimaryKey());

		Assert.assertEquals(existingStAccClosureDetails, newStAccClosureDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		StAccClosureDetails missingStAccClosureDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingStAccClosureDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		StAccClosureDetails newStAccClosureDetails1 = addStAccClosureDetails();
		StAccClosureDetails newStAccClosureDetails2 = addStAccClosureDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStAccClosureDetails1.getPrimaryKey());
		primaryKeys.add(newStAccClosureDetails2.getPrimaryKey());

		Map<Serializable, StAccClosureDetails> stAccClosureDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, stAccClosureDetailses.size());
		Assert.assertEquals(newStAccClosureDetails1,
			stAccClosureDetailses.get(newStAccClosureDetails1.getPrimaryKey()));
		Assert.assertEquals(newStAccClosureDetails2,
			stAccClosureDetailses.get(newStAccClosureDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, StAccClosureDetails> stAccClosureDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stAccClosureDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		StAccClosureDetails newStAccClosureDetails = addStAccClosureDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStAccClosureDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, StAccClosureDetails> stAccClosureDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stAccClosureDetailses.size());
		Assert.assertEquals(newStAccClosureDetails,
			stAccClosureDetailses.get(newStAccClosureDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, StAccClosureDetails> stAccClosureDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stAccClosureDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		StAccClosureDetails newStAccClosureDetails = addStAccClosureDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStAccClosureDetails.getPrimaryKey());

		Map<Serializable, StAccClosureDetails> stAccClosureDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stAccClosureDetailses.size());
		Assert.assertEquals(newStAccClosureDetails,
			stAccClosureDetailses.get(newStAccClosureDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = StAccClosureDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<StAccClosureDetails>() {
				@Override
				public void performAction(
					StAccClosureDetails stAccClosureDetails) {
					Assert.assertNotNull(stAccClosureDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		StAccClosureDetails newStAccClosureDetails = addStAccClosureDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StAccClosureDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("accClosureMasterSid",
				newStAccClosureDetails.getAccClosureMasterSid()));

		List<StAccClosureDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		StAccClosureDetails existingStAccClosureDetails = result.get(0);

		Assert.assertEquals(existingStAccClosureDetails, newStAccClosureDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StAccClosureDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("accClosureMasterSid",
				RandomTestUtil.nextInt()));

		List<StAccClosureDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		StAccClosureDetails newStAccClosureDetails = addStAccClosureDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StAccClosureDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"accClosureMasterSid"));

		Object newAccClosureMasterSid = newStAccClosureDetails.getAccClosureMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("accClosureMasterSid",
				new Object[] { newAccClosureMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingAccClosureMasterSid = result.get(0);

		Assert.assertEquals(existingAccClosureMasterSid, newAccClosureMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StAccClosureDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"accClosureMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("accClosureMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected StAccClosureDetails addStAccClosureDetails()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		StAccClosureDetails stAccClosureDetails = _persistence.create(pk);

		stAccClosureDetails.setLastModifiedDate(RandomTestUtil.nextDate());

		stAccClosureDetails.setCheckRecord(RandomTestUtil.randomBoolean());

		stAccClosureDetails.setContractName(RandomTestUtil.randomString());

		stAccClosureDetails.setUserId(RandomTestUtil.nextInt());

		stAccClosureDetails.setItemMasterSid(RandomTestUtil.nextInt());

		stAccClosureDetails.setModuleName(RandomTestUtil.randomString());

		stAccClosureDetails.setCompanyName(RandomTestUtil.randomString());

		stAccClosureDetails.setBrandName(RandomTestUtil.randomString());

		stAccClosureDetails.setCompanyCostCenter(RandomTestUtil.randomString());

		stAccClosureDetails.setCompanyNo(RandomTestUtil.randomString());

		stAccClosureDetails.setContractMasterSid(RandomTestUtil.nextInt());

		stAccClosureDetails.setSessionId(RandomTestUtil.nextInt());

		stAccClosureDetails.setCcpDetailsSid(RandomTestUtil.nextInt());

		stAccClosureDetails.setItemName(RandomTestUtil.randomString());

		stAccClosureDetails.setRsModelSid(RandomTestUtil.nextInt());

		stAccClosureDetails.setContractNo(RandomTestUtil.randomString());

		stAccClosureDetails.setCompanyMasterSid(RandomTestUtil.nextInt());

		stAccClosureDetails.setNdc8(RandomTestUtil.randomString());

		_stAccClosureDetailses.add(_persistence.update(stAccClosureDetails));

		return stAccClosureDetails;
	}

	private List<StAccClosureDetails> _stAccClosureDetailses = new ArrayList<StAccClosureDetails>();
	private StAccClosureDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}