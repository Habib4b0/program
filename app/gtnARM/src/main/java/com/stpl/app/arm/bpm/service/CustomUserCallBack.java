package com.stpl.app.arm.bpm.service;

import com.stpl.app.utils.VariableConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kie.internal.task.api.UserGroupCallback;

import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.Role;
import com.stpl.portal.model.User;
import com.stpl.portal.service.RoleLocalServiceUtil;
import com.stpl.portal.service.UserLocalServiceUtil;
import org.jboss.logging.Logger;

/**
 *
 * @author asha
 */
public class CustomUserCallBack implements UserGroupCallback {

    Map<String, User> userMap = new HashMap<>();
    Map<String, Role> roleMap = new HashMap<>();
    private static final Logger LOGGER = Logger.getLogger(CustomUserCallBack.class);

    public CustomUserCallBack() {
        try {
            List<User> usersList = UserLocalServiceUtil.getUsers(0, UserLocalServiceUtil.getUsersCount());
            List<Role> groupList = RoleLocalServiceUtil.getRoles(0, RoleLocalServiceUtil.getRolesCount());
            for (User user : usersList) {
                userMap.put(user.getScreenName(), user);
            }
            for (Role role : groupList) {
                roleMap.put(role.getName(), role);
            }
            if (userMap.containsKey(VariableConstants.ADMINISTRATOR)) {
                userMap.remove(VariableConstants.ADMINISTRATOR);
            }
            if (roleMap.containsKey(VariableConstants.ADMINISTRATOR)) {
                roleMap.remove(VariableConstants.ADMINISTRATOR);
            }
        } catch (SystemException e) {

            LOGGER.error("Error in CustomUserCallBack :"+e);
        }
    }

    @Override
    public boolean existsUser(String userId) {
        return userMap.containsKey(userId) || userId.equals(VariableConstants.ADMINISTRATOR);
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
        return roleMap.containsKey(groupId);
    }

    @Override
    public List<String> getGroupsForUser(String userId, List<String> roleIds, List<String> allExistingGroupIds) {
        List<String> userRoles = new ArrayList<>();
        List<Role> roles = null;
        if (userMap.containsKey(userId)) {
            try {
                roles = RoleLocalServiceUtil.getUserRoles(userMap.get(userId).getUserId());
            } catch (SystemException e) {
                LOGGER.error("Error in getGroupsForUser :"+e);
            }
            for (Role role : roles) {
                if (!VariableConstants.ADMINISTRATOR.equals(role.getName())) {
                    userRoles.add(role.getName());
                }
            }
        }
        return userRoles;
    }
}
