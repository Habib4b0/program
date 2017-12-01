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

import com.stpl.app.exception.NoSuchHistItemGroupDetailsException;
import com.stpl.app.model.HistItemGroupDetails;
import com.stpl.app.service.HistItemGroupDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.HistItemGroupDetailsPK;
import com.stpl.app.service.persistence.HistItemGroupDetailsPersistence;
import com.stpl.app.service.persistence.HistItemGroupDetailsUtil;

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
public class HistItemGroupDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = HistItemGroupDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<HistItemGroupDetails> iterator = _histItemGroupDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		HistItemGroupDetailsPK pk = new HistItemGroupDetailsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		HistItemGroupDetails histItemGroupDetails = _persistence.create(pk);

		Assert.assertNotNull(histItemGroupDetails);

		Assert.assertEquals(histItemGroupDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		HistItemGroupDetails newHistItemGroupDetails = addHistItemGroupDetails();

		_persistence.remove(newHistItemGroupDetails);

		HistItemGroupDetails existingHistItemGroupDetails = _persistence.fetchByPrimaryKey(newHistItemGroupDetails.getPrimaryKey());

		Assert.assertNull(existingHistItemGroupDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addHistItemGroupDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		HistItemGroupDetailsPK pk = new HistItemGroupDetailsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		HistItemGroupDetails newHistItemGroupDetails = _persistence.create(pk);

		newHistItemGroupDetails.setCreatedDate(RandomTestUtil.nextDate());

		newHistItemGroupDetails.setCreatedBy(RandomTestUtil.nextInt());

		newHistItemGroupDetails.setActionDate(RandomTestUtil.nextDate());

		newHistItemGroupDetails.setItemMasterSid(RandomTestUtil.nextInt());

		newHistItemGroupDetails.setModifiedBy(RandomTestUtil.nextInt());

		newHistItemGroupDetails.setModifiedDate(RandomTestUtil.nextDate());

		newHistItemGroupDetails.setItemGroupSid(RandomTestUtil.nextInt());

		_histItemGroupDetailses.add(_persistence.update(newHistItemGroupDetails));

		HistItemGroupDetails existingHistItemGroupDetails = _persistence.findByPrimaryKey(newHistItemGroupDetails.getPrimaryKey());

		Assert.assertEquals(existingHistItemGroupDetails.getItemGroupDetailsSid(),
			newHistItemGroupDetails.getItemGroupDetailsSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingHistItemGroupDetails.getCreatedDate()),
			Time.getShortTimestamp(newHistItemGroupDetails.getCreatedDate()));
		Assert.assertEquals(existingHistItemGroupDetails.getCreatedBy(),
			newHistItemGroupDetails.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingHistItemGroupDetails.getActionDate()),
			Time.getShortTimestamp(newHistItemGroupDetails.getActionDate()));
		Assert.assertEquals(existingHistItemGroupDetails.getItemMasterSid(),
			newHistItemGroupDetails.getItemMasterSid());
		Assert.assertEquals(existingHistItemGroupDetails.getActionFlag(),
			newHistItemGroupDetails.getActionFlag());
		Assert.assertEquals(existingHistItemGroupDetails.getVersionNo(),
			newHistItemGroupDetails.getVersionNo());
		Assert.assertEquals(existingHistItemGroupDetails.getModifiedBy(),
			newHistItemGroupDetails.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingHistItemGroupDetails.getModifiedDate()),
			Time.getShortTimestamp(newHistItemGroupDetails.getModifiedDate()));
		Assert.assertEquals(existingHistItemGroupDetails.getItemGroupSid(),
			newHistItemGroupDetails.getItemGroupSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		HistItemGroupDetails newHistItemGroupDetails = addHistItemGroupDetails();

		HistItemGroupDetails existingHistItemGroupDetails = _persistence.findByPrimaryKey(newHistItemGroupDetails.getPrimaryKey());

		Assert.assertEquals(existingHistItemGroupDetails,
			newHistItemGroupDetails);
	}

	@Test(expected = NoSuchHistItemGroupDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		HistItemGroupDetailsPK pk = new HistItemGroupDetailsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		HistItemGroupDetails newHistItemGroupDetails = addHistItemGroupDetails();

		HistItemGroupDetails existingHistItemGroupDetails = _persistence.fetchByPrimaryKey(newHistItemGroupDetails.getPrimaryKey());

		Assert.assertEquals(existingHistItemGroupDetails,
			newHistItemGroupDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		HistItemGroupDetailsPK pk = new HistItemGroupDetailsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		HistItemGroupDetails missingHistItemGroupDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingHistItemGroupDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		HistItemGroupDetails newHistItemGroupDetails1 = addHistItemGroupDetails();
		HistItemGroupDetails newHistItemGroupDetails2 = addHistItemGroupDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHistItemGroupDetails1.getPrimaryKey());
		primaryKeys.add(newHistItemGroupDetails2.getPrimaryKey());

		Map<Serializable, HistItemGroupDetails> histItemGroupDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, histItemGroupDetailses.size());
		Assert.assertEquals(newHistItemGroupDetails1,
			histItemGroupDetailses.get(newHistItemGroupDetails1.getPrimaryKey()));
		Assert.assertEquals(newHistItemGroupDetails2,
			histItemGroupDetailses.get(newHistItemGroupDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		HistItemGroupDetailsPK pk1 = new HistItemGroupDetailsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		HistItemGroupDetailsPK pk2 = new HistItemGroupDetailsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, HistItemGroupDetails> histItemGroupDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(histItemGroupDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		HistItemGroupDetails newHistItemGroupDetails = addHistItemGroupDetails();

		HistItemGroupDetailsPK pk = new HistItemGroupDetailsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHistItemGroupDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, HistItemGroupDetails> histItemGroupDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, histItemGroupDetailses.size());
		Assert.assertEquals(newHistItemGroupDetails,
			histItemGroupDetailses.get(newHistItemGroupDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, HistItemGroupDetails> histItemGroupDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(histItemGroupDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		HistItemGroupDetails newHistItemGroupDetails = addHistItemGroupDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHistItemGroupDetails.getPrimaryKey());

		Map<Serializable, HistItemGroupDetails> histItemGroupDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, histItemGroupDetailses.size());
		Assert.assertEquals(newHistItemGroupDetails,
			histItemGroupDetailses.get(newHistItemGroupDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = HistItemGroupDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<HistItemGroupDetails>() {
				@Override
				public void performAction(
					HistItemGroupDetails histItemGroupDetails) {
					Assert.assertNotNull(histItemGroupDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		HistItemGroupDetails newHistItemGroupDetails = addHistItemGroupDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HistItemGroupDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.itemGroupDetailsSid",
				newHistItemGroupDetails.getItemGroupDetailsSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.actionFlag",
				newHistItemGroupDetails.getActionFlag()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.versionNo",
				newHistItemGroupDetails.getVersionNo()));

		List<HistItemGroupDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		HistItemGroupDetails existingHistItemGroupDetails = result.get(0);

		Assert.assertEquals(existingHistItemGroupDetails,
			newHistItemGroupDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HistItemGroupDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.itemGroupDetailsSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.actionFlag",
				RandomTestUtil.randomString()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.versionNo",
				RandomTestUtil.nextInt()));

		List<HistItemGroupDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		HistItemGroupDetails newHistItemGroupDetails = addHistItemGroupDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HistItemGroupDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.itemGroupDetailsSid"));

		Object newItemGroupDetailsSid = newHistItemGroupDetails.getItemGroupDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.itemGroupDetailsSid",
				new Object[] { newItemGroupDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingItemGroupDetailsSid = result.get(0);

		Assert.assertEquals(existingItemGroupDetailsSid, newItemGroupDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HistItemGroupDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.itemGroupDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.itemGroupDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected HistItemGroupDetails addHistItemGroupDetails()
		throws Exception {
		HistItemGroupDetailsPK pk = new HistItemGroupDetailsPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		HistItemGroupDetails histItemGroupDetails = _persistence.create(pk);

		histItemGroupDetails.setCreatedDate(RandomTestUtil.nextDate());

		histItemGroupDetails.setCreatedBy(RandomTestUtil.nextInt());

		histItemGroupDetails.setActionDate(RandomTestUtil.nextDate());

		histItemGroupDetails.setItemMasterSid(RandomTestUtil.nextInt());

		histItemGroupDetails.setModifiedBy(RandomTestUtil.nextInt());

		histItemGroupDetails.setModifiedDate(RandomTestUtil.nextDate());

		histItemGroupDetails.setItemGroupSid(RandomTestUtil.nextInt());

		_histItemGroupDetailses.add(_persistence.update(histItemGroupDetails));

		return histItemGroupDetails;
	}

	private List<HistItemGroupDetails> _histItemGroupDetailses = new ArrayList<HistItemGroupDetails>();
	private HistItemGroupDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}