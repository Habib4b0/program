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

import com.stpl.app.parttwo.exception.NoSuchVwItemIdentifierException;
import com.stpl.app.parttwo.model.VwItemIdentifier;
import com.stpl.app.parttwo.service.VwItemIdentifierLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.VwItemIdentifierPersistence;
import com.stpl.app.parttwo.service.persistence.VwItemIdentifierUtil;

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
public class VwItemIdentifierPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = VwItemIdentifierUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<VwItemIdentifier> iterator = _vwItemIdentifiers.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwItemIdentifier vwItemIdentifier = _persistence.create(pk);

		Assert.assertNotNull(vwItemIdentifier);

		Assert.assertEquals(vwItemIdentifier.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		VwItemIdentifier newVwItemIdentifier = addVwItemIdentifier();

		_persistence.remove(newVwItemIdentifier);

		VwItemIdentifier existingVwItemIdentifier = _persistence.fetchByPrimaryKey(newVwItemIdentifier.getPrimaryKey());

		Assert.assertNull(existingVwItemIdentifier);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addVwItemIdentifier();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwItemIdentifier newVwItemIdentifier = _persistence.create(pk);

		newVwItemIdentifier.setItemStatus(RandomTestUtil.randomString());

		newVwItemIdentifier.setEndDate(RandomTestUtil.nextDate());

		newVwItemIdentifier.setItemId(RandomTestUtil.randomString());

		newVwItemIdentifier.setModifiedDate(RandomTestUtil.nextDate());

		newVwItemIdentifier.setEntityCode(RandomTestUtil.randomString());

		newVwItemIdentifier.setStartDate(RandomTestUtil.nextDate());

		newVwItemIdentifier.setCreatedDate(RandomTestUtil.nextDate());

		newVwItemIdentifier.setCreatedBy(RandomTestUtil.randomString());

		newVwItemIdentifier.setSource(RandomTestUtil.randomString());

		newVwItemIdentifier.setBatchId(RandomTestUtil.randomString());

		newVwItemIdentifier.setAddChgDelIndicator(RandomTestUtil.randomString());

		newVwItemIdentifier.setItemName(RandomTestUtil.randomString());

		newVwItemIdentifier.setItemIdentifier(RandomTestUtil.randomString());

		newVwItemIdentifier.setIdentifierCodeQualifierName(RandomTestUtil.randomString());

		newVwItemIdentifier.setModifiedBy(RandomTestUtil.randomString());

		newVwItemIdentifier.setItemNo(RandomTestUtil.randomString());

		newVwItemIdentifier.setIdentifierCodeQualifier(RandomTestUtil.randomString());

		_vwItemIdentifiers.add(_persistence.update(newVwItemIdentifier));

		VwItemIdentifier existingVwItemIdentifier = _persistence.findByPrimaryKey(newVwItemIdentifier.getPrimaryKey());

		Assert.assertEquals(existingVwItemIdentifier.getItemStatus(),
			newVwItemIdentifier.getItemStatus());
		Assert.assertEquals(existingVwItemIdentifier.getItemIdentifierSid(),
			newVwItemIdentifier.getItemIdentifierSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwItemIdentifier.getEndDate()),
			Time.getShortTimestamp(newVwItemIdentifier.getEndDate()));
		Assert.assertEquals(existingVwItemIdentifier.getItemId(),
			newVwItemIdentifier.getItemId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwItemIdentifier.getModifiedDate()),
			Time.getShortTimestamp(newVwItemIdentifier.getModifiedDate()));
		Assert.assertEquals(existingVwItemIdentifier.getEntityCode(),
			newVwItemIdentifier.getEntityCode());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwItemIdentifier.getStartDate()),
			Time.getShortTimestamp(newVwItemIdentifier.getStartDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwItemIdentifier.getCreatedDate()),
			Time.getShortTimestamp(newVwItemIdentifier.getCreatedDate()));
		Assert.assertEquals(existingVwItemIdentifier.getCreatedBy(),
			newVwItemIdentifier.getCreatedBy());
		Assert.assertEquals(existingVwItemIdentifier.getSource(),
			newVwItemIdentifier.getSource());
		Assert.assertEquals(existingVwItemIdentifier.getBatchId(),
			newVwItemIdentifier.getBatchId());
		Assert.assertEquals(existingVwItemIdentifier.getAddChgDelIndicator(),
			newVwItemIdentifier.getAddChgDelIndicator());
		Assert.assertEquals(existingVwItemIdentifier.getItemName(),
			newVwItemIdentifier.getItemName());
		Assert.assertEquals(existingVwItemIdentifier.getItemIdentifier(),
			newVwItemIdentifier.getItemIdentifier());
		Assert.assertEquals(existingVwItemIdentifier.getIdentifierCodeQualifierName(),
			newVwItemIdentifier.getIdentifierCodeQualifierName());
		Assert.assertEquals(existingVwItemIdentifier.getModifiedBy(),
			newVwItemIdentifier.getModifiedBy());
		Assert.assertEquals(existingVwItemIdentifier.getItemNo(),
			newVwItemIdentifier.getItemNo());
		Assert.assertEquals(existingVwItemIdentifier.getIdentifierCodeQualifier(),
			newVwItemIdentifier.getIdentifierCodeQualifier());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		VwItemIdentifier newVwItemIdentifier = addVwItemIdentifier();

		VwItemIdentifier existingVwItemIdentifier = _persistence.findByPrimaryKey(newVwItemIdentifier.getPrimaryKey());

		Assert.assertEquals(existingVwItemIdentifier, newVwItemIdentifier);
	}

	@Test(expected = NoSuchVwItemIdentifierException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<VwItemIdentifier> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("VW_ITEM_IDENTIFIER",
			"itemStatus", true, "itemIdentifierSid", true, "endDate", true,
			"itemId", true, "modifiedDate", true, "entityCode", true,
			"startDate", true, "createdDate", true, "createdBy", true,
			"source", true, "batchId", true, "addChgDelIndicator", true,
			"itemName", true, "itemIdentifier", true,
			"identifierCodeQualifierName", true, "modifiedBy", true, "itemNo",
			true, "identifierCodeQualifier", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		VwItemIdentifier newVwItemIdentifier = addVwItemIdentifier();

		VwItemIdentifier existingVwItemIdentifier = _persistence.fetchByPrimaryKey(newVwItemIdentifier.getPrimaryKey());

		Assert.assertEquals(existingVwItemIdentifier, newVwItemIdentifier);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwItemIdentifier missingVwItemIdentifier = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingVwItemIdentifier);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		VwItemIdentifier newVwItemIdentifier1 = addVwItemIdentifier();
		VwItemIdentifier newVwItemIdentifier2 = addVwItemIdentifier();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwItemIdentifier1.getPrimaryKey());
		primaryKeys.add(newVwItemIdentifier2.getPrimaryKey());

		Map<Serializable, VwItemIdentifier> vwItemIdentifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, vwItemIdentifiers.size());
		Assert.assertEquals(newVwItemIdentifier1,
			vwItemIdentifiers.get(newVwItemIdentifier1.getPrimaryKey()));
		Assert.assertEquals(newVwItemIdentifier2,
			vwItemIdentifiers.get(newVwItemIdentifier2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, VwItemIdentifier> vwItemIdentifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(vwItemIdentifiers.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		VwItemIdentifier newVwItemIdentifier = addVwItemIdentifier();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwItemIdentifier.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, VwItemIdentifier> vwItemIdentifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, vwItemIdentifiers.size());
		Assert.assertEquals(newVwItemIdentifier,
			vwItemIdentifiers.get(newVwItemIdentifier.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, VwItemIdentifier> vwItemIdentifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(vwItemIdentifiers.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		VwItemIdentifier newVwItemIdentifier = addVwItemIdentifier();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwItemIdentifier.getPrimaryKey());

		Map<Serializable, VwItemIdentifier> vwItemIdentifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, vwItemIdentifiers.size());
		Assert.assertEquals(newVwItemIdentifier,
			vwItemIdentifiers.get(newVwItemIdentifier.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = VwItemIdentifierLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<VwItemIdentifier>() {
				@Override
				public void performAction(VwItemIdentifier vwItemIdentifier) {
					Assert.assertNotNull(vwItemIdentifier);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		VwItemIdentifier newVwItemIdentifier = addVwItemIdentifier();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwItemIdentifier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("itemIdentifierSid",
				newVwItemIdentifier.getItemIdentifierSid()));

		List<VwItemIdentifier> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		VwItemIdentifier existingVwItemIdentifier = result.get(0);

		Assert.assertEquals(existingVwItemIdentifier, newVwItemIdentifier);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwItemIdentifier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("itemIdentifierSid",
				RandomTestUtil.nextInt()));

		List<VwItemIdentifier> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		VwItemIdentifier newVwItemIdentifier = addVwItemIdentifier();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwItemIdentifier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"itemIdentifierSid"));

		Object newItemIdentifierSid = newVwItemIdentifier.getItemIdentifierSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("itemIdentifierSid",
				new Object[] { newItemIdentifierSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingItemIdentifierSid = result.get(0);

		Assert.assertEquals(existingItemIdentifierSid, newItemIdentifierSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwItemIdentifier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"itemIdentifierSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("itemIdentifierSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected VwItemIdentifier addVwItemIdentifier() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwItemIdentifier vwItemIdentifier = _persistence.create(pk);

		vwItemIdentifier.setItemStatus(RandomTestUtil.randomString());

		vwItemIdentifier.setEndDate(RandomTestUtil.nextDate());

		vwItemIdentifier.setItemId(RandomTestUtil.randomString());

		vwItemIdentifier.setModifiedDate(RandomTestUtil.nextDate());

		vwItemIdentifier.setEntityCode(RandomTestUtil.randomString());

		vwItemIdentifier.setStartDate(RandomTestUtil.nextDate());

		vwItemIdentifier.setCreatedDate(RandomTestUtil.nextDate());

		vwItemIdentifier.setCreatedBy(RandomTestUtil.randomString());

		vwItemIdentifier.setSource(RandomTestUtil.randomString());

		vwItemIdentifier.setBatchId(RandomTestUtil.randomString());

		vwItemIdentifier.setAddChgDelIndicator(RandomTestUtil.randomString());

		vwItemIdentifier.setItemName(RandomTestUtil.randomString());

		vwItemIdentifier.setItemIdentifier(RandomTestUtil.randomString());

		vwItemIdentifier.setIdentifierCodeQualifierName(RandomTestUtil.randomString());

		vwItemIdentifier.setModifiedBy(RandomTestUtil.randomString());

		vwItemIdentifier.setItemNo(RandomTestUtil.randomString());

		vwItemIdentifier.setIdentifierCodeQualifier(RandomTestUtil.randomString());

		_vwItemIdentifiers.add(_persistence.update(vwItemIdentifier));

		return vwItemIdentifier;
	}

	private List<VwItemIdentifier> _vwItemIdentifiers = new ArrayList<VwItemIdentifier>();
	private VwItemIdentifierPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}