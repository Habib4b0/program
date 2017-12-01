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

import com.stpl.app.exception.NoSuchPhsActualsException;
import com.stpl.app.model.PhsActuals;
import com.stpl.app.service.PhsActualsLocalServiceUtil;
import com.stpl.app.service.persistence.PhsActualsPK;
import com.stpl.app.service.persistence.PhsActualsPersistence;
import com.stpl.app.service.persistence.PhsActualsUtil;

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
public class PhsActualsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = PhsActualsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<PhsActuals> iterator = _phsActualses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		PhsActualsPK pk = new PhsActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		PhsActuals phsActuals = _persistence.create(pk);

		Assert.assertNotNull(phsActuals);

		Assert.assertEquals(phsActuals.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		PhsActuals newPhsActuals = addPhsActuals();

		_persistence.remove(newPhsActuals);

		PhsActuals existingPhsActuals = _persistence.fetchByPrimaryKey(newPhsActuals.getPrimaryKey());

		Assert.assertNull(existingPhsActuals);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addPhsActuals();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		PhsActualsPK pk = new PhsActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		PhsActuals newPhsActuals = _persistence.create(pk);

		newPhsActuals.setActualPrice(RandomTestUtil.nextDouble());

		newPhsActuals.setNotes(RandomTestUtil.randomString());

		_phsActualses.add(_persistence.update(newPhsActuals));

		PhsActuals existingPhsActuals = _persistence.findByPrimaryKey(newPhsActuals.getPrimaryKey());

		Assert.assertEquals(existingPhsActuals.getPeriodSid(),
			newPhsActuals.getPeriodSid());
		Assert.assertEquals(existingPhsActuals.getPriceType(),
			newPhsActuals.getPriceType());
		AssertUtils.assertEquals(existingPhsActuals.getActualPrice(),
			newPhsActuals.getActualPrice());
		Assert.assertEquals(existingPhsActuals.getNotes(),
			newPhsActuals.getNotes());
		Assert.assertEquals(existingPhsActuals.getNaProjDetailsSid(),
			newPhsActuals.getNaProjDetailsSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		PhsActuals newPhsActuals = addPhsActuals();

		PhsActuals existingPhsActuals = _persistence.findByPrimaryKey(newPhsActuals.getPrimaryKey());

		Assert.assertEquals(existingPhsActuals, newPhsActuals);
	}

	@Test(expected = NoSuchPhsActualsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		PhsActualsPK pk = new PhsActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		PhsActuals newPhsActuals = addPhsActuals();

		PhsActuals existingPhsActuals = _persistence.fetchByPrimaryKey(newPhsActuals.getPrimaryKey());

		Assert.assertEquals(existingPhsActuals, newPhsActuals);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		PhsActualsPK pk = new PhsActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		PhsActuals missingPhsActuals = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingPhsActuals);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		PhsActuals newPhsActuals1 = addPhsActuals();
		PhsActuals newPhsActuals2 = addPhsActuals();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newPhsActuals1.getPrimaryKey());
		primaryKeys.add(newPhsActuals2.getPrimaryKey());

		Map<Serializable, PhsActuals> phsActualses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, phsActualses.size());
		Assert.assertEquals(newPhsActuals1,
			phsActualses.get(newPhsActuals1.getPrimaryKey()));
		Assert.assertEquals(newPhsActuals2,
			phsActualses.get(newPhsActuals2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		PhsActualsPK pk1 = new PhsActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		PhsActualsPK pk2 = new PhsActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, PhsActuals> phsActualses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(phsActualses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		PhsActuals newPhsActuals = addPhsActuals();

		PhsActualsPK pk = new PhsActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newPhsActuals.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, PhsActuals> phsActualses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, phsActualses.size());
		Assert.assertEquals(newPhsActuals,
			phsActualses.get(newPhsActuals.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, PhsActuals> phsActualses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(phsActualses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		PhsActuals newPhsActuals = addPhsActuals();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newPhsActuals.getPrimaryKey());

		Map<Serializable, PhsActuals> phsActualses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, phsActualses.size());
		Assert.assertEquals(newPhsActuals,
			phsActualses.get(newPhsActuals.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = PhsActualsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<PhsActuals>() {
				@Override
				public void performAction(PhsActuals phsActuals) {
					Assert.assertNotNull(phsActuals);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		PhsActuals newPhsActuals = addPhsActuals();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PhsActuals.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				newPhsActuals.getPeriodSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.priceType",
				newPhsActuals.getPriceType()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.naProjDetailsSid",
				newPhsActuals.getNaProjDetailsSid()));

		List<PhsActuals> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		PhsActuals existingPhsActuals = result.get(0);

		Assert.assertEquals(existingPhsActuals, newPhsActuals);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PhsActuals.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.priceType",
				RandomTestUtil.randomString()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.naProjDetailsSid",
				RandomTestUtil.nextInt()));

		List<PhsActuals> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		PhsActuals newPhsActuals = addPhsActuals();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PhsActuals.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		Object newPeriodSid = newPhsActuals.getPeriodSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { newPeriodSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingPeriodSid = result.get(0);

		Assert.assertEquals(existingPeriodSid, newPeriodSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PhsActuals.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected PhsActuals addPhsActuals() throws Exception {
		PhsActualsPK pk = new PhsActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		PhsActuals phsActuals = _persistence.create(pk);

		phsActuals.setActualPrice(RandomTestUtil.nextDouble());

		phsActuals.setNotes(RandomTestUtil.randomString());

		_phsActualses.add(_persistence.update(phsActuals));

		return phsActuals;
	}

	private List<PhsActuals> _phsActualses = new ArrayList<PhsActuals>();
	private PhsActualsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}