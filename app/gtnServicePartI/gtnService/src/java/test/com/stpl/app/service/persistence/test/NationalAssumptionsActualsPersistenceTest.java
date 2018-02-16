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

import com.stpl.app.exception.NoSuchNationalAssumptionsActualsException;
import com.stpl.app.model.NationalAssumptionsActuals;
import com.stpl.app.service.NationalAssumptionsActualsLocalServiceUtil;
import com.stpl.app.service.persistence.NationalAssumptionsActualsPK;
import com.stpl.app.service.persistence.NationalAssumptionsActualsPersistence;
import com.stpl.app.service.persistence.NationalAssumptionsActualsUtil;

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
public class NationalAssumptionsActualsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = NationalAssumptionsActualsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<NationalAssumptionsActuals> iterator = _nationalAssumptionsActualses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		NationalAssumptionsActualsPK pk = new NationalAssumptionsActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString());

		NationalAssumptionsActuals nationalAssumptionsActuals = _persistence.create(pk);

		Assert.assertNotNull(nationalAssumptionsActuals);

		Assert.assertEquals(nationalAssumptionsActuals.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		NationalAssumptionsActuals newNationalAssumptionsActuals = addNationalAssumptionsActuals();

		_persistence.remove(newNationalAssumptionsActuals);

		NationalAssumptionsActuals existingNationalAssumptionsActuals = _persistence.fetchByPrimaryKey(newNationalAssumptionsActuals.getPrimaryKey());

		Assert.assertNull(existingNationalAssumptionsActuals);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addNationalAssumptionsActuals();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		NationalAssumptionsActualsPK pk = new NationalAssumptionsActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString());

		NationalAssumptionsActuals newNationalAssumptionsActuals = _persistence.create(pk);

		newNationalAssumptionsActuals.setActualPrice(RandomTestUtil.nextDouble());

		_nationalAssumptionsActualses.add(_persistence.update(
				newNationalAssumptionsActuals));

		NationalAssumptionsActuals existingNationalAssumptionsActuals = _persistence.findByPrimaryKey(newNationalAssumptionsActuals.getPrimaryKey());

		Assert.assertEquals(existingNationalAssumptionsActuals.getPeriodSid(),
			newNationalAssumptionsActuals.getPeriodSid());
		Assert.assertEquals(existingNationalAssumptionsActuals.getItemMasterSid(),
			newNationalAssumptionsActuals.getItemMasterSid());
		Assert.assertEquals(existingNationalAssumptionsActuals.getPriceType(),
			newNationalAssumptionsActuals.getPriceType());
		AssertUtils.assertEquals(existingNationalAssumptionsActuals.getActualPrice(),
			newNationalAssumptionsActuals.getActualPrice());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		NationalAssumptionsActuals newNationalAssumptionsActuals = addNationalAssumptionsActuals();

		NationalAssumptionsActuals existingNationalAssumptionsActuals = _persistence.findByPrimaryKey(newNationalAssumptionsActuals.getPrimaryKey());

		Assert.assertEquals(existingNationalAssumptionsActuals,
			newNationalAssumptionsActuals);
	}

	@Test(expected = NoSuchNationalAssumptionsActualsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		NationalAssumptionsActualsPK pk = new NationalAssumptionsActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		NationalAssumptionsActuals newNationalAssumptionsActuals = addNationalAssumptionsActuals();

		NationalAssumptionsActuals existingNationalAssumptionsActuals = _persistence.fetchByPrimaryKey(newNationalAssumptionsActuals.getPrimaryKey());

		Assert.assertEquals(existingNationalAssumptionsActuals,
			newNationalAssumptionsActuals);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		NationalAssumptionsActualsPK pk = new NationalAssumptionsActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString());

		NationalAssumptionsActuals missingNationalAssumptionsActuals = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingNationalAssumptionsActuals);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		NationalAssumptionsActuals newNationalAssumptionsActuals1 = addNationalAssumptionsActuals();
		NationalAssumptionsActuals newNationalAssumptionsActuals2 = addNationalAssumptionsActuals();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNationalAssumptionsActuals1.getPrimaryKey());
		primaryKeys.add(newNationalAssumptionsActuals2.getPrimaryKey());

		Map<Serializable, NationalAssumptionsActuals> nationalAssumptionsActualses =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, nationalAssumptionsActualses.size());
		Assert.assertEquals(newNationalAssumptionsActuals1,
			nationalAssumptionsActualses.get(
				newNationalAssumptionsActuals1.getPrimaryKey()));
		Assert.assertEquals(newNationalAssumptionsActuals2,
			nationalAssumptionsActualses.get(
				newNationalAssumptionsActuals2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		NationalAssumptionsActualsPK pk1 = new NationalAssumptionsActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString());

		NationalAssumptionsActualsPK pk2 = new NationalAssumptionsActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, NationalAssumptionsActuals> nationalAssumptionsActualses =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(nationalAssumptionsActualses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		NationalAssumptionsActuals newNationalAssumptionsActuals = addNationalAssumptionsActuals();

		NationalAssumptionsActualsPK pk = new NationalAssumptionsActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNationalAssumptionsActuals.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, NationalAssumptionsActuals> nationalAssumptionsActualses =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, nationalAssumptionsActualses.size());
		Assert.assertEquals(newNationalAssumptionsActuals,
			nationalAssumptionsActualses.get(
				newNationalAssumptionsActuals.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, NationalAssumptionsActuals> nationalAssumptionsActualses =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(nationalAssumptionsActualses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		NationalAssumptionsActuals newNationalAssumptionsActuals = addNationalAssumptionsActuals();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNationalAssumptionsActuals.getPrimaryKey());

		Map<Serializable, NationalAssumptionsActuals> nationalAssumptionsActualses =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, nationalAssumptionsActualses.size());
		Assert.assertEquals(newNationalAssumptionsActuals,
			nationalAssumptionsActualses.get(
				newNationalAssumptionsActuals.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = NationalAssumptionsActualsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<NationalAssumptionsActuals>() {
				@Override
				public void performAction(
					NationalAssumptionsActuals nationalAssumptionsActuals) {
					Assert.assertNotNull(nationalAssumptionsActuals);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		NationalAssumptionsActuals newNationalAssumptionsActuals = addNationalAssumptionsActuals();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NationalAssumptionsActuals.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				newNationalAssumptionsActuals.getPeriodSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.itemMasterSid",
				newNationalAssumptionsActuals.getItemMasterSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.priceType",
				newNationalAssumptionsActuals.getPriceType()));

		List<NationalAssumptionsActuals> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		NationalAssumptionsActuals existingNationalAssumptionsActuals = result.get(0);

		Assert.assertEquals(existingNationalAssumptionsActuals,
			newNationalAssumptionsActuals);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NationalAssumptionsActuals.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.itemMasterSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.priceType",
				RandomTestUtil.randomString()));

		List<NationalAssumptionsActuals> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		NationalAssumptionsActuals newNationalAssumptionsActuals = addNationalAssumptionsActuals();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NationalAssumptionsActuals.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		Object newPeriodSid = newNationalAssumptionsActuals.getPeriodSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { newPeriodSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingPeriodSid = result.get(0);

		Assert.assertEquals(existingPeriodSid, newPeriodSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NationalAssumptionsActuals.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected NationalAssumptionsActuals addNationalAssumptionsActuals()
		throws Exception {
		NationalAssumptionsActualsPK pk = new NationalAssumptionsActualsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString());

		NationalAssumptionsActuals nationalAssumptionsActuals = _persistence.create(pk);

		nationalAssumptionsActuals.setActualPrice(RandomTestUtil.nextDouble());

		_nationalAssumptionsActualses.add(_persistence.update(
				nationalAssumptionsActuals));

		return nationalAssumptionsActuals;
	}

	private List<NationalAssumptionsActuals> _nationalAssumptionsActualses = new ArrayList<NationalAssumptionsActuals>();
	private NationalAssumptionsActualsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}