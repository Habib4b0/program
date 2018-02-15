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
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchMSupplementalDiscActualsException;
import com.stpl.app.model.MSupplementalDiscActuals;
import com.stpl.app.service.MSupplementalDiscActualsLocalServiceUtil;
import com.stpl.app.service.persistence.MSupplementalDiscActualsPK;
import com.stpl.app.service.persistence.MSupplementalDiscActualsPersistence;
import com.stpl.app.service.persistence.MSupplementalDiscActualsUtil;

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
public class MSupplementalDiscActualsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = MSupplementalDiscActualsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<MSupplementalDiscActuals> iterator = _mSupplementalDiscActualses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		MSupplementalDiscActualsPK pk = new MSupplementalDiscActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		MSupplementalDiscActuals mSupplementalDiscActuals = _persistence.create(pk);

		Assert.assertNotNull(mSupplementalDiscActuals);

		Assert.assertEquals(mSupplementalDiscActuals.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		MSupplementalDiscActuals newMSupplementalDiscActuals = addMSupplementalDiscActuals();

		_persistence.remove(newMSupplementalDiscActuals);

		MSupplementalDiscActuals existingMSupplementalDiscActuals = _persistence.fetchByPrimaryKey(newMSupplementalDiscActuals.getPrimaryKey());

		Assert.assertNull(existingMSupplementalDiscActuals);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addMSupplementalDiscActuals();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		MSupplementalDiscActualsPK pk = new MSupplementalDiscActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		MSupplementalDiscActuals newMSupplementalDiscActuals = _persistence.create(pk);

		newMSupplementalDiscActuals.setActualSales(RandomTestUtil.nextDouble());

		newMSupplementalDiscActuals.setActualRate(RandomTestUtil.nextDouble());

		newMSupplementalDiscActuals.setActualProjectionSales(RandomTestUtil.nextDouble());

		newMSupplementalDiscActuals.setActualProjectionRate(RandomTestUtil.nextDouble());

		_mSupplementalDiscActualses.add(_persistence.update(
				newMSupplementalDiscActuals));

		MSupplementalDiscActuals existingMSupplementalDiscActuals = _persistence.findByPrimaryKey(newMSupplementalDiscActuals.getPrimaryKey());

		AssertUtils.assertEquals(existingMSupplementalDiscActuals.getActualSales(),
			newMSupplementalDiscActuals.getActualSales());
		Assert.assertEquals(existingMSupplementalDiscActuals.getPeriodSid(),
			newMSupplementalDiscActuals.getPeriodSid());
		AssertUtils.assertEquals(existingMSupplementalDiscActuals.getActualRate(),
			newMSupplementalDiscActuals.getActualRate());
		AssertUtils.assertEquals(existingMSupplementalDiscActuals.getActualProjectionSales(),
			newMSupplementalDiscActuals.getActualProjectionSales());
		AssertUtils.assertEquals(existingMSupplementalDiscActuals.getActualProjectionRate(),
			newMSupplementalDiscActuals.getActualProjectionRate());
		Assert.assertEquals(existingMSupplementalDiscActuals.getProjectionDetailsSid(),
			newMSupplementalDiscActuals.getProjectionDetailsSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		MSupplementalDiscActuals newMSupplementalDiscActuals = addMSupplementalDiscActuals();

		MSupplementalDiscActuals existingMSupplementalDiscActuals = _persistence.findByPrimaryKey(newMSupplementalDiscActuals.getPrimaryKey());

		Assert.assertEquals(existingMSupplementalDiscActuals,
			newMSupplementalDiscActuals);
	}

	@Test(expected = NoSuchMSupplementalDiscActualsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		MSupplementalDiscActualsPK pk = new MSupplementalDiscActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		MSupplementalDiscActuals newMSupplementalDiscActuals = addMSupplementalDiscActuals();

		MSupplementalDiscActuals existingMSupplementalDiscActuals = _persistence.fetchByPrimaryKey(newMSupplementalDiscActuals.getPrimaryKey());

		Assert.assertEquals(existingMSupplementalDiscActuals,
			newMSupplementalDiscActuals);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		MSupplementalDiscActualsPK pk = new MSupplementalDiscActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		MSupplementalDiscActuals missingMSupplementalDiscActuals = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingMSupplementalDiscActuals);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		MSupplementalDiscActuals newMSupplementalDiscActuals1 = addMSupplementalDiscActuals();
		MSupplementalDiscActuals newMSupplementalDiscActuals2 = addMSupplementalDiscActuals();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMSupplementalDiscActuals1.getPrimaryKey());
		primaryKeys.add(newMSupplementalDiscActuals2.getPrimaryKey());

		Map<Serializable, MSupplementalDiscActuals> mSupplementalDiscActualses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, mSupplementalDiscActualses.size());
		Assert.assertEquals(newMSupplementalDiscActuals1,
			mSupplementalDiscActualses.get(
				newMSupplementalDiscActuals1.getPrimaryKey()));
		Assert.assertEquals(newMSupplementalDiscActuals2,
			mSupplementalDiscActualses.get(
				newMSupplementalDiscActuals2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		MSupplementalDiscActualsPK pk1 = new MSupplementalDiscActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		MSupplementalDiscActualsPK pk2 = new MSupplementalDiscActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, MSupplementalDiscActuals> mSupplementalDiscActualses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(mSupplementalDiscActualses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		MSupplementalDiscActuals newMSupplementalDiscActuals = addMSupplementalDiscActuals();

		MSupplementalDiscActualsPK pk = new MSupplementalDiscActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMSupplementalDiscActuals.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, MSupplementalDiscActuals> mSupplementalDiscActualses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, mSupplementalDiscActualses.size());
		Assert.assertEquals(newMSupplementalDiscActuals,
			mSupplementalDiscActualses.get(
				newMSupplementalDiscActuals.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, MSupplementalDiscActuals> mSupplementalDiscActualses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(mSupplementalDiscActualses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		MSupplementalDiscActuals newMSupplementalDiscActuals = addMSupplementalDiscActuals();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMSupplementalDiscActuals.getPrimaryKey());

		Map<Serializable, MSupplementalDiscActuals> mSupplementalDiscActualses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, mSupplementalDiscActualses.size());
		Assert.assertEquals(newMSupplementalDiscActuals,
			mSupplementalDiscActualses.get(
				newMSupplementalDiscActuals.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = MSupplementalDiscActualsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<MSupplementalDiscActuals>() {
				@Override
				public void performAction(
					MSupplementalDiscActuals mSupplementalDiscActuals) {
					Assert.assertNotNull(mSupplementalDiscActuals);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		MSupplementalDiscActuals newMSupplementalDiscActuals = addMSupplementalDiscActuals();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MSupplementalDiscActuals.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				newMSupplementalDiscActuals.getPeriodSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				newMSupplementalDiscActuals.getProjectionDetailsSid()));

		List<MSupplementalDiscActuals> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		MSupplementalDiscActuals existingMSupplementalDiscActuals = result.get(0);

		Assert.assertEquals(existingMSupplementalDiscActuals,
			newMSupplementalDiscActuals);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MSupplementalDiscActuals.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				RandomTestUtil.nextInt()));

		List<MSupplementalDiscActuals> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		MSupplementalDiscActuals newMSupplementalDiscActuals = addMSupplementalDiscActuals();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MSupplementalDiscActuals.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		Object newPeriodSid = newMSupplementalDiscActuals.getPeriodSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { newPeriodSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingPeriodSid = result.get(0);

		Assert.assertEquals(existingPeriodSid, newPeriodSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MSupplementalDiscActuals.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected MSupplementalDiscActuals addMSupplementalDiscActuals()
		throws Exception {
		MSupplementalDiscActualsPK pk = new MSupplementalDiscActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		MSupplementalDiscActuals mSupplementalDiscActuals = _persistence.create(pk);

		mSupplementalDiscActuals.setActualSales(RandomTestUtil.nextDouble());

		mSupplementalDiscActuals.setActualRate(RandomTestUtil.nextDouble());

		mSupplementalDiscActuals.setActualProjectionSales(RandomTestUtil.nextDouble());

		mSupplementalDiscActuals.setActualProjectionRate(RandomTestUtil.nextDouble());

		_mSupplementalDiscActualses.add(_persistence.update(
				mSupplementalDiscActuals));

		return mSupplementalDiscActuals;
	}

	private List<MSupplementalDiscActuals> _mSupplementalDiscActualses = new ArrayList<MSupplementalDiscActuals>();
	private MSupplementalDiscActualsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}