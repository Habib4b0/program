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

import com.stpl.app.exception.NoSuchStMSupplementalDiscMasterException;
import com.stpl.app.model.StMSupplementalDiscMaster;
import com.stpl.app.service.StMSupplementalDiscMasterLocalServiceUtil;
import com.stpl.app.service.persistence.StMSupplementalDiscMasterPK;
import com.stpl.app.service.persistence.StMSupplementalDiscMasterPersistence;
import com.stpl.app.service.persistence.StMSupplementalDiscMasterUtil;

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
public class StMSupplementalDiscMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = StMSupplementalDiscMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<StMSupplementalDiscMaster> iterator = _stMSupplementalDiscMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		StMSupplementalDiscMasterPK pk = new StMSupplementalDiscMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StMSupplementalDiscMaster stMSupplementalDiscMaster = _persistence.create(pk);

		Assert.assertNotNull(stMSupplementalDiscMaster);

		Assert.assertEquals(stMSupplementalDiscMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		StMSupplementalDiscMaster newStMSupplementalDiscMaster = addStMSupplementalDiscMaster();

		_persistence.remove(newStMSupplementalDiscMaster);

		StMSupplementalDiscMaster existingStMSupplementalDiscMaster = _persistence.fetchByPrimaryKey(newStMSupplementalDiscMaster.getPrimaryKey());

		Assert.assertNull(existingStMSupplementalDiscMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addStMSupplementalDiscMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		StMSupplementalDiscMasterPK pk = new StMSupplementalDiscMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StMSupplementalDiscMaster newStMSupplementalDiscMaster = _persistence.create(pk);

		newStMSupplementalDiscMaster.setActualDiscountRate2(RandomTestUtil.nextDouble());

		newStMSupplementalDiscMaster.setActualDiscountRate1(RandomTestUtil.nextDouble());

		newStMSupplementalDiscMaster.setMarketType(RandomTestUtil.randomString());

		newStMSupplementalDiscMaster.setActualMethodology(RandomTestUtil.randomString());

		newStMSupplementalDiscMaster.setActualContractPrice(RandomTestUtil.nextDouble());

		newStMSupplementalDiscMaster.setLastModifiedDate(RandomTestUtil.nextDate());

		newStMSupplementalDiscMaster.setActualDiscount(RandomTestUtil.nextDouble());

		newStMSupplementalDiscMaster.setCheckRecord(RandomTestUtil.nextInt());

		newStMSupplementalDiscMaster.setContractEndDate(RandomTestUtil.nextDate());

		_stMSupplementalDiscMasters.add(_persistence.update(
				newStMSupplementalDiscMaster));

		StMSupplementalDiscMaster existingStMSupplementalDiscMaster = _persistence.findByPrimaryKey(newStMSupplementalDiscMaster.getPrimaryKey());

		AssertUtils.assertEquals(existingStMSupplementalDiscMaster.getActualDiscountRate2(),
			newStMSupplementalDiscMaster.getActualDiscountRate2());
		AssertUtils.assertEquals(existingStMSupplementalDiscMaster.getActualDiscountRate1(),
			newStMSupplementalDiscMaster.getActualDiscountRate1());
		Assert.assertEquals(existingStMSupplementalDiscMaster.getMarketType(),
			newStMSupplementalDiscMaster.getMarketType());
		Assert.assertEquals(existingStMSupplementalDiscMaster.getActualMethodology(),
			newStMSupplementalDiscMaster.getActualMethodology());
		AssertUtils.assertEquals(existingStMSupplementalDiscMaster.getActualContractPrice(),
			newStMSupplementalDiscMaster.getActualContractPrice());
		Assert.assertEquals(existingStMSupplementalDiscMaster.getUserId(),
			newStMSupplementalDiscMaster.getUserId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingStMSupplementalDiscMaster.getLastModifiedDate()),
			Time.getShortTimestamp(
				newStMSupplementalDiscMaster.getLastModifiedDate()));
		Assert.assertEquals(existingStMSupplementalDiscMaster.getProjectionDetailsSid(),
			newStMSupplementalDiscMaster.getProjectionDetailsSid());
		AssertUtils.assertEquals(existingStMSupplementalDiscMaster.getActualDiscount(),
			newStMSupplementalDiscMaster.getActualDiscount());
		Assert.assertEquals(existingStMSupplementalDiscMaster.getSessionId(),
			newStMSupplementalDiscMaster.getSessionId());
		Assert.assertEquals(existingStMSupplementalDiscMaster.getCheckRecord(),
			newStMSupplementalDiscMaster.getCheckRecord());
		Assert.assertEquals(Time.getShortTimestamp(
				existingStMSupplementalDiscMaster.getContractEndDate()),
			Time.getShortTimestamp(
				newStMSupplementalDiscMaster.getContractEndDate()));
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		StMSupplementalDiscMaster newStMSupplementalDiscMaster = addStMSupplementalDiscMaster();

		StMSupplementalDiscMaster existingStMSupplementalDiscMaster = _persistence.findByPrimaryKey(newStMSupplementalDiscMaster.getPrimaryKey());

		Assert.assertEquals(existingStMSupplementalDiscMaster,
			newStMSupplementalDiscMaster);
	}

	@Test(expected = NoSuchStMSupplementalDiscMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		StMSupplementalDiscMasterPK pk = new StMSupplementalDiscMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		StMSupplementalDiscMaster newStMSupplementalDiscMaster = addStMSupplementalDiscMaster();

		StMSupplementalDiscMaster existingStMSupplementalDiscMaster = _persistence.fetchByPrimaryKey(newStMSupplementalDiscMaster.getPrimaryKey());

		Assert.assertEquals(existingStMSupplementalDiscMaster,
			newStMSupplementalDiscMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		StMSupplementalDiscMasterPK pk = new StMSupplementalDiscMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StMSupplementalDiscMaster missingStMSupplementalDiscMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingStMSupplementalDiscMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		StMSupplementalDiscMaster newStMSupplementalDiscMaster1 = addStMSupplementalDiscMaster();
		StMSupplementalDiscMaster newStMSupplementalDiscMaster2 = addStMSupplementalDiscMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStMSupplementalDiscMaster1.getPrimaryKey());
		primaryKeys.add(newStMSupplementalDiscMaster2.getPrimaryKey());

		Map<Serializable, StMSupplementalDiscMaster> stMSupplementalDiscMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, stMSupplementalDiscMasters.size());
		Assert.assertEquals(newStMSupplementalDiscMaster1,
			stMSupplementalDiscMasters.get(
				newStMSupplementalDiscMaster1.getPrimaryKey()));
		Assert.assertEquals(newStMSupplementalDiscMaster2,
			stMSupplementalDiscMasters.get(
				newStMSupplementalDiscMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		StMSupplementalDiscMasterPK pk1 = new StMSupplementalDiscMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StMSupplementalDiscMasterPK pk2 = new StMSupplementalDiscMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, StMSupplementalDiscMaster> stMSupplementalDiscMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stMSupplementalDiscMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		StMSupplementalDiscMaster newStMSupplementalDiscMaster = addStMSupplementalDiscMaster();

		StMSupplementalDiscMasterPK pk = new StMSupplementalDiscMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStMSupplementalDiscMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, StMSupplementalDiscMaster> stMSupplementalDiscMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stMSupplementalDiscMasters.size());
		Assert.assertEquals(newStMSupplementalDiscMaster,
			stMSupplementalDiscMasters.get(
				newStMSupplementalDiscMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, StMSupplementalDiscMaster> stMSupplementalDiscMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stMSupplementalDiscMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		StMSupplementalDiscMaster newStMSupplementalDiscMaster = addStMSupplementalDiscMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStMSupplementalDiscMaster.getPrimaryKey());

		Map<Serializable, StMSupplementalDiscMaster> stMSupplementalDiscMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stMSupplementalDiscMasters.size());
		Assert.assertEquals(newStMSupplementalDiscMaster,
			stMSupplementalDiscMasters.get(
				newStMSupplementalDiscMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = StMSupplementalDiscMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<StMSupplementalDiscMaster>() {
				@Override
				public void performAction(
					StMSupplementalDiscMaster stMSupplementalDiscMaster) {
					Assert.assertNotNull(stMSupplementalDiscMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		StMSupplementalDiscMaster newStMSupplementalDiscMaster = addStMSupplementalDiscMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StMSupplementalDiscMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				newStMSupplementalDiscMaster.getUserId()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				newStMSupplementalDiscMaster.getProjectionDetailsSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				newStMSupplementalDiscMaster.getSessionId()));

		List<StMSupplementalDiscMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		StMSupplementalDiscMaster existingStMSupplementalDiscMaster = result.get(0);

		Assert.assertEquals(existingStMSupplementalDiscMaster,
			newStMSupplementalDiscMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StMSupplementalDiscMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				RandomTestUtil.nextInt()));

		List<StMSupplementalDiscMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		StMSupplementalDiscMaster newStMSupplementalDiscMaster = addStMSupplementalDiscMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StMSupplementalDiscMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("id.userId"));

		Object newUserId = newStMSupplementalDiscMaster.getUserId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.userId",
				new Object[] { newUserId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingUserId = result.get(0);

		Assert.assertEquals(existingUserId, newUserId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StMSupplementalDiscMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("id.userId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.userId",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected StMSupplementalDiscMaster addStMSupplementalDiscMaster()
		throws Exception {
		StMSupplementalDiscMasterPK pk = new StMSupplementalDiscMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		StMSupplementalDiscMaster stMSupplementalDiscMaster = _persistence.create(pk);

		stMSupplementalDiscMaster.setActualDiscountRate2(RandomTestUtil.nextDouble());

		stMSupplementalDiscMaster.setActualDiscountRate1(RandomTestUtil.nextDouble());

		stMSupplementalDiscMaster.setMarketType(RandomTestUtil.randomString());

		stMSupplementalDiscMaster.setActualMethodology(RandomTestUtil.randomString());

		stMSupplementalDiscMaster.setActualContractPrice(RandomTestUtil.nextDouble());

		stMSupplementalDiscMaster.setLastModifiedDate(RandomTestUtil.nextDate());

		stMSupplementalDiscMaster.setActualDiscount(RandomTestUtil.nextDouble());

		stMSupplementalDiscMaster.setCheckRecord(RandomTestUtil.nextInt());

		stMSupplementalDiscMaster.setContractEndDate(RandomTestUtil.nextDate());

		_stMSupplementalDiscMasters.add(_persistence.update(
				stMSupplementalDiscMaster));

		return stMSupplementalDiscMaster;
	}

	private List<StMSupplementalDiscMaster> _stMSupplementalDiscMasters = new ArrayList<StMSupplementalDiscMaster>();
	private StMSupplementalDiscMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}