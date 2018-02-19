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

import com.stpl.app.exception.NoSuchChSalesProjectionMasterException;
import com.stpl.app.model.ChSalesProjectionMaster;
import com.stpl.app.service.ChSalesProjectionMasterLocalServiceUtil;
import com.stpl.app.service.persistence.ChSalesProjectionMasterPersistence;
import com.stpl.app.service.persistence.ChSalesProjectionMasterUtil;

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
public class ChSalesProjectionMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ChSalesProjectionMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ChSalesProjectionMaster> iterator = _chSalesProjectionMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ChSalesProjectionMaster chSalesProjectionMaster = _persistence.create(pk);

		Assert.assertNotNull(chSalesProjectionMaster);

		Assert.assertEquals(chSalesProjectionMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ChSalesProjectionMaster newChSalesProjectionMaster = addChSalesProjectionMaster();

		_persistence.remove(newChSalesProjectionMaster);

		ChSalesProjectionMaster existingChSalesProjectionMaster = _persistence.fetchByPrimaryKey(newChSalesProjectionMaster.getPrimaryKey());

		Assert.assertNull(existingChSalesProjectionMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addChSalesProjectionMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ChSalesProjectionMaster newChSalesProjectionMaster = _persistence.create(pk);

		newChSalesProjectionMaster.setCheckRecord(RandomTestUtil.randomBoolean());

		newChSalesProjectionMaster.setCalculationPeriods(RandomTestUtil.randomString());

		newChSalesProjectionMaster.setMethodology(RandomTestUtil.randomString());

		_chSalesProjectionMasters.add(_persistence.update(
				newChSalesProjectionMaster));

		ChSalesProjectionMaster existingChSalesProjectionMaster = _persistence.findByPrimaryKey(newChSalesProjectionMaster.getPrimaryKey());

		Assert.assertEquals(existingChSalesProjectionMaster.getCheckRecord(),
			newChSalesProjectionMaster.getCheckRecord());
		Assert.assertEquals(existingChSalesProjectionMaster.getCalculationPeriods(),
			newChSalesProjectionMaster.getCalculationPeriods());
		Assert.assertEquals(existingChSalesProjectionMaster.getProjectionDetailsSid(),
			newChSalesProjectionMaster.getProjectionDetailsSid());
		Assert.assertEquals(existingChSalesProjectionMaster.getMethodology(),
			newChSalesProjectionMaster.getMethodology());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ChSalesProjectionMaster newChSalesProjectionMaster = addChSalesProjectionMaster();

		ChSalesProjectionMaster existingChSalesProjectionMaster = _persistence.findByPrimaryKey(newChSalesProjectionMaster.getPrimaryKey());

		Assert.assertEquals(existingChSalesProjectionMaster,
			newChSalesProjectionMaster);
	}

	@Test(expected = NoSuchChSalesProjectionMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<ChSalesProjectionMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CH_SALES_PROJECTION_MASTER",
			"checkRecord", true, "calculationPeriods", true,
			"projectionDetailsSid", true, "methodology", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ChSalesProjectionMaster newChSalesProjectionMaster = addChSalesProjectionMaster();

		ChSalesProjectionMaster existingChSalesProjectionMaster = _persistence.fetchByPrimaryKey(newChSalesProjectionMaster.getPrimaryKey());

		Assert.assertEquals(existingChSalesProjectionMaster,
			newChSalesProjectionMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ChSalesProjectionMaster missingChSalesProjectionMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingChSalesProjectionMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ChSalesProjectionMaster newChSalesProjectionMaster1 = addChSalesProjectionMaster();
		ChSalesProjectionMaster newChSalesProjectionMaster2 = addChSalesProjectionMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newChSalesProjectionMaster1.getPrimaryKey());
		primaryKeys.add(newChSalesProjectionMaster2.getPrimaryKey());

		Map<Serializable, ChSalesProjectionMaster> chSalesProjectionMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, chSalesProjectionMasters.size());
		Assert.assertEquals(newChSalesProjectionMaster1,
			chSalesProjectionMasters.get(
				newChSalesProjectionMaster1.getPrimaryKey()));
		Assert.assertEquals(newChSalesProjectionMaster2,
			chSalesProjectionMasters.get(
				newChSalesProjectionMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ChSalesProjectionMaster> chSalesProjectionMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(chSalesProjectionMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ChSalesProjectionMaster newChSalesProjectionMaster = addChSalesProjectionMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newChSalesProjectionMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ChSalesProjectionMaster> chSalesProjectionMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, chSalesProjectionMasters.size());
		Assert.assertEquals(newChSalesProjectionMaster,
			chSalesProjectionMasters.get(
				newChSalesProjectionMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ChSalesProjectionMaster> chSalesProjectionMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(chSalesProjectionMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ChSalesProjectionMaster newChSalesProjectionMaster = addChSalesProjectionMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newChSalesProjectionMaster.getPrimaryKey());

		Map<Serializable, ChSalesProjectionMaster> chSalesProjectionMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, chSalesProjectionMasters.size());
		Assert.assertEquals(newChSalesProjectionMaster,
			chSalesProjectionMasters.get(
				newChSalesProjectionMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ChSalesProjectionMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ChSalesProjectionMaster>() {
				@Override
				public void performAction(
					ChSalesProjectionMaster chSalesProjectionMaster) {
					Assert.assertNotNull(chSalesProjectionMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ChSalesProjectionMaster newChSalesProjectionMaster = addChSalesProjectionMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ChSalesProjectionMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("projectionDetailsSid",
				newChSalesProjectionMaster.getProjectionDetailsSid()));

		List<ChSalesProjectionMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ChSalesProjectionMaster existingChSalesProjectionMaster = result.get(0);

		Assert.assertEquals(existingChSalesProjectionMaster,
			newChSalesProjectionMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ChSalesProjectionMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("projectionDetailsSid",
				RandomTestUtil.nextInt()));

		List<ChSalesProjectionMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ChSalesProjectionMaster newChSalesProjectionMaster = addChSalesProjectionMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ChSalesProjectionMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"projectionDetailsSid"));

		Object newProjectionDetailsSid = newChSalesProjectionMaster.getProjectionDetailsSid();

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
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ChSalesProjectionMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"projectionDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("projectionDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ChSalesProjectionMaster addChSalesProjectionMaster()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		ChSalesProjectionMaster chSalesProjectionMaster = _persistence.create(pk);

		chSalesProjectionMaster.setCheckRecord(RandomTestUtil.randomBoolean());

		chSalesProjectionMaster.setCalculationPeriods(RandomTestUtil.randomString());

		chSalesProjectionMaster.setMethodology(RandomTestUtil.randomString());

		_chSalesProjectionMasters.add(_persistence.update(
				chSalesProjectionMaster));

		return chSalesProjectionMaster;
	}

	private List<ChSalesProjectionMaster> _chSalesProjectionMasters = new ArrayList<ChSalesProjectionMaster>();
	private ChSalesProjectionMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}