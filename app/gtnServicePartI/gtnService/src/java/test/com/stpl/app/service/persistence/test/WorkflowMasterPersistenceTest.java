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

import com.stpl.app.exception.NoSuchWorkflowMasterException;
import com.stpl.app.model.WorkflowMaster;
import com.stpl.app.service.WorkflowMasterLocalServiceUtil;
import com.stpl.app.service.persistence.WorkflowMasterPersistence;
import com.stpl.app.service.persistence.WorkflowMasterUtil;

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
public class WorkflowMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = WorkflowMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<WorkflowMaster> iterator = _workflowMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		WorkflowMaster workflowMaster = _persistence.create(pk);

		Assert.assertNotNull(workflowMaster);

		Assert.assertEquals(workflowMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		WorkflowMaster newWorkflowMaster = addWorkflowMaster();

		_persistence.remove(newWorkflowMaster);

		WorkflowMaster existingWorkflowMaster = _persistence.fetchByPrimaryKey(newWorkflowMaster.getPrimaryKey());

		Assert.assertNull(existingWorkflowMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addWorkflowMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		WorkflowMaster newWorkflowMaster = _persistence.create(pk);

		newWorkflowMaster.setCreatedBy(RandomTestUtil.nextInt());

		newWorkflowMaster.setFileSize(RandomTestUtil.randomString());

		newWorkflowMaster.setWorkflowStatusId(RandomTestUtil.nextInt());

		newWorkflowMaster.setModifiedBy(RandomTestUtil.nextInt());

		newWorkflowMaster.setCreatedDate(RandomTestUtil.nextDate());

		newWorkflowMaster.setApprovalLevel(RandomTestUtil.nextInt());

		newWorkflowMaster.setNoOfApproval(RandomTestUtil.nextInt());

		newWorkflowMaster.setFileName(RandomTestUtil.randomString());

		newWorkflowMaster.setUploadedBy(RandomTestUtil.randomString());

		newWorkflowMaster.setModifiedDate(RandomTestUtil.nextDate());

		newWorkflowMaster.setAccClosureMasterSid(RandomTestUtil.nextInt());

		newWorkflowMaster.setNotes(RandomTestUtil.randomString());

		newWorkflowMaster.setWorkflowId(RandomTestUtil.randomString());

		newWorkflowMaster.setProjectionMasterSid(RandomTestUtil.nextInt());

		newWorkflowMaster.setUploadedDate(RandomTestUtil.nextDate());

		newWorkflowMaster.setFileType(RandomTestUtil.randomString());

		newWorkflowMaster.setApprovedBy(RandomTestUtil.nextInt());

		newWorkflowMaster.setWorkflowDescrption(RandomTestUtil.randomString());

		newWorkflowMaster.setApprovedDate(RandomTestUtil.nextDate());

		newWorkflowMaster.setContractMasterSid(RandomTestUtil.nextInt());

		newWorkflowMaster.setContractStructure(RandomTestUtil.randomString());

		_workflowMasters.add(_persistence.update(newWorkflowMaster));

		WorkflowMaster existingWorkflowMaster = _persistence.findByPrimaryKey(newWorkflowMaster.getPrimaryKey());

		Assert.assertEquals(existingWorkflowMaster.getCreatedBy(),
			newWorkflowMaster.getCreatedBy());
		Assert.assertEquals(existingWorkflowMaster.getFileSize(),
			newWorkflowMaster.getFileSize());
		Assert.assertEquals(existingWorkflowMaster.getWorkflowStatusId(),
			newWorkflowMaster.getWorkflowStatusId());
		Assert.assertEquals(existingWorkflowMaster.getModifiedBy(),
			newWorkflowMaster.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingWorkflowMaster.getCreatedDate()),
			Time.getShortTimestamp(newWorkflowMaster.getCreatedDate()));
		Assert.assertEquals(existingWorkflowMaster.getApprovalLevel(),
			newWorkflowMaster.getApprovalLevel());
		Assert.assertEquals(existingWorkflowMaster.getNoOfApproval(),
			newWorkflowMaster.getNoOfApproval());
		Assert.assertEquals(existingWorkflowMaster.getFileName(),
			newWorkflowMaster.getFileName());
		Assert.assertEquals(existingWorkflowMaster.getUploadedBy(),
			newWorkflowMaster.getUploadedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingWorkflowMaster.getModifiedDate()),
			Time.getShortTimestamp(newWorkflowMaster.getModifiedDate()));
		Assert.assertEquals(existingWorkflowMaster.getAccClosureMasterSid(),
			newWorkflowMaster.getAccClosureMasterSid());
		Assert.assertEquals(existingWorkflowMaster.getNotes(),
			newWorkflowMaster.getNotes());
		Assert.assertEquals(existingWorkflowMaster.getWorkflowMasterSid(),
			newWorkflowMaster.getWorkflowMasterSid());
		Assert.assertEquals(existingWorkflowMaster.getWorkflowId(),
			newWorkflowMaster.getWorkflowId());
		Assert.assertEquals(existingWorkflowMaster.getProjectionMasterSid(),
			newWorkflowMaster.getProjectionMasterSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingWorkflowMaster.getUploadedDate()),
			Time.getShortTimestamp(newWorkflowMaster.getUploadedDate()));
		Assert.assertEquals(existingWorkflowMaster.getFileType(),
			newWorkflowMaster.getFileType());
		Assert.assertEquals(existingWorkflowMaster.getApprovedBy(),
			newWorkflowMaster.getApprovedBy());
		Assert.assertEquals(existingWorkflowMaster.getWorkflowDescrption(),
			newWorkflowMaster.getWorkflowDescrption());
		Assert.assertEquals(Time.getShortTimestamp(
				existingWorkflowMaster.getApprovedDate()),
			Time.getShortTimestamp(newWorkflowMaster.getApprovedDate()));
		Assert.assertEquals(existingWorkflowMaster.getContractMasterSid(),
			newWorkflowMaster.getContractMasterSid());
		Assert.assertEquals(existingWorkflowMaster.getContractStructure(),
			newWorkflowMaster.getContractStructure());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		WorkflowMaster newWorkflowMaster = addWorkflowMaster();

		WorkflowMaster existingWorkflowMaster = _persistence.findByPrimaryKey(newWorkflowMaster.getPrimaryKey());

		Assert.assertEquals(existingWorkflowMaster, newWorkflowMaster);
	}

	@Test(expected = NoSuchWorkflowMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<WorkflowMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("WORKFLOW_MASTER",
			"createdBy", true, "fileSize", true, "workflowStatusId", true,
			"modifiedBy", true, "createdDate", true, "approvalLevel", true,
			"noOfApproval", true, "fileName", true, "uploadedBy", true,
			"modifiedDate", true, "accClosureMasterSid", true, "notes", true,
			"workflowMasterSid", true, "workflowId", true,
			"projectionMasterSid", true, "uploadedDate", true, "fileType",
			true, "approvedBy", true, "workflowDescrption", true,
			"approvedDate", true, "contractMasterSid", true,
			"contractStructure", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		WorkflowMaster newWorkflowMaster = addWorkflowMaster();

		WorkflowMaster existingWorkflowMaster = _persistence.fetchByPrimaryKey(newWorkflowMaster.getPrimaryKey());

		Assert.assertEquals(existingWorkflowMaster, newWorkflowMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		WorkflowMaster missingWorkflowMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingWorkflowMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		WorkflowMaster newWorkflowMaster1 = addWorkflowMaster();
		WorkflowMaster newWorkflowMaster2 = addWorkflowMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newWorkflowMaster1.getPrimaryKey());
		primaryKeys.add(newWorkflowMaster2.getPrimaryKey());

		Map<Serializable, WorkflowMaster> workflowMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, workflowMasters.size());
		Assert.assertEquals(newWorkflowMaster1,
			workflowMasters.get(newWorkflowMaster1.getPrimaryKey()));
		Assert.assertEquals(newWorkflowMaster2,
			workflowMasters.get(newWorkflowMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, WorkflowMaster> workflowMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(workflowMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		WorkflowMaster newWorkflowMaster = addWorkflowMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newWorkflowMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, WorkflowMaster> workflowMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, workflowMasters.size());
		Assert.assertEquals(newWorkflowMaster,
			workflowMasters.get(newWorkflowMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, WorkflowMaster> workflowMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(workflowMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		WorkflowMaster newWorkflowMaster = addWorkflowMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newWorkflowMaster.getPrimaryKey());

		Map<Serializable, WorkflowMaster> workflowMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, workflowMasters.size());
		Assert.assertEquals(newWorkflowMaster,
			workflowMasters.get(newWorkflowMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = WorkflowMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<WorkflowMaster>() {
				@Override
				public void performAction(WorkflowMaster workflowMaster) {
					Assert.assertNotNull(workflowMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		WorkflowMaster newWorkflowMaster = addWorkflowMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(WorkflowMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("workflowMasterSid",
				newWorkflowMaster.getWorkflowMasterSid()));

		List<WorkflowMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		WorkflowMaster existingWorkflowMaster = result.get(0);

		Assert.assertEquals(existingWorkflowMaster, newWorkflowMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(WorkflowMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("workflowMasterSid",
				RandomTestUtil.nextInt()));

		List<WorkflowMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		WorkflowMaster newWorkflowMaster = addWorkflowMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(WorkflowMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"workflowMasterSid"));

		Object newWorkflowMasterSid = newWorkflowMaster.getWorkflowMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("workflowMasterSid",
				new Object[] { newWorkflowMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingWorkflowMasterSid = result.get(0);

		Assert.assertEquals(existingWorkflowMasterSid, newWorkflowMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(WorkflowMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"workflowMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("workflowMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected WorkflowMaster addWorkflowMaster() throws Exception {
		int pk = RandomTestUtil.nextInt();

		WorkflowMaster workflowMaster = _persistence.create(pk);

		workflowMaster.setCreatedBy(RandomTestUtil.nextInt());

		workflowMaster.setFileSize(RandomTestUtil.randomString());

		workflowMaster.setWorkflowStatusId(RandomTestUtil.nextInt());

		workflowMaster.setModifiedBy(RandomTestUtil.nextInt());

		workflowMaster.setCreatedDate(RandomTestUtil.nextDate());

		workflowMaster.setApprovalLevel(RandomTestUtil.nextInt());

		workflowMaster.setNoOfApproval(RandomTestUtil.nextInt());

		workflowMaster.setFileName(RandomTestUtil.randomString());

		workflowMaster.setUploadedBy(RandomTestUtil.randomString());

		workflowMaster.setModifiedDate(RandomTestUtil.nextDate());

		workflowMaster.setAccClosureMasterSid(RandomTestUtil.nextInt());

		workflowMaster.setNotes(RandomTestUtil.randomString());

		workflowMaster.setWorkflowId(RandomTestUtil.randomString());

		workflowMaster.setProjectionMasterSid(RandomTestUtil.nextInt());

		workflowMaster.setUploadedDate(RandomTestUtil.nextDate());

		workflowMaster.setFileType(RandomTestUtil.randomString());

		workflowMaster.setApprovedBy(RandomTestUtil.nextInt());

		workflowMaster.setWorkflowDescrption(RandomTestUtil.randomString());

		workflowMaster.setApprovedDate(RandomTestUtil.nextDate());

		workflowMaster.setContractMasterSid(RandomTestUtil.nextInt());

		workflowMaster.setContractStructure(RandomTestUtil.randomString());

		_workflowMasters.add(_persistence.update(workflowMaster));

		return workflowMaster;
	}

	private List<WorkflowMaster> _workflowMasters = new ArrayList<WorkflowMaster>();
	private WorkflowMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}