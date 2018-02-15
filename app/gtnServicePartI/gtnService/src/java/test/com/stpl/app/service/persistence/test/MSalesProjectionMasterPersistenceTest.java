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

import com.stpl.app.exception.NoSuchMSalesProjectionMasterException;
import com.stpl.app.model.MSalesProjectionMaster;
import com.stpl.app.service.MSalesProjectionMasterLocalServiceUtil;
import com.stpl.app.service.persistence.MSalesProjectionMasterPersistence;
import com.stpl.app.service.persistence.MSalesProjectionMasterUtil;

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
public class MSalesProjectionMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = MSalesProjectionMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<MSalesProjectionMaster> iterator = _mSalesProjectionMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		MSalesProjectionMaster mSalesProjectionMaster = _persistence.create(pk);

		Assert.assertNotNull(mSalesProjectionMaster);

		Assert.assertEquals(mSalesProjectionMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		MSalesProjectionMaster newMSalesProjectionMaster = addMSalesProjectionMaster();

		_persistence.remove(newMSalesProjectionMaster);

		MSalesProjectionMaster existingMSalesProjectionMaster = _persistence.fetchByPrimaryKey(newMSalesProjectionMaster.getPrimaryKey());

		Assert.assertNull(existingMSalesProjectionMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addMSalesProjectionMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		MSalesProjectionMaster newMSalesProjectionMaster = _persistence.create(pk);

		newMSalesProjectionMaster.setMethodology(RandomTestUtil.randomString());

		newMSalesProjectionMaster.setCalculationPeriods(RandomTestUtil.randomString());

		newMSalesProjectionMaster.setCalculationBased(RandomTestUtil.randomString());

		newMSalesProjectionMaster.setCheckRecord(RandomTestUtil.randomBoolean());

		_mSalesProjectionMasters.add(_persistence.update(
				newMSalesProjectionMaster));

		MSalesProjectionMaster existingMSalesProjectionMaster = _persistence.findByPrimaryKey(newMSalesProjectionMaster.getPrimaryKey());

		Assert.assertEquals(existingMSalesProjectionMaster.getMethodology(),
			newMSalesProjectionMaster.getMethodology());
		Assert.assertEquals(existingMSalesProjectionMaster.getCalculationPeriods(),
			newMSalesProjectionMaster.getCalculationPeriods());
		Assert.assertEquals(existingMSalesProjectionMaster.getCalculationBased(),
			newMSalesProjectionMaster.getCalculationBased());
		Assert.assertEquals(existingMSalesProjectionMaster.getProjectionDetailsSid(),
			newMSalesProjectionMaster.getProjectionDetailsSid());
		Assert.assertEquals(existingMSalesProjectionMaster.getCheckRecord(),
			newMSalesProjectionMaster.getCheckRecord());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		MSalesProjectionMaster newMSalesProjectionMaster = addMSalesProjectionMaster();

		MSalesProjectionMaster existingMSalesProjectionMaster = _persistence.findByPrimaryKey(newMSalesProjectionMaster.getPrimaryKey());

		Assert.assertEquals(existingMSalesProjectionMaster,
			newMSalesProjectionMaster);
	}

	@Test(expected = NoSuchMSalesProjectionMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<MSalesProjectionMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("M_SALES_PROJECTION_MASTER",
			"methodology", true, "calculationPeriods", true,
			"calculationBased", true, "projectionDetailsSid", true,
			"checkRecord", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		MSalesProjectionMaster newMSalesProjectionMaster = addMSalesProjectionMaster();

		MSalesProjectionMaster existingMSalesProjectionMaster = _persistence.fetchByPrimaryKey(newMSalesProjectionMaster.getPrimaryKey());

		Assert.assertEquals(existingMSalesProjectionMaster,
			newMSalesProjectionMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		MSalesProjectionMaster missingMSalesProjectionMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingMSalesProjectionMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		MSalesProjectionMaster newMSalesProjectionMaster1 = addMSalesProjectionMaster();
		MSalesProjectionMaster newMSalesProjectionMaster2 = addMSalesProjectionMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMSalesProjectionMaster1.getPrimaryKey());
		primaryKeys.add(newMSalesProjectionMaster2.getPrimaryKey());

		Map<Serializable, MSalesProjectionMaster> mSalesProjectionMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, mSalesProjectionMasters.size());
		Assert.assertEquals(newMSalesProjectionMaster1,
			mSalesProjectionMasters.get(
				newMSalesProjectionMaster1.getPrimaryKey()));
		Assert.assertEquals(newMSalesProjectionMaster2,
			mSalesProjectionMasters.get(
				newMSalesProjectionMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, MSalesProjectionMaster> mSalesProjectionMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(mSalesProjectionMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		MSalesProjectionMaster newMSalesProjectionMaster = addMSalesProjectionMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMSalesProjectionMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, MSalesProjectionMaster> mSalesProjectionMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, mSalesProjectionMasters.size());
		Assert.assertEquals(newMSalesProjectionMaster,
			mSalesProjectionMasters.get(
				newMSalesProjectionMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, MSalesProjectionMaster> mSalesProjectionMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(mSalesProjectionMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		MSalesProjectionMaster newMSalesProjectionMaster = addMSalesProjectionMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMSalesProjectionMaster.getPrimaryKey());

		Map<Serializable, MSalesProjectionMaster> mSalesProjectionMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, mSalesProjectionMasters.size());
		Assert.assertEquals(newMSalesProjectionMaster,
			mSalesProjectionMasters.get(
				newMSalesProjectionMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = MSalesProjectionMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<MSalesProjectionMaster>() {
				@Override
				public void performAction(
					MSalesProjectionMaster mSalesProjectionMaster) {
					Assert.assertNotNull(mSalesProjectionMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		MSalesProjectionMaster newMSalesProjectionMaster = addMSalesProjectionMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MSalesProjectionMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("projectionDetailsSid",
				newMSalesProjectionMaster.getProjectionDetailsSid()));

		List<MSalesProjectionMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		MSalesProjectionMaster existingMSalesProjectionMaster = result.get(0);

		Assert.assertEquals(existingMSalesProjectionMaster,
			newMSalesProjectionMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MSalesProjectionMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("projectionDetailsSid",
				RandomTestUtil.nextInt()));

		List<MSalesProjectionMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		MSalesProjectionMaster newMSalesProjectionMaster = addMSalesProjectionMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MSalesProjectionMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"projectionDetailsSid"));

		Object newProjectionDetailsSid = newMSalesProjectionMaster.getProjectionDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("projectionDetailsSid",
				new Object[] { newProjectionDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingProjectionDetailsSid = result.get(0);

		Assert.assertEquals(existingProjectionDetailsSid,
			newProjectionDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MSalesProjectionMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"projectionDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("projectionDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected MSalesProjectionMaster addMSalesProjectionMaster()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		MSalesProjectionMaster mSalesProjectionMaster = _persistence.create(pk);

		mSalesProjectionMaster.setMethodology(RandomTestUtil.randomString());

		mSalesProjectionMaster.setCalculationPeriods(RandomTestUtil.randomString());

		mSalesProjectionMaster.setCalculationBased(RandomTestUtil.randomString());

		mSalesProjectionMaster.setCheckRecord(RandomTestUtil.randomBoolean());

		_mSalesProjectionMasters.add(_persistence.update(mSalesProjectionMaster));

		return mSalesProjectionMaster;
	}

	private List<MSalesProjectionMaster> _mSalesProjectionMasters = new ArrayList<MSalesProjectionMaster>();
	private MSalesProjectionMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}