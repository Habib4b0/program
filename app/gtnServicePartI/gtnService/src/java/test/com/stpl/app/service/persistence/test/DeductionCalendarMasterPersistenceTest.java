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
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchDeductionCalendarMasterException;
import com.stpl.app.model.DeductionCalendarMaster;
import com.stpl.app.service.DeductionCalendarMasterLocalServiceUtil;
import com.stpl.app.service.persistence.DeductionCalendarMasterPersistence;
import com.stpl.app.service.persistence.DeductionCalendarMasterUtil;

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
public class DeductionCalendarMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = DeductionCalendarMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<DeductionCalendarMaster> iterator = _deductionCalendarMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		DeductionCalendarMaster deductionCalendarMaster = _persistence.create(pk);

		Assert.assertNotNull(deductionCalendarMaster);

		Assert.assertEquals(deductionCalendarMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		DeductionCalendarMaster newDeductionCalendarMaster = addDeductionCalendarMaster();

		_persistence.remove(newDeductionCalendarMaster);

		DeductionCalendarMaster existingDeductionCalendarMaster = _persistence.fetchByPrimaryKey(newDeductionCalendarMaster.getPrimaryKey());

		Assert.assertNull(existingDeductionCalendarMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addDeductionCalendarMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		DeductionCalendarMaster newDeductionCalendarMaster = _persistence.create(pk);

		newDeductionCalendarMaster.setCreatedBy(RandomTestUtil.nextInt());

		newDeductionCalendarMaster.setDeductionCalendarNo(RandomTestUtil.randomString());

		newDeductionCalendarMaster.setModifiedBy(RandomTestUtil.nextInt());

		newDeductionCalendarMaster.setCreatedDate(RandomTestUtil.nextDate());

		newDeductionCalendarMaster.setCategory(RandomTestUtil.nextInt());

		newDeductionCalendarMaster.setAdditionalNotes(RandomTestUtil.randomString());

		newDeductionCalendarMaster.setUserId(RandomTestUtil.nextInt());

		newDeductionCalendarMaster.setDeductionCalendarName(RandomTestUtil.randomString());

		newDeductionCalendarMaster.setDeductionCalendarDesc(RandomTestUtil.randomString());

		newDeductionCalendarMaster.setModifiedDate(RandomTestUtil.nextDate());

		newDeductionCalendarMaster.setInboundStatus(RandomTestUtil.randomString());

		_deductionCalendarMasters.add(_persistence.update(
				newDeductionCalendarMaster));

		DeductionCalendarMaster existingDeductionCalendarMaster = _persistence.findByPrimaryKey(newDeductionCalendarMaster.getPrimaryKey());

		Assert.assertEquals(existingDeductionCalendarMaster.getCreatedBy(),
			newDeductionCalendarMaster.getCreatedBy());
		Assert.assertEquals(existingDeductionCalendarMaster.getDeductionCalendarMasterSid(),
			newDeductionCalendarMaster.getDeductionCalendarMasterSid());
		Assert.assertEquals(existingDeductionCalendarMaster.getDeductionCalendarNo(),
			newDeductionCalendarMaster.getDeductionCalendarNo());
		Assert.assertEquals(existingDeductionCalendarMaster.getModifiedBy(),
			newDeductionCalendarMaster.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingDeductionCalendarMaster.getCreatedDate()),
			Time.getShortTimestamp(newDeductionCalendarMaster.getCreatedDate()));
		Assert.assertEquals(existingDeductionCalendarMaster.getCategory(),
			newDeductionCalendarMaster.getCategory());
		Assert.assertEquals(existingDeductionCalendarMaster.getAdditionalNotes(),
			newDeductionCalendarMaster.getAdditionalNotes());
		Assert.assertEquals(existingDeductionCalendarMaster.getUserId(),
			newDeductionCalendarMaster.getUserId());
		Assert.assertEquals(existingDeductionCalendarMaster.getDeductionCalendarName(),
			newDeductionCalendarMaster.getDeductionCalendarName());
		Assert.assertEquals(existingDeductionCalendarMaster.getDeductionCalendarDesc(),
			newDeductionCalendarMaster.getDeductionCalendarDesc());
		Assert.assertEquals(Time.getShortTimestamp(
				existingDeductionCalendarMaster.getModifiedDate()),
			Time.getShortTimestamp(newDeductionCalendarMaster.getModifiedDate()));
		Assert.assertEquals(existingDeductionCalendarMaster.getInboundStatus(),
			newDeductionCalendarMaster.getInboundStatus());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		DeductionCalendarMaster newDeductionCalendarMaster = addDeductionCalendarMaster();

		DeductionCalendarMaster existingDeductionCalendarMaster = _persistence.findByPrimaryKey(newDeductionCalendarMaster.getPrimaryKey());

		Assert.assertEquals(existingDeductionCalendarMaster,
			newDeductionCalendarMaster);
	}

	@Test(expected = NoSuchDeductionCalendarMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<DeductionCalendarMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("DEDUCTION_CALENDAR_MASTER",
			"createdBy", true, "deductionCalendarMasterSid", true,
			"deductionCalendarNo", true, "modifiedBy", true, "createdDate",
			true, "category", true, "additionalNotes", true, "userId", true,
			"deductionCalendarName", true, "deductionCalendarDesc", true,
			"modifiedDate", true, "inboundStatus", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		DeductionCalendarMaster newDeductionCalendarMaster = addDeductionCalendarMaster();

		DeductionCalendarMaster existingDeductionCalendarMaster = _persistence.fetchByPrimaryKey(newDeductionCalendarMaster.getPrimaryKey());

		Assert.assertEquals(existingDeductionCalendarMaster,
			newDeductionCalendarMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		DeductionCalendarMaster missingDeductionCalendarMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingDeductionCalendarMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		DeductionCalendarMaster newDeductionCalendarMaster1 = addDeductionCalendarMaster();
		DeductionCalendarMaster newDeductionCalendarMaster2 = addDeductionCalendarMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDeductionCalendarMaster1.getPrimaryKey());
		primaryKeys.add(newDeductionCalendarMaster2.getPrimaryKey());

		Map<Serializable, DeductionCalendarMaster> deductionCalendarMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, deductionCalendarMasters.size());
		Assert.assertEquals(newDeductionCalendarMaster1,
			deductionCalendarMasters.get(
				newDeductionCalendarMaster1.getPrimaryKey()));
		Assert.assertEquals(newDeductionCalendarMaster2,
			deductionCalendarMasters.get(
				newDeductionCalendarMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, DeductionCalendarMaster> deductionCalendarMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(deductionCalendarMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		DeductionCalendarMaster newDeductionCalendarMaster = addDeductionCalendarMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDeductionCalendarMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, DeductionCalendarMaster> deductionCalendarMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, deductionCalendarMasters.size());
		Assert.assertEquals(newDeductionCalendarMaster,
			deductionCalendarMasters.get(
				newDeductionCalendarMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, DeductionCalendarMaster> deductionCalendarMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(deductionCalendarMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		DeductionCalendarMaster newDeductionCalendarMaster = addDeductionCalendarMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDeductionCalendarMaster.getPrimaryKey());

		Map<Serializable, DeductionCalendarMaster> deductionCalendarMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, deductionCalendarMasters.size());
		Assert.assertEquals(newDeductionCalendarMaster,
			deductionCalendarMasters.get(
				newDeductionCalendarMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = DeductionCalendarMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<DeductionCalendarMaster>() {
				@Override
				public void performAction(
					DeductionCalendarMaster deductionCalendarMaster) {
					Assert.assertNotNull(deductionCalendarMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		DeductionCalendarMaster newDeductionCalendarMaster = addDeductionCalendarMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DeductionCalendarMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"deductionCalendarMasterSid",
				newDeductionCalendarMaster.getDeductionCalendarMasterSid()));

		List<DeductionCalendarMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		DeductionCalendarMaster existingDeductionCalendarMaster = result.get(0);

		Assert.assertEquals(existingDeductionCalendarMaster,
			newDeductionCalendarMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DeductionCalendarMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"deductionCalendarMasterSid", RandomTestUtil.nextInt()));

		List<DeductionCalendarMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		DeductionCalendarMaster newDeductionCalendarMaster = addDeductionCalendarMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DeductionCalendarMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"deductionCalendarMasterSid"));

		Object newDeductionCalendarMasterSid = newDeductionCalendarMaster.getDeductionCalendarMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"deductionCalendarMasterSid",
				new Object[] { newDeductionCalendarMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingDeductionCalendarMasterSid = result.get(0);

		Assert.assertEquals(existingDeductionCalendarMasterSid,
			newDeductionCalendarMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DeductionCalendarMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"deductionCalendarMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"deductionCalendarMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected DeductionCalendarMaster addDeductionCalendarMaster()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		DeductionCalendarMaster deductionCalendarMaster = _persistence.create(pk);

		deductionCalendarMaster.setCreatedBy(RandomTestUtil.nextInt());

		deductionCalendarMaster.setDeductionCalendarNo(RandomTestUtil.randomString());

		deductionCalendarMaster.setModifiedBy(RandomTestUtil.nextInt());

		deductionCalendarMaster.setCreatedDate(RandomTestUtil.nextDate());

		deductionCalendarMaster.setCategory(RandomTestUtil.nextInt());

		deductionCalendarMaster.setAdditionalNotes(RandomTestUtil.randomString());

		deductionCalendarMaster.setUserId(RandomTestUtil.nextInt());

		deductionCalendarMaster.setDeductionCalendarName(RandomTestUtil.randomString());

		deductionCalendarMaster.setDeductionCalendarDesc(RandomTestUtil.randomString());

		deductionCalendarMaster.setModifiedDate(RandomTestUtil.nextDate());

		deductionCalendarMaster.setInboundStatus(RandomTestUtil.randomString());

		_deductionCalendarMasters.add(_persistence.update(
				deductionCalendarMaster));

		return deductionCalendarMaster;
	}

	private List<DeductionCalendarMaster> _deductionCalendarMasters = new ArrayList<DeductionCalendarMaster>();
	private DeductionCalendarMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}