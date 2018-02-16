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

import com.stpl.app.exception.NoSuchFcpProjException;
import com.stpl.app.model.FcpProj;
import com.stpl.app.service.FcpProjLocalServiceUtil;
import com.stpl.app.service.persistence.FcpProjPK;
import com.stpl.app.service.persistence.FcpProjPersistence;
import com.stpl.app.service.persistence.FcpProjUtil;

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
public class FcpProjPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = FcpProjUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<FcpProj> iterator = _fcpProjs.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		FcpProjPK pk = new FcpProjPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		FcpProj fcpProj = _persistence.create(pk);

		Assert.assertNotNull(fcpProj);

		Assert.assertEquals(fcpProj.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		FcpProj newFcpProj = addFcpProj();

		_persistence.remove(newFcpProj);

		FcpProj existingFcpProj = _persistence.fetchByPrimaryKey(newFcpProj.getPrimaryKey());

		Assert.assertNull(existingFcpProj);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addFcpProj();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		FcpProjPK pk = new FcpProjPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		FcpProj newFcpProj = _persistence.create(pk);

		newFcpProj.setAdjustment(RandomTestUtil.nextDouble());

		newFcpProj.setProjectionPrice(RandomTestUtil.nextDouble());

		newFcpProj.setNotes(RandomTestUtil.randomString());

		_fcpProjs.add(_persistence.update(newFcpProj));

		FcpProj existingFcpProj = _persistence.findByPrimaryKey(newFcpProj.getPrimaryKey());

		AssertUtils.assertEquals(existingFcpProj.getAdjustment(),
			newFcpProj.getAdjustment());
		Assert.assertEquals(existingFcpProj.getPeriodSid(),
			newFcpProj.getPeriodSid());
		Assert.assertEquals(existingFcpProj.getPriceType(),
			newFcpProj.getPriceType());
		AssertUtils.assertEquals(existingFcpProj.getProjectionPrice(),
			newFcpProj.getProjectionPrice());
		Assert.assertEquals(existingFcpProj.getNotes(), newFcpProj.getNotes());
		Assert.assertEquals(existingFcpProj.getNaProjDetailsSid(),
			newFcpProj.getNaProjDetailsSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		FcpProj newFcpProj = addFcpProj();

		FcpProj existingFcpProj = _persistence.findByPrimaryKey(newFcpProj.getPrimaryKey());

		Assert.assertEquals(existingFcpProj, newFcpProj);
	}

	@Test(expected = NoSuchFcpProjException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		FcpProjPK pk = new FcpProjPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		FcpProj newFcpProj = addFcpProj();

		FcpProj existingFcpProj = _persistence.fetchByPrimaryKey(newFcpProj.getPrimaryKey());

		Assert.assertEquals(existingFcpProj, newFcpProj);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		FcpProjPK pk = new FcpProjPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		FcpProj missingFcpProj = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingFcpProj);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		FcpProj newFcpProj1 = addFcpProj();
		FcpProj newFcpProj2 = addFcpProj();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newFcpProj1.getPrimaryKey());
		primaryKeys.add(newFcpProj2.getPrimaryKey());

		Map<Serializable, FcpProj> fcpProjs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, fcpProjs.size());
		Assert.assertEquals(newFcpProj1,
			fcpProjs.get(newFcpProj1.getPrimaryKey()));
		Assert.assertEquals(newFcpProj2,
			fcpProjs.get(newFcpProj2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		FcpProjPK pk1 = new FcpProjPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		FcpProjPK pk2 = new FcpProjPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, FcpProj> fcpProjs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(fcpProjs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		FcpProj newFcpProj = addFcpProj();

		FcpProjPK pk = new FcpProjPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newFcpProj.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, FcpProj> fcpProjs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, fcpProjs.size());
		Assert.assertEquals(newFcpProj, fcpProjs.get(newFcpProj.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, FcpProj> fcpProjs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(fcpProjs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		FcpProj newFcpProj = addFcpProj();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newFcpProj.getPrimaryKey());

		Map<Serializable, FcpProj> fcpProjs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, fcpProjs.size());
		Assert.assertEquals(newFcpProj, fcpProjs.get(newFcpProj.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = FcpProjLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<FcpProj>() {
				@Override
				public void performAction(FcpProj fcpProj) {
					Assert.assertNotNull(fcpProj);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		FcpProj newFcpProj = addFcpProj();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(FcpProj.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				newFcpProj.getPeriodSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.priceType",
				newFcpProj.getPriceType()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.naProjDetailsSid",
				newFcpProj.getNaProjDetailsSid()));

		List<FcpProj> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		FcpProj existingFcpProj = result.get(0);

		Assert.assertEquals(existingFcpProj, newFcpProj);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(FcpProj.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.priceType",
				RandomTestUtil.randomString()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.naProjDetailsSid",
				RandomTestUtil.nextInt()));

		List<FcpProj> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		FcpProj newFcpProj = addFcpProj();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(FcpProj.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		Object newPeriodSid = newFcpProj.getPeriodSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { newPeriodSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingPeriodSid = result.get(0);

		Assert.assertEquals(existingPeriodSid, newPeriodSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(FcpProj.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected FcpProj addFcpProj() throws Exception {
		FcpProjPK pk = new FcpProjPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		FcpProj fcpProj = _persistence.create(pk);

		fcpProj.setAdjustment(RandomTestUtil.nextDouble());

		fcpProj.setProjectionPrice(RandomTestUtil.nextDouble());

		fcpProj.setNotes(RandomTestUtil.randomString());

		_fcpProjs.add(_persistence.update(fcpProj));

		return fcpProj;
	}

	private List<FcpProj> _fcpProjs = new ArrayList<FcpProj>();
	private FcpProjPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}