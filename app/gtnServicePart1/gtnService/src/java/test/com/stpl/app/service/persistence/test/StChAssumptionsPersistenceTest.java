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

import com.stpl.app.exception.NoSuchStChAssumptionsException;
import com.stpl.app.model.StChAssumptions;
import com.stpl.app.service.StChAssumptionsLocalServiceUtil;
import com.stpl.app.service.persistence.StChAssumptionsPK;
import com.stpl.app.service.persistence.StChAssumptionsPersistence;
import com.stpl.app.service.persistence.StChAssumptionsUtil;

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
public class StChAssumptionsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = StChAssumptionsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<StChAssumptions> iterator = _stChAssumptionses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		StChAssumptionsPK pk = new StChAssumptionsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StChAssumptions stChAssumptions = _persistence.create(pk);

		Assert.assertNotNull(stChAssumptions);

		Assert.assertEquals(stChAssumptions.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		StChAssumptions newStChAssumptions = addStChAssumptions();

		_persistence.remove(newStChAssumptions);

		StChAssumptions existingStChAssumptions = _persistence.fetchByPrimaryKey(newStChAssumptions.getPrimaryKey());

		Assert.assertNull(existingStChAssumptions);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addStChAssumptions();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		StChAssumptionsPK pk = new StChAssumptionsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StChAssumptions newStChAssumptions = _persistence.create(pk);

		newStChAssumptions.setLastModifiedDate(RandomTestUtil.nextDate());

		newStChAssumptions.setParent(RandomTestUtil.randomBoolean());

		newStChAssumptions.setCommentary(RandomTestUtil.randomString());

		newStChAssumptions.setTotalDiscountPercentChange(RandomTestUtil.nextDouble());

		newStChAssumptions.setReasonCodes(RandomTestUtil.randomString());

		newStChAssumptions.setTotalDiscountPercentProjected(RandomTestUtil.nextDouble());

		newStChAssumptions.setTotalDiscountPercentPrior(RandomTestUtil.nextDouble());

		newStChAssumptions.setTotalDiscountChange(RandomTestUtil.nextDouble());

		newStChAssumptions.setTotalDiscountProjected(RandomTestUtil.nextDouble());

		newStChAssumptions.setIsChecked(RandomTestUtil.randomBoolean());

		newStChAssumptions.setCamId(RandomTestUtil.nextInt());

		newStChAssumptions.setGrossTradeSales(RandomTestUtil.nextDouble());

		newStChAssumptions.setTotalDiscountPrior(RandomTestUtil.nextDouble());

		_stChAssumptionses.add(_persistence.update(newStChAssumptions));

		StChAssumptions existingStChAssumptions = _persistence.findByPrimaryKey(newStChAssumptions.getPrimaryKey());

		Assert.assertEquals(Time.getShortTimestamp(
				existingStChAssumptions.getLastModifiedDate()),
			Time.getShortTimestamp(newStChAssumptions.getLastModifiedDate()));
		Assert.assertEquals(existingStChAssumptions.getParent(),
			newStChAssumptions.getParent());
		Assert.assertEquals(existingStChAssumptions.getCommentary(),
			newStChAssumptions.getCommentary());
		Assert.assertEquals(existingStChAssumptions.getProjectionDetailsSid(),
			newStChAssumptions.getProjectionDetailsSid());
		Assert.assertEquals(existingStChAssumptions.getUserId(),
			newStChAssumptions.getUserId());
		Assert.assertEquals(existingStChAssumptions.getQuarter(),
			newStChAssumptions.getQuarter());
		AssertUtils.assertEquals(existingStChAssumptions.getTotalDiscountPercentChange(),
			newStChAssumptions.getTotalDiscountPercentChange());
		Assert.assertEquals(existingStChAssumptions.getReasonCodes(),
			newStChAssumptions.getReasonCodes());
		Assert.assertEquals(existingStChAssumptions.getYear(),
			newStChAssumptions.getYear());
		AssertUtils.assertEquals(existingStChAssumptions.getTotalDiscountPercentProjected(),
			newStChAssumptions.getTotalDiscountPercentProjected());
		AssertUtils.assertEquals(existingStChAssumptions.getTotalDiscountPercentPrior(),
			newStChAssumptions.getTotalDiscountPercentPrior());
		Assert.assertEquals(existingStChAssumptions.getStChAssumptionsSid(),
			newStChAssumptions.getStChAssumptionsSid());
		Assert.assertEquals(existingStChAssumptions.getChAssumptionsSid(),
			newStChAssumptions.getChAssumptionsSid());
		AssertUtils.assertEquals(existingStChAssumptions.getTotalDiscountChange(),
			newStChAssumptions.getTotalDiscountChange());
		Assert.assertEquals(existingStChAssumptions.getSessionId(),
			newStChAssumptions.getSessionId());
		AssertUtils.assertEquals(existingStChAssumptions.getTotalDiscountProjected(),
			newStChAssumptions.getTotalDiscountProjected());
		Assert.assertEquals(existingStChAssumptions.getIsChecked(),
			newStChAssumptions.getIsChecked());
		Assert.assertEquals(existingStChAssumptions.getCamId(),
			newStChAssumptions.getCamId());
		AssertUtils.assertEquals(existingStChAssumptions.getGrossTradeSales(),
			newStChAssumptions.getGrossTradeSales());
		AssertUtils.assertEquals(existingStChAssumptions.getTotalDiscountPrior(),
			newStChAssumptions.getTotalDiscountPrior());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		StChAssumptions newStChAssumptions = addStChAssumptions();

		StChAssumptions existingStChAssumptions = _persistence.findByPrimaryKey(newStChAssumptions.getPrimaryKey());

		Assert.assertEquals(existingStChAssumptions, newStChAssumptions);
	}

	@Test(expected = NoSuchStChAssumptionsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		StChAssumptionsPK pk = new StChAssumptionsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		StChAssumptions newStChAssumptions = addStChAssumptions();

		StChAssumptions existingStChAssumptions = _persistence.fetchByPrimaryKey(newStChAssumptions.getPrimaryKey());

		Assert.assertEquals(existingStChAssumptions, newStChAssumptions);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		StChAssumptionsPK pk = new StChAssumptionsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StChAssumptions missingStChAssumptions = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingStChAssumptions);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		StChAssumptions newStChAssumptions1 = addStChAssumptions();
		StChAssumptions newStChAssumptions2 = addStChAssumptions();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStChAssumptions1.getPrimaryKey());
		primaryKeys.add(newStChAssumptions2.getPrimaryKey());

		Map<Serializable, StChAssumptions> stChAssumptionses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, stChAssumptionses.size());
		Assert.assertEquals(newStChAssumptions1,
			stChAssumptionses.get(newStChAssumptions1.getPrimaryKey()));
		Assert.assertEquals(newStChAssumptions2,
			stChAssumptionses.get(newStChAssumptions2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		StChAssumptionsPK pk1 = new StChAssumptionsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StChAssumptionsPK pk2 = new StChAssumptionsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, StChAssumptions> stChAssumptionses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stChAssumptionses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		StChAssumptions newStChAssumptions = addStChAssumptions();

		StChAssumptionsPK pk = new StChAssumptionsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStChAssumptions.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, StChAssumptions> stChAssumptionses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stChAssumptionses.size());
		Assert.assertEquals(newStChAssumptions,
			stChAssumptionses.get(newStChAssumptions.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, StChAssumptions> stChAssumptionses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stChAssumptionses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		StChAssumptions newStChAssumptions = addStChAssumptions();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStChAssumptions.getPrimaryKey());

		Map<Serializable, StChAssumptions> stChAssumptionses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stChAssumptionses.size());
		Assert.assertEquals(newStChAssumptions,
			stChAssumptionses.get(newStChAssumptions.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = StChAssumptionsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<StChAssumptions>() {
				@Override
				public void performAction(StChAssumptions stChAssumptions) {
					Assert.assertNotNull(stChAssumptions);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		StChAssumptions newStChAssumptions = addStChAssumptions();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StChAssumptions.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				newStChAssumptions.getProjectionDetailsSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				newStChAssumptions.getUserId()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.quarter",
				newStChAssumptions.getQuarter()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.year",
				newStChAssumptions.getYear()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.stChAssumptionsSid",
				newStChAssumptions.getStChAssumptionsSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.chAssumptionsSid",
				newStChAssumptions.getChAssumptionsSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				newStChAssumptions.getSessionId()));

		List<StChAssumptions> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		StChAssumptions existingStChAssumptions = result.get(0);

		Assert.assertEquals(existingStChAssumptions, newStChAssumptions);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StChAssumptions.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.quarter",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.year",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.stChAssumptionsSid",
				RandomTestUtil.randomString()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.chAssumptionsSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				RandomTestUtil.nextInt()));

		List<StChAssumptions> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		StChAssumptions newStChAssumptions = addStChAssumptions();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StChAssumptions.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.projectionDetailsSid"));

		Object newProjectionDetailsSid = newStChAssumptions.getProjectionDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.projectionDetailsSid",
				new Object[] { newProjectionDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingProjectionDetailsSid = result.get(0);

		Assert.assertEquals(existingProjectionDetailsSid,
			newProjectionDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StChAssumptions.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.projectionDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.projectionDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected StChAssumptions addStChAssumptions() throws Exception {
		StChAssumptionsPK pk = new StChAssumptionsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StChAssumptions stChAssumptions = _persistence.create(pk);

		stChAssumptions.setLastModifiedDate(RandomTestUtil.nextDate());

		stChAssumptions.setParent(RandomTestUtil.randomBoolean());

		stChAssumptions.setCommentary(RandomTestUtil.randomString());

		stChAssumptions.setTotalDiscountPercentChange(RandomTestUtil.nextDouble());

		stChAssumptions.setReasonCodes(RandomTestUtil.randomString());

		stChAssumptions.setTotalDiscountPercentProjected(RandomTestUtil.nextDouble());

		stChAssumptions.setTotalDiscountPercentPrior(RandomTestUtil.nextDouble());

		stChAssumptions.setTotalDiscountChange(RandomTestUtil.nextDouble());

		stChAssumptions.setTotalDiscountProjected(RandomTestUtil.nextDouble());

		stChAssumptions.setIsChecked(RandomTestUtil.randomBoolean());

		stChAssumptions.setCamId(RandomTestUtil.nextInt());

		stChAssumptions.setGrossTradeSales(RandomTestUtil.nextDouble());

		stChAssumptions.setTotalDiscountPrior(RandomTestUtil.nextDouble());

		_stChAssumptionses.add(_persistence.update(stChAssumptions));

		return stChAssumptions;
	}

	private List<StChAssumptions> _stChAssumptionses = new ArrayList<StChAssumptions>();
	private StChAssumptionsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}