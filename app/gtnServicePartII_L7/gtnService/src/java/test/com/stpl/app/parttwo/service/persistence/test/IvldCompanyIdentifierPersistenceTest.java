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

import com.stpl.app.parttwo.exception.NoSuchIvldCompanyIdentifierException;
import com.stpl.app.parttwo.model.IvldCompanyIdentifier;
import com.stpl.app.parttwo.service.IvldCompanyIdentifierLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.IvldCompanyIdentifierPersistence;
import com.stpl.app.parttwo.service.persistence.IvldCompanyIdentifierUtil;

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
public class IvldCompanyIdentifierPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = IvldCompanyIdentifierUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<IvldCompanyIdentifier> iterator = _ivldCompanyIdentifiers.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldCompanyIdentifier ivldCompanyIdentifier = _persistence.create(pk);

		Assert.assertNotNull(ivldCompanyIdentifier);

		Assert.assertEquals(ivldCompanyIdentifier.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		IvldCompanyIdentifier newIvldCompanyIdentifier = addIvldCompanyIdentifier();

		_persistence.remove(newIvldCompanyIdentifier);

		IvldCompanyIdentifier existingIvldCompanyIdentifier = _persistence.fetchByPrimaryKey(newIvldCompanyIdentifier.getPrimaryKey());

		Assert.assertNull(existingIvldCompanyIdentifier);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addIvldCompanyIdentifier();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldCompanyIdentifier newIvldCompanyIdentifier = _persistence.create(pk);

		newIvldCompanyIdentifier.setReasonForFailure(RandomTestUtil.randomString());

		newIvldCompanyIdentifier.setCompanyIdString(RandomTestUtil.randomString());

		newIvldCompanyIdentifier.setCompanyName(RandomTestUtil.randomString());

		newIvldCompanyIdentifier.setEndDate(RandomTestUtil.randomString());

		newIvldCompanyIdentifier.setModifiedDate(RandomTestUtil.nextDate());

		newIvldCompanyIdentifier.setIdentifierStatus(RandomTestUtil.randomString());

		newIvldCompanyIdentifier.setCompanyIdentifier(RandomTestUtil.randomString());

		newIvldCompanyIdentifier.setEntityCode(RandomTestUtil.randomString());

		newIvldCompanyIdentifier.setCompanyIdentifierIntfid(RandomTestUtil.randomString());

		newIvldCompanyIdentifier.setStartDate(RandomTestUtil.randomString());

		newIvldCompanyIdentifier.setSource(RandomTestUtil.randomString());

		newIvldCompanyIdentifier.setCreatedDate(RandomTestUtil.nextDate());

		newIvldCompanyIdentifier.setCreatedBy(RandomTestUtil.randomString());

		newIvldCompanyIdentifier.setCompanyNo(RandomTestUtil.randomString());

		newIvldCompanyIdentifier.setAddChgDelIndicator(RandomTestUtil.randomString());

		newIvldCompanyIdentifier.setBatchId(RandomTestUtil.randomString());

		newIvldCompanyIdentifier.setErrorField(RandomTestUtil.randomString());

		newIvldCompanyIdentifier.setErrorCode(RandomTestUtil.randomString());

		newIvldCompanyIdentifier.setIdentifierCodeQualifierName(RandomTestUtil.randomString());

		newIvldCompanyIdentifier.setIntfInsertedDate(RandomTestUtil.nextDate());

		newIvldCompanyIdentifier.setModifiedBy(RandomTestUtil.randomString());

		newIvldCompanyIdentifier.setReprocessedFlag(RandomTestUtil.randomString());

		newIvldCompanyIdentifier.setIdentifierCodeQualifier(RandomTestUtil.randomString());

		newIvldCompanyIdentifier.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldCompanyIdentifiers.add(_persistence.update(
				newIvldCompanyIdentifier));

		IvldCompanyIdentifier existingIvldCompanyIdentifier = _persistence.findByPrimaryKey(newIvldCompanyIdentifier.getPrimaryKey());

		Assert.assertEquals(existingIvldCompanyIdentifier.getReasonForFailure(),
			newIvldCompanyIdentifier.getReasonForFailure());
		Assert.assertEquals(existingIvldCompanyIdentifier.getCompanyIdString(),
			newIvldCompanyIdentifier.getCompanyIdString());
		Assert.assertEquals(existingIvldCompanyIdentifier.getCompanyName(),
			newIvldCompanyIdentifier.getCompanyName());
		Assert.assertEquals(existingIvldCompanyIdentifier.getEndDate(),
			newIvldCompanyIdentifier.getEndDate());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldCompanyIdentifier.getModifiedDate()),
			Time.getShortTimestamp(newIvldCompanyIdentifier.getModifiedDate()));
		Assert.assertEquals(existingIvldCompanyIdentifier.getIdentifierStatus(),
			newIvldCompanyIdentifier.getIdentifierStatus());
		Assert.assertEquals(existingIvldCompanyIdentifier.getCompanyIdentifier(),
			newIvldCompanyIdentifier.getCompanyIdentifier());
		Assert.assertEquals(existingIvldCompanyIdentifier.getEntityCode(),
			newIvldCompanyIdentifier.getEntityCode());
		Assert.assertEquals(existingIvldCompanyIdentifier.getCompanyIdentifierIntfid(),
			newIvldCompanyIdentifier.getCompanyIdentifierIntfid());
		Assert.assertEquals(existingIvldCompanyIdentifier.getStartDate(),
			newIvldCompanyIdentifier.getStartDate());
		Assert.assertEquals(existingIvldCompanyIdentifier.getSource(),
			newIvldCompanyIdentifier.getSource());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldCompanyIdentifier.getCreatedDate()),
			Time.getShortTimestamp(newIvldCompanyIdentifier.getCreatedDate()));
		Assert.assertEquals(existingIvldCompanyIdentifier.getCreatedBy(),
			newIvldCompanyIdentifier.getCreatedBy());
		Assert.assertEquals(existingIvldCompanyIdentifier.getCompanyNo(),
			newIvldCompanyIdentifier.getCompanyNo());
		Assert.assertEquals(existingIvldCompanyIdentifier.getAddChgDelIndicator(),
			newIvldCompanyIdentifier.getAddChgDelIndicator());
		Assert.assertEquals(existingIvldCompanyIdentifier.getBatchId(),
			newIvldCompanyIdentifier.getBatchId());
		Assert.assertEquals(existingIvldCompanyIdentifier.getErrorField(),
			newIvldCompanyIdentifier.getErrorField());
		Assert.assertEquals(existingIvldCompanyIdentifier.getErrorCode(),
			newIvldCompanyIdentifier.getErrorCode());
		Assert.assertEquals(existingIvldCompanyIdentifier.getIdentifierCodeQualifierName(),
			newIvldCompanyIdentifier.getIdentifierCodeQualifierName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldCompanyIdentifier.getIntfInsertedDate()),
			Time.getShortTimestamp(
				newIvldCompanyIdentifier.getIntfInsertedDate()));
		Assert.assertEquals(existingIvldCompanyIdentifier.getModifiedBy(),
			newIvldCompanyIdentifier.getModifiedBy());
		Assert.assertEquals(existingIvldCompanyIdentifier.getIvldCompanyIdentifierSid(),
			newIvldCompanyIdentifier.getIvldCompanyIdentifierSid());
		Assert.assertEquals(existingIvldCompanyIdentifier.getReprocessedFlag(),
			newIvldCompanyIdentifier.getReprocessedFlag());
		Assert.assertEquals(existingIvldCompanyIdentifier.getIdentifierCodeQualifier(),
			newIvldCompanyIdentifier.getIdentifierCodeQualifier());
		Assert.assertEquals(existingIvldCompanyIdentifier.getCheckRecord(),
			newIvldCompanyIdentifier.getCheckRecord());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		IvldCompanyIdentifier newIvldCompanyIdentifier = addIvldCompanyIdentifier();

		IvldCompanyIdentifier existingIvldCompanyIdentifier = _persistence.findByPrimaryKey(newIvldCompanyIdentifier.getPrimaryKey());

		Assert.assertEquals(existingIvldCompanyIdentifier,
			newIvldCompanyIdentifier);
	}

	@Test(expected = NoSuchIvldCompanyIdentifierException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<IvldCompanyIdentifier> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("IVLD_COMPANY_IDENTIFIER",
			"reasonForFailure", true, "companyIdString", true, "companyName",
			true, "endDate", true, "modifiedDate", true, "identifierStatus",
			true, "companyIdentifier", true, "entityCode", true,
			"companyIdentifierIntfid", true, "startDate", true, "source", true,
			"createdDate", true, "createdBy", true, "companyNo", true,
			"addChgDelIndicator", true, "batchId", true, "errorField", true,
			"errorCode", true, "identifierCodeQualifierName", true,
			"intfInsertedDate", true, "modifiedBy", true,
			"ivldCompanyIdentifierSid", true, "reprocessedFlag", true,
			"identifierCodeQualifier", true, "checkRecord", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		IvldCompanyIdentifier newIvldCompanyIdentifier = addIvldCompanyIdentifier();

		IvldCompanyIdentifier existingIvldCompanyIdentifier = _persistence.fetchByPrimaryKey(newIvldCompanyIdentifier.getPrimaryKey());

		Assert.assertEquals(existingIvldCompanyIdentifier,
			newIvldCompanyIdentifier);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldCompanyIdentifier missingIvldCompanyIdentifier = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingIvldCompanyIdentifier);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		IvldCompanyIdentifier newIvldCompanyIdentifier1 = addIvldCompanyIdentifier();
		IvldCompanyIdentifier newIvldCompanyIdentifier2 = addIvldCompanyIdentifier();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldCompanyIdentifier1.getPrimaryKey());
		primaryKeys.add(newIvldCompanyIdentifier2.getPrimaryKey());

		Map<Serializable, IvldCompanyIdentifier> ivldCompanyIdentifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ivldCompanyIdentifiers.size());
		Assert.assertEquals(newIvldCompanyIdentifier1,
			ivldCompanyIdentifiers.get(
				newIvldCompanyIdentifier1.getPrimaryKey()));
		Assert.assertEquals(newIvldCompanyIdentifier2,
			ivldCompanyIdentifiers.get(
				newIvldCompanyIdentifier2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, IvldCompanyIdentifier> ivldCompanyIdentifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldCompanyIdentifiers.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		IvldCompanyIdentifier newIvldCompanyIdentifier = addIvldCompanyIdentifier();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldCompanyIdentifier.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, IvldCompanyIdentifier> ivldCompanyIdentifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldCompanyIdentifiers.size());
		Assert.assertEquals(newIvldCompanyIdentifier,
			ivldCompanyIdentifiers.get(newIvldCompanyIdentifier.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, IvldCompanyIdentifier> ivldCompanyIdentifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldCompanyIdentifiers.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		IvldCompanyIdentifier newIvldCompanyIdentifier = addIvldCompanyIdentifier();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldCompanyIdentifier.getPrimaryKey());

		Map<Serializable, IvldCompanyIdentifier> ivldCompanyIdentifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldCompanyIdentifiers.size());
		Assert.assertEquals(newIvldCompanyIdentifier,
			ivldCompanyIdentifiers.get(newIvldCompanyIdentifier.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = IvldCompanyIdentifierLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<IvldCompanyIdentifier>() {
				@Override
				public void performAction(
					IvldCompanyIdentifier ivldCompanyIdentifier) {
					Assert.assertNotNull(ivldCompanyIdentifier);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		IvldCompanyIdentifier newIvldCompanyIdentifier = addIvldCompanyIdentifier();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldCompanyIdentifier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"ivldCompanyIdentifierSid",
				newIvldCompanyIdentifier.getIvldCompanyIdentifierSid()));

		List<IvldCompanyIdentifier> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		IvldCompanyIdentifier existingIvldCompanyIdentifier = result.get(0);

		Assert.assertEquals(existingIvldCompanyIdentifier,
			newIvldCompanyIdentifier);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldCompanyIdentifier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"ivldCompanyIdentifierSid", RandomTestUtil.nextInt()));

		List<IvldCompanyIdentifier> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		IvldCompanyIdentifier newIvldCompanyIdentifier = addIvldCompanyIdentifier();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldCompanyIdentifier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldCompanyIdentifierSid"));

		Object newIvldCompanyIdentifierSid = newIvldCompanyIdentifier.getIvldCompanyIdentifierSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"ivldCompanyIdentifierSid",
				new Object[] { newIvldCompanyIdentifierSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingIvldCompanyIdentifierSid = result.get(0);

		Assert.assertEquals(existingIvldCompanyIdentifierSid,
			newIvldCompanyIdentifierSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldCompanyIdentifier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldCompanyIdentifierSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"ivldCompanyIdentifierSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected IvldCompanyIdentifier addIvldCompanyIdentifier()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldCompanyIdentifier ivldCompanyIdentifier = _persistence.create(pk);

		ivldCompanyIdentifier.setReasonForFailure(RandomTestUtil.randomString());

		ivldCompanyIdentifier.setCompanyIdString(RandomTestUtil.randomString());

		ivldCompanyIdentifier.setCompanyName(RandomTestUtil.randomString());

		ivldCompanyIdentifier.setEndDate(RandomTestUtil.randomString());

		ivldCompanyIdentifier.setModifiedDate(RandomTestUtil.nextDate());

		ivldCompanyIdentifier.setIdentifierStatus(RandomTestUtil.randomString());

		ivldCompanyIdentifier.setCompanyIdentifier(RandomTestUtil.randomString());

		ivldCompanyIdentifier.setEntityCode(RandomTestUtil.randomString());

		ivldCompanyIdentifier.setCompanyIdentifierIntfid(RandomTestUtil.randomString());

		ivldCompanyIdentifier.setStartDate(RandomTestUtil.randomString());

		ivldCompanyIdentifier.setSource(RandomTestUtil.randomString());

		ivldCompanyIdentifier.setCreatedDate(RandomTestUtil.nextDate());

		ivldCompanyIdentifier.setCreatedBy(RandomTestUtil.randomString());

		ivldCompanyIdentifier.setCompanyNo(RandomTestUtil.randomString());

		ivldCompanyIdentifier.setAddChgDelIndicator(RandomTestUtil.randomString());

		ivldCompanyIdentifier.setBatchId(RandomTestUtil.randomString());

		ivldCompanyIdentifier.setErrorField(RandomTestUtil.randomString());

		ivldCompanyIdentifier.setErrorCode(RandomTestUtil.randomString());

		ivldCompanyIdentifier.setIdentifierCodeQualifierName(RandomTestUtil.randomString());

		ivldCompanyIdentifier.setIntfInsertedDate(RandomTestUtil.nextDate());

		ivldCompanyIdentifier.setModifiedBy(RandomTestUtil.randomString());

		ivldCompanyIdentifier.setReprocessedFlag(RandomTestUtil.randomString());

		ivldCompanyIdentifier.setIdentifierCodeQualifier(RandomTestUtil.randomString());

		ivldCompanyIdentifier.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldCompanyIdentifiers.add(_persistence.update(ivldCompanyIdentifier));

		return ivldCompanyIdentifier;
	}

	private List<IvldCompanyIdentifier> _ivldCompanyIdentifiers = new ArrayList<IvldCompanyIdentifier>();
	private IvldCompanyIdentifierPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}