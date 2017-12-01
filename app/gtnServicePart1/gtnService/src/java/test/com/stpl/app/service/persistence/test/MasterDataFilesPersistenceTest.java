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

import com.stpl.app.exception.NoSuchMasterDataFilesException;
import com.stpl.app.model.MasterDataFiles;
import com.stpl.app.service.MasterDataFilesLocalServiceUtil;
import com.stpl.app.service.persistence.MasterDataFilesPersistence;
import com.stpl.app.service.persistence.MasterDataFilesUtil;

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
public class MasterDataFilesPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = MasterDataFilesUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<MasterDataFiles> iterator = _masterDataFileses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		MasterDataFiles masterDataFiles = _persistence.create(pk);

		Assert.assertNotNull(masterDataFiles);

		Assert.assertEquals(masterDataFiles.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		MasterDataFiles newMasterDataFiles = addMasterDataFiles();

		_persistence.remove(newMasterDataFiles);

		MasterDataFiles existingMasterDataFiles = _persistence.fetchByPrimaryKey(newMasterDataFiles.getPrimaryKey());

		Assert.assertNull(existingMasterDataFiles);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addMasterDataFiles();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		MasterDataFiles newMasterDataFiles = _persistence.create(pk);

		newMasterDataFiles.setMasterTableSid(RandomTestUtil.nextInt());

		newMasterDataFiles.setMasterTableName(RandomTestUtil.randomString());

		newMasterDataFiles.setFilePath(RandomTestUtil.randomString());

		newMasterDataFiles.setCreatedDate(RandomTestUtil.nextDate());

		newMasterDataFiles.setCreatedBy(RandomTestUtil.nextInt());

		_masterDataFileses.add(_persistence.update(newMasterDataFiles));

		MasterDataFiles existingMasterDataFiles = _persistence.findByPrimaryKey(newMasterDataFiles.getPrimaryKey());

		Assert.assertEquals(existingMasterDataFiles.getMasterTableSid(),
			newMasterDataFiles.getMasterTableSid());
		Assert.assertEquals(existingMasterDataFiles.getMasterDataFilesSid(),
			newMasterDataFiles.getMasterDataFilesSid());
		Assert.assertEquals(existingMasterDataFiles.getMasterTableName(),
			newMasterDataFiles.getMasterTableName());
		Assert.assertEquals(existingMasterDataFiles.getFilePath(),
			newMasterDataFiles.getFilePath());
		Assert.assertEquals(Time.getShortTimestamp(
				existingMasterDataFiles.getCreatedDate()),
			Time.getShortTimestamp(newMasterDataFiles.getCreatedDate()));
		Assert.assertEquals(existingMasterDataFiles.getCreatedBy(),
			newMasterDataFiles.getCreatedBy());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		MasterDataFiles newMasterDataFiles = addMasterDataFiles();

		MasterDataFiles existingMasterDataFiles = _persistence.findByPrimaryKey(newMasterDataFiles.getPrimaryKey());

		Assert.assertEquals(existingMasterDataFiles, newMasterDataFiles);
	}

	@Test(expected = NoSuchMasterDataFilesException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<MasterDataFiles> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("MASTER_DATA_FILES",
			"masterTableSid", true, "masterDataFilesSid", true,
			"masterTableName", true, "filePath", true, "createdDate", true,
			"createdBy", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		MasterDataFiles newMasterDataFiles = addMasterDataFiles();

		MasterDataFiles existingMasterDataFiles = _persistence.fetchByPrimaryKey(newMasterDataFiles.getPrimaryKey());

		Assert.assertEquals(existingMasterDataFiles, newMasterDataFiles);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		MasterDataFiles missingMasterDataFiles = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingMasterDataFiles);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		MasterDataFiles newMasterDataFiles1 = addMasterDataFiles();
		MasterDataFiles newMasterDataFiles2 = addMasterDataFiles();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMasterDataFiles1.getPrimaryKey());
		primaryKeys.add(newMasterDataFiles2.getPrimaryKey());

		Map<Serializable, MasterDataFiles> masterDataFileses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, masterDataFileses.size());
		Assert.assertEquals(newMasterDataFiles1,
			masterDataFileses.get(newMasterDataFiles1.getPrimaryKey()));
		Assert.assertEquals(newMasterDataFiles2,
			masterDataFileses.get(newMasterDataFiles2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, MasterDataFiles> masterDataFileses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(masterDataFileses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		MasterDataFiles newMasterDataFiles = addMasterDataFiles();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMasterDataFiles.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, MasterDataFiles> masterDataFileses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, masterDataFileses.size());
		Assert.assertEquals(newMasterDataFiles,
			masterDataFileses.get(newMasterDataFiles.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, MasterDataFiles> masterDataFileses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(masterDataFileses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		MasterDataFiles newMasterDataFiles = addMasterDataFiles();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMasterDataFiles.getPrimaryKey());

		Map<Serializable, MasterDataFiles> masterDataFileses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, masterDataFileses.size());
		Assert.assertEquals(newMasterDataFiles,
			masterDataFileses.get(newMasterDataFiles.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = MasterDataFilesLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<MasterDataFiles>() {
				@Override
				public void performAction(MasterDataFiles masterDataFiles) {
					Assert.assertNotNull(masterDataFiles);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		MasterDataFiles newMasterDataFiles = addMasterDataFiles();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MasterDataFiles.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("masterDataFilesSid",
				newMasterDataFiles.getMasterDataFilesSid()));

		List<MasterDataFiles> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		MasterDataFiles existingMasterDataFiles = result.get(0);

		Assert.assertEquals(existingMasterDataFiles, newMasterDataFiles);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MasterDataFiles.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("masterDataFilesSid",
				RandomTestUtil.nextInt()));

		List<MasterDataFiles> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		MasterDataFiles newMasterDataFiles = addMasterDataFiles();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MasterDataFiles.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"masterDataFilesSid"));

		Object newMasterDataFilesSid = newMasterDataFiles.getMasterDataFilesSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("masterDataFilesSid",
				new Object[] { newMasterDataFilesSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingMasterDataFilesSid = result.get(0);

		Assert.assertEquals(existingMasterDataFilesSid, newMasterDataFilesSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MasterDataFiles.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"masterDataFilesSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("masterDataFilesSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected MasterDataFiles addMasterDataFiles() throws Exception {
		int pk = RandomTestUtil.nextInt();

		MasterDataFiles masterDataFiles = _persistence.create(pk);

		masterDataFiles.setMasterTableSid(RandomTestUtil.nextInt());

		masterDataFiles.setMasterTableName(RandomTestUtil.randomString());

		masterDataFiles.setFilePath(RandomTestUtil.randomString());

		masterDataFiles.setCreatedDate(RandomTestUtil.nextDate());

		masterDataFiles.setCreatedBy(RandomTestUtil.nextInt());

		_masterDataFileses.add(_persistence.update(masterDataFiles));

		return masterDataFiles;
	}

	private List<MasterDataFiles> _masterDataFileses = new ArrayList<MasterDataFiles>();
	private MasterDataFilesPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}