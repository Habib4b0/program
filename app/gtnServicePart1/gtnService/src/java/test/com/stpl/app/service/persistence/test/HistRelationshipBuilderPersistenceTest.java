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

import com.stpl.app.exception.NoSuchHistRelationshipBuilderException;
import com.stpl.app.model.HistRelationshipBuilder;
import com.stpl.app.service.persistence.HistRelationshipBuilderPK;
import com.stpl.app.service.persistence.HistRelationshipBuilderPersistence;
import com.stpl.app.service.persistence.HistRelationshipBuilderUtil;

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
public class HistRelationshipBuilderPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = HistRelationshipBuilderUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<HistRelationshipBuilder> iterator = _histRelationshipBuilders.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		HistRelationshipBuilderPK pk = new HistRelationshipBuilderPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		HistRelationshipBuilder histRelationshipBuilder = _persistence.create(pk);

		Assert.assertNotNull(histRelationshipBuilder);

		Assert.assertEquals(histRelationshipBuilder.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		HistRelationshipBuilder newHistRelationshipBuilder = addHistRelationshipBuilder();

		_persistence.remove(newHistRelationshipBuilder);

		HistRelationshipBuilder existingHistRelationshipBuilder = _persistence.fetchByPrimaryKey(newHistRelationshipBuilder.getPrimaryKey());

		Assert.assertNull(existingHistRelationshipBuilder);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addHistRelationshipBuilder();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		HistRelationshipBuilderPK pk = new HistRelationshipBuilderPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		HistRelationshipBuilder newHistRelationshipBuilder = _persistence.create(pk);

		newHistRelationshipBuilder.setStartDate(RandomTestUtil.nextDate());

		newHistRelationshipBuilder.setCreatedDate(RandomTestUtil.nextDate());

		newHistRelationshipBuilder.setCreatedBy(RandomTestUtil.nextInt());

		newHistRelationshipBuilder.setRelationshipDescription(RandomTestUtil.randomString());

		newHistRelationshipBuilder.setActionDate(RandomTestUtil.nextDate());

		newHistRelationshipBuilder.setHierarchyDefinitionSid(RandomTestUtil.nextInt());

		newHistRelationshipBuilder.setRelationshipName(RandomTestUtil.randomString());

		newHistRelationshipBuilder.setHierarchyVersion(RandomTestUtil.nextInt());

		newHistRelationshipBuilder.setModifiedBy(RandomTestUtil.nextInt());

		newHistRelationshipBuilder.setModifiedDate(RandomTestUtil.nextDate());

		newHistRelationshipBuilder.setRelationshipType(RandomTestUtil.nextInt());

		newHistRelationshipBuilder.setBuildType(RandomTestUtil.randomString());

		_histRelationshipBuilders.add(_persistence.update(
				newHistRelationshipBuilder));

		HistRelationshipBuilder existingHistRelationshipBuilder = _persistence.findByPrimaryKey(newHistRelationshipBuilder.getPrimaryKey());

		Assert.assertEquals(Time.getShortTimestamp(
				existingHistRelationshipBuilder.getStartDate()),
			Time.getShortTimestamp(newHistRelationshipBuilder.getStartDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingHistRelationshipBuilder.getCreatedDate()),
			Time.getShortTimestamp(newHistRelationshipBuilder.getCreatedDate()));
		Assert.assertEquals(existingHistRelationshipBuilder.getCreatedBy(),
			newHistRelationshipBuilder.getCreatedBy());
		Assert.assertEquals(existingHistRelationshipBuilder.getRelationshipDescription(),
			newHistRelationshipBuilder.getRelationshipDescription());
		Assert.assertEquals(Time.getShortTimestamp(
				existingHistRelationshipBuilder.getActionDate()),
			Time.getShortTimestamp(newHistRelationshipBuilder.getActionDate()));
		Assert.assertEquals(existingHistRelationshipBuilder.getActionFlag(),
			newHistRelationshipBuilder.getActionFlag());
		Assert.assertEquals(existingHistRelationshipBuilder.getHierarchyDefinitionSid(),
			newHistRelationshipBuilder.getHierarchyDefinitionSid());
		Assert.assertEquals(existingHistRelationshipBuilder.getVersionNo(),
			newHistRelationshipBuilder.getVersionNo());
		Assert.assertEquals(existingHistRelationshipBuilder.getRelationshipName(),
			newHistRelationshipBuilder.getRelationshipName());
		Assert.assertEquals(existingHistRelationshipBuilder.getRelationshipBuilderSid(),
			newHistRelationshipBuilder.getRelationshipBuilderSid());
		Assert.assertEquals(existingHistRelationshipBuilder.getHierarchyVersion(),
			newHistRelationshipBuilder.getHierarchyVersion());
		Assert.assertEquals(existingHistRelationshipBuilder.getModifiedBy(),
			newHistRelationshipBuilder.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingHistRelationshipBuilder.getModifiedDate()),
			Time.getShortTimestamp(newHistRelationshipBuilder.getModifiedDate()));
		Assert.assertEquals(existingHistRelationshipBuilder.getRelationshipType(),
			newHistRelationshipBuilder.getRelationshipType());
		Assert.assertEquals(existingHistRelationshipBuilder.getBuildType(),
			newHistRelationshipBuilder.getBuildType());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		HistRelationshipBuilder newHistRelationshipBuilder = addHistRelationshipBuilder();

		HistRelationshipBuilder existingHistRelationshipBuilder = _persistence.findByPrimaryKey(newHistRelationshipBuilder.getPrimaryKey());

		Assert.assertEquals(existingHistRelationshipBuilder,
			newHistRelationshipBuilder);
	}

	@Test(expected = NoSuchHistRelationshipBuilderException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		HistRelationshipBuilderPK pk = new HistRelationshipBuilderPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		HistRelationshipBuilder newHistRelationshipBuilder = addHistRelationshipBuilder();

		HistRelationshipBuilder existingHistRelationshipBuilder = _persistence.fetchByPrimaryKey(newHistRelationshipBuilder.getPrimaryKey());

		Assert.assertEquals(existingHistRelationshipBuilder,
			newHistRelationshipBuilder);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		HistRelationshipBuilderPK pk = new HistRelationshipBuilderPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		HistRelationshipBuilder missingHistRelationshipBuilder = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingHistRelationshipBuilder);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		HistRelationshipBuilder newHistRelationshipBuilder1 = addHistRelationshipBuilder();
		HistRelationshipBuilder newHistRelationshipBuilder2 = addHistRelationshipBuilder();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHistRelationshipBuilder1.getPrimaryKey());
		primaryKeys.add(newHistRelationshipBuilder2.getPrimaryKey());

		Map<Serializable, HistRelationshipBuilder> histRelationshipBuilders = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, histRelationshipBuilders.size());
		Assert.assertEquals(newHistRelationshipBuilder1,
			histRelationshipBuilders.get(
				newHistRelationshipBuilder1.getPrimaryKey()));
		Assert.assertEquals(newHistRelationshipBuilder2,
			histRelationshipBuilders.get(
				newHistRelationshipBuilder2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		HistRelationshipBuilderPK pk1 = new HistRelationshipBuilderPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		HistRelationshipBuilderPK pk2 = new HistRelationshipBuilderPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, HistRelationshipBuilder> histRelationshipBuilders = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(histRelationshipBuilders.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		HistRelationshipBuilder newHistRelationshipBuilder = addHistRelationshipBuilder();

		HistRelationshipBuilderPK pk = new HistRelationshipBuilderPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHistRelationshipBuilder.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, HistRelationshipBuilder> histRelationshipBuilders = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, histRelationshipBuilders.size());
		Assert.assertEquals(newHistRelationshipBuilder,
			histRelationshipBuilders.get(
				newHistRelationshipBuilder.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, HistRelationshipBuilder> histRelationshipBuilders = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(histRelationshipBuilders.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		HistRelationshipBuilder newHistRelationshipBuilder = addHistRelationshipBuilder();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHistRelationshipBuilder.getPrimaryKey());

		Map<Serializable, HistRelationshipBuilder> histRelationshipBuilders = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, histRelationshipBuilders.size());
		Assert.assertEquals(newHistRelationshipBuilder,
			histRelationshipBuilders.get(
				newHistRelationshipBuilder.getPrimaryKey()));
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		HistRelationshipBuilder newHistRelationshipBuilder = addHistRelationshipBuilder();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HistRelationshipBuilder.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.actionFlag",
				newHistRelationshipBuilder.getActionFlag()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.versionNo",
				newHistRelationshipBuilder.getVersionNo()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"id.relationshipBuilderSid",
				newHistRelationshipBuilder.getRelationshipBuilderSid()));

		List<HistRelationshipBuilder> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		HistRelationshipBuilder existingHistRelationshipBuilder = result.get(0);

		Assert.assertEquals(existingHistRelationshipBuilder,
			newHistRelationshipBuilder);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HistRelationshipBuilder.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.actionFlag",
				RandomTestUtil.randomString()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.versionNo",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"id.relationshipBuilderSid", RandomTestUtil.nextInt()));

		List<HistRelationshipBuilder> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		HistRelationshipBuilder newHistRelationshipBuilder = addHistRelationshipBuilder();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HistRelationshipBuilder.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.actionFlag"));

		Object newActionFlag = newHistRelationshipBuilder.getActionFlag();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.actionFlag",
				new Object[] { newActionFlag }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingActionFlag = result.get(0);

		Assert.assertEquals(existingActionFlag, newActionFlag);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HistRelationshipBuilder.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.actionFlag"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.actionFlag",
				new Object[] { RandomTestUtil.randomString() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected HistRelationshipBuilder addHistRelationshipBuilder()
		throws Exception {
		HistRelationshipBuilderPK pk = new HistRelationshipBuilderPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		HistRelationshipBuilder histRelationshipBuilder = _persistence.create(pk);

		histRelationshipBuilder.setStartDate(RandomTestUtil.nextDate());

		histRelationshipBuilder.setCreatedDate(RandomTestUtil.nextDate());

		histRelationshipBuilder.setCreatedBy(RandomTestUtil.nextInt());

		histRelationshipBuilder.setRelationshipDescription(RandomTestUtil.randomString());

		histRelationshipBuilder.setActionDate(RandomTestUtil.nextDate());

		histRelationshipBuilder.setHierarchyDefinitionSid(RandomTestUtil.nextInt());

		histRelationshipBuilder.setRelationshipName(RandomTestUtil.randomString());

		histRelationshipBuilder.setHierarchyVersion(RandomTestUtil.nextInt());

		histRelationshipBuilder.setModifiedBy(RandomTestUtil.nextInt());

		histRelationshipBuilder.setModifiedDate(RandomTestUtil.nextDate());

		histRelationshipBuilder.setRelationshipType(RandomTestUtil.nextInt());

		histRelationshipBuilder.setBuildType(RandomTestUtil.randomString());

		_histRelationshipBuilders.add(_persistence.update(
				histRelationshipBuilder));

		return histRelationshipBuilder;
	}

	private List<HistRelationshipBuilder> _histRelationshipBuilders = new ArrayList<HistRelationshipBuilder>();
	private HistRelationshipBuilderPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}