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
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchStChSalesProjectionMasterException;
import com.stpl.app.model.StChSalesProjectionMaster;
import com.stpl.app.service.StChSalesProjectionMasterLocalServiceUtil;
import com.stpl.app.service.persistence.StChSalesProjectionMasterPK;
import com.stpl.app.service.persistence.StChSalesProjectionMasterPersistence;
import com.stpl.app.service.persistence.StChSalesProjectionMasterUtil;

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
public class StChSalesProjectionMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = StChSalesProjectionMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<StChSalesProjectionMaster> iterator = _stChSalesProjectionMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		StChSalesProjectionMasterPK pk = new StChSalesProjectionMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StChSalesProjectionMaster stChSalesProjectionMaster = _persistence.create(pk);

		Assert.assertNotNull(stChSalesProjectionMaster);

		Assert.assertEquals(stChSalesProjectionMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		StChSalesProjectionMaster newStChSalesProjectionMaster = addStChSalesProjectionMaster();

		_persistence.remove(newStChSalesProjectionMaster);

		StChSalesProjectionMaster existingStChSalesProjectionMaster = _persistence.fetchByPrimaryKey(newStChSalesProjectionMaster.getPrimaryKey());

		Assert.assertNull(existingStChSalesProjectionMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addStChSalesProjectionMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		StChSalesProjectionMasterPK pk = new StChSalesProjectionMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StChSalesProjectionMaster newStChSalesProjectionMaster = _persistence.create(pk);

		newStChSalesProjectionMaster.setLastModifiedDate(RandomTestUtil.nextDate());

		newStChSalesProjectionMaster.setCheckRecord(RandomTestUtil.randomBoolean());

		newStChSalesProjectionMaster.setCalculationPeriods(RandomTestUtil.randomString());

		newStChSalesProjectionMaster.setMethodology(RandomTestUtil.randomString());

		_stChSalesProjectionMasters.add(_persistence.update(
				newStChSalesProjectionMaster));

		StChSalesProjectionMaster existingStChSalesProjectionMaster = _persistence.findByPrimaryKey(newStChSalesProjectionMaster.getPrimaryKey());

		Assert.assertEquals(Time.getShortTimestamp(
				existingStChSalesProjectionMaster.getLastModifiedDate()),
			Time.getShortTimestamp(
				newStChSalesProjectionMaster.getLastModifiedDate()));
		Assert.assertEquals(existingStChSalesProjectionMaster.getCheckRecord(),
			newStChSalesProjectionMaster.getCheckRecord());
		Assert.assertEquals(existingStChSalesProjectionMaster.getCalculationPeriods(),
			newStChSalesProjectionMaster.getCalculationPeriods());
		Assert.assertEquals(existingStChSalesProjectionMaster.getProjectionDetailsSid(),
			newStChSalesProjectionMaster.getProjectionDetailsSid());
		Assert.assertEquals(existingStChSalesProjectionMaster.getUserId(),
			newStChSalesProjectionMaster.getUserId());
		Assert.assertEquals(existingStChSalesProjectionMaster.getSessionId(),
			newStChSalesProjectionMaster.getSessionId());
		Assert.assertEquals(existingStChSalesProjectionMaster.getMethodology(),
			newStChSalesProjectionMaster.getMethodology());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		StChSalesProjectionMaster newStChSalesProjectionMaster = addStChSalesProjectionMaster();

		StChSalesProjectionMaster existingStChSalesProjectionMaster = _persistence.findByPrimaryKey(newStChSalesProjectionMaster.getPrimaryKey());

		Assert.assertEquals(existingStChSalesProjectionMaster,
			newStChSalesProjectionMaster);
	}

	@Test(expected = NoSuchStChSalesProjectionMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		StChSalesProjectionMasterPK pk = new StChSalesProjectionMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		StChSalesProjectionMaster newStChSalesProjectionMaster = addStChSalesProjectionMaster();

		StChSalesProjectionMaster existingStChSalesProjectionMaster = _persistence.fetchByPrimaryKey(newStChSalesProjectionMaster.getPrimaryKey());

		Assert.assertEquals(existingStChSalesProjectionMaster,
			newStChSalesProjectionMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		StChSalesProjectionMasterPK pk = new StChSalesProjectionMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StChSalesProjectionMaster missingStChSalesProjectionMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingStChSalesProjectionMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		StChSalesProjectionMaster newStChSalesProjectionMaster1 = addStChSalesProjectionMaster();
		StChSalesProjectionMaster newStChSalesProjectionMaster2 = addStChSalesProjectionMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStChSalesProjectionMaster1.getPrimaryKey());
		primaryKeys.add(newStChSalesProjectionMaster2.getPrimaryKey());

		Map<Serializable, StChSalesProjectionMaster> stChSalesProjectionMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, stChSalesProjectionMasters.size());
		Assert.assertEquals(newStChSalesProjectionMaster1,
			stChSalesProjectionMasters.get(
				newStChSalesProjectionMaster1.getPrimaryKey()));
		Assert.assertEquals(newStChSalesProjectionMaster2,
			stChSalesProjectionMasters.get(
				newStChSalesProjectionMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		StChSalesProjectionMasterPK pk1 = new StChSalesProjectionMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StChSalesProjectionMasterPK pk2 = new StChSalesProjectionMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, StChSalesProjectionMaster> stChSalesProjectionMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stChSalesProjectionMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		StChSalesProjectionMaster newStChSalesProjectionMaster = addStChSalesProjectionMaster();

		StChSalesProjectionMasterPK pk = new StChSalesProjectionMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStChSalesProjectionMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, StChSalesProjectionMaster> stChSalesProjectionMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stChSalesProjectionMasters.size());
		Assert.assertEquals(newStChSalesProjectionMaster,
			stChSalesProjectionMasters.get(
				newStChSalesProjectionMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, StChSalesProjectionMaster> stChSalesProjectionMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stChSalesProjectionMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		StChSalesProjectionMaster newStChSalesProjectionMaster = addStChSalesProjectionMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStChSalesProjectionMaster.getPrimaryKey());

		Map<Serializable, StChSalesProjectionMaster> stChSalesProjectionMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stChSalesProjectionMasters.size());
		Assert.assertEquals(newStChSalesProjectionMaster,
			stChSalesProjectionMasters.get(
				newStChSalesProjectionMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = StChSalesProjectionMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<StChSalesProjectionMaster>() {
				@Override
				public void performAction(
					StChSalesProjectionMaster stChSalesProjectionMaster) {
					Assert.assertNotNull(stChSalesProjectionMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		StChSalesProjectionMaster newStChSalesProjectionMaster = addStChSalesProjectionMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StChSalesProjectionMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				newStChSalesProjectionMaster.getProjectionDetailsSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				newStChSalesProjectionMaster.getUserId()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				newStChSalesProjectionMaster.getSessionId()));

		List<StChSalesProjectionMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		StChSalesProjectionMaster existingStChSalesProjectionMaster = result.get(0);

		Assert.assertEquals(existingStChSalesProjectionMaster,
			newStChSalesProjectionMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StChSalesProjectionMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				RandomTestUtil.nextInt()));

		List<StChSalesProjectionMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		StChSalesProjectionMaster newStChSalesProjectionMaster = addStChSalesProjectionMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StChSalesProjectionMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.projectionDetailsSid"));

		Object newProjectionDetailsSid = newStChSalesProjectionMaster.getProjectionDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.projectionDetailsSid",
				new Object[] { newProjectionDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingProjectionDetailsSid = result.get(0);

		Assert.assertEquals(existingProjectionDetailsSid,
			newProjectionDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StChSalesProjectionMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.projectionDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.projectionDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected StChSalesProjectionMaster addStChSalesProjectionMaster()
		throws Exception {
		StChSalesProjectionMasterPK pk = new StChSalesProjectionMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StChSalesProjectionMaster stChSalesProjectionMaster = _persistence.create(pk);

		stChSalesProjectionMaster.setLastModifiedDate(RandomTestUtil.nextDate());

		stChSalesProjectionMaster.setCheckRecord(RandomTestUtil.randomBoolean());

		stChSalesProjectionMaster.setCalculationPeriods(RandomTestUtil.randomString());

		stChSalesProjectionMaster.setMethodology(RandomTestUtil.randomString());

		_stChSalesProjectionMasters.add(_persistence.update(
				stChSalesProjectionMaster));

		return stChSalesProjectionMaster;
	}

	private List<StChSalesProjectionMaster> _stChSalesProjectionMasters = new ArrayList<StChSalesProjectionMaster>();
	private StChSalesProjectionMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}