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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchPsModelException;
import com.stpl.app.model.PsModel;
import com.stpl.app.service.PsModelLocalServiceUtil;
import com.stpl.app.service.persistence.PsModelPersistence;
import com.stpl.app.service.persistence.PsModelUtil;

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
public class PsModelPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = PsModelUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<PsModel> iterator = _psModels.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		PsModel psModel = _persistence.create(pk);

		Assert.assertNotNull(psModel);

		Assert.assertEquals(psModel.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		PsModel newPsModel = addPsModel();

		_persistence.remove(newPsModel);

		PsModel existingPsModel = _persistence.fetchByPrimaryKey(newPsModel.getPrimaryKey());

		Assert.assertNull(existingPsModel);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addPsModel();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		PsModel newPsModel = _persistence.create(pk);

		newPsModel.setPsId(RandomTestUtil.randomString());

		newPsModel.setPsName(RandomTestUtil.randomString());

		newPsModel.setPsType(RandomTestUtil.nextInt());

		newPsModel.setModifiedDate(RandomTestUtil.nextDate());

		newPsModel.setPsCategory(RandomTestUtil.nextInt());

		newPsModel.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newPsModel.setPsStatus(RandomTestUtil.nextInt());

		newPsModel.setCreatedDate(RandomTestUtil.nextDate());

		newPsModel.setCreatedBy(RandomTestUtil.nextInt());

		newPsModel.setSource(RandomTestUtil.randomString());

		newPsModel.setPsNo(RandomTestUtil.randomString());

		newPsModel.setPsDesignation(RandomTestUtil.randomString());

		newPsModel.setParentPsId(RandomTestUtil.randomString());

		newPsModel.setBatchId(RandomTestUtil.randomString());

		newPsModel.setPsEndDate(RandomTestUtil.nextDate());

		newPsModel.setPsTradeClass(RandomTestUtil.nextInt());

		newPsModel.setModifiedBy(RandomTestUtil.nextInt());

		newPsModel.setInboundStatus(RandomTestUtil.randomString());

		newPsModel.setPsStartDate(RandomTestUtil.nextDate());

		newPsModel.setParentPsName(RandomTestUtil.randomString());

		newPsModel.setInternalNotes(RandomTestUtil.randomString());

		_psModels.add(_persistence.update(newPsModel));

		PsModel existingPsModel = _persistence.findByPrimaryKey(newPsModel.getPrimaryKey());

		Assert.assertEquals(existingPsModel.getPsId(), newPsModel.getPsId());
		Assert.assertEquals(existingPsModel.getPsName(), newPsModel.getPsName());
		Assert.assertEquals(existingPsModel.getPsType(), newPsModel.getPsType());
		Assert.assertEquals(Time.getShortTimestamp(
				existingPsModel.getModifiedDate()),
			Time.getShortTimestamp(newPsModel.getModifiedDate()));
		Assert.assertEquals(existingPsModel.getPsCategory(),
			newPsModel.getPsCategory());
		Assert.assertEquals(existingPsModel.getRecordLockStatus(),
			newPsModel.getRecordLockStatus());
		Assert.assertEquals(existingPsModel.getPsStatus(),
			newPsModel.getPsStatus());
		Assert.assertEquals(Time.getShortTimestamp(
				existingPsModel.getCreatedDate()),
			Time.getShortTimestamp(newPsModel.getCreatedDate()));
		Assert.assertEquals(existingPsModel.getCreatedBy(),
			newPsModel.getCreatedBy());
		Assert.assertEquals(existingPsModel.getSource(), newPsModel.getSource());
		Assert.assertEquals(existingPsModel.getPsNo(), newPsModel.getPsNo());
		Assert.assertEquals(existingPsModel.getPsDesignation(),
			newPsModel.getPsDesignation());
		Assert.assertEquals(existingPsModel.getParentPsId(),
			newPsModel.getParentPsId());
		Assert.assertEquals(existingPsModel.getBatchId(),
			newPsModel.getBatchId());
		Assert.assertEquals(existingPsModel.getPsModelSid(),
			newPsModel.getPsModelSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingPsModel.getPsEndDate()),
			Time.getShortTimestamp(newPsModel.getPsEndDate()));
		Assert.assertEquals(existingPsModel.getPsTradeClass(),
			newPsModel.getPsTradeClass());
		Assert.assertEquals(existingPsModel.getModifiedBy(),
			newPsModel.getModifiedBy());
		Assert.assertEquals(existingPsModel.getInboundStatus(),
			newPsModel.getInboundStatus());
		Assert.assertEquals(Time.getShortTimestamp(
				existingPsModel.getPsStartDate()),
			Time.getShortTimestamp(newPsModel.getPsStartDate()));
		Assert.assertEquals(existingPsModel.getParentPsName(),
			newPsModel.getParentPsName());
		Assert.assertEquals(existingPsModel.getInternalNotes(),
			newPsModel.getInternalNotes());
	}

	@Test
	public void testCountBypsId() throws Exception {
		_persistence.countBypsId(StringPool.BLANK);

		_persistence.countBypsId(StringPool.NULL);

		_persistence.countBypsId((String)null);
	}

	@Test
	public void testCountBypsNo() throws Exception {
		_persistence.countBypsNo(StringPool.BLANK);

		_persistence.countBypsNo(StringPool.NULL);

		_persistence.countBypsNo((String)null);
	}

	@Test
	public void testCountBypsName() throws Exception {
		_persistence.countBypsName(StringPool.BLANK);

		_persistence.countBypsName(StringPool.NULL);

		_persistence.countBypsName((String)null);
	}

	@Test
	public void testCountBypsType() throws Exception {
		_persistence.countBypsType(RandomTestUtil.nextInt());

		_persistence.countBypsType(0);
	}

	@Test
	public void testCountBypsStatus() throws Exception {
		_persistence.countBypsStatus(RandomTestUtil.nextInt());

		_persistence.countBypsStatus(0);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		PsModel newPsModel = addPsModel();

		PsModel existingPsModel = _persistence.findByPrimaryKey(newPsModel.getPrimaryKey());

		Assert.assertEquals(existingPsModel, newPsModel);
	}

	@Test(expected = NoSuchPsModelException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<PsModel> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("PS_MODEL", "psId", true,
			"psName", true, "psType", true, "modifiedDate", true, "psCategory",
			true, "recordLockStatus", true, "psStatus", true, "createdDate",
			true, "createdBy", true, "source", true, "psNo", true,
			"psDesignation", true, "parentPsId", true, "batchId", true,
			"psModelSid", true, "psEndDate", true, "psTradeClass", true,
			"modifiedBy", true, "inboundStatus", true, "psStartDate", true,
			"parentPsName", true, "internalNotes", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		PsModel newPsModel = addPsModel();

		PsModel existingPsModel = _persistence.fetchByPrimaryKey(newPsModel.getPrimaryKey());

		Assert.assertEquals(existingPsModel, newPsModel);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		PsModel missingPsModel = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingPsModel);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		PsModel newPsModel1 = addPsModel();
		PsModel newPsModel2 = addPsModel();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newPsModel1.getPrimaryKey());
		primaryKeys.add(newPsModel2.getPrimaryKey());

		Map<Serializable, PsModel> psModels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, psModels.size());
		Assert.assertEquals(newPsModel1,
			psModels.get(newPsModel1.getPrimaryKey()));
		Assert.assertEquals(newPsModel2,
			psModels.get(newPsModel2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, PsModel> psModels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(psModels.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		PsModel newPsModel = addPsModel();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newPsModel.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, PsModel> psModels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, psModels.size());
		Assert.assertEquals(newPsModel, psModels.get(newPsModel.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, PsModel> psModels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(psModels.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		PsModel newPsModel = addPsModel();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newPsModel.getPrimaryKey());

		Map<Serializable, PsModel> psModels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, psModels.size());
		Assert.assertEquals(newPsModel, psModels.get(newPsModel.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = PsModelLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<PsModel>() {
				@Override
				public void performAction(PsModel psModel) {
					Assert.assertNotNull(psModel);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		PsModel newPsModel = addPsModel();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PsModel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("psModelSid",
				newPsModel.getPsModelSid()));

		List<PsModel> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		PsModel existingPsModel = result.get(0);

		Assert.assertEquals(existingPsModel, newPsModel);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PsModel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("psModelSid",
				RandomTestUtil.nextInt()));

		List<PsModel> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		PsModel newPsModel = addPsModel();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PsModel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("psModelSid"));

		Object newPsModelSid = newPsModel.getPsModelSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("psModelSid",
				new Object[] { newPsModelSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingPsModelSid = result.get(0);

		Assert.assertEquals(existingPsModelSid, newPsModelSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PsModel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("psModelSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("psModelSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected PsModel addPsModel() throws Exception {
		int pk = RandomTestUtil.nextInt();

		PsModel psModel = _persistence.create(pk);

		psModel.setPsId(RandomTestUtil.randomString());

		psModel.setPsName(RandomTestUtil.randomString());

		psModel.setPsType(RandomTestUtil.nextInt());

		psModel.setModifiedDate(RandomTestUtil.nextDate());

		psModel.setPsCategory(RandomTestUtil.nextInt());

		psModel.setRecordLockStatus(RandomTestUtil.randomBoolean());

		psModel.setPsStatus(RandomTestUtil.nextInt());

		psModel.setCreatedDate(RandomTestUtil.nextDate());

		psModel.setCreatedBy(RandomTestUtil.nextInt());

		psModel.setSource(RandomTestUtil.randomString());

		psModel.setPsNo(RandomTestUtil.randomString());

		psModel.setPsDesignation(RandomTestUtil.randomString());

		psModel.setParentPsId(RandomTestUtil.randomString());

		psModel.setBatchId(RandomTestUtil.randomString());

		psModel.setPsEndDate(RandomTestUtil.nextDate());

		psModel.setPsTradeClass(RandomTestUtil.nextInt());

		psModel.setModifiedBy(RandomTestUtil.nextInt());

		psModel.setInboundStatus(RandomTestUtil.randomString());

		psModel.setPsStartDate(RandomTestUtil.nextDate());

		psModel.setParentPsName(RandomTestUtil.randomString());

		psModel.setInternalNotes(RandomTestUtil.randomString());

		_psModels.add(_persistence.update(psModel));

		return psModel;
	}

	private List<PsModel> _psModels = new ArrayList<PsModel>();
	private PsModelPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}