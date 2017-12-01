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
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchStNmDiscountProjMasterException;
import com.stpl.app.model.StNmDiscountProjMaster;
import com.stpl.app.service.StNmDiscountProjMasterLocalServiceUtil;
import com.stpl.app.service.persistence.StNmDiscountProjMasterPK;
import com.stpl.app.service.persistence.StNmDiscountProjMasterPersistence;
import com.stpl.app.service.persistence.StNmDiscountProjMasterUtil;

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
public class StNmDiscountProjMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = StNmDiscountProjMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<StNmDiscountProjMaster> iterator = _stNmDiscountProjMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		StNmDiscountProjMasterPK pk = new StNmDiscountProjMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		StNmDiscountProjMaster stNmDiscountProjMaster = _persistence.create(pk);

		Assert.assertNotNull(stNmDiscountProjMaster);

		Assert.assertEquals(stNmDiscountProjMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		StNmDiscountProjMaster newStNmDiscountProjMaster = addStNmDiscountProjMaster();

		_persistence.remove(newStNmDiscountProjMaster);

		StNmDiscountProjMaster existingStNmDiscountProjMaster = _persistence.fetchByPrimaryKey(newStNmDiscountProjMaster.getPrimaryKey());

		Assert.assertNull(existingStNmDiscountProjMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addStNmDiscountProjMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		StNmDiscountProjMasterPK pk = new StNmDiscountProjMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		StNmDiscountProjMaster newStNmDiscountProjMaster = _persistence.create(pk);

		newStNmDiscountProjMaster.setSelectedPeriods(RandomTestUtil.randomString());

		newStNmDiscountProjMaster.setMethodology(RandomTestUtil.randomString());

		newStNmDiscountProjMaster.setNetFlag(RandomTestUtil.randomString());

		newStNmDiscountProjMaster.setPriceGroupType(RandomTestUtil.randomString());

		newStNmDiscountProjMaster.setUserGroup(RandomTestUtil.randomString());

		newStNmDiscountProjMaster.setLastModifiedDate(RandomTestUtil.nextDate());

		newStNmDiscountProjMaster.setCheckRecord(RandomTestUtil.randomBoolean());

		newStNmDiscountProjMaster.setBaselinePeriods(RandomTestUtil.randomString());

		_stNmDiscountProjMasters.add(_persistence.update(
				newStNmDiscountProjMaster));

		StNmDiscountProjMaster existingStNmDiscountProjMaster = _persistence.findByPrimaryKey(newStNmDiscountProjMaster.getPrimaryKey());

		Assert.assertEquals(existingStNmDiscountProjMaster.getSelectedPeriods(),
			newStNmDiscountProjMaster.getSelectedPeriods());
		Assert.assertEquals(existingStNmDiscountProjMaster.getMethodology(),
			newStNmDiscountProjMaster.getMethodology());
		Assert.assertEquals(existingStNmDiscountProjMaster.getNetFlag(),
			newStNmDiscountProjMaster.getNetFlag());
		Assert.assertEquals(existingStNmDiscountProjMaster.getPriceGroupType(),
			newStNmDiscountProjMaster.getPriceGroupType());
		Assert.assertEquals(existingStNmDiscountProjMaster.getUserGroup(),
			newStNmDiscountProjMaster.getUserGroup());
		Assert.assertEquals(existingStNmDiscountProjMaster.getUserId(),
			newStNmDiscountProjMaster.getUserId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingStNmDiscountProjMaster.getLastModifiedDate()),
			Time.getShortTimestamp(
				newStNmDiscountProjMaster.getLastModifiedDate()));
		Assert.assertEquals(existingStNmDiscountProjMaster.getProjectionDetailsSid(),
			newStNmDiscountProjMaster.getProjectionDetailsSid());
		Assert.assertEquals(existingStNmDiscountProjMaster.getRsModelSid(),
			newStNmDiscountProjMaster.getRsModelSid());
		Assert.assertEquals(existingStNmDiscountProjMaster.getSessionId(),
			newStNmDiscountProjMaster.getSessionId());
		Assert.assertEquals(existingStNmDiscountProjMaster.getCheckRecord(),
			newStNmDiscountProjMaster.getCheckRecord());
		Assert.assertEquals(existingStNmDiscountProjMaster.getBaselinePeriods(),
			newStNmDiscountProjMaster.getBaselinePeriods());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		StNmDiscountProjMaster newStNmDiscountProjMaster = addStNmDiscountProjMaster();

		StNmDiscountProjMaster existingStNmDiscountProjMaster = _persistence.findByPrimaryKey(newStNmDiscountProjMaster.getPrimaryKey());

		Assert.assertEquals(existingStNmDiscountProjMaster,
			newStNmDiscountProjMaster);
	}

	@Test(expected = NoSuchStNmDiscountProjMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		StNmDiscountProjMasterPK pk = new StNmDiscountProjMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		StNmDiscountProjMaster newStNmDiscountProjMaster = addStNmDiscountProjMaster();

		StNmDiscountProjMaster existingStNmDiscountProjMaster = _persistence.fetchByPrimaryKey(newStNmDiscountProjMaster.getPrimaryKey());

		Assert.assertEquals(existingStNmDiscountProjMaster,
			newStNmDiscountProjMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		StNmDiscountProjMasterPK pk = new StNmDiscountProjMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		StNmDiscountProjMaster missingStNmDiscountProjMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingStNmDiscountProjMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		StNmDiscountProjMaster newStNmDiscountProjMaster1 = addStNmDiscountProjMaster();
		StNmDiscountProjMaster newStNmDiscountProjMaster2 = addStNmDiscountProjMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStNmDiscountProjMaster1.getPrimaryKey());
		primaryKeys.add(newStNmDiscountProjMaster2.getPrimaryKey());

		Map<Serializable, StNmDiscountProjMaster> stNmDiscountProjMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, stNmDiscountProjMasters.size());
		Assert.assertEquals(newStNmDiscountProjMaster1,
			stNmDiscountProjMasters.get(
				newStNmDiscountProjMaster1.getPrimaryKey()));
		Assert.assertEquals(newStNmDiscountProjMaster2,
			stNmDiscountProjMasters.get(
				newStNmDiscountProjMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		StNmDiscountProjMasterPK pk1 = new StNmDiscountProjMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		StNmDiscountProjMasterPK pk2 = new StNmDiscountProjMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, StNmDiscountProjMaster> stNmDiscountProjMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stNmDiscountProjMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		StNmDiscountProjMaster newStNmDiscountProjMaster = addStNmDiscountProjMaster();

		StNmDiscountProjMasterPK pk = new StNmDiscountProjMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStNmDiscountProjMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, StNmDiscountProjMaster> stNmDiscountProjMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stNmDiscountProjMasters.size());
		Assert.assertEquals(newStNmDiscountProjMaster,
			stNmDiscountProjMasters.get(
				newStNmDiscountProjMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, StNmDiscountProjMaster> stNmDiscountProjMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stNmDiscountProjMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		StNmDiscountProjMaster newStNmDiscountProjMaster = addStNmDiscountProjMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStNmDiscountProjMaster.getPrimaryKey());

		Map<Serializable, StNmDiscountProjMaster> stNmDiscountProjMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stNmDiscountProjMasters.size());
		Assert.assertEquals(newStNmDiscountProjMaster,
			stNmDiscountProjMasters.get(
				newStNmDiscountProjMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = StNmDiscountProjMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<StNmDiscountProjMaster>() {
				@Override
				public void performAction(
					StNmDiscountProjMaster stNmDiscountProjMaster) {
					Assert.assertNotNull(stNmDiscountProjMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		StNmDiscountProjMaster newStNmDiscountProjMaster = addStNmDiscountProjMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StNmDiscountProjMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				newStNmDiscountProjMaster.getUserId()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				newStNmDiscountProjMaster.getProjectionDetailsSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.rsModelSid",
				newStNmDiscountProjMaster.getRsModelSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				newStNmDiscountProjMaster.getSessionId()));

		List<StNmDiscountProjMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		StNmDiscountProjMaster existingStNmDiscountProjMaster = result.get(0);

		Assert.assertEquals(existingStNmDiscountProjMaster,
			newStNmDiscountProjMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StNmDiscountProjMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.rsModelSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				RandomTestUtil.nextInt()));

		List<StNmDiscountProjMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		StNmDiscountProjMaster newStNmDiscountProjMaster = addStNmDiscountProjMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StNmDiscountProjMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("id.userId"));

		Object newUserId = newStNmDiscountProjMaster.getUserId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.userId",
				new Object[] { newUserId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingUserId = result.get(0);

		Assert.assertEquals(existingUserId, newUserId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StNmDiscountProjMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("id.userId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.userId",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected StNmDiscountProjMaster addStNmDiscountProjMaster()
		throws Exception {
		StNmDiscountProjMasterPK pk = new StNmDiscountProjMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		StNmDiscountProjMaster stNmDiscountProjMaster = _persistence.create(pk);

		stNmDiscountProjMaster.setSelectedPeriods(RandomTestUtil.randomString());

		stNmDiscountProjMaster.setMethodology(RandomTestUtil.randomString());

		stNmDiscountProjMaster.setNetFlag(RandomTestUtil.randomString());

		stNmDiscountProjMaster.setPriceGroupType(RandomTestUtil.randomString());

		stNmDiscountProjMaster.setUserGroup(RandomTestUtil.randomString());

		stNmDiscountProjMaster.setLastModifiedDate(RandomTestUtil.nextDate());

		stNmDiscountProjMaster.setCheckRecord(RandomTestUtil.randomBoolean());

		stNmDiscountProjMaster.setBaselinePeriods(RandomTestUtil.randomString());

		_stNmDiscountProjMasters.add(_persistence.update(stNmDiscountProjMaster));

		return stNmDiscountProjMaster;
	}

	private List<StNmDiscountProjMaster> _stNmDiscountProjMasters = new ArrayList<StNmDiscountProjMaster>();
	private StNmDiscountProjMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}