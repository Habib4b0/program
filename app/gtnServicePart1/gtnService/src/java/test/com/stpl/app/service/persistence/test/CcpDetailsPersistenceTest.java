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
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchCcpDetailsException;
import com.stpl.app.model.CcpDetails;
import com.stpl.app.service.CcpDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.CcpDetailsPersistence;
import com.stpl.app.service.persistence.CcpDetailsUtil;

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
public class CcpDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = CcpDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CcpDetails> iterator = _ccpDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CcpDetails ccpDetails = _persistence.create(pk);

		Assert.assertNotNull(ccpDetails);

		Assert.assertEquals(ccpDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CcpDetails newCcpDetails = addCcpDetails();

		_persistence.remove(newCcpDetails);

		CcpDetails existingCcpDetails = _persistence.fetchByPrimaryKey(newCcpDetails.getPrimaryKey());

		Assert.assertNull(existingCcpDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCcpDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CcpDetails newCcpDetails = _persistence.create(pk);

		newCcpDetails.setItemMasterSid(RandomTestUtil.nextInt());

		newCcpDetails.setContractMasterSid(RandomTestUtil.nextInt());

		newCcpDetails.setCompanyMasterSid(RandomTestUtil.nextInt());

		_ccpDetailses.add(_persistence.update(newCcpDetails));

		CcpDetails existingCcpDetails = _persistence.findByPrimaryKey(newCcpDetails.getPrimaryKey());

		Assert.assertEquals(existingCcpDetails.getItemMasterSid(),
			newCcpDetails.getItemMasterSid());
		Assert.assertEquals(existingCcpDetails.getContractMasterSid(),
			newCcpDetails.getContractMasterSid());
		Assert.assertEquals(existingCcpDetails.getCcpDetailsSid(),
			newCcpDetails.getCcpDetailsSid());
		Assert.assertEquals(existingCcpDetails.getCompanyMasterSid(),
			newCcpDetails.getCompanyMasterSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CcpDetails newCcpDetails = addCcpDetails();

		CcpDetails existingCcpDetails = _persistence.findByPrimaryKey(newCcpDetails.getPrimaryKey());

		Assert.assertEquals(existingCcpDetails, newCcpDetails);
	}

	@Test(expected = NoSuchCcpDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CcpDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CCP_DETAILS",
			"itemMasterSid", true, "contractMasterSid", true, "ccpDetailsSid",
			true, "companyMasterSid", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CcpDetails newCcpDetails = addCcpDetails();

		CcpDetails existingCcpDetails = _persistence.fetchByPrimaryKey(newCcpDetails.getPrimaryKey());

		Assert.assertEquals(existingCcpDetails, newCcpDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CcpDetails missingCcpDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCcpDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CcpDetails newCcpDetails1 = addCcpDetails();
		CcpDetails newCcpDetails2 = addCcpDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCcpDetails1.getPrimaryKey());
		primaryKeys.add(newCcpDetails2.getPrimaryKey());

		Map<Serializable, CcpDetails> ccpDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ccpDetailses.size());
		Assert.assertEquals(newCcpDetails1,
			ccpDetailses.get(newCcpDetails1.getPrimaryKey()));
		Assert.assertEquals(newCcpDetails2,
			ccpDetailses.get(newCcpDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CcpDetails> ccpDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ccpDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CcpDetails newCcpDetails = addCcpDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCcpDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CcpDetails> ccpDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ccpDetailses.size());
		Assert.assertEquals(newCcpDetails,
			ccpDetailses.get(newCcpDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CcpDetails> ccpDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ccpDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CcpDetails newCcpDetails = addCcpDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCcpDetails.getPrimaryKey());

		Map<Serializable, CcpDetails> ccpDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ccpDetailses.size());
		Assert.assertEquals(newCcpDetails,
			ccpDetailses.get(newCcpDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CcpDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CcpDetails>() {
				@Override
				public void performAction(CcpDetails ccpDetails) {
					Assert.assertNotNull(ccpDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CcpDetails newCcpDetails = addCcpDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CcpDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ccpDetailsSid",
				newCcpDetails.getCcpDetailsSid()));

		List<CcpDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CcpDetails existingCcpDetails = result.get(0);

		Assert.assertEquals(existingCcpDetails, newCcpDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CcpDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ccpDetailsSid",
				RandomTestUtil.nextInt()));

		List<CcpDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CcpDetails newCcpDetails = addCcpDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CcpDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ccpDetailsSid"));

		Object newCcpDetailsSid = newCcpDetails.getCcpDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("ccpDetailsSid",
				new Object[] { newCcpDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCcpDetailsSid = result.get(0);

		Assert.assertEquals(existingCcpDetailsSid, newCcpDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CcpDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ccpDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("ccpDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected CcpDetails addCcpDetails() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CcpDetails ccpDetails = _persistence.create(pk);

		ccpDetails.setItemMasterSid(RandomTestUtil.nextInt());

		ccpDetails.setContractMasterSid(RandomTestUtil.nextInt());

		ccpDetails.setCompanyMasterSid(RandomTestUtil.nextInt());

		_ccpDetailses.add(_persistence.update(ccpDetails));

		return ccpDetails;
	}

	private List<CcpDetails> _ccpDetailses = new ArrayList<CcpDetails>();
	private CcpDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}