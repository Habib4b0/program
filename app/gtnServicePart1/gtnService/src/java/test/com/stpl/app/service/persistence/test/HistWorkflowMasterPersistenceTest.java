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

import com.stpl.app.exception.NoSuchHistWorkflowMasterException;
import com.stpl.app.model.HistWorkflowMaster;
import com.stpl.app.service.HistWorkflowMasterLocalServiceUtil;
import com.stpl.app.service.persistence.HistWorkflowMasterPersistence;
import com.stpl.app.service.persistence.HistWorkflowMasterUtil;

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
public class HistWorkflowMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = HistWorkflowMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<HistWorkflowMaster> iterator = _histWorkflowMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		HistWorkflowMaster histWorkflowMaster = _persistence.create(pk);

		Assert.assertNotNull(histWorkflowMaster);

		Assert.assertEquals(histWorkflowMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		HistWorkflowMaster newHistWorkflowMaster = addHistWorkflowMaster();

		_persistence.remove(newHistWorkflowMaster);

		HistWorkflowMaster existingHistWorkflowMaster = _persistence.fetchByPrimaryKey(newHistWorkflowMaster.getPrimaryKey());

		Assert.assertNull(existingHistWorkflowMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addHistWorkflowMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		HistWorkflowMaster newHistWorkflowMaster = _persistence.create(pk);

		newHistWorkflowMaster.setCreatedBy(RandomTestUtil.nextInt());

		newHistWorkflowMaster.setFileSize(RandomTestUtil.randomString());

		newHistWorkflowMaster.setActionDate(RandomTestUtil.nextDate());

		newHistWorkflowMaster.setWorkflowStatusId(RandomTestUtil.nextInt());

		newHistWorkflowMaster.setActionFlag(RandomTestUtil.randomString());

		newHistWorkflowMaster.setModifiedBy(RandomTestUtil.nextInt());

		newHistWorkflowMaster.setCreatedDate(RandomTestUtil.nextDate());

		newHistWorkflowMaster.setApprovalLevel(RandomTestUtil.nextInt());

		newHistWorkflowMaster.setNoOfApproval(RandomTestUtil.nextInt());

		newHistWorkflowMaster.setFileName(RandomTestUtil.randomString());

		newHistWorkflowMaster.setUploadedBy(RandomTestUtil.randomString());

		newHistWorkflowMaster.setModifiedDate(RandomTestUtil.nextDate());

		newHistWorkflowMaster.setAccClosureMasterSid(RandomTestUtil.nextInt());

		newHistWorkflowMaster.setNotes(RandomTestUtil.randomString());

		newHistWorkflowMaster.setWorkflowId(RandomTestUtil.randomString());

		newHistWorkflowMaster.setProjectionMasterSid(RandomTestUtil.nextInt());

		newHistWorkflowMaster.setUploadedDate(RandomTestUtil.nextDate());

		newHistWorkflowMaster.setFileType(RandomTestUtil.randomString());

		newHistWorkflowMaster.setApprovedBy(RandomTestUtil.nextInt());

		newHistWorkflowMaster.setWorkflowDescrption(RandomTestUtil.randomString());

		newHistWorkflowMaster.setApprovedDate(RandomTestUtil.nextDate());

		_histWorkflowMasters.add(_persistence.update(newHistWorkflowMaster));

		HistWorkflowMaster existingHistWorkflowMaster = _persistence.findByPrimaryKey(newHistWorkflowMaster.getPrimaryKey());

		Assert.assertEquals(existingHistWorkflowMaster.getCreatedBy(),
			newHistWorkflowMaster.getCreatedBy());
		Assert.assertEquals(existingHistWorkflowMaster.getFileSize(),
			newHistWorkflowMaster.getFileSize());
		Assert.assertEquals(Time.getShortTimestamp(
				existingHistWorkflowMaster.getActionDate()),
			Time.getShortTimestamp(newHistWorkflowMaster.getActionDate()));
		Assert.assertEquals(existingHistWorkflowMaster.getWorkflowStatusId(),
			newHistWorkflowMaster.getWorkflowStatusId());
		Assert.assertEquals(existingHistWorkflowMaster.getActionFlag(),
			newHistWorkflowMaster.getActionFlag());
		Assert.assertEquals(existingHistWorkflowMaster.getModifiedBy(),
			newHistWorkflowMaster.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingHistWorkflowMaster.getCreatedDate()),
			Time.getShortTimestamp(newHistWorkflowMaster.getCreatedDate()));
		Assert.assertEquals(existingHistWorkflowMaster.getApprovalLevel(),
			newHistWorkflowMaster.getApprovalLevel());
		Assert.assertEquals(existingHistWorkflowMaster.getNoOfApproval(),
			newHistWorkflowMaster.getNoOfApproval());
		Assert.assertEquals(existingHistWorkflowMaster.getFileName(),
			newHistWorkflowMaster.getFileName());
		Assert.assertEquals(existingHistWorkflowMaster.getUploadedBy(),
			newHistWorkflowMaster.getUploadedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingHistWorkflowMaster.getModifiedDate()),
			Time.getShortTimestamp(newHistWorkflowMaster.getModifiedDate()));
		Assert.assertEquals(existingHistWorkflowMaster.getAccClosureMasterSid(),
			newHistWorkflowMaster.getAccClosureMasterSid());
		Assert.assertEquals(existingHistWorkflowMaster.getNotes(),
			newHistWorkflowMaster.getNotes());
		Assert.assertEquals(existingHistWorkflowMaster.getWorkflowMasterSid(),
			newHistWorkflowMaster.getWorkflowMasterSid());
		Assert.assertEquals(existingHistWorkflowMaster.getWorkflowId(),
			newHistWorkflowMaster.getWorkflowId());
		Assert.assertEquals(existingHistWorkflowMaster.getProjectionMasterSid(),
			newHistWorkflowMaster.getProjectionMasterSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingHistWorkflowMaster.getUploadedDate()),
			Time.getShortTimestamp(newHistWorkflowMaster.getUploadedDate()));
		Assert.assertEquals(existingHistWorkflowMaster.getFileType(),
			newHistWorkflowMaster.getFileType());
		Assert.assertEquals(existingHistWorkflowMaster.getApprovedBy(),
			newHistWorkflowMaster.getApprovedBy());
		Assert.assertEquals(existingHistWorkflowMaster.getWorkflowDescrption(),
			newHistWorkflowMaster.getWorkflowDescrption());
		Assert.assertEquals(Time.getShortTimestamp(
				existingHistWorkflowMaster.getApprovedDate()),
			Time.getShortTimestamp(newHistWorkflowMaster.getApprovedDate()));
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		HistWorkflowMaster newHistWorkflowMaster = addHistWorkflowMaster();

		HistWorkflowMaster existingHistWorkflowMaster = _persistence.findByPrimaryKey(newHistWorkflowMaster.getPrimaryKey());

		Assert.assertEquals(existingHistWorkflowMaster, newHistWorkflowMaster);
	}

	@Test(expected = NoSuchHistWorkflowMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<HistWorkflowMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("HIST_WORKFLOW_MASTER",
			"createdBy", true, "fileSize", true, "actionDate", true,
			"workflowStatusId", true, "actionFlag", true, "modifiedBy", true,
			"createdDate", true, "approvalLevel", true, "noOfApproval", true,
			"fileName", true, "uploadedBy", true, "modifiedDate", true,
			"accClosureMasterSid", true, "notes", true, "workflowMasterSid",
			true, "workflowId", true, "projectionMasterSid", true,
			"uploadedDate", true, "fileType", true, "approvedBy", true,
			"workflowDescrption", true, "approvedDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		HistWorkflowMaster newHistWorkflowMaster = addHistWorkflowMaster();

		HistWorkflowMaster existingHistWorkflowMaster = _persistence.fetchByPrimaryKey(newHistWorkflowMaster.getPrimaryKey());

		Assert.assertEquals(existingHistWorkflowMaster, newHistWorkflowMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		HistWorkflowMaster missingHistWorkflowMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingHistWorkflowMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		HistWorkflowMaster newHistWorkflowMaster1 = addHistWorkflowMaster();
		HistWorkflowMaster newHistWorkflowMaster2 = addHistWorkflowMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHistWorkflowMaster1.getPrimaryKey());
		primaryKeys.add(newHistWorkflowMaster2.getPrimaryKey());

		Map<Serializable, HistWorkflowMaster> histWorkflowMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, histWorkflowMasters.size());
		Assert.assertEquals(newHistWorkflowMaster1,
			histWorkflowMasters.get(newHistWorkflowMaster1.getPrimaryKey()));
		Assert.assertEquals(newHistWorkflowMaster2,
			histWorkflowMasters.get(newHistWorkflowMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, HistWorkflowMaster> histWorkflowMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(histWorkflowMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		HistWorkflowMaster newHistWorkflowMaster = addHistWorkflowMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHistWorkflowMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, HistWorkflowMaster> histWorkflowMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, histWorkflowMasters.size());
		Assert.assertEquals(newHistWorkflowMaster,
			histWorkflowMasters.get(newHistWorkflowMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, HistWorkflowMaster> histWorkflowMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(histWorkflowMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		HistWorkflowMaster newHistWorkflowMaster = addHistWorkflowMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newHistWorkflowMaster.getPrimaryKey());

		Map<Serializable, HistWorkflowMaster> histWorkflowMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, histWorkflowMasters.size());
		Assert.assertEquals(newHistWorkflowMaster,
			histWorkflowMasters.get(newHistWorkflowMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = HistWorkflowMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<HistWorkflowMaster>() {
				@Override
				public void performAction(HistWorkflowMaster histWorkflowMaster) {
					Assert.assertNotNull(histWorkflowMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		HistWorkflowMaster newHistWorkflowMaster = addHistWorkflowMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HistWorkflowMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("workflowMasterSid",
				newHistWorkflowMaster.getWorkflowMasterSid()));

		List<HistWorkflowMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		HistWorkflowMaster existingHistWorkflowMaster = result.get(0);

		Assert.assertEquals(existingHistWorkflowMaster, newHistWorkflowMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HistWorkflowMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("workflowMasterSid",
				RandomTestUtil.nextInt()));

		List<HistWorkflowMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		HistWorkflowMaster newHistWorkflowMaster = addHistWorkflowMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HistWorkflowMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"workflowMasterSid"));

		Object newWorkflowMasterSid = newHistWorkflowMaster.getWorkflowMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("workflowMasterSid",
				new Object[] { newWorkflowMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingWorkflowMasterSid = result.get(0);

		Assert.assertEquals(existingWorkflowMasterSid, newWorkflowMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HistWorkflowMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"workflowMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("workflowMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected HistWorkflowMaster addHistWorkflowMaster()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		HistWorkflowMaster histWorkflowMaster = _persistence.create(pk);

		histWorkflowMaster.setCreatedBy(RandomTestUtil.nextInt());

		histWorkflowMaster.setFileSize(RandomTestUtil.randomString());

		histWorkflowMaster.setActionDate(RandomTestUtil.nextDate());

		histWorkflowMaster.setWorkflowStatusId(RandomTestUtil.nextInt());

		histWorkflowMaster.setActionFlag(RandomTestUtil.randomString());

		histWorkflowMaster.setModifiedBy(RandomTestUtil.nextInt());

		histWorkflowMaster.setCreatedDate(RandomTestUtil.nextDate());

		histWorkflowMaster.setApprovalLevel(RandomTestUtil.nextInt());

		histWorkflowMaster.setNoOfApproval(RandomTestUtil.nextInt());

		histWorkflowMaster.setFileName(RandomTestUtil.randomString());

		histWorkflowMaster.setUploadedBy(RandomTestUtil.randomString());

		histWorkflowMaster.setModifiedDate(RandomTestUtil.nextDate());

		histWorkflowMaster.setAccClosureMasterSid(RandomTestUtil.nextInt());

		histWorkflowMaster.setNotes(RandomTestUtil.randomString());

		histWorkflowMaster.setWorkflowId(RandomTestUtil.randomString());

		histWorkflowMaster.setProjectionMasterSid(RandomTestUtil.nextInt());

		histWorkflowMaster.setUploadedDate(RandomTestUtil.nextDate());

		histWorkflowMaster.setFileType(RandomTestUtil.randomString());

		histWorkflowMaster.setApprovedBy(RandomTestUtil.nextInt());

		histWorkflowMaster.setWorkflowDescrption(RandomTestUtil.randomString());

		histWorkflowMaster.setApprovedDate(RandomTestUtil.nextDate());

		_histWorkflowMasters.add(_persistence.update(histWorkflowMaster));

		return histWorkflowMaster;
	}

	private List<HistWorkflowMaster> _histWorkflowMasters = new ArrayList<HistWorkflowMaster>();
	private HistWorkflowMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}