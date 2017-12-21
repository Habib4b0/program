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

import com.stpl.app.parttwo.exception.NoSuchCffCustomViewMasterException;
import com.stpl.app.parttwo.model.CffCustomViewMaster;
import com.stpl.app.parttwo.service.CffCustomViewMasterLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.CffCustomViewMasterPersistence;
import com.stpl.app.parttwo.service.persistence.CffCustomViewMasterUtil;

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
public class CffCustomViewMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = CffCustomViewMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CffCustomViewMaster> iterator = _cffCustomViewMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CffCustomViewMaster cffCustomViewMaster = _persistence.create(pk);

		Assert.assertNotNull(cffCustomViewMaster);

		Assert.assertEquals(cffCustomViewMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CffCustomViewMaster newCffCustomViewMaster = addCffCustomViewMaster();

		_persistence.remove(newCffCustomViewMaster);

		CffCustomViewMaster existingCffCustomViewMaster = _persistence.fetchByPrimaryKey(newCffCustomViewMaster.getPrimaryKey());

		Assert.assertNull(existingCffCustomViewMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCffCustomViewMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CffCustomViewMaster newCffCustomViewMaster = _persistence.create(pk);

		newCffCustomViewMaster.setCreatedDate(RandomTestUtil.nextDate());

		newCffCustomViewMaster.setCreatedBy(RandomTestUtil.nextInt());

		newCffCustomViewMaster.setCffMasterSid(RandomTestUtil.nextInt());

		newCffCustomViewMaster.setModifiedBy(RandomTestUtil.nextInt());

		newCffCustomViewMaster.setModifiedDate(RandomTestUtil.nextDate());

		newCffCustomViewMaster.setViewName(RandomTestUtil.randomString());

		_cffCustomViewMasters.add(_persistence.update(newCffCustomViewMaster));

		CffCustomViewMaster existingCffCustomViewMaster = _persistence.findByPrimaryKey(newCffCustomViewMaster.getPrimaryKey());

		Assert.assertEquals(Time.getShortTimestamp(
				existingCffCustomViewMaster.getCreatedDate()),
			Time.getShortTimestamp(newCffCustomViewMaster.getCreatedDate()));
		Assert.assertEquals(existingCffCustomViewMaster.getCreatedBy(),
			newCffCustomViewMaster.getCreatedBy());
		Assert.assertEquals(existingCffCustomViewMaster.getCffMasterSid(),
			newCffCustomViewMaster.getCffMasterSid());
		Assert.assertEquals(existingCffCustomViewMaster.getModifiedBy(),
			newCffCustomViewMaster.getModifiedBy());
		Assert.assertEquals(existingCffCustomViewMaster.getCffCustomViewMasterSid(),
			newCffCustomViewMaster.getCffCustomViewMasterSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCffCustomViewMaster.getModifiedDate()),
			Time.getShortTimestamp(newCffCustomViewMaster.getModifiedDate()));
		Assert.assertEquals(existingCffCustomViewMaster.getViewName(),
			newCffCustomViewMaster.getViewName());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CffCustomViewMaster newCffCustomViewMaster = addCffCustomViewMaster();

		CffCustomViewMaster existingCffCustomViewMaster = _persistence.findByPrimaryKey(newCffCustomViewMaster.getPrimaryKey());

		Assert.assertEquals(existingCffCustomViewMaster, newCffCustomViewMaster);
	}

	@Test(expected = NoSuchCffCustomViewMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CffCustomViewMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CFF_CUSTOM_VIEW_MASTER",
			"createdDate", true, "createdBy", true, "cffMasterSid", true,
			"modifiedBy", true, "cffCustomViewMasterSid", true, "modifiedDate",
			true, "viewName", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CffCustomViewMaster newCffCustomViewMaster = addCffCustomViewMaster();

		CffCustomViewMaster existingCffCustomViewMaster = _persistence.fetchByPrimaryKey(newCffCustomViewMaster.getPrimaryKey());

		Assert.assertEquals(existingCffCustomViewMaster, newCffCustomViewMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CffCustomViewMaster missingCffCustomViewMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCffCustomViewMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CffCustomViewMaster newCffCustomViewMaster1 = addCffCustomViewMaster();
		CffCustomViewMaster newCffCustomViewMaster2 = addCffCustomViewMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCffCustomViewMaster1.getPrimaryKey());
		primaryKeys.add(newCffCustomViewMaster2.getPrimaryKey());

		Map<Serializable, CffCustomViewMaster> cffCustomViewMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, cffCustomViewMasters.size());
		Assert.assertEquals(newCffCustomViewMaster1,
			cffCustomViewMasters.get(newCffCustomViewMaster1.getPrimaryKey()));
		Assert.assertEquals(newCffCustomViewMaster2,
			cffCustomViewMasters.get(newCffCustomViewMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CffCustomViewMaster> cffCustomViewMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cffCustomViewMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CffCustomViewMaster newCffCustomViewMaster = addCffCustomViewMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCffCustomViewMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CffCustomViewMaster> cffCustomViewMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cffCustomViewMasters.size());
		Assert.assertEquals(newCffCustomViewMaster,
			cffCustomViewMasters.get(newCffCustomViewMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CffCustomViewMaster> cffCustomViewMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cffCustomViewMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CffCustomViewMaster newCffCustomViewMaster = addCffCustomViewMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCffCustomViewMaster.getPrimaryKey());

		Map<Serializable, CffCustomViewMaster> cffCustomViewMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cffCustomViewMasters.size());
		Assert.assertEquals(newCffCustomViewMaster,
			cffCustomViewMasters.get(newCffCustomViewMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CffCustomViewMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CffCustomViewMaster>() {
				@Override
				public void performAction(
					CffCustomViewMaster cffCustomViewMaster) {
					Assert.assertNotNull(cffCustomViewMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CffCustomViewMaster newCffCustomViewMaster = addCffCustomViewMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffCustomViewMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("cffCustomViewMasterSid",
				newCffCustomViewMaster.getCffCustomViewMasterSid()));

		List<CffCustomViewMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CffCustomViewMaster existingCffCustomViewMaster = result.get(0);

		Assert.assertEquals(existingCffCustomViewMaster, newCffCustomViewMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffCustomViewMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("cffCustomViewMasterSid",
				RandomTestUtil.nextInt()));

		List<CffCustomViewMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CffCustomViewMaster newCffCustomViewMaster = addCffCustomViewMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffCustomViewMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"cffCustomViewMasterSid"));

		Object newCffCustomViewMasterSid = newCffCustomViewMaster.getCffCustomViewMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("cffCustomViewMasterSid",
				new Object[] { newCffCustomViewMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCffCustomViewMasterSid = result.get(0);

		Assert.assertEquals(existingCffCustomViewMasterSid,
			newCffCustomViewMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffCustomViewMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"cffCustomViewMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("cffCustomViewMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected CffCustomViewMaster addCffCustomViewMaster()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		CffCustomViewMaster cffCustomViewMaster = _persistence.create(pk);

		cffCustomViewMaster.setCreatedDate(RandomTestUtil.nextDate());

		cffCustomViewMaster.setCreatedBy(RandomTestUtil.nextInt());

		cffCustomViewMaster.setCffMasterSid(RandomTestUtil.nextInt());

		cffCustomViewMaster.setModifiedBy(RandomTestUtil.nextInt());

		cffCustomViewMaster.setModifiedDate(RandomTestUtil.nextDate());

		cffCustomViewMaster.setViewName(RandomTestUtil.randomString());

		_cffCustomViewMasters.add(_persistence.update(cffCustomViewMaster));

		return cffCustomViewMaster;
	}

	private List<CffCustomViewMaster> _cffCustomViewMasters = new ArrayList<CffCustomViewMaster>();
	private CffCustomViewMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}