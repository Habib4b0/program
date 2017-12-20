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

import com.stpl.app.exception.NoSuchStFederalNewNdcException;
import com.stpl.app.model.StFederalNewNdc;
import com.stpl.app.service.StFederalNewNdcLocalServiceUtil;
import com.stpl.app.service.persistence.StFederalNewNdcPK;
import com.stpl.app.service.persistence.StFederalNewNdcPersistence;
import com.stpl.app.service.persistence.StFederalNewNdcUtil;

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
public class StFederalNewNdcPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = StFederalNewNdcUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<StFederalNewNdc> iterator = _stFederalNewNdcs.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		StFederalNewNdcPK pk = new StFederalNewNdcPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StFederalNewNdc stFederalNewNdc = _persistence.create(pk);

		Assert.assertNotNull(stFederalNewNdc);

		Assert.assertEquals(stFederalNewNdc.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		StFederalNewNdc newStFederalNewNdc = addStFederalNewNdc();

		_persistence.remove(newStFederalNewNdc);

		StFederalNewNdc existingStFederalNewNdc = _persistence.fetchByPrimaryKey(newStFederalNewNdc.getPrimaryKey());

		Assert.assertNull(existingStFederalNewNdc);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addStFederalNewNdc();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		StFederalNewNdcPK pk = new StFederalNewNdcPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StFederalNewNdc newStFederalNewNdc = _persistence.create(pk);

		newStFederalNewNdc.setFss(RandomTestUtil.nextDouble());

		newStFederalNewNdc.setLastModifiedDate(RandomTestUtil.nextDate());

		newStFederalNewNdc.setWacPrice(RandomTestUtil.nextDouble());

		newStFederalNewNdc.setNonFamp(RandomTestUtil.nextDouble());

		_stFederalNewNdcs.add(_persistence.update(newStFederalNewNdc));

		StFederalNewNdc existingStFederalNewNdc = _persistence.findByPrimaryKey(newStFederalNewNdc.getPrimaryKey());

		AssertUtils.assertEquals(existingStFederalNewNdc.getFss(),
			newStFederalNewNdc.getFss());
		Assert.assertEquals(existingStFederalNewNdc.getUserId(),
			newStFederalNewNdc.getUserId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingStFederalNewNdc.getLastModifiedDate()),
			Time.getShortTimestamp(newStFederalNewNdc.getLastModifiedDate()));
		Assert.assertEquals(existingStFederalNewNdc.getItemMasterSid(),
			newStFederalNewNdc.getItemMasterSid());
		AssertUtils.assertEquals(existingStFederalNewNdc.getWacPrice(),
			newStFederalNewNdc.getWacPrice());
		Assert.assertEquals(existingStFederalNewNdc.getSessionId(),
			newStFederalNewNdc.getSessionId());
		AssertUtils.assertEquals(existingStFederalNewNdc.getNonFamp(),
			newStFederalNewNdc.getNonFamp());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		StFederalNewNdc newStFederalNewNdc = addStFederalNewNdc();

		StFederalNewNdc existingStFederalNewNdc = _persistence.findByPrimaryKey(newStFederalNewNdc.getPrimaryKey());

		Assert.assertEquals(existingStFederalNewNdc, newStFederalNewNdc);
	}

	@Test(expected = NoSuchStFederalNewNdcException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		StFederalNewNdcPK pk = new StFederalNewNdcPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		StFederalNewNdc newStFederalNewNdc = addStFederalNewNdc();

		StFederalNewNdc existingStFederalNewNdc = _persistence.fetchByPrimaryKey(newStFederalNewNdc.getPrimaryKey());

		Assert.assertEquals(existingStFederalNewNdc, newStFederalNewNdc);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		StFederalNewNdcPK pk = new StFederalNewNdcPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StFederalNewNdc missingStFederalNewNdc = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingStFederalNewNdc);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		StFederalNewNdc newStFederalNewNdc1 = addStFederalNewNdc();
		StFederalNewNdc newStFederalNewNdc2 = addStFederalNewNdc();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStFederalNewNdc1.getPrimaryKey());
		primaryKeys.add(newStFederalNewNdc2.getPrimaryKey());

		Map<Serializable, StFederalNewNdc> stFederalNewNdcs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, stFederalNewNdcs.size());
		Assert.assertEquals(newStFederalNewNdc1,
			stFederalNewNdcs.get(newStFederalNewNdc1.getPrimaryKey()));
		Assert.assertEquals(newStFederalNewNdc2,
			stFederalNewNdcs.get(newStFederalNewNdc2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		StFederalNewNdcPK pk1 = new StFederalNewNdcPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StFederalNewNdcPK pk2 = new StFederalNewNdcPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, StFederalNewNdc> stFederalNewNdcs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stFederalNewNdcs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		StFederalNewNdc newStFederalNewNdc = addStFederalNewNdc();

		StFederalNewNdcPK pk = new StFederalNewNdcPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStFederalNewNdc.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, StFederalNewNdc> stFederalNewNdcs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stFederalNewNdcs.size());
		Assert.assertEquals(newStFederalNewNdc,
			stFederalNewNdcs.get(newStFederalNewNdc.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, StFederalNewNdc> stFederalNewNdcs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stFederalNewNdcs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		StFederalNewNdc newStFederalNewNdc = addStFederalNewNdc();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStFederalNewNdc.getPrimaryKey());

		Map<Serializable, StFederalNewNdc> stFederalNewNdcs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stFederalNewNdcs.size());
		Assert.assertEquals(newStFederalNewNdc,
			stFederalNewNdcs.get(newStFederalNewNdc.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = StFederalNewNdcLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<StFederalNewNdc>() {
				@Override
				public void performAction(StFederalNewNdc stFederalNewNdc) {
					Assert.assertNotNull(stFederalNewNdc);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		StFederalNewNdc newStFederalNewNdc = addStFederalNewNdc();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StFederalNewNdc.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				newStFederalNewNdc.getUserId()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.itemMasterSid",
				newStFederalNewNdc.getItemMasterSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				newStFederalNewNdc.getSessionId()));

		List<StFederalNewNdc> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		StFederalNewNdc existingStFederalNewNdc = result.get(0);

		Assert.assertEquals(existingStFederalNewNdc, newStFederalNewNdc);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StFederalNewNdc.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.itemMasterSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				RandomTestUtil.nextInt()));

		List<StFederalNewNdc> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		StFederalNewNdc newStFederalNewNdc = addStFederalNewNdc();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StFederalNewNdc.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("id.userId"));

		Object newUserId = newStFederalNewNdc.getUserId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.userId",
				new Object[] { newUserId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingUserId = result.get(0);

		Assert.assertEquals(existingUserId, newUserId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StFederalNewNdc.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("id.userId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.userId",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected StFederalNewNdc addStFederalNewNdc() throws Exception {
		StFederalNewNdcPK pk = new StFederalNewNdcPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StFederalNewNdc stFederalNewNdc = _persistence.create(pk);

		stFederalNewNdc.setFss(RandomTestUtil.nextDouble());

		stFederalNewNdc.setLastModifiedDate(RandomTestUtil.nextDate());

		stFederalNewNdc.setWacPrice(RandomTestUtil.nextDouble());

		stFederalNewNdc.setNonFamp(RandomTestUtil.nextDouble());

		_stFederalNewNdcs.add(_persistence.update(stFederalNewNdc));

		return stFederalNewNdc;
	}

	private List<StFederalNewNdc> _stFederalNewNdcs = new ArrayList<StFederalNewNdc>();
	private StFederalNewNdcPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}