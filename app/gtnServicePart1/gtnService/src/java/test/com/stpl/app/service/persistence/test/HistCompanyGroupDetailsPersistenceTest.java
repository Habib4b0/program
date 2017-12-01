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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchHistCompanyGroupDetailsException;
import com.stpl.app.model.HistCompanyGroupDetails;
import com.stpl.app.service.persistence.HistCompanyGroupDetailsPK;
import com.stpl.app.service.persistence.HistCompanyGroupDetailsPersistence;
import com.stpl.app.service.persistence.HistCompanyGroupDetailsUtil;

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
public class HistCompanyGroupDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = HistCompanyGroupDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<HistCompanyGroupDetails> iterator = _histCompanyGroupDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		HistCompanyGroupDetailsPK pk = new HistCompanyGroupDetailsPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		HistCompanyGroupDetails histCompanyGroupDetails = _persistence.create(pk);

		Assert.assertNotNull(histCompanyGroupDetails);

		Assert.assertEquals(histCompanyGroupDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		HistCompanyGroupDetails newHistCompanyGroupDetails = addHistCompanyGroupDetails();

		_persistence.remove(newHistCompanyGroupDetails);

		HistCompanyGroupDetails existingHistCompanyGroupDetails = _persistence.fetchByPrimaryKey(newHistCompanyGroupDetails.getPrimaryKey());

		Assert.assertNull(existingHistCompanyGroupDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addHistCompanyGroupDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		HistCompanyGroupDetailsPK pk = new HistCompanyGroupDetailsPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		HistCompanyGroupDetails newHistCompanyGroupDetails = _persistence.create(pk);

		newHistCompanyGroupDetails.setCreatedDate(RandomTestUtil.nextDate());

		newHistCompanyGroupDetails.setCreatedBy(RandomTestUtil.nextInt());

		newHistCompanyGroupDetails.setActionDate(RandomTestUtil.nextDate());

		newHistCompanyGroupDetails.setCompanyParentDetailsSid(RandomTestUtil.randomString());

		newHistCompanyGroupDetails.setCompanyTradeclassSid(RandomTestUtil.nextInt());

		newHistCompanyGroupDetails.setCompanyGroupSid(RandomTestUtil.nextInt());

		newHistCompanyGroupDetails.setModifiedBy(RandomTestUtil.nextInt());

		newHistCompanyGroupDetails.setModifiedDate(RandomTestUtil.nextDate());

		newHistCompanyGroupDetails.setCompanyMasterSid(RandomTestUtil.nextInt());

		_histCompanyGroupDetailses.add(_persistence.update(
				newHistCompanyGroupDetails));

		HistCompanyGroupDetails existingHistCompanyGroupDetails = _persistence.findByPrimaryKey(newHistCompanyGroupDetails.getPrimaryKey());

		Assert.assertEquals(Time.getShortTimestamp(
				existingHistCompanyGroupDetails.getCreatedDate()),
			Time.getShortTimestamp(newHistCompanyGroupDetails.getCreatedDate()));
		Assert.assertEquals(existingHistCompanyGroupDetails.getCreatedBy(),
			newHistCompanyGroupDetails.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingHistCompanyGroupDetails.getActionDate()),
			Time.getShortTimestamp(newHistCompanyGroupDetails.getActionDate()));
		Assert.assertEquals(existingHistCompanyGroupDetails.getCompanyParentDetailsSid(),
			newHistCompanyGroupDetails.getCompanyParentDetailsSid());
		Assert.assertEquals(existingHistCompanyGroupDetails.getCompanyTradeclassSid(),
			newHistCompanyGroupDetails.getCompanyTradeclassSid());
		Assert.assertEquals(existingHistCompanyGroupDetails.getActionFlag(),
			newHistCompanyGroupDetails.getActionFlag());
		Assert.assertEquals(existingHistCompanyGroupDetails.getCompanyGroupSid(),
			newHistCompanyGroupDetails.getCompanyGroupSid());
		Assert.assertEquals(existingHistCompanyGroupDetails.getVersionNo(),
			newHistCompanyGroupDetails.getVersionNo());
		Assert.assertEquals(existingHistCompanyGroupDetails.getCompanyGroupDetailsSid(),
			newHistCompanyGroupDetails.getCompanyGroupDetailsSid());
		Assert.assertEquals(existingHistCompanyGroupDetails.getModifiedBy(),
			newHistCompanyGroupDetails.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingHistCompanyGroupDetails.getModifiedDate()),
			Time.getShortTimestamp(newHistCompanyGroupDetails.getModifiedDate()));
		Assert.assertEquals(existingHistCompanyGroupDetails.getCompanyMasterSid(),
			newHistCompanyGroupDetails.getCompanyMasterSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		HistCompanyGroupDetails newHistCompanyGroupDetails = addHistCompanyGroupDetails();

		HistCompanyGroupDetails existingHistCompanyGroupDetails = _persistence.findByPrimaryKey(newHistCompanyGroupDetails.getPrimaryKey());

		Assert.assertEquals(existingHistCompanyGroupDetails,
			newHistCompanyGroupDetails);
	}

	@Test(expected = NoSuchHistCompanyGroupDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		HistCompanyGroupDetailsPK pk = new HistCompanyGroupDetailsPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		HistCompanyGroupDetails newHistCompanyGroupDetails = addHistCompanyGroupDetails();

		HistCompanyGroupDetails existingHistCompanyGroupDetails = _persistence.fetchByPrimaryKey(newHistCompanyGroupDetails.getPrimaryKey());

		Assert.assertEquals(existingHistCompanyGroupDetails,
			newHistCompanyGroupDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		HistCompanyGroupDetailsPK pk = new HistCompanyGroupDetailsPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		HistCompanyGroupDetails missingHistCompanyGroupDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingHistCompanyGroupDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		HistCompanyGroupDetails newHistCompanyGroupDetails1 = addHistCompanyGroupDetails();
		HistCompanyGroupDetails newHistCompanyGroupDetails2 = addHistCompanyGroupDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHistCompanyGroupDetails1.getPrimaryKey());
		primaryKeys.add(newHistCompanyGroupDetails2.getPrimaryKey());

		Map<Serializable, HistCompanyGroupDetails> histCompanyGroupDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, histCompanyGroupDetailses.size());
		Assert.assertEquals(newHistCompanyGroupDetails1,
			histCompanyGroupDetailses.get(
				newHistCompanyGroupDetails1.getPrimaryKey()));
		Assert.assertEquals(newHistCompanyGroupDetails2,
			histCompanyGroupDetailses.get(
				newHistCompanyGroupDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		HistCompanyGroupDetailsPK pk1 = new HistCompanyGroupDetailsPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		HistCompanyGroupDetailsPK pk2 = new HistCompanyGroupDetailsPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, HistCompanyGroupDetails> histCompanyGroupDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(histCompanyGroupDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		HistCompanyGroupDetails newHistCompanyGroupDetails = addHistCompanyGroupDetails();

		HistCompanyGroupDetailsPK pk = new HistCompanyGroupDetailsPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHistCompanyGroupDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, HistCompanyGroupDetails> histCompanyGroupDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, histCompanyGroupDetailses.size());
		Assert.assertEquals(newHistCompanyGroupDetails,
			histCompanyGroupDetailses.get(
				newHistCompanyGroupDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, HistCompanyGroupDetails> histCompanyGroupDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(histCompanyGroupDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		HistCompanyGroupDetails newHistCompanyGroupDetails = addHistCompanyGroupDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHistCompanyGroupDetails.getPrimaryKey());

		Map<Serializable, HistCompanyGroupDetails> histCompanyGroupDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, histCompanyGroupDetailses.size());
		Assert.assertEquals(newHistCompanyGroupDetails,
			histCompanyGroupDetailses.get(
				newHistCompanyGroupDetails.getPrimaryKey()));
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		HistCompanyGroupDetails newHistCompanyGroupDetails = addHistCompanyGroupDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HistCompanyGroupDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.actionFlag",
				newHistCompanyGroupDetails.getActionFlag()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.versionNo",
				newHistCompanyGroupDetails.getVersionNo()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"id.companyGroupDetailsSid",
				newHistCompanyGroupDetails.getCompanyGroupDetailsSid()));

		List<HistCompanyGroupDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		HistCompanyGroupDetails existingHistCompanyGroupDetails = result.get(0);

		Assert.assertEquals(existingHistCompanyGroupDetails,
			newHistCompanyGroupDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HistCompanyGroupDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.actionFlag",
				RandomTestUtil.randomString()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.versionNo",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"id.companyGroupDetailsSid", RandomTestUtil.nextInt()));

		List<HistCompanyGroupDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		HistCompanyGroupDetails newHistCompanyGroupDetails = addHistCompanyGroupDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HistCompanyGroupDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.actionFlag"));

		Object newActionFlag = newHistCompanyGroupDetails.getActionFlag();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.actionFlag",
				new Object[] { newActionFlag }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingActionFlag = result.get(0);

		Assert.assertEquals(existingActionFlag, newActionFlag);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HistCompanyGroupDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.actionFlag"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.actionFlag",
				new Object[] { RandomTestUtil.randomString() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected HistCompanyGroupDetails addHistCompanyGroupDetails()
		throws Exception {
		HistCompanyGroupDetailsPK pk = new HistCompanyGroupDetailsPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		HistCompanyGroupDetails histCompanyGroupDetails = _persistence.create(pk);

		histCompanyGroupDetails.setCreatedDate(RandomTestUtil.nextDate());

		histCompanyGroupDetails.setCreatedBy(RandomTestUtil.nextInt());

		histCompanyGroupDetails.setActionDate(RandomTestUtil.nextDate());

		histCompanyGroupDetails.setCompanyParentDetailsSid(RandomTestUtil.randomString());

		histCompanyGroupDetails.setCompanyTradeclassSid(RandomTestUtil.nextInt());

		histCompanyGroupDetails.setCompanyGroupSid(RandomTestUtil.nextInt());

		histCompanyGroupDetails.setModifiedBy(RandomTestUtil.nextInt());

		histCompanyGroupDetails.setModifiedDate(RandomTestUtil.nextDate());

		histCompanyGroupDetails.setCompanyMasterSid(RandomTestUtil.nextInt());

		_histCompanyGroupDetailses.add(_persistence.update(
				histCompanyGroupDetails));

		return histCompanyGroupDetails;
	}

	private List<HistCompanyGroupDetails> _histCompanyGroupDetailses = new ArrayList<HistCompanyGroupDetails>();
	private HistCompanyGroupDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}