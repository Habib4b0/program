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

import com.stpl.app.exception.NoSuchNaProjDetailsException;
import com.stpl.app.model.NaProjDetails;
import com.stpl.app.service.NaProjDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.NaProjDetailsPersistence;
import com.stpl.app.service.persistence.NaProjDetailsUtil;

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
public class NaProjDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = NaProjDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<NaProjDetails> iterator = _naProjDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		NaProjDetails naProjDetails = _persistence.create(pk);

		Assert.assertNotNull(naProjDetails);

		Assert.assertEquals(naProjDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		NaProjDetails newNaProjDetails = addNaProjDetails();

		_persistence.remove(newNaProjDetails);

		NaProjDetails existingNaProjDetails = _persistence.fetchByPrimaryKey(newNaProjDetails.getPrimaryKey());

		Assert.assertNull(existingNaProjDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addNaProjDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		NaProjDetails newNaProjDetails = _persistence.create(pk);

		newNaProjDetails.setItemMasterSid(RandomTestUtil.nextInt());

		newNaProjDetails.setNaProjMasterSid(RandomTestUtil.nextInt());

		_naProjDetailses.add(_persistence.update(newNaProjDetails));

		NaProjDetails existingNaProjDetails = _persistence.findByPrimaryKey(newNaProjDetails.getPrimaryKey());

		Assert.assertEquals(existingNaProjDetails.getItemMasterSid(),
			newNaProjDetails.getItemMasterSid());
		Assert.assertEquals(existingNaProjDetails.getNaProjMasterSid(),
			newNaProjDetails.getNaProjMasterSid());
		Assert.assertEquals(existingNaProjDetails.getNaProjDetailsSid(),
			newNaProjDetails.getNaProjDetailsSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		NaProjDetails newNaProjDetails = addNaProjDetails();

		NaProjDetails existingNaProjDetails = _persistence.findByPrimaryKey(newNaProjDetails.getPrimaryKey());

		Assert.assertEquals(existingNaProjDetails, newNaProjDetails);
	}

	@Test(expected = NoSuchNaProjDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<NaProjDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("NA_PROJ_DETAILS",
			"itemMasterSid", true, "naProjMasterSid", true, "naProjDetailsSid",
			true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		NaProjDetails newNaProjDetails = addNaProjDetails();

		NaProjDetails existingNaProjDetails = _persistence.fetchByPrimaryKey(newNaProjDetails.getPrimaryKey());

		Assert.assertEquals(existingNaProjDetails, newNaProjDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		NaProjDetails missingNaProjDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingNaProjDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		NaProjDetails newNaProjDetails1 = addNaProjDetails();
		NaProjDetails newNaProjDetails2 = addNaProjDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNaProjDetails1.getPrimaryKey());
		primaryKeys.add(newNaProjDetails2.getPrimaryKey());

		Map<Serializable, NaProjDetails> naProjDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, naProjDetailses.size());
		Assert.assertEquals(newNaProjDetails1,
			naProjDetailses.get(newNaProjDetails1.getPrimaryKey()));
		Assert.assertEquals(newNaProjDetails2,
			naProjDetailses.get(newNaProjDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, NaProjDetails> naProjDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(naProjDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		NaProjDetails newNaProjDetails = addNaProjDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNaProjDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, NaProjDetails> naProjDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, naProjDetailses.size());
		Assert.assertEquals(newNaProjDetails,
			naProjDetailses.get(newNaProjDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, NaProjDetails> naProjDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(naProjDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		NaProjDetails newNaProjDetails = addNaProjDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNaProjDetails.getPrimaryKey());

		Map<Serializable, NaProjDetails> naProjDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, naProjDetailses.size());
		Assert.assertEquals(newNaProjDetails,
			naProjDetailses.get(newNaProjDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = NaProjDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<NaProjDetails>() {
				@Override
				public void performAction(NaProjDetails naProjDetails) {
					Assert.assertNotNull(naProjDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		NaProjDetails newNaProjDetails = addNaProjDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NaProjDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("naProjDetailsSid",
				newNaProjDetails.getNaProjDetailsSid()));

		List<NaProjDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		NaProjDetails existingNaProjDetails = result.get(0);

		Assert.assertEquals(existingNaProjDetails, newNaProjDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NaProjDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("naProjDetailsSid",
				RandomTestUtil.nextInt()));

		List<NaProjDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		NaProjDetails newNaProjDetails = addNaProjDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NaProjDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"naProjDetailsSid"));

		Object newNaProjDetailsSid = newNaProjDetails.getNaProjDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("naProjDetailsSid",
				new Object[] { newNaProjDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingNaProjDetailsSid = result.get(0);

		Assert.assertEquals(existingNaProjDetailsSid, newNaProjDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NaProjDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"naProjDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("naProjDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected NaProjDetails addNaProjDetails() throws Exception {
		int pk = RandomTestUtil.nextInt();

		NaProjDetails naProjDetails = _persistence.create(pk);

		naProjDetails.setItemMasterSid(RandomTestUtil.nextInt());

		naProjDetails.setNaProjMasterSid(RandomTestUtil.nextInt());

		_naProjDetailses.add(_persistence.update(naProjDetails));

		return naProjDetails;
	}

	private List<NaProjDetails> _naProjDetailses = new ArrayList<NaProjDetails>();
	private NaProjDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}