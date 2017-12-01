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

import com.stpl.app.exception.NoSuchNmDiscountProjectionException;
import com.stpl.app.model.NmDiscountProjection;
import com.stpl.app.service.NmDiscountProjectionLocalServiceUtil;
import com.stpl.app.service.persistence.NmDiscountProjectionPK;
import com.stpl.app.service.persistence.NmDiscountProjectionPersistence;
import com.stpl.app.service.persistence.NmDiscountProjectionUtil;

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
public class NmDiscountProjectionPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = NmDiscountProjectionUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<NmDiscountProjection> iterator = _nmDiscountProjections.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		NmDiscountProjectionPK pk = new NmDiscountProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		NmDiscountProjection nmDiscountProjection = _persistence.create(pk);

		Assert.assertNotNull(nmDiscountProjection);

		Assert.assertEquals(nmDiscountProjection.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		NmDiscountProjection newNmDiscountProjection = addNmDiscountProjection();

		_persistence.remove(newNmDiscountProjection);

		NmDiscountProjection existingNmDiscountProjection = _persistence.fetchByPrimaryKey(newNmDiscountProjection.getPrimaryKey());

		Assert.assertNull(existingNmDiscountProjection);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addNmDiscountProjection();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		NmDiscountProjectionPK pk = new NmDiscountProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		NmDiscountProjection newNmDiscountProjection = _persistence.create(pk);

		newNmDiscountProjection.setProjectionRate(RandomTestUtil.nextDouble());

		newNmDiscountProjection.setProjectionSales(RandomTestUtil.nextDouble());

		_nmDiscountProjections.add(_persistence.update(newNmDiscountProjection));

		NmDiscountProjection existingNmDiscountProjection = _persistence.findByPrimaryKey(newNmDiscountProjection.getPrimaryKey());

		Assert.assertEquals(existingNmDiscountProjection.getPeriodSid(),
			newNmDiscountProjection.getPeriodSid());
		AssertUtils.assertEquals(existingNmDiscountProjection.getProjectionRate(),
			newNmDiscountProjection.getProjectionRate());
		Assert.assertEquals(existingNmDiscountProjection.getProjectionDetailsSid(),
			newNmDiscountProjection.getProjectionDetailsSid());
		AssertUtils.assertEquals(existingNmDiscountProjection.getProjectionSales(),
			newNmDiscountProjection.getProjectionSales());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		NmDiscountProjection newNmDiscountProjection = addNmDiscountProjection();

		NmDiscountProjection existingNmDiscountProjection = _persistence.findByPrimaryKey(newNmDiscountProjection.getPrimaryKey());

		Assert.assertEquals(existingNmDiscountProjection,
			newNmDiscountProjection);
	}

	@Test(expected = NoSuchNmDiscountProjectionException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		NmDiscountProjectionPK pk = new NmDiscountProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		NmDiscountProjection newNmDiscountProjection = addNmDiscountProjection();

		NmDiscountProjection existingNmDiscountProjection = _persistence.fetchByPrimaryKey(newNmDiscountProjection.getPrimaryKey());

		Assert.assertEquals(existingNmDiscountProjection,
			newNmDiscountProjection);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		NmDiscountProjectionPK pk = new NmDiscountProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		NmDiscountProjection missingNmDiscountProjection = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingNmDiscountProjection);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		NmDiscountProjection newNmDiscountProjection1 = addNmDiscountProjection();
		NmDiscountProjection newNmDiscountProjection2 = addNmDiscountProjection();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNmDiscountProjection1.getPrimaryKey());
		primaryKeys.add(newNmDiscountProjection2.getPrimaryKey());

		Map<Serializable, NmDiscountProjection> nmDiscountProjections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, nmDiscountProjections.size());
		Assert.assertEquals(newNmDiscountProjection1,
			nmDiscountProjections.get(newNmDiscountProjection1.getPrimaryKey()));
		Assert.assertEquals(newNmDiscountProjection2,
			nmDiscountProjections.get(newNmDiscountProjection2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		NmDiscountProjectionPK pk1 = new NmDiscountProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		NmDiscountProjectionPK pk2 = new NmDiscountProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, NmDiscountProjection> nmDiscountProjections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(nmDiscountProjections.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		NmDiscountProjection newNmDiscountProjection = addNmDiscountProjection();

		NmDiscountProjectionPK pk = new NmDiscountProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNmDiscountProjection.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, NmDiscountProjection> nmDiscountProjections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, nmDiscountProjections.size());
		Assert.assertEquals(newNmDiscountProjection,
			nmDiscountProjections.get(newNmDiscountProjection.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, NmDiscountProjection> nmDiscountProjections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(nmDiscountProjections.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		NmDiscountProjection newNmDiscountProjection = addNmDiscountProjection();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNmDiscountProjection.getPrimaryKey());

		Map<Serializable, NmDiscountProjection> nmDiscountProjections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, nmDiscountProjections.size());
		Assert.assertEquals(newNmDiscountProjection,
			nmDiscountProjections.get(newNmDiscountProjection.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = NmDiscountProjectionLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<NmDiscountProjection>() {
				@Override
				public void performAction(
					NmDiscountProjection nmDiscountProjection) {
					Assert.assertNotNull(nmDiscountProjection);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		NmDiscountProjection newNmDiscountProjection = addNmDiscountProjection();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NmDiscountProjection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				newNmDiscountProjection.getPeriodSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				newNmDiscountProjection.getProjectionDetailsSid()));

		List<NmDiscountProjection> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		NmDiscountProjection existingNmDiscountProjection = result.get(0);

		Assert.assertEquals(existingNmDiscountProjection,
			newNmDiscountProjection);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NmDiscountProjection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				RandomTestUtil.nextInt()));

		List<NmDiscountProjection> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		NmDiscountProjection newNmDiscountProjection = addNmDiscountProjection();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NmDiscountProjection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		Object newPeriodSid = newNmDiscountProjection.getPeriodSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { newPeriodSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingPeriodSid = result.get(0);

		Assert.assertEquals(existingPeriodSid, newPeriodSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NmDiscountProjection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected NmDiscountProjection addNmDiscountProjection()
		throws Exception {
		NmDiscountProjectionPK pk = new NmDiscountProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		NmDiscountProjection nmDiscountProjection = _persistence.create(pk);

		nmDiscountProjection.setProjectionRate(RandomTestUtil.nextDouble());

		nmDiscountProjection.setProjectionSales(RandomTestUtil.nextDouble());

		_nmDiscountProjections.add(_persistence.update(nmDiscountProjection));

		return nmDiscountProjection;
	}

	private List<NmDiscountProjection> _nmDiscountProjections = new ArrayList<NmDiscountProjection>();
	private NmDiscountProjectionPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}