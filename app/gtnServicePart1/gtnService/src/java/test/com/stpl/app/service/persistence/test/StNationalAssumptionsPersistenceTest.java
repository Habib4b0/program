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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.AssertUtils;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchStNationalAssumptionsException;
import com.stpl.app.model.StNationalAssumptions;
import com.stpl.app.service.persistence.StNationalAssumptionsPK;
import com.stpl.app.service.persistence.StNationalAssumptionsPersistence;
import com.stpl.app.service.persistence.StNationalAssumptionsUtil;

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
public class StNationalAssumptionsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = StNationalAssumptionsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<StNationalAssumptions> iterator = _stNationalAssumptionses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		StNationalAssumptionsPK pk = new StNationalAssumptionsPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt());

		StNationalAssumptions stNationalAssumptions = _persistence.create(pk);

		Assert.assertNotNull(stNationalAssumptions);

		Assert.assertEquals(stNationalAssumptions.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		StNationalAssumptions newStNationalAssumptions = addStNationalAssumptions();

		_persistence.remove(newStNationalAssumptions);

		StNationalAssumptions existingStNationalAssumptions = _persistence.fetchByPrimaryKey(newStNationalAssumptions.getPrimaryKey());

		Assert.assertNull(existingStNationalAssumptions);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addStNationalAssumptions();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		StNationalAssumptionsPK pk = new StNationalAssumptionsPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt());

		StNationalAssumptions newStNationalAssumptions = _persistence.create(pk);

		newStNationalAssumptions.setLastModifiedDate(RandomTestUtil.nextDate());

		newStNationalAssumptions.setBaselinePeriod(RandomTestUtil.randomString());

		newStNationalAssumptions.setFrequency(RandomTestUtil.randomString());

		newStNationalAssumptions.setRollingPeriod(RandomTestUtil.randomString());

		newStNationalAssumptions.setForecastMethodology(RandomTestUtil.randomString());

		newStNationalAssumptions.setPriceBasis(RandomTestUtil.randomString());

		newStNationalAssumptions.setBaselineMethodology(RandomTestUtil.randomString());

		newStNationalAssumptions.setGrowthRate(RandomTestUtil.nextDouble());

		_stNationalAssumptionses.add(_persistence.update(
				newStNationalAssumptions));

		StNationalAssumptions existingStNationalAssumptions = _persistence.findByPrimaryKey(newStNationalAssumptions.getPrimaryKey());

		Assert.assertEquals(Time.getShortTimestamp(
				existingStNationalAssumptions.getLastModifiedDate()),
			Time.getShortTimestamp(
				newStNationalAssumptions.getLastModifiedDate()));
		Assert.assertEquals(existingStNationalAssumptions.getBaselinePeriod(),
			newStNationalAssumptions.getBaselinePeriod());
		Assert.assertEquals(existingStNationalAssumptions.getStartPeriod(),
			newStNationalAssumptions.getStartPeriod());
		Assert.assertEquals(existingStNationalAssumptions.getFrequency(),
			newStNationalAssumptions.getFrequency());
		Assert.assertEquals(existingStNationalAssumptions.getUserId(),
			newStNationalAssumptions.getUserId());
		Assert.assertEquals(existingStNationalAssumptions.getEndPeriod(),
			newStNationalAssumptions.getEndPeriod());
		Assert.assertEquals(existingStNationalAssumptions.getNaProjMasterSid(),
			newStNationalAssumptions.getNaProjMasterSid());
		Assert.assertEquals(existingStNationalAssumptions.getRollingPeriod(),
			newStNationalAssumptions.getRollingPeriod());
		Assert.assertEquals(existingStNationalAssumptions.getForecastMethodology(),
			newStNationalAssumptions.getForecastMethodology());
		Assert.assertEquals(existingStNationalAssumptions.getPriceType(),
			newStNationalAssumptions.getPriceType());
		Assert.assertEquals(existingStNationalAssumptions.getPriceBasis(),
			newStNationalAssumptions.getPriceBasis());
		Assert.assertEquals(existingStNationalAssumptions.getSessionId(),
			newStNationalAssumptions.getSessionId());
		Assert.assertEquals(existingStNationalAssumptions.getBaselineMethodology(),
			newStNationalAssumptions.getBaselineMethodology());
		AssertUtils.assertEquals(existingStNationalAssumptions.getGrowthRate(),
			newStNationalAssumptions.getGrowthRate());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		StNationalAssumptions newStNationalAssumptions = addStNationalAssumptions();

		StNationalAssumptions existingStNationalAssumptions = _persistence.findByPrimaryKey(newStNationalAssumptions.getPrimaryKey());

		Assert.assertEquals(existingStNationalAssumptions,
			newStNationalAssumptions);
	}

	@Test(expected = NoSuchStNationalAssumptionsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		StNationalAssumptionsPK pk = new StNationalAssumptionsPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		StNationalAssumptions newStNationalAssumptions = addStNationalAssumptions();

		StNationalAssumptions existingStNationalAssumptions = _persistence.fetchByPrimaryKey(newStNationalAssumptions.getPrimaryKey());

		Assert.assertEquals(existingStNationalAssumptions,
			newStNationalAssumptions);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		StNationalAssumptionsPK pk = new StNationalAssumptionsPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt());

		StNationalAssumptions missingStNationalAssumptions = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingStNationalAssumptions);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		StNationalAssumptions newStNationalAssumptions1 = addStNationalAssumptions();
		StNationalAssumptions newStNationalAssumptions2 = addStNationalAssumptions();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStNationalAssumptions1.getPrimaryKey());
		primaryKeys.add(newStNationalAssumptions2.getPrimaryKey());

		Map<Serializable, StNationalAssumptions> stNationalAssumptionses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, stNationalAssumptionses.size());
		Assert.assertEquals(newStNationalAssumptions1,
			stNationalAssumptionses.get(
				newStNationalAssumptions1.getPrimaryKey()));
		Assert.assertEquals(newStNationalAssumptions2,
			stNationalAssumptionses.get(
				newStNationalAssumptions2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		StNationalAssumptionsPK pk1 = new StNationalAssumptionsPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt());

		StNationalAssumptionsPK pk2 = new StNationalAssumptionsPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, StNationalAssumptions> stNationalAssumptionses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stNationalAssumptionses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		StNationalAssumptions newStNationalAssumptions = addStNationalAssumptions();

		StNationalAssumptionsPK pk = new StNationalAssumptionsPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStNationalAssumptions.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, StNationalAssumptions> stNationalAssumptionses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stNationalAssumptionses.size());
		Assert.assertEquals(newStNationalAssumptions,
			stNationalAssumptionses.get(
				newStNationalAssumptions.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, StNationalAssumptions> stNationalAssumptionses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stNationalAssumptionses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		StNationalAssumptions newStNationalAssumptions = addStNationalAssumptions();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStNationalAssumptions.getPrimaryKey());

		Map<Serializable, StNationalAssumptions> stNationalAssumptionses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stNationalAssumptionses.size());
		Assert.assertEquals(newStNationalAssumptions,
			stNationalAssumptionses.get(
				newStNationalAssumptions.getPrimaryKey()));
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		StNationalAssumptions newStNationalAssumptions = addStNationalAssumptions();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StNationalAssumptions.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.startPeriod",
				newStNationalAssumptions.getStartPeriod()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				newStNationalAssumptions.getUserId()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.endPeriod",
				newStNationalAssumptions.getEndPeriod()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.naProjMasterSid",
				newStNationalAssumptions.getNaProjMasterSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.priceType",
				newStNationalAssumptions.getPriceType()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				newStNationalAssumptions.getSessionId()));

		List<StNationalAssumptions> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		StNationalAssumptions existingStNationalAssumptions = result.get(0);

		Assert.assertEquals(existingStNationalAssumptions,
			newStNationalAssumptions);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StNationalAssumptions.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.startPeriod",
				RandomTestUtil.randomString()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.endPeriod",
				RandomTestUtil.randomString()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.naProjMasterSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.priceType",
				RandomTestUtil.randomString()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				RandomTestUtil.nextInt()));

		List<StNationalAssumptions> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		StNationalAssumptions newStNationalAssumptions = addStNationalAssumptions();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StNationalAssumptions.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.startPeriod"));

		Object newStartPeriod = newStNationalAssumptions.getStartPeriod();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.startPeriod",
				new Object[] { newStartPeriod }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingStartPeriod = result.get(0);

		Assert.assertEquals(existingStartPeriod, newStartPeriod);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StNationalAssumptions.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.startPeriod"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.startPeriod",
				new Object[] { RandomTestUtil.randomString() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected StNationalAssumptions addStNationalAssumptions()
		throws Exception {
		StNationalAssumptionsPK pk = new StNationalAssumptionsPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt());

		StNationalAssumptions stNationalAssumptions = _persistence.create(pk);

		stNationalAssumptions.setLastModifiedDate(RandomTestUtil.nextDate());

		stNationalAssumptions.setBaselinePeriod(RandomTestUtil.randomString());

		stNationalAssumptions.setFrequency(RandomTestUtil.randomString());

		stNationalAssumptions.setRollingPeriod(RandomTestUtil.randomString());

		stNationalAssumptions.setForecastMethodology(RandomTestUtil.randomString());

		stNationalAssumptions.setPriceBasis(RandomTestUtil.randomString());

		stNationalAssumptions.setBaselineMethodology(RandomTestUtil.randomString());

		stNationalAssumptions.setGrowthRate(RandomTestUtil.nextDouble());

		_stNationalAssumptionses.add(_persistence.update(stNationalAssumptions));

		return stNationalAssumptions;
	}

	private List<StNationalAssumptions> _stNationalAssumptionses = new ArrayList<StNationalAssumptions>();
	private StNationalAssumptionsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}