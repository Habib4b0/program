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

import com.stpl.app.exception.NoSuchIvldItemHierarchyDefinitionException;
import com.stpl.app.model.IvldItemHierarchyDefinition;
import com.stpl.app.service.IvldItemHierarchyDefinitionLocalServiceUtil;
import com.stpl.app.service.persistence.IvldItemHierarchyDefinitionPersistence;
import com.stpl.app.service.persistence.IvldItemHierarchyDefinitionUtil;

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
public class IvldItemHierarchyDefinitionPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = IvldItemHierarchyDefinitionUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<IvldItemHierarchyDefinition> iterator = _ivldItemHierarchyDefinitions.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldItemHierarchyDefinition ivldItemHierarchyDefinition = _persistence.create(pk);

		Assert.assertNotNull(ivldItemHierarchyDefinition);

		Assert.assertEquals(ivldItemHierarchyDefinition.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		IvldItemHierarchyDefinition newIvldItemHierarchyDefinition = addIvldItemHierarchyDefinition();

		_persistence.remove(newIvldItemHierarchyDefinition);

		IvldItemHierarchyDefinition existingIvldItemHierarchyDefinition = _persistence.fetchByPrimaryKey(newIvldItemHierarchyDefinition.getPrimaryKey());

		Assert.assertNull(existingIvldItemHierarchyDefinition);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addIvldItemHierarchyDefinition();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldItemHierarchyDefinition newIvldItemHierarchyDefinition = _persistence.create(pk);

		newIvldItemHierarchyDefinition.setMember(RandomTestUtil.randomString());

		newIvldItemHierarchyDefinition.setReasonForFailure(RandomTestUtil.randomString());

		newIvldItemHierarchyDefinition.setItemHierarchyDefnIntfid(RandomTestUtil.randomString());

		newIvldItemHierarchyDefinition.setBpiLvl(RandomTestUtil.randomString());

		newIvldItemHierarchyDefinition.setModifiedDate(RandomTestUtil.nextDate());

		newIvldItemHierarchyDefinition.setAlias(RandomTestUtil.randomString());

		newIvldItemHierarchyDefinition.setCreatedBy(RandomTestUtil.randomString());

		newIvldItemHierarchyDefinition.setCreatedDate(RandomTestUtil.nextDate());

		newIvldItemHierarchyDefinition.setSource(RandomTestUtil.randomString());

		newIvldItemHierarchyDefinition.setBatchId(RandomTestUtil.randomString());

		newIvldItemHierarchyDefinition.setAddChgDelIndicator(RandomTestUtil.randomString());

		newIvldItemHierarchyDefinition.setErrorField(RandomTestUtil.randomString());

		newIvldItemHierarchyDefinition.setErrorCode(RandomTestUtil.randomString());

		newIvldItemHierarchyDefinition.setIntfInsertedDate(RandomTestUtil.nextDate());

		newIvldItemHierarchyDefinition.setModifiedBy(RandomTestUtil.randomString());

		newIvldItemHierarchyDefinition.setReprocessedFlag(RandomTestUtil.randomString());

		newIvldItemHierarchyDefinition.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldItemHierarchyDefinitions.add(_persistence.update(
				newIvldItemHierarchyDefinition));

		IvldItemHierarchyDefinition existingIvldItemHierarchyDefinition = _persistence.findByPrimaryKey(newIvldItemHierarchyDefinition.getPrimaryKey());

		Assert.assertEquals(existingIvldItemHierarchyDefinition.getMember(),
			newIvldItemHierarchyDefinition.getMember());
		Assert.assertEquals(existingIvldItemHierarchyDefinition.getReasonForFailure(),
			newIvldItemHierarchyDefinition.getReasonForFailure());
		Assert.assertEquals(existingIvldItemHierarchyDefinition.getItemHierarchyDefnIntfid(),
			newIvldItemHierarchyDefinition.getItemHierarchyDefnIntfid());
		Assert.assertEquals(existingIvldItemHierarchyDefinition.getBpiLvl(),
			newIvldItemHierarchyDefinition.getBpiLvl());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldItemHierarchyDefinition.getModifiedDate()),
			Time.getShortTimestamp(
				newIvldItemHierarchyDefinition.getModifiedDate()));
		Assert.assertEquals(existingIvldItemHierarchyDefinition.getAlias(),
			newIvldItemHierarchyDefinition.getAlias());
		Assert.assertEquals(existingIvldItemHierarchyDefinition.getCreatedBy(),
			newIvldItemHierarchyDefinition.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldItemHierarchyDefinition.getCreatedDate()),
			Time.getShortTimestamp(
				newIvldItemHierarchyDefinition.getCreatedDate()));
		Assert.assertEquals(existingIvldItemHierarchyDefinition.getSource(),
			newIvldItemHierarchyDefinition.getSource());
		Assert.assertEquals(existingIvldItemHierarchyDefinition.getBatchId(),
			newIvldItemHierarchyDefinition.getBatchId());
		Assert.assertEquals(existingIvldItemHierarchyDefinition.getAddChgDelIndicator(),
			newIvldItemHierarchyDefinition.getAddChgDelIndicator());
		Assert.assertEquals(existingIvldItemHierarchyDefinition.getIvldItemHierarchyDefinitionSid(),
			newIvldItemHierarchyDefinition.getIvldItemHierarchyDefinitionSid());
		Assert.assertEquals(existingIvldItemHierarchyDefinition.getErrorField(),
			newIvldItemHierarchyDefinition.getErrorField());
		Assert.assertEquals(existingIvldItemHierarchyDefinition.getErrorCode(),
			newIvldItemHierarchyDefinition.getErrorCode());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldItemHierarchyDefinition.getIntfInsertedDate()),
			Time.getShortTimestamp(
				newIvldItemHierarchyDefinition.getIntfInsertedDate()));
		Assert.assertEquals(existingIvldItemHierarchyDefinition.getModifiedBy(),
			newIvldItemHierarchyDefinition.getModifiedBy());
		Assert.assertEquals(existingIvldItemHierarchyDefinition.getReprocessedFlag(),
			newIvldItemHierarchyDefinition.getReprocessedFlag());
		Assert.assertEquals(existingIvldItemHierarchyDefinition.getCheckRecord(),
			newIvldItemHierarchyDefinition.getCheckRecord());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		IvldItemHierarchyDefinition newIvldItemHierarchyDefinition = addIvldItemHierarchyDefinition();

		IvldItemHierarchyDefinition existingIvldItemHierarchyDefinition = _persistence.findByPrimaryKey(newIvldItemHierarchyDefinition.getPrimaryKey());

		Assert.assertEquals(existingIvldItemHierarchyDefinition,
			newIvldItemHierarchyDefinition);
	}

	@Test(expected = NoSuchIvldItemHierarchyDefinitionException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<IvldItemHierarchyDefinition> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("IVLD_ITEM_HIERARCHY_DEFINITION",
			"member", true, "reasonForFailure", true,
			"itemHierarchyDefnIntfid", true, "bpiLvl", true, "modifiedDate",
			true, "alias", true, "createdBy", true, "createdDate", true,
			"source", true, "batchId", true, "addChgDelIndicator", true,
			"ivldItemHierarchyDefinitionSid", true, "errorField", true,
			"errorCode", true, "intfInsertedDate", true, "modifiedBy", true,
			"reprocessedFlag", true, "checkRecord", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		IvldItemHierarchyDefinition newIvldItemHierarchyDefinition = addIvldItemHierarchyDefinition();

		IvldItemHierarchyDefinition existingIvldItemHierarchyDefinition = _persistence.fetchByPrimaryKey(newIvldItemHierarchyDefinition.getPrimaryKey());

		Assert.assertEquals(existingIvldItemHierarchyDefinition,
			newIvldItemHierarchyDefinition);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldItemHierarchyDefinition missingIvldItemHierarchyDefinition = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingIvldItemHierarchyDefinition);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		IvldItemHierarchyDefinition newIvldItemHierarchyDefinition1 = addIvldItemHierarchyDefinition();
		IvldItemHierarchyDefinition newIvldItemHierarchyDefinition2 = addIvldItemHierarchyDefinition();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldItemHierarchyDefinition1.getPrimaryKey());
		primaryKeys.add(newIvldItemHierarchyDefinition2.getPrimaryKey());

		Map<Serializable, IvldItemHierarchyDefinition> ivldItemHierarchyDefinitions =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ivldItemHierarchyDefinitions.size());
		Assert.assertEquals(newIvldItemHierarchyDefinition1,
			ivldItemHierarchyDefinitions.get(
				newIvldItemHierarchyDefinition1.getPrimaryKey()));
		Assert.assertEquals(newIvldItemHierarchyDefinition2,
			ivldItemHierarchyDefinitions.get(
				newIvldItemHierarchyDefinition2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, IvldItemHierarchyDefinition> ivldItemHierarchyDefinitions =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldItemHierarchyDefinitions.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		IvldItemHierarchyDefinition newIvldItemHierarchyDefinition = addIvldItemHierarchyDefinition();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldItemHierarchyDefinition.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, IvldItemHierarchyDefinition> ivldItemHierarchyDefinitions =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldItemHierarchyDefinitions.size());
		Assert.assertEquals(newIvldItemHierarchyDefinition,
			ivldItemHierarchyDefinitions.get(
				newIvldItemHierarchyDefinition.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, IvldItemHierarchyDefinition> ivldItemHierarchyDefinitions =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldItemHierarchyDefinitions.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		IvldItemHierarchyDefinition newIvldItemHierarchyDefinition = addIvldItemHierarchyDefinition();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldItemHierarchyDefinition.getPrimaryKey());

		Map<Serializable, IvldItemHierarchyDefinition> ivldItemHierarchyDefinitions =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldItemHierarchyDefinitions.size());
		Assert.assertEquals(newIvldItemHierarchyDefinition,
			ivldItemHierarchyDefinitions.get(
				newIvldItemHierarchyDefinition.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = IvldItemHierarchyDefinitionLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<IvldItemHierarchyDefinition>() {
				@Override
				public void performAction(
					IvldItemHierarchyDefinition ivldItemHierarchyDefinition) {
					Assert.assertNotNull(ivldItemHierarchyDefinition);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		IvldItemHierarchyDefinition newIvldItemHierarchyDefinition = addIvldItemHierarchyDefinition();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldItemHierarchyDefinition.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"ivldItemHierarchyDefinitionSid",
				newIvldItemHierarchyDefinition.getIvldItemHierarchyDefinitionSid()));

		List<IvldItemHierarchyDefinition> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		IvldItemHierarchyDefinition existingIvldItemHierarchyDefinition = result.get(0);

		Assert.assertEquals(existingIvldItemHierarchyDefinition,
			newIvldItemHierarchyDefinition);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldItemHierarchyDefinition.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"ivldItemHierarchyDefinitionSid", RandomTestUtil.nextInt()));

		List<IvldItemHierarchyDefinition> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		IvldItemHierarchyDefinition newIvldItemHierarchyDefinition = addIvldItemHierarchyDefinition();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldItemHierarchyDefinition.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldItemHierarchyDefinitionSid"));

		Object newIvldItemHierarchyDefinitionSid = newIvldItemHierarchyDefinition.getIvldItemHierarchyDefinitionSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"ivldItemHierarchyDefinitionSid",
				new Object[] { newIvldItemHierarchyDefinitionSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingIvldItemHierarchyDefinitionSid = result.get(0);

		Assert.assertEquals(existingIvldItemHierarchyDefinitionSid,
			newIvldItemHierarchyDefinitionSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldItemHierarchyDefinition.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldItemHierarchyDefinitionSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"ivldItemHierarchyDefinitionSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected IvldItemHierarchyDefinition addIvldItemHierarchyDefinition()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldItemHierarchyDefinition ivldItemHierarchyDefinition = _persistence.create(pk);

		ivldItemHierarchyDefinition.setMember(RandomTestUtil.randomString());

		ivldItemHierarchyDefinition.setReasonForFailure(RandomTestUtil.randomString());

		ivldItemHierarchyDefinition.setItemHierarchyDefnIntfid(RandomTestUtil.randomString());

		ivldItemHierarchyDefinition.setBpiLvl(RandomTestUtil.randomString());

		ivldItemHierarchyDefinition.setModifiedDate(RandomTestUtil.nextDate());

		ivldItemHierarchyDefinition.setAlias(RandomTestUtil.randomString());

		ivldItemHierarchyDefinition.setCreatedBy(RandomTestUtil.randomString());

		ivldItemHierarchyDefinition.setCreatedDate(RandomTestUtil.nextDate());

		ivldItemHierarchyDefinition.setSource(RandomTestUtil.randomString());

		ivldItemHierarchyDefinition.setBatchId(RandomTestUtil.randomString());

		ivldItemHierarchyDefinition.setAddChgDelIndicator(RandomTestUtil.randomString());

		ivldItemHierarchyDefinition.setErrorField(RandomTestUtil.randomString());

		ivldItemHierarchyDefinition.setErrorCode(RandomTestUtil.randomString());

		ivldItemHierarchyDefinition.setIntfInsertedDate(RandomTestUtil.nextDate());

		ivldItemHierarchyDefinition.setModifiedBy(RandomTestUtil.randomString());

		ivldItemHierarchyDefinition.setReprocessedFlag(RandomTestUtil.randomString());

		ivldItemHierarchyDefinition.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldItemHierarchyDefinitions.add(_persistence.update(
				ivldItemHierarchyDefinition));

		return ivldItemHierarchyDefinition;
	}

	private List<IvldItemHierarchyDefinition> _ivldItemHierarchyDefinitions = new ArrayList<IvldItemHierarchyDefinition>();
	private IvldItemHierarchyDefinitionPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}