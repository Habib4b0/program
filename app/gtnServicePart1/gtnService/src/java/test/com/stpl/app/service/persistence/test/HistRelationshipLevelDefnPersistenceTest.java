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

import com.stpl.app.exception.NoSuchHistRelationshipLevelDefnException;
import com.stpl.app.model.HistRelationshipLevelDefn;
import com.stpl.app.service.HistRelationshipLevelDefnLocalServiceUtil;
import com.stpl.app.service.persistence.HistRelationshipLevelDefnPK;
import com.stpl.app.service.persistence.HistRelationshipLevelDefnPersistence;
import com.stpl.app.service.persistence.HistRelationshipLevelDefnUtil;

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
public class HistRelationshipLevelDefnPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = HistRelationshipLevelDefnUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<HistRelationshipLevelDefn> iterator = _histRelationshipLevelDefns.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		HistRelationshipLevelDefnPK pk = new HistRelationshipLevelDefnPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		HistRelationshipLevelDefn histRelationshipLevelDefn = _persistence.create(pk);

		Assert.assertNotNull(histRelationshipLevelDefn);

		Assert.assertEquals(histRelationshipLevelDefn.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		HistRelationshipLevelDefn newHistRelationshipLevelDefn = addHistRelationshipLevelDefn();

		_persistence.remove(newHistRelationshipLevelDefn);

		HistRelationshipLevelDefn existingHistRelationshipLevelDefn = _persistence.fetchByPrimaryKey(newHistRelationshipLevelDefn.getPrimaryKey());

		Assert.assertNull(existingHistRelationshipLevelDefn);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addHistRelationshipLevelDefn();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		HistRelationshipLevelDefnPK pk = new HistRelationshipLevelDefnPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		HistRelationshipLevelDefn newHistRelationshipLevelDefn = _persistence.create(pk);

		newHistRelationshipLevelDefn.setRelationshipLevelValues(RandomTestUtil.randomString());

		newHistRelationshipLevelDefn.setActionDate(RandomTestUtil.nextDate());

		newHistRelationshipLevelDefn.setHierarchyLevelDefinitionSid(RandomTestUtil.nextInt());

		newHistRelationshipLevelDefn.setParentNode(RandomTestUtil.randomString());

		newHistRelationshipLevelDefn.setRelationshipBuilderSid(RandomTestUtil.nextInt());

		newHistRelationshipLevelDefn.setModifiedDate(RandomTestUtil.nextDate());

		newHistRelationshipLevelDefn.setCreatedBy(RandomTestUtil.nextInt());

		newHistRelationshipLevelDefn.setCreatedDate(RandomTestUtil.nextDate());

		newHistRelationshipLevelDefn.setLevelNo(RandomTestUtil.randomString());

		newHistRelationshipLevelDefn.setHierarchyNo(RandomTestUtil.randomString());

		newHistRelationshipLevelDefn.setModifiedBy(RandomTestUtil.nextInt());

		newHistRelationshipLevelDefn.setFlag(RandomTestUtil.randomString());

		newHistRelationshipLevelDefn.setLevelName(RandomTestUtil.randomString());

		_histRelationshipLevelDefns.add(_persistence.update(
				newHistRelationshipLevelDefn));

		HistRelationshipLevelDefn existingHistRelationshipLevelDefn = _persistence.findByPrimaryKey(newHistRelationshipLevelDefn.getPrimaryKey());

		Assert.assertEquals(existingHistRelationshipLevelDefn.getRelationshipLevelValues(),
			newHistRelationshipLevelDefn.getRelationshipLevelValues());
		Assert.assertEquals(Time.getShortTimestamp(
				existingHistRelationshipLevelDefn.getActionDate()),
			Time.getShortTimestamp(newHistRelationshipLevelDefn.getActionDate()));
		Assert.assertEquals(existingHistRelationshipLevelDefn.getHierarchyLevelDefinitionSid(),
			newHistRelationshipLevelDefn.getHierarchyLevelDefinitionSid());
		Assert.assertEquals(existingHistRelationshipLevelDefn.getParentNode(),
			newHistRelationshipLevelDefn.getParentNode());
		Assert.assertEquals(existingHistRelationshipLevelDefn.getVersionNo(),
			newHistRelationshipLevelDefn.getVersionNo());
		Assert.assertEquals(existingHistRelationshipLevelDefn.getRelationshipBuilderSid(),
			newHistRelationshipLevelDefn.getRelationshipBuilderSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingHistRelationshipLevelDefn.getModifiedDate()),
			Time.getShortTimestamp(
				newHistRelationshipLevelDefn.getModifiedDate()));
		Assert.assertEquals(existingHistRelationshipLevelDefn.getCreatedBy(),
			newHistRelationshipLevelDefn.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingHistRelationshipLevelDefn.getCreatedDate()),
			Time.getShortTimestamp(
				newHistRelationshipLevelDefn.getCreatedDate()));
		Assert.assertEquals(existingHistRelationshipLevelDefn.getLevelNo(),
			newHistRelationshipLevelDefn.getLevelNo());
		Assert.assertEquals(existingHistRelationshipLevelDefn.getActionFlag(),
			newHistRelationshipLevelDefn.getActionFlag());
		Assert.assertEquals(existingHistRelationshipLevelDefn.getHierarchyNo(),
			newHistRelationshipLevelDefn.getHierarchyNo());
		Assert.assertEquals(existingHistRelationshipLevelDefn.getModifiedBy(),
			newHistRelationshipLevelDefn.getModifiedBy());
		Assert.assertEquals(existingHistRelationshipLevelDefn.getRelationshipLevelSid(),
			newHistRelationshipLevelDefn.getRelationshipLevelSid());
		Assert.assertEquals(existingHistRelationshipLevelDefn.getFlag(),
			newHistRelationshipLevelDefn.getFlag());
		Assert.assertEquals(existingHistRelationshipLevelDefn.getLevelName(),
			newHistRelationshipLevelDefn.getLevelName());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		HistRelationshipLevelDefn newHistRelationshipLevelDefn = addHistRelationshipLevelDefn();

		HistRelationshipLevelDefn existingHistRelationshipLevelDefn = _persistence.findByPrimaryKey(newHistRelationshipLevelDefn.getPrimaryKey());

		Assert.assertEquals(existingHistRelationshipLevelDefn,
			newHistRelationshipLevelDefn);
	}

	@Test(expected = NoSuchHistRelationshipLevelDefnException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		HistRelationshipLevelDefnPK pk = new HistRelationshipLevelDefnPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		HistRelationshipLevelDefn newHistRelationshipLevelDefn = addHistRelationshipLevelDefn();

		HistRelationshipLevelDefn existingHistRelationshipLevelDefn = _persistence.fetchByPrimaryKey(newHistRelationshipLevelDefn.getPrimaryKey());

		Assert.assertEquals(existingHistRelationshipLevelDefn,
			newHistRelationshipLevelDefn);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		HistRelationshipLevelDefnPK pk = new HistRelationshipLevelDefnPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		HistRelationshipLevelDefn missingHistRelationshipLevelDefn = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingHistRelationshipLevelDefn);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		HistRelationshipLevelDefn newHistRelationshipLevelDefn1 = addHistRelationshipLevelDefn();
		HistRelationshipLevelDefn newHistRelationshipLevelDefn2 = addHistRelationshipLevelDefn();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHistRelationshipLevelDefn1.getPrimaryKey());
		primaryKeys.add(newHistRelationshipLevelDefn2.getPrimaryKey());

		Map<Serializable, HistRelationshipLevelDefn> histRelationshipLevelDefns = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, histRelationshipLevelDefns.size());
		Assert.assertEquals(newHistRelationshipLevelDefn1,
			histRelationshipLevelDefns.get(
				newHistRelationshipLevelDefn1.getPrimaryKey()));
		Assert.assertEquals(newHistRelationshipLevelDefn2,
			histRelationshipLevelDefns.get(
				newHistRelationshipLevelDefn2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		HistRelationshipLevelDefnPK pk1 = new HistRelationshipLevelDefnPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		HistRelationshipLevelDefnPK pk2 = new HistRelationshipLevelDefnPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, HistRelationshipLevelDefn> histRelationshipLevelDefns = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(histRelationshipLevelDefns.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		HistRelationshipLevelDefn newHistRelationshipLevelDefn = addHistRelationshipLevelDefn();

		HistRelationshipLevelDefnPK pk = new HistRelationshipLevelDefnPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHistRelationshipLevelDefn.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, HistRelationshipLevelDefn> histRelationshipLevelDefns = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, histRelationshipLevelDefns.size());
		Assert.assertEquals(newHistRelationshipLevelDefn,
			histRelationshipLevelDefns.get(
				newHistRelationshipLevelDefn.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, HistRelationshipLevelDefn> histRelationshipLevelDefns = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(histRelationshipLevelDefns.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		HistRelationshipLevelDefn newHistRelationshipLevelDefn = addHistRelationshipLevelDefn();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHistRelationshipLevelDefn.getPrimaryKey());

		Map<Serializable, HistRelationshipLevelDefn> histRelationshipLevelDefns = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, histRelationshipLevelDefns.size());
		Assert.assertEquals(newHistRelationshipLevelDefn,
			histRelationshipLevelDefns.get(
				newHistRelationshipLevelDefn.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = HistRelationshipLevelDefnLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<HistRelationshipLevelDefn>() {
				@Override
				public void performAction(
					HistRelationshipLevelDefn histRelationshipLevelDefn) {
					Assert.assertNotNull(histRelationshipLevelDefn);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		HistRelationshipLevelDefn newHistRelationshipLevelDefn = addHistRelationshipLevelDefn();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HistRelationshipLevelDefn.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.versionNo",
				newHistRelationshipLevelDefn.getVersionNo()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.actionFlag",
				newHistRelationshipLevelDefn.getActionFlag()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.relationshipLevelSid",
				newHistRelationshipLevelDefn.getRelationshipLevelSid()));

		List<HistRelationshipLevelDefn> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		HistRelationshipLevelDefn existingHistRelationshipLevelDefn = result.get(0);

		Assert.assertEquals(existingHistRelationshipLevelDefn,
			newHistRelationshipLevelDefn);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HistRelationshipLevelDefn.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.versionNo",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.actionFlag",
				RandomTestUtil.randomString()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.relationshipLevelSid",
				RandomTestUtil.nextInt()));

		List<HistRelationshipLevelDefn> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		HistRelationshipLevelDefn newHistRelationshipLevelDefn = addHistRelationshipLevelDefn();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HistRelationshipLevelDefn.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.versionNo"));

		Object newVersionNo = newHistRelationshipLevelDefn.getVersionNo();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.versionNo",
				new Object[] { newVersionNo }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingVersionNo = result.get(0);

		Assert.assertEquals(existingVersionNo, newVersionNo);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HistRelationshipLevelDefn.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.versionNo"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.versionNo",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected HistRelationshipLevelDefn addHistRelationshipLevelDefn()
		throws Exception {
		HistRelationshipLevelDefnPK pk = new HistRelationshipLevelDefnPK(RandomTestUtil.nextInt(),
				RandomTestUtil.randomString(), RandomTestUtil.nextInt());

		HistRelationshipLevelDefn histRelationshipLevelDefn = _persistence.create(pk);

		histRelationshipLevelDefn.setRelationshipLevelValues(RandomTestUtil.randomString());

		histRelationshipLevelDefn.setActionDate(RandomTestUtil.nextDate());

		histRelationshipLevelDefn.setHierarchyLevelDefinitionSid(RandomTestUtil.nextInt());

		histRelationshipLevelDefn.setParentNode(RandomTestUtil.randomString());

		histRelationshipLevelDefn.setRelationshipBuilderSid(RandomTestUtil.nextInt());

		histRelationshipLevelDefn.setModifiedDate(RandomTestUtil.nextDate());

		histRelationshipLevelDefn.setCreatedBy(RandomTestUtil.nextInt());

		histRelationshipLevelDefn.setCreatedDate(RandomTestUtil.nextDate());

		histRelationshipLevelDefn.setLevelNo(RandomTestUtil.randomString());

		histRelationshipLevelDefn.setHierarchyNo(RandomTestUtil.randomString());

		histRelationshipLevelDefn.setModifiedBy(RandomTestUtil.nextInt());

		histRelationshipLevelDefn.setFlag(RandomTestUtil.randomString());

		histRelationshipLevelDefn.setLevelName(RandomTestUtil.randomString());

		_histRelationshipLevelDefns.add(_persistence.update(
				histRelationshipLevelDefn));

		return histRelationshipLevelDefn;
	}

	private List<HistRelationshipLevelDefn> _histRelationshipLevelDefns = new ArrayList<HistRelationshipLevelDefn>();
	private HistRelationshipLevelDefnPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}