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
import com.liferay.portal.kernel.test.AssertUtils;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchStMSupplementalDiscActualsException;
import com.stpl.app.model.StMSupplementalDiscActuals;
import com.stpl.app.service.StMSupplementalDiscActualsLocalServiceUtil;
import com.stpl.app.service.persistence.StMSupplementalDiscActualsPK;
import com.stpl.app.service.persistence.StMSupplementalDiscActualsPersistence;
import com.stpl.app.service.persistence.StMSupplementalDiscActualsUtil;

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
public class StMSupplementalDiscActualsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = StMSupplementalDiscActualsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<StMSupplementalDiscActuals> iterator = _stMSupplementalDiscActualses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		StMSupplementalDiscActualsPK pk = new StMSupplementalDiscActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StMSupplementalDiscActuals stMSupplementalDiscActuals = _persistence.create(pk);

		Assert.assertNotNull(stMSupplementalDiscActuals);

		Assert.assertEquals(stMSupplementalDiscActuals.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		StMSupplementalDiscActuals newStMSupplementalDiscActuals = addStMSupplementalDiscActuals();

		_persistence.remove(newStMSupplementalDiscActuals);

		StMSupplementalDiscActuals existingStMSupplementalDiscActuals = _persistence.fetchByPrimaryKey(newStMSupplementalDiscActuals.getPrimaryKey());

		Assert.assertNull(existingStMSupplementalDiscActuals);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addStMSupplementalDiscActuals();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		StMSupplementalDiscActualsPK pk = new StMSupplementalDiscActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StMSupplementalDiscActuals newStMSupplementalDiscActuals = _persistence.create(pk);

		newStMSupplementalDiscActuals.setActualSales(RandomTestUtil.nextDouble());

		newStMSupplementalDiscActuals.setPeriodSid(RandomTestUtil.nextInt());

		newStMSupplementalDiscActuals.setActualRate(RandomTestUtil.nextDouble());

		newStMSupplementalDiscActuals.setLastModifiedDate(RandomTestUtil.nextDate());

		newStMSupplementalDiscActuals.setActualProjectionSales(RandomTestUtil.nextDouble());

		newStMSupplementalDiscActuals.setActualProjectionRate(RandomTestUtil.nextDouble());

		_stMSupplementalDiscActualses.add(_persistence.update(
				newStMSupplementalDiscActuals));

		StMSupplementalDiscActuals existingStMSupplementalDiscActuals = _persistence.findByPrimaryKey(newStMSupplementalDiscActuals.getPrimaryKey());

		AssertUtils.assertEquals(existingStMSupplementalDiscActuals.getActualSales(),
			newStMSupplementalDiscActuals.getActualSales());
		Assert.assertEquals(existingStMSupplementalDiscActuals.getPeriodSid(),
			newStMSupplementalDiscActuals.getPeriodSid());
		AssertUtils.assertEquals(existingStMSupplementalDiscActuals.getActualRate(),
			newStMSupplementalDiscActuals.getActualRate());
		Assert.assertEquals(existingStMSupplementalDiscActuals.getUserId(),
			newStMSupplementalDiscActuals.getUserId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingStMSupplementalDiscActuals.getLastModifiedDate()),
			Time.getShortTimestamp(
				newStMSupplementalDiscActuals.getLastModifiedDate()));
		AssertUtils.assertEquals(existingStMSupplementalDiscActuals.getActualProjectionSales(),
			newStMSupplementalDiscActuals.getActualProjectionSales());
		AssertUtils.assertEquals(existingStMSupplementalDiscActuals.getActualProjectionRate(),
			newStMSupplementalDiscActuals.getActualProjectionRate());
		Assert.assertEquals(existingStMSupplementalDiscActuals.getProjectionDetailsSid(),
			newStMSupplementalDiscActuals.getProjectionDetailsSid());
		Assert.assertEquals(existingStMSupplementalDiscActuals.getSessionId(),
			newStMSupplementalDiscActuals.getSessionId());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		StMSupplementalDiscActuals newStMSupplementalDiscActuals = addStMSupplementalDiscActuals();

		StMSupplementalDiscActuals existingStMSupplementalDiscActuals = _persistence.findByPrimaryKey(newStMSupplementalDiscActuals.getPrimaryKey());

		Assert.assertEquals(existingStMSupplementalDiscActuals,
			newStMSupplementalDiscActuals);
	}

	@Test(expected = NoSuchStMSupplementalDiscActualsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		StMSupplementalDiscActualsPK pk = new StMSupplementalDiscActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		StMSupplementalDiscActuals newStMSupplementalDiscActuals = addStMSupplementalDiscActuals();

		StMSupplementalDiscActuals existingStMSupplementalDiscActuals = _persistence.fetchByPrimaryKey(newStMSupplementalDiscActuals.getPrimaryKey());

		Assert.assertEquals(existingStMSupplementalDiscActuals,
			newStMSupplementalDiscActuals);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		StMSupplementalDiscActualsPK pk = new StMSupplementalDiscActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StMSupplementalDiscActuals missingStMSupplementalDiscActuals = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingStMSupplementalDiscActuals);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		StMSupplementalDiscActuals newStMSupplementalDiscActuals1 = addStMSupplementalDiscActuals();
		StMSupplementalDiscActuals newStMSupplementalDiscActuals2 = addStMSupplementalDiscActuals();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStMSupplementalDiscActuals1.getPrimaryKey());
		primaryKeys.add(newStMSupplementalDiscActuals2.getPrimaryKey());

		Map<Serializable, StMSupplementalDiscActuals> stMSupplementalDiscActualses =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, stMSupplementalDiscActualses.size());
		Assert.assertEquals(newStMSupplementalDiscActuals1,
			stMSupplementalDiscActualses.get(
				newStMSupplementalDiscActuals1.getPrimaryKey()));
		Assert.assertEquals(newStMSupplementalDiscActuals2,
			stMSupplementalDiscActualses.get(
				newStMSupplementalDiscActuals2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		StMSupplementalDiscActualsPK pk1 = new StMSupplementalDiscActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StMSupplementalDiscActualsPK pk2 = new StMSupplementalDiscActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, StMSupplementalDiscActuals> stMSupplementalDiscActualses =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stMSupplementalDiscActualses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		StMSupplementalDiscActuals newStMSupplementalDiscActuals = addStMSupplementalDiscActuals();

		StMSupplementalDiscActualsPK pk = new StMSupplementalDiscActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStMSupplementalDiscActuals.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, StMSupplementalDiscActuals> stMSupplementalDiscActualses =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stMSupplementalDiscActualses.size());
		Assert.assertEquals(newStMSupplementalDiscActuals,
			stMSupplementalDiscActualses.get(
				newStMSupplementalDiscActuals.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, StMSupplementalDiscActuals> stMSupplementalDiscActualses =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stMSupplementalDiscActualses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		StMSupplementalDiscActuals newStMSupplementalDiscActuals = addStMSupplementalDiscActuals();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStMSupplementalDiscActuals.getPrimaryKey());

		Map<Serializable, StMSupplementalDiscActuals> stMSupplementalDiscActualses =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stMSupplementalDiscActualses.size());
		Assert.assertEquals(newStMSupplementalDiscActuals,
			stMSupplementalDiscActualses.get(
				newStMSupplementalDiscActuals.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = StMSupplementalDiscActualsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<StMSupplementalDiscActuals>() {
				@Override
				public void performAction(
					StMSupplementalDiscActuals stMSupplementalDiscActuals) {
					Assert.assertNotNull(stMSupplementalDiscActuals);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		StMSupplementalDiscActuals newStMSupplementalDiscActuals = addStMSupplementalDiscActuals();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StMSupplementalDiscActuals.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				newStMSupplementalDiscActuals.getUserId()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				newStMSupplementalDiscActuals.getProjectionDetailsSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				newStMSupplementalDiscActuals.getSessionId()));

		List<StMSupplementalDiscActuals> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		StMSupplementalDiscActuals existingStMSupplementalDiscActuals = result.get(0);

		Assert.assertEquals(existingStMSupplementalDiscActuals,
			newStMSupplementalDiscActuals);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StMSupplementalDiscActuals.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				RandomTestUtil.nextInt()));

		List<StMSupplementalDiscActuals> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		StMSupplementalDiscActuals newStMSupplementalDiscActuals = addStMSupplementalDiscActuals();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StMSupplementalDiscActuals.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("id.userId"));

		Object newUserId = newStMSupplementalDiscActuals.getUserId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.userId",
				new Object[] { newUserId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingUserId = result.get(0);

		Assert.assertEquals(existingUserId, newUserId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StMSupplementalDiscActuals.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("id.userId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.userId",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected StMSupplementalDiscActuals addStMSupplementalDiscActuals()
		throws Exception {
		StMSupplementalDiscActualsPK pk = new StMSupplementalDiscActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StMSupplementalDiscActuals stMSupplementalDiscActuals = _persistence.create(pk);

		stMSupplementalDiscActuals.setActualSales(RandomTestUtil.nextDouble());

		stMSupplementalDiscActuals.setPeriodSid(RandomTestUtil.nextInt());

		stMSupplementalDiscActuals.setActualRate(RandomTestUtil.nextDouble());

		stMSupplementalDiscActuals.setLastModifiedDate(RandomTestUtil.nextDate());

		stMSupplementalDiscActuals.setActualProjectionSales(RandomTestUtil.nextDouble());

		stMSupplementalDiscActuals.setActualProjectionRate(RandomTestUtil.nextDouble());

		_stMSupplementalDiscActualses.add(_persistence.update(
				stMSupplementalDiscActuals));

		return stMSupplementalDiscActuals;
	}

	private List<StMSupplementalDiscActuals> _stMSupplementalDiscActualses = new ArrayList<StMSupplementalDiscActuals>();
	private StMSupplementalDiscActualsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}