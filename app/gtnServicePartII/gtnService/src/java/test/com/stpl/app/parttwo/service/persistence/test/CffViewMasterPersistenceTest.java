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

import com.stpl.app.parttwo.exception.NoSuchCffViewMasterException;
import com.stpl.app.parttwo.model.CffViewMaster;
import com.stpl.app.parttwo.service.CffViewMasterLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.CffViewMasterPersistence;
import com.stpl.app.parttwo.service.persistence.CffViewMasterUtil;

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
public class CffViewMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = CffViewMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CffViewMaster> iterator = _cffViewMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CffViewMaster cffViewMaster = _persistence.create(pk);

		Assert.assertNotNull(cffViewMaster);

		Assert.assertEquals(cffViewMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CffViewMaster newCffViewMaster = addCffViewMaster();

		_persistence.remove(newCffViewMaster);

		CffViewMaster existingCffViewMaster = _persistence.fetchByPrimaryKey(newCffViewMaster.getPrimaryKey());

		Assert.assertNull(existingCffViewMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCffViewMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CffViewMaster newCffViewMaster = _persistence.create(pk);

		newCffViewMaster.setCreatedDate(RandomTestUtil.nextDate());

		newCffViewMaster.setCreatedBy(RandomTestUtil.randomString());

		newCffViewMaster.setViewType(RandomTestUtil.randomString());

		newCffViewMaster.setCffMasterSid(RandomTestUtil.nextInt());

		newCffViewMaster.setModifiedBy(RandomTestUtil.randomString());

		newCffViewMaster.setModifiedDate(RandomTestUtil.nextDate());

		newCffViewMaster.setViewName(RandomTestUtil.randomString());

		_cffViewMasters.add(_persistence.update(newCffViewMaster));

		CffViewMaster existingCffViewMaster = _persistence.findByPrimaryKey(newCffViewMaster.getPrimaryKey());

		Assert.assertEquals(Time.getShortTimestamp(
				existingCffViewMaster.getCreatedDate()),
			Time.getShortTimestamp(newCffViewMaster.getCreatedDate()));
		Assert.assertEquals(existingCffViewMaster.getCreatedBy(),
			newCffViewMaster.getCreatedBy());
		Assert.assertEquals(existingCffViewMaster.getViewType(),
			newCffViewMaster.getViewType());
		Assert.assertEquals(existingCffViewMaster.getCffViewMasterSid(),
			newCffViewMaster.getCffViewMasterSid());
		Assert.assertEquals(existingCffViewMaster.getCffMasterSid(),
			newCffViewMaster.getCffMasterSid());
		Assert.assertEquals(existingCffViewMaster.getModifiedBy(),
			newCffViewMaster.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCffViewMaster.getModifiedDate()),
			Time.getShortTimestamp(newCffViewMaster.getModifiedDate()));
		Assert.assertEquals(existingCffViewMaster.getViewName(),
			newCffViewMaster.getViewName());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CffViewMaster newCffViewMaster = addCffViewMaster();

		CffViewMaster existingCffViewMaster = _persistence.findByPrimaryKey(newCffViewMaster.getPrimaryKey());

		Assert.assertEquals(existingCffViewMaster, newCffViewMaster);
	}

	@Test(expected = NoSuchCffViewMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CffViewMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CFF_VIEW_MASTER",
			"createdDate", true, "createdBy", true, "viewType", true,
			"cffViewMasterSid", true, "cffMasterSid", true, "modifiedBy", true,
			"modifiedDate", true, "viewName", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CffViewMaster newCffViewMaster = addCffViewMaster();

		CffViewMaster existingCffViewMaster = _persistence.fetchByPrimaryKey(newCffViewMaster.getPrimaryKey());

		Assert.assertEquals(existingCffViewMaster, newCffViewMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CffViewMaster missingCffViewMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCffViewMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CffViewMaster newCffViewMaster1 = addCffViewMaster();
		CffViewMaster newCffViewMaster2 = addCffViewMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCffViewMaster1.getPrimaryKey());
		primaryKeys.add(newCffViewMaster2.getPrimaryKey());

		Map<Serializable, CffViewMaster> cffViewMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, cffViewMasters.size());
		Assert.assertEquals(newCffViewMaster1,
			cffViewMasters.get(newCffViewMaster1.getPrimaryKey()));
		Assert.assertEquals(newCffViewMaster2,
			cffViewMasters.get(newCffViewMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CffViewMaster> cffViewMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cffViewMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CffViewMaster newCffViewMaster = addCffViewMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCffViewMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CffViewMaster> cffViewMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cffViewMasters.size());
		Assert.assertEquals(newCffViewMaster,
			cffViewMasters.get(newCffViewMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CffViewMaster> cffViewMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cffViewMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CffViewMaster newCffViewMaster = addCffViewMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCffViewMaster.getPrimaryKey());

		Map<Serializable, CffViewMaster> cffViewMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cffViewMasters.size());
		Assert.assertEquals(newCffViewMaster,
			cffViewMasters.get(newCffViewMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CffViewMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CffViewMaster>() {
				@Override
				public void performAction(CffViewMaster cffViewMaster) {
					Assert.assertNotNull(cffViewMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CffViewMaster newCffViewMaster = addCffViewMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffViewMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("cffViewMasterSid",
				newCffViewMaster.getCffViewMasterSid()));

		List<CffViewMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CffViewMaster existingCffViewMaster = result.get(0);

		Assert.assertEquals(existingCffViewMaster, newCffViewMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffViewMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("cffViewMasterSid",
				RandomTestUtil.nextInt()));

		List<CffViewMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CffViewMaster newCffViewMaster = addCffViewMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffViewMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"cffViewMasterSid"));

		Object newCffViewMasterSid = newCffViewMaster.getCffViewMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("cffViewMasterSid",
				new Object[] { newCffViewMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCffViewMasterSid = result.get(0);

		Assert.assertEquals(existingCffViewMasterSid, newCffViewMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffViewMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"cffViewMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("cffViewMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected CffViewMaster addCffViewMaster() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CffViewMaster cffViewMaster = _persistence.create(pk);

		cffViewMaster.setCreatedDate(RandomTestUtil.nextDate());

		cffViewMaster.setCreatedBy(RandomTestUtil.randomString());

		cffViewMaster.setViewType(RandomTestUtil.randomString());

		cffViewMaster.setCffMasterSid(RandomTestUtil.nextInt());

		cffViewMaster.setModifiedBy(RandomTestUtil.randomString());

		cffViewMaster.setModifiedDate(RandomTestUtil.nextDate());

		cffViewMaster.setViewName(RandomTestUtil.randomString());

		_cffViewMasters.add(_persistence.update(cffViewMaster));

		return cffViewMaster;
	}

	private List<CffViewMaster> _cffViewMasters = new ArrayList<CffViewMaster>();
	private CffViewMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}