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

import com.stpl.app.exception.NoSuchHistHierarchyLevelDefnException;
import com.stpl.app.model.HistHierarchyLevelDefn;
import com.stpl.app.service.HistHierarchyLevelDefnLocalServiceUtil;
import com.stpl.app.service.persistence.HistHierarchyLevelDefnPK;
import com.stpl.app.service.persistence.HistHierarchyLevelDefnPersistence;
import com.stpl.app.service.persistence.HistHierarchyLevelDefnUtil;

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
public class HistHierarchyLevelDefnPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = HistHierarchyLevelDefnUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<HistHierarchyLevelDefn> iterator = _histHierarchyLevelDefns.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		HistHierarchyLevelDefnPK pk = new HistHierarchyLevelDefnPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString());

		HistHierarchyLevelDefn histHierarchyLevelDefn = _persistence.create(pk);

		Assert.assertNotNull(histHierarchyLevelDefn);

		Assert.assertEquals(histHierarchyLevelDefn.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		HistHierarchyLevelDefn newHistHierarchyLevelDefn = addHistHierarchyLevelDefn();

		_persistence.remove(newHistHierarchyLevelDefn);

		HistHierarchyLevelDefn existingHistHierarchyLevelDefn = _persistence.fetchByPrimaryKey(newHistHierarchyLevelDefn.getPrimaryKey());

		Assert.assertNull(existingHistHierarchyLevelDefn);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addHistHierarchyLevelDefn();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		HistHierarchyLevelDefnPK pk = new HistHierarchyLevelDefnPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString());

		HistHierarchyLevelDefn newHistHierarchyLevelDefn = _persistence.create(pk);

		newHistHierarchyLevelDefn.setTableName(RandomTestUtil.randomString());

		newHistHierarchyLevelDefn.setActionDate(RandomTestUtil.nextDate());

		newHistHierarchyLevelDefn.setFieldName(RandomTestUtil.randomString());

		newHistHierarchyLevelDefn.setModifiedDate(RandomTestUtil.nextDate());

		newHistHierarchyLevelDefn.setCreatedBy(RandomTestUtil.nextInt());

		newHistHierarchyLevelDefn.setCreatedDate(RandomTestUtil.nextDate());

		newHistHierarchyLevelDefn.setLevelValueReference(RandomTestUtil.randomString());

		newHistHierarchyLevelDefn.setLevelNo(RandomTestUtil.nextInt());

		newHistHierarchyLevelDefn.setHierarchyDefinitionSid(RandomTestUtil.nextInt());

		newHistHierarchyLevelDefn.setModifiedBy(RandomTestUtil.nextInt());

		newHistHierarchyLevelDefn.setLevelName(RandomTestUtil.randomString());

		_histHierarchyLevelDefns.add(_persistence.update(
				newHistHierarchyLevelDefn));

		HistHierarchyLevelDefn existingHistHierarchyLevelDefn = _persistence.findByPrimaryKey(newHistHierarchyLevelDefn.getPrimaryKey());

		Assert.assertEquals(existingHistHierarchyLevelDefn.getTableName(),
			newHistHierarchyLevelDefn.getTableName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingHistHierarchyLevelDefn.getActionDate()),
			Time.getShortTimestamp(newHistHierarchyLevelDefn.getActionDate()));
		Assert.assertEquals(existingHistHierarchyLevelDefn.getFieldName(),
			newHistHierarchyLevelDefn.getFieldName());
		Assert.assertEquals(existingHistHierarchyLevelDefn.getHierarchyLevelDefinitionSid(),
			newHistHierarchyLevelDefn.getHierarchyLevelDefinitionSid());
		Assert.assertEquals(existingHistHierarchyLevelDefn.getVersionNo(),
			newHistHierarchyLevelDefn.getVersionNo());
		Assert.assertEquals(Time.getShortTimestamp(
				existingHistHierarchyLevelDefn.getModifiedDate()),
			Time.getShortTimestamp(newHistHierarchyLevelDefn.getModifiedDate()));
		Assert.assertEquals(existingHistHierarchyLevelDefn.getCreatedBy(),
			newHistHierarchyLevelDefn.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingHistHierarchyLevelDefn.getCreatedDate()),
			Time.getShortTimestamp(newHistHierarchyLevelDefn.getCreatedDate()));
		Assert.assertEquals(existingHistHierarchyLevelDefn.getLevelValueReference(),
			newHistHierarchyLevelDefn.getLevelValueReference());
		Assert.assertEquals(existingHistHierarchyLevelDefn.getLevelNo(),
			newHistHierarchyLevelDefn.getLevelNo());
		Assert.assertEquals(existingHistHierarchyLevelDefn.getActionFlag(),
			newHistHierarchyLevelDefn.getActionFlag());
		Assert.assertEquals(existingHistHierarchyLevelDefn.getHierarchyDefinitionSid(),
			newHistHierarchyLevelDefn.getHierarchyDefinitionSid());
		Assert.assertEquals(existingHistHierarchyLevelDefn.getModifiedBy(),
			newHistHierarchyLevelDefn.getModifiedBy());
		Assert.assertEquals(existingHistHierarchyLevelDefn.getLevelName(),
			newHistHierarchyLevelDefn.getLevelName());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		HistHierarchyLevelDefn newHistHierarchyLevelDefn = addHistHierarchyLevelDefn();

		HistHierarchyLevelDefn existingHistHierarchyLevelDefn = _persistence.findByPrimaryKey(newHistHierarchyLevelDefn.getPrimaryKey());

		Assert.assertEquals(existingHistHierarchyLevelDefn,
			newHistHierarchyLevelDefn);
	}

	@Test(expected = NoSuchHistHierarchyLevelDefnException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		HistHierarchyLevelDefnPK pk = new HistHierarchyLevelDefnPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		HistHierarchyLevelDefn newHistHierarchyLevelDefn = addHistHierarchyLevelDefn();

		HistHierarchyLevelDefn existingHistHierarchyLevelDefn = _persistence.fetchByPrimaryKey(newHistHierarchyLevelDefn.getPrimaryKey());

		Assert.assertEquals(existingHistHierarchyLevelDefn,
			newHistHierarchyLevelDefn);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		HistHierarchyLevelDefnPK pk = new HistHierarchyLevelDefnPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString());

		HistHierarchyLevelDefn missingHistHierarchyLevelDefn = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingHistHierarchyLevelDefn);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		HistHierarchyLevelDefn newHistHierarchyLevelDefn1 = addHistHierarchyLevelDefn();
		HistHierarchyLevelDefn newHistHierarchyLevelDefn2 = addHistHierarchyLevelDefn();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHistHierarchyLevelDefn1.getPrimaryKey());
		primaryKeys.add(newHistHierarchyLevelDefn2.getPrimaryKey());

		Map<Serializable, HistHierarchyLevelDefn> histHierarchyLevelDefns = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, histHierarchyLevelDefns.size());
		Assert.assertEquals(newHistHierarchyLevelDefn1,
			histHierarchyLevelDefns.get(
				newHistHierarchyLevelDefn1.getPrimaryKey()));
		Assert.assertEquals(newHistHierarchyLevelDefn2,
			histHierarchyLevelDefns.get(
				newHistHierarchyLevelDefn2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		HistHierarchyLevelDefnPK pk1 = new HistHierarchyLevelDefnPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString());

		HistHierarchyLevelDefnPK pk2 = new HistHierarchyLevelDefnPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, HistHierarchyLevelDefn> histHierarchyLevelDefns = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(histHierarchyLevelDefns.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		HistHierarchyLevelDefn newHistHierarchyLevelDefn = addHistHierarchyLevelDefn();

		HistHierarchyLevelDefnPK pk = new HistHierarchyLevelDefnPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHistHierarchyLevelDefn.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, HistHierarchyLevelDefn> histHierarchyLevelDefns = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, histHierarchyLevelDefns.size());
		Assert.assertEquals(newHistHierarchyLevelDefn,
			histHierarchyLevelDefns.get(
				newHistHierarchyLevelDefn.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, HistHierarchyLevelDefn> histHierarchyLevelDefns = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(histHierarchyLevelDefns.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		HistHierarchyLevelDefn newHistHierarchyLevelDefn = addHistHierarchyLevelDefn();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHistHierarchyLevelDefn.getPrimaryKey());

		Map<Serializable, HistHierarchyLevelDefn> histHierarchyLevelDefns = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, histHierarchyLevelDefns.size());
		Assert.assertEquals(newHistHierarchyLevelDefn,
			histHierarchyLevelDefns.get(
				newHistHierarchyLevelDefn.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = HistHierarchyLevelDefnLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<HistHierarchyLevelDefn>() {
				@Override
				public void performAction(
					HistHierarchyLevelDefn histHierarchyLevelDefn) {
					Assert.assertNotNull(histHierarchyLevelDefn);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		HistHierarchyLevelDefn newHistHierarchyLevelDefn = addHistHierarchyLevelDefn();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HistHierarchyLevelDefn.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"id.hierarchyLevelDefinitionSid",
				newHistHierarchyLevelDefn.getHierarchyLevelDefinitionSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.versionNo",
				newHistHierarchyLevelDefn.getVersionNo()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.actionFlag",
				newHistHierarchyLevelDefn.getActionFlag()));

		List<HistHierarchyLevelDefn> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		HistHierarchyLevelDefn existingHistHierarchyLevelDefn = result.get(0);

		Assert.assertEquals(existingHistHierarchyLevelDefn,
			newHistHierarchyLevelDefn);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HistHierarchyLevelDefn.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"id.hierarchyLevelDefinitionSid", RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.versionNo",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.actionFlag",
				RandomTestUtil.randomString()));

		List<HistHierarchyLevelDefn> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		HistHierarchyLevelDefn newHistHierarchyLevelDefn = addHistHierarchyLevelDefn();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HistHierarchyLevelDefn.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.hierarchyLevelDefinitionSid"));

		Object newHierarchyLevelDefinitionSid = newHistHierarchyLevelDefn.getHierarchyLevelDefinitionSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"id.hierarchyLevelDefinitionSid",
				new Object[] { newHierarchyLevelDefinitionSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingHierarchyLevelDefinitionSid = result.get(0);

		Assert.assertEquals(existingHierarchyLevelDefinitionSid,
			newHierarchyLevelDefinitionSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HistHierarchyLevelDefn.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.hierarchyLevelDefinitionSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"id.hierarchyLevelDefinitionSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected HistHierarchyLevelDefn addHistHierarchyLevelDefn()
		throws Exception {
		HistHierarchyLevelDefnPK pk = new HistHierarchyLevelDefnPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt(), RandomTestUtil.randomString());

		HistHierarchyLevelDefn histHierarchyLevelDefn = _persistence.create(pk);

		histHierarchyLevelDefn.setTableName(RandomTestUtil.randomString());

		histHierarchyLevelDefn.setActionDate(RandomTestUtil.nextDate());

		histHierarchyLevelDefn.setFieldName(RandomTestUtil.randomString());

		histHierarchyLevelDefn.setModifiedDate(RandomTestUtil.nextDate());

		histHierarchyLevelDefn.setCreatedBy(RandomTestUtil.nextInt());

		histHierarchyLevelDefn.setCreatedDate(RandomTestUtil.nextDate());

		histHierarchyLevelDefn.setLevelValueReference(RandomTestUtil.randomString());

		histHierarchyLevelDefn.setLevelNo(RandomTestUtil.nextInt());

		histHierarchyLevelDefn.setHierarchyDefinitionSid(RandomTestUtil.nextInt());

		histHierarchyLevelDefn.setModifiedBy(RandomTestUtil.nextInt());

		histHierarchyLevelDefn.setLevelName(RandomTestUtil.randomString());

		_histHierarchyLevelDefns.add(_persistence.update(histHierarchyLevelDefn));

		return histHierarchyLevelDefn;
	}

	private List<HistHierarchyLevelDefn> _histHierarchyLevelDefns = new ArrayList<HistHierarchyLevelDefn>();
	private HistHierarchyLevelDefnPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}