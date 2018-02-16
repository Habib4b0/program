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

import com.stpl.app.exception.NoSuchChSalesProjectionException;
import com.stpl.app.model.ChSalesProjection;
import com.stpl.app.service.ChSalesProjectionLocalServiceUtil;
import com.stpl.app.service.persistence.ChSalesProjectionPK;
import com.stpl.app.service.persistence.ChSalesProjectionPersistence;
import com.stpl.app.service.persistence.ChSalesProjectionUtil;

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
public class ChSalesProjectionPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ChSalesProjectionUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ChSalesProjection> iterator = _chSalesProjections.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		ChSalesProjectionPK pk = new ChSalesProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		ChSalesProjection chSalesProjection = _persistence.create(pk);

		Assert.assertNotNull(chSalesProjection);

		Assert.assertEquals(chSalesProjection.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ChSalesProjection newChSalesProjection = addChSalesProjection();

		_persistence.remove(newChSalesProjection);

		ChSalesProjection existingChSalesProjection = _persistence.fetchByPrimaryKey(newChSalesProjection.getPrimaryKey());

		Assert.assertNull(existingChSalesProjection);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addChSalesProjection();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		ChSalesProjectionPK pk = new ChSalesProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		ChSalesProjection newChSalesProjection = _persistence.create(pk);

		newChSalesProjection.setContractUnits(RandomTestUtil.nextDouble());

		newChSalesProjection.setPerOfBusiness(RandomTestUtil.nextDouble());

		newChSalesProjection.setContractSales(RandomTestUtil.nextDouble());

		newChSalesProjection.setGtsSales(RandomTestUtil.nextDouble());

		_chSalesProjections.add(_persistence.update(newChSalesProjection));

		ChSalesProjection existingChSalesProjection = _persistence.findByPrimaryKey(newChSalesProjection.getPrimaryKey());

		AssertUtils.assertEquals(existingChSalesProjection.getContractUnits(),
			newChSalesProjection.getContractUnits());
		AssertUtils.assertEquals(existingChSalesProjection.getPerOfBusiness(),
			newChSalesProjection.getPerOfBusiness());
		Assert.assertEquals(existingChSalesProjection.getPeriodSid(),
			newChSalesProjection.getPeriodSid());
		AssertUtils.assertEquals(existingChSalesProjection.getContractSales(),
			newChSalesProjection.getContractSales());
		Assert.assertEquals(existingChSalesProjection.getProjectionDetailsSid(),
			newChSalesProjection.getProjectionDetailsSid());
		AssertUtils.assertEquals(existingChSalesProjection.getGtsSales(),
			newChSalesProjection.getGtsSales());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ChSalesProjection newChSalesProjection = addChSalesProjection();

		ChSalesProjection existingChSalesProjection = _persistence.findByPrimaryKey(newChSalesProjection.getPrimaryKey());

		Assert.assertEquals(existingChSalesProjection, newChSalesProjection);
	}

	@Test(expected = NoSuchChSalesProjectionException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		ChSalesProjectionPK pk = new ChSalesProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ChSalesProjection newChSalesProjection = addChSalesProjection();

		ChSalesProjection existingChSalesProjection = _persistence.fetchByPrimaryKey(newChSalesProjection.getPrimaryKey());

		Assert.assertEquals(existingChSalesProjection, newChSalesProjection);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		ChSalesProjectionPK pk = new ChSalesProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		ChSalesProjection missingChSalesProjection = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingChSalesProjection);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ChSalesProjection newChSalesProjection1 = addChSalesProjection();
		ChSalesProjection newChSalesProjection2 = addChSalesProjection();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newChSalesProjection1.getPrimaryKey());
		primaryKeys.add(newChSalesProjection2.getPrimaryKey());

		Map<Serializable, ChSalesProjection> chSalesProjections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, chSalesProjections.size());
		Assert.assertEquals(newChSalesProjection1,
			chSalesProjections.get(newChSalesProjection1.getPrimaryKey()));
		Assert.assertEquals(newChSalesProjection2,
			chSalesProjections.get(newChSalesProjection2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		ChSalesProjectionPK pk1 = new ChSalesProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		ChSalesProjectionPK pk2 = new ChSalesProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ChSalesProjection> chSalesProjections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(chSalesProjections.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ChSalesProjection newChSalesProjection = addChSalesProjection();

		ChSalesProjectionPK pk = new ChSalesProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newChSalesProjection.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ChSalesProjection> chSalesProjections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, chSalesProjections.size());
		Assert.assertEquals(newChSalesProjection,
			chSalesProjections.get(newChSalesProjection.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ChSalesProjection> chSalesProjections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(chSalesProjections.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ChSalesProjection newChSalesProjection = addChSalesProjection();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newChSalesProjection.getPrimaryKey());

		Map<Serializable, ChSalesProjection> chSalesProjections = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, chSalesProjections.size());
		Assert.assertEquals(newChSalesProjection,
			chSalesProjections.get(newChSalesProjection.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ChSalesProjectionLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ChSalesProjection>() {
				@Override
				public void performAction(ChSalesProjection chSalesProjection) {
					Assert.assertNotNull(chSalesProjection);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ChSalesProjection newChSalesProjection = addChSalesProjection();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ChSalesProjection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				newChSalesProjection.getPeriodSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				newChSalesProjection.getProjectionDetailsSid()));

		List<ChSalesProjection> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ChSalesProjection existingChSalesProjection = result.get(0);

		Assert.assertEquals(existingChSalesProjection, newChSalesProjection);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ChSalesProjection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				RandomTestUtil.nextInt()));

		List<ChSalesProjection> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ChSalesProjection newChSalesProjection = addChSalesProjection();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ChSalesProjection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		Object newPeriodSid = newChSalesProjection.getPeriodSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { newPeriodSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingPeriodSid = result.get(0);

		Assert.assertEquals(existingPeriodSid, newPeriodSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ChSalesProjection.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ChSalesProjection addChSalesProjection()
		throws Exception {
		ChSalesProjectionPK pk = new ChSalesProjectionPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		ChSalesProjection chSalesProjection = _persistence.create(pk);

		chSalesProjection.setContractUnits(RandomTestUtil.nextDouble());

		chSalesProjection.setPerOfBusiness(RandomTestUtil.nextDouble());

		chSalesProjection.setContractSales(RandomTestUtil.nextDouble());

		chSalesProjection.setGtsSales(RandomTestUtil.nextDouble());

		_chSalesProjections.add(_persistence.update(chSalesProjection));

		return chSalesProjection;
	}

	private List<ChSalesProjection> _chSalesProjections = new ArrayList<ChSalesProjection>();
	private ChSalesProjectionPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}