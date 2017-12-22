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

import com.stpl.app.parttwo.exception.NoSuchIvldItemIdentifierException;
import com.stpl.app.parttwo.model.IvldItemIdentifier;
import com.stpl.app.parttwo.service.IvldItemIdentifierLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.IvldItemIdentifierPersistence;
import com.stpl.app.parttwo.service.persistence.IvldItemIdentifierUtil;

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
public class IvldItemIdentifierPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = IvldItemIdentifierUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<IvldItemIdentifier> iterator = _ivldItemIdentifiers.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldItemIdentifier ivldItemIdentifier = _persistence.create(pk);

		Assert.assertNotNull(ivldItemIdentifier);

		Assert.assertEquals(ivldItemIdentifier.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		IvldItemIdentifier newIvldItemIdentifier = addIvldItemIdentifier();

		_persistence.remove(newIvldItemIdentifier);

		IvldItemIdentifier existingIvldItemIdentifier = _persistence.fetchByPrimaryKey(newIvldItemIdentifier.getPrimaryKey());

		Assert.assertNull(existingIvldItemIdentifier);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addIvldItemIdentifier();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldItemIdentifier newIvldItemIdentifier = _persistence.create(pk);

		newIvldItemIdentifier.setCreatedBy(RandomTestUtil.randomString());

		newIvldItemIdentifier.setIdentifierCodeQualifierName(RandomTestUtil.randomString());

		newIvldItemIdentifier.setItemNo(RandomTestUtil.randomString());

		newIvldItemIdentifier.setModifiedBy(RandomTestUtil.randomString());

		newIvldItemIdentifier.setCreatedDate(RandomTestUtil.nextDate());

		newIvldItemIdentifier.setIdentifierCodeQualifier(RandomTestUtil.randomString());

		newIvldItemIdentifier.setItemId(RandomTestUtil.randomString());

		newIvldItemIdentifier.setEndDate(RandomTestUtil.randomString());

		newIvldItemIdentifier.setErrorField(RandomTestUtil.randomString());

		newIvldItemIdentifier.setStartDate(RandomTestUtil.randomString());

		newIvldItemIdentifier.setBatchId(RandomTestUtil.randomString());

		newIvldItemIdentifier.setModifiedDate(RandomTestUtil.nextDate());

		newIvldItemIdentifier.setItemName(RandomTestUtil.randomString());

		newIvldItemIdentifier.setErrorCode(RandomTestUtil.randomString());

		newIvldItemIdentifier.setReprocessedFlag(RandomTestUtil.randomString());

		newIvldItemIdentifier.setItemIdentifier(RandomTestUtil.randomString());

		newIvldItemIdentifier.setItemStatus(RandomTestUtil.randomString());

		newIvldItemIdentifier.setReasonForFailure(RandomTestUtil.randomString());

		newIvldItemIdentifier.setSource(RandomTestUtil.randomString());

		newIvldItemIdentifier.setAddChgDelIndicator(RandomTestUtil.randomString());

		newIvldItemIdentifier.setEntityCode(RandomTestUtil.randomString());

		newIvldItemIdentifier.setItemIdentifierIntfid(RandomTestUtil.randomString());

		newIvldItemIdentifier.setIntfInsertedDate(RandomTestUtil.nextDate());

		newIvldItemIdentifier.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldItemIdentifiers.add(_persistence.update(newIvldItemIdentifier));

		IvldItemIdentifier existingIvldItemIdentifier = _persistence.findByPrimaryKey(newIvldItemIdentifier.getPrimaryKey());

		Assert.assertEquals(existingIvldItemIdentifier.getCreatedBy(),
			newIvldItemIdentifier.getCreatedBy());
		Assert.assertEquals(existingIvldItemIdentifier.getIdentifierCodeQualifierName(),
			newIvldItemIdentifier.getIdentifierCodeQualifierName());
		Assert.assertEquals(existingIvldItemIdentifier.getIvldItemIdentifierSid(),
			newIvldItemIdentifier.getIvldItemIdentifierSid());
		Assert.assertEquals(existingIvldItemIdentifier.getItemNo(),
			newIvldItemIdentifier.getItemNo());
		Assert.assertEquals(existingIvldItemIdentifier.getModifiedBy(),
			newIvldItemIdentifier.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldItemIdentifier.getCreatedDate()),
			Time.getShortTimestamp(newIvldItemIdentifier.getCreatedDate()));
		Assert.assertEquals(existingIvldItemIdentifier.getIdentifierCodeQualifier(),
			newIvldItemIdentifier.getIdentifierCodeQualifier());
		Assert.assertEquals(existingIvldItemIdentifier.getItemId(),
			newIvldItemIdentifier.getItemId());
		Assert.assertEquals(existingIvldItemIdentifier.getEndDate(),
			newIvldItemIdentifier.getEndDate());
		Assert.assertEquals(existingIvldItemIdentifier.getErrorField(),
			newIvldItemIdentifier.getErrorField());
		Assert.assertEquals(existingIvldItemIdentifier.getStartDate(),
			newIvldItemIdentifier.getStartDate());
		Assert.assertEquals(existingIvldItemIdentifier.getBatchId(),
			newIvldItemIdentifier.getBatchId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldItemIdentifier.getModifiedDate()),
			Time.getShortTimestamp(newIvldItemIdentifier.getModifiedDate()));
		Assert.assertEquals(existingIvldItemIdentifier.getItemName(),
			newIvldItemIdentifier.getItemName());
		Assert.assertEquals(existingIvldItemIdentifier.getErrorCode(),
			newIvldItemIdentifier.getErrorCode());
		Assert.assertEquals(existingIvldItemIdentifier.getReprocessedFlag(),
			newIvldItemIdentifier.getReprocessedFlag());
		Assert.assertEquals(existingIvldItemIdentifier.getItemIdentifier(),
			newIvldItemIdentifier.getItemIdentifier());
		Assert.assertEquals(existingIvldItemIdentifier.getItemStatus(),
			newIvldItemIdentifier.getItemStatus());
		Assert.assertEquals(existingIvldItemIdentifier.getReasonForFailure(),
			newIvldItemIdentifier.getReasonForFailure());
		Assert.assertEquals(existingIvldItemIdentifier.getSource(),
			newIvldItemIdentifier.getSource());
		Assert.assertEquals(existingIvldItemIdentifier.getAddChgDelIndicator(),
			newIvldItemIdentifier.getAddChgDelIndicator());
		Assert.assertEquals(existingIvldItemIdentifier.getEntityCode(),
			newIvldItemIdentifier.getEntityCode());
		Assert.assertEquals(existingIvldItemIdentifier.getItemIdentifierIntfid(),
			newIvldItemIdentifier.getItemIdentifierIntfid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldItemIdentifier.getIntfInsertedDate()),
			Time.getShortTimestamp(newIvldItemIdentifier.getIntfInsertedDate()));
		Assert.assertEquals(existingIvldItemIdentifier.getCheckRecord(),
			newIvldItemIdentifier.getCheckRecord());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		IvldItemIdentifier newIvldItemIdentifier = addIvldItemIdentifier();

		IvldItemIdentifier existingIvldItemIdentifier = _persistence.findByPrimaryKey(newIvldItemIdentifier.getPrimaryKey());

		Assert.assertEquals(existingIvldItemIdentifier, newIvldItemIdentifier);
	}

	@Test(expected = NoSuchIvldItemIdentifierException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<IvldItemIdentifier> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("IVLD_ITEM_IDENTIFIER",
			"createdBy", true, "identifierCodeQualifierName", true,
			"ivldItemIdentifierSid", true, "itemNo", true, "modifiedBy", true,
			"createdDate", true, "identifierCodeQualifier", true, "itemId",
			true, "endDate", true, "errorField", true, "startDate", true,
			"batchId", true, "modifiedDate", true, "itemName", true,
			"errorCode", true, "reprocessedFlag", true, "itemIdentifier", true,
			"itemStatus", true, "reasonForFailure", true, "source", true,
			"addChgDelIndicator", true, "entityCode", true,
			"itemIdentifierIntfid", true, "intfInsertedDate", true,
			"checkRecord", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		IvldItemIdentifier newIvldItemIdentifier = addIvldItemIdentifier();

		IvldItemIdentifier existingIvldItemIdentifier = _persistence.fetchByPrimaryKey(newIvldItemIdentifier.getPrimaryKey());

		Assert.assertEquals(existingIvldItemIdentifier, newIvldItemIdentifier);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldItemIdentifier missingIvldItemIdentifier = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingIvldItemIdentifier);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		IvldItemIdentifier newIvldItemIdentifier1 = addIvldItemIdentifier();
		IvldItemIdentifier newIvldItemIdentifier2 = addIvldItemIdentifier();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldItemIdentifier1.getPrimaryKey());
		primaryKeys.add(newIvldItemIdentifier2.getPrimaryKey());

		Map<Serializable, IvldItemIdentifier> ivldItemIdentifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ivldItemIdentifiers.size());
		Assert.assertEquals(newIvldItemIdentifier1,
			ivldItemIdentifiers.get(newIvldItemIdentifier1.getPrimaryKey()));
		Assert.assertEquals(newIvldItemIdentifier2,
			ivldItemIdentifiers.get(newIvldItemIdentifier2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, IvldItemIdentifier> ivldItemIdentifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldItemIdentifiers.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		IvldItemIdentifier newIvldItemIdentifier = addIvldItemIdentifier();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldItemIdentifier.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, IvldItemIdentifier> ivldItemIdentifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldItemIdentifiers.size());
		Assert.assertEquals(newIvldItemIdentifier,
			ivldItemIdentifiers.get(newIvldItemIdentifier.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, IvldItemIdentifier> ivldItemIdentifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldItemIdentifiers.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		IvldItemIdentifier newIvldItemIdentifier = addIvldItemIdentifier();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldItemIdentifier.getPrimaryKey());

		Map<Serializable, IvldItemIdentifier> ivldItemIdentifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldItemIdentifiers.size());
		Assert.assertEquals(newIvldItemIdentifier,
			ivldItemIdentifiers.get(newIvldItemIdentifier.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = IvldItemIdentifierLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<IvldItemIdentifier>() {
				@Override
				public void performAction(IvldItemIdentifier ivldItemIdentifier) {
					Assert.assertNotNull(ivldItemIdentifier);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		IvldItemIdentifier newIvldItemIdentifier = addIvldItemIdentifier();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldItemIdentifier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldItemIdentifierSid",
				newIvldItemIdentifier.getIvldItemIdentifierSid()));

		List<IvldItemIdentifier> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		IvldItemIdentifier existingIvldItemIdentifier = result.get(0);

		Assert.assertEquals(existingIvldItemIdentifier, newIvldItemIdentifier);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldItemIdentifier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldItemIdentifierSid",
				RandomTestUtil.nextInt()));

		List<IvldItemIdentifier> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		IvldItemIdentifier newIvldItemIdentifier = addIvldItemIdentifier();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldItemIdentifier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldItemIdentifierSid"));

		Object newIvldItemIdentifierSid = newIvldItemIdentifier.getIvldItemIdentifierSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldItemIdentifierSid",
				new Object[] { newIvldItemIdentifierSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingIvldItemIdentifierSid = result.get(0);

		Assert.assertEquals(existingIvldItemIdentifierSid,
			newIvldItemIdentifierSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldItemIdentifier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldItemIdentifierSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldItemIdentifierSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected IvldItemIdentifier addIvldItemIdentifier()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldItemIdentifier ivldItemIdentifier = _persistence.create(pk);

		ivldItemIdentifier.setCreatedBy(RandomTestUtil.randomString());

		ivldItemIdentifier.setIdentifierCodeQualifierName(RandomTestUtil.randomString());

		ivldItemIdentifier.setItemNo(RandomTestUtil.randomString());

		ivldItemIdentifier.setModifiedBy(RandomTestUtil.randomString());

		ivldItemIdentifier.setCreatedDate(RandomTestUtil.nextDate());

		ivldItemIdentifier.setIdentifierCodeQualifier(RandomTestUtil.randomString());

		ivldItemIdentifier.setItemId(RandomTestUtil.randomString());

		ivldItemIdentifier.setEndDate(RandomTestUtil.randomString());

		ivldItemIdentifier.setErrorField(RandomTestUtil.randomString());

		ivldItemIdentifier.setStartDate(RandomTestUtil.randomString());

		ivldItemIdentifier.setBatchId(RandomTestUtil.randomString());

		ivldItemIdentifier.setModifiedDate(RandomTestUtil.nextDate());

		ivldItemIdentifier.setItemName(RandomTestUtil.randomString());

		ivldItemIdentifier.setErrorCode(RandomTestUtil.randomString());

		ivldItemIdentifier.setReprocessedFlag(RandomTestUtil.randomString());

		ivldItemIdentifier.setItemIdentifier(RandomTestUtil.randomString());

		ivldItemIdentifier.setItemStatus(RandomTestUtil.randomString());

		ivldItemIdentifier.setReasonForFailure(RandomTestUtil.randomString());

		ivldItemIdentifier.setSource(RandomTestUtil.randomString());

		ivldItemIdentifier.setAddChgDelIndicator(RandomTestUtil.randomString());

		ivldItemIdentifier.setEntityCode(RandomTestUtil.randomString());

		ivldItemIdentifier.setItemIdentifierIntfid(RandomTestUtil.randomString());

		ivldItemIdentifier.setIntfInsertedDate(RandomTestUtil.nextDate());

		ivldItemIdentifier.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldItemIdentifiers.add(_persistence.update(ivldItemIdentifier));

		return ivldItemIdentifier;
	}

	private List<IvldItemIdentifier> _ivldItemIdentifiers = new ArrayList<IvldItemIdentifier>();
	private IvldItemIdentifierPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}