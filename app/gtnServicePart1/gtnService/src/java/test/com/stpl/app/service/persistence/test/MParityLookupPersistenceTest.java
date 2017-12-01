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

import com.stpl.app.exception.NoSuchMParityLookupException;
import com.stpl.app.model.MParityLookup;
import com.stpl.app.service.MParityLookupLocalServiceUtil;
import com.stpl.app.service.persistence.MParityLookupPersistence;
import com.stpl.app.service.persistence.MParityLookupUtil;

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
public class MParityLookupPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = MParityLookupUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<MParityLookup> iterator = _mParityLookups.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		MParityLookup mParityLookup = _persistence.create(pk);

		Assert.assertNotNull(mParityLookup);

		Assert.assertEquals(mParityLookup.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		MParityLookup newMParityLookup = addMParityLookup();

		_persistence.remove(newMParityLookup);

		MParityLookup existingMParityLookup = _persistence.fetchByPrimaryKey(newMParityLookup.getPrimaryKey());

		Assert.assertNull(existingMParityLookup);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addMParityLookup();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		MParityLookup newMParityLookup = _persistence.create(pk);

		newMParityLookup.setContractMasterSid(RandomTestUtil.nextInt());

		newMParityLookup.setMarketType(RandomTestUtil.randomString());

		newMParityLookup.setItemMasterSid(RandomTestUtil.nextInt());

		newMParityLookup.setProjectionDetailsSid(RandomTestUtil.nextInt());

		_mParityLookups.add(_persistence.update(newMParityLookup));

		MParityLookup existingMParityLookup = _persistence.findByPrimaryKey(newMParityLookup.getPrimaryKey());

		Assert.assertEquals(existingMParityLookup.getContractMasterSid(),
			newMParityLookup.getContractMasterSid());
		Assert.assertEquals(existingMParityLookup.getMarketType(),
			newMParityLookup.getMarketType());
		Assert.assertEquals(existingMParityLookup.getItemMasterSid(),
			newMParityLookup.getItemMasterSid());
		Assert.assertEquals(existingMParityLookup.getMParityLookupSid(),
			newMParityLookup.getMParityLookupSid());
		Assert.assertEquals(existingMParityLookup.getProjectionDetailsSid(),
			newMParityLookup.getProjectionDetailsSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		MParityLookup newMParityLookup = addMParityLookup();

		MParityLookup existingMParityLookup = _persistence.findByPrimaryKey(newMParityLookup.getPrimaryKey());

		Assert.assertEquals(existingMParityLookup, newMParityLookup);
	}

	@Test(expected = NoSuchMParityLookupException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<MParityLookup> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("M_PARITY_LOOKUP",
			"contractMasterSid", true, "marketType", true, "itemMasterSid",
			true, "mParityLookupSid", true, "projectionDetailsSid", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		MParityLookup newMParityLookup = addMParityLookup();

		MParityLookup existingMParityLookup = _persistence.fetchByPrimaryKey(newMParityLookup.getPrimaryKey());

		Assert.assertEquals(existingMParityLookup, newMParityLookup);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		MParityLookup missingMParityLookup = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingMParityLookup);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		MParityLookup newMParityLookup1 = addMParityLookup();
		MParityLookup newMParityLookup2 = addMParityLookup();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMParityLookup1.getPrimaryKey());
		primaryKeys.add(newMParityLookup2.getPrimaryKey());

		Map<Serializable, MParityLookup> mParityLookups = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, mParityLookups.size());
		Assert.assertEquals(newMParityLookup1,
			mParityLookups.get(newMParityLookup1.getPrimaryKey()));
		Assert.assertEquals(newMParityLookup2,
			mParityLookups.get(newMParityLookup2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, MParityLookup> mParityLookups = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(mParityLookups.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		MParityLookup newMParityLookup = addMParityLookup();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMParityLookup.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, MParityLookup> mParityLookups = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, mParityLookups.size());
		Assert.assertEquals(newMParityLookup,
			mParityLookups.get(newMParityLookup.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, MParityLookup> mParityLookups = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(mParityLookups.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		MParityLookup newMParityLookup = addMParityLookup();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMParityLookup.getPrimaryKey());

		Map<Serializable, MParityLookup> mParityLookups = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, mParityLookups.size());
		Assert.assertEquals(newMParityLookup,
			mParityLookups.get(newMParityLookup.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = MParityLookupLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<MParityLookup>() {
				@Override
				public void performAction(MParityLookup mParityLookup) {
					Assert.assertNotNull(mParityLookup);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		MParityLookup newMParityLookup = addMParityLookup();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MParityLookup.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("mParityLookupSid",
				newMParityLookup.getMParityLookupSid()));

		List<MParityLookup> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		MParityLookup existingMParityLookup = result.get(0);

		Assert.assertEquals(existingMParityLookup, newMParityLookup);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MParityLookup.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("mParityLookupSid",
				RandomTestUtil.nextInt()));

		List<MParityLookup> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		MParityLookup newMParityLookup = addMParityLookup();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MParityLookup.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"mParityLookupSid"));

		Object newMParityLookupSid = newMParityLookup.getMParityLookupSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("mParityLookupSid",
				new Object[] { newMParityLookupSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingMParityLookupSid = result.get(0);

		Assert.assertEquals(existingMParityLookupSid, newMParityLookupSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MParityLookup.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"mParityLookupSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("mParityLookupSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected MParityLookup addMParityLookup() throws Exception {
		int pk = RandomTestUtil.nextInt();

		MParityLookup mParityLookup = _persistence.create(pk);

		mParityLookup.setContractMasterSid(RandomTestUtil.nextInt());

		mParityLookup.setMarketType(RandomTestUtil.randomString());

		mParityLookup.setItemMasterSid(RandomTestUtil.nextInt());

		mParityLookup.setProjectionDetailsSid(RandomTestUtil.nextInt());

		_mParityLookups.add(_persistence.update(mParityLookup));

		return mParityLookup;
	}

	private List<MParityLookup> _mParityLookups = new ArrayList<MParityLookup>();
	private MParityLookupPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}