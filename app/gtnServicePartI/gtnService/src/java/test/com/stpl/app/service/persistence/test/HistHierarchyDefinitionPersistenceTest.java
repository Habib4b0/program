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

import com.stpl.app.exception.NoSuchHistHierarchyDefinitionException;
import com.stpl.app.model.HistHierarchyDefinition;
import com.stpl.app.service.persistence.HistHierarchyDefinitionPK;
import com.stpl.app.service.persistence.HistHierarchyDefinitionPersistence;
import com.stpl.app.service.persistence.HistHierarchyDefinitionUtil;

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
public class HistHierarchyDefinitionPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = HistHierarchyDefinitionUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<HistHierarchyDefinition> iterator = _histHierarchyDefinitions.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		HistHierarchyDefinitionPK pk = new HistHierarchyDefinitionPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		HistHierarchyDefinition histHierarchyDefinition = _persistence.create(pk);

		Assert.assertNotNull(histHierarchyDefinition);

		Assert.assertEquals(histHierarchyDefinition.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		HistHierarchyDefinition newHistHierarchyDefinition = addHistHierarchyDefinition();

		_persistence.remove(newHistHierarchyDefinition);

		HistHierarchyDefinition existingHistHierarchyDefinition = _persistence.fetchByPrimaryKey(newHistHierarchyDefinition.getPrimaryKey());

		Assert.assertNull(existingHistHierarchyDefinition);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addHistHierarchyDefinition();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		HistHierarchyDefinitionPK pk = new HistHierarchyDefinitionPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		HistHierarchyDefinition newHistHierarchyDefinition = _persistence.create(pk);

		newHistHierarchyDefinition.setNoOfLevels(RandomTestUtil.nextInt());

		newHistHierarchyDefinition.setCreatedBy(RandomTestUtil.nextInt());

		newHistHierarchyDefinition.setActionDate(RandomTestUtil.nextDate());

		newHistHierarchyDefinition.setModifiedBy(RandomTestUtil.nextInt());

		newHistHierarchyDefinition.setCreatedDate(RandomTestUtil.nextDate());

		newHistHierarchyDefinition.setHierarchyType(RandomTestUtil.nextInt());

		newHistHierarchyDefinition.setHierarchyCategory(RandomTestUtil.nextInt());

		newHistHierarchyDefinition.setHierarchyName(RandomTestUtil.randomString());

		newHistHierarchyDefinition.setModifiedDate(RandomTestUtil.nextDate());

		_histHierarchyDefinitions.add(_persistence.update(
				newHistHierarchyDefinition));

		HistHierarchyDefinition existingHistHierarchyDefinition = _persistence.findByPrimaryKey(newHistHierarchyDefinition.getPrimaryKey());

		Assert.assertEquals(existingHistHierarchyDefinition.getNoOfLevels(),
			newHistHierarchyDefinition.getNoOfLevels());
		Assert.assertEquals(existingHistHierarchyDefinition.getCreatedBy(),
			newHistHierarchyDefinition.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingHistHierarchyDefinition.getActionDate()),
			Time.getShortTimestamp(newHistHierarchyDefinition.getActionDate()));
		Assert.assertEquals(existingHistHierarchyDefinition.getActionFlag(),
			newHistHierarchyDefinition.getActionFlag());
		Assert.assertEquals(existingHistHierarchyDefinition.getModifiedBy(),
			newHistHierarchyDefinition.getModifiedBy());
		Assert.assertEquals(existingHistHierarchyDefinition.getHierarchyDefinitionSid(),
			newHistHierarchyDefinition.getHierarchyDefinitionSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingHistHierarchyDefinition.getCreatedDate()),
			Time.getShortTimestamp(newHistHierarchyDefinition.getCreatedDate()));
		Assert.assertEquals(existingHistHierarchyDefinition.getHierarchyType(),
			newHistHierarchyDefinition.getHierarchyType());
		Assert.assertEquals(existingHistHierarchyDefinition.getHierarchyCategory(),
			newHistHierarchyDefinition.getHierarchyCategory());
		Assert.assertEquals(existingHistHierarchyDefinition.getHierarchyName(),
			newHistHierarchyDefinition.getHierarchyName());
		Assert.assertEquals(existingHistHierarchyDefinition.getVersionNo(),
			newHistHierarchyDefinition.getVersionNo());
		Assert.assertEquals(Time.getShortTimestamp(
				existingHistHierarchyDefinition.getModifiedDate()),
			Time.getShortTimestamp(newHistHierarchyDefinition.getModifiedDate()));
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		HistHierarchyDefinition newHistHierarchyDefinition = addHistHierarchyDefinition();

		HistHierarchyDefinition existingHistHierarchyDefinition = _persistence.findByPrimaryKey(newHistHierarchyDefinition.getPrimaryKey());

		Assert.assertEquals(existingHistHierarchyDefinition,
			newHistHierarchyDefinition);
	}

	@Test(expected = NoSuchHistHierarchyDefinitionException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		HistHierarchyDefinitionPK pk = new HistHierarchyDefinitionPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		HistHierarchyDefinition newHistHierarchyDefinition = addHistHierarchyDefinition();

		HistHierarchyDefinition existingHistHierarchyDefinition = _persistence.fetchByPrimaryKey(newHistHierarchyDefinition.getPrimaryKey());

		Assert.assertEquals(existingHistHierarchyDefinition,
			newHistHierarchyDefinition);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		HistHierarchyDefinitionPK pk = new HistHierarchyDefinitionPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		HistHierarchyDefinition missingHistHierarchyDefinition = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingHistHierarchyDefinition);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		HistHierarchyDefinition newHistHierarchyDefinition1 = addHistHierarchyDefinition();
		HistHierarchyDefinition newHistHierarchyDefinition2 = addHistHierarchyDefinition();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHistHierarchyDefinition1.getPrimaryKey());
		primaryKeys.add(newHistHierarchyDefinition2.getPrimaryKey());

		Map<Serializable, HistHierarchyDefinition> histHierarchyDefinitions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, histHierarchyDefinitions.size());
		Assert.assertEquals(newHistHierarchyDefinition1,
			histHierarchyDefinitions.get(
				newHistHierarchyDefinition1.getPrimaryKey()));
		Assert.assertEquals(newHistHierarchyDefinition2,
			histHierarchyDefinitions.get(
				newHistHierarchyDefinition2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		HistHierarchyDefinitionPK pk1 = new HistHierarchyDefinitionPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		HistHierarchyDefinitionPK pk2 = new HistHierarchyDefinitionPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, HistHierarchyDefinition> histHierarchyDefinitions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(histHierarchyDefinitions.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		HistHierarchyDefinition newHistHierarchyDefinition = addHistHierarchyDefinition();

		HistHierarchyDefinitionPK pk = new HistHierarchyDefinitionPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHistHierarchyDefinition.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, HistHierarchyDefinition> histHierarchyDefinitions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, histHierarchyDefinitions.size());
		Assert.assertEquals(newHistHierarchyDefinition,
			histHierarchyDefinitions.get(
				newHistHierarchyDefinition.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, HistHierarchyDefinition> histHierarchyDefinitions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(histHierarchyDefinitions.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		HistHierarchyDefinition newHistHierarchyDefinition = addHistHierarchyDefinition();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHistHierarchyDefinition.getPrimaryKey());

		Map<Serializable, HistHierarchyDefinition> histHierarchyDefinitions = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, histHierarchyDefinitions.size());
		Assert.assertEquals(newHistHierarchyDefinition,
			histHierarchyDefinitions.get(
				newHistHierarchyDefinition.getPrimaryKey()));
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		HistHierarchyDefinition newHistHierarchyDefinition = addHistHierarchyDefinition();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HistHierarchyDefinition.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.actionFlag",
				newHistHierarchyDefinition.getActionFlag()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"id.hierarchyDefinitionSid",
				newHistHierarchyDefinition.getHierarchyDefinitionSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.versionNo",
				newHistHierarchyDefinition.getVersionNo()));

		List<HistHierarchyDefinition> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		HistHierarchyDefinition existingHistHierarchyDefinition = result.get(0);

		Assert.assertEquals(existingHistHierarchyDefinition,
			newHistHierarchyDefinition);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HistHierarchyDefinition.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.actionFlag",
				RandomTestUtil.randomString()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"id.hierarchyDefinitionSid", RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.versionNo",
				RandomTestUtil.nextInt()));

		List<HistHierarchyDefinition> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		HistHierarchyDefinition newHistHierarchyDefinition = addHistHierarchyDefinition();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HistHierarchyDefinition.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.actionFlag"));

		Object newActionFlag = newHistHierarchyDefinition.getActionFlag();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.actionFlag",
				new Object[] { newActionFlag }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingActionFlag = result.get(0);

		Assert.assertEquals(existingActionFlag, newActionFlag);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HistHierarchyDefinition.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.actionFlag"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.actionFlag",
				new Object[] { RandomTestUtil.randomString() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected HistHierarchyDefinition addHistHierarchyDefinition()
		throws Exception {
		HistHierarchyDefinitionPK pk = new HistHierarchyDefinitionPK(RandomTestUtil.randomString(),
				RandomTestUtil.nextInt(), RandomTestUtil.nextInt());

		HistHierarchyDefinition histHierarchyDefinition = _persistence.create(pk);

		histHierarchyDefinition.setNoOfLevels(RandomTestUtil.nextInt());

		histHierarchyDefinition.setCreatedBy(RandomTestUtil.nextInt());

		histHierarchyDefinition.setActionDate(RandomTestUtil.nextDate());

		histHierarchyDefinition.setModifiedBy(RandomTestUtil.nextInt());

		histHierarchyDefinition.setCreatedDate(RandomTestUtil.nextDate());

		histHierarchyDefinition.setHierarchyType(RandomTestUtil.nextInt());

		histHierarchyDefinition.setHierarchyCategory(RandomTestUtil.nextInt());

		histHierarchyDefinition.setHierarchyName(RandomTestUtil.randomString());

		histHierarchyDefinition.setModifiedDate(RandomTestUtil.nextDate());

		_histHierarchyDefinitions.add(_persistence.update(
				histHierarchyDefinition));

		return histHierarchyDefinition;
	}

	private List<HistHierarchyDefinition> _histHierarchyDefinitions = new ArrayList<HistHierarchyDefinition>();
	private HistHierarchyDefinitionPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}