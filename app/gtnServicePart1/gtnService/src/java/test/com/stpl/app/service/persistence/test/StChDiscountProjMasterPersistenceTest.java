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

import com.stpl.app.exception.NoSuchStChDiscountProjMasterException;
import com.stpl.app.model.StChDiscountProjMaster;
import com.stpl.app.service.StChDiscountProjMasterLocalServiceUtil;
import com.stpl.app.service.persistence.StChDiscountProjMasterPK;
import com.stpl.app.service.persistence.StChDiscountProjMasterPersistence;
import com.stpl.app.service.persistence.StChDiscountProjMasterUtil;

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
public class StChDiscountProjMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = StChDiscountProjMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<StChDiscountProjMaster> iterator = _stChDiscountProjMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		StChDiscountProjMasterPK pk = new StChDiscountProjMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		StChDiscountProjMaster stChDiscountProjMaster = _persistence.create(pk);

		Assert.assertNotNull(stChDiscountProjMaster);

		Assert.assertEquals(stChDiscountProjMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		StChDiscountProjMaster newStChDiscountProjMaster = addStChDiscountProjMaster();

		_persistence.remove(newStChDiscountProjMaster);

		StChDiscountProjMaster existingStChDiscountProjMaster = _persistence.fetchByPrimaryKey(newStChDiscountProjMaster.getPrimaryKey());

		Assert.assertNull(existingStChDiscountProjMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addStChDiscountProjMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		StChDiscountProjMasterPK pk = new StChDiscountProjMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		StChDiscountProjMaster newStChDiscountProjMaster = _persistence.create(pk);

		newStChDiscountProjMaster.setCheckRecord(RandomTestUtil.randomBoolean());

		newStChDiscountProjMaster.setSelectedPeriods(RandomTestUtil.randomString());

		newStChDiscountProjMaster.setLastModifiedDate(RandomTestUtil.nextDate());

		newStChDiscountProjMaster.setPriceGroupType(RandomTestUtil.randomString());

		newStChDiscountProjMaster.setNetFlag(RandomTestUtil.randomString());

		newStChDiscountProjMaster.setProjectedType(RandomTestUtil.randomString());

		newStChDiscountProjMaster.setBaselinePeriods(RandomTestUtil.randomString());

		newStChDiscountProjMaster.setMethodology(RandomTestUtil.randomString());

		newStChDiscountProjMaster.setDiscountType(RandomTestUtil.randomString());

		_stChDiscountProjMasters.add(_persistence.update(
				newStChDiscountProjMaster));

		StChDiscountProjMaster existingStChDiscountProjMaster = _persistence.findByPrimaryKey(newStChDiscountProjMaster.getPrimaryKey());

		Assert.assertEquals(existingStChDiscountProjMaster.getCheckRecord(),
			newStChDiscountProjMaster.getCheckRecord());
		Assert.assertEquals(existingStChDiscountProjMaster.getSelectedPeriods(),
			newStChDiscountProjMaster.getSelectedPeriods());
		Assert.assertEquals(Time.getShortTimestamp(
				existingStChDiscountProjMaster.getLastModifiedDate()),
			Time.getShortTimestamp(
				newStChDiscountProjMaster.getLastModifiedDate()));
		Assert.assertEquals(existingStChDiscountProjMaster.getProjectionDetailsSid(),
			newStChDiscountProjMaster.getProjectionDetailsSid());
		Assert.assertEquals(existingStChDiscountProjMaster.getPriceGroupType(),
			newStChDiscountProjMaster.getPriceGroupType());
		Assert.assertEquals(existingStChDiscountProjMaster.getUserId(),
			newStChDiscountProjMaster.getUserId());
		Assert.assertEquals(existingStChDiscountProjMaster.getNetFlag(),
			newStChDiscountProjMaster.getNetFlag());
		Assert.assertEquals(existingStChDiscountProjMaster.getProjectedType(),
			newStChDiscountProjMaster.getProjectedType());
		Assert.assertEquals(existingStChDiscountProjMaster.getBaselinePeriods(),
			newStChDiscountProjMaster.getBaselinePeriods());
		Assert.assertEquals(existingStChDiscountProjMaster.getSessionId(),
			newStChDiscountProjMaster.getSessionId());
		Assert.assertEquals(existingStChDiscountProjMaster.getMethodology(),
			newStChDiscountProjMaster.getMethodology());
		Assert.assertEquals(existingStChDiscountProjMaster.getRsModelSid(),
			newStChDiscountProjMaster.getRsModelSid());
		Assert.assertEquals(existingStChDiscountProjMaster.getDiscountType(),
			newStChDiscountProjMaster.getDiscountType());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		StChDiscountProjMaster newStChDiscountProjMaster = addStChDiscountProjMaster();

		StChDiscountProjMaster existingStChDiscountProjMaster = _persistence.findByPrimaryKey(newStChDiscountProjMaster.getPrimaryKey());

		Assert.assertEquals(existingStChDiscountProjMaster,
			newStChDiscountProjMaster);
	}

	@Test(expected = NoSuchStChDiscountProjMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		StChDiscountProjMasterPK pk = new StChDiscountProjMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		StChDiscountProjMaster newStChDiscountProjMaster = addStChDiscountProjMaster();

		StChDiscountProjMaster existingStChDiscountProjMaster = _persistence.fetchByPrimaryKey(newStChDiscountProjMaster.getPrimaryKey());

		Assert.assertEquals(existingStChDiscountProjMaster,
			newStChDiscountProjMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		StChDiscountProjMasterPK pk = new StChDiscountProjMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		StChDiscountProjMaster missingStChDiscountProjMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingStChDiscountProjMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		StChDiscountProjMaster newStChDiscountProjMaster1 = addStChDiscountProjMaster();
		StChDiscountProjMaster newStChDiscountProjMaster2 = addStChDiscountProjMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStChDiscountProjMaster1.getPrimaryKey());
		primaryKeys.add(newStChDiscountProjMaster2.getPrimaryKey());

		Map<Serializable, StChDiscountProjMaster> stChDiscountProjMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, stChDiscountProjMasters.size());
		Assert.assertEquals(newStChDiscountProjMaster1,
			stChDiscountProjMasters.get(
				newStChDiscountProjMaster1.getPrimaryKey()));
		Assert.assertEquals(newStChDiscountProjMaster2,
			stChDiscountProjMasters.get(
				newStChDiscountProjMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		StChDiscountProjMasterPK pk1 = new StChDiscountProjMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		StChDiscountProjMasterPK pk2 = new StChDiscountProjMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, StChDiscountProjMaster> stChDiscountProjMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stChDiscountProjMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		StChDiscountProjMaster newStChDiscountProjMaster = addStChDiscountProjMaster();

		StChDiscountProjMasterPK pk = new StChDiscountProjMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStChDiscountProjMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, StChDiscountProjMaster> stChDiscountProjMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stChDiscountProjMasters.size());
		Assert.assertEquals(newStChDiscountProjMaster,
			stChDiscountProjMasters.get(
				newStChDiscountProjMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, StChDiscountProjMaster> stChDiscountProjMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stChDiscountProjMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		StChDiscountProjMaster newStChDiscountProjMaster = addStChDiscountProjMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStChDiscountProjMaster.getPrimaryKey());

		Map<Serializable, StChDiscountProjMaster> stChDiscountProjMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stChDiscountProjMasters.size());
		Assert.assertEquals(newStChDiscountProjMaster,
			stChDiscountProjMasters.get(
				newStChDiscountProjMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = StChDiscountProjMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<StChDiscountProjMaster>() {
				@Override
				public void performAction(
					StChDiscountProjMaster stChDiscountProjMaster) {
					Assert.assertNotNull(stChDiscountProjMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		StChDiscountProjMaster newStChDiscountProjMaster = addStChDiscountProjMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StChDiscountProjMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				newStChDiscountProjMaster.getProjectionDetailsSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				newStChDiscountProjMaster.getUserId()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				newStChDiscountProjMaster.getSessionId()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.rsModelSid",
				newStChDiscountProjMaster.getRsModelSid()));

		List<StChDiscountProjMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		StChDiscountProjMaster existingStChDiscountProjMaster = result.get(0);

		Assert.assertEquals(existingStChDiscountProjMaster,
			newStChDiscountProjMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StChDiscountProjMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.rsModelSid",
				RandomTestUtil.nextInt()));

		List<StChDiscountProjMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		StChDiscountProjMaster newStChDiscountProjMaster = addStChDiscountProjMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StChDiscountProjMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.projectionDetailsSid"));

		Object newProjectionDetailsSid = newStChDiscountProjMaster.getProjectionDetailsSid();

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
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StChDiscountProjMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.projectionDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.projectionDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected StChDiscountProjMaster addStChDiscountProjMaster()
		throws Exception {
		StChDiscountProjMasterPK pk = new StChDiscountProjMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		StChDiscountProjMaster stChDiscountProjMaster = _persistence.create(pk);

		stChDiscountProjMaster.setCheckRecord(RandomTestUtil.randomBoolean());

		stChDiscountProjMaster.setSelectedPeriods(RandomTestUtil.randomString());

		stChDiscountProjMaster.setLastModifiedDate(RandomTestUtil.nextDate());

		stChDiscountProjMaster.setPriceGroupType(RandomTestUtil.randomString());

		stChDiscountProjMaster.setNetFlag(RandomTestUtil.randomString());

		stChDiscountProjMaster.setProjectedType(RandomTestUtil.randomString());

		stChDiscountProjMaster.setBaselinePeriods(RandomTestUtil.randomString());

		stChDiscountProjMaster.setMethodology(RandomTestUtil.randomString());

		stChDiscountProjMaster.setDiscountType(RandomTestUtil.randomString());

		_stChDiscountProjMasters.add(_persistence.update(stChDiscountProjMaster));

		return stChDiscountProjMaster;
	}

	private List<StChDiscountProjMaster> _stChDiscountProjMasters = new ArrayList<StChDiscountProjMaster>();
	private StChDiscountProjMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}