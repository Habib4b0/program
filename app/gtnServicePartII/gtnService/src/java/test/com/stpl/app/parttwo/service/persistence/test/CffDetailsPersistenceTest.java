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
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.parttwo.exception.NoSuchCffDetailsException;
import com.stpl.app.parttwo.model.CffDetails;
import com.stpl.app.parttwo.service.CffDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.CffDetailsPersistence;
import com.stpl.app.parttwo.service.persistence.CffDetailsUtil;

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
public class CffDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = CffDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CffDetails> iterator = _cffDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CffDetails cffDetails = _persistence.create(pk);

		Assert.assertNotNull(cffDetails);

		Assert.assertEquals(cffDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CffDetails newCffDetails = addCffDetails();

		_persistence.remove(newCffDetails);

		CffDetails existingCffDetails = _persistence.fetchByPrimaryKey(newCffDetails.getPrimaryKey());

		Assert.assertNull(existingCffDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCffDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CffDetails newCffDetails = _persistence.create(pk);

		newCffDetails.setCcpDetailsSid(RandomTestUtil.nextInt());

		newCffDetails.setProjectionMasterSid(RandomTestUtil.nextInt());

		newCffDetails.setCffMasterSid(RandomTestUtil.nextInt());

		newCffDetails.setInboundStatus(RandomTestUtil.randomString());

		_cffDetailses.add(_persistence.update(newCffDetails));

		CffDetails existingCffDetails = _persistence.findByPrimaryKey(newCffDetails.getPrimaryKey());

		Assert.assertEquals(existingCffDetails.getCcpDetailsSid(),
			newCffDetails.getCcpDetailsSid());
		Assert.assertEquals(existingCffDetails.getProjectionMasterSid(),
			newCffDetails.getProjectionMasterSid());
		Assert.assertEquals(existingCffDetails.getCffMasterSid(),
			newCffDetails.getCffMasterSid());
		Assert.assertEquals(existingCffDetails.getInboundStatus(),
			newCffDetails.getInboundStatus());
		Assert.assertEquals(existingCffDetails.getCffDetailsSid(),
			newCffDetails.getCffDetailsSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CffDetails newCffDetails = addCffDetails();

		CffDetails existingCffDetails = _persistence.findByPrimaryKey(newCffDetails.getPrimaryKey());

		Assert.assertEquals(existingCffDetails, newCffDetails);
	}

	@Test(expected = NoSuchCffDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CffDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CFF_DETAILS",
			"ccpDetailsSid", true, "projectionMasterSid", true, "cffMasterSid",
			true, "inboundStatus", true, "cffDetailsSid", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CffDetails newCffDetails = addCffDetails();

		CffDetails existingCffDetails = _persistence.fetchByPrimaryKey(newCffDetails.getPrimaryKey());

		Assert.assertEquals(existingCffDetails, newCffDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CffDetails missingCffDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCffDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CffDetails newCffDetails1 = addCffDetails();
		CffDetails newCffDetails2 = addCffDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCffDetails1.getPrimaryKey());
		primaryKeys.add(newCffDetails2.getPrimaryKey());

		Map<Serializable, CffDetails> cffDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, cffDetailses.size());
		Assert.assertEquals(newCffDetails1,
			cffDetailses.get(newCffDetails1.getPrimaryKey()));
		Assert.assertEquals(newCffDetails2,
			cffDetailses.get(newCffDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CffDetails> cffDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cffDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CffDetails newCffDetails = addCffDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCffDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CffDetails> cffDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cffDetailses.size());
		Assert.assertEquals(newCffDetails,
			cffDetailses.get(newCffDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CffDetails> cffDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cffDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CffDetails newCffDetails = addCffDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCffDetails.getPrimaryKey());

		Map<Serializable, CffDetails> cffDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cffDetailses.size());
		Assert.assertEquals(newCffDetails,
			cffDetailses.get(newCffDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CffDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CffDetails>() {
				@Override
				public void performAction(CffDetails cffDetails) {
					Assert.assertNotNull(cffDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CffDetails newCffDetails = addCffDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("cffDetailsSid",
				newCffDetails.getCffDetailsSid()));

		List<CffDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CffDetails existingCffDetails = result.get(0);

		Assert.assertEquals(existingCffDetails, newCffDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("cffDetailsSid",
				RandomTestUtil.nextInt()));

		List<CffDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CffDetails newCffDetails = addCffDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"cffDetailsSid"));

		Object newCffDetailsSid = newCffDetails.getCffDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("cffDetailsSid",
				new Object[] { newCffDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCffDetailsSid = result.get(0);

		Assert.assertEquals(existingCffDetailsSid, newCffDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"cffDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("cffDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected CffDetails addCffDetails() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CffDetails cffDetails = _persistence.create(pk);

		cffDetails.setCcpDetailsSid(RandomTestUtil.nextInt());

		cffDetails.setProjectionMasterSid(RandomTestUtil.nextInt());

		cffDetails.setCffMasterSid(RandomTestUtil.nextInt());

		cffDetails.setInboundStatus(RandomTestUtil.randomString());

		_cffDetailses.add(_persistence.update(cffDetails));

		return cffDetails;
	}

	private List<CffDetails> _cffDetailses = new ArrayList<CffDetails>();
	private CffDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}