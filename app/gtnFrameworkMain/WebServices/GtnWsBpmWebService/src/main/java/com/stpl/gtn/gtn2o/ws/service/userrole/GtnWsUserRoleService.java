/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.service.userrole;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import com.stpl.gtn.gtn2o.ws.entity.role.Role;
import com.stpl.gtn.gtn2o.ws.entity.user.User;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.service.GtnWsReturnsDatabaseService;

/**
 *
 * @author STPL
 */
public class GtnWsUserRoleService {

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsUserRoleService.class);
	
	@Autowired
	private GtnWsReturnsDatabaseService databaseService;
	
	public GtnWsUserRoleService() {
	super();
	}

	
	public GtnWsUserRoleService(GtnWsReturnsDatabaseService databaseService) {
		super();
		this.databaseService = databaseService;
	}



	public GtnWsReturnsDatabaseService getDatabaseService() {
		return databaseService;
	}

	public void setDatabaseService(GtnWsReturnsDatabaseService databaseService) {
		this.databaseService = databaseService;
	}

	public User getUser(long userId) {
		User user = null;
		Session session = null;
		try {
			session = databaseService.getSysSessionFactory().openSession();
			user = (User) session.get(User.class, userId);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<Role> getUserRoles(long userId) {
		List<Object[]> results = new ArrayList<>();
		List<Role> roles = new ArrayList<>();
		Session session = null;
		try {
			session = databaseService.getSysSessionFactory().openSession();
			StringBuilder userRoleQuery = new StringBuilder(
					"select r.roleId,r.name from Role_ r join Users_Roles UR ON UR.roleId =r.roleId AND UR.userId=");
			userRoleQuery.append(userId);
			Query query = session.createSQLQuery(userRoleQuery.toString());
			results = query.list();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		if (results != null && !results.isEmpty()) {
			int roleListSize = results.size();
			for (int i = 0; i < roleListSize; i++) {
				Role role = new Role();
				final Object[] obj = results.get(i);
				role.setRoleId(obj[0] == null ? 0 : Long.valueOf(String.valueOf(obj[0])));
				role.setName(String.valueOf(obj[1]));
				roles.add(role);
			}
		}
		return roles;
	}

	@SuppressWarnings("unchecked")
	public List<User> getUserList() {
		List<User> results = null;
		Session session = null;
		try {
			session = databaseService.getSysSessionFactory().openSession();
			Criteria cr = session.createCriteria(User.class);
			results = cr.list();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return results;
	}

	@SuppressWarnings("unchecked")
	public List<Role> getUserRoleList() {
		List<Role> roles = new ArrayList<>();
		Session session = null;
		try {
			session = databaseService.getSysSessionFactory().openSession();
			Criteria cr = session.createCriteria(Role.class);
			roles = cr.list();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return roles;
	}

	public List<Object[]> testData() {
		List<Object[]> results = new ArrayList<>();
		Object[] obj1 = { 10167, "Power User" };
		Object[] obj2 = { 10168, "User" };
		Object[] obj3 = { 775456, "admin" };
		Object[] obj4 = { 775457, "analyst" };
		Object[] obj5 = { 781527, "Contract Analyst Manager" };
		Object[] obj6 = { 781528, "Contract Analyst Heavy Director" };
		Object[] obj7 = { 782101, "Approver 1" };
		Object[] obj8 = { 782102, "Approver 2" };
		results.add(obj1);
		results.add(obj2);
		results.add(obj3);
		results.add(obj4);
		results.add(obj5);
		results.add(obj6);
		results.add(obj7);
		results.add(obj8);

		return results;
	}
}
