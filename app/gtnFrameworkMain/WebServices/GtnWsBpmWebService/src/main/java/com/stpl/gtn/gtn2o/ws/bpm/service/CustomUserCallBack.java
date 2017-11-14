package com.stpl.gtn.gtn2o.ws.bpm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kie.internal.task.api.UserGroupCallback;
import org.springframework.beans.factory.annotation.Autowired;

import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.entity.role.Role;
import com.stpl.gtn.gtn2o.ws.entity.user.User;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.service.userrole.GtnWsUserRoleService;

/**
 *
 * @author STPL
 */

public class CustomUserCallBack implements UserGroupCallback {

	private Map<String, User> userMap = new HashMap<>();
	private Map<String, Role> roleMap = new HashMap<>();
	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(CustomUserCallBack.class);

	@Autowired
	private GtnWsUserRoleService gtnWsUserRoleService;

	public CustomUserCallBack() {
		super();
	}

	public CustomUserCallBack(Map<String, User> userMap, Map<String, Role> roleMap,
			GtnWsUserRoleService gtnWsUserRoleService) {
		super();
		this.userMap = userMap;
		this.roleMap = roleMap;
		this.gtnWsUserRoleService = gtnWsUserRoleService;
	}

	@Override
	public boolean existsUser(String userId) {
		return userMap.containsKey(userId) || userId.equals(GtnFrameworkWebserviceConstant.ADMINISTRATOR);
	}

	@Override
	public boolean existsGroup(String groupId) {
		if (groupId.contains(",")) {
			String[] groups = groupId.split(",");
			if (groups != null) {
				for (int i = 0; i < groups.length; i++) {
					if (roleMap.containsKey(groups[i].trim())) {
						return true;
					}

				}
			}
		}
		return roleMap.containsKey(groupId.trim());

	}

	@Override
	public List<String> getGroupsForUser(String userId, List<String> roleIds, List<String> allExistingGroupIds) {
		List<String> userRoles = new ArrayList<>();
		List<Role> roles = null;
		if (userMap.containsKey(userId)) {
			try {
				roles = gtnWsUserRoleService.getUserRoles(userMap.get(userId).getUserId());
			} catch (Exception e) {
				LOGGER.error("Exception in getGroupsForUser.", e);
			}
			if (roles != null) {
				for (Role role : roles) {
					if (!GtnFrameworkWebserviceConstant.ADMINISTRATOR.equals(role.getName())) {
						userRoles.add(role.getName());
					}
				}
			}
		}
		return userRoles;
	}

	public GtnWsUserRoleService getGtnWsUserRoleService() {
		return gtnWsUserRoleService;
	}

	public void setGtnWsUserRoleService(GtnWsUserRoleService gtnWsUserRoleService) {
		this.gtnWsUserRoleService = gtnWsUserRoleService;
		initializeUserRoleMap();
	}

	public void initializeUserRoleMap() {
		try {

			List<User> usersList = gtnWsUserRoleService.getUserList();
			List<Role> roleList = gtnWsUserRoleService.getUserRoleList();
			for (User user : usersList) {
				userMap.put(user.getScreenName(), user);
			}
			for (Role role : roleList) {
				roleMap.put(role.getName().trim(), role);
			}
			if (userMap.containsKey(GtnFrameworkWebserviceConstant.ADMINISTRATOR)) {
				userMap.remove(GtnFrameworkWebserviceConstant.ADMINISTRATOR);
			}
			if (roleMap.containsKey(GtnFrameworkWebserviceConstant.ADMINISTRATOR)) {
				roleMap.remove(GtnFrameworkWebserviceConstant.ADMINISTRATOR);
			}
		} catch (Exception e) {
			LOGGER.error("Exception in CustomUserCallBack.", e);
		}
	}
}