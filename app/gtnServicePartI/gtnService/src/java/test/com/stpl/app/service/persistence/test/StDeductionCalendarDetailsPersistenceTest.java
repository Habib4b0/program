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
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchStDeductionCalendarDetailsException;
import com.stpl.app.model.StDeductionCalendarDetails;
import com.stpl.app.service.StDeductionCalendarDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.StDeductionCalendarDetailsPK;
import com.stpl.app.service.persistence.StDeductionCalendarDetailsPersistence;
import com.stpl.app.service.persistence.StDeductionCalendarDetailsUtil;

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
public class StDeductionCalendarDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = StDeductionCalendarDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<StDeductionCalendarDetails> iterator = _stDeductionCalendarDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		StDeductionCalendarDetailsPK pk = new StDeductionCalendarDetailsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString());

		StDeductionCalendarDetails stDeductionCalendarDetails = _persistence.create(pk);

		Assert.assertNotNull(stDeductionCalendarDetails);

		Assert.assertEquals(stDeductionCalendarDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		StDeductionCalendarDetails newStDeductionCalendarDetails = addStDeductionCalendarDetails();

		_persistence.remove(newStDeductionCalendarDetails);

		StDeductionCalendarDetails existingStDeductionCalendarDetails = _persistence.fetchByPrimaryKey(newStDeductionCalendarDetails.getPrimaryKey());

		Assert.assertNull(existingStDeductionCalendarDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addStDeductionCalendarDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		StDeductionCalendarDetailsPK pk = new StDeductionCalendarDetailsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString());

		StDeductionCalendarDetails newStDeductionCalendarDetails = _persistence.create(pk);

		newStDeductionCalendarDetails.setAdjustmentBasis(RandomTestUtil.randomString());

		newStDeductionCalendarDetails.setAdjustmentValue(RandomTestUtil.randomString());

		newStDeductionCalendarDetails.setAdjustmentAllocationMethodology(RandomTestUtil.randomString());

		newStDeductionCalendarDetails.setDiscountAmount(RandomTestUtil.nextInt());

		newStDeductionCalendarDetails.setAdjustmentVariable(RandomTestUtil.randomString());

		newStDeductionCalendarDetails.setAdjustmentType(RandomTestUtil.randomString());

		newStDeductionCalendarDetails.setCheckRecord(RandomTestUtil.randomBoolean());

		_stDeductionCalendarDetailses.add(_persistence.update(
				newStDeductionCalendarDetails));

		StDeductionCalendarDetails existingStDeductionCalendarDetails = _persistence.findByPrimaryKey(newStDeductionCalendarDetails.getPrimaryKey());

		Assert.assertEquals(existingStDeductionCalendarDetails.getAdjustmentBasis(),
			newStDeductionCalendarDetails.getAdjustmentBasis());
		Assert.assertEquals(existingStDeductionCalendarDetails.getPeriodSid(),
			newStDeductionCalendarDetails.getPeriodSid());
		Assert.assertEquals(existingStDeductionCalendarDetails.getAdjustmentValue(),
			newStDeductionCalendarDetails.getAdjustmentValue());
		Assert.assertEquals(existingStDeductionCalendarDetails.getAdjustmentAllocationMethodology(),
			newStDeductionCalendarDetails.getAdjustmentAllocationMethodology());
		Assert.assertEquals(existingStDeductionCalendarDetails.getCompanyMasterSid(),
			newStDeductionCalendarDetails.getCompanyMasterSid());
		Assert.assertEquals(existingStDeductionCalendarDetails.getDiscountAmount(),
			newStDeductionCalendarDetails.getDiscountAmount());
		Assert.assertEquals(existingStDeductionCalendarDetails.getAdjustmentVariable(),
			newStDeductionCalendarDetails.getAdjustmentVariable());
		Assert.assertEquals(existingStDeductionCalendarDetails.getUserId(),
			newStDeductionCalendarDetails.getUserId());
		Assert.assertEquals(existingStDeductionCalendarDetails.getItemMasterSid(),
			newStDeductionCalendarDetails.getItemMasterSid());
		Assert.assertEquals(existingStDeductionCalendarDetails.getAdjustmentType(),
			newStDeductionCalendarDetails.getAdjustmentType());
		Assert.assertEquals(existingStDeductionCalendarDetails.getSessionId(),
			newStDeductionCalendarDetails.getSessionId());
		Assert.assertEquals(existingStDeductionCalendarDetails.getCheckRecord(),
			newStDeductionCalendarDetails.getCheckRecord());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		StDeductionCalendarDetails newStDeductionCalendarDetails = addStDeductionCalendarDetails();

		StDeductionCalendarDetails existingStDeductionCalendarDetails = _persistence.findByPrimaryKey(newStDeductionCalendarDetails.getPrimaryKey());

		Assert.assertEquals(existingStDeductionCalendarDetails,
			newStDeductionCalendarDetails);
	}

	@Test(expected = NoSuchStDeductionCalendarDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		StDeductionCalendarDetailsPK pk = new StDeductionCalendarDetailsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		StDeductionCalendarDetails newStDeductionCalendarDetails = addStDeductionCalendarDetails();

		StDeductionCalendarDetails existingStDeductionCalendarDetails = _persistence.fetchByPrimaryKey(newStDeductionCalendarDetails.getPrimaryKey());

		Assert.assertEquals(existingStDeductionCalendarDetails,
			newStDeductionCalendarDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		StDeductionCalendarDetailsPK pk = new StDeductionCalendarDetailsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString());

		StDeductionCalendarDetails missingStDeductionCalendarDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingStDeductionCalendarDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		StDeductionCalendarDetails newStDeductionCalendarDetails1 = addStDeductionCalendarDetails();
		StDeductionCalendarDetails newStDeductionCalendarDetails2 = addStDeductionCalendarDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStDeductionCalendarDetails1.getPrimaryKey());
		primaryKeys.add(newStDeductionCalendarDetails2.getPrimaryKey());

		Map<Serializable, StDeductionCalendarDetails> stDeductionCalendarDetailses =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, stDeductionCalendarDetailses.size());
		Assert.assertEquals(newStDeductionCalendarDetails1,
			stDeductionCalendarDetailses.get(
				newStDeductionCalendarDetails1.getPrimaryKey()));
		Assert.assertEquals(newStDeductionCalendarDetails2,
			stDeductionCalendarDetailses.get(
				newStDeductionCalendarDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		StDeductionCalendarDetailsPK pk1 = new StDeductionCalendarDetailsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString());

		StDeductionCalendarDetailsPK pk2 = new StDeductionCalendarDetailsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, StDeductionCalendarDetails> stDeductionCalendarDetailses =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stDeductionCalendarDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		StDeductionCalendarDetails newStDeductionCalendarDetails = addStDeductionCalendarDetails();

		StDeductionCalendarDetailsPK pk = new StDeductionCalendarDetailsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStDeductionCalendarDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, StDeductionCalendarDetails> stDeductionCalendarDetailses =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stDeductionCalendarDetailses.size());
		Assert.assertEquals(newStDeductionCalendarDetails,
			stDeductionCalendarDetailses.get(
				newStDeductionCalendarDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, StDeductionCalendarDetails> stDeductionCalendarDetailses =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(stDeductionCalendarDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		StDeductionCalendarDetails newStDeductionCalendarDetails = addStDeductionCalendarDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newStDeductionCalendarDetails.getPrimaryKey());

		Map<Serializable, StDeductionCalendarDetails> stDeductionCalendarDetailses =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, stDeductionCalendarDetailses.size());
		Assert.assertEquals(newStDeductionCalendarDetails,
			stDeductionCalendarDetailses.get(
				newStDeductionCalendarDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = StDeductionCalendarDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<StDeductionCalendarDetails>() {
				@Override
				public void performAction(
					StDeductionCalendarDetails stDeductionCalendarDetails) {
					Assert.assertNotNull(stDeductionCalendarDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		StDeductionCalendarDetails newStDeductionCalendarDetails = addStDeductionCalendarDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StDeductionCalendarDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				newStDeductionCalendarDetails.getPeriodSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.companyMasterSid",
				newStDeductionCalendarDetails.getCompanyMasterSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				newStDeductionCalendarDetails.getUserId()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.itemMasterSid",
				newStDeductionCalendarDetails.getItemMasterSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				newStDeductionCalendarDetails.getSessionId()));

		List<StDeductionCalendarDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		StDeductionCalendarDetails existingStDeductionCalendarDetails = result.get(0);

		Assert.assertEquals(existingStDeductionCalendarDetails,
			newStDeductionCalendarDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StDeductionCalendarDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.periodSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.companyMasterSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.userId",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.itemMasterSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.sessionId",
				RandomTestUtil.randomString()));

		List<StDeductionCalendarDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		StDeductionCalendarDetails newStDeductionCalendarDetails = addStDeductionCalendarDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StDeductionCalendarDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		Object newPeriodSid = newStDeductionCalendarDetails.getPeriodSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { newPeriodSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingPeriodSid = result.get(0);

		Assert.assertEquals(existingPeriodSid, newPeriodSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(StDeductionCalendarDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.periodSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.periodSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected StDeductionCalendarDetails addStDeductionCalendarDetails()
		throws Exception {
		StDeductionCalendarDetailsPK pk = new StDeductionCalendarDetailsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString());

		StDeductionCalendarDetails stDeductionCalendarDetails = _persistence.create(pk);

		stDeductionCalendarDetails.setAdjustmentBasis(RandomTestUtil.randomString());

		stDeductionCalendarDetails.setAdjustmentValue(RandomTestUtil.randomString());

		stDeductionCalendarDetails.setAdjustmentAllocationMethodology(RandomTestUtil.randomString());

		stDeductionCalendarDetails.setDiscountAmount(RandomTestUtil.nextInt());

		stDeductionCalendarDetails.setAdjustmentVariable(RandomTestUtil.randomString());

		stDeductionCalendarDetails.setAdjustmentType(RandomTestUtil.randomString());

		stDeductionCalendarDetails.setCheckRecord(RandomTestUtil.randomBoolean());

		_stDeductionCalendarDetailses.add(_persistence.update(
				stDeductionCalendarDetails));

		return stDeductionCalendarDetails;
	}

	private List<StDeductionCalendarDetails> _stDeductionCalendarDetailses = new ArrayList<StDeductionCalendarDetails>();
	private StDeductionCalendarDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}