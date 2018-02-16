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

import com.stpl.app.exception.NoSuchStMAssumptionsException;
import com.stpl.app.model.StMAssumptions;
import com.stpl.app.service.StMAssumptionsLocalServiceUtil;
import com.stpl.app.service.persistence.StMAssumptionsPK;
import com.stpl.app.service.persistence.StMAssumptionsPersistence;
import com.stpl.app.service.persistence.StMAssumptionsUtil;

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
public class StMAssumptionsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = StMAssumptionsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<StMAssumptions> iterator = _stMAssumptionses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		StMAssumptionsPK pk = new StMAssumptionsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StMAssumptions stMAssumptions = _persistence.create(pk);

		Assert.assertNotNull(stMAssumptions);

		Assert.assertEquals(stMAssumptions.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		StMAssumptions newStMAssumptions = addStMAssumptions();

		_persistence.remove(newStMAssumptions);

		StMAssumptions existingStMAssumptions = _persistence.fetchByPrimaryKey(newStMAssumptions.getPrimaryKey());

		Assert.assertNull(existingStMAssumptions);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addStMAssumptions();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		StMAssumptionsPK pk = new StMAssumptionsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StMAssumptions newStMAssumptions = _persistence.create(pk);

		newStMAssumptions.setGrossSalesPercentChange(RandomTestUtil.nextDouble());

		newStMAssumptions.setGrossSalesPrior(RandomTestUtil.nextDouble());

		newStMAssumptions.setTotalDiscountPercentProjected(RandomTestUtil.nextDouble());

		newStMAssumptions.setCamId(RandomTestUtil.nextInt());

		newStMAssumptions.setCommentary(RandomTestUtil.randomString());

		newStMAssumptions.setIsChecked(RandomTestUtil.randomBoolean());

		newStMAssumptions.setLastModifiedDate(RandomTestUtil.nextDate());

		newStMAssumptions.setGrossSalesProjected(RandomTestUtil.nextDouble());

		newStMAssumptions.setTotalDiscountPercentChange(RandomTestUtil.nextDouble());

		newStMAssumptions.setTotalDiscountPercentPrior(RandomTestUtil.nextDouble());

		newStMAssumptions.setNetSalesPercentChange(RandomTestUtil.nextDouble());

		newStMAssumptions.setParent(RandomTestUtil.randomBoolean());

		newStMAssumptions.setNetSalesPrior(RandomTestUtil.nextDouble());

		newStMAssumptions.setNetSalesProjected(RandomTestUtil.nextDouble());

		newStMAssumptions.setReasonCodes(RandomTestUtil.randomString());

		_stMAssumptionses.add(_persistence.update(newStMAssumptions));

		StMAssumptions existingStMAssumptions = _persistence.findByPrimaryKey(newStMAssumptions.getPrimaryKey());

		AssertUtils.assertEquals(existingStMAssumptions.getGrossSalesPercentChange(),
			newStMAssumptions.getGrossSalesPercentChange());
		AssertUtils.assertEquals(existingStMAssumptions.getGrossSalesPrior(),
			newStMAssumptions.getGrossSalesPrior());
		Assert.assertEquals(existingStMAssumptions.getProjYear(),
			newStMAssumptions.getProjYear());
		AssertUtils.assertEquals(existingStMAssumptions.getTotalDiscountPercentProjected(),
			newStMAssumptions.getTotalDiscountPercentProjected());
		Assert.assertEquals(existingStMAssumptions.getCamId(),
			newStMAssumptions.getCamId());
		Assert.assertEquals(existingStMAssumptions.getCommentary(),
			newStMAssumptions.getCommentary());
		Assert.assertEquals(existingStMAssumptions.getIsChecked(),
			newStMAssumptions.getIsChecked());
		Assert.assertEquals(existingStMAssumptions.getUserId(),
			newStMAssumptions.getUserId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingStMAssumptions.getLastModifiedDate()),
			Time.getShortTimestamp(newStMAssumptions.getLastModifiedDate()));
		AssertUtils.assertEquals(existingStMAssumptions.getGrossSalesProjected(),
			newStMAssumptions.getGrossSalesProjected());
		AssertUtils.assertEquals(existingStMAssumptions.getTotalDiscountPercentChange(),
			newStMAssumptions.getTotalDiscountPercentChange());
		AssertUtils.assertEquals(existingStMAssumptions.getTotalDiscountPercentPrior(),
			newStMAssumptions.getTotalDiscountPercentPrior());
		AssertUtils.assertEquals(existingStMAssumptions.getNetSalesPercentChange(),
			newStMAssumptions.getNetSalesPercentChange());
		Assert.assertEquals(existingStMAssumptions.getParent(),
			newStMAssumptions.getParent());
		Assert.assertEquals(existingStMAssumptions.getStMAssumptionsSid(),
			newStMAssumptions.getStMAssumptionsSid());
		Assert.assertEquals(existingStMAssumptions.getProjectionPeriod(),
			newStMAssumptions.getProjectionPeriod());
		Assert.assertEquals(existingStMAssumptions.getProjectionDetailsSid(),
			newStMAssumptions.getProjectionDetailsSid());
		AssertUtils.assertEquals(existingStMAssumptions.getNetSalesPrior(),
			newStMAssumptions.getNetSalesPrior());
		Assert.assertEquals(existingStMAssumptions.getSessionId(),
			newStMAssumptions.getSessionId());
		AssertUtils.assertEquals(existingStMAssumptions.getNetSalesProjected(),
			newStMAssumptions.getNetSalesProjected());
		Assert.assertEquals(existingStMAssumptions.getReasonCodes(),
			newStMAssumptions.getReasonCodes());
		Assert.assertEquals(existingStMAssumptions.getMAssumptionsSid(),
			newStMAssumptions.getMAssumptionsSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		StMAssumptions newStMAssumptions = addStMAssumptions();

		StMAssumptions existingStMAssumptions = _persistence.findByPrimaryKey(newStMAssumptions.getPrimaryKey());

		Assert.assertEquals(existingStMAssumptions, newStMAssumptions);
	}

	@Test(expected = NoSuchStMAssumptionsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		StMAssumptionsPK pk = new StMAssumptionsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		StMAssumptions newStMAssumptions = addStMAssumptions();

		StMAssumptions existingStMAssumptions = _persistence.fetchByPrimaryKey(newStMAssumptions.getPrimaryKey());

		Assert.assertEquals(existingStMAssumptions, newStMAssumptions);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		StMAssumptionsPK pk = new StMAssumptionsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StMAssumptions missingStMAssumptions = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingStMAssumptions);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		StMAssumptions newStMAssumptions1 = addStMAssumptions();
		StMAssumptions newStMAssumptions2 = addStMAssumptions();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStMAssumptions1.getPrimaryKey());
		primaryKeys.add(newStMAssumptions2.getPrimaryKey());

		Map<Serializable, StMAssumptions> stMAssumptionses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, stMAssumptionses.size());
		Assert.assertEquals(newStMAssumptions1,
			stMAssumptionses.get(newStMAssumptions1.getPrimaryKey()));
		Assert.assertEquals(newStMAssumptions2,
			stMAssumptionses.get(newStMAssumptions2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		StMAssumptionsPK pk1 = new StMAssumptionsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StMAssumptionsPK pk2 = new StMAssumptionsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, StMAssumptions> stMAssumptionses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stMAssumptionses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		StMAssumptions newStMAssumptions = addStMAssumptions();

		StMAssumptionsPK pk = new StMAssumptionsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStMAssumptions.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, StMAssumptions> stMAssumptionses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stMAssumptionses.size());
		Assert.assertEquals(newStMAssumptions,
			stMAssumptionses.get(newStMAssumptions.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, StMAssumptions> stMAssumptionses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stMAssumptionses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		StMAssumptions newStMAssumptions = addStMAssumptions();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStMAssumptions.getPrimaryKey());

		Map<Serializable, StMAssumptions> stMAssumptionses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stMAssumptionses.size());
		Assert.assertEquals(newStMAssumptions,
			stMAssumptionses.get(newStMAssumptions.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = StMAssumptionsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<StMAssumptions>() {
				@Override
				public void performAction(StMAssumptions stMAssumptions) {
					Assert.assertNotNull(stMAssumptions);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		StMAssumptions newStMAssumptions = addStMAssumptions();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StMAssumptions.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projYear",
				newStMAssumptions.getProjYear()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				newStMAssumptions.getUserId()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.stMAssumptionsSid",
				newStMAssumptions.getStMAssumptionsSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionPeriod",
				newStMAssumptions.getProjectionPeriod()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				newStMAssumptions.getProjectionDetailsSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				newStMAssumptions.getSessionId()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.mAssumptionsSid",
				newStMAssumptions.getMAssumptionsSid()));

		List<StMAssumptions> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		StMAssumptions existingStMAssumptions = result.get(0);

		Assert.assertEquals(existingStMAssumptions, newStMAssumptions);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StMAssumptions.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projYear",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.stMAssumptionsSid",
				RandomTestUtil.randomString()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionPeriod",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.mAssumptionsSid",
				RandomTestUtil.nextInt()));

		List<StMAssumptions> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		StMAssumptions newStMAssumptions = addStMAssumptions();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StMAssumptions.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("id.projYear"));

		Object newProjYear = newStMAssumptions.getProjYear();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.projYear",
				new Object[] { newProjYear }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingProjYear = result.get(0);

		Assert.assertEquals(existingProjYear, newProjYear);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StMAssumptions.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("id.projYear"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.projYear",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected StMAssumptions addStMAssumptions() throws Exception {
		StMAssumptionsPK pk = new StMAssumptionsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StMAssumptions stMAssumptions = _persistence.create(pk);

		stMAssumptions.setGrossSalesPercentChange(RandomTestUtil.nextDouble());

		stMAssumptions.setGrossSalesPrior(RandomTestUtil.nextDouble());

		stMAssumptions.setTotalDiscountPercentProjected(RandomTestUtil.nextDouble());

		stMAssumptions.setCamId(RandomTestUtil.nextInt());

		stMAssumptions.setCommentary(RandomTestUtil.randomString());

		stMAssumptions.setIsChecked(RandomTestUtil.randomBoolean());

		stMAssumptions.setLastModifiedDate(RandomTestUtil.nextDate());

		stMAssumptions.setGrossSalesProjected(RandomTestUtil.nextDouble());

		stMAssumptions.setTotalDiscountPercentChange(RandomTestUtil.nextDouble());

		stMAssumptions.setTotalDiscountPercentPrior(RandomTestUtil.nextDouble());

		stMAssumptions.setNetSalesPercentChange(RandomTestUtil.nextDouble());

		stMAssumptions.setParent(RandomTestUtil.randomBoolean());

		stMAssumptions.setNetSalesPrior(RandomTestUtil.nextDouble());

		stMAssumptions.setNetSalesProjected(RandomTestUtil.nextDouble());

		stMAssumptions.setReasonCodes(RandomTestUtil.randomString());

		_stMAssumptionses.add(_persistence.update(stMAssumptions));

		return stMAssumptions;
	}

	private List<StMAssumptions> _stMAssumptionses = new ArrayList<StMAssumptions>();
	private StMAssumptionsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}