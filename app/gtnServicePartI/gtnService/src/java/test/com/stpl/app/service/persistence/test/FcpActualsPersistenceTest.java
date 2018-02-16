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

import com.stpl.app.exception.NoSuchFcpActualsException;
import com.stpl.app.model.FcpActuals;
import com.stpl.app.service.FcpActualsLocalServiceUtil;
import com.stpl.app.service.persistence.FcpActualsPK;
import com.stpl.app.service.persistence.FcpActualsPersistence;
import com.stpl.app.service.persistence.FcpActualsUtil;

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
public class FcpActualsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = FcpActualsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<FcpActuals> iterator = _fcpActualses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		FcpActualsPK pk = new FcpActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		FcpActuals fcpActuals = _persistence.create(pk);

		Assert.assertNotNull(fcpActuals);

		Assert.assertEquals(fcpActuals.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		FcpActuals newFcpActuals = addFcpActuals();

		_persistence.remove(newFcpActuals);

		FcpActuals existingFcpActuals = _persistence.fetchByPrimaryKey(newFcpActuals.getPrimaryKey());

		Assert.assertNull(existingFcpActuals);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addFcpActuals();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		FcpActualsPK pk = new FcpActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		FcpActuals newFcpActuals = _persistence.create(pk);

		newFcpActuals.setActualPrice(RandomTestUtil.nextDouble());

		newFcpActuals.setNotes(RandomTestUtil.randomString());

		_fcpActualses.add(_persistence.update(newFcpActuals));

		FcpActuals existingFcpActuals = _persistence.findByPrimaryKey(newFcpActuals.getPrimaryKey());

		Assert.assertEquals(existingFcpActuals.getPeriodSid(),
			newFcpActuals.getPeriodSid());
		Assert.assertEquals(existingFcpActuals.getPriceType(),
			newFcpActuals.getPriceType());
		AssertUtils.assertEquals(existingFcpActuals.getActualPrice(),
			newFcpActuals.getActualPrice());
		Assert.assertEquals(existingFcpActuals.getNotes(),
			newFcpActuals.getNotes());
		Assert.assertEquals(existingFcpActuals.getNaProjDetailsSid(),
			newFcpActuals.getNaProjDetailsSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		FcpActuals newFcpActuals = addFcpActuals();

		FcpActuals existingFcpActuals = _persistence.findByPrimaryKey(newFcpActuals.getPrimaryKey());

		Assert.assertEquals(existingFcpActuals, newFcpActuals);
	}

	@Test(expected = NoSuchFcpActualsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		FcpActualsPK pk = new FcpActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		FcpActuals newFcpActuals = addFcpActuals();

		FcpActuals existingFcpActuals = _persistence.fetchByPrimaryKey(newFcpActuals.getPrimaryKey());

		Assert.assertEquals(existingFcpActuals, newFcpActuals);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		FcpActualsPK pk = new FcpActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		FcpActuals missingFcpActuals = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingFcpActuals);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		FcpActuals newFcpActuals1 = addFcpActuals();
		FcpActuals newFcpActuals2 = addFcpActuals();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newFcpActuals1.getPrimaryKey());
		primaryKeys.add(newFcpActuals2.getPrimaryKey());

		Map<Serializable, FcpActuals> fcpActualses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, fcpActualses.size());
		Assert.assertEquals(newFcpActuals1,
			fcpActualses.get(newFcpActuals1.getPrimaryKey()));
		Assert.assertEquals(newFcpActuals2,
			fcpActualses.get(newFcpActuals2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		FcpActualsPK pk1 = new FcpActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		FcpActualsPK pk2 = new FcpActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, FcpActuals> fcpActualses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(fcpActualses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		FcpActuals newFcpActuals = addFcpActuals();

		FcpActualsPK pk = new FcpActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newFcpActuals.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, FcpActuals> fcpActualses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, fcpActualses.size());
		Assert.assertEquals(newFcpActuals,
			fcpActualses.get(newFcpActuals.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, FcpActuals> fcpActualses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(fcpActualses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		FcpActuals newFcpActuals = addFcpActuals();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newFcpActuals.getPrimaryKey());

		Map<Serializable, FcpActuals> fcpActualses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, fcpActualses.size());
		Assert.assertEquals(newFcpActuals,
			fcpActualses.get(newFcpActuals.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = FcpActualsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<FcpActuals>() {
				@Override
				public void performAction(FcpActuals fcpActuals) {
					Assert.assertNotNull(fcpActuals);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		FcpActuals newFcpActuals = addFcpActuals();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(FcpActuals.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				newFcpActuals.getPeriodSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.priceType",
				newFcpActuals.getPriceType()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.naProjDetailsSid",
				newFcpActuals.getNaProjDetailsSid()));

		List<FcpActuals> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		FcpActuals existingFcpActuals = result.get(0);

		Assert.assertEquals(existingFcpActuals, newFcpActuals);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(FcpActuals.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.priceType",
				RandomTestUtil.randomString()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.naProjDetailsSid",
				RandomTestUtil.nextInt()));

		List<FcpActuals> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		FcpActuals newFcpActuals = addFcpActuals();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(FcpActuals.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		Object newPeriodSid = newFcpActuals.getPeriodSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { newPeriodSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingPeriodSid = result.get(0);

		Assert.assertEquals(existingPeriodSid, newPeriodSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(FcpActuals.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected FcpActuals addFcpActuals() throws Exception {
		FcpActualsPK pk = new FcpActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		FcpActuals fcpActuals = _persistence.create(pk);

		fcpActuals.setActualPrice(RandomTestUtil.nextDouble());

		fcpActuals.setNotes(RandomTestUtil.randomString());

		_fcpActualses.add(_persistence.update(fcpActuals));

		return fcpActuals;
	}

	private List<FcpActuals> _fcpActualses = new ArrayList<FcpActuals>();
	private FcpActualsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}