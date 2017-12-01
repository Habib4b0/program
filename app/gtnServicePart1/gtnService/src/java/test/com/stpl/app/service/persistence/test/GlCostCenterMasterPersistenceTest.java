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

import com.stpl.app.exception.NoSuchGlCostCenterMasterException;
import com.stpl.app.model.GlCostCenterMaster;
import com.stpl.app.service.GlCostCenterMasterLocalServiceUtil;
import com.stpl.app.service.persistence.GlCostCenterMasterPersistence;
import com.stpl.app.service.persistence.GlCostCenterMasterUtil;

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
public class GlCostCenterMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = GlCostCenterMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<GlCostCenterMaster> iterator = _glCostCenterMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		GlCostCenterMaster glCostCenterMaster = _persistence.create(pk);

		Assert.assertNotNull(glCostCenterMaster);

		Assert.assertEquals(glCostCenterMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		GlCostCenterMaster newGlCostCenterMaster = addGlCostCenterMaster();

		_persistence.remove(newGlCostCenterMaster);

		GlCostCenterMaster existingGlCostCenterMaster = _persistence.fetchByPrimaryKey(newGlCostCenterMaster.getPrimaryKey());

		Assert.assertNull(existingGlCostCenterMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addGlCostCenterMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		GlCostCenterMaster newGlCostCenterMaster = _persistence.create(pk);

		newGlCostCenterMaster.setCreatedBy(RandomTestUtil.nextInt());

		newGlCostCenterMaster.setModifiedBy(RandomTestUtil.nextInt());

		newGlCostCenterMaster.setUploadDate(RandomTestUtil.nextDate());

		newGlCostCenterMaster.setCreatedDate(RandomTestUtil.nextDate());

		newGlCostCenterMaster.setBatchId(RandomTestUtil.randomString());

		newGlCostCenterMaster.setModifiedDate(RandomTestUtil.nextDate());

		newGlCostCenterMaster.setNdc8(RandomTestUtil.randomString());

		newGlCostCenterMaster.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newGlCostCenterMaster.setCompanyCode(RandomTestUtil.randomString());

		newGlCostCenterMaster.setSource(RandomTestUtil.randomString());

		newGlCostCenterMaster.setCompanyCostCenter(RandomTestUtil.randomString());

		newGlCostCenterMaster.setInboundStatus(RandomTestUtil.randomString());

		_glCostCenterMasters.add(_persistence.update(newGlCostCenterMaster));

		GlCostCenterMaster existingGlCostCenterMaster = _persistence.findByPrimaryKey(newGlCostCenterMaster.getPrimaryKey());

		Assert.assertEquals(existingGlCostCenterMaster.getCreatedBy(),
			newGlCostCenterMaster.getCreatedBy());
		Assert.assertEquals(existingGlCostCenterMaster.getModifiedBy(),
			newGlCostCenterMaster.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingGlCostCenterMaster.getUploadDate()),
			Time.getShortTimestamp(newGlCostCenterMaster.getUploadDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingGlCostCenterMaster.getCreatedDate()),
			Time.getShortTimestamp(newGlCostCenterMaster.getCreatedDate()));
		Assert.assertEquals(existingGlCostCenterMaster.getGlCostCenterMasterSid(),
			newGlCostCenterMaster.getGlCostCenterMasterSid());
		Assert.assertEquals(existingGlCostCenterMaster.getBatchId(),
			newGlCostCenterMaster.getBatchId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingGlCostCenterMaster.getModifiedDate()),
			Time.getShortTimestamp(newGlCostCenterMaster.getModifiedDate()));
		Assert.assertEquals(existingGlCostCenterMaster.getNdc8(),
			newGlCostCenterMaster.getNdc8());
		Assert.assertEquals(existingGlCostCenterMaster.getRecordLockStatus(),
			newGlCostCenterMaster.getRecordLockStatus());
		Assert.assertEquals(existingGlCostCenterMaster.getCompanyCode(),
			newGlCostCenterMaster.getCompanyCode());
		Assert.assertEquals(existingGlCostCenterMaster.getSource(),
			newGlCostCenterMaster.getSource());
		Assert.assertEquals(existingGlCostCenterMaster.getCompanyCostCenter(),
			newGlCostCenterMaster.getCompanyCostCenter());
		Assert.assertEquals(existingGlCostCenterMaster.getInboundStatus(),
			newGlCostCenterMaster.getInboundStatus());
	}

	@Test
	public void testCountByCompanyCostCenter() throws Exception {
		_persistence.countByCompanyCostCenter(StringPool.BLANK);

		_persistence.countByCompanyCostCenter(StringPool.NULL);

		_persistence.countByCompanyCostCenter((String)null);
	}

	@Test
	public void testCountByNdc8() throws Exception {
		_persistence.countByNdc8(StringPool.BLANK);

		_persistence.countByNdc8(StringPool.NULL);

		_persistence.countByNdc8((String)null);
	}

	@Test
	public void testCountByCompanyCode() throws Exception {
		_persistence.countByCompanyCode(StringPool.BLANK);

		_persistence.countByCompanyCode(StringPool.NULL);

		_persistence.countByCompanyCode((String)null);
	}

	@Test
	public void testCountByGlCostCenterUnique() throws Exception {
		_persistence.countByGlCostCenterUnique(StringPool.BLANK,
			StringPool.BLANK, StringPool.BLANK);

		_persistence.countByGlCostCenterUnique(StringPool.NULL,
			StringPool.NULL, StringPool.NULL);

		_persistence.countByGlCostCenterUnique((String)null, (String)null,
			(String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		GlCostCenterMaster newGlCostCenterMaster = addGlCostCenterMaster();

		GlCostCenterMaster existingGlCostCenterMaster = _persistence.findByPrimaryKey(newGlCostCenterMaster.getPrimaryKey());

		Assert.assertEquals(existingGlCostCenterMaster, newGlCostCenterMaster);
	}

	@Test(expected = NoSuchGlCostCenterMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<GlCostCenterMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("GL_COST_CENTER_MASTER",
			"createdBy", true, "modifiedBy", true, "uploadDate", true,
			"createdDate", true, "glCostCenterMasterSid", true, "batchId",
			true, "modifiedDate", true, "ndc8", true, "recordLockStatus", true,
			"companyCode", true, "source", true, "companyCostCenter", true,
			"inboundStatus", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		GlCostCenterMaster newGlCostCenterMaster = addGlCostCenterMaster();

		GlCostCenterMaster existingGlCostCenterMaster = _persistence.fetchByPrimaryKey(newGlCostCenterMaster.getPrimaryKey());

		Assert.assertEquals(existingGlCostCenterMaster, newGlCostCenterMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		GlCostCenterMaster missingGlCostCenterMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingGlCostCenterMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		GlCostCenterMaster newGlCostCenterMaster1 = addGlCostCenterMaster();
		GlCostCenterMaster newGlCostCenterMaster2 = addGlCostCenterMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newGlCostCenterMaster1.getPrimaryKey());
		primaryKeys.add(newGlCostCenterMaster2.getPrimaryKey());

		Map<Serializable, GlCostCenterMaster> glCostCenterMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, glCostCenterMasters.size());
		Assert.assertEquals(newGlCostCenterMaster1,
			glCostCenterMasters.get(newGlCostCenterMaster1.getPrimaryKey()));
		Assert.assertEquals(newGlCostCenterMaster2,
			glCostCenterMasters.get(newGlCostCenterMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, GlCostCenterMaster> glCostCenterMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(glCostCenterMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		GlCostCenterMaster newGlCostCenterMaster = addGlCostCenterMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newGlCostCenterMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, GlCostCenterMaster> glCostCenterMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, glCostCenterMasters.size());
		Assert.assertEquals(newGlCostCenterMaster,
			glCostCenterMasters.get(newGlCostCenterMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, GlCostCenterMaster> glCostCenterMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(glCostCenterMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		GlCostCenterMaster newGlCostCenterMaster = addGlCostCenterMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newGlCostCenterMaster.getPrimaryKey());

		Map<Serializable, GlCostCenterMaster> glCostCenterMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, glCostCenterMasters.size());
		Assert.assertEquals(newGlCostCenterMaster,
			glCostCenterMasters.get(newGlCostCenterMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = GlCostCenterMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<GlCostCenterMaster>() {
				@Override
				public void performAction(GlCostCenterMaster glCostCenterMaster) {
					Assert.assertNotNull(glCostCenterMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		GlCostCenterMaster newGlCostCenterMaster = addGlCostCenterMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(GlCostCenterMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("glCostCenterMasterSid",
				newGlCostCenterMaster.getGlCostCenterMasterSid()));

		List<GlCostCenterMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		GlCostCenterMaster existingGlCostCenterMaster = result.get(0);

		Assert.assertEquals(existingGlCostCenterMaster, newGlCostCenterMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(GlCostCenterMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("glCostCenterMasterSid",
				RandomTestUtil.nextInt()));

		List<GlCostCenterMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		GlCostCenterMaster newGlCostCenterMaster = addGlCostCenterMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(GlCostCenterMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"glCostCenterMasterSid"));

		Object newGlCostCenterMasterSid = newGlCostCenterMaster.getGlCostCenterMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("glCostCenterMasterSid",
				new Object[] { newGlCostCenterMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingGlCostCenterMasterSid = result.get(0);

		Assert.assertEquals(existingGlCostCenterMasterSid,
			newGlCostCenterMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(GlCostCenterMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"glCostCenterMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("glCostCenterMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected GlCostCenterMaster addGlCostCenterMaster()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		GlCostCenterMaster glCostCenterMaster = _persistence.create(pk);

		glCostCenterMaster.setCreatedBy(RandomTestUtil.nextInt());

		glCostCenterMaster.setModifiedBy(RandomTestUtil.nextInt());

		glCostCenterMaster.setUploadDate(RandomTestUtil.nextDate());

		glCostCenterMaster.setCreatedDate(RandomTestUtil.nextDate());

		glCostCenterMaster.setBatchId(RandomTestUtil.randomString());

		glCostCenterMaster.setModifiedDate(RandomTestUtil.nextDate());

		glCostCenterMaster.setNdc8(RandomTestUtil.randomString());

		glCostCenterMaster.setRecordLockStatus(RandomTestUtil.randomBoolean());

		glCostCenterMaster.setCompanyCode(RandomTestUtil.randomString());

		glCostCenterMaster.setSource(RandomTestUtil.randomString());

		glCostCenterMaster.setCompanyCostCenter(RandomTestUtil.randomString());

		glCostCenterMaster.setInboundStatus(RandomTestUtil.randomString());

		_glCostCenterMasters.add(_persistence.update(glCostCenterMaster));

		return glCostCenterMaster;
	}

	private List<GlCostCenterMaster> _glCostCenterMasters = new ArrayList<GlCostCenterMaster>();
	private GlCostCenterMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}