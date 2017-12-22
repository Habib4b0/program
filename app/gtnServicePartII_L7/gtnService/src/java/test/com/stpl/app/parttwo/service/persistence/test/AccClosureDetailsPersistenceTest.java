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

import com.stpl.app.parttwo.exception.NoSuchAccClosureDetailsException;
import com.stpl.app.parttwo.model.AccClosureDetails;
import com.stpl.app.parttwo.service.AccClosureDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.AccClosureDetailsPersistence;
import com.stpl.app.parttwo.service.persistence.AccClosureDetailsUtil;

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
public class AccClosureDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = AccClosureDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<AccClosureDetails> iterator = _accClosureDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AccClosureDetails accClosureDetails = _persistence.create(pk);

		Assert.assertNotNull(accClosureDetails);

		Assert.assertEquals(accClosureDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		AccClosureDetails newAccClosureDetails = addAccClosureDetails();

		_persistence.remove(newAccClosureDetails);

		AccClosureDetails existingAccClosureDetails = _persistence.fetchByPrimaryKey(newAccClosureDetails.getPrimaryKey());

		Assert.assertNull(existingAccClosureDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addAccClosureDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AccClosureDetails newAccClosureDetails = _persistence.create(pk);

		newAccClosureDetails.setCcpDetailsSid(RandomTestUtil.nextInt());

		newAccClosureDetails.setAccClosureMasterSid(RandomTestUtil.nextInt());

		newAccClosureDetails.setRsModelSid(RandomTestUtil.nextInt());

		_accClosureDetailses.add(_persistence.update(newAccClosureDetails));

		AccClosureDetails existingAccClosureDetails = _persistence.findByPrimaryKey(newAccClosureDetails.getPrimaryKey());

		Assert.assertEquals(existingAccClosureDetails.getAccClosureDetailsSid(),
			newAccClosureDetails.getAccClosureDetailsSid());
		Assert.assertEquals(existingAccClosureDetails.getCcpDetailsSid(),
			newAccClosureDetails.getCcpDetailsSid());
		Assert.assertEquals(existingAccClosureDetails.getAccClosureMasterSid(),
			newAccClosureDetails.getAccClosureMasterSid());
		Assert.assertEquals(existingAccClosureDetails.getRsModelSid(),
			newAccClosureDetails.getRsModelSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		AccClosureDetails newAccClosureDetails = addAccClosureDetails();

		AccClosureDetails existingAccClosureDetails = _persistence.findByPrimaryKey(newAccClosureDetails.getPrimaryKey());

		Assert.assertEquals(existingAccClosureDetails, newAccClosureDetails);
	}

	@Test(expected = NoSuchAccClosureDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<AccClosureDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("ACC_CLOSURE_DETAILS",
			"accClosureDetailsSid", true, "ccpDetailsSid", true,
			"accClosureMasterSid", true, "rsModelSid", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		AccClosureDetails newAccClosureDetails = addAccClosureDetails();

		AccClosureDetails existingAccClosureDetails = _persistence.fetchByPrimaryKey(newAccClosureDetails.getPrimaryKey());

		Assert.assertEquals(existingAccClosureDetails, newAccClosureDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		AccClosureDetails missingAccClosureDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingAccClosureDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		AccClosureDetails newAccClosureDetails1 = addAccClosureDetails();
		AccClosureDetails newAccClosureDetails2 = addAccClosureDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAccClosureDetails1.getPrimaryKey());
		primaryKeys.add(newAccClosureDetails2.getPrimaryKey());

		Map<Serializable, AccClosureDetails> accClosureDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, accClosureDetailses.size());
		Assert.assertEquals(newAccClosureDetails1,
			accClosureDetailses.get(newAccClosureDetails1.getPrimaryKey()));
		Assert.assertEquals(newAccClosureDetails2,
			accClosureDetailses.get(newAccClosureDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, AccClosureDetails> accClosureDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(accClosureDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		AccClosureDetails newAccClosureDetails = addAccClosureDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAccClosureDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, AccClosureDetails> accClosureDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, accClosureDetailses.size());
		Assert.assertEquals(newAccClosureDetails,
			accClosureDetailses.get(newAccClosureDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, AccClosureDetails> accClosureDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(accClosureDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		AccClosureDetails newAccClosureDetails = addAccClosureDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAccClosureDetails.getPrimaryKey());

		Map<Serializable, AccClosureDetails> accClosureDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, accClosureDetailses.size());
		Assert.assertEquals(newAccClosureDetails,
			accClosureDetailses.get(newAccClosureDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = AccClosureDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<AccClosureDetails>() {
				@Override
				public void performAction(AccClosureDetails accClosureDetails) {
					Assert.assertNotNull(accClosureDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		AccClosureDetails newAccClosureDetails = addAccClosureDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AccClosureDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("accClosureDetailsSid",
				newAccClosureDetails.getAccClosureDetailsSid()));

		List<AccClosureDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		AccClosureDetails existingAccClosureDetails = result.get(0);

		Assert.assertEquals(existingAccClosureDetails, newAccClosureDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AccClosureDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("accClosureDetailsSid",
				RandomTestUtil.nextInt()));

		List<AccClosureDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		AccClosureDetails newAccClosureDetails = addAccClosureDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AccClosureDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"accClosureDetailsSid"));

		Object newAccClosureDetailsSid = newAccClosureDetails.getAccClosureDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("accClosureDetailsSid",
				new Object[] { newAccClosureDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingAccClosureDetailsSid = result.get(0);

		Assert.assertEquals(existingAccClosureDetailsSid,
			newAccClosureDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AccClosureDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"accClosureDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("accClosureDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected AccClosureDetails addAccClosureDetails()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		AccClosureDetails accClosureDetails = _persistence.create(pk);

		accClosureDetails.setCcpDetailsSid(RandomTestUtil.nextInt());

		accClosureDetails.setAccClosureMasterSid(RandomTestUtil.nextInt());

		accClosureDetails.setRsModelSid(RandomTestUtil.nextInt());

		_accClosureDetailses.add(_persistence.update(accClosureDetails));

		return accClosureDetails;
	}

	private List<AccClosureDetails> _accClosureDetailses = new ArrayList<AccClosureDetails>();
	private AccClosureDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}