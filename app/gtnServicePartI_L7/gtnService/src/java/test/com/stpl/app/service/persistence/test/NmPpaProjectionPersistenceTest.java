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

import com.stpl.app.exception.NoSuchNmPpaProjectionException;
import com.stpl.app.model.NmPpaProjection;
import com.stpl.app.service.NmPpaProjectionLocalServiceUtil;
import com.stpl.app.service.persistence.NmPpaProjectionPK;
import com.stpl.app.service.persistence.NmPpaProjectionPersistence;
import com.stpl.app.service.persistence.NmPpaProjectionUtil;

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
public class NmPpaProjectionPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = NmPpaProjectionUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<NmPpaProjection> iterator = _nmPpaProjections.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		NmPpaProjectionPK pk = new NmPpaProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		NmPpaProjection nmPpaProjection = _persistence.create(pk);

		Assert.assertNotNull(nmPpaProjection);

		Assert.assertEquals(nmPpaProjection.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		NmPpaProjection newNmPpaProjection = addNmPpaProjection();

		_persistence.remove(newNmPpaProjection);

		NmPpaProjection existingNmPpaProjection = _persistence.fetchByPrimaryKey(newNmPpaProjection.getPrimaryKey());

		Assert.assertNull(existingNmPpaProjection);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addNmPpaProjection();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		NmPpaProjectionPK pk = new NmPpaProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		NmPpaProjection newNmPpaProjection = _persistence.create(pk);

		newNmPpaProjection.setProjectionRate(RandomTestUtil.nextDouble());

		newNmPpaProjection.setPriceCap(RandomTestUtil.nextDouble());

		newNmPpaProjection.setProjectionDiscountUnits(RandomTestUtil.nextDouble());

		newNmPpaProjection.setProjectionDiscountDollar(RandomTestUtil.nextDouble());

		newNmPpaProjection.setReset(RandomTestUtil.randomBoolean());

		newNmPpaProjection.setProjectionSales(RandomTestUtil.nextDouble());

		newNmPpaProjection.setProjectionMap(RandomTestUtil.nextDouble());

		newNmPpaProjection.setResetPriceCap(RandomTestUtil.randomBoolean());

		_nmPpaProjections.add(_persistence.update(newNmPpaProjection));

		NmPpaProjection existingNmPpaProjection = _persistence.findByPrimaryKey(newNmPpaProjection.getPrimaryKey());

		Assert.assertEquals(existingNmPpaProjection.getPeriodSid(),
			newNmPpaProjection.getPeriodSid());
		AssertUtils.assertEquals(existingNmPpaProjection.getProjectionRate(),
			newNmPpaProjection.getProjectionRate());
		Assert.assertEquals(existingNmPpaProjection.getProjectionDetailsSid(),
			newNmPpaProjection.getProjectionDetailsSid());
		AssertUtils.assertEquals(existingNmPpaProjection.getPriceCap(),
			newNmPpaProjection.getPriceCap());
		AssertUtils.assertEquals(existingNmPpaProjection.getProjectionDiscountUnits(),
			newNmPpaProjection.getProjectionDiscountUnits());
		AssertUtils.assertEquals(existingNmPpaProjection.getProjectionDiscountDollar(),
			newNmPpaProjection.getProjectionDiscountDollar());
		Assert.assertEquals(existingNmPpaProjection.getReset(),
			newNmPpaProjection.getReset());
		AssertUtils.assertEquals(existingNmPpaProjection.getProjectionSales(),
			newNmPpaProjection.getProjectionSales());
		AssertUtils.assertEquals(existingNmPpaProjection.getProjectionMap(),
			newNmPpaProjection.getProjectionMap());
		Assert.assertEquals(existingNmPpaProjection.getResetPriceCap(),
			newNmPpaProjection.getResetPriceCap());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		NmPpaProjection newNmPpaProjection = addNmPpaProjection();

		NmPpaProjection existingNmPpaProjection = _persistence.findByPrimaryKey(newNmPpaProjection.getPrimaryKey());

		Assert.assertEquals(existingNmPpaProjection, newNmPpaProjection);
	}

	@Test(expected = NoSuchNmPpaProjectionException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		NmPpaProjectionPK pk = new NmPpaProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		NmPpaProjection newNmPpaProjection = addNmPpaProjection();

		NmPpaProjection existingNmPpaProjection = _persistence.fetchByPrimaryKey(newNmPpaProjection.getPrimaryKey());

		Assert.assertEquals(existingNmPpaProjection, newNmPpaProjection);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		NmPpaProjectionPK pk = new NmPpaProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		NmPpaProjection missingNmPpaProjection = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingNmPpaProjection);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		NmPpaProjection newNmPpaProjection1 = addNmPpaProjection();
		NmPpaProjection newNmPpaProjection2 = addNmPpaProjection();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNmPpaProjection1.getPrimaryKey());
		primaryKeys.add(newNmPpaProjection2.getPrimaryKey());

		Map<Serializable, NmPpaProjection> nmPpaProjections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, nmPpaProjections.size());
		Assert.assertEquals(newNmPpaProjection1,
			nmPpaProjections.get(newNmPpaProjection1.getPrimaryKey()));
		Assert.assertEquals(newNmPpaProjection2,
			nmPpaProjections.get(newNmPpaProjection2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		NmPpaProjectionPK pk1 = new NmPpaProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		NmPpaProjectionPK pk2 = new NmPpaProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, NmPpaProjection> nmPpaProjections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(nmPpaProjections.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		NmPpaProjection newNmPpaProjection = addNmPpaProjection();

		NmPpaProjectionPK pk = new NmPpaProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNmPpaProjection.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, NmPpaProjection> nmPpaProjections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, nmPpaProjections.size());
		Assert.assertEquals(newNmPpaProjection,
			nmPpaProjections.get(newNmPpaProjection.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, NmPpaProjection> nmPpaProjections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(nmPpaProjections.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		NmPpaProjection newNmPpaProjection = addNmPpaProjection();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNmPpaProjection.getPrimaryKey());

		Map<Serializable, NmPpaProjection> nmPpaProjections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, nmPpaProjections.size());
		Assert.assertEquals(newNmPpaProjection,
			nmPpaProjections.get(newNmPpaProjection.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = NmPpaProjectionLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<NmPpaProjection>() {
				@Override
				public void performAction(NmPpaProjection nmPpaProjection) {
					Assert.assertNotNull(nmPpaProjection);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		NmPpaProjection newNmPpaProjection = addNmPpaProjection();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NmPpaProjection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				newNmPpaProjection.getPeriodSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				newNmPpaProjection.getProjectionDetailsSid()));

		List<NmPpaProjection> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		NmPpaProjection existingNmPpaProjection = result.get(0);

		Assert.assertEquals(existingNmPpaProjection, newNmPpaProjection);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NmPpaProjection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				RandomTestUtil.nextInt()));

		List<NmPpaProjection> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		NmPpaProjection newNmPpaProjection = addNmPpaProjection();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NmPpaProjection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		Object newPeriodSid = newNmPpaProjection.getPeriodSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { newPeriodSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingPeriodSid = result.get(0);

		Assert.assertEquals(existingPeriodSid, newPeriodSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NmPpaProjection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected NmPpaProjection addNmPpaProjection() throws Exception {
		NmPpaProjectionPK pk = new NmPpaProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		NmPpaProjection nmPpaProjection = _persistence.create(pk);

		nmPpaProjection.setProjectionRate(RandomTestUtil.nextDouble());

		nmPpaProjection.setPriceCap(RandomTestUtil.nextDouble());

		nmPpaProjection.setProjectionDiscountUnits(RandomTestUtil.nextDouble());

		nmPpaProjection.setProjectionDiscountDollar(RandomTestUtil.nextDouble());

		nmPpaProjection.setReset(RandomTestUtil.randomBoolean());

		nmPpaProjection.setProjectionSales(RandomTestUtil.nextDouble());

		nmPpaProjection.setProjectionMap(RandomTestUtil.nextDouble());

		nmPpaProjection.setResetPriceCap(RandomTestUtil.randomBoolean());

		_nmPpaProjections.add(_persistence.update(nmPpaProjection));

		return nmPpaProjection;
	}

	private List<NmPpaProjection> _nmPpaProjections = new ArrayList<NmPpaProjection>();
	private NmPpaProjectionPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}