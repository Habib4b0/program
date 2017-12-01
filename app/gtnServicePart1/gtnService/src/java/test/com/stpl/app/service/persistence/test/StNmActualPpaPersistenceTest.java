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

import com.stpl.app.exception.NoSuchStNmActualPpaException;
import com.stpl.app.model.StNmActualPpa;
import com.stpl.app.service.StNmActualPpaLocalServiceUtil;
import com.stpl.app.service.persistence.StNmActualPpaPK;
import com.stpl.app.service.persistence.StNmActualPpaPersistence;
import com.stpl.app.service.persistence.StNmActualPpaUtil;

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
public class StNmActualPpaPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = StNmActualPpaUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<StNmActualPpa> iterator = _stNmActualPpas.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		StNmActualPpaPK pk = new StNmActualPpaPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		StNmActualPpa stNmActualPpa = _persistence.create(pk);

		Assert.assertNotNull(stNmActualPpa);

		Assert.assertEquals(stNmActualPpa.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		StNmActualPpa newStNmActualPpa = addStNmActualPpa();

		_persistence.remove(newStNmActualPpa);

		StNmActualPpa existingStNmActualPpa = _persistence.fetchByPrimaryKey(newStNmActualPpa.getPrimaryKey());

		Assert.assertNull(existingStNmActualPpa);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addStNmActualPpa();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		StNmActualPpaPK pk = new StNmActualPpaPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		StNmActualPpa newStNmActualPpa = _persistence.create(pk);

		newStNmActualPpa.setLastModifiedDate(RandomTestUtil.nextDate());

		newStNmActualPpa.setActualRate(RandomTestUtil.nextDouble());

		newStNmActualPpa.setActualProjDiscountDollar(RandomTestUtil.nextDouble());

		newStNmActualPpa.setActualProjectionSales(RandomTestUtil.nextDouble());

		newStNmActualPpa.setActualProjectionRate(RandomTestUtil.nextDouble());

		newStNmActualPpa.setActualProjDiscountUnits(RandomTestUtil.nextDouble());

		newStNmActualPpa.setActualDiscountDollar(RandomTestUtil.nextDouble());

		newStNmActualPpa.setActualDiscountUnits(RandomTestUtil.nextDouble());

		newStNmActualPpa.setActualSales(RandomTestUtil.nextDouble());

		_stNmActualPpas.add(_persistence.update(newStNmActualPpa));

		StNmActualPpa existingStNmActualPpa = _persistence.findByPrimaryKey(newStNmActualPpa.getPrimaryKey());

		Assert.assertEquals(Time.getShortTimestamp(
				existingStNmActualPpa.getLastModifiedDate()),
			Time.getShortTimestamp(newStNmActualPpa.getLastModifiedDate()));
		AssertUtils.assertEquals(existingStNmActualPpa.getActualRate(),
			newStNmActualPpa.getActualRate());
		Assert.assertEquals(existingStNmActualPpa.getPeriodSid(),
			newStNmActualPpa.getPeriodSid());
		AssertUtils.assertEquals(existingStNmActualPpa.getActualProjDiscountDollar(),
			newStNmActualPpa.getActualProjDiscountDollar());
		AssertUtils.assertEquals(existingStNmActualPpa.getActualProjectionSales(),
			newStNmActualPpa.getActualProjectionSales());
		Assert.assertEquals(existingStNmActualPpa.getProjectionDetailsSid(),
			newStNmActualPpa.getProjectionDetailsSid());
		Assert.assertEquals(existingStNmActualPpa.getUserId(),
			newStNmActualPpa.getUserId());
		AssertUtils.assertEquals(existingStNmActualPpa.getActualProjectionRate(),
			newStNmActualPpa.getActualProjectionRate());
		Assert.assertEquals(existingStNmActualPpa.getSessionId(),
			newStNmActualPpa.getSessionId());
		AssertUtils.assertEquals(existingStNmActualPpa.getActualProjDiscountUnits(),
			newStNmActualPpa.getActualProjDiscountUnits());
		AssertUtils.assertEquals(existingStNmActualPpa.getActualDiscountDollar(),
			newStNmActualPpa.getActualDiscountDollar());
		AssertUtils.assertEquals(existingStNmActualPpa.getActualDiscountUnits(),
			newStNmActualPpa.getActualDiscountUnits());
		AssertUtils.assertEquals(existingStNmActualPpa.getActualSales(),
			newStNmActualPpa.getActualSales());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		StNmActualPpa newStNmActualPpa = addStNmActualPpa();

		StNmActualPpa existingStNmActualPpa = _persistence.findByPrimaryKey(newStNmActualPpa.getPrimaryKey());

		Assert.assertEquals(existingStNmActualPpa, newStNmActualPpa);
	}

	@Test(expected = NoSuchStNmActualPpaException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		StNmActualPpaPK pk = new StNmActualPpaPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		StNmActualPpa newStNmActualPpa = addStNmActualPpa();

		StNmActualPpa existingStNmActualPpa = _persistence.fetchByPrimaryKey(newStNmActualPpa.getPrimaryKey());

		Assert.assertEquals(existingStNmActualPpa, newStNmActualPpa);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		StNmActualPpaPK pk = new StNmActualPpaPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		StNmActualPpa missingStNmActualPpa = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingStNmActualPpa);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		StNmActualPpa newStNmActualPpa1 = addStNmActualPpa();
		StNmActualPpa newStNmActualPpa2 = addStNmActualPpa();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStNmActualPpa1.getPrimaryKey());
		primaryKeys.add(newStNmActualPpa2.getPrimaryKey());

		Map<Serializable, StNmActualPpa> stNmActualPpas = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, stNmActualPpas.size());
		Assert.assertEquals(newStNmActualPpa1,
			stNmActualPpas.get(newStNmActualPpa1.getPrimaryKey()));
		Assert.assertEquals(newStNmActualPpa2,
			stNmActualPpas.get(newStNmActualPpa2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		StNmActualPpaPK pk1 = new StNmActualPpaPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		StNmActualPpaPK pk2 = new StNmActualPpaPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, StNmActualPpa> stNmActualPpas = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stNmActualPpas.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		StNmActualPpa newStNmActualPpa = addStNmActualPpa();

		StNmActualPpaPK pk = new StNmActualPpaPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStNmActualPpa.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, StNmActualPpa> stNmActualPpas = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stNmActualPpas.size());
		Assert.assertEquals(newStNmActualPpa,
			stNmActualPpas.get(newStNmActualPpa.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, StNmActualPpa> stNmActualPpas = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stNmActualPpas.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		StNmActualPpa newStNmActualPpa = addStNmActualPpa();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStNmActualPpa.getPrimaryKey());

		Map<Serializable, StNmActualPpa> stNmActualPpas = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stNmActualPpas.size());
		Assert.assertEquals(newStNmActualPpa,
			stNmActualPpas.get(newStNmActualPpa.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = StNmActualPpaLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<StNmActualPpa>() {
				@Override
				public void performAction(StNmActualPpa stNmActualPpa) {
					Assert.assertNotNull(stNmActualPpa);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		StNmActualPpa newStNmActualPpa = addStNmActualPpa();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StNmActualPpa.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				newStNmActualPpa.getPeriodSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				newStNmActualPpa.getProjectionDetailsSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				newStNmActualPpa.getUserId()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				newStNmActualPpa.getSessionId()));

		List<StNmActualPpa> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		StNmActualPpa existingStNmActualPpa = result.get(0);

		Assert.assertEquals(existingStNmActualPpa, newStNmActualPpa);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StNmActualPpa.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				RandomTestUtil.nextInt()));

		List<StNmActualPpa> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		StNmActualPpa newStNmActualPpa = addStNmActualPpa();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StNmActualPpa.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		Object newPeriodSid = newStNmActualPpa.getPeriodSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { newPeriodSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingPeriodSid = result.get(0);

		Assert.assertEquals(existingPeriodSid, newPeriodSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StNmActualPpa.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected StNmActualPpa addStNmActualPpa() throws Exception {
		StNmActualPpaPK pk = new StNmActualPpaPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		StNmActualPpa stNmActualPpa = _persistence.create(pk);

		stNmActualPpa.setLastModifiedDate(RandomTestUtil.nextDate());

		stNmActualPpa.setActualRate(RandomTestUtil.nextDouble());

		stNmActualPpa.setActualProjDiscountDollar(RandomTestUtil.nextDouble());

		stNmActualPpa.setActualProjectionSales(RandomTestUtil.nextDouble());

		stNmActualPpa.setActualProjectionRate(RandomTestUtil.nextDouble());

		stNmActualPpa.setActualProjDiscountUnits(RandomTestUtil.nextDouble());

		stNmActualPpa.setActualDiscountDollar(RandomTestUtil.nextDouble());

		stNmActualPpa.setActualDiscountUnits(RandomTestUtil.nextDouble());

		stNmActualPpa.setActualSales(RandomTestUtil.nextDouble());

		_stNmActualPpas.add(_persistence.update(stNmActualPpa));

		return stNmActualPpa;
	}

	private List<StNmActualPpa> _stNmActualPpas = new ArrayList<StNmActualPpa>();
	private StNmActualPpaPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}