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

import com.stpl.app.exception.NoSuchStNmPpaProjectionException;
import com.stpl.app.model.StNmPpaProjection;
import com.stpl.app.service.StNmPpaProjectionLocalServiceUtil;
import com.stpl.app.service.persistence.StNmPpaProjectionPK;
import com.stpl.app.service.persistence.StNmPpaProjectionPersistence;
import com.stpl.app.service.persistence.StNmPpaProjectionUtil;

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
public class StNmPpaProjectionPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = StNmPpaProjectionUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<StNmPpaProjection> iterator = _stNmPpaProjections.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		StNmPpaProjectionPK pk = new StNmPpaProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		StNmPpaProjection stNmPpaProjection = _persistence.create(pk);

		Assert.assertNotNull(stNmPpaProjection);

		Assert.assertEquals(stNmPpaProjection.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		StNmPpaProjection newStNmPpaProjection = addStNmPpaProjection();

		_persistence.remove(newStNmPpaProjection);

		StNmPpaProjection existingStNmPpaProjection = _persistence.fetchByPrimaryKey(newStNmPpaProjection.getPrimaryKey());

		Assert.assertNull(existingStNmPpaProjection);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addStNmPpaProjection();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		StNmPpaProjectionPK pk = new StNmPpaProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		StNmPpaProjection newStNmPpaProjection = _persistence.create(pk);

		newStNmPpaProjection.setLastModifiedDate(RandomTestUtil.nextDate());

		newStNmPpaProjection.setProjectionRate(RandomTestUtil.nextDouble());

		newStNmPpaProjection.setPriceCap(RandomTestUtil.nextDouble());

		newStNmPpaProjection.setProjectionDiscountUnits(RandomTestUtil.nextDouble());

		newStNmPpaProjection.setProjectionDiscountDollar(RandomTestUtil.nextDouble());

		newStNmPpaProjection.setReset(RandomTestUtil.randomBoolean());

		newStNmPpaProjection.setProjectionSales(RandomTestUtil.nextDouble());

		newStNmPpaProjection.setProjectionMap(RandomTestUtil.nextDouble());

		newStNmPpaProjection.setResetPriceCap(RandomTestUtil.randomBoolean());

		_stNmPpaProjections.add(_persistence.update(newStNmPpaProjection));

		StNmPpaProjection existingStNmPpaProjection = _persistence.findByPrimaryKey(newStNmPpaProjection.getPrimaryKey());

		Assert.assertEquals(Time.getShortTimestamp(
				existingStNmPpaProjection.getLastModifiedDate()),
			Time.getShortTimestamp(newStNmPpaProjection.getLastModifiedDate()));
		Assert.assertEquals(existingStNmPpaProjection.getPeriodSid(),
			newStNmPpaProjection.getPeriodSid());
		AssertUtils.assertEquals(existingStNmPpaProjection.getProjectionRate(),
			newStNmPpaProjection.getProjectionRate());
		Assert.assertEquals(existingStNmPpaProjection.getProjectionDetailsSid(),
			newStNmPpaProjection.getProjectionDetailsSid());
		Assert.assertEquals(existingStNmPpaProjection.getUserId(),
			newStNmPpaProjection.getUserId());
		AssertUtils.assertEquals(existingStNmPpaProjection.getPriceCap(),
			newStNmPpaProjection.getPriceCap());
		AssertUtils.assertEquals(existingStNmPpaProjection.getProjectionDiscountUnits(),
			newStNmPpaProjection.getProjectionDiscountUnits());
		Assert.assertEquals(existingStNmPpaProjection.getSessionId(),
			newStNmPpaProjection.getSessionId());
		AssertUtils.assertEquals(existingStNmPpaProjection.getProjectionDiscountDollar(),
			newStNmPpaProjection.getProjectionDiscountDollar());
		Assert.assertEquals(existingStNmPpaProjection.getReset(),
			newStNmPpaProjection.getReset());
		AssertUtils.assertEquals(existingStNmPpaProjection.getProjectionSales(),
			newStNmPpaProjection.getProjectionSales());
		AssertUtils.assertEquals(existingStNmPpaProjection.getProjectionMap(),
			newStNmPpaProjection.getProjectionMap());
		Assert.assertEquals(existingStNmPpaProjection.getResetPriceCap(),
			newStNmPpaProjection.getResetPriceCap());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		StNmPpaProjection newStNmPpaProjection = addStNmPpaProjection();

		StNmPpaProjection existingStNmPpaProjection = _persistence.findByPrimaryKey(newStNmPpaProjection.getPrimaryKey());

		Assert.assertEquals(existingStNmPpaProjection, newStNmPpaProjection);
	}

	@Test(expected = NoSuchStNmPpaProjectionException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		StNmPpaProjectionPK pk = new StNmPpaProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		StNmPpaProjection newStNmPpaProjection = addStNmPpaProjection();

		StNmPpaProjection existingStNmPpaProjection = _persistence.fetchByPrimaryKey(newStNmPpaProjection.getPrimaryKey());

		Assert.assertEquals(existingStNmPpaProjection, newStNmPpaProjection);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		StNmPpaProjectionPK pk = new StNmPpaProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		StNmPpaProjection missingStNmPpaProjection = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingStNmPpaProjection);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		StNmPpaProjection newStNmPpaProjection1 = addStNmPpaProjection();
		StNmPpaProjection newStNmPpaProjection2 = addStNmPpaProjection();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStNmPpaProjection1.getPrimaryKey());
		primaryKeys.add(newStNmPpaProjection2.getPrimaryKey());

		Map<Serializable, StNmPpaProjection> stNmPpaProjections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, stNmPpaProjections.size());
		Assert.assertEquals(newStNmPpaProjection1,
			stNmPpaProjections.get(newStNmPpaProjection1.getPrimaryKey()));
		Assert.assertEquals(newStNmPpaProjection2,
			stNmPpaProjections.get(newStNmPpaProjection2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		StNmPpaProjectionPK pk1 = new StNmPpaProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		StNmPpaProjectionPK pk2 = new StNmPpaProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, StNmPpaProjection> stNmPpaProjections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stNmPpaProjections.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		StNmPpaProjection newStNmPpaProjection = addStNmPpaProjection();

		StNmPpaProjectionPK pk = new StNmPpaProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStNmPpaProjection.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, StNmPpaProjection> stNmPpaProjections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stNmPpaProjections.size());
		Assert.assertEquals(newStNmPpaProjection,
			stNmPpaProjections.get(newStNmPpaProjection.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, StNmPpaProjection> stNmPpaProjections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stNmPpaProjections.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		StNmPpaProjection newStNmPpaProjection = addStNmPpaProjection();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStNmPpaProjection.getPrimaryKey());

		Map<Serializable, StNmPpaProjection> stNmPpaProjections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stNmPpaProjections.size());
		Assert.assertEquals(newStNmPpaProjection,
			stNmPpaProjections.get(newStNmPpaProjection.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = StNmPpaProjectionLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<StNmPpaProjection>() {
				@Override
				public void performAction(StNmPpaProjection stNmPpaProjection) {
					Assert.assertNotNull(stNmPpaProjection);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		StNmPpaProjection newStNmPpaProjection = addStNmPpaProjection();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StNmPpaProjection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				newStNmPpaProjection.getPeriodSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				newStNmPpaProjection.getProjectionDetailsSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				newStNmPpaProjection.getUserId()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				newStNmPpaProjection.getSessionId()));

		List<StNmPpaProjection> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		StNmPpaProjection existingStNmPpaProjection = result.get(0);

		Assert.assertEquals(existingStNmPpaProjection, newStNmPpaProjection);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StNmPpaProjection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				RandomTestUtil.nextInt()));

		List<StNmPpaProjection> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		StNmPpaProjection newStNmPpaProjection = addStNmPpaProjection();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StNmPpaProjection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		Object newPeriodSid = newStNmPpaProjection.getPeriodSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { newPeriodSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingPeriodSid = result.get(0);

		Assert.assertEquals(existingPeriodSid, newPeriodSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StNmPpaProjection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected StNmPpaProjection addStNmPpaProjection()
		throws Exception {
		StNmPpaProjectionPK pk = new StNmPpaProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		StNmPpaProjection stNmPpaProjection = _persistence.create(pk);

		stNmPpaProjection.setLastModifiedDate(RandomTestUtil.nextDate());

		stNmPpaProjection.setProjectionRate(RandomTestUtil.nextDouble());

		stNmPpaProjection.setPriceCap(RandomTestUtil.nextDouble());

		stNmPpaProjection.setProjectionDiscountUnits(RandomTestUtil.nextDouble());

		stNmPpaProjection.setProjectionDiscountDollar(RandomTestUtil.nextDouble());

		stNmPpaProjection.setReset(RandomTestUtil.randomBoolean());

		stNmPpaProjection.setProjectionSales(RandomTestUtil.nextDouble());

		stNmPpaProjection.setProjectionMap(RandomTestUtil.nextDouble());

		stNmPpaProjection.setResetPriceCap(RandomTestUtil.randomBoolean());

		_stNmPpaProjections.add(_persistence.update(stNmPpaProjection));

		return stNmPpaProjection;
	}

	private List<StNmPpaProjection> _stNmPpaProjections = new ArrayList<StNmPpaProjection>();
	private StNmPpaProjectionPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}