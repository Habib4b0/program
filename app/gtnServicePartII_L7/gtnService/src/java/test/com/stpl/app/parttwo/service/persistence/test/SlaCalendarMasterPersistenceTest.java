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

package com.stpl.app.parttwo.service.persistence.test;

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

import com.stpl.app.parttwo.exception.NoSuchSlaCalendarMasterException;
import com.stpl.app.parttwo.model.SlaCalendarMaster;
import com.stpl.app.parttwo.service.SlaCalendarMasterLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.SlaCalendarMasterPersistence;
import com.stpl.app.parttwo.service.persistence.SlaCalendarMasterUtil;

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
public class SlaCalendarMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = SlaCalendarMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<SlaCalendarMaster> iterator = _slaCalendarMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		SlaCalendarMaster slaCalendarMaster = _persistence.create(pk);

		Assert.assertNotNull(slaCalendarMaster);

		Assert.assertEquals(slaCalendarMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		SlaCalendarMaster newSlaCalendarMaster = addSlaCalendarMaster();

		_persistence.remove(newSlaCalendarMaster);

		SlaCalendarMaster existingSlaCalendarMaster = _persistence.fetchByPrimaryKey(newSlaCalendarMaster.getPrimaryKey());

		Assert.assertNull(existingSlaCalendarMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addSlaCalendarMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		SlaCalendarMaster newSlaCalendarMaster = _persistence.create(pk);

		newSlaCalendarMaster.setCreatedBy(RandomTestUtil.nextInt());

		newSlaCalendarMaster.setModifiedBy(RandomTestUtil.nextInt());

		newSlaCalendarMaster.setCreatedDate(RandomTestUtil.nextDate());

		newSlaCalendarMaster.setDefaultHolidays(RandomTestUtil.randomBoolean());

		newSlaCalendarMaster.setCalendarName(RandomTestUtil.randomString());

		newSlaCalendarMaster.setModifiedDate(RandomTestUtil.nextDate());

		newSlaCalendarMaster.setInboundStatus(RandomTestUtil.randomString());

		_slaCalendarMasters.add(_persistence.update(newSlaCalendarMaster));

		SlaCalendarMaster existingSlaCalendarMaster = _persistence.findByPrimaryKey(newSlaCalendarMaster.getPrimaryKey());

		Assert.assertEquals(existingSlaCalendarMaster.getCreatedBy(),
			newSlaCalendarMaster.getCreatedBy());
		Assert.assertEquals(existingSlaCalendarMaster.getModifiedBy(),
			newSlaCalendarMaster.getModifiedBy());
		Assert.assertEquals(existingSlaCalendarMaster.getSlaCalendarMasterSid(),
			newSlaCalendarMaster.getSlaCalendarMasterSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingSlaCalendarMaster.getCreatedDate()),
			Time.getShortTimestamp(newSlaCalendarMaster.getCreatedDate()));
		Assert.assertEquals(existingSlaCalendarMaster.getDefaultHolidays(),
			newSlaCalendarMaster.getDefaultHolidays());
		Assert.assertEquals(existingSlaCalendarMaster.getCalendarName(),
			newSlaCalendarMaster.getCalendarName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingSlaCalendarMaster.getModifiedDate()),
			Time.getShortTimestamp(newSlaCalendarMaster.getModifiedDate()));
		Assert.assertEquals(existingSlaCalendarMaster.getInboundStatus(),
			newSlaCalendarMaster.getInboundStatus());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		SlaCalendarMaster newSlaCalendarMaster = addSlaCalendarMaster();

		SlaCalendarMaster existingSlaCalendarMaster = _persistence.findByPrimaryKey(newSlaCalendarMaster.getPrimaryKey());

		Assert.assertEquals(existingSlaCalendarMaster, newSlaCalendarMaster);
	}

	@Test(expected = NoSuchSlaCalendarMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<SlaCalendarMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("SLA_CALENDAR_MASTER",
			"createdBy", true, "modifiedBy", true, "slaCalendarMasterSid",
			true, "createdDate", true, "defaultHolidays", true, "calendarName",
			true, "modifiedDate", true, "inboundStatus", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		SlaCalendarMaster newSlaCalendarMaster = addSlaCalendarMaster();

		SlaCalendarMaster existingSlaCalendarMaster = _persistence.fetchByPrimaryKey(newSlaCalendarMaster.getPrimaryKey());

		Assert.assertEquals(existingSlaCalendarMaster, newSlaCalendarMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		SlaCalendarMaster missingSlaCalendarMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingSlaCalendarMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		SlaCalendarMaster newSlaCalendarMaster1 = addSlaCalendarMaster();
		SlaCalendarMaster newSlaCalendarMaster2 = addSlaCalendarMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newSlaCalendarMaster1.getPrimaryKey());
		primaryKeys.add(newSlaCalendarMaster2.getPrimaryKey());

		Map<Serializable, SlaCalendarMaster> slaCalendarMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, slaCalendarMasters.size());
		Assert.assertEquals(newSlaCalendarMaster1,
			slaCalendarMasters.get(newSlaCalendarMaster1.getPrimaryKey()));
		Assert.assertEquals(newSlaCalendarMaster2,
			slaCalendarMasters.get(newSlaCalendarMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, SlaCalendarMaster> slaCalendarMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(slaCalendarMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		SlaCalendarMaster newSlaCalendarMaster = addSlaCalendarMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newSlaCalendarMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, SlaCalendarMaster> slaCalendarMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, slaCalendarMasters.size());
		Assert.assertEquals(newSlaCalendarMaster,
			slaCalendarMasters.get(newSlaCalendarMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, SlaCalendarMaster> slaCalendarMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(slaCalendarMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		SlaCalendarMaster newSlaCalendarMaster = addSlaCalendarMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newSlaCalendarMaster.getPrimaryKey());

		Map<Serializable, SlaCalendarMaster> slaCalendarMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, slaCalendarMasters.size());
		Assert.assertEquals(newSlaCalendarMaster,
			slaCalendarMasters.get(newSlaCalendarMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = SlaCalendarMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<SlaCalendarMaster>() {
				@Override
				public void performAction(SlaCalendarMaster slaCalendarMaster) {
					Assert.assertNotNull(slaCalendarMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		SlaCalendarMaster newSlaCalendarMaster = addSlaCalendarMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SlaCalendarMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("slaCalendarMasterSid",
				newSlaCalendarMaster.getSlaCalendarMasterSid()));

		List<SlaCalendarMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		SlaCalendarMaster existingSlaCalendarMaster = result.get(0);

		Assert.assertEquals(existingSlaCalendarMaster, newSlaCalendarMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SlaCalendarMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("slaCalendarMasterSid",
				RandomTestUtil.nextInt()));

		List<SlaCalendarMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		SlaCalendarMaster newSlaCalendarMaster = addSlaCalendarMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SlaCalendarMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"slaCalendarMasterSid"));

		Object newSlaCalendarMasterSid = newSlaCalendarMaster.getSlaCalendarMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("slaCalendarMasterSid",
				new Object[] { newSlaCalendarMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingSlaCalendarMasterSid = result.get(0);

		Assert.assertEquals(existingSlaCalendarMasterSid,
			newSlaCalendarMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SlaCalendarMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"slaCalendarMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("slaCalendarMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected SlaCalendarMaster addSlaCalendarMaster()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		SlaCalendarMaster slaCalendarMaster = _persistence.create(pk);

		slaCalendarMaster.setCreatedBy(RandomTestUtil.nextInt());

		slaCalendarMaster.setModifiedBy(RandomTestUtil.nextInt());

		slaCalendarMaster.setCreatedDate(RandomTestUtil.nextDate());

		slaCalendarMaster.setDefaultHolidays(RandomTestUtil.randomBoolean());

		slaCalendarMaster.setCalendarName(RandomTestUtil.randomString());

		slaCalendarMaster.setModifiedDate(RandomTestUtil.nextDate());

		slaCalendarMaster.setInboundStatus(RandomTestUtil.randomString());

		_slaCalendarMasters.add(_persistence.update(slaCalendarMaster));

		return slaCalendarMaster;
	}

	private List<SlaCalendarMaster> _slaCalendarMasters = new ArrayList<SlaCalendarMaster>();
	private SlaCalendarMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}