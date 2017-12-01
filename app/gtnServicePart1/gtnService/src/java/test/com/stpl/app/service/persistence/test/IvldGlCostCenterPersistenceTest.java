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

import com.stpl.app.exception.NoSuchIvldGlCostCenterException;
import com.stpl.app.model.IvldGlCostCenter;
import com.stpl.app.service.IvldGlCostCenterLocalServiceUtil;
import com.stpl.app.service.persistence.IvldGlCostCenterPersistence;
import com.stpl.app.service.persistence.IvldGlCostCenterUtil;

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
public class IvldGlCostCenterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = IvldGlCostCenterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<IvldGlCostCenter> iterator = _ivldGlCostCenters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldGlCostCenter ivldGlCostCenter = _persistence.create(pk);

		Assert.assertNotNull(ivldGlCostCenter);

		Assert.assertEquals(ivldGlCostCenter.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		IvldGlCostCenter newIvldGlCostCenter = addIvldGlCostCenter();

		_persistence.remove(newIvldGlCostCenter);

		IvldGlCostCenter existingIvldGlCostCenter = _persistence.fetchByPrimaryKey(newIvldGlCostCenter.getPrimaryKey());

		Assert.assertNull(existingIvldGlCostCenter);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addIvldGlCostCenter();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldGlCostCenter newIvldGlCostCenter = _persistence.create(pk);

		newIvldGlCostCenter.setReasonForFailure(RandomTestUtil.randomString());

		newIvldGlCostCenter.setGlCostCenterIntfid(RandomTestUtil.randomString());

		newIvldGlCostCenter.setModifiedDate(RandomTestUtil.nextDate());

		newIvldGlCostCenter.setCompanyCostCenter(RandomTestUtil.randomString());

		newIvldGlCostCenter.setUploadDate(RandomTestUtil.randomString());

		newIvldGlCostCenter.setCreatedBy(RandomTestUtil.randomString());

		newIvldGlCostCenter.setCreatedDate(RandomTestUtil.nextDate());

		newIvldGlCostCenter.setSource(RandomTestUtil.randomString());

		newIvldGlCostCenter.setBatchId(RandomTestUtil.randomString());

		newIvldGlCostCenter.setAddChgDelIndicator(RandomTestUtil.randomString());

		newIvldGlCostCenter.setErrorField(RandomTestUtil.randomString());

		newIvldGlCostCenter.setErrorCode(RandomTestUtil.randomString());

		newIvldGlCostCenter.setIntfInsertedDate(RandomTestUtil.nextDate());

		newIvldGlCostCenter.setCompanyCode(RandomTestUtil.randomString());

		newIvldGlCostCenter.setModifiedBy(RandomTestUtil.randomString());

		newIvldGlCostCenter.setReprocessedFlag(RandomTestUtil.randomString());

		newIvldGlCostCenter.setNdc8(RandomTestUtil.randomString());

		newIvldGlCostCenter.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldGlCostCenters.add(_persistence.update(newIvldGlCostCenter));

		IvldGlCostCenter existingIvldGlCostCenter = _persistence.findByPrimaryKey(newIvldGlCostCenter.getPrimaryKey());

		Assert.assertEquals(existingIvldGlCostCenter.getReasonForFailure(),
			newIvldGlCostCenter.getReasonForFailure());
		Assert.assertEquals(existingIvldGlCostCenter.getGlCostCenterIntfid(),
			newIvldGlCostCenter.getGlCostCenterIntfid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldGlCostCenter.getModifiedDate()),
			Time.getShortTimestamp(newIvldGlCostCenter.getModifiedDate()));
		Assert.assertEquals(existingIvldGlCostCenter.getCompanyCostCenter(),
			newIvldGlCostCenter.getCompanyCostCenter());
		Assert.assertEquals(existingIvldGlCostCenter.getUploadDate(),
			newIvldGlCostCenter.getUploadDate());
		Assert.assertEquals(existingIvldGlCostCenter.getCreatedBy(),
			newIvldGlCostCenter.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldGlCostCenter.getCreatedDate()),
			Time.getShortTimestamp(newIvldGlCostCenter.getCreatedDate()));
		Assert.assertEquals(existingIvldGlCostCenter.getSource(),
			newIvldGlCostCenter.getSource());
		Assert.assertEquals(existingIvldGlCostCenter.getBatchId(),
			newIvldGlCostCenter.getBatchId());
		Assert.assertEquals(existingIvldGlCostCenter.getAddChgDelIndicator(),
			newIvldGlCostCenter.getAddChgDelIndicator());
		Assert.assertEquals(existingIvldGlCostCenter.getIvldGlCostCenterSid(),
			newIvldGlCostCenter.getIvldGlCostCenterSid());
		Assert.assertEquals(existingIvldGlCostCenter.getErrorField(),
			newIvldGlCostCenter.getErrorField());
		Assert.assertEquals(existingIvldGlCostCenter.getErrorCode(),
			newIvldGlCostCenter.getErrorCode());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldGlCostCenter.getIntfInsertedDate()),
			Time.getShortTimestamp(newIvldGlCostCenter.getIntfInsertedDate()));
		Assert.assertEquals(existingIvldGlCostCenter.getCompanyCode(),
			newIvldGlCostCenter.getCompanyCode());
		Assert.assertEquals(existingIvldGlCostCenter.getModifiedBy(),
			newIvldGlCostCenter.getModifiedBy());
		Assert.assertEquals(existingIvldGlCostCenter.getReprocessedFlag(),
			newIvldGlCostCenter.getReprocessedFlag());
		Assert.assertEquals(existingIvldGlCostCenter.getNdc8(),
			newIvldGlCostCenter.getNdc8());
		Assert.assertEquals(existingIvldGlCostCenter.getCheckRecord(),
			newIvldGlCostCenter.getCheckRecord());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		IvldGlCostCenter newIvldGlCostCenter = addIvldGlCostCenter();

		IvldGlCostCenter existingIvldGlCostCenter = _persistence.findByPrimaryKey(newIvldGlCostCenter.getPrimaryKey());

		Assert.assertEquals(existingIvldGlCostCenter, newIvldGlCostCenter);
	}

	@Test(expected = NoSuchIvldGlCostCenterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<IvldGlCostCenter> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("IVLD_GL_COST_CENTER",
			"reasonForFailure", true, "glCostCenterIntfid", true,
			"modifiedDate", true, "companyCostCenter", true, "uploadDate",
			true, "createdBy", true, "createdDate", true, "source", true,
			"batchId", true, "addChgDelIndicator", true, "ivldGlCostCenterSid",
			true, "errorField", true, "errorCode", true, "intfInsertedDate",
			true, "companyCode", true, "modifiedBy", true, "reprocessedFlag",
			true, "ndc8", true, "checkRecord", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		IvldGlCostCenter newIvldGlCostCenter = addIvldGlCostCenter();

		IvldGlCostCenter existingIvldGlCostCenter = _persistence.fetchByPrimaryKey(newIvldGlCostCenter.getPrimaryKey());

		Assert.assertEquals(existingIvldGlCostCenter, newIvldGlCostCenter);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldGlCostCenter missingIvldGlCostCenter = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingIvldGlCostCenter);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		IvldGlCostCenter newIvldGlCostCenter1 = addIvldGlCostCenter();
		IvldGlCostCenter newIvldGlCostCenter2 = addIvldGlCostCenter();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldGlCostCenter1.getPrimaryKey());
		primaryKeys.add(newIvldGlCostCenter2.getPrimaryKey());

		Map<Serializable, IvldGlCostCenter> ivldGlCostCenters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ivldGlCostCenters.size());
		Assert.assertEquals(newIvldGlCostCenter1,
			ivldGlCostCenters.get(newIvldGlCostCenter1.getPrimaryKey()));
		Assert.assertEquals(newIvldGlCostCenter2,
			ivldGlCostCenters.get(newIvldGlCostCenter2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, IvldGlCostCenter> ivldGlCostCenters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldGlCostCenters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		IvldGlCostCenter newIvldGlCostCenter = addIvldGlCostCenter();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldGlCostCenter.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, IvldGlCostCenter> ivldGlCostCenters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldGlCostCenters.size());
		Assert.assertEquals(newIvldGlCostCenter,
			ivldGlCostCenters.get(newIvldGlCostCenter.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, IvldGlCostCenter> ivldGlCostCenters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldGlCostCenters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		IvldGlCostCenter newIvldGlCostCenter = addIvldGlCostCenter();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldGlCostCenter.getPrimaryKey());

		Map<Serializable, IvldGlCostCenter> ivldGlCostCenters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldGlCostCenters.size());
		Assert.assertEquals(newIvldGlCostCenter,
			ivldGlCostCenters.get(newIvldGlCostCenter.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = IvldGlCostCenterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<IvldGlCostCenter>() {
				@Override
				public void performAction(IvldGlCostCenter ivldGlCostCenter) {
					Assert.assertNotNull(ivldGlCostCenter);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		IvldGlCostCenter newIvldGlCostCenter = addIvldGlCostCenter();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldGlCostCenter.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldGlCostCenterSid",
				newIvldGlCostCenter.getIvldGlCostCenterSid()));

		List<IvldGlCostCenter> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		IvldGlCostCenter existingIvldGlCostCenter = result.get(0);

		Assert.assertEquals(existingIvldGlCostCenter, newIvldGlCostCenter);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldGlCostCenter.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldGlCostCenterSid",
				RandomTestUtil.nextInt()));

		List<IvldGlCostCenter> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		IvldGlCostCenter newIvldGlCostCenter = addIvldGlCostCenter();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldGlCostCenter.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldGlCostCenterSid"));

		Object newIvldGlCostCenterSid = newIvldGlCostCenter.getIvldGlCostCenterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldGlCostCenterSid",
				new Object[] { newIvldGlCostCenterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingIvldGlCostCenterSid = result.get(0);

		Assert.assertEquals(existingIvldGlCostCenterSid, newIvldGlCostCenterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldGlCostCenter.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldGlCostCenterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldGlCostCenterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected IvldGlCostCenter addIvldGlCostCenter() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldGlCostCenter ivldGlCostCenter = _persistence.create(pk);

		ivldGlCostCenter.setReasonForFailure(RandomTestUtil.randomString());

		ivldGlCostCenter.setGlCostCenterIntfid(RandomTestUtil.randomString());

		ivldGlCostCenter.setModifiedDate(RandomTestUtil.nextDate());

		ivldGlCostCenter.setCompanyCostCenter(RandomTestUtil.randomString());

		ivldGlCostCenter.setUploadDate(RandomTestUtil.randomString());

		ivldGlCostCenter.setCreatedBy(RandomTestUtil.randomString());

		ivldGlCostCenter.setCreatedDate(RandomTestUtil.nextDate());

		ivldGlCostCenter.setSource(RandomTestUtil.randomString());

		ivldGlCostCenter.setBatchId(RandomTestUtil.randomString());

		ivldGlCostCenter.setAddChgDelIndicator(RandomTestUtil.randomString());

		ivldGlCostCenter.setErrorField(RandomTestUtil.randomString());

		ivldGlCostCenter.setErrorCode(RandomTestUtil.randomString());

		ivldGlCostCenter.setIntfInsertedDate(RandomTestUtil.nextDate());

		ivldGlCostCenter.setCompanyCode(RandomTestUtil.randomString());

		ivldGlCostCenter.setModifiedBy(RandomTestUtil.randomString());

		ivldGlCostCenter.setReprocessedFlag(RandomTestUtil.randomString());

		ivldGlCostCenter.setNdc8(RandomTestUtil.randomString());

		ivldGlCostCenter.setCheckRecord(RandomTestUtil.randomBoolean());

		_ivldGlCostCenters.add(_persistence.update(ivldGlCostCenter));

		return ivldGlCostCenter;
	}

	private List<IvldGlCostCenter> _ivldGlCostCenters = new ArrayList<IvldGlCostCenter>();
	private IvldGlCostCenterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}