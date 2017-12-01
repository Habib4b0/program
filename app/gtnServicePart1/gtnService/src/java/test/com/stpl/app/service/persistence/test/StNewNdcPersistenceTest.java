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

import com.stpl.app.exception.NoSuchStNewNdcException;
import com.stpl.app.model.StNewNdc;
import com.stpl.app.service.StNewNdcLocalServiceUtil;
import com.stpl.app.service.persistence.StNewNdcPK;
import com.stpl.app.service.persistence.StNewNdcPersistence;
import com.stpl.app.service.persistence.StNewNdcUtil;

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
public class StNewNdcPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = StNewNdcUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<StNewNdc> iterator = _stNewNdcs.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		StNewNdcPK pk = new StNewNdcPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		StNewNdc stNewNdc = _persistence.create(pk);

		Assert.assertNotNull(stNewNdc);

		Assert.assertEquals(stNewNdc.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		StNewNdc newStNewNdc = addStNewNdc();

		_persistence.remove(newStNewNdc);

		StNewNdc existingStNewNdc = _persistence.fetchByPrimaryKey(newStNewNdc.getPrimaryKey());

		Assert.assertNull(existingStNewNdc);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addStNewNdc();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		StNewNdcPK pk = new StNewNdcPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		StNewNdc newStNewNdc = _persistence.create(pk);

		newStNewNdc.setForecastAmp(RandomTestUtil.nextDouble());

		newStNewNdc.setForecastBestprice(RandomTestUtil.nextDouble());

		newStNewNdc.setBaseYearCpi(RandomTestUtil.nextDouble());

		newStNewNdc.setLastModifiedDate(RandomTestUtil.nextDate());

		newStNewNdc.setWacPrice(RandomTestUtil.nextDouble());

		newStNewNdc.setBaseYearAmp(RandomTestUtil.nextDouble());

		_stNewNdcs.add(_persistence.update(newStNewNdc));

		StNewNdc existingStNewNdc = _persistence.findByPrimaryKey(newStNewNdc.getPrimaryKey());

		AssertUtils.assertEquals(existingStNewNdc.getForecastAmp(),
			newStNewNdc.getForecastAmp());
		AssertUtils.assertEquals(existingStNewNdc.getForecastBestprice(),
			newStNewNdc.getForecastBestprice());
		Assert.assertEquals(existingStNewNdc.getNaProjDetailsSid(),
			newStNewNdc.getNaProjDetailsSid());
		AssertUtils.assertEquals(existingStNewNdc.getBaseYearCpi(),
			newStNewNdc.getBaseYearCpi());
		Assert.assertEquals(existingStNewNdc.getUserId(),
			newStNewNdc.getUserId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingStNewNdc.getLastModifiedDate()),
			Time.getShortTimestamp(newStNewNdc.getLastModifiedDate()));
		Assert.assertEquals(existingStNewNdc.getItemMasterSid(),
			newStNewNdc.getItemMasterSid());
		AssertUtils.assertEquals(existingStNewNdc.getWacPrice(),
			newStNewNdc.getWacPrice());
		AssertUtils.assertEquals(existingStNewNdc.getBaseYearAmp(),
			newStNewNdc.getBaseYearAmp());
		Assert.assertEquals(existingStNewNdc.getSessionId(),
			newStNewNdc.getSessionId());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		StNewNdc newStNewNdc = addStNewNdc();

		StNewNdc existingStNewNdc = _persistence.findByPrimaryKey(newStNewNdc.getPrimaryKey());

		Assert.assertEquals(existingStNewNdc, newStNewNdc);
	}

	@Test(expected = NoSuchStNewNdcException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		StNewNdcPK pk = new StNewNdcPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		StNewNdc newStNewNdc = addStNewNdc();

		StNewNdc existingStNewNdc = _persistence.fetchByPrimaryKey(newStNewNdc.getPrimaryKey());

		Assert.assertEquals(existingStNewNdc, newStNewNdc);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		StNewNdcPK pk = new StNewNdcPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		StNewNdc missingStNewNdc = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingStNewNdc);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		StNewNdc newStNewNdc1 = addStNewNdc();
		StNewNdc newStNewNdc2 = addStNewNdc();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStNewNdc1.getPrimaryKey());
		primaryKeys.add(newStNewNdc2.getPrimaryKey());

		Map<Serializable, StNewNdc> stNewNdcs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, stNewNdcs.size());
		Assert.assertEquals(newStNewNdc1,
			stNewNdcs.get(newStNewNdc1.getPrimaryKey()));
		Assert.assertEquals(newStNewNdc2,
			stNewNdcs.get(newStNewNdc2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		StNewNdcPK pk1 = new StNewNdcPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		StNewNdcPK pk2 = new StNewNdcPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, StNewNdc> stNewNdcs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stNewNdcs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		StNewNdc newStNewNdc = addStNewNdc();

		StNewNdcPK pk = new StNewNdcPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStNewNdc.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, StNewNdc> stNewNdcs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stNewNdcs.size());
		Assert.assertEquals(newStNewNdc,
			stNewNdcs.get(newStNewNdc.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, StNewNdc> stNewNdcs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stNewNdcs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		StNewNdc newStNewNdc = addStNewNdc();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStNewNdc.getPrimaryKey());

		Map<Serializable, StNewNdc> stNewNdcs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stNewNdcs.size());
		Assert.assertEquals(newStNewNdc,
			stNewNdcs.get(newStNewNdc.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = StNewNdcLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<StNewNdc>() {
				@Override
				public void performAction(StNewNdc stNewNdc) {
					Assert.assertNotNull(stNewNdc);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		StNewNdc newStNewNdc = addStNewNdc();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StNewNdc.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.naProjDetailsSid",
				newStNewNdc.getNaProjDetailsSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				newStNewNdc.getUserId()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.itemMasterSid",
				newStNewNdc.getItemMasterSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				newStNewNdc.getSessionId()));

		List<StNewNdc> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		StNewNdc existingStNewNdc = result.get(0);

		Assert.assertEquals(existingStNewNdc, newStNewNdc);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StNewNdc.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.naProjDetailsSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.itemMasterSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				RandomTestUtil.nextInt()));

		List<StNewNdc> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		StNewNdc newStNewNdc = addStNewNdc();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StNewNdc.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.naProjDetailsSid"));

		Object newNaProjDetailsSid = newStNewNdc.getNaProjDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.naProjDetailsSid",
				new Object[] { newNaProjDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingNaProjDetailsSid = result.get(0);

		Assert.assertEquals(existingNaProjDetailsSid, newNaProjDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StNewNdc.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.naProjDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.naProjDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected StNewNdc addStNewNdc() throws Exception {
		StNewNdcPK pk = new StNewNdcPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		StNewNdc stNewNdc = _persistence.create(pk);

		stNewNdc.setForecastAmp(RandomTestUtil.nextDouble());

		stNewNdc.setForecastBestprice(RandomTestUtil.nextDouble());

		stNewNdc.setBaseYearCpi(RandomTestUtil.nextDouble());

		stNewNdc.setLastModifiedDate(RandomTestUtil.nextDate());

		stNewNdc.setWacPrice(RandomTestUtil.nextDouble());

		stNewNdc.setBaseYearAmp(RandomTestUtil.nextDouble());

		_stNewNdcs.add(_persistence.update(stNewNdc));

		return stNewNdc;
	}

	private List<StNewNdc> _stNewNdcs = new ArrayList<StNewNdc>();
	private StNewNdcPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}