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

import com.stpl.app.parttwo.exception.NoSuchSlaCalendarDetailsException;
import com.stpl.app.parttwo.model.SlaCalendarDetails;
import com.stpl.app.parttwo.service.SlaCalendarDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.SlaCalendarDetailsPersistence;
import com.stpl.app.parttwo.service.persistence.SlaCalendarDetailsUtil;

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
public class SlaCalendarDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = SlaCalendarDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<SlaCalendarDetails> iterator = _slaCalendarDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		SlaCalendarDetails slaCalendarDetails = _persistence.create(pk);

		Assert.assertNotNull(slaCalendarDetails);

		Assert.assertEquals(slaCalendarDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		SlaCalendarDetails newSlaCalendarDetails = addSlaCalendarDetails();

		_persistence.remove(newSlaCalendarDetails);

		SlaCalendarDetails existingSlaCalendarDetails = _persistence.fetchByPrimaryKey(newSlaCalendarDetails.getPrimaryKey());

		Assert.assertNull(existingSlaCalendarDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addSlaCalendarDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		SlaCalendarDetails newSlaCalendarDetails = _persistence.create(pk);

		newSlaCalendarDetails.setCreatedDate(RandomTestUtil.nextDate());

		newSlaCalendarDetails.setCreatedBy(RandomTestUtil.nextInt());

		newSlaCalendarDetails.setSlaCalendarMasterSid(RandomTestUtil.nextInt());

		newSlaCalendarDetails.setHolidayYear(RandomTestUtil.randomString());

		newSlaCalendarDetails.setModifiedBy(RandomTestUtil.nextInt());

		newSlaCalendarDetails.setInboundStatus(RandomTestUtil.randomString());

		newSlaCalendarDetails.setHolidayDay(RandomTestUtil.randomString());

		newSlaCalendarDetails.setModifiedDate(RandomTestUtil.nextDate());

		newSlaCalendarDetails.setHolidayCombined(RandomTestUtil.nextDate());

		newSlaCalendarDetails.setHolidayMonth(RandomTestUtil.randomString());

		_slaCalendarDetailses.add(_persistence.update(newSlaCalendarDetails));

		SlaCalendarDetails existingSlaCalendarDetails = _persistence.findByPrimaryKey(newSlaCalendarDetails.getPrimaryKey());

		Assert.assertEquals(Time.getShortTimestamp(
				existingSlaCalendarDetails.getCreatedDate()),
			Time.getShortTimestamp(newSlaCalendarDetails.getCreatedDate()));
		Assert.assertEquals(existingSlaCalendarDetails.getCreatedBy(),
			newSlaCalendarDetails.getCreatedBy());
		Assert.assertEquals(existingSlaCalendarDetails.getSlaCalendarMasterSid(),
			newSlaCalendarDetails.getSlaCalendarMasterSid());
		Assert.assertEquals(existingSlaCalendarDetails.getHolidayYear(),
			newSlaCalendarDetails.getHolidayYear());
		Assert.assertEquals(existingSlaCalendarDetails.getSlaCalendarDetailsSid(),
			newSlaCalendarDetails.getSlaCalendarDetailsSid());
		Assert.assertEquals(existingSlaCalendarDetails.getModifiedBy(),
			newSlaCalendarDetails.getModifiedBy());
		Assert.assertEquals(existingSlaCalendarDetails.getInboundStatus(),
			newSlaCalendarDetails.getInboundStatus());
		Assert.assertEquals(existingSlaCalendarDetails.getHolidayDay(),
			newSlaCalendarDetails.getHolidayDay());
		Assert.assertEquals(Time.getShortTimestamp(
				existingSlaCalendarDetails.getModifiedDate()),
			Time.getShortTimestamp(newSlaCalendarDetails.getModifiedDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingSlaCalendarDetails.getHolidayCombined()),
			Time.getShortTimestamp(newSlaCalendarDetails.getHolidayCombined()));
		Assert.assertEquals(existingSlaCalendarDetails.getHolidayMonth(),
			newSlaCalendarDetails.getHolidayMonth());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		SlaCalendarDetails newSlaCalendarDetails = addSlaCalendarDetails();

		SlaCalendarDetails existingSlaCalendarDetails = _persistence.findByPrimaryKey(newSlaCalendarDetails.getPrimaryKey());

		Assert.assertEquals(existingSlaCalendarDetails, newSlaCalendarDetails);
	}

	@Test(expected = NoSuchSlaCalendarDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<SlaCalendarDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("SLA_CALENDAR_DETAILS",
			"createdDate", true, "createdBy", true, "slaCalendarMasterSid",
			true, "holidayYear", true, "slaCalendarDetailsSid", true,
			"modifiedBy", true, "inboundStatus", true, "holidayDay", true,
			"modifiedDate", true, "holidayCombined", true, "holidayMonth", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		SlaCalendarDetails newSlaCalendarDetails = addSlaCalendarDetails();

		SlaCalendarDetails existingSlaCalendarDetails = _persistence.fetchByPrimaryKey(newSlaCalendarDetails.getPrimaryKey());

		Assert.assertEquals(existingSlaCalendarDetails, newSlaCalendarDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		SlaCalendarDetails missingSlaCalendarDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingSlaCalendarDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		SlaCalendarDetails newSlaCalendarDetails1 = addSlaCalendarDetails();
		SlaCalendarDetails newSlaCalendarDetails2 = addSlaCalendarDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newSlaCalendarDetails1.getPrimaryKey());
		primaryKeys.add(newSlaCalendarDetails2.getPrimaryKey());

		Map<Serializable, SlaCalendarDetails> slaCalendarDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, slaCalendarDetailses.size());
		Assert.assertEquals(newSlaCalendarDetails1,
			slaCalendarDetailses.get(newSlaCalendarDetails1.getPrimaryKey()));
		Assert.assertEquals(newSlaCalendarDetails2,
			slaCalendarDetailses.get(newSlaCalendarDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, SlaCalendarDetails> slaCalendarDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(slaCalendarDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		SlaCalendarDetails newSlaCalendarDetails = addSlaCalendarDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newSlaCalendarDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, SlaCalendarDetails> slaCalendarDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, slaCalendarDetailses.size());
		Assert.assertEquals(newSlaCalendarDetails,
			slaCalendarDetailses.get(newSlaCalendarDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, SlaCalendarDetails> slaCalendarDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(slaCalendarDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		SlaCalendarDetails newSlaCalendarDetails = addSlaCalendarDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newSlaCalendarDetails.getPrimaryKey());

		Map<Serializable, SlaCalendarDetails> slaCalendarDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, slaCalendarDetailses.size());
		Assert.assertEquals(newSlaCalendarDetails,
			slaCalendarDetailses.get(newSlaCalendarDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = SlaCalendarDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<SlaCalendarDetails>() {
				@Override
				public void performAction(SlaCalendarDetails slaCalendarDetails) {
					Assert.assertNotNull(slaCalendarDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		SlaCalendarDetails newSlaCalendarDetails = addSlaCalendarDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SlaCalendarDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("slaCalendarDetailsSid",
				newSlaCalendarDetails.getSlaCalendarDetailsSid()));

		List<SlaCalendarDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		SlaCalendarDetails existingSlaCalendarDetails = result.get(0);

		Assert.assertEquals(existingSlaCalendarDetails, newSlaCalendarDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SlaCalendarDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("slaCalendarDetailsSid",
				RandomTestUtil.nextInt()));

		List<SlaCalendarDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		SlaCalendarDetails newSlaCalendarDetails = addSlaCalendarDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SlaCalendarDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"slaCalendarDetailsSid"));

		Object newSlaCalendarDetailsSid = newSlaCalendarDetails.getSlaCalendarDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("slaCalendarDetailsSid",
				new Object[] { newSlaCalendarDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingSlaCalendarDetailsSid = result.get(0);

		Assert.assertEquals(existingSlaCalendarDetailsSid,
			newSlaCalendarDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SlaCalendarDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"slaCalendarDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("slaCalendarDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected SlaCalendarDetails addSlaCalendarDetails()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		SlaCalendarDetails slaCalendarDetails = _persistence.create(pk);

		slaCalendarDetails.setCreatedDate(RandomTestUtil.nextDate());

		slaCalendarDetails.setCreatedBy(RandomTestUtil.nextInt());

		slaCalendarDetails.setSlaCalendarMasterSid(RandomTestUtil.nextInt());

		slaCalendarDetails.setHolidayYear(RandomTestUtil.randomString());

		slaCalendarDetails.setModifiedBy(RandomTestUtil.nextInt());

		slaCalendarDetails.setInboundStatus(RandomTestUtil.randomString());

		slaCalendarDetails.setHolidayDay(RandomTestUtil.randomString());

		slaCalendarDetails.setModifiedDate(RandomTestUtil.nextDate());

		slaCalendarDetails.setHolidayCombined(RandomTestUtil.nextDate());

		slaCalendarDetails.setHolidayMonth(RandomTestUtil.randomString());

		_slaCalendarDetailses.add(_persistence.update(slaCalendarDetails));

		return slaCalendarDetails;
	}

	private List<SlaCalendarDetails> _slaCalendarDetailses = new ArrayList<SlaCalendarDetails>();
	private SlaCalendarDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}