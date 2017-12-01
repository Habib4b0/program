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

import com.stpl.app.exception.NoSuchStMSupplementalDiscProjException;
import com.stpl.app.model.StMSupplementalDiscProj;
import com.stpl.app.service.StMSupplementalDiscProjLocalServiceUtil;
import com.stpl.app.service.persistence.StMSupplementalDiscProjPK;
import com.stpl.app.service.persistence.StMSupplementalDiscProjPersistence;
import com.stpl.app.service.persistence.StMSupplementalDiscProjUtil;

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
public class StMSupplementalDiscProjPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = StMSupplementalDiscProjUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<StMSupplementalDiscProj> iterator = _stMSupplementalDiscProjs.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		StMSupplementalDiscProjPK pk = new StMSupplementalDiscProjPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StMSupplementalDiscProj stMSupplementalDiscProj = _persistence.create(pk);

		Assert.assertNotNull(stMSupplementalDiscProj);

		Assert.assertEquals(stMSupplementalDiscProj.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		StMSupplementalDiscProj newStMSupplementalDiscProj = addStMSupplementalDiscProj();

		_persistence.remove(newStMSupplementalDiscProj);

		StMSupplementalDiscProj existingStMSupplementalDiscProj = _persistence.fetchByPrimaryKey(newStMSupplementalDiscProj.getPrimaryKey());

		Assert.assertNull(existingStMSupplementalDiscProj);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addStMSupplementalDiscProj();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		StMSupplementalDiscProjPK pk = new StMSupplementalDiscProjPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StMSupplementalDiscProj newStMSupplementalDiscProj = _persistence.create(pk);

		newStMSupplementalDiscProj.setProjectionRate(RandomTestUtil.nextDouble());

		newStMSupplementalDiscProj.setLastModifiedDate(RandomTestUtil.nextDate());

		newStMSupplementalDiscProj.setParityReference(RandomTestUtil.randomString());

		newStMSupplementalDiscProj.setProjectionSales(RandomTestUtil.nextDouble());

		newStMSupplementalDiscProj.setContractPrice(RandomTestUtil.nextDouble());

		newStMSupplementalDiscProj.setMethodology(RandomTestUtil.randomString());

		newStMSupplementalDiscProj.setParity(RandomTestUtil.randomBoolean());

		newStMSupplementalDiscProj.setPeriodSid(RandomTestUtil.nextInt());

		newStMSupplementalDiscProj.setDiscountRate1(RandomTestUtil.nextDouble());

		newStMSupplementalDiscProj.setDiscountRate2(RandomTestUtil.nextDouble());

		newStMSupplementalDiscProj.setParityDiscount(RandomTestUtil.nextDouble());

		newStMSupplementalDiscProj.setAccess(RandomTestUtil.randomString());

		_stMSupplementalDiscProjs.add(_persistence.update(
				newStMSupplementalDiscProj));

		StMSupplementalDiscProj existingStMSupplementalDiscProj = _persistence.findByPrimaryKey(newStMSupplementalDiscProj.getPrimaryKey());

		AssertUtils.assertEquals(existingStMSupplementalDiscProj.getProjectionRate(),
			newStMSupplementalDiscProj.getProjectionRate());
		Assert.assertEquals(existingStMSupplementalDiscProj.getUserId(),
			newStMSupplementalDiscProj.getUserId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingStMSupplementalDiscProj.getLastModifiedDate()),
			Time.getShortTimestamp(
				newStMSupplementalDiscProj.getLastModifiedDate()));
		Assert.assertEquals(existingStMSupplementalDiscProj.getParityReference(),
			newStMSupplementalDiscProj.getParityReference());
		AssertUtils.assertEquals(existingStMSupplementalDiscProj.getProjectionSales(),
			newStMSupplementalDiscProj.getProjectionSales());
		AssertUtils.assertEquals(existingStMSupplementalDiscProj.getContractPrice(),
			newStMSupplementalDiscProj.getContractPrice());
		Assert.assertEquals(existingStMSupplementalDiscProj.getMethodology(),
			newStMSupplementalDiscProj.getMethodology());
		Assert.assertEquals(existingStMSupplementalDiscProj.getParity(),
			newStMSupplementalDiscProj.getParity());
		Assert.assertEquals(existingStMSupplementalDiscProj.getPeriodSid(),
			newStMSupplementalDiscProj.getPeriodSid());
		AssertUtils.assertEquals(existingStMSupplementalDiscProj.getDiscountRate1(),
			newStMSupplementalDiscProj.getDiscountRate1());
		Assert.assertEquals(existingStMSupplementalDiscProj.getProjectionDetailsSid(),
			newStMSupplementalDiscProj.getProjectionDetailsSid());
		AssertUtils.assertEquals(existingStMSupplementalDiscProj.getDiscountRate2(),
			newStMSupplementalDiscProj.getDiscountRate2());
		AssertUtils.assertEquals(existingStMSupplementalDiscProj.getParityDiscount(),
			newStMSupplementalDiscProj.getParityDiscount());
		Assert.assertEquals(existingStMSupplementalDiscProj.getSessionId(),
			newStMSupplementalDiscProj.getSessionId());
		Assert.assertEquals(existingStMSupplementalDiscProj.getAccess(),
			newStMSupplementalDiscProj.getAccess());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		StMSupplementalDiscProj newStMSupplementalDiscProj = addStMSupplementalDiscProj();

		StMSupplementalDiscProj existingStMSupplementalDiscProj = _persistence.findByPrimaryKey(newStMSupplementalDiscProj.getPrimaryKey());

		Assert.assertEquals(existingStMSupplementalDiscProj,
			newStMSupplementalDiscProj);
	}

	@Test(expected = NoSuchStMSupplementalDiscProjException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		StMSupplementalDiscProjPK pk = new StMSupplementalDiscProjPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		StMSupplementalDiscProj newStMSupplementalDiscProj = addStMSupplementalDiscProj();

		StMSupplementalDiscProj existingStMSupplementalDiscProj = _persistence.fetchByPrimaryKey(newStMSupplementalDiscProj.getPrimaryKey());

		Assert.assertEquals(existingStMSupplementalDiscProj,
			newStMSupplementalDiscProj);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		StMSupplementalDiscProjPK pk = new StMSupplementalDiscProjPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StMSupplementalDiscProj missingStMSupplementalDiscProj = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingStMSupplementalDiscProj);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		StMSupplementalDiscProj newStMSupplementalDiscProj1 = addStMSupplementalDiscProj();
		StMSupplementalDiscProj newStMSupplementalDiscProj2 = addStMSupplementalDiscProj();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStMSupplementalDiscProj1.getPrimaryKey());
		primaryKeys.add(newStMSupplementalDiscProj2.getPrimaryKey());

		Map<Serializable, StMSupplementalDiscProj> stMSupplementalDiscProjs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, stMSupplementalDiscProjs.size());
		Assert.assertEquals(newStMSupplementalDiscProj1,
			stMSupplementalDiscProjs.get(
				newStMSupplementalDiscProj1.getPrimaryKey()));
		Assert.assertEquals(newStMSupplementalDiscProj2,
			stMSupplementalDiscProjs.get(
				newStMSupplementalDiscProj2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		StMSupplementalDiscProjPK pk1 = new StMSupplementalDiscProjPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StMSupplementalDiscProjPK pk2 = new StMSupplementalDiscProjPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, StMSupplementalDiscProj> stMSupplementalDiscProjs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stMSupplementalDiscProjs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		StMSupplementalDiscProj newStMSupplementalDiscProj = addStMSupplementalDiscProj();

		StMSupplementalDiscProjPK pk = new StMSupplementalDiscProjPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStMSupplementalDiscProj.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, StMSupplementalDiscProj> stMSupplementalDiscProjs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stMSupplementalDiscProjs.size());
		Assert.assertEquals(newStMSupplementalDiscProj,
			stMSupplementalDiscProjs.get(
				newStMSupplementalDiscProj.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, StMSupplementalDiscProj> stMSupplementalDiscProjs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stMSupplementalDiscProjs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		StMSupplementalDiscProj newStMSupplementalDiscProj = addStMSupplementalDiscProj();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStMSupplementalDiscProj.getPrimaryKey());

		Map<Serializable, StMSupplementalDiscProj> stMSupplementalDiscProjs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stMSupplementalDiscProjs.size());
		Assert.assertEquals(newStMSupplementalDiscProj,
			stMSupplementalDiscProjs.get(
				newStMSupplementalDiscProj.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = StMSupplementalDiscProjLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<StMSupplementalDiscProj>() {
				@Override
				public void performAction(
					StMSupplementalDiscProj stMSupplementalDiscProj) {
					Assert.assertNotNull(stMSupplementalDiscProj);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		StMSupplementalDiscProj newStMSupplementalDiscProj = addStMSupplementalDiscProj();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StMSupplementalDiscProj.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				newStMSupplementalDiscProj.getUserId()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				newStMSupplementalDiscProj.getProjectionDetailsSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				newStMSupplementalDiscProj.getSessionId()));

		List<StMSupplementalDiscProj> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		StMSupplementalDiscProj existingStMSupplementalDiscProj = result.get(0);

		Assert.assertEquals(existingStMSupplementalDiscProj,
			newStMSupplementalDiscProj);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StMSupplementalDiscProj.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				RandomTestUtil.nextInt()));

		List<StMSupplementalDiscProj> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		StMSupplementalDiscProj newStMSupplementalDiscProj = addStMSupplementalDiscProj();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StMSupplementalDiscProj.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("id.userId"));

		Object newUserId = newStMSupplementalDiscProj.getUserId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.userId",
				new Object[] { newUserId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingUserId = result.get(0);

		Assert.assertEquals(existingUserId, newUserId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StMSupplementalDiscProj.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("id.userId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.userId",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected StMSupplementalDiscProj addStMSupplementalDiscProj()
		throws Exception {
		StMSupplementalDiscProjPK pk = new StMSupplementalDiscProjPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StMSupplementalDiscProj stMSupplementalDiscProj = _persistence.create(pk);

		stMSupplementalDiscProj.setProjectionRate(RandomTestUtil.nextDouble());

		stMSupplementalDiscProj.setLastModifiedDate(RandomTestUtil.nextDate());

		stMSupplementalDiscProj.setParityReference(RandomTestUtil.randomString());

		stMSupplementalDiscProj.setProjectionSales(RandomTestUtil.nextDouble());

		stMSupplementalDiscProj.setContractPrice(RandomTestUtil.nextDouble());

		stMSupplementalDiscProj.setMethodology(RandomTestUtil.randomString());

		stMSupplementalDiscProj.setParity(RandomTestUtil.randomBoolean());

		stMSupplementalDiscProj.setPeriodSid(RandomTestUtil.nextInt());

		stMSupplementalDiscProj.setDiscountRate1(RandomTestUtil.nextDouble());

		stMSupplementalDiscProj.setDiscountRate2(RandomTestUtil.nextDouble());

		stMSupplementalDiscProj.setParityDiscount(RandomTestUtil.nextDouble());

		stMSupplementalDiscProj.setAccess(RandomTestUtil.randomString());

		_stMSupplementalDiscProjs.add(_persistence.update(
				stMSupplementalDiscProj));

		return stMSupplementalDiscProj;
	}

	private List<StMSupplementalDiscProj> _stMSupplementalDiscProjs = new ArrayList<StMSupplementalDiscProj>();
	private StMSupplementalDiscProjPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}