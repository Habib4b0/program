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

import com.stpl.app.exception.NoSuchStNmPpaProjectionMasterException;
import com.stpl.app.model.StNmPpaProjectionMaster;
import com.stpl.app.service.StNmPpaProjectionMasterLocalServiceUtil;
import com.stpl.app.service.persistence.StNmPpaProjectionMasterPK;
import com.stpl.app.service.persistence.StNmPpaProjectionMasterPersistence;
import com.stpl.app.service.persistence.StNmPpaProjectionMasterUtil;

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
public class StNmPpaProjectionMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = StNmPpaProjectionMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<StNmPpaProjectionMaster> iterator = _stNmPpaProjectionMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		StNmPpaProjectionMasterPK pk = new StNmPpaProjectionMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StNmPpaProjectionMaster stNmPpaProjectionMaster = _persistence.create(pk);

		Assert.assertNotNull(stNmPpaProjectionMaster);

		Assert.assertEquals(stNmPpaProjectionMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		StNmPpaProjectionMaster newStNmPpaProjectionMaster = addStNmPpaProjectionMaster();

		_persistence.remove(newStNmPpaProjectionMaster);

		StNmPpaProjectionMaster existingStNmPpaProjectionMaster = _persistence.fetchByPrimaryKey(newStNmPpaProjectionMaster.getPrimaryKey());

		Assert.assertNull(existingStNmPpaProjectionMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addStNmPpaProjectionMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		StNmPpaProjectionMasterPK pk = new StNmPpaProjectionMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StNmPpaProjectionMaster newStNmPpaProjectionMaster = _persistence.create(pk);

		newStNmPpaProjectionMaster.setLastModifiedDate(RandomTestUtil.nextDate());

		newStNmPpaProjectionMaster.setCheckRecord(RandomTestUtil.randomBoolean());

		newStNmPpaProjectionMaster.setUserGroup(RandomTestUtil.randomString());

		newStNmPpaProjectionMaster.setPriceBasis(RandomTestUtil.randomString());

		newStNmPpaProjectionMaster.setPriceProtectionEndDate(RandomTestUtil.nextDate());

		newStNmPpaProjectionMaster.setPriceProtectionStartDate(RandomTestUtil.nextDate());

		newStNmPpaProjectionMaster.setActualPriceCap(RandomTestUtil.nextDouble());

		_stNmPpaProjectionMasters.add(_persistence.update(
				newStNmPpaProjectionMaster));

		StNmPpaProjectionMaster existingStNmPpaProjectionMaster = _persistence.findByPrimaryKey(newStNmPpaProjectionMaster.getPrimaryKey());

		Assert.assertEquals(Time.getShortTimestamp(
				existingStNmPpaProjectionMaster.getLastModifiedDate()),
			Time.getShortTimestamp(
				newStNmPpaProjectionMaster.getLastModifiedDate()));
		Assert.assertEquals(existingStNmPpaProjectionMaster.getCheckRecord(),
			newStNmPpaProjectionMaster.getCheckRecord());
		Assert.assertEquals(existingStNmPpaProjectionMaster.getUserGroup(),
			newStNmPpaProjectionMaster.getUserGroup());
		Assert.assertEquals(existingStNmPpaProjectionMaster.getProjectionDetailsSid(),
			newStNmPpaProjectionMaster.getProjectionDetailsSid());
		Assert.assertEquals(existingStNmPpaProjectionMaster.getUserId(),
			newStNmPpaProjectionMaster.getUserId());
		Assert.assertEquals(existingStNmPpaProjectionMaster.getSessionId(),
			newStNmPpaProjectionMaster.getSessionId());
		Assert.assertEquals(existingStNmPpaProjectionMaster.getPriceBasis(),
			newStNmPpaProjectionMaster.getPriceBasis());
		Assert.assertEquals(Time.getShortTimestamp(
				existingStNmPpaProjectionMaster.getPriceProtectionEndDate()),
			Time.getShortTimestamp(
				newStNmPpaProjectionMaster.getPriceProtectionEndDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingStNmPpaProjectionMaster.getPriceProtectionStartDate()),
			Time.getShortTimestamp(
				newStNmPpaProjectionMaster.getPriceProtectionStartDate()));
		AssertUtils.assertEquals(existingStNmPpaProjectionMaster.getActualPriceCap(),
			newStNmPpaProjectionMaster.getActualPriceCap());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		StNmPpaProjectionMaster newStNmPpaProjectionMaster = addStNmPpaProjectionMaster();

		StNmPpaProjectionMaster existingStNmPpaProjectionMaster = _persistence.findByPrimaryKey(newStNmPpaProjectionMaster.getPrimaryKey());

		Assert.assertEquals(existingStNmPpaProjectionMaster,
			newStNmPpaProjectionMaster);
	}

	@Test(expected = NoSuchStNmPpaProjectionMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		StNmPpaProjectionMasterPK pk = new StNmPpaProjectionMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		StNmPpaProjectionMaster newStNmPpaProjectionMaster = addStNmPpaProjectionMaster();

		StNmPpaProjectionMaster existingStNmPpaProjectionMaster = _persistence.fetchByPrimaryKey(newStNmPpaProjectionMaster.getPrimaryKey());

		Assert.assertEquals(existingStNmPpaProjectionMaster,
			newStNmPpaProjectionMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		StNmPpaProjectionMasterPK pk = new StNmPpaProjectionMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StNmPpaProjectionMaster missingStNmPpaProjectionMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingStNmPpaProjectionMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		StNmPpaProjectionMaster newStNmPpaProjectionMaster1 = addStNmPpaProjectionMaster();
		StNmPpaProjectionMaster newStNmPpaProjectionMaster2 = addStNmPpaProjectionMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStNmPpaProjectionMaster1.getPrimaryKey());
		primaryKeys.add(newStNmPpaProjectionMaster2.getPrimaryKey());

		Map<Serializable, StNmPpaProjectionMaster> stNmPpaProjectionMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, stNmPpaProjectionMasters.size());
		Assert.assertEquals(newStNmPpaProjectionMaster1,
			stNmPpaProjectionMasters.get(
				newStNmPpaProjectionMaster1.getPrimaryKey()));
		Assert.assertEquals(newStNmPpaProjectionMaster2,
			stNmPpaProjectionMasters.get(
				newStNmPpaProjectionMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		StNmPpaProjectionMasterPK pk1 = new StNmPpaProjectionMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StNmPpaProjectionMasterPK pk2 = new StNmPpaProjectionMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, StNmPpaProjectionMaster> stNmPpaProjectionMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stNmPpaProjectionMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		StNmPpaProjectionMaster newStNmPpaProjectionMaster = addStNmPpaProjectionMaster();

		StNmPpaProjectionMasterPK pk = new StNmPpaProjectionMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStNmPpaProjectionMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, StNmPpaProjectionMaster> stNmPpaProjectionMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stNmPpaProjectionMasters.size());
		Assert.assertEquals(newStNmPpaProjectionMaster,
			stNmPpaProjectionMasters.get(
				newStNmPpaProjectionMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, StNmPpaProjectionMaster> stNmPpaProjectionMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stNmPpaProjectionMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		StNmPpaProjectionMaster newStNmPpaProjectionMaster = addStNmPpaProjectionMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStNmPpaProjectionMaster.getPrimaryKey());

		Map<Serializable, StNmPpaProjectionMaster> stNmPpaProjectionMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stNmPpaProjectionMasters.size());
		Assert.assertEquals(newStNmPpaProjectionMaster,
			stNmPpaProjectionMasters.get(
				newStNmPpaProjectionMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = StNmPpaProjectionMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<StNmPpaProjectionMaster>() {
				@Override
				public void performAction(
					StNmPpaProjectionMaster stNmPpaProjectionMaster) {
					Assert.assertNotNull(stNmPpaProjectionMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		StNmPpaProjectionMaster newStNmPpaProjectionMaster = addStNmPpaProjectionMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StNmPpaProjectionMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				newStNmPpaProjectionMaster.getProjectionDetailsSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				newStNmPpaProjectionMaster.getUserId()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				newStNmPpaProjectionMaster.getSessionId()));

		List<StNmPpaProjectionMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		StNmPpaProjectionMaster existingStNmPpaProjectionMaster = result.get(0);

		Assert.assertEquals(existingStNmPpaProjectionMaster,
			newStNmPpaProjectionMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StNmPpaProjectionMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				RandomTestUtil.nextInt()));

		List<StNmPpaProjectionMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		StNmPpaProjectionMaster newStNmPpaProjectionMaster = addStNmPpaProjectionMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StNmPpaProjectionMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.projectionDetailsSid"));

		Object newProjectionDetailsSid = newStNmPpaProjectionMaster.getProjectionDetailsSid();

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
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StNmPpaProjectionMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.projectionDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.projectionDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected StNmPpaProjectionMaster addStNmPpaProjectionMaster()
		throws Exception {
		StNmPpaProjectionMasterPK pk = new StNmPpaProjectionMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StNmPpaProjectionMaster stNmPpaProjectionMaster = _persistence.create(pk);

		stNmPpaProjectionMaster.setLastModifiedDate(RandomTestUtil.nextDate());

		stNmPpaProjectionMaster.setCheckRecord(RandomTestUtil.randomBoolean());

		stNmPpaProjectionMaster.setUserGroup(RandomTestUtil.randomString());

		stNmPpaProjectionMaster.setPriceBasis(RandomTestUtil.randomString());

		stNmPpaProjectionMaster.setPriceProtectionEndDate(RandomTestUtil.nextDate());

		stNmPpaProjectionMaster.setPriceProtectionStartDate(RandomTestUtil.nextDate());

		stNmPpaProjectionMaster.setActualPriceCap(RandomTestUtil.nextDouble());

		_stNmPpaProjectionMasters.add(_persistence.update(
				stNmPpaProjectionMaster));

		return stNmPpaProjectionMaster;
	}

	private List<StNmPpaProjectionMaster> _stNmPpaProjectionMasters = new ArrayList<StNmPpaProjectionMaster>();
	private StNmPpaProjectionMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}