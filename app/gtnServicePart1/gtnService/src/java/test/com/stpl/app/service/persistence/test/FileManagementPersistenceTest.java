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

import com.stpl.app.exception.NoSuchFileManagementException;
import com.stpl.app.model.FileManagement;
import com.stpl.app.service.FileManagementLocalServiceUtil;
import com.stpl.app.service.persistence.FileManagementPersistence;
import com.stpl.app.service.persistence.FileManagementUtil;

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
public class FileManagementPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = FileManagementUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<FileManagement> iterator = _fileManagements.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		FileManagement fileManagement = _persistence.create(pk);

		Assert.assertNotNull(fileManagement);

		Assert.assertEquals(fileManagement.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		FileManagement newFileManagement = addFileManagement();

		_persistence.remove(newFileManagement);

		FileManagement existingFileManagement = _persistence.fetchByPrimaryKey(newFileManagement.getPrimaryKey());

		Assert.assertNull(existingFileManagement);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addFileManagement();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		FileManagement newFileManagement = _persistence.create(pk);

		newFileManagement.setCountry(RandomTestUtil.nextInt());

		newFileManagement.setFromPeriod(RandomTestUtil.nextDate());

		newFileManagement.setVersionNo(RandomTestUtil.nextInt());

		newFileManagement.setForecastSource(RandomTestUtil.randomString());

		newFileManagement.setModifiedDate(RandomTestUtil.nextDate());

		newFileManagement.setCreatedBy(RandomTestUtil.nextInt());

		newFileManagement.setCreatedDate(RandomTestUtil.nextDate());

		newFileManagement.setVersion(RandomTestUtil.randomString());

		newFileManagement.setFileSource(RandomTestUtil.randomString());

		newFileManagement.setToPeriod(RandomTestUtil.nextDate());

		newFileManagement.setModifiedBy(RandomTestUtil.nextInt());

		newFileManagement.setForecastName(RandomTestUtil.randomString());

		newFileManagement.setFileType(RandomTestUtil.nextInt());

		newFileManagement.setBusinessUnit(RandomTestUtil.randomString());

		newFileManagement.setCompany(RandomTestUtil.nextInt());

		_fileManagements.add(_persistence.update(newFileManagement));

		FileManagement existingFileManagement = _persistence.findByPrimaryKey(newFileManagement.getPrimaryKey());

		Assert.assertEquals(existingFileManagement.getCountry(),
			newFileManagement.getCountry());
		Assert.assertEquals(Time.getShortTimestamp(
				existingFileManagement.getFromPeriod()),
			Time.getShortTimestamp(newFileManagement.getFromPeriod()));
		Assert.assertEquals(existingFileManagement.getVersionNo(),
			newFileManagement.getVersionNo());
		Assert.assertEquals(existingFileManagement.getForecastSource(),
			newFileManagement.getForecastSource());
		Assert.assertEquals(Time.getShortTimestamp(
				existingFileManagement.getModifiedDate()),
			Time.getShortTimestamp(newFileManagement.getModifiedDate()));
		Assert.assertEquals(existingFileManagement.getCreatedBy(),
			newFileManagement.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingFileManagement.getCreatedDate()),
			Time.getShortTimestamp(newFileManagement.getCreatedDate()));
		Assert.assertEquals(existingFileManagement.getVersion(),
			newFileManagement.getVersion());
		Assert.assertEquals(existingFileManagement.getFileSource(),
			newFileManagement.getFileSource());
		Assert.assertEquals(Time.getShortTimestamp(
				existingFileManagement.getToPeriod()),
			Time.getShortTimestamp(newFileManagement.getToPeriod()));
		Assert.assertEquals(existingFileManagement.getModifiedBy(),
			newFileManagement.getModifiedBy());
		Assert.assertEquals(existingFileManagement.getFileManagementSid(),
			newFileManagement.getFileManagementSid());
		Assert.assertEquals(existingFileManagement.getForecastName(),
			newFileManagement.getForecastName());
		Assert.assertEquals(existingFileManagement.getFileType(),
			newFileManagement.getFileType());
		Assert.assertEquals(existingFileManagement.getBusinessUnit(),
			newFileManagement.getBusinessUnit());
		Assert.assertEquals(existingFileManagement.getCompany(),
			newFileManagement.getCompany());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		FileManagement newFileManagement = addFileManagement();

		FileManagement existingFileManagement = _persistence.findByPrimaryKey(newFileManagement.getPrimaryKey());

		Assert.assertEquals(existingFileManagement, newFileManagement);
	}

	@Test(expected = NoSuchFileManagementException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<FileManagement> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("FILE_MANAGEMENT",
			"country", true, "fromPeriod", true, "versionNo", true,
			"forecastSource", true, "modifiedDate", true, "createdBy", true,
			"createdDate", true, "version", true, "fileSource", true,
			"toPeriod", true, "modifiedBy", true, "fileManagementSid", true,
			"forecastName", true, "fileType", true, "businessUnit", true,
			"company", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		FileManagement newFileManagement = addFileManagement();

		FileManagement existingFileManagement = _persistence.fetchByPrimaryKey(newFileManagement.getPrimaryKey());

		Assert.assertEquals(existingFileManagement, newFileManagement);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		FileManagement missingFileManagement = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingFileManagement);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		FileManagement newFileManagement1 = addFileManagement();
		FileManagement newFileManagement2 = addFileManagement();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newFileManagement1.getPrimaryKey());
		primaryKeys.add(newFileManagement2.getPrimaryKey());

		Map<Serializable, FileManagement> fileManagements = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, fileManagements.size());
		Assert.assertEquals(newFileManagement1,
			fileManagements.get(newFileManagement1.getPrimaryKey()));
		Assert.assertEquals(newFileManagement2,
			fileManagements.get(newFileManagement2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, FileManagement> fileManagements = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(fileManagements.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		FileManagement newFileManagement = addFileManagement();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newFileManagement.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, FileManagement> fileManagements = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, fileManagements.size());
		Assert.assertEquals(newFileManagement,
			fileManagements.get(newFileManagement.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, FileManagement> fileManagements = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(fileManagements.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		FileManagement newFileManagement = addFileManagement();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newFileManagement.getPrimaryKey());

		Map<Serializable, FileManagement> fileManagements = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, fileManagements.size());
		Assert.assertEquals(newFileManagement,
			fileManagements.get(newFileManagement.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = FileManagementLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<FileManagement>() {
				@Override
				public void performAction(FileManagement fileManagement) {
					Assert.assertNotNull(fileManagement);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		FileManagement newFileManagement = addFileManagement();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(FileManagement.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("fileManagementSid",
				newFileManagement.getFileManagementSid()));

		List<FileManagement> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		FileManagement existingFileManagement = result.get(0);

		Assert.assertEquals(existingFileManagement, newFileManagement);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(FileManagement.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("fileManagementSid",
				RandomTestUtil.nextInt()));

		List<FileManagement> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		FileManagement newFileManagement = addFileManagement();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(FileManagement.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"fileManagementSid"));

		Object newFileManagementSid = newFileManagement.getFileManagementSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("fileManagementSid",
				new Object[] { newFileManagementSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingFileManagementSid = result.get(0);

		Assert.assertEquals(existingFileManagementSid, newFileManagementSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(FileManagement.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"fileManagementSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("fileManagementSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected FileManagement addFileManagement() throws Exception {
		int pk = RandomTestUtil.nextInt();

		FileManagement fileManagement = _persistence.create(pk);

		fileManagement.setCountry(RandomTestUtil.nextInt());

		fileManagement.setFromPeriod(RandomTestUtil.nextDate());

		fileManagement.setVersionNo(RandomTestUtil.nextInt());

		fileManagement.setForecastSource(RandomTestUtil.randomString());

		fileManagement.setModifiedDate(RandomTestUtil.nextDate());

		fileManagement.setCreatedBy(RandomTestUtil.nextInt());

		fileManagement.setCreatedDate(RandomTestUtil.nextDate());

		fileManagement.setVersion(RandomTestUtil.randomString());

		fileManagement.setFileSource(RandomTestUtil.randomString());

		fileManagement.setToPeriod(RandomTestUtil.nextDate());

		fileManagement.setModifiedBy(RandomTestUtil.nextInt());

		fileManagement.setForecastName(RandomTestUtil.randomString());

		fileManagement.setFileType(RandomTestUtil.nextInt());

		fileManagement.setBusinessUnit(RandomTestUtil.randomString());

		fileManagement.setCompany(RandomTestUtil.nextInt());

		_fileManagements.add(_persistence.update(fileManagement));

		return fileManagement;
	}

	private List<FileManagement> _fileManagements = new ArrayList<FileManagement>();
	private FileManagementPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}