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

import com.stpl.app.exception.NoSuchStNmAssumptionsException;
import com.stpl.app.model.StNmAssumptions;
import com.stpl.app.service.StNmAssumptionsLocalServiceUtil;
import com.stpl.app.service.persistence.StNmAssumptionsPK;
import com.stpl.app.service.persistence.StNmAssumptionsPersistence;
import com.stpl.app.service.persistence.StNmAssumptionsUtil;

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
public class StNmAssumptionsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = StNmAssumptionsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<StNmAssumptions> iterator = _stNmAssumptionses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		StNmAssumptionsPK pk = new StNmAssumptionsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt());

		StNmAssumptions stNmAssumptions = _persistence.create(pk);

		Assert.assertNotNull(stNmAssumptions);

		Assert.assertEquals(stNmAssumptions.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		StNmAssumptions newStNmAssumptions = addStNmAssumptions();

		_persistence.remove(newStNmAssumptions);

		StNmAssumptions existingStNmAssumptions = _persistence.fetchByPrimaryKey(newStNmAssumptions.getPrimaryKey());

		Assert.assertNull(existingStNmAssumptions);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addStNmAssumptions();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		StNmAssumptionsPK pk = new StNmAssumptionsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt());

		StNmAssumptions newStNmAssumptions = _persistence.create(pk);

		newStNmAssumptions.setLastModifiedDate(RandomTestUtil.nextDate());

		newStNmAssumptions.setParent(RandomTestUtil.randomBoolean());

		newStNmAssumptions.setCommentary(RandomTestUtil.randomString());

		newStNmAssumptions.setNetSalesPrior(RandomTestUtil.nextDouble());

		newStNmAssumptions.setGrossSalesPercentChange(RandomTestUtil.nextDouble());

		newStNmAssumptions.setTotalDiscountPercentChange(RandomTestUtil.nextDouble());

		newStNmAssumptions.setReasonCodes(RandomTestUtil.randomString());

		newStNmAssumptions.setTotalDiscountPercentProjected(RandomTestUtil.nextDouble());

		newStNmAssumptions.setTotalDiscountPercentPrior(RandomTestUtil.nextDouble());

		newStNmAssumptions.setNetSalesProjected(RandomTestUtil.nextDouble());

		newStNmAssumptions.setGrossSalesProjected(RandomTestUtil.nextDouble());

		newStNmAssumptions.setGrossSalesPrior(RandomTestUtil.nextDouble());

		newStNmAssumptions.setIsChecked(RandomTestUtil.randomBoolean());

		newStNmAssumptions.setCamId(RandomTestUtil.nextInt());

		newStNmAssumptions.setNetSalesPercentChange(RandomTestUtil.nextDouble());

		newStNmAssumptions.setSegment(RandomTestUtil.randomString());

		_stNmAssumptionses.add(_persistence.update(newStNmAssumptions));

		StNmAssumptions existingStNmAssumptions = _persistence.findByPrimaryKey(newStNmAssumptions.getPrimaryKey());

		Assert.assertEquals(Time.getShortTimestamp(
				existingStNmAssumptions.getLastModifiedDate()),
			Time.getShortTimestamp(newStNmAssumptions.getLastModifiedDate()));
		Assert.assertEquals(existingStNmAssumptions.getParent(),
			newStNmAssumptions.getParent());
		Assert.assertEquals(existingStNmAssumptions.getProjectionPeriod(),
			newStNmAssumptions.getProjectionPeriod());
		Assert.assertEquals(existingStNmAssumptions.getCommentary(),
			newStNmAssumptions.getCommentary());
		Assert.assertEquals(existingStNmAssumptions.getNmAssumptionsSid(),
			newStNmAssumptions.getNmAssumptionsSid());
		Assert.assertEquals(existingStNmAssumptions.getProjectionDetailsSid(),
			newStNmAssumptions.getProjectionDetailsSid());
		AssertUtils.assertEquals(existingStNmAssumptions.getNetSalesPrior(),
			newStNmAssumptions.getNetSalesPrior());
		Assert.assertEquals(existingStNmAssumptions.getUserId(),
			newStNmAssumptions.getUserId());
		AssertUtils.assertEquals(existingStNmAssumptions.getGrossSalesPercentChange(),
			newStNmAssumptions.getGrossSalesPercentChange());
		AssertUtils.assertEquals(existingStNmAssumptions.getTotalDiscountPercentChange(),
			newStNmAssumptions.getTotalDiscountPercentChange());
		Assert.assertEquals(existingStNmAssumptions.getReasonCodes(),
			newStNmAssumptions.getReasonCodes());
		AssertUtils.assertEquals(existingStNmAssumptions.getTotalDiscountPercentProjected(),
			newStNmAssumptions.getTotalDiscountPercentProjected());
		AssertUtils.assertEquals(existingStNmAssumptions.getTotalDiscountPercentPrior(),
			newStNmAssumptions.getTotalDiscountPercentPrior());
		AssertUtils.assertEquals(existingStNmAssumptions.getNetSalesProjected(),
			newStNmAssumptions.getNetSalesProjected());
		Assert.assertEquals(existingStNmAssumptions.getStNmAssumptionsSid(),
			newStNmAssumptions.getStNmAssumptionsSid());
		AssertUtils.assertEquals(existingStNmAssumptions.getGrossSalesProjected(),
			newStNmAssumptions.getGrossSalesProjected());
		Assert.assertEquals(existingStNmAssumptions.getSessionId(),
			newStNmAssumptions.getSessionId());
		AssertUtils.assertEquals(existingStNmAssumptions.getGrossSalesPrior(),
			newStNmAssumptions.getGrossSalesPrior());
		Assert.assertEquals(existingStNmAssumptions.getIsChecked(),
			newStNmAssumptions.getIsChecked());
		Assert.assertEquals(existingStNmAssumptions.getCamId(),
			newStNmAssumptions.getCamId());
		AssertUtils.assertEquals(existingStNmAssumptions.getNetSalesPercentChange(),
			newStNmAssumptions.getNetSalesPercentChange());
		Assert.assertEquals(existingStNmAssumptions.getSegment(),
			newStNmAssumptions.getSegment());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		StNmAssumptions newStNmAssumptions = addStNmAssumptions();

		StNmAssumptions existingStNmAssumptions = _persistence.findByPrimaryKey(newStNmAssumptions.getPrimaryKey());

		Assert.assertEquals(existingStNmAssumptions, newStNmAssumptions);
	}

	@Test(expected = NoSuchStNmAssumptionsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		StNmAssumptionsPK pk = new StNmAssumptionsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		StNmAssumptions newStNmAssumptions = addStNmAssumptions();

		StNmAssumptions existingStNmAssumptions = _persistence.fetchByPrimaryKey(newStNmAssumptions.getPrimaryKey());

		Assert.assertEquals(existingStNmAssumptions, newStNmAssumptions);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		StNmAssumptionsPK pk = new StNmAssumptionsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt());

		StNmAssumptions missingStNmAssumptions = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingStNmAssumptions);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		StNmAssumptions newStNmAssumptions1 = addStNmAssumptions();
		StNmAssumptions newStNmAssumptions2 = addStNmAssumptions();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStNmAssumptions1.getPrimaryKey());
		primaryKeys.add(newStNmAssumptions2.getPrimaryKey());

		Map<Serializable, StNmAssumptions> stNmAssumptionses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, stNmAssumptionses.size());
		Assert.assertEquals(newStNmAssumptions1,
			stNmAssumptionses.get(newStNmAssumptions1.getPrimaryKey()));
		Assert.assertEquals(newStNmAssumptions2,
			stNmAssumptionses.get(newStNmAssumptions2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		StNmAssumptionsPK pk1 = new StNmAssumptionsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt());

		StNmAssumptionsPK pk2 = new StNmAssumptionsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, StNmAssumptions> stNmAssumptionses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stNmAssumptionses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		StNmAssumptions newStNmAssumptions = addStNmAssumptions();

		StNmAssumptionsPK pk = new StNmAssumptionsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStNmAssumptions.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, StNmAssumptions> stNmAssumptionses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stNmAssumptionses.size());
		Assert.assertEquals(newStNmAssumptions,
			stNmAssumptionses.get(newStNmAssumptions.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, StNmAssumptions> stNmAssumptionses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stNmAssumptionses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		StNmAssumptions newStNmAssumptions = addStNmAssumptions();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStNmAssumptions.getPrimaryKey());

		Map<Serializable, StNmAssumptions> stNmAssumptionses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stNmAssumptionses.size());
		Assert.assertEquals(newStNmAssumptions,
			stNmAssumptionses.get(newStNmAssumptions.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = StNmAssumptionsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<StNmAssumptions>() {
				@Override
				public void performAction(StNmAssumptions stNmAssumptions) {
					Assert.assertNotNull(stNmAssumptions);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		StNmAssumptions newStNmAssumptions = addStNmAssumptions();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StNmAssumptions.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionPeriod",
				newStNmAssumptions.getProjectionPeriod()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.nmAssumptionsSid",
				newStNmAssumptions.getNmAssumptionsSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				newStNmAssumptions.getProjectionDetailsSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				newStNmAssumptions.getUserId()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.stNmAssumptionsSid",
				newStNmAssumptions.getStNmAssumptionsSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				newStNmAssumptions.getSessionId()));

		List<StNmAssumptions> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		StNmAssumptions existingStNmAssumptions = result.get(0);

		Assert.assertEquals(existingStNmAssumptions, newStNmAssumptions);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StNmAssumptions.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionPeriod",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.nmAssumptionsSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.stNmAssumptionsSid",
				RandomTestUtil.randomString()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				RandomTestUtil.nextInt()));

		List<StNmAssumptions> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		StNmAssumptions newStNmAssumptions = addStNmAssumptions();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StNmAssumptions.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.projectionPeriod"));

		Object newProjectionPeriod = newStNmAssumptions.getProjectionPeriod();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.projectionPeriod",
				new Object[] { newProjectionPeriod }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingProjectionPeriod = result.get(0);

		Assert.assertEquals(existingProjectionPeriod, newProjectionPeriod);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StNmAssumptions.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.projectionPeriod"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.projectionPeriod",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected StNmAssumptions addStNmAssumptions() throws Exception {
		StNmAssumptionsPK pk = new StNmAssumptionsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt());

		StNmAssumptions stNmAssumptions = _persistence.create(pk);

		stNmAssumptions.setLastModifiedDate(RandomTestUtil.nextDate());

		stNmAssumptions.setParent(RandomTestUtil.randomBoolean());

		stNmAssumptions.setCommentary(RandomTestUtil.randomString());

		stNmAssumptions.setNetSalesPrior(RandomTestUtil.nextDouble());

		stNmAssumptions.setGrossSalesPercentChange(RandomTestUtil.nextDouble());

		stNmAssumptions.setTotalDiscountPercentChange(RandomTestUtil.nextDouble());

		stNmAssumptions.setReasonCodes(RandomTestUtil.randomString());

		stNmAssumptions.setTotalDiscountPercentProjected(RandomTestUtil.nextDouble());

		stNmAssumptions.setTotalDiscountPercentPrior(RandomTestUtil.nextDouble());

		stNmAssumptions.setNetSalesProjected(RandomTestUtil.nextDouble());

		stNmAssumptions.setGrossSalesProjected(RandomTestUtil.nextDouble());

		stNmAssumptions.setGrossSalesPrior(RandomTestUtil.nextDouble());

		stNmAssumptions.setIsChecked(RandomTestUtil.randomBoolean());

		stNmAssumptions.setCamId(RandomTestUtil.nextInt());

		stNmAssumptions.setNetSalesPercentChange(RandomTestUtil.nextDouble());

		stNmAssumptions.setSegment(RandomTestUtil.randomString());

		_stNmAssumptionses.add(_persistence.update(stNmAssumptions));

		return stNmAssumptions;
	}

	private List<StNmAssumptions> _stNmAssumptionses = new ArrayList<StNmAssumptions>();
	private StNmAssumptionsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}